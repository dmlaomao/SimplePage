package myBlog;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue        
    private long id;
    private Timestamp time;
    private String name;
    private String email;
    private String password;

    protected Blog() {}

    public Blog(long id, Timestamp time,String name, String email, String password) {
        this.id = id;
        this.time = time;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }
}
