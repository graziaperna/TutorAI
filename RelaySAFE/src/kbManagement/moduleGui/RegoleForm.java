package kbManagement.moduleGui;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
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

//import com.sun.javafx.css.Rule;

import kbManagement.filesWR.ReplaceInFile;
import kbManagement.filesWR.WriteFile;
import kbManagement.filesWR.Utility;
import kbManagement.org.eclipse.wb.swt.SWTResourceManager;
import kbManagement.ruleRecognition.RuleRecognizer;
import kbManagement.structuredInput.Comments;
import kbManagement.structuredInput.Rules;
import kbManagement.structuredInput.SpecialRules;
import kbManagement.structuredInput.Variables;


public class RegoleForm{

	 SashForm sashForm;
	 private Label lblListaRegoleEsistenti;
	 private Combo comboRules;
	 private Button btnModifica;
	 private Button btnElimina;
	 private Button btnNuovaregola;
	 private Composite composite_central;
	 private Composite compositeDown;
	 private Label lblModificaRegole;
	 private Label lblPedicati;
	 private Label lblCommento;
	 private Text textId;
	 private Text text_Priority;
	 private Button btnNuovoRegolaCon;
	 private Button btnEliminaTeste;
	 private Button btnAggiungiCodaO;
	 private Button btnEliminaCode;
	 private List listTesta;
	 private Label lblPriorit;
	 private Text textCertainty;
	 private Label label;
	 private Button btnAggiungiVariabile;
	 private Button btnAddTestaItem;
	 private Text textComment;
	 private Combo comboOperators;
	 private Composite compositeCentralRight;
	 private Composite compositeCentralLeft;
	 private List listVariabili;
	 private List listPremesse;
	 private String strSelElemTesta;
	 private String strSelElemPremesse;
	 private String strOperPremesse;
	 private String strRuleSelect;
	 private String commentRuleSelected;
	
	public RegoleForm(TabFolder folder) {

		 sashForm = new SashForm(folder, SWT.VERTICAL);
		 
		 Composite compositeHigh = new Composite(sashForm, SWT.NONE);
		 compositeHigh.setLayout(new GridLayout(4, false));
		 
		 lblListaRegoleEsistenti = new Label(compositeHigh, SWT.NONE);
		 lblListaRegoleEsistenti.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		 lblListaRegoleEsistenti.setText("Lista Regole esistenti");
		 
		 comboRules = new Combo(compositeHigh, SWT.NONE);
		 GridData gd_comboRules = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		 gd_comboRules.widthHint = 204;
		 comboRules.setLayoutData(gd_comboRules);
		 comboRules.addSelectionListener(new SelectionAdapter() {			 
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 	int idx = comboRules.getSelectionIndex();
				 	strRuleSelect = comboRules.getItem(idx);
				 	btnElimina.setEnabled(true);
				 	btnModifica.setEnabled(true);
	            }
			 
		 });
		 
		 btnModifica = new Button(compositeHigh, SWT.NONE);
		 btnModifica.setText("Modifica");
		 btnModifica.addListener(SWT.Selection, new Listener()
			{
			    @Override
			    public void handleEvent(Event event)
			    {
			    	uploadRuleAction();
			    }
			});
		 
	     btnModifica.setEnabled(false);
	     
