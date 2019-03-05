package com.ck.multimodulecommon.util;
/**
 * Title: ReflectBean.java  
 * Description: 实体类映射工具 
 * Copyright: Copyright (c) 2018
 * @author Kang Chen  
 * @date 2018年9月13日 下午5:14:37
 * @version 1.0  
 */
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;
  
/**
 * 用于把表映射为实体类的工具
 * @author Kang Chen
 * @data 2018年9月14日
 * @version 1.0.0
 */
public class ReflectBean {  
    private Connection connection;  
    /*mysql url的连接字符串*/  
    private static String url = "jdbc:mysql://localhost:3306/lm_dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
    //账号  
    private static String user = "root";  
    //密码  
    private static String password = "258963";  
    //mysql jdbc的java包驱动字符串  
    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    //数据库中的表名  
    String table = "m_fnd_user";
    //生成的实体类名类名
    String nameOfClass = "FndUser";
    String entityPrefixPath = "D:/git/multimodule/multi-module-dao/src/main/java/";
    String entityPackagePath = "com/ck/multimoduledao/entity/";
    //数据库的列名称  
    private String[] colnames; // 列名数组  
    //列名类型数组    
    private String[] colTypes;  
    public ReflectBean(){  
        try {//驱动注册  
            Class.forName(driverClassName);  
            if (connection == null || connection.isClosed())  
                //获得链接  
                connection = DriverManager.getConnection(url, user, password);  
        } catch (ClassNotFoundException ex) {  
                ex.printStackTrace();  
                System.out.println("Oh,not");  
            } catch (SQLException e) {  
                e.printStackTrace();  
                System.out.println("Oh,not");  
            }  
    }  
      
    public Connection getConnection() {  
        return connection;  
    }  
    public void setConnection(Connection connection) {  
        this.connection = connection;  
    }  
  
