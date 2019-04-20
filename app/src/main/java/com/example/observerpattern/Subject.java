package com.example.observerpattern;


import android.widget.CheckBox;

public interface Subject {
    void registerObserver(RepositoryObserver repositoryObserver);
    void removeObserver(RepositoryObserver repositoryObserver);
    void notifyObservers();
}