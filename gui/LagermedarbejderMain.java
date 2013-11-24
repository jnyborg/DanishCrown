package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;

import service.Service;

import model.Afl�sning;
import model.Afl�sningsstatus;
import model.Rampe;
import dao.Dao;

/**
 * Skaber hovedgr�nsefladen til lagermedarbejdervinduet.
 */
public class LagermedarbejderMain extends JDialog implements Observer, Subject {

	private final JPanel contentPanel = new JPanel();
	private JTextFieldHint txfS�g;
	private JList rampeListe, afl�sningListe;
	private JButton btnS�g;
	private Controller controller;
	private JLabel lblAflsningsk, lblRampenummer, lblRampeStatus, lblRampeMateriel, lblAfl�sningForventetStart, lblAfl�sningForventetSlut, lblAfl�sningStatus, lblAfl�sningLastbil, lblDelordreSt�rrelse,
			lblAfl�sningPrioritet, lblP�l�sses, lblHjprioritet;
	private JButton btnBegyndAfl�sning, btnAfslutAflsning, btnStopRampe, btnStartRampe;
	private JTextField txfYellow, txfGreen;
	private ImageIcon iLast;
	
	/**
	 * Create the dialog.
	 */
	public LagermedarbejderMain() {
		this.setIconImage(MainFrame.logo);
		controller = new Controller();
		setTitle("Lagermedarbejder");
		setBounds(100, 100, 644, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane scrollPane_1 = new JScrollPane();
		setLocationRelativeTo(null);
		observers.add(this);
		
		try {
			InputStream input4 = getClass().getResourceAsStream("/search.png");
			Image logo4 = ImageIO.read(input4);
			iLast = new ImageIcon(logo4);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		txfS�g = new JTextFieldHint(txfS�g, iLast,"Nummer, materiel");
		txfS�g.setColumns(10);
		// ActionListener til Enter-knap
		txfS�g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnS�g.doClick();
			}
		});

		btnS�g = new JButton("S�g");
		btnS�g.addActionListener(controller);

		lblAflsningsk = new JLabel("Afl�sningsk�:");

		lblRampenummer = new JLabel(" ");

		lblRampeStatus = new JLabel(" ");

		lblRampeMateriel = new JLabel(" ");

		lblAfl�sningForventetStart = new JLabel(" ");

		lblAfl�sningForventetSlut = new JLabel(" ");

		lblAfl�sningStatus = new JLabel(" ");

		lblAfl�sningLastbil = new JLabel(" ");

		lblDelordreSt�rrelse = new JLabel(" ");

		lblAfl�sningPrioritet = new JLabel(" ");

		btnBegyndAfl�sning = new JButton("Begynd afl�sning");
		btnBegyndAfl�sning.addActionListener(controller);
		btnBegyndAfl�sning.setEnabled(false);

		btnAfslutAflsning = new JButton("Afslut afl�sning");
		btnAfslutAflsning.addActionListener(controller);
		btnAfslutAflsning.setEnabled(false);

		btnStopRampe = new JButton("Stop rampe");
		btnStopRampe.addActionListener(controller);
		btnStopRampe.setEnabled(false);
		
		btnStartRampe = new JButton("Start rampe");
		btnStartRampe.addActionListener(controller);
		btnStartRampe.setEnabled(false);
		
		txfYellow = new JTextField();
		txfYellow.setColumns(10);
		txfYellow.setEditable(false);
		txfYellow.setBackground(new Color(255, 255, 0));
			
		txfGreen = new JTextField();
		txfGreen.setColumns(10);
		txfGreen.setEditable(false);
		txfGreen.setBackground(new Color(0, 204, 0));
		
		lblHjprioritet = new JLabel("H�jprioritet");
		
