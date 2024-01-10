package Midterm.webgk.Service;

import Midterm.webgk.Model.User;
import Midterm.webgk.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository ;
    public boolean checklogin(String useremail, String pass){
        try{
            User user = loginRepository.checkUser(useremail);
            if(user.getPassword().equals(pass)){
                return true;
            }
        }catch (Exception e){
            e.getMessage();
        }
        return false;
    }
    public boolean checkAdmin(String userEmail) {
        User admin = loginRepository.checkAdmin(userEmail);
        return admin != null;
    }
}
