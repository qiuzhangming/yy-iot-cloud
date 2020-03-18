package cn.zzdz.api.device;

import cn.zzdz.common.entity.device.DeviceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "streetlamp-info", url = "http://api.map.baidu.com/")
public interface DeviceService {

    @RequestMapping("/v1.0.0/iocm/app/sql/gateway/getLampGateway")
    DeviceInfo findByDeviceSn(@RequestParam(value = "deviceSn") String deviceSn);

}
