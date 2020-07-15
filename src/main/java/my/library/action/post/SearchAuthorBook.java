package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Author;
import my.library.entity.Book;
import my.library.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static my.library.action.Constants.*;

public class SearchAuthorBook implements Action {
    private static final Logger log = Logger.getLogger(SearchAuthorBook.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)  {
        String finder = req.getParameter(SEARCHER);
        BookService bookService = new BookService();
        List<Author> authors = null;
        try {
            authors = bookService.searchByAuthorName(finder);
        } catch (Exception e) {
            log.info("can't show books by author: " + e.getMessage());
        }
        List<Book>books = bookService.searchByAuthorNameAndBookTittle(authors);

        req.setAttribute(BOOKS, books);

        return new ActionResult(FOUND_BOOKS);
    }
}
