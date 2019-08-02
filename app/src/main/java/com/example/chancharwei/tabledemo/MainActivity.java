 package com.example.chancharwei.tabledemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.chancharwei.tabledemo.fragments.Example1Fragment;
import com.example.chancharwei.tabledemo.fragments.Example2Fragment;
import com.example.chancharwei.tabledemo.fragments.Example3Fragment;

 public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
     private static final String TAG = MainActivity.class.getName()+"[ByronLog]";
     private FragmentManager mFragmentManager;
     private static final String EXAMPLE1 = "EXAMPLE1";
     private static final String EXAMPLE2 = "EXAMPLE2";
     private static final String EXAMPLE3 = "EXAMPLE3";
     private Example1Fragment mExample1Fragment;
     private Example2Fragment mExample2Fragment;
     private Example3Fragment mExample3Fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        ((BottomNavigationView) findViewById(R.id.navigation)).setOnNavigationItemSelectedListener(this);
        if(savedInstanceState == null){
            ((BottomNavigationView) findViewById(R.id.navigation)).setSelectedItemId(R.id.navigation_home); //initial set to home fragment
        }else{
            mExample1Fragment = (Example1Fragment) mFragmentManager.findFragmentByTag(EXAMPLE1);
            mExample2Fragment = (Example2Fragment) mFragmentManager.findFragmentByTag(EXAMPLE2);
            mExample3Fragment = (Example3Fragment) mFragmentManager.findFragmentByTag(EXAMPLE3);
        }
    }


     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
         Log.d(TAG,"onNavigationItemSelected");
         FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

         switch (menuItem.getItemId()){
             case R.id.navigation_home:
                 Log.d(TAG,"HOME");
                 if(mExample1Fragment == null) mExample1Fragment = new Example1Fragment();
                 if(mExample2Fragment != null) fragmentTransaction.hide(mExample2Fragment);
                 if(mExample3Fragment != null) fragmentTransaction.hide(mExample3Fragment);
                 if(mExample1Fragment.isAdded()){
                     fragmentTransaction.show(mExample1Fragment);
                 }else{
                     fragmentTransaction.add(R.id.container_main, mExample1Fragment,EXAMPLE1);
                 }
                 fragmentTransaction.commit();
                 return true;
             case R.id.navigation_control:
                 Log.d(TAG,"CONTROL");
                 if(mExample2Fragment == null) mExample2Fragment = new Example2Fragment();
                 if(mExample1Fragment != null){
                     fragmentTransaction.hide(mExample1Fragment);
                 }else{
                     Log.d(TAG,"mExample1Fragment not null");
                 }
                 if(mExample3Fragment != null) fragmentTransaction.hide(mExample3Fragment);
                 if(mExample2Fragment.isAdded()){
                     fragmentTransaction.show(mExample2Fragment);
                 }else{
                     fragmentTransaction.add(R.id.container_main, mExample2Fragment,EXAMPLE2);
                 }
                 fragmentTransaction.commit();
                 return true;
             case R.id.navigation_tablet:
                 Log.d(TAG,"TABLET");
                 if(mExample3Fragment == null) mExample3Fragment = new Example3Fragment();
                 if(mExample1Fragment != null) fragmentTransaction.hide(mExample1Fragment);
                 if(mExample2Fragment != null) fragmentTransaction.hide(mExample2Fragment);
                 if(mExample3Fragment.isAdded()){
                     fragmentTransaction.show(mExample3Fragment);
                 }else{
                     fragmentTransaction.add(R.id.container_main, mExample3Fragment,EXAMPLE3);
                 }
                 fragmentTransaction.commit();
                 return true;
         }
         return false;
     }
}
