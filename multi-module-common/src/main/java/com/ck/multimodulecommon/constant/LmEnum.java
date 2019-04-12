package com.ck.multimodulecommon.constant;

/**
 * @author ck
 * @date 2018/11/5 16:12
 * Description  : 枚举类
 */
public enum LmEnum {
    /**
     * 全局异常的提示
     */
    SYSTEM_EXCEPTION("系统繁忙，请稍后再试"),
    /**
     * 性别男、女
     */
    SEX_MALE("男", "1"),SEX_FEMALE("女", "0"),
    /**
     * 返回json的code
     */
    RETURN_NUM_200(200),RETURN_NUM_201(201),RETURN_NUM_202(202),RETURN_NUM_203(203),RETURN_NUM_401(401),
    /**
     * Redis的key常量
     */
    MODULE_EMPLOYEE("LM:employee"),
    /**
     * Redis的缓存时间
     */
    LOGIN_INFO_EXPIRE(300),
    /**
     * 常量名
     */
    AUTHORIZATION("Authorization"),REFRESH_TOKEN("Refresh_Token"),LOGIN_NAME("loginName"),PASSWORD("password"),USER_INFO("userInfo"),ROLES("roles"),PERMISSIONS("permissions"),
    /**
     * 业务表名
     */
    IMAGE_BLOG("image_blog"),
    /**
     * 阿里OSS访问域名
     */
    ENDPOINT("oss-cn-beijing.aliyuncs.com"),ACCESS_KEY_ID("LTAIebGfCOUJ8IJp"),ACCESS_KEY_SECRET("R9E47aBiFQz98ywLrGG5tFT9o7O2hS"),BACKET_NAME("2019-2-16-ck"),FOLDER("lmsystem/")
    ;
    private Integer num;
    /**
     * name
     */
    private String name;
    /**
     * code
     */
    private String code;
    private LmEnum(Integer num){
        this.num = num;
    }
    private LmEnum(String name){
        this.name = name;
    }
    private LmEnum(String name, String code){
        this.name = name;
        this.code = code;
    }

    /**
     * 通过name值获取code值
     * @param name
     * @return
     */
    public static String getCode(String name){
        for(LmEnum lmEnum : LmEnum.values()){
            if(lmEnum.getName().equals(name)){
                return lmEnum.getCode();
            }
        }
        return null;
    }

    /**
     * 生成redis的key
     * @param args
     * @return
     */
    public String join(Object... args){
        StringBuilder sb = new StringBuilder(name);
        for(Object arg : args){
            sb.append(":").append(arg);
        }
        return sb.toString();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