    public void doAction(){  
        String sql = "select * from "+table;  
        try {  
            PreparedStatement statement = connection.prepareStatement(sql);  
            //获取数据库的元数据   
            ResultSetMetaData metadata = statement.getMetaData();
            //数据库的字段个数  
            int len = metadata.getColumnCount();  
            //字段名称  
            colnames = new String[len+1];  
            //字段类型 --->已经转化为java中的类名称了  
            colTypes = new String[len+1];  
            for(int i= 1;i<=len;i++){  
                //System.out.println(metadata.getColumnName(i)+":"+metadata.getColumnTypeName(i)+":"+sqlType2JavaType(metadata.getColumnTypeName(i).toLowerCase())+":"+metadata.getColumnDisplaySize(i));  
                //metadata.getColumnDisplaySize(i);  
                colnames[i] = metadata.getColumnName(i); //获取字段名称  
                System.out.println(colnames[i]);
                colTypes[i] = sqlType2JavaType(metadata.getColumnTypeName(i)); //获取字段类型   
                System.out.println(metadata.getColumnTypeName(i));
                System.out.println(colTypes[i]);
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
    /* 
     * mysql的字段类型转化为java的类型*/  
    private String sqlType2JavaType(String sqlType) {    
          
        if(sqlType.equalsIgnoreCase("bit")){    
            return "boolean";    
        }else if(sqlType.equalsIgnoreCase("tinyint")){    
            return "byte";    
        }else if(sqlType.equalsIgnoreCase("smallint")){    
            return "short";    
        }else if(sqlType.equalsIgnoreCase("int")){    
            return "int";    
        }else if(sqlType.equalsIgnoreCase("bigint")){    
            return "Long";    
        }else if(sqlType.equalsIgnoreCase("float")){    
            return "float";    
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")     
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")     
                || sqlType.equalsIgnoreCase("smallmoney")){    
            return "double";    
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")     
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")     
                || sqlType.equalsIgnoreCase("text")){    
            return "String";    
        }else if(sqlType.equalsIgnoreCase("datetime") ||sqlType.equalsIgnoreCase("date")){    
            return "Date";    
        }else if(sqlType.equalsIgnoreCase("image")){    
            return "Blod";    
        }else if(sqlType.equalsIgnoreCase("timestamp")){    
            return "Timestamp";    
        }    
            
        return null;    
    }  
    /*获取整个类的字符串并且输出为java文件 
     * */  
    public  StringBuffer getClassStr(){  
        //输出的类字符串  
        StringBuffer str = new StringBuffer("");  
        //获取表类型和表名的字段名  
        this.doAction();  
        //校验  
        if(null == colnames && null == colTypes) return null;  
        //拼接  
        str.append("package " + entityPackagePath.substring(0, entityPackagePath.length()-1).replace("/", ".") + ";\n\r");
        str.append("import com.sf.channelexpand.query.BaseQuery;\n\r");
        str.append("public class "+ nameOfClass +" extends BaseQuery{\r\n");  
        //拼接属性  
        for(int index=1; index < colnames.length; index++){
        	str.append("\t/*  */\r\n");
            str.append(getAttrbuteString(colnames[index],colTypes[index]));  
        }  
        //拼接get，Set方法         
        for(int index=1; index < colnames.length; index++){
            str.append(getGetMethodString(colnames[index],colTypes[index]));  
            str.append(getSetMethodString(colnames[index],colTypes[index]));  
        }  
        str.append("}\r\n");  
        //输出到文件中  
        File file = new File(entityPrefixPath + entityPackagePath + nameOfClass +".java"); 
        BufferedWriter write = null;  
  
        try {  
            write = new BufferedWriter(new FileWriter(file));  
            write.write(str.toString());  
            write.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            if (write != null)  
                try {  
                    write.close();  
                } catch (IOException e1) {            
                    e1.printStackTrace();  
                }  
        }  
        return str;  
    }  
    /* 
     * 获取字段字符串*/  
    public StringBuffer getAttrbuteString(String name, String type) {  
        if(!check(name,type)) {  
            System.out.println("类中有属性或者类型为空");  
            return null;  
        };  
        name = GetTuoFeng(name);
        String format = String.format("    private %s %s;\n\r", new String[]{type,name});  
        return new StringBuffer(format);  
    }  
    /* 
     * 校验name和type是否合法*/  
    public boolean check(String name, String type) {  
        if("".equals(name) || name == null || name.trim().length() ==0){  
            return false;  
        }  
        if("".equals(type) || type == null || type.trim().length() ==0){  
            return false;  
        }  
        return true;  
          
    }  
    /* 
     * 获取get方法字符串*/  
    private StringBuffer getGetMethodString(String name, String type) {  
        if(!check(name,type)) {  
            System.out.println("类中有属性或者类型为空");  
            return null;  
        };  
        name = GetTuoFeng(name);
        String Methodname = "get"+ name.substring(0, 1).toUpperCase() + name.substring(1);  
        String format = String.format("    public %s %s(){\n\r", new Object[]{type,Methodname});  
        format += String.format("        return this.%s;\r\n", new Object[]{name});  
        format += "    }\r\n";  
        return new StringBuffer(format);  
    }  
    //将名称首字符大写  
    private String GetTuoFeng(String name) {  
    	if (name == null) return name;
	    if (name.contains("_"))
	    {
	    char[] ch = name.toCharArray();
	    for (int index = 0 ;index < ch.length ; index++)
	    {
	    if (ch[index] == '_' && index > 0 && index < ch.length - 1)
	    {
	    if(ch[index + 1] >= 'a' && ch[index + 1] <= 'z'){
	    ch[index + 1] = (char)(ch[index + 1] - 32);
	    }
	    }
	    }
	     
	    return new String(ch).replace("_", "");
	    }
	    return name;
    }     
    /* 
     * 获取字段的get方法字符串*/  
    private Object getSetMethodString(String name, String type) {  
        if(!check(name,type)) {  
            System.out.println("类中有属性或者类型为空");  
            return null;  
        };  
        name = GetTuoFeng(name);
        String Methodname = "set"+name.substring(0, 1).toUpperCase() + name.substring(1);  
        String format = String.format("    public void %s(%s %s){\n\r", new Object[]{Methodname,type,name});  
        format += String.format("        this.%s = %s;\r\n", new Object[]{name,name});  
        format += "    }\r\n";  
        return new StringBuffer(format);  
    }  
  
    public static void main(String[] args) {  
        ReflectBean bean = new ReflectBean();  
        System.err.println(bean.getClassStr());  
    }  
      
}  

