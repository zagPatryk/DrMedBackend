package com.drmed.base.workstation.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.test.domain.Test;
import com.drmed.base.test.service.TestService;
import com.drmed.base.workstation.domain.Workstation;
import com.drmed.base.workstation.dto.NewWorkstationDto;
import com.drmed.base.workstation.mapper.WorkstationMapper;
import com.drmed.base.workstation.dto.WorkstationDto;
import com.drmed.base.workstation.dto.WorkstationInfoDto;
import com.drmed.base.workstation.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkstationService {
    @Autowired
    private WorkstationRepository workstationRepository;
    @Autowired
    private WorkstationMapper workstationMapper;
    @Autowired
    private TestService testService;

    public WorkstationDto addWorkstation(NewWorkstationDto newWorkstationDto) {
        Workstation workstation = new Workstation.WorkstationBuilder()
                .setCode(newWorkstationDto.getCode())
                .setName(newWorkstationDto.getName())
                .setWorkstationStatus(ActivityStatus.ACTIVE).build();
        return workstationMapper.mapToWorkstationDto(workstationRepository.saveWorkstation(workstation));
    }

    public WorkstationDto updateWorkstation(WorkstationDto workstationDto) throws WorkstationNotFoundException, TestNotFoundException {
        Workstation workstationFromBase = workstationRepository.getWorkstationById(workstationDto.getId());
        Workstation workstation = new Workstation.WorkstationBuilder()
                .setId(workstationDto.getId())
                .setCode(workstationDto.getCode())
                .setName(workstationDto.getName())
                .setAvailableTestsIds(workstationFromBase.getAvailableTestsIds())
                .setWorkstationStatus(workstationDto.getActivityStatus())
                .build();
        mapTestIdsToTestList(workstation);
        return workstationMapper.mapToWorkstationDto(workstationRepository.saveWorkstation(workstation));
    }

    private void mapTestIdsToTestList(Workstation workstation) throws TestNotFoundException {
        List<Test> testList = new ArrayList<>();
        for (Long testId : workstation.getAvailableTestsIds()) {
            Test test = testService.getTestById(testId);
            testList.add(test);
        }
        workstation.setAvailableTests(testList);
    }

    public Workstation getWorkstationById(Long workstationId) throws WorkstationNotFoundException {
        return workstationRepository.getWorkstationById(workstationId);
    }

    public WorkstationDto getWorkstationDtoById(Long workstationId) throws WorkstationNotFoundException {
        return workstationMapper.mapToWorkstationDto(workstationRepository.getWorkstationById(workstationId));
    }

    public List<WorkstationInfoDto> getWorkstationsByCode(String code) {
        return workstationMapper.mapToWorkstationInfoDtoList(workstationRepository.getWorkstationByCode(code));
    }

    public List<WorkstationInfoDto> getWorkstationsByName(String name) {
        return workstationMapper.mapToWorkstationInfoDtoList(workstationRepository.getWorkstationByName(name));
    }

    public List<WorkstationInfoDto> getAllWorkstation() {
        return workstationMapper.mapToWorkstationInfoDtoList(workstationRepository.getAllWorkstations());
    }
}