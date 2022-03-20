package cin.ufpe.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cin.ufpe.finalapp.databinding.ActivityAppsListBinding;

public class AppsListActivity extends AppCompatActivity {
    private ActivityAppsListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.listApps.setAdapter(listOfApps());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.listApps.setAdapter(listOfApps());
    }

    private ArrayAdapter<String> listOfApps(){
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> values = new ArrayList<String>();
        for(ApplicationInfo app:list){
            if(app.enabled){
                values.add(app.uid + " (Enable)");
            }else{
                values.add(app.uid + " (DISABLE)");
            }
        }
        return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
    }
}