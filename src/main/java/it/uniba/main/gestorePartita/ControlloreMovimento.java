package it.uniba.main.gestorePartita;

import it.uniba.main.costanti.Colore;
import it.uniba.main.costanti.Numero;


/**
 * Tipologia della classe: <b><< control class >></b><br>
 * Responsabilita' della classe: effettua controlli sul corretto spostamento
 * delle pedine in base alle regole del gioco
 */

public class ControlloreMovimento extends Spostamento {



    /**
     *
     * @param comando
     */
    protected static void conversione(final String comando) {

		String[] split = comando.split("-");
		int casellaInizio = Integer.parseInt(split[0]);
		int casellaFine = Integer.parseInt(split[1]);

		setrInizio(getValueR(casellaInizio));
		setcInizio(getValueC(casellaInizio));
		setrDest(getValueR(casellaFine));
		setcDest(getValueC(casellaFine));
	}

    /**
     *
     * @param comando
     */
    protected static void conversioneComandoPresa(final String comando) {

		String[] split = comando.split("x");
		int casellaInizio = Integer.parseInt(split[0]);
		int casellaFine = Integer.parseInt(split[1]);

		setrInizio(getValueR(casellaInizio));
		setcInizio(getValueC(casellaInizio));
		setrDest(getValueR(casellaFine));
		setcDest(getValueC(casellaFine));
	}

    /**
     *
     * @param casella
     * @return
     */
    protected static int getValueR(final int casella) {

		int x = Numero.N0;

		if (casella == Numero.N1 || casella == Numero.N2 || casella == Numero.N3 || casella == Numero.N4) {
			x = Numero.N0;
		}

		if (casella == Numero.N5 || casella == Numero.N6 || casella == Numero.N7 || casella == Numero.N8) {
			x = Numero.N1;
		}

		if (casella == Numero.N9 || casella == Numero.N10 || casella == Numero.N11 || casella == Numero.N12) {
			x = Numero.N2;
		}

		if (casella == Numero.N13 || casella == Numero.N14 || casella == Numero.N15 || casella == Numero.N16) {
			x = Numero.N3;
		}

		if (casella == Numero.N17 || casella == Numero.N18 || casella == Numero.N19 || casella == Numero.N20) {
			x = Numero.N4;
		}
		if (casella == Numero.N21 || casella == Numero.N22 || casella == Numero.N23 || casella == Numero.N24) {
			x = Numero.N5;
		}
		if (casella == Numero.N25 || casella == Numero.N26 || casella == Numero.N27 || casella == Numero.N28) {
			x = Numero.N6;
		}
		if (casella == Numero.N29 || casella == Numero.N30 || casella == Numero.N31 || casella == Numero.N32) {
			x = Numero.N7;
		}

		return x;
	}

    /**
     *
     * @param casella
     * @return
     */
    protected static int getValueC(final int casella) {

		int y = Numero.N0;

		if (casella == Numero.N1 || casella == Numero.N9 || casella == Numero.N17 || casella == Numero.N25) {
			y = Numero.N0;
		}

		if (casella == Numero.N5 || casella == Numero.N13 || casella == Numero.N21 || casella == Numero.N29) {
			y = Numero.N1;
		}

		if (casella == Numero.N2 || casella == Numero.N10 || casella == Numero.N18 || casella == Numero.N26) {
			y = Numero.N2;
		}

		if (casella == Numero.N6 || casella == Numero.N14 || casella == Numero.N22 || casella == Numero.N30) {
			y = Numero.N3;
		}

		if (casella == Numero.N3 || casella == Numero.N11 || casella == Numero.N19 || casella == Numero.N27) {
			y = Numero.N4;
		}
		if (casella == Numero.N7 || casella == Numero.N15 || casella == Numero.N23 || casella == Numero.N31) {
			y = Numero.N5;
		}
		if (casella == Numero.N4 || casella == Numero.N12 || casella == Numero.N20 || casella == Numero.N28) {
			y = Numero.N6;
		}
		if (casella == Numero.N8 || casella == Numero.N16 || casella == Numero.N24 || casella == Numero.N32) {
			y = Numero.N7;
		}

		return y;
	}

	/*
	 * controlli effettuati sulla damiera
	 */

    /**
     *
     * @param p
     * @return
     */
	public static boolean contrTurno(final Partita p) {

		boolean flag = false;

		if (p.getTurno() == p.getInstanceDamiera().getColoreMatrice(getrInizio(), getcInizio())) {
			flag = true;
		}
		return flag;
	}

