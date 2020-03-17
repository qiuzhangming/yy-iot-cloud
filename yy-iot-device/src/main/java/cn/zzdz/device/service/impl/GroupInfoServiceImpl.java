package cn.zzdz.device.service.impl;

import cn.zzdz.common.entity.device.GroupDeviceRelKey;
import cn.zzdz.common.entity.device.GroupInfo;
import cn.zzdz.common.entity.device.GroupInfoExample;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.utils.IdWorker;
import cn.zzdz.device.dao.GroupInfoMapper;
import cn.zzdz.device.service.GroupDeviceRelService;
import cn.zzdz.device.service.GroupInfoService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Autowired
    private GroupDeviceRelService groupDeviceRelService;

    @Override
    public int save(GroupInfo groupInfo) {
        // 设置id,创建时间
        String id = idWorker.nextId()+"";
        Preconditions.checkArgument(id.trim().length()>0, ResultCode.ID_ERROR.message());
        groupInfo.setId(id);
        groupInfo.setGmtCreat(new Date());

        return groupInfoMapper.insertSelective(groupInfo);
    }

    @Override
    public GroupInfo findById(String id) {
        return groupInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(String id, GroupInfo groupInfo) {
        Preconditions.checkArgument(id.trim().length()>0, ResultCode.ID_ERROR.message());
        groupInfo.setId(id);
        groupInfo.setGmtModified(new Date());
        return groupInfoMapper.updateByPrimaryKeySelective(groupInfo);
    }

    @Override
    public int deleyeById(String id) {
        // 如果分组下有设备，不允许删除
        List<GroupDeviceRelKey> groupDeviceRel = groupDeviceRelService.findByGroupIds(Collections.singletonList(id));
        Preconditions.checkArgument((groupDeviceRel.size() <= 0), ResultCode.FAIL.message());
        return groupInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<GroupInfo> findByCompanyIdAndModelAndType(String companyId, int model, int type) {
        GroupInfoExample example = new GroupInfoExample();
        GroupInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId)
                .andModelEqualTo((short) model)
                .andTypeEqualTo((short) type);

        List<GroupInfo> groupInfos = groupInfoMapper.selectByExample(example);
        return groupInfos;
    }

    @Override
    public void validId(String id) {
        GroupInfo groupInfo = findById(id);
        Preconditions.checkArgument(!Objects.isNull(groupInfo), ResultCode.ID_ERROR.message());
    }
}
