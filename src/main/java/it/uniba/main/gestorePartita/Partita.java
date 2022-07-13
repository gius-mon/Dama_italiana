package it.uniba.main.gestorePartita;

import java.util.ArrayList;
import java.util.List;

import it.uniba.main.costanti.Colore;
import it.uniba.main.costanti.RegExpMosse;
import it.uniba.main.entity.Damiera;
import it.uniba.main.entity.Pezzo;

/**
 * Tipologia della classe: <b> << control class >> </b> <br>
 * Responsabilita' della classe: consente di giocare la partita
 */
public final class Partita {

	private static Partita partita = new Partita();

	private boolean stato = false; // true se la partita � avviata
	private Colore turno;

	private List<Pezzo> pezziCatturati = new ArrayList<Pezzo>(); // pezzi catturati
	private List<String> mosseEffettuate = new ArrayList<>(); // mosse effettuate

	private Tempo tempoBianco = new Tempo();
	public Tempo getInstanceTempoBianco() {
		return this.tempoBianco;
	}
	private Tempo tempoNero = new Tempo();
	public Tempo getInstanceTempoNero() {
		return this.tempoNero;
	}
	private Damiera damiera = new Damiera();
	public Damiera getInstanceDamiera() {
		return this.getDamiera();
	}

	private Partita() {

	}

	public void initPartita() {
		damiera = new Damiera();
		pezziCatturati.clear();
		mosseEffettuate.clear();
		this.setStato(true);
		tempoBianco.setTempoInizioTurno();

		getDamiera().initDamiera();

		this.setTurno(Colore.BIANCO);

	 }

	public static Partita getInstance() {
		return partita;
	}

	public Colore getTurno() {
		return turno;
	}

	public void setTurno(final Colore coloreGiocatore) {
		turno = coloreGiocatore;
	}

	public boolean getStato() {
		return stato;
	}

	public void setStato(final boolean statoGiocatore) {
		stato = statoGiocatore;
	}

	public void cambiaTurno() {

		if (this.getTurno() == Colore.BIANCO) {
			this.setTurno(Colore.NERO);

		} else {
			this.setTurno(Colore.BIANCO);

		}

	}

	/**
	* @param comando
    */
	public void aggiungiMossa(final String comando) {

		char letteraTurno = 'x';

		if (this.getTurno() == Colore.BIANCO) {
			letteraTurno = 'B';
		} else {
			letteraTurno = 'N';
		}

		String mossa = letteraTurno + " " + comando;
		mosseEffettuate.add(mossa);

	}

	/**
	* @param pedina
    */
	public void aggiungiPezzo(final Pezzo pedina) {

		pezziCatturati.add(pedina);

	}

	/**
	* @param p
	* @param comando
	* return
    */
	public static boolean eseguiMossa(final String comando, final Partita p) {

		boolean mossaEseguita = false;

		if (comando.matches(RegExpMosse.SPOSTAMENTO_SEMPLICE)) {
			if (Spostamento.spostamentoSemplice(comando, p)) {
				mossaEseguita = true;

			}

		}

		if (comando.matches(RegExpMosse.SPOSTAMENTO_PRESA)) {
			if (Spostamento.spostamentoPresa(comando, p)) {
				mossaEseguita = true;
			}

		}

		if (comando.matches(RegExpMosse.SPOSTAMENTO_PRESA_MULTIPLA)) {
			if (Spostamento.spostamentoPresaMultipla(comando, p)) {
				mossaEseguita = true;

			}

		}

		if (mossaEseguita) {
			p.aggiungiMossa(comando);

			p.cambiaTurno();
			if (p.getTurno() == Colore.BIANCO) {
				p.tempoBianco.setTempoInizioTurno();
				p.tempoNero.addTempoGiocatore();
			} else if (p.getTurno() == Colore.NERO) {
				p.tempoNero.setTempoInizioTurno();
				p.tempoBianco.addTempoGiocatore();
			}
		}

		return mossaEseguita;
	}

	public List<Pezzo> getPezziCatturati() {
		return pezziCatturati;
	}

	public List<String> getMosseEffettuate() {
		return mosseEffettuate;
	}

	public Damiera getDamiera() {
		return damiera;
	}
}
