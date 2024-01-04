package nta.bookstore.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.EOrderStatus;

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
    }

    @Getter
    @Setter
    public static class Create {
        List<Long> cartItemIds;
    }

    @Getter
    @Setter
    public static class Update {
        EOrderStatus status;
    }
}
