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
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        BatteryManager manager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);

        viewModel.setBatteryHealth(intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1));
        viewModel.setBatteryState(intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1));
        viewModel.setBatteryPlugged(intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1));
        viewModel.setBatteryLevel((int) (level * 100 / (float) scale));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            long timeRemaining = manager.computeChargeTimeRemaining();
            viewModel.setTimeForCharger(timeRemaining);
        }
    }
}
