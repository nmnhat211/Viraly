package com.example.viralyapplication.ui.event;

import com.example.viralyapplication.repository.model.UserModel;

public class SigInEvent extends BaseEvent {
    private UserModel userData;

    public SigInEvent(boolean status, String errorMessage, UserModel userModel, String filterName) {
        super(status, errorMessage, null);
        setFilterName(filterName);
        this.userData = userModel;
    }

    public UserModel getUserData() {
        return userData;
    }
}
