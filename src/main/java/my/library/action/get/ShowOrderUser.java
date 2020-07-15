package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Order;
import my.library.entity.User;
import my.library.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static my.library.action.Constants.*;

public class ShowOrderUser implements Action {
    private static final Logger log = Logger.getLogger(ShowOrderUser.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute(ATT_USER_ID);
        OrderService orderService = new OrderService();
        User user = new User();
        user.setId(userId);
        List<Order>orders = null;
        try {
            orders = orderService.showUserOrders(user);
        } catch (Exception e) {
            log.info("can't show orders by user: " + e.getMessage());
        }

        req.setAttribute(ORDERS, orders);

        return new ActionResult(ORDER_PAGE);
    }
}
