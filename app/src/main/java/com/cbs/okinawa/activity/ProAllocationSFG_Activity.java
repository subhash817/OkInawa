package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.cbs.okinawa.databinding.ActivityProAllocationSfgBinding;
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

public class ProAllocationSFG_Activity extends AppCompatActivity {
    ActivityProAllocationSfgBinding sfgBinding;
    Context mContext;
    ArrayList<String> preOrder = new ArrayList<>();
    ArrayList<String> docEntrys = new ArrayList<>();
    String preorder, doc, date, docEntry, status, itemCode, description, quantity, issueRef, receiptRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sfgBinding = DataBindingUtil.setContentView(this, R.layout.activity_pro_allocation_sfg);
        mContext = ProAllocationSFG_Activity.this;
        preorder = CommonMethods.getPrefsData(mContext, PrefrenceKey.ProOrder, "");
        docEntry = CommonMethods.getPrefsData(mContext, PrefrenceKey.Docentry, "");
        date = CommonMethods.getPrefsData(mContext, PrefrenceKey.Date, "");
        status = CommonMethods.getPrefsData(mContext, PrefrenceKey.Status, "");
        itemCode = CommonMethods.getPrefsData(mContext, PrefrenceKey.ItemCode, "");
        description = CommonMethods.getPrefsData(mContext, PrefrenceKey.Description, "");
        quantity = CommonMethods.getPrefsData(mContext, PrefrenceKey.Quantity, "");
        issueRef = CommonMethods.getPrefsData(mContext, PrefrenceKey.IssueRef, "");
        receiptRef = CommonMethods.getPrefsData(mContext, PrefrenceKey.ReceiptRef, "");
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
                String status = sfgBinding.spnStatus.getSelectedItem().toString();
                if (status.equalsIgnoreCase("Select Status")) {
                    Toast.makeText(mContext, "Please Select Status", Toast.LENGTH_SHORT).show();

                } else {
                    String st = sfgBinding.spnStatus.getSelectedItem().toString();
                    RetrofitClient.getClient().getOkinaProdu(st).enqueue(new Callback<List<OkinaProdu>>() {
                        @Override
                        public void onResponse(Call<List<OkinaProdu>> call, Response<List<OkinaProdu>> response) {
                            if (response.code() == 200 && response.body() != null) {
                                Log.d("Url", response.toString());
                                List<OkinaProdu> okinaProdu = response.body();
                                if (okinaProdu.size() > 0) {
                                    for (int i = 0; i < okinaProdu.size(); i++) {
                                        preOrder.add(okinaProdu.get(i).getProOrder());
                                        docEntrys.add(okinaProdu.get(i).getDocentry());
                                        CommonMethods.setPrefsData(mContext, PrefrenceKey.Date, okinaProdu.get(0).getDate());
                                        CommonMethods.setPrefsData(mContext, PrefrenceKey.ProOrder, okinaProdu.get(i).getProOrder());
                                        // Toast.makeText(ProAllocationFGActivity.this, okinaProdu.get(i).getProOrder().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                    getDataOnPopup();


                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<List<OkinaProdu>> call, Throwable t) {
                            Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });

        sfgBinding.btnSCANVINInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ScanVinInfo_Activity.class);
                startActivity(intent);
            }
        });

    }

    private void getDataOnPopup() {
        final CharSequence contract[] = docEntrys.toArray(new CharSequence[docEntrys.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(ProAllocationSFG_Activity.this);
        // builder.setTitle("");
        builder.setItems(contract, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                // proAllocFgBinding.autoTxtViewProOrder.setText(preOrder.get(position));
                sfgBinding.autoTxtViewProOrder.setText(docEntrys.get(position));
                doc = sfgBinding.autoTxtViewProOrder.getText().toString();
                CommonMethods.setPrefsData(mContext, PrefrenceKey.Docentry, doc);
                RetrofitClient.getClient().getOkinaProduDC(doc).enqueue(new Callback<List<OkinaProduDC>>() {
                    @Override
                    public void onResponse(Call<List<OkinaProduDC>> call, Response<List<OkinaProduDC>> response) {
                        if (response.code() == 200 && response.body() != null) {
                            Log.d("Url", response.toString());
                            List<OkinaProduDC> okinaProduDCS = response.body();
                            if (okinaProduDCS.size() > 0) {
                                for (int i = 0; i < okinaProduDCS.size(); i++) {
                                    sfgBinding.txtItemCode.setText(okinaProduDCS.get(i).getItemCode());
                                    sfgBinding.txtFGDescription.setText(okinaProduDCS.get(i).getDescription());
                                    sfgBinding.txtProductionOrderQTY.setText(okinaProduDCS.get(i).getQuantity());
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
        });
        builder.show();
    }

}