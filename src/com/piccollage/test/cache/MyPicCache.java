package com.piccollage.test.cache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.LruCache;

public class MyPicCache implements PicoCache{
	
	int cacheType;
	int cacheStrategy;
	int cacheSize;//kb
	
	//memory
	LruCache<String, Bitmap> mMemoryCache;
	
	//disk
	File storageDir = new File(Environment.getExternalStorageDirectory()+File.separator+"PicoCache");
	int curDiskCacheSize=0;
	
	
	ArrayList<String> keyList=new ArrayList<String>();
	
	
	
	public MyPicCache(int cacheType, int cacheStrategy){
		this.cacheType=cacheType;
		this.cacheStrategy=cacheStrategy;
		
	}

	@Override
	public void put(String key, Bitmap bitmap) {
		
		
		
		if(cacheType==PicoCacheFactory.TYPE_MEMORY){
			
			//check size
			if(mMemoryCache.size()+(bitmap.getByteCount()/1024)>cacheSize){//overflow
				if(cacheStrategy==PicoCacheFactory.STRATEGY_FIFO){
					mMemoryCache.remove(keyList.get(0));
					keyList.remove(0);
				}else if(cacheStrategy==PicoCacheFactory.STRATEGY_LIFO){				
					mMemoryCache.remove(keyList.get(keyList.size()-1));
					keyList.remove(keyList.size()-1);
				}
			}
			
			//add bitmap
			keyList.add(key);
			mMemoryCache.put(key, bitmap);
			
			
		}else{
			
			//check size
			if(curDiskCacheSize+(bitmap.getByteCount()/1024)>cacheSize){//overflow
				if(cacheStrategy==PicoCacheFactory.STRATEGY_FIFO){
					deleteFile(storageDir+File.separator+keyList.get(0));
					keyList.remove(0);
				}else if(cacheStrategy==PicoCacheFactory.STRATEGY_LIFO){				
					deleteFile(storageDir+File.separator+keyList.get(keyList.size()-1));
					keyList.remove(keyList.size()-1);
				}
			}
			
			//add bitmap
			keyList.add(key);
			saveBitmap(bitmap,storageDir,key, CompressFormat.PNG, 100);
			curDiskCacheSize+=(bitmap.getByteCount()/1024);
		}
		
	}

	@Override
	public Bitmap get(String key) {
		
		if(cacheType==PicoCacheFactory.TYPE_MEMORY){
			return mMemoryCache.get(key);
			
		}else{
			return getBitmapFromFile(storageDir+File.separator+key);
		}
	}

	@Override
	public void clear() {
		
		if(cacheType==PicoCacheFactory.TYPE_MEMORY){
			mMemoryCache.evictAll();
		}else{
			deleteDir(storageDir.toString());
		}
		keyList.clear();
		curDiskCacheSize=0;
	}

	@Override
	public void setCacheSize(int kb) {
		cacheSize=kb;
		if(cacheType==PicoCacheFactory.TYPE_MEMORY){
			mMemoryCache = new LruCache<String, Bitmap>(kb) {
		        @Override
		        protected int sizeOf(String key, Bitmap bitmap) {
		            // The cache size will be measured in kilobytes 
		            return bitmap.getByteCount() / 1024;
		        }
		    };
		}else if(cacheType==PicoCacheFactory.TYPE_DISK){
			if(storageDir.exists()==false)storageDir.mkdir();
			curDiskCacheSize=0;
		}
		
		
	}
	
	
	//====================================================
	
	private void saveBitmap(Bitmap bitmap, File dir, String filename,CompressFormat cf, int quality){
		File file=new File(dir, filename);
		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Convert bitmap to byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(cf, quality /*ignored for PNG*/, bos);
		byte[] bitmapdata = bos.toByteArray();

		//write the bytes in file
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(bitmapdata);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private Bitmap getBitmapFromFile(String filepath){  
        return BitmapFactory.decodeFile(filepath);  
    } 
	private void deleteFile(String filepath) {  
        File tFile = new File(filepath);  
        if(! tFile.exists()){  
            return;  
        }  
        if (tFile.isDirectory()) {  
            String[] children = tFile.list();  
            for (int i = 0; i < children.length; i++) {  
                new File(tFile, children[i]).delete();  
            }  
        }  
          
        tFile.delete();  
    }
	private void deleteDir(String dirpath) {  
        File dirFile = new File(dirpath);  
        if(! dirFile.exists()){  
            return;  
        }  
        if (dirFile.isDirectory()) {  
            String[] children = dirFile.list();  
            for (int i = 0; i < children.length; i++) {  
                new File(dirFile, children[i]).delete();  
            }  
        }  
          
        dirFile.delete();  
    }
	 

}
