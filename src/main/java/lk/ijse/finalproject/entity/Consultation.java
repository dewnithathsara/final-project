package lk.ijse.finalproject.entity;

public class Consultation {
    private String conId;
    private double fee;
    private String description;
    private String cId;

    public Consultation() {
    }

    public Consultation(String conId, double fee, String description, String cId) {
        this.conId = conId;
        this.fee = fee;
        this.description = description;
        this.cId = cId;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}
