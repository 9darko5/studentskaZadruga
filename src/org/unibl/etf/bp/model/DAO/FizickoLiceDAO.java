package org.unibl.etf.bp.model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DTO.FizickoLiceDTO;
import org.unibl.etf.bp.model.DTO.KompanijaDTO;

public class FizickoLiceDAO {
	public static void addFizickoLice(FizickoLiceDTO fizickoLice) {
        Connection connection = null;
        CallableStatement statement = null;
        try {
        	
        	/*
        	 * FizickoLiceDTO(int poslodavacId, int zIPcode, String ime, String prezime, String brojLK) {
		super(poslodavacId, zIPcode);
		
		insert_into_fizicko_lice(in ZipKod integer, in ime varchar(50), in prezime varchar(50), in brojLK varchar(20))
        	 */
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall("{call insert_into_fizicko_lice(?,?,?,?)}");
            statement.setInt(1, fizickoLice.getZIPkod());
            statement.setString(2, fizickoLice.getIme());
            statement.setString(3, fizickoLice.getPrezime());
            statement.setString(4, fizickoLice.getBrojLK());
            

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

    public static void deleteKompanija(String brojLk) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            statement.execute("UPDATE fizicko_lice set Aktivan = false where brojLK = " + brojLk);

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
    
    public static List<FizickoLiceDTO> pretragaFizickihLica(String naziv){
    	List<FizickoLiceDTO> listaFL=new ArrayList<>();
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from fizicko_lice natural join poslodavac");
			while(rs.next())
			{
				// FizickoLiceDTO(int zIPcode, String ime, String prezime, String brojLK) 
				String imeIPrezime=rs.getString(2)+" "+rs.getString(3);
				if(imeIPrezime.contains(naziv)) {
					FizickoLiceDTO fizickoLice=new FizickoLiceDTO(rs.getInt(6), rs.getString(2), rs.getString(3), rs.getString(4));
					listaFL.add(fizickoLice);
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
		return listaFL;
    }
}
