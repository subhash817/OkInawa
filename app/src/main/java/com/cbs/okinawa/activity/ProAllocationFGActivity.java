package com.cbs.okinawa.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.ItemClickListener;
import com.cbs.okinawa.R;
import com.cbs.okinawa.adapter.OkinaProduAdapter;
import com.cbs.okinawa.databinding.ActivityProAllocationFgactivityBinding;
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

public class ProAllocationFGActivity extends AppCompatActivity implements ItemClickListener {
    ActivityProAllocationFgactivityBinding proAllocFgBinding;
    Context mContext;
    String preorder, doc, date, docEntry, status, itemCode, description, quantity, issueRef, receiptRef;
    ArrayList<String> preOrder = new ArrayList<>();
    ArrayList<String> docEntrys = new ArrayList<>();
    Handler mHandler;
    OkinaProduAdapter okinaProduAdapter;
    AutoCompleteTextView autoCompleteTextView;
    String getDocEntry;
    Dialog custDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proAllocFgBinding = DataBindingUtil.setContentView(this, R.layout.activity_pro_allocation_fgactivity);
        mContext = ProAllocationFGActivity.this;

        //status Spinner Code
        String[] Status = {"Select Status", "P", "R"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Status);
        arrayAdapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        proAllocFgBinding.spnStatus.setAdapter(arrayAdapter);
//        preorder = CommonMethods.getPrefsData(mContext, PrefrenceKey.ProOrder, "");
//        docEntry = CommonMethods.getPrefsData(mContext, PrefrenceKey.Docentry, "");
//        date = CommonMethods.getPrefsData(mContext, PrefrenceKey.Date, "");
//        status = CommonMethods.getPrefsData(mContext, PrefrenceKey.Status, "");
//        itemCode = CommonMethods.getPrefsData(mContext, PrefrenceKey.ItemCode, "");
//        description = CommonMethods.getPrefsData(mContext, PrefrenceKey.Description, "");
//        quantity = CommonMethods.getPrefsData(mContext, PrefrenceKey.Quantity, "");
//        issueRef = CommonMethods.getPrefsData(mContext, PrefrenceKey.IssueRef, "");
//        receiptRef = CommonMethods.getPrefsData(mContext, PrefrenceKey.ReceiptRef, "");
        autoCompleteTextView = findViewById(R.id.autoTxtViewProOrder);
        getInitView();

    }


    private void getInitView() {
        proAllocFgBinding.imgSearchCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDocNumberPopup();
            }
        });

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
                String rem=proAllocFgBinding.edtRemark.getText().toString().trim();
                if (rem.isEmpty()){
                    proAllocFgBinding.edtRemark.setError("Please Enter Remarks");
                }
                else {
                    Intent intent = new Intent(mContext, ScanVehicleInfo_Activity.class);
                    startActivity(intent);
                }

            }
        });


    }

    private void showDocNumberPopup() {
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
        ivScrollUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custList.smoothScrollToPosition(0);
            }
        });

        status = proAllocFgBinding.spnStatus.getSelectedItem().toString();
        if (status.equalsIgnoreCase("Select Status")) {
            Toast.makeText(mContext, "Please Select Status", Toast.LENGTH_SHORT).show();
            return;
        }
        RetrofitClient.getClient().getOkinaProdu(status).enqueue(new Callback<List<OkinaProdu>>() {
            @Override
            public void onResponse(Call<List<OkinaProdu>> call, Response<List<OkinaProdu>> response) {
                if (response.code() == 200 && response.body() != null) {
                    List<OkinaProdu> okinaProdus = response.body();
                    for (int i=0;i<okinaProdus.size();i++){
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        custList.setLayoutManager(linearLayoutManager);
                        okinaProduAdapter = new OkinaProduAdapter(mContext, okinaProdus, ProAllocationFGActivity.this);
                        custList.setAdapter(okinaProduAdapter);
                        CommonMethods.setPrefsData(mContext,PrefrenceKey.Status,okinaProdus.get(i).getStatus());
                        CommonMethods.setPrefsData(mContext,PrefrenceKey.ProOrder,okinaProdus.get(i).getProOrder());
                        CommonMethods.setPrefsData(mContext,PrefrenceKey.Docentry,okinaProdus.get(i).getDocentry());
                        CommonMethods.setPrefsData(mContext,PrefrenceKey.Date,okinaProdus.get(i).getDate());

                    }



                }
                searchView.setQueryHint("Search Doc Number");
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        searchView.clearFocus();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        okinaProduAdapter.getFilter().filter(s);

                        return false;
                    }
                });


            }

            @Override
            public void onFailure(Call<List<OkinaProdu>> call, Throwable t) {


            }
        });


    }

    @Override
    public void onClick(String docEntry) {
        this.docEntry = docEntry;
        if (custDialog != null)
            proAllocFgBinding.autoTxtViewProOrder.setText(docEntry);

        RetrofitClient.getClient().getOkinaProduDC(docEntry).enqueue(new Callback<List<OkinaProduDC>>() {
            @Override
            public void onResponse(Call<List<OkinaProduDC>> call, Response<List<OkinaProduDC>> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("Url", response.toString());
                    List<OkinaProduDC> okinaProduDCS = response.body();
                    if (okinaProduDCS.size() > 0) {
                        for (int i = 0; i < okinaProduDCS.size(); i++) {
                            proAllocFgBinding.txtItemCode.setText(okinaProduDCS.get(i).getItemCode());
                            proAllocFgBinding.txtFGDescription.setText(okinaProduDCS.get(i).getDescription());
                            proAllocFgBinding.txtProductionOrderQTY.setText(okinaProduDCS.get(i).getQuantity());
                            proAllocFgBinding.txtIssueReference.setText(okinaProduDCS.get(i).getIssueRef());
                            proAllocFgBinding.txtReceiptReference.setText(okinaProduDCS.get(i).getReceiptRef());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.ItemCode, okinaProduDCS.get(i).getItemCode());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.Description, okinaProduDCS.get(i).getDescription());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.Quantity, okinaProduDCS.get(i).getQuantity());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.IssueRef, okinaProduDCS.get(i).getIssueRef());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.ReceiptRef, okinaProduDCS.get(i).getIssueRef());


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OkinaProduDC>> call, Throwable t) {

            }
        });

        custDialog.dismiss();

    }


}