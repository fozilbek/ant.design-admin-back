package com.efa.windoor.admin.form.table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FilterForm implements Serializable {
    @JsonProperty("current")
    private Integer current;
    @JsonProperty("pageSize")
    private Integer pageSize;
}
