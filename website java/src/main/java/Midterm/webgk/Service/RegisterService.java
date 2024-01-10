package Midterm.webgk.Service;
import Midterm.webgk.Model.User;
import Midterm.webgk.Repository.LoginRepository;
import Midterm.webgk.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    RegisterRepository registerRepository ;
    public boolean checkreg(String username, String pass,String role,String useremail){
       User user = registerRepository.checkUser(useremail);
       if(user!=null){
           return false;
       }
       else{
           registerRepository.save(new User(username,pass,useremail,role));
       }
        return true;
    }
}
