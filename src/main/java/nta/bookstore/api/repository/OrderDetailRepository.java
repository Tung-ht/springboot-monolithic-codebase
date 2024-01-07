package nta.bookstore.api.repository;

import nta.bookstore.api.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    @Query("SELECT odt FROM OrderDetailEntity odt " +
            "WHERE odt.order.id = :orderId ")
    List<OrderDetailEntity> findAllByOrderId(Long orderId);
}
