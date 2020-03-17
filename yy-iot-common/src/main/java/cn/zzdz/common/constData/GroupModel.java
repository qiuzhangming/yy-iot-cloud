package cn.zzdz.common.constData;

/**
 * model(分组模式):
 * 1:控制柜分组
 * 2:网关分组
 * 3:灯杆分组
 * 4:单灯分组
 *
 * type(分组类型):
 * 1:默认,通常用于树状图默认的加载
 * 2:其他值:扩展类型,例如用户自定义的分组。
 */
public enum GroupModel {
    GROUP_MODEL_DISTRIBUTIONBOX(1, "控制柜分组"),
    GROUP_MODEL_RTU(2, "rtu分组"),
    GROUP_MODEL_GATEWAY(3, "网关分组"),
    GROUP_MODEL_LAMPPOST(4, "灯杆分组"),
    GROUP_MODEL_LIGHTCONTROLLER(5, "单灯分组"),

    GROUP_TYPE_DEFAULT(1,"默认类型,通常用于树状图默认的加载"),
    GROUP_TYPE_USER_1(2,"用户自定义1"),
    GROUP_TYPE_USER_2(3,"用户自定义2"),
    GROUP_TYPE_USER_3(4,"用户自定义3"),
    ;

    private Integer code;
    private String description;

    GroupModel() {
    }

    GroupModel(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
