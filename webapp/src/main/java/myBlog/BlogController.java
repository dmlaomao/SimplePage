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

public class BlogController {

    //private static Dao dao = new Dao();
    //private static BlogRepository blogRepo = new BlogRepository();
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Autowired
    private BlogRepository blogRepo; 

    @RequestMapping("/blog")
    public String blog(@RequestParam(value="ID", defaultValue = "1") long id) {
        return gson.toJson(blogRepo.findByid(id));
        //return gson.toJson(BlogRepository.findByid(id));
    }

    @RequestMapping("/postblog")
    public String postblog(@RequestParam(value="title") String title, @RequestParam(value="content") String content, @RequestParam(value="token") String token) {
        try {
            Blog b = new Blog(new Timestamp(System.currentTimeMillis()), title, content, LogTemp.findByToken(token));
            blogRepo.save(b);
        } catch (Exception e) {
            return "false";
        }
        return "true";
    }

}
