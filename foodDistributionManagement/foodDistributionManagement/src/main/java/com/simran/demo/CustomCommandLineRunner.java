package com.simran.demo;

import com.simran.demo.model.Role;
import com.simran.demo.model.User;
import com.simran.demo.repository.RoleRepository;
import com.simran.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail("simran@gmail.com"));
        if(user.isEmpty()) {

            Role role = new Role(1, "ROLE_USER");
            roleRepository.save(role);
            userRepository.save(new User(101, "simran", "simran@gmail.com", "1234", "manager", "8877642767", "BHU", 80000, Collections.singletonList(role)));

        }
    }
}
