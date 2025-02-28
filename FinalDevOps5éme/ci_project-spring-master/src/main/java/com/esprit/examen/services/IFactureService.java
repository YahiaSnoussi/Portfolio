package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import com.esprit.examen.entities.Facture;

public interface IFactureService {
	List<Facture> retrieveAllFactures();

	List<Facture> getFacturesByFournisseur(Long idFournisseur);
	
	Facture addFacture(Facture f);

	void deleteFacture(Long id);

	Facture retrieveFacture(Long id);


	Facture updateFacture(Facture o);
	
	void assignOperateurToFacture(Long idOperateur, Long idFacture);

	float pourcentageRecouvrement(Date startDate, Date endDate);
	

}
