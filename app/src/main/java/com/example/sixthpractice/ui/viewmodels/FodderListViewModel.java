package com.example.sixthpractice.ui.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sixthpractice.data.datasources.Entity.FodderEntity;
import com.example.sixthpractice.data.datasources.dataOwl;
import com.example.sixthpractice.data.models.Fodder;
import com.example.sixthpractice.data.repository.FodderRepository;

import java.util.List;

public class FodderListViewModel extends AndroidViewModel {
    private FodderRepository mRepository;

    private final LiveData<List<Fodder>> mAllFodders;

    public FodderListViewModel(Application application) {
        super(application);
        mRepository = new FodderRepository(application);
        mAllFodders = mRepository.getAllFodders();
    }

    public LiveData<List<Fodder>> getAllFodders() { return mAllFodders; }

    public void insert(Fodder fodder) { mRepository.insert(new FodderEntity(fodder.getName(), fodder.getPhoto())); }
}

