package com.esprit.examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

@Service
public class OperateurServiceImpl implements IOperateurService {

	@Autowired
	OperateurRepository operateurRepository;

	@Override
	public List<Operateur> retrieveAllOperateurs() {
		return (List<Operateur>) operateurRepository.findAll();
	}

	@Override
	public Operateur addOperateur(Operateur oadd) {
		operateurRepository.save(oadd);
		return oadd;
	}

	@Override
	public void deleteOperateur(Long id) {
		operateurRepository.deleteById(id);

	}

	@Override
	public Operateur updateOperateur(Operateur oedit) {
		operateurRepository.save(oedit);
		return oedit;
	}

	@Override
	public Operateur retrieveOperateur(Long id) {
		return operateurRepository.findById(id).orElse(null);
	}

}
