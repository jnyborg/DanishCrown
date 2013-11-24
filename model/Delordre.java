package model;

import java.util.Date;

/**
 * Modellerer en delordre.
 */
public class Delordre {
	private double vægt;
	private double id;
	private String kundeNavn;
	private int læssetid;
	private Date læssedato;
	private Lastbil lastbil;
	private Ordre ordre;

	/**
	 * Initialiserer en delordre. 
	 * Krav: Lastbil og Ordre not null
	 * @param vægt		Delordrens vægt i kg
	 * @param id		Angivet i kommatal efter ordrens id
	 * @param kundeNavn	Kundens navn, eller firmanavn
	 * @param læssetid	Angives i minutter
	 * @param læssedato	Dato, ordren skal læsses
	 * @param lastbil	Lastbil, der skal levere ordren
	 * @param ordre		Den ordre delordren stammer fra.
	 */
	public Delordre(int vægt, double id, String kundeNavn, int læssetid,
			Date læssedato, Lastbil lastbil, Ordre ordre) {
		this.vægt = vægt;
		this.id = id;
		this.kundeNavn = kundeNavn;
		this.læssetid = læssetid;
		this.læssedato = læssedato;
		setLastbil(lastbil);
		this.ordre = ordre;
	}

	public double getVægt() {
		return vægt;
	}

	public void setVægt(double vægt) {
		this.vægt = vægt;
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

	public int getLæssetid() {
		return læssetid;
	}

	/**
	 * Registrerer læssetiden. Angives i minutter
	 */
	public void setLæssetid(int læssetid) {
		this.læssetid = læssetid;
	}

	public Date getLæssedato() {
		return læssedato;
	}

	public void setLæssedato(Date læssedato) {
		this.læssedato = læssedato;
	}

	/**
	 * Returnerer lastbilen til denne delordre
	 */
	public Lastbil getLastbil() {
		return lastbil;
	}

	/**
	 * Registrerer lastbilen til denne delordre, samt linker denne delordre på lastbilen. 
	 * Krav: Lastbil not null
	 */
	public void setLastbil(Lastbil lastbil) {
		if (this.lastbil != lastbil) {
			//Fjerner nuværende delordre på lastbil
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
