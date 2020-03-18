package cn.zzdz.common.dto.device;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BaiduLocationBean {
    /**
     * status : 0
     * result : {"location":{"lng":121.50989077799083,"lat":31.22932842411674},"formatted_address":"上海市黄浦区中山南路187","business":"外滩,陆家嘴,董家渡","addressComponent":{"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"上海市","city":"上海市","city_level":2,"district":"黄浦区","town":"","town_code":"","adcode":"310101","street":"中山南路","street_number":"187","direction":"东北","distance":"91"},"pois":[],"roads":[],"poiRegions":[],"sematic_description":"","cityCode":289}
     */

    private int status;
    private ResultBean result;

    @NoArgsConstructor
    @Data
    public static class ResultBean {
        /**
         * location : {"lng":121.50989077799083,"lat":31.22932842411674}
         * formatted_address : 上海市黄浦区中山南路187
         * business : 外滩,陆家嘴,董家渡
         * addressComponent : {"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"上海市","city":"上海市","city_level":2,"district":"黄浦区","town":"","town_code":"","adcode":"310101","street":"中山南路","street_number":"187","direction":"东北","distance":"91"}
         * pois : []
         * roads : []
         * poiRegions : []
         * sematic_description :
         * cityCode : 289
         */

        private LocationBean location;
        private String formattedAddress;
        private String business;
        private AddressComponentBean addressComponent;
        private String sematicDescription;
        private int cityCode;
        private List<?> pois;
        private List<?> roads;
        private List<?> poiRegions;

        @NoArgsConstructor
        @Data
        public static class LocationBean {
            /**
             * lng : 121.50989077799083
             * lat : 31.22932842411674
             */

            private double lng;
            private double lat;
        }

        @NoArgsConstructor
        @Data
        public static class AddressComponentBean {
            /**
             * country : 中国
             * country_code : 0
             * country_code_iso : CHN
             * country_code_iso2 : CN
             * province : 上海市
             * city : 上海市
             * city_level : 2
             * district : 黄浦区
             * town :
             * town_code :
             * adcode : 310101
             * street : 中山南路
             * street_number : 187
             * direction : 东北
             * distance : 91
             */

            private String country;
            private int countryCode;
            private String countryCodeIso;
            private String countryCodeIso2;
            private String province;
            private String city;
            private int cityLevel;
            private String district;
            private String town;
            private String townCode;
            private String adcode;
            private String street;
            private String streetNumber;
            private String direction;
            private String distance;
        }
    }
}
