CREATE TABLE `system_users` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                                `username` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
                                `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
                                `nickname` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
                                `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
                                `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
                                `post_ids` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位编号数组',
                                `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户邮箱',
                                `mobile` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号码',
                                `sex` tinyint(4) DEFAULT '0' COMMENT '用户性别',
                                `avatar` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像地址',
                                `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                                `login_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '最后登录IP',
                                `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
                                `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                                `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE KEY `idx_username` (`username`,`update_time`,`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';

CREATE TABLE `system_dept` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
                               `name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
                               `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父部门id',
                               `sort` int(11) NOT NULL DEFAULT '0' COMMENT '显示顺序',
                               `leader_user_id` bigint(20) DEFAULT NULL COMMENT '负责人',
                               `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
                               `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
                               `status` tinyint(4) NOT NULL COMMENT '部门状态（0正常 1停用）',
                               `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                               `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';

CREATE TABLE `system_menu` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                               `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
                               `permission` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
                               `type` tinyint(4) NOT NULL COMMENT '菜单类型',
                               `sort` int(11) NOT NULL DEFAULT '0' COMMENT '显示顺序',
                               `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
                               `path` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '路由地址',
                               `icon` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '菜单图标',
                               `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件路径',
                               `component_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件名',
                               `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '菜单状态',
                               `visible` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可见',
                               `keep_alive` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否缓存',
                               `always_show` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否总是显示',
                               `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单权限表';

