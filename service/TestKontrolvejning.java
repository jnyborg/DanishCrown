package service;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;

import model.Chauff�r;
import model.Lastbil;
import model.Transportmateriel;
import model.Transportmateriel.MaterielType;

import org.junit.Before;
import org.junit.Test;

/**
 * Tester metoden kontrolvejning i klassen Trailer
 */
public class TestKontrolvejning {

	Lastbil l1, l2, l3;
	
	@Before
	public void setUp() throws Exception {
		Transportmateriel juletr� = Service.getInstance().createTransportmateriel(MaterielType.JULETR�);
		

		//Lastbil informationer
		Chauff�r c1 = Service.getInstance().createChauff�r("Henning Jensen", "12345678");
		l1 = Service.getInstance().createLastbil("UE1234567", c1, juletr�, 24, 1000);
		l1.getTrailer().setV�gtF�rKontrolvejning(1000);
		Chauff�r c2 = Service.getInstance().createChauff�r("Mustaffa Allah", "24893029");
		l2 = Service.getInstance().createLastbil("UE2345678", c2, juletr�, 24, 400);
		l2.getTrailer().setV�gtF�rKontrolvejning(400);
		Chauff�r c3 = Service.getInstance().createChauff�r("Jens Hansen", "48492019");
		l3 = Service.getInstance().createLastbil("UE3456789", c3, juletr�, 24, 500);
		l3.getTrailer().setV�gtF�rKontrolvejning(3000);

		// Delordre informationer
		HashMap<Integer, Lastbil> hm1 = new HashMap<>();
		hm1.put(0, l1);
		hm1.put(1, l2);
		hm1.put(2, l3);
		HashMap<Integer, Integer> hm2 = new HashMap<>();
		hm2.put(0, 100);
		hm2.put(1, 400);
		hm2.put(2, 500);
		HashMap<Integer, Integer> hm3 = new HashMap<>();
		hm3.put(0, 10);
		hm3.put(1, 20);
		hm3.put(2, 30);
		HashMap<Integer, Date> hm4 = new HashMap<>();
		hm4.put(0, new Date());
		hm4.put(1, new Date());
		hm4.put(2, new Date());
		
		Service.getInstance().createOrdre(1000, "Jens", 40, juletr�, hm1, hm2, hm3, hm4);



	}

	@Test
	public void TC9() {		
		assertTrue(!l1.getTrailer().kontrolvejning(1000));
	}
	@Test
	public void TC10() {		
		assertTrue(l2.getTrailer().kontrolvejning(784));
	}
	@Test
	public void TC11() {		
		assertTrue(l2.getTrailer().kontrolvejning(810));
	}
	@Test
	public void TC12() {		
		assertTrue(l2.getTrailer().kontrolvejning(816));
	}
	@Test
	public void TC13() {
		assertTrue(!l2.getTrailer().kontrolvejning(3521));
	}

	
	
}
