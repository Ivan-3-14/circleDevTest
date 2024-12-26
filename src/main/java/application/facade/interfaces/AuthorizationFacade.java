package application.facade.interfaces;

import application.DTO.UserDTO;

public interface AuthorizationFacade {

    /**
     * Authenticates user.
     *
     * @param userDTO The UserDTO object containing user login credentials.
     * @return UserDTO.
     * @see application.DTO.UserDTO
     */
    UserDTO loginUserDTO(UserDTO userDTO);

}
