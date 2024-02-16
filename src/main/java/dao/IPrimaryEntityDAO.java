package dao;

import java.util.List;

import dao.exceptions.CustomerDAOException;
import dao.exceptions.OrderDAOException;
import dao.exceptions.ProductDAOException;
import dao.exceptions.SupplierDAOException;
import model.KeywordType;

/**
 * The {@link IPrimaryEntityDAO} interface provides the CRUD 
 * method definitions for primary table operations.
 * It inherits a TIMEOUT metric from the {@link IGenericEntity} interface.
 * @param <T> A generic enabling sub-type polymorphism.
 */
public interface IPrimaryEntityDAO<T> extends IGenericEntity {
	
	void insert(T t) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	T get(int id) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	void update(T t) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	void delete(int id) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	List<T> getAll() throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	List<T> getAllByKeyword(KeywordType t, String kw) throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
	int getLastInsertID() throws CustomerDAOException, SupplierDAOException, ProductDAOException, OrderDAOException;
}
