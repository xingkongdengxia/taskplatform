package com.smg.framework.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smg.framework.common.annotation.BaseService;
import com.smg.framework.common.base.BaseServiceImpl;
import com.smg.framework.upms.dao.mapper.UpmsPermissionMapper;
import com.smg.framework.upms.dao.mapper.UpmsSystemMapper;
import com.smg.framework.upms.dao.mapper.UpmsUserPermissionMapper;
import com.smg.framework.upms.dao.model.UpmsPermission;
import com.smg.framework.upms.dao.model.UpmsPermissionExample;
import com.smg.framework.upms.dao.model.UpmsRolePermission;
import com.smg.framework.upms.dao.model.UpmsSystem;
import com.smg.framework.upms.dao.model.UpmsSystemExample;
import com.smg.framework.upms.dao.model.UpmsUserPermission;
import com.smg.framework.upms.dao.model.UpmsUserPermissionExample;
import com.smg.framework.upms.rpc.api.UpmsApiService;
import com.smg.framework.upms.rpc.api.UpmsPermissionService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsPermissionService实现
 *
 * @author justincai
 */
@Service
@Transactional
@BaseService
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {

    private static final Log log = LogFactory.getLog(UpmsPermissionServiceImpl.class);

    private UpmsSystemMapper upmsSystemMapper;

    private UpmsPermissionMapper upmsPermissionMapper;

    private UpmsApiService upmsApiService;

    private UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        // 角色已有权限
        List<UpmsRolePermission> rolePermissions = upmsApiService.selectUpmsRolePermisstionByUpmsRoleId(roleId);

        JSONArray systems = new JSONArray();
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system : systems) {
                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);
                if (upmsPermissions.isEmpty()) {
                    continue;
                }
                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermission : upmsPermissions) {
                    if (upmsPermission.getPid() != 0 || upmsPermission.getType() != 1) {
                        continue;
                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getPermissionId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpmsRolePermission rolePermission : rolePermissions) {
                        if (rolePermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpmsPermission upmsPermission2 : upmsPermissions) {
                            if (upmsPermission2.getPid() != ((JSONObject) folder).getIntValue("id") || upmsPermission2.getType() != 2) {
                                continue;
                            }
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getPermissionId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpmsRolePermission rolePermission : rolePermissions) {
                                if (rolePermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpmsPermission upmsPermission3 : upmsPermissions) {
                                    if (upmsPermission3.getPid() != ((JSONObject) menu).getIntValue("id") || upmsPermission3.getType() != 3) {
                                        continue;
                                    }
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getPermissionId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpmsRolePermission rolePermission : rolePermissions) {
                                        if (rolePermission.getPermissionId().intValue() == upmsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    @Override
    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
        // 角色权限
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(usereId)
                .andTypeEqualTo(type);
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);

        JSONArray systems = new JSONArray();
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system : systems) {
                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);
                if (upmsPermissions.isEmpty()) {
                    continue;
                }
                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermission : upmsPermissions) {
                    if (upmsPermission.getPid() != 0 || upmsPermission.getType() != 1) {
                        continue;
                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getPermissionId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                        if (upmsUserPermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpmsPermission upmsPermission2 : upmsPermissions) {
                            if (upmsPermission2.getPid() != ((JSONObject) folder).getIntValue("id") || upmsPermission2.getType() != 2) {
                                continue;
                            }
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getPermissionId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                                if (upmsUserPermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpmsPermission upmsPermission3 : upmsPermissions) {
                                    if (upmsPermission3.getPid() != ((JSONObject) menu).getIntValue("id") || upmsPermission3.getType() != 3) {
                                        continue;
                                    }
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getPermissionId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                                        if (upmsUserPermission.getPermissionId().intValue() == upmsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    public UpmsSystemMapper getUpmsSystemMapper() {
        return upmsSystemMapper;
    }

    public void setUpmsSystemMapper(UpmsSystemMapper upmsSystemMapper) {
        this.upmsSystemMapper = upmsSystemMapper;
    }

    public UpmsPermissionMapper getUpmsPermissionMapper() {
        return upmsPermissionMapper;
    }

    public void setUpmsPermissionMapper(UpmsPermissionMapper upmsPermissionMapper) {
        this.upmsPermissionMapper = upmsPermissionMapper;
    }

    public UpmsApiService getUpmsApiService() {
        return upmsApiService;
    }

    public void setUpmsApiService(UpmsApiService upmsApiService) {
        this.upmsApiService = upmsApiService;
    }

    public UpmsUserPermissionMapper getUpmsUserPermissionMapper() {
        return upmsUserPermissionMapper;
    }

    public void setUpmsUserPermissionMapper(UpmsUserPermissionMapper upmsUserPermissionMapper) {
        this.upmsUserPermissionMapper = upmsUserPermissionMapper;
    }

}
