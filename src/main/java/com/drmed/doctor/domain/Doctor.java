package com.drmed.doctor.domain;

import com.drmed.additional.statuses.ActivityStatus;
import com.drmed.patient.domain.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    private Long id;
    private String primaryId;
    private String firstName;
    private String lastName;
    private ActivityStatus doctorStatus;
    private String email;

    private List<Long> patientsIds = new ArrayList<>();
    private List<Patient> patientList = new ArrayList<>();

    public Doctor(String primaryId, String firstName, String lastName, ActivityStatus doctorStatus, String email) {
        this.primaryId = primaryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorStatus = doctorStatus;
        this.email = email;
    }

    public Doctor(Long id, String primaryId, String firstName, String lastName, ActivityStatus doctorStatus, String email, List<Long> patientsIds) {
        this.id = id;
        this.primaryId = primaryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorStatus = doctorStatus;
        this.email = email;
        this.patientsIds = patientsIds;
    }
}
