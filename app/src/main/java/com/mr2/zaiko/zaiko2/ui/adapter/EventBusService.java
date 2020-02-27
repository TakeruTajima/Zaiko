package com.mr2.zaiko.zaiko2.ui.adapter;

import org.greenrobot.eventbus.EventBus;

public class EventBusService {
    private static final EventBus noSubscriberEventBus = EventBus.builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();

    public static EventBus getDefault(){
        return EventBus.getDefault();
    }

    public static EventBus noSubscriberEvent(){
        return noSubscriberEventBus;
    }
}
