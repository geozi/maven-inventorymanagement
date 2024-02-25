package dao;

/**
 * The {@link IGenericEntity} provides common metrics to 
 * the {@link IPrimaryEntityDAO} and {@link IJoinEntityDAO} 
 * interfaces.
 */
public interface IGenericEntity {
	static int TIMEOUT=5;
	static int LIST_CAPACITY = 50;
}
