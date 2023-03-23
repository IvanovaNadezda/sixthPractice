package com.example.sixthpractice;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class DisplayService extends Service {

    private WindowManager mWindowManager;
    private View mBannerView;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("...");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Создаем визуальный элемент для баннера
        mBannerView = LayoutInflater.from(this).inflate(R.layout.banner_layout, null);

        // Инициализируем WindowManager
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Определяем параметры для размещения баннера
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        // Устанавливаем позицию баннера
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        params.y = 100;

        //Запрос разрешения
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent2 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        } else {
            // Разрешение уже получено, отобразить баннер
            mWindowManager.addView(mBannerView, params);
        }

        // Создаем PendingIntent для перехода в запущенное приложение
        Intent appIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, appIntent, 0);

        // Добавляем возможность перехода в запущенное приложение при клике на баннер
        mBannerView.setOnClickListener(v -> {
            try {
                pendingIntent.send();
                onDestroy();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Удаляем баннер с экрана при завершении сервиса
        if (mBannerView != null) {
            mWindowManager.removeView(mBannerView);
        }
    }
}