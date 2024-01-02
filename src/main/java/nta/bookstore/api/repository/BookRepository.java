package nta.bookstore.api.repository;

import nta.bookstore.api.common.enumtype.ECategory;
import nta.bookstore.api.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("SELECT b " +
            "FROM BookEntity b " +
            "WHERE (:title IS NULL OR b.title LIKE %:title%) " +
            "AND (:author IS NULL OR b.author LIKE %:author%) " +
            "AND (:category IS NULL OR b.category = :category) " +
            "ORDER BY b.title ASC ")
    Page<BookEntity> searchBooks(String title,
                                 String author,
                                 ECategory category,
                                 Pageable pageable);
}
