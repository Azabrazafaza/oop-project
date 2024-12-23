package interfaces;

import users.User;

public interface Notification {
    void notifyUser(User user, String message);
    void subscribeToTopic(User user, String topic);
    void unsubscribeFromTopic(User user, String topic);
}
