package service;

import java.util.List;

import dao.exceptions.BaseDAOException;
import model.KeywordType;
import service.exceptions.BaseServiceException;

/**
 * The {@link IService} interface contains the Service-level definitions 
 * of the CRUD wrapper methods and common metrics.
 * @param <T> A generic enabling sub-type polymorphism.
 * @param <E> A generic enabling sub-type polymorphism.
 */
public interface IService<T, E> {
	static int LIST_CAPACITY=50;
	
	void insert(T t) throws BaseDAOException, BaseServiceException;
	void update(T t) throws BaseDAOException, BaseServiceException;
	E get(int id) throws BaseDAOException, BaseServiceException;
	void delete(int id) throws BaseDAOException, BaseServiceException;
	List<E> getAll() throws BaseDAOException;
	List<E> getAllByKeyword(KeywordType t, String kw) throws BaseDAOException;
	E map(T t);

}
