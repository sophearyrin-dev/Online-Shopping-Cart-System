package miu.edu.cs489.shopping.onlineshoppingcart.dto.userauth;

import jakarta.validation.constraints.NotBlank;

/**
 * Step9: Add user
 */
public record UserAuthRequest(
        @NotBlank(message = "Username cannot be null, empty or blankspace(s)")
        String username,
        @NotBlank(message = "Password cannot be null, empty or blankspace(s)")
//        @Min(value = 8, message = "Must be at least 8 characters")
        String password
) {

}
