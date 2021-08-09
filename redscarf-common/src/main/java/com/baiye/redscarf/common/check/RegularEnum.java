package com.baiye.redscarf.common.check;


import java.util.regex.Pattern;

/**
 * @author baiye
 * @since 2021/7/2 10:03 上午
 **/
public enum RegularEnum {

    NUMBER("^[0-9]*$", "Integer"),

    DATE("((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]", "Date"),

    BIG_DECIMAL("^[0-9]+\\.{0,1}[0-9]{0,2}$", "BigDecimal"),

    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", "email"),

    PHONE("((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))", "phone"),

    CUSTOM("", ""),

    NON("", ""),


    ;

    RegularEnum() {}

    RegularEnum(String regular, String objType) {
        this.regular = regular;
        this.objType = objType;
    }

    private String regular;

    private String objType;

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public static void main(String[] args) {
        boolean matches = Pattern.matches(DATE.regular, "2020-01-01 12:12:00");
        System.out.println(matches);
    }
}
