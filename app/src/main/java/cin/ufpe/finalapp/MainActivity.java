package cin.ufpe.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;

import cin.ufpe.finalapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private WifiBroadcast wifiReceiver;
    private BatteryBroadcast batteryReceiver;
    private MainActivityViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.wifiEnable.observe(this,
                enable -> {
                    binding.iconWifi.setImageDrawable(Util.setIconWifiEnable(this, enable));
                    binding.textChangedWifi.setTextColor(Util.setColorEnable(this, enable));
                    binding.textChangedWifi.setText(Util.setTextWifiEnable(this, enable));
        });

        viewModel.batteryPlugged.observe(this,
                plugged -> {
                    binding.textUsb.setText(Util.setTextPlugged(plugged));
                    binding.iconUsb.setImageDrawable(Util.setIconPlugged(this, plugged));
                });

        viewModel.batteryState.observe(this,
                state -> {
                    binding.iconBattery.setImageDrawable(Util.setIconBatteryState(this, state));
                    binding.textBatteryStatus.setText(Util.setTextBatteryState(this, state));
                    binding.iconBattery.setColorFilter(Util.setColorBatteryState(this, state));
                    binding.textBatteryStatus.setTextColor(Util.setColorBatteryState(this, state));
        });

        viewModel.batteryLevel.observe(this,
                level -> {
                    binding.textPorcentBatttery.setText(level + " %");
                    binding.textPorcentBatttery.setTextColor(Util.setColorCharge(this, level));
                    binding.iconPorcentBattery.setColorFilter(Util.setColorCharge(this, level));
                    binding.iconPorcentBattery.setImageDrawable(Util.setIconBatteryCharger(this, level));
                });

        viewModel.batteryHealth.observe(this,
                health -> {
                    binding.textBatteryHealth.setText(Util.setTextBatteryHealth(health));
        });

        viewModel.timeForCharger.observe(this,
                time -> {
                    if (time != -1) {
                        int minuts = (int) (time / 60000);
                        int hours = minuts / 60;
                        minuts = minuts % 60;
                        binding.textTimeForCharger.setText("Time reming for charger: " +
                                hours + " h and " +
                                minuts + " min");
                        binding.textTimeForCharger.setVisibility(View.VISIBLE);
                    } else {
                        binding.textTimeForCharger.setVisibility(View.GONE);
                    }
                });

        binding.buttonGoListApps.setOnClickListener(view -> {
            startActivity(new Intent(this, AppsListActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        wifiReceiver = new WifiBroadcast(viewModel);
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiReceiver, intentFilter);

        batteryReceiver = new BatteryBroadcast(viewModel);
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        ifilter.addAction(BatteryManager.EXTRA_HEALTH);
        registerReceiver(batteryReceiver, ifilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (wifiReceiver != null) {
            unregisterReceiver(wifiReceiver);
            wifiReceiver = null;
        }
    }
}