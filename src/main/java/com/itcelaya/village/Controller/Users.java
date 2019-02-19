package com.itcelaya.village.Controller;

import com.itcelaya.village.Model.User;
import com.itcelaya.village.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class Users {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public void create(@RequestBody @Valid User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userName") String userName) {
        return userRepository.findByUsername(userName);
    }


}
