package com.example.caronas.ui.rides;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRidesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyRidesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my rides fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}