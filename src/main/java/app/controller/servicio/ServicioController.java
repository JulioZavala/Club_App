
package app.controller.servicio;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adm/servicio")
public class ServicioController extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adm/servicio/servicio.jsp");
        rd.forward(request, response);
        
    }

}
