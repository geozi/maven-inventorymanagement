package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.CustomerDAOImpl;
import dao.exceptions.BaseDAOException;
import model.Customer;
import model.KeywordType;
import service.dto.CustomerDTO;
import service.exceptions.BaseServiceException;
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
	public void insert(CustomerDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Customer customer = map(dto);
			customerDAO.insert(customer);
			
			} catch(BaseDAOException e1) {
				throw e1;
			} catch(BaseServiceException e1) {
				throw e1;
			}
	}

	@Override
	public void update(CustomerDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Customer customer = map(dto);
			if(customerDAO.get(customer.getId()) == null) {
				throw new CustomerNotFoundException();
			}
			customerDAO.update(customer);
			
		} catch(BaseDAOException e2) {
			throw e2;
		} catch(BaseServiceException e2) {
			throw e2;
		}
		
	}

	@Override
	public Customer get(int id) throws BaseDAOException, BaseServiceException {
		
		Customer customer = null;
		
		try {
			customer = customerDAO.get(id);
			if(customer == null) {
				throw new CustomerNotFoundException();
			}
		} catch (BaseDAOException e3) {
			throw e3;
		} catch(BaseServiceException e3) {
			throw e3;
		}
		return customer;
	}

	@Override
	public void delete(int id) throws BaseDAOException, BaseServiceException {
		
		try {
			if(customerDAO.get(id) == null) {
				throw new CustomerNotFoundException();
			}
			
			customerDAO.delete(id);
			
		} catch(BaseDAOException e4) {
			throw e4;
		} catch(BaseServiceException e4) {
			throw e4;
		}
		
	}

	@Override
	public List<Customer> getAllByKeyword(KeywordType type, String kw) throws BaseDAOException {
		
		List<Customer> customers = new ArrayList<>(LIST_CAPACITY);
		
		try {
			if(!kw.isEmpty()) {
				customers = customerDAO.getAllByKeyword(type, kw);
			}
				
		} catch (BaseDAOException e5) {
			throw e5;
		}
		
		return customers;
	}

	@Override
	public Customer map(CustomerDTO dto) {
		return new Customer( dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getMobilePhone(), dto.getEmail(), dto.getBillingAddress(), dto.getCity());
	}

	@Override
	public List<Customer> getAll() throws BaseDAOException {
		
		List<Customer> customers = new ArrayList<>(LIST_CAPACITY);
		
		try {
			
			customers = customerDAO.getAll();
				
		} catch (BaseDAOException e6) {
			throw e6;
		} 
		
		return customers;
	}
	
}
