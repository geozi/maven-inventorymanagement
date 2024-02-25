package dao;

import model.Customer;
import model.KeywordType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.exceptions.CustomerDeleteDAOException;
import dao.exceptions.CustomerGetAllDAOException;
import dao.exceptions.CustomerGetByKeywordDAOException;
import dao.exceptions.CustomerGetDAOException;
import dao.exceptions.CustomerInsertDAOException;
import dao.exceptions.CustomerUpdateDAOException;
import dao.exceptions.InvalidKeywordDAOException;
import dao.util.DBUtil;

import java.sql.ResultSet;

/**
 * The {@link CustomerDAOImpl} class implements the CRUD methods
 * defined in the {@link IPrimaryEntityDAO} interface for
 * the Customer primary table.
 */
public class CustomerDAOImpl implements IPrimaryEntityDAO<Customer> {
	
	private int id;
	private String firstname;
	private String lastname;
	private String mobilePhone;
	private String email;
	private String billingAddress;
	private String city;
	private String sql;
	private StringBuilder sqlBuilder;
	private ResultSet rs;
	private Customer customer;
	private ArrayList<Customer> customers;

	@Override
	public void insert(Customer customer) throws CustomerInsertDAOException {
		
		firstname = customer.getFirstname();
		lastname = customer.getLastname();
		mobilePhone = customer.getMobilePhone();
		email = customer.getEmail();
		billingAddress= customer.getBillingAddress();
		city = customer.getCity();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO Customer (Firstname, Lastname, Mobile_Phone, Email, Billing_Address, City)");
		sqlBuilder.append(" VALUES (?, ?, ?, ?, ?, ?)");
		sql = sqlBuilder.toString();
		
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, mobilePhone);
				ps.setString(4, email);
				ps.setString(5, billingAddress);
				ps.setString(6, city);
				ps.executeUpdate();
			}
			
		} catch (SQLException e1) {
			throw new CustomerInsertDAOException();
		}
	}

	@Override
	public Customer get(int id) throws CustomerGetDAOException {
		
		sql = "SELECT * FROM Customer WHERE ID=?";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					this.id = rs.getInt("ID");
					firstname = rs.getString("Firstname");
					lastname = rs.getString("Lastname");
					mobilePhone = rs.getString("Mobile_Phone");
					email = rs.getString("Email");
					billingAddress = rs.getString("Billing_Address");
					city = rs.getString("City");
				}
				
				customer = new Customer(this.id, firstname, lastname, mobilePhone, email, billingAddress, city);
			}
			
		} catch (SQLException e2) {
			throw new CustomerGetDAOException();
		}
		return customer;
	}

	@Override
	public void update(Customer customer) throws CustomerUpdateDAOException {
		
		id = customer.getId();
		firstname = customer.getFirstname();
		lastname = customer.getLastname();
		mobilePhone = customer.getMobilePhone();
		email = customer.getEmail();
		billingAddress= customer.getBillingAddress();
		city = customer.getCity();
		
		sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE Customer SET Firstname=?, Lastname=?, Mobile_Phone=?, ");
		sqlBuilder.append(" Email=?, Billing_Address=?, City=? WHERE ID=?");
		sql = sqlBuilder.toString();
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, mobilePhone);
				ps.setString(4, email);
				ps.setString(5, billingAddress);
				ps.setString(6, city);
				ps.setInt(7, id);
				ps.executeUpdate();
			}

			
		} catch(SQLException e3) {
			throw new CustomerUpdateDAOException();
		}
	}

	@Override
	public void delete(int id) throws CustomerDeleteDAOException {
		
		sql = "DELETE FROM Customer WHERE ID=?";
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				ps.setInt(1, id);
				ps.executeUpdate();
			}
			
		} catch(SQLException e4) {
			throw new CustomerDeleteDAOException();
		}
		
	}

	@Override
	public List<Customer> getAllByKeyword(KeywordType t, String kw) throws InvalidKeywordDAOException, CustomerGetByKeywordDAOException {
		
		try {
			if(t.equals(KeywordType.LASTNAME)) {
				sql = "SELECT * FROM Customer WHERE Lastname LIKE ?";
			} else if (t.equals(KeywordType.CITY)) {
				sql = "SELECT * FROM Customer WHERE City LIKE ?";
			} else {
				throw new SQLException();
			}
		} catch (SQLException e5) {
			throw new InvalidKeywordDAOException();
		}
		
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				customers = new ArrayList<>(LIST_CAPACITY);
				
				ps.setString(1, kw+"%");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					id = rs.getInt("ID");
					firstname = rs.getString("Firstname");
					lastname = rs.getString("Lastneme");
					mobilePhone = rs.getString("Mobile_Phone");
					email = rs.getString("Email");
					billingAddress = rs.getString("Billing_Address");
					city = rs.getString("City");
					
					customers.add(new Customer(id, firstname, lastname, mobilePhone, email, billingAddress, city));
				}
				
			}
			
		} catch(SQLException e6) {
			throw new CustomerGetByKeywordDAOException();
		}
			
		return  customers;
	}

	@Override
	public List<Customer> getAll() throws CustomerGetAllDAOException {
		
		sql = "SELECT * FROM Customer";
		
		try(Connection connection = DBUtil.getConnection();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			if(connection.isValid(TIMEOUT)) {
				
				System.out.println("Connection is established");
				
				customers = new ArrayList<>(LIST_CAPACITY);
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					id = rs.getInt("ID");
					firstname = rs.getString("Firstname");
					lastname = rs.getString("Lastname");
					mobilePhone = rs.getString("Mobile_Phone");
					email = rs.getString("Email");
					billingAddress = rs.getString("Billing_Address");
					city = rs.getString("City");
					
					customers.add(new Customer(id, firstname, lastname, mobilePhone, email, billingAddress, city));
				}
				
			}
			
		} catch(SQLException e8) {
			throw new CustomerGetAllDAOException();
		}
		
		return customers;
	}
}
