package lk.ijse.finalproject.entity;

public class Employee {
    private String EmpId;
    private String name;
    private String email;
    private String contact;
    private String type;

    public Employee() {
    }

    public Employee(String empId, String name, String email, String contact, String type) {
        EmpId = empId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.type = type;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
