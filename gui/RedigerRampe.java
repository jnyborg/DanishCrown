package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import service.Service;

import model.Rampe;
import model.Transportmateriel;
import model.Transportmateriel.MaterielType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Skaber systemadministratorvinduet til redigering af ramper.
 */
public class RedigerRampe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private Rampe rampe;
	private JLabel lblRampenr,lblnummer, lblTransportmateriel;
	private JComboBox comboBox;
	private SystemadministratorMain systemadminstratorMain;
	private Controller controller;
	private JButton okButton, cancelButton;


	/**
	 * Create the dialog.
	 */
	public RedigerRampe(Rampe rampe, SystemadministratorMain systemadministratorMain) {
		this.setIconImage(MainFrame.logo);
		this.systemadminstratorMain = systemadministratorMain;
		this.rampe = rampe;
		this.setModal(true);
		this.setTitle("Rediger rampe");
		controller = new Controller();
		
		setBounds(100, 100, 330, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		
		lblRampenr = new JLabel("Rampenummer:");
		
		lblTransportmateriel = new JLabel("Transportmateriel: ");
		
		comboBox = new JComboBox(Transportmateriel.MaterielType.values());
		comboBox.setSelectedItem(rampe.getTransportmateriel());
		comboBox.addItemListener(controller);
		
		lblnummer = new JLabel("");
		lblnummer.setText(rampe.getRampeNummer()+"");
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTransportmateriel)
						.addComponent(lblRampenr))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblnummer)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTransportmateriel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRampenr)
						.addComponent(lblnummer))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(controller);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(controller);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private class Controller implements ActionListener, ItemListener{
		@Override
			public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(okButton)){
				try{
					rampe.setRampeNummer(Integer.parseInt(lblnummer.getText()));
					Transportmateriel temp = Service.getInstance().createTransportmateriel((MaterielType) comboBox.getSelectedItem());
					rampe.setTransportmateriel(temp);
					Service.getInstance().updateRampe(rampe, temp);
					systemadminstratorMain.updateRampeJlist();
					dispose();
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "Venligst udfyld alle felter korrekt", "Fejl!",JOptionPane.ERROR_MESSAGE);
					}
			}else if(e.getSource().equals(cancelButton)){
				dispose();
			}
		}
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange()== ItemEvent.SELECTED){
				if(event.getItem().equals(Transportmateriel.MaterielType.JULETRÆ)){
					lblnummer.setText((Service.getInstance().getRampenummerJuletræ()+1)+"");
				}
				else if(event.getItem().equals(Transportmateriel.MaterielType.KAR)){
					lblnummer.setText((Service.getInstance().getRampenummerKar()+1)+"");
				}
				else if(event.getItem().equals(Transportmateriel.MaterielType.KASSE)){
					lblnummer.setText((Service.getInstance().getRampenummerKasse()+1)+"");
				}
			}
		}
	}
}
