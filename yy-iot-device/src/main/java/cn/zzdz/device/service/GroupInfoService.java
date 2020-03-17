package cn.zzdz.device.service;

import cn.zzdz.common.entity.device.GroupInfo;

import java.util.List;

public interface GroupInfoService {

    int save(GroupInfo groupInfo);

    GroupInfo findById(String id);

    int updateById(String id, GroupInfo groupInfo);

    int deleyeById(String id);

    List<GroupInfo> findByCompanyIdAndModelAndType(String companyId, int model, int type);

    void validId(String id);
}
