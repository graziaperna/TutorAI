package kbManagement.moduleGui;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import kbManagement.factRecognition.ConstantsRecognizerFact;
import kbManagement.factRecognition.FactRecognizer;
import kbManagement.factRecognition.PredicateRecognizerFact;
import kbManagement.filesWR.ReplaceInFile;
import kbManagement.filesWR.WriteFile;
import kbManagement.filesWR.Utility;
import kbManagement.org.eclipse.wb.swt.SWTResourceManager;
import kbManagement.ruleRecognition.RuleRecognizer;
import kbManagement.structuredInput.Comments;
import kbManagement.structuredInput.Constants;
import kbManagement.structuredInput.Facts;
import kbManagement.structuredInput.Predicate;

public class FattiForm{

	 SashForm sashForm;
	 private Label lblListaFattiEsistenti;
	 private Combo comboFatti;
	 private Button btnModificaF;
	 private Button btnEliminaF;
	 private Button btnNuovoFatto;
	 private Label lblModificaFatto;
	 private Text textID;
	 private Text textCertezza;
	 private Text textCommento;
	 private Composite compositeCentralLeft;
	 private Composite compositeCentralRight;
	 private Composite compositeCentral;
	 private String strItemSelect;
	 private Combo comboPredicati;
	 private Label lblPredicati;
	 private Composite compositeDown;
	 private int numbersPred;
	 private List listArgomenti;
	 private boolean bNewFact;
	 private String commentFactSelected;
	
	public FattiForm(TabFolder folder) {
		
		 sashForm = new SashForm(folder, SWT.VERTICAL);
		 
		 Composite compositeHigh = new Composite(sashForm, SWT.NONE);
		 compositeHigh.setLayout(new GridLayout(4, false));
		 
		 lblListaFattiEsistenti = new Label(compositeHigh, SWT.NONE);
		 lblListaFattiEsistenti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 lblListaFattiEsistenti.setText("Lista Fatti esistenti");
		 
		 comboFatti = new Combo(compositeHigh, SWT.NONE);
		 comboFatti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		 comboFatti.addSelectionListener(new SelectionAdapter() {
			 
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 comboFattiItemSelect();				 
	            }
			 
		});
		 
		 btnModificaF = new Button(compositeHigh, SWT.NONE);
		 btnModificaF.setEnabled(false);
		 btnModificaF.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 btnModificaF.setText("Modifica");
		 btnModificaF.addListener(SWT.Selection, new Listener()
			{
			    @Override
			    public void handleEvent(Event event)
			    {
			        
			    	updateFactAction();
			    }
			});
	     
	     btnEliminaF = new Button(compositeHigh, SWT.NONE);
	     btnEliminaF.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
	     btnEliminaF.setEnabled(false);
		 btnEliminaF.setText("Elimina");
		 btnEliminaF.addListener(SWT.Selection, new Listener()
			{
			    @Override
			    public void handleEvent(Event event)
			    {
			        
			    	deleteFactAction();
			    }
			});
	     
		 
		 compositeCentral = new Composite(sashForm, SWT.NONE);
		 compositeCentral.setLayout(new FillLayout(SWT.HORIZONTAL));
		 
		 compositeCentralLeft = new Composite(compositeCentral, SWT.BORDER);
		 compositeCentralLeft.setLayout(new GridLayout(2, false));
		 
		 Label lblId = new Label(compositeCentralLeft, SWT.NONE);
		 lblId.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 lblId.setText("ID");
		 lblId.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 
		 textID = new Text(compositeCentralLeft, SWT.BORDER);
		 textID.setEnabled(false);
		 textID.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		 
		 Label lblCertezza = new Label(compositeCentralLeft, SWT.NONE);
		 lblCertezza.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 lblCertezza.setText("Certezza");
		 lblCertezza.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 
		 textCertezza = new Text(compositeCentralLeft, SWT.BORDER);
		 textCertezza.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		 new Label(compositeCentralLeft, SWT.NONE);
		 new Label(compositeCentralLeft, SWT.NONE);
		 
		 Label label = new Label(compositeCentralLeft, SWT.NONE);
		 label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 label.setText("Commento");
		 label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 new Label(compositeCentralLeft, SWT.NONE);
		 
		 textCommento = new Text(compositeCentralLeft, SWT.BORDER);
		 GridData gd_textCommento = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		 gd_textCommento.widthHint = 243;
		 gd_textCommento.heightHint = 66;
		 textCommento.setLayoutData(gd_textCommento);
		 
		 compositeCentralRight = new Composite(compositeCentral, SWT.BORDER);
		 compositeCentralRight.setLayout(new GridLayout(2, false));
		 new Label(compositeCentralRight, SWT.NONE);
		 
