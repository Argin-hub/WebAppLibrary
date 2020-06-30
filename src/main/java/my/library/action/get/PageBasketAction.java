package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.BookInfo;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static my.library.action.Constants.*;

public class PageBasketAction implements Action {
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

        BookService bookService = new BookService();
        List<BookInfo> books = new ArrayList<>();

        for (Integer bookId: basketList) {
            BookInfo bookInfo = bookService.findBookById(bookId);
            if (bookInfo.getAmount() <= 0) {
                req.setAttribute(BOOK_NOT_AVAILABLE, TRUE);
            }
            books.add(bookInfo);
        }
        req.setAttribute(BASKET_BOOKS_LIST, books);
        return new ActionResult(BASKET);
    }
}
