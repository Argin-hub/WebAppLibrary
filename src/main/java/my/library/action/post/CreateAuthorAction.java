package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Author;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static my.library.action.Constants.*;
import static my.library.action.Constants.TRUE;

public class CreateAuthorAction implements Action {
    private boolean wrong = false;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Properties properties = new Properties();
        properties.load(CreateAuthorAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));

        String fir_name = req.getParameter(FIRST_NAME);
        String last_name = req.getParameter(LAST_NAME);
        String mid_name = req.getParameter(MIDDLE_NAME);

        checkParamValid(FIRST_NAME, fir_name, properties.getProperty(NAME_VALID), req);
        checkParamValid(LAST_NAME, last_name, properties.getProperty(NAME_VALID), req);
        checkParamValid(MIDDLE_NAME, mid_name, properties.getProperty(NAME_VALID), req);

        if (wrong) {
            wrong = false;
            return new ActionResult(ADD_AUTHOR);
        }

        BookService bookService = new BookService();
        Author author = new Author();
        author.setFirstName(fir_name);
        author.setLastName(last_name);
        author.setMiddleName(mid_name);
        bookService.createAuthor(author);

        return new ActionResult(req.getHeader(REFERER), true);
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }
}
