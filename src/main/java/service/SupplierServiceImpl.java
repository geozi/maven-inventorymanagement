package service;

import java.util.ArrayList;
import java.util.List;

import dao.IPrimaryEntityDAO;
import dao.SupplierDAOImpl;
import dao.exceptions.SupplierDAOException;
import model.KeywordType;
import model.Supplier;
import service.dto.SupplierDTO;
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
	public void insert(SupplierDTO dto) throws SupplierDAOException, NoDataProvidedException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Supplier supplier= map(dto);
			supplierDAO.insert(supplier);
			
		} catch(SupplierDAOException | NoDataProvidedException e1) {
			e1.printStackTrace();
			throw e1;
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void update(SupplierDTO dto) throws SupplierDAOException, SupplierNotFoundException, NoDataProvidedException {
		
		try {
			if(dto == null) {
				throw new NoDataProvidedException();
			}
			
			Supplier supplier = map(dto);
			if(supplierDAO.get(supplier.getId()) == null) {
				throw new SupplierNotFoundException();
			}
			supplierDAO.update(supplier);
			
		} catch(SupplierDAOException | NoDataProvidedException | SupplierNotFoundException e2) {
			e2.printStackTrace();
			throw e2;
		} catch(Exception e2) {
			e2.printStackTrace();
		}
		
	}

	@Override
	public Supplier get(int id)
			throws SupplierDAOException, SupplierNotFoundException {
		
		Supplier supplier = null;
		
		try {
			supplier = supplierDAO.get(id);
			if(supplier == null) {
				throw new SupplierNotFoundException();
			}
		} catch(SupplierDAOException | SupplierNotFoundException e3) {
			e3.printStackTrace();
			throw e3;
		} catch(Exception e3) {
			e3.printStackTrace();
		}
		return supplier;
	}

	@Override
	public void delete(int id)
			throws SupplierDAOException, SupplierNotFoundException {
		
		try {
			if(supplierDAO.get(id) == null) {
				throw new SupplierNotFoundException();
			}
			
			supplierDAO.delete(id);
			
		} catch(SupplierDAOException | SupplierNotFoundException e4) {
			e4.printStackTrace();
			throw e4;
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		
	}

	@Override
	public List<Supplier> getAllByKeyword(KeywordType type, String kw)
			throws SupplierDAOException {
		
		List<Supplier> suppliers = new ArrayList<>(100);
		
		try {
			if(!kw.isEmpty()) {
				suppliers = supplierDAO.getAllByKeyword(type, kw);
			}
				
		} catch (SupplierDAOException e5) {
			e5.printStackTrace();
			throw e5;
		} catch(Exception e5) {
			e5.printStackTrace();
		}
		
		return suppliers;
	}

	@Override
	public Supplier map(SupplierDTO dto) {
		return new Supplier(dto.getId(), dto.getName(), dto.getMobilePhone(), dto.getEmail(), dto.getAddress(), dto.getCity());
	}

	@Override
	public List<Supplier> getAll() throws SupplierDAOException {

		List<Supplier> suppliers = new ArrayList<>(100);
		
		try {
			
			suppliers = supplierDAO.getAll();
				
		} catch (SupplierDAOException e6) {
			e6.printStackTrace();
			throw e6;
		} catch(Exception e6) {
			e6.printStackTrace();
		}
		
		return suppliers;
	}
	
}
