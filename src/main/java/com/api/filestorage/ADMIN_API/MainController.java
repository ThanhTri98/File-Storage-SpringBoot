package com.api.filestorage.ADMIN_API;

import java.util.List;

import com.api.filestorage.ADMIN_API.ClazzReq_p.Revenue;
import com.api.filestorage.entities.BillHistoryEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class MainController {
    @Autowired
    private MainService mainService;

    @GetMapping("/memories")
    public long getTotalMemory() {
        return mainService.getTotalMemory();
    }

    @GetMapping("/users")
    public int getTotalUsers() {
        return mainService.getTotalUsers();
    }

    @GetMapping("/salaries")
    public long getTotalSalary() {
        return mainService.getTotalSalary();
    }

    @GetMapping("/files")
    public int[] getTotalNumberTypeFile() {
        return mainService.getTotalNumberTypeFile();
    }

    @GetMapping("/salaries/{year}")
    public long[] getSalaryByMonthOfYear(@PathVariable int year) {
        return mainService.getSalaryByMonthOfYear(year);
    }

    @GetMapping("/revenues")
    public List<Revenue> getListRevenue() {
        return mainService.getListRevenue();
    }

    @GetMapping("/revenues/{user_id}")
    public List<BillHistoryEntity> findByUserId(@PathVariable String user_id) {
        return mainService.findByUserId(user_id);
    }

}
