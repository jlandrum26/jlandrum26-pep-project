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
        if () {
            ;
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

    public boolean login(String username, String password) {
        if () {
            ;
            return true;
        }
        return false;
    }
}
