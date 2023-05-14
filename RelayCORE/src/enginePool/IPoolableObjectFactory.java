package enginePool;

/**
 *
 * @author pasquale
 *
 * @param <T>
 *
 * Interfaccia per una factory per risorse gestite da una Object Pool.
 */
public interface IPoolableObjectFactory<T> {

	T makeObject() throws Exception;
	void destroyObject(T obj) throws Exception;
	void initialiseObject(T obj) throws Exception;
}
