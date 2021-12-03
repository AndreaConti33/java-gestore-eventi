package org.generation.italy.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

	// Attributi
	private String titolo;
	private LocalDate data;
	private int postiTotale;
	private int postiPrenotati;

	// Costruttore
	public Evento(String titolo, LocalDate data, int postiTotale) throws Exception {
		this.titolo = titolo;
		if (data.isAfter(LocalDate.now())) {
			this.data = data;
		} else {
			throw new Exception("La data non può essere precedente a quella odierna.");
		}
		if (postiTotale > 0) {
			this.postiTotale = postiTotale;
		} else {
			throw new IllegalArgumentException("Il valore deve essere positivo.");
		}
		this.postiPrenotati = 0;
	}

	// Getters and Set
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getNumeroTotale() {
		return postiTotale;
	}

	public int getNumeroPrenotati() {
		return postiPrenotati;
	}

	// Metodi

	public void prenota() throws Exception {
		if (data.isBefore(LocalDate.now())) {
			throw new Exception("La data non può essere precedente a quella odierna.");
		} else if (postiPrenotati == postiTotale) {
			throw new Exception("Non ci sono posti prenotabili.");
		} else {
			postiPrenotati++;
		}
	}

	public void desdici() throws Exception {
		if (data.isBefore(LocalDate.now())) {
			throw new Exception("La data non può essere precedente a quella odierna.");
		} else if (postiPrenotati == 0) {
			throw new Exception("Non hai posti prenotati da disdire.");
		} else {
			postiPrenotati--;
		}
	}

	@Override
	public String toString() {
		String dataFormattata;
		DateTimeFormatter formdate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataFormattata = this.data.format(formdate);
		return dataFormattata + " - " + this.titolo;
	}

}
