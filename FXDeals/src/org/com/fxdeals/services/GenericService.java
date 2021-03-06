/**
 * 
 */
package org.com.fxdeals.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.com.fxdeals.dto.FXDeal;

/**
 * @author Administrator
 *
 */
public class GenericService {

	/**
	 * @return established Connection object
	 * @throws NamingException
	 * @throws SQLException
	 */
	public Connection getConnection() throws NamingException, SQLException {
		try {

			Connection connection = null;
			Context cxt = new InitialContext();
			DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/taskDB");
			System.out.println("Data Source :: " + ds);

			connection = ds.getConnection();

			return connection;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method to save the fx deal request to the DB
	 * @param fxDeal
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public void addFXDealRequest(FXDeal fxDeal) throws SQLIntegrityConstraintViolationException {
		
		System.out.println(fxDeal.getDealUniqueId() + " Starting save the request to the DB");
		PreparedStatement ps = null;
		try {
			
			// change java util date to sql date 
			Timestamp sqlDate = new Timestamp(fxDeal.getDealTime().getTime());
			System.out.println("sqlDate :: " + sqlDate);
			Connection connection = getConnection();
			String query = "INSERT INTO FX_DEALS (FROM_CURRENCY, TO_CURRENCY, DEAL_TIME, DEAL_AMOUNT,DEAL_UNIQUE_ID) VALUES (?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setString(1, fxDeal.getFromCurrency());
			ps.setString(2, fxDeal.getToCurrency());
			ps.setTimestamp(3, sqlDate);
			ps.setString(4, fxDeal.getDealAmount());
			ps.setString(5, fxDeal.getDealUniqueId());
			
			int rows = ps.executeUpdate();
			ps.close();
			connection.close();

			System.out.println(rows + " Rows inserted");
		} catch (SQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
