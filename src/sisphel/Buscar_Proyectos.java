
package sisphel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Buscar_Proyectos {

    Conexion con = new Conexion();
    DefaultTableModel md;

    void bpro(String valor, String filtro, JTable tbl_proyectos) {
        String[] columnas = {"NOMBRE","GRUPO", "ESTADO"};
        String[] registro = new String[4];

        String sql;
        md = new DefaultTableModel(null, columnas);

        Connection c = null;

        if (filtro.equals("Estado")) {
            sql = ("SELECT h.Nombre, h.Grupo, h.Estado FROM proyectos h WHERE h.Estado LIKE '%" + valor + "%'");

        } else if (filtro.equals("Nombre")) {
            sql = ("SELECT h. Nombre, h.Grupo, h.Estado FROM proyectos h WHERE h.Nombre LIKE '%" + valor + "%'");

        }else{
            sql = ("SELECT h. Nombre, h.Grupo, h.Estado FROM proyectos h WHERE h.Grupo LIKE '%" + valor + "%'");
        }

        try {

            c = con.conexion();
            PreparedStatement psql = c.prepareStatement(sql);
            ResultSet ab = psql.executeQuery(sql);

            while (ab.next()) {

                registro[0] = ab.getString("Nombre");
                registro[1] = ab.getString("Grupo");
                registro[2] =ab.getString("Estado");

                md.addRow(registro);

            }
            tbl_proyectos.setModel(md);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error durante el proceso" + e + "", "SISPHEL - [ERROR]", JOptionPane.ERROR_MESSAGE, new ImageIcon("src/img/error.png"));

        } finally {

            if (c != null) {

                try {

                    c.close();

                } catch (SQLException e) {

                    JOptionPane.showMessageDialog(null, "Error de desconexión" + e + "", "SISPHEL - [ERROR]", JOptionPane.ERROR_MESSAGE, new ImageIcon("src/img/error.png"));

                }
            }
        }
    }

}
