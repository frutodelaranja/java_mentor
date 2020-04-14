package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/*")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/delete.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service= UserService.getInstance();
        String idString = req.getParameter("id");
        if (!idString.equals("") && !idString.equals(" ") && !idString.equals("0")){
            Long id = Long.parseLong(idString);
            User user = service.getUser(id);
            if (service.deleteUser(id)){
                req.setAttribute("userName", user.getName());
                doGet(req, resp);
            }else {
                req.setAttribute("userName", null);
                doGet(req, resp);
            }
        }else {
            req.setAttribute("userName", null);
            doGet(req, resp);
        }


    }
}
