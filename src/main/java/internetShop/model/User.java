package internetShop.model;

import java.util.List;

public class User {
    private static Long idGenerator = 0L;
    private Long userId;
    private String name;
    private String login;
    private String password;
    private String token;
    private Bucket userBucket;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User() {
        userId = idGenerator++;
    }

    public Bucket getUserBucket() {
        return userBucket;
    }

    public void setUserBucket(Bucket userBucket) {
        this.userBucket = userBucket;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
