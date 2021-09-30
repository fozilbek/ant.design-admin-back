package com.efa.windoor.core.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}