		lblP�l�sses = new JLabel("P�l�sses");
		
		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblRampeStatus, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(txfS�g, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnS�g, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblRampenummer, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
									.addGap(18)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAfl�sningForventetStart, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
								.addComponent(lblAfl�sningForventetSlut, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnStopRampe, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
										.addComponent(btnAfslutAflsning, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
										.addComponent(btnBegyndAfl�sning, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txfGreen, 0, 0, Short.MAX_VALUE)
												.addComponent(txfYellow, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblHjprioritet)
												.addComponent(lblP�l�sses))
											.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE))
										.addComponent(btnStartRampe, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
								.addComponent(lblAflsningsk))
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblRampeMateriel, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAfl�sningLastbil, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(lblDelordreSt�rrelse))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblAfl�sningStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAfl�sningPrioritet, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnS�g)
						.addComponent(txfS�g, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAflsningsk))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnBegyndAfl�sning)
							.addGap(4)
							.addComponent(btnAfslutAflsning)
							.addGap(5)
							.addComponent(btnStopRampe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStartRampe)
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHjprioritet)
								.addComponent(txfYellow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblP�l�sses, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txfGreen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAfl�sningForventetStart)
						.addComponent(lblRampenummer))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAfl�sningForventetSlut)
						.addComponent(lblRampeStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRampeMateriel)
						.addComponent(lblAfl�sningLastbil)
						.addComponent(lblAfl�sningStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAfl�sningPrioritet)
						.addComponent(lblDelordreSt�rrelse))
					.addGap(40))
		);
		{
			afl�sningListe = new JList();
			scrollPane_1.setViewportView(afl�sningListe);
			afl�sningListe.addListSelectionListener(controller);
			afl�sningListe.setCellRenderer(new SelectedListCellRenderer());
		}
		{
			rampeListe = new JList();
			scrollPane.setViewportView(rampeListe);
			rampeListe.setListData(Service.getInstance().getRamper().toArray());
			rampeListe.addListSelectionListener(controller);
			//Denne klasse er til at �ndre farven p� obejkterne i JListen hvis de er under p�l�sning eller er af h�jprioritet.
			rampeListe.setCellRenderer(new SelectedListCellRenderer());
		}
		contentPanel.setLayout(gl_contentPanel);
	}

	/**
	 * Override af dispose(). Da alle gui-vinduer er b�de Observer og Subject skal de fjernes fra listen af observers n�r vinduet lukkes
	 */
	@Override
	public void dispose() {
		super.dispose();
		Subject.observers.remove(this);
	}

	public void updateRampelist() {
		rampeListe.setListData(Service.getInstance().getRamper().toArray());
		Rampe rampe = (Rampe) rampeListe.getSelectedValue();
		
	}

	public void updateAfl�sningList() {
		if (rampeListe.getSelectedValue() != null) {
			Rampe rampe = (Rampe) rampeListe.getSelectedValue();
			afl�sningListe.setListData(rampe.getAfl�sningsk�().toArray());
			System.out.println(rampe.getAfl�sningsk�());
		}
	}
	
	public String formatTime(int time) {
		
		if(String.valueOf(time).length()==1) {
			return "0"+time;
		} else
			return ""+time;
	}

	public class Controller implements ActionListener, ListSelectionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnS�g)) {
				String s�g = txfS�g.getText().toLowerCase().trim();
				if (s�g.equals("".trim())) {
					updateRampelist();
				} else {
					Set<Rampe> s�gRampe = new HashSet<Rampe>();
					Set<Rampe> ramper = Service.getInstance().getRamper();
					try {
						// isInteger
						Integer.parseInt(s�g);

						for (Rampe rampe : ramper) {
							if (String.valueOf(rampe.getRampeNummer()).contains(s�g)) {
								s�gRampe.add(rampe);
							}
						}
					} catch (Exception e1) {
						// notInteger
						for (Rampe rampe : ramper) {
							if (rampe.getTransportmateriel().getType().name().toLowerCase().contains(s�g)) {
								s�gRampe.add(rampe);
							}
						}
					}
					rampeListe.setListData(s�gRampe.toArray());
				}
			} else if (e.getSource().equals(btnBegyndAfl�sning)) {
				if (afl�sningListe.getSelectedValue().equals(afl�sningListe.getModel().getElementAt(0))) {
					Afl�sning afl�sning = (Afl�sning) afl�sningListe.getSelectedValue();
					if(afl�sning.getLastbil().getTrailer().getV�gtF�rKontrolvejning() != 0.0){	
						OpretDato opretDato;
						//Hvis afl�sningen er h�jprioritet g�lder dens forventede start/slut ikke l�ngere, og OpretDato s�ttes til tiden lige nu.
						if(afl�sning.isH�jPrioritet()){
							opretDato = new OpretDato();
						}
						//Afl�sningen er af normal prioritet og opretDato oprettes med det forventede starttidspunkt.
						else{
							opretDato = new OpretDato(afl�sning.getForventetStart());
						}
						Date date = opretDato.showDialog();
						if(date != null){
							afl�sning.getRampe().begyndAfl�sning(date);
							btnBegyndAfl�sning.setEnabled(false);
							btnAfslutAflsning.setEnabled(true);
							afl�sningListe.setSelectedValue(afl�sning, true);
							updateRampelist();
							notifyObservers();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Vej trailer f�r afl�sning kan p�begyndes!", "Fejl",JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource().equals(btnAfslutAflsning)) {
				if (afl�sningListe.getSelectedValue().equals(afl�sningListe.getModel().getElementAt(0))) {
					Afl�sning afl�sning = (Afl�sning) afl�sningListe.getSelectedValue();
					OpretDato opretDato;
					//Hvis afl�sningen er h�jprioritet g�lder dens forventede start/slut ikke l�ngere, og OpretDato s�ttes til tiden lige nu.
					if(afl�sning.isH�jPrioritet()){
						opretDato = new OpretDato();
					}
					//Afl�sningen er af normal prioritet og opretDato oprettes med det forventede sluttidspunkt.
					else{
						opretDato = new OpretDato(afl�sning.getForventetSlut());
					}
					Date date = opretDato.showDialog();
					if(date != null){
						if(date.getTime() > afl�sning.getAktuelStart().getTime()){
							afl�sning.getRampe().afslutAfl�sning(date);
							btnAfslutAflsning.setEnabled(false);
							afl�sningListe.setListData(afl�sning.getRampe().getAfl�sningsk�().toArray());
							Date afhentningstid = null;
							if(date.getTime() > afl�sning.getHviletid().getTime()){
								afhentningstid = date;
							}else{
								afhentningstid = afl�sning.getHviletid();
							}
							
							JOptionPane.showMessageDialog(null, afl�sning.getLastbil().getChauff�r().getNavn()+ " afhenter trailer klokken: " + formatTime(afhentningstid.getHours()) + ":"
							+ formatTime(afhentningstid.getMinutes()) , "SMS afsendt!", JOptionPane.INFORMATION_MESSAGE);
							notifyObservers();
						}else {
							JOptionPane.showMessageDialog(null,"Sluttidspunkt skal v�re efter starttidspunkt!", "Fejl", JOptionPane.ERROR_MESSAGE);
						}
					}
					notifyObservers();
				}
			} else if(e.getSource().equals(btnStopRampe)){
				if(rampeListe.getSelectedValue() != null){
					Rampe rampe = (Rampe) rampeListe.getSelectedValue();
					if(!rampe.isStoppet()){
						int response = JOptionPane.showConfirmDialog(null, "Er du sikker p� at du vil stopppe denne rampe?\n\rAlle afl�sninger vil blive flyttet til ledige ramper", "Advarsel",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.YES_OPTION) {
								Service.getInstance().stopRampe(rampe);
								updateRampelist();
								rampeListe.setSelectedValue(rampe, true);
								updateAfl�sningList();
								notifyObservers();
							}
						}
				}
			} else if(e.getSource().equals(btnStartRampe)){
				if(rampeListe.getSelectedValue() != null){
					Rampe rampe = (Rampe) rampeListe.getSelectedValue();
					rampe.setStoppet(false);
					updateRampelist();
					JOptionPane.showMessageDialog(null,"Rampe er opstartet igen.", "Fejl", JOptionPane.INFORMATION_MESSAGE);
					notifyObservers();
				}
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource().equals(rampeListe)) {
				if (rampeListe.getSelectedValue() != null) {
					Rampe rampe = (Rampe) rampeListe.getSelectedValue();
					if(rampe.isStoppet()){
						btnStartRampe.setEnabled(true);
						btnStopRampe.setEnabled(false);
					} else{
						btnStopRampe.setEnabled(true);
						btnStartRampe.setEnabled(false);
					}
					lblRampenummer.setText("Nummer: " + rampe.getRampeNummer());
					lblRampeMateriel.setText("Transportmateriel: " + rampe.getTransportmateriel().getType().toString());
					if (rampe.isStoppet()) {
						lblRampeStatus.setText("Status: Inaktiv");
					} else {
						lblRampeStatus.setText("Status: Aktiv");
					}
					if (rampe.getAfl�sningIGang() == null) {
						afl�sningListe.setListData(rampe.getAfl�sningsk�().toArray());
					} else {
						LinkedList<Afl�sning> afl�sninger = new LinkedList();
						afl�sninger.addAll(rampe.getAfl�sningsk�());
						afl�sninger.add(0, rampe.getAfl�sningIGang());
						afl�sningListe.setListData(afl�sninger.toArray());
					}
				} else {
					btnStopRampe.setEnabled(false);
					btnStartRampe.setEnabled(false);
				}
			} else if (e.getSource().equals(afl�sningListe)) {
				if (afl�sningListe.getSelectedValue() != null) {
					Afl�sning afl�sning = (Afl�sning) afl�sningListe.getSelectedValue();
					if (afl�sning.equals(afl�sningListe.getModel().getElementAt(0))) {
						if (afl�sning.getStatus().equals(Afl�sningsstatus.P�L�SSES)) {
							btnBegyndAfl�sning.setEnabled(false);
							btnAfslutAflsning.setEnabled(true);
						} else {
							btnBegyndAfl�sning.setEnabled(true);
							btnAfslutAflsning.setEnabled(false);
						}
					} else {
						btnBegyndAfl�sning.setEnabled(false);
						btnAfslutAflsning.setEnabled(false);
					}
					lblAfl�sningForventetStart.setText("Forventet start: " + afl�sning.getForventetStart());
					lblAfl�sningForventetSlut.setText("Forventet slut:   " + afl�sning.getForventetSlut());
					lblAfl�sningLastbil.setText("Lastbil nr: " + afl�sning.getLastbil().getNummer());
					lblDelordreSt�rrelse.setText("St�rrelse: " + afl�sning.getLastbil().getDelordre().getV�gt() + " kg");
					lblAfl�sningPrioritet.setText("Prioritet: " + afl�sning.isH�jPrioritet());
					lblAfl�sningStatus.setText("Status: " + afl�sning.getStatus().toString());
					if (afl�sning.isH�jPrioritet()) {
						lblAfl�sningPrioritet.setText("Prioritet: H�j");
					} else {
						lblAfl�sningPrioritet.setText("Prioritet: Normal");
					}
				} else {
					btnBegyndAfl�sning.setEnabled(false);
					btnAfslutAflsning.setEnabled(false);
				}
			}
		}
	}

	public class SelectedListCellRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object obj, int index, boolean isSelected, boolean cellHasFocus) {
			Component component = super.getListCellRendererComponent(list, obj, index, isSelected, cellHasFocus);
			if (list.equals(LagermedarbejderMain.this.afl�sningListe)) {
				if (((Afl�sning) obj).getStatus().equals(Afl�sningsstatus.P�L�SSES)) {
					Color colorBG = new Color(0, 204, 0);
					setBackground(colorBG);
				}
				if(((Afl�sning) obj).isH�jPrioritet()){
					Color colorBG = new Color(255, 255, 0);
					setBackground(colorBG);
				}
			}
			return component;
		}
	}

	@Override
	public void update() {
		Rampe rampe = (Rampe) rampeListe.getSelectedValue();
		updateRampelist();
		rampeListe.setSelectedValue(rampe, true);
		updateAfl�sningList();
	}

	@Override
	public void notifyObservers() {
		for(Observer obv: observers){
			obv.update();
		}
		
	}

}
