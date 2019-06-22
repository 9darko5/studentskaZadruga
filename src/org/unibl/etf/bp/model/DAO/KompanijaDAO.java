package org.unibl.etf.bp.model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DTO.ClanDTO;
import org.unibl.etf.bp.model.DTO.FizickoLiceDTO;
import org.unibl.etf.bp.model.DTO.KompanijaDTO;

public class KompanijaDAO {
	public static void addKompanija(KompanijaDTO kompanija) {
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall("{call insert_into_kompanija(?,?,?,?)}");
            statement.setInt(1, kompanija.getZIPkod());
            statement.setString(2, kompanija.getNaziv());
            statement.setString(3, kompanija.getAdresa());
            statement.setString(4, kompanija.getJIB());
            

            statement.executeQuery();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().checkIn(connection);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                	ex.printStackTrace();
                }
            }
        }
    }

    public static void deleteKompanija(String jib) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            statement.execute("UPDATE kompanija set Aktivan = false where JIB = " + jib);

        } catch (SQLException ex) {
        	ex.printStackTrace();
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().checkIn(connection);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                	ex.printStackTrace();
                }
            }
        }
    }
    
    public static List<KompanijaDTO> pretragaKompanija(String naziv){
    	List<KompanijaDTO> listaKompanija=new ArrayList<>();
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from kompanija natural join poslodavac");
			while(rs.next())
			{
				// KompanijaDTO(int zIPcode, String naziv, String adresa, String JIB)
				if(rs.getString(2).contains(naziv)) {
					KompanijaDTO kompanija=new KompanijaDTO(rs.getInt(6), rs.getString(2), rs.getString(3), rs.getString(4));
					listaKompanija.add(kompanija);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return listaKompanija;
    }

}
