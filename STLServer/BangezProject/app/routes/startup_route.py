import asyncio

from fastapi import FastAPI

from app.database.startup_database import exist_collection
from app.services.startup_service.apt_rent import startup_apt_rent
from app.services.startup_service.apt_trade import startup_apt_trade
from app.services.startup_service.city_park import startup_city_park
from app.services.startup_service.officetel_rent import startup_officetel_rent
from app.services.startup_service.officetel_trade import startup_officetel_trade
from app.services.startup_service.school import startup_school

app = FastAPI()


async def save_mongodb():
    print('save_mongodb 시작')

    collections: list = await exist_collection()
    print('collections 읽어들임')

    if 'a' not in collections:
        await startup_apt_rent()
    else:
        print('aptRents collection exist')

    if 'a' not in collections:
        await startup_apt_trade()
    else:
        print('aptTrades collection exist')

    if 'a' not in collections:
        await startup_officetel_rent()
    else:
        print('officetelRents collection exist')

    if 'a' not in collections:
        await startup_officetel_trade()
    else:
        print('officetelTrades collection exist')

    if 'a' not in collections:
        await startup_city_park()
    else:
        print('cityPark collection exist')

    if 'a' not in collections:
        await startup_school()
    else:
        print('school collection exist')

    print('save_mongodb 완료')