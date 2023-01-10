package com.cbs.okinawa.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityScanVehicleInfoBinding;
import com.cbs.okinawa.postmodel.ItemNew;
import com.cbs.okinawa.postmodel.OkinProdAPINEWPost;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanVehicleInfo_Activity extends AppCompatActivity {
    ActivityScanVehicleInfoBinding scanVehInfoBinding;
    Context mContext;
    String proOrder, Quantity, DocEntry, DT, ProductionOrderNo, FGITEM, FGQTY, VINNO, BatteryNO, ChargerNO;
    ArrayList<ItemNew> mArray = new ArrayList<>();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanVehInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_scan_vehicle_info);
        mContext = ScanVehicleInfo_Activity.this;
        proOrder = CommonMethods.getPrefsData(mContext, PrefrenceKey.ProOrder, "");
        Quantity = CommonMethods.getPrefsData(mContext, PrefrenceKey.Quantity, "");
        DocEntry = CommonMethods.getPrefsData(mContext, PrefrenceKey.Docentry, "");
        FGITEM = CommonMethods.getPrefsData(mContext, PrefrenceKey.ItemCode, "");

        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC,     100);
        surfaceView = findViewById(R.id.surface_view);
        barcodeText = findViewById(R.id.barcodeVinNo);
        getInitView();


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
                String vinNo = scanVehInfoBinding.barcodeVinNo.getText().toString();
                String chargerNo = scanVehInfoBinding.barcodeCharger.getText().toString();
                String batteryNo = scanVehInfoBinding.barcodeBatteryNo.getText().toString();
                if (vinNo.isEmpty()) {
                    scanVehInfoBinding.barcodeVinNo.setText("Please Scan Vin No");

                } else if (chargerNo.isEmpty()) {
                    scanVehInfoBinding.barcodeCharger.setText("Please chargerNo  No");

                } else if (batteryNo.isEmpty()) {
                    scanVehInfoBinding.barcodeBatteryNo.setText("Please Scan Battery No");
                }


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
        RetrofitClient.getClient().itemNewPost(prodAPINEWPost).enqueue(new Callback<ItemNew>() {
            @Override
            public void onResponse(Call<ItemNew> call, Response<ItemNew> response) {
                if (response.code() == 200 && response.body() != null) {
                    Log.d("ItemNew", response.toString());
                    Toast.makeText(mContext, " post", Toast.LENGTH_SHORT).show();

                    // finish();
                } else {
                    Toast.makeText(mContext, "not post", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ItemNew> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}