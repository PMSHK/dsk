package com.xrc.dsk.events;

import com.google.common.eventbus.EventBus;

public class EventManager {
    private static final EventBus bus = new EventBus();
    public static void post(final Object event) {
        bus.post(event);
    }
    public static void register(final Object event) {
        bus.register(event);
    }
}
