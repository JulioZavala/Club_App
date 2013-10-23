package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.Servicio;
import app.zelper.ConexionBD;
import java.util.List;

public class ServicioDAO extends BaseDAO {

    public Servicio save(Servicio servicio) {
        String query = "insert into servicio(descripcion,costo_hora) values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, servicio.getDescripcion());
            stmt.setDouble(2, servicio.getCostoHora());
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
            
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicio;
    }

    public Servicio get(Servicio servicio) {
        Servicio servicioOriginal = new Servicio();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select * from servicio where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, servicio.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                servicioOriginal.setId(rs.getLong("id"));
                servicioOriginal.setDescripcion(rs.getString("descripcion"));
                servicioOriginal.setCostoHora(rs.getDouble("costo_hora"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicioOriginal;
    }

    public void delete(Servicio servicio) {

        String query = "delete from servicio WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, servicio.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }

    public Servicio update(Servicio servicio) {
        String query = "update servicio set descripcion=?,costo_hora=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, servicio.getDescripcion());
            stmt.setDouble(2, servicio.getCostoHora());
            stmt.setLong(3, servicio.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return servicio;
    }

    public List<Servicio> list() {
        List<Servicio> lista = new ArrayList<Servicio>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "select * from servicio order by id;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Servicio item = new Servicio();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setCostoHora(rs.getDouble("costo_hora"));
                lista.add(item);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
           
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return lista;
    }
}
