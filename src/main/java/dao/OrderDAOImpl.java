package dao;

import model.KeywordType;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.exceptions.OrderDAOException;
import dao.util.DBUtil;

import java.sql.ResultSet;

/**
 * The {@link OrderDAOImpl} class implements the CRUD methods
 * defined in the {@link IPrimaryEntityDAO} interface for
 * the Order primary table.
 */
public class OrderDAOImpl implements IPrimaryEntityDAO<Order> {
	
	private int id;
	private int customerID;
	private int quantity;
	private double price;
	private String shippingAddress;
	private String city;
	private String sql;
	private StringBuilder sqlBuilder;
	private ResultSet rs;
	private Order order;
	private ArrayList<Order> orders;
	private int lastInsertId;

	@Override
	public void insert(Order order) throws OrderDAOException {
		
		customerID = order.getCustomerID();
		quantity = order.getQuantity();
		price = order.getPrice();
		shippingAddress = order.getShippingAddress();
		city = order.getCity();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO `Order` (Customer_ID, Quantity, Price, Shipping_Address, City)");
		sqlBuilder.append(" VALUES(?, ?, ?, ?, ?)");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setString(1, Integer.toString(customerID));
			ps.setString(2, Integer.toString(quantity));
			ps.setString(3, Double.toString(price));
			ps.setString(4, shippingAddress);
			ps.setString(5, city);
			ps.executeUpdate();
			
		} catch(SQLException e1) {
			throw new OrderDAOException("SQL Error in Order record addition");
		}

	}

	@Override
	public Order get(int id) throws OrderDAOException {
		
		sql = "SELECT * FROM Order WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			ps.setString(1, Integer.toString(id));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				customerID = rs.getInt("Customer_ID");
				quantity = rs.getInt("Quantity");
				price = rs.getDouble("Price");
				shippingAddress = rs.getString("Shipping_Address");
				city = rs.getString("City");
			}
			
			order = new Order(id, customerID, quantity, price, shippingAddress, city);
			
			
		} catch(SQLException e2) {
			throw new OrderDAOException("SQL Error in retrieving a specified Order record");
		}
		return order;
	}

	@Override
	public void update(Order order) throws OrderDAOException {
		
		id = order.getId();
		customerID = order.getCustomerID();
		quantity = order.getQuantity();
		price = order.getPrice();
		shippingAddress = order.getShippingAddress();
		city = order.getCity();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE Order SET Customer_ID=?, Quantity=?, Price=?,");
		sqlBuilder.append(" Shipping_Address=?, City=? WHERE ID=?");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setString(1, Integer.toString(customerID));
			ps.setString(2, Integer.toString(quantity));
			ps.setString(3, Double.toString(price));
			ps.setString(4, shippingAddress);
			ps.setString(5,city);
			ps.setString(6, Integer.toString(id));
			ps.executeUpdate();
			
		} catch(SQLException e3) {
			throw new OrderDAOException("SQL Error in Order record update");
		}
	}

	@Override
	public void delete(int id) throws OrderDAOException {
		
		sql = "DELETE FROM Order WHERE ID=?";
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1, Integer.toString(id));
			ps.executeUpdate();
			
		} catch(SQLException e4) {
			throw new OrderDAOException("SQL Error in Order record deletion");
		}

	}

	@Override
	public List<Order> getAllByKeyword(KeywordType t, String kw) throws OrderDAOException {
		
		try {
			if(t.equals(KeywordType.CITY)) {
				sql = "SELECT * FROM Order WHERE City LIKE ?";
			} else {
				throw new SQLException();
			}
 			
		} catch (SQLException e5) {
			throw new OrderDAOException("SQL Error in forming query statement");
		}
		
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				orders = new ArrayList<>(100);
				
				ps.setString(1, kw+"%");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					id = rs.getInt("ID");
					customerID = rs.getInt("Customer_ID");
					quantity = rs.getInt("Quantity");
					price = Double.parseDouble(rs.getString("Price"));
					shippingAddress = rs.getString("Shipping_Address");
					city = rs.getString("City");
					
					orders.add(new Order(id, customerID, quantity, price, shippingAddress, city));
				}
			}
			
		} catch (SQLException e6) {
			throw new OrderDAOException("SQL Error in retrieving Order records");
		}
		
		return orders;
	}
	
	@Override
	public int getLastInsertID() throws OrderDAOException {
		
		sql = "SELECT LAST_INSERT_ID() as ID";
		
		try(Connection connection = DBUtil.getConnection();
				Statement smt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				rs = smt.executeQuery(sql);
				lastInsertId = rs.getInt("ID");
			}
			
		} catch(SQLException e7) {
			throw new OrderDAOException("SQL Error in LAST_INSERT_ID() from Order operation");
		}
		
		return lastInsertId;
	}

	@Override
	public List<Order> getAll() throws OrderDAOException {
		 
		sql = "SELECT * FROM `Order`";
		
		try(Connection connection = DBUtil.getConnection();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				orders = new ArrayList<>(100);
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					id = rs.getInt("ID");
					customerID = rs.getInt("Customer_ID");
					quantity = rs.getInt("Quantity");
					price = Double.parseDouble(rs.getString("Price"));
					shippingAddress = rs.getString("Shipping_Address");
					city = rs.getString("City");
					
					orders.add(new Order(id, customerID, quantity, price, shippingAddress, city));
				}
			}
			
		} catch (SQLException e8) {
			throw new OrderDAOException("SQL Error in retrieving Order records");
		}
		
		return orders;
	}
	
	
	
}
