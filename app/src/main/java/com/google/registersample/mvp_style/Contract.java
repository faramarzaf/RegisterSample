package com.google.registersample.mvp_style;

public interface Contract {

    interface View {
        void onUserReceived(String user);
        void onPassReceived(String pass);
    }

    interface Presenter {
        void attachView(View view);
        void getUsername(String user);
        void getPassWord(String pass);
        void onUserReceived(String user);
        void onPassReceived(String pass);
    }

    interface Model {
        void attachPresenter(Presenter presenter);
        void getUsername(String user);
        void getPassWord(String pass);
    }


}
