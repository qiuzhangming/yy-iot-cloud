package cn.zzdz.device.service.impl;

import cn.zzdz.api.map.BaiduMapService;
import cn.zzdz.common.dto.device.BaiduLocationBean;
import cn.zzdz.device.service.AddressService;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@RefreshScope
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "com.zzdz.baidu")
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private BaiduMapService mapService;

    private String ak;
    private String output;
    private String coordtype;

    /**
     * 获取地址信息并校验完整性
     * 此方法确保字段不为空，调用者无需再次验证数据
     * @param lat
     * @param lng
     */
    @Override
    public Map<String, String> getAddress(String lat, String lng) {
        log.info("百度参数设置：{}，{}，{}",ak, output, coordtype);
        String location = lat + "," + lng;

        // 请求服务器获取地址信息
        String locationStr = mapService.reverseGgeocoding(ak, output, coordtype, location);
        BaiduLocationBean baiduLocationBean = JSON.parseObject(locationStr, BaiduLocationBean.class);
        Preconditions.checkNotNull(baiduLocationBean);
        log.info("address:{}",baiduLocationBean.toString());
        BaiduLocationBean.ResultBean.AddressComponentBean addressComponent = baiduLocationBean.getResult().getAddressComponent();
        Preconditions.checkNotNull(addressComponent);

        String address = baiduLocationBean.getResult().getFormattedAddress();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(address), "address isNullOrEmpty");
        String province = addressComponent.getProvince();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(province), "province isNullOrEmpty");
        String city = addressComponent.getCity();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(city), "city isNullOrEmpty");
        String district = addressComponent.getDistrict();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(district), "district isNullOrEmpty");

        Map<String, String> map = new HashMap<>();
        map.put("address", address);
        map.put("province", province);
        map.put("city", city);
        map.put("district", district);
        return map;
    }
}
