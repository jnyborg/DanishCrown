package model;

/**
 * Modellerer et transportmateriel.
 */
public class Transportmateriel {

	private MaterielType type;
	/**
	 * Initialiserer et transportmateriel.
	 * @param type	En enum af typen MaterielType.
	 */
	public Transportmateriel(MaterielType type) {
		this.type = type;
	}

	public MaterielType getType() {
		return type;
	}

	public void setType(MaterielType type) {
		this.type = type;
	}
	
	public enum MaterielType {
		JULETRÆ {
			@Override
			public String toString() {
				return "Juletræ";
			}
		},
		KAR {
			@Override
			public String toString() {
				return "Kar";
			}
		},
		KASSE {
			@Override
			public String toString() {
				return "Kasse";
			}
		}
	}
	
	@Override
	public String toString() {
		return type+"";
	}
}
