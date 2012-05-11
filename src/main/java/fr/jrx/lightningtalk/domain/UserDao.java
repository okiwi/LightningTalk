package fr.jrx.lightningtalk.domain;

public interface UserDao {
    User getForUsername(String username);

    void createUser(User user);
}