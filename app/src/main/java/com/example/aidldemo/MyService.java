package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * author: tonydeng
 * mail : tonydeng@hxy.com
 * 2018/11/22
 */
public class MyService extends Service{
    private final static String TAG = "MyService";
    // 客户端的包名
    private final static String CLIENT_PACKAGE = "com.example.daxiong";

    private List<Book> mBooks = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        initData();
    }

    private void initData() {
        for (int i = 1; i < 6; i++) {
            Book book = new Book();
            book.setName(i + "天龙八部");
            book.setPrice(i * 10);
            mBooks.add(book);
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind:" + intent.toString());
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onBionStartCommandnd:" + intent.toString());
        return super.onStartCommand(intent, flags, startId);
    }

    private final BookManager.Stub mBinder = new BookManager.Stub() {

        @Override
        public List<Book> getBooks() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            if(!mBooks.contains(book)){
                mBooks.add(book);
            }
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Log.d(TAG, "onTransact0");
           /* String packageName = null;
            String[] packages = MyService.this.getPackageManager().getPackagesForUid(getCallingPid());
            if(packages != null && packages.length > 0){
                packageName = packages[0];
                Log.d(TAG, "packageName:" + packageName);
            }
            if(!CLIENT_PACKAGE.equals(packageName)){
                return false;
            }
            Log.d(TAG, "onTransact1");*/
            return super.onTransact(code, data, reply, flags);

        }
    };

}
