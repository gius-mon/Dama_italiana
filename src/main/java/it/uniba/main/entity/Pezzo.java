package it.uniba.main.entity;

import it.uniba.main.costanti.Colore;
import it.uniba.main.costanti.TipoPezzo;

/**
 * Tipologia della classe: <b><< entity class >></b><br>
 * Responsabilità della classe:
 */
public class Pezzo {
	private TipoPezzo tipo;
	private Colore colore;

    /**
     * modifica il colore e il tipo di pedina in una casella
     * @param colorePezzo
     * @param tipoPezzo
     */
    public void setPezzo(final Colore colorePezzo, final TipoPezzo tipoPezzo) {
		setTipo(tipoPezzo);
		setColore(colorePezzo);
	}

    /**
     * imposta il tipo di una pedina
     * @param tipoPezzo
     */
    public void setTipo(final TipoPezzo tipoPezzo) {
		tipo = tipoPezzo;
	}

    /**
     * imposta il colore di una pedina
     * @param colorePezzo
     */
    public void setColore(final Colore colorePezzo) {
		colore = colorePezzo;
	}

    /**
     * restituisce il colore di una pedina
     * @return
     */
    public Colore getColore() {
		return colore;
	}

    /**
     * restituisce il tipo di una pedina
     * @return
     */
    public TipoPezzo getTipo() {
		return tipo;
	}

}
