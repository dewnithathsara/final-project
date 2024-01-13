package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.CustomerBo;
import lk.ijse.finalproject.dao.DAOFactory;
import lk.ijse.finalproject.dao.custom.ClientDao;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    ClientDao dao=(ClientDao) DAOFactory.getDaoFactory().getDaoTypes(DAOFactory.DaoTypes.CUSTOMER);
    @Override
    public boolean saveCustomer(ClientDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Customer(dto.getcId(),dto.getCustName(),dto.getEmail(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean updateClient(ClientDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Customer(dto.getcId(),dto.getCustName(),dto.getEmail(),dto.getAddress(),dto.getContact()));

    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {


        return dao.delete(id);
    }

    @Override
    public List<ClientDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customer= (ArrayList<Customer>) dao.getAll();
        ArrayList<ClientDto> dto=new ArrayList<>();
        for(Customer c:customer) {
            dto.add(new ClientDto(c.getcId(),c.getCustName(),c.getEmail(),c.getAddress(),c.getContact()));
        }
        return  dto;

    }

    @Override
    public ClientDto searchId(String id) throws SQLException, ClassNotFoundException {
        Customer dto=dao.searchId(id);
       return new ClientDto(dto.getcId(),dto.getCustName(),dto.getEmail(),dto.getAddress(),dto.getContact());
    }

    @Override
    public ClientDto search(String id) throws SQLException, ClassNotFoundException {
        Customer dto=dao.search(id);
        return new ClientDto(dto.getcId(),dto.getCustName(),dto.getEmail(),dto.getAddress(),dto.getContact());
    }
}
