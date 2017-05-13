package com.ezworking.wechatunlock.api;


/**
 * Created by Wan.N on 2016/8/31.
 * 存放，管理与后台交互的地址url
 */
public class ConstantNetUrl {

   //2017.05.10  public static final String SZBMMeetingBaseUrl = Constant.URL;
    public static final String SZBMMeetingBaseUrl = "";
    public static boolean isMock = false;
    /**
     * 登录
     */
    public static final String LOGIN = SZBMMeetingBaseUrl + "user/rest/login";
    /**
     * 查看评价
     */
    public static final String GET_MY_COURSE_RATE = SZBMMeetingBaseUrl + "stu/getMyCourseRateApi";
    /**
     * 提交评价
     */
    public static final String RATE_FOR_COURSE = SZBMMeetingBaseUrl + "stu/rateForCourseApi";
    /**
     * 我的课程
     */
    public static final String GET_STU_MY_COURSES = SZBMMeetingBaseUrl + "course/getStuMyCourses";
    /**
     * 搜索API
     */
    public static final String SEARCH = SZBMMeetingBaseUrl + "index/searchApi";
    /**
     * 获取课件详情
     */
    public static final String GET_COURSE_WARE_BY_ID = SZBMMeetingBaseUrl + "courseware/getCoursewareById";
    /**
     * 获取课程详情
     */
    public static final String GET_COURSE_INFO_BY_ID = SZBMMeetingBaseUrl + "course/getCourseInfoById";
    /**
     * 获得课件数据(更多)
     */
    public static final String GET_COURSE_WARElIST_BY_CA_ID = SZBMMeetingBaseUrl + "courseware/getCoursewarListByCaID";
    /**
     * 查看课程评价
     */
    public static final String GET_RATE_BY_COURSE_ID = SZBMMeetingBaseUrl + "course/getRateByCourseID";

    /**
     * 版本检查
     */
    public static String getCheckVersonUrl() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/31/getVersionInfo";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/common/getVersionInfo";
        }
    }

    /**
     * 意见反馈
     */
    public static String submitSuggestApi() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/submitSuggestApi";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/suggest/submitSuggestApi";
        }
    }

    /**
     * 管理人员邮箱
     */
    public static String getAministratorApi() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/31/getAministratorApi";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/common/getAministratorApi";
        }
    }

    /**
     * 获得课程或者课件的社交统计(播放，点赞，评论次数)
     */
    public static String getSocialCounting() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/getSocialCounting";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/comment/getSocialCounting";
        }
    }

    /**
     * 提交点赞接口
     */
    public static String submitLikeApi() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/submitLikeApi";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/suggest/submitLikeApi";
        }
    }

    /**
     * 获取评论列表数据
     */
    public static String getCommentList() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/getCommentList";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/comment/getCommentList";
        }
    }

    /**
     * 提交评论接口
     */
    public static String submitCommentApi() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/submitCommentApi";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/comment/submitCommentApi";
        }
    }

    /**
     * 提交点播纪录接口
     */
    public static String submitLearningRecord() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/v110/courseware/submitLearningRecord";
        } else {
            return SZBMMeetingBaseUrl + "service/v110/courseware/submitLearningRecord";
        }
    }

    /**
     * 获取首页数据
     */
    public static String getFrontPageInfo() {
//        if (isMock) {
//            return SZBMMeetingBaseUrl + "index/getFrontPageInfo";
//        } else {
//            return SZBMMeetingBaseUrl + "service/v110/index/getFrontPageInfo";
//        }
        return SZBMMeetingBaseUrl + "service/v110/index/getFrontPageInfo";
    }

    /**
     * @return
     */
    public static String examine() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/examine";
        } else {
            return SZBMMeetingBaseUrl + "service/v111/examine";
        }
    }

    /**
     * 签到
     */
    public static String getSignIn() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/signIn";
        } else {
            return SZBMMeetingBaseUrl + "service/v111/signin";
        }
    }

    /**
     * 是否签到
     */
    public static String getIsSigned() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/isSigned";
        } else {
            return SZBMMeetingBaseUrl + "service/v111/signin/isSigned";
        }
    }

    /**
     *  获取试题
     */

    public static String getExamQuestions() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/app/service/v120/examCourse/getExamQuestions";
        } else {
            return SZBMMeetingBaseUrl + "service/v120/examCourse/getExamQuestions";

        }
    }

    /**
     *  查看试题
     */

    public static String showExamedQuestions() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/app/service/v120/examCourse/showExamedQuestions";
        } else {
            return SZBMMeetingBaseUrl + "service/v120/examCourse/showExamedQuestions";
        }
    }

    /**
     * 提交考卷
     */
    public static String submitExams() {
        if (isMock) {
            return "http://192.168.236.1:9002/mockjsdata/24/app/service/v120/examCourse/submitExams";
        } else {
            return SZBMMeetingBaseUrl + "service/v120/examCourse/submitExams";
        }
    }

}