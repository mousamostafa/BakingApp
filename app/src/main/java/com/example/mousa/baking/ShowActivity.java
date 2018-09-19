package com.example.mousa.baking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.mousa.baking.Fragments.ShowDetail;


public class ShowActivity extends AppCompatActivity {
    android.app.FragmentManager fragmentManager =getFragmentManager();
    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    ShowDetail mshowDetail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showw);
        if (savedInstanceState != null) {
            mshowDetail = (ShowDetail) getFragmentManager().findFragmentByTag("gg");


        } else {
            mshowDetail = new ShowDetail();
            fragmentTransaction.add(R.id.fragshow, mshowDetail);
            fragmentTransaction.commit();
        }

    }

}





