package com.example.chancharwei.tabledemo.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chancharwei.tabledemo.MainActivity;
import com.example.chancharwei.tabledemo.R;

public class Example2Fragment extends Fragment {
    private static final String TAG = Example2Fragment.class.getName()+"[ByronLog]";
    private static MainActivity sActivity;
    private TextView mTextTitle;
    private int mRow = -1,mColumn = -1;
    public Example2Fragment(){

    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG,"onAttach");
        if(context instanceof MainActivity){
            sActivity = ((MainActivity) context);
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
        View root = inflater.inflate(R.layout.fragment_example2, container, false);
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
            constructTable(mRow,mColumn);
        }else{
            Toast.makeText(getActivity(), "Please input row and column in example1", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void constructTable(int row,int column) {
        TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tableLayout);
        tableLayout.setShrinkAllColumns(true);
        tableLayout.setStretchAllColumns(true);
        for (int i = 0; i <= row; i++) {
            TableRow tableRow = new TableRow(getActivity());

            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < column; j++) {
                TextView textView = new TextView(getActivity());
                textView.setGravity(Gravity.CENTER);
                if(i==row){
                    textView.setText("button");
                    textView.setBackground(getResources().getDrawable(R.drawable.border_style_button));
                }else if(i%2==0){
                    textView.setBackground(getResources().getDrawable(R.drawable.border_style));
                }else{
                    textView.setBackground(getResources().getDrawable(R.drawable.border_style2));
                }

                tableRow.addView(textView,new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            }
            tableLayout.addView(tableRow,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT,1));
        }
    }*/

    @SuppressLint("ResourceAsColor")
    private void constructTable(int row, int column){
        LinearLayout linearLayout = getActivity().findViewById(R.id.linearLayout2);
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
                subLinearLayout.addView(textView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1));
            }
            linearLayout.addView(subLinearLayout,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1));
        }
    }
}
