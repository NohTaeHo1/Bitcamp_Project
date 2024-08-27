# import asyncio
# import os
# import motor
# from dotenv import load_dotenv
# from fastapi import FastAPI
# from motor.motor_asyncio import AsyncIOMotorClient
#
#
# app = FastAPI()
# load_dotenv()
#
# client = motor.motor_asyncio.AsyncIOMotorClient(os.getenv('MONGODB_URL'))
# aptTrades_collection = client.mongo_db.aptTrades
# officetelTrades_collection = client.mongo_db.officetelTrades
#
# async def read_apt_trades():
#     read = aptTrades_collection.find({})
#     apt_trades = await read.to_list(None)
#     return apt_trades
#
# if __name__ == '__main__':
#     test = asyncio.run(read_apt_trades())
#     print(test)
#     print(type(test))
#     test2 = test.pop(1)
#     print(type(test2))
