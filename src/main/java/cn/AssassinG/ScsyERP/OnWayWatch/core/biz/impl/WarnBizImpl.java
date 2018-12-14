package cn.AssassinG.ScsyERP.OnWayWatch.core.biz.impl;

import cn.AssassinG.ScsyERP.File.facade.entity.MyFile;
import cn.AssassinG.ScsyERP.File.facade.service.MyFileServiceFacade;
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

    @Autowired
    private MyFileServiceFacade myFileServiceFacade;

    @Transactional
    public void addPicture(Long entityId, Long pictureId) {
        if(entityId == null){
            throw new WarnBizException(WarnBizException.WARNBIZ_PARAMS_ILLEGAL, "异常信息主键不能为空");
        }
        if(pictureId == null){
            throw new WarnBizException(WarnBizException.WARNBIZ_PARAMS_ILLEGAL, "图片基本信息主键不能为空");
        }
        Warn warn = this.getById(entityId);
        if(warn == null || warn.getIfDeleted()){
            throw new WarnBizException(WarnBizException.WARNBIZ_NOSUIT_RESULT, "没有符合条件的异常信息，entityId: %d", entityId);
        }
        MyFile myFile = myFileServiceFacade.getById(pictureId);
        if(myFile == null || myFile.getIfDeleted()){
            throw new WarnBizException(WarnBizException.WARNBIZ_NOSUIT_RESULT, "没有符合条件的图片基本信息，entityId: %d", entityId);
        }
        warn.getPictures().add(myFile.getId());
        this.update(warn);
    }

    @Transactional
    public void removePicture(Long entityId, Long pictureId) {
        if(entityId == null){
            throw new WarnBizException(WarnBizException.WARNBIZ_PARAMS_ILLEGAL, "异常信息主键不能为空");
        }
        if(pictureId == null){
            throw new WarnBizException(WarnBizException.WARNBIZ_PARAMS_ILLEGAL, "图片基本信息主键不能为空");
        }
        Warn onTruckForm = this.getById(entityId);
        if(onTruckForm == null || onTruckForm.getIfDeleted()){
            throw new WarnBizException(WarnBizException.WARNBIZ_NOSUIT_RESULT, "没有符合条件的异常信息，entityId: %d", entityId);
        }
        MyFile myFile = myFileServiceFacade.getById(pictureId);
        if(myFile == null || myFile.getIfDeleted()){
            throw new WarnBizException(WarnBizException.WARNBIZ_NOSUIT_RESULT, "没有符合条件的图片基本信息，entityId: %d", entityId);
        }
        onTruckForm.getPictures().remove(myFile.getId());
        this.update(onTruckForm);
    }
}
