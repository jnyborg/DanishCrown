package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import model.Chauffør;
import service.Service;

/**
 * Skaber systemadministratorvinduet til redigering af chauffør.
 * @author Joachim
 *
 */
public class RedigerChauffør extends JDialog {


	private JPanel contentPane;
	private JButton btnFortryd, btnGem;
	private JLabel lblNavn, lblMobilnummer;
	private JTextFieldHint txfNavnHint, txfMobilnummerHint;
	private Chauffør chauffør;
	private Controller controller;
	private ChaufførDialog chaufførDialog;
	private boolean checkBox;
	
	public RedigerChauffør(Chauffør chauffør, ChaufførDialog chaufførDialog, boolean checkBox){
		this.setIconImage(MainFrame.logo);
		this.checkBox = checkBox;
		this.chaufførDialog = chaufførDialog;
		this.chauffør = chauffør;
		controller = new Controller();
		setResizable(false);
		setModal(true);
		
		this.setTitle("Rediger Chauffør");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);

		btnFortryd = new JButton("Fortryd");
		btnFortryd.addActionListener(controller);

		btnGem = new JButton("Gem");
		btnGem.addActionListener(controller);

		lblNavn = new JLabel("Navn:");

		lblMobilnummer = new JLabel("Mobilnummer");

		txfNavnHint = new JTextFieldHint(txfNavnHint, null, "Indtast navn");
		txfNavnHint.setText(chauffør.getNavn());

		txfMobilnummerHint = new JTextFieldHint(txfMobilnummerHint, null, "Indtast mobilnummer");
		txfMobilnummerHint.setText(chauffør.getMobilNummer());
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addGap(10)
								.addGroup(
										gl_contentPane
												.createParallelGroup(Alignment.LEADING)
												.addGroup(
														Alignment.TRAILING,
														gl_contentPane
																.createSequentialGroup()
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNavn).addComponent(lblMobilnummer))
																.addGap(39)
																.addGroup(
																		gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(txfMobilnummerHint)
																				.addComponent(txfNavnHint, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
												.addGroup(Alignment.TRAILING,
														gl_contentPane.createSequentialGroup().addComponent(btnGem).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnFortryd))).addGap(140)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNavn)
										.addComponent(txfNavnHint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblMobilnummer)
										.addComponent(txfMobilnummerHint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnFortryd).addComponent(btnGem))));
		contentPane.setLayout(gl_contentPane);
	}


	private class Controller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnGem)){
				try{
				chauffør.setNavn(txfNavnHint.getText());
				chauffør.setMobilNummer(txfMobilnummerHint.getText());
				Service.getInstance().updateChauffør(chauffør);
				if(checkBox){
					chaufførDialog.updateListLedigeChauffører();
				}else
					chaufførDialog.updateListAlleChauffører();
				dispose();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Venligst udfyld alle felter korrekt", "Fejl!",JOptionPane.ERROR_MESSAGE);
				}
			}else if(e.getSource().equals(btnFortryd)){
				dispose();
			}
		}
	}
}