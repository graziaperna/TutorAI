package enginePool;

/**
 *
 * @author pasquale
 *
 * @param <T>
 *
 * Classe che implementa una factory Object Pool basate su Blocking Queue.
 */
public class BlockingQueueObjectPoolFactory<T> implements IObjectPoolFactory<T> {

	protected IPoolableObjectFactory<T> factory = null;
	protected int numInstances = BlockingQueueObjectPool.DEFAULT_NUM_INSTANCES;

	public BlockingQueueObjectPoolFactory() {
		this(null, BlockingQueueObjectPool.DEFAULT_NUM_INSTANCES);
	}

	public BlockingQueueObjectPoolFactory(int maxInstances) {
		this(null, maxInstances);
	}

	public BlockingQueueObjectPoolFactory(IPoolableObjectFactory<T> factory) {
		this(factory, BlockingQueueObjectPool.DEFAULT_NUM_INSTANCES);
	}

	public BlockingQueueObjectPoolFactory(IPoolableObjectFactory<T> factory,
			int maxInstances) {
		this.factory = factory;
		this.numInstances = maxInstances;
	}

	public IObjectPool<T> createPool() throws Exception {
		return new BlockingQueueObjectPool<T>(factory, numInstances);
	}

}
