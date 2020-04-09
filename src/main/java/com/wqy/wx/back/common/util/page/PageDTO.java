package com.wqy.wx.back.common.util.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页dto
 */
@ApiModel
@Data
@Valid
public class PageDTO implements Serializable {
    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageIndex;
    /**
     * 页面展示条数
     */
    @NotNull(message = "页面展示条数不能为空")
    @ApiModelProperty(value = "页面展示条数", required = true)
    private Integer pageSize;

    /**
     * 分页开始
     *
     * @return
     */
    @ApiModelProperty(hidden = true)
    public Integer getPageStart() {
        if (this.pageIndex == null || this.pageIndex <= 1) {
            return 0;
        } else {
            return (this.pageIndex - 1) * pageSize;
        }
    }
}
