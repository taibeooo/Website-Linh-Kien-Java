package Midterm.webgk.Controller;

import Midterm.webgk.Repository.LoginRepository;
import Midterm.webgk.Service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService ;
    @GetMapping("/login")
    public String login(){
        return "Login";
    }

    @PostMapping("/checklog")
    public RedirectView checklog(@RequestParam("uname") String uname, @RequestParam("psw") String pass, RedirectAttributes redirectAttributes) {
        boolean isAdmin = loginService.checkAdmin(uname); // Kiểm tra xem người dùng có phải là admin không
        if (loginService.checklogin(uname, pass)) {
            redirectAttributes.addAttribute("userid", uname);
            if (isAdmin) {
                return new RedirectView("/admin");
            } else {
                return new RedirectView("/homeuser/{userid}");
            }
        }
        return new RedirectView("/register");
    }
}
