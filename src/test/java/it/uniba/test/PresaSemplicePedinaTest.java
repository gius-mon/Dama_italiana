package it.uniba.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.uniba.main.gestorePartita.Partita;

public class PresaSemplicePedinaTest {

	// caso di test per presa bianco in orizzontale
	@Test
	void testPresaOrizzontaleBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("23-20", partita);
		Partita.eseguiMossa("14-18", partita);

		assertFalse(Partita.eseguiMossa("17x19", partita));

	}

	// caso di test per presa nera in orizzontale
	@Test
	void testPresaOrizzontaleNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-18", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("23-20", partita);
		Partita.eseguiMossa("14-19", partita);

		assertFalse(Partita.eseguiMossa("19x17", partita));

	}

	// caso di test per verificare che una presa sia effettuata correttamente
	@Test
	void testPresaSempliceCorrettaBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-18", partita);
		Partita.eseguiMossa("9-13", partita);

		assertTrue(Partita.eseguiMossa("18x9", partita));
		// verifico che il pezzo venga aggiunto alla lista dei pezzi catturati
		assertTrue(!partita.getPezziCatturati().isEmpty());
	}

	@Test
	void testPresaSempliceCorrettaNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-18", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("22-19", partita);

		assertTrue(Partita.eseguiMossa("13x22", partita));

		// verifico che il pezzo venga aggiunto alla lista dei pezzi catturati
		assertTrue(!partita.getPezziCatturati().isEmpty());

	}

	// pedina nera prova a mangiare pedina nera
	@Test
	void testPresaNonConsentitaNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("24-20", partita);

		assertFalse(Partita.eseguiMossa("11x18", partita));

	}

	// pedina bianca prova a mangiare pedina bianca
	@Test
	void testPresaNonConsentitaBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("22-18", partita);
		Partita.eseguiMossa("12-16", partita);
		Partita.eseguiMossa("18-13", partita);
		Partita.eseguiMossa("16-20", partita);

		assertFalse(Partita.eseguiMossa("17x10", partita));

	}

	// pedina bianca prova a mangiare pedina nera in direzione sbagliata
	@Test
	void testPresaBiancoVersoOpposto() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("23-19", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("19x10", partita);
		Partita.eseguiMossa("9-13", partita);

		assertFalse(Partita.eseguiMossa("10x17", partita));

	}

	// pedina nera prova a mangiare pedina bianca in direzione sbagliata
	@Test
	void testPresaNeroVersoOpposto() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("22-18", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("24-20", partita);
		Partita.eseguiMossa("14-19", partita);
		Partita.eseguiMossa("20-16", partita);
		Partita.eseguiMossa("19-22", partita);
		Partita.eseguiMossa("23-20", partita);

		assertFalse(Partita.eseguiMossa("22x13", partita));

	}

	// caso di test per presa bianco in verticale
	@Test
	void testPresaVerticaleBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("17-13", partita);
		Partita.eseguiMossa("14-19", partita);
		Partita.eseguiMossa("24-20", partita);
		Partita.eseguiMossa("11-15", partita);

		assertFalse(Partita.eseguiMossa("27x11", partita));

	}

	// caso di test per presa nera in verticale
	@Test
	void testPresaVErticaleNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("22-18", partita);
		Partita.eseguiMossa("26-21", partita);

		assertFalse(Partita.eseguiMossa("10x26", partita));

	}

}