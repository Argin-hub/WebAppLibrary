package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Book;
import my.library.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static my.library.action.Constants.*;

public class SearchTittleBook implements Action {
    private static final Logger log = Logger.getLogger(SearchTittleBook.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String finder = req.getParameter(SEARCHER);
        BookService bookService = new BookService();

        try {
            List<Book> books = bookService.searchByBookTittle(finder);
            req.setAttribute(FIND_BOOKS, books);
        } catch (Exception e) {
            log.info("can't show books by tittle: " + e.getMessage());
        }

        return new ActionResult(FOUND_BOOKS);
    }
}
