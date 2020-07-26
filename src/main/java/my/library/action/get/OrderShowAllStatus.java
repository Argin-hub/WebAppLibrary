package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.controller.ControllerServlet;
import my.library.entity.Order;
import my.library.entity.User;
import my.library.service.OrderService;
import my.library.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static my.library.action.Constants.*;

public class OrderShowAllStatus implements Action {
    private static final Logger log = Logger.getLogger(ControllerServlet.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String idUser = req.getParameter(READER_ID);
        UserService userService = new UserService();
        User user = null;
        try {
            user = userService.findUserById(Integer.parseInt(idUser));
        } catch (Exception e) {
            log.info("can't find user by id: " + e.getMessage());
        }
        OrderService orderService = new OrderService();

        try {
            List<Order> orders = orderService.showAllOrders(user);
            req.setAttribute(ORDERS, orders);
            if (orders != null) {
                for (Order order : orders) {
                    req.setAttribute(ATT_BOOKS, order.getBooks());
                }
            }
        } catch (Exception e) {
            log.info("can't show all orders by user: " + e.getMessage());
        }

        return new ActionResult(ORDERS_USER);
    }
}
