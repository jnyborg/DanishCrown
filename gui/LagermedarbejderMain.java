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

import model.Aflæsning;
import model.Aflæsningsstatus;
import model.Rampe;
import dao.Dao;

/**
 * Skaber hovedgrænsefladen til lagermedarbejdervinduet.
 */
public class LagermedarbejderMain extends JDialog implements Observer, Subject {

	private final JPanel contentPanel = new JPanel();
	private JTextFieldHint txfSøg;
	private JList rampeListe, aflæsningListe;
	private JButton btnSøg;
	private Controller controller;
	private JLabel lblAflsningsk, lblRampenummer, lblRampeStatus, lblRampeMateriel, lblAflæsningForventetStart, lblAflæsningForventetSlut, lblAflæsningStatus, lblAflæsningLastbil, lblDelordreStørrelse,
			lblAflæsningPrioritet, lblPålæsses, lblHjprioritet;
	private JButton btnBegyndAflæsning, btnAfslutAflsning, btnStopRampe, btnStartRampe;
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
		
		txfSøg = new JTextFieldHint(txfSøg, iLast,"Nummer, materiel");
		txfSøg.setColumns(10);
		// ActionListener til Enter-knap
		txfSøg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSøg.doClick();
			}
		});

		btnSøg = new JButton("Søg");
		btnSøg.addActionListener(controller);

		lblAflsningsk = new JLabel("Aflæsningskø:");

		lblRampenummer = new JLabel(" ");

		lblRampeStatus = new JLabel(" ");

		lblRampeMateriel = new JLabel(" ");

		lblAflæsningForventetStart = new JLabel(" ");

		lblAflæsningForventetSlut = new JLabel(" ");

		lblAflæsningStatus = new JLabel(" ");

		lblAflæsningLastbil = new JLabel(" ");

		lblDelordreStørrelse = new JLabel(" ");

		lblAflæsningPrioritet = new JLabel(" ");

		btnBegyndAflæsning = new JButton("Begynd aflæsning");
		btnBegyndAflæsning.addActionListener(controller);
		btnBegyndAflæsning.setEnabled(false);

		btnAfslutAflsning = new JButton("Afslut aflæsning");
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
		
		lblHjprioritet = new JLabel("Højprioritet");
		
		lblPålæsses = new JLabel("Pålæsses");
		
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
											.addComponent(txfSøg, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnSøg, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblRampenummer, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
									.addGap(18)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAflæsningForventetStart, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
								.addComponent(lblAflæsningForventetSlut, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnStopRampe, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
										.addComponent(btnAfslutAflsning, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
										.addComponent(btnBegyndAflæsning, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txfGreen, 0, 0, Short.MAX_VALUE)
												.addComponent(txfYellow, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblHjprioritet)
												.addComponent(lblPålæsses))
											.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE))
										.addComponent(btnStartRampe, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
								.addComponent(lblAflsningsk))
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblRampeMateriel, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAflæsningLastbil, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(lblDelordreStørrelse))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblAflæsningStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAflæsningPrioritet, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSøg)
						.addComponent(txfSøg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAflsningsk))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnBegyndAflæsning)
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
								.addComponent(lblPålæsses, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txfGreen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAflæsningForventetStart)
						.addComponent(lblRampenummer))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAflæsningForventetSlut)
						.addComponent(lblRampeStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRampeMateriel)
						.addComponent(lblAflæsningLastbil)
						.addComponent(lblAflæsningStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAflæsningPrioritet)
						.addComponent(lblDelordreStørrelse))
					.addGap(40))
		);
		{
			aflæsningListe = new JList();
			scrollPane_1.setViewportView(aflæsningListe);
			aflæsningListe.addListSelectionListener(controller);
			aflæsningListe.setCellRenderer(new SelectedListCellRenderer());
		}
		{
			rampeListe = new JList();
			scrollPane.setViewportView(rampeListe);
			rampeListe.setListData(Service.getInstance().getRamper().toArray());
			rampeListe.addListSelectionListener(controller);
			//Denne klasse er til at ændre farven på obejkterne i JListen hvis de er under pålæsning eller er af højprioritet.
			rampeListe.setCellRenderer(new SelectedListCellRenderer());
		}
		contentPanel.setLayout(gl_contentPanel);
	}

	/**
	 * Override af dispose(). Da alle gui-vinduer er både Observer og Subject skal de fjernes fra listen af observers når vinduet lukkes
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

	public void updateAflæsningList() {
		if (rampeListe.getSelectedValue() != null) {
			Rampe rampe = (Rampe) rampeListe.getSelectedValue();
			aflæsningListe.setListData(rampe.getAflæsningskø().toArray());
			System.out.println(rampe.getAflæsningskø());
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
				if (e.getSource().equals(btnSøg)) {
				String søg = txfSøg.getText().toLowerCase().trim();
				if (søg.equals("".trim())) {
					updateRampelist();
				} else {
					Set<Rampe> søgRampe = new HashSet<Rampe>();
					Set<Rampe> ramper = Service.getInstance().getRamper();
					try {
						// isInteger
						Integer.parseInt(søg);

						for (Rampe rampe : ramper) {
							if (String.valueOf(rampe.getRampeNummer()).contains(søg)) {
								søgRampe.add(rampe);
							}
						}
					} catch (Exception e1) {
						// notInteger
						for (Rampe rampe : ramper) {
							if (rampe.getTransportmateriel().getType().name().toLowerCase().contains(søg)) {
								søgRampe.add(rampe);
							}
						}
					}
					rampeListe.setListData(søgRampe.toArray());
				}
			} else if (e.getSource().equals(btnBegyndAflæsning)) {
				if (aflæsningListe.getSelectedValue().equals(aflæsningListe.getModel().getElementAt(0))) {
					Aflæsning aflæsning = (Aflæsning) aflæsningListe.getSelectedValue();
					if(aflæsning.getLastbil().getTrailer().getVægtFørKontrolvejning() != 0.0){	
						OpretDato opretDato;
						//Hvis aflæsningen er højprioritet gælder dens forventede start/slut ikke længere, og OpretDato sættes til tiden lige nu.
						if(aflæsning.isHøjPrioritet()){
							opretDato = new OpretDato();
						}
						//Aflæsningen er af normal prioritet og opretDato oprettes med det forventede starttidspunkt.
						else{
							opretDato = new OpretDato(aflæsning.getForventetStart());
						}
						Date date = opretDato.showDialog();
						if(date != null){
							aflæsning.getRampe().begyndAflæsning(date);
							btnBegyndAflæsning.setEnabled(false);
							btnAfslutAflsning.setEnabled(true);
							aflæsningListe.setSelectedValue(aflæsning, true);
							updateRampelist();
							notifyObservers();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Vej trailer før aflæsning kan påbegyndes!", "Fejl",JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource().equals(btnAfslutAflsning)) {
				if (aflæsningListe.getSelectedValue().equals(aflæsningListe.getModel().getElementAt(0))) {
					Aflæsning aflæsning = (Aflæsning) aflæsningListe.getSelectedValue();
					OpretDato opretDato;
					//Hvis aflæsningen er højprioritet gælder dens forventede start/slut ikke længere, og OpretDato sættes til tiden lige nu.
					if(aflæsning.isHøjPrioritet()){
						opretDato = new OpretDato();
					}
					//Aflæsningen er af normal prioritet og opretDato oprettes med det forventede sluttidspunkt.
					else{
						opretDato = new OpretDato(aflæsning.getForventetSlut());
					}
					Date date = opretDato.showDialog();
					if(date != null){
						if(date.getTime() > aflæsning.getAktuelStart().getTime()){
							aflæsning.getRampe().afslutAflæsning(date);
							btnAfslutAflsning.setEnabled(false);
							aflæsningListe.setListData(aflæsning.getRampe().getAflæsningskø().toArray());
							Date afhentningstid = null;
							if(date.getTime() > aflæsning.getHviletid().getTime()){
								afhentningstid = date;
							}else{
								afhentningstid = aflæsning.getHviletid();
							}
							
							JOptionPane.showMessageDialog(null, aflæsning.getLastbil().getChauffør().getNavn()+ " afhenter trailer klokken: " + formatTime(afhentningstid.getHours()) + ":"
							+ formatTime(afhentningstid.getMinutes()) , "SMS afsendt!", JOptionPane.INFORMATION_MESSAGE);
							notifyObservers();
						}else {
							JOptionPane.showMessageDialog(null,"Sluttidspunkt skal være efter starttidspunkt!", "Fejl", JOptionPane.ERROR_MESSAGE);
						}
					}
					notifyObservers();
				}
			} else if(e.getSource().equals(btnStopRampe)){
				if(rampeListe.getSelectedValue() != null){
					Rampe rampe = (Rampe) rampeListe.getSelectedValue();
					if(!rampe.isStoppet()){
						int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil stopppe denne rampe?\n\rAlle aflæsninger vil blive flyttet til ledige ramper", "Advarsel",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.YES_OPTION) {
								Service.getInstance().stopRampe(rampe);
								updateRampelist();
								rampeListe.setSelectedValue(rampe, true);
								updateAflæsningList();
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
					if (rampe.getAflæsningIGang() == null) {
						aflæsningListe.setListData(rampe.getAflæsningskø().toArray());
					} else {
						LinkedList<Aflæsning> aflæsninger = new LinkedList();
						aflæsninger.addAll(rampe.getAflæsningskø());
						aflæsninger.add(0, rampe.getAflæsningIGang());
						aflæsningListe.setListData(aflæsninger.toArray());
					}
				} else {
					btnStopRampe.setEnabled(false);
					btnStartRampe.setEnabled(false);
				}
			} else if (e.getSource().equals(aflæsningListe)) {
				if (aflæsningListe.getSelectedValue() != null) {
					Aflæsning aflæsning = (Aflæsning) aflæsningListe.getSelectedValue();
					if (aflæsning.equals(aflæsningListe.getModel().getElementAt(0))) {
						if (aflæsning.getStatus().equals(Aflæsningsstatus.PÅLÆSSES)) {
							btnBegyndAflæsning.setEnabled(false);
							btnAfslutAflsning.setEnabled(true);
						} else {
							btnBegyndAflæsning.setEnabled(true);
							btnAfslutAflsning.setEnabled(false);
						}
					} else {
						btnBegyndAflæsning.setEnabled(false);
						btnAfslutAflsning.setEnabled(false);
					}
					lblAflæsningForventetStart.setText("Forventet start: " + aflæsning.getForventetStart());
					lblAflæsningForventetSlut.setText("Forventet slut:   " + aflæsning.getForventetSlut());
					lblAflæsningLastbil.setText("Lastbil nr: " + aflæsning.getLastbil().getNummer());
					lblDelordreStørrelse.setText("Størrelse: " + aflæsning.getLastbil().getDelordre().getVægt() + " kg");
					lblAflæsningPrioritet.setText("Prioritet: " + aflæsning.isHøjPrioritet());
					lblAflæsningStatus.setText("Status: " + aflæsning.getStatus().toString());
					if (aflæsning.isHøjPrioritet()) {
						lblAflæsningPrioritet.setText("Prioritet: Høj");
					} else {
						lblAflæsningPrioritet.setText("Prioritet: Normal");
					}
				} else {
					btnBegyndAflæsning.setEnabled(false);
					btnAfslutAflsning.setEnabled(false);
				}
			}
		}
	}

	public class SelectedListCellRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object obj, int index, boolean isSelected, boolean cellHasFocus) {
			Component component = super.getListCellRendererComponent(list, obj, index, isSelected, cellHasFocus);
			if (list.equals(LagermedarbejderMain.this.aflæsningListe)) {
				if (((Aflæsning) obj).getStatus().equals(Aflæsningsstatus.PÅLÆSSES)) {
					Color colorBG = new Color(0, 204, 0);
					setBackground(colorBG);
				}
				if(((Aflæsning) obj).isHøjPrioritet()){
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
		updateAflæsningList();
	}

	@Override
	public void notifyObservers() {
		for(Observer obv: observers){
			obv.update();
		}
		
	}

}
