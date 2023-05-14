package kbManagement.moduleGui;

import java.io.IOException;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import kbManagement.filesWR.ReplaceInFile;
import kbManagement.org.eclipse.wb.swt.SWTResourceManager;
import kbManagement.structuredInput.Constants;

public class CostantForm {

	SashForm sashForm;
	private Text textCostante;
	protected Button textFilename;
	private final List listConstanti;
	private Composite CompositeNuovaCost;
	private Label lblCostante;
	private Button btnAddUploadCostant;
	private String strConstantSelect = ""; 
	
	public CostantForm(TabFolder folder) {

		sashForm = new SashForm(folder, SWT.FILL);
		sashForm.setLayout(new GridLayout());

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));

		listConstanti = new List(composite_1, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_list.heightHint = 246;
		gd_list.widthHint = 213;
		listConstanti.setLayoutData(gd_list);
		listConstanti.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				selectFromList();
			}
		});

		Button btnNewButton = new Button(composite_1, SWT.CENTER);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1);
		gd_btnNewButton.heightHint = 40;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Nuova Costante");
		btnNewButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {

				newConstatAction();
			}
		});

		// Interfaccia di modifica e aggiunta
		CompositeNuovaCost = new Composite(sashForm, SWT.BORDER);
		GridLayout gl_CompositeNuovaCost = new GridLayout(1, false);
		gl_CompositeNuovaCost.marginWidth = 15;
		gl_CompositeNuovaCost.marginHeight = 15;
		CompositeNuovaCost.setLayout(gl_CompositeNuovaCost);
		CompositeNuovaCost.setVisible(false);

		lblCostante = new Label(CompositeNuovaCost, SWT.CENTER);
		lblCostante.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblCostante.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));

		textCostante = new Text(CompositeNuovaCost, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		textCostante.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		CompositeNuovaCost.setTabList(new Control[] { textCostante, lblCostante });

		btnAddUploadCostant = new Button(CompositeNuovaCost, SWT.CENTER);
		btnAddUploadCostant.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnAddUploadCostant.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1));
		btnAddUploadCostant.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String strNewConstant = textCostante.getText();
				if(!strNewConstant.isEmpty())
				{
					Constants.constants.add(strNewConstant);
					String strMsgBox = "Nuova costante aggiunta con successo!";
					//Modifica Costante
					if(!strConstantSelect.isEmpty())
					{
						Constants.constants.remove(strConstantSelect);
						try {
							ReplaceInFile.replaceInFile(strConstantSelect, strNewConstant);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						strMsgBox = "Costante modificata con successo!";
					}
					
					CompositeNuovaCost.setVisible(false);
					sashForm.layout();
					refreshData();
					MessageBox messageBox = new MessageBox(sashForm.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			        
			        messageBox.setText("Constante");
			        messageBox.setMessage(strMsgBox);
			        messageBox.open();
				}				
			}
		});
		sashForm.setWeights(new int[] { 222, 225 });

	}

	private void newConstatAction() {
		lblCostante.setText("Nuova costante");
		textCostante.setText("");
		strConstantSelect = "";
		btnAddUploadCostant.setText("Aggiungi");
		if(!CompositeNuovaCost.isVisible())
		{
			CompositeNuovaCost.setVisible(true);
			sashForm.layout();
		}		
	}

	private void selectFromList() {
		int[] selections = listConstanti.getSelectionIndices();
		String outText = "";
		for (int loopIndex = 0; loopIndex < selections.length; loopIndex++) {
			outText = listConstanti.getItem(selections[loopIndex]);
		}
		lblCostante.setText("Modifica costante");
		textCostante.setText(outText);
		strConstantSelect = outText;
		btnAddUploadCostant.setText("Modifica");
		if(!CompositeNuovaCost.isVisible())
		{
			CompositeNuovaCost.setVisible(true);
			sashForm.layout();
		}	
	}

	public SashForm getForm() {
		return sashForm;
	}

	public void refreshData() {
		listConstanti.removeAll();
		// Popolamento della lista da file
		ArrayList<String> listConstants = new ArrayList<String>();
		listConstants.addAll(Constants.constants);
		for (String constantItem : listConstants) {
			listConstanti.add(constantItem);
		}
	}

}
