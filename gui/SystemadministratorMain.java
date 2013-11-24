package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import service.Service;

import model.Lastbil;
import model.Rampe;

/**
 * Skaber hovedvinduet til systemadministratoren.
 * @author Joachim
 *
 */
public class SystemadministratorMain extends JDialog implements Subject, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private JButton btnOpretLastbil, btnChaufførListe, btnSletLastbil, btnRedigerLastbil, btnOpretRampe, btnRedigerRampe, btnSletRampe, btnStatistik;
	private JScrollPane scrollPane, scrollPane_1;
	private JList rampeliste, lastbilerListe;
	private Controller controller;
	private SystemadministratorMain systemadministratorMain;
	private JLabel lblLastbiler, lblRamper, lblLastbil, lblLastbilNummer, lblLastbilchauffr, lblLastbiltrailer, lblTrailerMateriel, lblLastbilvgt, lblRampe, lblRampennummer, lblRampestatus,
			lblRampeTransportmateriel;

	/**
	 * Create the frame...
	 */
	public SystemadministratorMain() {
		this.setIconImage(MainFrame.logo);
		this.setModal(false);
		this.systemadministratorMain = this;
		controller = new Controller();

		observers.add(this);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Administator");
		setBounds(100, 100, 644, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		btnOpretLastbil = new JButton("Opret Lastbil");
		btnOpretLastbil.addActionListener(controller);

		btnSletLastbil = new JButton("Slet Lastbil");
		btnSletLastbil.addActionListener(controller);

		btnChaufførListe = new JButton("Chauffør liste");
		btnChaufførListe.addActionListener(controller);

		btnRedigerLastbil = new JButton("Rediger Lastbil");
		btnRedigerLastbil.addActionListener(controller);

		scrollPane = new JScrollPane();

		btnOpretRampe = new JButton("Opret Rampe");
		btnOpretRampe.addActionListener(controller);

		btnRedigerRampe = new JButton("Rediger Rampe");
		btnRedigerRampe.addActionListener(controller);

		btnSletRampe = new JButton("Slet Rampe");
		btnSletRampe.addActionListener(controller);

		scrollPane_1 = new JScrollPane();

		lblLastbiler = new JLabel("Lastbiler");

		lblRamper = new JLabel("Ramper");

		// Auto-generated kode af Window-Builder
		panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_contentPane
														.createSequentialGroup()
														.addGroup(
																gl_contentPane
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(
																				gl_contentPane.createSequentialGroup().addComponent(btnOpretLastbil, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(btnSletLastbil, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
																		.addComponent(btnChaufførListe, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
																		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
																		.addGroup(
																				gl_contentPane.createSequentialGroup().addComponent(btnRedigerLastbil, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
																						.addPreferredGap(ComponentPlacement.RELATED)))
														.addGap(43)
														.addGroup(
																gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(btnSletRampe, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
																		.addComponent(btnRedigerRampe, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
																		.addComponent(btnOpretRampe, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
																		.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
																		.addComponent(lblRamper, Alignment.LEADING)).addGap(18)
														.addComponent(panel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)).addComponent(lblLastbiler)).addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblLastbiler).addComponent(lblRamper))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(Alignment.LEADING)
														.addComponent(panel, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane.createParallelGroup(Alignment.TRAILING)
																						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
																						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(Alignment.LEADING)
																						.addGroup(
																								gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnSletLastbil)
																										.addComponent(btnOpretRampe)).addComponent(btnOpretLastbil))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnRedigerLastbil).addComponent(btnRedigerRampe))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnChaufførListe).addComponent(btnSletRampe))))
										.addContainerGap()));

		lblLastbil = new JLabel(" ");
		Font temp = new Font(lblLastbil.getFont().getName(), Font.BOLD, lblLastbil.getFont().getSize());
		lblLastbil.setFont(temp);

		lblLastbilNummer = new JLabel(" ");

		lblLastbilchauffr = new JLabel(" ");

		lblLastbiltrailer = new JLabel(" ");

		lblTrailerMateriel = new JLabel(" ");

		lblLastbilvgt = new JLabel(" ");

		lblRampe = new JLabel(" ");
		lblRampe.setFont(temp);

		lblRampennummer = new JLabel(" ");

		lblRampestatus = new JLabel(" ");

		lblRampeTransportmateriel = new JLabel(" ");

		btnStatistik = new JButton("Statistik");
		btnStatistik.addActionListener(controller);

		// Auto-generated kode af Window-Builder
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblRampeTransportmateriel, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addComponent(lblRampestatus, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE).addComponent(lblRampennummer, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addComponent(lblRampe, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE).addComponent(lblLastbilvgt, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addComponent(lblTrailerMateriel, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addComponent(lblLastbiltrailer, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addComponent(lblLastbilchauffr, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE).addComponent(lblLastbilNummer, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addComponent(lblLastbil, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnStatistik, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_panel.createSequentialGroup().addComponent(btnStatistik).addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE).addComponent(lblLastbil)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLastbilNummer).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLastbilchauffr)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLastbiltrailer).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblTrailerMateriel)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLastbilvgt).addGap(18).addComponent(lblRampe).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblRampennummer).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblRampestatus).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblRampeTransportmateriel)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

		rampeliste = new JList();
		scrollPane_1.setViewportView(rampeliste);
		rampeliste.addListSelectionListener(controller);

		lastbilerListe = new JList();
		scrollPane.setViewportView(lastbilerListe);
		lastbilerListe.addListSelectionListener(controller);

		updateLastbilJlist();
		updateRampeJlist();
	}

	/**
	 * Opdatering af JList med lastbiler. Metoden kan kaldes af andre opbjekter
	 * når der oprettes en ny lastbil i createLastbil-dialogen.
	 */
	public void updateLastbilJlist() {
		lastbilerListe.setListData(Service.getInstance().getLastbiler().toArray());
	}

	/**
	 * Opdatering af JList med ramper. Metoden kan kaldes af andre objekter når
	 * der oprettes en ny rampe i createRame-dialogen.
	 */
	public void updateRampeJlist() {
		rampeliste.setListData(Service.getInstance().getRamper().toArray());
	}

	public void updateRampeLabels() {
		Rampe temp = (Rampe) rampeliste.getSelectedValue();
		lblRampe.setText("Rampe");
		lblRampennummer.setText("Nr: " + temp.getRampeNummer() + "");
		lblRampeTransportmateriel.setText("Transportmateriel: " + temp.getTransportmateriel().getType().toString());
		String statusString = "";
		if (!(temp.isStoppet()) == true) {
			statusString = "aktiv";
		} else {
			statusString = "inaktiv";
		}
		lblRampestatus.setText("Status: " + statusString);
	}

	private class Controller implements ActionListener, ListSelectionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnOpretLastbil)) {
				OpretLastbil opretLastbilDialog = new OpretLastbil(systemadministratorMain);
				opretLastbilDialog.setVisible(true);

			} else if (e.getSource().equals(btnRedigerLastbil)) {
				if (!lastbilerListe.isSelectionEmpty()) {
					RedigerLastbil redigerLastbil = new RedigerLastbil(systemadministratorMain, (Lastbil) lastbilerListe.getSelectedValue());
					redigerLastbil.setVisible(true);
				}
			} else if (e.getSource().equals(btnSletLastbil)) {
				if (!lastbilerListe.isSelectionEmpty()) {
					Lastbil temp = (Lastbil) lastbilerListe.getSelectedValue();
					if (temp.getDelordre() == null) {
						int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil slette denne lastbil?", "Advarsel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION) {
							Service.getInstance().removeLastbil(temp);
							updateLastbilJlist();
							notifyObservers();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Du kan ikke slette en lastbil som har en delordre tilknyttet!", "Fejl", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else if (e.getSource().equals(btnOpretRampe)) {
				OpretRampe opretRampe = new OpretRampe(systemadministratorMain);
				opretRampe.setVisible(true);
			} else if (e.getSource().equals(btnRedigerRampe)) {
				if (!rampeliste.isSelectionEmpty()) {
					Rampe rampe = (Rampe) rampeliste.getSelectedValue();
					if (rampe.getAflæsningskø().isEmpty() && rampe.getAflæsningIGang() == null) {
						RedigerRampe redigerRampe = new RedigerRampe(rampe, systemadministratorMain);
						redigerRampe.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Rampen er ikke tom for aflæsninger. Færdiggør aflæsingerne eller stop rampen før rampen kan ændres!", "Fejl",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else if (e.getSource().equals(btnSletRampe)) {
				if (!rampeliste.isSelectionEmpty()) {
					Rampe rampe = (Rampe) rampeliste.getSelectedValue();
					if (rampe.getAflæsningskø().isEmpty() && rampe.getAflæsningIGang() == null) {
						int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil slette denne rampe?", "Advarsel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION) {
							Service.getInstance().removeRampe(rampe);
							updateRampeJlist();
							notifyObservers();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Rampen er ikke tom for aflæsninger. Færdiggør aflæsingerne eller stop rampen før rampen kan slettes!", "Fejl",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (e.getSource().equals(btnChaufførListe)) {
					ChaufførDialog chaufførDialog = new ChaufførDialog();
					chaufførDialog.setVisible(true);
				} else if (e.getSource().equals(btnStatistik)) {
					Statistik statistik = new Statistik();
					statistik.setVisible(true);
				}
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource().equals(lastbilerListe)) {
				if (!(lastbilerListe.getSelectedValue() == null)) {
					Lastbil temp = (Lastbil) lastbilerListe.getSelectedValue();
					lblLastbil.setText("Lastbil");

					lblLastbilNummer.setText("Nr: " + temp.getNummer());
					lblLastbilchauffr.setText("Chauffør: " + temp.getChauffør().getNavn());
					lblLastbiltrailer.setText("Trailernr: " + temp.getTrailer().getTrailerNummer() + "");
					lblTrailerMateriel.setText("Transportmateriel: " + temp.getTrailer().getTransportmateriel().getType().toString());

					try {
						lblLastbilvgt.setText("Samlet vægt: " + temp.beregnSamletVægt() + "");
					} catch (Exception e0) {
						lblLastbilvgt.setText("Samlet vægt: Ikke angivet");
					}
				}
			}

			else if (e.getSource().equals(rampeliste)) {
				if (!(rampeliste.getSelectedValue() == null)) {
					updateRampeLabels();
				}
			}

		}
	}

	@Override
	public void update() {
		updateLastbilJlist();
		updateRampeJlist();
		if (rampeliste.getSelectedValue() != null) {
			updateRampeLabels();
		}
	}

	/**
	 * Metoden sørger for at alle observers kalder deres update-metode når en af
	 * JListerne opdateres.
	 */
	@Override
	public void notifyObservers() {

		for(Observer obv: observers){
			obv.update();
		}
	}
}
