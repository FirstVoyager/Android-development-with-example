package limitless.android.androiddevelopment.Other;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import limitless.android.androiddevelopment.R;

public class BluetoothUtil {

    private Context context;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothListener bluetoothListener;
    private static UUID MY_UUID;

    public BluetoothUtil(Context context){
        this.context = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        MY_UUID = UUID.randomUUID();
    }

    public boolean isEnabled(){
        if (bluetoothAdapter == null)
            return false;
        return bluetoothAdapter.isEnabled();
    }

    public boolean setBluetooth(boolean enable){
        if (enable && ! isEnabled()){
            return bluetoothAdapter.enable();
        }else if (! enable && isEnabled()){
            return bluetoothAdapter.disable();
        }
        return true;
    }

    public List<BluetoothDevice> getPairDevices() {
        return new ArrayList<>(bluetoothAdapter.getBondedDevices());
    }

    public void startScan(BluetoothListener bluetoothListener){
        this.bluetoothListener = bluetoothListener;
        if (bluetoothAdapter.isDiscovering()){
            bluetoothAdapter.cancelDiscovery();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(receiver, filter);
        bluetoothAdapter.startDiscovery();
    }

    public boolean setName(String name){
        if (bluetoothAdapter == null)
            return false;
        return bluetoothAdapter.setName(name);
    }

    public void onDestroy(){
        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
            Tools.error(e);
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null || bluetoothListener == null)
                return;
            if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                bluetoothListener.found(device);
            } else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
                bluetoothListener.started();
            }else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                bluetoothListener.finished();
            }
        }
    };

    public String getName() {
        if (bluetoothAdapter == null)
            return null;
        return bluetoothAdapter.getName();
    }

    public boolean supportBluetooth() {
        return bluetoothAdapter != null;
    }

    public static interface BluetoothListener {
        void found(BluetoothDevice device);
        void started();
        void finished();
    }

}
