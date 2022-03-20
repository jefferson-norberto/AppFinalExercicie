package cin.ufpe.finalapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _wifiEnable = new MutableLiveData<>(false);
    public LiveData<Boolean> wifiEnable = _wifiEnable;

    private final MutableLiveData<Boolean> _batteryInUsb = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> _batteryInPlug = new MutableLiveData<>(false);

    private final MutableLiveData<Integer> _batteryPlugged = new MutableLiveData<>(0);
    public LiveData<Integer> batteryPlugged = _batteryPlugged;

    private final MutableLiveData<Integer> _batteryState = new MutableLiveData<>(-1);
    public LiveData<Integer> batteryState = _batteryState;

    private final MutableLiveData<Integer> _batteryLevel = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> _batteryHealth = new MutableLiveData<>(0);
    private final MutableLiveData<Long> _timeForCharger = new MutableLiveData<Long>(0L);

    public LiveData<Boolean> batteryInUsb = _batteryInUsb;
    public LiveData<Boolean> batteryInPlug = _batteryInPlug;
    public LiveData<Integer> batteryLevel = _batteryLevel;
    public LiveData<Integer> batteryHealth = _batteryHealth;
    public LiveData<Long> timeForCharger = _timeForCharger;

    public void setBatteryState(int state){
        _batteryState.postValue(state);
    }

    public void setBatteryPlugged(int state){
        _batteryPlugged.postValue(state);
    }

    public void setWifiEnable(boolean enable){
        _wifiEnable.postValue(enable);
    }

    public void setBatteryInPlug(boolean state){
        _batteryInPlug.postValue(state);
    }

    public void setBatteryInUsb(boolean state){
        _batteryInUsb.postValue(state);
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
