package lk.ijse.finalproject.dto.tm;

public class userTm {

    private String uId;

    private String name;
    private String userName;
    private String email;
    private String password;


    public userTm() {
    }

    public userTm(String uId, String name, String userName, String email, String password) {
        this.uId = uId;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;

    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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
        return "userTm{" +
                "uId='" + uId + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
