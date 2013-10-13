package app.controller.servicio;

import app.dao.ServicioDAO;
import app.excepcion.DAOExcepcion;
import app.model.Servicio;
import java.util.Collection;

public class ServicioService {

    private ServicioDAO servicioDAO;

    public ServicioService() {
        servicioDAO = new ServicioDAO();

    }

    public Collection<Servicio> listar() throws DAOExcepcion {

        return servicioDAO.listar();

    }

    public Servicio insertar() throws DAOExcepcion {

        return servicioDAO.insertar(null);

    }

    public Servicio obtener(Servicio servicio) throws DAOExcepcion {

        return servicioDAO.obtener(servicio);
    }
}
