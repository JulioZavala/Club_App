package app.controller.campo;

import app.dao.CampoDAO;
import app.dao.DAOExcepcion;
import app.model.Campo;
import java.util.List;

public class CampoService {

    private CampoDAO campoDAO;

    public CampoService() {
        campoDAO = new CampoDAO();

    }

    public List<Campo> list() throws DAOExcepcion {

        return campoDAO.list();

    }

    public Campo save(Campo campo) throws DAOExcepcion {

        return campoDAO.save(campo);

    }

    public Campo get(Campo campo) throws DAOExcepcion {

        return campoDAO.get(campo);
    }

    public void delete(Campo campo) throws DAOExcepcion {

        campoDAO.delete(campo);
    }

    public Campo update(Campo campo) throws DAOExcepcion {

        return campoDAO.update(campo);
    }
}
