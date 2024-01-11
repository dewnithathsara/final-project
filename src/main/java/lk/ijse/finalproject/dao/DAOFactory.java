package lk.ijse.finalproject.dao;

import lk.ijse.finalproject.dao.custom.ClientDao;
import lk.ijse.finalproject.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory():daoFactory;
    }
    public enum DaoTypes{
        APPOINTMENT,CUSTOMER,EVENT,USER,VENDOR,COLLABORATION,SERVICE,CONSULTATION,EMPLOYEE,EVENTROLE

    }
    public SuperDao getDaoTypes(DaoTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER :
                return new ClientDaoImpl();
            case APPOINTMENT:
                return new AppointmentDaoImpl();
            case EVENT:
                return new EventDaoImpl();
            case USER:
                return new UserDaoImpl();
            case VENDOR:
                return new VendorsDaoImpl();
            case COLLABORATION:
                return new CollabaratingDaoImpl();
            case SERVICE:
                return new ServiceDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case CONSULTATION:
                return new EventConDaoImpl();
            case EVENTROLE:
                return new EventRoleDaoImpl();
            default:
                return null;
        }

    }
}
