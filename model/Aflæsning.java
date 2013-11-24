package model;

import java.util.Date;


/**
 * Modellerer en afl�sning.
 */
public class Afl�sning implements Comparable<Afl�sning> {
	private int id;
	private Date forventetStart;
	private Date forventetSlut;
	private Date aktuelStart;
	private Date aktuelSlut;
	private Date ankomst;
	private Date afgang;
	private Date hviletid;
	private Afl�sningsstatus status;
	private boolean h�jPrioritet;
	private Lastbil lastbil;
	private Rampe rampe;

	/**
	 * Initialiserer en afl�sning. 
	 * Krav: Rampe og Lastbil not null
	 */
	public Afl�sning(Date ankomst, Date hviletid, Rampe rampe, Lastbil lastbil,
			int id) {
		this.ankomst = ankomst;
		this.hviletid = hviletid;
		this.rampe = rampe;
		this.lastbil = lastbil;
		status = Afl�sningsstatus.VENTER;
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isH�jPrioritet() {
		return h�jPrioritet;
	}

	public void setH�jPrioritet(boolean h�jPrioritet) {
		this.h�jPrioritet = h�jPrioritet;
	}

	public Date getHviletid() {
		return hviletid;
	}

	public void setHviletid(Date hviletid) {
		this.hviletid = hviletid;
	}

	public Date getForventetStart() {
		return forventetStart;
	}

	public void setForventetStart(Date forventetStart) {
		this.forventetStart = forventetStart;
	}

	public Date getForventetSlut() {
		return forventetSlut;
	}

	public void setForventetSlut(Date forventetSlut) {
		this.forventetSlut = forventetSlut;
	}

	public Date getAktuelStart() {
		return aktuelStart;
	}

	public void setAktuelStart(Date aktuelStart) {
		this.aktuelStart = aktuelStart;
	}

	public Date getAktuelSlut() {
		return aktuelSlut;
	}

	public void setAktuelSlut(Date aktuelSlut) {
		this.aktuelSlut = aktuelSlut;
	}

	public Date getAnkomst() {
		return ankomst;
	}

	public void setAnkomst(Date ankomst) {
		this.ankomst = ankomst;
	}

	public Date getAfgang() {
		return afgang;
	}

	public void setAfgang(Date afgang) {
		this.afgang = afgang;
	}

	public Afl�sningsstatus getStatus() {
		return status;
	}

	public void setStatus(Afl�sningsstatus status) {
		this.status = status;
	}

	public Lastbil getLastbil() {
		return lastbil;
	}

	public void setLastbil(Lastbil lastbil) {
		this.lastbil = lastbil;
	}

	public Rampe getRampe() {
		return rampe;
	}

	public void setRampe(Rampe rampe) {
		this.rampe = rampe;
	}

	public String toString() {
		return "Lastbil: " + lastbil.getNummer()
				+ " Afl�sning: " + id;
	}
	
	/**
	 * Sammenligner denne afl�sning med en anden afl�sning ved at k�re compareTo p� deres ankomst. 
	 */
	@Override
	public int compareTo(Afl�sning o) {
		return this.ankomst.compareTo(o.getAnkomst());
	}

}
