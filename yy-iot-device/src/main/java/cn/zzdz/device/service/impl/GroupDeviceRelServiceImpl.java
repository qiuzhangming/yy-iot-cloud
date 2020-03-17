package cn.zzdz.device.service.impl;

import cn.zzdz.common.entity.device.GroupDeviceRelExample;
import cn.zzdz.common.entity.device.GroupDeviceRelKey;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.utils.IdWorker;
import cn.zzdz.device.dao.GroupDeviceRelMapper;
import cn.zzdz.device.service.GroupDeviceRelService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupDeviceRelServiceImpl implements GroupDeviceRelService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private GroupDeviceRelMapper groupDeviceRelMapper;

    @Override
    public int save(GroupDeviceRelKey groupDeviceRel) {
        String deviceId = idWorker.nextId()+"";
        String groupId = idWorker.nextId()+"";
        Preconditions.checkArgument(deviceId.trim().length()>0, ResultCode.ID_ERROR.message());
        Preconditions.checkArgument(groupId.trim().length()>0, ResultCode.ID_ERROR.message());

        groupDeviceRel.setDeviceId(deviceId);
        groupDeviceRel.setGroupId(groupId);

        // 保存
        return groupDeviceRelMapper.insert(groupDeviceRel);
    }

    @Override
    public int deleteByGroupId(String groupId) {
        GroupDeviceRelExample example = new GroupDeviceRelExample();
        GroupDeviceRelExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        return groupDeviceRelMapper.deleteByExample(example);
    }

    @Override
    public List<GroupDeviceRelKey> findByGroupId(String groupId) {
        GroupDeviceRelExample example = new GroupDeviceRelExample();
        GroupDeviceRelExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        return groupDeviceRelMapper.selectByExample(example);
    }

    @Override
    public List<GroupDeviceRelKey> findByGroupIds(List<String> groupIds) {
        GroupDeviceRelExample example = new GroupDeviceRelExample();
        GroupDeviceRelExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdIn(groupIds);
        return groupDeviceRelMapper.selectByExample(example);
    }
}
