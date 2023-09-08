package com.vue.vuebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vue.vuebackend.repository.UserRepository;
import org.json.JSONObject;

import com.vue.vuebackend.entity.User;

@RestController
@RequestMapping("/newUserRegistration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String registerUser(@RequestBody String body) {
        System.out.println(body);
        JSONObject jsonObject = new JSONObject(body);
        User existingUser = userRepository.findByEmail(jsonObject.getString("email")).get();
        System.out.println(existingUser);
        if(existingUser != null){
            return "duplicate";
        }
        User user = new User();
        user.setEmail(jsonObject.getString("email"));
        user.setPassword(jsonObject.getString("password"));

        userRepository.save(user);

        return "registered";
    }
}
