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
        if (this.DAO.getAccount(account.username) == null
        && this.DAO.verify(account.username, account.password)) {
            this.DAO.createAccount(account);
            return account;
        }
        return null;
    }

    public Message addMessage(Message message) {
        if () {
            ;
            return message;
        }
        return null;
    }

    public Account login(Account account) {
        if () {
            ;
            return account;
        }
        return null;
    }
}
