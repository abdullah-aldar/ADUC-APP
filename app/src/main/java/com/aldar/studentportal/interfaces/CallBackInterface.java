package com.aldar.studentportal.interfaces;

import com.aldar.studentportal.models.financeModel.FinanceResponseModel;

import io.reactivex.Observable;

public interface CallBackInterface {

    Observable<FinanceResponseModel> financeCallBackResponse();
}
