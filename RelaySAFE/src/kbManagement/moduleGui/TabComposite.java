package kbManagement.moduleGui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FillLayout;
public class TabComposite extends Composite {

	private CostantForm costantiForm;
	private PredicatiForm predForm;
	private FattiForm fattiForm;
	private RegoleForm regoleForm;
	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param fill 
	 */
	public TabComposite(Composite parent, int style, int fill) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Costanti");
		
		costantiForm = new CostantForm(tabFolder);
		tbtmNewItem.setControl(costantiForm.getForm());
				
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Predicati");
		
		predForm = new PredicatiForm(tabFolder);
		tbtmNewItem_1.setControl(predForm.getForm());
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("Fatti");
		
		fattiForm = new FattiForm(tabFolder);
		tbtmNewItem_2.setControl(fattiForm.getForm());
		
		TabItem tbtmNewItem_3 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_3.setText("Regole");
		
		regoleForm = new RegoleForm(tabFolder);
		tbtmNewItem_3.setControl(regoleForm.getForm());

	    tabFolder.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) {
	        String strTabSelected = tabFolder.getSelection()[0].getText();
	        switch (strTabSelected) {
			case "Constanti":
				costantiForm.refreshData();
				break;
			case "Predicati":
				predForm.refreshData();
				break;
			case "Fatti":
				fattiForm.refreshData();
				break;
			case "Regole":
				regoleForm.refreshData();
				break;
			default:
				break;
			}
	        
	      }
	    });
	}
	
	public void refreshAllData()
	{
		costantiForm.refreshData();
		predForm.refreshData();
		fattiForm.refreshData();
		regoleForm.refreshData();
	}
}

