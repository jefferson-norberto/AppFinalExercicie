package cin.ufpe.finalapp;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.List;

public class TopActivityIntentService extends IntentService {

    public static final String CHANNEL_ID = "TopActivity";
    public static final int NOTFY_ID = 33;
    String name = "";

    public TopActivityIntentService() {
        super("TopActivityIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        createNotificationChannel();

        if (intent != null) {

            ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);

            if(activityManager != null){
                List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();
                if(!tasks.get(0).getTaskInfo().topActivity.getClassName().equals(name)){
                    name = tasks.get(0).getTaskInfo().topActivity.getClassName();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_plug)
                            .setContentTitle("Top Activity")
                            .setContentText(name)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setGroup(NotificationCompat.GROUP_KEY_SILENT);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                    notificationManager.notify(NOTFY_ID, builder.build());

                    Toast.makeText(this, "Look your Notifications", Toast.LENGTH_SHORT).show();
                }

            }


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MyChannel";
            String description = "MyDescription";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}