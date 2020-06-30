package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Author;
import my.library.entity.Book;
import my.library.entity.BookInfo;
import my.library.entity.Genre;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static my.library.action.Constants.*;
import static my.library.action.Constants.AUTHOR_3;
import static my.library.util.SqlDate.stringToDate;

public class CreateBookAction implements Action {
    private boolean wrong = false;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Properties properties = new Properties();
        properties.load(CreateBookAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));

        BookService bookService = new BookService();
        req.setAttribute(ATT_GENRES, bookService.getAllGenre());
        String isbn = req.getParameter(ISBN);
        String description = req.getParameter(DESCRIPTION);
        String name = req.getParameter(BOOK_NAME);
        String year = req.getParameter(YEAR);
        String genreName = req.getParameter(GENRE_NAME);
        String amount = req.getParameter(AMOUNT);
        List<Author> authorsList;

        int author1 = Integer.parseInt(req.getParameter(AUTHOR_1));
        int author2 = Integer.parseInt(req.getParameter(AUTHOR_2));
        int author3 = Integer.parseInt(req.getParameter(AUTHOR_3));
        List<Integer> authors = new ArrayList<>();
        authors.add(author1);

        if (author2 != 0) {
            authors.add(author2);
        }
        if (author3 != 0) {
            authors.add(author3);
        }

        authorsList = bookService.fillAuthors(authors);
        Book book = new Book();
        Genre genre = new Genre();
        genre.setId(Integer.parseInt(genreName));
        BookInfo bookInfo = new BookInfo();
        book.setDescription(description);
        book.setDate(stringToDate(year));
        book.setIsbn(isbn);
        book.setName(name);
        book.setGenre(genre);
        book.setAuthorList(authorsList);
        bookInfo.setBook(book);
        bookInfo.setAmount(Integer.parseInt(amount));

        checkParamValid(ISBN, isbn, properties.getProperty(ISBN_VALID), req);
        checkParamValid(DESCRIPTION, isbn, properties.getProperty(DESCRIPTION_VALID), req);
        checkParamValid(BOOK_NAME, name, properties.getProperty(BOOK_NAME_VALID), req);
        checkParamValid(YEAR, year, properties.getProperty(DATE_VALID), req);
        checkParamValid(AMOUNT, amount, properties.getProperty(BOOK_AMOUNT_VALID), req);

        if (wrong) {
            wrong = false;
            return new ActionResult(NEW_BOOK);
        } else {
            try {
                bookService.addBook(bookInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ActionResult(WELCOME);
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
