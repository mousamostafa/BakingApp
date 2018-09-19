package com.example.mousa.baking;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mousa.baking.Fragments.DetailFragment;
import com.example.mousa.baking.Fragments.Frgmentt;
import com.example.mousa.baking.Fragments.ShowDetail;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    public static boolean IsPort;

 public static Context context;


    @SuppressLint({"NewApi", "RestrictedApi"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailcontainer);
        context=getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        IsPort=getResources().getBoolean(R.bool.TabletPort);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Bundle bundle1 = new Bundle();
        Bundle bundle2=new Bundle();
        bundle1.putString("Ing", bundle.getString("In"));
        bundle2.putString("Ste", bundle.getString("St"));
        DetailFragment fragment = new DetailFragment();
        Frgmentt frgmentt=new Frgmentt();
        ShowDetail showDetail=new ShowDetail();
        fragment.setArguments(bundle1);
        frgmentt.setArguments(bundle2);

        android.support.v4.app.FragmentManager  fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.ingrdContainer,fragment);

        android.support.v4.app.FragmentManager  fragmentManagerr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransactionn=fragmentManagerr.beginTransaction();
        fragmentTransactionn.add(R.id.sepContainer,frgmentt);

        //------------------------for Rotation --------------------------------------------
        if(savedInstanceState==null) {
            fragmentTransaction.commit();
            fragmentTransactionn.commit();
        }
        else {
            fragmentTransaction.remove(fragment);
            fragmentTransactionn.remove(frgmentt);
            fragmentTransaction.commit();
            fragmentTransactionn.commit();

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
