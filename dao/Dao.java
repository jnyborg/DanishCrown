package dao;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import model.Chauff�r;
import model.Delordre;
import model.Lastbil;
import model.Ordre;
import model.Rampe;
import model.Transportmateriel;

/**
 * Modellerer Dao.
 */
public class Dao {
	private static Dao uniqueInstance;
	private Set<Chauff�r> chauff�rer;
	private Set<Delordre> delordrer;
	private Set<Lastbil> lastbiler;
	private Set<Ordre> ordrer;
	private Set<Rampe> ramper;
	private Set<Transportmateriel> transportmaterialer;
	public int rampeNummerJuletr�;
	public int rampeNummerKar;
	public int rampeNummerKasse;
	public int ordreNummer;

	/**
	 * Initialiserer Dao. Privat constructor til Singleton pattern.
	 */
	private Dao() {
		chauff�rer = new HashSet<Chauff�r>();
		delordrer = new HashSet<Delordre>();
		lastbiler = new HashSet<Lastbil>();
		ordrer = new HashSet<Ordre>();
		ramper = new TreeSet<Rampe>();
		transportmaterialer = new HashSet<Transportmateriel>();
		this.rampeNummerJuletr� = 100;
		this.rampeNummerKar = 200;
		this.rampeNummerKasse = 300;
		this.ordreNummer = 0;
	}
	
	/**
	 * Returnerer singleton instans af Service klassen.
	 */
	public static Dao getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Dao();
		}
		return uniqueInstance;
	}

	public int getRampeNummerJuletr�() {
		return rampeNummerJuletr�;
	}

	public int getRampeNummerKar() {
		return rampeNummerKar;
	}

	public int getRampeNummerKasse() {
		return rampeNummerKasse;
	}
	public void setRampeNummerJuletr�Auto(){
		rampeNummerJuletr�++;
	}
	public void setRampeNummerKarAuto(){
		rampeNummerKar++;
	}
	public void setRampeNummerKasseAuto(){
		rampeNummerKasse++;
	}
	
	public void addChauff�r(Chauff�r chauff�r) {
		if(!chauff�rer.contains(chauff�r))
			chauff�rer.add(chauff�r);
	}

	public void removeChauff�r(Chauff�r chauff�r) {
		if(chauff�rer.contains(chauff�r)) {
			chauff�rer.remove(chauff�r);
		}
	}
	
	public void updateChauff�r(Chauff�r chauff�r){
		if(chauff�rer.contains(chauff�r)) {
			chauff�rer.add(chauff�r);
		}
	}

	public HashSet<Chauff�r> getChauff�r() {
		return new HashSet<Chauff�r>(chauff�rer);
	}

	public void addDelordre(Delordre delordre) {
		if(!delordrer.contains(delordre)){
			delordrer.add(delordre);
		}
	}

	public void removeDelordre(Delordre delordre) {
		if(delordrer.contains(delordre)) {
			delordrer.remove(delordre);
		}
	}

	public HashSet<Delordre> getDelordrer() {
		return new HashSet<Delordre>(delordrer);
	}

	public void addLastbil(Lastbil lastbil) {
		if(!lastbiler.contains(lastbil)){
			lastbiler.add(lastbil);
		}
	}

	public void removeLastbil(Lastbil lastbil) {
		if(lastbiler.contains(lastbil)) {
			lastbiler.remove(lastbil);
		}
	}
	
	public void updateLastbil(Lastbil lastbil)	{
		if(lastbiler.contains(lastbil)) {
			lastbiler.add(lastbil);
		}
	}

	public HashSet<Lastbil> getLastbiler() {
		return new HashSet<Lastbil>(lastbiler);
	}

	public void addTransportmateriel(Transportmateriel transportmateriel) {
		if(!transportmaterialer.contains(transportmateriel)){
			transportmaterialer.add(transportmateriel);
		}
	}

	public void removeTransportmateriel(Transportmateriel transportmateriel) {
		if(transportmaterialer.contains(transportmateriel)) {
			transportmaterialer.remove(transportmateriel);
		}
	}

	public HashSet<Transportmateriel> getTransportmaterialer() {
		return new HashSet<Transportmateriel>(transportmaterialer);
	}

	public void addOrdre(Ordre ordre) {
		if(!ordrer.contains(ordre)){
			ordrer.add(ordre);
		}
	}

	public void removeOrdre(Ordre ordre) {
		if(ordrer.contains(ordre)) {
			ordrer.remove(ordre);
		}
	}

	public HashSet<Ordre> getOrdrer() {
		return new HashSet<Ordre>(ordrer);
	}

	public void addRampe(Rampe rampe) {
		if(!ramper.contains(rampe)){
			ramper.add(rampe);
		}
	}

	public void removeRampe(Rampe rampe) {
		if(ramper.contains(rampe)) {
			ramper.remove(rampe);
		}
		
	}
	
	public void updateRampe(Rampe rampe) {
		if(ramper.contains(rampe)) {
			ramper.remove(rampe);
			ramper.add(rampe);
		}
	}

	public Set<Rampe> getRamper() {
		return new TreeSet<Rampe>(ramper);
	}
	/**
	 * Skal bruges til JPA for at gemme vores rampeNumre
	 */
	public void updateRampeNumre(){
			for(Rampe ramp: Dao.getInstance().getRamper()){
				if(ramp.getTransportmateriel().getType().name().toLowerCase().equals("juletr�")){
					rampeNummerJuletr� = ramp.getRampeNummer();
				}
				else if(ramp.getTransportmateriel().getType().name().toLowerCase().equals("kar")){
					rampeNummerKar = ramp.getRampeNummer();
				}
				else{
					rampeNummerKasse = ramp.getRampeNummer();
				}	
			}
	}
}
