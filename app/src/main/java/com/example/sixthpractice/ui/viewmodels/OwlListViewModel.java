package com.example.sixthpractice.ui.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sixthpractice.data.datasources.Entity.OwlEntity;
import com.example.sixthpractice.data.datasources.dataOwl;
import com.example.sixthpractice.data.models.Owl;
import com.example.sixthpractice.data.repository.OwlRepository;

import java.util.List;

public class OwlListViewModel extends AndroidViewModel {
    private OwlRepository mRepository;

    private final LiveData<List<Owl>> mAllBooks;

    public OwlListViewModel(Application application) {
        super(application);
        mRepository = new OwlRepository(application);
        mAllBooks = mRepository.getAllOwls();
    }

    public void setAddress(Context context, String fileName, String fileContext){
        mRepository.setAddress(context, fileName, fileContext);
    }
    public void setAddress1(Context context, String fileName, String fileContext){
        mRepository.setAddress1(context, fileName, fileContext);
    }
    public void setAddress2(Activity act, String fileName, String fileContext){
        mRepository.setAddress1(act, fileName, fileContext);
    }

    public LiveData<List<Owl>> getAllOwls() { return mAllBooks; }

    public void insert(Owl owl) { mRepository.insert(new OwlEntity(owl.getName(), owl.getName(), owl.getImage())); }
}

