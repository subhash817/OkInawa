package com.cbs.okinawa.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.R;
import com.cbs.okinawa.SfgItemClcikListener;
import com.cbs.okinawa.adapter.SfgOkinaproduAdapter;
import com.cbs.okinawa.databinding.ActivityProAllocationSfgBinding;
import com.cbs.okinawa.model.OkinaProdu;
import com.cbs.okinawa.model.OkinaProduDC;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.CustomProgressbar;
import com.cbs.okinawa.utils.PrefrenceKey;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProAllocationSFG_Activity extends AppCompatActivity implements SfgItemClcikListener {
    ActivityProAllocationSfgBinding sfgBinding;
    Context mContext;
    ArrayList<String> preOrder = new ArrayList<>();
    ArrayList<String> docEntrys = new ArrayList<>();
    String preorder, doc, date, docEntry, status, itemCode, description, quantity, issueRef, receiptRef;
    Dialog custDialog;
    SfgOkinaproduAdapter sfgOkinaproduAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sfgBinding = DataBindingUtil.setContentView(this, R.layout.activity_pro_allocation_sfg);
        mContext = ProAllocationSFG_Activity.this;
        String[] Status = {"Select Status", "P", "R"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Status);
        arrayAdapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        sfgBinding.spnStatus.setAdapter(arrayAdapter);
        initView();
    }

    private void initView() {
        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Pro Allocation (SFG)");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        sfgBinding.imgSearchCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupDoc();
            }
        });
        sfgBinding.btnSCANVINInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ScanVinInfo_Activity.class);
                startActivity(intent);
            }
        });
        sfgBinding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sfgBinding.autoTxtViewProOrder.getText().clear();
                sfgBinding.txtItemCode.setText("");
                sfgBinding.txtFGDescription.setText("");
                sfgBinding.txtProductionOrderQTY.setText("");
                sfgBinding.txtIssueReference.setText("");
                sfgBinding.txtReceiptReference.setText("");

            }
        });
        sfgBinding.buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DashBoard_Activity.class);
                startActivity(intent);

            }
        });

    }

    public void showPopupDoc() {

        custDialog = new Dialog(mContext, android.R.style.Theme_Material_Light_Dialog);
        custDialog.setContentView(R.layout.recylerview_popup);
        custDialog.show();
        final RecyclerView custList = custDialog.findViewById(R.id.rcvOkinaProdu);
        ImageView ivClose = custDialog.findViewById(R.id.dlg_close);
        final ImageView ivScrollUp = custDialog.findViewById(R.id.iv_scroll_up);
        final SearchView searchView = custDialog.findViewById(R.id.search_client);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custDialog.dismiss();
            }
        });
        status = sfgBinding.spnStatus.getSelectedItem().toString();
        if (status.equalsIgnoreCase("Select Status")) {
            Toast.makeText(mContext, "Please Select Status", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        CustomProgressbar.showProgressBar(mContext, false);
        RetrofitClient.getClient().getOkinaProdu(status).enqueue(new Callback<List<OkinaProdu>>() {
            @Override
            public void onResponse(Call<List<OkinaProdu>> call, Response<List<OkinaProdu>> response) {
                if (response.code() == 200 && response.body() != null) {
                    CustomProgressbar.hideProgressBar();
                    List<OkinaProdu> okinaProdus = response.body();
                    for (int i = 0; i < okinaProdus.size(); i++) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        custList.setLayoutManager(linearLayoutManager);
                        sfgOkinaproduAdapter = new SfgOkinaproduAdapter(mContext, okinaProdus, ProAllocationSFG_Activity.this);
                        custList.setAdapter(sfgOkinaproduAdapter);
                        CommonMethods.setPrefsData(mContext, PrefrenceKey.Status, okinaProdus.get(i).getStatus());
                        CommonMethods.setPrefsData(mContext, PrefrenceKey.ProOrder, okinaProdus.get(i).getProOrder());
                        CommonMethods.setPrefsData(mContext, PrefrenceKey.Docentry, okinaProdus.get(i).getDocentry());
                        CommonMethods.setPrefsData(mContext, PrefrenceKey.Date, okinaProdus.get(i).getDate());
                    }


                }

            }

            @Override
            public void onFailure(Call<List<OkinaProdu>> call, Throwable t) {
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(String docEntry) {
        this.doc = docEntry;
        if (custDialog != null) {
            sfgBinding.autoTxtViewProOrder.setText(docEntry);
            CustomProgressbar.showProgressBar(mContext, false);
            RetrofitClient.getClient().getOkinaProduDC(docEntry).enqueue(new Callback<List<OkinaProduDC>>() {
                @Override
                public void onResponse(Call<List<OkinaProduDC>> call, Response<List<OkinaProduDC>> response) {
                    if (response.code() == 200 && response.body() != null) {
                        CustomProgressbar.hideProgressBar();
                        Log.d("Url", response.toString());
                        List<OkinaProduDC> okinaProduDCS = response.body();
                        if (okinaProduDCS.size() > 0) {
                            for (int i = 0; i < okinaProduDCS.size(); i++) {
                                sfgBinding.txtItemCode.setText(okinaProduDCS.get(i).getItemCode());
                                sfgBinding.txtFGDescription.setText(okinaProduDCS.get(i).getDescription());
                                sfgBinding.txtProductionOrderQTY.setText(okinaProduDCS.get(i).getQuantity());
                                sfgBinding.txtIssueReference.setText(okinaProduDCS.get(i).getIssueRef());
                                sfgBinding.txtReceiptReference.setText(okinaProduDCS.get(i).getReceiptRef());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<OkinaProduDC>> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            custDialog.dismiss();
        }
    }
}