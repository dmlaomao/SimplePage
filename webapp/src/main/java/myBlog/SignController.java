package myBlog;

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
                return "登录成功!";
            }
            else 
                return "密码不正确";
        }
        catch (Exception e) {
            return "账户不存在,请注册";
        }
    }

}
