package it.uniba.main;

import java.io.IOException;

import it.uniba.main.gestorePartita.InterfacciaUtente;

/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */

/**
 * Tipologia della classe: <b><< control class >></b><br>
 * Responsabilità della classe: consente di avviare l'applicazione
 */
public final class AppMain {

/**
* Private constructor. Change if needed.
*/
	private AppMain() {

	}

	/**
	 * * This is the main entry of the application.
	 *
	 * @param args The command-line arguments.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException, InterruptedException {

		InterfacciaUtente interfacciaUtente = new InterfacciaUtente();
		interfacciaUtente.avvia();

	}

}
