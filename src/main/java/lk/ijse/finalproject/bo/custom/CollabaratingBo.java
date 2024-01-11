package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.CollabaratingDto;

import java.sql.SQLException;
import java.util.List;

public interface CollabaratingBo extends SuperBo {
    boolean saveCollabaring(CollabaratingDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCollabaration(String id) throws SQLException, ClassNotFoundException;
    boolean updateCollobarating(CollabaratingDto dto) throws SQLException, ClassNotFoundException;
    List<CollabaratingDto> getAllCollabarting(String eid) throws SQLException, ClassNotFoundException;
    CollabaratingDto search(String id) throws SQLException, ClassNotFoundException;
}
