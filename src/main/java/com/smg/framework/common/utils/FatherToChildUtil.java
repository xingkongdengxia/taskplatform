package com.smg.framework.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 父类所有的属性COPY到子类 适应场景：model代码根据数据库表已经自动生成，但在使用过程中需要增加属性便于操作（比如列表显示其他对象属性），
 * 又不想修改model代码（如果修改了，后续增加数据库表字段重新生成代码会覆盖原代码）
 *
 * @author justincai
 */
public class FatherToChildUtil {

    private static final Log log = LogFactory.getLog(FatherToChildUtil.class);

    /*
    * 将父类所有的属性COPY到子类中。
    * 类定义中child一定要extends father；
    * 而且child和father一定为严格javabean写法，属性为deleteDate，方法为getDeleteDate
     */
    public static void fatherToChild(Object father, Object child) throws InvocationTargetException {

        if (!(child.getClass().getSuperclass() == father.getClass())) {
            log.error("child不是father的子类");
        }
        Class fatherClass = father.getClass();
        Field ff[] = fatherClass.getDeclaredFields();
        for (Field f : ff) {
            //过滤掉字段serialVersionUID
            if (!StringUtils.equals("serialVersionUID", f.getName())) {
                log.debug("Field f:" + f.getName());
                //取出每一个属性，如deleteDate 
                Class type = f.getType();
                try {
                    Method fm = fatherClass.getMethod("get" + upperHeadChar(f.getName()));//方法getDeleteDate
                    Object obj = fm.invoke(father);//取出属性值  
                    Method cm = child.getClass().getDeclaredMethod("set" + upperHeadChar(f.getName()), type);
                    Object result = cm.invoke(child, obj);
                } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {

                    log.debug(e);
                }
            }

        }
    }

    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     */
    private static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }

}
