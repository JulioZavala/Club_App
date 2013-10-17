package app.controller.local;

import app.dao.LocalDAO;
import app.dao.DAOExcepcion;
import app.model.Local;
import java.util.List;

public class LocalService {

    private LocalDAO localDAO;

    public LocalService() {
        localDAO = new LocalDAO();
    }

    public List<Local> list() throws DAOExcepcion {

        return localDAO.list();

    }

    public Local save(Local local) throws DAOExcepcion {

        return localDAO.save(local);

    }

    public Local get(Local local) throws DAOExcepcion {

        return localDAO.get(local);
    }

    public void delete(Local local) throws DAOExcepcion {

        localDAO.delete(local);
    }

    public Local update(Local local) throws DAOExcepcion {

        return localDAO.update(local);
    }
}
