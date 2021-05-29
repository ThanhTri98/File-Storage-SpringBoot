package com.api.filestorage.ADMIN_API;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.api.filestorage.ADMIN_API.ClazzReq_p.Revenue;
import com.api.filestorage.entities.BillHistoryEntity;
import com.api.filestorage.repository.BillHistoryRepository;
import com.api.filestorage.repository.MusicRepository;
import com.api.filestorage.repository.PictureRepository;
import com.api.filestorage.repository.UserRepository;
import com.api.filestorage.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    public long getTotalMemory() {
        return musicRepository.getTotalSize() + videoRepository.getTotalSize() + pictureRepository.getTotalSize();
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
