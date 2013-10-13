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
                Campo vo = new Campo();
                vo.setId(rs.getInt("id"));
                vo.setDescripcion(rs.getString("nombre"));
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

    public Campo insertar(Campo campo) throws DAOExcepcion {
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
            stmt.setInt(5, campo.getLocal().getId());

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

    public Campo obtener(int idCampo) throws DAOExcepcion {
        Campo campo = new Campo();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select id, descripcion, estado, tipo, costoHora from campo where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idCampo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                campo.setId(rs.getInt(1));
                campo.setDescripcion(rs.getString(2));
                campo.setEstado(rs.getInt(3));
                campo.setTipo(rs.getInt(4));
                campo.setCostoHora(rs.getDouble(5));
            }
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

    public void eliminar(int idCampo) throws DAOExcepcion {
        String query = "delete from campo WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idCampo);
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

    public Campo actualizar(Campo campo) throws DAOExcepcion {
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
            stmt.setInt(5, campo.getLocal().getId());
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

    public Collection<Campo> listar() throws DAOExcepcion {
        Collection<Campo> c = new ArrayList<Campo>();
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
