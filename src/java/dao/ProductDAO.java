package dao;

import model.Product;
import model.ProductInManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Load du lieu tu SQL Server
public class ProductDAO extends DBConnecttion<Product> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try {
            ps = connection.prepareStatement(query);//ném query sang bên SQL server
            rs = ps.executeQuery();//Chạy câu lệnh query, nhận kết quả trả về

            //Giờ đây, câu lệnh đã đc chạy, rs là bảng Result -> Giờ phải lấy dữ liệu từ bảng rs và cho vào List
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public Product getHotProduct() {
        //Product with most amount
        String query = "select top 1 * from Product\n"
                + "order by Amount desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Product getFavoriteProduct() {
        //Product with second most amount
        String query = "select top 2 * from Product\n"
                + "order by Amount desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                rs.next();
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE SellerID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void edit(String id, String name, String description, String price, String imageLink, String CategoryID, String SellerID, String amount) {
        String query = "UPDATE Product\n"
                + "SET ProductName = ?,\n"
                + "Description = ?,\n"
                + "Price = ?,\n"
                + "imageLink = ?,\n"
                + "CategoryID = ?,\n"
                + "SellerID = ?,\n"
                + "Amount = ?\n"
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, imageLink);
            ps.setString(5, CategoryID);
            ps.setString(6, SellerID);
            ps.setString(7, amount);
            ps.setString(8, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void delete(String id) {
        String query = "DELETE FROM Cart WHERE ProductID = ?\n"
                + "DELETE FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void add(String name, String description, String price, String imageLink, String CategoryID, String SellerID, String amount) {
        String query = "INSERT INTO Product VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, imageLink);
            ps.setString(5, CategoryID);
            ps.setString(6, SellerID);
            ps.setString(7, amount);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //count total product
    public int countProduct() {
        String query = "SELECT COUNT(*) FROM Product";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> pagingProduct(int index) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY ProductID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 6);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> pagingByCategory(int index, int CategoryID) {
        List<Product> list = new ArrayList<>();
        if (CategoryID == 0) {
            list = pagingProduct(index);
        } else {
            String query = "SELECT * FROM Product WHERE CategoryID = ? ORDER BY ProductID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, CategoryID);
                ps.setInt(2, (index - 1) * 6);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List<Product> pagingManagerProduct(int index, int SellerID) {
        List<Product> list = new ArrayList<>();
        if (SellerID == 0) {
            list = pagingProduct(index);
        } else {
            String query = "SELECT * FROM Product WHERE SellerID = ? ORDER BY ProductID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, SellerID);
                ps.setInt(2, (index - 1) * 6);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public int countProductByCategory(int CategoryID) {
        if (CategoryID == 0) {
            return countProduct();
        } else {
            String query = "SELECT COUNT(*) FROM Product WHERE CategoryID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, CategoryID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public int countProductBySeller(int SellerID) {
        if (SellerID == 0) {
            return countProduct();
        } else {
            String query = "SELECT COUNT(*) FROM Product WHERE SellerID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, SellerID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public Product getProductByID(String id) {
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ProductInManager getProductForManager(String id) {
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new ProductInManager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> searchProductByName(String name) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where ProductName like ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Product> searchProductInManager(String name, int SellerID) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where ProductName like ? and SellerID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, SellerID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<ProductInManager> top3MostSell() {
        List<ProductInManager> list = new ArrayList<>();
        String query = "select top 3 * from Product order by Amount asc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductInManager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    } 
    
    public List<ProductInManager> top3LeastSell() {
        List<ProductInManager> list = new ArrayList<>();
        String query = "select top 3 * from Product order by Amount desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductInManager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    } 

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.searchProductInManager("Nike", 1);
        for (Product product : list) {
            System.out.println(product);
        }
    }
}
