package com.efa.windoor.admin.serivces;


import com.efa.windoor.admin.utils.Utils;
import com.efa.windoor.core.constants.Role;
import com.efa.windoor.core.dtos.ModifiedResponse;
import com.efa.windoor.core.dtos.UserDto;
import com.efa.windoor.core.dtos.user.CurrentUser;
import com.efa.windoor.core.entities.UserEntity;
import com.efa.windoor.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;


    @Transactional(readOnly = true)
    public List<UserDto> getUsersList() {
        return userRepository.findAll().stream().map(UserEntity::getDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getCurrentUserDetails() {
        Optional<UserEntity> optional = userRepository.findByUsernameIgnoreCase(Utils.getUsername());
        return optional.map(UserEntity::getDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public CurrentUser getCurrentUser() {
        UserEntity optional = userRepository.findByUsernameIgnoreCase(Utils.getUsername()).orElse(null);
        if (optional != null) {
            CurrentUser currentUser = new CurrentUser();
            currentUser.setName(optional.getFullName());
            currentUser.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
            currentUser.setUserid(optional.getId().toString());
            currentUser.setEmail(optional.getUsername());
            currentUser.setSignature("Tolerance is great");
            currentUser.setTitle("Interaction expert");
            currentUser.setGroup("Ant Financial Services-XX Business Group-XX Platform Department-XX Technology Department-UED");
            currentUser.setNotifyCount(3);
            currentUser.setUnreadCount(0);
            currentUser.setCountry("Uzbekistan");
            currentUser.setAccess("admin");
            currentUser.setAddress("Sergili, Uzar 63/31");
            currentUser.setPhone("97 724 67 47");
            return currentUser;
        }
        return null;
    }

    @Transactional
    public ModifiedResponse saveBackendUser(UserDto userDto) {
        ModifiedResponse response = new ModifiedResponse();
        response.setPayload(userDto);

        if (userDto == null || userDto.getUsername() == null) {
            response.setError(Boolean.TRUE);
            response.setMessage("Username cannot be empty");
            return response;
        }
        if (userDto.getId() == null && userRepository.findByUsernameIgnoreCase(userDto.getUsername()).isPresent()) {
            response.setError(Boolean.TRUE);
            response.setMessage("Username must be unique");
            return response;
        }


        UserEntity userEntity = new UserEntity();

        // UPDATE
        if (userDto.getId() != null) {
            Optional<UserEntity> optional = userRepository.findById(userDto.getId());
            if (optional.isPresent()) {
                userEntity = optional.get();
                userEntity.setFullName(userDto.getFullName());
            }
        }
        // CREATE
        else {
            BeanUtils.copyProperties(userDto, userEntity, "isActive");

            if (userDto.getRole() == null) userEntity.setRole(Role.MANAGER);
        }

        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);

        response.setError(Boolean.FALSE);
        response.setMessage("User: " + userEntity.getUsername() + " successfully created");
        return response;
    }


    @Transactional
    public void deleteUserById(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        byId.ifPresent(userRepository::delete);
    }

}
