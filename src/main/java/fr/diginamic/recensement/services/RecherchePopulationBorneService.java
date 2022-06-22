package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.AppException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws AppException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		
		boolean choixKo = !choix.matches("^[0-9]+$");
		if(choixKo) {
			throw new AppException("Le numéro de département doit être un nombre !");
		}

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		boolean minOrMaxNotInt = !saisieMin.matches("^[0-9]+$") || !saisieMax.matches("^[0-9]+$");
		if(minOrMaxNotInt) {
			throw new AppException("Le min et le max doivent être des nombres !");
		}
		
		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		
		boolean minOrMaxKo = min < 0 || max < 0 || min > max;
		if(minOrMaxKo) {
			throw new AppException("Min et max incohérents !");
		}
		
		
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
	}

}
