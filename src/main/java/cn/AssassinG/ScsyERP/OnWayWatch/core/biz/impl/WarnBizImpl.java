package cn.AssassinG.ScsyERP.OnWayWatch.core.biz.impl;

import cn.AssassinG.ScsyERP.OnWayWatch.core.biz.WarnBiz;
import cn.AssassinG.ScsyERP.OnWayWatch.core.dao.WarnDao;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.Warn;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.enums.WarnStatus;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.exceptions.WarnBizException;
import cn.AssassinG.ScsyERP.common.core.biz.impl.BaseBizImpl;
import cn.AssassinG.ScsyERP.common.core.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component("WarnBiz")
public class WarnBizImpl extends BaseBizImpl<Warn> implements WarnBiz {
    @Autowired
    private WarnDao warnDao;
    protected BaseDao<Warn> getDao() {
        return this.warnDao;
    }

    /**
     * @param entityId
     * @param paramMap 异常信息字段(status)
     */
    @Transactional
    public void updateByMap(Long entityId, Map<String, String> paramMap) {
        if(entityId == null){
            throw new WarnBizException(WarnBizException.WARNBIZ_PARAMS_ILLEGAL, "异常信息主键不能为空");
        }
        Warn warn = this.getById(entityId);
        if(warn == null || warn.getIfDeleted()){
            throw new WarnBizException(WarnBizException.WARNBIZ_NOSUIT_RESULT, "没有符合条件的异常信息，entityId: %d", entityId);
        }
        WarnStatus status = WarnStatus.getEnum(paramMap.get("status"));
        if(status != null) {
            warn.setStatus(status);
            this.update(warn);
        }
    }
}
