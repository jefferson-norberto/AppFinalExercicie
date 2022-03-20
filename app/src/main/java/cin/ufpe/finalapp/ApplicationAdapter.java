package cin.ufpe.finalapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationHolder> {
    private final ArrayAdapter<Application> applications;
    private final Activity activity;

    public ApplicationAdapter(ArrayAdapter<Application> applications, Activity activity) {
        this.applications = applications;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ApplicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = activity.getLayoutInflater().inflate(R.layout.line_application, parent, false);
        return new ApplicationHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationHolder holder, int position) {
        Application application = applications.getItem(position);
        holder.setAppInLine(application);
    }

    @Override
    public int getItemCount() {
        return applications.getCount();
    }
}
