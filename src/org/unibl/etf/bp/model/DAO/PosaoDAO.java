package org.unibl.etf.bp.model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.bp.connectionPool.ConnectionPool;
import org.unibl.etf.bp.model.DTO.PosaoDTO;

public class PosaoDAO {
	public static void updatePosao(PosaoDTO posao) {
		Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            statement.execute("UPDATE posao set BrojPotrebnihRadnika="+posao.getBrojPotrebnihRadnika()+", CijenaPoSatu="+posao.getCijenaPoSatu()+" where PosaoId = " + getIdPosao(posao.getOpisPosla()));

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
	
	public static void addPosao(PosaoDTO posao) {
		Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall("{call insert_into_posao(?,?,?,?,?)}");
            statement.setDouble(1, posao.getCijenaPoSatu());
            statement.setString(2, posao.getOpisPosla());
            statement.setInt(3, posao.getBrojPotrebnihRadnika());
            statement.setString(4, posao.getaJmb());
            statement.setInt(5, posao.getPoslodavacId());

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

    public static void deletePosao(int posaoId) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            statement.execute("UPDATE posao set Zavrsen = true where PosaoId = " + posaoId);

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
    
    public static List<PosaoDTO> pretragaPosla(String naziv){
    	List<PosaoDTO> listaPoslova=new ArrayList<>();
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from posao");
			while(rs.next())
			{
				//PosaoDTO(double cijenaPoSatu, int brojPotrebnihRadnika, String opisPosla, String aJmb, int poslodavacId)
				if(rs.getString(3).contains(naziv)) {
					PosaoDTO posao=new PosaoDTO(rs.getFloat(2), rs.getInt(5), rs.getString(3), rs.getString(6), rs.getInt(7));
					listaPoslova.add(posao);
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
		return listaPoslova;
    }
    
    public static float cijenaPoslaPoSatu(int posaoId) {
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select CijenaPoSatu from posao where PosaoId="+posaoId);
			if(rs.next())
			{
				return rs.getFloat(1);
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
    public static int getIdPosao(String posao){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select OpisPosla, PosaoId from prikaz_poslova");
			while (rs.next()) {
				if(rs.getString(1).equals(posao)) {
					return rs.getInt(2);
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
		return -1;
	}
	
	public static String getOpisPosla(int posaoId){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			statement = connection.createStatement();
			rs = statement.executeQuery("select OpisPosla from posao where PosaoId="+posaoId);
			if(rs.next()) {
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
	
   
}
