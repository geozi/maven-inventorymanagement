package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.ProductDAOImpl;
import dao.exceptions.BaseDAOException;
import model.KeywordType;
import model.Product;
import service.dto.ProductDTO;
import service.exceptions.BaseServiceException;
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
	public void insert(ProductDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Product product= map(dto);
			productDAO.insert(product);
			
		} catch(BaseDAOException e1) {
			throw e1;
		} catch(BaseServiceException e1) {
			throw e1;
		}
		
	}

	@Override
	public void update(ProductDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Product product = map(dto);
			if(productDAO.get(product.getId()) == null) {
				throw new ProductNotFoundException();
			}
			productDAO.update(product);
			
		} catch(BaseDAOException e2) {
			throw e2;
		} catch(BaseServiceException e2) {
			throw e2;
		}
		
	}

	@Override
	public Product get(int id) throws BaseDAOException, BaseServiceException {
		
		Product product = null;
		
		try {
			product = productDAO.get(id);
			if(product == null) {
				throw new ProductNotFoundException();
			}
		} catch(BaseDAOException e3) {
			throw e3;
		} catch(BaseServiceException e3) {
			throw e3;
		}
		return product;
	}

	@Override
	public void delete(int id) throws BaseDAOException, BaseServiceException {
		
		try {
			if(productDAO.get(id) == null) {
				throw new ProductNotFoundException();
			}
			
			productDAO.delete(id);
			
		} catch(BaseDAOException e4) {
			throw e4;
		} catch (BaseServiceException e4) {
			throw e4;
		}
		
	}

	@Override
	public List<Product> getAllByKeyword(KeywordType type, String name) throws BaseDAOException {
		
		List<Product> products = new ArrayList<>(LIST_CAPACITY);
		
		try {
			if(!name.isEmpty()) {
				products = productDAO.getAllByKeyword(type, name);
			}
				
		} catch (BaseDAOException e5) {
			throw e5;
		}
		
		return products;
	}

	@Override
	public Product map(ProductDTO dto) {
		return new Product(dto.getId(), dto.getName(), dto.getDescription(), Double.parseDouble(dto.getUnitPrice()), Integer.parseInt(dto.getQuantity()), Integer.parseInt(dto.getSupplierID()));
	}

	@Override
	public List<Product> getAll() throws BaseDAOException {
		
		List<Product> products = new ArrayList<>(LIST_CAPACITY);
		
		try {
			
			products = productDAO.getAll();
				
		} catch (BaseDAOException e6) {
			throw e6;
		} 
		
		return products;
	}
}
