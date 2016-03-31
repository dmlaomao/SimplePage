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
    //private static BlogRepository blogRepo = new BlogRepository();
    @Autowired
    private SignRepository signRepo; 
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @RequestMapping("/login")
    public String login(@RequestParam(value="name") String name, @RequestParam(value="password") String password ) {
        try {
            User u = signRepo.findByName(name);
            return "test";
        }
        catch (IllegalArgumentException e) {
            //do something, in thinking

        }
        
    }
}
