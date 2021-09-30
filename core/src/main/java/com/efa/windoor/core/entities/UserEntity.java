package com.efa.windoor.core.entities;


import com.efa.windoor.core.constants.TableNames;
import com.efa.windoor.core.dtos.UserDto;
import com.efa.windoor.core.constants.Role;
import com.efa.windoor.core.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Entity
@Table(name = TableNames.WD_USER)
public class UserEntity extends BaseEntity {


    @Column(name = "token")
    private String token;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;


    public UserDto getDto() {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(this, userDto, "password");
        return userDto;
    }
}
