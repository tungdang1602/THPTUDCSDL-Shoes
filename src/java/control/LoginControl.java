package control;

import model.Account;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //Phần doGet của Servlet Login sẽ có nhiệm vụ lấy thông tin của User và Pass từ Cookie và đẩy lên trang Login.jsp để hiển thị
        
        //Bước 1: get user, pass from cookie
        //Cookie là 1 Mảng, gồm nhiều thành phần, bao gồm cả thông tin của Browser để cho mình biết là đang Login ở đâu
        Cookie arr[] = request.getCookies();
        if (arr != null) { 
        //Xử lý trg hợp: Khi mình đóng trình duyệt mà vào Login từ đường Link -> Cookie rỗng -> Vòng for bên dưới bị lỗi
        //Còn nếu vào từ /home thì dù vẫn chưa có userC và passC nhưng Cookie vẫn ko rỗng (vì có Cookie của Browser) nên cx ko lỗi
            for (Cookie o : arr) {
            if(o.getName().equals("userC")) { //Tìm đến thằng Cookie lưu về Username
                request.setAttribute("username", o.getValue()); //Đẩy Value của nó lên ô Username ở trang Login.jsp
            }
            if(o.getName().equals("passC")) {
                request.setAttribute("password", o.getValue());
            }
        }
        }
        
        //Bước 2: set user, pass to Login form        
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String remember = request.getParameter("remember");
        UserDAO dao = new UserDAO();
        Account a = dao.login(username, password);
        if (a == null) {
            request.setAttribute("mess", "Username hoặc Password Sai ");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            
            Cookie u = new Cookie("userC", username);
            Cookie p = new Cookie("passC", password);
            
            u.setMaxAge(60*60); 
            if (remember != null) {
                p.setMaxAge(60*60); 
            } else {
                p.setMaxAge(0); 
            }            
            response.addCookie(u);
            response.addCookie(p);
            
            if (a.getIsAdmin() == 1 || a.getIsSell() == 1) {
                response.sendRedirect("manager");
            } else {
                response.sendRedirect("home");
            }
        }
    }

}
