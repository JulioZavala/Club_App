
package app.dao;

import app.model.SocioAlquiler;
import app.zelper.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class SocioAlquilerDAO extends BaseDAO {
    
    
    
    public SocioAlquiler save(SocioAlquiler socioAlquiler) throws DAOExcepcion {
        String query = "insert into solicitud_alquiler(hora_inicio, hora_fin, dia, servicios, estado, id_socio, id_campo) values (?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, socioAlquiler.getHoraInicio());
            stmt.setString(2, socioAlquiler.getHoraFin());
            stmt.setDate(3, socioAlquiler.getDia());
            stmt.setString(4, socioAlquiler.getServicios());
            stmt.setInt(5, socioAlquiler.getEstado());
            stmt.setLong(6, socioAlquiler.getSocio().getId());
            stmt.setLong(7, socioAlquiler.getCampo().getId());
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
            socioAlquiler.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return socioAlquiler;
    }

    public SocioAlquiler get(SocioAlquiler socioAlquiler) throws DAOExcepcion {
        SocioAlquiler item = new SocioAlquiler();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "select * from solicitud_alquiler where id=?";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, socioAlquiler.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                item.setId(rs.getLong("id"));
                item.setHoraInicio(rs.getString("hora_inicio"));
                item.setHoraFin(rs.getString("hora_fin"));
                item.setDia(rs.getDate("dia"));
                item.setServicios(rs.getString("servicios"));
                item.setEstado(rs.getInt("estado"));
                //item.setSocio();
                //item.setCampo();
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

    public void delete(SocioAlquiler socioAlquiler) throws DAOExcepcion {

        String query = "delete from solicitud_alquiler WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, socioAlquiler.getId());
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

    public SocioAlquiler update(SocioAlquiler socioAlquiler) throws DAOExcepcion {
        String query = "update solicitud_alquiler set hora_inicio=?,hora_fin=?,dia=?,servicios=?,estado=?,id_socio=?,id_campo=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, socioAlquiler.getHoraInicio());
            stmt.setString(2, socioAlquiler.getHoraFin());
            stmt.setDate(3, socioAlquiler.getDia());
            stmt.setString(4, socioAlquiler.getServicios());
            stmt.setInt(5, socioAlquiler.getEstado());
            stmt.setLong(6, socioAlquiler.getSocio().getId());
            stmt.setLong(7, socioAlquiler.getCampo().getId());
            stmt.setLong(8, socioAlquiler.getId());
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
        return socioAlquiler;
    }

    public List<SocioAlquiler> list() throws DAOExcepcion {
        List<SocioAlquiler> lista = new ArrayList<SocioAlquiler>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "select * from solicitud_alquiler order by id;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SocioAlquiler item = new SocioAlquiler();
                item.setId(rs.getLong("id"));
                item.setHoraInicio(rs.getString("hora_inicio"));
                item.setHoraFin(rs.getString("hora_fin"));
                item.setDia(rs.getDate("dia"));
                item.setServicios(rs.getString("servicios"));
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
        return lista;
    }
    
    
}
