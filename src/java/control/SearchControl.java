package control;

import model.Product;
import model.Category;
import model.Information;
import dao.ProductDAO;
import dao.InforDAO;
import dao.CategoryDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchControl", urlPatterns = {"/search"})
public class SearchControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String txtSearch = request.getParameter("txt");
        if (txtSearch.equals("")) {
            response.sendRedirect("home");
        } else {
            //Call to DAOs
            ProductDAO ProductDAO = new ProductDAO();
            InforDAO InforDAO = new InforDAO();
            CategoryDAO CategoryDAO = new CategoryDAO();
            UserDAO UserDAO = new UserDAO();

            List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category
            Product first = ProductDAO.getHotProduct(); //Get First Product
            Product last = ProductDAO.getFavoriteProduct(); //Get Last Product
            Information infor = InforDAO.getInfor(); //Get Information

            String CategoryID = request.getParameter("CategoryID");
            if (CategoryID == null) { 
                CategoryID = "0";
            }
            request.setAttribute("CategoryID", CategoryID);

            int CID = Integer.parseInt(CategoryID);

            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }

            int index = Integer.parseInt(indexPage);

            int count = ProductDAO.countProductByCategory(CID);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            }
            List<Product> listP = ProductDAO.searchProductByName(txtSearch);

            //Set Data to JSP
            request.setAttribute("allCategory", listC);
            request.setAttribute("first", first);
            request.setAttribute("last", last);
            request.setAttribute("infor", infor);

            request.setAttribute("listP", listP); //List Product
            request.setAttribute("end", endPage);
            request.setAttribute("tag", index); //Page number
            request.setAttribute("count", count);
            request.setAttribute("CateID", CID);

            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
