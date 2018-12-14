package TestService;

import cn.AssassinG.ScsyERP.OnWayWatch.core.biz.WarnBiz;
import cn.AssassinG.ScsyERP.OnWayWatch.core.dao.WarnDao;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.entity.Warn;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.enums.WarnStatus;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.enums.WarnType;
import cn.AssassinG.ScsyERP.OnWayWatch.facade.service.WarnServiceFacade;
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
public class WarnServiceFacadeImplTest {
    private static Logger logger = Logger.getLogger(WarnServiceFacadeImplTest.class);
    @Autowired
    private WarnServiceFacade warnService;
    @Autowired
    private WarnBiz warnBiz;
    @Autowired
    private WarnDao warnDao;

    private Long warnId;

    @Before
    public void setUp() throws Exception {
        Warn warn = new Warn();
        warn.setCorporation(1L);
        warn.setOutStorageForm(1L);
        warn.setTruck(1L);
        warn.setTruckLog(1L);
        warn.setTime(new Date());
        warn.setGPSX(1.1);
        warn.setGPSY(2.2);
        warn.setWarnType(WarnType.Fuel);
        warn.setStatus(WarnStatus.Handled);
        warn.setWarnValue(StringUtils.getRandomStr(6));
        warnId = warnBiz.create(warn);
    }

    @After
    public void tearDown() throws Exception {
        warnDao.delete(warnId);
    }

    @Test
    public void create() {
        Warn warn = new Warn();
        warn.setCorporation(1L);
        warn.setOutStorageForm(1L);
        warn.setTruck(1L);
        warn.setTruckLog(1L);
        warn.setTime(new Date());
        warn.setGPSX(1.1);
        warn.setGPSY(2.2);
        warn.setWarnType(WarnType.Fuel);
        warn.setStatus(WarnStatus.Handled);
        warn.setWarnValue(StringUtils.getRandomStr(8));
        Long id = warnService.create(warn);
        if(id == null){
            throw new RuntimeException("Create nothing");
        }else {
            logger.info("Created : " + warnDao.getById(id));
        }
    }

    @Test
    public void update() {
        Warn warn = warnDao.getById(warnId);
        String new_name = StringUtils.getRandomStr(6);
        logger.info("Before Update: "+warn);
        warn.setWarnValue(new_name);
        warnService.update(warn);
        Warn warn_check = warnDao.getById(warnId);
        if(!warn_check.getWarnValue().equals(new_name)){
            throw new RuntimeException("Update failed");
        }else{
            logger.info("Updated succeed");
        }
    }

    @Test
    public void deleteById() {
        warnService.deleteById(warnId);
        Warn warn_check = warnDao.getById(warnId);
        if(!warn_check.getIfDeleted()){
            throw new RuntimeException("Delete failed");
        }else {
            logger.info("Delete succeed");
        }
    }

    @Test
    public void delete() {
        Warn warn_test = warnDao.getById(warnId);
        warnService.delete(warn_test);
        Warn warn_check = warnDao.getById(warnId);
        if(!warn_check.getIfDeleted()){
            throw new RuntimeException("Delete failed");
        }else {
            logger.info("Delete succeed");
        }
    }

    @Test
    public void getById() {
        Warn warn = warnService.getById(warnId);
        if(warn == null || warn.getIfDeleted()){
            throw new RuntimeException("getById null");
        }else{
            if(warn.getId().longValue() != warnId.longValue()){
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
        paramMap.put("Id", warnId);
        Warn warn = warnService.getBy(paramMap);
        if(warn.getId() != warnId){
            throw new RuntimeException("getBy failed");
        }else{
            logger.info("getBy succeed");
        }
    }

    @Test
    public void listBy() {
        Warn warn = warnDao.getById(warnId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("IfDeleted", false);
        paramMap.put("Name", warn.getWarnValue());
        List<Warn> warns = warnService.listBy(paramMap);
        for (int i = 0; i < warns.size(); i++)
            logger.info("Item" + i + ":" + warns.get(i));
    }

    @Test
    public void listPage() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("IfDeleted", false);
        PageParam pageParam = new PageParam(2, 2);
        PageBean<Warn> pageBean = warnService.listPage(pageParam, paramMap);
        logger.info(pageBean);
        List<Warn> warns = pageBean.getRecordList();
        for (int i = 0; i < warns.size(); i++)
            logger.info("Item" + i + ":" + warns.get(i));
    }

//    @Test
//    public void createWithUser() {
//        Warn warn = new Warn();
//        warn.setCorporation(1L);
//        warn.setDept(WarnDeptType.FinancialWarn);
//        User user = new User();
//        user.setUserName(StringUtils.getRandomStr(8));
//        user.setPassWord("d123456");
//        user.setPhone("188" + StringUtils.getRandomStr(8, StringUtils.StrType.NUMBER));
//        user.setCorporation(1L);
//        warnService.createWithUser(warn, user);
//        if(user.getUserType() != UserType.Warn || user.getUserInfo().longValue() != warn.getId().longValue()){
//            throw new RuntimeException("createWithUser failed");
//        }else {
//            logger.info("createWithUser succeed : WarnID:" + warn.getId() + ", userID:" + user.getId());
//        }
//    }

    @Test
    public void updateByMap() {
        String new_name = StringUtils.getRandomStr(6);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("status", WarnStatus.Handled.getName());
        warnService.updateByMap(warnId, paramMap);
        Warn warn_check = warnDao.getById(warnId);
        if(warn_check.getStatus().getValue().intValue() != WarnStatus.Handled.getValue().intValue()){
            throw new RuntimeException("updateByMap failed");
        }else{
            logger.info("updateByMap succeed");
        }
    }

//    @Test
//    public void deleteByUserId() {
//        warnService.deleteByUserId(userId);
//        User user_check = userDao.getById(userId);
//        if(user_check == null || !user_check.getIfDeleted() || user_check.getUserType().getValue().intValue() != UserType.Warn.getValue().intValue()){
//            throw new RuntimeException("deleteByUserId failed");
//        }
//        Warn warn_check = warnDao.getById(user_check.getUserInfo());
//        if(warn_check == null || !warn_check.getIfDeleted()){
//            throw new RuntimeException("deleteByUserId failed");
//        }
//        logger.info("deleteByUserId succeed");
//    }
}