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
        if (this.DAO.getAccount(account.username) != null
        && this.DAO.getAccount(account.username).password == account.password) {
            return this.DAO.getAccount(account.username);
        }
        return null;
    }

    public Message getMessage(int message_id) {
        return DAO.getMessage(message_id);
    }

    public List<Message> getAllMessagesFromAccount(int account_id) {
        return DAO.getAllMessagesFromAccount(account_id);
    }
}
