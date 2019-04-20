package com.example.observerpattern;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements RepositoryObserver{

    private Subject mUserDataRepository;
    private TextView mTextViewUserFullName;
    private TextView mTextViewUserAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextViewUserAge = (TextView) findViewById(R.id.tv_age);
        mTextViewUserFullName = (TextView) findViewById(R.id.tv_fullname);

        mUserDataRepository = UserDataRepository.getInstance();
        mUserDataRepository.registerObserver(this);

        new CountDownTimer(3000, 3000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                UserDataRepository.getInstance().setUserData("Sami", 25);
            }
        }.start();
    }

    @Override
    public void onUserDataChanged(String fullname, int age) {
        mTextViewUserFullName.setText(fullname);
        mTextViewUserAge.setText("" + age);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserDataRepository.removeObserver(this);
    }
}
