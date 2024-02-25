package dao;

import java.util.List;

import dao.exceptions.BaseDAOException;
import model.KeywordType;

/**
 * The {@link IPrimaryEntityDAO} interface provides the CRUD 
 * method definitions for primary entities.
 * It inherits a TIMEOUT metric from the {@link IGenericEntity} interface.
 * @param <T> A generic type enabling sub-type polymorphism.
 */
public interface IPrimaryEntityDAO<T> extends IGenericEntity {
	
	void insert(T t) throws BaseDAOException;
	T get(int id) throws BaseDAOException;
	void update(T t) throws BaseDAOException;
	void delete(int id) throws BaseDAOException;
	List<T> getAll() throws BaseDAOException;
	List<T> getAllByKeyword(KeywordType t, String kw) throws BaseDAOException;
	
}
