package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.ID_ORDER;
import static my.library.action.Constants.REFERER;

public class ChangeStatusCompleted implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id_order = Integer.parseInt(req.getParameter(ID_ORDER));
        OrderService orderService = new OrderService();
        orderService.ChangeOrderCom(id_order, 3);

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
