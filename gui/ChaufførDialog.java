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

import model.Chauffør;
import model.Lastbil;

/**
 * Systemadministratorvindue til håndtering af chauffører.
 */
public class ChaufførDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList chaufførListe;
	private JButton btnOpretChauffør, btnRedigerChauffør, btnSletChauffør, btnTilbage;
	private JCheckBox checkBox;
	private Controller controller;
	private ChaufførDialog chaufførDialog;


	/**
	 * Create the dialog.
	 */
	public ChaufførDialog() {
		this.setIconImage(MainFrame.logo);
		this.chaufførDialog = this;
		setModal(true);
		controller = new Controller();
		setTitle("Chaufførliste");
		
		setBounds(100, 100, 365, 265);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		scrollPane = new JScrollPane();
		
		btnOpretChauffør = new JButton("Opret Chauff\u00F8r");
		btnOpretChauffør.addActionListener(controller);
		
		btnRedigerChauffør = new JButton("Rediger Chauff\u00F8r");
		btnRedigerChauffør.addActionListener(controller);
		
		btnSletChauffør = new JButton("Slet Chauff\u00F8r");
		btnSletChauffør.addActionListener(controller);
		
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
						.addComponent(btnOpretChauffør, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnRedigerChauffør, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(btnSletChauffør, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
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
							.addComponent(btnOpretChauffør)
							.addGap(8)
							.addComponent(btnRedigerChauffør)
							.addGap(9)
							.addComponent(btnSletChauffør)
							.addGap(9)
							.addComponent(checkBox)))
					.addContainerGap())
		);
		
		chaufførListe = new JList();
		scrollPane.setViewportView(chaufførListe);
		
		updateListAlleChauffører();
		
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
	 * Metode opdaterer JList med alle chauffører registreret i systemet.
	 */
	public void updateListAlleChauffører(){
		chaufførListe.setListData(Service.getInstance().getChauffører().toArray());
	}
	
	/**
	 * Metode opdaterer JList kun med ledige chauffører.
	 */
	public void updateListLedigeChauffører(){
		Set<Chauffør> temp = Service.getInstance().getChauffører();
		for(Lastbil lastbil: Service.getInstance().getLastbiler()){
			if(!(lastbil.getChauffør()==null))
			{
				temp.remove(lastbil.getChauffør());
			}
		}
		chaufførListe.setListData(temp.toArray());
	}

	private class Controller implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnOpretChauffør)){
				OpretChauffør opretChauffør = new OpretChauffør(chaufførDialog, checkBox.isSelected());
				opretChauffør.setVisible(true);
			}else if(e.getSource().equals(btnTilbage)){
					dispose();
			}else if(e.getSource().equals(btnSletChauffør)){
				if(!chaufførListe.isSelectionEmpty()){
					Chauffør temp = (Chauffør) chaufførListe.getSelectedValue();		
					int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil slette denne chauffør?", "Advarsel",
				        	JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    	if (response == JOptionPane.YES_OPTION) {
				    		Service.getInstance().removeChauffør(temp);
				    		if(checkBox.isSelected()){
				    			updateListLedigeChauffører();
				    		}else{
				    			updateListAlleChauffører();
				    		}
				    	}
				}
			}else if(e.getSource().equals(btnRedigerChauffør)){
				if(!chaufførListe.isSelectionEmpty()){
					RedigerChauffør redigerChauffør = new RedigerChauffør((Chauffør)chaufførListe.getSelectedValue(),chaufførDialog, checkBox.isSelected());
					redigerChauffør.setVisible(true);
				}
			}else if(checkBox.isSelected()){
				updateListLedigeChauffører();
			}else if(!checkBox.isSelected()){
				updateListAlleChauffører();
			}
		}
	}
}
