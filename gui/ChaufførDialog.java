package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import service.Service;

import model.Chauff�r;
import model.Lastbil;

/**
 * Systemadministratorvindue til h�ndtering af chauff�rer.
 */
public class Chauff�rDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList chauff�rListe;
	private JButton btnOpretChauff�r, btnRedigerChauff�r, btnSletChauff�r, btnTilbage;
	private JCheckBox checkBox;
	private Controller controller;
	private Chauff�rDialog chauff�rDialog;


	/**
	 * Create the dialog.
	 */
	public Chauff�rDialog() {
		this.setIconImage(MainFrame.logo);
		this.chauff�rDialog = this;
		setModal(true);
		controller = new Controller();
		setTitle("Chauff�rliste");
		
		setBounds(100, 100, 365, 265);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		scrollPane = new JScrollPane();
		
		btnOpretChauff�r = new JButton("Opret Chauff\u00F8r");
		btnOpretChauff�r.addActionListener(controller);
		
		btnRedigerChauff�r = new JButton("Rediger Chauff\u00F8r");
		btnRedigerChauff�r.addActionListener(controller);
		
		btnSletChauff�r = new JButton("Slet Chauff\u00F8r");
		btnSletChauff�r.addActionListener(controller);
		
		checkBox = new JCheckBox("Vis kun ledige chauff\u00F8r");
		checkBox.addActionListener(controller);
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOpretChauff�r, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnRedigerChauff�r, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnSletChauff�r, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(checkBox))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnOpretChauff�r)
							.addGap(8)
							.addComponent(btnRedigerChauff�r)
							.addGap(9)
							.addComponent(btnSletChauff�r)
							.addGap(9)
							.addComponent(checkBox)))
					.addContainerGap())
		);
		
		chauff�rListe = new JList();
		scrollPane.setViewportView(chauff�rListe);
		
		updateListAlleChauff�rer();
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnTilbage = new JButton("Tilbage");
				btnTilbage.addActionListener(controller);
				btnTilbage.setActionCommand("Cancel");
				buttonPane.add(btnTilbage);
			}
		}
	}
	
	/**
	 * Metode opdaterer JList med alle chauff�rer registreret i systemet.
	 */
	public void updateListAlleChauff�rer(){
		chauff�rListe.setListData(Service.getInstance().getChauff�rer().toArray());
	}
	
	/**
	 * Metode opdaterer JList kun med ledige chauff�rer.
	 */
	public void updateListLedigeChauff�rer(){
		Set<Chauff�r> temp = Service.getInstance().getChauff�rer();
		for(Lastbil lastbil: Service.getInstance().getLastbiler()){
			if(!(lastbil.getChauff�r()==null))
			{
				temp.remove(lastbil.getChauff�r());
			}
		}
		chauff�rListe.setListData(temp.toArray());
	}

	private class Controller implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnOpretChauff�r)){
				OpretChauff�r opretChauff�r = new OpretChauff�r(chauff�rDialog, checkBox.isSelected());
				opretChauff�r.setVisible(true);
			}else if(e.getSource().equals(btnTilbage)){
					dispose();
			}else if(e.getSource().equals(btnSletChauff�r)){
				if(!chauff�rListe.isSelectionEmpty()){
					Chauff�r temp = (Chauff�r) chauff�rListe.getSelectedValue();		
					int response = JOptionPane.showConfirmDialog(null, "Er du sikker p� at du vil slette denne chauff�r?", "Advarsel",
				        	JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    	if (response == JOptionPane.YES_OPTION) {
				    		Service.getInstance().removeChauff�r(temp);
				    		if(checkBox.isSelected()){
				    			updateListLedigeChauff�rer();
				    		}else{
				    			updateListAlleChauff�rer();
				    		}
				    	}
				}
			}else if(e.getSource().equals(btnRedigerChauff�r)){
				if(!chauff�rListe.isSelectionEmpty()){
					RedigerChauff�r redigerChauff�r = new RedigerChauff�r((Chauff�r)chauff�rListe.getSelectedValue(),chauff�rDialog, checkBox.isSelected());
					redigerChauff�r.setVisible(true);
				}
			}else if(checkBox.isSelected()){
				updateListLedigeChauff�rer();
			}else if(!checkBox.isSelected()){
				updateListAlleChauff�rer();
			}
		}
	}
}
