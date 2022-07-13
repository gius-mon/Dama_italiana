package it.uniba.main.gestorePartita;

import it.uniba.main.costanti.Colore;
import it.uniba.main.costanti.TipoPezzo;
import it.uniba.main.entity.Pezzo;
import it.uniba.main.costanti.Numero;

/**
 * Tipologia della classe: <b><< control class >></b><br>
 * Responsabilita'  della classe: effettua lo spostamento delle pedine sulla
 * damiera in base al comando impartito
 */
public class Spostamento {

	protected Spostamento() {

	}

    /*
     *  inizializzazione rInizio
     */
    private static int rInizio = 0;

    /*
     *  inizializzazione cInizio
     */
	private static int cInizio = 0;

	/*
     *  inizializzazione rDest
     */
	private static int rDest = 0;

	/*
     *  inizializzazione cDest
     */
	private static int cDest = 0;

    public static int getrInizio() {
		return rInizio;
	}

	public static void setrInizio(final int rigaInizio) {
		Spostamento.rInizio = rigaInizio;
	}

	public static int getcInizio() {
		return cInizio;
	}

	public static void setcInizio(final int colonnaInizio) {
		Spostamento.cInizio = colonnaInizio;
	}

	public static int getrDest() {
		return rDest;
	}

	public static void setrDest(final int rigaDest) {
		Spostamento.rDest = rigaDest;
	}

	public static int getcDest() {
		return cDest;
	}

	public static void setcDest(final int colonnaDest) {
		Spostamento.cDest = colonnaDest;
	}

