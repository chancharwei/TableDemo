package com.example.chancharwei.tabledemo.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chancharwei.tabledemo.MainActivity;
import com.example.chancharwei.tabledemo.R;

import java.lang.ref.WeakReference;
import java.util.Random;

public class Example3Fragment extends Fragment {
    private static final String TAG = Example3Fragment.class.getName()+"[ByronLog]";
    private static final String RANDOM_ROW = "random_row";
    private static final String RANDOM_COLUMN = "random_column";
    private static MainActivity sActivity;
    private int mRow = -1,mColumn = -1;
    private boolean stopThread;
    private LinearLayout mSelectSubLinearLayouot;
    private TextView mSelectTextView;
    private TextView mSelectButtonTextView;
    private MyHandler mHandler;
    private Thread mThread;
    private static class MyHandler extends Handler{
        private final WeakReference<MainActivity> mActivity;
        private final WeakReference<Example3Fragment> mFragment;
        MyHandler(MainActivity activity,Example3Fragment fragment){
            mActivity = new WeakReference<>(activity);
            mFragment = new WeakReference<>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            if(msg != null){
                Bundle bundle = (Bundle)msg.obj;
                if(bundle != null){
                    if(mActivity.get() != null && mFragment.get() != null){
                        int row = bundle.getInt(RANDOM_ROW);
                        int column = bundle.getInt(RANDOM_COLUMN);
                        if(row <0 || column<0){
                            Toast.makeText(mActivity.get(), "error random row = "+row+" column = "+column, Toast.LENGTH_SHORT).show();
                        }else{
                            mFragment.get().markRandomColumn(row,column);
                        }
                    }
                }
            }

        }
    }



    public Example3Fragment(){
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG,"onAttach");
        if(context instanceof MainActivity){
            sActivity = ((MainActivity) context);
            mHandler = new MyHandler(sActivity,this);
        }

        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View root = inflater.inflate(R.layout.fragment_example3, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG,"onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG,"onPause");
        super.onPause();
    }


    @Override
    public void onStop() {
        Log.d(TAG,"onStop");
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG,"onDestroyView");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        stopThread = true;
        if(mThread != null)mThread.interrupt();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG,"onDetach");
        super.onDetach();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"onViewCreated");
        if(sActivity != null){
            int[] rowColumnInfo = sActivity.getRowAndColumn();
            mRow = rowColumnInfo[0];
            mColumn = rowColumnInfo[1];
        }
        if(mRow >= 0 && mColumn >= 0){
            stopThread = false;
            constructTable(mRow,mColumn);
            createThreadForRandom();
        }else{
            Toast.makeText(sActivity, "Please input row and column in example1", Toast.LENGTH_SHORT).show();
        }
    }

    private void createThreadForRandom(){
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stopThread){
                    Message message =  new Message();
                    Bundle bundle = new Bundle();
                    Random random = new Random();
                    int row = random.nextInt(mRow);
                    int column = random.nextInt(mColumn);
                    Log.d(TAG,"random row = "+row+" column = "+column);
                    bundle.putInt(RANDOM_ROW,row);
                    bundle.putInt(RANDOM_COLUMN,column);
                    message.obj = bundle;
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mThread.start();
    }
    @SuppressLint("ClickableViewAccessibility")
    private synchronized void clearLastRandomColumnMark(){
        if(mSelectSubLinearLayouot != null){
            mSelectSubLinearLayouot.setBackgroundResource(R.drawable.border_column);
            mSelectSubLinearLayouot = null;
        }
        if(mSelectTextView != null){
            mSelectTextView.setText(null);
            mSelectTextView = null;
        }

        if(mSelectButtonTextView != null){
            mSelectButtonTextView.setBackgroundResource(R.drawable.border_style_button);
            mSelectButtonTextView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            mSelectButtonTextView = null;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void markRandomColumn(int randomRow, int randomColumn){
        clearLastRandomColumnMark();
        LinearLayout linearLayout = getActivity().findViewById(R.id.linearLayout3);
        LinearLayout subLinearLayout = (LinearLayout) linearLayout.getChildAt(randomColumn);
        subLinearLayout.setBackgroundResource(R.drawable.border_select_column);
        TextView selectTextView = (TextView)subLinearLayout.getChildAt(randomRow);
        selectTextView.setText("random");
        TextView selectButtonTextView = (TextView)subLinearLayout.getChildAt(mRow);
        selectButtonTextView.setBackground(getResources().getDrawable(R.drawable.border_style_select_button));

        mSelectSubLinearLayouot = subLinearLayout;
        mSelectTextView = selectTextView;
        mSelectButtonTextView = selectButtonTextView;

        mSelectButtonTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mSelectButtonTextView.setBackgroundResource(R.drawable.border_style_button);
                    clearLastRandomColumnMark();
                }
                return false;
            }
        });
    }
    @SuppressLint("ResourceAsColor")
    private void constructTable(int row, int column){
        LinearLayout linearLayout = getActivity().findViewById(R.id.linearLayout3);
        for (int i = 0; i < column; i++) {
            LinearLayout subLinearLayout = new LinearLayout(getActivity());
            subLinearLayout.setOrientation(LinearLayout.VERTICAL);
            subLinearLayout.setPadding(8,8,8,8);
            subLinearLayout.setBackgroundResource(R.drawable.border_column);
            for (int j = 0; j <= row; j++) {
                TextView textView = new TextView(getActivity());
                textView.setGravity(Gravity.CENTER);
                if(j==row){
                    textView.setText("button");
                    textView.setBackground(getResources().getDrawable(R.drawable.border_style_button));
                }else if(j%2==0){
                    textView.setBackground(getResources().getDrawable(R.drawable.border_style));
                }else{
                    textView.setBackground(getResources().getDrawable(R.drawable.border_style2));
                }
                subLinearLayout.addView(textView,j,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1));
            }
            linearLayout.addView(subLinearLayout,i,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1));
        }
    }
}
