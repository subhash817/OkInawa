package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityStartScanningBinding;
import com.cbs.okinawa.model.OkinaProdu;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartScanning_Activity extends AppCompatActivity {
    ActivityStartScanningBinding startScanningBinding;
    Context mContext;
    ArrayList<String> preOrder = new ArrayList<>();
    ArrayList<String> docEntrys = new ArrayList<>();
    RecyclerView rcvJsondata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startScanningBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_scanning);
        mContext = StartScanning_Activity.this;
        initView();


    }

    private void initView() {
        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Start Scanning");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        RetrofitClient.getClient().getOkinaProdu("P").enqueue(new Callback<List<OkinaProdu>>() {
            @Override
            public void onResponse(Call<List<OkinaProdu>> call, Response<List<OkinaProdu>> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("Url", response.toString());
                    List<OkinaProdu> okinaProdu = response.body();
                    if (okinaProdu.size() > 0) {
                        for (int i = 0; i < okinaProdu.size(); i++) {
                            preOrder.add(okinaProdu.get(0).getProOrder());
                            docEntrys.add(okinaProdu.get(i).getDocentry());
                            List<OkinaProdu> okinaProduList=okinaProdu;

//                            rcvJsondata = (RecyclerView) findViewById(R.id.rcvJsondata);
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//                            rcvJsondata.setLayoutManager(linearLayoutManager);
//                            JsonAdapterProducation billRegisterAdapter = new JsonAdapterProducation(mContext, okinaProduList);
//                            rcvJsondata.setAdapter(billRegisterAdapter);
                        }


                    }


                }
            }

            @Override
            public void onFailure(Call<List<OkinaProdu>> call, Throwable t) {

            }
        });

    }
}