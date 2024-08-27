import datetime
import os

import motor
from dotenv import load_dotenv
from fastapi import FastAPI
import asyncio
from motor.motor_asyncio import AsyncIOMotorClient

app = FastAPI()
load_dotenv()

client = motor.motor_asyncio.AsyncIOMotorClient('mongodb://root:bangezez@223.130.158.84:27017/?authSource=admin')
aptRents_collection = client.bangez_mongodb.aptRents
aptTrades_collection = client.bangez_mongodb.aptTrades
cityParks_collection = client.bangez_mongodb.cityParks
officetelRents_collection = client.bangez_mongodb.officetelRents
officetelTrades_collection = client.bangez_mongodb.officetelTrades
schools_collection = client.bangez_mongodb.schools


current = datetime.datetime.now()
formatted_date = current.strftime("%Y%m%d")


async def schedule_save_apt_rent(parsing_data: list):
    for data in parsing_data:
        existing_data = await aptRents_collection.find_one({
            "apt_name": data["apt_name"],
            "contract_date": data["contract_date"],
            "floor": data["floor"],
            "security_deposit": data["security_deposit"]
        })
        if not existing_data:
            await aptRents_collection.insert_one(data)


async def schedule_save_apt_trade(parsing_data: list):
    for data in parsing_data:
        existing_data = await aptTrades_collection.find_one({
            "apt_name": data["apt_name"],
            "contract_date": data["contract_date"],
        })
        if not existing_data:
            await aptTrades_collection.insert_one(data)


async def schedule_save_city_park(parsing_data: list):
    for i in parsing_data:
        existing_data = await cityParks_collection.find_one(i)
        if not existing_data:
            await cityParks_collection.insert_one(parsing_data)


async def schedule_save_officetel_rent(parsing_data: list):
    for data in parsing_data:
        existing_data = await officetelRents_collection.find_one({
            "officetel_name": data["officetel_name"],
            "contract_date": data["contract_date"],
        })
        if not existing_data:
            await officetelRents_collection.insert_one(data)


async def schedule_save_officetel_trade(parsing_data: list):
    for data in parsing_data:
        existing_data = await officetelTrades_collection.find_one({
            "officetel_name": data["officetel_name"],
            "contract_date": data["contract_date"],
        })
        if not existing_data:
            await officetelTrades_collection.insert_one(data)


async def schedule_save_school(parsing_data: list):
    for i in parsing_data:
        existing_data = await schools_collection.find_one(i)
        if not existing_data:
            await schools_collection.insert_one(parsing_data)


if __name__ == '__main__':
    # asyncio.get_event_loop().run_until_complete(test2()) #비동기실행
    #
    # asyncio.run(test2()) #동기실행

    print('test')
