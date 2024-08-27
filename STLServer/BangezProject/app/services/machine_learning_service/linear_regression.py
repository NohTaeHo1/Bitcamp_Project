# import asyncio
#
# from sklearn.model_selection import train_test_split
# from sklearn.linear_model import LinearRegression
# from sklearn.metrics import mean_squared_error, r2_score
#
# from app.database.machine_learning_database import read_apt_trades
# import pandas as pd
#
#
# def model_learning(apt_trade: list):
#     df = pd.DataFrame(apt_trade)
#     # 독립 변수와 종속 변수 설정
#     X = df[['built_year', 'floor', 'built_year']]
#     y = df['trade_price']
#
#     # 데이터 분할
#     X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
#
#     # 모델 학습
#     model = LinearRegression()
#     model.fit(X_train, y_train)
#
#     # 예측 및 평가
#     y_pred = model.predict(X_test)
#     mse = mean_squared_error(y_test, y_pred)
#     r2 = r2_score(y_test, y_pred)
#
#     print(f'MSE: {mse}, R^2: {r2}')
#
#
# if __name__ == '__main__':
#     apt = asyncio.run(read_apt_trades())
#     df = pd.DataFrame(apt)
#     print(df.head(3).T)
