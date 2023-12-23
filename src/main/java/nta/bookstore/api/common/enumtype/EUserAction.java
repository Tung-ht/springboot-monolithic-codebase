package nta.bookstore.api.common.enumtype;

public enum EUserAction {
    RESET_PASSWORD("forgot-pw-otp", "nta-bookstore: Yêu cầu đổi mật khẩu"),
    VERIFY_EMAIL("registration-otp", "nta-bookstore: Xác nhận email đăng kí");

    public final String emailTemplate;
    public final String emailSubject;

    EUserAction(String emailTemplate, String emailSubject) {
        this.emailTemplate = emailTemplate;
        this.emailSubject = emailSubject;
    }
}
