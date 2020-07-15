package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.controller.ControllerServlet;
import my.library.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.ID_BOOK;
import static my.library.action.Constants.REFERER;

public class DeleteBookAction implements Action {
    private static final Logger log = Logger.getLogger(ControllerServlet.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int idBook = Integer.parseInt(req.getParameter(ID_BOOK));
        BookService bookService = new BookService();
        try {
            bookService.deleteBook(idBook);
        } catch (Exception e) {
            log.info("can't delete book: " + e.getMessage());
        }

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
