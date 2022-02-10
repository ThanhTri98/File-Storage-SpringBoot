package com.api.filestorage.ADMIN_API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.filestorage.ADMIN_API.ClazzReq_p.Revenue;
import com.api.filestorage.dto.UserDTO;
import com.api.filestorage.entities.BillHistoryEntity;
import com.api.filestorage.entities.RoleEntity;
import com.api.filestorage.entities.UserEntity;
import com.api.filestorage.repository.BillHistoryRepository;
import com.api.filestorage.repository.MusicRepository;
import com.api.filestorage.repository.PictureRepository;
import com.api.filestorage.repository.UserRepository;
import com.api.filestorage.repository.VideoRepository;
import com.api.filestorage.security.UserDetailsImpl;
import com.api.filestorage.security.payload.request.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    @Autowired
    private AuthenticationManager authenticationManager;
    // Admin login
    public ResponseEntity<?> adminSignIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toSet());
        if (roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.ok().body(userDetails);
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // active
    public boolean activeEntity(String email) {
        System.out.println(email);
        UserEntity userEntity = userRepository.findByEmail(email);
        System.out.println(userEntity.toString() );
        userEntity.setIs_active(userEntity.getIs_active() == 1 ? 0 : 1);
        try {
            userRepository.save(userEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
   // get memberlist
    public List<UserDetailsImpl> getListMember () {
        List<UserDetailsImpl> rsUserDetailsImpls = new ArrayList<>();
        userRepository.findAll().forEach(e->{
            rsUserDetailsImpls.add(UserDetailsImpl.build(new UserDTO().toDTO(e)));
        });
        return rsUserDetailsImpls;
     }
    // // upgrade 
     public boolean upgrade(Map<String,Object> data){
        boolean isAdd = Boolean.valueOf(data.get("isAdd")+"");
        String username = data.get("username")+"";
        UserEntity userEntity = userRepository.findByUsername(username);
         if(isAdd){
            userEntity.getRoles().add(new RoleEntity(2, "ROLE_ADMIN"));
         }else{
            userEntity.getRoles().clear();
            userEntity.getRoles().add(new RoleEntity(1, "ROLE_USER"));
         }
         userRepository.save(userEntity);
        return true;
    }

    public long getTotalMemory() {
        Long videoSize = videoRepository.getTotalSize();
        Long pictureSize = pictureRepository.getTotalSize();
        Long musicSize = musicRepository.getTotalSize();

        return (videoSize != null ? videoSize : 0) + (pictureSize != null ? pictureSize : 0)
                + (musicSize != null ? musicSize : 0);
    }

    public int getTotalUsers() {
        return userRepository.getTotalUsers();
    }

    public long getTotalSalary() {
        return billHistoryRepository.getTotalSalary();
    }

    public int[] getTotalNumberTypeFile() {
        int m = musicRepository.getTotalNumberTypeFile();
        int v = videoRepository.getTotalNumberTypeFile();
        int p = pictureRepository.getTotalNumberTypeFile();
        return new int[] { m, p, v };
    }

    public long[] getSalaryByMonthOfYear(int year) {
        long[] rs = new long[12];
        for (int i = 1; i < 13; i++) {
            Long ok = billHistoryRepository.getSalaryByMonthOfYear(year, i);
            rs[i - 1] = ok != null ? ok : 0;
        }
        return rs;
    }

    public List<Revenue> getListRevenue() {
        Map<String, Long> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        billHistoryRepository.findAll().forEach(b -> {
            String username = b.getUser().getUsername();
            if (map.containsKey(username))
                map.put(username, map.get(username) + b.getAmount());
            else {
                map.put(username, (long) b.getAmount());
                map2.put(username, b.getUser().getFull_name());
            }

        });
        return map.entrySet().stream().map(entry -> {
            return new Revenue(entry.getKey(), map2.get(entry.getKey()), entry.getValue());
        }).collect(Collectors.toList());

    }

    public List<BillHistoryEntity> findByUserId(String user_id) {
        return billHistoryRepository.findByUserId(user_id);
    }

    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private BillHistoryRepository billHistoryRepository;
    @Autowired
    private UserRepository userRepository;

}
