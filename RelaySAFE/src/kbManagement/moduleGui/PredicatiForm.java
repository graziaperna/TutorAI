package kbManagement.moduleGui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import kbManagement.org.eclipse.wb.swt.SWTResourceManager;
import kbManagement.structuredInput.Predicate;

public class PredicatiForm {

	SashForm sashForm;
	private Text textPredicato;
	private List listPredicati;
	private Composite compositeNuovaPred;
	private Label lblNuovoPredicato;
	private Button btnAddUploadPred;
	private Spinner spinnerNumArgomenti;
	private String strPredSelect = "";

	public PredicatiForm(TabFolder folder) {

		sashForm = new SashForm(folder, SWT.FILL);
		sashForm.setLayout(new GridLayout());

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));

		listPredicati = new List(composite_1, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_list.heightHint = 246;
		gd_list.widthHint = 213;
		listPredicati.setLayoutData(gd_list);
		listPredicati.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				selectFromList();
			}
		});

		Button btnNewButton = new Button(composite_1, SWT.CENTER);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1);
		gd_btnNewButton.heightHint = 40;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Nuovo Predicato");
		btnNewButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				newPredAction();
			}
		});

		// Interfaccia di modifica e aggiunta
		compositeNuovaPred = new Composite(sashForm, SWT.BORDER);
		GridLayout gl_CompositeNuovaPred = new GridLayout(1, false);
		gl_CompositeNuovaPred.marginWidth = 15;
		gl_CompositeNuovaPred.marginHeight = 15;
		compositeNuovaPred.setLayout(gl_CompositeNuovaPred);
		compositeNuovaPred.setVisible(false);

		lblNuovoPredicato = new Label(compositeNuovaPred, SWT.CENTER);
		lblNuovoPredicato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNuovoPredicato.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblNuovoPredicato.setText("Nuovo Predicato");

		Label lblNomePredicato = new Label(compositeNuovaPred, SWT.NONE);
		lblNomePredicato.setText("Nome Predicato");
		lblNomePredicato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNomePredicato.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNomePredicato.setText("Predicato");

		textPredicato = new Text(compositeNuovaPred, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		textPredicato.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compositeNuovaPred.setTabList(new Control[] { textPredicato, lblNomePredicato });

		Label lblNumeroArgomenti = new Label(compositeNuovaPred, SWT.NONE);
		lblNumeroArgomenti.setText("Numero Argomenti");
		lblNumeroArgomenti.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		
		spinnerNumArgomenti = new Spinner(compositeNuovaPred, SWT.BORDER);
		new Label(compositeNuovaPred, SWT.NONE);
		spinnerNumArgomenti.setSelection(0);
		spinnerNumArgomenti.setMinimum(0);
		spinnerNumArgomenti.setMaximum(10);

		btnAddUploadPred = new Button(compositeNuovaPred, SWT.CENTER);
		btnAddUploadPred.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnAddUploadPred.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1));
		btnAddUploadPred.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String strMsgBox = "Errore nella selezione dei parametri";
				String strNewPredicato = textPredicato.getText();
				int nNumArgomenti = spinnerNumArgomenti.getSelection();
				String newPredicateComplete = Predicate.reconstructPredicates(strNewPredicato, String.valueOf(nNumArgomenti));
				if((!strNewPredicato.isEmpty())&& nNumArgomenti>0 && !Predicate.predicates.contains(newPredicateComplete))
				{
					Predicate.predicates.add(newPredicateComplete);
					strMsgBox = "Aggiunto con successo!";
					//Modifica predicato
					if(!strPredSelect.isEmpty())
					{
						Predicate.predicates.remove(strPredSelect);
						strMsgBox = "Modificato con successo!";
					}
					
					compositeNuovaPred.setVisible(false);
					sashForm.layout();
					refreshData();
				}
				
				MessageBox msgBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);
				msgBox.setText("Predicato");
				msgBox.setMessage(strMsgBox);
				msgBox.open();
			}
		});
		btnAddUploadPred.setText("Aggiungi");
		sashForm.setWeights(new int[] { 222, 225 });

	}

	protected void newPredAction() {
		lblNuovoPredicato.setText("Nuovo Predicato");
		textPredicato.setText("");
		spinnerNumArgomenti.setSelection(0);
		strPredSelect = "";
		btnAddUploadPred.setText("Aggiungi");		
		if(!compositeNuovaPred.isVisible())
		{
			compositeNuovaPred.setVisible(true);
			sashForm.layout();
		}
	}
	
	private void selectFromList(){
		int[] selections = listPredicati.getSelectionIndices();
		String outText = "";
		for(int loopIndex = 0; loopIndex < selections.length; loopIndex++)
		{
			outText = listPredicati.getItem(selections[loopIndex]);
		}
		strPredSelect = outText;
		String[] arrList = outText.split("/");
		if(arrList.length > 1)
		{
			textPredicato.setText(arrList[0]);
			spinnerNumArgomenti.setSelection(Integer.parseInt(arrList[1]));
			
			lblNuovoPredicato.setText("Modifica Predicato");		
			btnAddUploadPred.setText("Modifica");		
			if(!compositeNuovaPred.isVisible())
			{
				compositeNuovaPred.setVisible(true);
				sashForm.layout();
			}
		}		
	}

	public SashForm getForm() {
		return sashForm;
	}

	public void refreshData() {
		listPredicati.removeAll();
		// Popolamento della lista da file
		ArrayList<String> arrListPred = new ArrayList<String>();
		arrListPred.addAll(Predicate.predicates);
		for (String predItem : arrListPred) {
			listPredicati.add(predItem);
		}
	}

}
