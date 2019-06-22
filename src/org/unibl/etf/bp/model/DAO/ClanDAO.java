package org.unibl.etf.bp.model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DTO.*;

public class ClanDAO {

    public static void addClan(ClanDTO clan) {
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            /*
             * in cJMB char(13), in ime varchar(50), in prezime varchar(50), 
								  in adresa varchar(50), in strucnaSprema varchar(50), in zipKod INTEGER,
                                  in brojClanskeKarte integer, in brojLK varchar(50),
                                  in datumIstekaClanskeKarte date, in aJMB char(13
             */
            statement = connection.prepareCall("{call insert_into_clan(?,?,?,?,?,?,?,?,?)}");
            statement.setString(1, clan.getJMB());
            statement.setString(2, clan.getIme());
            statement.setString(3, clan.getPrezime());
            statement.setString(4, clan.getAdresa());
            statement.setInt(5, clan.getZIPkod());
            statement.setInt(6, clan.getBrojClanskeKarte());
            statement.setString(7, clan.getBrojLK());
            statement.setDate(8, new java.sql.Date(clan.getDatumIstekaClanskeKarte().getTime()));
            statement.setString(9, clan.getaJMB());

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
    
    public static List<ClanDTO> pretragaClanova(int brojCK){
    	List<ClanDTO> listaClanova=new ArrayList<>();
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from clan");
			while(rs.next())
			{
				//ClanDTO(String jMB, String ime, String prezime, String adresa, int zIPkod,
				//int brojClanskeKarte, String brojLK, Date datumIstekaClanskeKarte, String aJMB) 
				if(rs.getInt(6)==brojCK) {
					ClanDTO clan=new ClanDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
							rs.getInt(6), rs.getString(7), rs.getDate(8), rs.getString(9));
					listaClanova.add(clan);
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
		return listaClanova;
    }

    public static void deleteClan(String c_jmb) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            statement.execute("UPDATE clan set Aktivan = false where C_JMB = " + c_jmb);

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
}
