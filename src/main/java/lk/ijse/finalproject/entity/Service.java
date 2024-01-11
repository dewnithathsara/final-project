package lk.ijse.finalproject.entity;

public class Service {
    private String sid;
    private String packageName;
    private String description;
    private double price;

    public Service() {
    }

    public Service(String sid, String packageName, String description, double price) {
        this.sid = sid;
        this.packageName = packageName;
        this.description = description;
        this.price = price;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
