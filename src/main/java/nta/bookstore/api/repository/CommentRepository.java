package nta.bookstore.api.repository;

import nta.bookstore.api.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT c " +
            "FROM CommentEntity c " +
            "WHERE c.book.id = :bookId " +
            "AND c.parent.id IS NULL " +
            "AND c.book.isActive = true " +
            "AND c.isActive = true ")
    List<CommentEntity> findByBookIdAndParentNull(Long bookId);
}
