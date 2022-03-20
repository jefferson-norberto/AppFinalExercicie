package cin.ufpe.finalapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;

import androidx.core.content.ContextCompat;

public class Util {

    public static Drawable setIconBattery(Context context, int level){
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

    public static int setColorCharge(Context context, int level){
        if(level >= 80){
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
}
