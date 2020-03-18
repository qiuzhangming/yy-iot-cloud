package cn.zzdz.api.map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 访问百度地图的api接口
 */

@FeignClient(name = "baidu-map-api", url = "http://api.map.baidu.com/")
public interface BaiduMapService {

    @GetMapping("/reverse_geocoding/v3/")
    String reverseGgeocoding(@RequestParam("ak") String ak,
                             @RequestParam("output") String output,
                             @RequestParam("coordtype") String coordtype,
                             @RequestParam("location")String location);
}
