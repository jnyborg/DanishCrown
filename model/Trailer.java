package model;

/**
 * Modellerer en trailer.
 */
public class Trailer {
	private int trailerNummer;
	private int kapacitet;
	private double v�gtF�rKontrolvejning;
	private double v�gtEfterKontrolvejning;
	private Transportmateriel transportmateriel;
	private Lastbil lastbil;

	/**
	 * Initialiserer en trailer. 
	 * Krav: transportmateriel not null
	 * @param kapacitet	   		Angivet i m^2
	 * @param transportmateriel	Det transportmateriel, trailer kan k�re med.
	 * @param trailerNummer		Trailerens nummer.
	 */
	public Trailer(int kapacitet, Transportmateriel transportmateriel, int trailerNummer) {
		this.kapacitet = kapacitet;
		this.transportmateriel = transportmateriel;
		this.trailerNummer = trailerNummer;
	}

	public double getV�gtF�rKontrolvejning() {
		
		return v�gtF�rKontrolvejning;
	}

	public void setV�gtF�rKontrolvejning(double v�gtF�rKontrolvejning) {
		this.v�gtF�rKontrolvejning = v�gtF�rKontrolvejning;
		
	}

	public double getV�gtEfterKontrolvejning() {
		return v�gtEfterKontrolvejning;
	}

	public void setV�gtEfterKontrolvejning(double v�gtEfterKontrolvejning) {
		this.v�gtEfterKontrolvejning = v�gtEfterKontrolvejning;
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
	 * Kontrollere en trailers v�gt ved endt afl�sning. Beregner delordrens procentvise fejlmargen 
	 * efter forholdet mellem delordrens v�gt og ordrens bruttov�gt. P� denne m�de bestemmes en delordres 
	 * maks -og minimumsv�gt. Kontrolvejningen godkendes, hvis minimumsV�gt <= v�gtVedVejning >= maksimumsV�gt. 
	 * @param v�gtVedVejning	v�gten af fyldt traileren ved kontrolvejning. Angives i kg.
	 * @return 					om traileren er godkendt eller ej.
	 */
	public boolean kontrolvejning(int v�gtVedVejning){
		boolean godkendt = false;
		Double delordreV�gt = lastbil.getDelordre().getV�gt();
		int fejlMargen = lastbil.getDelordre().getOrdre().getFejlmargen();
		Double bruttoV�gt = lastbil.getDelordre().getOrdre().getBruttov�gt();
		double minimumsv�gt = delordreV�gt-(fejlMargen*delordreV�gt/bruttoV�gt)+v�gtF�rKontrolvejning;
		double maksimumsv�gt = delordreV�gt+(fejlMargen*delordreV�gt/bruttoV�gt)+v�gtF�rKontrolvejning;
		if(minimumsv�gt <= v�gtVedVejning &&  maksimumsv�gt >= v�gtVedVejning){
			godkendt = true;
			v�gtEfterKontrolvejning =  v�gtVedVejning;
		}
		return godkendt;
	}
}
