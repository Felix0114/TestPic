package com.piccollage.test.cache;

public class PicoCacheFactory {
	public static final int TYPE_MEMORY = 1;
    public static final int TYPE_DISK   = 2;

    public static final int STRATEGY_FIFO = 100; // first in first out
    public static final int STRATEGY_LIFO = 200; // last in first out
    public static PicoCache create(int cacheType, int cacheStrategy) {
        return new MyPicCache(cacheType,cacheStrategy);
    }

}
