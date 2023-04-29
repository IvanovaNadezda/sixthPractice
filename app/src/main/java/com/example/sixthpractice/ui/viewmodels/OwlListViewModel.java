package com.example.sixthpractice.ui.viewmodels;

import android.app.Application;
import android.content.Context;
import android.text.Editable;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sixthpractice.data.database.Entity.OwlEntity;
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

    public LiveData<List<Owl>> getAllOwls() { return mAllBooks; }

    public void insert(Owl owl) { mRepository.insert(new OwlEntity(owl.getName(), owl.getName(), owl.getImage())); }
}

