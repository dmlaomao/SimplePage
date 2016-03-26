package myBlog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.*;

@RestController
@EnableAutoConfiguration

public class BlogController {

    //private static Dao dao = new Dao();
    //private static BlogRepository blogRepo = new BlogRepository();
    @Autowired
    private BlogRepository blogRepo; 
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @RequestMapping("/blog")
    public String blog(@RequestParam(value="ID", defaultValue = "1") long id) {
        return gson.toJson(blogRepo.findByid(id));
        //return gson.toJson(BlogRepository.findByid(id));
    }
}
