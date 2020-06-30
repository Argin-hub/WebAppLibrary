package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.ID_BOOK;
import static my.library.action.Constants.REFERER;

public class DeleteBookAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int idBook = Integer.parseInt(req.getParameter(ID_BOOK));
        BookService bookService = new BookService();
        bookService.deleteBook(idBook);

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
