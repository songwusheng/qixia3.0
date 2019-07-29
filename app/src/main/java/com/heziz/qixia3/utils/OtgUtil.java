package com.heziz.qixia3.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.github.mjdev.libaums.UsbMassStorageDevice;
import com.github.mjdev.libaums.fs.FileSystem;
import com.github.mjdev.libaums.fs.UsbFile;
import com.github.mjdev.libaums.partition.Partition;

/**
 * Created by sws on 2018/8/28.
 * from:
 * describe:
 */

public class OtgUtil {
    /**
     * @description U盘设备读取
     * @author ldm
     * @time 2017/9/home_bg_icon1 17:20
     */
    public static void redUDiskDevsList(Context context) {
        UsbMassStorageDevice[] storageDevices;
        //设备管理器
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        //获取U盘存储设备
        storageDevices = UsbMassStorageDevice.getMassStorageDevices(context);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(Constant.ACTION_USB_PERMISSION), 0);
        //一般手机只有1个OTG插口
        for (UsbMassStorageDevice device : storageDevices) {
            //读取设备是否有权限
            if (usbManager.hasPermission(device.getUsbDevice())) {
                ToastUtil.showToast("有权限");
                readDevice(device);
            } else {
                ToastUtil.showToast("没有权限，进行申请");
                //没有权限，进行申请
                usbManager.requestPermission(device.getUsbDevice(), pendingIntent);
            }
        }
        if (storageDevices.length == 0) {
            ToastUtil.showToast("请插入可用的U盘");
        }
    }

    private static void readDevice(UsbMassStorageDevice device) {
        UsbFile cFolder;
        try {
            device.init();//初始化
            //设备分区
            Partition partition = device.getPartitions().get(0);

            //文件系统
            FileSystem currentFs = partition.getFileSystem();
            currentFs.getVolumeLabel();//可以获取到设备的标识

            //通过FileSystem可以获取当前U盘的一些存储信息，包括剩余空间大小，容量等等
            Log.e("Capacity: ", currentFs.getCapacity() + "");
            Log.e("Occupied Space: ", currentFs.getOccupiedSpace() + "");
            Log.e("Free Space: ", currentFs.getFreeSpace() + "");
            Log.e("Chunk size: ", currentFs.getChunkSize() + "");

            ToastUtil.showToast("可用空间：" + currentFs.getFreeSpace());


            cFolder = currentFs.getRootDirectory();//设置当前文件对象为根目录


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
