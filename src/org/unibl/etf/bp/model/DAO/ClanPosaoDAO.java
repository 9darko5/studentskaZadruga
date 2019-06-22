package org.unibl.etf.bp.model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DTO.ClanPosaoDTO;

public class ClanPosaoDAO {
	public static void addClanPosao(ClanPosaoDTO clanPosao) {
        Connection connection = null;
        CallableStatement statement = null;
        PreparedStatement ps=null;
        
        Statement st=null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            /*
             * create procedure insert_into_clan_posao(in cJmb varchar(13), in posaoId integer, in brojRadnihSati float)
             */
            System.out.println("bbbbb: "+postojiClanPosao(clanPosao.getcJMB(), clanPosao.getPosaoId()));
            if(postojiClanPosao(clanPosao.getcJMB(), clanPosao.getPosaoId())) {
            	System.out.println("USLO");
            	//update_clan_posao(in cJmb varchar(13), in posaoId integer, in brojRadnihSati float, in ukupnoZaradjeno float)
            	statement = connection.prepareCall("{call update_clan_posao(?,?,?,?)}");
	            statement.setString(1, clanPosao.getcJMB());
	            statement.setInt(2, clanPosao.getPosaoId());
	            statement.setFloat(3, clanPosao.getBrojRadnihSati());
	            statement.setFloat(4, clanPosao.getZaradjeno());
	
	            statement.executeQuery();
            }
            else {
            	System.out.println("PosaoId="+clanPosao.getPosaoId());
            	/*
            	 * 
            	 * create procedure insert_into_clan_posao(in cJmb varchar(13), in posaoId integer, in brojRadnihSati float, in ukupnoZaradjeno float, in dat date)
begin
	insert into clan_posao(BrojRadnihSati, Zaradjeno, C_JMB,PosaoId, Datum) values (brojRadnihSati, ukupnoZaradjeno, cJmb, posaoId, dat);
	update posao set BrojPotrebnihRadnika=BrojPotrebnihRadnika-1 where PosaoId=posaoId;
            	 */
	            ps = connection.prepareStatement("insert into clan_posao(BrojRadnihSati, Zaradjeno, C_JMB,PosaoId, Datum) values (?,?,?,?,?)");
	            ps.setString(3, clanPosao.getcJMB());
	            ps.setInt(4, clanPosao.getPosaoId());
	            ps.setFloat(1, clanPosao.getBrojRadnihSati());
	            ps.setFloat(2, clanPosao.getZaradjeno());
	            ps.setDate(5, new java.sql.Date(clanPosao.getDatum().getTime()));
	
	            ps.executeUpdate();
	            st=connection.createStatement();
	            //st.execute("update posao set BrojPotrebnihRadnika=BrojPotrebnihRadnika-1 where PosaoId="+clanPosao.getPosaoId());
	            if(brojRadnikaZaPosao(clanPosao.getPosaoId())==0) {
	            	PosaoDAO.deletePosao(clanPosao.getPosaoId());
	            }
            }
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
	
	public static int brojRadnikaZaPosao(int posaoId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select BrojPotrebnihRadnika from posao where PosaoId="+posaoId);
			if(rs.next())
			{
				return rs.getInt(1);
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
	return -1;
	}
	
	public static String opisPosla(int posaoId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select OpisPosla from posao where PosaoId="+posaoId);
			if(rs.next())
			{
				return rs.getString(1);
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
	return null;
	}
	
	public static boolean postojiClanPosao(String cJmb, int posaoId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		boolean tmp=false;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			System.out.println(connection);
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from clan_posao where PosaoId="+posaoId+" and C_JMB="+cJmb);
			//System.out.println("next(): "+rs.next());
			if(rs.next()){
				tmp=true;
			}
			else {
				tmp=false;
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
		System.out.println("TMP="+tmp);
		return tmp;
	}
}
