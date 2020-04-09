package com.wqy.wx.back.common.util.excel;

import lombok.Data;

import java.io.Serializable;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ExcelHead
 * @Description: TODO
 * @Author licm
 * @Date 2020/1/6 10:51
 * @Version V1.0
 * @Explain : 表头与实体属性关系映射实体
 **/
@Data
public class ExcelHead implements Serializable {
    /**
     * Excel名
     */
    private String excelName;
    /**
     * 实体类属性名
     */
    private String entityName;
    /**
     * 值必填
     */
    private boolean required = false;

    public ExcelHead() {
    }

    public ExcelHead(String excelName, String entityName) {
        this.excelName = excelName;
        this.entityName = entityName;
        this.required = false;
    }

    public ExcelHead(String excelName, String entityName, boolean required) {
        this.excelName = excelName;
        this.entityName = entityName;
        this.required = required;
    }
}
