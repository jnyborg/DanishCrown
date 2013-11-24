package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Aflæsningsstatus;
import model.Lastbil;
import service.Service;

/**
 * Skaber brugergrænsefladen til anvendelse af chauffører ved portvagten.
 */
public class ChaufførMain extends JDialog implements Observer, Subject {

	private final JPanel contentPanel = new JPanel();
	private JTextFieldHint txtSg;
	private JLabel lblChauffør, lblNavn, lblMobilnr, lblLastbil, lblNummerplade, lblTrailer, lblMateriel, lblOrdre, lblOrdreNavn, lblVgt, lblVgt_1, lblRampe;
	private JButton btnRegistrerAnkomst, btnVejTrailer, btnKontrolvejning, btnRegistrerAfgang, btnSøg;
	private Controller controller = new Controller();
	private JList<Lastbil> list;
	private ChaufførMain chaufførMain;

	/**
	 * Create the dialog.
	 */
	public ChaufførMain() {
		this.chaufførMain = this;
		setModal(false);
		this.setIconImage(MainFrame.logo);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Chauffør");
		setBounds(100, 100, 644, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		observers.add(this);
		
		txtSg = new JTextFieldHint(txtSg, null, "Søg på navn, nummer");
		txtSg.setColumns(10);
		//ActionListener til enterknap
		txtSg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSøg.doClick();
			}
		});

		btnSøg = new JButton("Søg");
		btnSøg.addActionListener(controller);

		lblChauffør = new JLabel("Chauffør");
		lblChauffør.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblNavn = new JLabel("Navn:");

		lblMobilnr = new JLabel("Tlf. :");

		lblLastbil = new JLabel("Lastbil");
		lblLastbil.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblNummerplade = new JLabel("Nr.plade:");

		lblTrailer = new JLabel("Trailer");
		lblTrailer.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblMateriel = new JLabel("Materiel:");

		lblOrdre = new JLabel("Ordre");
		lblOrdre.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblOrdreNavn = new JLabel("Kunde: ");

		lblVgt = new JLabel("Vægt: ");

		btnRegistrerAnkomst = new JButton("Registrer Ankomst");
		btnRegistrerAnkomst.setBackground(UIManager
				.getColor("Button.background"));
		btnRegistrerAnkomst.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegistrerAnkomst.addActionListener(controller);

		list = new JList<Lastbil>();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.addListSelectionListener(controller);

		updateList();

		btnRegistrerAfgang = new JButton("Registrer Afgang");
		btnRegistrerAfgang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegistrerAfgang.setBackground(UIManager
				.getColor("Button.background"));
		btnRegistrerAfgang.addActionListener(controller);

		btnVejTrailer = new JButton("Vej Trailer");
		btnVejTrailer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVejTrailer.setBackground(UIManager.getColor("Button.background"));
		btnVejTrailer.addActionListener(controller);

		btnKontrolvejning = new JButton("Kontrolvejning");
		btnKontrolvejning.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnKontrolvejning
				.setBackground(UIManager.getColor("Button.background"));
		btnKontrolvejning.addActionListener(controller);
		
		lblVgt_1 = new JLabel("V\u00E6gt:");
		
		lblRampe = new JLabel("Rampenummer:");

		//Auto-generated kode af Window-Builder
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnRegistrerAnkomst, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegistrerAfgang, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(txtSg, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSøg))
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnVejTrailer, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnKontrolvejning, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(25)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblLastbil)
										.addComponent(lblTrailer)
										.addComponent(lblChauffør)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(16)
											.addComponent(lblNummerplade, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblOrdre)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(19)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblVgt_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
												.addComponent(lblMateriel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblRampe, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(46)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblMobilnr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNavn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblVgt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(10))
								.addComponent(lblOrdreNavn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtSg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSøg))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblChauffør)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNavn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMobilnr)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLastbil)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNummerplade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTrailer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMateriel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVgt_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRampe)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblOrdre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOrdreNavn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVgt)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegistrerAnkomst, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRegistrerAfgang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVejTrailer, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnKontrolvejning, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		btnRegistrerAnkomst.setEnabled(false);
		btnRegistrerAfgang.setEnabled(false);
		btnVejTrailer.setEnabled(false);
		btnKontrolvejning.setEnabled(false);
	}
	/**
	 * Opdaterer GUI med alle de lastbiler der har et link til en delordre.
	 */
	public void updateList() {
		HashSet<Lastbil> temp = Service.getInstance().getLastbiler();
		HashSet<Lastbil> result = new HashSet<Lastbil>();
		for(Lastbil l : temp) {
			if(l.getDelordre()!=null) {
				result.add(l);
			}
		}
		list.setListData(result.toArray(new Lastbil[0]));
	}
	
	/**
	 * Opdaterer labels ud fra den valgte lastbil i listen
	 * @param l
	 */
	public void updateData(Lastbil l) {
		lblMateriel.setText("Materiel: "
				+ l.getTrailer().getTransportmateriel().getType()
						.toString());
		lblMobilnr.setText("Tlf: "
				+ l.getChauffør().getMobilNummer());
		lblNavn.setText("Navn: " + l.getChauffør().getNavn());
		lblNummerplade.setText("Nr.plade: " + l.getNummer());
		lblOrdreNavn.setText(l.getDelordre().getKundeNavn());
		lblVgt.setText("Trailer skal læsses med " + l.getDelordre().getVægt() + " kg");
		if(l.isAnkommet()) {
			lblRampe.setText("Rampenummer: " + l.getAflæsning().getRampe().getRampeNummer());
		} else {
			lblRampe.setText("Rampenummer: Ikke tildelt");
		}
		if(l.getTrailer().getVægtFørKontrolvejning()!=0) {
			lblVgt_1.setText("Vægt: " + l.getTrailer().getVægtFørKontrolvejning()+ " kg");
			
					
		} else {
			lblVgt_1.setText("Vægt: Uvejet");
		}
	}
	
	/**
	 * Denne metode opdaterer knapperne i vinduet, og sikrer at chaufføreren udfører use-cases i den rigtige rækkefølge.
	 * @param l
	 */
	public void updateButtons(Lastbil l) {
		if(l!=null) {
			if(!l.isAnkommet()) {
				btnKontrolvejning.setEnabled(false);
				btnRegistrerAfgang.setEnabled(false);
				btnRegistrerAnkomst.setEnabled(true);
				btnVejTrailer.setEnabled(false);
			} else if(l.getTrailer().getVægtFørKontrolvejning() == 0.0) {
				btnKontrolvejning.setEnabled(false);
				btnRegistrerAfgang.setEnabled(false);
				btnRegistrerAnkomst.setEnabled(false);
				btnVejTrailer.setEnabled(true);
			} else if(l.getAflæsning().getStatus() == Aflæsningsstatus.PÅLÆSSES || l.getAflæsning().getStatus() == Aflæsningsstatus.VENTER) {
				btnKontrolvejning.setEnabled(false);
				btnRegistrerAfgang.setEnabled(false);
				btnRegistrerAnkomst.setEnabled(false);
				btnVejTrailer.setEnabled(false);
			} else if(l.getTrailer().getVægtEfterKontrolvejning() == 0.0){
				btnKontrolvejning.setEnabled(true);
				btnRegistrerAfgang.setEnabled(false);
				btnRegistrerAnkomst.setEnabled(false);
				btnVejTrailer.setEnabled(false);
			} else {
				btnKontrolvejning.setEnabled(false);
				btnRegistrerAfgang.setEnabled(true);
				btnRegistrerAnkomst.setEnabled(false);
				btnVejTrailer.setEnabled(false);
			}
		} else {
			btnKontrolvejning.setEnabled(false);
			btnRegistrerAfgang.setEnabled(false);
			btnRegistrerAnkomst.setEnabled(false);
			btnVejTrailer.setEnabled(false);
		}
	}
	/**
	 * Override af dispose(). Da alle gui-vinduer er både Observer og Subject skal de fjernes fra listen af observers når vinduet lukkes
	 */
	@Override
	public void dispose() {
		SystemadministratorMain.observers.remove(this);
		super.dispose();
	}
	public class Controller implements ActionListener, ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource() == list) {
				if (list.getSelectedValue() != null) {
					Lastbil l = (Lastbil) list.getSelectedValue();
					updateData(l);
					updateButtons(l);
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSøg) {
				updateList();
				Set<Lastbil> søgLastbiler = new HashSet<Lastbil>();
				String nySøg = txtSg.getText().replaceAll("\\s", "").toLowerCase();
				for (Lastbil a : Service.getInstance().getLastbiler()) {
					//søg efter navn
					boolean found = false;
					String chaufførNavn = a.getChauffør().getNavn()
							.replaceAll("\\s", "").toLowerCase();
					int søgL = nySøg.length();
					int i = 0;
					while (!found && i <= chaufførNavn.length() - søgL) {
						if (chaufførNavn.substring(i, i + søgL).equals(nySøg)) {
							søgLastbiler.add(a);
							found = true;
						}
						i++;
					}
					//søg efter nummerplade
					found = false;
					String nummerPlade = a.getNummer().replaceAll("\\s", "")
							.toLowerCase();
					i = 0;
					while (!found && i <= nummerPlade.length() - søgL) {
						if (nummerPlade.substring(i, i + søgL).equals(nySøg)) {
							søgLastbiler.add(a);
							found = true;
						}
						i++;
					}
				}
				list.setListData(søgLastbiler.toArray(new Lastbil[0]));
			}

			if (e.getSource() == btnRegistrerAfgang) {
				if (list.getSelectedValue() != null) {
					Lastbil l = (Lastbil) list.getSelectedValue();
					if (l.isAnkommet()) {
						OpretDato opretDato = new OpretDato();
						Date result = opretDato.showDialog();
						if(result!=null) {
							Service.getInstance().registrerAfgang(l,result);
							JOptionPane.showMessageDialog(null,"Afgang registreret.");
							updateList();
							updateButtons(null);
							notifyObservers();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Registrer først ankomst!", "Fejl",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			if (e.getSource() == btnRegistrerAnkomst) {
				if (list.getSelectedValue() != null) {
					Lastbil l = (Lastbil) list.getSelectedValue();
					if (!l.isAnkommet()) {
						IndtastHviletid frame = new IndtastHviletid(l,
								chaufførMain);
						frame.setModal(true);
						frame.setVisible(true);
						notifyObservers();
					}
				}
			}

			if (e.getSource() == btnKontrolvejning) {
				if (list.getSelectedValue() != null) {
					Lastbil l = (Lastbil) list.getSelectedValue();

					IndtastKontrolvejningVægt frame = new IndtastKontrolvejningVægt();
					frame.setFrame(chaufførMain);
					frame.setLastbil(l);
					frame.setModal(true);
					frame.setVisible(true);
					updateButtons(l);
					notifyObservers();
				}
			}

			if (e.getSource() == btnVejTrailer) {
				if (list.getSelectedValue() != null) {
					Lastbil l = (Lastbil) list.getSelectedValue();

					IndtastVægt frame = new IndtastVægt();
					frame.setLastbil(l);
					frame.setFrame(chaufførMain);
					frame.setModal(true);
					frame.setVisible(true);
					updateButtons(l);
					notifyObservers();
				}
			}
		}
	}

	@Override
	public void update() {
		int index = list.getSelectedIndex();
		updateList();
		list.setSelectedIndex(index);
		if(list.getSelectedValue() != null){
			updateButtons(list.getSelectedValue());
		}
	}
	@Override
	public void notifyObservers() {
		for(Observer obv: observers){
			obv.update();
		}
	}
}
