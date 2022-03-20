package cin.ufpe.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
                    if(enable){
                        binding.iconWifi.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_wifi_on));
                        binding.textChangedWifi.setText("Wifi Enable");
                    }else{
                        binding.iconWifi.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_wifi_off_24));
                        binding.textChangedWifi.setText("Wifi Disable");
                    }
        });

        viewModel.batteryInUsb.observe(this,
                state -> {
                    if (state) {
                        binding.textUsb.setText("Is in Ubs");
                        binding.textUsb.setTextColor(ContextCompat.getColor(this, R.color.teal_700));
                    } else {
                        binding.textUsb.setText("Is not in Ubs");
                        binding.textUsb.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    }
                });

        viewModel.batteryInPlug.observe(this,
                state -> {
                    if (state) {
                        binding.textPlug.setText("Is In plug");
                        binding.textPlug.setTextColor(ContextCompat.getColor(this, R.color.teal_700));
                    } else {
                        binding.textPlug.setText("Is Not in Plug");
                        binding.textPlug.setTextColor(ContextCompat.getColor(this, R.color.grey));
                    }
                });

        viewModel.batteryFull.observe(this,
                state -> {
                    if (state) {
                        binding.iconBattery.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_battery));
                        binding.iconBattery.setColorFilter(ContextCompat.getColor(this, R.color.teal_700));
                        binding.textBatteryStatus.setText("Is Full");
                        binding.textBatteryStatus.setTextColor(ContextCompat.getColor(this, R.color.teal_700));
                    }
                });

        viewModel.batteryCharging.observe(this,
                state -> {
                    if (state) {
                        binding.iconBattery.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_battery_charging));
                        binding.iconBattery.setColorFilter(ContextCompat.getColor(this, R.color.yellow));
                        binding.textBatteryStatus.setText("Is charging");
                        binding.textBatteryStatus.setTextColor(ContextCompat.getColor(this, R.color.yellow));
                    }
                });

        viewModel.batteryDischarging.observe(this,
                state -> {
                    if (state) {
                        binding.iconBattery.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_battery));
                        binding.iconBattery.setColorFilter(ContextCompat.getColor(this, R.color.orange));
                        binding.textBatteryStatus.setText("Is discharging");
                        binding.textBatteryStatus.setTextColor(ContextCompat.getColor(this, R.color.orange));
                    }
                });

        viewModel.batteryLevel.observe(this,
                level -> {
                    binding.textPorcentBatttery.setText(level + " %");
                    binding.textPorcentBatttery.setTextColor(Util.setColorCharge(this, level));
                    binding.iconPorcentBattery.setColorFilter(Util.setColorCharge(this, level));
                    binding.iconPorcentBattery.setImageDrawable(Util.setIconBattery(this, level));
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