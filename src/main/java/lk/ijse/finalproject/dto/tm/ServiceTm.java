package lk.ijse.finalproject.dto.tm;

public class ServiceTm {
    private String sid;
    private String package_name;
    private String description;
    private double price;


    public ServiceTm(String sid, String package_name, String description, double price) {
        this.sid = sid;
        this.package_name = package_name;
        this.description = description;
        this.price = price;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
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

    @Override
    public String toString() {
        return "ServiceTm{" +
                "sid='" + sid + '\'' +
                ", package_name='" + package_name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
