package lk.ijse.finalproject.dto;

public class EventConDto {
    private String conId;
    private String clientName;
    private double fee;
    private String description;
    private String cId;

    public EventConDto(String conId, double fee, String description, String cId) {
      this.conId=conId;
      this.fee=fee;
      this.description=description;
      this.cId=cId;

    }

    public EventConDto(String conId, double fee,String clientName,  String description, String cId) {
        this.conId = conId;
        this.clientName = clientName;
        this.fee = fee;
        this.description = description;
        this.cId = cId;
    }

    public EventConDto( double fee,String clientName, String description, String cId) {
        this.clientName = clientName;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    @Override
    public String toString() {
        return "EventConDto{" +
                "conId='" + conId + '\'' +
                ", fee=" + fee +
                ", description='" + description + '\'' +
                ", cId='" + cId + '\'' +

                '}';
    }
}
