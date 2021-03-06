package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.ID_ORDER;
import static my.library.action.Constants.REFERER;

public class ChangeStatusTaken implements Action {
    private static final Logger log = Logger.getLogger(ChangeStatusTaken.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int idOrder = Integer.parseInt(req.getParameter(ID_ORDER));
        OrderService orderService = new OrderService();

        try {
            orderService.changeOrder(idOrder, 2);
        } catch (Exception e) {
            log.info("can't change order status on taken: " + e.getMessage());
        }

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
