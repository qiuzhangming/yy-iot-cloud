package cn.zzdz.device.service;

import cn.zzdz.common.dto.device.*;
import cn.zzdz.common.entity.device.DeviceInfo;
import cn.zzdz.common.entity.device.GroupDeviceRelKey;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeviceInfoService {

    int addDistributionbox(DistributionboxDto distributionboxDto);

    int addRtu(RtuDto rtuDto);

    int addGateway(GatewayDto gatewayDto);

    int addLamppost(LamppostDto lamppostDto);

    int addLightcontroller(LightcontrollerDto lightcontrollerDto);

    int save(DeviceInfo deviceInfo);

    DeviceInfo findById(String id);

    int deleyeById(String id);

    int updateById(String id, DeviceInfo deviceInfo);

    List<DeviceInfo> findByCompanyIdAndDeviceType(String companyId, int deviceType);

    List<DeviceInfo> findByPid(String pid);

    PageInfo<DeviceInfo> findByIds(List<String> ids, int pageNum, int pageSize);

    void validId(String id);
}
