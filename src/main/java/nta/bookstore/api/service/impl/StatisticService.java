package nta.bookstore.api.service.impl;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.DashboardStatisticDTO;
import nta.bookstore.api.entity.BookEntity;
import nta.bookstore.api.entity.OrderEntity;
import nta.bookstore.api.repository.BookRepository;
import nta.bookstore.api.repository.OrderRepository;
import nta.bookstore.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Transactional
    public DashboardStatisticDTO getShopStatistic() {
        DashboardStatisticDTO dashboardStatisticDTO = new DashboardStatisticDTO();
        dashboardStatisticDTO.setTotalActiveUsers(userRepository.countActiveUsers());

        List<BookEntity> activeBooks = bookRepository.findAllActiveBooks();
        dashboardStatisticDTO.setTotalBooks(activeBooks.size());
        dashboardStatisticDTO.setTotalSoldBooks(activeBooks.stream().mapToLong(BookEntity::getSoldQuantity).sum());
        dashboardStatisticDTO.setTotalRemainingBooks(activeBooks.stream().mapToLong(BookEntity::getRemainingQuantity).sum());
        dashboardStatisticDTO.setTotalExpenses(activeBooks.stream()
                .mapToDouble(book -> (double) (book.getSoldQuantity() + book.getRemainingQuantity()) * book.getImportingPrice())
                .sum()
        );

        List<OrderEntity> completedOrders = orderRepository.getAllCompletedOrders();
        dashboardStatisticDTO.setTotalOrders(completedOrders.size());
        dashboardStatisticDTO.setTotalRevenue(completedOrders.stream()
                .mapToDouble(order ->
                        order.getOrderDetailEntityList().stream()
                                .mapToDouble(orderDetail -> (double) orderDetail.getSellingPrice() * orderDetail.getQuantity())
                                .sum()
                ).sum());

        dashboardStatisticDTO.setTotalProfit(completedOrders.stream()
                .mapToDouble(order ->
                        order.getOrderDetailEntityList().stream()
                                .mapToDouble(orderDetail -> (orderDetail.getSellingPrice() - orderDetail.getImportingPrice()) * orderDetail.getQuantity())
                                .sum()
                ).sum());

        return dashboardStatisticDTO;
    }
}
