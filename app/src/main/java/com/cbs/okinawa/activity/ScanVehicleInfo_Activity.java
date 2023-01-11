package com.cbs.okinawa.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cbs.okinawa.R;
import com.cbs.okinawa.adapter.ItemNewPostAdapter;
import com.cbs.okinawa.databinding.ActivityScanVehicleInfoBinding;
import com.cbs.okinawa.postmodel.ItemNew;
import com.cbs.okinawa.postmodel.OkinProdAPINEWPost;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanVehicleInfo_Activity extends AppCompatActivity {
    ActivityScanVehicleInfoBinding scanVehInfoBinding;
    Context mContext;
    String proOrder, Quantity, DocEntry, DT, ProductionOrderNo, FGITEM, FGQTY, VINNO, BatteryNO, ChargerNO;
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    private SurfaceView surfaceView;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    //This class provides methods to play DTMF tones
    private ToneGenerator toneGen1;
    private TextView barcodeText;
    private String barcodeData;
    ArrayList<ItemNew> mArray = new ArrayList<>();
    ItemNewPostAdapter itemNewPostAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanVehInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_scan_vehicle_info);
        mContext = ScanVehicleInfo_Activity.this;
        proOrder = CommonMethods.getPrefsData(mContext, PrefrenceKey.ProOrder, "");
        Quantity = CommonMethods.getPrefsData(mContext, PrefrenceKey.Quantity, "");
        DocEntry = CommonMethods.getPrefsData(mContext, PrefrenceKey.Docentry, "");
        FGITEM = CommonMethods.getPrefsData(mContext, PrefrenceKey.ItemCode, "");

        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        surfaceView = findViewById(R.id.surface_view);
        barcodeText = findViewById(R.id.barcodeVinNo);
        getInitView();
        setRecyclerView();


    }

    private void getInitView() {

        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Scan Vehicle Info");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        scanVehInfoBinding.tvProdOrder.setText(proOrder);
        scanVehInfoBinding.tvQuantity.setText(Quantity);
        scanVehInfoBinding.btnScanitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VINNO = scanVehInfoBinding.barcodeVinNo.getText().toString();
                BatteryNO = scanVehInfoBinding.barcodeCharger.getText().toString();
                ChargerNO = scanVehInfoBinding.barcodeBatteryNo.getText().toString();
                ItemNew itemNew = new ItemNew();
                itemNew.setDocEntry(DocEntry);
                itemNew.setDt(DT);
                itemNew.setProductionOrderNo(ProductionOrderNo);
                itemNew.setFgItem(FGITEM);
                itemNew.setFgQty(FGQTY);
                itemNew.setVinNo(VINNO);
                itemNew.setChargerNO(ChargerNO);
                itemNew.setBatteryNO(BatteryNO);
                mArray.add(itemNew);
                itemNewPostAdapter.notifyDataSetChanged();


            }

        });
        scanVehInfoBinding.btnPostItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 postProductionItem();
            }
        });


    }


    public void postProductionItem() {
        OkinProdAPINEWPost prodAPINEWPost = new OkinProdAPINEWPost();
        for (int i = 0; i < mArray.size(); i++) {
            ItemNew itemNew = new ItemNew();
            itemNew.setDocEntry(mArray.get(i).getDocEntry());
            itemNew.setDt(mArray.get(i).getDt());
            itemNew.setProductionOrderNo(mArray.get(i).getProductionOrderNo());
            itemNew.setFgItem(mArray.get(i).getFgItem());
            itemNew.setFgQty(mArray.get(i).getFgQty());
            itemNew.setVinNo(mArray.get(i).getVinNo());
            itemNew.setBatteryNO(mArray.get(i).getBatteryNO());
            itemNew.setChargerNO(mArray.get(i).getChargerNO());
            prodAPINEWPost.getItemNew().add(itemNew);
        }
//        RetrofitClient.getClient().itemNewPost(prodAPINEWPost).enqueue(new Callback<ItemNew>() {
//            @Override
//            public void onResponse(Call<ItemNew> call, Response<ItemNew> response) {
//                if (response.code() == 200 && response.body() != null) {
//                    Log.d("ItemNew", response.toString());
//                    Toast.makeText(mContext, " post", Toast.LENGTH_SHORT).show();
//
//                    // finish();
//                } else {
//                    Toast.makeText(mContext, "not post", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ItemNew> call, Throwable t) {
//                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        scanVehInfoBinding.rcvScanItem.setLayoutManager(linearLayoutManager);
        itemNewPostAdapter = new ItemNewPostAdapter(ScanVehicleInfo_Activity.this, mArray);
        scanVehInfoBinding.rcvScanItem.setAdapter(itemNewPostAdapter);
    }
}