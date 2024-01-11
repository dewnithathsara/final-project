package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.EventConDto;

import java.sql.SQLException;

public interface ConsultingBo extends SuperBo {
    String generateNextCode() throws SQLException, ClassNotFoundException;

    boolean saveConsulting(EventConDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteEventCon(String id) throws SQLException, ClassNotFoundException;
    boolean updateConsulting(EventConDto dto) throws SQLException, ClassNotFoundException;
    double total() throws SQLException, ClassNotFoundException;
    EventConDto getAllDetails(String id) throws SQLException, ClassNotFoundException;
    EventConDto search(String id) throws SQLException, ClassNotFoundException;
}
