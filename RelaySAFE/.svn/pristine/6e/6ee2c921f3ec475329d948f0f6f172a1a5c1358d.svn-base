package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;

public class KnowledgeLoaderFrame extends JFrame {
	/** Generated Serial Version UID */
	private static final long serialVersionUID = -2517318984810112441L;

	private JPanel contentPane;

	public KnowledgeLoaderFrame() {
		setTitle("Knowledge Loader");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new KnowledgeLoaderPanel();
		setContentPane(contentPane);

		this.setVisible(true);
	}

	public class KnowledgeLoaderPanel extends GenericPanel {

		/** Generated Serial Version UID */
		private static final long serialVersionUID = 6718542437057675246L;

		private JButton btnCarica;
		private JButton btnRimuovi;
		private JButton btnConferma;
		private JScrollPane listaFilesPane;
		private JList<String> listaFiles = new JList<String>();
		private DefaultListModel<String> listaFilesModel = new DefaultListModel<String>();

		private ArrayList<String> filesCaricati = MainPanel2.files;

		public KnowledgeLoaderPanel() {
			setBorder(new EmptyBorder(5, 5, 5, 5));
			setLayout(new BorderLayout(0, 0));

			listaFiles.setModel(listaFilesModel);
			listaFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaFiles.setSelectedIndex(0);
			listaFiles.setVisibleRowCount(5);
			listaFilesPane = new JScrollPane(listaFiles);
			listaFilesPane.setPreferredSize(new Dimension(600, 200));

			btnCarica = new JButton("Aggiungi Conoscenza");
			btnRimuovi = new JButton("Rimuovi Conoscenza");
			btnConferma = new JButton("Conferma");
			btnRimuovi.setEnabled(false);
			btnConferma.setEnabled(true);
			add(listaFilesPane, BorderLayout.NORTH);
			add(btnCarica, BorderLayout.WEST);
			add(btnRimuovi, BorderLayout.EAST);
			add(btnConferma, BorderLayout.CENTER);

			btnCarica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LoadNewFile();
					btnRimuovi.setEnabled(true);
				}
			});
			btnRimuovi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RemoveFile();
				}
			});
			
			btnConferma.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					rw.resetKnowledge(currentRunKnowledge);
					rw.writerules(filesCaricati); // ricopia la KB aggiungendo i dynamic -> VA FATTO NON UNO PER UNO
					
					dispose();					
				}
			});

			RefreshFilesList();
		}

		private void RefreshFilesList() {
			listaFilesModel.clear();
			for (String filePath : filesCaricati) {
				File file = new File(filePath);
				listaFilesModel.addElement(file.getName());
			}

			boolean listIsEmpty = filesCaricati.size() > 0;
			btnRimuovi.setEnabled(listIsEmpty);
		}

		private void LoadNewFile() {
			File selectedFile = SceltaFile();
			if (selectedFile != null) {
				String newKnowledgeFilePath = selectedFile.getAbsolutePath();
				System.out.println("Aggiunto path della conoscenza:" + newKnowledgeFilePath);
				filesCaricati.add(newKnowledgeFilePath);				
				RefreshFilesList();
			}
		}

		private void RemoveFile() {
			int index = listaFiles.getSelectedIndex();
			if (listaFiles.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Non hai selezionato alcun file da rimuovere", "Errore", 1);
				System.out.println("Non hai selezionato nulla");
			} else {
				System.out.println("Rimosso path della conoscenza:" + filesCaricati.get(index));
				filesCaricati.remove(index);
				RefreshFilesList();
			}
		}
	}
}
