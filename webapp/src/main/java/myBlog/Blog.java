package myBlog;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "Blog")
public class Blog {

    @Id
    @GeneratedValue        
    private long id;
    //the format?
    private Timestamp time;
    private String title;
    private String content;

    protected Blog() {}

    public Blog(long id, Timestamp time,String title, String content) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
