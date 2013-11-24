package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import service.Service;

import model.Afl�sning;
import model.Rampe;
import model.Transportmateriel;

/**
 * Skaber vinduet til statistik.
 */
public class Statistik extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTabbedPane tabbedPane;
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton enDag, enUge, enM�ned;
	private JLabel lblStartAfvigelse, lblSlutAfvigelse, lblTmAfvigelse, lblKasseSt�rrelse, lblKasseL�seTid, lblKasseTmAfvigelse, lblL�setid, lblSt�rrelse, lblRampeAntalAFl�sninger,
	lblRampeAfvegne, lblKarSt�rrelse, lblKarTmAfvigelse, lblKarL�seTid, lblAntalAflsninger, lblAntalAfvegneAflsninger, lblProcentAfvigelse, lblProcentAfvigelseVariabel;
	private JButton cancelButton, btnOpdater, btnOpdateKasse, btnKarUpdate;
	private Controller controller;
	private JScrollPane scrollPane;
	private JList rampeListe;

	/**
	 * Create the dialog.
	 */
	public Statistik() {
		this.setIconImage(MainFrame.logo);
		setModal(true);
		setTitle("Statistik");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		controller = new Controller();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
		);
		{
			JPanel tabKar = new JPanel();
			tabbedPane.addTab("Kar", null, tabKar, null);
			
			btnKarUpdate = new JButton("Opdater");
			btnKarUpdate.addActionListener(controller);
			
			lblKarSt�rrelse = new JLabel("");
			
			lblKarTmAfvigelse = new JLabel("");
			
			lblKarL�seTid = new JLabel("");
			GroupLayout gl_tabKar = new GroupLayout(tabKar);
			gl_tabKar.setHorizontalGroup(
				gl_tabKar.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabKar.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabKar.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_tabKar.createSequentialGroup()
								.addComponent(lblKarTmAfvigelse)
								.addPreferredGap(ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
								.addComponent(btnKarUpdate)
								.addGap(29))
							.addGroup(gl_tabKar.createSequentialGroup()
								.addComponent(lblKarSt�rrelse)
								.addContainerGap(434, Short.MAX_VALUE))
							.addGroup(gl_tabKar.createSequentialGroup()
								.addComponent(lblKarL�seTid)
								.addContainerGap(416, Short.MAX_VALUE))))
			);
			gl_tabKar.setVerticalGroup(
				gl_tabKar.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabKar.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabKar.createParallelGroup(Alignment.LEADING)
							.addComponent(btnKarUpdate)
							.addGroup(gl_tabKar.createSequentialGroup()
								.addComponent(lblKarSt�rrelse)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblKarTmAfvigelse)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblKarL�seTid)
						.addContainerGap(125, Short.MAX_VALUE))
			);
			tabKar.setLayout(gl_tabKar);
		}
		{
			JPanel tabKasse = new JPanel();
			tabbedPane.addTab("Kasse", null, tabKasse, null);
			
			btnOpdateKasse = new JButton("Opdater");
			btnOpdateKasse.addActionListener(controller);
			
			lblKasseSt�rrelse = new JLabel("");
			
			lblKasseTmAfvigelse = new JLabel("");
			
			lblKasseL�seTid = new JLabel("");
			GroupLayout gl_tabKasse = new GroupLayout(tabKasse);
			gl_tabKasse.setHorizontalGroup(
				gl_tabKasse.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_tabKasse.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabKasse.createParallelGroup(Alignment.LEADING)
							.addComponent(lblKasseSt�rrelse)
							.addGroup(gl_tabKasse.createSequentialGroup()
								.addComponent(lblKasseTmAfvigelse)
								.addPreferredGap(ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
								.addComponent(btnOpdateKasse)
								.addGap(29))
							.addGroup(gl_tabKasse.createSequentialGroup()
								.addComponent(lblKasseL�seTid)
								.addContainerGap(416, Short.MAX_VALUE))))
			);
			gl_tabKasse.setVerticalGroup(
				gl_tabKasse.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabKasse.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabKasse.createParallelGroup(Alignment.LEADING)
							.addComponent(btnOpdateKasse)
							.addGroup(gl_tabKasse.createSequentialGroup()
								.addComponent(lblKasseSt�rrelse)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblKasseTmAfvigelse)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblKasseL�seTid)
						.addContainerGap(125, Short.MAX_VALUE))
			);
			tabKasse.setLayout(gl_tabKasse);
		}
		{
			JPanel tabJuletr� = new JPanel();
			tabbedPane.addTab("Juletr\u00E6", null, tabJuletr�, null);
			
			lblL�setid = new JLabel("");
			
			lblTmAfvigelse = new JLabel("");
			
			lblSt�rrelse = new JLabel("");
			
			btnOpdater = new JButton("Opdater");
			btnOpdater.addActionListener(controller);
			
			GroupLayout gl_tabJuletr� = new GroupLayout(tabJuletr�);
			gl_tabJuletr�.setHorizontalGroup(
				gl_tabJuletr�.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabJuletr�.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabJuletr�.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_tabJuletr�.createSequentialGroup()
								.addGroup(gl_tabJuletr�.createParallelGroup(Alignment.LEADING)
									.addComponent(lblTmAfvigelse)
									.addComponent(lblSt�rrelse))
								.addPreferredGap(ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
								.addComponent(btnOpdater)
								.addGap(29))
							.addGroup(gl_tabJuletr�.createSequentialGroup()
								.addComponent(lblL�setid)
								.addContainerGap(416, Short.MAX_VALUE))))
			);
			gl_tabJuletr�.setVerticalGroup(
				gl_tabJuletr�.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabJuletr�.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabJuletr�.createParallelGroup(Alignment.LEADING)
							.addComponent(btnOpdater)
							.addGroup(gl_tabJuletr�.createSequentialGroup()
								.addComponent(lblSt�rrelse)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblTmAfvigelse)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblL�setid)
						.addContainerGap(125, Short.MAX_VALUE))
			);
			tabJuletr�.setLayout(gl_tabJuletr�);
		}
		{
			JPanel tabDato = new JPanel();
			tabbedPane.addTab("Dato", null, tabDato, null);
			JPanel panel = new JPanel();
			GroupLayout gl_tabDato = new GroupLayout(tabDato);
			gl_tabDato.setHorizontalGroup(
				gl_tabDato.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabDato.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE))
			);
			gl_tabDato.setVerticalGroup(
				gl_tabDato.createParallelGroup(Alignment.LEADING)
					.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
			);
			enDag = new JRadioButton("En dag");
			enDag.addActionListener(controller);
			enUge = new JRadioButton("En uge");
			enUge.addActionListener(controller);
			enM�ned = new JRadioButton("En m�ned");
			enM�ned.addActionListener(controller);
			bg.add(enDag);
			bg.add(enUge);
			bg.add(enM�ned);
			
			lblStartAfvigelse = new JLabel("");
			
			lblSlutAfvigelse = new JLabel("");
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(enDag)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(enUge)
								.addGap(2)
								.addComponent(enM�ned))
							.addComponent(lblStartAfvigelse)
							.addComponent(lblSlutAfvigelse))
						.addContainerGap(214, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(enDag)
							.addComponent(enUge)
							.addComponent(enM�ned))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblStartAfvigelse)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblSlutAfvigelse)
						.addContainerGap(147, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
			tabDato.setLayout(gl_tabDato);
		}
		{
			JPanel tabRampe = new JPanel();
			tabbedPane.addTab("Rampe", null, tabRampe, null);
			
			scrollPane = new JScrollPane();
			
			lblRampeAntalAFl�sninger = new JLabel("0");
			
			lblRampeAfvegne = new JLabel("0");
			
			lblAntalAflsninger = new JLabel("Antal afl\u00E6sninger i alt:");
			
			lblAntalAfvegneAflsninger = new JLabel("Antal afvegne afl\u00E6sninger:");
			
			lblProcentAfvigelse = new JLabel("Procent afvigelse:");
			
			lblProcentAfvigelseVariabel = new JLabel("0.0 %");
			
			GroupLayout gl_tabRampe = new GroupLayout(tabRampe);
			gl_tabRampe.setHorizontalGroup(
				gl_tabRampe.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabRampe.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_tabRampe.createParallelGroup(Alignment.LEADING)
							.addComponent(lblAntalAfvegneAflsninger)
							.addComponent(lblAntalAflsninger)
							.addComponent(lblProcentAfvigelse))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_tabRampe.createParallelGroup(Alignment.LEADING)
							.addComponent(lblRampeAntalAFl�sninger)
							.addComponent(lblRampeAfvegne)
							.addComponent(lblProcentAfvigelseVariabel))
						.addGap(82))
			);
			gl_tabRampe.setVerticalGroup(
				gl_tabRampe.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabRampe.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabRampe.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addGroup(gl_tabRampe.createSequentialGroup()
								.addGroup(gl_tabRampe.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAntalAflsninger)
									.addComponent(lblRampeAntalAFl�sninger))
								.addGap(10)
								.addGroup(gl_tabRampe.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAntalAfvegneAflsninger)
									.addComponent(lblRampeAfvegne))
								.addGap(11)
								.addGroup(gl_tabRampe.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblProcentAfvigelse)
									.addComponent(lblProcentAfvigelseVariabel))))
						.addContainerGap())
			);
			
			rampeListe = new JList(Service.getInstance().getRamper().toArray());
			scrollPane.setViewportView(rampeListe);
			rampeListe.addListSelectionListener(controller);
			tabRampe.setLayout(gl_tabRampe);
	
		}
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(controller);
			}
		}
	}
	private class Controller implements ActionListener, ListSelectionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(cancelButton)){
				dispose();
			}
			else if(arg0.getSource().equals(enDag)){
				lblStartAfvigelse.setText("Start afvigelse: " + Double.toString(udregnAfvigelseStart(1)) + " min");
				lblSlutAfvigelse.setText("Slut afvigelse: " + Double.toString(udregnAfvigelseSlut(1)) + " min");
			}
			else if(arg0.getSource().equals(enUge)){
				lblStartAfvigelse.setText("Start afvigelse: " + Double.toString(udregnAfvigelseStart(7)) + " min");
				lblSlutAfvigelse.setText("Slut afvigelse: " + Double.toString(udregnAfvigelseSlut(7)) + " min");
			}
			else if(arg0.getSource().equals(enM�ned)){
				lblStartAfvigelse.setText("Start afvigelse: " + Double.toString(udregnAfvigelseStart(30)) + " min");
				lblSlutAfvigelse.setText("Slut afvigelse: " + Double.toString(udregnAfvigelseSlut(30)) + " min");
			}
			else if(arg0.getSource().equals(btnOpdater)){
				Transportmateriel t = null;
				for(Transportmateriel tm: Service.getInstance().getTransportmaterialer()){
					if(tm.getType().name().toLowerCase().equals("juletr�"))
						t = tm;
				}
				if(t != null){
				lblL�setid.setText("L�setid i min: " + transportMaterielL�sningstid(t));
				lblTmAfvigelse.setText("Afvigelse i min: "+ transportMaterielAfvigelseStart(t));
				lblSt�rrelse.setText("St�rrelse i kg: " + transportMaterielDelOrdreSt�rrelse(t));
				}
			}
			else if(arg0.getSource().equals(btnOpdateKasse)){
				Transportmateriel t = null;
				for(Transportmateriel tm: Service.getInstance().getTransportmaterialer()){
					if(tm.getType().name().toLowerCase().equals("kasse"))
						t = tm;
				}
				if(t != null){
				lblKasseL�seTid.setText("L�setid i min: " + transportMaterielL�sningstid(t));
				lblKasseTmAfvigelse.setText("Afvigelse i min: "+ transportMaterielAfvigelseStart(t));
				lblKasseSt�rrelse.setText("St�rrelse i kg: " + transportMaterielDelOrdreSt�rrelse(t));
				}
			}
			else if(arg0.getSource().equals(btnKarUpdate)){
				Transportmateriel t = null;
				for(Transportmateriel tm: Service.getInstance().getTransportmaterialer()){
					if(tm.getType().name().toLowerCase().equals("kar"))
						t = tm;
				}
				if(t != null){
				lblKarL�seTid.setText("L�setid i min: " + transportMaterielL�sningstid(t));
				lblKarTmAfvigelse.setText("Afvigelse i min: "+ transportMaterielAfvigelseStart(t));
				lblKarSt�rrelse.setText("St�rrelse i kg: " + transportMaterielDelOrdreSt�rrelse(t));
				}
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource().equals(rampeListe)){
				if(rampeListe.getSelectedValue()!=null){		
					Rampe rampe = (Rampe) rampeListe.getSelectedValue();
					double count = 0;
					List<Afl�sning> afl�sninger = new ArrayList<>();
					for(Rampe ramper: Service.getInstance().getRamper()){
						afl�sninger.addAll(ramper.getRampeHistorik());
					}
					for(Afl�sning afl�sning: afl�sninger){
						if(afl�sning.getRampe().equals(rampe)){
							count++;
						}
					}
					lblRampeAntalAFl�sninger.setText(String.valueOf((int)count));
					double antalAfvegneAfl�sninger = rampeAntalAfvegneAfl�sninger(rampe);
					lblRampeAfvegne.setText(String.valueOf((int)antalAfvegneAfl�sninger));
					if(count != 0){ 
						//DecimalFormat s�rger for at der maks er 2 decimaler p� denne double
						DecimalFormat df = new DecimalFormat("#.##");
						df.format((antalAfvegneAfl�sninger / count)*100);
						lblProcentAfvigelseVariabel.setText(String.valueOf(df.format((antalAfvegneAfl�sninger / count)*100)) + " %");
					}
				}
			}	
		}
	}
	/**
	 * Metoden udregner den gennemsnitlige forskel p� afvigelsen getAktuelStart og getForventetStart. 
	 * Da forskellen er en negativ faktor uanset f�r eller efter vil afvigelsen altid blive medregnet
	 * @param dage Dage er et symbol for det interval der �nkes medregnet. Der er i en dag 86400000 ms.
	 * @return
	 */
	private double udregnAfvigelseStart(double dage){
		double afvigelse = 0;
		int count = 0;
		List<Afl�sning> afl�sninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			afl�sninger.addAll(ramper.getRampeHistorik());
		}
		for(int a = 0; a<afl�sninger.size() && (new Date().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetStart().getTime())/100000 < 864*dage; a++){
			if((afl�sninger.get(afl�sninger.size()-1-a).getAktuelStart().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetStart().getTime())/60000<0){
				afvigelse -= (afl�sninger.get(afl�sninger.size()-1-a).getAktuelStart().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetStart().getTime())/60000;
				}
				else {
					afvigelse += (afl�sninger.get(afl�sninger.size()-1-a).getAktuelStart().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetStart().getTime())/60000;

				}
			count++;
		}
		if(count != 0)
		return afvigelse/count;
		else
			return 0;
	}
	/**	 Metoden udregner den gennemsnitlige forskel p� afvigelsen getAktuelSlut og getForventetSlut. 
	 * Da forskellen er en negativ faktor uanset f�r eller efter vil afvigelsen altid blive medregnet
	 * @param dage Dage er et symbol for det interval der �nkes medregnet. Der er i en dag 86400000 ms.
	 * @return
	 */
	private double udregnAfvigelseSlut(double dage){
		double afvigelse = 0;
		int count = 0;
		List<Afl�sning> afl�sninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			afl�sninger.addAll(ramper.getRampeHistorik());
		}
		for(int a = 0; a<afl�sninger.size() && (new Date().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetSlut().getTime())/100000 < 864*dage; a++){
			if((afl�sninger.get(afl�sninger.size()-1-a).getAktuelSlut().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetSlut().getTime())/60000<0){
				afvigelse -= (afl�sninger.get(afl�sninger.size()-1-a).getAktuelSlut().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetSlut().getTime())/60000;
				}
				else {
					afvigelse += (afl�sninger.get(afl�sninger.size()-1-a).getAktuelSlut().getTime() - afl�sninger.get(afl�sninger.size()-1-a).getForventetSlut().getTime())/60000;

				}
			count++;
		}
		if(count != 0)
		return afvigelse/count;
		else
			return 0;
	}
	/**
	 * Metoden skal udregne den gennemsnitlige l�setid p� et konkret transportmateriel.
	 * @param tm 
	 * @return
	 */
	private double transportMaterielL�sningstid(Transportmateriel tm){
		double l�sningstid = 0;
		int count = 0;
		List<Afl�sning> afl�sninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			afl�sninger.addAll(ramper.getRampeHistorik());
		}
		for(Afl�sning ramp: afl�sninger){
			if(ramp.getRampe().getTransportmateriel().equals(tm)){
			
				l�sningstid += ramp.getAktuelSlut().getTime() - ramp.getAktuelStart().getTime();
				count++;
			}
			}
		if(count != 0)
		return (l�sningstid/count) / 60000;
		else 
			return 0;
	}
	/**
	 * Metoden skal udregne den konkrette afvigelse. Forholdene er n�rmere beskrevet i beskrivelsen af UdregnAfvigelseSlut
	 * @param tm
	 * @return
	 */
	private double transportMaterielAfvigelseStart(Transportmateriel tm){
		double afvigelse = 0;
		int count = 0;
		List<Afl�sning> afl�sninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			afl�sninger.addAll(ramper.getRampeHistorik());
		}
		for(Afl�sning af: afl�sninger){
			if(af.getRampe().getTransportmateriel().equals(tm)){
				if(af.getAktuelStart().getTime()-af.getForventetStart().getTime()<0){
					afvigelse -= af.getAktuelStart().getTime()-af.getForventetStart().getTime();
					}
					else {
						afvigelse += af.getAktuelStart().getTime() - af.getForventetStart().getTime();
						
					}
				count++;
			}
		}
		if(count != 0)
		return (afvigelse/count) / 60000;
		else
		return 0;
	}
	private double transportMaterielDelOrdreSt�rrelse(Transportmateriel tm){
		double st�rrelse = 0;
		int count = 0;
		List<Afl�sning> afl�sninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			afl�sninger.addAll(ramper.getRampeHistorik());
		}
		for(Afl�sning af: afl�sninger){
			if(af.getRampe().getTransportmateriel().equals(tm)){
				st�rrelse += af.getLastbil().getDelordre().getV�gt();
				count++;
			}
		}
		if(count != 0)
		return st�rrelse/count;
		else return 0;
	}
	
	private int rampeAntalAfvegneAfl�sninger(Rampe rampe){
		int count = 0;
		Set<Afl�sning> afl�sninger = rampe.getRampeHistorik();
		for(Afl�sning afl�sning: afl�sninger){
			if((!afl�sning.getForventetStart().equals(afl�sning.getAktuelStart()) || !afl�sning.getForventetSlut().equals(afl�sning.getAktuelSlut()))){
				count++;
			}
		}
		return count;	
	}
}

