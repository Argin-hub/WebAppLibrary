package my.library;

import my.library.dao.BookInfoImplDao;
import my.library.dao.DaoFactory;
import my.library.entity.BookInfo;
import my.library.service.BookService;

public class Main {
    public static void main(String[] args) throws Exception {
        BookService bookService = new BookService();
        DaoFactory daoFactory = new DaoFactory();
        BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
       // BookInfo bookInfo = bookService.findBookById(69);
        BookInfo bookInfo1 = bookInfoImplDao.findByBookAmount(69);

        System.out.println(bookInfo1.getAmount());
    }
}
