package servlets;

import model.User;
import service.Service;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    Service service = UserService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        service.deleteUser(Long.valueOf(req.getParameter("id")));
        req.setAttribute("users", service.getAllUsers());
        req.getRequestDispatcher("/home.jsp").forward(req,resp);
    }
}
