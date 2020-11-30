package com.quan.loginservice.controller;

import com.quan.loginservice.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/userLogin")
    public Boolean userLogin(@RequestBody User user){
        return true;
    }
}
