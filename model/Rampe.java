package model;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Modellerer en rampe.
 */
public class Rampe implements Comparable<Rampe> {

	private int rampeNummer;
	private boolean stoppet;
	private List<Afl�sning> afl�sningsk�;
	private Transportmateriel transportmateriel;
	private Date k�Tid;
	private Afl�sning afl�sningIGang;
	private Date h�jPrioritetStart;
	private Set<Afl�sning> afl�sningshistorik;

	/**
	 * Initialiserer en rampe Krav: transportmateriel not null
	 */
	public Rampe(int rampeNummer, Transportmateriel transportmateriel) {
		this.rampeNummer = rampeNummer;
		this.transportmateriel = transportmateriel;
		afl�sningsk� = new LinkedList<Afl�sning>();
		k�Tid = null;
		afl�sningIGang = null;
		this.stoppet = false;
		afl�sningshistorik = new HashSet<Afl�sning>();
	}

	public Date getK�Tid() {
		return k�Tid;
	}
	public Set<Afl�sning> getRampeHistorik(){
		return new HashSet<Afl�sning>(afl�sningshistorik);
	}
	/**
	 * Begynder den n�ste afl�sning i k�en. Den forreste afl�sning i k�en tages ud af k�en, 
	 * og gemmes i variablen afl�sningIGang. Er der en difference mellem forventetStart og aktuelStart, 
	 * opdateres k� tiden samt de forventede v�rdier for alle afl�sninger i k�en. 
	 * Er der tale om en h�jprioritet, gemmes aktuelStart i variablen h�jPrioritetStart.
	 */
	public void begyndAfl�sning(Date aktuelStart) {
		afl�sningIGang = ((LinkedList<Afl�sning>) afl�sningsk�).poll();
		afl�sningIGang.setStatus(Afl�sningsstatus.P�L�SSES);
		
		if (!afl�sningIGang.isH�jPrioritet()) {
			long time = aktuelStart.getTime()-afl�sningIGang.getForventetStart().getTime();
			afl�sningIGang.setAktuelStart(aktuelStart);
			if(time!=0.0) {
				afl�sningIGang.setForventetSlut(new Date(afl�sningIGang.getForventetSlut().getTime()+time));
				for(Afl�sning afl�sning : afl�sningsk�){
					afl�sning.setForventetStart(new Date(afl�sning.getForventetStart().getTime() + time));
					afl�sning.setForventetSlut(new Date(afl�sning.getForventetSlut().getTime() + time));
				}
			}
			k�Tid = new Date(k�Tid.getTime()+ time);
		} else {
			h�jPrioritetStart = aktuelStart;
			
		}

	}

	/**
	 * Afslutter afl�sningIGang. Er der en difference mellem forventetSlut og aktuelSlut, opdateres k� tiden
	 * samt forventede v�rdier for alle afl�sninger i k�en. Flytter afl�sningIGang til historikken, og s�tter 
	 * afl�sningIGang til null.
	 */
	public void afslutAfl�sning(Date aktuelSlut) {
		afl�sningIGang.setStatus(Afl�sningsstatus.AFSLUTTET);
		if(!afl�sningsk�.isEmpty()) {
			if (!afl�sningIGang.isH�jPrioritet()) {
				afl�sningIGang.setAktuelSlut(aktuelSlut);
				
				long time = aktuelSlut.getTime() -afl�sningIGang.getForventetSlut().getTime();
				if(time!=0.0) {
					for(Afl�sning afl�sning : afl�sningsk�){
						afl�sning.setForventetStart(new Date(afl�sning.getForventetStart().getTime() + time));
						afl�sning.setForventetSlut(new Date(afl�sning.getForventetSlut().getTime() + time));
					}	
				}				
				k�Tid = new Date(k�Tid.getTime() + time);
				afl�sningshistorik.add(afl�sningIGang);
				
			} else {
				if(k�Tid != null){
					k�Tid = new Date(k�Tid.getTime() + (aktuelSlut.getTime()-h�jPrioritetStart.getTime()));
				}
			}
		} else {
			if(!afl�sningIGang.isH�jPrioritet()) {
				afl�sningIGang.setAktuelSlut(aktuelSlut);
				k�Tid = null;
			}
		}
		afl�sningIGang = null;
		afl�sningshistorik.add(afl�sningIGang);
	}

