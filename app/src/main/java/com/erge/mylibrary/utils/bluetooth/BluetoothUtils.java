package com.erge.mylibrary.utils.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.erge.mylibrary.MyLibrary;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +                                 <br/>
 * 　　　　　　　┏┛┻━━━┛┻┓ + +                                  <br/>
 * 　　　　　　　┃　　　　　　　┃                                <br/>
 * 　　　　　　　┃　　　━　　　┃ ++ + + +                        <br/>
 * 　　　　　　 ████━████ ┃+                                    <br/>
 * 　　　　　　　┃　　　　　　　┃ +                              <br/>
 * 　　　　　　　┃　　　┻　　　┃                                 <br/>
 * 　　　　　　　┃　　　　　　　┃ + +                            <br/>
 * 　　　　　　　┗━┓　　　┏━┛                                   <br/>
 * 　　　　　　　　　┃　　　┃                                    <br/>
 * 　　　　　　　　　┃　　　┃ + + + +                            <br/>
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting                   <br/>
 * <br/>
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug                   <br/>
 * 　　　　　　　　　┃　　　┃                             <br/>
 * 　　　　　　　　　┃　　　┃　　+                        <br/>
 * 　　　　　　　　　┃　 　　┗━━━┓ + +                    <br/>
 * 　　　　　　　　　┃ 　　　　　　　┣┓                    <br/>
 * 　　　　　　　　　┃ 　　　　　　　┏┛                    <br/>
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +                    <br/>
 * 　　　　　　　　　　┃┫┫　┃┫┫                           <br/>
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +                    <br/>
 * <p>
 * Created by liurui on 2017/12/15.
 */
public class BluetoothUtils {

    private static final String TAG = "BluetoothUtils";

    private static BluetoothUtils instance;
    private static BluetoothAdapter mBluetoothAdapter;
    private boolean connected = false;
    private BluetoothScanListener scanListener;
    private BluetoothGatt gatt;

    private BluetoothUtils() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static BluetoothUtils getInstance() {
        if (instance == null) {
            instance = new BluetoothUtils();
        }
        return instance;
    }

    @SuppressLint("MissingPermission")
    public boolean getBluetoothState() {
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled();
    }

    public void openBluetooth() {
        try {
            Intent intent = new Intent();
            intent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            MyLibrary.getApp().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void disconnectAll() {
        if (gatt != null) {
            gatt.disconnect();
            gatt = null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void scan(boolean enable, BluetoothScanListener scanListener) {
        this.scanListener = scanListener;
        if (enable) {
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, final byte[] scanRecord) {
            if (scanListener != null) {
                boolean result = scanListener.onVerification(device, scanRecord);
                if (result) {
                    scan(false,scanListener);
                    //device.connectGatt(MyLibrary.getApp(), null);
                }
            }
        }
    };

}
