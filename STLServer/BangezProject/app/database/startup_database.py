import asyncio
import os
import motor
from dotenv import load_dotenv
from fastapi import FastAPI
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


async def start_save_apt_rent(parsing_data: list):
    await aptRents_collection.insert_many(parsing_data)


async def start_save_apt_trade(parsing_data: list):
    await aptTrades_collection.insert_many(parsing_data)


async def start_save_city_park(parsing_data: list):
    await cityParks_collection.insert_many(parsing_data)


async def start_save_officetel_rent(parsing_data: list):
    await officetelRents_collection.insert_many(parsing_data)


async def start_save_officetel_trade(parsing_data: list):
    await officetelTrades_collection.insert_many(parsing_data)


async def start_save_school(parsing_data: list):
    await schools_collection.insert_many(parsing_data)


async def exist_collection():
    collections = await client.mongo_db.list_collection_names()
    return collections


if __name__ == '__main__':
    asyncio.get_event_loop().run_until_complete(exist_collection())