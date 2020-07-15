package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Author;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;
import static my.library.validator.RegistrValidation.validateNameRegex;

public class CreateAuthorAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        String middleName = req.getParameter(MIDDLE_NAME);

        if (!validateNameRegex(firstName)) {
            req.setAttribute(FIRST_NAME_ERROR, TRUE);
            return new ActionResult(ADD_AUTHOR);
        }
        if (!validateNameRegex(lastName)) {
            req.setAttribute(LAST_NAME_ERROR, TRUE);
            return new ActionResult(ADD_AUTHOR);
        }
        if (!validateNameRegex(middleName)) {
            req.setAttribute(MIDDLE_NAME_ERROR, TRUE);
            return new ActionResult(ADD_AUTHOR);
        }

        BookService bookService = new BookService();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setMiddleName(middleName);
        bookService.createAuthor(author);

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
