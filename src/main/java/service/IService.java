package service;

import java.util.List;

import dao.exceptions.CustomerDAOException;
import dao.exceptions.OrderDAOException;
import dao.exceptions.ProductDAOException;
import dao.exceptions.SupplierDAOException;
import model.KeywordType;
import service.exceptions.CustomerNotFoundException;
import service.exceptions.NoDataProvidedException;
import service.exceptions.OrderNotFoundException;
import service.exceptions.ProductNotFoundException;
import service.exceptions.SupplierNotFoundException;


/**
 * The {@link IService} interface contains the Service-level definitions 
 * of the CRUD wrapper methods.
 * @param <T> A generic enabling sub-type polymorphism.
 * @param <E> A generic enabling sub-type polymorphism.
 */
public interface IService<T, E> {
	
	void insert(T t) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException, NoDataProvidedException;
	void update(T t) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException, CustomerNotFoundException, SupplierNotFoundException, ProductNotFoundException, OrderNotFoundException, NoDataProvidedException;
	E get(int id) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException, CustomerNotFoundException, SupplierNotFoundException, ProductNotFoundException, OrderNotFoundException;
	void delete(int id) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException, CustomerNotFoundException, SupplierNotFoundException, ProductNotFoundException, OrderNotFoundException;
	List<E> getAll() throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	List<E> getAllByKeyword(KeywordType t, String kw) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	E map(T t);

}
