package myBlog;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
    public Blog findByid(long id);
    public Blog save(Blog blog);
    Page<Blog> findAll(Pageable pageable); 
}
