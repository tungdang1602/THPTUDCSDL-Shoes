package dao;

import model.Product;
import model.Cart;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAO extends DBConnecttion<Account> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<Cart> getCart(int id) {
        List<Cart> list = new ArrayList<>();
        String query = "select Product.ProductID, Product.ProductName, Product.Description, Product.Price, Product.imageLink, Cart.Amount\n"
                + "from Cart inner join Product\n"
                + "on Cart.ProductID = Product.ProductID\n"
                + "where Cart.UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                int amount = rs.getInt(6);
                list.add(new Cart(p, amount));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int countCart(int id) {
        int count = 0;
        String query = "select count(*)\n"
                + "from Cart inner join Product \n"
                + "on Cart.ProductID = Product.ProductID\n"
                + "where Cart.UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public int countAllCart() {
        int count = 0;
        String query = "select count(*)\n"
                + "from Cart group by UserID";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    //Function returns a boolean to inform whether the product is added to cart or not
    //If Product is out of stock
    public boolean addToCart(int userID, int productID, int amount) {
        //Check if Product to add already has at least 1 in cart
        CartDAO dao = new CartDAO();

        //Before add to cart: check if product is out of stock
        if (countAmountProduct(productID) == 0) {
            return false;
        } else {
            List<Cart> list = dao.getCart(userID);
            for (Cart cart : list) {
                if (cart.getP().getId() == productID) {
                    String query = "update Cart\n"
                            + "set amount = ?\n"
                            + "where UserID = ? and ProductID = ?";
                    try {
                        ps = connection.prepareStatement(query);
                        ps.setInt(1, cart.getAmount() + amount);
                        ps.setInt(2, userID);
                        ps.setInt(3, productID);
                        ps.executeUpdate();
                    } catch (Exception e) {
                    }
                    //Call to delete1amount
                    dao.delete1Amount(productID);
                    return true;
                }
            }
            //Else: Add 1 product into cart
            String query = "INSERT INTO Cart VALUES (?, ?, ?);";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, userID);
                ps.setInt(2, productID);
                ps.setInt(3, amount);
                ps.executeUpdate();
            } catch (Exception e) {
            }
            delete1Amount(productID);
            return true;
        }
    }

    //Minus 1 amount from Product after 1 Customer Add to cart
    public void delete1Amount(int ProductID) {
        String query = "update Product\n"
                + "set Amount = Amount - 1\n"
                + "where ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //Count the amount of a product to see if it is out of stock
    public int countAmountProduct(int ProductID) {
        String query = "select amount\n"
                + "from Product\n"
                + "where ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countNumCart(int userID) {
        String query = "select count(*) from Cart where UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void deleteCart(int UserID) {
        String query = "delete from Cart where UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, UserID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProductCart(int UserID, int ProductID) {
        String query = "delete from cart where UserID = ? and ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, UserID);
            ps.setInt(2, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CartDAO CartDAO = new CartDAO();
        System.out.println(CartDAO.countAllCart());
    }
}
