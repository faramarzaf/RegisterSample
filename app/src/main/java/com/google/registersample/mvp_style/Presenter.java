package com.google.registersample.mvp_style;

public class Presenter implements Contract.Presenter {

    Contract.View view;
    Contract.Model model = new Model();
    @Override
    public void attachView(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);
        model.loadData();
    }

    @Override
    public void register(String user, String pass) {
        model.register(user,pass);
    }

    @Override
    public void userRegistered() {
        view.userRegistered();
        model.loadData();
    }

    @Override
    public void onUserLoaded(String user) {
        view.onUserLoaded(user);
    }


}
