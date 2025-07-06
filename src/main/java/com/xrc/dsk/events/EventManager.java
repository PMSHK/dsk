package com.xrc.dsk.events;

import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.xrc.dsk.settings.AppParameters.DEBOUNCED_FACTOR;

public class EventManager {
    private static final Map<Class<?>, TimestampedEvent> events = new ConcurrentHashMap<>();
    private static final double debounceFactor = DEBOUNCED_FACTOR;
    private static final EventBus bus = new EventBus();
    public static void post(final Object event) {
        TimestampedEvent last = events.get(event.getClass());
        long now = System.currentTimeMillis();
        if (last == null || now-last.getTimestamp() > debounceFactor) {
            events.put(event.getClass(), new TimestampedEvent(event, now));
            bus.post(event);
        }
    }
    public static void register(final Object event) {
        bus.register(event);
    }
    public static void unregister(final Object event) {bus.unregister(event);}

}
