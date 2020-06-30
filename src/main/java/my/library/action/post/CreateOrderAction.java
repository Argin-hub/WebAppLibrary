package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Book;
import my.library.entity.BookInfo;
import my.library.entity.Order;
import my.library.entity.User;
import my.library.service.BookService;
import my.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static my.library.action.Constants.*;

public class CreateOrderAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        HttpSession session = req.getSession();

        HashSet<Integer> basketList;
        if (session.getAttribute(BASKET_LIST) != null) {
            basketList = (HashSet<Integer>) session.getAttribute(BASKET_LIST);
        }
        else {
            req.setAttribute(BASKET_EMPTY, TRUE);
            return new ActionResult(BASKET);
        }

        int userId;
        if (session.getAttribute(ATT_USER_ID) != null) {
            userId = (int) session.getAttribute(ATT_USER_ID);
        }
        else {
            req.setAttribute(NOT_AUTH, TRUE);
            return new ActionResult(WELCOME);
        }

        BookService bookService = new BookService();
        List<Book> books = new ArrayList<>();
        for (Integer bookId: basketList) {
            BookInfo bookInfo = bookService.findBookById(bookId);
            if (bookInfo.getAmount() <= 0) {
                req.setAttribute(BOOK_NOT_AVAILABLE, TRUE);
                return new ActionResult(BASKET);
            }
            else {
                books.add(bookInfo.getBook());
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ActionResult(MAIN, true);
    }
}
