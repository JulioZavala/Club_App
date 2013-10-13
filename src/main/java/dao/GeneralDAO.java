package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import excepcion.DAOExcepcion;
import model.General;
//import ZelpPer.ConexionDB;
import model.Servicio;

public class GeneralDAO extends BaseDAO {

    public Collection<General> buscarPorNombre(String nombre)
            throws DAOExcepcion {
        String query = "select id, usuario, password  from General where id like ?";
        Collection<General> lista = new ArrayList<General>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + nombre + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                General vo = new General();
                vo.setId(rs.getInt("id"));
                vo.setUsuario(rs.getString("usuario"));
                vo.setPassword(rs.getString("password"));
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

    public Servicio insertar(Servicio vo) throws DAOExcepcion {
        String query = "insert into servicio(desripcion,costo_hora) values (?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, vo.getDescripcion());
            stmt.setDouble(2, vo.getCosto_hora());
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
            vo.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return vo;
    }

    public Servicio obtener(int idServicio) throws DAOExcepcion {
        Servicio vo = new Servicio();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select id, descripcion, costo_hora from servicio where id=?";
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idServicio);
            rs = stmt.executeQuery();
            if (rs.next()) {
                vo.setId(rs.getInt(1));
                vo.setDescripcion(rs.getString(2));
                vo.setCosto_hora(rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return vo;
    }

    public void eliminar(int IdServicio) throws DAOExcepcion {
        String query = "delete from servicio WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, IdServicio);
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
            con = ConexionDB.obtenerConexion();
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
            con = ConexionDB.obtenerConexion();
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
