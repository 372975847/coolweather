package com.coolweather.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class AutoUpdataService extends Service {

    public AutoUpdataService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        upDateWeather();
        upDateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8*60*60*1000;  // 这是8小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;  // 触发的时间
        Intent i = new Intent(this,AutoUpdataService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    /*
    * 更新必应每日一图
    * */
    private void upDateBingPic() {

    }

    /*
    * 更新天气信息
    * */
    private void upDateWeather() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }
}
