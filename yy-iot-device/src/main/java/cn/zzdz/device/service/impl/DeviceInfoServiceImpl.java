package cn.zzdz.device.service.impl;

import cn.zzdz.common.constData.DeviceModel;
import cn.zzdz.common.dto.device.*;
import cn.zzdz.common.entity.device.DeviceInfo;
import cn.zzdz.common.entity.device.DeviceInfoExample;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.utils.IdWorker;
import cn.zzdz.device.dao.DeviceInfoMapper;
import cn.zzdz.device.service.DeviceInfoService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static cn.zzdz.common.constData.DeviceModel.DEVICE_TYPE_DISTRIBUTIONBOX;

/**
 * device_sn(设备编号):
 * 1:老版本未区分项目的用companyid+dtuid+mcuid
 * 2:机场设备直接填写对应的id
 * 3:nb设备填写平台返回的uuid
 * 4:控制柜可填写控制柜编号
 * 5:灯杆可填写灯杆编号
 */
@Service
@Slf4j
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    /**
     * 老版本未区分项目的deviceSn生成规则
     * companyid+dtuid+mcuid
     * @param companyId
     * @param dtuId
     * @param mcuId
     * @return
     */
    public String generateDeviceSn(String companyId,String dtuId, String mcuId) {
        return companyId+"-"+dtuId+"-"+mcuId;
    }

    /**
     * 添加控制柜
     * @param distributionboxDto
     * @return
     */
    @Override
    public int addDistributionbox(DistributionboxDto distributionboxDto) {
        DeviceInfo deviceInfo = new DeviceInfo();
        BeanUtils.copyProperties(distributionboxDto, deviceInfo);
        //设置设备类型为控制柜,device_type=1
        deviceInfo.setDeviceType((short) DeviceModel.DEVICE_TYPE_DISTRIBUTIONBOX.code());
        return save(deviceInfo);
    }

    /**
     * 添加rtu设备
     * @param rtuDto
     * @return
     */
    @Override
    public int addRtu(RtuDto rtuDto) {
        DeviceInfo deviceInfo = new DeviceInfo();
        BeanUtils.copyProperties(rtuDto, deviceInfo);
        //device_type=2 hardware_version=1
        deviceInfo.setDeviceType((short) DeviceModel.DEVICE_TYPE_RTU.code());
        //根据规则生成sn
        if (rtuDto.getHardwareVersion() == DeviceModel.GATEWAY_HARDWAREVERSION_V1.code()) {
            deviceInfo.setDeviceSn(generateDeviceSn(
                    rtuDto.getCompanyId(),
                    rtuDto.getDtuId(),
                    rtuDto.getMcuId()));
        }
        return save(deviceInfo);
    }

    /**
     * 添加旧版的网关 device_type=3,hardware_version=1
     * 添加机场网关 device_type=3,hardware_version=2
     * @param gatewayDto
     * @return
     */
    @Override
    public int addGateway(GatewayDto gatewayDto) {
        DeviceInfo deviceInfo = new DeviceInfo();
        BeanUtils.copyProperties(gatewayDto, deviceInfo);
        deviceInfo.setDeviceType((short) DeviceModel.DEVICE_TYPE_GATEWAY.code());
        // 添加旧版的网关需要生成sn
        if (gatewayDto.getHardwareVersion() == DeviceModel.GATEWAY_HARDWAREVERSION_V1.code()) {
            deviceInfo.setDeviceSn(generateDeviceSn(
                    gatewayDto.getCompanyId(),
                    gatewayDto.getDtuId(),
                    gatewayDto.getMcuId()));
        }
        return save(deviceInfo);
    }

    /**
     * 添加灯杆 device_type=4
     * @param lamppostDto
     * @return
     */
    @Override
    public int addLamppost(LamppostDto lamppostDto) {
        DeviceInfo deviceInfo = new DeviceInfo();
        BeanUtils.copyProperties(lamppostDto, deviceInfo);
        deviceInfo.setDeviceType((short) DeviceModel.DEVICE_TYPE_LAMPPOST.code());
        return save(deviceInfo);
    }

    /**
     * 添加旧版的单灯 device_type=5, hardware_version=1
     * 添加nb单灯 device_type=5, hardware_version=2
     * 添加机场单灯 device_type=5, hardware_version=3
     * @param lightcontrollerDto
     * @return
     */
    @Override
    public int addLightcontroller(LightcontrollerDto lightcontrollerDto) {
        DeviceInfo deviceInfo = new DeviceInfo();
        BeanUtils.copyProperties(lightcontrollerDto, deviceInfo);
        deviceInfo.setDeviceType((short) DeviceModel.DEVICE_TYPE_LIGHTCONTROLLER.code());
        // nb设备需要到nb平台注册
        if (lightcontrollerDto.getHardwareVersion() == DeviceModel.LIGHTCONTROLLER_HARDWAREVERSION_NB_V1.code()) {
            log.info("nb通讯方式,需要先到nb平台注册,获取注册device_sn.");
            deviceInfo.setDeviceSn("nb-");
        }
        return save(deviceInfo);
    }

    @Override
    public int save(DeviceInfo deviceInfo) {

        // 设置id,启用标志,创建时间
        String id = idWorker.nextId()+"";
        Preconditions.checkArgument(id.trim().length()>0, ResultCode.ID_ERROR.message());
        deviceInfo.setId(id);
        deviceInfo.setEnabledFlag(true);
        deviceInfo.setGmtCreat(new Date());
        return deviceInfoMapper.insertSelective(deviceInfo);
    }

    @Override
    public DeviceInfo findById(String id) {
        return deviceInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleyeById(String id) {
        /**
         * 删除前的检查工作，后续补充
         */
        return deviceInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(String id, DeviceInfo deviceInfo) {
        deviceInfo.setId(id);
        deviceInfo.setGmtModified(new Date());
        return deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
    }

    @Override
    public List<DeviceInfo> findByCompanyId(String companyId) {
        DeviceInfoExample example = new DeviceInfoExample();
        DeviceInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        return deviceInfoMapper.selectByExample(example);
    }

    @Override
    public List<DeviceInfo> findByPid(String pid) {
        DeviceInfoExample example = new DeviceInfoExample();
        DeviceInfoExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        return deviceInfoMapper.selectByExample(example);
    }

    @Override
    public void validId(String id) {
        DeviceInfo deviceInfo = findById(id);
        Preconditions.checkArgument(Objects.nonNull(deviceInfo), ResultCode.ID_ERROR.message());
    }
}
