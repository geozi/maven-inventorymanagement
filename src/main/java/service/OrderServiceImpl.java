package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.OrderDAOImpl;
import dao.exceptions.OrderDAOException;
import model.KeywordType;
import model.Order;
import service.dto.OrderDTO;
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
	public void insert(OrderDTO dto) throws OrderDAOException, NoDataProvidedException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			Order order= map(dto);
			orderDAO.insert(order);
			
		} catch(OrderDAOException | NoDataProvidedException e1) {
			e1.printStackTrace();
			throw e1;
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void update(OrderDTO dto) throws OrderDAOException, OrderNotFoundException, NoDataProvidedException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Order order = map(dto);
			if(orderDAO.get(order.getId()) == null) {
				throw new OrderNotFoundException();
			}
			orderDAO.update(order);
			
		} catch(OrderDAOException | NoDataProvidedException | OrderNotFoundException e2) {
			e2.printStackTrace();
			throw e2;
		} catch(Exception e2) {
			e2.printStackTrace();
		}
		
	}

	@Override
	public Order get(int id) throws OrderDAOException, OrderNotFoundException {
		
		Order order = null;
		
		try {
			order = orderDAO.get(id);
			if(order == null) {
				throw new OrderNotFoundException();
			}
		} catch(OrderDAOException | OrderNotFoundException e3) {
			e3.printStackTrace();
			throw e3;
		} catch(Exception e3) {
			e3.printStackTrace();
		}
		return order;
	}

	@Override
	public void delete(int id)
			throws OrderDAOException, OrderNotFoundException {
		
		try {
			if(orderDAO.get(id) == null) {
				throw new OrderNotFoundException();
			}
			
			orderDAO.delete(id);
			
		} catch(OrderDAOException | OrderNotFoundException e4) {
			e4.printStackTrace();
			throw e4;
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		
	}

	@Override
	public List<Order> getAllByKeyword(KeywordType type, String city)
			throws OrderDAOException {
		
		List<Order> orders = new ArrayList<>(100);
		
		try {
			if(!city.isEmpty()) {
				orders = orderDAO.getAllByKeyword(type, city);
			}
				
		} catch (OrderDAOException e5) {
			e5.printStackTrace();
			throw e5;
		} catch(Exception e5) {
			e5.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public Order map(OrderDTO dto) {
		return new Order(dto.getId(), Integer.parseInt(dto.getCustomerID()), Integer.parseInt(dto.getQuantity()), Double.parseDouble(dto.getPrice()), dto.getShippingAddress(), dto.getCity());
	}

	@Override
	public List<Order> getAll() throws OrderDAOException {
		
		List<Order> orders = new ArrayList<>(100);
		
		try {
			orders = orderDAO.getAll();
				
		} catch (OrderDAOException e6) {
			e6.printStackTrace();
			throw e6;
		} catch(Exception e6) {
			e6.printStackTrace();
		}
		
		return orders;
	}

}
