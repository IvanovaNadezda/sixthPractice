package com.example.sixthpractice.ui.fragments;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sixthpractice.R;
import com.example.sixthpractice.databinding.Screen1Binding;
import com.example.sixthpractice.ui.activities.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileFragment extends Fragment {
    Screen1Binding binding;
    private static final String CHANNEL_ID = "my_channel";

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 1;


    public ProfileFragment() {
        super(R.layout.screen1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = Screen1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);







        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_fodder_list_fragment);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_book_list_fragment);
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("name", binding.editText.getText().toString());
                editor.apply();
            }
        });
        // Создаем канал уведомлений (для Android 8.0 и выше)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Мой канал";
            String description = "Канал для уведомлений";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = requireContext().
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Создаем intent, который будет запускать наше приложение при нажатии на уведомление
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0);

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Есть ли разрешения на отправку уведомления
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                        POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // Если разрешение не получено, запрашиваем его у пользователя
                    requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                }
                // Создаем уведомление
                NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(),
                        CHANNEL_ID)
                        .setSmallIcon(R.drawable.owl) //маленькая иконка
                        .setContentTitle("Совиный домик") //заголовок
                        .setContentText("Не забудь покормить сову!") //текст уведомления
                        .setAutoCancel(true) // автоматически закрывает уведомление после нажатия
                        .setContentIntent(pendingIntent)// добавляем PendingIntent
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                // Отправляем уведомление
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }


        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults
        );
    }
//    void createFileAppScecificStorage(String fileName, String text)
//    {
//        Context context = getContext();
//        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
//            fos.write(text.getBytes());
//              Toast.makeText(context, "Был создан текстовый файл в общем хранилище  "
//                   + context.getDataDir().getAbsolutePath()+"/"
//                   + fileName, Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {throw new RuntimeException(e);}
//    }






}



