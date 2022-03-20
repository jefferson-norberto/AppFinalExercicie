package cin.ufpe.finalapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _wifiEnable = new MutableLiveData<>(false);
    public LiveData<Boolean> wifiEnable = _wifiEnable;

    private final MutableLiveData<Boolean> _batteryInUsb = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> _batteryInPlug = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> _batteryCharging = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> _batteryDischarging = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> _batteryFull = new MutableLiveData<>(false);
    private final MutableLiveData<Integer> _batteryLevel = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> _batteryHealth = new MutableLiveData<>(0);
    private final MutableLiveData<Long> _timeForCharger = new MutableLiveData<Long>(0L);

    public LiveData<Boolean> batteryInUsb = _batteryInUsb;
    public LiveData<Boolean> batteryInPlug = _batteryInPlug;
    public LiveData<Boolean> batteryCharging = _batteryCharging;
    public LiveData<Boolean> batteryDischarging = _batteryDischarging;
    public LiveData<Boolean> batteryFull = _batteryFull;
    public LiveData<Integer> batteryLevel = _batteryLevel;
    public LiveData<Integer> batteryHealth = _batteryHealth;
    public LiveData<Long> timeForCharger = _timeForCharger;

    public void setWifiEnable(boolean enable){
        _wifiEnable.postValue(enable);
    }

    public void setBatteryInPlug(boolean state){
        _batteryInPlug.postValue(state);
    }

    public void setBatteryInUsb(boolean state){
        _batteryInUsb.postValue(state);
    }

    public void setBatteryCharging(boolean state){
        _batteryCharging.postValue(state);
    }

    public void setBatteryDischarging(boolean state){
        _batteryDischarging.postValue(state);
    }

    public void setBatteryFull(boolean state){
        _batteryFull.postValue(state);
    }

    public void setBatteryLevel(int level){
        _batteryLevel.postValue(level);
    }

    public void setBatteryHealth(int level){
        _batteryHealth.postValue(level);
    }

    public void setTimeForCharger(long time){
        _timeForCharger.postValue(time);
    }

}
