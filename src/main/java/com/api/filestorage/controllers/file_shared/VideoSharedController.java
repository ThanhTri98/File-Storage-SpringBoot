package com.api.filestorage.controllers.file_shared;

import java.util.List;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.entities.file_shared.VideoFileSharedEntity;
import com.api.filestorage.services.ClazzData.Shared;
import com.api.filestorage.services.file_shared.VideoSharedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/shared/videos")
public class VideoSharedController implements BaseSharedController<VideoFileSharedEntity> {

    @Autowired
    VideoSharedService videoSharedService;

    @Override
    public List<? extends BaseFileShared> findByOwner(String owner) {
        return BaseSharedController.super.findByOwnerImpl(owner, videoSharedService);
    }

    @Override
    public List<? extends BaseFileShared> findByReceiver(String receiver) {
        return BaseSharedController.super.findByReceiverImpl(receiver, videoSharedService);
    }

    @Override
    public void deleteById(int id) {
        BaseSharedController.super.deleteByIdImpl(id, videoSharedService);
    }

    @Override
    public void addReceiver(Shared receivers) {
        BaseSharedController.super.addReceiverImpl(receivers, videoSharedService);
    }
}
