package my.library.controller;


import my.library.action.manager.Action;
import my.library.action.manager.ActionFactory;
import my.library.action.manager.ActionResult;
import my.library.action.manager.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

public class ControllerServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ControllerServlet.class);

    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = actionFactory.getAction(req);
        log.info("Создан " + action.toString() + " объект по запросу " + req.getMethod() + req.getPathInfo());
        ActionResult result = null;
        try {
            result = action.execute(req, resp);
            log.info("Создан ActionResult: " + result.getView() + " по запросу " + req.getMethod() + req.getPathInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = new View(req, resp);
        log.info("Создан View по запросу "  + req.getMethod() + req.getPathInfo());
        view.navigate(result);
        log.info("Осуществлена навигация на View по ActionResult: "  + result.getView());
    }
}