package model;

import java.util.Date;

/**
 * Modellerer en delordre.
 */
public class Delordre {
	private double v�gt;
	private double id;
	private String kundeNavn;
	private int l�ssetid;
	private Date l�ssedato;
	private Lastbil lastbil;
	private Ordre ordre;

	/**
	 * Initialiserer en delordre. 
	 * Krav: Lastbil og Ordre not null
	 * @param v�gt		Delordrens v�gt i kg
	 * @param id		Angivet i kommatal efter ordrens id
	 * @param kundeNavn	Kundens navn, eller firmanavn
	 * @param l�ssetid	Angives i minutter
	 * @param l�ssedato	Dato, ordren skal l�sses
	 * @param lastbil	Lastbil, der skal levere ordren
	 * @param ordre		Den ordre delordren stammer fra.
	 */
	public Delordre(int v�gt, double id, String kundeNavn, int l�ssetid,
			Date l�ssedato, Lastbil lastbil, Ordre ordre) {
		this.v�gt = v�gt;
		this.id = id;
		this.kundeNavn = kundeNavn;
		this.l�ssetid = l�ssetid;
		this.l�ssedato = l�ssedato;
		setLastbil(lastbil);
		this.ordre = ordre;
	}

	public double getV�gt() {
		return v�gt;
	}

	public void setV�gt(double v�gt) {
		this.v�gt = v�gt;
	}

	public double getId() {
		return this.id;
	}

	public String getKundeNavn() {
		return kundeNavn;
	}

	public void setKundeNavn(String kundeNavn) {
		this.kundeNavn = kundeNavn;
	}

	public int getL�ssetid() {
		return l�ssetid;
	}

	/**
	 * Registrerer l�ssetiden. Angives i minutter
	 */
	public void setL�ssetid(int l�ssetid) {
		this.l�ssetid = l�ssetid;
	}

	public Date getL�ssedato() {
		return l�ssedato;
	}

	public void setL�ssedato(Date l�ssedato) {
		this.l�ssedato = l�ssedato;
	}

	/**
	 * Returnerer lastbilen til denne delordre
	 */
	public Lastbil getLastbil() {
		return lastbil;
	}

	/**
	 * Registrerer lastbilen til denne delordre, samt linker denne delordre p� lastbilen. 
	 * Krav: Lastbil not null
	 */
	public void setLastbil(Lastbil lastbil) {
		if (this.lastbil != lastbil) {
			//Fjerner nuv�rende delordre p� lastbil
			if (this.lastbil != null) {
				this.lastbil.removeDelordre();
			}
			this.lastbil = lastbil;
			if (lastbil != null) {
				lastbil.setDelordre(this);
			}
		}

	}

	public Ordre getOrdre() {
		return ordre;
	}

	public void setOrdre(Ordre ordre) {
		if (this.ordre != ordre)
			this.ordre = ordre;
	}

}
