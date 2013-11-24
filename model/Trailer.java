package model;

/**
 * Modellerer en trailer.
 */
public class Trailer {
	private int trailerNummer;
	private int kapacitet;
	private double vægtFørKontrolvejning;
	private double vægtEfterKontrolvejning;
	private Transportmateriel transportmateriel;
	private Lastbil lastbil;

	/**
	 * Initialiserer en trailer. 
	 * Krav: transportmateriel not null
	 * @param kapacitet	   		Angivet i m^2
	 * @param transportmateriel	Det transportmateriel, trailer kan køre med.
	 * @param trailerNummer		Trailerens nummer.
	 */
	public Trailer(int kapacitet, Transportmateriel transportmateriel, int trailerNummer) {
		this.kapacitet = kapacitet;
		this.transportmateriel = transportmateriel;
		this.trailerNummer = trailerNummer;
	}

	public double getVægtFørKontrolvejning() {
		
		return vægtFørKontrolvejning;
	}

	public void setVægtFørKontrolvejning(double vægtFørKontrolvejning) {
		this.vægtFørKontrolvejning = vægtFørKontrolvejning;
		
	}

	public double getVægtEfterKontrolvejning() {
		return vægtEfterKontrolvejning;
	}

	public void setVægtEfterKontrolvejning(double vægtEfterKontrolvejning) {
		this.vægtEfterKontrolvejning = vægtEfterKontrolvejning;
	}

	public int getTrailerNummer() {
		return trailerNummer;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public Transportmateriel getTransportmateriel() {
		return transportmateriel;
	}

	/**
	 * Registrerer transportmateriel til denne trailer. Krav: transportmateriel not null
	 * 
	 * @param transportmateriel
	 */
	public void setTransportmateriel(Transportmateriel transportmateriel) {
		if (this.transportmateriel != transportmateriel) {
			this.transportmateriel = transportmateriel;
		}
	}

	public Lastbil getLastbil() {
		return lastbil;
	}

	public void setLastbil(Lastbil lastbil) {
		if (this.lastbil != lastbil)
			this.lastbil = lastbil;
	}

	@Override
	public String toString() {
		return "Nr: " + trailerNummer + " - " + transportmateriel.getType().toString();
	}

	/**
	 * Kontrollere en trailers vægt ved endt aflæsning. Beregner delordrens procentvise fejlmargen 
	 * efter forholdet mellem delordrens vægt og ordrens bruttovægt. På denne måde bestemmes en delordres 
	 * maks -og minimumsvægt. Kontrolvejningen godkendes, hvis minimumsVægt <= vægtVedVejning >= maksimumsVægt. 
	 * @param vægtVedVejning	vægten af fyldt traileren ved kontrolvejning. Angives i kg.
	 * @return 					om traileren er godkendt eller ej.
	 */
	public boolean kontrolvejning(int vægtVedVejning){
		boolean godkendt = false;
		Double delordreVægt = lastbil.getDelordre().getVægt();
		int fejlMargen = lastbil.getDelordre().getOrdre().getFejlmargen();
		Double bruttoVægt = lastbil.getDelordre().getOrdre().getBruttovægt();
		double minimumsvægt = delordreVægt-(fejlMargen*delordreVægt/bruttoVægt)+vægtFørKontrolvejning;
		double maksimumsvægt = delordreVægt+(fejlMargen*delordreVægt/bruttoVægt)+vægtFørKontrolvejning;
		if(minimumsvægt <= vægtVedVejning &&  maksimumsvægt >= vægtVedVejning){
			godkendt = true;
			vægtEfterKontrolvejning =  vægtVedVejning;
		}
		return godkendt;
	}
}
