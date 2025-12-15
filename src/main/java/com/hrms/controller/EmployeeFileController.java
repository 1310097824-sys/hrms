package com.hrms.controller;

import com.hrms.model.EmployeeFile;
import com.hrms.model.UserAccount;
import com.hrms.model.UserRole;
import com.hrms.service.EmployeeFileService;
import com.hrms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class EmployeeFileController {
    private final EmployeeFileService employeeFileService;
    private final UserService userService;

    public EmployeeFileController(EmployeeFileService employeeFileService, UserService userService) {
        this.employeeFileService = employeeFileService;
        this.userService = userService;
    }

    private boolean hasArchivePermission(UserAccount user) {
        return user.getRole() == UserRole.ARCHIVE_ADMIN || user.getRole() == UserRole.SUPER_ADMIN;
    }

    @GetMapping
    public String list(Model model, HttpSession session) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        if (!hasArchivePermission(user)) {
            return "redirect:/dashboard";
        }
        model.addAttribute("files", employeeFileService.listAll());
        model.addAttribute("users", userService.listAll());
        return "employee-files";
    }

    @PostMapping
    public String save(EmployeeFile file, Long userId, HttpSession session) {
        UserAccount current = (UserAccount) session.getAttribute("user");
        if (!hasArchivePermission(current)) {
            return "redirect:/dashboard";
        }
        userService.findById(userId).ifPresent(file::setUser);
        employeeFileService.save(file);
        return "redirect:/files";
    }

    @PostMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id, HttpSession session) {
        UserAccount current = (UserAccount) session.getAttribute("user");
        if (!hasArchivePermission(current)) {
            return "redirect:/dashboard";
        }
        employeeFileService.softDelete(id);
        return "redirect:/files";
    }

    @PostMapping("/restore/{id}")
    public String restore(@PathVariable Long id, HttpSession session) {
        UserAccount current = (UserAccount) session.getAttribute("user");
        if (!hasArchivePermission(current)) {
            return "redirect:/dashboard";
        }
        employeeFileService.restore(id);
        return "redirect:/files";
    }
}
