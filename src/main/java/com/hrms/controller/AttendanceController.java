package com.hrms.controller;

import com.hrms.model.UserAccount;
import com.hrms.service.AttendanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public String history(HttpSession session, Model model) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        model.addAttribute("records", attendanceService.history(user));
        return "attendance";
    }

    @PostMapping("/checkin")
    public String checkIn(HttpSession session) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        attendanceService.checkIn(user);
        return "redirect:/attendance";
    }
}
