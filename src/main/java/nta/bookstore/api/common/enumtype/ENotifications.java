package nta.bookstore.api.common.enumtype;

public enum ENotifications {
    COMMENT("%s đã trả lời 1 bình luận của bạn.", 1),
    ORDER_STATUS_UPDATED("Đơn hàng %s của bạn vừa được cập nhật.", 1),
    PRICE_CHANGE("Sách '%s' trong giỏ hàng của bạn đã thay đổi giá từ %s thành %s.", 3);

    public final String notificationTemplate;
    public final int paramCount;

    ENotifications(String notificationTemplate, int paramCount) {
        this.notificationTemplate = notificationTemplate;
        this.paramCount = paramCount;
    }

    public static String getNotificationMessage(ENotifications type, String... params) {
        if (params.length != type.paramCount) {
            throw new IllegalArgumentException(
                    String.format("ENotifications error: type %s requires %d parameters, but %d were provided",
                            type.name(), type.paramCount, params.length)
            );
        }
        return String.format(type.notificationTemplate, (Object[]) params);
    }
}