CREATE TABLE `system_oauth2_access_token` (
                                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                              `user_id` bigint(20) NOT NULL COMMENT '用户编号',
                                              `user_type` tinyint(4) NOT NULL COMMENT '用户类型',
                                              `access_token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问令牌',
                                              `refresh_token` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
                                              `client_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
                                              `scopes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权范围',
                                              `expires_time` datetime NOT NULL COMMENT '过期时间',
                                              `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                              `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                                              `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                              `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                              `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                                              PRIMARY KEY (`id`) USING BTREE,
                                              KEY `idx_access_token` (`access_token`) USING BTREE,
                                              KEY `idx_refresh_token` (`refresh_token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OAuth2 访问令牌';

CREATE TABLE `system_oauth2_client` (
                                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                        `client_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
                                        `secret` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端密钥',
                                        `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用名',
                                        `logo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用图标',
                                        `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用描述',
                                        `status` tinyint(4) NOT NULL COMMENT '状态',
                                        `access_token_validity_seconds` int(11) NOT NULL COMMENT '访问令牌的有效期',
                                        `refresh_token_validity_seconds` int(11) NOT NULL COMMENT '刷新令牌的有效期',
                                        `redirect_uris` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '可重定向的 URI 地址',
                                        `authorized_grant_types` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权类型',
                                        `scopes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权范围',
                                        `auto_approve_scopes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自动通过的授权范围',
                                        `authorities` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限',
                                        `resource_ids` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源',
                                        `additional_information` varchar(4096) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '附加信息',
                                        `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OAuth2 客户端表';

CREATE TABLE `system_oauth2_refresh_token` (
                                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                               `user_id` bigint(20) NOT NULL COMMENT '用户编号',
                                               `refresh_token` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
                                               `user_type` tinyint(4) NOT NULL COMMENT '用户类型',
                                               `client_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
                                               `scopes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权范围',
                                               `expires_time` datetime NOT NULL COMMENT '过期时间',
                                               `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                               `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                               `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                               `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OAuth2 刷新令牌';


CREATE TABLE `system_post` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
                               `code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
                               `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
                               `sort` int(11) NOT NULL COMMENT '显示顺序',
                               `status` tinyint(4) NOT NULL COMMENT '状态（0正常 1停用）',
                               `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
                               `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                               `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='岗位信息表';


CREATE TABLE `system_role` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                               `name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
                               `code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
                               `sort` int(11) NOT NULL COMMENT '显示顺序',
                               `data_scope` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
                               `data_scope_dept_ids` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '数据范围(指定部门数组)',
                               `status` tinyint(4) NOT NULL COMMENT '角色状态（0正常 1停用）',
                               `type` tinyint(4) NOT NULL COMMENT '角色类型',
                               `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
                               `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                               `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色信息表';

CREATE TABLE `system_role_menu` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
                                    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                    `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                    `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色和菜单关联表';

CREATE TABLE `system_user_post` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
                                    `post_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '岗位ID',
                                    `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                    `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户岗位表';

CREATE TABLE `system_user_role` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
                                    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                    `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
                                    `tenant_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '租户编号',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户和角色关联表';

CREATE TABLE `crm_permission` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',

                                  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户编号',

                                  `biz_type` tinyint NOT NULL DEFAULT '100' COMMENT '数据类型',
                                  `biz_id` bigint NOT NULL DEFAULT '0' COMMENT '数据编号',

                                  `level` int NOT NULL DEFAULT '0' COMMENT '会员等级',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM 数据权限表';


INSERT INTO `system_users`(`id`, `username`, `password`, `nickname`, `remark`, `dept_id`, `post_ids`, `email`, `mobile`,
                           `sex`, `avatar`, `status`, `login_ip`, `login_date`, `creator`, `create_time`, `updater`,
                           `update_time`, `deleted`, `tenant_id`) VALUES (1, 'admin', '$2a$10$mRMIYLDtRHlf6.9ipiqH1.Z.bh/R9dO9d5iHiGYPigi6r5KOoR2Wm',
'芋道源码', '管理员', 103, '[1]', 'aoteman@126.com', '18818260277', 2,
'http://127.0.0.1:48080/admin-api/infra/file/4/get/37e56010ecbee472cdd821ac4b608e151e62a74d9633f15d085aee026eedeb60.png', 0,
'60.12.139.34', '2024-04-11 19:44:50', 'admin', '2021-01-05 17:03:47', NULL, '2024-04-11 19:44:50', b'0', 1);

INSERT INTO `system_oauth2_client`(`id`, `client_id`, `secret`, `name`, `logo`, `description`, `status`,
  `access_token_validity_seconds`, `refresh_token_validity_seconds`, `redirect_uris`, `authorized_grant_types`, `scopes`,
   `auto_approve_scopes`, `authorities`, `resource_ids`, `additional_information`, `creator`, `create_time`, `updater`,
    `update_time`, `deleted`) VALUES (1, 'default', 'admin123', '芋道源码', 'http://test.yudao.iocoder.cn/a5e2e244368878a366b516805a4aabf1.png',
  '我是描述', 0, 1800, 43200, '[\"https://www.iocoder.cn\",\"https://doc.iocoder.cn\"]', '[\"password\",\"authorization_code\",\"implicit\",\"refresh_token\"]', '[\"user.read\",\"user.write\"]', '[]', '[\"user.read\",\"user.write\"]', '[]', '{}', '1', '2022-05-11 21:47:12', '1', '2024-04-09 12:15:10', b'0');


INSERT INTO `system_role`(`id`, `name`, `code`, `sort`, `data_scope`, `data_scope_dept_ids`, `status`, `type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`) VALUES (1, '超级管理员', 'super_admin', 1, 1, '', 0, 1, '超级管理员', 'admin', '2021-01-05 17:03:48', '', '2022-02-22 05:08:21', b'0', 1);
INSERT INTO `system_user_role`(`id`, `user_id`, `role_id`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`) VALUES (1, 1, 1, '', '2022-01-11 13:19:45', '', '2022-05-12 12:35:17', b'0', 1);
INSERT INTO `system_role_menu`(`id`, `role_id`, `menu_id`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`) VALUES (3879, 1, 1002, '1', '2024-01-02 17:35:25', '1', '2024-01-02 17:35:25', b'0', 1);
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1, '系统管理', '', 2, 10, 0, '/system', 'system', NULL, NULL, 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2024-04-01 12:15:56', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (100, '用户管理', 'system:user:list', 2, 1, 1, 'user', 'user', 'system/user/index', 'SystemUser', 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:31:59', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (101, '角色管理', '', 2, 2, 1, 'role', 'peoples', 'system/role/index', 'SystemRole', 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:33:59', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (102, '菜单管理', '', 2, 3, 1, 'menu', 'tree-table', 'system/menu/index', 'SystemMenu', 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:34:32', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (103, '部门管理', '', 2, 4, 1, 'dept', 'tree', 'system/dept/index', 'SystemDept', 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:35:32', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (104, '岗位管理', '', 2, 5, 1, 'post', 'post', 'system/post/index', 'SystemPost', 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:36:21', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1002, '用户新增', 'system:user:create', 3, 2, 100, '', '', '', NULL, 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1003, '用户修改', 'system:user:update', 3, 3, 100, '', '', '', NULL, 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2, '基础设施', '', 1, 20, 0, '/infra', 'monitor', NULL, NULL, 0, b'1', b'1', b'1', 'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1117, '支付管理', '', 1, 30, 0, '/pay', 'money', NULL, NULL, 0, b'1', b'1', b'1', '1', '2021-12-25 16:43:41', '1', '2022-12-10 16:33:19', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1185, '工作流程', '', 1, 50, 0, '/bpm', 'tool', NULL, NULL, 0, b'1', b'1', b'1', '1', '2021-12-30 20:26:36', '103', '2022-04-20 17:03:10', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1254, '作者动态', '', 1, 0, 0, 'https://www.iocoder.cn', 'ep:avatar', NULL, NULL, 0, b'1', b'1', b'1', '1', '2022-04-23 01:03:15', '1', '2024-02-27 13:37:55', b'1');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1281, '报表管理', '', 1, 40, 0, '/report', 'chart', NULL, NULL, 0, b'1', b'1', b'1', '1', '2022-07-10 20:22:15', '1', '2023-02-07 17:16:40', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2084, '公众号管理', '', 1, 100, 0, '/mp', 'wechat', NULL, NULL, 0, b'1', b'1', b'1', '1', '2023-01-01 20:11:04', '1', '2023-01-15 11:28:57', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2159, 'Boot 开发文档', '', 1, 1, 0, 'https://doc.iocoder.cn/', 'ep:document', NULL, NULL, 0, b'1', b'1', b'1', '1', '2023-02-10 22:46:28', '1', '2024-02-27 13:37:40', b'1');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2160, 'Cloud 开发文档', '', 1, 2, 0, 'https://cloud.iocoder.cn', 'ep:document-copy', NULL, NULL, 0, b'1', b'1', b'1', '1', '2023-02-10 22:47:07', '1', '2024-02-27 13:38:07', b'1');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2262, '会员中心', '', 1, 55, 0, '/member', 'ep:bicycle', NULL, NULL, 0, b'1', b'1', b'1', '1', '2023-06-10 00:42:03', '1', '2023-08-20 09:23:56', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2362, '商城系统', '', 1, 59, 0, '/mall', 'ep:shop', '', '', 0, b'1', b'1', b'1', '1', '2023-09-30 11:52:02', '1', '2023-09-30 11:52:18', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2397, 'CRM 系统', '', 1, 200, 0, '/crm', 'ep:avatar', '', '', 0, b'1', b'1', b'1', '1', '2023-10-29 17:08:30', '1', '2024-02-04 15:37:31', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (2563, 'ERP 系统', '', 1, 300, 0, '/erp', 'fa-solid:store', '', '', 0, b'1', b'1', b'1', '1', '2024-02-04 15:37:25', '1', '2024-02-04 15:37:25', b'0');
INSERT INTO `system_menu`(`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1063, '设置角色菜单权限', 'system:permission:assign-role-menu', 3, 6, 101, '', '', '', NULL, 0, b'1', b'1', b'1', '', '2021-01-06 17:53:44', '', '2022-04-20 17:03:10', b'0');





