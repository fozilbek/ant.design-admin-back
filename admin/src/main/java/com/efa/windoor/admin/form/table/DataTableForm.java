package com.efa.windoor.admin.form.table;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataTableForm<T> implements Serializable {
    public List<T> data;
    public long total;
    private boolean success;
}
