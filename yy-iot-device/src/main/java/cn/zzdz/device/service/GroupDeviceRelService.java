package cn.zzdz.device.service;

import cn.zzdz.common.entity.device.GroupDeviceRelKey;

import java.util.List;

public interface GroupDeviceRelService {
    int save(GroupDeviceRelKey groupDeviceRel);

    int deleteByGroupId(String groupId);

    List<GroupDeviceRelKey> findByGroupId(String groupId);

    List<GroupDeviceRelKey> findByGroupIds(List<String> groupIds);
}
