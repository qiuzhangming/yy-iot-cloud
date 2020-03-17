package cn.zzdz.device.controller;

import cn.zzdz.common.dto.device.*;
import cn.zzdz.common.entity.device.DeviceInfo;
import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.validated.CreatMethod;
import cn.zzdz.common.validated.UpdateMethod;
import cn.zzdz.device.service.DeviceInfoService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@Api(tags="设备管理")
@Slf4j
@Validated
@RequestMapping("/device")
@RestController
public class DeviceController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @ApiOperation(value="添加控制柜")
    @PostMapping("/distributionbox")
    public Result addDistributionbox(@Validated({CreatMethod.class}) @RequestBody DistributionboxDto distributionboxDto) {
        log.info("添加控制柜：{}", JSON.toJSONString(distributionboxDto));
        int count = deviceInfoService.addDistributionbox(distributionboxDto);
        return getResult(count);
    }

    @ApiOperation(value="添加rtu")
    @PostMapping("/rtu")
    public Result addRtu(@Validated({CreatMethod.class}) @RequestBody RtuDto rtuDto) {
        log.info("添加rtu：{}", JSON.toJSONString(rtuDto));
        deviceInfoService.addRtu(rtuDto);
        int count = deviceInfoService.addRtu(rtuDto);
        return getResult(count);
    }

    @ApiOperation(value="添加网关")
    @PostMapping("/gateway")
    public Result addGateway(@Validated({CreatMethod.class}) @RequestBody GatewayDto gatewayDto) {
        log.info("添加网关：{}", JSON.toJSONString(gatewayDto));
        int count =deviceInfoService.addGateway(gatewayDto);
        return getResult(count);
    }

    @ApiOperation(value="添加灯杆")
    @PostMapping("/")
    public Result addLamppost(@Validated({CreatMethod.class}) @RequestBody LamppostDto lamppostDto) {
        log.info("添加灯杆：{}", JSON.toJSONString(lamppostDto));
        int count =deviceInfoService.addLamppost(lamppostDto);
        return getResult(count);
    }

    @ApiOperation(value="添加单灯控制器")
    @PostMapping("/lightcontroller")
    public Result addLightcontroller(@Validated({CreatMethod.class}) @RequestBody LightcontrollerDto lightcontrollerDto) {
        log.info("添加单灯控制器：{}", JSON.toJSONString(lightcontrollerDto));
        int count = deviceInfoService.addLightcontroller(lightcontrollerDto);
        return getResult(count);
    }

    @ApiOperation(value="根据id删除")
    @DeleteMapping("/{id}")
    public Result deleyeById(@Size(min = ID_SIZE) @PathVariable("id") String id) {
        int count = deviceInfoService.deleyeById(id);
        return getResult(count);
    }

    @ApiOperation(value="根据id修改")
    @PutMapping("/{id}")
    public Result updateById(@Size(min = ID_SIZE) @PathVariable("id") String id, @Validated({UpdateMethod.class}) @RequestBody DeviceInfo deviceInfo) {
        int count = deviceInfoService.updateById(id, deviceInfo);
        return getResult(count);
    }

    @ApiOperation(value="根据id查询")
    @GetMapping("/{id}")
    public Result findById(@Size(min = ID_SIZE) @PathVariable("id") String id) {
        DeviceInfo deviceInfo = deviceInfoService.findById(id);
        return new Result(ResultCode.SUCCESS, deviceInfo);
    }

    @ApiOperation(value="根据公司id查询")
    @GetMapping("/company/{id}")
    public Result findByCompanyId(@Size(min = ID_SIZE) @PathVariable("id") String id) {
        List<DeviceInfo> deviceInfo = deviceInfoService.findByCompanyId(id);
        return new Result(ResultCode.SUCCESS, deviceInfo);
    }

    @ApiOperation(value="根据pid查询")
    @GetMapping("/pid/{id}")
    public Result findByPid(@Size(min = ID_SIZE) @PathVariable("id") String id) {
        List<DeviceInfo> deviceInfo = deviceInfoService.findByPid(id);
        return new Result(ResultCode.SUCCESS, deviceInfo);
    }

    /**
     * 根据影响行数返回结果
     * @param count
     * @return
     */
    private Result getResult(int count) {
        if (count > 0 ) {
            return new Result(ResultCode.SUCCESS,count);
        }
        return new Result(ResultCode.FAIL,count);
    }
}
