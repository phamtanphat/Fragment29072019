package com.example.fragment29072019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager =  getSupportFragmentManager();
    MutableLiveData<Boolean> mutableLiveDataExecute = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mutableLiveDataExecute.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null && !aBoolean){
                    Log.d("BBB",fragmentManager.getFragments().size() +"");
                }
            }
        });
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AndroidFragment androidFragment = new AndroidFragment();
        AndroidFragment androidFragment1 = new AndroidFragment();
        fragmentTransaction.add(R.id.linearContainer , androidFragment);
        fragmentTransaction.add(R.id.linearContainer , androidFragment1);
        fragmentTransaction.commit();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (fragmentManager.executePendingTransactions()){
                    this.run();
                }else{
                    mutableLiveDataExecute.setValue(false);
                }
            }
        });

    }
}
