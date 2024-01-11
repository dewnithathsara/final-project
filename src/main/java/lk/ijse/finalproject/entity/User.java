package lk.ijse.finalproject.entity;

public class User {
    public String  uid;
    private String name;
    private String userName;
    private String email;
    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
      this.password = password;
    }

    public User(String uid, String name, String userName, String email, String password) {
        this.uid = uid;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
