package dao;

import model.KeywordType;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.exceptions.InvalidKeywordDAOException;
import dao.exceptions.ProductDeleteDAOException;
import dao.exceptions.ProductGetAllDAOException;
import dao.exceptions.ProductGetByKeywordDAOException;
import dao.exceptions.ProductGetDAOException;
import dao.exceptions.ProductInsertDAOException;
import dao.exceptions.ProductUpdateDAOException;
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

	@Override
	public void insert(Product product) throws ProductInsertDAOException {
		
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
				ps.setDouble(3, unitPrice);
				ps.setInt(4, quantity);
				ps.setInt(5, supplierID);
				ps.executeUpdate();
			}

		} catch(SQLException e1) {
			throw new ProductInsertDAOException();
		}

	}

	@Override
	public Product get(int id) throws ProductGetDAOException {
		
		sql = "SELECT * FROM Product WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					this.id = rs.getInt("ID");
					name = rs.getString("Name");
					description = rs.getString("Description");
					unitPrice = rs.getDouble("Unit_Price") ;
					quantity = rs.getInt("Quantity");
					supplierID = rs.getInt("Supplier_ID");
				}
				
				product = new Product(this.id, name, description, unitPrice, quantity, supplierID);
			}
			
		} catch(SQLException e2) {
			throw new ProductGetDAOException();
		}
		
		return product;
	}

	@Override
	public void update(Product product) throws ProductUpdateDAOException {
		
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
				ps.setDouble(3, unitPrice);
				ps.setInt(4, quantity);
				ps.setInt(5, supplierID);
				ps.setInt(6, id);
				ps.executeUpdate();
			}
			
			
		} catch(SQLException e3) {
			throw new ProductUpdateDAOException();
		}

	}

	@Override
	public void delete(int id) throws ProductDeleteDAOException {
		
		sql = "DELETE FROM Product WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setInt(1,id);
				ps.executeUpdate();
			}
			
		}catch(SQLException e4) {
			throw new ProductDeleteDAOException();
		}
	}

	@Override
	public List<Product> getAllByKeyword(KeywordType t, String kw) throws InvalidKeywordDAOException, ProductGetByKeywordDAOException {
		
		try {
			if(t.equals(KeywordType.NAME)) {
				sql = "SELECT * FROM Product WHERE Name LIKE ?";
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
				
				products = new ArrayList<>(LIST_CAPACITY);
				
				ps.setString(1, kw+"%");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					id = rs.getInt("ID");
					name = rs.getString("Name");
					description = rs.getString("Description");
					unitPrice =rs.getDouble("Unit_Price");
					quantity = rs.getInt("Quantity");
					supplierID = rs.getInt("Supplier_ID");
					
					products.add(new Product(name, description, unitPrice, quantity, supplierID));
				}
			}
			
		} catch(SQLException e6) {
			throw new ProductGetByKeywordDAOException();
		}
		
		return products;
	}

	@Override
	public List<Product> getAll() throws ProductGetAllDAOException {
		
		sql = "SELECT * FROM Product";
		
		try(Connection connection = DBUtil.getConnection();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				products = new ArrayList<>(LIST_CAPACITY);
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					id = rs.getInt("ID");
					name = rs.getString("Name");
					description = rs.getString("Description");
					unitPrice =rs.getDouble("Unit_Price");
					quantity = rs.getInt("Quantity");
					supplierID = rs.getInt("Supplier_ID");
					
					products.add(new Product(name, description, unitPrice, quantity, supplierID));
				}
			}
			
		} catch(SQLException e8) {
			throw new ProductGetAllDAOException();
		}
		
		return products;
	}
	
}
