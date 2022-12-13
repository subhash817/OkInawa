package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityProAllocationFgactivityBinding;
import com.cbs.okinawa.model.OkinaProdu;
import com.cbs.okinawa.retrofit.RetrofitClient;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProAllocationFGActivity extends AppCompatActivity {
    ActivityProAllocationFgactivityBinding proAllocFgBinding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proAllocFgBinding = DataBindingUtil.setContentView(this, R.layout.activity_pro_allocation_fgactivity);
        mContext = ProAllocationFGActivity.this;
        getInitView();


    }



    private void getInitView() {

        //status Spinner Code
        String[] courses = {"Select Status", "P", "R"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, courses);
        arrayAdapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        proAllocFgBinding.spnStatus.setAdapter(arrayAdapter);

        // press back icon back to previous page
        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Pro Allocation (FG)");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        proAllocFgBinding.txtBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st=proAllocFgBinding.edtstatus.getText().toString();
                RetrofitClient.getClient().getOkinaProdu(st).enqueue(new Callback<OkinaProdu>() {
                    @Override
                    public void onResponse(Call<OkinaProdu> call, Response<OkinaProdu> response) {
                        if (response.code()==200 && response.body()!=null){
                            Toast.makeText(mContext, "onResponse", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OkinaProdu> call, Throwable t) {
                        Toast.makeText(mContext, "onFailure", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }

}