package dao;

/**
 * The {@link IGenericEntity} provides a common 
 * TIMEOUT metric to the {@link IPrimaryEntityDAO} and {@link IJoinEntityDAO} 
 * interfaces.
 */
public interface IGenericEntity {
	static int TIMEOUT=5;
}
