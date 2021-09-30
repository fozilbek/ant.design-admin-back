package com.efa.windoor.core.dtos.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tag implements Serializable {
    String key;
    String label;
}
