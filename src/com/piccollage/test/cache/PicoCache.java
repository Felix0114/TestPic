package com.piccollage.test.cache;

import android.graphics.Bitmap;

public interface PicoCache {
    void put(String key, Bitmap bitmap);
    Bitmap get(String key);
    void clear();
    void setCacheSize(int kb);
}
