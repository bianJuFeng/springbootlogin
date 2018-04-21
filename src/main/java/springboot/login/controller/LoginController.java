package springboot.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.login.WebSecurityConfig;
import springboot.login.domain.User;
import springboot.login.service.LoginService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangds on 2017/10/24.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account,Model model){

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginVerify")
    public String loginVerify(String username,String password,HttpSession session){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean verify = loginService.verifyLogin(user);
        if (verify) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}
