package com.efa.windoor.core.dtos.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CurrentUser implements Serializable {
    String name;
    String avatar;
    String userid;
    String email;
    String signature;
    String title;
    String group;
    List<Tag> tags;
    Integer notifyCount;
    Integer unreadCount;
    String country;
    String access;
    /*geographic?: {
        province?: { label?: string; key?: string };
        city?: { label?: string; key?: string };
    };*/
    String address;
    String phone;
}
