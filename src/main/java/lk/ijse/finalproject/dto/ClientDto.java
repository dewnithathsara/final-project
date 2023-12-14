package lk.ijse.finalproject.dto;

public class ClientDto {

    private String cId;
    private String custName;

    private String email;

    private String address;
    private String contact;

    public ClientDto() {
    }

    public ClientDto(String cId, String custName, String email, String address, String contact) {
        this.cId = cId;
        this.custName = custName;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getEmail() {
        return email;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "cId='" + cId + '\'' +
                ", custName='" + custName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                '}';
    }
}
