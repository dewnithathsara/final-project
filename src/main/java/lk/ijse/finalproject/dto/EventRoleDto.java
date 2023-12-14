package lk.ijse.finalproject.dto;

public class EventRoleDto {

    private String empId;
    private String aid;
    private String task;
    private String status;

    public EventRoleDto() {
    }

    public EventRoleDto(String empId, String aid, String task, String status) {
        this.empId=empId;
        this.aid=aid;
        this.task=task;
        this.status=status;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventRoleDto{" +
                "empId='" + empId + '\'' +
                ", aid='" + aid + '\'' +
                ", task='" + task + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
