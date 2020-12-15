package com.drmed.base.visit.mapper;

import com.drmed.base.doctor.mapper.DoctorMapper;
import com.drmed.base.order.mapper.OrderMapper;
import com.drmed.base.order.repository.OrderHibernate;
import com.drmed.base.patient.mapper.PatientMapper;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.dto.VisitDto;
import com.drmed.base.visit.dto.VisitInfoDto;
import com.drmed.base.visit.repository.VisitHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitMapper {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private OrderMapper orderMapper;

    public VisitHibernate mapToVisitHibernate(Visit visit) {
        return new VisitHibernate(
                visit.getId(),
                visit.getCode(),
                visit.getDateOfVisit(),
                patientMapper.mapToPatientHibernate(visit.getPatient()),
                doctorMapper.mapToDoctorHibernate(visit.getDoctor()),
                orderMapper.mapToOrderHibernateList(visit.getOrderList())
        );
    }

    public Visit mapToVisit(VisitHibernate visitHibernate) {
        return new Visit(
                visitHibernate.getId(),
                visitHibernate.getCode(),
                visitHibernate.getDateOfVisit(),
                patientMapper.mapToPatient(visitHibernate.getPatient()),
                doctorMapper.mapToDoctor(visitHibernate.getDoctor()),
                visitHibernate.getOrders().stream().map(OrderHibernate::getId).collect(Collectors.toList())
        );
    }

    public VisitInfoDto mapToVisitInfoDto(Visit visit) {
        return new VisitInfoDto(
                visit.getId(),
                visit.getCode(),
                visit.getDateOfVisit()
        );
    }

    public VisitDto mapToVisitDto(Visit visit) {
        return new VisitDto(
                visit.getId(),
                visit.getCode(),
                visit.getDateOfVisit(),
                patientMapper.mapToPatientInfoDto(visit.getPatient()),
                doctorMapper.mapToDoctorInfoDto(visit.getDoctor()),
                orderMapper.mapToOrderInfoDtoList(visit.getOrderList())
        );
    }

    public List<VisitHibernate> mapToVisitHibernateList(List<Visit> visitList) {
        return visitList.stream().map(this::mapToVisitHibernate).collect(Collectors.toList());
    }

    public List<Visit> mapToVisitList(List<VisitHibernate> visitHibernateList) {
        return visitHibernateList.stream().map(this::mapToVisit).collect(Collectors.toList());
    }

    public List<VisitInfoDto> mapToVisitInfoDtoList(List<Visit> visitList) {
        return visitList.stream().map(this::mapToVisitInfoDto).collect(Collectors.toList());
    }
}