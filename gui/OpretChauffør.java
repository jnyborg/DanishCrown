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
 * Systemadministratorvindue til oprettelse af chauffører.
 */
public class OpretChauffør extends JDialog {

	private JPanel contentPane;
	private JButton btnFortryd, btnGem;
	private JLabel lblNavn, lblMobilnummer;
	private JTextFieldHint txfNavnHint, txfMobilnummerHint;
	private Controller controller;
	private OpretLastbil opretLastbil = null;
	private ChaufførDialog chaufførDialog = null;
	private RedigerLastbil redigerLastbil = null;
	private boolean checkBox;
	
	/**
	 * 3 Constructors da vi kan genanvende dialogen da den skal gøre det samme.
	 * Den ene constructor kaldes fra OpretLastbil, den anden kaldes fra ChaufførDialog, mens den sidste kaldes fra RedigerLasbil.
	 */
	public OpretChauffør(OpretLastbil opretLastbil) {
		this.setIconImage(MainFrame.logo);
		this.opretLastbil =opretLastbil;
		this.chaufførDialog = null;
		this.redigerLastbil = null;
		initializeComponent();
	}
	
	public OpretChauffør(ChaufførDialog chaufførDialog, boolean checkBox){
		this.setIconImage(MainFrame.logo);
		this.checkBox = checkBox;
		this.chaufførDialog = chaufførDialog;
		this.opretLastbil = null;
		this.redigerLastbil = null;
		initializeComponent();
	}
	public OpretChauffør(RedigerLastbil redigerLastbil){
		this.setIconImage(MainFrame.logo);
		this.redigerLastbil= redigerLastbil;
		chaufførDialog = null;
		opretLastbil = null;
		initializeComponent();
	}
	
	/**
	 * Initialiserer JDialog.
	 */
	public void initializeComponent(){
		controller = new Controller();
		setResizable(false);
		setModal(true);
		this.setTitle("Opret Chauffør");
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
		//ActionListener til enterknap.
		txfNavnHint.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    btnGem.doClick();
            }});
		
		txfMobilnummerHint = new JTextFieldHint(txfMobilnummerHint, null, "Indtast mobilnummer");
		//ActionListener til enterknap.
		txfMobilnummerHint.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    btnGem.doClick();
            }});
		
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
			
			if (e.getSource().equals(btnFortryd)) {
				dispose();
			} 
			
			else if (e.getSource().equals(btnGem)) {
					
				try {
					if(txfNavnHint.getText().length()==0 || txfMobilnummerHint.getText().length()==0){
						throw new NumberFormatException();
					}
					Integer.parseInt(txfMobilnummerHint.getText());
					Chauffør temp = Service.getInstance().createChauffør(txfNavnHint.getText(),txfMobilnummerHint.getText());
					if(opretLastbil != null){
						opretLastbil.updateChaufførCombobox();
						opretLastbil.setAktuelChauffør(temp);
					} else if(chaufførDialog != null){
						if(checkBox){
							chaufførDialog.updateListLedigeChauffører();
						}else
						chaufførDialog.updateListAlleChauffører();
					} else if(redigerLastbil != null){
						redigerLastbil.updateChaufførCombobox();
						redigerLastbil.setAktuelChauffør(temp);
					}
					dispose();
						 
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Venligst udfyld alle felter korrekt", "Fejl!",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}