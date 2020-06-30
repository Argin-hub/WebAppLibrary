package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Order;
import my.library.entity.User;
import my.library.service.OrderService;
import my.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static my.library.action.Constants.*;

public class OrderShowAllStatus implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter(READER_ID);
        List<Order> orders;
        UserService userService = new UserService();
        User user = userService.findUserById(Integer.parseInt(id));
        OrderService orderService = new OrderService();
        orders = orderService.showAllOrders(user);
        req.setAttribute(ORDERS, orders);
        for(Order dd:orders){
            req.setAttribute(ATT_BOOKS, dd.getBooks());
        }

        return new ActionResult(ORDERS_USER);
    }
}
