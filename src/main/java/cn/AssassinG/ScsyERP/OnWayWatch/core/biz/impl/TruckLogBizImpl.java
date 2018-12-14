package cn.AssassinG.ScsyERP.OnWayWatch.core.biz.impl;

import cn.AssassinG.ScsyERP.OnWayWatch.core.biz.TruckLogBiz;
import cn.AssassinG.ScsyERP.OnWayWatch.core.biz.WarnBiz;
import cn.AssassinG.ScsyERP.OnWayWatch.core.dao.TruckLogDao;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.TruckLog;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.Warn;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.exceptions.TruckLogBizException;
import cn.AssassinG.ScsyERP.common.core.biz.impl.BaseBizImpl;
import cn.AssassinG.ScsyERP.common.core.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("TruckLogBiz")
public class TruckLogBizImpl extends BaseBizImpl<TruckLog> implements TruckLogBiz {
    @Autowired
    private TruckLogDao truckLogDao;
    @Autowired
    private WarnBiz warnBiz;

    protected BaseDao<TruckLog> getDao() {
        return this.truckLogDao;
    }

    /**
     * @param entityId
     * @param paramMap 行车日志信息字段(啥都不能修改)
     */
    public void updateByMap(Long entityId, Map<String, String> paramMap) {

    }

    public Long createWithWarn(TruckLog truckLog, Warn warn) {
        Long truckLogId = this.create(truckLog);
        if(truckLog.getHasWarn()){
            if(warn == null){
                throw new TruckLogBizException(TruckLogBizException.TRUCKLOGBIZ_PARAMS_ILLEGAL, "异常信息不能为空");
            }
            Long warnId = warnBiz.create(warn);
            truckLog.setWarn(warnId);
            this.update(truckLog);
        }
        return truckLogId;
    }
}
