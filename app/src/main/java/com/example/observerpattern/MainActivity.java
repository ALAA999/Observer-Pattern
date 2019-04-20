package com.example.observerpattern;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RepositoryObserver {

    private Subject mUserDataRepository;
    private TextView mTextViewUserFullName;
    private TextView mTextViewUserAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewUserAge = (TextView) findViewById(R.id.tv_age);
        mTextViewUserFullName = (TextView) findViewById(R.id.tv_fullname);

        FragmentUtil.addFragment(this, new Fragment2(), R.id.fragment2);

        mUserDataRepository = UserDataRepository.getInstance();
        mUserDataRepository.registerObserver(this);
        new CountDownTimer(3000, 3000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                UserDataRepository.getInstance().setUserData("Alaa", 20);
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
