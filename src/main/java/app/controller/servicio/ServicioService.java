package app.controller.servicio;

import app.dao.ServicioDAO;
import app.model.Servicio;
import java.util.List;

public class ServicioService {

    private ServicioDAO servicioDAO;

    public ServicioService() {
        servicioDAO = new ServicioDAO();

    }

    public List<Servicio> list() {

        return servicioDAO.list();

    }

    public Servicio save(Servicio servicio) {

        return servicioDAO.save(servicio);

    }

    public Servicio get(Servicio servicio) {

        return servicioDAO.get(servicio);
    }

    public void delete(Servicio servicio) {

        servicioDAO.delete(servicio);
    }

    public Servicio update(Servicio servicio) {

        return servicioDAO.update(servicio);
    }
}