	/**
	 * Tilf�jer en afl�sning til k�en p� rampen. Er k�en tom, og er der ikke en afl�sning i gang,
	 * s�ttes k�Tid til ankomsten for lastbilen + l�ssetid. 
	 * Ellers opdateres den til den k�Tid + l�ssetid.
	 * Hvis afl�sningen er en h�jprioritets-afl�sning s� s�rger vi for at den kommer forrest i k�en p� rampen.
	 */
	public void addAfl�sning(Afl�sning afl�sning) {
		if(afl�sning.isH�jPrioritet()){
			if (!afl�sningsk�.isEmpty()) {
				int index = 0;
				boolean found = false;				
				while (index < afl�sningsk�.size() && !found) {
					Afl�sning a = afl�sningsk�.get(index);
					if (!a.isH�jPrioritet()) {
						System.out.println("add on"+rampeNummer);
						afl�sningsk�.add(index,afl�sning);
						found=true;
					} else if (index == afl�sningsk�.size()-1) {
						((LinkedList<Afl�sning>) afl�sningsk�).add(index,afl�sning);
						found = true;
					}
					index++;
				}
			} else {
				((LinkedList<Afl�sning>) afl�sningsk�).addFirst((afl�sning));		
			}
		}
		else{
			// l�sseTid i minutter
			int l�sseTid = afl�sning.getLastbil().getDelordre().getL�ssetid() * 60000;
			Date ankomst = afl�sning.getAnkomst();
			if (afl�sningsk�.isEmpty() && afl�sningIGang == null || k�Tid == null) {
				k�Tid = new Date(ankomst.getTime() + l�sseTid);
				afl�sning.setForventetStart(ankomst);
				afl�sning.setForventetSlut(k�Tid);
			
			} else {
				afl�sning.setForventetStart(k�Tid);
				k�Tid = new Date(k�Tid.getTime() + l�sseTid);	
				afl�sning.setForventetSlut(k�Tid);
			}
			((LinkedList<Afl�sning>) afl�sningsk�).offer(afl�sning);
		}
	}

	public int getRampeNummer() {

		return rampeNummer;
	}

	public boolean isStoppet() {
		return stoppet;
	}

	public void setStoppet(boolean status) {
		this.stoppet = status;
	}

	public Transportmateriel getTransportmateriel() {
		return transportmateriel;
	}

	/**
	 * Setter transportmateriel. 
	 * Krav: transportmateriel not null
	 */
	public void setTransportmateriel(Transportmateriel transportmateriel) {
		if (this.transportmateriel != transportmateriel) {
			this.transportmateriel = transportmateriel;
		}
	}
	public List<Afl�sning> getAfl�sningsk�() {
		return new LinkedList<Afl�sning>(afl�sningsk�);
	}

	public Afl�sning getAfl�sningIGang() {
		return afl�sningIGang;
	}

	public void setRampeNummer(int rampeNummer) {
		this.rampeNummer = rampeNummer;
	}

	public void setK�Tid(Date k�Tid) {
		this.k�Tid = k�Tid;
	}
	
	public void clearAfl�sningsK�(){
		afl�sningsk�.clear();
	}

	@Override
	public String toString() {
		String statusString = "";
		if (stoppet == true) {
			statusString = "inaktiv";
		} else {
			statusString = "aktiv";
		}

		return "Rampe: " + rampeNummer + ", status: " + statusString;

	}
	
	/**
	 * Sammenligner en rampe med en anden rampe p� rampenummer.
	 */
	@Override
	public int compareTo(Rampe arg0) {
		return this.getRampeNummer() - arg0.getRampeNummer();
	}

}
