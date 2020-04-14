package com.wqy.wx.back.common.util.excel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ExclDto
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/13 16:54
 * @Version V1.0
 */
@Data
public class ExclDto implements Serializable {
    private  List<?> dataList;
    private Map<String, String> titleMap;
    private String sheetName;

    public ExclDto(List<?> dataList, Map<String, String> titleMap, String sheetName) {
        this.dataList = dataList;
        this.titleMap = titleMap;
        this.sheetName = sheetName;
    }
}
