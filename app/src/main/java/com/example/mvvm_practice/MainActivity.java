package com.example.mvvm_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvm_practice.model.CompanyDataModel;
import com.example.mvvm_practice.viewmodels.CompanyDataViewModel;

public class MainActivity extends AppCompatActivity {

    EditText nameEdt;
    Button submitBtn;
    TextView dataTxt;
    CompanyDataViewModel companyDataViewModel;
    CompanyDataModel companyDataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitBtn=findViewById(R.id.postBtn);
        nameEdt = findViewById(R.id.nameTxt);

        companyDataViewModel = new ViewModelProvider(this).get(CompanyDataViewModel.class);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String CompanyyName = nameEdt.getText().toString();
                companyDataViewModel.makePostCall(CompanyyName);

            }
        });


        companyDataViewModel.companyDataModel.observe(this, new Observer<CompanyDataModel>() {
            @Override
            public void onChanged(CompanyDataModel companyDataModel) {
                Log.d("COMPANY DATA MODEL",companyDataModel.getName());
            }
        });

      /*  companyDataViewModel.postCompanyName().observe(this, new Observer<CompanyDataModel>() {
            @Override
            public void onChanged(CompanyDataModel companyDataModel) {
                Log.d("COMPANY DATA MODEL",companyDataModel.getName());
            }
        });*/
    }
}