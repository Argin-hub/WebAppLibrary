package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;

public class PageAddBookAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BookService bookService = new BookService();
        req.setAttribute(ATT_GENRES, bookService.getAllGenre());
        req.setAttribute(ATT_AUTHORS, bookService.getAllAuthor());
        return new ActionResult(ADD_BOOK);
    }
}
