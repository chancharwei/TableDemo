 package com.example.chancharwei.tabledemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
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
    private static final String ROWVALUE = "row_value";
    private static final String COLUMNVALUE = "column_value";
    private Example1Fragment mExample1Fragment;
    private Example2Fragment mExample2Fragment;
    private Example3Fragment mExample3Fragment;
    private static int mRow = -1,mColumn = -1;
    public enum InfoType{
        ROW_VALUE,
        CLOLUM_VALUE
    }

    public static Handler fragmentHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg != null){
                InfoType infoType = (InfoType)msg.obj;
                switch (infoType){
                    case ROW_VALUE:
                        mRow = msg.arg1;
                        break;
                    case CLOLUM_VALUE:
                        mColumn = msg.arg1;
                        break;
                }
            }
        }
    };

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

            mRow = savedInstanceState.getInt(ROWVALUE,-1);
            mColumn = savedInstanceState.getInt(COLUMNVALUE,-1);
            Log.i(TAG,"check restore mRow = "+mRow+", mColumn = "+mColumn);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ROWVALUE,mRow);
        outState.putInt(COLUMNVALUE,mColumn);
        super.onSaveInstanceState(outState);
    }

    public void setRow(int row){
        this.mRow = row;
    }
    public void setmColumn(int column){
        this.mRow = column;
    }

    public int[] getRowAndColumn(){
        int[] rowColumnInfo = new int[2];
        rowColumnInfo[0] = mRow;
        rowColumnInfo[1] = mColumn;
        return rowColumnInfo;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d(TAG,"onNavigationItemSelected");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                Log.d(TAG,"EXAMPLE1");
                if(mExample1Fragment == null) mExample1Fragment = new Example1Fragment();
                if(mExample2Fragment != null) fragmentTransaction.remove(mExample2Fragment);
                if(mExample3Fragment != null) fragmentTransaction.remove(mExample3Fragment);
                if(mExample1Fragment.isAdded()){
                    fragmentTransaction.show(mExample1Fragment);
                }else{
                    fragmentTransaction.add(R.id.container_main, mExample1Fragment,EXAMPLE1);
                }
                fragmentTransaction.commit();
                return true;
            case R.id.navigation_control:
                Log.d(TAG,"EXAMPLE2");
                if(mExample2Fragment == null) mExample2Fragment = new Example2Fragment();
                if(mExample1Fragment != null) fragmentTransaction.remove(mExample1Fragment);
                if(mExample3Fragment != null) fragmentTransaction.remove(mExample3Fragment);
                if(mExample2Fragment.isAdded()){
                    fragmentTransaction.show(mExample2Fragment);
                }else{
                    fragmentTransaction.add(R.id.container_main, mExample2Fragment,EXAMPLE2);
                }
                fragmentTransaction.commit();
                return true;
            case R.id.navigation_tablet:
                Log.d(TAG,"EXAMPLE3");
                if(mExample3Fragment == null) mExample3Fragment = new Example3Fragment();
                if(mExample1Fragment != null) fragmentTransaction.remove(mExample1Fragment);
                if(mExample2Fragment != null) fragmentTransaction.remove(mExample2Fragment);
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
