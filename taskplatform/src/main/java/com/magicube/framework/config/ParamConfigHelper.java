package com.magicube.framework.config;

import com.magicube.framework.common.SpringUtil;
import com.magicube.framework.config.dao.model.ParamConfig;
import com.magicube.framework.config.dao.model.ParamConfigExample;
import com.magicube.framework.config.rpc.api.ParamConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ObjectUtils;

/**
 * 参数配置表辅助类
 *
 * @author justincai
 */
public class ParamConfigHelper {

    private static final Log log = LogFactory.getLog(ParamConfigHelper.class);

    private static final ParamConfigService paramConfigService = (ParamConfigService) SpringUtil.getBean("paramConfigService");

    /**
     * 增加或更新参数
     *
     * @param paramConfig
     */
    public static void doParam(ParamConfig paramConfig) {

        //如果参数名不为空，则继续
        String paramName = paramConfig.getParamName();
        if (!StringUtils.isBlank(paramName)) {
            log.debug("参数名：" + paramName);
            //根据参数名检查是否有数据
            ParamConfigExample pce = new ParamConfigExample();
            pce.createCriteria()
                    .andParamNameEqualTo(paramName);
            ParamConfig pc = paramConfigService.selectFirstByExample(pce);

            //如果参数非空，则修改参数值
            if (!ObjectUtils.isEmpty(pc) && !StringUtils.isBlank(pc.getParamName())) {
                log.debug("参数名" + pc.getParamName() + " 已经存在，开始更新...");
                pc.setParamValue(paramConfig.getParamValue());
                pc.setRemark(paramConfig.getRemark());
                paramConfigService.updateByPrimaryKeySelective(pc);
            } else {    //如果参数为空，则增加
                log.debug("参数名" + paramName + " 不存在，开始增加...");
                paramConfigService.insertSelective(paramConfig);
            }

        }

    }

    /**
     * 根据参数名得到参数值
     *
     * @param paramName 参数名
     * @return 参数值
     */
    public static String getParamValue(String paramName) {

        if (!StringUtils.isBlank(paramName)) {
            ParamConfigExample pce = new ParamConfigExample();
            pce.createCriteria()
                    .andParamNameEqualTo(paramName);
            ParamConfig pc = paramConfigService.selectFirstByExample(pce);

            //如果参数非空
            if (!ObjectUtils.isEmpty(pc) && !StringUtils.isBlank(pc.getParamName())) {
                log.debug("参数名：" + pc.getParamName() + " 参数值：" + pc.getParamValue());
                return pc.getParamValue();
            } else {    //如果参数为空
                log.debug("参数配置表中没有此参数名：" + paramName);
                return null;
            }

        } else {
            log.debug("参数名为空，返回null");
            return null;
        }

    }

}
