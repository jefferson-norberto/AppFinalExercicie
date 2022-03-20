package cin.ufpe.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ApplicationAdapter applicationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        applicationAdapter = new ApplicationAdapter(listOfApps(), this);
        binding.listApps.setLayoutManager(new LinearLayoutManager(this));
        binding.listApps.setAdapter(applicationAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private ArrayAdapter<Application> listOfApps(){
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<Application> values = new ArrayList<Application>();
        Application application;
        for(ApplicationInfo app:list){
            application = new Application(app.uid, app.packageName, app.enabled);
            values.add(application);
        }
        return new ArrayAdapter<Application>(this, android.R.layout.simple_list_item_1, values);
    }
}