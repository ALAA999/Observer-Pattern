package com.example.observerpattern;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements RepositoryObserver {
    private TextView mTextViewUserFullName;
    private TextView mTextViewUserAge;
    private Subject mUserDataRepository;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        mTextViewUserAge = (TextView) view.findViewById(R.id.tv_age);
        mTextViewUserFullName = (TextView) view.findViewById(R.id.tv_fullname);

        mUserDataRepository = UserDataRepository.getInstance();
        mUserDataRepository.registerObserver(this);

        return view;
    }

    @Override
    public void onUserDataChanged(String fullname, int age) {
        mTextViewUserFullName.setText(fullname);
        mTextViewUserAge.setText("" + age);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUserDataRepository.removeObserver(this);
    }
}
