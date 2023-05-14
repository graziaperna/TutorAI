package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import ReLay.MI;
//import ReLay.PanelMappa;
import service.RW;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	public static JTabbedPane tabbedPane;
	
	//lISTA DELLE INTERFACCE
	private static LinkedList<GenericPanel> tabs;
	public String metodo="Rete";
	
	//TopMenu di navigazione
	public static JMenuBar menuTopBar= new JMenuBar();
	public static JMenu menuFile= new JMenu("File");
	public static JMenu menuMetodo= new JMenu("Metodo");
	public static JMenu menu3= new JMenu("Timestamp");
	public static JMenu menu4= new JMenu("Processi");
	
	//Roba nuova
    public static int ntimestamp=1;
	public static String idtimestamp=null;
	

	
	public MainFrame() throws Exception {
		//motoreInferenziale = new ReLay(); //ste al momento gia' istanziato da MainPanel2
		//System.out.println(motoreInferenziale.getCurrentRunName());
		
		tabs = Utility.GetAllPanel(this);
		
		JMenuItem esci = new JMenuItem("Esci");
		esci.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				System.exit(0);
			}
		});
		
		//menuFile.add(loadMappa);
		//menuFile.add(saveMappa);
		menuFile.add(esci);
		
		ChangeListener changeListenerRadio = new ChangeListener() {
		      public void stateChanged(ChangeEvent changEvent) {
		        AbstractButton aButton = (AbstractButton)changEvent.getSource();
		        if(aButton.isSelected())
		        	metodo=aButton.getText();
		      }
		    };
		
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem JradioForward= new JRadioButtonMenuItem("Forward");
		group.add(JradioForward);
		JRadioButtonMenuItem JradioBackward= new JRadioButtonMenuItem("Backward");
		group.add(JradioBackward);
		JRadioButtonMenuItem JradioRete= new JRadioButtonMenuItem("Rete");
		group.add(JradioRete);
		JradioRete.setSelected(true);

		JradioForward.addChangeListener(changeListenerRadio);
		JradioBackward.addChangeListener(changeListenerRadio);
		JradioRete.addChangeListener(changeListenerRadio);

		menuMetodo.add(JradioForward);
		menuMetodo.add(JradioBackward);
		menuMetodo.add(JradioRete);
		
		JMenuItem newtimestamp = new JMenuItem("Nuovo");
		newtimestamp.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				RW.writetimestamp();
			}
		});
		JMenuItem numbertimestamp = new JMenuItem("Numero di timestamp");
		numbertimestamp.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				changetimestamp();
			}
		});
		menu3.add(newtimestamp);
		menu3.add(numbertimestamp);
		
		menuTopBar.add(menuFile);
		menuTopBar.add(menuMetodo);
		menuTopBar.add(menu3);
		menuTopBar.add(menu4);

		setJMenuBar(menuTopBar);
		
		setTitle("ReLay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		//Restituisce tutte le interfacce
		for (GenericPanel tab : tabs) {
			tabbedPane.add(tab.getTitle(), new JScrollPane(tab));
		}
		
		this.setVisible(true);
		pack();	
	}
	/*
	private boolean loadGraph(){
		try{
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("XML File","xml");
		 chooser.setAcceptAllFileFilterUsed(false);
		 chooser.setFileFilter(filter);
		 File workingDirectory = new File(System.getProperty("user.dir")+"/resources");
		 chooser.setCurrentDirectory(workingDirectory);
		int returnVal = chooser.showOpenDialog(this);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();	
			PanelMappa.filename="resources/"+file.getName().replace(".xml", "");
			PanelMappa.filename="resources/"+file.getName().replace(".graphml", "");
			//panelMappa.start();
			XML.loadXMLMappa(PanelMappa.filename);
			return true;
		}
	} catch (Exception e) {
		JOptionPane.showMessageDialog(this,
				"Impossibile caricare il salvataggio selezionato!",
				"ERRORE", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
		return false;
	}

	private void saveGraph(){
		try{
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("XML File","xml");
		 chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);
		 File workingDirectory = new File(System.getProperty("user.dir")+"/resources");
		 chooser.setCurrentDirectory(workingDirectory);
		int returnVal = chooser.showOpenDialog(this);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			PanelMappa.filename="resources/"+file.getName().replace(".xml", "");
			PanelMappa.filename="resources/"+file.getName().replace(".graphml", "");
			//panelMappa.start();
		    XML.saveXMLMappa(PanelMappa.filename);
		
		}
	
	} catch (Exception e) {
		JOptionPane.showMessageDialog(this,
				"Impossibile salvare!",
				"ERRORE", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
	}
	*/
	
    private void changetimestamp(){
   	 final JFrame frametimestamp=new JFrame("Numero di timestamp");
			frametimestamp.setLayout(new GridLayout(3,1));
			JPanel panelincred=new JPanel();
			JPanel panelbut=new JPanel();
			panelincred.setLayout(new GridLayout(1,3));
			panelbut.setLayout(new GridLayout(1,2));
			final JTextField ntext=new JTextField();
			ntext.setHorizontalAlignment(JTextField.CENTER);
			ntext.setText(String.valueOf(ntimestamp));
			JButton incntimestamp=new JButton("+");
			JButton decntimestamp=new JButton("-");
			JButton butc=new JButton("Conferma");
			JButton buta=new JButton("Annulla");		
			incntimestamp.addActionListener( new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	int ntt=Integer.valueOf(ntext.getText());			 
			    	ntt++;
			    	ntext.setText(String.valueOf(ntt));
			    }
			});
			decntimestamp.addActionListener( new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	int ntt=Integer.valueOf(ntext.getText());
			    	if(ntt>1)
			    	ntt--;
			    	ntext.setText(String.valueOf(ntt));
			    }
			});
			
			butc.addActionListener( new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ntimestamp=Integer.valueOf(ntext.getText());
			    	frametimestamp.dispose();
			    }
			});
			buta.addActionListener( new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	frametimestamp.dispose();
			    }
			});
			panelincred.add(decntimestamp);
			panelincred.add(ntext);
			panelincred.add(incntimestamp);
			panelbut.add(buta);
			panelbut.add(butc);
			frametimestamp.add(new JLabel("Scegli il numero timestamp da memorizzare"));
			frametimestamp.add(panelincred);
					frametimestamp.add(panelbut);
					frametimestamp.setVisible(true);
					frametimestamp.pack();
    }
	
	//mostra il file chooser per scegliere il file contenente i fatti da caricare.
	//Ritorna il percorso del file scelto.
}