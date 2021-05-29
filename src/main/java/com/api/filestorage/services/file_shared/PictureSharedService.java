package com.api.filestorage.services.file_shared;

import java.util.List;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.repository.file_shared.PictureSharedRepository;
import com.api.filestorage.services.ClazzData.Shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureSharedService extends BaseSharedService {
    @Autowired
    private PictureSharedRepository pictureSharedRepository;

    @Override
    public List<? extends BaseFileShared> findByOwner(String owner) {
        return super.findByOwnerImpl(owner, pictureSharedRepository);
    }

    @Override
    public List<? extends BaseFileShared> findByReceiver(String owner) {
        return super.findByReceiverImpl(owner, pictureSharedRepository);
    }

    @Override
    public void delete(int id) {
        super.deleteImpl(id, pictureSharedRepository);
    }
    @Override
    public void addReceiver(Shared receiver) {
        super.addReceiverImpl(receiver, pictureSharedRepository);
    }
}