		 lblModificaFatto = new Label(compositeCentralRight, SWT.CENTER);
		 lblModificaFatto.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 lblModificaFatto.setText("Modifica Fatto");
		 lblModificaFatto.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		 
		 lblPredicati = new Label(compositeCentralRight, SWT.NONE);
		 lblPredicati.setText("Predicati");
		 
		 comboPredicati = new Combo(compositeCentralRight, SWT.NONE);
		 comboPredicati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 comboPredicati.addSelectionListener(new SelectionAdapter() {
			 
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 comboPredicatiItemSelect();			 
	            }			 
		});
		 new Label(compositeCentralRight, SWT.NONE);
		 new Label(compositeCentralRight, SWT.NONE);
		 
		 
		 
		 Label lblArgomenti = new Label(compositeCentralRight, SWT.NONE);
		 lblArgomenti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 lblArgomenti.setText("Argomenti:");
		 lblArgomenti.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		 
		 Label lblselezionaUnElemento = new Label(compositeCentralRight, SWT.NONE);
		 lblselezionaUnElemento.setText("(Seleziona un elemento per cambiarlo)");
		 
		 
		 listArgomenti = new List(compositeCentralRight, SWT.BORDER);
		 GridData gd_listArgomenti = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		 gd_listArgomenti.heightHint = 171;
		 gd_listArgomenti.widthHint = 148;
		 listArgomenti.setLayoutData(gd_listArgomenti);
		 listArgomenti.addSelectionListener(new SelectionAdapter() {
			   @Override
		        public void widgetSelected(SelectionEvent e) {
				   int[] selections = listArgomenti.getSelectionIndices();
				   if(selections.length > 0)
				   {
					   String strSelected = listArgomenti.getItem(selections[0]);
					   if(!strSelected.isEmpty())
					   {
						   callArgomentDialog(strSelected,selections[0]);
					   }
				   }				   		           
		        } 		 
		 });
		 	 		 
		 new Label(compositeCentralRight, SWT.NONE);
		 Button btnSave = new Button(compositeCentralRight, SWT.CENTER);
		 btnSave.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		 btnSave.setText("Salva");
		 btnSave.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		 btnSave.addListener(SWT.Selection, new Listener(){
		 	    @Override
		 	    public void handleEvent(Event event)
		 	    {		 	    	
		 	    	saveFact();		 	        
		 	    }
		 	});
		 
		 
		 compositeDown = new Composite(sashForm, SWT.NONE);
		 compositeDown.setLayout(new GridLayout(1, false));
		 
		 btnNuovoFatto = new Button(compositeDown, SWT.NONE);
		 btnNuovoFatto.setAlignment(SWT.LEFT);
		 btnNuovoFatto.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		 btnNuovoFatto.setText("Nuovo Fatto");
		 sashForm.setWeights(new int[] {35, 284, 36});
		 
		 btnNuovoFatto.addListener(SWT.Selection, new Listener()
		 	{
		 	    @Override
		 	    public void handleEvent(Event event)
		 	    {
		 	        
		 	    	newFactAction();
		 	    }
		 	});
		 
		compositeCentralLeft.setVisible(false);
		compositeCentralRight.setVisible(false);
	 }
	
	public void newFactAction()
	{
		bNewFact = true;
		lblModificaFatto.setText("Nuovo Fatto");
		btnEliminaF.setEnabled(false);
		btnModificaF.setEnabled(false);
		comboFatti.deselectAll();
		comboFatti.clearSelection();
		comboPredicati.deselectAll();
		comboPredicati.clearSelection();
		textID.setText(Facts.generateId());
		textCertezza.setText("");
		textCommento.setText("");
		listArgomenti.removeAll();
		
		if((!compositeCentralLeft.isVisible())||(!compositeCentralRight.isVisible()))
		{
			compositeCentralLeft.setVisible(true);
			compositeCentralRight.setVisible(true);			
		}		
		sashForm.layout();
	}
	
	public void updateFactAction()
	{
		bNewFact = false;
		lblModificaFatto.setText("Modifica Fatto");
		//dati caricati in strItemSelect
		String strIdFact = Integer.toString(FactRecognizer.getFactId(strItemSelect)); 
		textID.setText(strIdFact);
		textCertezza.setText(Float.toString(FactRecognizer.getFactCertezza(strItemSelect)));
		
		String predSelected = PredicateRecognizerFact.predicateRecognizer(strItemSelect).get(0);
		
		//predicati
		for(int i = 0; i<numbersPred; i++)
		{
			String itemCombo = comboPredicati.getItem(i);
			if(itemCombo.equals(predSelected))
			{
				comboPredicati.select(i);
				break;
			}
		}
		
		//Commenti
		for(int i=0; i<Comments.commentFacts.size();i++)
		{
			if(Comments.commentFacts.get(i).get(0).equalsIgnoreCase(strIdFact))
			{
				textCommento.setText(Comments.commentFacts.get(i).get(1));
				commentFactSelected = "commentf(" + Comments.commentFacts.get(i).get(0) + "," + Comments.commentFacts.get(i).get(1) + ").";
				break;
			}
		}	
		
		//Costanti
		String testFactSelected = PredicateRecognizerFact.factRecognizer(strItemSelect).get(0);		
		ArrayList<String> listConstants = new ArrayList<String>();
		listConstants.addAll(ConstantsRecognizerFact.constantsRecognizerFact(testFactSelected));
		listArgomenti.removeAll();
		for (String strItemConst : listConstants) {
			listArgomenti.add(strItemConst);
		}		
		
		if((!compositeCentralLeft.isVisible())||(!compositeCentralRight.isVisible()))
		{
			compositeCentralLeft.setVisible(true);
			compositeCentralRight.setVisible(true);
		}	
		sashForm.layout();
	}
	
	private void callArgomentDialog(String strSelected, int idItemSel)
	{
        final Shell dialogShell = new Shell(sashForm.getShell(), SWT.PRIMARY_MODAL | SWT.SHEET);
        dialogShell.setText("Seleziona una nuova costante");
        
        dialogShell.setLayout(new GridLayout(3, false));
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		
		Label lblConstanti = new Label(dialogShell, SWT.NONE);
		lblConstanti.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConstanti.setText("Constanti:");
		
		Combo comboConstantiLocal = new Combo(dialogShell, SWT.NONE);
		GridData gd_comboConstantiLocal = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_comboConstantiLocal.widthHint = 355;
		comboConstantiLocal.setLayoutData(gd_comboConstantiLocal);
		//Popolamento delle costanti
		ArrayList<String> listConstanti = new ArrayList<String>();
		listConstanti.addAll(Constants.constants);
		for (String strConst : listConstanti) {
			comboConstantiLocal.add(strConst);
		}
		
		for(int i = 0; i<Constants.constants.size(); i++)
		{
			String itemCombo = comboConstantiLocal.getItem(i);
			if(itemCombo.equals(strSelected))
			{
				comboConstantiLocal.select(i);
				break;
			}
		}		
		
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		
		Composite compositeBtn = new Composite(dialogShell, SWT.NONE);
		compositeBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeBtn.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnOK = new Button(compositeBtn, SWT.CENTER);
		btnOK.setBounds(0, 0, 75, 25);
		btnOK.setText("OK");
		btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	int idx = comboConstantiLocal.getSelectionIndex();
        		String strItemSel = comboConstantiLocal.getItem(idx);
        		listArgomenti.setItem(idItemSel, strItemSel);
        		dialogShell.dispose();
            }
        });	
		
		new Label(dialogShell, SWT.NONE);
		
		Button btnAnnulla = new Button(compositeBtn, SWT.NONE);
		btnAnnulla.setText("Annulla");
		btnAnnulla.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                dialogShell.dispose();
            }
        });
		
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
               
        dialogShell.pack();
        dialogShell.open();
	}
	
	
	public void comboFattiItemSelect(){
		int idx = comboFatti.getSelectionIndex();
		strItemSelect = comboFatti.getItem(idx);
		btnEliminaF.setEnabled(true);
		btnModificaF.setEnabled(true);
	}
	
	public void comboPredicatiItemSelect() 
	{
		int idx = comboPredicati.getSelectionIndex();
		String strItemSel = comboPredicati.getItem(idx);
		int numConstanti = Integer.parseInt(Predicate.getNumArguments(strItemSel));
		ArrayList<String> allConstants = new ArrayList<String>();
		allConstants.addAll(Constants.constants);
		listArgomenti.removeAll();
		for (int i = 0; i < numConstanti; i++) {
			listArgomenti.add(allConstants.get(i));
		}
	}
	
	public SashForm getForm() {
		return sashForm;
	}
	
	public void saveFact()
	{
		String[] arrStrArgomenti = listArgomenti.getItems();
		String finalConstantsFormatted = "";
		for (int i = 0; i < arrStrArgomenti.length; i++) {
			finalConstantsFormatted += arrStrArgomenti[i];
			if(i != arrStrArgomenti.length - 1)
			{
				finalConstantsFormatted += ",";
			}
		}
		
		int idx = comboPredicati.getSelectionIndex();
		String strPredSel = comboPredicati.getItem(idx);
		strPredSel = strPredSel.substring(0,strPredSel.length()-2);
		String strCertezza = "";
		try {
			int nCertezza = (int)Float.parseFloat(textCertezza.getText());
			if(!(nCertezza<0||nCertezza>1))
			{
				strCertezza = Integer.toString(nCertezza);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			strCertezza = "";
		}
		
		if(!(strPredSel.isEmpty()&& finalConstantsFormatted.isEmpty() &&strCertezza.isEmpty())) {
			String strFinalFact = "fact("+textID.getText()+","+strPredSel+"("+finalConstantsFormatted+"),"+strCertezza+").";			
			
			if(bNewFact)
	        {
				//Nuovo Fatto
	        	WriteFile.writeFile("\r\n"+strFinalFact);
	        	Facts.addFact(strFinalFact);
	        	
	        }
	        else
	        {
	        	//Modifica fatto
	        	String toReplaceModified = Utility.addParenthesis(strItemSelect);
	        	try {
					ReplaceInFile.replaceInFile(toReplaceModified, strFinalFact);
					Facts.facts.remove(strItemSelect);
					Facts.addFact(strFinalFact);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
			
			
			String strFinalComment = "commentf("+textID.getText()+","+textCommento.getText()+").";
			if(!textCommento.getText().isEmpty() && commentFactSelected.isEmpty())
        	{
        		WriteFile.writeFile("\r\n"+strFinalComment);
        		
        		ArrayList<String> modedComment = new ArrayList<String>();
				modedComment.add(textID.getText());
				modedComment.add(textCommento.getText());
				Comments.commentFacts.add(modedComment);
        	}else if(!commentFactSelected.isEmpty() && !textCommento.getText().isEmpty())
        	{
        		String toReplaceCommentModified = Utility.addParenthesis(commentFactSelected);
				try {
					ReplaceInFile.replaceInFile(toReplaceCommentModified, strFinalComment);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int j=0; j<Comments.commentFacts.size();j++) {
					if(Comments.commentFacts.get(j).get(0).equals(textID.getText())) {
						Comments.commentFacts.remove(j);
						ArrayList<String> modedComment = new ArrayList<String>();
						modedComment.add(textID.getText());
						modedComment.add(textCommento.getText());
						Comments.commentFacts.add(modedComment);
					}
				}
				
        	}
						
			refreshData();
			MessageBox messageBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);
	        
	        messageBox.setText("Fatto");
	        messageBox.setMessage("Fatto salvato!");
	        messageBox.open();
		}
		
		
		
	}
	
	@SuppressWarnings("unused")
	public void deleteFactAction()
	{
		
		String idFactSelected = Integer.toString(RuleRecognizer.getRuleId(strItemSelect));

		String toReplaceModified = Utility.addParenthesis(strItemSelect);
		try {
			ReplaceInFile.replaceInFile(toReplaceModified, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Facts.facts.remove(strItemSelect);
		for(int i=0; i<Comments.commentFacts.size();i++)
		{
			if(Comments.commentFacts.get(i).get(0).equalsIgnoreCase(idFactSelected))
			{
				String textcomment = Comments.commentFacts.get(i).get(1);
				if(!textcomment.isEmpty())
				{
					Comments.commentFacts.remove(i);				
					String finalCommentFact = "commentf(" + idFactSelected + "," + textcomment + ").";
					String toReplaceCommentModified = Utility.addParenthesis(finalCommentFact);
					try {
						ReplaceInFile.replaceInFile(toReplaceCommentModified, "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}					
				break;
			}
		}	
		
		
		refreshData();
		MessageBox messageBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);
        
        messageBox.setText("Fatto");
        messageBox.setMessage("Fatto eliminato!");
        messageBox.open();
	}
	
	
	public void refreshData() {
		compositeCentralLeft.setVisible(false);
		compositeCentralRight.setVisible(false);
		comboFatti.removeAll();
		comboPredicati.removeAll();
		textID.setText("");
		textCertezza.setText("");
		textCommento.setText("");
		bNewFact = true;
		commentFactSelected = "";
		
		//Popolamento della lista dei Fatti da file
		ArrayList<String> listFacts = new ArrayList<String>();
		listFacts.addAll(Facts.facts);
		for (String strFact : listFacts) {
			comboFatti.add(strFact);
		}
		
		//Popolamento della lista dei Predicati da file
		ArrayList<String> listPreds = new ArrayList<String>();
		numbersPred = 0;
		listPreds.addAll(Predicate.predicates);
		for (String strPred : listPreds) {
			comboPredicati.add(strPred);
			numbersPred++;
		}
		sashForm.layout();
	}
}
