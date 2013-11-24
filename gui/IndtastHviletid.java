package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Lastbil;
import service.Service;
/**
 * Skaber brugergrænsefladen til indtastning af hviletid.
 */
public class IndtastHviletid extends JDialog implements Subject {

	private final JPanel contentPanel = new JPanel();
	private JTextField txfTimer, txfMinutter;
	private Controller controller = new Controller();
	private JButton btnBeregnHviletid, okButton, cancelButton, btnIndtastKlokkeslæt;
	private JLabel lblHviletid, lblFejl, lblKlokkenErNu, lblKlokkeslæt;
	private Date hviletid, result;
	private Lastbil lastbil;
	private ChaufførMain chaufførMain;

	
	/**
	 * Create the dialog.
	 */
	public IndtastHviletid(Lastbil lastbil, ChaufførMain chaufførMain) {
		this.setIconImage(MainFrame.logo);
		this.result = null;
		this.chaufførMain = chaufførMain; 
		this.hviletid = null;
		this.lastbil = lastbil;
		
		setBounds(100, 100, 450, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("Jeg skal holde hviletid i");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		txfTimer = new JTextField();
		txfTimer.setColumns(10);
		JLabel lblTimerOg = new JLabel("timer og");
		txfMinutter = new JTextField();
		txfMinutter.setColumns(10);
		JLabel lblMinutter = new JLabel("minutter");
		btnBeregnHviletid = new JButton("Beregn hviletid");
		btnBeregnHviletid.addActionListener(controller);
		lblHviletid = new JLabel("Ikke beregnet hviletid");
		lblHviletid.setHorizontalAlignment(SwingConstants.CENTER);
		this.setTitle("Indtast hviletid");
		
		lblFejl = new JLabel("");
		lblFejl.setHorizontalAlignment(SwingConstants.CENTER);
		lblFejl.setForeground(Color.RED);
		
		lblKlokkenErNu = new JLabel("Dato og klokkesl\u00E6t er nu");
		lblKlokkenErNu.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnIndtastKlokkeslæt = new JButton("Indtast klokkesl\u00E6t");
		btnIndtastKlokkeslæt.addActionListener(controller);
		
		lblKlokkeslæt = new JLabel("Ikke valgt tidspunkt");
		lblKlokkeslæt.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(148, Short.MAX_VALUE)
					.addComponent(btnIndtastKlokkeslæt)
					.addGap(165))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKlokkeslæt, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblFejl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblHviletid, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
						.addComponent(lblKlokkenErNu, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(105)
							.addComponent(txfTimer, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBeregnHviletid)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblTimerOg)
									.addGap(18)
									.addComponent(txfMinutter, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblMinutter)))
							.addGap(104)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblKlokkenErNu)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnIndtastKlokkeslæt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblKlokkeslæt)
					.addGap(33)
					.addComponent(lblNewLabel)
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txfTimer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTimerOg)
						.addComponent(txfMinutter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMinutter))
					.addGap(28)
					.addComponent(btnBeregnHviletid)
					.addGap(18)
					.addComponent(lblHviletid)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblFejl, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
					.addGap(18))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Godkend hviletid");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(controller);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(controller);
			}
		}
	}
	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String formatTime(int time) {
		
		if(String.valueOf(time).length()==1) {
			return "0"+time;
		} else
			return ""+time;
	}

	/**
	 * Override af dispose(). Da alle gui-vinduer er både Observer og Subject skal de fjernes fra listen af observers når vinduet lukkes
	 */
	@Override
	public void dispose() {
		super.dispose();
		observers.remove(this);
	}


	public class Controller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnBeregnHviletid) {
				if(isInteger(txfTimer.getText()) && isInteger(txfMinutter.getText())) {
					if(Integer.parseInt(txfTimer.getText()) >= 0 && Integer.parseInt(txfMinutter.getText()) >= 0) {
						//love you big
						long time =  (Integer.parseInt(txfTimer.getText())*60 + Integer.parseInt(txfMinutter.getText()))*60000;
						Date chosenTime = result;
						hviletid = new Date(chosenTime.getTime()+time);
					
						lblHviletid.setText("Jeg er klar igen d. "+formatTime(hviletid.getDate())+"/"+(formatTime(hviletid.getMonth()+1)+"/"+(chosenTime.getYear()+1900))+" kl. "+formatTime(hviletid.getHours())+":"+formatTime(hviletid.getMinutes()));
						lblFejl.setText("");
					} else {
						lblFejl.setText("Fejl: Timer og minutter skal være positive tal.");
						lblHviletid.setText("");
						hviletid = null;
					}
				} else {
					lblFejl.setText("Fejl: Timer og minutter skal være tal.");
					lblHviletid.setText("");
					hviletid = null;
				}
			}
			else if(e.getSource()==btnIndtastKlokkeslæt) {
				OpretDato opretDato = null;
				if(result == null){
					opretDato = new OpretDato();
				}else{
					opretDato = new OpretDato(result);
				}
				opretDato.setDatoLabel(lblKlokkeslæt);
				Date temp = opretDato.showDialog();
				if(temp!=null) {
					result = temp;
					lblKlokkeslæt.setText(result.toString());
				}
			} else if(e.getSource()==okButton) {
				if(hviletid!=null) {

					Service.getInstance().registrerAnkomst(lastbil, hviletid,result);

					chaufførMain.updateButtons(lastbil);
					notifyObservers();
					dispose();
					JOptionPane.showMessageDialog(null,
						    "Din ankomst er godkendt. Husk at veje den tomme trailer!",
						    "Godkendt",
						    JOptionPane.INFORMATION_MESSAGE);
				} else {
					lblFejl.setText("Beregn først en gyldig hviletid!");
				}
			}
			else if(e.getSource()==cancelButton) {
				dispose();
			}

		}
	}

	@Override
	public void notifyObservers() {
		for(Observer obv: observers){
			obv.update();
		}
	}
}
