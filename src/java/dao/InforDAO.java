package dao;

import model.Category;
import model.Information;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class InforDAO extends DBConnecttion<Information>{
        
    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về
    
    public Information getInfor() {
        String query = "SELECT * FROM Information";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) { 
                return new Information(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
}
