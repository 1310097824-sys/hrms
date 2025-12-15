package com.hrms.config;

import com.hrms.model.*;
import com.hrms.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;

@Component
public class DataInitializer {
    private final OrganizationUnitRepository organizationUnitRepository;
    private final PositionRepository positionRepository;
    private final UserAccountRepository userAccountRepository;
    private final EmployeeFileRepository employeeFileRepository;
    private final PayrollRecordRepository payrollRecordRepository;

    public DataInitializer(OrganizationUnitRepository organizationUnitRepository,
                           PositionRepository positionRepository,
                           UserAccountRepository userAccountRepository,
                           EmployeeFileRepository employeeFileRepository,
                           PayrollRecordRepository payrollRecordRepository) {
        this.organizationUnitRepository = organizationUnitRepository;
        this.positionRepository = positionRepository;
        this.userAccountRepository = userAccountRepository;
        this.employeeFileRepository = employeeFileRepository;
        this.payrollRecordRepository = payrollRecordRepository;
    }

    @PostConstruct
    public void init() {
        if (userAccountRepository.count() > 0) {
            return;
        }

        OrganizationUnit level1 = new OrganizationUnit();
        level1.setName("总部");
        level1.setLevel(1);
        organizationUnitRepository.save(level1);

        OrganizationUnit level2 = new OrganizationUnit();
        level2.setName("研发中心");
        level2.setLevel(2);
        level2.setParent(level1);
        organizationUnitRepository.save(level2);

        OrganizationUnit level3 = new OrganizationUnit();
        level3.setName("平台研发部");
        level3.setLevel(3);
        level3.setParent(level2);
        organizationUnitRepository.save(level3);

        Position engineer = new Position();
        engineer.setName("Java工程师");
        engineer.setOrganizationUnit(level3);
        positionRepository.save(engineer);

        Position hr = new Position();
        hr.setName("档案管理员");
        hr.setOrganizationUnit(level3);
        positionRepository.save(hr);

        UserAccount superAdmin = new UserAccount();
        superAdmin.setUsername("admin");
        superAdmin.setPassword("123456");
        superAdmin.setFullName("超级管理员");
        superAdmin.setRole(UserRole.SUPER_ADMIN);
        userAccountRepository.save(superAdmin);

        UserAccount archiveAdmin = new UserAccount();
        archiveAdmin.setUsername("archive");
        archiveAdmin.setPassword("123456");
        archiveAdmin.setFullName("档案管理员");
        archiveAdmin.setRole(UserRole.ARCHIVE_ADMIN);
        archiveAdmin.setPosition(hr);
        userAccountRepository.save(archiveAdmin);

        UserAccount payrollAdmin = new UserAccount();
        payrollAdmin.setUsername("payroll");
        payrollAdmin.setPassword("123456");
        payrollAdmin.setFullName("薪酬管理员");
        payrollAdmin.setRole(UserRole.PAYROLL_ADMIN);
        payrollAdmin.setPosition(engineer);
        userAccountRepository.save(payrollAdmin);

        UserAccount normal = new UserAccount();
        normal.setUsername("user");
        normal.setPassword("123456");
        normal.setFullName("普通员工");
        normal.setRole(UserRole.USER);
        normal.setPosition(engineer);
        userAccountRepository.save(normal);

        EmployeeFile file = new EmployeeFile();
        file.setUser(normal);
        file.setHireDate(LocalDate.now().minusYears(1));
        file.setNotes("优秀员工");
        employeeFileRepository.save(file);

        PayrollRecord payrollRecord = new PayrollRecord();
        payrollRecord.setUser(normal);
        payrollRecord.setMonth(YearMonth.now());
        payrollRecord.setAmount(15000.0);
        payrollRecord.setNotes("含绩效");
        payrollRecordRepository.save(payrollRecord);
    }
}
