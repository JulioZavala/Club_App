package app.controller.servicio;

import app.dao.ServicioDAO;
import app.dao.DAOExcepcion;
import app.model.Servicio;
import java.util.List;

public class ServicioService {

    private ServicioDAO servicioDAO;

    public ServicioService() {
        servicioDAO = new ServicioDAO();

    }

    public List<Servicio> list() throws DAOExcepcion {

        return servicioDAO.list();

    }

    public Servicio save(Servicio servicio) throws DAOExcepcion {

        return servicioDAO.save(servicio);

    }

    public Servicio get(Servicio servicio) throws DAOExcepcion {

        return servicioDAO.get(servicio);
    }

    public void delete(Servicio servicio) throws DAOExcepcion {

        servicioDAO.delete(servicio);
    }

    public Servicio update(Servicio servicio) throws DAOExcepcion {

        return servicioDAO.update(servicio);
    }
}
