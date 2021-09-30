package com.efa.windoor.admin.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AdminResponse implements Serializable {
    boolean success;
    Object data;
    String errorCode;
    String errorMessage;
}
