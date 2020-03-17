package cn.zzdz.device.service;

import cn.zzdz.common.dto.device.*;
import cn.zzdz.common.entity.device.DeviceInfo;

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

    List<DeviceInfo> findByCompanyId(String companyId);

    List<DeviceInfo> findByPid(String pid);

    void validId(String id);
}
