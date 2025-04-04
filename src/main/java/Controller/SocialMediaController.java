package Controller;

import Model.Account;
import Model.Message;
import Service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    Service Service;

    public SocialMediaController() {
        this.Service = new Service();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::patchMessageHandler);
        app.get("accounts/{account_id}/messages", this::getAllMessagesFromUserHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesHandler(Context context) {
        List<Message> messages = Service.getAllMessages();
        context.json(messages);
        context.status(200);
    }

    private void registerHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account addedAccount = Service.addAccount(account);
        if(addedAccount!=null){
            context.json(om.writeValueAsString(addedAccount));
            context.status(200);
        }else{
            context.status(400);
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account login = Service.login(account);
        if(login!=null){
            context.json(om.writeValueAsString(login));
            context.status(200);
        }else{
            context.status(401);
        }
    }

    private void postMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        Message addedMessage = Service.addMessage(message);
        if(addedMessage!=null){
            context.json(om.writeValueAsString(addedMessage));
            context.status(200);
        }else{
            context.status(400);
        }
    }

    private void getMessageHandler(Context context) {
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        context.json(Service.getMessage(message_id));
        context.status(200);
    }

    private void deleteMessageHandler(Context context) {
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        context.json(Service.deleteMessage(message_id));
        context.status(200);
    }

    private void patchMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message updatedMessage = Service.updateMessage(message.message_text, message_id);
        if(updatedMessage == null){
            context.status(400);
        }else{
            context.json(om.writeValueAsString(updatedMessage));
            context.status(200);
        }
    }

    private void getAllMessagesFromUserHandler(Context context) {
        int account_id = Integer.parseInt(context.pathParam("account_id"));
        context.json(Service.getAllMessagesFromAccount(account_id));
        context.status(200);
    }

}