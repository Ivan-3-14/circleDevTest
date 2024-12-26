package application.facade.impl;

import application.DTO.UserDTO;
import application.conventer.UserMapper;
import application.facade.interfaces.AuthorizationFacade;
import application.service.interfaces.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthorizationFacadeImpl implements AuthorizationFacade {

    /**
     * Authorization interface
     *
     * @see application.service.interfaces.AuthorizationService
     */
    private final AuthorizationService authorizationService;

    /**
     * UserMapper interface
     *
     * @see application.conventer.UserMapper
     */
    private final UserMapper userMapper;

    /**
     * Authenticates user.
     *
     * @param userDTO The UserDTO object containing user login credentials.
     * @return UserDTO.
     * @see application.DTO.UserDTO
     */
    @Override
    public UserDTO loginUserDTO(UserDTO userDTO) {
        return userMapper.toDTO(authorizationService.loginUser(userMapper.toEntity(userDTO)));
    }
}
