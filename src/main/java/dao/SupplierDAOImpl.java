package dao;

import model.KeywordType;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.exceptions.InvalidKeywordDAOException;
import dao.exceptions.SupplierDeleteDAOException;
import dao.exceptions.SupplierGetAllDAOException;
import dao.exceptions.SupplierGetByKeywordDAOException;
import dao.exceptions.SupplierGetDAOException;
import dao.exceptions.SupplierInsertDAOException;
import dao.exceptions.SupplierUpdateDAOException;
import dao.util.DBUtil;

import java.sql.ResultSet;

/**
 * The {@link SupplierDAOImpl} class implements the CRUD methods
 * defined in the {@link IPrimaryEntityDAO} interface for
 * the Supplier primary table.
 */
public class SupplierDAOImpl implements IPrimaryEntityDAO<Supplier> {
	
	private int id;
	private String name;
	private String mobilePhone;
	private String email;
	private String address;
	private String city;
	private String sql;
	private StringBuilder sqlBuilder;
	private ResultSet rs;
	private Supplier supplier;
	private ArrayList<Supplier> suppliers;

	@Override
	public void insert(Supplier supplier) throws SupplierInsertDAOException {
		
		name = supplier.getName();
		mobilePhone = supplier.getMobilePhone();
		email = supplier.getEmail();
		address = supplier.getAddress();
		city = supplier.getCity();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO Supplier (Name, Mobile_Phone, Email, Address, City)");
		sqlBuilder.append(" VALUES (?, ?, ?, ?, ?)");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, name);
				ps.setString(2, mobilePhone);
				ps.setString(3, email);
				ps.setString(4, address);
				ps.setString(5, city);
				ps.executeUpdate();
			}
			
		} catch(SQLException e1) {
			throw new SupplierInsertDAOException();
		}

	}

	@Override
	public Supplier get(int id) throws SupplierGetDAOException {
		
		sql = "SELECT * FROM Supplier WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			if(connection.isValid(TIMEOUT)) {
				System.out.println("Connection is established");
				
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					this.id = rs.getInt("ID");
					name = rs.getString("Name");
					mobilePhone = rs.getString("Mobile_Phone");
					email = rs.getString("Email");;
					address = rs.getString("Address");
					city = rs.getString("City");
				}
				
				supplier = new Supplier(this.id, name, mobilePhone, email, address, city);
			}
			
		} catch (SQLException e2) {
			throw new SupplierGetDAOException();
		}
		return supplier;
	}

	@Override
	public void update(Supplier supplier) throws SupplierUpdateDAOException {
		
		id = supplier.getId();
		name = supplier.getName();
		mobilePhone = supplier.getMobilePhone();
		email = supplier.getEmail();
		address = supplier.getAddress();
		city = supplier.getCity();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE Supplier SET Name=?, Mobile_Phone=?, Email=?,");
		sqlBuilder.append(" Address=?, City=? WHERE ID=?");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, name);
				ps.setString(2, mobilePhone);
				ps.setString(3, email);
				ps.setString(4, address);
				ps.setString(5, city);
				ps.setInt(6, id);
				ps.executeUpdate();
				
			}
			
		} catch(SQLException e3) {
			throw new SupplierUpdateDAOException();
		}
	}

	@Override
	public void delete(int id) throws SupplierDeleteDAOException {
		
		sql = "DELETE FROM Supplier WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setInt(1, id);
				ps.executeUpdate();
			}

			
		} catch(SQLException e4) {
			throw new SupplierDeleteDAOException();
		}
	}

	@Override
	public List<Supplier> getAllByKeyword(KeywordType t, String kw) throws InvalidKeywordDAOException, SupplierGetByKeywordDAOException {
		
		try {
			if(t.equals(KeywordType.NAME)) {
				sql = "SELECT * FROM Supplier WHERE Name LIKE ?";
			} else if(t.equals(KeywordType.CITY)) {
				sql = "SELECT * FROM Supplier WHERE City LIKE ?";
			} else {
				throw new SQLException();
			}
			
		} catch(SQLException e5) {
			throw new InvalidKeywordDAOException();
		}
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				suppliers = new ArrayList<>(LIST_CAPACITY);
				
				ps.setString(1, kw+"%");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					id = rs.getInt("ID");
					name = rs.getString("Name");
					mobilePhone =rs.getString("Mobile_Phone");
					email =rs.getString("Email");
					address =rs.getString("Address");
					city = rs.getString("City");
					
					suppliers.add(new Supplier(id, name, mobilePhone, email, address, city));
				}
			}
			
		} catch (SQLException e6) {
			throw new SupplierGetByKeywordDAOException();
		}
		
		return suppliers;
	}

	@Override
	public List<Supplier> getAll() throws SupplierGetAllDAOException {
		
		sql = "SELECT * FROM Supplier";
		
		try(Connection connection = DBUtil.getConnection();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				suppliers = new ArrayList<>(LIST_CAPACITY);
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					id = rs.getInt("ID");
					name = rs.getString("Name");
					mobilePhone =rs.getString("Mobile_Phone");
					email =rs.getString("Email");
					address =rs.getString("Address");
					city = rs.getString("City");
					
					suppliers.add(new Supplier(id, name, mobilePhone, email, address, city));
				}
			}
			
		} catch (SQLException e8) {
			throw new SupplierGetAllDAOException();
		}
		
		return suppliers;
	}
	
}
