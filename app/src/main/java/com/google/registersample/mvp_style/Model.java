package com.google.registersample.mvp_style;

public class Model implements Contract.Model {
    Contract.Presenter presenter;

    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getUsername(String user) {
        presenter.onUserReceived(user);
    }

    @Override
    public void getPassWord(String pass) {
        presenter.onPassReceived(pass);
    }
}
