package etc.jyclapps.testproject.myapplication;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Josh 2 on 4/15/2017.
 */

public class CheckTimerService extends Service {
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
