package com.wqy.wx.back.configer;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: MetaObjectHandlerConfig
 * @Description: TODO
 * @Author licm
 * @Date 2020/3/13 15:43
 * @Version V1.0
 * @Explain :
 **/
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
//        String userId = TokenUtil.getRequest().getHeader("userId");
//        setFieldValByName("id", UuidUtil.getUniqID(0L), metaObject);
//        setFieldValByName("add_user_id", userId, metaObject);
//        setFieldValByName("add_time", new Date(), metaObject);
//        setFieldValByName("update_user_id", userId, metaObject);
//        setFieldValByName("update_time", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        String userId = TokenUtil.getRequest().getHeader("userId");
//        setFieldValByName("update_user_id", userId, metaObject);
//        setFieldValByName("update_time", new Date(), metaObject);
    }
}
