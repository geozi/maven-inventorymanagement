package dao;

import model.KeywordType;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.exceptions.InvalidKeywordDAOException;
import dao.exceptions.OrderDeleteDAOException;
import dao.exceptions.OrderGetAllDAOException;
import dao.exceptions.OrderGetByKeywordDAOException;
import dao.exceptions.OrderGetDAOException;
import dao.exceptions.OrderInsertDAOException;
import dao.exceptions.OrderUpdateDAOException;
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

	@Override
	public void insert(Order order) throws OrderInsertDAOException {
		
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
			
			ps.setInt(1, customerID);
			ps.setInt(2, quantity);
			ps.setDouble(3, price);
			ps.setString(4, shippingAddress);
			ps.setString(5, city);
			ps.executeUpdate();
			
		} catch(SQLException e1) {
			throw new OrderInsertDAOException();
		}

	}

	@Override
	public Order get(int id) throws OrderGetDAOException {
		
		sql = "SELECT * FROM Order WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				this.id = rs.getInt("ID");
				customerID = rs.getInt("Customer_ID");
				quantity = rs.getInt("Quantity");
				price = rs.getDouble("Price");
				shippingAddress = rs.getString("Shipping_Address");
				city = rs.getString("City");
			}
			
			order = new Order(this.id, customerID, quantity, price, shippingAddress, city);
			
			
		} catch(SQLException e2) {
			throw new OrderGetDAOException();
		}
		return order;
	}

	@Override
	public void update(Order order) throws OrderUpdateDAOException {
		
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
			
			ps.setInt(1, customerID);
			ps.setInt(2, quantity);
			ps.setDouble(3, price);
			ps.setString(4, shippingAddress);
			ps.setString(5,city);
			ps.setInt(6, id);
			ps.executeUpdate();
			
		} catch(SQLException e3) {
			throw new OrderUpdateDAOException();
		}
	}

	@Override
	public void delete(int id) throws OrderDeleteDAOException {
		
		sql = "DELETE FROM Order WHERE ID=?";
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch(SQLException e4) {
			throw new OrderDeleteDAOException();
		}

	}

	@Override
	public List<Order> getAllByKeyword(KeywordType t, String kw) throws InvalidKeywordDAOException, OrderGetByKeywordDAOException {
		
		try {
			if(t.equals(KeywordType.CITY)) {
				sql = "SELECT * FROM Order WHERE City LIKE ?";
			} else {
				throw new SQLException();
			}
 			
		} catch (SQLException e5) {
			throw new InvalidKeywordDAOException();
		}
		
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				orders = new ArrayList<>(LIST_CAPACITY);
				
				ps.setString(1, kw+"%");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					id = rs.getInt("ID");
					customerID = rs.getInt("Customer_ID");
					quantity = rs.getInt("Quantity");
					price = rs.getDouble("Price");
					shippingAddress = rs.getString("Shipping_Address");
					city = rs.getString("City");
					
					orders.add(new Order(id, customerID, quantity, price, shippingAddress, city));
				}
			}
			
		} catch (SQLException e6) {
			throw new OrderGetByKeywordDAOException();
		}
		
		return orders;
	}


	@Override
	public List<Order> getAll() throws OrderGetAllDAOException {
		 
		sql = "SELECT * FROM `Order`";
		
		try(Connection connection = DBUtil.getConnection();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				orders = new ArrayList<>(LIST_CAPACITY);
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					id = rs.getInt("ID");
					customerID = rs.getInt("Customer_ID");
					quantity = rs.getInt("Quantity");
					price = rs.getDouble("Price");
					shippingAddress = rs.getString("Shipping_Address");
					city = rs.getString("City");
					
					orders.add(new Order(id, customerID, quantity, price, shippingAddress, city));
				}
			}
			
		} catch (SQLException e8) {
			throw new OrderGetAllDAOException();
		}
		
		return orders;
	}
}
