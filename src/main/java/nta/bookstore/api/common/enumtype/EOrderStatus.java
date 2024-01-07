package nta.bookstore.api.common.enumtype;

public enum EOrderStatus {
    PENDING("Pending", "Đang chờ"),
    COMPLETED("Completed", "Hoàn thành"),
    REJECTED("Rejected", "Hủy đơn");

    public final String english;
    public final String vietnamese;

    EOrderStatus(String english, String vietnamese) {
        this.english = english;
        this.vietnamese = vietnamese;
    }
}