	/**
    *
    * @param p
    * @return
    */
	public static boolean contrArrivoLibero(final Partita p) {
		boolean flag = false;
		if (p.getInstanceDamiera().getColoreMatrice(getrDest(), getcDest()) == Colore.NULLO) {
			flag = true;

		}
		return flag;
	}

    /**
     *
     * @param p
     * @return
     */
    public static boolean contrPezzoMangiato(final Partita p) {

		boolean flag = false;
		int rigaPezzoMangiatoBianco = getrInizio() + 1;
		int colonnaPezzoMangiatoDx = getcInizio() + 1;
		int colonnaPezzoMangiatoSx = getcInizio() - 1;
		int rigaPezzoMangiatoNero = getrInizio() - 1;

		/*
		 * controllo valori ai bordi della damiera
		 */

		if (p.getTurno() == Colore.NERO && rigaPezzoMangiatoBianco > -1
				&& rigaPezzoMangiatoBianco < Numero.N8) {

			if (colonnaPezzoMangiatoSx > -1) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoBianco,
						colonnaPezzoMangiatoSx) == Colore.BIANCO)
						&& (getcInizio() - 2 == getcDest())) {

					flag = true;
				}
			}

			if (colonnaPezzoMangiatoDx < Numero.N8) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoBianco,
						colonnaPezzoMangiatoDx) == Colore.BIANCO)
						&& (getcInizio() + 2 == getcDest())) {
					flag = true;

				}
			}
		}

		if (p.getTurno() == Colore.BIANCO && rigaPezzoMangiatoNero < Numero.N8
				&& rigaPezzoMangiatoNero > -1) {
			if (colonnaPezzoMangiatoSx > -1) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoNero,
						colonnaPezzoMangiatoSx) == Colore.NERO)
						&& (getcInizio() - 2 == getcDest())) {

					flag = true;
				}
			}

			if (colonnaPezzoMangiatoDx < Numero.N8) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoNero,
						colonnaPezzoMangiatoDx) == Colore.NERO)
						&& (getcInizio() + 2 == getcDest())) {
					flag = true;

				}
			}
		}
		return flag;
	}

    /**
     *
     * @param p
     * @return
     */
    public static boolean contrSpostamento(final Partita p) {
		boolean flag = false;
		if ((p.getTurno() == Colore.BIANCO && getrDest() == getrInizio() - 1
				&& (getcDest() >= 0 || getcDest() <= Numero.N7)
				&& (getcDest() == getcInizio() - 1 || getcDest() == getcInizio() + 1))
				|| (p.getTurno() == Colore.NERO
				&& getrDest() == getrInizio() + 1
						&& ((getcDest() == getcInizio() - 1
						|| getcDest() == getcInizio() + 1)))) {
			flag = true;
		}

		return flag;
	}

	/*
	 * spostamento con presa semplice
	 */

	public static boolean contrPresaSemplice(final Partita p) {
		boolean flag = false;

		/*
		 * il nero puo' mangiare solo bianco e viceversa
		 */

		if ((p.getTurno() == Colore.NERO) && (getcDest() == getcInizio() - 2)
				&& (getcDest() >= 0 && getcDest() <= Numero.N7)
				&& (contrPezzoMangiato(p)) && (getrDest() == getrInizio() + 2)
				&& (contrArrivoLibero(p))) {
			flag = true;
		}

		if ((p.getTurno() == Colore.NERO) && (getcDest() == getcInizio() + 2)
				&& (getcDest() >= 0 && getcDest() <= Numero.N7)
				&& (contrPezzoMangiato(p)) && (getrDest() == getrInizio() + 2)
				&& (contrArrivoLibero(p))) {
			flag = true;
		}

		if ((p.getTurno() == Colore.BIANCO) && (getcDest() == getcInizio() - 2)
				&& (getcDest() >= 0 && getcDest() <= Numero.N7)
				&& (contrPezzoMangiato(p)) && (getrDest() == getrInizio() - 2)
				&& (contrArrivoLibero(p))) {
			flag = true;
		}

		if ((p.getTurno() == Colore.BIANCO) && (getcDest() == getcInizio() + 2)
				&& (getcDest() >= 0 && getcDest() <= Numero.N7)
				&& (contrPezzoMangiato(p)) && (getrDest() == getrInizio() - 2)
				&& (contrArrivoLibero(p))) {
			flag = true;
		}

		return flag;
	}

}
