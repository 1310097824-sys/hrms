package com.hrms.controller;

import com.hrms.model.OrganizationUnit;
import com.hrms.model.Position;
import com.hrms.model.UserAccount;
import com.hrms.model.UserRole;
import com.hrms.service.OrganizationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/org")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
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
        model.addAttribute("units", organizationService.listUnits());
        model.addAttribute("positions", organizationService.listPositions());
        model.addAttribute("level1", organizationService.listLevel(1));
        model.addAttribute("level2", organizationService.listLevel(2));
        model.addAttribute("level3", organizationService.listLevel(3));
        return "organization";
    }

    @PostMapping("/unit")
    public String saveUnit(@RequestParam String name, @RequestParam int level,
                           @RequestParam(required = false) Long parentId,
                           HttpSession session) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        if (!hasArchivePermission(user)) {
            return "redirect:/dashboard";
        }
        OrganizationUnit unit = new OrganizationUnit();
        unit.setName(name);
        unit.setLevel(level);
        if (parentId != null) {
            organizationService.findUnit(parentId).ifPresent(unit::setParent);
        }
        organizationService.saveUnit(unit);
        return "redirect:/org";
    }

    @PostMapping("/position")
    public String savePosition(@RequestParam String name, @RequestParam Long orgUnitId,
                               HttpSession session) {
        UserAccount user = (UserAccount) session.getAttribute("user");
        if (!hasArchivePermission(user)) {
            return "redirect:/dashboard";
        }
        Position position = new Position();
        position.setName(name);
        organizationService.findUnit(orgUnitId).ifPresent(position::setOrganizationUnit);
        organizationService.savePosition(position);
        return "redirect:/org";
    }
}
