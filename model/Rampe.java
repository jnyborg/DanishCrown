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
	private List<Aflæsning> aflæsningskø;
	private Transportmateriel transportmateriel;
	private Date køTid;
	private Aflæsning aflæsningIGang;
	private Date højPrioritetStart;
	private Set<Aflæsning> aflæsningshistorik;

	/**
	 * Initialiserer en rampe Krav: transportmateriel not null
	 */
	public Rampe(int rampeNummer, Transportmateriel transportmateriel) {
		this.rampeNummer = rampeNummer;
		this.transportmateriel = transportmateriel;
		aflæsningskø = new LinkedList<Aflæsning>();
		køTid = null;
		aflæsningIGang = null;
		this.stoppet = false;
		aflæsningshistorik = new HashSet<Aflæsning>();
	}

	public Date getKøTid() {
		return køTid;
	}
	public Set<Aflæsning> getRampeHistorik(){
		return new HashSet<Aflæsning>(aflæsningshistorik);
	}
	/**
	 * Begynder den næste aflæsning i køen. Den forreste aflæsning i køen tages ud af køen, 
	 * og gemmes i variablen aflæsningIGang. Er der en difference mellem forventetStart og aktuelStart, 
	 * opdateres kø tiden samt de forventede værdier for alle aflæsninger i køen. 
	 * Er der tale om en højprioritet, gemmes aktuelStart i variablen højPrioritetStart.
	 */
	public void begyndAflæsning(Date aktuelStart) {
		aflæsningIGang = ((LinkedList<Aflæsning>) aflæsningskø).poll();
		aflæsningIGang.setStatus(Aflæsningsstatus.PÅLÆSSES);
		
		if (!aflæsningIGang.isHøjPrioritet()) {
			long time = aktuelStart.getTime()-aflæsningIGang.getForventetStart().getTime();
			aflæsningIGang.setAktuelStart(aktuelStart);
			if(time!=0.0) {
				aflæsningIGang.setForventetSlut(new Date(aflæsningIGang.getForventetSlut().getTime()+time));
				for(Aflæsning aflæsning : aflæsningskø){
					aflæsning.setForventetStart(new Date(aflæsning.getForventetStart().getTime() + time));
					aflæsning.setForventetSlut(new Date(aflæsning.getForventetSlut().getTime() + time));
				}
			}
			køTid = new Date(køTid.getTime()+ time);
		} else {
			højPrioritetStart = aktuelStart;
			
		}

	}

	/**
	 * Afslutter aflæsningIGang. Er der en difference mellem forventetSlut og aktuelSlut, opdateres kø tiden
	 * samt forventede værdier for alle aflæsninger i køen. Flytter aflæsningIGang til historikken, og sætter 
	 * aflæsningIGang til null.
	 */
	public void afslutAflæsning(Date aktuelSlut) {
		aflæsningIGang.setStatus(Aflæsningsstatus.AFSLUTTET);
		if(!aflæsningskø.isEmpty()) {
			if (!aflæsningIGang.isHøjPrioritet()) {
				aflæsningIGang.setAktuelSlut(aktuelSlut);
				
				long time = aktuelSlut.getTime() -aflæsningIGang.getForventetSlut().getTime();
				if(time!=0.0) {
					for(Aflæsning aflæsning : aflæsningskø){
						aflæsning.setForventetStart(new Date(aflæsning.getForventetStart().getTime() + time));
						aflæsning.setForventetSlut(new Date(aflæsning.getForventetSlut().getTime() + time));
					}	
				}				
				køTid = new Date(køTid.getTime() + time);
				aflæsningshistorik.add(aflæsningIGang);
				
			} else {
				if(køTid != null){
					køTid = new Date(køTid.getTime() + (aktuelSlut.getTime()-højPrioritetStart.getTime()));
				}
			}
		} else {
			if(!aflæsningIGang.isHøjPrioritet()) {
				aflæsningIGang.setAktuelSlut(aktuelSlut);
				køTid = null;
			}
		}
		aflæsningIGang = null;
		aflæsningshistorik.add(aflæsningIGang);
	}

	/**
	 * Tilføjer en aflæsning til køen på rampen. Er køen tom, og er der ikke en aflæsning i gang,
	 * sættes køTid til ankomsten for lastbilen + læssetid. 
	 * Ellers opdateres den til den køTid + læssetid.
	 * Hvis aflæsningen er en højprioritets-aflæsning så sørger vi for at den kommer forrest i køen på rampen.
	 */
	public void addAflæsning(Aflæsning aflæsning) {
		if(aflæsning.isHøjPrioritet()){
			if (!aflæsningskø.isEmpty()) {
				int index = 0;
				boolean found = false;				
				while (index < aflæsningskø.size() && !found) {
					Aflæsning a = aflæsningskø.get(index);
					if (!a.isHøjPrioritet()) {
						System.out.println("add on"+rampeNummer);
						aflæsningskø.add(index,aflæsning);
						found=true;
					} else if (index == aflæsningskø.size()-1) {
						((LinkedList<Aflæsning>) aflæsningskø).add(index,aflæsning);
						found = true;
					}
					index++;
				}
			} else {
				((LinkedList<Aflæsning>) aflæsningskø).addFirst((aflæsning));		
			}
		}
		else{
			// læsseTid i minutter
			int læsseTid = aflæsning.getLastbil().getDelordre().getLæssetid() * 60000;
			Date ankomst = aflæsning.getAnkomst();
			if (aflæsningskø.isEmpty() && aflæsningIGang == null || køTid == null) {
				køTid = new Date(ankomst.getTime() + læsseTid);
				aflæsning.setForventetStart(ankomst);
				aflæsning.setForventetSlut(køTid);
			
			} else {
				aflæsning.setForventetStart(køTid);
				køTid = new Date(køTid.getTime() + læsseTid);	
				aflæsning.setForventetSlut(køTid);
			}
			((LinkedList<Aflæsning>) aflæsningskø).offer(aflæsning);
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
	public List<Aflæsning> getAflæsningskø() {
		return new LinkedList<Aflæsning>(aflæsningskø);
	}

	public Aflæsning getAflæsningIGang() {
		return aflæsningIGang;
	}

	public void setRampeNummer(int rampeNummer) {
		this.rampeNummer = rampeNummer;
	}

	public void setKøTid(Date køTid) {
		this.køTid = køTid;
	}
	
	public void clearAflæsningsKø(){
		aflæsningskø.clear();
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
	 * Sammenligner en rampe med en anden rampe på rampenummer.
	 */
	@Override
	public int compareTo(Rampe arg0) {
		return this.getRampeNummer() - arg0.getRampeNummer();
	}

}
