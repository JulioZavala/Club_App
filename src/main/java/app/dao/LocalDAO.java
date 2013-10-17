package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.Local;
import app.zelper.ConexionBD;
import java.util.List;

public class LocalDAO extends BaseDAO {

    public List<Local> buscarPorNombre(String nombre)
            throws DAOExcepcion {
        String query = "select * from local where descripcion like ?";
        List<Local> lista = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + nombre + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Local item = new Local();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("direccion"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setMaps(rs.getString("maps"));
                item.setTelefono(rs.getString("telefono"));
                item.setEstado(rs.getInt("estado"));
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
        System.out.println(lista.size());
        return lista;
    }

    public Local save(Local local) throws DAOExcepcion {
        String query = "insert into local(direccion, descripcion, estado, maps, telefono) values (?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);

            stmt.setString(1, local.getDireccion());
            stmt.setString(2, local.getDescripcion());
            stmt.setInt(3, local.getEstado());
            stmt.setString(4, local.getMaps());
            stmt.setString(5, local.getTelefono());

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
            local.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return local;
    }

    public Local get(Local local) throws DAOExcepcion {
        Local item = new Local();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select * from local where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, local.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                item.setId(rs.getInt("id"));
                item.setDireccion(rs.getString("direccion"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(rs.getInt("estado"));
                item.setMaps(rs.getString("maps"));
                item.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return item;
    }

    public void delete(Local local) throws DAOExcepcion {

        String query = "delete from local WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, local.getId());
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

    public Local update(Local local) throws DAOExcepcion {
        String query = "update local set direccion=?,descripcion=?,estado=?,maps=?,telefono=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, local.getDireccion());
            stmt.setString(2, local.getDescripcion());
            stmt.setInt(3, local.getEstado());
            stmt.setString(4, local.getMaps());
            stmt.setString(5, local.getDescripcion());
            stmt.setLong(6, local.getId());
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
        return local;
    }

    public List<Local> list() throws DAOExcepcion {
        List<Local> lista = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "select * from local order by id";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Local item = new Local();
                item.setId(rs.getInt("id"));
                item.setDireccion(rs.getString("direccion"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(rs.getInt("estado"));
                item.setMaps(rs.getString("maps"));
                item.setTelefono(rs.getString("telefono"));
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