	public static boolean spostamentoSemplice(final String comando, final Partita p) {
		boolean flag = false;
		ControlloreMovimento.conversione(comando);

		if (ControlloreMovimento.contrTurno(p) && ControlloreMovimento.contrArrivoLibero(p)
				&& ControlloreMovimento.contrSpostamento(p)) {

			p.getInstanceDamiera().setMatrice(rInizio, cInizio, Colore.NULLO, TipoPezzo.NULLO);

			if (p.getTurno() == Colore.BIANCO) {

				p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.BIANCO, TipoPezzo.PEDINA);
				flag = true;

			} else {
				p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.NERO, TipoPezzo.PEDINA);
				flag = true;

			}
			setDamatura(p);
		}

		return flag;
	}

	/*
	 * spostamento con presa semplice
	 */
	public static boolean spostamentoPresa(final String comando, final Partita p) {

		boolean flag = false;
		ControlloreMovimento.conversioneComandoPresa(comando);
		Pezzo pedina = new Pezzo();

		if (ControlloreMovimento.contrTurno(p) && ControlloreMovimento.contrArrivoLibero(p)
				&& ControlloreMovimento.contrPresaSemplice(p)) {
			p.getInstanceDamiera().setMatrice(rInizio, cInizio, Colore.NULLO, TipoPezzo.NULLO);

			if (p.getTurno() == Colore.BIANCO) {

				p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.BIANCO, TipoPezzo.PEDINA);
				flag = true;

				pedina.setPezzo(Colore.NERO, TipoPezzo.PEDINA);

				p.aggiungiPezzo(pedina);

			} else {
				p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.NERO, TipoPezzo.PEDINA);
				flag = true;

				pedina.setPezzo(Colore.BIANCO, TipoPezzo.PEDINA);

				p.aggiungiPezzo(pedina);

			}
			setDamatura(p);

		}
		mangiapezzo(p);
		return flag;
	}

	/*
	 *  spostamento presa multipla
     */

	/**
     *
     * @param comando
     * @param p
     * @return
     */
	public static boolean spostamentoPresaSC(final String comando, final Partita p) {
		boolean flag = false;
		ControlloreMovimento.conversioneComandoPresa(comando);
		Pezzo pedina = new Pezzo();

		p.getInstanceDamiera().setMatrice(rInizio, cInizio, Colore.NULLO, TipoPezzo.NULLO);
		if (p.getTurno() == Colore.BIANCO) {

			p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.BIANCO, TipoPezzo.PEDINA);
			flag = true;

			pedina.setPezzo(Colore.NERO, TipoPezzo.PEDINA);

			p.aggiungiPezzo(pedina);

		} else {
			p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.NERO, TipoPezzo.PEDINA);
			flag = true;

			pedina.setPezzo(Colore.BIANCO, TipoPezzo.PEDINA);

			p.aggiungiPezzo(pedina);

		}
		mangiapezzo(p);

		return flag;
	}

    /**
     *
     * @param p
     */
    public static void mangiapezzo(final Partita p) {

		int rigaPezzoMangiatoBianco = rInizio + 1;
		int colonnaPezzoMangiatoDx = cInizio + 1;
		int colonnaPezzoMangiatoSx = cInizio - 1;
		int rigaPezzoMangiatoNero = rInizio - 1;

		if (p.getTurno() == Colore.NERO && rigaPezzoMangiatoBianco > -1
				&& rigaPezzoMangiatoBianco < Numero.N8) {

			if (colonnaPezzoMangiatoSx > -1) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoBianco,
						colonnaPezzoMangiatoSx)
						== Colore.BIANCO) && (cInizio - 2 == cDest)) {
					p.getInstanceDamiera().setMatrice(rigaPezzoMangiatoBianco,
							colonnaPezzoMangiatoSx,	Colore.NULLO,
							TipoPezzo.NULLO);

				}
			}

			if (colonnaPezzoMangiatoDx < Numero.N8) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoBianco,
						colonnaPezzoMangiatoDx)	== Colore.BIANCO)
						&& (cInizio + 2 == cDest)) {

					p.getInstanceDamiera().setMatrice(rigaPezzoMangiatoBianco,
							colonnaPezzoMangiatoDx,	Colore.NULLO, TipoPezzo.NULLO);
				}
			}
		}

		if (p.getTurno() == Colore.BIANCO && rigaPezzoMangiatoNero < Numero.N8
				&& rigaPezzoMangiatoNero > -1) {
			if (colonnaPezzoMangiatoSx > -1) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoNero,
						colonnaPezzoMangiatoSx) == Colore.NERO)
						&& (cInizio - 2 == cDest)) {
					p.getInstanceDamiera().setMatrice(rigaPezzoMangiatoNero, colonnaPezzoMangiatoSx,
							Colore.NULLO, TipoPezzo.NULLO);

				}
			}

			if (colonnaPezzoMangiatoDx < Numero.N8) {
				if ((p.getInstanceDamiera().getColoreMatrice(rigaPezzoMangiatoNero,
						colonnaPezzoMangiatoDx) == Colore.NERO)
						&& (cInizio + 2 == cDest)) {

					p.getInstanceDamiera().setMatrice(rigaPezzoMangiatoNero,
							colonnaPezzoMangiatoDx, Colore.NULLO, TipoPezzo.NULLO);
				}
			}
		}

	}

    /**
     *
     * @param comando
     * @param p
     * @return
     */
    public static boolean spostamentoPresaMultipla(final String comando, final Partita p) {
		boolean flag = false, flag1 = false, flag2 = false;

		String comando1, comando2;
		String[] subs = comando.split("x");
		String a, b, c;
		a = subs[0];
		b = subs[1];
		c = subs[2];

		comando1 = a + 'x' + b;
		comando2 = b + 'x' + c;

		ControlloreMovimento.conversioneComandoPresa(comando1);
		if (ControlloreMovimento.contrTurno(p) && ControlloreMovimento.contrArrivoLibero(p)
				&& ControlloreMovimento.contrPresaSemplice(p)) {
			flag1 = true;
		}

		ControlloreMovimento.conversioneComandoPresa(comando2);
		if (ControlloreMovimento.contrArrivoLibero(p) && ControlloreMovimento.contrPresaSemplice(p)) {
			flag2 = true;
		}

		if (flag1 && flag2) {
			flag = ControlloreMovimento.spostamentoPresaSC(comando1, p);
			flag = ControlloreMovimento.spostamentoPresaSC(comando2, p);
			setDamatura(p);
		}

		return flag;
	}

	public static void setDamatura(final Partita p) {
		if (p.getTurno() == Colore.BIANCO && rDest == 0) {
			p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.BIANCO, TipoPezzo.DAMA);
		}

		if (p.getTurno() == Colore.NERO && rDest == Numero.N7) {
			p.getInstanceDamiera().setMatrice(rDest, cDest, Colore.NERO, TipoPezzo.DAMA);
		}

	}

}
