package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.ConsultingBo;
import lk.ijse.finalproject.dao.custom.EventConDao;
import lk.ijse.finalproject.dao.impl.EventConDaoImpl;
import lk.ijse.finalproject.dto.CollabaratingDto;
import lk.ijse.finalproject.dto.EventConDto;
import lk.ijse.finalproject.entity.Consultation;
import lk.ijse.finalproject.entity.VendorCollabaration;

import java.sql.SQLException;

public class ConsultingBoImpl implements ConsultingBo {
    public EventConDao eventConDaoImpl = new EventConDaoImpl();
    @Override
    public String generateNextCode() throws SQLException, ClassNotFoundException {
        return eventConDaoImpl.generateId();
    }

    @Override
    public boolean saveConsulting(EventConDto dto) throws SQLException, ClassNotFoundException {
        return eventConDaoImpl.save(new Consultation(dto.getConId(),dto.getFee(),dto.getDescription(),dto.getcId()));
    }

    @Override
    public boolean deleteEventCon(String id) throws SQLException, ClassNotFoundException {
        return eventConDaoImpl.delete(id);
    }

    @Override
    public boolean updateConsulting(EventConDto dto) throws SQLException, ClassNotFoundException {
        return eventConDaoImpl.update(new Consultation(dto.getConId(),dto.getFee(),dto.getDescription(),dto.getcId()));
    }

    @Override
    public double total() throws SQLException, ClassNotFoundException {
        return eventConDaoImpl.total();
    }

    @Override
    public EventConDto getAllDetails(String id) throws SQLException, ClassNotFoundException {
        Consultation entity=eventConDaoImpl.search(id);
        return new EventConDto(entity.getConId(),entity.getFee(),entity.getDescription(),entity.getcId());
    }

    @Override
    public EventConDto search(String id) throws SQLException, ClassNotFoundException {
        Consultation entity=eventConDaoImpl.search(id);
        return new EventConDto(entity.getConId(),entity.getFee(),entity.getDescription(),entity.getcId());
    }
}
