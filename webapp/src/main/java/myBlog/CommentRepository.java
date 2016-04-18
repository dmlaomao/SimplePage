package myBlog;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
    public List<Comment> findByBlogid(int blogid);
    public Comment save(Comment comment);
}
