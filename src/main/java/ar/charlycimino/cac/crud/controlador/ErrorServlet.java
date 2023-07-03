package ar.charlycimino.cac.crud.controlador;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
@WebServlet(name = "ErrorServlet", urlPatterns = {"/error-handler"})
public class ErrorServlet extends HttpServlet {

    private void processError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Throwable th = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        List<Throwable> throwables = new ArrayList<>();
        for (Throwable t = th; t != null; t = t.getCause()) {
            throwables.add(t);
        }
        request.setAttribute("throwablesList", throwables);
        request.setAttribute("mensaje", th.getMessage());
        request.setAttribute("causa", th.getCause());
        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processError(request, response);
    }

}
