package com.example.mvvm_practice.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_practice.model.CompanyDataModel;
import com.example.mvvm_practice.retrofit.ApiServices;
import com.example.mvvm_practice.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDataViewModel extends ViewModel {

    Context context;

    public CompanyDataViewModel(Context context) {
        this.context = context;
    }

    public MutableLiveData<CompanyDataModel> companyDataModel;
    public CompanyDataViewModel()
    {
        companyDataModel = new MutableLiveData<>();
    }

    public MutableLiveData<CompanyDataModel> postCompanyName(){
        return companyDataModel;
    }

    public void makePostCall(String name)
    {
        CompanyDataModel companyDataModel1 = new CompanyDataModel(name);
        ApiServices apiServices = RetrofitInstance.getRetrofitClient().create(ApiServices.class);
        Call<CompanyDataModel> call = apiServices.postCompanyData(companyDataModel1);
        try {
            call.enqueue(new Callback<CompanyDataModel>() {
                @Override
                public void onResponse(Call<CompanyDataModel> call, Response<CompanyDataModel> response) {

                    companyDataModel.postValue(companyDataModel1);
                    List<CompanyDataModel> GenratedData = new ArrayList<>();
                    GenratedData.add(response.body());
                }

                @Override
                public void onFailure(Call<CompanyDataModel> call, Throwable t) {
                  Log.d("hello",t.getMessage());
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
//        call.enqueue(new Callback<CompanyDataModel>() {
//            @Override
//            public void onResponse(Call<CompanyDataModel> call, Response<CompanyDataModel> response) {
//
//                Log.d("RESPONSE CODE",""+response.code());
//                if(!response.isSuccessful())
//                {
//                    Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
//
//                    return;
//                }
//
//                companyDataModel.postValue(companyDataModel1);
//
//                List<CompanyDataModel> GenratedData = new ArrayList<>();
//                GenratedData.add(response.body());
//
//                Toast.makeText(context, ""+GenratedData.size(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<CompanyDataModel> call, Throwable t) {
//                //Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
