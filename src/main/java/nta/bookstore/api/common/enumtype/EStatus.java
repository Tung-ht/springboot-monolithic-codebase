package nta.bookstore.api.common.enumtype;

public enum EStatus {
    INACTIVE(false),
    ACTIVE(true);

    public final boolean value;

    EStatus(boolean value) {
        this.value = value;
    }
}
