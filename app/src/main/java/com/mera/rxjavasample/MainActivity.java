package com.mera.rxjavasample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {


    String welcome = "Welcome";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    private TextView txtTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTV = findViewById(R.id.txttv);

        myObservable = Observable.just(welcome);
        myObservable.subscribeOn(Schedulers.io());
        myObservable.subscribeOn(AndroidSchedulers.mainThread());

        myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("mah", "onSubscribe invoked");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e("mah", "onNext invoked");
                txtTV.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("mah", "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.e("mah", "onComplete invoked");
            }
        };

        myObservable.subscribe(myObserver);

    }
}