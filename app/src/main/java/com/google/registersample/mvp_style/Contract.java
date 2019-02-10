package com.google.registersample.mvp_style;

public interface Contract {

    interface View {
        void userRegistered();
        void onUserLoaded(String user);
    }

    interface Presenter {
        void attachView(View view);
        void register(String user , String pass);
        void userRegistered();
        void onUserLoaded(String user);

        }

    interface Model {
        void attachPresenter(Presenter presenter);
        void register(String user , String pass);
        void loadData();
    }


}
