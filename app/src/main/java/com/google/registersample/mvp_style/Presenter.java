package com.google.registersample.mvp_style;

public class Presenter implements Contract.Presenter {

    Contract.View view;
    Contract.Model model = new Model();

    public Presenter(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);
    }



    @Override
    public void register(String user, String pass) {
        model.register(user,pass);
    }

    @Override
    public void onDataReceived(String user) {
        view.onDataReceived(user);
    }


}
