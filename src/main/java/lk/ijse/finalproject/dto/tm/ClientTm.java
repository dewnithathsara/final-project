package lk.ijse.finalproject.dto.tm;

public class ClientTm {
    private String client_id;
    private String name;
    private String email;
    private String address;
    private String contactInfo;

    public ClientTm() {
    }

    public ClientTm(String client_id, String name, String email, String address, String contactInfo) {
        this.client_id = client_id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactInfo = contactInfo;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "ClientTm{" +
                "client_id='" + client_id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
