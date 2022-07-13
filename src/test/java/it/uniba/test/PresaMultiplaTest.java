package it.uniba.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniba.main.costanti.Colore;
import it.uniba.main.costanti.TipoPezzo;

import it.uniba.main.gestorePartita.Partita;

public class PresaMultiplaTest {

	// caso di test per verificare che la presa multipla del bianco sia corretta
	@Test
	void testPresaMultiplaBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-18", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("18x9", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("25-21", partita);
		Partita.eseguiMossa("5-10", partita);
		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("14-19", partita);

		assertTrue(Partita.eseguiMossa("23x14x5", partita));

	}

	// test per verificare che la presa multipla del nero sia corretta
	@Test
	void testPresaMultiplaNero() {

		Partita partita = Partita.getInstance();

		partita.initPartita();

		Partita.eseguiMossa("22-18", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("18x9", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("23-19", partita);
		Partita.eseguiMossa("5-10", partita);
		Partita.eseguiMossa("24-20", partita);
		Partita.eseguiMossa("10-13", partita);
		Partita.eseguiMossa("28-24", partita);
		Partita.eseguiMossa("1-5", partita);
		Partita.eseguiMossa("32-28", partita);

		assertTrue(Partita.eseguiMossa("14x23x32", partita));
		assertEquals(TipoPezzo.DAMA, partita.getDamiera().getTipoPezzo(7, 7));
	}

	// test per verificare che la pedina nera diventi dama quando raggiunge base
	// avversaria
	@Test
	void testDamaturaNero() {
		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("24-20", partita);
		Partita.eseguiMossa("12-16", partita);
		Partita.eseguiMossa("23-19", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("22-18", partita);
		Partita.eseguiMossa("13-17", partita);
		Partita.eseguiMossa("26-22", partita);
		Partita.eseguiMossa("11-15", partita);
		Partita.eseguiMossa("30-26", partita);

		assertTrue(Partita.eseguiMossa("16x23x30", partita));
		assertEquals(TipoPezzo.DAMA, partita.getDamiera().getTipoPezzo(7, 3));
	}

	// test per verificare che la pedina bianca diventi dama quando raggiunge base
	// avversaria

	@Test
	void testDamaturaBianco() {
		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("26-21", partita);
		Partita.eseguiMossa("12-15", partita);
		Partita.eseguiMossa("24-20", partita);
		Partita.eseguiMossa("7-12", partita);
		Partita.eseguiMossa("23-19", partita);
		Partita.eseguiMossa("3-7", partita);
		Partita.eseguiMossa("20-16", partita);
		Partita.eseguiMossa("10-14", partita);

		assertTrue(Partita.eseguiMossa("17x10x3", partita));
		assertTrue(!partita.getPezziCatturati().isEmpty());
		assertEquals(TipoPezzo.DAMA, partita.getDamiera().getTipoPezzo(0, 4));
	}

	/*
	 * test per verifcare che nessuna pedina venga spostata in caso di presa
	 * multipla parzialmente corretta
	 */
	@Test
	void testPresaMultiplaCorrettaParzialmente() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("26-21", partita);
		Partita.eseguiMossa("12-15", partita);
		Partita.eseguiMossa("24-20", partita);
		Partita.eseguiMossa("7-12", partita);
		Partita.eseguiMossa("23-19", partita);
		Partita.eseguiMossa("3-7", partita);
		Partita.eseguiMossa("20-16", partita);
		Partita.eseguiMossa("10-14", partita);

		assertFalse(Partita.eseguiMossa("17x10x23", partita));
		assertEquals(TipoPezzo.NULLO, partita.getDamiera().getTipoPezzo(2, 2));
		assertEquals(TipoPezzo.PEDINA, partita.getDamiera().getTipoPezzo(4, 0));
		// verifico che nessuna presa sia stata fatta
		assertTrue(partita.getPezziCatturati().isEmpty());

	}

	/*
	 * test per verificare presa multipla che mangia prima da dx e poi da sx
	 */
	@Test
	void testPresaMultiplaDirezioniDiverseCorretta() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-17", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("26-21", partita);
		Partita.eseguiMossa("12-15", partita);
		Partita.eseguiMossa("24-20", partita);
		Partita.eseguiMossa("7-12", partita);
		Partita.eseguiMossa("23-19", partita);
		Partita.eseguiMossa("3-7", partita);
		Partita.eseguiMossa("20-16", partita);
		Partita.eseguiMossa("10-14", partita);
		Partita.eseguiMossa("30-26", partita);

		assertTrue(Partita.eseguiMossa("14x23x30", partita));
		assertTrue(!(partita.getMosseEffettuate().isEmpty()));
		assertEquals(TipoPezzo.NULLO, partita.getDamiera().getTipoPezzo(3, 3));
		assertEquals(TipoPezzo.DAMA, partita.getDamiera().getTipoPezzo(7, 3));

	}

	/*
	 * test per verificare presa multipla che mangia pedina stesso colore e poi
	 * presa consentita a sx
	 */
	@Test
	void testPresaMultiplaErrata() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		partita.getDamiera().setMatrice(3, 3, Colore.NULLO, TipoPezzo.NULLO);
		partita.getDamiera().setMatrice(1, 5, Colore.NULLO, TipoPezzo.NULLO);

		assertFalse(Partita.eseguiMossa("30x14x7", partita));
		assertTrue((partita.getPezziCatturati().isEmpty()));
		assertEquals(TipoPezzo.PEDINA, partita.getDamiera().getTipoPezzo(7, 3));
		assertEquals(TipoPezzo.NULLO, partita.getDamiera().getTipoPezzo(3, 3));
		assertEquals(TipoPezzo.PEDINA, partita.getDamiera().getTipoPezzo(2, 4));
		assertEquals(TipoPezzo.NULLO, partita.getDamiera().getTipoPezzo(1, 5));

	}

	// Test presa multipla bianco corretta
	@Test
	void testPresaMultiplaDiagonaleCorrettaBianco() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-18", partita);
		Partita.eseguiMossa("10-14", partita);
		partita.getDamiera().setMatrice(2, 4, Colore.NULLO, TipoPezzo.NULLO);
		partita.getDamiera().setMatrice(0, 6, Colore.NULLO, TipoPezzo.NULLO);

		assertTrue(Partita.eseguiMossa("18x11x4", partita));
		assertEquals(TipoPezzo.DAMA, partita.getDamiera().getTipoPezzo(0, 6));

	}

	// Test presa multipla nero corretta
	@Test
	void testPresaMultiplaDiagonaleCorrettaNero() {

		Partita partita = Partita.getInstance();
		partita.initPartita();

		Partita.eseguiMossa("21-18", partita);
		Partita.eseguiMossa("9-13", partita);
		Partita.eseguiMossa("23-20", partita);
		partita.getDamiera().setMatrice(5, 3, Colore.NULLO, TipoPezzo.NULLO);
		partita.getDamiera().setMatrice(7, 5, Colore.NULLO, TipoPezzo.NULLO);

		assertTrue(Partita.eseguiMossa("13x22x31", partita));
		assertEquals(TipoPezzo.DAMA, partita.getDamiera().getTipoPezzo(7, 5));

	}

}
