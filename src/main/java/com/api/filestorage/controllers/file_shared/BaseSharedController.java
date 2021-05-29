package com.api.filestorage.controllers.file_shared;

import java.util.List;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.services.ClazzData.Shared;
import com.api.filestorage.services.file_shared.BaseSharedService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// "/api/user/shared/? ? ?"
public interface BaseSharedController<T extends BaseFileShared> {

    @GetMapping("/owner/{owner}")
    public List<? extends BaseFileShared> findByOwner(@PathVariable String owner);

    default List<? extends BaseFileShared> findByOwnerImpl(String owner, BaseSharedService services) {
        return services.findByOwner(owner);
    }

    @GetMapping("/receiver/{receiver}")
    public List<? extends BaseFileShared> findByReceiver(@PathVariable String receiver);

    default List<? extends BaseFileShared> findByReceiverImpl(String receiver, BaseSharedService services) {
        return services.findByReceiver(receiver);
    }

    @PostMapping("/delete")
    public void deleteById(@RequestBody int id);

    default void deleteByIdImpl(int id, BaseSharedService services) {
        services.delete(id);
    }

    @PostMapping
    public void addReceiver(@RequestBody Shared receivers);

    default void addReceiverImpl(Shared receivers, BaseSharedService services) {
        services.addReceiver(receivers);
    }
}
