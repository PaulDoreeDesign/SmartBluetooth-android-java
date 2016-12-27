package com.smartcodeunited.demo.bluetooth.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;

import com.smartcodeunited.lib.bluetooth.managers.BluetoothDeviceManager;

/**
 * Created by JackWaiting on 2016/12/27.
 */
public class BluetoothDeviceManagerProxy {

    public static final String TAG = "BluetoothDeviceManagerProxy";

    private boolean modifieds = false;
    private Context context;
    private static int currentDeviceMode;  //当前连接模式
    private boolean bluzManReady = false;

    /**
     * 蓝牙库控制类
     */
    public static BluetoothDeviceManagerProxy proxy;
    /**
     * 是否已连接
     */
    private boolean connected;
    /**
     * 蓝牙设备管理类
     */
    private static BluetoothDeviceManager bluzDeviceMan;
    /**
     * 已连接的蓝牙设备
     */
    private BluetoothDevice connectedDevice;
    /**
     * 获取蓝牙管理类
     *
     * @return
     */
    public BluetoothDeviceManager getBluetoothDeviceManager() {
        if (bluzDeviceMan == null) {
            Log.d("", "getBluetoothDeviceManager");
            bluzManReady = false;
            bluzDeviceMan = BluetoothDeviceManager.getInstance(context);
        }
        return bluzDeviceMan;
    }

    private BluetoothDeviceManagerProxy(Context context) {
        this.context = context.getApplicationContext();
        getBluetoothDeviceManager();
    }

    public static BluetoothDeviceManagerProxy getInstance(Context context) {
        if (proxy == null) {
            proxy = new BluetoothDeviceManagerProxy(context.getApplicationContext());
        }
        return proxy;
    }

    public Context getContext() {
        return context;
    }

    /**
     * 获取已存在的实例对象，如果不存在则返回null
     *
     * @return
     */
    public static BluetoothDeviceManagerProxy getInstance() {
        return proxy;
    }

    /**
     * 扫描蓝牙设备
     *
     * @param listener 监听
     */
    public void startScanning(BluetoothDeviceManager.OnBluetoothDeviceBluetoothScanningListener listener) {
        Log.i("startDiscoverys", "startDiscoverys");
        bluzDeviceMan = getBluetoothDeviceManager();
        bluzDeviceMan.setOnBluetoothDeviceBluetoothScanningListener(listener);
        bluzDeviceMan.startScanning(BluetoothDeviceManager.BluetoothType.BLE);
    }
}
