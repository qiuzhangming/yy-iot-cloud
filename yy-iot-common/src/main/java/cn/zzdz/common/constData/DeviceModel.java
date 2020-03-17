package cn.zzdz.common.constData;

/**
 * device_type(设备类型)：
 * 1.控制柜
 * 2:rtu
 * 3:网关
 * 4:灯杆
 * 5:单灯控制器
 *
 * hardware_version(硬件版本):
 * rtu:
 * 1:目前只有一个版本
 * gateway:
 * 1:老版本的
 * 2:机场版本
 * controller:
 * 1:老版本
 * 2:NB单灯
 * 3:机场单灯
 *
 * comm_type(通讯方式):
 * 0:无需通讯，通常为虚拟设备或灯杆
 * 1:GPRS
 * 2:NB
 *
 * usage_type(使用类型):
 * rtu:
 * 1:路灯
 * 2:光彩
 * 单灯控制器:
 * 1:主灯
 * 2:副灯
 * 3:灯带
 * 4:机场高杆灯
 */
public enum DeviceModel {
    RTU_HARDWAREVERSION_V1(1, "rtu硬件版本V1"),
    RTU_SOFTWAREVERSION_V1(1, "rtu软件版本V1"),

    GATEWAY_HARDWAREVERSION_V1(1, "网关硬件版本V1"),
    GATEWAY_SOFTWAREVERSION_V1(1, "网关软件版本V1"),
    GATEWAY_HARDWAREVERSION_AIR_V1(2, "机场网关硬件版本V1"),
    GATEWAY_SOFTWAREVERSION_AIR_V1(1, "机场网关软件版本V1"),

    LIGHTCONTROLLER_HARDWAREVERSION_V1(1, "单灯硬件版本V1"),
    LIGHTCONTROLLER_SOFTWAREVERSION_V1(1, "单灯软件版本V1"),
    LIGHTCONTROLLER_HARDWAREVERSION_NB_V1(2, "nb单灯硬件版本V1"),
    LIGHTCONTROLLER_SOFTWAREVERSION_NB_V1(1, "nb单灯软件版本V1"),
    LIGHTCONTROLLER_HARDWAREVERSION_AIR_V1(3, "机场单灯硬件版本V1"),
    LIGHTCONTROLLER_SOFTWAREVERSION_AIR_V1(1, "机场单灯软件版本V1"),

    DEVICE_TYPE_DISTRIBUTIONBOX(1, "控制柜"),
    DEVICE_TYPE_RTU(2, "RTU"),
    DEVICE_TYPE_GATEWAY(3, "网关"),
    DEVICE_TYPE_LAMPPOST(4, "灯杆"),
    DEVICE_TYPE_LIGHTCONTROLLER(5, "单灯控制器"),

    COMM_TYPE_NONE(0, "无需通讯，通常为虚拟设备或灯杆"),
    COMM_TYPE_GPRS(1, "GPRS"),
    COMM_TYPE_NB(2, "NB"),

    USAGE_TYPE_RTU_STREETLAMP(1, "路灯工程"),
    USAGE_TYPE_RTU_BRIGHTLIGHTING(2, "光彩工程"),

    USAGE_TYPE_CONTROLLER_MASTER(1, "主灯"),
    USAGE_TYPE_CONTROLLER_SLAVE(2, "副灯"),
    USAGE_TYPE_CONTROLLER_BELT(3, "灯带"),
    USAGE_TYPE_CONTROLLER_AIR(4, "机场高杆灯"),
    ;

    private Integer code;
    private String description;

    DeviceModel() {
    }

    DeviceModel(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
