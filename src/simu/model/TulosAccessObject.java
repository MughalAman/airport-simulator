package simu.model;

import java.sql.*;
import java.util.ArrayList;

public class TulosAccessObject implements ITulosDAO {
	private Connection conn;

	public TulosAccessObject() {
        final String URL = "jdbc:mysql://10.114.34.11/mysql";
        final String USERNAME = "username";
        final String PWD = "password";

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PWD);
        } catch (SQLException e) {
            printSQLExceptions("ValuuttaAccessObject()", e);
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    @Override
    public boolean createTulos(Tulos tulos) {
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO airportsim_tulokset VALUES(?,?,?,?,?,?,?,?,?,?)");) {
            statement.setDouble(1, tulos.getLoppuaika());
            statement.setInt(2, tulos.getAsiakasMaara());
            statement.setDouble(3, tulos.getCheckAktiiviAika());
            statement.setDouble(4, tulos.getTurvaTarkastus());
            statement.setDouble(5, tulos.getOleskeluAikaTurvaTarkastus());
            statement.setDouble(6, tulos.getCheckInKayttoaste());
            statement.setDouble(7, tulos.getLentokentanTeho());
            statement.setDouble(8, tulos.getCheckinPalveluaika());
            statement.setDouble(9, tulos.getTurvatarkastusJono());
            statement.setDouble(10, tulos.getTurvatarkastusJononPituus());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            printSQLExceptions("createValuutta()", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Tulos[] readTulokset() {
        ResultSet rs = null;
        ArrayList<Tulos> tulokset = new ArrayList<Tulos>();
        try (Statement statement = conn.createStatement();) {
            rs = statement.executeQuery("SELECT * FROM valuutta");
            while (rs.next()) {
                tulokset.add(new Tulos(rs.getDouble("loppuaika"), rs.getInt("asiakasmaara"), rs.getDouble("checkaktiiviaika"), rs.getDouble("turvatarkastus"), rs.getDouble("oleskeluaitaturvatarkastus"), rs.getDouble("checkinkayttoaste"), rs.getDouble("lentokentanteho"), rs.getDouble("checkinpalveluaika"), rs.getDouble("turvatarkastusjono"), rs.getDouble("turvatarkastusjononpituus")));
            }

        } catch (SQLException e) {
            printSQLExceptions("readValuutat()", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Tulos[] t = new Tulos[tulokset.size()];
        return (Tulos[]) tulokset.toArray(t);
    }



    private void printSQLExceptions(String methodName, SQLException e) {
        System.err.println("Metodi: " + methodName);
        do {
            System.err.println("Viesti: " + e.getMessage());
            System.err.println("Virhekoodi: " + e.getErrorCode());
            System.err.println("SQL-tilakoodi: " + e.getSQLState());
        } while (e.getNextException() != null);
    }
}
