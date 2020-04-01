package com.wqy.modules.system.dto.output;

import com.google.common.collect.Lists;
import com.wqy.modules.system.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * <p> 菜单树节点 </p>
 *
 * @author : wqy
 * @description :
 * @date : 2019/8/19 18:54
 */
@Data
public class MenuTreeNode extends Menu {

    List<MenuTreeNode> children = Lists.newArrayList();

}
