package ru.danilsibgatullin.BroadCastChat.serverside.interfaces;

public interface AuthService {
    void start();
    void stop();
    String getNickByLoginAndPassword(String login, String password);
}
