package it.uniba.test;

import org.junit.jupiter.api.Test;
import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniba.main.entity.Damiera;
import it.uniba.main.gestorePartita.Partita;
import it.uniba.main.gestorePartita.Spostamento;

public class SpostamentoPedinaTest {

	@Test
	void testSpostamentoConsentitoBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		assertTrue(Partita.eseguiMossa("22-19", partita));
		assertTrue(!(partita.getMosseEffettuate().isEmpty()));

	}

	@Test
	void testSpostamentoConsentitoNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		assertTrue(Partita.eseguiMossa("22-19", partita));
		assertTrue(Partita.eseguiMossa("9-13", partita));
		assertTrue(!(partita.getMosseEffettuate().isEmpty()));

	}

	// caso di test per turno errato nel caso in cui il bianco digitasse un comando
	// del nero
	@Test
	void testTurnoErratoBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		assertFalse(Partita.eseguiMossa("9-13", partita));

	}

	// caso di test per turno errato nel caso in cui il nero digitasse un comando
	// del bianco
	@Test
	void testTurnoErratoNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();
		Partita.eseguiMossa("21-17", partita);

		assertFalse(Partita.eseguiMossa("22-18", partita));

	}

	// caso di test per casella non esistente
	@Test
	void testCasellaNonEsistente() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		assertFalse(Partita.eseguiMossa("10-33", partita));

	}

	// caso di test per spostamento in cui casella arrivo è uguale a casella di
	// partenza
	@Test
	void testSpostamentoSuPosto() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		assertFalse(Partita.eseguiMossa("21-21", partita));

	}

	// caso di test per mossa in verticale
	@Test
	void testMossaInVerticale() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		assertFalse(Partita.eseguiMossa("22-14", partita));

	}

	// caso di test per mossa in orizzontale
	@Test
	void testMossaInOrizzontale() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-18", partita);
		assertFalse(Partita.eseguiMossa("18-19", partita));

	}

	// caso di test posizione d'arrivo occupata
	@Test
	void testPosizioneArrivoOccupata() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("22-18", partita);
		Partita.eseguiMossa("9-13", partita);

		assertFalse(Partita.eseguiMossa("18-13", partita));

	}

	// test pedina nera spostamento in direzione contraria
	@Test
	void testSpostamentoIndietroNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("22-19", partita);

		assertFalse(Partita.eseguiMossa("14-10", partita));

	}

	// test pedina bianca spostamento in direzione contraria
	@Test
	void testSpostamentoIndietroBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("10-14", partita);

		assertFalse(Partita.eseguiMossa("17-21", partita));

	}

}