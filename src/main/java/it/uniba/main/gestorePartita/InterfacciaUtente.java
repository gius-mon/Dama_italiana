package it.uniba.main.gestorePartita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import it.uniba.main.Help;
import it.uniba.main.costanti.Colore;
import it.uniba.main.costanti.Numero;
import it.uniba.main.costanti.RegExpMosse;
import it.uniba.main.costanti.TipoPezzo;
import it.uniba.main.entity.Pezzo;


/**
 * Tipologia della classe: <b> << boundary class >> </b> <br>
 * Responsabilita' della classe: provvede all'accettazione dei comandi da
 * tastiera e alla chiamata dei rispettivi metodi
 */
public class InterfacciaUtente {
	private boolean esciP = false;

	/*
    * interfaccia utente
    */

	/**
	 * 
	 * @throws IOException
	 */
 public void avvia() throws IOException,
        InterruptedException {

		InputStreamReader in = new InputStreamReader(System.in, "UTF-8");
		BufferedReader tastiera = new BufferedReader(in);

		boolean fAbbandona = false;

		Help.stampaDescrizione();

		System.out.print(" Premere invio per continuare... ");
		String comando = tastiera.readLine();

		Help.stampaHelp();

		comando = "";

		do {
			fAbbandona = false;
			System.out.print(" Inserisci comando: ");

		    comando = tastiera.readLine();
			if (comando != null) {
				switch (comando) {

				case "-h":
					Help.stampaHelp();
					break;

				case "--help":
					Help.stampaHelp();
					break;

				case "help":
					Help.stampaHelp();
					break;
				case "damiera":

				case "mosse":

				case "prese":

				case "tempo":

				case "abbandona":
					System.out.println("\n Non ci sono partite in corso \n Digitare il comando"
							+ " 'gioca' per avviarne una nuova");
					break;

				case "esci":
					esciP = esci();
					break;
				case "numeri":
					numeri();
					break;

				case "gioca":

					Partita p = Partita.getInstance();
					p.initPartita();

					fAbbandona = false;
					do {
						System.out.print(p.getTurno() + " > ");
						comando = tastiera.readLine();
						if (comando != null) {
							switch (comando) {
							case "-h":
								Help.stampaHelp();
								break;

							case "--help":
								Help.stampaHelp();
								break;

							case "help":
								Help.stampaHelp();
								break;

							case "numeri":
								numeri();
								break;

							case "gioca":
								System.out.println("Partita gia' in corso");
								break;

							case "damiera":
								 stampaDamiera(p);
								break;

							case "mosse":
								  stampaMosse(p);
								break;

							case "prese":
								  stampaPrese(p);
								break;

							case "tempo":
								  mostraTempo(p, p.getInstanceTempoBianco());
								break;

							case "abbandona":
								abbandona(p);
								fAbbandona = true;
								break;

							case "esci":
								esciP = esci();
								break;

							default:

								// accettazione dei comandi spostamento
								if (matchesComando(comando)) {
									if (Partita.eseguiMossa(comando, p)) {
										System.out.println(" !!Mossa "
												+ "effettuata!!  \n");

									} else {
										System.out.println(" !!Mossa "
											+ "non consentita!!  \n");
									}

								} else {
									System.out.println("!!Comando "
											+ "non esistente!! \n");
								}
							}
						}
					} while (!(fAbbandona || esciP));

				default:

					// accettazione dei comandi spostamentoif
					// (comando.matches(RegExpMosse.SPOSTAMENTO_SEMPLICE)
					if (matchesComando(comando)) {
						System.out.println(" Non ci sono partite in corso "
								+ "\n Digitare il comando 'gioca'"
								+ " per avviarne una nuova");
					} else {
						if (!(fAbbandona || esciP)) {
							System.out.println(" !!Comando non esistente!! \n");
						}
					}
				}
			}

		} while (!esciP);
	}

	/**
	* @param comando
    */
	private boolean matchesComando(final String comando) {
		boolean matchComando = false;
		if (comando.matches(RegExpMosse.SPOSTAMENTO_SEMPLICE) || comando.matches(RegExpMosse.SPOSTAMENTO_PRESA)
				|| comando.matches(RegExpMosse.SPOSTAMENTO_PRESA_MULTIPLA)) {
			matchComando = true;
		}
		return matchComando;

	}

