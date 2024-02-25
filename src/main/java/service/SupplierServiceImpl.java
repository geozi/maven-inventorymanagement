package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.SupplierDAOImpl;
import dao.exceptions.BaseDAOException;
import model.KeywordType;
import model.Supplier;
import service.dto.SupplierDTO;
import service.exceptions.BaseServiceException;
import service.exceptions.NoDataProvidedException;
import service.exceptions.SupplierNotFoundException;

/**
 * The {@link SupplierServiceImpl} class implements the methods
 * defined in the {@link IService} interface for the Supplier
 * primary table.
 */
public class SupplierServiceImpl implements IService<SupplierDTO, Supplier> {
	
	private final IPrimaryEntityDAO<Supplier> supplierDAO;
	

	public SupplierServiceImpl() {
		supplierDAO = new SupplierDAOImpl();
	}

	@Override
	public void insert(SupplierDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Supplier supplier= map(dto);
			supplierDAO.insert(supplier);
			
		} catch(BaseDAOException e1) {
			throw e1;
		} catch(BaseServiceException e1) {
			throw e1;
		}
		
	}

	@Override
	public void update(SupplierDTO dto) throws BaseDAOException, BaseServiceException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Supplier supplier = map(dto);
			if(supplierDAO.get(supplier.getId()) == null) {
				throw new SupplierNotFoundException();
			}
			supplierDAO.update(supplier);
			
		} catch(BaseDAOException e2) {
			throw e2;
		} catch(BaseServiceException e2) {
			throw e2;
		}
		
	}

	@Override
	public Supplier get(int id) throws BaseDAOException, BaseServiceException {
		
		Supplier supplier = null;
		
		try {
			supplier = supplierDAO.get(id);
			if(supplier == null) {
				throw new SupplierNotFoundException();
			}
		} catch(BaseDAOException e3) {
			throw e3;
		} catch(BaseServiceException e3) {
			throw e3;
		}
		return supplier;
	}

	@Override
	public void delete(int id) throws BaseDAOException, BaseServiceException {
		
		try {
			if(supplierDAO.get(id) == null) {
				throw new SupplierNotFoundException();
			}
			
			supplierDAO.delete(id);
			
		} catch(BaseDAOException e4) {
			throw e4;
		} catch (BaseServiceException e4) {
			throw e4;
		}
		
	}

	@Override
	public List<Supplier> getAllByKeyword(KeywordType type, String kw) throws BaseDAOException {
		
		List<Supplier> suppliers = new ArrayList<>(LIST_CAPACITY);
		
		try {
			if(!kw.isEmpty()) {
				suppliers = supplierDAO.getAllByKeyword(type, kw);
			}
				
		} catch (BaseDAOException e5) {
			throw e5;
		} 
		
		return suppliers;
	}

	@Override
	public Supplier map(SupplierDTO dto) {
		return new Supplier(dto.getId(), dto.getName(), dto.getMobilePhone(), dto.getEmail(), dto.getAddress(), dto.getCity());
	}

	@Override
	public List<Supplier> getAll() throws BaseDAOException {

		List<Supplier> suppliers = new ArrayList<>(LIST_CAPACITY);
		
		try {
			
			suppliers = supplierDAO.getAll();
				
		} catch (BaseDAOException e6) {
			throw e6;
		} 
		
		return suppliers;
	}
	
}
