package it.uniba.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import it.uniba.main.gestorePartita.Partita;
import it.uniba.main.gestorePartita.Tempo;

class TempoTest {
	// Verifica corretto funzionamento del tempo
	// nel caso di un giocatore
	@Test
	void testTempoUnTurno() throws InterruptedException {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Tempo testTempo = new Tempo();
		TimeUnit.SECONDS.sleep(10);
		testTempo.addTempoGiocatore();

		assertEquals(10, testTempo.getTempoGiocatore());

	}

	// Verifico il corretto funzionamento del tempo su piu' turni
	@Test
	void testTempoPiuTurni() throws InterruptedException {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Tempo testTempoBianco = partita.getInstanceTempoBianco();
		Tempo testTempoNero = partita.getInstanceTempoNero();

		// turno bianco
		TimeUnit.SECONDS.sleep(10);
		testTempoBianco.addTempoGiocatore();
		assertEquals(10, testTempoBianco.getTempoTurno());
		assertEquals(10, testTempoBianco.getTempoGiocatore());
		partita.cambiaTurno();

		// turno nero
		testTempoNero.setTempoInizioTurno();
		TimeUnit.SECONDS.sleep(2);
		testTempoNero.addTempoGiocatore();
		assertEquals(2, testTempoNero.getTempoGiocatore());
		partita.cambiaTurno();

		// turno bianco
		testTempoBianco.setTempoInizioTurno();	
		TimeUnit.SECONDS.sleep(2);
		testTempoBianco.addTempoGiocatore();
		assertEquals(2, testTempoBianco.getTempoTurno());
		assertEquals(12, testTempoBianco.getTempoGiocatore());// tempo da inizio partita

	}

	@Test
	void testAzzeraTempo() throws InterruptedException {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Tempo testTempoBianco = new Tempo();
		TimeUnit.SECONDS.sleep(2);
		testTempoBianco.addTempoGiocatore();
		testTempoBianco.azzeratempo();
		assertEquals(0, testTempoBianco.getTempoGiocatore());
	}

}
