package com.google.registersample.mvp_style;

import android.view.Display;

public class Presenter implements Contract.Presenter {

    Contract.View view;
    Contract.Model model = new Model();
    @Override
    public void attachView(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);

    }

    @Override
    public void getUsername(String user) {
        model.getUsername(user);
    }

    @Override
    public void getPassWord(String pass) {
        model.getPassWord(pass);
    }

    @Override
    public void onUserReceived(String user) {
        view.onUserReceived(user);
    }

    @Override
    public void onPassReceived(String pass) {
        view.onPassReceived(pass);
    }
}
