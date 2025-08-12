package com.example.practice;

import static android.widget.Toast.LENGTH_SHORT;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private Button scanBtn, enableBtn, showPairedBtn;
    private TextView statusTxt;
    private ListView listDevices;

    private static final int REQUEST_ENABLE_BT=1;

    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> devicelList;
    ArrayAdapter<String> adapter;

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN})
    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.bluetooth_layout);

        scanBtn = findViewById(R.id.scanBtn);
        enableBtn = findViewById(R.id.enableBT);
        showPairedBtn = findViewById(R.id.showPairedBTn);
        statusTxt = findViewById(R.id.statusTxt);
        listDevices = findViewById(R.id.listDevices);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devicelList= new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, devicelList);
        listDevices.setAdapter(adapter);

        enableBtn.setOnClickListener(v -> {
            enableBluetooth();
        });

        showPairedBtn.setOnClickListener(v -> {
            showPairedDevices();
        });

        scanBtn.setOnClickListener(v -> {
            scanDevices();
        });

    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    public void enableBluetooth(){
        String status = "Bluetooth ON";
        enableBtn.setText("Disable bluetooth");
        if(bluetoothAdapter == null){
            Toast.makeText(this, "Bluetooth not supported", LENGTH_SHORT).show();
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            statusTxt.setText(String.format("Status: %s", status));
        } else {
            bluetoothAdapter.disable();
            Toast.makeText(this, "Bluetooth turning off", LENGTH_SHORT).show();
            enableBtn.setText("Enable Bluetooth");
            devicelList.clear();
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    public void showPairedDevices(){
        String status = "Showing paired devices";
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0) {
            for(BluetoothDevice device : pairedDevices){
                devicelList.add("[PAIRED] " + device.getName() + "\n" +
                        device.getUuids() + "\n" +
                        device.getAddress() + "\n" +
                        device.getBondState());
            }
        } else {
            devicelList.clear();
            Toast.makeText(this, "No Paired Devices", LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
        statusTxt.setText(String.format("Status: %s", status));
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    public void scanDevices(){
        String status = "Scanning...";
        if(bluetoothAdapter.isDiscovering()){
            bluetoothAdapter.cancelDiscovery();
        }
        devicelList.clear();
        adapter.notifyDataSetChanged();
        bluetoothAdapter.startDiscovery();
        statusTxt.setText(String.format("Status: %s" , status));
    }

    public void disableBt(){
        super.onDestroy();
    }

}
