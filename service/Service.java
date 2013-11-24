package service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.Aflæsning;
import model.Aflæsningsstatus;
import model.Chauffør;
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

	public Chauffør createChauffør(String navn, String mobilNummer) {
		Chauffør c = new Chauffør(navn, mobilNummer);
		Dao.getInstance().addChauffør(c);
		return c;
	}
	
	public void updateChauffør(Chauffør chauffør){
		Dao.getInstance().updateChauffør(chauffør);
	}

	public Lastbil createLastbil(String nummer, Chauffør c,
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

	public Ordre createOrdre(int bruttovægt, String kundeNavn, int fejlmagen,
			Transportmateriel t, HashMap<Integer, Lastbil> mapLastbil,
			HashMap<Integer, Integer> mapVægt,
			HashMap<Integer, Integer> mapLæssetid,
			HashMap<Integer, Date> mapLæssedato) {
		Dao.getInstance().ordreNummer++;
		Ordre o = new Ordre(bruttovægt, Dao.getInstance().ordreNummer, kundeNavn, fejlmagen, t,
				mapLastbil, mapVægt, mapLæssetid, mapLæssedato);
		Dao.getInstance().addOrdre(o);
		return o;
	}
	
	public HashSet<Chauffør> getChauffører(){
		return new HashSet<Chauffør>(Dao.getInstance().getChauffør());
	}
	
	public void removeChauffør(Chauffør chauffør) {
		Dao.getInstance().removeChauffør(chauffør);
	}
	
	public int getRampenummerJuletræ(){
		return Dao.getInstance().getRampeNummerJuletræ();
	}
	
	public int getRampenummerKar(){
		return Dao.getInstance().getRampeNummerKar();
	}
	
	public int getRampenummerKasse(){
		return Dao.getInstance().getRampeNummerKar();
	}
	
	/**
	 * Initialiserer en rampe. Rampen får nummer alt efter materieltype. 
	 * @return	Den rampe, der oprettes.
	 */
	public Rampe createRampe(Transportmateriel t) {
		int rampeNummer = 0;
		if (t.getType() == MaterielType.JULETRÆ) {
			Dao.getInstance().setRampeNummerJuletræAuto();
			rampeNummer = Dao.getInstance().getRampeNummerJuletræ();
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
		if (t.getType() == MaterielType.JULETRÆ) {
			Dao.getInstance().setRampeNummerJuletræAuto();
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
	 * Registerer en lastbils ankomst, hvorved en aflæsning oprettes.
	 * 
	 * @param lastbil	Den lastbil, hvis ankomst skal registreres.
	 * @param hviletid	Det tidspunkt, hvor chaufføren igen må køre.
	 * @param ankomst	Tidspunktet for ankomsten.
	 * @return			Den aflæsning, der oprettes ved registreringen.
	 */
	public Aflæsning registrerAnkomst(Lastbil lastbil, Date hviletid, Date ankomst) {
		lastbil.setAnkommet(true);
		Rampe rampe = vælgRampe(lastbil.getTrailer().getTransportmateriel());
		return lastbil.createAflæsning(ankomst, hviletid, rampe);

	}

/**
 * Registrerer tidspunktet for en lastbils afgang. 
 * Nulstiller lastbilens delordre samt trailerens vægt.
 * @param lastbil	Den lastbil, hvis afgang skal registreres.
 * @param afgang	Tidspunktet for afgangen.
 */
	public void registrerAfgang(Lastbil lastbil, Date afgang) {
		lastbil.setAnkommet(false);
		Trailer t = lastbil.getTrailer();
		t.setVægtFørKontrolvejning(0.0);
		t.setVægtEfterKontrolvejning(0.0);
		lastbil.setDelordre(null);
		lastbil.getAflæsning().setAfgang(afgang);
	}

	/**
	 * Vælger en ikke-stoppet rampe efter transportmateriel,
	 * der har kortest kø tid.
	 * @param transportmateriel	Det transportmateriel, rampen skal kunne aflæsse.
	 * @return 					Den valgte rampe.
	 */
	public Rampe vælgRampe(Transportmateriel transportmateriel) {
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
		// hvis kø tid er null, anvend denne rampe
		if (r.getKøTid() == null) {
			return r;
		}
		while (it.hasNext()) {
			Rampe rampe = it.next();
			if (rampe.getKøTid() == null) {
				return rampe;
			}
			if (rampe.getKøTid().before(r.getKøTid())) {
				r = rampe;
			}
		}
		
		return r;
		
	}

	/**
	 * Stopper en rampe, og omplanlægger afæsninger i dens aflæsningskø til en anden rampe
	 * @param rampe	Rampe, der skal stoppes
	 */
	public void stopRampe(Rampe rampe) {
		if (!rampe.isStoppet()) {
			rampe.setStoppet(true);
			List<Aflæsning> aflæsningerTilOmplanlægning = rampe.getAflæsningskø();
			Transportmateriel t = rampe.getTransportmateriel();
			for(Aflæsning a : aflæsningerTilOmplanlægning) {
		        Rampe r = vælgRampe(t);
		        r.addAflæsning(a);
		        a.setRampe(r);
			}
			rampe.clearAflæsningsKø();
		}
	}
	
	/**
	 * Omplanlægger en aflæsning til en ny rampe som højprioritet.
	 * @param aflæsning	Den aflæsning, der skal omplanlægges.
	 * @return	Den rampe, aflæsningen blev omplanlagt til.
	 */
	public Rampe omplanlægAflæsning(Aflæsning aflæsning) {
		aflæsning.setHøjPrioritet(true);
		aflæsning.setStatus(Aflæsningsstatus.VENTER);
		Rampe r = vælgRampe(aflæsning.getLastbil().getTrailer().getTransportmateriel());
		aflæsning.setRampe(r);
		r.addAflæsning(aflæsning);
		return r;
		
	}

	public HashSet<Transportmateriel> getTransportmaterialer() {
		return new HashSet<Transportmateriel>(Dao.getInstance().getTransportmaterialer());
		}
	
	public void removeRampe(Rampe rampe){
		Dao.getInstance().removeRampe(rampe);
	}

}
