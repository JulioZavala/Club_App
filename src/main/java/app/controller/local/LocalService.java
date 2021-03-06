package app.controller.local;

import app.dao.LocalDAO;
import app.model.Local;
import java.util.List;

public class LocalService {

    private LocalDAO localDAO;

    public LocalService() {
        localDAO = new LocalDAO();
    }

    public List<Local> list() {

        return localDAO.list();

    }

    public Local save(Local local) {

        return localDAO.save(local);

    }

    public Local get(Local local) {

        return localDAO.get(local);
    }

    public void delete(Local local) {

        localDAO.delete(local);
    }

    public Local update(Local local) {

        return localDAO.update(local);
    }
}
