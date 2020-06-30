package my.library.action;

public final class Constants {
    //pages
    public static final String REGISTER = "register";
    public static final String BOOKS = "books";
    public static final String WELCOME = "welcome";
    public static final String MAIN = "main";
    public static final String READERS = "readers";
    public static final String ABOUT_READER = "aboutReader";
    public static final String BASKET = "basket";
    public static final String ORDERS_USER = "orders_user";
    public static final String FORUM = "forum";
    public static final String ORDER_PAGE = "order_page";
    public static final String COMMENT = "comment";
    public static final String FOUND_BOOKS = "found_books";
    //Validation regex
    public static final String VALIDATION_PROPERTIES = "validation.properties";
    public static final String NAME_VALID = "name.valid.regexp";
    public static final String LIMIT_NUMBER_VALID = "limit.number.valid.regexp";
    public static final String DATE_VALID = "date.valid.regexp";
    public static final String PASSWORD_VALID = "password.valid.regexp";
    public static final String EMAIL_VALID = "email.valid.regexp";
    public static final String ISBN_VALID = "book.isbn.valid.regexp";
    public static final String DESCRIPTION_VALID = "book.description.valid.regexp";
    public static final String BOOK_NAME_VALID = "book.name.valid.regexp";
    public static final String BOOK_AMOUNT_VALID = "book.amount.valid.regexp";

    //RegisterAction constants
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONFIRM = "password_confirm";
    public static final String PHONE = "phone";
    public static final String BIRTHDAY = "birthday";
    public static final String EMAIL_ERROR = "email_error";
    public static final String PASSWORD_ERROR = "password_error";
    public static final String TRUE = "true";
    public static final String ERROR = "_error";

    //LoginAction constants
    public static final String LOGIN = "login";
    public static final String LOGIN_ERROR = "login_error";
    public static final String USER_ROLE_NAME = "user";
    public static final String ADMIN_ROLE_NAME = "admin";

    //Session constants
    public static final String ATT_USER_ID = "userId";
    public static final String ATT_ROLE = "role";
    public static final String ATT_ROLE_ID = "role_id";
    public static final String ATT_NAME = "name";

    //View constants
    public static final String PATH_TO_JSP = "/WEB-INF/jsp/";
    public static final String JSP_FORMAT = ".jsp";

    //PageReaders constants
    public static final String ATT_READERS = "readers";
    public static final String PAGE = "page";
    public static final String ATT_NO_PAGES = "noOfPages";
    public static final String ATT_CURRENT_PAGE = "currentPage";

    //PageAboutReader constants
    public static final String READER_ID = "user_id";
    public static final String ATT_USER_INFO = "user_info";
    public static final String DELETE_ID = "delete_id";
    public static final String ADMIN = "admin";

    //PageBook constants
    public static final String GENRE_ID = "genre_id";
    public static final String ATT_BOOKS = "books";
    public static final String ATT_GENRES = "genres";
    public static final String ATT_AUTHORS = "authors";
    public static final double CONVERT_TO_DOUBLE = 1.0;

    //AddBookAction
    public static final String ISBN = "isbn";
    public static final String DESCRIPTION = "description";
    public static final String BOOK_NAME = "book_name";
    public static final String YEAR = "year";
    public static final String GENRE_NAME = "genre_name";
    public static final String ISBN_ERROR = "isbn_error";
    public static final String ADD_BOOK = "addBook";
    public static final String AMOUNT = "amount";
    public static final String AUTHOR_ID = "author_id";

    //SelectLanguageAction
    public static final String LANG = "lang";
    public static final String REFERER = "referer";
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final int HOUR = 24;
    public static final int MINUTE = 60;
    public static final int SEC = 60;

    //Basket&Order
    public static final String ID_BOOK = "id_book";
    public static final String BASKET_LIST = "basket_list";
    public static final String ADD_TO_BASKET_SUCCESS = "add_to_basket_success";
    public static final String BASKET_SIZE = "basket_size";
    public static final String BASKET_EMPTY = "basket_empty";
    public static final String BASKET_BOOKS_LIST = "basket_books_list";
    public static final String NOT_AUTH = "not_auth";
    public static final String BOOK_NOT_AVAILABLE = "book_not_available";

    //OrdersPage
    public static final String ATT_ORDER_LIST = "orders_list";
    public static final String ORDER_ACTION = "order_action";
    public static final String ORDER_ID = "order_id";
    public static final String STATUS_ORDER_NEW = "New";
    public static final String STATUS_ORDER_TAKEN = "Taken";
    public static final String STATUS_ORDER_COMPLETED = "Completed";
    public static final String STATUS_ORDER_DELETE = "Delete";

    public static final String ORDERS = "orders";
    public static final String TITTLES = "tittles";
    public static final String TITTLE_ID = "tittle_id";
    public static final String FORUM_ID = "id_forum";
    public static final String COMMENTS = "comments";
    public static final String TITTLE = "tittle";
    public static final String OPINION = "opinion";

    public static final String SEARCHER = "searcher";
    public static final String FIND_BOOKS = "findBooks";

    public Constants() {
    }
}
