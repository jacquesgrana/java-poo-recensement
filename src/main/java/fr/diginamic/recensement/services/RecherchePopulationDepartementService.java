package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.AppException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.utils.RecensementUtils;

/** Recherche et affichage de la population d'un département
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationDepartementService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws AppException {
		
		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		
		//if (!NumberUtils.isDigits(choix)) {
		
		if (choix.matches("[0-9]*[1-9]+$|^[1-9]+[0-9]*$")) {
			throw new RuntimeException("Le département doit être un entier.");
		}
		
		List<Ville> villes = rec.getVilles();
		int somme = 0;
		for (Ville ville: villes){
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)){
				somme+=ville.getPopulation();
			}
		}
		if (somme>0){
			System.out.println("Population du département "+choix+" : "+ somme);
		}
		else {
			throw new AppException("Département "+choix+ " non trouvé.");
			//System.out.println("Département "+choix+ " non trouvé.");
		}
	}

}
