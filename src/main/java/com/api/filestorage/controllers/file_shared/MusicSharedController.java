package com.api.filestorage.controllers.file_shared;

import java.util.List;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.entities.file_shared.MusicFileSharedEntity;
import com.api.filestorage.services.ClazzData.Shared;
import com.api.filestorage.services.file_shared.MusicSharedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/shared/musics")
public class MusicSharedController implements BaseSharedController<MusicFileSharedEntity> {

    @Autowired
    private MusicSharedService musicSharedService;

    @Override
    public List<? extends BaseFileShared> findByOwner(String owner) {
        return BaseSharedController.super.findByOwnerImpl(owner, musicSharedService);
    }

    @Override
    public List<? extends BaseFileShared> findByReceiver(String receiver) {
        return BaseSharedController.super.findByReceiverImpl(receiver, musicSharedService);
    }

    @Override
    public void deleteById(int id) {
        BaseSharedController.super.deleteByIdImpl(id, musicSharedService);
    }

    @Override
    public void addReceiver(Shared receivers) {
        BaseSharedController.super.addReceiverImpl(receivers, musicSharedService);
    }
}
