package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import lombok.extern.slf4j.Slf4j;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;

@Slf4j
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FournisseurServiceImplMockTest {
	
	@Mock
	FournisseurRepository fournisseurrepo;
	@InjectMocks
	FournisseurServiceImpl fournisseurservi;
	
	Fournisseur p = Fournisseur.builder().idFournisseur((long) 7).code("yahia1").libelle("yahia1").build();
	
	@Test
	public void GetFournisseurTest() {
		List<Fournisseur> listfournisseurs = new ArrayList<>();
		listfournisseurs.add(new Fournisseur());
		
		Mockito.when(fournisseurrepo.findAll()).thenReturn(listfournisseurs);
		List<Fournisseur> listp = fournisseurservi.retrieveAllFournisseurs();
		assertEquals(listp, listfournisseurs);
		log.info("get product ==>"+ listp.toString());
		
	}
	
	@Test
	public void GetbyID() {

		Mockito.when(fournisseurrepo.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
		Fournisseur prod = fournisseurservi.retrieveFournisseur(3L);
		assertNotNull(prod);
		log.info("get ==> " + prod.toString());
		verify(fournisseurrepo).findById(Mockito.anyLong());

	}
	
	@Test
	public void AddFournisseurTest() {

		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setLibelle("yahia2");
		Mockito.lenient().when(fournisseurrepo.save(fournisseur)).thenReturn(fournisseur);
		Fournisseur newp = fournisseurservi.addFournisseur(fournisseur);
		assertEquals(fournisseur.getLibelle(), newp.getLibelle());
		verify(fournisseurrepo).save(fournisseur);
		log.info("Added ==> " + fournisseur.toString());
	}
	

	
	
	@Test
	public void DeleteFournisseurTest() {
		Fournisseur p = new Fournisseur();
		p.setLibelle("libelle");
		p.setIdFournisseur((long) 3);
		Long pid = p.getIdFournisseur();
		Mockito.lenient().when(fournisseurrepo.findById(pid)).thenReturn(Optional.of(p));

		fournisseurservi.deleteFournisseur(pid);
		verify(fournisseurrepo).deleteById(pid);
		log.info("Deleted ==> " + pid.toString());
	}
}