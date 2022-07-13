package it.uniba.main.gestorePartita;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


/**
 * Tipologia della classe: <b> << control class >> </b> <br>
 * Responsabilita'  della classe: Gestire la funzionalita' tempo
 */
public class Tempo {
	private LocalTime tempoInizioTurno;
	private long tempoGiocatore;
	private long tempoTurno;

    /**
     * @param tempoGiocatore
     * @param tempoTurno
     */
    public Tempo() {
		setTempoInizioTurno();
		this.tempoGiocatore = 0;
		this.tempoTurno = 0;
	}

    /**
     * @param tempoInizioTurno
     */
    public void setTempoInizioTurno() {
		tempoInizioTurno = LocalTime.now();
	}

    /**
     * @return
     */
    public long getTempoGiocatore() {
		return tempoGiocatore;
	}

	/*
	 *  aggiunge al tempo totale del giocatore il tempo del turno
	 */

    /**
     *
     */
	public void addTempoGiocatore() {
		calcolaTempo();
		tempoGiocatore = tempoGiocatore + tempoTurno;
	}

	/*
	 *  calcola il tempo del turno
	 */

	/**
     * @param tempoTurno
     */
	public void calcolaTempo() {
		tempoTurno = ChronoUnit.SECONDS.between(tempoInizioTurno, LocalTime.now());

	}

    /**
    * @param tempoTurno
    * return
    */
	public long getTempoTurno() {
		return tempoTurno;
	}

    /**
     * @param tempoInizioTurno
     * @param tempoGiocatore
     * @param tempoTurno
     */
    public void azzeratempo() {
		tempoInizioTurno = null;
		tempoGiocatore = 0;
		tempoTurno = 0;

	}

}
