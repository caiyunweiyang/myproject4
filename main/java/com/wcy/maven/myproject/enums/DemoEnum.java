package com.wcy.maven.myproject.enums;

import lombok.Data;
import lombok.Getter;

/**
 * TODO
 *
 * @author wcy
 * @date 2020-04-15 18:02
 */
@Getter
public enum DemoEnum {
    CONVERT_TO_EXCELDATA(0, "数据转换");
    private Integer key;
    private String desc;

    DemoEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
