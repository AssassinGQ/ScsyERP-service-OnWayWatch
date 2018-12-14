package cn.AssassinG.ScsyERP.OnWayWatch.core.dao.impl;

import cn.AssassinG.ScsyERP.OnWayWatch.core.dao.WarnDao;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.Warn;
import cn.AssassinG.ScsyERP.common.core.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="WarnDao")
public class WarnDaoImpl extends BaseDaoImpl<Warn> implements WarnDao {
}
