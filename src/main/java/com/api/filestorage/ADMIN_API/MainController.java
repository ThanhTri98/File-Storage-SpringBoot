package com.api.filestorage.ADMIN_API;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.api.filestorage.ADMIN_API.ClazzReq_p.Revenue;
import com.api.filestorage.entities.BillHistoryEntity;
import com.api.filestorage.security.UserDetailsImpl;
import com.api.filestorage.security.payload.request.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class MainController {
    @Autowired
    private MainService mainService;

    //admin dang nhap
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(mainService.adminSignIn(loginRequest));
    }
    // kich hoat
    @PutMapping("/active")
    public Boolean activeEntity(@RequestBody String email) {
        if (!mainService.activeEntity(email))
        return false;
    return true;
    }
    //danh sach
    @GetMapping("/memberlist")
    public List<UserDetailsImpl> getListMember(){
        return mainService.getListMember();
    } 
    // thang quyen, xoa quyen
    @PutMapping("/upgrade")
    public boolean upgrade(@RequestBody Map<String,Object> data){
      return  mainService.upgrade(data);
    }
    
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
