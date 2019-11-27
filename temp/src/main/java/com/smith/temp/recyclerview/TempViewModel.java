package com.smith.temp.recyclerview;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TempViewModel extends ViewModel {
    private List<String> dataList;
    MutableLiveData<List<String >> list = new MutableLiveData<>();

    public void getList() {
        if (null == dataList) {
            dataList = new ArrayList<>();
        }
        for(int i = 0; i < 5; i++) {
            dataList.add("---item---"+i);
        }
        list.setValue(dataList);
    }

    public void loadList() {
        List<String > list1 = list.getValue();
        list1.add("hello----");
        list.postValue(list1);
    }
}
