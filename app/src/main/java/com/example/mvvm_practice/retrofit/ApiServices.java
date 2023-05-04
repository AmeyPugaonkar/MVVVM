package com.example.mvvm_practice.retrofit;

import com.example.mvvm_practice.model.CompanyDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("/company/save")
    Call<CompanyDataModel> postCompanyData(@Body CompanyDataModel companyDataModel);

}
