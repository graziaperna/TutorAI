package enginePool;

/**
 *
 * @author pasquale
 *
 * @param <T>
 *
 * Interfaccia di una Object Pool.
 */
public interface IObjectPool<T> {

	T requestObject() throws Exception;
	void returnObject(T obj) throws Exception;

	void setFactory(IPoolableObjectFactory<T> factory) throws Exception;

	void clear() throws Exception;
}