	     btnElimina = new Button(compositeHigh, SWT.NONE);
	     btnElimina.setText("Elimina");
	     btnElimina.addListener(SWT.Selection, new Listener()
			{
			    @Override
			    public void handleEvent(Event event)
			    {
			    	try {
						deleteRuleAction();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
		 btnElimina.setEnabled(false);
		 
		 composite_central = new Composite(sashForm, SWT.BORDER);
		 composite_central.setLayout(new GridLayout(10, false));
		 
		 compositeCentralLeft = new Composite(composite_central, SWT.BORDER);
		 compositeCentralLeft.setLayout(new GridLayout(2, false));
		 GridData gd_compositeCentralLeft = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 3);
		 gd_compositeCentralLeft.widthHint = 135;
		 gd_compositeCentralLeft.heightHint = 209;
		 compositeCentralLeft.setLayoutData(gd_compositeCentralLeft);
		 
		 Label lblId = new Label(compositeCentralLeft, SWT.NONE);
		 lblId.setText("ID");
		 lblId.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 
		 textId = new Text(compositeCentralLeft, SWT.BORDER);
		 textId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 textId.setEnabled(false);
		 
		 lblPriorit = new Label(compositeCentralLeft, SWT.NONE);
		 lblPriorit.setText("Priorit\u00E0");
		 lblPriorit.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 
		 text_Priority = new Text(compositeCentralLeft, SWT.BORDER);
		 text_Priority.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 
		 Label lblCertezza = new Label(compositeCentralLeft, SWT.NONE);
		 lblCertezza.setText("Certezza");
		 lblCertezza.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 
		 textCertainty = new Text(compositeCentralLeft, SWT.BORDER);
		 textCertainty.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 
		 lblCommento = new Label(compositeCentralLeft, SWT.NONE);
		 lblCommento.setText("Lista variabili");
		 lblCommento.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 
		 btnAggiungiVariabile = new Button(compositeCentralLeft, SWT.NONE);
		 btnAggiungiVariabile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		 btnAggiungiVariabile.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		 btnAggiungiVariabile.setText("Aggiungi Variabile");
		 btnAggiungiVariabile.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {
			 		callDialogVariable("");
			 	}
		 });
		 
		 listVariabili = new List(compositeCentralLeft, SWT.BORDER);
		 GridData gd_listVariabili = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		 gd_listVariabili.heightHint = 22;
		 listVariabili.setLayoutData(gd_listVariabili);
		 listVariabili.addSelectionListener(new SelectionAdapter() {
			   @Override
		        public void widgetSelected(SelectionEvent e) {
				   int[] selections = listVariabili.getSelectionIndices();
				   if(selections.length > 0)
				   {
					   String strSelected = listVariabili.getItem(selections[0]);
					   if(!strSelected.isEmpty())
					   {
						   callDialogVariable(strSelected);
					   }
				   }				   		           
		        } 		 
		 });
		 
		 label = new Label(compositeCentralLeft, SWT.NONE);
		 label.setText("Commento");
		 label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 new Label(compositeCentralLeft, SWT.NONE);
		 
		 textComment = new Text(compositeCentralLeft, SWT.BORDER);
		 textComment.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		 
		 compositeCentralRight = new Composite(composite_central, SWT.BORDER);
		 compositeCentralRight.setLayout(new GridLayout(3, false));
		 GridData gd_compositeCentralRight = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 3);
		 gd_compositeCentralRight.widthHint = 318;
		 gd_compositeCentralRight.heightHint = 204;
		 compositeCentralRight.setLayoutData(gd_compositeCentralRight);
		 new Label(compositeCentralRight, SWT.NONE);
		 
		 lblModificaRegole = new Label(compositeCentralRight, SWT.CENTER);
		 lblModificaRegole.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		 lblModificaRegole.setText("Nuova Regola");
		 lblModificaRegole.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		 new Label(compositeCentralRight, SWT.NONE);
		 
		 lblPedicati = new Label(compositeCentralRight, SWT.NONE);
		 lblPedicati.setText("Testa");
		 lblPedicati.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		 new Label(compositeCentralRight, SWT.NONE);
		 new Label(compositeCentralRight, SWT.NONE);
		 
