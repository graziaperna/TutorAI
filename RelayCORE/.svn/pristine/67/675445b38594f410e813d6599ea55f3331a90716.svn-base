package reasoningEngine;

import com.declarativa.interprolog.PrologEngine;



/**
 *
 * @author pasquale
 *
 * Classe che rappresenta un motore inferenziale.
 */
public class ReasoningEngine {

	private PrologEngine engine;

	public ReasoningEngine() { }

	public ReasoningEngine(PrologEngine engine) {
		setEngine(engine);
	}

	public void setEngine(PrologEngine engine) {
		this.engine = engine;
	}

	public PrologEngine getEngine() {
		return engine;
	}

	public void shutdown() {
		engine.shutdown();
	}

}
