package nta.bookstore.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatisticDTO {
    private long totalActiveUsers;
    private long totalBooks;
    private long totalSoldBooks;
    private long totalRemainingBooks;
    private long totalOrders;
    private Double totalExpenses;
    private Double totalRevenue;
    private Double totalProfit;
}
