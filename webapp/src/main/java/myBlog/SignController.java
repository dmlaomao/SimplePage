package myBlog;

import java.sql.Timestamp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.*;

@RestController
@EnableAutoConfiguration

public class SignController {

    //private static Dao dao = new Dao();
    @Autowired
    private UserRepository userRepo; 
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @RequestMapping("/login")
    public String login(@RequestParam(value="name") String name, @RequestParam(value="password") String password ) {
        try {
            User u = userRepo.findByName(name);
            if (u.getPassword().equals(password)) {
                String token = LogTemp.generateToken(u.getName());
                LogTemp.saveToken(token,u.getName());
                //System.out.println(LogTemp.chm.get(token));
                return "登录成功!"+" "+token+" "+System.currentTimeMillis();
            }
            else 
                return "密码不正确";
        }
        catch (Exception e) {
            return "账户不存在,请注册";
        }
    }

    @RequestMapping("/register")
    public String register(@RequestParam(value="name") String name, @RequestParam(value="password") String password, @RequestParam(value="email") String email) {
        try {
            User u = userRepo.findByName(name);
            if (u.getName().equals(name))
                return "帐号已存在"; 
        }
        catch (Exception e) {
           // do nothing 
        }
        User u = new User(new Timestamp(System.currentTimeMillis()), name, email, password); 
        userRepo.save(u);
        String token = LogTemp.generateToken(u.getName());
        LogTemp.saveToken(token,u.getName());
        return "注册成功!"+" "+token+" "+System.currentTimeMillis();
    }

}
