package com.example.surviveinsubria.utils;

import com.example.surviveinsubria.objects.ObjectDatabase;

import java.util.List;

public interface CallbackListener {

    void onSuccess(List<ObjectDatabase> objects);

    void onFailure(Object msg);
}
