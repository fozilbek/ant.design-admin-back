package com.efa.windoor.core.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class AuthenticationResponse implements Serializable {

    private final String token;
    private final String status;
    private final String currentAuthority;
    private final String type;
}
