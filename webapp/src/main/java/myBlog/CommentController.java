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

public class CommentController {

    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Autowired
    private CommentRepository commentRepo; 

    @RequestMapping("/getComment")
    public String login(@RequestParam(value="blogid") int blogid) {
        //return all comments of the given blog
        return gson.toJson(commentRepo.findByBlogid(blogid));
    }

    @RequestMapping("/saveComment")
    public void register(@RequestParam(value="content") String content, @RequestParam(value="name") String name, @RequestParam(value="blogid") int id) {
        Comment c = new Comment(new Timestamp(System.currentTimeMillis()), content, name, blogid); 
        commentRepo.save(c);
    }

}
