package lk.ijse.finalproject.bo;

import lk.ijse.finalproject.bo.impl.*;
import lk.ijse.finalproject.dao.DAOFactory;
import lk.ijse.finalproject.dao.SuperDao;
import lk.ijse.finalproject.dao.impl.*;

import static lk.ijse.finalproject.bo.BoFactory.botypes.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }
    public static BoFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BoFactory():boFactory;
    }
    public enum botypes{
        APPOINTMENT,CUSTOMER,EVENT,USER,VENDOR,COLLABORATION,SERVICE,CONSULTATION,EMPLOYEE,EVENTROLE

    }
    public SuperBo getBOTypes(BoFactory.botypes Botypes){
        switch (Botypes){
            case CUSTOMER :
                return new CustomerBoImpl();
            case APPOINTMENT:
                return new AppointmentBoImpl();
            case EVENT:
                return new EventBoImpl();
            case USER:
                return new UserBoImpl();
            case VENDOR:
                return new VendorsBoImpl();
            case COLLABORATION:
                return new CollabaratingBoImpl();
            case SERVICE:
                return new ServiceBoImpl();
            case EMPLOYEE:
                return new EmployeeBoIMpl();
            case CONSULTATION:
                return new ConsultingBoImpl();
            case EVENTROLE:
                return new EventRoleBoImpl();
            default:
                return null;
        }

    }
}
