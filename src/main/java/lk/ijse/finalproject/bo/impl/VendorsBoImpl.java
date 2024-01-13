package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.VendorsBo;
import lk.ijse.finalproject.dao.custom.VendorsDao;
import lk.ijse.finalproject.dao.impl.VendorsDaoImpl;
import lk.ijse.finalproject.dto.ServiceDto;
import lk.ijse.finalproject.dto.VendorDto;
import lk.ijse.finalproject.entity.Service;
import lk.ijse.finalproject.entity.Vendors;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendorsBoImpl implements VendorsBo {
    public VendorsDao vendorsDaoImpl =new VendorsDaoImpl();
    @Override
    public VendorDto search(String id) throws SQLException, ClassNotFoundException {
        Vendors entity=vendorsDaoImpl.search(id);
        return new VendorDto(entity.getId(),entity.getUid(),entity.getCategory(),entity.getName(), entity.getEmail(),entity.getContact());
    }

    @Override
    public String generateNextVendorId() throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.generateId();
    }

    @Override
    public boolean saveVendor(VendorDto dto) throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.save(new Vendors(dto.getId(),dto.getUid(),dto.getCategory(),dto.getName(),dto.getEmail(),dto.getContact()));
    }

    @Override
    public List<VendorDto> getAllVendors() throws SQLException, ClassNotFoundException {
        ArrayList<Vendors> events = (ArrayList<Vendors>) vendorsDaoImpl.getAll();
        ArrayList<VendorDto> dto = new ArrayList<>();
        for(Vendors eventsrole:events){
            dto.add(new VendorDto(eventsrole.getId(),eventsrole.getUid(),eventsrole.getCategory(),eventsrole.getName(),eventsrole.getEmail(),eventsrole.getContact()));
        }
        return dto;
    }
    @Override
    public boolean deleteVendor(String id) throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.delete(id);
    }

    @Override
    public boolean updateVendor(VendorDto dto) throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.update(new Vendors(dto.getId(),dto.getUid(),dto.getCategory(),dto.getName(),dto.getEmail(),dto.getContact()));
    }

    @Override
    public int countVendors() throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.countVendors();
    }

    @Override
    public List<String> getAllVendorsByCatgory(String category) throws SQLException, ClassNotFoundException {
       return null;
    }
    @Override
    public int countphotographers(String category) throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.countphotographers(category);
    }

    @Override
    public int countflowerists(String category) throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.countflowerists(category);
    }

    @Override
    public int countCategories() throws SQLException, ClassNotFoundException {
        return vendorsDaoImpl.countCategories();
    }
}
