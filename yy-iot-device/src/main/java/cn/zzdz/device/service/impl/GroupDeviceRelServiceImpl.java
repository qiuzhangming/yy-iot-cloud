package cn.zzdz.device.service.impl;

import cn.zzdz.common.entity.device.DeviceInfo;
import cn.zzdz.common.entity.device.GroupDeviceRelExample;
import cn.zzdz.common.entity.device.GroupDeviceRelKey;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.utils.IdWorker;
import cn.zzdz.device.dao.GroupDeviceRelMapper;
import cn.zzdz.device.service.DeviceInfoService;
import cn.zzdz.device.service.GroupDeviceRelService;
import cn.zzdz.device.service.GroupInfoService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupDeviceRelServiceImpl implements GroupDeviceRelService {
    @Autowired
    private GroupDeviceRelMapper groupDeviceRelMapper;

    @Autowired
    private GroupInfoService groupInfoService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Override
    public int save(GroupDeviceRelKey groupDeviceRel) {
        // 验证分组id是否有效
        groupInfoService.validId(groupDeviceRel.getGroupId());
        // 验证设备id是否有效
        deviceInfoService.validId(groupDeviceRel.getDeviceId());
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
