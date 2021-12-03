package org.generation.italy.eventi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean operazioneIncorso = false;
		String sceltaUtenteNegativa = "n";
		String sceltaUtentePositiva = "s";
		String sceltaUtente;

		System.out.println("Digitare il titolo dell'evento: ");
		String inputTitolo = scanner.nextLine();
		System.out.print("Inserisci il giorno dell'evento: ");
		int giorno = scanner.nextInt();
		System.out.print("Inserisci il mese: ");
		int mese = scanner.nextInt();
		System.out.print("Inserisci l'anno: ");
		int anno = scanner.nextInt();
		LocalDate dataEvento = LocalDate.of(anno, mese, giorno);
		System.out.println("Inserire numero massimo di partecipanti: ");
		int inputPartecipanti = scanner.nextInt();

		try {
			Evento evento = new Evento(inputTitolo, dataEvento, inputPartecipanti);
			System.out.println("Hai creato un nuovo evento di nome: " + inputTitolo + ", che si svolegerà il:  "
					+ dataEvento + ", con una capienza massima di " + inputPartecipanti + " partecipanti.");
			do {
				System.out.println("Vuoi fare una prenotazione? (s/n)");
				scanner.nextLine();
				sceltaUtente = scanner.nextLine().toLowerCase();
				if (sceltaUtente.equals(sceltaUtentePositiva)) {
					operazioneIncorso = true;
				} else if (sceltaUtente.equals(sceltaUtenteNegativa)) {
					System.out.println("Arrivederci");
					main(args);
				} else {
					System.out.println("Attenzione, inserire un valore valido (s/n).");
				}
			} while (operazioneIncorso == false);
			System.out.println("Quante prenotazioni vuoi fare?");
			int prenotazioniUtente = scanner.nextInt();
			for (int i = 0; i < prenotazioniUtente; i++) {
				evento.prenota();
			}
			System.out.println("Hai " + (evento.getNumeroTotale() - evento.getNumeroPrenotati())
					+ " posti disponibili e " + evento.getNumeroPrenotati() + " prenotati.");

			operazioneIncorso = false;

			do {
				System.out.println("Vuoi disdire una prenotazione? (s/n)");
				scanner.nextLine();
				sceltaUtente = scanner.nextLine().toLowerCase();
				if (sceltaUtente.equals(sceltaUtentePositiva)) {
					operazioneIncorso = true;
				} else if (sceltaUtente.equals(sceltaUtenteNegativa)) {
					System.out.println("Arrivederci");
					main(args);
				} else {
					System.out.println("Attenzione, inserire un valore valido (s/n).");
				}
			} while (operazioneIncorso == false);
			System.out.println("Quante prenotazioni vuoi disdire?");
			int disdireUtente = scanner.nextInt();
			for (int i = 0; i < disdireUtente; i++) {
				evento.desdici();
			}
			System.out.println("Hai disdetto " + disdireUtente + " prenotazioni, al momento ci sono "
					+ (evento.getNumeroTotale() - evento.getNumeroPrenotati()) + " posti disponibili e "
					+ evento.getNumeroPrenotati() + " prenotati.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		scanner.close();
	}

}
