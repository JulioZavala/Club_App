package app.dao;


import app.dao.BaseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import app.excepcion.DAOExcepcion;
import app.model.Local;

import app.zelper.ConexionBD;


public class LocalDAO extends BaseDAO{
 
 
    public Collection<Local> buscarPorNombre(String nombre)
            throws DAOExcepcion {
            String query = "select id, direccion, descripcion from local where descripcion like ?";
        Collection<Local> lista = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + nombre + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Local vo = new Local();
                vo.setId(rs.getInt("id"));
                vo.setDescripcion(rs.getString("direccion"));
                vo.setDescripcion(rs.getString("descripcion"));
                lista.add(vo);
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

    public Local insertar(Local local) throws DAOExcepcion {
        String query = "insert into local(id, direccion, descripcion, estado, maps, telefono) values (?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, local.getId());
            stmt.setString(2, local.getDireccion());
            stmt.setString(3, local.getDescripcion());
            stmt.setInt(4, local.getEstado());
            stmt.setString(5, local.getMaps());
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

    public Local obtener(Local local) throws DAOExcepcion {
        int idLocal = local.getId();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select id, direccion, descripcion, estado, maps, telefono from local where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLocal);
            rs = stmt.executeQuery();
            if (rs.next()) {
                local.setId(rs.getInt(1));
                local.setDireccion(rs.getString(2));
                local.setDescripcion(rs.getString(3));
                local.setEstado(rs.getInt(4));
                local.setMaps(rs.getString(5));
                local.setTelefono(rs.getString(6));
            }
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

    public void eliminar(Local local) throws DAOExcepcion {
        int idLocal = local.getId();
        String query = "delete from local WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLocal);
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

    public Local actualizar(Local local) throws DAOExcepcion {
        String query = "update campo set direccion=?,descripcion=?,estado=?,maps=?,telefono=? where id=?";
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
            stmt.setInt(6, local.getId());
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

    public Collection<Local> listar() throws DAOExcepcion {
        Collection<Local> c = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "id, direccion, descripcion, estado, maps, telefono from local order by direccion";
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
                c.add(item);
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
