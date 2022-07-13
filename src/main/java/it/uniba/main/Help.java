package it.uniba.main;

/**
 * Tipologia della classe: <b><< boundary class >></b><br>
 * Responsabilità della classe: consente la visualizzalione della lista dei
 * comandi
 */
public final class Help {

    public static void stampaHelp() {

        System.out.println("\n\n !!Help dei comandi!! \n");
		System.out.println(" Comandi disponibili:");
		System.out.println("  - gioca");
        System.out.println("  - abbandona");
		System.out.println("  - esci");
		System.out.println("  - numeri");
		System.out.println("  - tempo");
		System.out.println("  - mosse");
		System.out.println("  - damiera");
		System.out.println("  - prese");
		System.out.println(
				"  Le mosse sono descritte in notazione algebrica "
				+ "\n Spostamento semplice ->  1-5"
				+ " \n Spostamento con presa -> 1x10 \n ");

	}

    public static void stampaDescrizione() {

		System.out.println("\n\t\t !!Benvenuto nel gioco della Dama italiana!! \t\t\n "
				+ "\n La dama e' un gioco da tavolo tradizionale per due giocatori. "
				+ "Tale gioco si svolge tra"
				+ "\n due giocatori, un giocatore "
				+ "ha i pezzi bianchi, l'altro quelli neri. \t "
				+ "\n I pezzi si muovono diagonalmente "
				+ "solo sulle caselle scure e quelli catturati vengono \n"
				+ " rimossi dalla damiera ed esclusi dal gioco. \t "
				+ "\n Perde il giocatore a cui vengono catturati "
				+ "tutti i pezzi o che e' impossibilitato a muovere."
				+ " \t " + "\n\n -Team glass \n ");
	}

	private Help() {

	}

}
