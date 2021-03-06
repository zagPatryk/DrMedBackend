package com.drmed.base.patient.service;

import com.drmed.base.additional.Gender;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.patient.domain.Patient;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.repository.PatientCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PatientServiceTestSuite {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientCrudRepository patientCrudRepository;

    @Test
    public void addNewPatient() throws PatientNotFoundException {
        // Given
        NewPatientDto newPatientDto = new NewPatientDto("patientCode", "firstName",
                "lastName", LocalDate.of(1999,12,1), Gender.FEMALE);
        // When
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);
        Patient patientFromDatabase = patientService.getPatientById(patientDto.getId());

        // Then
        assertEquals(patientDto.getId(), patientFromDatabase.getId());
        assertEquals(newPatientDto.getCode(), patientFromDatabase.getCode());
        assertEquals(newPatientDto.getFirstName(), patientFromDatabase.getFirstName());
        assertEquals(newPatientDto.getLastName(), patientFromDatabase.getLastName());
        assertEquals(newPatientDto.getBirthDate(), patientFromDatabase.getBirthDate());
        assertEquals(newPatientDto.getGender(), patientFromDatabase.getGender());
        assertTrue(patientFromDatabase.getVisitIdList().isEmpty());
        assertTrue(patientFromDatabase.getVisitList().isEmpty());

        // Clean
        patientCrudRepository.deleteById(patientDto.getId());
    }

    @Test
    public void updatePatient() {
    }

    @Test
    public void getPatientById() {
    }

    @Test
    public void getPatientsByCodeContains() {
    }

    @Test
    public void getPatientsByFirstNameContains() {
    }

    @Test
    public void getPatientsByLastNameContains() {
    }

    @Test
    public void getPatientsByFirstNameContainsAndLastNameContains() {
    }

    @Test
    public void getAllPatients() {
    }
}