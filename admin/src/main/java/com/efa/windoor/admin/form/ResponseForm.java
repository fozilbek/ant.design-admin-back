package com.efa.windoor.admin.form;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResponseForm implements Serializable {
    private String msg;
    private boolean success;
    private Object obj;
}