	/*
    * controllo chiusura applicazione
    */

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean esci() throws IOException, InterruptedException {
		boolean flag = true;
		InputStreamReader in = new InputStreamReader(System.in, "UTF-8");
		BufferedReader tastiera = new BufferedReader(in);
		String conferma = "";
		System.out.println("\n Sicuro di voler chiudere l'applicazione? (si/no)");
		System.out.print(" Comando inserito: ");
		conferma = tastiera.readLine();

		while ((!"si".equals(conferma)) && (!"SI".equals(conferma)) && (!"Si".equals(conferma))
				&& (!"no".equals(conferma)) && (!"NO".equals(conferma))) {
			System.out.println("\n Errore di inserimento");
			System.out.print(" Comando inserito: ");
			conferma = tastiera.readLine();

		}

		if (("si".equals(conferma)) || ("SI".equals(conferma)) || ("Si".equals(conferma))) {

			final int timeSleep = 2000;
			System.out.println("\n !!Grazie per aver utilizzato Dama italiana!!");

			Thread.sleep(timeSleep);

			flag = true;

		} else {
			System.out.println("");
		}
		return flag;

	}

	/**
	* @param p
	* @throws IOException
    */
	public void abbandona(final Partita partita) throws IOException {

		InputStreamReader in = new InputStreamReader(System.in, "UTF-8");
		BufferedReader tastiera = new BufferedReader(in);

		String conferma = "";

		System.out.println("\n Sicuro di voler abbandonare la partita? (si/no)");
		System.out.print("\n  Comando inserito: ");
		conferma = tastiera.readLine();

		while ((!"si".equals(conferma)) && (!"SI".equals(conferma)) && (!"no".equals(conferma))
				&& (!"NO".equals(conferma))) {
			System.out.println(" errore di inserimento");
			System.out.print(" Comando inserito: ");
			conferma = tastiera.readLine();

		}

		if (("si".equals(conferma)) || ("SI".equals(conferma))) {

			partita.getInstanceTempoBianco().azzeratempo();
			partita.getInstanceTempoNero().azzeratempo();
			System.out.println("\n La partita e' terminata!");

			if (partita.getTurno() == Colore.BIANCO) {
				System.out.println("\n Il giocatore Nero ha vinto la partita per abbandono!");
			} else {
				System.out.println("\n Il giocatore Bianco ha vinto la partita per abbandono!");
			}

		}

		System.out.println("");
	}

	/**
	* @param p
    */
	public void stampaPrese(final Partita p) {

		if (!p.getPezziCatturati().isEmpty()) {
			Iterator<Pezzo> i = p.getPezziCatturati().iterator();
			Iterator<Pezzo> j = p.getPezziCatturati().iterator();

			System.out.println(" Elenco prese da inizio partita:  \n ");

			System.out.print(" Bianco: ");
			while (i.hasNext()) {
				Pezzo pedina = (Pezzo) i.next();

				if (pedina.getColore() == Colore.NERO) {
					System.out.print("\u26C0" + " ");
				}
			}
			System.out.println();

			System.out.print(" Nero: ");
			while (j.hasNext()) {
				Pezzo pedina = (Pezzo) j.next();

				if (pedina.getColore() == Colore.BIANCO) {
					System.out.print("\u26C2" + " ");
				}

			}

			System.out.println();

		} else {
			System.out.println(" Nessuna presa ancora effettuata...");
		}
		System.out.println("");

	}

	/**
	* @param p
    */
	public void stampaMosse(final Partita p) {

		if (!(p.getMosseEffettuate().isEmpty())) {
			Iterator<String> i = p.getMosseEffettuate().iterator();

			System.out.println(" Elenco mosse da inizio partita:  \n ");
			while (i.hasNext()) {
				String mossa = (String) i.next();

				System.out.println(" " + mossa);
			}
			System.out.println();

		} else {
			System.out.println(" Nessuna mossa ancora effettuata...");
		}
		System.out.println("");

	}

