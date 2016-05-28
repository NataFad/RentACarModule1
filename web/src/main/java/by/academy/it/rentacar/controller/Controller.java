package by.academy.it.rentacar.controller;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.factory.ActionFactory;
import by.academy.it.rentacar.exceptions.ActionNotFoundException;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import java.util.Enumeration;
//import javax.servlet.http.HttpSession;

/**
 * Servlet Controller
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
public class Controller extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 6207043207197741821L;
    private ActionFactory client = ActionFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        // определение команды, пришедшей из JSP
        Action command = null;
        try {
            command = client.getAction(request);
            page = command.execute(request, response);
        } catch (ActionNotFoundException e) {
            //errorManager.writeError(request, e, "Unknown Action command: ", true);
        } catch (Exception e) {
            //errorManager.writeError(request, e, "Exception at Controller: ", true);
        }
        if (page == null) {
            ConfigurationManager.getInstance();
            page = ConfigurationManager.getProperty("page.index");
            // установка страницы c cообщением об ошибке
            response.sendRedirect(request.getContextPath() + page);
            return;
        }
        // метод возвращает страницу ответа
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }
}