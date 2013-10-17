
package app.controller.socio;

import app.dao.DAOExcepcion;
import app.dao.SocioDAO;
import app.model.Socio;
import java.util.List;


public class SocioService {
    
    
    private SocioDAO socioDAO;

    public SocioService() {
        socioDAO = new SocioDAO();

    }

    public List<Socio> list() throws DAOExcepcion {

        return socioDAO.list();

    }

    public Socio save(Socio socio) throws DAOExcepcion {

        return socioDAO.save(socio);

    }

    public Socio get(Socio socio) throws DAOExcepcion {

        return socioDAO.get(socio);
    }

    public void delete(Socio socio) throws DAOExcepcion {

        socioDAO.delete(socio);
    }

    public Socio update(Socio socio) throws DAOExcepcion {

        return socioDAO.update(socio);
    }
    
}
