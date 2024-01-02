package nta.bookstore.api.repository;

import nta.bookstore.api.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    @Query("SELECT i " +
            "FROM CartItemEntity i " +
            "WHERE i.user.id = :userId " +
            "ORDER BY i.createdAt DESC ")
    List<CartItemEntity> findAllByUserId(Long userId);

    @Query("SELECT i " +
            "FROM CartItemEntity i " +
            "WHERE i.user.id = :userId " +
            "AND i.book.id = :bookId")
    CartItemEntity findByUserIdAndBookId(Long userId, Long bookId);
}
