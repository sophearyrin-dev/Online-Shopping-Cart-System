package miu.edu.cs489.shopping.onlineshoppingcart;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import miu.edu.cs489.shopping.onlineshoppingcart.model.User;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.RoleRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Role;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Online Shopping Cart API", version = "3.0", description = "Online Shopping Cart System"))
@SecurityScheme(name = "onlineshopapi", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OnlineshoppingcartApplication {

    private UserRepository userRepository;


    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public OnlineshoppingcartApplication(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineshoppingcartApplication.class, args);
    }

    @PostConstruct
    public void initAdminUser() {
        var adminUser = userRepository.findUserByUsername("admin");
        if(adminUser.isEmpty()) {
            List<Role> listAdminRoles = new ArrayList<>();
            var adminRole = roleRepository.findRoleByName("ROLE_ADMIN");
            if(adminRole.isEmpty()) {
                var newAdminRole =  new Role(null, "ROLE_ADMIN");
                listAdminRoles.add(newAdminRole);
            } else {
                listAdminRoles.add(adminRole.get());
            }
            User newAdminUser = new User(null, "Admin", "Admin", "Admin", "admin",
                    passwordEncoder.encode("test1234"), "admin@shop.com",
                    true,true, true, true);
            newAdminUser.setRoles(listAdminRoles);
            userRepository.save(newAdminUser);
        }
    }

    @PostConstruct
    public void initCustomerUser() {
        var user = userRepository.findUserByUsername("user");
        if(user.isEmpty()) {
            List<Role> listAdminRoles = new ArrayList<>();
            var adminRole = roleRepository.findRoleByName("ROLE_USER");
            if(adminRole.isEmpty()) {
                var newAdminRole =  new Role(null, "ROLE_USER");
                listAdminRoles.add(newAdminRole);
            } else {
                listAdminRoles.add(adminRole.get());
            }
            User newAdminUser = new User(null, "user", "user", "user", "user",
                    passwordEncoder.encode("test1234"), "user@shop.com",
                    true,true, true, true);
            newAdminUser.setRoles(listAdminRoles);
            userRepository.save(newAdminUser);
        }
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println("Hello RESTful Web API");
            System.out.println("Advantis WebAPI server. Starting...");
            System.out.println("Advantis WebAPI server. Started.\nRunning Apache Tomcat service and Listening for HTTP Request on Port number, 8080");
            System.out.println("To see list of Publishers, send HTTP GET Request to the URI,\nhttp://localhost:8080/adsweb/api/v1/address/list");
        };
    }

}
