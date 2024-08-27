# from fbprophet import Prophet
#
# # Prophet 데이터 준비
# prophet_data = data.reset_index()[['contract_date', 'trade_price']]
# prophet_data.columns = ['ds', 'y']
#
# # 모델 학습
# model = Prophet()
# model.fit(prophet_data)
#
# # 예측
# future = model.make_future_dataframe(periods=12, freq='M')
# forecast = model.predict(future)
#
# # 결과 시각화
# model.plot(forecast)
# plt.show()
