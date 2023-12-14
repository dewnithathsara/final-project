package lk.ijse.finalproject.dto;

public class RegisterDto {
    public String  uid;
    private String name;
    private String userName;
    private String email;
    private String password;


    public RegisterDto(String uid, String name, String userName, String email, String password) {
        this.uid = uid;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public RegisterDto(String username, String mail) {
        this.userName=username;
        this.email=mail;
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

    public void setPassword(String setPassword) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
