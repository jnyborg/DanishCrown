package model;

/**
 * Modellerer en chauffør.
 */
public class Chauffør {
	private String navn;
	private String mobilNummer;

	/**
	 * Initialiserer en chauffør.
	 */
	public Chauffør(String navn, String mobilNummer) {
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
