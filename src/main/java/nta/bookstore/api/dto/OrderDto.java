package nta.bookstore.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.EOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {
    @Getter
    @Setter
    public static class Overview {
        private Long userId;
        private Long orderId;
        private String email;
        private String fullName;
        private String phone;
        private String address;
        private EOrderStatus orderStatus;
        private Long totalValue;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    public static class Detail {
        private Long userId;
        private Long orderId;
        private String email;
        private String fullName;
        private String phone;
        private String address;
        private EOrderStatus orderStatus;
        private Long totalValue;
        private List<OrderDetailDto> orderDetailDtos;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    public static class Create {
        private String email;
        private String fullName;
        private String phone;
        private String address;
    }

    @Getter
    @Setter
    public static class Update {
        EOrderStatus status;
    }
}
