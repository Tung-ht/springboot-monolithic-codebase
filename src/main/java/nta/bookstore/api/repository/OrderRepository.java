package nta.bookstore.api.repository;

import nta.bookstore.api.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT od FROM OrderEntity od " +
            "WHERE od.user.id = :userId ")
    List<OrderEntity> findAllByUserId(Long userId);


    @Query("SELECT od FROM OrderEntity od ORDER BY od.createdAt DESC ")
    List<OrderEntity> findAllAndOrderByCreatedAt();
}
