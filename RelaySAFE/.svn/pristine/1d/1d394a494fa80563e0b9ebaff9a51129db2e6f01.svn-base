package util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class MyDialogEsempi extends JDialog {
	String insieme=new String();
	
	
  public MyDialogEsempi(JFrame parent) {
    super(parent, "Apprendimento", true);
    setResizable(false);
    insieme="null";
    Container cp = getContentPane();
    getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
    JLabel label = new JLabel("Scegli il tipo di esempio");
    label.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(label);
    
    JPanel panel = new JPanel();
    getContentPane().add(panel);
    panel.setLayout(new GridLayout(0, 3, 0, 0));
    JButton positivo = new JButton("Positivo");
    panel.add(positivo);
    positivo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  insieme="pos";
        dispose(); // Closes the dialog
      }
    });
    JButton negativo = new JButton("Negativo");
    negativo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	insieme="neg";
          dispose(); // Closes the dialog
        }
      });
    panel.add(negativo);
    JButton annulla = new JButton("Non interessante");
    panel.add(annulla);
    annulla.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  insieme="null";
        dispose(); // Closes the dialog
      }
    });
    
    setSize(300, 110);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    setVisible(true);
  }
  
  public String getInsieme(){
	  return insieme;
  }
}

