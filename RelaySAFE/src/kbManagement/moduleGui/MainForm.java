package kbManagement.moduleGui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import kbManagement.filesWR.ReadFile; // kbManagement.
import kbManagement.structuredInput.Comments;
import kbManagement.structuredInput.Constants;
import kbManagement.structuredInput.Facts;
import kbManagement.structuredInput.Predicate;
import kbManagement.structuredInput.Rules;
import kbManagement.structuredInput.SpecialRules;
import kbManagement.structuredInput.Variables;

public class MainForm {

	protected Shell shell;
	private Text textFilename;
	private Composite composite;
	private Button btnUploadFile;
	private boolean bUpload;
	private TabComposite tabComposite;
	private Display display;
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		display = Display.getDefault();
		bUpload = false;
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(729, 507);
		shell.setText("SWT Prolog Gui");
		shell.setLayout(new GridLayout(2, false));
		shell.setImage(new Image(display,"resource/logo.ico"));
		
		btnUploadFile = new Button(shell, SWT.NONE);
		btnUploadFile.setText("Upload Knowldge Base");
		btnUploadFile.addListener(SWT.Selection, new Listener()
		{
		    @Override
		    public void handleEvent(Event event)
		    {
		    	if(!bUpload) {
		    		fileChooserBtnAction();
		    	}else {
		    		resetGui();
		    		clearArrayList();
		    		bUpload = false;
		    	}
		    }
		});
		
		textFilename = new Text(shell, SWT.BORDER);
		GridData gd_textFilename = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_textFilename.widthHint = 555;
		textFilename.setLayoutData(gd_textFilename);
		
		composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1));
		
		tabComposite = new TabComposite(composite, SWT.NONE, 0);
		tabComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_tabComposite = new FormData();
		fd_tabComposite.right = new FormAttachment(100);
		fd_tabComposite.bottom = new FormAttachment(100);
		fd_tabComposite.top = new FormAttachment(0);
		fd_tabComposite.left = new FormAttachment(0);
		tabComposite.setLayoutData(fd_tabComposite);
		composite.setVisible(false);
		shell.setTabList(new Control[]{composite, btnUploadFile, textFilename});
	}
	
	protected void fileChooserBtnAction() {
		
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
        fd.setText("Open");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.pl", "*.*" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open();
        if(selected != null)
        {
        	textFilename.setText(selected);
            ReadFile.readFileFromPath(selected);
            tabComposite.refreshAllData();
            btnUploadFile.setText("Home");
            composite.setVisible(true);
            bUpload = true;
        }        
	}
	
	protected void resetGui() {
		btnUploadFile.setText("Upload Knowldge Base");
        textFilename.setText("");
        composite.setVisible(false);
	}
	
	private void clearArrayList() {
		Facts.facts.clear();
		Rules.rules.clear();
		SpecialRules.Specialrules.clear();
		Predicate.predicates.clear();
		Comments.commentFacts.clear();
		Comments.commentRules.clear();
		Constants.constants.clear();
		Variables.variablesRule.clear();
	}
}
