package com.example.viralyapplication.utility;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class EventBus extends Bus {

    private static EventBus eventBus;

    private EventBus (ThreadEnforcer enforcer){
        super(enforcer);
    }
    public static EventBus getInstance(){
        if(eventBus == null){
            eventBus = new EventBus(ThreadEnforcer.ANY);
        }
        return eventBus;
    }
}