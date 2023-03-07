package simu.model;
import java.sql.*;

public class PalvelupisteetAccessObject implements PalvelupisteetDao{
    private Connection conn;

    private int aloitus, info, check_in, check_in_auto, security_check, security_gate, gate, plane;

    public PalvelupisteetAccessObject() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://10.114.34.11/airportsim_palvelupisteet?user=username&password=password");
        } catch (SQLException e) {
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int[] readPalvelupisteet(){
        try {
            String sql = "SELECT aloitus, info, check_in, check_in_auto, security_check, security_gate, gate, plane FROM airportsim_palvelupisteet";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                aloitus = rs.getInt("aloitus");
                info = rs.getInt("info");
                check_in = rs.getInt("check_in");
                check_in_auto = rs.getInt("check_in_auto");
                security_check = rs.getInt("security_check");
                security_gate = rs.getInt("security_gate");
                gate = rs.getInt("gate");
                plane = rs.getInt("plane");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new int[]{aloitus, info, check_in, check_in_auto, security_check, security_gate, gate, plane};
    }
}
