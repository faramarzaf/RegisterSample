package com.google.registersample.mvp_style;

public interface Contract {

    interface View {
        void onDataReceived(String user);
    }

    interface Presenter {
        void register(String user , String pass);
        void onDataReceived(String user);
        }

    interface Model {
        void attachPresenter(Presenter presenter);
        void register(String user , String pass);

    }


}
