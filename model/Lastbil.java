package model;

import java.util.Date;

/**
 * Modellerer en lastbil.
 */
public class Lastbil {

	private String nummerPlade;
	private Delordre delordre;
	private Trailer trailer;
	private Chauffør chauffør;
	private Aflæsning aflæsning;
	private double førerhusVægt;
	private static int trailerNummer, aflæsningId = 0;
	private boolean ankommet;

	/**
	 * Initialiserer en lastbil. 
	 * Krav: Chauffør og Transportmateriel not null
	 */
	public Lastbil(String nummer, Chauffør c, Transportmateriel t, int kapacitet, double førerhusVægt) {
		this.nummerPlade = nummer;
		this.chauffør = c;
		this.aflæsning = null;
		createTrailer(kapacitet, t);
		this.førerhusVægt = førerhusVægt;
		this.ankommet = false;
		this.delordre = null;
	}

	/**
	 * Beregner den samlede vægt af en læsset lastbil. 
	 * Krav: Trailer er kontrolvejet.
	 */
	public double beregnSamletVægt() {
		if(trailer!=null) {
			return this.førerhusVægt+this.trailer.getVægtEfterKontrolvejning();
		}
		return this.førerhusVægt;
	}

	/**
	 * Initialiserer en aflæsning linket til denne lastbil.
	 * Krav: Rampe not null
	 * @return Den aflæsning, der er oprettet.
	 */
	public Aflæsning createAflæsning(Date ankomst, Date hviletid, Rampe rampe) {
		aflæsningId++;
		Aflæsning aflæsning = new Aflæsning(ankomst, hviletid, rampe, this, aflæsningId);
		this.aflæsning = aflæsning;
		rampe.addAflæsning(aflæsning);
		return aflæsning;
	}

	public void removeAflæsning() {
		this.aflæsning = null;
	}

	public Aflæsning getAflæsning() {
		return this.aflæsning;
	}

	public Chauffør getChauffør() {
		return chauffør;
	}

	/**
	 * Registrerer chauffør. 
	 * Krav: Chauffør not null
	 */
	public void setChauffør(Chauffør chauffør) {
		if (this.chauffør != chauffør)
			this.chauffør = chauffør;
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

	public double getFørerhusVægt() {
		return førerhusVægt;
	}

	public void setFørerhusVægt(double førerhusVægt) {
		this.førerhusVægt = førerhusVægt;
	}

	@Override
	public String toString() {
		return "Nr: " + getNummer() + ", " + getChauffør();
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
	 * Fjerner delordren. Bemærk: Fjerner ikke linket til denne lastbil fra delordre, idet det er ønsket
	 * at man kan genbruge en lastbil på en ny delordre, samtidig med at vi kan lagre en historik over delordrene
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
