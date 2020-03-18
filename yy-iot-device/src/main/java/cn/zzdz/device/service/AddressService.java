package cn.zzdz.device.service;

import java.util.Map;

/**
 * 地址解析服务
 */
public interface AddressService {
    Map<String, String> getAddress(String lat, String lng);
}
