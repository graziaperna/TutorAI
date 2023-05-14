package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

import kbManagement.filesWR.ReadFile;
import kbManagement.moduleGui.MainForm;
import kbManagement.structuredInput.*;

public class PanelManagerKB extends GenericPanel {
	private JPanel panel_8;//Contiene i pulsanti inferiori
	private JButton btnUploadKb; // tasto Carica KB
	private JButton btnCostanti; // tasto Costanti 
	private JButton btnGestore; // tasto Gestore

	private static int conta=0;
	public static String pathFileSelected = "";

	public PanelManagerKB(String title) {
		super(title);

		panel_8 = new JPanel();
		panel_8.setBounds(0, 0, 212, 46);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		add(panel_8, gbc_panel);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	
		btnUploadKb = new JButton("Carica KB");
		btnUploadKb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(conta>0) {
					clearArrayList();
				}
				conta++;
				File selectedFile = SceltaFile();
				if (selectedFile != null) {
					pathFileSelected = selectedFile.getAbsolutePath();
//					listview.getItems().add(pathFileSelected.getAbsolutePath());
//					pathFileSelected = pathFileSelected.getAbsolutePath();
//					setButtonEnable();
				} else {
					//System.out.println("file is not valid");
				}
				ReadFile.readFile(pathFileSelected);
			}
		});
		panel_8.add(btnUploadKb);
		
		btnCostanti = new JButton("Costanti");
		btnCostanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Stage stage = null;
//				Parent myNewScene = null;
//
//				stage = (Stage) btnCostanti.getScene().getWindow();
//				myNewScene = FXMLLoader.load(getClass().getResource("/controller/constantstab.fxml"));
//				Scene scene = new Scene(myNewScene);
//				stage.setScene(scene);
//				stage.setTitle("COSTANTI");
//				stage.show();
			}
		});
		panel_8.add(btnCostanti);
			
		btnGestore = new JButton("Open manager");
		btnGestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainForm mpc = new MainForm();
					mpc.open ();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		panel_8.add(btnGestore);
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
