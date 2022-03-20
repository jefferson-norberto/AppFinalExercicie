package cin.ufpe.finalapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Util {

    public static Drawable setIconBatteryCharger(Context context, int level){
        if(level >= 95){
            return ContextCompat.getDrawable(context, R.drawable.ic_battery);
        }else if(level >= 80){
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_battery_6);
        }else if(level >= 65){
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_battery_5);
        }else if(level >= 50){
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_battery_4);
        }else if (level >= 35){
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_battery_3);
        }else if (level >= 15){
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_battery_2);
        }else if (level >= 5){
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_battery_1);
        }else{
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_battery_0);
        }
    }

    public static int setColorCharger(Context context, int level){
        if(level >= 60){
            return ContextCompat.getColor(context, R.color.green);
        }else if (level > 15){
            return ContextCompat.getColor(context, R.color.yellow);
        }else{
            return ContextCompat.getColor(context, R.color.red);
        }
    }

    public static String setTextBatteryHealth(int health){
        if(health == BatteryManager.BATTERY_HEALTH_GOOD){
            return "Health Battery: Good";
        }else if(health == BatteryManager.BATTERY_HEALTH_DEAD){
            return "Health Battery: Dead";
        }else if(health == BatteryManager.BATTERY_HEALTH_UNKNOWN) {
            return "Health Battery: Unknown";
        }else if(health == BatteryManager.BATTERY_HEALTH_COLD) {
            return "Health Battery: Cold";
        }else if(health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
            return "Health Battery: Over Voltage";
        }else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT){
            return "Health Battery: Overheat";
        }
        return "Health Battery; Failed";
    }

    public static Drawable setIconWifiEnable(Context context, boolean enable){
        if(enable){
            return ContextCompat.getDrawable(context, R.drawable.ic_wifi_on);
        }else{
            return ContextCompat.getDrawable(context, R.drawable.ic_wifi_off_24);
        }
    }

    public static String setTextWifiEnable(Context context, boolean enable){
        if(enable){
            return "Wifi Enable";
        }else{
            return "Wifi Disable";
        }
    }

    public static int setColorEnable(Context context, boolean state){
        if (state) {
            return ContextCompat.getColor(context, R.color.teal_700);
        } else {
            return ContextCompat.getColor(context, R.color.grey);
        }
    }

    public static String setTextPlugged(int state){
        if (state == BatteryManager.BATTERY_PLUGGED_AC) {
            return "Is Plugged in AC";
        }else if (state == BatteryManager.BATTERY_PLUGGED_USB){
            return "Is Plugged in UBS";
        }else{
            return "Is Not Plugged";
        }
    }

    public static int setColorBatteryState(Context context, int state){
        if(state == BatteryManager.BATTERY_STATUS_DISCHARGING){
            return ContextCompat.getColor(context, R.color.orange);
        }else if(state == BatteryManager.BATTERY_STATUS_CHARGING){
            return ContextCompat.getColor(context, R.color.yellow);
        }else if (state == BatteryManager.BATTERY_STATUS_FULL){
            return ContextCompat.getColor(context, R.color.teal_700);
        }
        return ContextCompat.getColor(context, R.color.black);
    }

    public static String setTextBatteryState(Context context, int state){
        if(state == BatteryManager.BATTERY_STATUS_DISCHARGING){
            return "Is discharging";
        }else if(state == BatteryManager.BATTERY_STATUS_CHARGING){
            return "Is charging";
        }else if (state == BatteryManager.BATTERY_STATUS_FULL){
            return "Is Full";
        }
        return "Erro!";
    }

    public static Drawable setIconBatteryState(Context context, int state){
        if(state == BatteryManager.BATTERY_STATUS_DISCHARGING){
            return ContextCompat.getDrawable(context, R.drawable.ic_battery);
        }else if(state == BatteryManager.BATTERY_STATUS_CHARGING){
            return ContextCompat.getDrawable(context, R.drawable.ic_battery_charging);
        }else if (state == BatteryManager.BATTERY_STATUS_FULL){
            return ContextCompat.getDrawable(context, R.drawable.ic_battery_alert);
        }
        return ContextCompat.getDrawable(context, R.drawable.ic_battery_unknown);
    }

    public static Drawable setIconPlugged(Context context, int state) {
        if (state == BatteryManager.BATTERY_PLUGGED_AC) {
            return ContextCompat.getDrawable(context, R.drawable.ic_plug);
        }else if (state == BatteryManager.BATTERY_PLUGGED_USB){
            return ContextCompat.getDrawable(context, R.drawable.ic_usb);
        }else{
            return ContextCompat.getDrawable(context, R.drawable.ic_block_);
        }
    }

    public static String setTextTimeForCharger(Long time){
        if (time != -1) {
            int minutes = (int) (time / 60000);
            int hours = minutes / 60;
            minutes = minutes % 60;
            if(hours > 0){
                return "Time reming for charger: " +
                        hours + " h and " +
                        minutes + " min";
            }else{
                return "Time reming for charger: " +
                        minutes + " min";
            }
        } else {
            return "";
        }
    }
}
