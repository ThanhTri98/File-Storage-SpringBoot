package com.api.filestorage.services.file_shared;

import java.util.List;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.repository.file_shared.MusicSharedRepository;
import com.api.filestorage.services.ClazzData.Shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicSharedService extends BaseSharedService {
    @Autowired
    private MusicSharedRepository musicSharedRepository;

    @Override
    public List<? extends BaseFileShared> findByOwner(String owner) {
        return super.findByOwnerImpl(owner, musicSharedRepository);
    }

    @Override
    public List<? extends BaseFileShared> findByReceiver(String owner) {
        return super.findByReceiverImpl(owner, musicSharedRepository);
    }
    @Override
    public void delete(int id) {
        super.deleteImpl(id, musicSharedRepository);
    }

    @Override
    public void addReceiver(Shared receiver) {
        super.addReceiverImpl(receiver, musicSharedRepository);
    }
}
