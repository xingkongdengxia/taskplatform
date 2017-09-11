-- MySQL dump 10.13  Distrib 5.1.73, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: framework
-- ------------------------------------------------------
-- Server version	5.1.73

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `upms_log`
--

DROP TABLE IF EXISTS `upms_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `description` varchar(100) DEFAULT NULL COMMENT '操作描述',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `start_time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `spend_time` int(11) DEFAULT NULL COMMENT '消耗时间',
  `base_path` varchar(500) DEFAULT NULL COMMENT '根路径',
  `uri` varchar(500) DEFAULT NULL COMMENT 'URI',
  `url` varchar(500) DEFAULT NULL COMMENT 'URL',
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` mediumtext,
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户标识',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `result` mediumtext,
  `permissions` varchar(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`log_id`),
  KEY `log_id` (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=779 DEFAULT CHARSET=utf8 COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_log`
--

LOCK TABLES `upms_log` WRITE;
/*!40000 ALTER TABLE `upms_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `upms_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_organization`
--

DROP TABLE IF EXISTS `upms_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_organization` (
  `organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(10) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '组织名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '组织描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='组织';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_organization`
--

LOCK TABLES `upms_organization` WRITE;
/*!40000 ALTER TABLE `upms_organization` DISABLE KEYS */;
INSERT INTO `upms_organization` VALUES (1,NULL,'总部','北京总部',1),(4,NULL,'河北分部','河北石家庄',1488122466236),(5,NULL,'河南分部','河南郑州',1488122480265),(6,NULL,'湖北分部','湖北武汉',1488122493265),(7,NULL,'湖南分部','湖南长沙',1488122502752);
/*!40000 ALTER TABLE `upms_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_permission`
--

DROP TABLE IF EXISTS `upms_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_permission` (
  `permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `system_id` int(10) unsigned NOT NULL COMMENT '所属系统',
  `pid` int(10) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  `permission_value` varchar(50) DEFAULT NULL COMMENT '权限值',
  `uri` varchar(100) DEFAULT NULL COMMENT '路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_permission`
--

LOCK TABLES `upms_permission` WRITE;
/*!40000 ALTER TABLE `upms_permission` DISABLE KEYS */;
INSERT INTO `upms_permission` VALUES (1,1,0,'系统组织管理',1,'','','zmdi zmdi-accounts-list',1,1,1),(2,1,1,'系统管理',2,'upms:system:read','/manage/system/index','',1,2,2),(3,1,1,'组织管理',2,'upms:organization:read','/manage/organization/index','',1,3,3),(4,1,0,'角色用户管理',1,'','','zmdi zmdi-accounts',1,4,4),(5,1,4,'角色管理',2,'upms:role:read','/manage/role/index','',1,6,6),(6,1,4,'用户管理',2,'upms:user:read','/manage/user/index','',1,5,5),(7,1,0,'权限资源管理',1,'','','zmdi zmdi-lock-outline',1,7,7),(12,1,0,'其他数据管理',1,'','','zmdi zmdi-more',1,12,12),(14,1,12,'会话管理',2,'upms:session:read','/manage/session/index','',1,14,14),(15,1,12,'日志记录',2,'upms:log:read','/manage/log/index','',1,15,15),(17,2,0,'标签类目管理',1,NULL,NULL,'zmdi zmdi-menu',1,17,17),(18,2,17,'标签管理',2,'cms:tag:read','/manage/tag/index',NULL,1,18,18),(19,2,17,'类目管理',2,'cms:category:read','/manage/category/index',NULL,1,19,19),(20,2,0,'文章评论管理',1,NULL,NULL,'zmdi zmdi-collection-text',1,20,20),(21,2,20,'文章管理',2,'cms:article:read','/manage/article/index',NULL,1,21,21),(22,2,20,'回收管理',2,'cms:article:read','/manage/article/recycle',NULL,1,22,22),(24,1,2,'新增系统',3,'upms:system:create','/manage/system/create','zmdi zmdi-plus',1,24,24),(25,1,2,'编辑系统',3,'upms:system:update','/manage/system/update','zmdi zmdi-edit',1,25,25),(26,1,2,'删除系统',3,'upms:system:delete','/manage/system/delete','zmdi zmdi-close',1,26,26),(27,1,3,'新增组织',3,'upms:organization:create','/manage/organization/create','zmdi zmdi-plus',1,27,27),(28,1,3,'编辑组织',3,'upms:organization:update','/manage/organization/update','zmdi zmdi-edit',1,28,28),(29,1,3,'删除组织',3,'upms:organization:delete','/manage/organization/delete','zmdi zmdi-close',1,29,29),(30,1,6,'新增用户',3,'upms:user:create','/manage/user/create','zmdi zmdi-plus',1,30,30),(31,1,6,'编辑用户',3,'upms:user:update','/manage/user/update','zmdi zmdi-edit',1,31,31),(32,1,6,'删除用户',3,'upms:user:delete','/manage/user/delete','zmdi zmdi-close',1,32,32),(33,1,5,'新增角色',3,'upms:role:create','/manage/role/create','zmdi zmdi-plus',1,33,33),(34,1,5,'编辑角色',3,'upms:role:update','/manage/role/update','zmdi zmdi-edit',1,34,34),(35,1,5,'删除角色',3,'upms:role:delete','/manage/role/delete','zmdi zmdi-close',1,35,35),(36,1,39,'新增权限',3,'upms:permission:create','/manage/permission/create','zmdi zmdi-plus',1,36,36),(37,1,39,'编辑权限',3,'upms:permission:update','/manage/permission/update','zmdi zmdi-edit',1,37,37),(38,1,39,'删除权限',3,'upms:permission:delete','/manage/permission/delete','zmdi zmdi-close',1,38,38),(39,1,7,'权限管理',2,'upms:permission:read','/manage/permission/index',NULL,1,39,39),(46,1,5,'角色权限',3,'upms:role:permission','/manage/role/permission','zmdi zmdi-key',1,1488091928257,1488091928257),(48,1,6,'用户组织',3,'upms:user:organization','/manage/user/organization','zmdi zmdi-accounts-list',1,1488120011165,1488120011165),(50,1,6,'用户角色',3,'upms:user:role','/manage/user/role','zmdi zmdi-accounts',1,1488120554175,1488120554175),(51,1,6,'用户权限',3,'upms:user:permission','/manage/user/permission','zmdi zmdi-key',1,1488092013302,1488092013302),(53,1,14,'强制退出',3,'upms:session:forceout','/manage/session/forceout','zmdi zmdi-run',1,1488379514715,1488379514715),(54,2,18,'新增标签',3,'cms:tag:create','/manage/tag/create','zmdi zmdi-plus',1,1489417315159,1489417315159),(55,2,18,'编辑标签',3,'cms:tag:update','zmdi zmdi-edit','zmdi zmdi-widgets',1,1489417344931,1489417344931),(56,2,18,'删除标签',3,'cms:tag:delete','/manage/tag/delete','zmdi zmdi-close',1,1489417372114,1489417372114),(57,1,15,'删除权限',3,'upms:log:delete','/manage/log/delete','zmdi zmdi-close',1,1489503867909,1489503867909),(58,2,19,'编辑类目',3,'cms:category:update','/manage/category/update','zmdi zmdi-edit',1,1489586600462,1489586600462),(59,2,19,'删除类目',3,'cms:category:delete','/manage/category/delete','zmdi zmdi-close',1,1489586633059,1489586633059),(60,2,19,'新增类目',3,'cms:category:create','/manage/category/create','zmdi zmdi-plus',1,1489590342089,1489590342089),(61,2,0,'其他数据管理',1,'','','zmdi zmdi-more',1,1489835455359,1489835455359),(62,2,20,'评论管理',2,'cms:comment:read','/manage/comment/index','',1,1489591408224,1489591408224),(63,2,62,'删除评论',3,'cms:comment:delete','/manage/comment/delete','zmdi zmdi-close',1,1489591449614,1489591449614),(64,2,79,'单页管理',2,'cms:page:read','/manage/page/index','',1,1489591332779,1489591332779),(65,2,64,'新增单页',3,'cms:page:create','/manage/page/create','zmdi zmdi-plus',1,1489591614473,1489591614473),(66,2,64,'编辑单页',3,'cms:page:update','/manage/page/update','zmdi zmdi-edit',1,1489591653000,1489591653000),(67,2,64,'删除单页',3,'cms:page:delete','/manage/page/delete','zmdi zmdi-close',1,1489591683552,1489591683552),(68,2,61,'菜单管理',2,'cms:menu:read','/manage/menu/index','zmdi zmdi-widgets',1,1489591746846,1489591746846),(69,2,68,'新增菜单',3,'cms:menu:create','/manage/menu/create','zmdi zmdi-plus',1,1489591791747,1489591791747),(70,2,68,'编辑菜单',3,'cms:menu:update','/manage/menu/update','zmdi zmdi-edit',1,1489591831878,1489591831878),(71,2,68,'删除菜单',3,'cms:menu:delete','/manage/menu/delete','zmdi zmdi-close',1,1489591865454,1489591865454),(72,2,61,'系统设置',2,'cms:setting:read','/manage/setting/index','zmdi zmdi-widgets',1,1489591981165,1489591981165),(73,2,72,'新增设置',3,'cms:setting:create','/manage/setting/create','zmdi zmdi-plus',1,1489592024762,1489592024762),(74,2,72,'编辑设置',3,'cms:setting:update','/manage/setting/update','zmdi zmdi-edit',1,1489592052582,1489592052582),(75,2,72,'删除设置',3,'cms:setting:delete','/manage/setting/delete','zmdi zmdi-close',1,1489592081426,1489592081426),(76,2,21,'新增文章',3,'cms:article:create','/manage/article/create','zmdi zmdi-plus',1,1489820150404,1489820150404),(77,2,21,'编辑文章',3,'cms:article:update','/manage/article/update','zmdi zmdi-edit',1,1489820178269,1489820178269),(78,2,21,'删除文章',3,'cms:article:delete','/manage/article/delete','zmdi zmdi-close',1,1489820207607,1489820207607),(79,2,0,'单页专题管理',1,'','','zmdi zmdi-view-web',1,1489835320327,1489835320327),(80,2,79,'专题管理',2,'cms:topic:read','/manage/topic/index','zmdi zmdi-widgets',1,1489591507566,1489591507566),(81,2,80,'新增专题',3,'cms:topic:create','/manage/topic/create','zmdi zmdi-plus',1,1489843327028,1489843327028),(82,2,80,'编辑专题',3,'cms:topic:update','/manage/topic/update','zmdi zmdi-edit',1,1489843351513,1489843351513),(83,2,80,'删除专题',3,'cms:topic:delete','/manage/topic/delete','zmdi zmdi-close',1,1489843379953,1489843379953),(84,2,68,'上移菜单',3,'cms:menu:up','/manage/menu/up','zmdi zmdi-long-arrow-up',1,1489846486548,1489846486548),(85,2,68,'下移菜单',3,'cms:menu:down','/manage/menu/down','zmdi zmdi-long-arrow-down',1,1489846578051,1489846578051),(89,1,0,'安全设置',1,'','','zmdi-shield-security',1,1504765124933,1504765124933),(90,1,89,'修改密码',2,'upms:security:read','/manage/user/modipass','zmdi zmdi-widgets',1,1504765289559,1504765289559),(91,1,90,'修改密码',3,'upms:security:modipass','/manage/user/modipass','zmdi zmdi-archive',1,1504772699965,1504772699965);
/*!40000 ALTER TABLE `upms_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_role`
--

DROP TABLE IF EXISTS `upms_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `title` varchar(20) DEFAULT NULL COMMENT '角色标题',
  `description` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `orders` bigint(20) NOT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_role`
--

LOCK TABLES `upms_role` WRITE;
/*!40000 ALTER TABLE `upms_role` DISABLE KEYS */;
INSERT INTO `upms_role` VALUES (1,'super','超级管理员','拥有所有权限',1,1),(2,'admin','管理员','拥有除权限管理系统外的所有权限',1487471013117,1487471013117),(4,'normal','普通人员','具有系统通用功能的操作权限',1504764440072,1504764440072);
/*!40000 ALTER TABLE `upms_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_role_permission`
--

DROP TABLE IF EXISTS `upms_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_role_permission` (
  `role_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_permission_id`),
  KEY `FK_Reference_23` (`role_id`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`role_id`) REFERENCES `upms_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_role_permission`
--

LOCK TABLES `upms_role_permission` WRITE;
/*!40000 ALTER TABLE `upms_role_permission` DISABLE KEYS */;
INSERT INTO `upms_role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,39),(12,1,12),(14,1,14),(15,1,15),(17,1,17),(19,1,19),(20,1,20),(21,1,21),(24,1,24),(27,1,27),(28,1,28),(29,1,29),(30,1,30),(31,1,31),(32,1,32),(33,1,33),(34,1,34),(35,1,35),(36,1,36),(37,1,37),(38,1,38),(39,1,46),(40,1,51),(44,1,48),(45,1,50),(47,1,53),(48,1,18),(49,1,54),(50,1,54),(51,1,55),(52,1,54),(53,1,55),(54,1,56),(55,1,57),(56,1,58),(57,1,58),(58,1,59),(59,1,60),(60,1,61),(61,1,62),(62,1,62),(63,1,63),(64,1,62),(65,1,63),(66,1,64),(67,1,62),(68,1,63),(69,1,64),(70,1,65),(71,1,62),(72,1,63),(73,1,64),(74,1,65),(75,1,66),(76,1,62),(77,1,63),(78,1,64),(79,1,65),(80,1,66),(81,1,67),(82,1,68),(83,1,69),(84,1,69),(85,1,70),(86,1,69),(87,1,70),(88,1,71),(89,1,72),(90,1,72),(91,1,73),(92,1,72),(93,1,73),(94,1,74),(95,1,72),(96,1,73),(97,1,74),(98,1,75),(99,1,76),(100,1,76),(101,1,77),(102,1,76),(103,1,77),(105,1,79),(106,1,80),(107,1,81),(108,1,81),(109,1,82),(110,1,81),(111,1,82),(112,1,83),(113,1,84),(114,1,84),(115,1,85),(121,1,78),(122,1,78),(124,1,25),(125,1,26),(126,1,86),(127,1,87),(128,2,86),(129,2,87),(132,4,88),(133,1,88),(136,2,88),(137,4,89),(138,4,90),(139,2,89),(140,2,90),(141,1,89),(142,1,90),(143,1,91),(144,2,91),(145,4,91);
/*!40000 ALTER TABLE `upms_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_system`
--

DROP TABLE IF EXISTS `upms_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_system` (
  `system_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `banner` varchar(150) DEFAULT NULL COMMENT '背景',
  `theme` varchar(50) DEFAULT NULL COMMENT '主题',
  `basepath` varchar(100) DEFAULT NULL COMMENT '根目录',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(-1:黑名单,1:正常)',
  `name` varchar(20) DEFAULT NULL COMMENT '系统名称',
  `title` varchar(20) DEFAULT NULL COMMENT '系统标题',
  `description` varchar(300) DEFAULT NULL COMMENT '系统描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_system`
