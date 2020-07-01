package com.aldar.studentportal.ui.studentPortal.myFinance;

import com.aldar.studentportal.models.financeModel.FinanceResponseModel;
import com.aldar.studentportal.remote.APIService;

import io.reactivex.Observable;

public class MyFinanceRepository {
    private APIService apiService;
    private Observable<FinanceResponseModel> financeResponseObservable;

}
