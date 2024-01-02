package nta.bookstore.api.repository;

import nta.bookstore.api.common.enumtype.ECategory;
import nta.bookstore.api.entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findBookEntitiesByCategory(ECategory category);

    @Query("SELECT b " +
            "FROM BookEntity b " +
            "WHERE (:title IS NULL OR b.title LIKE %:title%) " +
            "AND (:author IS NULL OR b.author LIKE %:author%) " +
            "AND (:category IS NULL OR b.category = :category) " +
            "ORDER BY b.title ASC ")
    List<BookEntity> searchBooks(String title,
                                 String author,
                                 ECategory category,
                                 Pageable pageable);
}
