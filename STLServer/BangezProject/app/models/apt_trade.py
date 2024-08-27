# 데이터베이스 모델 정의 예시
import datetime

from pydantic import BaseModel

class AptTrade(BaseModel):
    address: str
    apt_name: str
    built_year: int
    contract_date: datetime
    floor: int
    legal_code: int
    net_leasable_area: float
    price_per_area: float
    trade_price: int
    ward: str


