package app.controller.alquiler;

import app.dao.DAOExcepcion;
import app.dao.SocioAlquilerDAO;
import app.model.SocioAlquiler;
import java.util.List;

public class AlquilerService {

    private SocioAlquilerDAO socioAlquilerDAO;

    public AlquilerService() {
        socioAlquilerDAO = new SocioAlquilerDAO();
    }

    public List<SocioAlquiler> list() throws DAOExcepcion {

        return socioAlquilerDAO.list();

    }

    public SocioAlquiler save(SocioAlquiler socioAlquiler) throws DAOExcepcion {

        return socioAlquilerDAO.save(socioAlquiler);

    }

    public SocioAlquiler get(SocioAlquiler socioAlquiler) throws DAOExcepcion {

        return socioAlquilerDAO.get(socioAlquiler);
    }

    public void delete(SocioAlquiler socioAlquiler) throws DAOExcepcion {

        socioAlquilerDAO.delete(socioAlquiler);
    }

    public SocioAlquiler update(SocioAlquiler socioAlquiler) throws DAOExcepcion {

        return socioAlquilerDAO.update(socioAlquiler);
    }
}
