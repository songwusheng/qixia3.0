package com.heziz.qixia3.network;

/**
 * Created by Administrator on 2016/9/21.
 *
 */

public class API {



 public static String base_url = "https://apis.heziz.com/";
 public static String base_url1 = "https://apis.heziz.com/hzxm/web/";

 public static String base_url2 = "http://120.27.0.207:31007/";

 //登录
 public static String LOGIN=base_url+"urmp/getToken/getTokenByUser1";
 //修改密码
 public static String PASSWORD_XG=base_url+"/urmp/user/alterPassword";
 //查询用户名是否存在
 public static String USERNAME_CZ=base_url+"/urmp/user/accountExists";
 //找回密码
 public static String PASSWORD_ZH=base_url+"/urmp/user/updatePasswardByAccount";

 //栖霞站点id
 public static String STATION="1551258680345413";

 //首页
 public static String HOME_DATA1=base_url1+"getQxAppProjectVo";

 //首页天气预报（新）
 public static String HOME_WHEATHER=base_url+"hzjg/weatherForecast/getRequestWeatherForecastDataTask";
 //首页空气质量（新）
 public static String HOME_WHEATHER1=base_url+"hzjg/airQuality/getRequestKqzlCityListDataTask";
 //首页列表
 public static String HOME_DATA2=base_url1+"queryEveryPopedom";
 //首页--二级页面--全部项目列表
 public static String HOME_TOTAL_PROJECT_LIST=base_url1+"queryForPageAndStreet";
 //工程列表 home_map
 public static String PROJECT_LIST=base_url1+"queryByCondition";
 public static String PROJECT_LIST1=base_url1+"queryForsubList";
// public static String PROJECT_LIST1=base_url1+"queryForsubList";
 //街道列表
 public static String STREET_LIST=base_url+"urmp/pRole/queryAreaIdList?siteId="+STATION;
 //项目详情
 public static String PROJECT_DETAILS=base_url1+"getProjectUnits";
 //通过项目id获取车辆未冲洗name
 public static String CAR_COUNT=base_url+"hzjg/qxNewDemand/getAllInfoByProject";
 //通过项目id获取扬尘设备列表
 public static String YC_LIST=base_url+"hzjg/weather/getWeatherDeviceForPage";
 //通过项目id车辆未冲洗列表
 public static String CAR_PROJECT_LIST=base_url+"hzjg/ftpCarsInfoManager/getFtpEsInfoByProject";
 //通过项目id车辆未冲洗本日本周本月违章数量
 public static String CAR_WZ_NUM=base_url+"hzjg/ftpCarsInfoManager/getFtpEsInfoByProjectByDate";
 //通过设备id车辆未冲洗列表
 public static String CAR_DEVICE_LIST=base_url+"hzjg/qxNewDemand/getFtpAlarmByDivId";
 //websocket地址
public static String WEBSOCKET_URL="ws://skt.heziz.com:28020/websocket";
 //根据设备id查看扬尘折线图
 public static String YC_HISTORY_URL=base_url+"hzjg/newDemandForDust/getAWeekOrOnDayAvgOfFugitiveDustByDeviceId/1"; //根据项目id获取扬尘报警数据
 public static String YC_HISTORY_URL1=base_url+"hzjg/newDemandForDust/getAWeekOrOnDayAvgOfFugitiveDustByDeviceId1/"; //根据项目id获取扬尘报警数据
 public static String YC_BJ_URL=base_url+"hzjg/elastic/getyangchencountByDate";
 //根据项目id获取扬尘报警数据列表
 public static String YC_BJ_LIST=base_url+"hzjg/elastic/getWeatherEsInfoByProject";

 //智慧工地扬尘、视频、车辆数据
 public static String ZH_DATA1=base_url+"hzjg/qxNewDemand/getAllDeviceInfoByProject";
 //智慧工地非道路机械数据
 public static String ZH_DATA2=base_url+"hzjg/projectDieselOilOpt/statisticsNowDayRecord";

 //根据项目id查看视频设备数
 public static String SP_LIST=base_url+"hzjg/video/getVideoDeviceForPage";

 //扬尘界面获取各设备总数，在离线数（统计信息）
 public static String YC_STREET_NUM=base_url+"hzjg/elastic/getAllYangchenDeviceIsOnlineByStation";
 //扬尘界面获取各街道在离线数列表
 public static String YC_STREET_LIST=base_url+"hzjg/qxNewDemand/getYangchenIsOnlineByManagerRole";
 //扬尘界面获取各街道项目在离线列表
 public static String YC_STREET_PROJECT_LIST=base_url+"hzjg/qxNewDemand/getYangchenIsOnlineavgByRole";
 //通过项目id获取扬尘设备列表(在离线情况)
 public static String YC_LIST_DEVICE=base_url+"hzjg/qxNewDemand/getDavidIDByProject";

