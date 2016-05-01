package myBlog;

import java.sql.Timestamp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.google.gson.*;

@RestController
@EnableAutoConfiguration

public class CommentController {

    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Autowired
    private CommentRepository commentRepo; 
    @Autowired
    private StringRedisTemplate redis; 

    @RequestMapping("/getComment")
    public String login(@RequestParam(value="blogid") int blogid) {
        //return all comments of the given blog
        //add redis here
        String strid = String.valueOf(blogid);
        //timer
        long bef = System.currentTimeMillis();
        String redisStr = redis.opsForValue().get(strid);
        long aft = System.currentTimeMillis();
        aft -= bef;
        if (redisStr == null) {
            bef = System.currentTimeMillis();

            redisStr = gson.toJson(commentRepo.findByBlogid(blogid));

            aft = System.currentTimeMillis();
            aft -= bef;

            redis.opsForValue().set(strid, redisStr);
            System.out.println("no hit" + " time " + aft);
        }
        else 
            System.out.println("hit!!" + " time " + aft);

        return redisStr;
    }

    @RequestMapping("/saveComment")
    public String register(@RequestParam(value="content") String content, @RequestParam(value="token") String token, @RequestParam(value="blogid") int blogid) {
        try {
            Comment c = new Comment(new Timestamp(System.currentTimeMillis()), content, LogTemp.findByToken(token), blogid); 
            commentRepo.save(c);
            //add redis here
            String redisStr = gson.toJson(commentRepo.findByBlogid(blogid));
            redis.opsForValue().set(String.valueOf(blogid), redisStr);
            System.out.println("cache update");

        } catch (Exception e) {
            return "false";
        }
        return "true";
    }

}
