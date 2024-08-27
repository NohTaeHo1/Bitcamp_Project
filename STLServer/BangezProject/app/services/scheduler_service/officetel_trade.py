import asyncio
import datetime
import json
import os

import pandas as pd
import requests
from bs4 import BeautifulSoup as bs
from dotenv import load_dotenv

from app.database.schedule_database import schedule_save_officetel_trade

load_dotenv()
dir = os.path.dirname(__file__)
data_path = os.path.join(dir, '../../static_data/legal_info_b_seoul.csv')


async def officetel_trade_parsing():

    df = pd.read_csv(data_path)

    curtrade = datetime.datetime.now()
    deal_ymd = curtrade.strftime('%Y%m')

    LAWD_CD_list = df['법정동시군구코드'].unique()

    api_key = 'KdkuO4SDl1tiDcQGsbbxcJWaTb0FxTWt2xQ0JrXrJ4hblgEKtvgDxux84LkVgjDt3r50lENl1GmZ5HvqnuJ23Q=='

    column_nm = ['거래금액', '거래유형', '건축년도', '년', '단지', '매도자', '매수자',
                 '법정동', '시군구', '월세금액', '월', '일', '전용면적', '중개사소재지',
                 '지번', '지역코드', '층']

    total = pd.DataFrame()

    for i in range(len(LAWD_CD_list)):
        url = 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiTrade'
        params = {'serviceKey': api_key, 'LAWD_CD': LAWD_CD_list[i], 'DEAL_YMD': deal_ymd}

        res = requests.get(url, params)
        soup = bs(res.text, 'xml')
        items = soup.find_all('item')

        for k in range(len(items)):
            df_raw = []
            for j in column_nm:
                try:
                    df_raw.append(items[k].find(j).text)
                except:
                    df_raw.append('존재하지 않음')

            df = pd.DataFrame(df_raw).T
            df.columns = column_nm

            total = pd.concat([total, df])

    total.columns = column_nm

    return total


async def officetel_trade_preprocess(parsing_data: pd.DataFrame):

    legal_info_b_seoul = pd.read_csv(data_path).astype({'법정동코드': str, '읍면동명': str})

    officetel_trade = parsing_data

    officetel_trade = officetel_trade[['건축년도', '거래금액', '전용면적', '법정동', '단지', '층', '년', '월', '일', '시군구', '지번']]
    officetel_trade = officetel_trade.copy()

    officetel_trade.rename(columns={'지역코드': '법정동시군구코드'}, inplace=True)
    officetel_trade.rename(columns={'법정동': '읍면동명'}, inplace=True)

    officetel_trade = officetel_trade[officetel_trade['건축년도'].notnull()]
    officetel_trade = officetel_trade.astype({'읍면동명': str})

    officetel_trade['읍면동명'] = officetel_trade['읍면동명'].str.strip().str.lower()
    legal_info_b_seoul['읍면동명'] = legal_info_b_seoul['읍면동명'].str.strip().str.lower()

    officetel_trade_2 = pd.merge(officetel_trade, legal_info_b_seoul, on=['읍면동명'], how='left')
    officetel_trade_2 = officetel_trade_2.where(pd.notnull(officetel_trade_2), '')

    officetel_trade_2['시도명'] = officetel_trade_2['시도명'].str.strip()
    officetel_trade_2['시군구명'] = officetel_trade_2['시군구명'].str.strip()
    officetel_trade_2['읍면동명'] = officetel_trade_2['읍면동명'].str.strip()

    officetel_trade_2 = officetel_trade_2.where(pd.notnull(officetel_trade_2), '')
    officetel_trade_2['주소'] = officetel_trade_2['시도명'] + ' ' + officetel_trade_2['시군구명'] + ' ' + officetel_trade_2[
        '읍면동명']

    officetel_trade_2['주소'] = officetel_trade_2['주소'].str.replace('  ', ' ')
    officetel_trade_2['주소'] = officetel_trade_2['주소'].str.strip()

    officetel_trade_2 = officetel_trade_2.replace('충청북도 청주시 상당구 북문로2가동', '충청북도 청주시 상당구 북문로2가')
    officetel_trade_2 = officetel_trade_2.replace('충청북도 청주시 상당구 북문로3가동', '충청북도 청주시 상당구 북문로3가')
    officetel_trade_2 = officetel_trade_2.replace('충청북도 청주시 상당구 남문로1가동', '충청북도 청주시 상당구 남문로1가')

    officetel_trade_2['계약날짜'] = pd.to_datetime(officetel_trade_2['년'] + officetel_trade_2['월'] + officetel_trade_2['일'],
                                               format='%Y%m%d').dt.strftime('%Y%m%d')

    return officetel_trade_2


async def officetel_trade_select_columns(preprocessed_data: pd.DataFrame):

    officetel_trade_final = preprocessed_data[['건축년도', '단지', '거래금액', '계약날짜', '전용면적', '주소', '법정동코드', '층']]
    officetel_trade_final_copy = officetel_trade_final.copy()
    officetel_trade_final_copy.rename(columns={'건축년도': 'built_year', '단지': 'officetel_name', '거래금액': 'trade_price',
                                               '계약날짜': 'contract_date', '전용면적': 'net_leasable_area',
                                               '주소': 'address', '법정동코드': 'legal_code', '층': 'floor'}, inplace=True)
    officetel_trade_final_copy['ward'] = officetel_trade_final_copy['address'].apply(lambda x: x.split(' ')).apply(lambda x: x[1] if len(x) > 2 else '')
    officetel_trade_final_copy['trade_price'] = officetel_trade_final_copy['trade_price'].apply(lambda x: x.replace(' ', '')). apply(lambda x: x.replace(',', '')).astype(float)
    officetel_trade_final_copy['net_leasable_area'] = officetel_trade_final_copy['net_leasable_area'].astype(float)
    officetel_trade_final_copy['price_per_area'] = officetel_trade_final_copy['trade_price'] / officetel_trade_final_copy['net_leasable_area']
    officetel_trade_final_copy.astype(str)
    officetel_trade_final_copy[officetel_trade_final_copy.select_dtypes(include=['object']).columns] = officetel_trade_final_copy.select_dtypes(include=['object']).apply(
        lambda x: x.str.strip())
    return officetel_trade_final_copy


async def schedule_officetel_trade():

    df = await officetel_trade_parsing()
    df = await officetel_trade_preprocess(df)
    df = await officetel_trade_select_columns(df)

    total_json = json.loads(df.to_json(orient='records'))  # columns, records, index, values

    await schedule_save_officetel_trade(total_json)

    current = datetime.datetime.now()
    print("schedule_officetel_trade 데이터 업데이트 완료: ", current)


if __name__ == '__main__':
    print('test')
    asyncio.run(schedule_officetel_trade())