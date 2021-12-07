package org.generation.italy.eventi;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		// Attributi
		boolean operazioneIncorso = false;
		boolean intChecker = false;
		String sceltaUtenteNegativa = "n";
		String sceltaUtentePositiva = "s";
		String sceltaUtente;
		String inputTitolo;
		int x = 0;

		// Input utente "Titolo".

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

		// Input utente "Data evento".

		LocalDate dataEvento;
		do {
			int giorno = 0;
			int mese = 0;
			int anno = 0;

			// Input utente "Mese dell'evento".

			do {
				try {
					System.out.println("Inserisci il mese dell'evento: ");
					mese = scanner.nextInt();
					if (mese <= 0 || mese > 12) {
						System.out.println("Inserire un valore compreso tra \"1\" e \"12\".");
					} else {
						operazioneIncorso = true;
					}
				} catch (InputMismatchException ime) {
					System.out.println("Inserire un valore compreso tra \"1\" e \"12\".");
					scanner.nextLine();
				}
			} while (operazioneIncorso == false);
			operazioneIncorso = false;

			// Input utente "Giorno dell'evento".

			do {
				try {
					System.out.print("Inserisci il giorno dell'evento: ");
					giorno = scanner.nextInt();
					switch (mese) {
					case 2:
						x = 28;
						break;
					case 4:
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
					if (giorno <= 0 || giorno > x) {
						System.out.println("Inserire un valore compreso tra \"1\" e \"" + x + "\"");
					} else {
						operazioneIncorso = true;
					}
				} catch (InputMismatchException ime) {
					System.out.println("Inserire un valore compreso tra \"1\" e \"" + x + "\"");
					scanner.nextLine();
				}
			} while (operazioneIncorso == false);
			operazioneIncorso = false;

			// Input utente "Anno dell'evento".

			do {
				try {
					System.out.print("Inserisci l'anno: ");
					anno = scanner.nextInt();
					if (anno < 2021) {
						System.out.println("Impossibile inserire un anno precedente a quello attuale.");
					} else {
						operazioneIncorso = true;
					}
				} catch (InputMismatchException ime) {
					System.out.println("Valore non valido.");
					scanner.nextLine();
				}
			} while (operazioneIncorso == false);
			operazioneIncorso = false;
			dataEvento = LocalDate.of(anno, mese, giorno);
			if (dataEvento.isBefore(LocalDate.now())) {
				System.out.println("La data è già passata, reionserire la data.");
			} else {
				operazioneIncorso = true;
			}
		} while (operazioneIncorso == false);
		operazioneIncorso = false;

		// Input utente "Capienza dell'evento".

		int inputPartecipanti = 0;

		do {
			try {
				System.out.println("Inserire numero massimo di partecipanti: ");
				inputPartecipanti = scanner.nextInt();
				if (inputPartecipanti <= 0 || inputPartecipanti > 999) {
					System.out.println("Inserire un valore compreso tra \"1\" e \"999\".");
				} else {
					operazioneIncorso = true;
				}
			} catch (InputMismatchException ime) {
				System.out.println("Inserire un valore compreso tra \"1\" e \"999\".");
				scanner.nextLine();
			}
		} while (operazioneIncorso == false);
		operazioneIncorso = false;

		// Creazione evento.

		Evento evento = new Evento(inputTitolo, dataEvento, inputPartecipanti);

		// Input utente per "Richiesta per eventuali prenotazioni all'evento".

		scanner.nextLine(); // Pulizia buffer dello scanner.

		int prenotazioniUtente = 0;

		do {
			System.out.println("Vuoi fare una prenotazione? (s/n)");
			sceltaUtente = scanner.nextLine();
			sceltaUtente.toLowerCase();
			if (sceltaUtente.equals(sceltaUtentePositiva)) {

				// Input utente "Eventuali prenotazioni".

				prenotazioniUtente = 0;

				do {
					try {
						System.out.println("Quante prenotazioni vuoi fare?");
						prenotazioniUtente = scanner.nextInt();
						if (prenotazioniUtente <= 0 || prenotazioniUtente > inputPartecipanti) {
							System.out
									.println("Inserire una valore compreso tra \"1\" e \"" + inputPartecipanti + "\".");
						} else {
							intChecker = true;
						}
					} catch (InputMismatchException ime) {
						System.out.println("Valore non valido, inserire una valore compreso tra \"1\" e \""
								+ inputPartecipanti + "\".");
						scanner.nextLine();
					}
				} while (intChecker == false);
				intChecker = false;
				for (int i = 0; i < prenotazioniUtente; i++) {
					evento.prenota();
				}
				operazioneIncorso = true;
				System.out.println("Hai " + (evento.getNumeroTotale() - evento.getNumeroPrenotati())
						+ " posti disponibili e " + evento.getNumeroPrenotati() + " prenotati.");
				scanner.nextLine();
			} else if (sceltaUtente.equals(sceltaUtenteNegativa)) {
				operazioneIncorso = true;
			} else if (sceltaUtente == null || sceltaUtente.length() == 0) {
				System.out.println("Valore nullo non accettabile.");
			} else {
				System.out.println("Attenzione, inserire un valore valido.");
			}
		} while (operazioneIncorso == false);
		operazioneIncorso = false;

		// Input utente per "Richiesta per disdire prenotazioni".

		do {
			System.out.println("Vuoi disdire una prenotazione? (s/n)");
			sceltaUtente = scanner.nextLine().toLowerCase();
			if (sceltaUtente.equals(sceltaUtentePositiva) && evento.getNumeroPrenotati() == 0) {
				System.out.println("Attualmente non ci sono posti prenotati.");
				operazioneIncorso = true;
			} else if (sceltaUtente.equals(sceltaUtentePositiva) && evento.getNumeroPrenotati() != 0) {
				int disdireUtente = 0;

				do {
					try {
						System.out.println("Quante prenotazioni vuoi disdire?");
						disdireUtente = scanner.nextInt();
						if (disdireUtente <= 0 || disdireUtente > evento.getNumeroPrenotati()) {
							System.out
									.println("Inserire un valore compreso tra \"1\" e \"" + prenotazioniUtente + "\".");
						} else {
							intChecker = true;
						}
					} catch (InputMismatchException ime) {
						System.out.println("Valore non valido, inserire un valore compreso tra \"1\" e \""
								+ prenotazioniUtente + "\".");
						scanner.nextLine();
					}
				} while (intChecker == false);
				intChecker = false;
				for (int i = 0; i < disdireUtente; i++) {
					evento.disdici();
				}
				System.out.println("Hai disdetto " + disdireUtente + " prenotazioni ed al momento ci sono "
						+ (evento.getNumeroTotale() - evento.getNumeroPrenotati()) + " posti disponibili e "
						+ evento.getNumeroPrenotati() + " prenotati.");
				operazioneIncorso = true;
			} else if (sceltaUtente.equals(sceltaUtenteNegativa)) {
				System.out.println("Arrivederci.");
				operazioneIncorso = true;
			} else if (sceltaUtente == null || sceltaUtente.length() == 0) {
				System.out.println("Valore nullo non accettabile.");
			} else {
				System.out.println("Attenzione, inserire un valore valido (s/n).");
			}
		} while (operazioneIncorso == false);
		operazioneIncorso = false;

		// Stampa Finale

		System.out.println("Hai creato un nuovo evento di nome: " + inputTitolo + ", che si svolegerà il:  "
				+ dataEvento + ", con una capienza massima di " + inputPartecipanti + " partecipanti.");
		System.out.println("Grazie per aver usato il mio programma.");

		scanner.close();
	}

}
