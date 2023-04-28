package com.example.sixthpractice.ui.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;

public class ProfileViewModel {
    public static void createFileAppSpecific(Context requireContext, String s, Editable text) {
    }
    public static void createFileSharedPreferences(Context cont, String fName, String fContent){
        SharedPreferences settings = cont.getSharedPreferences(fName, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("name", fContent);
        ed.apply();
    }
}
