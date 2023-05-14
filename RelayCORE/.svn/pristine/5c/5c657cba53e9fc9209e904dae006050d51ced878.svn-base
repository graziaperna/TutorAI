package enginePool;

/**
 *
 * @author pasquale
 *
 * @param <T>
 *
 * Classe astratta che implementa le funzionalità di base di una Object Pool e ne definisce i metodi
 * da implementare.
 */
public abstract class BaseObjectPool<T> implements IObjectPool<T> {

	public abstract T requestObject() throws Exception;

	public abstract void returnObject(T obj) throws Exception;

	public void setFactory(IPoolableObjectFactory<T> factory)
			throws IllegalStateException, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public abstract void clear() throws Exception;
}
