package service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.Afl�sning;
import model.Afl�sningsstatus;
import model.Chauff�r;
import model.Lastbil;
import model.Ordre;
import model.Rampe;
import model.Trailer;
import model.Transportmateriel;
import model.Transportmateriel.MaterielType;
import dao.Dao;

/**
 * Modellerer Service.
 */
public class Service {


	private static Service uniqueInstance;
	
	/**
	 * Initialiserer Service. Privat constructor til Singleton pattern.
	 */
	private Service() {

	}
	
	/**
	 * Returnerer singleton instans af Service klassen.
	 */
	public static Service getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Service();
		}
		return uniqueInstance;
	}

	public Chauff�r createChauff�r(String navn, String mobilNummer) {
		Chauff�r c = new Chauff�r(navn, mobilNummer);
		Dao.getInstance().addChauff�r(c);
		return c;
	}
	
	public void updateChauff�r(Chauff�r chauff�r){
		Dao.getInstance().updateChauff�r(chauff�r);
	}

	public Lastbil createLastbil(String nummer, Chauff�r c,
			Transportmateriel t, int kapacitet, double d) {
		Lastbil l = new Lastbil(nummer, c, t, kapacitet, d);
		Dao.getInstance().addLastbil(l);
		return l;
	}
	
	public void updateLastbil(Lastbil lastbil){
		Dao.getInstance().updateLastbil(lastbil);
	}

	public Transportmateriel createTransportmateriel(MaterielType type) {
		Transportmateriel m = new Transportmateriel(type);
		Dao.getInstance().addTransportmateriel(m);
		return m;
	}

	public Ordre createOrdre(int bruttov�gt, String kundeNavn, int fejlmagen,
			Transportmateriel t, HashMap<Integer, Lastbil> mapLastbil,
			HashMap<Integer, Integer> mapV�gt,
			HashMap<Integer, Integer> mapL�ssetid,
			HashMap<Integer, Date> mapL�ssedato) {
		Dao.getInstance().ordreNummer++;
		Ordre o = new Ordre(bruttov�gt, Dao.getInstance().ordreNummer, kundeNavn, fejlmagen, t,
				mapLastbil, mapV�gt, mapL�ssetid, mapL�ssedato);
		Dao.getInstance().addOrdre(o);
		return o;
	}
	
	public HashSet<Chauff�r> getChauff�rer(){
		return new HashSet<Chauff�r>(Dao.getInstance().getChauff�r());
	}
	
	public void removeChauff�r(Chauff�r chauff�r) {
		Dao.getInstance().removeChauff�r(chauff�r);
	}
	
	public int getRampenummerJuletr�(){
		return Dao.getInstance().getRampeNummerJuletr�();
	}
	
	public int getRampenummerKar(){
		return Dao.getInstance().getRampeNummerKar();
	}
	
	public int getRampenummerKasse(){
		return Dao.getInstance().getRampeNummerKar();
	}
	
	/**
	 * Initialiserer en rampe. Rampen f�r nummer alt efter materieltype. 
	 * @return	Den rampe, der oprettes.
	 */
	public Rampe createRampe(Transportmateriel t) {
		int rampeNummer = 0;
		if (t.getType() == MaterielType.JULETR�) {
			Dao.getInstance().setRampeNummerJuletr�Auto();
			rampeNummer = Dao.getInstance().getRampeNummerJuletr�();
		} else if (t.getType() == MaterielType.KAR) {
			Dao.getInstance().setRampeNummerKarAuto();
			rampeNummer = Dao.getInstance().getRampeNummerKar();;
		} else {
			Dao.getInstance().setRampeNummerKasseAuto();
			rampeNummer = Dao.getInstance().getRampeNummerKasse();;
		}
		Rampe r = new Rampe(rampeNummer, t);
		Dao.getInstance().addRampe(r);
		return r;
	}
	
	public void updateRampe(Rampe rampe, Transportmateriel t)
	{
		Dao.getInstance().updateRampe(rampe);	
		if (t.getType() == MaterielType.JULETR�) {
			Dao.getInstance().setRampeNummerJuletr�Auto();
		} else if (t.getType() == MaterielType.KAR) {
			Dao.getInstance().setRampeNummerKarAuto();

		} else {
			Dao.getInstance().setRampeNummerKasseAuto();
		}
	}

	public Set<Rampe> getRamper() {
		return new TreeSet<Rampe>(Dao.getInstance().getRamper());
	}
	
	public void removeLastbil(Lastbil lastbil){
		Dao.getInstance().removeLastbil(lastbil);
	}
	
	public HashSet<Lastbil> getLastbiler() {

		return new HashSet<Lastbil>(Dao.getInstance().getLastbiler());
	}

	/**
	 * Registerer en lastbils ankomst, hvorved en afl�sning oprettes.
	 * 
	 * @param lastbil	Den lastbil, hvis ankomst skal registreres.
	 * @param hviletid	Det tidspunkt, hvor chauff�ren igen m� k�re.
	 * @param ankomst	Tidspunktet for ankomsten.
	 * @return			Den afl�sning, der oprettes ved registreringen.
	 */
	public Afl�sning registrerAnkomst(Lastbil lastbil, Date hviletid, Date ankomst) {
		lastbil.setAnkommet(true);
		Rampe rampe = v�lgRampe(lastbil.getTrailer().getTransportmateriel());
		return lastbil.createAfl�sning(ankomst, hviletid, rampe);

	}

