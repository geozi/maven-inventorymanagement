package dao;

import java.util.List;

import dao.exceptions.OrderDAOException;
import dao.exceptions.OrderProductDAOException;

/**
 * The {@link IJoinEntityDAO} interface provides the CRUD 
 * method definitions for join table operations.
 * It inherits a TIMEOUT metric from the {@link IGenericEntity} interface.
 */
public interface IJoinEntityDAO extends IGenericEntity {

	void insert(List<Integer> lst) throws OrderDAOException, OrderProductDAOException; 
	void delete(int i, int j) throws OrderProductDAOException;
}
