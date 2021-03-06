package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Book;
import my.library.entity.BookInfo;
import my.library.entity.Order;
import my.library.entity.User;
import my.library.service.BookService;
import my.library.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static my.library.action.Constants.*;

public class CreateOrderAction implements Action {
    private static final Logger log = Logger.getLogger(CreateOrderAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        HashSet<Integer> basketList;
        if (session.getAttribute(BASKET_LIST) != null) {
            basketList = (HashSet<Integer>) session.getAttribute(BASKET_LIST);
        } else {
            req.setAttribute(BASKET_EMPTY, TRUE);
            return new ActionResult(BASKET);
        }

        int userId;
        if (session.getAttribute(ATT_USER_ID) != null) {
            userId = (int) session.getAttribute(ATT_USER_ID);
        } else {
            req.setAttribute(NOT_AUTH, TRUE);
            return new ActionResult(WELCOME);
        }

        BookService bookService = new BookService();
        List<Book> books = new ArrayList<>();
        for (Integer bookId : basketList) {
            BookInfo bookInfo = null;
            try {
                bookInfo = bookService.findBookById(bookId);

            } catch (Exception e) {
                log.info("can't find book by id: " + e.getMessage());
            }
            if (bookInfo != null) {
                if (bookInfo.getAmount() <= 0) {
                    req.setAttribute(BOOK_NOT_AVAILABLE, TRUE);
                    return new ActionResult(BASKET);
                } else {
                    books.add(bookInfo.getBook());
                }
            }
        }

        OrderService orderService = new OrderService();
        Order order = new Order();
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        order.setBooks(books);

        try {
            orderService.addOrder(order);
            session.removeAttribute(BASKET_BOOKS_LIST);
            session.removeAttribute(BASKET_SIZE);
            session.removeAttribute(BASKET_LIST);
            session.removeAttribute(ONE_BOOK_ONLY);
        } catch (Exception e) {
            log.info("Ошибка при создании страницы CreateOrderAction " + e.getMessage());
        }

        return new ActionResult(MAIN);
    }
}
