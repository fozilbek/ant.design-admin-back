package com.efa.windoor.core.dtos;

import com.efa.windoor.core.validations.user.CheckUniqueUsername;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.efa.windoor.core.constants.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Getter
@Setter
@CheckUniqueUsername
@JsonInclude(NON_NULL)
public class UserDto implements Serializable {
    private Long id;
    private Role role;
    private String token;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String username;
    private String password;
    private Boolean isActive;
    private String newPassword;
}
