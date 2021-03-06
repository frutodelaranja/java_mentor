package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update/*")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service= UserService.getInstance();
        User user = new User();
//        String idString = req.getParameter("id");
        user.setId(Long.parseLong(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("mail"));
        user.setPassword(req.getParameter("pass"));
        if (validate(user)){
            if (service.updateUser(user)){
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
    private boolean validate(User user){
        boolean id = user.getId() > 0;
        boolean name = (!user.getName().equals("") && !user.getName().equals(" ") && !user.getName().equals("0") && user.getName() != null);
        boolean login = (!user.getLogin().equals("") && !user.getLogin().equals(" ") && !user.getLogin().equals("0") && user.getLogin() != null);
        boolean password = (!user.getPassword().equals("") && !user.getPassword().equals(" ") && !user.getPassword().equals("0") && user.getPassword() != null);
        return (name && login && password);
    }
}
