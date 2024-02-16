package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.CustomerDAOImpl;
import dao.exceptions.CustomerDAOException;
import model.Customer;
import model.KeywordType;
import service.dto.CustomerDTO;
import service.exceptions.CustomerNotFoundException;
import service.exceptions.NoDataProvidedException;

/**
 * The {@link CustomerServiceImpl} class implements the methods
 * defined in the {@link IService} interface for the Customer
 * primary table.
 */
public class CustomerServiceImpl implements IService<CustomerDTO, Customer> {
	private final IPrimaryEntityDAO<Customer> customerDAO;
	
	

	public CustomerServiceImpl() {
		customerDAO = new CustomerDAOImpl();
	}

	@Override
	public void insert(CustomerDTO dto)
			throws CustomerDAOException, NoDataProvidedException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Customer customer = map(dto);
			customerDAO.insert(customer);
			
		} catch(CustomerDAOException | NoDataProvidedException e1) {
			e1.printStackTrace();
			throw e1;
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void update(CustomerDTO dto)
			throws CustomerDAOException, NoDataProvidedException, CustomerNotFoundException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Customer customer = map(dto);
			if(customerDAO.get(customer.getId()) == null) {
				throw new CustomerNotFoundException();
			}
			customerDAO.update(customer);
			
		} catch(CustomerDAOException | NoDataProvidedException | CustomerNotFoundException e2) {
			e2.printStackTrace();
			throw e2;
		} catch(Exception e2) {
			e2.printStackTrace();
		}
		
	}

	@Override
	public Customer get(int id) throws CustomerDAOException, CustomerNotFoundException {
		
		Customer customer = null;
		
		try {
			customer = customerDAO.get(id);
			if(customer == null) {
				throw new CustomerNotFoundException();
			}
		} catch(CustomerDAOException | CustomerNotFoundException e3) {
			e3.printStackTrace();
			throw e3;
		} catch(Exception e3) {
			e3.printStackTrace();
		}
		return customer;
	}

	@Override
	public void delete(int id)
			throws CustomerDAOException, CustomerNotFoundException {
		
		try {
			if(customerDAO.get(id) == null) {
				throw new CustomerNotFoundException();
			}
			
			customerDAO.delete(id);
			
		} catch(CustomerDAOException | CustomerNotFoundException e4) {
			e4.printStackTrace();
			throw e4;
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		
	}

	@Override
	public List<Customer> getAllByKeyword(KeywordType type, String kw)
			throws CustomerDAOException {
		
		List<Customer> customers = new ArrayList<>(100);
		
		try {
			if(!kw.isEmpty()) {
				customers = customerDAO.getAllByKeyword(type, kw);
			}
				
		} catch (CustomerDAOException e5) {
			e5.printStackTrace();
			throw e5;
		} catch(Exception e5) {
			e5.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public Customer map(CustomerDTO dto) {
		return new Customer( dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getMobilePhone(), dto.getEmail(), dto.getBillingAddress(), dto.getCity());
	}

	@Override
	public List<Customer> getAll() throws CustomerDAOException {
		
		List<Customer> customers = new ArrayList<>(100);
		
		try {
			
			customers = customerDAO.getAll();
				
		} catch (CustomerDAOException e6) {
			e6.printStackTrace();
			throw e6;
		} catch(Exception e6) {
			e6.printStackTrace();
		}
		
		return customers;
	}
	
}
