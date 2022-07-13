package it.uniba.main.entity;

import it.uniba.main.costanti.Colore;
import it.uniba.main.costanti.Numero;
import it.uniba.main.costanti.TipoPezzo;

/**
 * Tipologia della classe: <b><< entity class >></b><br>
 * Responsabilita' della classe: provvede alla definizione e inizializzazione
 * della damiera di gioco
 */
public class Damiera {

	static final int DIM_MATRICE = 8;
	private Pezzo[][] matrice = new Pezzo[DIM_MATRICE][DIM_MATRICE];

    /**
     * costruttore dell'oggetto Damiera
     */
	public Damiera() {

		initDamiera();
	}

    /**
     * inizializza la damiera disponendo i pezzi secondo le regole del gioco
     */
    public void initDamiera() {


		for (int i = 0; i < DIM_MATRICE; i++) {
			for (int j = 0; j < DIM_MATRICE; j++) {
				matrice[i][j] = new Pezzo();
				matrice[i][j].setPezzo(Colore.NULLO, TipoPezzo.NULLO);
			}

		}
		for (int i = 0; i < Numero.N3; i++) {
			for (int j = 0; j < DIM_MATRICE; j++) {
				if ((i + j) % 2 == 0) {

					matrice[i][j].setPezzo(Colore.NERO, TipoPezzo.PEDINA);
				}
			}
		}
		for (int i = Numero.N7; i > Numero.N4; i--) {
			for (int j = 0; j < DIM_MATRICE; j++) {
				if ((i + j) % 2 == 0) {
					matrice[i][j].setPezzo(Colore.BIANCO, TipoPezzo.PEDINA);
				}
			}
		}

	}

	/** metodo utile per poter impostare un determinato colore e tipo ad una
	 *  particolare casella della damiera
     * @param riga
     * @param colonna
     * @param tipo
     * @param colore
	 */
	public void setMatrice(final int riga, final int colonna, final Colore colore, final TipoPezzo tipo) {
		matrice[riga][colonna].setPezzo(colore, tipo);
	}

    /**
     * metodo che restituisce la pedina presente sulla casella selezionata
     * @param riga
     * @param colonna
     * @return
     */
    public Colore getColoreMatrice(final int riga, final int colonna) {
		return matrice[riga][colonna].getColore();
	}

    /**
    * restituisce il tipo del pezzo
    * @param riga
    * @param colonna
    * @return
    */
	public TipoPezzo getTipoPezzo(final int riga, final int colonna) {
		return matrice[riga][colonna].getTipo();
	}

}
