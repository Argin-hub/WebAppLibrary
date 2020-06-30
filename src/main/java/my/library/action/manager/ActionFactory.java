package my.library.action.manager;

import my.library.action.get.*;
import my.library.action.post.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private Map<String, Action> actions;

    public void init() {
        actions = new HashMap<>();

        actions.put("GET/welcome", new ShowPageAction("welcome"));
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("GET/main", new ShowPageAction("main"));
        actions.put("GET/select-language", new SelectLanguageAction());

        actions.put("GET/readers", new PageReadersAction());
        actions.put("GET/aboutReader", new PageAboutReaderAction());

        actions.put("GET/books", new PageBooksAction());
        actions.put("GET/addBook", new PageAddBookAction());
        actions.put("GET/logout", new LogOutAction());
        actions.put("GET/aboutOrder", new OrderShowAllStatus());

        actions.put("GET/basket", new PageBasketAction());

        actions.put("GET/removeBook", new DeleteBookAction());

        actions.put("GET/taken", new ChangeStatusTaken());
        actions.put("GET/completed", new ChangeStatusCompleted());
        actions.put("GET/removeOrder", new DeleteOrderAction());
        actions.put("GET/orderUser", new ShowOrderUser());

        actions.put("GET/forumShow", new ShowForumPage());
        actions.put("GET/add_comment", new TopicShowAction());
        actions.put("GET/delete_comment", new DeleteCommentAction());

        actions.put("POST/login", new LoginAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/deleteProfile", new DeleteProfileAction());

        actions.put("POST/insertBook", new AddBookAction());
        actions.put("POST/tittleForum", new AddTittleAction());
        actions.put("POST/commentUser", new CommentAddUser());

        actions.put("POST/searchTitle", new SearchTittleBook());
        actions.put("POST/searchAuthor", new SearchAuthorBook());

        actions.put("POST/createOrder", new CreateOrderAction());
        actions.put("GET/addToBasket", new AddToBasketAction());
    }

    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            init();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
