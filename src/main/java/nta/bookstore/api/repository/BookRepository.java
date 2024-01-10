package nta.bookstore.api.repository;

import nta.bookstore.api.common.enumtype.ECategory;
import nta.bookstore.api.common.enumtype.EStatus;
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
            "WHERE (:keyword IS NULL OR b.title LIKE %:keyword% OR b.author LIKE %:keyword%) " +
            "AND (:category IS NULL OR b.category = :category) " +
            "AND b.isActive = :status " +
            "ORDER BY b.title ASC ")
    Page<BookEntity> searchBooks(String keyword,
                                 ECategory category,
                                 Pageable pageable,
                                 boolean status);
}
