package org.generation.italy.eventi;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean operazioneIncorso = false;
		String sceltaUtenteNegativa = "n";
		String sceltaUtentePositiva = "s";
		String sceltaUtente;

		String inputTitolo;
		do {
			System.out.println("Digitare il titolo dell'evento: ");
			inputTitolo = scanner.nextLine();
			if (inputTitolo == null || inputTitolo.length() == 0) {
				System.out.println("Valore nullo non accettabile.");
			} else {
				operazioneIncorso = true;
			}
		} while (operazioneIncorso == false);
		operazioneIncorso = false;
		int giorno;
		int mese = 0;
		int anno;
		int x;
		do {
			System.out.print("Inserisci il mese: ");
			mese = scanner.nextInt();
			if (mese <= 0 || mese > 12) {
				System.out.println("Inserire un valore compreso tra \"1\" e \"12\".");
			} else {
				operazioneIncorso = true;
			}
		} while (operazioneIncorso == false);
		operazioneIncorso = false;
		do {
			System.out.print("Inserisci il giorno dell'evento: ");
			giorno = scanner.nextInt();
			switch (mese) { // assegnamo di default 31 giorni, il pi comodo
			case 2:
				x = 28; // se febbraio massimo 28 giorni
				break;
			case 4: // se aprile, giugno settembre, novembre 30 giorni altrimenti rimane 31
				x = 30;
				break;
			case 6:
				x = 30;
				break;
			case 9:
				x = 30;
				break;
			case 11:
				x = 30;
				break;
			default:
				x = 31;
				break;
			}
			if (giorno < 0 || giorno > x) {
				System.out.println("Inserire un valore compreso tra \"1\" e \"" + x + "\"");
			} else {
				operazioneIncorso = true;
			}
		} while (operazioneIncorso == false);

		operazioneIncorso = false;
		do {
			System.out.print("Inserisci l'anno: ");
			anno = scanner.nextInt();
			if (anno < 2021) {
				System.out.println("Impossibile inserire un anno precedente a quello attuale.");
			} else {
				operazioneIncorso = true;
			}
		} while (operazioneIncorso == false);
		operazioneIncorso = false;
		LocalDate dataEvento = LocalDate.of(anno, mese, giorno);
		int inputPartecipanti;
		do {
			System.out.println("Inserire numero massimo di partecipanti: ");
			inputPartecipanti = scanner.nextInt();
			if (inputPartecipanti <= 0 || inputPartecipanti > 999) {
				System.out.println("Inserire un valore compreso tra \"1\" e \"999\".");
			} else {
				operazioneIncorso = true;
			}
		} while (operazioneIncorso == false);

		operazioneIncorso = false;
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
				} else if (sceltaUtente == null || sceltaUtente.length() == 0) {
					System.out.println("Valore nullo non accettabile.");
				} else {
					System.out.println("Attenzione, inserire un valore valido (s/n).");
				}
			} while (operazioneIncorso == false);
			operazioneIncorso = false;
			int prenotazioniUtente;
			do {
				System.out.println("Quante prenotazioni vuoi fare?");
				prenotazioniUtente = scanner.nextInt();
				if (prenotazioniUtente <= 0 || prenotazioniUtente > inputPartecipanti) {
					System.out.println("Inserire una valore compreso tra \"1\" e " + inputPartecipanti + ".");
				} else {
					operazioneIncorso = true;
				}
			} while (operazioneIncorso == false);
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
				} else if (sceltaUtente == null || sceltaUtente.length() == 0) {
					System.out.println("Valore nullo non accettabile.");
				} else {
					System.out.println("Attenzione, inserire un valore valido (s/n).");
				}
			} while (operazioneIncorso == false);
			operazioneIncorso = false;
			int disdireUtente;
			do {
				System.out.println("Quante prenotazioni vuoi disdire?");
				disdireUtente = scanner.nextInt();
				if (disdireUtente <= 0 || disdireUtente > evento.getNumeroPrenotati()) {
					System.out.println("Inserire un valore compreso tra \"1\" e " + prenotazioniUtente + ".");
				} else {
					operazioneIncorso = true;
				}
			} while (operazioneIncorso == false);
			for (int i = 0; i < disdireUtente; i++) {
				evento.disdici();
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
