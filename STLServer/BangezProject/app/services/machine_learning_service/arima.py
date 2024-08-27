# import asyncio
# import datetime
#
# import numpy as np
# import pandas as pd
# from sklearn.metrics import mean_absolute_error, mean_squared_error, r2_score
# from statsmodels.tsa.arima.model import ARIMA
# import joblib
#
# from app.database.machine_learning_database import read_apt_trades
#
#
# def arima_model_learning(apt_trade: list):
#     df = pd.DataFrame(apt_trade)
#     df['contract_date'] = pd.to_datetime(df['contract_date'], format='%Y%m%d')
#
#     df['year_month'] = df['contract_date'].dt.to_period('M')
#     avg_price = df.groupby('year_month')['trade_price'].mean()
#
#     df = pd.DataFrame({'year_month': avg_price.index, 'avg_trade_price': avg_price.values})
#
#     learn_df = df[df['year_month'] < '2024-01' ]
#     confirm_df = df[df['year_month'] >= '2024-01']
#     confirm_df = confirm_df[confirm_df['year_month'] < '2024-08']
#
#     print(confirm_df.T)
#     print(f"학습데이터 수 : {learn_df.count()}")
#     print(f"평가데이터 수 : {confirm_df.count()}")
#     model = ARIMA(learn_df['avg_trade_price'], order=(1, 1, 1)).fit()
#
#     predict_date = pd.date_range(start='2024-01', end='2024-07', freq='MS')
#
#     arima_forecast = model.forecast(steps=len(predict_date))
#     arima_predictions = pd.DataFrame({'date': predict_date, 'predicted_avg_trade_price': arima_forecast})
#
#     mae = mean_absolute_error(confirm_df['avg_trade_price'], arima_predictions['predicted_avg_trade_price'])
#     mse = mean_squared_error(confirm_df['avg_trade_price'], arima_predictions['predicted_avg_trade_price'])
#     rmse = np.sqrt(mse)
#     r2 = r2_score(confirm_df['avg_trade_price'], arima_predictions['predicted_avg_trade_price'])
#
#     # # 모델 저장
#     # joblib.dump(model, 'arima_model.pkl')
#     print("평가 지표:")
#     print(f"MSE: {mse}")
#     print(f"MAE: {mae}")
#     print(f"RMSE: {rmse}")
#     print(f"R2: {r2}")
#
# if __name__ == '__main__':
#     list = asyncio.run(read_apt_trades())
#     arima_model_learning(list)
#
#     # # 모델 로드
#     # model = joblib.load('arima_model.pkl')