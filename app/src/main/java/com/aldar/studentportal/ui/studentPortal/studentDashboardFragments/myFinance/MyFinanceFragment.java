package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myFinance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.StudentFinanceAdapter;
import com.aldar.studentportal.databinding.FragmentMyFinanceBinding;
import com.aldar.studentportal.models.financeModel.FinanceResponseModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class  MyFinanceFragment extends Fragment {
    private FragmentMyFinanceBinding binding;
    private StudentFinanceAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_finance,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyFinanceViewModel viewModel =  new ViewModelProvider(this).get(MyFinanceViewModel.class);
        binding.rvFinance.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setFinanceViewModel(viewModel);

        financeData(viewModel.getFinanceData());

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    private void financeData(MutableLiveData<FinanceResponseModel> mutableLiveData){
        mutableLiveData.observe(getViewLifecycleOwner(),financeResponseModel -> {
            if(financeResponseModel != null){
                adapter = new StudentFinanceAdapter(financeResponseModel.getData());
                binding.rvFinance.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }
}
