package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.controller.ControllerServlet;
import my.library.entity.Author;
import my.library.entity.Book;
import my.library.entity.BookInfo;
import my.library.entity.Genre;
import my.library.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


import static my.library.action.Constants.*;
import static my.library.util.SqlDate.stringToDate;
import static my.library.validator.BookAddValidation.*;
import static my.library.validator.RegistrValidation.validateDateRegex;

public class CreateBookAction implements Action {
    private static final Logger log = Logger.getLogger(ControllerServlet.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        BookService bookService = new BookService();
        try {
            req.setAttribute(ATT_GENRES, bookService.getAllGenre());
        } catch (Exception e) {
            log.info("can't show genres: " + e.getMessage());
        }
        String isbn = req.getParameter(ISBN);
        String description = req.getParameter(DESCRIPTION);
        String name = req.getParameter(BOOK_NAME);
        String year = req.getParameter(YEAR);
        String genreName = req.getParameter(GENRE_NAME);
        String amount = req.getParameter(AMOUNT);
        List<Author> authorsList = null;

        if(!validateAmountRegex(amount)){
            req.setAttribute(AMOUNT_ERROR, TRUE);
            return new ActionResult(NEW_BOOK);
        }
        if(!validateDescriptionRegex(description)){
            req.setAttribute(DESCRIPTION_ERROR, TRUE);
            return new ActionResult(NEW_BOOK);
        }
        if(!validateBookNameRegex(name)){
            req.setAttribute(BOOK_NAME_ERROR, TRUE);
            return new ActionResult(NEW_BOOK);
        }
        if(!validateIsbnRegex(isbn)){
            req.setAttribute(ISBN_ERROR, TRUE);
            return new ActionResult(NEW_BOOK);
        }
        if(!validateDateRegex(year)){
            req.setAttribute(YEAR_ERROR, TRUE);
            return new ActionResult(NEW_BOOK);
        }

        String author1 = req.getParameter(AUTHOR_1);
        String author2 = req.getParameter(AUTHOR_2);
        String author3 = req.getParameter(AUTHOR_3);
        List<Integer> authors = new ArrayList<>();

        if (author1 != null) {
            authors.add(Integer.parseInt(author1));
        }

        if (author2 != null) {
            authors.add(Integer.parseInt(author2));
        }
        if (author3 != null) {
            authors.add(Integer.parseInt(author3));
        }

        try {
            authorsList = bookService.fillAuthors(authors);
        } catch (Exception e) {
            log.info("can't fill authorsList: " + e.getMessage());
        }
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

            try {
                bookService.addBook(bookInfo);
            } catch (Exception e) {
                log.info("Ошибка при создании страницы CreateBookAction " + e.getMessage());
            }

        return new ActionResult(WELCOME);
    }
}
