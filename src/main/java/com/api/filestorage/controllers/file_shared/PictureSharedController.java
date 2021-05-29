package com.api.filestorage.controllers.file_shared;

import java.util.List;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.entities.file_shared.PictureFileSharedEntity;
import com.api.filestorage.services.ClazzData.Shared;
import com.api.filestorage.services.file_shared.PictureSharedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/shared/pictures")
public class PictureSharedController implements BaseSharedController<PictureFileSharedEntity> {

    @Autowired
    private PictureSharedService pictureSharedService;

    @Override
    public List<? extends BaseFileShared> findByOwner(String owner) {
        return BaseSharedController.super.findByOwnerImpl(owner, pictureSharedService);
    }

    @Override
    public List<? extends BaseFileShared> findByReceiver(String receiver) {
        return BaseSharedController.super.findByReceiverImpl(receiver, pictureSharedService);
    }

    @Override
    public void deleteById(int id) {
        BaseSharedController.super.deleteByIdImpl(id, pictureSharedService);
    }

    @Override
    public void addReceiver(Shared receivers) {
        BaseSharedController.super.addReceiverImpl(receivers, pictureSharedService);
    }
}
