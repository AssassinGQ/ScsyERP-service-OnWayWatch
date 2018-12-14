package cn.AssassinG.ScsyERP.OnWayWatch.core.dao.impl;

import cn.AssassinG.ScsyERP.OnWayWatch.core.dao.TruckLogDao;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.TruckLog;
import cn.AssassinG.ScsyERP.common.core.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="TruckLogDao")
public class TruckLogDaoImpl extends BaseDaoImpl<TruckLog> implements TruckLogDao {
}
