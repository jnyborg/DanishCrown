package model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Modellerer en ordre.
 */
public class Ordre {
	private double bruttov�gt;
	private int ordreNummer;
	private String kundeNavn;
	private int fejlmargen;
	private Transportmateriel transportmateriel;
	private Set<Delordre> delordrer;
	
	/**
	 * Initialiserer en ordre, samt tilh�rende delordre. Data om delordre sendes fra kunden i HashMaps som parametre. HashMaps passer sammen, s�ledes at en delordre skal 
	 * oprettes med data fra alle HashMaps med samme key
	 */
	public Ordre(int bruttov�gt, int ordreNummer, String kundeNavn, int fejlmagen, Transportmateriel t,
			HashMap<Integer, Lastbil> mapLastbil,
			HashMap<Integer, Integer> mapV�gt,
			HashMap<Integer, Integer> mapL�ssetid, 
			HashMap<Integer, Date> mapL�ssedato) {
		this.bruttov�gt = bruttov�gt;
		this.ordreNummer = ordreNummer;
		this.kundeNavn = kundeNavn;
		this.fejlmargen = fejlmagen;
		this.transportmateriel = t;
		delordrer = new HashSet<Delordre>();
		createDelordrer(mapLastbil, mapV�gt, mapL�ssetid, mapL�ssedato);

	}

	/**
	 * Returnerer en ny delordre
	 */
	public Delordre createDelordre(Lastbil lastbil, double delordreNummer, int v�gt, int l�ssetid, Date l�ssedato) {
		return new Delordre(v�gt,delordreNummer,this.kundeNavn,l�ssetid,l�ssedato,lastbil,this);
	}
	
	/**
	 * Opretter delordre ud fra values med samme key i fire HashMaps. 
	 * Krav: HashMaps har keys der passer sammen. 
	 */
	public void createDelordrer(HashMap<Integer, Lastbil> mapLastbil,
			HashMap<Integer, Integer> mapV�gt,
			HashMap<Integer, Integer> mapL�ssetid, 
			HashMap<Integer, Date> mapL�ssedato) {
		/*
		 * Bestemmer den faktor som delordrenes id skal s�ttes efter. Hvis der fx skal oprettes 10 delordre, 
		 * skal deres id s�ttes til ordens id + .01, .02, .03 ..., eller 100, s� ordrens id + .001, .002 osv
		 */
		double factor = potens(10,String.valueOf(mapLastbil.size()).length());
		
		for(int key = 0; key<=mapLastbil.size()-1; key++) {
			Lastbil lastbil = mapLastbil.get(key);
			//key + 1 s� vi starter p� 1
			double delordreNummer = (key+1)/factor+ordreNummer;
			int v�gt = mapV�gt.get(key);
			int l�ssetid = mapL�ssetid.get(key);
			Date l�ssedato = mapL�ssedato.get(key);
			
			Delordre d1 = createDelordre(lastbil, delordreNummer, v�gt, l�ssetid, l�ssedato);
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
 	public double getBruttov�gt() {
		return bruttov�gt;
	}

	public void setBruttov�gt(double bruttov�gt) {
		this.bruttov�gt = bruttov�gt;
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
