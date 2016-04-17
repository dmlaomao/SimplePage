package myBlog;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue        
    private long id;
    //the format?
    private String content;
    private Timestamp time;
    private String name;
    private int blogid;

    protected Comment() {}

    public Comment(/*long id,*/ Timestamp time,String content, String name, int blogid) {
        //this.id = id;
        this.time = time;
        this.content = content;
        this.name = name;
        this.blogid = blogid;
    }

    public long getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public int getBlogID() {
        return blogid;
    }
}
