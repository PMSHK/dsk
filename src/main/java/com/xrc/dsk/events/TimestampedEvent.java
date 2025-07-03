package com.xrc.dsk.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TimestampedEvent {
    private final Object event;
    private final long timestamp;
}
