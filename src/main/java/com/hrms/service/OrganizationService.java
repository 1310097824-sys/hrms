package com.hrms.service;

import com.hrms.model.OrganizationUnit;
import com.hrms.model.Position;
import com.hrms.repository.OrganizationUnitRepository;
import com.hrms.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationUnitRepository organizationUnitRepository;
    private final PositionRepository positionRepository;

    public OrganizationService(OrganizationUnitRepository organizationUnitRepository, PositionRepository positionRepository) {
        this.organizationUnitRepository = organizationUnitRepository;
        this.positionRepository = positionRepository;
    }

    public List<OrganizationUnit> listUnits() {
        return organizationUnitRepository.findAll();
    }

    public List<OrganizationUnit> listLevel(int level) {
        return organizationUnitRepository.findByLevel(level);
    }

    public OrganizationUnit saveUnit(OrganizationUnit unit) {
        if (unit.getLevel() > 1 && unit.getParent() == null) {
            throw new IllegalArgumentException("非一级机构必须指定父机构");
        }
        return organizationUnitRepository.save(unit);
    }

    public Optional<OrganizationUnit> findUnit(Long id) {
        return organizationUnitRepository.findById(id);
    }

    public void deleteUnit(Long id) {
        organizationUnitRepository.deleteById(id);
    }

    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }

    public List<Position> listPositions() {
        return positionRepository.findAll();
    }

    public Optional<Position> findPosition(Long id) {
        return positionRepository.findById(id);
    }
}
