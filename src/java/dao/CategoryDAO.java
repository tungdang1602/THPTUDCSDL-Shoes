package dao;

import model.Category;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DBConnecttion<Category> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    //Tạo 1 Hàm để Load tất cả các Category lên
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>(); //Tạo 1 List để truyền dữ liệu
        String query = "SELECT * FROM Category"; //Viết query từ DB rồi copy sang 
        try {
            //Mở kết nối vs SQL Server
            ps = connection.prepareStatement(query); //Ném query vào
            rs = ps.executeQuery(); // Chạy query
            //RS chính là bảng result -> Lấy dữ liệu của bảng result và đẩy vào List
            while (rs.next() /*Next từng DÒNG tới cuối bảng*/) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3))); //Add vào
            }
        } catch (Exception e) {
        }

        return list;
    }

    public String getCateNameByID(int id) {
        String query = "select CategoryName from Category\n"
                + "where CategoryID = ?"; 
        try {
            ps = connection.prepareStatement(query); 
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
//        System.out.println(dao.getCateNameByID(1));
        List<Category> list = dao.getAllCategory();
        for (Category category : list) {
            System.out.println(category);
        }
    }
}
