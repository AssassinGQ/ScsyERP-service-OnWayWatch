package cn.AssassinG.ScsyERP.OnWayWatch.core.biz;

import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.TruckLog;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.Warn;
import cn.AssassinG.ScsyERP.common.core.biz.BaseBiz;

public interface TruckLogBiz extends BaseBiz<TruckLog> {
    Long createWithWarn(TruckLog truckLog, Warn warn);
}
