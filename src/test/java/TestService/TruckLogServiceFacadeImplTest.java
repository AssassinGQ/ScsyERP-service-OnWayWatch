package TestService;

import cn.AssassinG.ScsyERP.OnWayWatch.core.biz.TruckLogBiz;
import cn.AssassinG.ScsyERP.OnWayWatch.core.dao.TruckLogDao;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.TruckLog;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.service.TruckLogServiceFacade;
import cn.AssassinG.ScsyERP.common.page.PageBean;
import cn.AssassinG.ScsyERP.common.page.PageParam;
import cn.AssassinG.ScsyERP.common.utils.StringUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class TruckLogServiceFacadeImplTest {
    private static Logger logger = Logger.getLogger(TruckLogServiceFacadeImplTest.class);
    @Autowired
    private TruckLogServiceFacade truckLogService;
    @Autowired
    private TruckLogBiz truckLogBiz;
    @Autowired
    private TruckLogDao truckLogDao;

    private Long truckLogId;

    @Before
    public void setUp() throws Exception {
        TruckLog truckLog = new TruckLog();
        truckLog.setCorporation(1L);
        truckLog.setOutStorageForm(1L);
        truckLog.setTruck(1L);
        truckLog.setDistance(9.9);
        truckLog.setTime(new Date());
        truckLog.setGPSX(1.1);
        truckLog.setGPSY(2.2);
        truckLog.setSpeed(3.3);
        truckLog.setFuelVol(4.4);
        truckLog.setLeftTirePressure(5.5);
        truckLog.setLeftTireTemp(6.6);
        truckLog.setRightTirePressure(7.7);
        truckLog.setRightTireTemp(8.8);
        truckLog.setHasWarn(false);
        truckLogId = truckLogBiz.create(truckLog);
    }

    @After
    public void tearDown() throws Exception {
        truckLogDao.delete(truckLogId);
    }

    @Test
    public void create() {
        TruckLog truckLog = new TruckLog();
        truckLog.setCorporation(1L);
        truckLog.setOutStorageForm(1L);
        truckLog.setTruck(1L);
        truckLog.setDistance(9.9);
        truckLog.setTime(new Date());
        truckLog.setGPSX(1.1);
        truckLog.setGPSY(2.2);
        truckLog.setSpeed(3.3);
        truckLog.setFuelVol(4.4);
        truckLog.setLeftTirePressure(5.5);
        truckLog.setLeftTireTemp(6.6);
        truckLog.setRightTirePressure(7.7);
        truckLog.setRightTireTemp(8.8);
        truckLog.setHasWarn(false);
        Long id = truckLogService.create(truckLog);
        if(id == null){
            throw new RuntimeException("Create nothing");
        }else {
            logger.info("Created : " + truckLogDao.getById(id));
        }
    }

    @Test
    public void update() {
        TruckLog truckLog = truckLogDao.getById(truckLogId);
        String new_name = StringUtils.getRandomStr(6);
        logger.info("Before Update: "+truckLog);
        truckLog.setPosture(new_name);
        truckLogService.update(truckLog);
        TruckLog truckLog_check = truckLogDao.getById(truckLogId);
        if(!truckLog_check.getPosture().equals(new_name)){
            throw new RuntimeException("Update failed");
        }else{
            logger.info("Updated succeed");
        }
    }

    @Test
    public void deleteById() {
        truckLogService.deleteById(truckLogId);
        TruckLog truckLog_check = truckLogDao.getById(truckLogId);
        if(!truckLog_check.getIfDeleted()){
            throw new RuntimeException("Delete failed");
        }else {
            logger.info("Delete succeed");
        }
    }

    @Test
    public void delete() {
        TruckLog truckLog_test = truckLogDao.getById(truckLogId);
        truckLogService.delete(truckLog_test);
        TruckLog truckLog_check = truckLogDao.getById(truckLogId);
        if(!truckLog_check.getIfDeleted()){
            throw new RuntimeException("Delete failed");
        }else {
            logger.info("Delete succeed");
        }
    }

    @Test
    public void getById() {
        TruckLog truckLog = truckLogService.getById(truckLogId);
        if(truckLog == null || truckLog.getIfDeleted()){
            throw new RuntimeException("getById null");
        }else{
            if(truckLog.getId().longValue() != truckLogId.longValue()){
                throw new RuntimeException("getById failed");
            }else{
                logger.info("getById succeed");
            }
        }
    }

    @Test
    public void getBy() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("IfDeleted", false);
        paramMap.put("Id", truckLogId);
        TruckLog truckLog = truckLogService.getBy(paramMap);
        if(truckLog.getId() != truckLogId){
            throw new RuntimeException("getBy failed");
        }else{
            logger.info("getBy succeed");
        }
    }

    @Test
    public void listBy() {
        TruckLog truckLog = truckLogDao.getById(truckLogId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("IfDeleted", false);
//        paramMap.put("TimeBegin", truckLog.getTime());
//        paramMap.put("TimeEnd", truckLog.getTime());
        paramMap.put("Time", truckLog.getTime());
        List<TruckLog> truckLogs = truckLogService.listBy(paramMap);
        for (int i = 0; i < truckLogs.size(); i++)
            logger.info("Item" + i + ":" + truckLogs.get(i));
    }

    @Test
    public void listPage() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("IfDeleted", false);
        PageParam pageParam = new PageParam(2, 2);
        PageBean<TruckLog> pageBean = truckLogService.listPage(pageParam, paramMap);
        logger.info(pageBean);
        List<TruckLog> truckLogs = pageBean.getRecordList();
        for (int i = 0; i < truckLogs.size(); i++)
            logger.info("Item" + i + ":" + truckLogs.get(i));
    }

//    @Test
//    public void createWithUser() {
//        TruckLog truckLog = new TruckLog();
//        truckLog.setCorporation(1L);
//        truckLog.setDept(TruckLogDeptType.FinancialTruckLog);
//        User user = new User();
//        user.setUserName(StringUtils.getRandomStr(8));
//        user.setPassWord("d123456");
//        user.setPhone("188" + StringUtils.getRandomStr(8, StringUtils.StrType.NUMBER));
//        user.setCorporation(1L);
//        truckLogService.createWithUser(truckLog, user);
//        if(user.getUserType() != UserType.TruckLog || user.getUserInfo().longValue() != truckLog.getId().longValue()){
//            throw new RuntimeException("createWithUser failed");
//        }else {
//            logger.info("createWithUser succeed : TruckLogID:" + truckLog.getId() + ", userID:" + user.getId());
//        }
//    }

    @Test
    public void updateByMap() {
    }

//    @Test
//    public void deleteByUserId() {
//        truckLogService.deleteByUserId(userId);
//        User user_check = userDao.getById(userId);
//        if(user_check == null || !user_check.getIfDeleted() || user_check.getUserType().getValue().intValue() != UserType.TruckLog.getValue().intValue()){
//            throw new RuntimeException("deleteByUserId failed");
//        }
//        TruckLog truckLog_check = truckLogDao.getById(user_check.getUserInfo());
//        if(truckLog_check == null || !truckLog_check.getIfDeleted()){
//            throw new RuntimeException("deleteByUserId failed");
//        }
//        logger.info("deleteByUserId succeed");
//    }
}