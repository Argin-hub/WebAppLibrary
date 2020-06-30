package my.library.service;

import my.library.dao.*;
import my.library.entity.Author;
import my.library.entity.Book;
import my.library.entity.BookInfo;
import my.library.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookService {

    public void createAuthor(Author author){
        try {
            try(DaoFactory daoFactory = new DaoFactory()) {
                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                authorDaoImpl.insert(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillBook(Book book) throws Exception {
        try {
            if (book != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {
                    AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                    GenreDaoImpl genreDaoImpl = daoFactory.getGenreDao();
                    book.setAuthor(authorDaoImpl.findByBook(book));
                    book.setGenre(genreDaoImpl.findByBook(book));
                }
            }
        } catch (Exception e) {
            throw new Exception("can't fill book", e);
        }
    }

    public List<Genre> getAllGenre() throws Exception {
        List<Genre> list;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                GenreDaoImpl genreDaoImpl = daoFactory.getGenreDao();
                list = genreDaoImpl.getAll();
                return list;
            }
        } catch (Exception e) {
            throw new Exception("can't get all genre", e);
        }
    }

    public List<Author> getAllAuthor() throws Exception {
        List<Author> list;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                list = authorDaoImpl.allAuthors();
                return list;
            }
        } catch (Exception e) {
            throw new Exception("can't get all genre", e);
        }
    }

    public List<BookInfo> getListBook(Genre genre, int start, int end) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();
                List<Book> list = bookImplDao.getLimitBookByGenre(genre, start, end);
                for (Book book : list) {
                    fillBook(book);
                }

                List<BookInfo> bookInfoList = new ArrayList<>();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                for (Book book : list) {
                    BookInfo bookInfo = bookInfoImplDao.findById(book.getId());
                    bookInfo.setBook(book);
                    bookInfoList.add(bookInfo);
                }
                return bookInfoList;
            } catch (Exception e) {
                throw new Exception("can't get list by genre book", e);
            }
        }
    }
public List<BookInfo> allBooksShow() throws Exception {
        List<BookInfo> bookInfos = new ArrayList<>();
        try(DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                List<Book> books = bookImplDao.getAllBooks();
                for (Book book :books) {
                    fillBook(book);
                }
                for(Book book:books){
                    BookInfo bookInfo;
                    bookInfo = bookInfoImplDao.findById(book.getId());

                    bookInfo.setBook(book);
                    bookInfos.add(bookInfo);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bookInfos;
}

    public int getBookCountByGenre(Genre genre) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();
                int count = bookImplDao.getBookCountByGenre(genre);
                return count;
            } catch (Exception e) {
                throw new Exception("can't get count book", e);
            }
        }
    }

    public void addBook(BookInfo bookInfo) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();

                daoFactory.startTransaction();
                bookImplDao.insert(bookInfo.getBook());
                bookInfoImplDao.insert(bookInfo);
                daoFactory.commitTransaction();
            } catch (Exception e) {
                daoFactory.rollbackTransaction();
                throw new Exception("can't register book", e);
            }
        }
    }

    public BookInfo findBookById(int id) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();

                Book book = bookImplDao.findById(id);
                fillBook(book);

                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                BookInfo bookInfo = bookInfoImplDao.findById(book.getId());
                bookInfo.setBook(book);
                return bookInfo;
            } catch (Exception e) {
                throw new Exception("can't get book by ID " + id);
            }

        }
    }

    public Author findAuthorById(int id) throws Exception {
        Author author = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
         author = authorDaoImpl.findById(id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return author;
    }

    public List<Book> poiskPoNazvaniu(String item) throws Exception {
        List<Book> books = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()){
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();
                books = bookImplDao.getAllBooks();
                Pattern p = Pattern.compile(item.trim() + "?");
                Pattern small_case = Pattern.compile(item.toLowerCase().trim()+"?");
                for(Book kniga:books){
                    Matcher m = p.matcher(kniga.getName());
                    Matcher small = small_case.matcher(kniga.getName().toLowerCase());
                    if (m.find() || small.find()){
                        Book book;
                        book = bookImplDao.findById(kniga.getId());
                        fillBook(book);
                        books2.add(book);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return books2;
    }

    public List<Author> poiskPoImeniAuthora(String item) throws Exception {
        List<Author> authors;
        List<Author> authors2 = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()){
            try {
                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                authors = authorDaoImpl.allAuthors();
                Pattern p = Pattern.compile(item.trim() + "?");
                Pattern small_case = Pattern.compile(item.toLowerCase().trim()+"?");
                for(Author poet:authors){
                    Matcher m = p.matcher(poet.getLastName());
                    Matcher small = small_case.matcher(poet.getLastName().toLowerCase());
                    if (m.find() || small.find()){
                        Author author;
                        author = authorDaoImpl.findById(poet.getId());
                        authors2.add(author);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return authors2;
    }

    public List<Book> poiskPoImeniAurhoraCKnigami(List<Author> authors){
        List<Book> books = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();
                books = bookImplDao.getBooksByAuthor(authors);
                for(Book book:books){
                    fillBook(book);
                }
        } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return books;
    }

    public void deleteBook(int id) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookImplDao bookImplDao = daoFactory.getBookDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                Book book = new Book();
                book.setId(id);
                BookInfo bookInfo = bookInfoImplDao.findByBook(book.getId());
                daoFactory.startTransaction();
               bookInfoImplDao.delete(bookInfo);
                bookImplDao.delete(book);
                daoFactory.commitTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addAuthor(Author author) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();

                daoFactory.startTransaction();
                authorDaoImpl.insert(author);
                daoFactory.commitTransaction();
            } catch (Exception e) {
                daoFactory.rollbackTransaction();
                throw new Exception("can't register book", e);
            }
        }
    }
}