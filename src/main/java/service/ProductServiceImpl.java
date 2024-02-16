package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.ProductDAOImpl;
import dao.exceptions.ProductDAOException;
import model.KeywordType;
import model.Product;
import service.dto.ProductDTO;
import service.exceptions.NoDataProvidedException;
import service.exceptions.ProductNotFoundException;

/**
 * The {@link ProductServiceImpl} class implements the methods
 * defined in the {@link IService} interface for the Product
 * primary table.
 */
public class ProductServiceImpl implements IService<ProductDTO, Product> {
	private final IPrimaryEntityDAO<Product> productDAO;

	public ProductServiceImpl() {
		productDAO = new ProductDAOImpl();
	}

	@Override
	public void insert(ProductDTO dto) throws ProductDAOException, NoDataProvidedException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Product product= map(dto);
			productDAO.insert(product);
			
		} catch(ProductDAOException | NoDataProvidedException e1) {
			e1.printStackTrace();
			throw e1;
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void update(ProductDTO dto) throws ProductDAOException, ProductNotFoundException, NoDataProvidedException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Product product = map(dto);
			if(productDAO.get(product.getId()) == null) {
				throw new ProductNotFoundException();
			}
			productDAO.update(product);
			
		} catch(ProductDAOException | NoDataProvidedException | ProductNotFoundException e2) {
			e2.printStackTrace();
			throw e2;
		} catch(Exception e2) {
			e2.printStackTrace();
		}
		
	}

	@Override
	public Product get(int id)
			throws ProductDAOException, ProductNotFoundException {
		
		Product product = null;
		
		try {
			product = productDAO.get(id);
			if(product == null) {
				throw new ProductNotFoundException();
			}
		} catch(ProductDAOException | ProductNotFoundException e3) {
			e3.printStackTrace();
			throw e3;
		} catch(Exception e3) {
			e3.printStackTrace();
		}
		return product;
	}

	@Override
	public void delete(int id)
			throws ProductDAOException, ProductNotFoundException {
		
		try {
			if(productDAO.get(id) == null) {
				throw new ProductNotFoundException();
			}
			
			productDAO.delete(id);
			
		} catch(ProductDAOException | ProductNotFoundException e4) {
			e4.printStackTrace();
			throw e4;
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		
	}

	@Override
	public List<Product> getAllByKeyword(KeywordType type, String name)
			throws ProductDAOException {
		
		List<Product> products = new ArrayList<>(100);
		
		try {
			if(!name.isEmpty()) {
				products = productDAO.getAllByKeyword(type, name);
			}
				
		} catch (ProductDAOException e5) {
			e5.printStackTrace();
			throw e5;
		} catch(Exception e5) {
			e5.printStackTrace();
		}
		
		return products;
	}

	@Override
	public Product map(ProductDTO dto) {
		return new Product(dto.getId(), dto.getName(), dto.getDescription(), Double.parseDouble(dto.getUnitPrice()), Integer.parseInt(dto.getQuantity()), Integer.parseInt(dto.getSupplierID()));
	}

	@Override
	public List<Product> getAll() throws ProductDAOException {
		
		List<Product> products = new ArrayList<>(100);
		
		try {
			
			products = productDAO.getAll();
				
		} catch (ProductDAOException e6) {
			e6.printStackTrace();
			throw e6;
		} catch(Exception e6) {
			e6.printStackTrace();
		}
		
		return products;
	}

}
