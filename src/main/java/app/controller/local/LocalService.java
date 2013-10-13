package app.controller.local;

import app.dao.LocalDAO;
import app.excepcion.DAOExcepcion;
import app.model.Local;

public class LocalService {

    private LocalDAO localDAO;

    public LocalService() {
        localDAO = new LocalDAO();
    }

    public Local insertar() throws DAOExcepcion {

        return localDAO.insertar(null);

    }

    public Local obtener(Local local) throws DAOExcepcion {

        return localDAO.obtener(local);
    }

    public void eliminar(Local local) throws DAOExcepcion {

        localDAO.eliminar(local);
    }

    public Local actualizar(Local local) throws DAOExcepcion {

        return localDAO.actualizar(local);
    }
}
