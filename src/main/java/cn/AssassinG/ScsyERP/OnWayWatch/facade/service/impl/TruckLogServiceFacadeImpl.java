package cn.AssassinG.ScsyERP.OnWayWatch.facade.service.impl;

import cn.AssassinG.ScsyERP.OnWayWatch.core.biz.TruckLogBiz;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.TruckLog;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.Warn;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.service.TruckLogServiceFacade;
import cn.AssassinG.ScsyERP.common.core.biz.BaseBiz;
import cn.AssassinG.ScsyERP.common.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("TruckLogServiceFacade")
public class TruckLogServiceFacadeImpl extends BaseServiceImpl<TruckLog> implements TruckLogServiceFacade {
    @Autowired
    private TruckLogBiz truckLogBiz;
    protected BaseBiz<TruckLog> getBiz() {
        return this.truckLogBiz;
    }

    /**
     * 屏蔽create方法
     * @param entity
     * @return
     */
    public Long create(TruckLog entity){
        return -1L;
    }

    public Long createWithWarn(TruckLog truckLog, Warn warn) {
        return truckLogBiz.createWithWarn(truckLog, warn);
    }

    public void updateByMap(Long entityId, Map<String, String> paramMap) {
        this.truckLogBiz.updateByMap(entityId, paramMap);
    }
}
