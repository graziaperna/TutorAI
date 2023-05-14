package core;

import enginePool.BlockingQueueObjectPool;
import enginePool.IObjectPool;
import enginePool.IPoolableObjectFactory;
import reasoningEngine.ReasoningEngine;
import reasoningEngine.ReasoningEngineFactory;

/**
 * Questa classe crea oggetti di classe APICore.
 * @see APICore
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class APICoreFactory {
	private IObjectPool<ReasoningEngine> pool;
	private RelayImpl relayImpl;
	
	/**
	 * Costruttore di default.
	 * Inizializza la factory con cwd uguale alla cartella "bin" contenuta nella cartella corrente
	 * e numero di richieste concorrenti simultanee pari a 1.
	 */	
	public APICoreFactory() throws Exception {		
		String cwd = System.getProperty("user.dir") + "/bin/";		
		init(1, cwd);
	}
	
	/**
	 * Costruttore.
	 * Inizializza la factory con la stringa cwd e numero di richieste concorrenti simultanee pari a 1.
	 * 
	 * @param cwd Path della working directory
	 */	
	public APICoreFactory(String cwd) throws Exception {		
		init(1, cwd);
	}
	
	/**
	 * Costruttore.
	 * Inizializza la factory con cwd uguale alla cartella "bin" contenuta nella cartella corrente
	 * e numero di richieste concorrenti simultanee pari a poolSize.
	 * 
	 * @param poolSize Numero di richieste concorrenti simultanee
	 */	
	public APICoreFactory(int poolSize) throws Exception {		
		String cwd = System.getProperty("user.dir") + "/bin/";		
		init(poolSize, cwd);
	}
	
	/**
	 * Costruttore.
	 * Inizializza la factory con numero di richieste concorrenti simultanee pari a poolSize 
	 * e la stringa cwd.
	 * 
	 * @param poolSize Numero di richieste concorrenti simultanee
	 * @param cwd Path della working directory
	 */	
	public APICoreFactory(int poolSize, String cwd) throws Exception {
		init(poolSize, cwd);
	}
		
	private void init(int poolSize, String cwd) throws Exception {
		IPoolableObjectFactory<ReasoningEngine> factory = new ReasoningEngineFactory("/usr/bin/yap", cwd, true);
		pool = new BlockingQueueObjectPool<ReasoningEngine>(factory, poolSize);		
		relayImpl = new RelayImpl(pool, cwd);
	}
	
	/**
	 * Istanzia un oggetto APICore.
	 *
	 * @param workspacePath Path del workspace del progetto
	 * @return oggetto APICore
	 */	
	public APICore createAPICore(String workspacePath) {
		return new APICore(relayImpl, workspacePath);
	}
	
	/**
	 * Termina i motori inferenziali presenti nel pool.
	 */	
	public void clearPool() throws Exception {
		if (pool != null) {
			System.out.println("Clearing pool...");
			pool.clear();
		}
	}
}
