package my.library.service;

import my.library.dao.*;
import my.library.entity.Book;
import my.library.entity.Order;
import my.library.entity.OrderStatus;
import my.library.entity.User;
import my.library.util.SqlDate;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final String STATUS_ORDER_NEW = "New";
    private final String STATUS_ORDER_TAKEN = "Taken";
    private final String STATUS_ORDER_COMPLETED = "Completed";

    public void addOrder(Order order) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
                OrderStatusImplDao orderStatusImplDao = daoFactory.getOrderStatusDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                OrderStatus orderStatus = orderStatusImplDao.findOrderStatusByName(STATUS_ORDER_NEW);
                order.setStatus(orderStatus);
                order.setStartDate(SqlDate.currentDateAndTime());
                daoFactory.startTransaction();
                orderImplDao.insert(order);
                bookInfoImplDao.updateAmount(order.getBooks());
                daoFactory.commitTransaction();
            } catch (Exception e) {
                daoFactory.rollbackTransaction();
                e.printStackTrace();
            }
        }
    }

    public List<Order> showUserOrders(User user) throws Exception{
        List<Order> orders;
        try (DaoFactory daoFactory = new DaoFactory()){
            OrderImplDao orderImplDao = daoFactory.getOrderDao();
            BookImplDao bookImplDao = daoFactory.getBookDao();
            OrderStatusImplDao orderStatusImplDao = daoFactory.getOrderStatusDao();
            orders = orderImplDao.orderByUser(user);
            for(Order zakaz:orders){
                List<Book> books = new ArrayList<>();
                OrderStatus orderStatus = orderStatusImplDao.findById(Integer.parseInt(zakaz.getStatus().getName()));
                for(Book book:zakaz.getBooks()){
                    Book kniga;
                    kniga = bookImplDao.findById(book.getId());
                    books.add(kniga);
                }
                zakaz.setStatus(orderStatus);
                zakaz.setBooks(books);
            }
        }
        return orders;
    }

    public List<Order> showAllOrders(User user) throws Exception {
        List<Order> orders = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
                BookImplDao bookImplDao = daoFactory.getBookDao();
                OrderStatusImplDao orderStatusImplDao = daoFactory.getOrderStatusDao();
                orders = orderImplDao.orderByUser(user);
                for(Order order:orders){
                    List<Book> knigi = new ArrayList<>();
                    OrderStatus orderStatus = orderStatusImplDao.findById(Integer.parseInt(order.getStatus().getName()));
                    order.setStatus(orderStatus);
                    for(Book book:order.getBooks()){
                        Book one = bookImplDao.findById(book.getId());
                        knigi.add(one);
                    }
                    order.setBooks(knigi);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return orders;
    }

    public Order ChangeOrder(int id, int status) throws Exception {
        Order order = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
            order = orderImplDao.findById(id);
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(status);
            order.setStatus(orderStatus);
            orderImplDao.update(order);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
return order;
    }

    public void deleteOrder(int id) {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                Order order = new Order();
                order.setId(id);
                List<Book>books = orderImplDao.takeBookByOrderId(id);
                daoFactory.startTransaction();
                orderImplDao.delete(order);
                orderImplDao.deleteOrder(order);
                bookInfoImplDao.returnAmount(books);
                daoFactory.commitTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
