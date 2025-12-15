package com.hrms.controller;

import com.hrms.model.UserAccount;
import com.hrms.model.UserRole;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("isArchiveAdmin", user.getRole() == UserRole.ARCHIVE_ADMIN || user.getRole() == UserRole.SUPER_ADMIN);
        model.addAttribute("isPayrollAdmin", user.getRole() == UserRole.PAYROLL_ADMIN || user.getRole() == UserRole.SUPER_ADMIN);
        return "dashboard";
    }
}
