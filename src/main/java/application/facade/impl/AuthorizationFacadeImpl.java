package application.facade.impl;

import application.DTO.UserDTO;
import application.mapper.UserMapper;
import application.facade.AuthorizationFacade;
import application.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements the AuthorFacade interface and contains method for login user.
 *
 * @see application.facade.AuthorizationFacade
 */
@RequiredArgsConstructor
@Component
public class AuthorizationFacadeImpl implements AuthorizationFacade {

    /**
     * Authorization interface.
     *
     * @see application.service.AuthorizationService
     */
    private final AuthorizationService authorizationService;

    /**
     * UserMapper interface.
     *
     * @see application.mapper.UserMapper
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
