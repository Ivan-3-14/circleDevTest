package application.controller;

import application.DTO.UserDTO;
import application.facade.interfaces.AuthorizationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller class for handling authentication related endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("rest/authorization")
public class AuthorizationController {

    /**
     * AuthorizationFacade interface
     *
     * @see application.facade.interfaces.AuthorizationFacade
     */
    private final AuthorizationFacade authorizationFacade;

    /**
     * Endpoint for user login.
     *
     * @param userDTO The UserDTO object containing login credentials.
     * @return ResponseEntity with logged-in user details.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO userDTO) {
        UserDTO respBody = authorizationFacade.loginUserDTO(userDTO);
        return ResponseEntity.ok().body(respBody);
    }

}
