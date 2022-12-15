package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
    String preorder, date, docEntry, status, itemCode, description, quantity, issueRef, receiptRef;
    ArrayList<String> preOrder = new ArrayList<>();
    ArrayList<String> docEntrys = new ArrayList<>();
    Handler mHandler;


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
        itemCode = CommonMethods.getPrefsData(mContext, PrefrenceKey.ItemCode, "");
        description = CommonMethods.getPrefsData(mContext, PrefrenceKey.Description, "");
        quantity = CommonMethods.getPrefsData(mContext, PrefrenceKey.Quantity, "");
        issueRef = CommonMethods.getPrefsData(mContext, PrefrenceKey.IssueRef, "");
        receiptRef = CommonMethods.getPrefsData(mContext, PrefrenceKey.ReceiptRef, "");

        getInitView();


    }


    private void getInitView() {
        proAllocFgBinding.imgSearchCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                getOkinaProduDC();
                getItemCode();

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
                status = proAllocFgBinding.spnStatus.getSelectedItem().toString();
                if (status.equalsIgnoreCase("Select Status")) {
                    CommonMethods.setPrefsData(mContext, PrefrenceKey.Status, status);
                    Toast.makeText(mContext, "Select Status", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
        proAllocFgBinding.autoTxtViewProOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                overridePendingTransition(0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                proAllocFgBinding.txtItemCode.setText(itemCode);
                proAllocFgBinding.txtFGDescription.setText(description);
                proAllocFgBinding.txtProductionOrderQTY.setText(quantity);
                proAllocFgBinding.txtIssueReference.setText(issueRef);
                proAllocFgBinding.txtReceiptReference.setText(receiptRef);

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
                            preOrder.add(okinaProdu.get(0).getProOrder());
                            docEntrys.add(okinaProdu.get(i).getDocentry());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.Date, okinaProdu.get(0).getDate());
                            //  Toast.makeText(ProAllocationFGActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
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
        String selectdoc = CommonMethods.getPrefsData(mContext, PrefrenceKey.Docentry, "");
        RetrofitClient.getClient().getOkinaProduDC(selectdoc).enqueue(new Callback<List<OkinaProduDC>>() {
            @Override
            public void onResponse(Call<List<OkinaProduDC>> call, Response<List<OkinaProduDC>> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("Url", response.toString());
                    List<OkinaProduDC> okinaProduDCS = response.body();
                    if (okinaProduDCS.size() > 0) {
                        for (int i = 0; i < okinaProduDCS.size(); i++) {
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

    }

    public void getItemCode() {

        RetrofitClient.getClient().getItemCode(itemCode).enqueue(new Callback<List<ItemCode>>() {
            @Override
            public void onResponse(Call<List<ItemCode>> call, Response<List<ItemCode>> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("Url", response.toString());
                    List<ItemCode> itemCodes = response.body();
                    if (itemCodes.size() > 0) {
                        for (int i = 0; i < itemCodes.size(); i++) {
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.OnHand, itemCodes.get(i).getOnHand());
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<List<ItemCode>> call, Throwable t) {

            }
        });
    }

    private void getDataOnPopup() {
        final CharSequence contract[] = docEntrys.toArray(new CharSequence[docEntrys.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(ProAllocationFGActivity.this);
        // builder.setTitle("");
        builder.setItems(contract, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                // proAllocFgBinding.autoTxtViewProOrder.setText(preOrder.get(position));
                proAllocFgBinding.autoTxtViewProOrder.setText(docEntrys.get(position));
                String doc = proAllocFgBinding.autoTxtViewProOrder.getText().toString();
                CommonMethods.setPrefsData(mContext, PrefrenceKey.Docentry, doc);


            }
        });
        builder.show();
    }


}