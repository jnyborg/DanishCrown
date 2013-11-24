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

import model.Aflæsning;
import model.Rampe;
import model.Transportmateriel;

/**
 * Skaber vinduet til statistik.
 */
public class Statistik extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTabbedPane tabbedPane;
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton enDag, enUge, enMåned;
	private JLabel lblStartAfvigelse, lblSlutAfvigelse, lblTmAfvigelse, lblKasseStørrelse, lblKasseLæseTid, lblKasseTmAfvigelse, lblLæsetid, lblStørrelse, lblRampeAntalAFlæsninger,
	lblRampeAfvegne, lblKarStørrelse, lblKarTmAfvigelse, lblKarLæseTid, lblAntalAflsninger, lblAntalAfvegneAflsninger, lblProcentAfvigelse, lblProcentAfvigelseVariabel;
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
			
			lblKarStørrelse = new JLabel("");
			
			lblKarTmAfvigelse = new JLabel("");
			
			lblKarLæseTid = new JLabel("");
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
								.addComponent(lblKarStørrelse)
								.addContainerGap(434, Short.MAX_VALUE))
							.addGroup(gl_tabKar.createSequentialGroup()
								.addComponent(lblKarLæseTid)
								.addContainerGap(416, Short.MAX_VALUE))))
			);
			gl_tabKar.setVerticalGroup(
				gl_tabKar.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabKar.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabKar.createParallelGroup(Alignment.LEADING)
							.addComponent(btnKarUpdate)
							.addGroup(gl_tabKar.createSequentialGroup()
								.addComponent(lblKarStørrelse)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblKarTmAfvigelse)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblKarLæseTid)
						.addContainerGap(125, Short.MAX_VALUE))
			);
			tabKar.setLayout(gl_tabKar);
		}
		{
			JPanel tabKasse = new JPanel();
			tabbedPane.addTab("Kasse", null, tabKasse, null);
			
			btnOpdateKasse = new JButton("Opdater");
			btnOpdateKasse.addActionListener(controller);
			
			lblKasseStørrelse = new JLabel("");
			
			lblKasseTmAfvigelse = new JLabel("");
			
			lblKasseLæseTid = new JLabel("");
			GroupLayout gl_tabKasse = new GroupLayout(tabKasse);
			gl_tabKasse.setHorizontalGroup(
				gl_tabKasse.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_tabKasse.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabKasse.createParallelGroup(Alignment.LEADING)
							.addComponent(lblKasseStørrelse)
							.addGroup(gl_tabKasse.createSequentialGroup()
								.addComponent(lblKasseTmAfvigelse)
								.addPreferredGap(ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
								.addComponent(btnOpdateKasse)
								.addGap(29))
							.addGroup(gl_tabKasse.createSequentialGroup()
								.addComponent(lblKasseLæseTid)
								.addContainerGap(416, Short.MAX_VALUE))))
			);
			gl_tabKasse.setVerticalGroup(
				gl_tabKasse.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabKasse.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabKasse.createParallelGroup(Alignment.LEADING)
							.addComponent(btnOpdateKasse)
							.addGroup(gl_tabKasse.createSequentialGroup()
								.addComponent(lblKasseStørrelse)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblKasseTmAfvigelse)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblKasseLæseTid)
						.addContainerGap(125, Short.MAX_VALUE))
			);
			tabKasse.setLayout(gl_tabKasse);
		}
		{
			JPanel tabJuletræ = new JPanel();
			tabbedPane.addTab("Juletr\u00E6", null, tabJuletræ, null);
			
			lblLæsetid = new JLabel("");
			
			lblTmAfvigelse = new JLabel("");
			
			lblStørrelse = new JLabel("");
			
			btnOpdater = new JButton("Opdater");
			btnOpdater.addActionListener(controller);
			
			GroupLayout gl_tabJuletræ = new GroupLayout(tabJuletræ);
			gl_tabJuletræ.setHorizontalGroup(
				gl_tabJuletræ.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabJuletræ.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabJuletræ.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_tabJuletræ.createSequentialGroup()
								.addGroup(gl_tabJuletræ.createParallelGroup(Alignment.LEADING)
									.addComponent(lblTmAfvigelse)
									.addComponent(lblStørrelse))
								.addPreferredGap(ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
								.addComponent(btnOpdater)
								.addGap(29))
							.addGroup(gl_tabJuletræ.createSequentialGroup()
								.addComponent(lblLæsetid)
								.addContainerGap(416, Short.MAX_VALUE))))
			);
			gl_tabJuletræ.setVerticalGroup(
				gl_tabJuletræ.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_tabJuletræ.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_tabJuletræ.createParallelGroup(Alignment.LEADING)
							.addComponent(btnOpdater)
							.addGroup(gl_tabJuletræ.createSequentialGroup()
								.addComponent(lblStørrelse)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblTmAfvigelse)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblLæsetid)
						.addContainerGap(125, Short.MAX_VALUE))
			);
			tabJuletræ.setLayout(gl_tabJuletræ);
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
			enMåned = new JRadioButton("En måned");
			enMåned.addActionListener(controller);
			bg.add(enDag);
			bg.add(enUge);
			bg.add(enMåned);
			
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
								.addComponent(enMåned))
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
							.addComponent(enMåned))
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
			
			lblRampeAntalAFlæsninger = new JLabel("0");
			
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
							.addComponent(lblRampeAntalAFlæsninger)
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
									.addComponent(lblRampeAntalAFlæsninger))
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
			else if(arg0.getSource().equals(enMåned)){
				lblStartAfvigelse.setText("Start afvigelse: " + Double.toString(udregnAfvigelseStart(30)) + " min");
				lblSlutAfvigelse.setText("Slut afvigelse: " + Double.toString(udregnAfvigelseSlut(30)) + " min");
			}
			else if(arg0.getSource().equals(btnOpdater)){
				Transportmateriel t = null;
				for(Transportmateriel tm: Service.getInstance().getTransportmaterialer()){
					if(tm.getType().name().toLowerCase().equals("juletræ"))
						t = tm;
				}
				if(t != null){
				lblLæsetid.setText("Læsetid i min: " + transportMaterielLæsningstid(t));
				lblTmAfvigelse.setText("Afvigelse i min: "+ transportMaterielAfvigelseStart(t));
				lblStørrelse.setText("Størrelse i kg: " + transportMaterielDelOrdreStørrelse(t));
				}
			}
			else if(arg0.getSource().equals(btnOpdateKasse)){
				Transportmateriel t = null;
				for(Transportmateriel tm: Service.getInstance().getTransportmaterialer()){
					if(tm.getType().name().toLowerCase().equals("kasse"))
						t = tm;
				}
				if(t != null){
				lblKasseLæseTid.setText("Læsetid i min: " + transportMaterielLæsningstid(t));
				lblKasseTmAfvigelse.setText("Afvigelse i min: "+ transportMaterielAfvigelseStart(t));
				lblKasseStørrelse.setText("Størrelse i kg: " + transportMaterielDelOrdreStørrelse(t));
				}
			}
			else if(arg0.getSource().equals(btnKarUpdate)){
				Transportmateriel t = null;
				for(Transportmateriel tm: Service.getInstance().getTransportmaterialer()){
					if(tm.getType().name().toLowerCase().equals("kar"))
						t = tm;
				}
				if(t != null){
				lblKarLæseTid.setText("Læsetid i min: " + transportMaterielLæsningstid(t));
				lblKarTmAfvigelse.setText("Afvigelse i min: "+ transportMaterielAfvigelseStart(t));
				lblKarStørrelse.setText("Størrelse i kg: " + transportMaterielDelOrdreStørrelse(t));
				}
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource().equals(rampeListe)){
				if(rampeListe.getSelectedValue()!=null){		
					Rampe rampe = (Rampe) rampeListe.getSelectedValue();
					double count = 0;
					List<Aflæsning> aflæsninger = new ArrayList<>();
					for(Rampe ramper: Service.getInstance().getRamper()){
						aflæsninger.addAll(ramper.getRampeHistorik());
					}
					for(Aflæsning aflæsning: aflæsninger){
						if(aflæsning.getRampe().equals(rampe)){
							count++;
						}
					}
					lblRampeAntalAFlæsninger.setText(String.valueOf((int)count));
					double antalAfvegneAflæsninger = rampeAntalAfvegneAflæsninger(rampe);
					lblRampeAfvegne.setText(String.valueOf((int)antalAfvegneAflæsninger));
					if(count != 0){ 
						//DecimalFormat sørger for at der maks er 2 decimaler på denne double
						DecimalFormat df = new DecimalFormat("#.##");
						df.format((antalAfvegneAflæsninger / count)*100);
						lblProcentAfvigelseVariabel.setText(String.valueOf(df.format((antalAfvegneAflæsninger / count)*100)) + " %");
					}
				}
			}	
		}
	}
	/**
	 * Metoden udregner den gennemsnitlige forskel på afvigelsen getAktuelStart og getForventetStart. 
	 * Da forskellen er en negativ faktor uanset før eller efter vil afvigelsen altid blive medregnet
	 * @param dage Dage er et symbol for det interval der ønkes medregnet. Der er i en dag 86400000 ms.
	 * @return
	 */
	private double udregnAfvigelseStart(double dage){
		double afvigelse = 0;
		int count = 0;
		List<Aflæsning> aflæsninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			aflæsninger.addAll(ramper.getRampeHistorik());
		}
		for(int a = 0; a<aflæsninger.size() && (new Date().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetStart().getTime())/100000 < 864*dage; a++){
			if((aflæsninger.get(aflæsninger.size()-1-a).getAktuelStart().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetStart().getTime())/60000<0){
				afvigelse -= (aflæsninger.get(aflæsninger.size()-1-a).getAktuelStart().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetStart().getTime())/60000;
				}
				else {
					afvigelse += (aflæsninger.get(aflæsninger.size()-1-a).getAktuelStart().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetStart().getTime())/60000;

				}
			count++;
		}
		if(count != 0)
		return afvigelse/count;
		else
			return 0;
	}
	/**	 Metoden udregner den gennemsnitlige forskel på afvigelsen getAktuelSlut og getForventetSlut. 
	 * Da forskellen er en negativ faktor uanset før eller efter vil afvigelsen altid blive medregnet
	 * @param dage Dage er et symbol for det interval der ønkes medregnet. Der er i en dag 86400000 ms.
	 * @return
	 */
	private double udregnAfvigelseSlut(double dage){
		double afvigelse = 0;
		int count = 0;
		List<Aflæsning> aflæsninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			aflæsninger.addAll(ramper.getRampeHistorik());
		}
		for(int a = 0; a<aflæsninger.size() && (new Date().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetSlut().getTime())/100000 < 864*dage; a++){
			if((aflæsninger.get(aflæsninger.size()-1-a).getAktuelSlut().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetSlut().getTime())/60000<0){
				afvigelse -= (aflæsninger.get(aflæsninger.size()-1-a).getAktuelSlut().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetSlut().getTime())/60000;
				}
				else {
					afvigelse += (aflæsninger.get(aflæsninger.size()-1-a).getAktuelSlut().getTime() - aflæsninger.get(aflæsninger.size()-1-a).getForventetSlut().getTime())/60000;

				}
			count++;
		}
		if(count != 0)
		return afvigelse/count;
		else
			return 0;
	}
	/**
	 * Metoden skal udregne den gennemsnitlige læsetid på et konkret transportmateriel.
	 * @param tm 
	 * @return
	 */
	private double transportMaterielLæsningstid(Transportmateriel tm){
		double læsningstid = 0;
		int count = 0;
		List<Aflæsning> aflæsninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			aflæsninger.addAll(ramper.getRampeHistorik());
		}
		for(Aflæsning ramp: aflæsninger){
			if(ramp.getRampe().getTransportmateriel().equals(tm)){
			
				læsningstid += ramp.getAktuelSlut().getTime() - ramp.getAktuelStart().getTime();
				count++;
			}
			}
		if(count != 0)
		return (læsningstid/count) / 60000;
		else 
			return 0;
	}
	/**
	 * Metoden skal udregne den konkrette afvigelse. Forholdene er nærmere beskrevet i beskrivelsen af UdregnAfvigelseSlut
	 * @param tm
	 * @return
	 */
	private double transportMaterielAfvigelseStart(Transportmateriel tm){
		double afvigelse = 0;
		int count = 0;
		List<Aflæsning> aflæsninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			aflæsninger.addAll(ramper.getRampeHistorik());
		}
		for(Aflæsning af: aflæsninger){
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
	private double transportMaterielDelOrdreStørrelse(Transportmateriel tm){
		double størrelse = 0;
		int count = 0;
		List<Aflæsning> aflæsninger = new ArrayList<>();
		for(Rampe ramper: Service.getInstance().getRamper()){
			aflæsninger.addAll(ramper.getRampeHistorik());
		}
		for(Aflæsning af: aflæsninger){
			if(af.getRampe().getTransportmateriel().equals(tm)){
				størrelse += af.getLastbil().getDelordre().getVægt();
				count++;
			}
		}
		if(count != 0)
		return størrelse/count;
		else return 0;
	}
	
	private int rampeAntalAfvegneAflæsninger(Rampe rampe){
		int count = 0;
		Set<Aflæsning> aflæsninger = rampe.getRampeHistorik();
		for(Aflæsning aflæsning: aflæsninger){
			if((!aflæsning.getForventetStart().equals(aflæsning.getAktuelStart()) || !aflæsning.getForventetSlut().equals(aflæsning.getAktuelSlut()))){
				count++;
			}
		}
		return count;	
	}
}

