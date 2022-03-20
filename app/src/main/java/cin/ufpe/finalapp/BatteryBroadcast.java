package cin.ufpe.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;

public class BatteryBroadcast extends BroadcastReceiver {
    MainActivityViewModel viewModel;
    public BatteryBroadcast(MainActivityViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        BatteryManager manager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        manager.getLongProperty(1);

        BatteryManager batteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            long timeRemaining = batteryManager.computeChargeTimeRemaining();
            viewModel.setTimeForCharger(timeRemaining);
        }

        boolean batteryCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
        boolean batteryDischarging = status == BatteryManager.BATTERY_STATUS_DISCHARGING;
        boolean batteryFull = status == BatteryManager.BATTERY_STATUS_FULL;
        boolean batteryInUsb = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean batteryInPlug = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        viewModel.setBatteryHealth(health);

        viewModel.setBatteryCharging(batteryCharging);
        viewModel.setBatteryDischarging(batteryDischarging);
        viewModel.setBatteryFull(batteryFull);
        viewModel.setBatteryInUsb(batteryInUsb);
        viewModel.setBatteryInPlug(batteryInPlug);

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        int batteryLevel = (int) (level * 100 / (float) scale);
        viewModel.setBatteryLevel(batteryLevel);


    }

    public boolean usb(){
        return true;
    }
}
