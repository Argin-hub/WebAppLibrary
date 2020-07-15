package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.controller.ControllerServlet;
import my.library.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;

public class PageAddBookAction implements Action {
    private static final Logger log = Logger.getLogger(ControllerServlet.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        BookService bookService = new BookService();

        try {
            req.setAttribute(ATT_GENRES, bookService.getAllGenre());
            req.setAttribute(ATT_AUTHORS, bookService.getAllAuthor());
        } catch (Exception e) {
            log.info("can't show genres or authors: " + e.getMessage());
        }

        return new ActionResult(NEW_BOOK);
    }
}