--

LOCK TABLES `upms_system` WRITE;
/*!40000 ALTER TABLE `upms_system` DISABLE KEYS */;
INSERT INTO `upms_system` VALUES (1,'zmdi zmdi-shield-security','/resources/admin/images/upms.png','#29A176','http://localhost:8080',1,'upms-server','权限管理系统','用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）',1,1),(2,'zmdi zmdi-wikipedia','/resources/admin/images/cms.png','#455EC5','http://localhost:8080',0,'cms-admin','内容管理系统','内容管理系统（门户、博客、论坛、问答等）',2,2),(3,'zmdi zmdi-paypal-alt','/resources/admin/images/pay.png','#F06292','http://localhost:8080',0,'pay-admin','支付管理系统','支付管理系统',3,3),(4,'zmdi zmdi-account','/resources/admin/images/ucenter.png','#6539B4','http://localhost:8080',0,'ucenter-home','用户管理系统','用户管理系统',4,4),(5,'zmdi zmdi-cloud','/resources/admin/images/oss.png','#0b8de5','http://localhost:8080',-1,'oss-web','存储管理系统','存储管理系统',5,5);
/*!40000 ALTER TABLE `upms_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_user`
--

DROP TABLE IF EXISTS `upms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `realname` varchar(20) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `locked` tinyint(4) DEFAULT NULL COMMENT '状态(0:正常,1:锁定)',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_user`
--

LOCK TABLES `upms_user` WRITE;
/*!40000 ALTER TABLE `upms_user` DISABLE KEYS */;
INSERT INTO `upms_user` VALUES (1,'admin','3038D9CB63B3152A79B8153FB06C02F7','66f1b370c660445a8657bf8bf1794486','蔡华臣','/resources/admin/images/avatar.jpg','','13119725@qq.com',1,0,1),(2,'test','285C9762F5F9046F5893F752DFAF3476','d2d0d03310444ad388a8b290b0fe8564','蔡华臣','/resources/admin/images/avatar.jpg','','13119725@qq.com',1,0,1493394720495);
/*!40000 ALTER TABLE `upms_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_user_organization`
--

DROP TABLE IF EXISTS `upms_user_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_user_organization` (
  `user_organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `organization_id` int(10) unsigned NOT NULL COMMENT '组织编号',
  PRIMARY KEY (`user_organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='用户组织关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_user_organization`
--

LOCK TABLES `upms_user_organization` WRITE;
/*!40000 ALTER TABLE `upms_user_organization` DISABLE KEYS */;
INSERT INTO `upms_user_organization` VALUES (19,1,1),(20,1,4),(21,1,5),(22,1,6),(23,1,7),(25,2,5),(26,2,6);
/*!40000 ALTER TABLE `upms_user_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_user_permission`
--

DROP TABLE IF EXISTS `upms_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_user_permission` (
  `user_permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限编号',
  `type` tinyint(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='用户权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_user_permission`
--

LOCK TABLES `upms_user_permission` WRITE;
/*!40000 ALTER TABLE `upms_user_permission` DISABLE KEYS */;
INSERT INTO `upms_user_permission` VALUES (3,1,22,-1),(4,1,22,1),(5,2,24,-1),(6,2,26,-1),(7,2,27,-1),(8,2,29,-1),(9,2,32,-1),(10,2,51,-1),(11,2,48,-1),(12,2,50,-1),(13,2,35,-1),(14,2,46,-1),(15,2,37,-1),(16,2,38,-1),(17,2,57,-1),(18,2,56,-1),(19,2,59,-1),(20,2,78,-1),(21,2,67,-1),(22,2,83,-1),(23,2,71,-1),(24,2,75,-1);
/*!40000 ALTER TABLE `upms_user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upms_user_role`
--

DROP TABLE IF EXISTS `upms_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upms_user_role` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `role_id` int(10) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upms_user_role`
--

LOCK TABLES `upms_user_role` WRITE;
/*!40000 ALTER TABLE `upms_user_role` DISABLE KEYS */;
INSERT INTO `upms_user_role` VALUES (10,4,2),(14,1,1),(15,1,2),(16,2,4);
/*!40000 ALTER TABLE `upms_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-08 10:40:49
