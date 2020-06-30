package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Book;
import my.library.entity.BookInfo;
import my.library.entity.Genre;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static my.library.action.Constants.*;


public class PageBooksAction implements Action {
    private int genreId = 0;
    private int genreState = 1;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookService bookService = new BookService();

        Genre genre = new Genre();
int page;
        int recordPerPage = 20;

        try {
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            } else page = 1;
            if (request.getParameter(GENRE_ID) != null) {
                genreId = Integer.parseInt(request.getParameter(GENRE_ID));
                genre.setId(genreId);
                genreState = genreId;
            } else {
                genre.setId(genreState);
            }

            List<BookInfo> books = bookService.getListBook(genre, page, recordPerPage);
            List<Genre> genres = bookService.getAllGenre();


            int noOfRecords = bookService.getBookCountByGenre(genre);
            int noOfPages = (int) Math.ceil(noOfRecords * CONVERT_TO_DOUBLE / recordPerPage);

            request.setAttribute(ATT_BOOKS, books);
            request.setAttribute(ATT_GENRES, genres);
            request.setAttribute(ATT_NO_PAGES, noOfPages);
            request.setAttribute(ATT_CURRENT_PAGE, page);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ActionResult(BOOKS);
    }


}
