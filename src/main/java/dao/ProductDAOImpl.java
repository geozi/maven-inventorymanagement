package dao;

import model.KeywordType;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.exceptions.ProductDAOException;
import dao.util.DBUtil;

import java.sql.ResultSet;

/**
 * The {@link ProductDAOImpl} class implements the CRUD methods
 * defined in the {@link IPrimaryEntityDAO} interface for
 * the Product primary table.
 */
public class ProductDAOImpl implements IPrimaryEntityDAO<Product> {
	
	private int id;
	private String name;
	private String description;
	private double unitPrice;
	private int quantity;
	private int supplierID;
	private String sql;
	private StringBuilder sqlBuilder;
	private ResultSet rs;
	private Product product;
	private ArrayList<Product> products;
	private int lastInsertId;

	@Override
	public void insert(Product product) throws ProductDAOException {
		
		name = product.getName();
		description = product.getDescription();
		unitPrice = product.getUnitPrice();
		quantity = product.getQuantity();
		supplierID = product.getSupplierID();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO Product (Name, Description, Unit_Price, Quantity, Supplier_ID)");
		sqlBuilder.append(" VALUES (?, ?, ?, ?, ?)");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql) ){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, name);
				ps.setString(2, description);
				ps.setString(3, Double.toString(unitPrice));
				ps.setString(4, Integer.toString(quantity));
				ps.setString(5, Integer.toString(supplierID));
				ps.executeUpdate();
			}

		} catch(SQLException e1) {
			throw new ProductDAOException("SQL Error in Product record addition");
		}

	}

	@Override
	public Product get(int id) throws ProductDAOException {
		
		sql = "SELECT * FROM Product WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, Integer.toString(id));
				rs = ps.executeQuery();
				
				while(rs.next()) {
					name = rs.getString("Name");
					description = rs.getString("Description");
					unitPrice = Double.parseDouble(rs.getString("Unit_Price")) ;
					quantity = rs.getInt("Quantity");
					supplierID = rs.getInt("Supplier_ID");
				}
				
				product = new Product(id, name, description, unitPrice, quantity, supplierID);
			}
			
		} catch(SQLException e2) {
			throw new ProductDAOException("SQL Error in retrieving a specified Product record");
		}
		
		return product;
	}

	@Override
	public void update(Product product) throws ProductDAOException {
		
		id = product.getId();
		name = product.getName();
		description = product.getDescription();
		unitPrice = product.getUnitPrice();
		quantity = product.getQuantity();
		supplierID = product.getSupplierID();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE Product SET Name=?, Description=?, Unit_Price=?, Quantity=?,");
		sqlBuilder.append(" Supplier_ID=? WHERE ID=?");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, name);
				ps.setString(2, description);
				ps.setString(3, Double.toString(unitPrice));
				ps.setString(4, Integer.toString(quantity));
				ps.setString(5, Integer.toString(supplierID));
				ps.setString(6, Integer.toString(id));
				ps.executeUpdate();
			}
			
			
		} catch(SQLException e3) {
			throw new ProductDAOException("SQL Error in Product record update");
		}

	}

	@Override
	public void delete(int id) throws ProductDAOException {
		
		sql = "DELETE FROM Product WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1,Integer.toString(id));
				ps.executeUpdate();
			}
			
		}catch(SQLException e4) {
			throw new ProductDAOException("SQL Error in Product record deletion");
		}
	}

	@Override
	public List<Product> getAllByKeyword(KeywordType t, String kw) throws ProductDAOException {
		
		try {
			if(t.equals(KeywordType.NAME)) {
				sql = "SELECT * FROM Product WHERE Name LIKE ?";
			} else {
				throw new SQLException();
			}
			
		} catch (SQLException e5) {
			throw new ProductDAOException("SQL Error in forming query statement");
		}
		
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				products = new ArrayList<>(100);
				
				ps.setString(1, kw+"%");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					id = rs.getInt("ID");
					name = rs.getString("Name");
					description = rs.getString("Description");
					unitPrice =Double.parseDouble(rs.getString("Unit_Price"));
					quantity = rs.getInt("Quantity");
					supplierID = rs.getInt("Supplier_ID");
					
					products.add(new Product(name, description, unitPrice, quantity, supplierID));
				}
			}
			
		} catch(SQLException e6) {
			throw new ProductDAOException("SQL Error in retrieving Product records");
		}
		
		return products;
	}
	
	@Override
	public int getLastInsertID() throws ProductDAOException {
		
		sql = "SELECT LAST_INSERT_ID() as ID";
		
		try(Connection connection = DBUtil.getConnection();
				Statement smt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				rs = smt.executeQuery(sql);
				lastInsertId = rs.getInt("ID");
			}
			
		} catch(SQLException e7) {
			throw new ProductDAOException("SQL Error in LAST_INSERT_ID() from Product operation");
		}
		
		return lastInsertId;
	}

	@Override
	public List<Product> getAll() throws ProductDAOException {
		
		sql = "SELECT * FROM Product";
		
		try(Connection connection = DBUtil.getConnection();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				products = new ArrayList<>(100);
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					id = rs.getInt("ID");
					name = rs.getString("Name");
					description = rs.getString("Description");
					unitPrice =Double.parseDouble(rs.getString("Unit_Price"));
					quantity = rs.getInt("Quantity");
					supplierID = rs.getInt("Supplier_ID");
					
					products.add(new Product(name, description, unitPrice, quantity, supplierID));
				}
			}
			
		} catch(SQLException e8) {
			throw new ProductDAOException("SQL Error in retrieving Product records");
		}
		
		return products;
	}
	
	

}
