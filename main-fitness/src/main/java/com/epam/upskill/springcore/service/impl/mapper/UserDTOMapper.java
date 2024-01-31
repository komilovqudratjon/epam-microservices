package com.epam.upskill.authenticationservice.service.impl.mapper;


import com.epam.upskill.authenticationservice.model.Users;
import com.epam.upskill.authenticationservice.model.dtos.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @description: Mapper class for UserDTO.
 * @date: 09 November 2023 $
 * @time: 3:37 PM 25 $
 * @author: Qudratjon Komilov
 */
@Service
@AllArgsConstructor
public class UserDTOMapper implements Function<Users, UserDTO> {

    @Override
    public UserDTO apply(Users users) {
        return new UserDTO(
                users.getId(),
                users.getAddress(),
                users.getFirstName(),
                users.getLastName(),
                users.getUsername(),
                users.getDateOfBirth(),
                users.getIsActive()
        );
    }
}
