package lk.ijse.finalproject.dto.tm;

public class EmployeeTm {
    private String empId;
    private String name;
    private String email;
    private String contact;
    private String type;


    public EmployeeTm() {
    }

    public EmployeeTm(String empId, String name, String email, String contact, String type) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.type = type;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String EmpId) {
        this.empId = empId;
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

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact+'\''+
                ", type='" + type + '\'' +
                '}';
    }
}