/**
 * Registrerer tidspunktet for en lastbils afgang. 
 * Nulstiller lastbilens delordre samt trailerens v�gt.
 * @param lastbil	Den lastbil, hvis afgang skal registreres.
 * @param afgang	Tidspunktet for afgangen.
 */
	public void registrerAfgang(Lastbil lastbil, Date afgang) {
		lastbil.setAnkommet(false);
		Trailer t = lastbil.getTrailer();
		t.setV�gtF�rKontrolvejning(0.0);
		t.setV�gtEfterKontrolvejning(0.0);
		lastbil.setDelordre(null);
		lastbil.getAfl�sning().setAfgang(afgang);
	}

	/**
	 * V�lger en ikke-stoppet rampe efter transportmateriel,
	 * der har kortest k� tid.
	 * @param transportmateriel	Det transportmateriel, rampen skal kunne afl�sse.
	 * @return 					Den valgte rampe.
	 */
	public Rampe v�lgRampe(Transportmateriel transportmateriel) {
		// ramper, med materialetype der matcher
		Set<Rampe> anvendeligeRamper = new HashSet<Rampe>();
		// alle ramper
		Set<Rampe> ramper = getRamper();
		for (Rampe rampe : ramper) {
			if (rampe.getTransportmateriel() == transportmateriel
					&& !rampe.isStoppet()) {
				anvendeligeRamper.add(rampe);// found match
			}
		}
		Iterator<Rampe> it = anvendeligeRamper.iterator();
		Rampe r = it.next();	
		// hvis k� tid er null, anvend denne rampe
		if (r.getK�Tid() == null) {
			return r;
		}
		while (it.hasNext()) {
			Rampe rampe = it.next();
			if (rampe.getK�Tid() == null) {
				return rampe;
			}
			if (rampe.getK�Tid().before(r.getK�Tid())) {
				r = rampe;
			}
		}
		
		return r;
		
	}

	/**
	 * Stopper en rampe, og omplanl�gger af�sninger i dens afl�sningsk� til en anden rampe
	 * @param rampe	Rampe, der skal stoppes
	 */
	public void stopRampe(Rampe rampe) {
		if (!rampe.isStoppet()) {
			rampe.setStoppet(true);
			List<Afl�sning> afl�sningerTilOmplanl�gning = rampe.getAfl�sningsk�();
			Transportmateriel t = rampe.getTransportmateriel();
			for(Afl�sning a : afl�sningerTilOmplanl�gning) {
		        Rampe r = v�lgRampe(t);
		        r.addAfl�sning(a);
		        a.setRampe(r);
			}
			rampe.clearAfl�sningsK�();
		}
	}
	
	/**
	 * Omplanl�gger en afl�sning til en ny rampe som h�jprioritet.
	 * @param afl�sning	Den afl�sning, der skal omplanl�gges.
	 * @return	Den rampe, afl�sningen blev omplanlagt til.
	 */
	public Rampe omplanl�gAfl�sning(Afl�sning afl�sning) {
		afl�sning.setH�jPrioritet(true);
		afl�sning.setStatus(Afl�sningsstatus.VENTER);
		Rampe r = v�lgRampe(afl�sning.getLastbil().getTrailer().getTransportmateriel());
		afl�sning.setRampe(r);
		r.addAfl�sning(afl�sning);
		return r;
		
	}

	public HashSet<Transportmateriel> getTransportmaterialer() {
		return new HashSet<Transportmateriel>(Dao.getInstance().getTransportmaterialer());
		}
	
	public void removeRampe(Rampe rampe){
		Dao.getInstance().removeRampe(rampe);
	}

}
