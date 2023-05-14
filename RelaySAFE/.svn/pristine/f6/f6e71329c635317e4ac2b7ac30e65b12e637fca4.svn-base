package kbManagement.moduleGui;

import org.eclipse.swt.widgets.Shell;

import kbManagement.structuredInput.Constants;
import kbManagement.structuredInput.Predicate;

import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;

public class AtomDialog {

	private Composite composite;
	private Composite composite_1;
	private ScrolledComposite scrolledComposite;
	private String[] arrVariabili;
	private String strPredName;
	private String[] arrStrConstati;
	private String[] arrStrVaribili;

	/**
	 * Create contents of the window.
	 */
	public void openDialog(SashForm sashForm,RegoleForm regoleForm,boolean bIsTesta) {
		final Shell dialogShell = new Shell(sashForm.getShell(), SWT.PRIMARY_MODAL | SWT.SHEET);
		dialogShell.setSize(637, 478);
		dialogShell.setText("Nuovo elemento");
		dialogShell.setLayout(new GridLayout(1, false));
		
		composite = new Composite(dialogShell, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite.widthHint = 616;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(3, false));
		
		
		ArrayList<String> listPred = new ArrayList<String>();
		listPred.addAll(Predicate.predicates);
		
		Label lblSelezionaUnPredicato = new Label(composite, SWT.NONE);
		lblSelezionaUnPredicato.setText("Seleziona un predicato :");
		new Label(composite, SWT.NONE);
		
		Combo comboPredicati = new Combo(composite, SWT.NONE);
		comboPredicati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		for (String predString : listPred) {
			comboPredicati.add(predString);
		}
		
		comboPredicati.addSelectionListener(new SelectionAdapter() {
			 
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 	int idx = comboPredicati.getSelectionIndex();
				 	String strSelPred = comboPredicati.getItem(idx);
				 	String[] arrStr = strSelPred.split("/");
				 	if(arrStr.length > 0)
				 	{		 		
				 		strPredName = arrStr[0];
				 		int nElem = Integer.parseInt(arrStr[1]);
				 		arrStrConstati = new String[nElem];
				 		arrStrVaribili = new String[nElem];
				 		
				 		createPredicateRows(nElem);
				 		composite_1.layout();	
				 		comboPredicati.setEnabled(false);
				 		composite.layout();
				 	}				 
	            }
			 
		});

		scrolledComposite = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setAlwaysShowScrollBars(true);
		GridData gd_scrolledComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_scrolledComposite.heightHint = 291;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinSize(dialogShell.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		composite_1 = new Composite(scrolledComposite, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		
		scrolledComposite.setContent(composite_1);
		scrolledComposite.setMinSize(composite_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
		
		
		Composite composite_2 = new Composite(dialogShell, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));
		composite_2.setLayout(new GridLayout(2, false));
		
		Button btnOk = new Button(composite_2, SWT.NONE);
		btnOk.setSize(28, 25);
		btnOk.setText("Conferma");
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
		 	public void widgetSelected(SelectionEvent e) {
				
				boolean isOk = true;
				String strElement = strPredName + "(";
				for(int x = 0; x < arrStrConstati.length;x++)
				{
					if(arrStrConstati[x] != null && arrStrVaribili[x] !=null)
					{
						strElement += arrStrConstati[x] + "," + arrStrVaribili[x];
						if(x< arrStrConstati.length - 1)
						{
							strElement += ",";
						}
						
					}else {
						isOk = false;
					}
					
				}										
				
				if(isOk)
				{
					strElement += ")";
					if(bIsTesta)
					{
						regoleForm.addElemTestaList(strElement);
					}
					else 
					{
						regoleForm.addElemPremesseList(strElement);
					}
					
					dialogShell.close();
				}		 		
		 	}
		});
		
		
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnAnnulla = new Button(composite_2, SWT.NONE);
		btnAnnulla.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnAnnulla.setBounds(0, 0, 75, 25);
		btnAnnulla.setText("Annulla");	
		btnAnnulla.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		dialogShell.close();
		 	}
		});
		
		///
		dialogShell.pack();
		dialogShell.open();
	}
	
	public void setListVariabili(String[] arrVariabili){
		this.arrVariabili = arrVariabili;
	}
	
	protected void createPredicateRows(int nNumItems) {
		
		Composite compPrec = null;
		
		for (int i = 0; i < nNumItems; i++) {
			Composite compositePred = new Composite(composite_1, SWT.NONE);
			compositePred.setLayout(new FillLayout(SWT.HORIZONTAL));
			FormData fd_compositePred = new FormData();
			fd_compositePred.right = new FormAttachment(100);
			fd_compositePred.left = new FormAttachment(0);
			
			if(compPrec == null)
			{
				fd_compositePred.top = new FormAttachment(0, 10);
			}else {
				fd_compositePred.top = new FormAttachment(compPrec, 10);
			}		
						
			compositePred.setLayoutData(fd_compositePred);
			
			Label lblArgomento = new Label(compositePred, SWT.NONE);
			String strArgName = String.format("Argomento %d", i);
			lblArgomento.setText(strArgName);
			int idxRow = i;
			
			Label lblConstanti = new Label(compositePred, SWT.RIGHT);
			lblConstanti.setText("Constanti: ");
			
			Combo comboCostanti = new Combo(compositePred, SWT.NONE);
			ArrayList<String> listConst = new ArrayList<String>(); 
			listConst.addAll(Constants.constants);
			for (String itemConst : listConst) {
				comboCostanti.add(itemConst);
			}
			
			comboCostanti.addSelectionListener(new SelectionAdapter() {				 
				 @Override
		            public void widgetSelected(SelectionEvent e) {
					 int idx = comboCostanti.getSelectionIndex();
					 String strCostante = comboCostanti.getItem(idx);
					 if(arrStrConstati.length > idxRow) {
						 arrStrConstati[idxRow] = strCostante;
					 }
				 }
			});
									
			Label lblVariabili = new Label(compositePred, SWT.RIGHT);
			lblVariabili.setText("Variabili: ");		
			
			Combo comboVariabili = new Combo(compositePred, SWT.NONE);
			for (int j = 0; j < arrVariabili.length; j++) {
				comboVariabili.add(arrVariabili[j]);
			}
			
			comboVariabili.addSelectionListener(new SelectionAdapter() {				 
				 @Override
		            public void widgetSelected(SelectionEvent e) {
					 int idx = comboVariabili.getSelectionIndex();
					 String strVariabile = comboVariabili.getItem(idx);
					 if(arrStrVaribili.length > idxRow) {
						 arrStrVaribili[idxRow] = strVariabile;
					 }
				 }
			});
						
			compPrec = compositePred;
		}
		
	}
}
