package Midterm.webgk.Controller;

import Midterm.webgk.Service.LoginService;
import Midterm.webgk.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService ;
    @GetMapping("/register")
    public String login(){
        return "Register";
    }
    @PostMapping("/checkreg")
    public RedirectView checklog(@RequestParam("uname")String uname, @RequestParam("psw")String pass, @RequestParam("email")String email){
        
        if(registerService.checkreg(uname, pass,"customer",email)==true){
            return new RedirectView("/login") ;
        }
        return new RedirectView("/register");
    }
}
