package backward;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * Questa classe definisce i campi per tenere traccia della sessione dell'inferenza backward.
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class BackwardSession implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Intero con la versione della sessione
	 * <br><br>
	 * Questo campo viene utilizzato per tenere traccia dei cambiamenti della sessione.
	 * Pertanto, viene incrementato di 1 a seguito di ogni modifica.
	 * <br><br>
	 * Valore di default: -1
	 */
	private int version = -1;
	
	/**
	 * Stringa con lo stato corrente
	 * <br><br>
	 * Valore di default: null
	 */
	private String status = null;
	
	/**
	 * Stringa con la domanda corrente
	 * <br><br>
	 * Valore di default: null
	 */
	private String question = null;
	
	/**
	 * Booleano che indica se la domanda è binaria (risposta Si/No)
	 * <br><br>
	 * Valore di default: null
	 */
	private Boolean binaryQuestion = null;
		
	/**
	 * Array di stringhe con le possibili risposte alla domanda	corrente (solo per domande a risposta multipla)
	 * <br><br>
	 * Valore di default: null
	 */
	private String[] availableAnswers = null;
	
	/**
	 * Stringa con la spiegazione della domanda corrente
	 * <br><br>
	 * Valore di default: null
	 */
	private String questionWhy = null;
	
	/**
	 * Stringa con la risposta data dall'utente	alla domanda corrente
	 * <br><br>
	 * Valore di default: null
	 */
	private String questionAnswer = null;
	
	/**
	 * Stringa con l'obiettivo dedotto
	 * <br><br>
	 * Valore di default: null
	 */
	private String dedGoal = null;
	
	/**
	 * Probabilità dell'obiettivo dedotto
	 * <br><br>
	 * Valore di default: 0
	 */
	private float dedProb = 0F;
	
	/**
	 * Stringa con la spiegazione della deduzione
	 * <br><br>
	 * Valore di default: null
	 */
	private String dedTrace = null;
	
	/**
	 * Booleano che indica se l'utente richiede altre deduzioni
	 * <br><br>
	 * Valore di default: null
	 */
	private Boolean otherDeductions = null;

	/**
	 * Intero con l'ID del fatto corrente
	 * <br><br>
	 * Valore di default: 100000
	 */
	private Integer currentFactID = 100000;
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Boolean getBinaryQuestion() {
		return binaryQuestion;
	}

	public void setBinaryQuestion(Boolean binaryQuestion) {
		this.binaryQuestion = binaryQuestion;
	}

	public String[] getAvailableAnswers() {
		return availableAnswers;
	}

	public void setAvailableAnswers(String[] availableAnswers) {
		this.availableAnswers = availableAnswers;
	}

	public String getQuestionWhy() {
		return questionWhy;
	}

	public void setQuestionWhy(String questionWhy) {
		this.questionWhy = questionWhy;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getDedGoal() {
		return dedGoal;
	}

	public void setDedGoal(String dedGoal) {
		this.dedGoal = dedGoal;
	}	

	public float getDedProb() {
		return dedProb;
	}

	public void setDedProb(float dedProb) {
		this.dedProb = dedProb;
	}

	public String getDedTrace() {
		return dedTrace;
	}

	public void setDedTrace(String dedTrace) {
		this.dedTrace = dedTrace;
	}

	public Boolean getOtherDeductions() {
		return otherDeductions;
	}

	public void setOtherDeductions(Boolean otherDeductions) {
		this.otherDeductions = otherDeductions;
	}
		
	public Integer getCurrentFactID() {
		return currentFactID;
	}

	public void setCurrentFactID(Integer currentFactID) {
		this.currentFactID = currentFactID;
	}

	public void incrementVersion() {
		this.version++;
	}
	
	public void incrementCurrentFactID() {
		this.currentFactID++;
	}
	
	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> hashMap = new HashMap<>();
		
		hashMap.put("version", version);
		hashMap.put("status", status);
		hashMap.put("question", question);
		hashMap.put("binaryQuestion", binaryQuestion);
		hashMap.put("availableAnswers", availableAnswers);
		hashMap.put("questionWhy", questionWhy);
		hashMap.put("questionAnswer", questionAnswer);
		hashMap.put("dedGoal", dedGoal);
		hashMap.put("dedProb", dedProb);
		hashMap.put("dedTrace", dedTrace);
		hashMap.put("otherDeductions", otherDeductions);
		hashMap.put("currentFactID", currentFactID);
		
		return hashMap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BackwardSession other = (BackwardSession) obj;
		return version == other.version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BackwardSession [version=").append(version).append(", status=").append(status)
				.append(", question=").append(question).append(", binaryQuestion=").append(binaryQuestion)
				.append(", availableAnswers=").append(Arrays.toString(availableAnswers)).append(", questionWhy=")
				.append(questionWhy).append(", questionAnswer=").append(questionAnswer).append(", dedGoal=")
				.append(dedGoal).append(", dedProb=").append(dedProb).append(", dedTrace=").append(dedTrace)
				.append(", otherDeductions=").append(otherDeductions).append(", currentFactID=").append(currentFactID)
				.append("]");
		return builder.toString();
	}
}
