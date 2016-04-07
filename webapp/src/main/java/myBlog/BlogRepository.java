package myBlog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
    public Blog findByid(long id);
    public Blog save(Blog blog);
}
