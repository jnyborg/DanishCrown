package service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.Aflæsning;
import model.Chauffør;
import model.Lastbil;
import model.Ordre;
import model.Rampe;
import model.Transportmateriel;
import model.Transportmateriel.MaterielType;

import org.junit.Before;
import org.junit.Test;

/**
 * Tester metoder i klassen Rampe
 */
public class TestRampe {

	Lastbil l1, l2, l3;
	Rampe r;
	@Before
	public void setUp() throws Exception {
		Transportmateriel juletræ = Service.getInstance().createTransportmateriel(MaterielType.JULETRÆ);
		r = Service.getInstance().createRampe(juletræ);


		
		Chauffør c1 = Service.getInstance().createChauffør("Henning Jensen", "12345678");
		l1 = Service.getInstance().createLastbil("UE1234567", c1, juletræ, 24, 50);
		Chauffør c2 = Service.getInstance().createChauffør("Mustaffa Allah", "24893029");
		l2 = Service.getInstance().createLastbil("UE2345678", c2, juletræ, 24, 50);
		Chauffør c3 = Service.getInstance().createChauffør("Jens Hansen", "48492019");
		l3 = Service.getInstance().createLastbil("UE3456789", c3, juletræ, 24, 2400);

		// Delordre informationer
		HashMap<Integer, Lastbil> hm1 = new HashMap<>();
		hm1.put(0, l1);
		hm1.put(1, l2);
		hm1.put(2, l3);
		HashMap<Integer, Integer> hm2 = new HashMap<>();
		hm2.put(0, 2000);
		hm2.put(1, 2000);
		hm2.put(2, 1000);
		HashMap<Integer, Integer> hm3 = new HashMap<>();
		hm3.put(0, 10);
		hm3.put(1, 20);
		hm3.put(2, 30);
		HashMap<Integer, Date> hm4 = new HashMap<>();
		hm4.put(0, new Date());
		hm4.put(1, new Date());
		hm4.put(2, new Date());
			
		Ordre o1 = Service.getInstance().createOrdre(5000, "Jens", 20, juletræ, hm1, hm2, hm3, hm4);



	}

	@Test
	public void TC1() {		
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 9,1));
		Service.getInstance().registrerAnkomst(l3, new Date(), new Date(113,4, 16, 9,15));
		assertEquals(new Date(113,4, 16, 10,0).getTime(),r.getKøTid().getTime(),0.1);
				
	}
	@Test
	public void TC2() {	
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		r.begyndAflæsning(l1.getAflæsning().getForventetStart());
		r.afslutAflæsning(l1.getAflæsning().getForventetSlut());
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 12, 1));
		Service.getInstance().registrerAnkomst(l3, new Date(), new Date(113,4, 16, 12,15));
		assertEquals(new Date(113,4, 16, 12,51).getTime(),r.getKøTid().getTime(),0.1);
	}
	@Test
	public void TC3() {	
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 9,1));
		r.begyndAflæsning(new Date(113,4,16,9,12));		
		Service.getInstance().registrerAnkomst(l3, new Date(), new Date(113,4, 16, 9,15));
		r.afslutAflæsning(new Date(113, 4, 16, 9, 22));
		assertEquals(new Date(113,4, 16, 10,12).getTime(),r.getKøTid().getTime(),0.1);
	}
	
	@Test
	public void TC4() {	
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		r.begyndAflæsning(new Date(113,4,16,9,0));
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 9,1));
		Service.getInstance().registrerAnkomst(l3, new Date(), new Date(113,4, 16, 9,15));
		r.afslutAflæsning(new Date(113, 4, 16, 9, 32));
		assertEquals(new Date(113,4, 16, 10,22).getTime(),r.getKøTid().getTime(),0.1);
	}

	@Test
	public void TC5() {	
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		r.begyndAflæsning(new Date(113,4,16,9,0));
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 9,1));
		r.afslutAflæsning(new Date(113, 4, 16, 9, 2));		
		r.begyndAflæsning(new Date(113,4,16,9,2));
		Service.getInstance().registrerAnkomst(l3, new Date(), new Date(113,4, 16, 9,7));
		r.afslutAflæsning(new Date(113, 4, 16, 9, 8));
		
		
		assertEquals(new Date(113,4, 16, 9,38).getTime(),r.getKøTid().getTime(),0.1);
	}
	
	@Test
	public void TC6() {	
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		r.begyndAflæsning(new Date(113,4,16,9,0));
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 9,1));
		Service.getInstance().registrerAnkomst(l3, new Date(), new Date(113,4, 16, 9,2));
		r.afslutAflæsning(new Date(113, 4, 16, 9,10));
		Service.getInstance().omplanlægAflæsning(l1.getAflæsning());
		
		assertEquals(l1.getAflæsning(),  ((List<Aflæsning>) r.getAflæsningskø()).get(0));
	}
	
	@Test
	public void TC7() {	
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		r.begyndAflæsning(new Date(113,4,16,9,0));
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 9,1));
		Service.getInstance().registrerAnkomst(l3, new Date(), new Date(113,4, 16, 9,2));
		r.afslutAflæsning(new Date(113, 4, 16, 9,10));
		r.begyndAflæsning(new Date(113, 4, 16, 9,10));
		r.afslutAflæsning(new Date(113, 4, 16, 9,30));

		Service.getInstance().omplanlægAflæsning(l1.getAflæsning());
		Service.getInstance().omplanlægAflæsning(l2.getAflæsning());
		
		
		assertEquals(l1.getAflæsning(),  ((List<Aflæsning>) r.getAflæsningskø()).get(0));
		assertEquals(l2.getAflæsning(),  ((List<Aflæsning>) r.getAflæsningskø()).get(1));
	}
	
	@Test
	public void TC8() {	
		Service.getInstance().registrerAnkomst(l1, new Date(), new Date(113,4, 16, 9,0));
		r.begyndAflæsning(new Date(113, 4, 16, 9,0));
		Service.getInstance().registrerAnkomst(l2, new Date(), new Date(113,4, 16, 9,1));
		r.afslutAflæsning(new Date(113, 4, 16, 9,5));
		Service.getInstance().omplanlægAflæsning(l1.getAflæsning());
		r.begyndAflæsning(new Date(113, 4, 16, 9,5));
		r.afslutAflæsning(new Date(113, 4, 16, 9,15));
		
		assertEquals(new Date(113,4, 16, 9,35).getTime(),r.getKøTid().getTime(),0.1);
	}
	
}
