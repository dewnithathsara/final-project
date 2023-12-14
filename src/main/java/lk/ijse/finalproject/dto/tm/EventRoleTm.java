package lk.ijse.finalproject.dto.tm;

public class EventRoleTm {

    private String empId;
   // private String aid;
    private String task;
    private String status;

    public EventRoleTm() {
    }

    public EventRoleTm(String empId,  String task, String status) {
        this.empId = empId;

        this.task = task;
        this.status = status;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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
        return "EventRoleTm{" +
                "empId='" + empId + '\'' +

                ", task='" + task + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
