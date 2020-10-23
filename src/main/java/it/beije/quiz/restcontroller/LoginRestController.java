package it.beije.quiz.restcontroller;

import it.beije.quiz.model.User;
import it.beije.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class LoginRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/login/getUserByEmail/{email}/ok")
    public User emailExist(@PathVariable String email){
        System.out.println("Rest controller for user by email: " + email);
        return userService.retrieve(email);
    }

    @GetMapping("/login/getUser/{email}-{pw}")
    public User getUser(@PathVariable String email,
                              @PathVariable String pw){

        return userService.retrieve(email, pw);
    }
}