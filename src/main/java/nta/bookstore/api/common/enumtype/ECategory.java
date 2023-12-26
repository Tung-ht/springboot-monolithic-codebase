package nta.bookstore.api.common.enumtype;

public enum ECategory {
    AUTOBIOGRAPHY("Autobiography", "Tự truyện"),
    BIOGRAPHY("Biography", "Tiểu sử"),
    CHILDREN("Children’s Book", "Sách thiếu nhi"),
    COOKBOOK("Cookbook", "Sách nấu ăn"),
    DICTIONARY("Dictionary", "Từ điển"),
    ENCYCLOPEDIA("Encyclopedia", "Bách khoa toàn thư"),
    FANTASY("Fantasy", "Sách huyền huyễn"),
    HISTORY("History", "Sách lịch sử"),
    HORROR("Horror", "Sách kinh dị"),
    MYSTERY("Mystery", "Sách trinh thám"),
    NOVEL("Novel", "Tiểu thuyết"),
    POETRY("Poetry", "Thơ"),
    ROMANCE("Romance", "Tiểu thuyết lãng mạn"),
    SCIENCE_FICTION("Science Fiction", "Khoa học viễn tưởng"),
    THRILLER("Thriller", "Sách ly kỳ"),
    TRAVEL("Travel", "Sách du lịch"),
    GRAPHIC("Graphic Novel", "Truyện tranh dài"),
    SELF_HELP("Self-help", "Self-help"),
    BUSINESS("Business", "Sách kinh doanh");

    public final String english;
    public final String vietnamese;

    ECategory(String english, String vietnamese) {
        this.english = english;
        this.vietnamese = vietnamese;
    }
}
