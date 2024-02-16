package dao;

import dao.exceptions.OrderDAOException;
import dao.exceptions.OrderProductDAOException;
import dao.util.DBUtil;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The {@link OrderProductDAOImpl} class implements the CRUD methods
 * defined in the {@link IJoinEntityDAO} interface for
 * the Order_Product join table.
 */
public class OrderProductDAOImpl implements IJoinEntityDAO {
	
	private ArrayList<Integer> ids;
	private String sql;
	private OrderDAOImpl orderDAO;
	private int lastInsertId;
	private StringBuilder sqlBuilder;
	

	@Override
	public void insert(List<Integer> lst) throws OrderDAOException, OrderProductDAOException {
		
		ids.addAll(lst);
		lastInsertId = orderDAO.getLastInsertID();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO Order_Product (Order_ID, Product_ID)");
		sqlBuilder.append(" VALUES(?, ?)");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				for(Integer id : ids) {
					ps.setString(1, Integer.toString(lastInsertId));
					ps.setString(2, Integer.toString(id));
					ps.executeUpdate();
				}
			}
			
		} catch(SQLException e1) {
			throw new OrderProductDAOException("SQL Error in Order_Product record addition");
		}

	}

	@Override
	public void delete(int id1, int id2) throws OrderProductDAOException {
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("DELETE FROM Order_Product");
		sqlBuilder.append(" WHERE Order_ID=? AND Product_ID=?");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, Integer.toString(id1));
				ps.setString(2, Integer.toString(id2));
				ps.executeUpdate();
				
			}
		}  catch (SQLException e2) {
			throw new OrderProductDAOException("SQL Error in Order_Product record deletion");

		}

	}

}
