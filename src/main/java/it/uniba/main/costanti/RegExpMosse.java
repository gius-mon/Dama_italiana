package it.uniba.main.costanti;

/**
 * Tipologia della classe: <b> << entity class >> </b> <br>
 * Responsabilita' della classe:contiene le espressioni regolari utili nella
 * gestione delle mosse
 * 
 */
public final class RegExpMosse {

    /**
     * espressione per lo spostamento semplice
     */
	public static final String SPOSTAMENTO_SEMPLICE = "((\\b([1-9]|1[0-9]|2[0-9]|3[0-2])\\b))(\\-)"
			+ "(\\b([1-9]|1[0-9]|2[0-9]|3[0-2])\\b)";

    /**
     * espressione per lo spostamento con presa semplice
     */
	public static final String SPOSTAMENTO_PRESA = "([1-9]|1[0-9]|2[0-9]|3[0-2])x([1-9]|1[0-9]|2[0-9]|3[0-2])";

    /**
     * espressione per lo spostamento con presa multipla
     */
	public static final String SPOSTAMENTO_PRESA_MULTIPLA = "([1-9]|1[0-9]|2[0-9]|3[0-2])x"
			+ "([1-9]|1[0-9]|2[0-9]|3[0-2])x([1-9]|1[0-9]|2[0-9]|3[0-2])";

	private RegExpMosse() {
	}
}
