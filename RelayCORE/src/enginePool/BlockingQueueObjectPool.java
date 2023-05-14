package enginePool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author pasquale
 *
 * @param <T>
 *
 * Classe che implementa una Object Pool basata su una Blocking Queue.
 */
public class BlockingQueueObjectPool<T> extends BaseObjectPool<T> implements IObjectPool<T> {

	protected static final int DEFAULT_NUM_INSTANCES = 8;

	protected int numInstances = 0;
	protected static final Object numInstancesLock = new Object();

	protected BlockingQueue<T> pool = null;
	protected IPoolableObjectFactory<T> factory = null;

	public BlockingQueueObjectPool() throws InterruptedException, Exception {
		this(null, DEFAULT_NUM_INSTANCES);
	}

	public BlockingQueueObjectPool(int numInstances)
			throws InterruptedException, Exception {
		this(null, numInstances);
	}

	public BlockingQueueObjectPool(IPoolableObjectFactory<T> factory) throws InterruptedException, Exception {
		this(factory, DEFAULT_NUM_INSTANCES);
	}

	public BlockingQueueObjectPool(IPoolableObjectFactory<T> factory, int numInstances) throws InterruptedException, Exception {
		this.factory = factory;
		this.numInstances = numInstances;
		this.pool = new ArrayBlockingQueue<T>(numInstances, true);
		// VINCENZO -COMMENTARE PER NON UTILIZZARE PROLOG, ASSICURARSI DI NON USARE ANCHE I METODI BASATI SUL IE
		  for (int i = 0; i < numInstances; ++i) {
			if (!pool.offer(factory.makeObject())) {
				throw new RuntimeException("No space is currently available into the pool.");
			}
		}
	}

	public T requestObject() throws Exception {
		System.out.println("Object requested from pool");
		
		T ret = pool.take();
		factory.initialiseObject(ret);
		return ret;
	}

	public void returnObject(T obj) throws Exception {
		System.out.println("Object returned to pool");
		
		if (!pool.offer(obj)) {
			throw new RuntimeException("No space is currently available in the pool.");
		}
	}

	public void clear() throws Exception {
		if (factory != null) {
			synchronized (numInstancesLock) {
				while ((numInstances--) != 0) {
					factory.destroyObject(pool.take());
				}
			}
		}
	}
}
