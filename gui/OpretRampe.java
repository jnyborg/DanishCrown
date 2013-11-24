package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import model.Transportmateriel;
import model.Transportmateriel.MaterielType;
import service.Service;

/**
 * Skaber systemadministratorvinduet til oprettelse af ramper.
 */
public class OpretRampe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblRampenr,lblNummer;
	private JComboBox comboBox;
	private SystemadministratorMain systemadminstratorMain;
	private Controller controller;
	private JButton okButton, cancelButton;

	/**
	 * Create the dialog.
	 */
	public OpretRampe(SystemadministratorMain systemadminstratorMain) {
		this.setIconImage(MainFrame.logo);
		this.systemadminstratorMain = systemadminstratorMain;
		this.setModal(true);
		this.setTitle("Opret Rampe");
		controller = new Controller();
		
		setBounds(100, 100, 330, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		
		lblRampenr = new JLabel("Rampenummer:");
		JLabel lblTransportmateriel = new JLabel("Transportmateriel: ");
		
		comboBox = new JComboBox(Transportmateriel.MaterielType.values());
		comboBox.addItemListener(controller);
		
		lblNummer = new JLabel("");
		lblNummer.setText((Service.getInstance().getRampenummerJuletræ()+1)+"");
		
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
						.addComponent(lblNummer)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
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
						.addComponent(lblNummer))
					.addContainerGap(27, Short.MAX_VALUE))
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

					Transportmateriel temp = Service.getInstance().createTransportmateriel((MaterielType) comboBox.getSelectedItem());
					Service.getInstance().createRampe(temp);
					systemadminstratorMain.updateRampeJlist();
					dispose();

				}
			else if(e.getSource().equals(cancelButton)){
				dispose();
				}
			}
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange()== ItemEvent.SELECTED){
				if(event.getItem().equals(Transportmateriel.MaterielType.JULETRÆ)){
					lblNummer.setText((Service.getInstance().getRampenummerJuletræ()+1)+"");
				}
				else if(event.getItem().equals(Transportmateriel.MaterielType.KAR)){
					lblNummer.setText((Service.getInstance().getRampenummerKar()+1)+"");
				}
				else if(event.getItem().equals(Transportmateriel.MaterielType.KASSE)){
					lblNummer.setText((Service.getInstance().getRampenummerKasse()+1)+"");
				}
			}
		}
	}
}
