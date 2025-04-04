package Service;

import DAO.DAO;
import Model.Account;
import Model.Message;

import java.util.List;
public class Service {
    public DAO DAO;

    public Service() {
        DAO = new DAO();
    }

    public Service(DAO DAO) {
        this.DAO = DAO;
    }

    public List<Message> getAllMessages() {
        return this.DAO.getAllMessages();
    }

    public Account addAccount(Account account) {
        if (!(account.username.isBlank())
        && this.DAO.getAccount(account.username) == null
        && account.password.length() >= 4) {
            return this.DAO.createAccount(account);
        }
        return null;
    }

    public Message addMessage(Message message) {
        if (!(message.message_text.isEmpty()) && !(message.message_text.isBlank())
        && (message.message_text.length() <= 255)) {
            return this.DAO.createMessage(message);
        }
        return null;
    }

    public Account login(Account account) {
        if (this.DAO.verifyAccount(account.username, account.password) != null) {
            return this.DAO.verifyAccount(account.username, account.password);
        }
        return null;
    }

    public Message getMessage(int message_id) {
        return this.DAO.getMessage(message_id);
    }

    public List<Message> getAllMessagesFromAccount(int account_id) {
        return this.DAO.getAllMessagesFromAccount(account_id);
    }

    public Message deleteMessage(int message_id) {
        Message deleted_message = getMessage(message_id);
        return this.DAO.deleteMessage(message_id);
    }

    public Message updateMessage(String message_text, int message_id) {
        if (message_text.length() <= 255 && !(message_text.isEmpty())
        && !(message_text.isBlank())) {
            this.DAO.updateMessage(message_text, message_id);
            return getMessage(message_id);
        }
        return null;
    }
}
