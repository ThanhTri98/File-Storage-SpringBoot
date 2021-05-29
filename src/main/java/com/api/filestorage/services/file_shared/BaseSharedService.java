package com.api.filestorage.services.file_shared;

import java.util.List;
import java.util.stream.Collectors;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.repository.UserRepository;
import com.api.filestorage.repository.file_shared.BaseSharedRepository;
import com.api.filestorage.services.ClazzData.Shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseSharedService {
    public abstract List<? extends BaseFileShared> findByOwner(String owner);

    List<? extends BaseFileShared> findByOwnerImpl(String owner, BaseSharedRepository<? extends BaseFileShared> repos) {
        return repos.findByOwner(owner);
    }

    public abstract List<? extends BaseFileShared> findByReceiver(String owner);

    List<? extends BaseFileShared> findByReceiverImpl(String receiver,
            BaseSharedRepository<? extends BaseFileShared> repos) {
        return repos.findByReceiver(receiver);
    }

    public abstract void delete(int id);

    void deleteImpl(int id, BaseSharedRepository<? extends BaseFileShared> repos) {
        repos.deleteById(id);
    }

    // public abstract void addReceiver(List<String> receivers);
    public abstract void addReceiver(Shared receiver);

    void addReceiverImpl(Shared receiver, BaseSharedRepository<? extends BaseFileShared> repos) {
        List<String> receivers = receiver.getReceivers().stream().filter(u -> userIsExist(u)).map(u -> {
            if (u.contains("@"))
                return userRepository.findUserNameByEmail(u);
            return u;
        }).collect(Collectors.toList());
        receiver.getFile_ids().forEach(id -> {
            receivers.forEach(recei -> {
                BaseFileShared baseFileShared = new BaseFileShared();
                baseFileShared.setFile_id(id);
                baseFileShared.setOwner(receiver.getOwner());
                baseFileShared.setReceiver(recei);
                repos.insert(baseFileShared);
            });
        });
    }

    private boolean userIsExist(String val) {
        if (val.contains("@"))
            return userRepository.findByEmail(val) != null;
        else
            return userRepository.findByUsername(val) != null;
    }

    @Autowired
    private UserRepository userRepository;
}
