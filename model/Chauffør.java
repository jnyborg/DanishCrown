package model;

/**
 * Modellerer en chauff�r.
 */
public class Chauff�r {
	private String navn;
	private String mobilNummer;

	/**
	 * Initialiserer en chauff�r.
	 */
	public Chauff�r(String navn, String mobilNummer) {
		this.navn = navn;
		this.mobilNummer = mobilNummer;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getMobilNummer() {
		return mobilNummer;
	}

	public void setMobilNummer(String mobilNummer) {
		this.mobilNummer = mobilNummer;
	}

	@Override
	public String toString() {
		return navn;
	}

}
