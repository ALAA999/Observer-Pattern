package com.example.observerpattern;

public interface RepositoryObserver {
    void onUserDataChanged(String fullname, int age);
}