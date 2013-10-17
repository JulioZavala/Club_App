package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import app.zelper.ConexionBD;
import app.model.Campo;
import java.util.List;

public class CampoDAO extends BaseDAO {

    public Campo save(Campo campo) throws DAOExcepcion {
        String query = "insert into campo(descripcion,estado,tipo,costoHora,local) values (?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, campo.getDescripcion());
            stmt.setInt(2, campo.getEstado());
            stmt.setInt(3, campo.getTipo());
            stmt.setDouble(4, campo.getCostoHora());
            stmt.setLong(5, campo.getLocal().getId());

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
            campo.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }

    public Campo get(Campo campo) throws DAOExcepcion {
        Campo item = new Campo();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select id, descripcion, estado, tipo, costoHora from campo where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, campo.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                item.setId(rs.getInt(1));
                item.setDescripcion(rs.getString(2));
                item.setEstado(rs.getInt(3));
                item.setTipo(rs.getInt(4));
                item.setCostoHora(rs.getDouble(5));
                //item.setLocal();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return item;
    }

    public void delete(Campo campo) throws DAOExcepcion {
        String query = "delete from campo WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, campo.getId());
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

    public Campo update(Campo campo) throws DAOExcepcion {
        String query = "update campo set descripcion=?,estado=?,tipo=?,costoHora=?,local=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, campo.getDescripcion());
            stmt.setInt(2, campo.getEstado());
            stmt.setInt(3, campo.getTipo());
            stmt.setDouble(4, campo.getCostoHora());
            stmt.setLong(5, campo.getLocal().getId());
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
        return campo;
    }

    public List<Campo> list() throws DAOExcepcion {
        List<Campo> lista = new ArrayList<Campo>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "select id, descripcion, estado, tipo, costoHora from campo order by id";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campo item = new Campo();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(rs.getInt("estado"));
                item.setTipo(rs.getInt("tipo"));
                item.setCostoHora(rs.getInt("costoHora"));
                lista.add(item);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return lista;
    }
}
