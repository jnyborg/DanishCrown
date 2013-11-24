package model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Modellerer en ordre.
 */
public class Ordre {
	private double bruttovægt;
	private int ordreNummer;
	private String kundeNavn;
	private int fejlmargen;
	private Transportmateriel transportmateriel;
	private Set<Delordre> delordrer;
	
	/**
	 * Initialiserer en ordre, samt tilhørende delordre. Data om delordre sendes fra kunden i HashMaps som parametre. HashMaps passer sammen, således at en delordre skal 
	 * oprettes med data fra alle HashMaps med samme key
	 */
	public Ordre(int bruttovægt, int ordreNummer, String kundeNavn, int fejlmagen, Transportmateriel t,
			HashMap<Integer, Lastbil> mapLastbil,
			HashMap<Integer, Integer> mapVægt,
			HashMap<Integer, Integer> mapLæssetid, 
			HashMap<Integer, Date> mapLæssedato) {
		this.bruttovægt = bruttovægt;
		this.ordreNummer = ordreNummer;
		this.kundeNavn = kundeNavn;
		this.fejlmargen = fejlmagen;
		this.transportmateriel = t;
		delordrer = new HashSet<Delordre>();
		createDelordrer(mapLastbil, mapVægt, mapLæssetid, mapLæssedato);

	}

	/**
	 * Returnerer en ny delordre
	 */
	public Delordre createDelordre(Lastbil lastbil, double delordreNummer, int vægt, int læssetid, Date læssedato) {
		return new Delordre(vægt,delordreNummer,this.kundeNavn,læssetid,læssedato,lastbil,this);
	}
	
	/**
	 * Opretter delordre ud fra values med samme key i fire HashMaps. 
	 * Krav: HashMaps har keys der passer sammen. 
	 */
	public void createDelordrer(HashMap<Integer, Lastbil> mapLastbil,
			HashMap<Integer, Integer> mapVægt,
			HashMap<Integer, Integer> mapLæssetid, 
			HashMap<Integer, Date> mapLæssedato) {
		/*
		 * Bestemmer den faktor som delordrenes id skal sættes efter. Hvis der fx skal oprettes 10 delordre, 
		 * skal deres id sættes til ordens id + .01, .02, .03 ..., eller 100, så ordrens id + .001, .002 osv
		 */
		double factor = potens(10,String.valueOf(mapLastbil.size()).length());
		
		for(int key = 0; key<=mapLastbil.size()-1; key++) {
			Lastbil lastbil = mapLastbil.get(key);
			//key + 1 så vi starter på 1
			double delordreNummer = (key+1)/factor+ordreNummer;
			int vægt = mapVægt.get(key);
			int læssetid = mapLæssetid.get(key);
			Date læssedato = mapLæssedato.get(key);
			
			Delordre d1 = createDelordre(lastbil, delordreNummer, vægt, læssetid, læssedato);
			delordrer.add(d1);
		}
	}
	
	/**
	 * Rekursiv metode til beregning af potens
	 * Krav: b er positiv eller nul.
	 * @param a roden
	 * @param b eksponenten
	 * @return resultatet af potensen
	 */
	public int potens(int a, int b) {
		if(b==0) {
			return 1;
		}
		int result = a * potens(a,b-1);
		return result;
	}

	public Set<Delordre> getDelordre(){
		return new HashSet<Delordre>(delordrer);
	}
 	public double getBruttovægt() {
		return bruttovægt;
	}

	public void setBruttovægt(double bruttovægt) {
		this.bruttovægt = bruttovægt;
	}

	public int getOrdreNummer() {
		return ordreNummer;
	}

	public void setOrdreNummer(int ordreNummer) {
		this.ordreNummer = ordreNummer;
	}

	public String getKundeNavn() {
		return kundeNavn;
	}

	public void setKundeNavn(String kundeNavn) {
		this.kundeNavn = kundeNavn;
	}

	public int getFejlmargen() {
		return fejlmargen;
	}

	public void setFejlmargen(int fejlmargen) {
		this.fejlmargen = fejlmargen;
	}

	public Transportmateriel getTransportmateriel() {
		return transportmateriel;
	}
	
	public void setTransportmateriel(Transportmateriel transportmateriel) {
		if (this.transportmateriel != transportmateriel)
			this.transportmateriel = transportmateriel;
	}

}
