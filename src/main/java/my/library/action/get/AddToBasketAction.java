package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashSet;

import static my.library.action.Constants.*;

public class AddToBasketAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        Integer bookId = Integer.parseInt(req.getParameter(ID_BOOK));
        HttpSession session = req.getSession();

        HashSet<Integer> basketList = new HashSet<>();
        if (session.getAttribute(BASKET_LIST) != null) {
            session.setAttribute(ONE_BOOK_ONLY, TRUE);
            return new ActionResult(BASKET, true);
        }
        else basketList.add(bookId);

        session.setAttribute(BASKET_LIST, basketList);
        session.setAttribute(BASKET_SIZE, basketList.size());

        return new ActionResult(BASKET, true);
    }
}
