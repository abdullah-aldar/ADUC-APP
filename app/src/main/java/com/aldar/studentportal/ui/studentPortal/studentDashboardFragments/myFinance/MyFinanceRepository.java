package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myFinance;

import com.aldar.studentportal.models.financeModel.FinanceResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import io.reactivex.Observable;

public class MyFinanceRepository {
    private APIService apiService;
    private Observable<FinanceResponseModel> financeResponseObservable;

}
