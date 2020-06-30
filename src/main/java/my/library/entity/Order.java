package my.library.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity {
    private User user;
    private List<Book> books;
    private OrderStatus status;
    private Date startDate;
    private Date endDate;

    public Order() {
        user = new User();
        books = new ArrayList<>();
        status = new OrderStatus();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return getId() + " // " + user + " // " + books + " // " + status + " // " + startDate  + " // " + endDate;
    }
}
