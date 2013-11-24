package gui;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.swing.UIManager;

import model.Chauffør;
import model.Lastbil;
import model.Ordre;
import model.Rampe;
import model.Transportmateriel;
import model.Transportmateriel.MaterielType;
import service.Service;
import dao.Dao;

/**
 * Starter programmet.
 */
public class App {

	static Transportmateriel juletræ, kar, kasse;

	public static void main(String[] args) {

		try {
			try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        }
	        catch (Exception e) {
	        }
			juletræ = Service.getInstance().createTransportmateriel(MaterielType.JULETRÆ);
			kar = Service.getInstance().createTransportmateriel(MaterielType.KAR);
			kasse = Service.getInstance().createTransportmateriel(MaterielType.KASSE);
			createSomeObjects();
			/**
			 * Er der kun så man kan skifte Dao i JPA versionen ud med denne Dao
			 */
			Dao.getInstance().updateRampeNumre();
			
			MainFrame m1 = new MainFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void createSomeObjects() {

		Rampe r1 = Service.getInstance().createRampe(kar);
		Rampe r2 = Service.getInstance().createRampe(kar);
		Rampe r3 = Service.getInstance().createRampe(juletræ);
		Rampe r4 = Service.getInstance().createRampe(juletræ);
		Rampe r5 = Service.getInstance().createRampe(kasse);
		Chauffør c1 = Service.getInstance().createChauffør("Henning Jensen", "12345678");
		Lastbil l1 = Service.getInstance().createLastbil("UE1234567", c1, juletræ, 24, 50);
		Chauffør c2 = Service.getInstance().createChauffør("Hans Jørgensen", "24893029");
		Lastbil l2 = Service.getInstance().createLastbil("UE2345678", c2, juletræ, 24, 50);
		Chauffør c3 = Service.getInstance().createChauffør("Jens Hansen", "48492019");
		Lastbil l3 = Service.getInstance().createLastbil("UE3456789", c3, juletræ, 24, 2400);
		Chauffør c4 = Service.getInstance().createChauffør("Børge Larsen", "38471938");
		Lastbil l4 = Service.getInstance().createLastbil("UE4567891", c4, kar, 24, 1000);
		Chauffør c5 = Service.getInstance().createChauffør("Kristoffer Petersen", "84827489");
		Lastbil l5 = Service.getInstance().createLastbil("UE5678912", c5, kar, 24, 1200);
		Chauffør c6 = Service.getInstance().createChauffør("Jakob Hansen", "38103923");
		Lastbil l6 = Service.getInstance().createLastbil("UE6789123", c6, kar, 24, 2400);
				
		//Ordre o1 oprettes med tre delordrer
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
		
		//Ordre o2 oprettes med tre delordrer
		HashMap<Integer, Lastbil> hm5 = new HashMap<>();
		hm5.put(0, l4);
		hm5.put(1, l5);
		hm5.put(2, l6);
		HashMap<Integer, Integer> hm6 = new HashMap<>();
		hm6.put(0, 1000);
		hm6.put(1, 1000);
		hm6.put(2, 2000);
		HashMap<Integer, Integer> hm7 = new HashMap<>();
		hm7.put(0, 20);
		hm7.put(1, 30);
		hm7.put(2, 40);
		HashMap<Integer, Date> hm8 = new HashMap<>();
		hm8.put(0, new Date());
		hm8.put(1, new Date());
		hm8.put(2, new Date());
		
		Ordre o1 = Service.getInstance().createOrdre(5000, "Jens", 20, juletræ, hm1, hm2, hm3, hm4);
		
		Ordre o2 = Service.getInstance().createOrdre(4000, "Hans", 50, kar, hm5, hm6, hm7, hm8);
	}
}
