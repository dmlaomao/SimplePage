package myBlog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
    public Comment findByBlogid(int blogid);
    public Comment save(Comment comment);
}