		 listTesta = new List(compositeCentralRight, SWT.BORDER);
		 GridData gd_listTesta = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2);
		 gd_listTesta.widthHint = 147;
		 listTesta.setLayoutData(gd_listTesta);
		 listTesta.addSelectionListener(new SelectionAdapter() {
			   @Override
		        public void widgetSelected(SelectionEvent e) {
				   int[] selections = listTesta.getSelectionIndices();
				   if(selections.length > 0)
				   {
					   String strSelected = listTesta.getItem(selections[0]);
					   if(!strSelected.isEmpty())
					   {
						   strSelElemTesta = strSelected;
						  if(!btnEliminaTeste.isEnabled())
						  {
							  btnEliminaTeste.setEnabled(true);
							  sashForm.layout();
						  }
					   }
				   } else {
					   strSelElemTesta = "";
					   if(btnEliminaTeste.isEnabled()){
						  btnEliminaTeste.setEnabled(false);
						  sashForm.layout();
					   }
				   }
		        } 		 
		 });
		 strSelElemTesta = "";
		 
		 
		 btnAddTestaItem = new Button(compositeCentralRight, SWT.NONE);
		 btnAddTestaItem.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		 btnAddTestaItem.setText("Aggiungi elemento");
		 btnAddTestaItem.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		 RegoleForm regoleForm = this;
		 btnAddTestaItem.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {
			 		if(listVariabili.getItemCount() > 0)
			 		{
			 			AtomDialog atomDialog = new AtomDialog();
				 		atomDialog.setListVariabili(listVariabili.getItems());
				 		atomDialog.openDialog(sashForm,regoleForm,true);
			 		}else {
			 			MessageBox messageBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);				        
				        messageBox.setText("Regole");
				        messageBox.setMessage("Inserisci le variabili!");
				        messageBox.open();
			 		}			 		
			 		
			 	}
			 });
		 
		 btnEliminaTeste = new Button(compositeCentralRight, SWT.NONE);
		 GridData gd_btnEliminaTeste = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		 gd_btnEliminaTeste.widthHint = 132;
		 btnEliminaTeste.setLayoutData(gd_btnEliminaTeste);
		 btnEliminaTeste.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		 btnEliminaTeste.setText("Elimina elemento dalle teste");
		 btnEliminaTeste.setEnabled(false);
		 btnEliminaTeste.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {
			 		if(!strSelElemTesta.isEmpty())
			 		{
			 			listTesta.remove(strSelElemTesta);
			 			strSelElemTesta = "";
			 			btnEliminaTeste.setEnabled(false);
			 			sashForm.layout();
			 		}			 		
			 	}
			 });
		 
		 
		 new Label(compositeCentralRight, SWT.NONE);
		 
		 comboOperators = new Combo(compositeCentralRight, SWT.NONE);
		 comboOperators.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 new Label(compositeCentralRight, SWT.NONE);
		 comboOperators.add("OR");
		 comboOperators.add("AND");
		 comboOperators.add("NOT");
		 
		 
		 Label lblArgomenti = new Label(compositeCentralRight, SWT.NONE);
		 lblArgomenti.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		 lblArgomenti.setText("Premesse");
		 lblArgomenti.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		 new Label(compositeCentralRight, SWT.NONE);
		 
		 listPremesse = new List(compositeCentralRight, SWT.BORDER);
		 GridData gd_listPremesse = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 4);
		 gd_listPremesse.widthHint = 148;
		 listPremesse.setLayoutData(gd_listPremesse);
		 listPremesse.addSelectionListener(new SelectionAdapter() {
			   @Override
		        public void widgetSelected(SelectionEvent e) {
				   int[] selections = listPremesse.getSelectionIndices();
				   if(selections.length > 0)
				   {
					   String strSelected = listPremesse.getItem(selections[0]);
					   if(!strSelected.isEmpty())
					   {
						   strSelElemPremesse = strSelected;
						  if(!btnEliminaCode.isEnabled())
						  {
							  btnEliminaCode.setEnabled(true);
							  sashForm.layout();
						  }
					   }
				   } else {
					   strSelElemPremesse = "";
					   if(btnEliminaCode.isEnabled()){
						   btnEliminaCode.setEnabled(false);
						  sashForm.layout();
					   }
				   }
		        } 		 
		 });
		 strSelElemPremesse = "";
		 
		 
		 btnAggiungiCodaO = new Button(compositeCentralRight, SWT.NONE);
		 GridData gd_btnAggiungiCodaO = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		 gd_btnAggiungiCodaO.widthHint = 120;
		 btnAggiungiCodaO.setLayoutData(gd_btnAggiungiCodaO);
		 btnAggiungiCodaO.setText("Aggiungi elemento");
		 btnAggiungiCodaO.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		 btnAggiungiCodaO.addSelectionListener(new SelectionAdapter() {
			 @Override
			 	public void widgetSelected(SelectionEvent e) {
			 		if(listVariabili.getItemCount() > 0)
			 		{
			 			AtomDialog atomDialog = new AtomDialog();
				 		atomDialog.setListVariabili(listVariabili.getItems());
				 		atomDialog.openDialog(sashForm,regoleForm,false);
			 		}else {
			 			MessageBox messageBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);				        
				        messageBox.setText("Regole");
				        messageBox.setMessage("Inserisci le variabili!");
				        messageBox.open();
			 		}		 		
			 	}
		});
		 
		 Button btnAggiungiOperatore = new Button(compositeCentralRight, SWT.NONE);
		 btnAggiungiOperatore.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		 btnAggiungiOperatore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		 btnAggiungiOperatore.setText("Aggiungi operatore");
		 btnAggiungiOperatore.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {			 		
			 		
			 		callDialogOperatorPremesse();
			 	}
			 });
		 
		 btnEliminaCode = new Button(compositeCentralRight, SWT.NONE);
		 btnEliminaCode.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		 btnEliminaCode.setText("Elimina elemento dalle code");
		 btnEliminaCode.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		 btnEliminaCode.setEnabled(false);
		 btnEliminaCode.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {
			 		
			 		if(!strSelElemPremesse.isEmpty())
			 		{
			 			listPremesse.remove(strSelElemPremesse);
			 			strSelElemPremesse = "";
			 			btnEliminaCode.setEnabled(false);
			 			sashForm.layout();
			 		}			 		
			 	}
			 });
		 
		 
		 Button btnSave = new Button(compositeCentralRight, SWT.CENTER);
		 btnSave.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1));
		 btnSave.setText("Salva");
		 btnSave.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		 btnSave.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {
			 		saveRuleAction();
			 	}
		 });
		 
		 compositeDown = new Composite(sashForm, SWT.NONE);
		 
		 btnNuovaregola = new Button(compositeDown, SWT.NONE);
		 btnNuovaregola.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		newRuleActionBtn();
		 	}
		 });
		 btnNuovaregola.setLocation(10, 0);
		 btnNuovaregola.setSize(192, 31);
		 btnNuovaregola.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		 btnNuovaregola.setText("Nuova Regola");
		 
		 btnNuovoRegolaCon = new Button(compositeDown, SWT.NONE);
		 btnNuovoRegolaCon.setBounds(218, 0, 222, 31);
		 btnNuovoRegolaCon.setText("Nuova Regola con funzioni");
		 btnNuovoRegolaCon.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		 btnNuovoRegolaCon.setEnabled(false);
		 sashForm.setWeights(new int[] {33, 226, 35});
		 
		compositeCentralLeft.setVisible(false);
		compositeCentralRight.setVisible(false);
	}
	
	
	
	protected void callDialogVariable(String strVariable) {
				
		//dialog
		final Shell dialogShell = new Shell(sashForm.getShell(), SWT.PRIMARY_MODAL | SWT.SHEET);
		dialogShell.setSize(431, 184);		
		
		dialogShell.setLayout(new GridLayout(4, false));
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		
		Label lblInserisciUnaNuova = new Label(dialogShell, SWT.NONE);
		lblInserisciUnaNuova.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblInserisciUnaNuova.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 4, 1));		
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		
		Label lblNomeVariabile = new Label(dialogShell, SWT.NONE);
		lblNomeVariabile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNomeVariabile.setText("Nome variabile");
		
		Text textVariableName = new Text(dialogShell, SWT.BORDER);
		textVariableName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
		new Label(dialogShell, SWT.NONE);
			
		
		Button btnOK = new Button(dialogShell, SWT.NONE);
		GridData gd_btnOK = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnOK.widthHint = 63;
		btnOK.setLayoutData(gd_btnOK);
		btnOK.setText("OK");
		btnOK.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		String strVariabileText = textVariableName.getText();
		 		if(!strVariabileText.isEmpty())
		 		{
		 			//elimino il vecchio elemento
		 			if(!strVariable.isEmpty())
		 			{
		 				listVariabili.remove(strVariable);
		 			}
		 			listVariabili.add(strVariabileText);
		 			dialogShell.close();
		 		}
		 	}
		});
		
		Button btnAnnulla = new Button(dialogShell, SWT.NONE);
		GridData gd_btnAnnulla = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAnnulla.widthHint = 76;
		btnAnnulla.setLayoutData(gd_btnAnnulla);
		btnAnnulla.setText("Annulla");	
		btnAnnulla.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		dialogShell.close();
		 	}
		});
		
		if(strVariable.isEmpty())
		{
			dialogShell.setText("Nuova variabile");
			lblInserisciUnaNuova.setText("Inserisci una nuova variabile per questa regola");
			btnOK.setText("Aggiungi");
		}
		else
		{
			dialogShell.setText("Modifica o elimina variabile");
			lblInserisciUnaNuova.setText("Modifica o elimina variabile per questa regola");
			textVariableName.setText(strVariable);			
			btnOK.setText("Modifica");
			btnElimina = new Button(dialogShell, SWT.NONE);
			btnElimina.setText("Elimina");
			btnElimina.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {
			 		if(!strVariable.isEmpty())
			 		{
			 			listVariabili.remove(strVariable);
			 		}
			 		dialogShell.close();
			 	}
			});
		}		
		
		dialogShell.pack();
		dialogShell.open();
	}
	
	protected void callDialogOperatorPremesse() {
		
		//dialog
		final Shell dialogShell = new Shell(sashForm.getShell(), SWT.PRIMARY_MODAL | SWT.SHEET);
		dialogShell.setSize(349, 150);
		dialogShell.setText("Aggiungi operatore");
		dialogShell.setLayout(new FormLayout());
		
		Composite composite = new Composite(dialogShell, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(0, 130);
		fd_composite.right = new FormAttachment(0, 333);
		composite.setLayoutData(fd_composite);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblSelezionaOperatore = new Label(composite, SWT.NONE);
		lblSelezionaOperatore.setText("Seleziona operatore:");
		
		Combo comboOperatorsLoc = new Combo(composite, SWT.NONE);
		comboOperatorsLoc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboOperatorsLoc.add("OR");
		comboOperatorsLoc.add("AND");
		comboOperatorsLoc.add("NOT");
		strOperPremesse = "";
		comboOperatorsLoc.addSelectionListener(new SelectionAdapter() {			 
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 	int idx = comboOperatorsLoc.getSelectionIndex();
				 	strOperPremesse = comboOperatorsLoc.getItem(idx);				 				 
	            }			 
		});
		
		
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnConferma = new Button(composite, SWT.NONE);
		btnConferma.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnConferma.setText("Conferma");
		btnConferma.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		if(!strOperPremesse.isEmpty())
		 		{
		 			listPremesse.add(strOperPremesse);
		 			dialogShell.close();
		 		}	 				 		
		 	}
		});
		
		
		Button btnAnnulla = new Button(composite, SWT.NONE);
		btnAnnulla.setText("Annulla");
		btnAnnulla.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		dialogShell.close();
		 	}
		});
		
		dialogShell.pack();
		dialogShell.open();
	}
	
	public void addElemTestaList(String strElem)
	{
		listTesta.add(strElem);
	}
	
	public void addElemPremesseList(String strElem)
	{
		listPremesse.add(strElem);
	}
	
	
	
	protected void newRuleActionBtn() {
		lblModificaRegole.setText("Nuova Regola");
		comboRules.deselectAll();
		strRuleSelect = "";
		commentRuleSelected = "";
		
		textId.setText(Rules.generateId());
		text_Priority.setText("");
		textCertainty.setText("");
		listVariabili.removeAll();
		textComment.setText("");
		
		listTesta.removeAll();
		comboOperators.deselectAll();
		listPremesse.removeAll();
		
		compositeCentralLeft.setVisible(true);
		compositeCentralRight.setVisible(true);
 		sashForm.layout();	
	}
	
	protected void uploadRuleAction(){
		
		if(!strRuleSelect.isEmpty()){
			lblModificaRegole.setText("Modifica Regola");
			String idRuleSelected = Integer.toString(RuleRecognizer.getRuleId(strRuleSelect));
			textId.setText(idRuleSelected);
			text_Priority.setText(Float.toString(RuleRecognizer.getRulePriority(strRuleSelect)));
			textCertainty.setText(Float.toString(RuleRecognizer.getRuleCertezza(strRuleSelect)));
			
			String strOperatorSel = Rules.getOperator(strRuleSelect);
			String[] arrOpertators =  comboOperators.getItems();
			for (int i = 0; i < arrOpertators.length; i++) {
				if(arrOpertators[i].equalsIgnoreCase(strOperatorSel))
				{
					comboOperators.select(i);
					break;
				}
			}
			
			ArrayList<String> arrVariabili = Variables.getVariables(strRuleSelect);
			listVariabili.removeAll();
			for (String strVar : arrVariabili) {
				listVariabili.add(strVar);
			}
			
			
			for (int i = 0; i < Comments.commentRules.size(); i++) {
				if (Comments.commentRules.get(i).get(0).equalsIgnoreCase(idRuleSelected)) {
					textComment.setText(Comments.commentRules.get(i).get(1));
					commentRuleSelected = "commentr(" + Comments.commentRules.get(i).get(0) + ","
							+ Comments.commentRules.get(i).get(1) + ").";
				}
			}
			
			listTesta.removeAll();
			listPremesse.removeAll();
			
			boolean bIsFullTesta = false;
			boolean bIsFullPremesse = false;
			
			ArrayList<String> arrListHeads = Rules.getHeadsRule(strRuleSelect);
			for (String strHead : arrListHeads) {
				listTesta.add(strHead);
				bIsFullTesta = true;
			}
			
			ArrayList<ArrayList<String>> arrListTails = Rules.getTailsRule(strRuleSelect);
			for (ArrayList<String> arrayListTail : arrListTails) {
				for (String strTail : arrayListTail) {
					listPremesse.add(strTail);
					bIsFullPremesse = true;
				}
			}		
			
			if(bIsFullTesta)
				btnEliminaTeste.setEnabled(true);
			
			if(bIsFullPremesse)
				btnEliminaCode.setEnabled(true);		
			
			compositeCentralLeft.setVisible(true);
			compositeCentralRight.setVisible(true);
	 		sashForm.layout();
		}
	}
	
	protected void saveRuleAction() {
		
		String strFinalRuleSave = "rule(";
		strFinalRuleSave += textId.getText() +",";
		String[] arrStringTesta =  listTesta.getItems(); 
		for (String strTesta : arrStringTesta) {
			strFinalRuleSave += strTesta +",";
		}
		
		String[] arrOperators =  comboOperators.getItems();
		strFinalRuleSave += arrOperators[comboOperators.getSelectionIndex()].toLowerCase() + "([";
		
		String[] arrStringPremesse = listPremesse.getItems();
		for (int i = 0; i < arrStringPremesse.length; i++) {
			strFinalRuleSave += arrStringPremesse[i];
			if(i < arrStringPremesse.length -1)
			{
				strFinalRuleSave += ",";
			}
		}
		strFinalRuleSave += "]),";
		
		String strPriority = text_Priority.getText();
		
		if(strPriority.indexOf(".")>-1)
		{
			String[] arrTmp = strPriority.split("\\.");
			strFinalRuleSave += arrTmp[0] + ",";
		}else {
			strFinalRuleSave += strPriority + ",";
		}
		
		String strCert = textCertainty.getText();
		if(strCert.indexOf(".") > -1)
		{
			String[] arrTmp = strCert.split("\\.");
			strFinalRuleSave += arrTmp[0] + ").";
		}else {
			strFinalRuleSave += strCert + ").";
		}
		
		MessageBox messageBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);	   
		
		if(arrStringTesta.length>0 && arrStringPremesse.length>0 && !strPriority.isEmpty() && !strCert.isEmpty())
		{
			//Commenti
			String strFinalComment = "commentr(";
			strFinalComment += textId.getText() + ",";
			strFinalComment += textComment.getText() + ").";
			
			
			
			if(!strRuleSelect.isEmpty()){ // Modifica regola
				try {
					Rules.rules.remove(strFinalRuleSave);	
					strRuleSelect = Utility.addParenthesis(strRuleSelect);
					ReplaceInFile.replaceInFile(strRuleSelect, strFinalRuleSave);
					commentRuleSelected = Utility.addParenthesis(commentRuleSelected);
					ReplaceInFile.replaceInFile(commentRuleSelected, strFinalComment);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else { //Nuova regola
				WriteFile.writeFile("\r\n" + strFinalRuleSave);
				WriteFile.writeFile("\r\n" + strFinalComment);
			}
			
			Rules.rules.add(strFinalRuleSave);
			refreshData();			     
	       
	        messageBox.setMessage("Regola salvata con successo!");	       
		}else {
	        messageBox.setMessage("Completa prima di salvare!");
		}
		 messageBox.setText("Regola");
		 messageBox.open();        
	}
	
	protected void deleteRuleAction() throws IOException{
		
		if(!strRuleSelect.isEmpty())
		{
			String idRuleSelected = Integer.toString(RuleRecognizer.getRuleId(strRuleSelect));
			String toReplaceModified = Utility.addParenthesis(strRuleSelect);
			ReplaceInFile.replaceInFile(toReplaceModified, "");
			
			if(Rules.rules.contains(strRuleSelect)) {
				Rules.rules.remove(strRuleSelect);
			}else {
				SpecialRules.Specialrules.remove(strRuleSelect);	
			}
			
			
			for (int i = 0; i < Comments.commentRules.size(); i++) {
				if (Comments.commentRules.get(i).get(0).equalsIgnoreCase(idRuleSelected)) {
					textComment.setText(Comments.commentRules.get(i).get(1));
				}
			}
			
			for(int j=0; j<Comments.commentRules.size();j++) {
				if(Comments.commentRules.get(j).get(0).equals(idRuleSelected)) {
					Comments.commentRules.remove(j);
					String strTextcomment = textComment.getText();
					String finalCommentRule = "commentr(" + idRuleSelected + "," + strTextcomment + ").";
					String toReplaceCommentModified = Utility.addParenthesis(finalCommentRule);
					ReplaceInFile.replaceInFile(toReplaceCommentModified, "");
				}
			}
			
			refreshData();
			MessageBox messageBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);
	        
	        messageBox.setText("Regola");
	        messageBox.setMessage("Regola eliminata!");
	        messageBox.open();
		}
	}
	
	
	public SashForm getForm() {
		return sashForm;
	}
	
	public void refreshData(){
		
		//Caricamento regole da file
		comboRules.removeAll();
		
		ArrayList<String> alRules = new ArrayList<String>();
		alRules.addAll(Rules.rules);
		alRules.addAll(SpecialRules.Specialrules);
		for (String strRule : alRules) {
			comboRules.add(strRule);
		}	
		
		compositeCentralLeft.setVisible(false);
		compositeCentralRight.setVisible(false);
 		sashForm.layout();
	}
}
