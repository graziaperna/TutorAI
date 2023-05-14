package kbManagement.factRecognition;

public class FactRecognizer {

	public static int getFactId(String fact) {
		int id;
		String idSplitted = "";
		String[] idSplit = fact.split("\\,");
		idSplitted = idSplit[0];
		int idSplittedLength = idSplitted.length();
		id = Integer.parseInt(idSplitted.substring(5, idSplittedLength));
		return id;
	}

public static float getFactCertezza(String fact) {
		
		float certezza;
		String certezzaSplitted = "";
		String[] certezzaSplit = fact.split("\\,");
		certezzaSplitted = certezzaSplit[certezzaSplit.length - 1];
		try {
			certezza = Float.parseFloat(certezzaSplitted.substring(0, certezzaSplitted.length() - 2));
		} catch (Exception e) {
			certezza = 0;
		}
		
		return certezza;
	}
}
		
