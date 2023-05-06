package com.example.sixthpractice.data.datasources;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;


import com.example.sixthpractice.Manifest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class dataOwl {
    public static void createFileAppSpecific(Context context, String fileName, String fileContext) {

        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(fileContext.getBytes());
            File filePath = Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_DOWNLOADS));
            File file = new File(filePath + ".txt");
            Toast.makeText(context, "Был создан текстовый файл в общем хранилище  "
                    + context.getDataDir().getAbsolutePath()+"/"
                    + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public static void createFileSharedPreferences(Context cont, String fName, String fContent){
        SharedPreferences settings = cont.getSharedPreferences(fName, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("name", fContent);
        ed.apply();
    }

    public static void createFileExtWithToast(Activity activity, String fileName, String fileContent) {
        Context context = activity.getApplicationContext();
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(filePath, fileName + ".txt");
            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(fileContent.getBytes());
                Toast.makeText(context, "Файл в:" + filePath+ "/" + fileName + ".txt", Toast.LENGTH_SHORT).show();
                System.out.println("fiiiiile");
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    public void createFileSharedPref(Context context, String fileName, String fileContent) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString( "nothing", fileContent);
        editor.apply();
        Toast.makeText(context, "Файл создан " + settings + fileName, Toast.LENGTH_SHORT).show();
        System.out.println("fiiiiile");
    }

}
