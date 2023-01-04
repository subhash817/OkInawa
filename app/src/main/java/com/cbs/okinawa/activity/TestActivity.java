package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cbs.okinawa.R;
import com.cbs.okinawa.adapter.OkinaProduAdapter;
import com.cbs.okinawa.databinding.ActivityTestBinding;
import com.cbs.okinawa.model.OkinaProdu;
import com.cbs.okinawa.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding testBinding;
    Context mContext;
    List<OkinaProdu> okinaProduList;
    OkinaProduAdapter okinaProduAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        mContext = TestActivity.this;
        testBinding.searchClient.clearFocus();
        testBinding.searchClient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });
        RetrofitClient.getClient().getOkinaProdu("P").enqueue(new Callback<List<OkinaProdu>>() {
            @Override
            public void onResponse(Call<List<OkinaProdu>> call, Response<List<OkinaProdu>> response) {
                if (response.code() == 200 && response.body() != null) {
                    List<OkinaProdu> okinaProdus = response.body();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    testBinding.rcvOkinaProdu.setLayoutManager(linearLayoutManager);
                    okinaProduAdapter = new OkinaProduAdapter(mContext, okinaProdus);
                    testBinding.rcvOkinaProdu.setAdapter(okinaProduAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<OkinaProdu>> call, Throwable t) {


            }
        });

    }

    private void filterList(String text) {
        List<OkinaProdu> okinaProdus = new ArrayList<>();
        for (OkinaProdu okinaProdu : okinaProduList) {
            if (okinaProdu.getDocentry().toLowerCase().contains(text.toLowerCase())) {
                okinaProdus.add(okinaProdu);
            }
            if (okinaProdus.isEmpty()) {
                Toast.makeText(mContext, "No Data Found", Toast.LENGTH_SHORT).show();
            } else {
                okinaProduAdapter.setFilterList(okinaProdus);
            }

        }
    }
}