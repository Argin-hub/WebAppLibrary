package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Author;
import my.library.entity.Book;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static my.library.action.Constants.*;

public class SearchAuthorBook implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String iskomoe = req.getParameter(SEARCHER);
        BookService bookService = new BookService();
        List<Author> authors = bookService.poiskPoImeniAuthora(iskomoe);
        List<Book>books = bookService.poiskPoImeniAurhoraCKnigami(authors);
        req.setAttribute(BOOKS, books);
        return new ActionResult(FOUND_BOOKS);
    }
}
