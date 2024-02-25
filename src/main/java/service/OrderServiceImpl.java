package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.OrderDAOImpl;
import dao.exceptions.BaseDAOException;
import model.KeywordType;
import model.Order;
import service.dto.OrderDTO;
import service.exceptions.BaseServiceException;
import service.exceptions.NoDataProvidedException;
import service.exceptions.OrderNotFoundException;

/**
 * The {@link OrderServiceImpl} class implements the methods
 * defined in the {@link IService} interface for the Order
 * primary table.
 */
public class OrderServiceImpl implements IService<OrderDTO, Order> {
	private final IPrimaryEntityDAO<Order> orderDAO;

	public OrderServiceImpl() {
		orderDAO = new OrderDAOImpl();
	}

	@Override
	public void insert(OrderDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			Order order= map(dto);
			orderDAO.insert(order);
			
		} catch(BaseDAOException e1) {
			throw e1;
		} catch(BaseServiceException e1) {
			throw e1;
		}
		
	}

	@Override
	public void update(OrderDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Order order = map(dto);
			if(orderDAO.get(order.getId()) == null) {
				throw new OrderNotFoundException();
			}
			orderDAO.update(order);
			
		} catch(BaseDAOException e2) {
			throw e2;
		} catch(BaseServiceException e2) {
			throw e2;
		}
		
	}

	@Override
	public Order get(int id) throws BaseDAOException, BaseServiceException {
		
		Order order = null;
		
		try {
			order = orderDAO.get(id);
			if(order == null) {
				throw new OrderNotFoundException();
			}
		} catch(BaseDAOException e3) {
			throw e3;
		}catch(BaseServiceException e3) {
			throw e3;
		} 
		
		return order;
	}

	@Override
	public void delete(int id) throws BaseDAOException, BaseServiceException {
		
		try {
			if(orderDAO.get(id) == null) {
				throw new OrderNotFoundException();
			}
			
			orderDAO.delete(id);
			
		} catch(BaseDAOException e4) {
			throw e4;
		} catch(BaseServiceException e4) {
			throw e4;
		}
		
	}

	@Override
	public List<Order> getAllByKeyword(KeywordType type, String city) throws BaseDAOException {
		
		List<Order> orders = new ArrayList<>(LIST_CAPACITY);
		
		try {
			if(!city.isEmpty()) {
				orders = orderDAO.getAllByKeyword(type, city);
			}
				
		} catch (BaseDAOException e5) {
			e5.printStackTrace();
			throw e5;
		}
		
		return orders;
	}

	@Override
	public Order map(OrderDTO dto) {
		return new Order(dto.getId(), Integer.parseInt(dto.getCustomerID()), Integer.parseInt(dto.getQuantity()), Double.parseDouble(dto.getPrice()), dto.getShippingAddress(), dto.getCity());
	}

	@Override
	public List<Order> getAll() throws BaseDAOException {
		
		List<Order> orders = new ArrayList<>(LIST_CAPACITY);
		
		try {
			orders = orderDAO.getAll();
				
		} catch (BaseDAOException e6) {
			throw e6;
		} 
		
		return orders;
	}

}