	/**
	    * @param p
	    * @param tempoGiocatori
	    */
		public void mostraTempo(final Partita p, final Tempo tempoGiocatori) {

			final int maxSecondi = 60;

			tempoGiocatori.calcolaTempo();
			long tempoAttuale = tempoGiocatori.getTempoGiocatore() + tempoGiocatori.getTempoTurno();

			System.out.println("\n Il tempo del  giocatore  "
					+ p.getTurno() + " e':  "
					+ (tempoAttuale / maxSecondi) + " minuti e "
					+ (tempoAttuale % maxSecondi) + " secondi ");
			if ((p.getTurno() == Colore.BIANCO)) {

				System.out.println("\n Il tempo del  giocatore NERO e': "
				     + p.getInstanceTempoNero().getTempoGiocatore() / maxSecondi
					 + " minuti e " + p.getInstanceTempoNero().getTempoGiocatore() % maxSecondi
					 + " secondi\n");
			} else {

				System.out.println("\n Il tempo del giocatore BIANCO e': "
					 + p.getInstanceTempoBianco().getTempoGiocatore() / maxSecondi
					 + " minuti e " + p.getInstanceTempoBianco().getTempoGiocatore() % maxSecondi
					 + " secondi\n");
			}
		}

	/*
	 * stampa della damiera
	 */

	public static void numeri() {
		System.out.println(" Damiera con numerazione:");
		System.out.println(" ___ ___ ___ ___ ___ ___ ___ ___");
		System.out.println("| 1 |███| 2 |███| 3 |███| 4 |███|");
		System.out.println("|___|███|___|███|___|███|___|███|");
		System.out.println("|███| 5 |███| 6 |███| 7 |███| 8 |");
		System.out.println("|███|___|███|___|███|___|███|___|");
		System.out.println("| 9 |███|10 |███|11 |███|12 |███|");
		System.out.println("|___|███|___|███|___|███|___|███|");
		System.out.println("|███|13 |███|14 |███|15 |███|16 |");
		System.out.println("|███|___|███|___|███|___|███|___|");
		System.out.println("|17 |███|18 |███|19 |███|20 |███|");
		System.out.println("|___|███|___|███|___|███|___|███|");
		System.out.println("|███|21 |███|22 |███|23 |███|24 |");
		System.out.println("|███|___|███|___|███|___|███|___|");
		System.out.println("|25 |███|26 |███|27 |███|28 |███|");
		System.out.println("|___|███|___|███|___|███|___|███|");
		System.out.println("|███|29 |███|30 |███|31 |███|32 |");
		System.out.println("|███|___|███|___|███|___|███|___|");
		System.out.println("");

	}

	/**
    *
    * metodo di esciP
    */
	public boolean isEsciP() {
		return esciP;
	}

	/**
	* @param p
	*/
	public void stampaDamiera(final Partita p) {

		System.out.println(" Damiera con i pezzi:");
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|");
		for (int i = 0; i < Numero.N8; i++) {
			if (i % 2 == 0) {
				System.out.println("|     |█████|     |█████|"
						+ "     |█████|     |█████|");
			} else {
				System.out.println("|█████|     |█████|"
						+ "     |█████|     |█████|     |");
			}
			for (int j = 0; j < Numero.N8; j++) {
				System.out.print("|");
				if ((p.getDamiera().getTipoPezzo(i, j)
						== TipoPezzo.PEDINA
						&& p.getDamiera().getColoreMatrice(i, j)
						== Colore.NERO)) {
					System.out.print("  \u26C0  "); // stampa pedina nera

				} else if ((p.getDamiera().getTipoPezzo(i, j)
						== TipoPezzo.PEDINA
						&& p.getDamiera().getColoreMatrice(i, j)
						== Colore.BIANCO)) {
					System.out.print("  \u26C2  "); // stampa pedina bianca
				} else if ((p.getDamiera().getTipoPezzo(i, j) == TipoPezzo.DAMA
						&& p.getDamiera().getColoreMatrice(i, j)
						== Colore.NERO)) {
					System.out.print("  \u26C1  "); // stampa dama nera

				} else if ((p.getDamiera().getTipoPezzo(i, j)
						== TipoPezzo.DAMA
						&& p.getDamiera().getColoreMatrice(i, j)
						== Colore.BIANCO)) {
					System.out.print("  \u26C3  "); // stampa dama bianca

				} else if ((j + i) % 2 == 0) {
					System.out.print("     ");
				} else {
					System.out.print("█████");
				}

			}

			System.out.println("|");
			System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|");
		}

	}

}
