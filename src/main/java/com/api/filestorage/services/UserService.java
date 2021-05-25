package com.api.filestorage.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.filestorage.dto.RoleDTO;
import com.api.filestorage.dto.UserDTO;
import com.api.filestorage.entities.AccountPackageEntity;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.OtpEntity;
import com.api.filestorage.entities.UserEntity;
import com.api.filestorage.repository.AccountPackageRepository;
import com.api.filestorage.repository.MusicRepository;
import com.api.filestorage.repository.OtpRepository;
import com.api.filestorage.repository.PictureRepository;
import com.api.filestorage.repository.UserRepository;
import com.api.filestorage.repository.VideoRepository;
import com.api.filestorage.security.UserDetailsImpl;
import com.api.filestorage.security.jwt.JwtUtils;
import com.api.filestorage.security.payload.request.LoginRequest;
import com.api.filestorage.security.payload.response.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> findAll() {
        // return userRepository.findAll().stream().map(u -> {
        //     return new UserDTO().toDTO(u);
        // }).collect(Collectors.toList());
        return userRepository.findAll();
    }

    public UserDTO findByUsername(String userName) {
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null)
            return null;
        return new UserDTO().toDTO(userEntity);
    }

    public UserDTO findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            return null;
        return new UserDTO().toDTO(userEntity);
    }

    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), userDetails.getFull_name(),
                userDetails.getAcc_pkg_name(), userDetails.getAcc_pkg_size(), roles);
    }

    public void signUp(UserDTO user) {
        RoleDTO role = new RoleDTO(1, "ROLE_USER");
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIs_active(1);
        UserEntity entity = new UserEntity().toEntity(user);

        entity.setAcc_pkg(new AccountPackageEntity(1, "Normal", 16106127360L, 0));
        userRepository.save(entity);
    }

    public boolean validateOtpSignup(String uuid, String code) {
        try {
            return otpRepository.validOtp(uuid, Integer.parseInt(code)) != null;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public void sendEmail(String email, String uuid) {
        int randomCode = mailService.sendEmail(email);
        if (randomCode != 0) {
            OtpEntity entity = new OtpEntity();
            entity.setCode(randomCode);
            entity.setUuid(uuid);
            entity.setExpireTime(LocalDateTime.now().plusMinutes(3));
            otpRepository.save(entity);
        }
    }

    public long getUsedMemory(String userName) {
        // Get tat ca cac size trong music, video, picture
        Long size_music = musicRepository.getTotalSize(userName, BaseService.FOLDER_EXT);
        size_music = size_music != null ? size_music : 0;
        // System.out.println(size_music+"size music");
        Long size_picture = pictureRepository.getTotalSize(userName, BaseService.FOLDER_EXT);
        size_picture = size_picture != null ? size_picture : 0;
        Long size_video = videoRepository.getTotalSize(userName, BaseService.FOLDER_EXT);
        size_video = size_video != null ? size_video : 0;
        ;
        // long useable = total_size - (size_music + size_picture + size_video)
        return size_music + size_picture + size_video;
    }

    public long getTotalMemory(int price) {
        return AccountPackageEntity.findByPrice(price).getTotal_size();
    }

    public List<FilesEntity> getTrash(String username) {
        List<? extends FilesEntity> listVideos = getOnlyFolderParent(videoRepository.findByStateAndCreator(0, username),
                "videos");
        List<? extends FilesEntity> listMusics = getOnlyFolderParent(musicRepository.findByStateAndCreator(0, username),
                "musics");
        List<? extends FilesEntity> listPicture = getOnlyFolderParent(
                pictureRepository.findByStateAndCreator(0, username), "pictures");
        List<FilesEntity> rs = new ArrayList<>();
        rs.addAll(listVideos);
        rs.addAll(listMusics);
        rs.addAll(listPicture);
        return rs;
    }

    private List<? extends FilesEntity> getOnlyFolderParent(List<? extends FilesEntity> list, String kind) {
        // kind: định nghĩa file đó thuộc nhóm nào (music, video, picture)
        // Vì thuộc tính parent trong trường hợp này k dùng nên lấy set tạm
        if (list.isEmpty())
            return new ArrayList<>();
        List<String> fileNames = list.stream().map(file -> file.getName()).collect(Collectors.toList());
        return list.stream().filter(file -> !fileNames.contains(file.getParent())).map(map -> {
            map.setParent(kind);
            return map;
        }).collect(Collectors.toList());
    }

    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private AccountPackageRepository AccountPackageEntity;
}
