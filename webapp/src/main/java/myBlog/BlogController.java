package myBlog;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.*;

@RestController
public class BlogController {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();
    private static Dao dao = new Dao();
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @RequestMapping("/blog")
    public String blog(@RequestParam(value="ID", defaultValue = "1") long id) {
        return gson.toJson(dao.getBlogByID(id));
    }
}
