package cin.ufpe.finalapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class WifiBroadcast extends BroadcastReceiver {
    MainActivityViewModel viewModel;

    public WifiBroadcast(MainActivityViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        if(manager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            Log.d("JMN", "Wifi ligado");
            Log.d("JMN",info.getSSID());
            viewModel.setWifiEnable(true);
        }else if(manager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
            Log.d("JMN", "Wifi desligado");
            viewModel.setWifiEnable(false);
        }
    }
}
