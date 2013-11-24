package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
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

import model.Chauff�r;
import model.Lastbil;
import model.Transportmateriel;
import service.Service;

/**
 * Skaber systemadministratorvinduet til oprettelse af lastbil.
 */
public class OpretLastbil extends JDialog {

	private JPanel contentPane;
	private JButton btnOpretChauff�r, btnAfbryd, btnGem;
	private JLabel lblLastbilnummer, lblChauffr, lblTrailer, lblChauffrhusVgt, lblTransportmateriel, lblKg, lblM2;
	private JComboBox comboBox, comboBox_1;
	private JTextFieldHint txfLastbilnummer, txfV�gt, txfTrailerKapacitet;
	private Controller controller;
	private OpretLastbil opretLastbil;
	private SystemadministratorMain systemadminStratorMain;

	/**
	 * Create the frame.
	 */
	public OpretLastbil(SystemadministratorMain systemadminstratorMain) {
		this.systemadminStratorMain = systemadminstratorMain;
		opretLastbil = this;
		setResizable(false);
		setModal(true);
		controller = new Controller();
		this.setIconImage(MainFrame.logo);
		this.setTitle("Opret lastbil");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 410, 250);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnOpretChauff�r = new JButton("Opret Chauff�r");
		btnOpretChauff�r.addActionListener(controller);
		
		lblLastbilnummer = new JLabel("Lastbil-nummer:");
		
		lblChauffr = new JLabel("Chauff�r:");
		
		comboBox = new JComboBox();
		
		txfLastbilnummer = new JTextFieldHint(txfLastbilnummer,null,"Indtast lastbilnummer");
		txfLastbilnummer.setColumns(10);
		
		lblTrailer = new JLabel("Trailer:");
		lblTrailer.setFont(new Font(lblTrailer.getFont().getName(), Font.BOLD,lblTrailer.getFont().getSize()));
		
		btnAfbryd = new JButton("Afbryd");
		btnAfbryd.addActionListener(controller);
		
		btnGem = new JButton("Gem");
		btnGem.addActionListener(controller);
		
		lblChauffrhusVgt = new JLabel("Chauff�rhus v�gt:");
		
		txfV�gt = new JTextFieldHint(txfV�gt,null,"Indtast chauff�rhusets v�gt");
		txfV�gt.setColumns(10);
		
		txfTrailerKapacitet = new JTextFieldHint(txfTrailerKapacitet,null,"Indtast traileres kapacitet");
		txfTrailerKapacitet.setColumns(10);
		
		comboBox_1 = new JComboBox(Transportmateriel.MaterielType.values());
		
		lblTransportmateriel = new JLabel("Transportmateriel");
		
		lblKg = new JLabel("kg");
		
		lblM2 = new JLabel("m�");

		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGem)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAfbryd))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChauffr)
								.addComponent(lblLastbilnummer)
								.addComponent(lblChauffrhusVgt)
								.addComponent(lblTrailer)
								.addComponent(lblTransportmateriel))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txfTrailerKapacitet)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txfLastbilnummer, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
								.addComponent(txfV�gt)
								.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblM2)
								.addComponent(lblKg)
								.addComponent(btnOpretChauff�r))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastbilnummer)
						.addComponent(txfLastbilnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChauffr)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOpretChauff�r))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txfV�gt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChauffrhusVgt)
						.addComponent(lblKg))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTrailer)
						.addComponent(txfTrailerKapacitet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblM2))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTransportmateriel))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAfbryd)
						.addComponent(btnGem)))
		);
		contentPane.setLayout(gl_contentPane);
		
		updateChauff�rCombobox();
		
	}
	
	/**
	 * Opdaterer Comboboxen med chauff�rer. Desuden skal den s�rge for at kun de chauff�rer der ingen lastbil har vises p� denne liste.
	 */
	public void updateChauff�rCombobox(){
		Set<Chauff�r> temp = Service.getInstance().getChauff�rer();
		for(Lastbil lastbil: Service.getInstance().getLastbiler()){
			if(!(lastbil.getChauff�r()==null))
			{
				temp.remove(lastbil.getChauff�r());
			}
		}
		comboBox.setModel(new DefaultComboBoxModel(temp.toArray()));
	}
	
	
	
	/**
	 * Denne metode sikrer at n�r der oprettes en ny chauff�r, s�ttes han som den valgte chauff�r. Metoden kaldes fra OpretChauff�r Dialogen. 
	 */
	public void setAktuelChauff�r(Chauff�r chauff�r){
		comboBox.setSelectedItem(chauff�r);
	}

private class Controller implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnOpretChauff�r)){
			OpretChauff�r opretChauff�r = new OpretChauff�r(opretLastbil);
			opretChauff�r.setVisible(true);
		}else if(e.getSource().equals(btnGem)){

			if(comboBox.getSelectedItem()!=null) {
				try{	
				Transportmateriel temp = new Transportmateriel((Transportmateriel.MaterielType) comboBox_1.getSelectedItem());
				Service.getInstance().createLastbil(txfLastbilnummer.getText(),(Chauff�r) comboBox.getSelectedItem(), 
					temp, Integer.parseInt(txfTrailerKapacitet.getText()), Double.parseDouble(txfV�gt.getText()));
				systemadminStratorMain.updateLastbilJlist();
					dispose();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Venligst udfyld alle felter korrekt", "Fejl!",JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
					    "Opret f�rst en chauff�r!",
					    "OBS",
					    JOptionPane.INFORMATION_MESSAGE);

			}
		}
		else if(e.getSource().equals(btnAfbryd)){
			dispose();
			}
		}
	}
}

