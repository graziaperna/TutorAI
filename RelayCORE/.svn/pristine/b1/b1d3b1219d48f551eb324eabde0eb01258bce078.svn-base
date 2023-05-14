package reasoningEngine;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.declarativa.interprolog.PrologEngine;
import com.declarativa.interprolog.YAPSubprocessEngine;

import enginePool.IPoolableObjectFactory;

/**
 *
 * @author pasquale
 *
 * Classe per la produzione e la distruzione di motori inferenziali.
 */
public class ReasoningEngineFactory implements IPoolableObjectFactory<ReasoningEngine> {
	private static final String REASONING_PROPERTIES_FILE = "reasoning.properties";

	private static final String YAP_PATH_DEFAULT = "yap";
	private static final boolean DEBUG_DEFAULT = false;

	private String yapPath = null;
	private boolean debug = DEBUG_DEFAULT;	
	private String cwd = System.getProperty("user.dir");

	public ReasoningEngineFactory() {
		this(null, DEBUG_DEFAULT);
	}

	public ReasoningEngineFactory(boolean debug) {
		this(null, debug);
	}

	public ReasoningEngineFactory(String yp) {
		this(yp, DEBUG_DEFAULT);
	}

	public ReasoningEngineFactory(String yp, boolean debug) {
		this.debug = debug;
		this.yapPath = yp;

		if (yapPath == null) {
			yapPath = System.getProperty("YAP_PATH");
			if (yapPath == null) {
				URL url = Thread.currentThread().getContextClassLoader()
						.getResource(REASONING_PROPERTIES_FILE);
				if (url != null) {
					try {
						Properties props = new Properties();
						props.load(url.openStream());
						if (props.keySet().contains("yap")) {
							yapPath = props.getProperty("yap");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

			if (yapPath == null) {
				yapPath = YAP_PATH_DEFAULT;
			}
		}
	}
	
	public ReasoningEngineFactory(String yp, String cwd, boolean debug) {
		this(yp, debug);
		this.cwd = cwd;
	}

	public void destroyObject(ReasoningEngine obj) {
		_destroyObject(obj.getEngine());
	}

	private void _destroyObject(PrologEngine obj) {
		obj.shutdown();
	}

	public ReasoningEngine makeObject() {
		return new ReasoningEngine(_makeObject());
	}

	private PrologEngine _makeObject() {
		PrologEngine ret = new YAPSubprocessEngine(yapPath, debug);
		ret.setThreadedCallbacks(false);
		ret.deterministicGoal("use_module(library(system))");
		return ret;
	}
	
	public void initialiseObject(ReasoningEngine obj) throws Exception {
		// XXX: hack
		_destroyObject(obj.getEngine());
		obj.setEngine(_makeObject());		
		obj.getEngine().deterministicGoal("working_directory(_,'" + cwd.replace("\\", "/") + "')");
	}
}
