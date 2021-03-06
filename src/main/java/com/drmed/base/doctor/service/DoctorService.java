package com.drmed.base.doctor.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.config.CoreConfig;
import com.drmed.base.doctor.domain.Doctor;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.mapper.DoctorMapper;
import com.drmed.base.doctor.repository.DoctorRepository;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private VisitService visitService;
    @Autowired
    private CoreConfig coreConfig;

    public DoctorDto addNewDoctor(NewDoctorDto newDoctorDto) {
        Doctor doctor = new Doctor.DoctorBuilder()
                .setCode(newDoctorDto.getCode())
                .setFirstName(newDoctorDto.getFirstName())
                .setLastName(newDoctorDto.getLastName())
                .setEmail(newDoctorDto.getEmail())
                .setDoctorStatus(ActivityStatus.ACTIVE)
                .build();
        return doctorMapper.mapToDoctorDto(doctorRepository.saveDoctor(doctor));
    }

    public DoctorDto updateDoctor(DoctorDto doctorDto) throws DoctorNotFoundException, VisitNotFoundException {
        Doctor doctorFromBase = doctorRepository.getDoctorById(doctorDto.getId());
        Doctor doctor = new Doctor.DoctorBuilder()
                .setId(doctorDto.getId())
                .setCode(doctorDto.getCode())
                .setFirstName(doctorDto.getFirstName())
                .setLastName(doctorDto.getLastName())
                .setEmail(doctorDto.getEmail())
                .setDoctorStatus(doctorDto.getDoctorStatus())
                .setVisitIdList(doctorFromBase.getVisitIdList())
                .build();
        mapVisitIdsToVisits(doctor);
        return doctorMapper.mapToDoctorDto(doctorRepository.saveDoctor(doctor));
    }

    public Doctor removeVisitFromDoctorList(Long doctorId, Long visitId)
            throws DoctorNotFoundException, VisitNotFoundException {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        Visit visit = visitService.getVisitById(visitId);
        mapVisitIdsToVisits(doctor);
        doctor.getVisitList().remove(visit);
        return doctorRepository.saveDoctor(doctor);
    }

    public DoctorDto safeDeleteDoctorById(Long doctorId) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        doctor.setDoctorStatus(ActivityStatus.DELETED);
        return doctorMapper.mapToDoctorDto(doctorRepository.saveDoctor(doctor));
    }

    private void mapVisitIdsToVisits(Doctor doctor) throws VisitNotFoundException {
        List<Visit> visitList = new ArrayList<>();
        for (Long visitId : doctor.getVisitIdList()) {
            Visit visit = visitService.getVisitById(visitId);
            visitList.add(visit);
        }
        doctor.setVisitList(visitList);
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void cleanDoctorEmails() {
        if (coreConfig.getTestingMode()) {
            doctorRepository.updateAllDoctorsEmails();
        }
    }

    public DoctorDto getDoctorDtoById(Long doctorId) throws DoctorNotFoundException {
        return doctorMapper.mapToDoctorDto(doctorRepository.getDoctorById(doctorId));
    }

    public Doctor getDoctorById(Long doctorId) throws DoctorNotFoundException {
        return doctorRepository.getDoctorById(doctorId);
    }

    public List<DoctorInfoDto> getDoctorByPrimaryIdContains(String code) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByCodeContains(code));
    }

    public List<DoctorInfoDto> getDoctorByFirstNameContains(String firstName) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByFirstNameContains(firstName));
    }

    public List<DoctorInfoDto> getDoctorByLastNameContains(String lastName) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByLastNameContains(lastName));
    }

    public List<DoctorInfoDto> getDoctorByFirstNameContainsAndLastNameContaining(String firstName, String lastName) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByFirstNameContainsAndLastNameContaining(firstName, lastName));
    }

    public List<DoctorInfoDto> getAllDoctors() {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getAllDoctors());
    }
}

