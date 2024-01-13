package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.ServiceBo;
import lk.ijse.finalproject.dao.custom.ServiceDao;
import lk.ijse.finalproject.dao.impl.ServiceDaoImpl;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.dto.ServiceDto;
import lk.ijse.finalproject.entity.EventRole;
import lk.ijse.finalproject.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBoImpl implements ServiceBo {
    public ServiceDao serviceDaoImpl =new ServiceDaoImpl();
    @Override
    public String generateSid() throws SQLException, ClassNotFoundException {
        return serviceDaoImpl.generateId();
    }

    @Override
    public boolean saveService(ServiceDto dto) throws SQLException, ClassNotFoundException {
        return serviceDaoImpl.save(new Service(dto.getSid(),dto.getPackageName(),dto.getDescription(),dto.getPrice()));
    }

    @Override
    public List<ServiceDto> getAllServices() throws SQLException, ClassNotFoundException {
        ArrayList<Service> events = (ArrayList<Service>) serviceDaoImpl.getAll();
        ArrayList<ServiceDto> dto = new ArrayList<>();
        for(Service eventsrole:events){
            dto.add(new ServiceDto(eventsrole.getSid(),eventsrole.getPackageName(),eventsrole.getDescription(),eventsrole.getPrice()));
        }
        return dto;

    }

    @Override
    public boolean deleteService(String id) throws SQLException, ClassNotFoundException {
        return serviceDaoImpl.delete(id);
    }

    @Override
    public boolean updateService(ServiceDto dto) throws SQLException, ClassNotFoundException {
        return serviceDaoImpl.update(new Service(dto.getSid(),dto.getPackageName(),dto.getDescription(),dto.getPrice()));

    }

    @Override
    public List<ServiceDto> getservices() throws SQLException, ClassNotFoundException {
        ArrayList<Service> events = (ArrayList<Service>) serviceDaoImpl.getAll();
        ArrayList<ServiceDto> dto = new ArrayList<>();
        for(Service eventsrole:events){
            dto.add(new ServiceDto(eventsrole.getSid(),eventsrole.getPackageName(),eventsrole.getDescription(),eventsrole.getPrice()));
        }
        return dto;
    }

    @Override
    public ServiceDto search(String id) throws SQLException, ClassNotFoundException {
        Service entity=serviceDaoImpl.search(id);
        return new ServiceDto(entity.getSid(), entity.getPackageName(),entity.getDescription(),entity.getPrice());
    }
}
