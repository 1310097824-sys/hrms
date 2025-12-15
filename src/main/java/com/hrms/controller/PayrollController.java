package com.hrms.controller;

import com.hrms.model.PayrollRecord;
import com.hrms.model.UserAccount;
import com.hrms.model.UserRole;
import com.hrms.service.PayrollService;
import com.hrms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.YearMonth;

@Controller
@RequestMapping("/payroll")
public class PayrollController {
    private final PayrollService payrollService;
    private final UserService userService;

    public PayrollController(PayrollService payrollService, UserService userService) {
        this.payrollService = payrollService;
        this.userService = userService;
    }

    private boolean hasPayrollPermission(UserAccount user) {
        return user.getRole() == UserRole.PAYROLL_ADMIN || user.getRole() == UserRole.SUPER_ADMIN;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        if (hasPayrollPermission(user)) {
            model.addAttribute("records", payrollService.listAll());
            model.addAttribute("users", userService.listAll());
        } else {
            model.addAttribute("records", payrollService.listByUser(user));
        }
        model.addAttribute("canEdit", hasPayrollPermission(user));
        return "payroll";
    }

    @PostMapping
    public String save(@RequestParam Long userId, @RequestParam String month,
                       @RequestParam Double amount, @RequestParam(required = false) String notes,
                       HttpSession session) {
        UserAccount current = (UserAccount) session.getAttribute("user");
        if (!hasPayrollPermission(current)) {
            return "redirect:/payroll";
        }
        PayrollRecord record = new PayrollRecord();
        userService.findById(userId).ifPresent(record::setUser);
        record.setMonth(YearMonth.parse(month));
        record.setAmount(amount);
        record.setNotes(notes);
        payrollService.save(record);
        return "redirect:/payroll";
    }
}
