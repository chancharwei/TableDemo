package com.example.chancharwei.tabledemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chancharwei.tabledemo.MainActivity;
import com.example.chancharwei.tabledemo.R;

public class Example1Fragment extends Fragment {
    private static final String TAG = Example1Fragment.class.getName()+"[ByronLog]";
    private EditText mEditTextRow;
    private EditText mEditTextColumn;
    private static Handler sHandler;
    public Example1Fragment(){

    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG,"onAttach");
        super.onAttach(context);

        if(context instanceof MainActivity){
            sHandler = MainActivity.fragmentHandler;
        }
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
        View root = inflater.inflate(R.layout.fragment_example1, container, false);
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
        mEditTextRow = getActivity().findViewById(R.id.editTextRaw);
        mEditTextRow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s !=null ){
                    sendInfoToActivity(MainActivity.InfoType.ROW_VALUE,s.toString());
                }
            }
        });
        mEditTextColumn = getActivity().findViewById(R.id.editTextColumn);
        mEditTextColumn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(s !=null ){
                    sendInfoToActivity(MainActivity.InfoType.CLOLUM_VALUE,s.toString());
                }
            }
        });
        Log.d(TAG,"onViewCreated");
    }

    private void sendInfoToActivity(MainActivity.InfoType infoType, String info){
        Message msg = new Message();
        msg.obj = infoType;
        try {
            msg.arg1 = Integer.parseInt(info);
            sHandler.sendMessage(msg);
        }catch (NumberFormatException e){
            Log.e(TAG,"Wrong input with info = "+info);
        }
    }
}
