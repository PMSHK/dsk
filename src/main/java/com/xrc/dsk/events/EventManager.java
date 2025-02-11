package com.xrc.dsk.events;

import com.google.common.eventbus.EventBus;

public class EventManager {
    private final EventBus bus = new EventBus();
    public void post(final Object event) {
        bus.post(event);
    }
    public void register(final Object event) {
        bus.register(event);
    }
}
