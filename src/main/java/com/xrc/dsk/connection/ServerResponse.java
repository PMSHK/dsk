package com.xrc.dsk.connection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ServerResponse {
    private final int status;
    private final String body;
}
