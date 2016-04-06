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
    private Timestamp registerDate;
    private String name;
    private String email;
    private String password;

    protected User() {}

    public User(/*long id,*/ Timestamp registerDate,String name, String email, String password) {
        //this.id = id;
        this.registerDate = registerDate;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public Timestamp getDate() {
        return registerDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
