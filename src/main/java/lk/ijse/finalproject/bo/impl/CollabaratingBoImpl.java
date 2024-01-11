package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.CollabaratingBo;
import lk.ijse.finalproject.dao.custom.CollabaratingDao;
import lk.ijse.finalproject.dao.impl.CollabaratingDaoImpl;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.CollabaratingDto;
import lk.ijse.finalproject.entity.Customer;
import lk.ijse.finalproject.entity.VendorCollabaration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollabaratingBoImpl implements CollabaratingBo {
    public CollabaratingDao dao=new CollabaratingDaoImpl();
    @Override
    public boolean saveCollabaring(CollabaratingDto dto) throws SQLException, ClassNotFoundException {
        return dao.saveCollabaring(new VendorCollabaration(dto.geteId(),dto.getsId(),dto.getvId(),dto.getDesc(),dto.getTime(),dto.getDate(),dto.getPrice()));
    }

    @Override
    public boolean deleteCollabaration(String id) throws SQLException, ClassNotFoundException {
        return dao.deleteCollabaration(id);
    }

    @Override
    public boolean updateCollobarating(CollabaratingDto dto) throws SQLException, ClassNotFoundException {
          return dao.updateCollobarating(new VendorCollabaration(dto.geteId(),dto.getsId(),dto.getvId(),dto.getDesc(),dto.getTime(),dto.getDate(),dto.getPrice()));

    }

    @Override
    public List<CollabaratingDto> getAllCollabarting(String eid) throws SQLException, ClassNotFoundException {
        ArrayList<VendorCollabaration> collabarations= (ArrayList<VendorCollabaration>) dao.getAllCollabarting(eid);
        ArrayList<CollabaratingDto> dto=new ArrayList<>();
        for(VendorCollabaration c:collabarations) {
            dto.add(new CollabaratingDto(c.getsId(),c.geteId(),c.getvId(),c.getDesc(),c.getTime(),c.getDate(),c.getPrice()));
        }
        return  dto;
    }

    @Override
    public CollabaratingDto search(String id) throws SQLException, ClassNotFoundException {
        VendorCollabaration entity=dao.search(id);
        return new CollabaratingDto(entity.geteId(),entity.getsId(),entity.getvId(),entity.getDesc(),entity.getTime(),entity.getDate(),entity.getPrice());
    }
}
