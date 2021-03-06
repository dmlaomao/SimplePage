package myBlog;

import java.sql.Timestamp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.google.gson.*;

@RestController
@EnableAutoConfiguration

public class BlogController {

    //private static Dao dao = new Dao();
    //private static BlogRepository blogRepo = new BlogRepository();
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Autowired
    private BlogRepository blogRepo; 

    @RequestMapping("/getPageNum")
    public int blog() {
        //return the total number of pages for pagantion
        return blogRepo.findAll(new PageRequest(0,10)).getTotalPages();
    }

    @RequestMapping("/blog")
    public String blog(@RequestParam(value="page", defaultValue = "0") int pageNum) {
        //the page infomation is lost here
        //only the content is returned
        return gson.toJson(blogRepo.findAll(new PageRequest(pageNum,10,new Sort(new Sort.Order(Direction.DESC,"time")))).getContent());
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
