package model;

import java.util.Date;

/**
 * Modellerer en lastbil.
 */
public class Lastbil {

	private String nummerPlade;
	private Delordre delordre;
	private Trailer trailer;
	private Chauff�r chauff�r;
	private Afl�sning afl�sning;
	private double f�rerhusV�gt;
	private static int trailerNummer, afl�sningId = 0;
	private boolean ankommet;

	/**
	 * Initialiserer en lastbil. 
	 * Krav: Chauff�r og Transportmateriel not null
	 */
	public Lastbil(String nummer, Chauff�r c, Transportmateriel t, int kapacitet, double f�rerhusV�gt) {
		this.nummerPlade = nummer;
		this.chauff�r = c;
		this.afl�sning = null;
		createTrailer(kapacitet, t);
		this.f�rerhusV�gt = f�rerhusV�gt;
		this.ankommet = false;
		this.delordre = null;
	}

	/**
	 * Beregner den samlede v�gt af en l�sset lastbil. 
	 * Krav: Trailer er kontrolvejet.
	 */
	public double beregnSamletV�gt() {
		if(trailer!=null) {
			return this.f�rerhusV�gt+this.trailer.getV�gtEfterKontrolvejning();
		}
		return this.f�rerhusV�gt;
	}

	/**
	 * Initialiserer en afl�sning linket til denne lastbil.
	 * Krav: Rampe not null
	 * @return Den afl�sning, der er oprettet.
	 */
	public Afl�sning createAfl�sning(Date ankomst, Date hviletid, Rampe rampe) {
		afl�sningId++;
		Afl�sning afl�sning = new Afl�sning(ankomst, hviletid, rampe, this, afl�sningId);
		this.afl�sning = afl�sning;
		rampe.addAfl�sning(afl�sning);
		return afl�sning;
	}

	public void removeAfl�sning() {
		this.afl�sning = null;
	}

	public Afl�sning getAfl�sning() {
		return this.afl�sning;
	}

	public Chauff�r getChauff�r() {
		return chauff�r;
	}

	/**
	 * Registrerer chauff�r. 
	 * Krav: Chauff�r not null
	 */
	public void setChauff�r(Chauff�r chauff�r) {
		if (this.chauff�r != chauff�r)
			this.chauff�r = chauff�r;
	}

	public String getNummer() {
		return nummerPlade;
	}

	public void setNummer(String nummer) {
		this.nummerPlade = nummer;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	/**
	 * Initialiserer en trailer.
	 * @return Den trailer, der oprettes. 
	 */
	public Trailer createTrailer(int kapacitet,Transportmateriel transportmateriel) {
		trailerNummer++;
		Trailer t = new Trailer(kapacitet, transportmateriel, trailerNummer);
		this.trailer = t;
		t.setLastbil(this);
		return t;
	}
	
	public void updateTrailer(int kapacitet, Transportmateriel transportmateriel){
		trailer.setKapacitet(kapacitet);
		trailer.setTransportmateriel(transportmateriel);
	}
	
	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public double getF�rerhusV�gt() {
		return f�rerhusV�gt;
	}

	public void setF�rerhusV�gt(double f�rerhusV�gt) {
		this.f�rerhusV�gt = f�rerhusV�gt;
	}

	@Override
	public String toString() {
		return "Nr: " + getNummer() + ", " + getChauff�r();
	}

	public Delordre getDelordre() {
		return this.delordre;
	}

	/**
	 * Linker lastbil til delordre, og linker samtidig delordren til denne
	 * lastbil
	 */
	public void setDelordre(Delordre delordre) {
		this.delordre = delordre;
		
	}

	/**
	 * Fjerner delordren. Bem�rk: Fjerner ikke linket til denne lastbil fra delordre, idet det er �nsket
	 * at man kan genbruge en lastbil p� en ny delordre, samtidig med at vi kan lagre en historik over delordrene
	 * leveret af denne lastbil. 
	 */
	public void removeDelordre() {
		this.delordre = null;

	}
	
	public boolean isAnkommet() {
		return this.ankommet;
	}
	
	public void setAnkommet(boolean ankommet) {
		this.ankommet = ankommet;
	}
}
