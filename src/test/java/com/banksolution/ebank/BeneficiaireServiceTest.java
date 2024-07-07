package com.banksolution.ebank.service;

import com.banksolution.ebank.model.Beneficiaire;
import com.banksolution.ebank.repository.BeneficiaireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BeneficiaireServiceTest {

    @InjectMocks
    private BeneficiaireService beneficiaireService;

    @Mock
    private BeneficiaireRepository beneficiaireRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBeneficiaire() {
        Beneficiaire beneficiaire1 = new Beneficiaire();
        beneficiaire1.setId(1L);
        when(beneficiaireRepository.findAll()).thenReturn(List.of(beneficiaire1));

        List<Beneficiaire> beneficiaires = beneficiaireService.getAllBeneficiaire();

        assertEquals(1, beneficiaires.size());
        verify(beneficiaireRepository, times(1)).findAll();
    }

    @Test
    public void testGetBeneficiaireById() {
        Beneficiaire beneficiaire = new Beneficiaire();
        beneficiaire.setId(1L);
        when(beneficiaireRepository.findById(1L)).thenReturn(Optional.of(beneficiaire));

        Beneficiaire foundBeneficiaire = beneficiaireService.getBeneficiaireById(1L);

        assertNotNull(foundBeneficiaire);
        assertEquals(1L, foundBeneficiaire.getId());
        verify(beneficiaireRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveBeneficiaire() {
        Beneficiaire beneficiaire = new Beneficiaire();
        when(beneficiaireRepository.save(beneficiaire)).thenReturn(beneficiaire);

        Beneficiaire savedBeneficiaire = beneficiaireService.save(beneficiaire);

        assertNotNull(savedBeneficiaire);
        verify(beneficiaireRepository, times(1)).save(beneficiaire);
    }

    @Test
    public void testDeleteBeneficiaire() {
        doNothing().when(beneficiaireRepository).deleteById(1L);

        beneficiaireService.deleteBeneficiaire(1L);

        verify(beneficiaireRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateBeneficiaire() {
        Beneficiaire beneficiaire = new Beneficiaire();
        when(beneficiaireRepository.save(beneficiaire)).thenReturn(beneficiaire);

        Beneficiaire updatedBeneficiaire = beneficiaireService.updateBeneficiaire(beneficiaire);

        assertNotNull(updatedBeneficiaire);
        verify(beneficiaireRepository, times(1)).save(beneficiaire);
    }
}
