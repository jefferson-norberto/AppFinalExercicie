package cin.ufpe.finalapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ApplicationHolder extends RecyclerView.ViewHolder {
    private final TextView textName;
    private final TextView description;
    private final ImageView icon;

    public ApplicationHolder(@NonNull View view) {
        super(view);
        this.textName = view.findViewById(R.id.text_name_option);
        this.description = view.findViewById(R.id.text_description_option);
        this.icon = view.findViewById(R.id.icon_option);
    }

    public void setAppInLine(Application app){
        this.textName.setText(String.valueOf(app.getId()));
        this.description.setText(app.getName());
        if (app.isEnable()) {
            icon.setImageResource(R.drawable.ic_baseline_check_24);
        }else{
            icon.setImageResource(R.drawable.ic_baseline_close_24);
        }
    }
}