 //视频界面获取各设备总数，在离线数（统计信息）
 public static String SP_STREET_NUM=base_url+"hzjg/qxNewDemand/getvideoonline";
 //视频界面获取各街道在离线数列表
 public static String SP_STREET_LIST=base_url+"hzjg/qxNewDemand/viedoOnlineByManagerRole/1/10";
 //根据街道id或者项目id获取视频工地列表
 public static String SP_PROJECT_LIST=base_url+"hzjg/qxNewDemand/getviedoIsOnlineByRole";

 //车辆冲洗界面获取各设备总数，在离线数（统计信息）
 public static String CL_STREET_NUM=base_url+"hzjg/qxNewDemand/geturmpAllFTPdeviceisonline";
 //车辆冲洗界面获取各街道在离线数列表
 public static String CL_STREET_LIST=base_url+"hzjg/qxNewDemand/getFtpIsOnlineByManagerRole";
 //根据街道id或者项目id获取车辆冲洗工地列表
 public static String CL_PROJECT_LIST=base_url+"hzjg/qxNewDemand/getFtpIsOnlineavgByRole";
 //根据项目id查看车辆设备数
 public static String CL_LIST=base_url+"hzjg/qxNewDemand/getFTPbyproject";

 //根据设备id查看车辆未冲洗列表
 public static String CL_NEW_LIST=base_url+"hzjg/ftpCarsInfoManager/getRequesEsCarsInfo";

 //获取非道路机械的各街道的数据list
 public static String FDL_STREET_LIST=base_url+"hzjg/projectDieselOilOpt/statisticsNowDayRecordBy";
 //获取非道路机械的项目数据list
 public static String FDL_PROJECT_LIST=base_url+"hzjg/projectDieselOilOpt/statisticsNowDayRecordByCondition";
 //获取非道路机械的项目下具体购油数据list
 public static String FDL_PROJECT_DETAILS_LIST=base_url+"hzjg/projectDieselOilOpt/queryTDieselOilList";

 //日常任务--八达标一公示
 public static String RCRW_WDBYGS=base_url+"hzxm/daliy/queryZCCountVo";
 //八达标一公示--已查项目列表
 public static String WDBYGS_YC_LIST=base_url+"hzxm/daliy/searchPage";
 //八达标一公示--未查项目列表
 public static String WDBYGS_WC_LIST=base_url+"hzxm/daliy/queryNotCheck";
 //八达标一公示--合格列表
 public static String HG_LIST=base_url+"hzxm/daliy/searchList";
 //八达标一公示--不合格列表
 public static String BHG_LIST=base_url+"hzxm/daliy/searchList";

 //日常任务--网络人员
 public static String RCRW_WLRY=base_url+"hzxm/daliy/queryWCCountVo";
 public static String WEB_URL1="https://oa.heziz.com/loginApp";

 //我的界面
 public static String YC_CL_NUM=base_url+"hzjg/qxNewDemand/getProjectAlarmByprojectId";
 //我的界面--工作状态
 public static String WORKSTATUS=base_url+"urmp/user/setWorkStatus";
 //我的--扬尘报警列表
 public static String MINE_YC_LIST=base_url+"hzjg/qxNewDemand/getWeatherAlarmByProject";
 //我的--扬尘报警列表--工地详情列表
 public static String MINE_YC_DETAILS_LIST=base_url+"hzjg/qxNewDemand/getWeatherAlarmByprojectId";
 //我的--扬尘报警列表--工地详情列表
 public static String MINE_YC_DETAILS_LIST1=base_url+"hzjg/qxNewDemand/getWeatherAlarmByprojectId1";
 //我的--车辆报警列表
 public static String MINE_CLCX_LIST=base_url+"hzjg/qxNewDemand/getFtpAlarmByProject";
 //我的--车辆报警列表--工地详情列表
 public static String MINE_CLCX_DETAILS_LIST=base_url+"hzjg/qxNewDemand/getFtpAlarmByprojectId";
 //我的--车辆报警列表--工地详情列表
 public static String MINE_CLCX_DETAILS_LIST1=base_url+"hzjg/qxNewDemand/getFtpAlarmByprojectId1";
 //我的--八达标一公示list
 public static String MINE_WDBYGS_LIST=base_url+"hzxm/daliy/searchAPPPage";
 //我的--专项检查list
 public static String MINE_ZXJC_LIST=base_url+"hzxm/customDaliy/searchPage";

 //我的--非道路机械管理list
 public static String MINE_FDLJX_LIST=base_url+"hzjg/projectDieselOilOpt/queryForPage";
 //我的--非道路机械管理改变状态
 public static String MINE_FDLJX_ZT_LIST=base_url+"hzjg/projectDieselOilOpt/updateTDieselOil";
//我的--新闻资讯列表
 public static String MINE_XWZX_LIST=base_url2+"policyDoc/queryList";

 //我的--新闻资讯列表--删除
 public static String MINE_XWZX_LIST_DELETE=base_url2+"policyDoc/delete";

 //我的--新闻资讯列表--编辑
 public static String MINE_XWZX_LIST_EDITE=base_url2+"policyDoc/modify";

 //视频项目列表
 public static String VIDEO_LIST=base_url+"app/clock/getVedioList";

 public static String FILE=base_url2+"policyDoc/add?fileName=";

}
