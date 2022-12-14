package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityProAllocationFgactivityBinding;
import com.cbs.okinawa.model.ItemCode;
import com.cbs.okinawa.model.OkinaProdu;
import com.cbs.okinawa.model.OkinaProduDC;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProAllocationFGActivity extends AppCompatActivity {
    ActivityProAllocationFgactivityBinding proAllocFgBinding;
    Context mContext;
    String preorder, date, docEntry, status, itemCode;
    ArrayList<String> preOrder = new ArrayList<>();
    ArrayList<String> docEntrys = new ArrayList<>();

    AutoCompleteTextView autoCompleteTextView;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proAllocFgBinding = DataBindingUtil.setContentView(this, R.layout.activity_pro_allocation_fgactivity);
        mContext = ProAllocationFGActivity.this;
       preorder = CommonMethods.getPrefsData(mContext, PrefrenceKey.ProOrder, "");
       docEntry = CommonMethods.getPrefsData(mContext, PrefrenceKey.Docentry, "");
       date = CommonMethods.getPrefsData(mContext, PrefrenceKey.Date, "");
       status = CommonMethods.getPrefsData(mContext, PrefrenceKey.Status, "");
         itemCode = CommonMethods.getPrefsData(mContext, PrefrenceKey.ItemCode, "");

         proAllocFgBinding.txtItemCode.setText(itemCode);
        //proAllocFgBinding.txtBarCode.setText(preorder);
        getInitView();


    }


    private void getInitView() {
        proAllocFgBinding.imgSearchCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, " imgSearchCode clicked", Toast.LENGTH_SHORT).show();
                getData();
            }
        });


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
        proAllocFgBinding.btnSCANVehicleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = proAllocFgBinding.spnStatus.getSelectedItem().toString();

                if (status.equalsIgnoreCase("Select Status")) {
                    CommonMethods.setPrefsData(mContext, PrefrenceKey.Status, status);
                    Toast.makeText(mContext, "Select Status", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


    }

    public void getData() {
        String st = proAllocFgBinding.spnStatus.getSelectedItem().toString();
        RetrofitClient.getClient().getOkinaProdu(st).enqueue(new Callback<List<OkinaProdu>>() {
            @Override
            public void onResponse(Call<List<OkinaProdu>> call, Response<List<OkinaProdu>> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("Url", response.toString());
                    List<OkinaProdu> okinaProdu = response.body();
                    if (okinaProdu.size() > 0) {
                        for (int i = 0; i < okinaProdu.size(); i++) {
                            preOrder.add(okinaProdu.get(i).getProOrder());
                            docEntrys.add(okinaProdu.get(i).getDate());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.Date, okinaProdu.get(0).getDate());
                            Toast.makeText(ProAllocationFGActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
                        }
                        getDataOnPopup();


                    }


                }
            }

            @Override
            public void onFailure(Call<List<OkinaProdu>> call, Throwable t) {

            }
        });


    }

    public void getOkinaProduDC() {
        RetrofitClient.getClient().getOkinaProduDC("13400").enqueue(new Callback<List<OkinaProduDC>>() {
            @Override
            public void onResponse(Call<List<OkinaProduDC>> call, Response<List<OkinaProduDC>> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("Url", response.toString());
                    List<OkinaProduDC> okinaProduDCS = response.body();
                    if (okinaProduDCS.size() > 0) {
                        for (int i = 0; i < okinaProduDCS.size(); i++) {
                            // itemCode.add(okinaProduDCS.get(i).getItemCode());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.ItemCode, okinaProduDCS.get(0).getItemCode());


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OkinaProduDC>> call, Throwable t) {

            }
        });

    }

    public void getItemCode() {
        RetrofitClient.getClient().getItemCode("FG-OD01201-FRD").enqueue(new Callback<List<ItemCode>>() {
            @Override
            public void onResponse(Call<List<ItemCode>> call, Response<List<ItemCode>> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("Url", response.toString());
                    List<ItemCode> itemCodes = response.body();
                    if (itemCodes.size() > 0) {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<ItemCode>> call, Throwable t) {

            }
        });
    }

    private void getDataOnPopup() {
        final CharSequence contract[] = preOrder.toArray(new CharSequence[preOrder.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(ProAllocationFGActivity.this);
        // builder.setTitle("");
        builder.setItems(contract, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                proAllocFgBinding.edtGeneralCustCode.setText(preOrder.get(position));

            }
        });
        builder.show();
    }


}