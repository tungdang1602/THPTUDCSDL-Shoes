package dao;

import model.Account;
import model.Ship;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ShipDAO extends DBConnecttion<Account> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<Ship> getAllShip() {
        List<Ship> list = new ArrayList<>();
        String query = "select * from Ship";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ship(rs.getString(1), rs.getInt(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public int getShipPriceByCityName(String cityName) {
        String query = "select ShipPrice from Ship where CityName = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cityName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        ShipDAO ShipDAO = new ShipDAO();
        System.out.println(ShipDAO.getShipPriceByCityName("Hà Nội"));
    }
}
