package lk.ijse.finalproject.dto;

public class VendorDto {

    private String id;
    private String uid;
    private String category;
    private String name;
    private String email;
    private String contact;
    public VendorDto(String id, String uid, String category, String name, String email, String contact) {
        this.id=id;
        this.uid=uid;
        this.category=category;
        this.name=name;
        this.email=email;
        this.contact=contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "VendorDto{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
