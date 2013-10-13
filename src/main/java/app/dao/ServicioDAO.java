package app.dao;

import app.dao.BaseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import app.excepcion.DAOExcepcion;
import app.model.General;
import app.model.Servicio;
import app.zelper.ConexionBD;

public class ServicioDAO extends BaseDAO {

    public Servicio insertar(Servicio servicio) throws DAOExcepcion {
        String query = "insert into servicio(desripcion,costo_hora) values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, servicio.getDescripcion());
            stmt.setDouble(2, servicio.getCosto_hora());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }
            // Obtener el ultimo id
            int id = 0;
            query = "select last_insert_id()";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            servicio.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicio;
    }

    public Servicio obtener(Servicio servicio) throws DAOExcepcion {
        int idServicio = servicio.getId();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select id, descripcion, costo_hora from servicio where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idServicio);
            rs = stmt.executeQuery();
            if (rs.next()) {
                servicio.setId(rs.getInt(1));
                servicio.setDescripcion(rs.getString(2));
                servicio.setCosto_hora(rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicio;
    }

    public void eliminar(Servicio servicio) throws DAOExcepcion {
        int idServicio = servicio.getId();
        String query = "delete from servicio WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idServicio);
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }

    public Servicio actualizar(Servicio vo) throws DAOExcepcion {
        String query = "update servicio set descripcion=?,costo_hora=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, vo.getDescripcion());
            stmt.setDouble(2, vo.getCosto_hora());
            stmt.setInt(3, vo.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return vo;
    }

    public Collection<Servicio> listar() throws DAOExcepcion {
        Collection<Servicio> c = new ArrayList<Servicio>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "select id, descripcion, costo_total from servicio order by descripcion";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Servicio vo = new Servicio();
                vo.setId(rs.getInt("id"));
                vo.setDescripcion(rs.getString("descripcion"));
                vo.setDescripcion(rs.getString("costo_total"));
                c.add(vo);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return c;
    }
}
