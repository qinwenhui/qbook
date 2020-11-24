# Host: localhost  (Version: 5.7.26)
# Date: 2020-11-25 00:57:20
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "q_book"
#

DROP TABLE IF EXISTS `q_book`;
CREATE TABLE `q_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `lanmu_id` int(11) DEFAULT NULL COMMENT '所属栏目:id:name',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` varchar(255) DEFAULT NULL COMMENT '排序',
  `status` tinyint(2) DEFAULT '1' COMMENT '上架状态:0=下架,1=正常',
  `view` int(15) DEFAULT '0' COMMENT '浏览量',
  `image` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `type` varchar(255) DEFAULT NULL COMMENT '类型(漫画1小说2)',
  `auther` varchar(255) DEFAULT NULL COMMENT '作者',
  `author_id` int(11) NOT NULL DEFAULT '0' COMMENT '作者id',
  `desc` longtext COMMENT '描述',
  `mark` int(11) DEFAULT '0' COMMENT '订阅量',
  `ticai_id` int(11) DEFAULT NULL COMMENT '题材:id:name',
  `duzhequn_id` int(11) DEFAULT NULL COMMENT '读者群:id:name',
  `diyu_id` int(11) DEFAULT NULL COMMENT '地域:id:name',
  `mhstatus` tinyint(3) DEFAULT '0' COMMENT '进度:0=连载,1=完结',
  `tjswitch` tinyint(1) DEFAULT '0' COMMENT '推荐:0=OFF,1=ON',
  `isfree` tinyint(1) DEFAULT '0' COMMENT '收费状态:0=免费,1=收费',
  `cjid` varchar(255) DEFAULT NULL COMMENT '采集标识',
  `cjstatus` tinyint(11) DEFAULT '0' COMMENT '采集状态:0=NO,1=YES',
  `cjname` varchar(255) DEFAULT NULL COMMENT '采集渠道',
  `keyword` varchar(255) DEFAULT NULL COMMENT '标签',
  `last_chapter_title` varchar(255) DEFAULT NULL COMMENT '最新章节',
  `searchnums` int(11) DEFAULT '0' COMMENT '被搜索次数',
  `last_chapter` varchar(255) DEFAULT NULL COMMENT '最新章节用于检测',
  `isjingpin` tinyint(1) DEFAULT '0' COMMENT '精品:0=OFF,1=ON',
  `xianmian` tinyint(1) DEFAULT '0' COMMENT '限免:0=OFF,1=ON',
  `cover` varchar(255) DEFAULT NULL COMMENT '横着的封面图',
  `ishot` tinyint(1) DEFAULT '0' COMMENT '热门:0=OFF,1=ON',
  `issole` tinyint(1) DEFAULT '0' COMMENT '独家:0=OFF,1=ON',
  `isnew` tinyint(1) DEFAULT '0' COMMENT '新作:0=OFF,1=ON',
  `h` tinyint(1) DEFAULT '1' COMMENT '18+:0=OFF,1=ON',
  `vipcanread` tinyint(1) DEFAULT '1' COMMENT 'VIP专属:0=OFF,1=ON',
  `pingfen` varchar(255) DEFAULT '9.68' COMMENT '评分',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "q_book"
#

/*!40000 ALTER TABLE `q_book` DISABLE KEYS */;
INSERT INTO `q_book` VALUES (1,'测试作品',NULL,'2020-11-14 01:14:52','2020-11-14 01:14:52',NULL,1,1,NULL,'2','张三的笔名',2,'作品1蹭词层次错错错错\r\n蹭词层次错错错错错错错',0,21,NULL,NULL,0,0,0,NULL,0,NULL,NULL,NULL,0,NULL,0,0,NULL,0,0,0,1,1,'9.68'),(2,'创建作品2',NULL,'2020-11-14 01:15:37','2020-11-14 01:15:37',NULL,1,0,NULL,'2','张三的笔名',2,'啊',0,30,NULL,NULL,0,0,0,NULL,0,NULL,NULL,NULL,0,NULL,0,0,NULL,0,0,0,1,1,'9.68'),(3,'vvvvv',NULL,'2020-11-14 01:17:40','2020-11-14 01:17:40',NULL,1,0,NULL,'2','张三的笔名',2,'aa',0,21,NULL,NULL,0,0,0,NULL,0,NULL,NULL,NULL,0,NULL,0,0,NULL,0,0,0,1,1,'9.68');
/*!40000 ALTER TABLE `q_book` ENABLE KEYS */;

#
# Structure for table "q_capter"
#

DROP TABLE IF EXISTS `q_capter`;
CREATE TABLE `q_capter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) NOT NULL COMMENT '章节标题',
  `image` varchar(255) DEFAULT NULL COMMENT '章节封面图',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `book_id` int(11) NOT NULL DEFAULT '0' COMMENT '书籍id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `isvip` enum('1','0') DEFAULT '0' COMMENT '性别:0=免费,1=VIP',
  `score` int(11) DEFAULT NULL COMMENT '售价',
  `view` int(11) DEFAULT NULL COMMENT '阅读量',
  `type` tinyint(2) DEFAULT '1' COMMENT '类型:2=小说,1=漫画',
  `content` text COMMENT '内容',
  `imagelist` text COMMENT '图片集',
  `cjid` varchar(255) DEFAULT NULL COMMENT '采集标识',
  `cjname` varchar(255) DEFAULT NULL COMMENT '采集渠道',
  `switch` tinyint(1) DEFAULT '0' COMMENT '上架状态:0=下架,1=正常',
  `cjstatus` tinyint(11) DEFAULT '0' COMMENT '采集状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "q_capter"
#

/*!40000 ALTER TABLE `q_capter` DISABLE KEYS */;
INSERT INTO `q_capter` VALUES (1,'第一章 试试',NULL,'2020-11-14 01:23:40','2020-11-14 01:23:40',1,1,'0',25,2,2,'/Uploads/book/1/第一章试试.txt',NULL,NULL,'py',1,0),(2,'第二章',NULL,'2020-11-14 01:31:45','2020-11-14 01:31:45',1,2,'0',25,4,2,'/Uploads/book/1/第二章.txt',NULL,NULL,'py',1,0);
/*!40000 ALTER TABLE `q_capter` ENABLE KEYS */;

#
# Structure for table "q_history"
#

DROP TABLE IF EXISTS `q_history`;
CREATE TABLE `q_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户:id:username',
  `book_id` int(11) NOT NULL DEFAULT '0' COMMENT '浏览书籍id',
  `addtime` varchar(11) DEFAULT NULL COMMENT '浏览时间',
  `type` tinyint(4) DEFAULT '1' COMMENT '类型:1=漫画,2=小说',
  `capter_id` int(11) DEFAULT NULL COMMENT '章节:id:title',
  `showtime` datetime DEFAULT NULL COMMENT '展示时间',
  PRIMARY KEY (`id`,`user_id`,`book_id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

#
# Data for table "q_history"
#

/*!40000 ALTER TABLE `q_history` DISABLE KEYS */;
INSERT INTO `q_history` VALUES (1,383,1,'1603837300',1,48,'2020-10-28 06:21:40'),(2,384,2,'1603827567',1,81,'2020-10-28 03:39:27'),(3,383,5,'1603835765',1,719,'2020-10-28 05:56:05'),(4,383,4,'1603835430',1,546,'2020-10-28 05:50:30'),(5,383,2,'1603835526',1,87,'2020-10-28 05:52:06'),(6,384,1,'1605294827',1,2,'2020-11-14 03:13:47'),(7,385,3,'1603926066',1,219,'2020-10-29 07:01:06'),(8,388,1,'1604403387',1,12,'2020-11-03 19:36:27'),(9,387,1,'1604042479',1,32,'2020-10-30 15:21:19'),(10,390,5,'1603979406',1,714,'2020-10-29 21:50:06'),(11,388,5,'1603985253',1,719,'2020-10-29 23:27:33'),(12,387,5,'1603987950',1,717,'2020-10-30 00:12:30'),(13,386,1,'1604085106',1,10,'2020-10-31 03:11:46'),(14,390,1,'1604283042',1,17,'2020-11-02 10:10:42'),(15,388,2,'1604099809',1,88,'2020-10-31 07:16:49'),(16,391,1,'1604303320',1,12,'2020-11-02 15:48:40'),(17,391,5,'1603998655',1,716,'2020-10-30 03:10:55'),(18,390,3,'1604002997',1,219,'2020-10-30 04:23:17'),(19,385,1,'1604517820',1,22,'2020-11-05 03:23:40'),(20,389,1,'1604094005',1,11,'2020-10-31 05:40:05'),(21,383,17,'1604163113',1,4874,'2020-11-01 00:51:53'),(22,383,82,'1604253800',1,22979,'2020-11-02 02:03:20'),(23,384,82,'1604253322',1,22977,'2020-11-02 01:55:22'),(24,385,28,'1604518270',1,19296,'2020-11-05 03:31:10'),(25,384,214,'1605268934',1,191443,'2020-11-13 20:02:14');
/*!40000 ALTER TABLE `q_history` ENABLE KEYS */;

#
# Structure for table "q_lanmu"
#

DROP TABLE IF EXISTS `q_lanmu`;
CREATE TABLE `q_lanmu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '栏目',
  `switch` tinyint(1) NOT NULL COMMENT '状态:0=隐藏,1=显示',
  `type` tinyint(1) NOT NULL COMMENT '分类:1=漫画,2=小说',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "q_lanmu"
#

/*!40000 ALTER TABLE `q_lanmu` DISABLE KEYS */;
/*!40000 ALTER TABLE `q_lanmu` ENABLE KEYS */;

#
# Structure for table "q_order"
#

DROP TABLE IF EXISTS `q_order`;
CREATE TABLE `q_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户:id:username',
  `pro_id` int(11) NOT NULL COMMENT '套餐:id:title',
  `paydata` tinyint(2) DEFAULT '1' COMMENT '付款类型:1=微信,2=支付宝,3=微信H5',
  `ordernums` varchar(255) NOT NULL COMMENT '订单号',
  `orderswitch` tinyint(255) DEFAULT NULL COMMENT '状态:1=已付款,0=未付款',
  `addtime` datetime DEFAULT NULL COMMENT '下单时间',
  `money` int(11) DEFAULT NULL COMMENT '下单金额',
  `addtime2` varchar(255) DEFAULT NULL COMMENT '时间戳',
  `kou_show` tinyint(11) DEFAULT '1' COMMENT '0代表扣量不展示',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

#
# Data for table "q_order"
#

/*!40000 ALTER TABLE `q_order` DISABLE KEYS */;
INSERT INTO `q_order` VALUES (1,383,11,2,'2020102897481019',1,'2020-10-28 04:58:02',10,'1603832282',1),(2,384,10,2,'738331603917732',0,'2020-10-29 04:42:12',1,'1603917732',1),(3,384,10,2,'215011603917825',0,'2020-10-29 04:43:45',1,'1603917825',1),(4,384,10,2,'9751603917829',0,'2020-10-29 04:43:49',1,'1603917829',1),(5,384,13,2,'81371603917832',0,'2020-10-29 04:43:52',50,'1603917832',1),(6,384,10,2,'365801603917952',0,'2020-10-29 04:45:52',1,'1603917952',1),(7,384,10,2,'391981603917956',0,'2020-10-29 04:45:56',1,'1603917956',1),(8,384,12,2,'176501603917961',0,'2020-10-29 04:46:01',25,'1603917961',1),(9,384,12,2,'694301603917964',0,'2020-10-29 04:46:04',25,'1603917964',1),(10,384,12,2,'951261603917968',0,'2020-10-29 04:46:08',25,'1603917968',1),(11,384,13,2,'813961603917972',0,'2020-10-29 04:46:12',50,'1603917972',1),(12,384,10,2,'671081603918067',0,'2020-10-29 04:47:47',1,'1603918067',1),(13,384,10,2,'712261603918072',0,'2020-10-29 04:47:52',1,'1603918072',1),(14,384,10,2,'422271603918076',0,'2020-10-29 04:47:56',1,'1603918076',1),(15,384,10,2,'723121603918079',0,'2020-10-29 04:47:59',1,'1603918079',1),(16,384,10,2,'185811603918202',0,'2020-10-29 04:50:02',1,'1603918202',1),(17,384,10,2,'464091603918206',0,'2020-10-29 04:50:06',1,'1603918206',1),(18,384,10,2,'821081603918247',0,'2020-10-29 04:50:47',1,'1603918247',1),(19,384,10,2,'123991603918343',0,'2020-10-29 04:52:23',1,'1603918343',1),(20,384,10,2,'860951603918346',0,'2020-10-29 04:52:26',1,'1603918346',1),(21,384,10,2,'54081603918426',0,'2020-10-29 04:53:46',1,'1603918426',1),(22,384,10,2,'374131603918431',0,'2020-10-29 04:53:51',1,'1603918431',1),(23,384,10,2,'680181603918442',0,'2020-10-29 04:54:02',1,'1603918442',1),(24,384,10,2,'976831603918446',0,'2020-10-29 04:54:06',1,'1603918446',1),(25,384,10,2,'634341603918450',0,'2020-10-29 04:54:10',1,'1603918450',1),(26,384,10,2,'266191603918456',0,'2020-10-29 04:54:16',1,'1603918456',1),(27,384,10,2,'295251603918460',0,'2020-10-29 04:54:20',1,'1603918460',1),(28,384,12,2,'2020102997534910',0,'2020-10-29 04:54:50',25,'1603918490',1),(29,384,10,2,'856691603918531',0,'2020-10-29 04:55:31',1,'1603918531',1),(30,384,10,2,'296731603918536',0,'2020-10-29 04:55:36',1,'1603918536',1),(31,384,10,2,'953071603918709',0,'2020-10-29 04:58:29',1,'1603918709',1),(32,384,10,2,'667571603918714',0,'2020-10-29 04:58:34',1,'1603918714',1),(33,384,13,2,'199991603918719',0,'2020-10-29 04:58:39',50,'1603918719',1),(34,384,10,2,'103081603918724',0,'2020-10-29 04:58:44',1,'1603918724',1),(35,384,10,2,'866961603918729',0,'2020-10-29 04:58:49',1,'1603918729',1),(36,384,10,2,'968591603918733',0,'2020-10-29 04:58:53',1,'1603918733',1),(37,384,14,2,'696841603918738',0,'2020-10-29 04:58:58',188,'1603918738',1),(38,384,10,1,'946091603918745',0,'2020-10-29 04:59:05',1,'1603918745',1),(39,384,14,1,'107521603918750',0,'2020-10-29 04:59:10',188,'1603918750',1),(40,384,10,2,'808221603918834',0,'2020-10-29 05:00:34',1,'1603918834',1),(41,384,10,2,'421761603918839',0,'2020-10-29 05:00:39',1,'1603918839',1),(42,384,10,2,'215271603918844',0,'2020-10-29 05:00:44',1,'1603918844',1),(43,384,12,2,'816941603918847',0,'2020-10-29 05:00:47',25,'1603918847',1),(44,384,14,2,'27571603918852',0,'2020-10-29 05:00:52',188,'1603918852',1),(45,384,10,2,'577411603918858',0,'2020-10-29 05:00:58',1,'1603918858',1),(46,384,10,2,'970231603918868',0,'2020-10-29 05:01:08',1,'1603918868',1),(47,384,14,2,'880641603921970',0,'2020-10-29 05:52:50',188,'1603921970',1),(48,384,13,2,'801601603921987',0,'2020-10-29 05:53:07',50,'1603921987',1),(49,384,13,2,'272241603921995',0,'2020-10-29 05:53:15',50,'1603921995',1),(50,384,13,2,'184561603922000',0,'2020-10-29 05:53:20',50,'1603922000',1),(51,384,14,1,'770871603922018',0,'2020-10-29 05:53:38',188,'1603922018',1),(52,384,13,1,'663171603922026',0,'2020-10-29 05:53:46',50,'1603922026',1),(53,384,12,2,'246861603922190',0,'2020-10-29 05:56:30',25,'1603922190',1),(54,384,14,2,'27701603922195',0,'2020-10-29 05:56:35',188,'1603922195',1),(55,383,14,1,'603561603922775',0,'2020-10-29 06:06:15',188,'1603922775',1),(56,384,13,2,'335891603922982',0,'2020-10-29 06:09:42',50,'1603922982',1),(57,384,10,2,'917501603922986',0,'2020-10-29 06:09:46',1,'1603922986',1),(58,384,14,2,'859811603922991',0,'2020-10-29 06:09:51',188,'1603922991',1),(59,384,14,1,'94191603924728',0,'2020-10-29 06:38:48',188,'1603924728',1),(60,383,11,2,'452581603925285',0,'2020-10-29 06:48:05',10,'1603925285',1),(61,384,12,2,'302601603925447',0,'2020-10-29 06:50:47',25,'1603925447',1),(62,384,13,2,'782081603925460',1,'2020-10-29 06:51:00',50,'1603925460',1),(63,385,14,2,'72971603926888',0,'2020-10-29 07:14:48',188,'1603926888',1),(64,385,15,1,'784851603926918',1,'2020-10-29 07:15:18',88,'1603926918',1),(65,385,13,2,'807541603927930',0,'2020-10-29 07:32:10',50,'1603927930',1),(66,385,15,2,'54891604026817',0,'2020-10-30 11:00:17',88,'1604026817',1),(67,383,10,2,'511071604087157',0,'2020-10-31 03:45:57',1,'1604087157',1),(68,383,15,2,'578991604087162',0,'2020-10-31 03:46:02',88,'1604087162',1);
/*!40000 ALTER TABLE `q_order` ENABLE KEYS */;

#
# Structure for table "q_pro"
#

DROP TABLE IF EXISTS `q_pro`;
CREATE TABLE `q_pro` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_status` tinyint(2) DEFAULT '1' COMMENT '类型:1=时间卡,2=VIP卡',
  `title` varchar(255) DEFAULT NULL COMMENT '名称',
  `intro` varchar(255) DEFAULT NULL COMMENT '前端展示名',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `addcoin` int(11) DEFAULT NULL COMMENT '实际到账金币',
  `addvip` int(11) DEFAULT NULL COMMENT '增加的VIP时间',
  `sort` varchar(11) DEFAULT NULL COMMENT '排序',
  `ishotswitch` tinyint(255) DEFAULT '0' COMMENT '是否热销:1=是,0=否',
  `istjswitch` tinyint(255) DEFAULT '0' COMMENT '是否热销:1=是,0=否',
  `iszjswitch` tinyint(255) DEFAULT '0' COMMENT '最佳选择:1=是,0=否',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

#
# Data for table "q_pro"
#

/*!40000 ALTER TABLE `q_pro` DISABLE KEYS */;
INSERT INTO `q_pro` VALUES (10,1,'800+800','金币卡-赠送800金币',1,1800,0,'1',0,1,0),(11,2,'VIP天卡','24小时随便看',10,0,24,'2',1,1,0),(12,2,'周卡','周卡,七天送一天!',25,0,192,'3',1,1,1),(13,2,'月卡[限时降价]','31天任意看',50,500,744,'4',1,0,0),(14,2,'季度卡','100天畅阅',188,0,2400,'5',1,1,0),(15,1,'8800+3800','金币卡--多送38元',88,12600,10,'6',1,1,0);
/*!40000 ALTER TABLE `q_pro` ENABLE KEYS */;

#
# Structure for table "q_user"
#

DROP TABLE IF EXISTS `q_user`;
CREATE TABLE `q_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `paypass` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `register_time` varchar(255) DEFAULT NULL COMMENT '注册时间',
  `last_time` varchar(255) DEFAULT NULL COMMENT '最后登录时间',
  `isvip_status` tinyint(4) DEFAULT '0' COMMENT 'vip状态:1=是,0=否',
  `viptime` varchar(15) DEFAULT NULL COMMENT 'vip到期时间',
  `headimage` varchar(255) DEFAULT NULL COMMENT '头像',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态:1=正常,0=冻结',
  `money` decimal(11,2) DEFAULT '0.00' COMMENT '余额',
  `score` int(11) DEFAULT '0' COMMENT '书币',
  `lv` tinyint(2) DEFAULT '0' COMMENT '等级',
  `pid` int(11) DEFAULT NULL COMMENT '上级ID',
  `path_id` varchar(255) DEFAULT NULL COMMENT '路径',
  `register_ip` varchar(255) DEFAULT NULL COMMENT '注册ip',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录IP',
  `login_nums` int(11) DEFAULT '0' COMMENT '登录次数',
  `useragent` varchar(255) DEFAULT '' COMMENT '用户客户端信息',
  `type` tinyint(4) DEFAULT '0' COMMENT '类别:0=临时,1=注册',
  `lxqd` int(11) DEFAULT '0' COMMENT '连续签到次数',
  `is_agent` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是不是代理商 0 不是 1是的',
  `has_kou` int(11) DEFAULT '0' COMMENT '代理商扣量（0走系统配置-1表示不扣量）',
  `tj_kou` int(11) DEFAULT '0' COMMENT '扣量统计标识',
  `myui` varchar(255) DEFAULT 'color-theme-pink' COMMENT '个人皮肤',
  `is_first` int(11) DEFAULT '1' COMMENT '是否第一次',
  `domain` int(11) DEFAULT '0' COMMENT '来源域名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "q_user"
#

/*!40000 ALTER TABLE `q_user` DISABLE KEYS */;
INSERT INTO `q_user` VALUES (1,'admin',NULL,'123456',NULL,NULL,NULL,0,NULL,NULL,1,0.00,0,0,NULL,NULL,NULL,NULL,0,'',0,0,0,0,0,'color-theme-pink',1,0),(2,'qwe',NULL,'qwe',NULL,NULL,NULL,0,NULL,NULL,1,0.00,0,0,NULL,NULL,NULL,NULL,0,'',0,0,0,0,0,'color-theme-pink',1,0),(3,'qqq',NULL,'qqq',NULL,NULL,NULL,0,NULL,NULL,1,0.00,0,0,NULL,NULL,NULL,NULL,0,'',0,0,0,0,0,'color-theme-pink',1,0),(4,'adsada',NULL,'sdasd',NULL,NULL,NULL,0,NULL,NULL,1,0.00,0,0,NULL,NULL,NULL,NULL,0,'',0,0,0,0,0,'color-theme-pink',1,0),(5,'asdsasd',NULL,'ffsfs',NULL,NULL,NULL,0,NULL,NULL,1,0.00,0,0,NULL,NULL,NULL,NULL,0,'',0,0,0,0,0,'color-theme-pink',1,0),(6,'1323',NULL,'sdas',NULL,NULL,NULL,0,NULL,NULL,1,0.00,0,0,NULL,NULL,NULL,NULL,0,'',0,0,0,0,0,'color-theme-pink',1,0),(7,'ddddd',NULL,'123',NULL,NULL,NULL,0,NULL,NULL,1,0.00,0,0,NULL,NULL,NULL,NULL,0,'',0,0,0,0,0,'color-theme-pink',1,0);
/*!40000 ALTER TABLE `q_user` ENABLE KEYS */;

#
# Structure for table "sys_menu"
#

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `url` varchar(255) NOT NULL COMMENT '菜单路径',
  `pid` int(11) DEFAULT '0' COMMENT '父级菜单',
  `icon` varchar(255) DEFAULT '' COMMENT '图标',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='菜单表';

#
# Data for table "sys_menu"
#

INSERT INTO `sys_menu` VALUES (1,'菜单管理','aas',0,'layui-icon-component',2),(2,'权限管理','Permission,/permission',0,'el-icon-lock',3),(3,'编辑管理','{\"component\":\"views/charts/keyboard\",\"name\":\"KeyboardChart\",\"path\":\"keyboard\"}',2,'layui-icon-friends',1),(4,'角色管理','Role,/role',2,'user',2),(5,'接口管理','Api,/api',2,'el-icon-paperclip',3),(6,'作者管理','',0,'layui-icon-username',4),(15,'主页','',0,'layui-icon-console',1),(16,'控制台','/12',15,'layui-icon-template-1',1),(17,'作者列表','author/list',6,'layui-icon-star-fill',2),(18,'作者统计','author/tongji',6,'layui-icon-star-fill',3),(19,'作者信息','author/info',6,'layui-icon-star-fill',1),(20,'书籍管理','',0,'layui-icon-read',5),(21,'我的书籍','book/mybook',20,'layui-icon-form',2),(22,'书籍列表','book/list',20,'layui-icon-component',1),(23,'创建书籍','book/add',20,'layui-icon-star-fill',3),(24,'统计管理','',0,'layui-icon-table',6),(25,'书籍统计','statistic/book',24,'layui-icon-app',1),(26,'酬稿统计','statistic/salary',24,'layui-icon-star-fill',2),(27,'编辑菜单','/admin/menu1',1,NULL,2);

#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `description` varchar(500) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) NOT NULL COMMENT 'url地址',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

#
# Data for table "sys_permission"
#

INSERT INTO `sys_permission` VALUES (1,'获取当前用户信息','  获取当前登录用户信息','/admin/admin/loginUserInfo',0),(2,'获取用户信息','通过用户id获取用户信息','',0),(3,'查询所有作者','管理员和主编可以查询所有作者信息，责编和网编只能查询其名下的所有作者','/admin/admin/userlist',0),(4,'获取菜单','根据登录的用户角色不同获取后台菜单列表','/admin/admin/userMenuList',0),(5,'获取菜单列表','分页获取菜单列表','/admin/admin/allMenuList',0),(6,'添加菜单','添加菜单','',0),(7,'修改菜单','修改惨淡','',0),(8,'删除菜单','删除菜单，参数是id和ids','',0),(9,'获取接口列表','获取接口列表，可以分页，模糊查询','',0),(10,'添加接口','添加接口','',0),(11,'编辑接口','  编辑接口','',0),(12,'删除接口','删除接口','',0),(17,'角色列表','  获取角色列表','/admin/system/rolelist',0),(18,'添加角色','  添加角色','/admin/system/addrole',0),(19,'编辑角色','  编辑角色','/admin/system/deleterole',0),(20,'删除角色','  删除角色','/admin/system/editrole',0),(21,'获取角色权限','  通过角色id获取某个角色的所有权限','',0),(22,'设置角色权限','  设置角色的接口权限','',0),(23,'获取角色菜单','获取角色菜单','',0),(24,'设置角色菜单','  设置角色菜单','',0),(25,'获取编辑人员','获取编辑人员','',0),(26,'添加工作人员','  添加工作人员(主编、责编、网编)','',0),(27,'编辑工作人员',' 编辑工作人员','',0),(28,'删除用户','  删除用户，包括作者、编辑、管理员等\n参数有id和ids','/admin/admin/logout',0),(29,'上传文件','通用上传文件接口，参数upload_type指明上传用途,user为上传用户头像，book为书籍相关','',0),(30,'编辑当前用户信息','  编辑当前登录的用户的信息','',0);

#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "sys_role"
#

INSERT INTO `sys_role` VALUES (1,'管理员','作者后台管理员',0),(2,'主编','编辑',0),(3,'责编','编辑',0),(4,'网编','编辑',0),(5,'作者','小说作者',1),(6,'飒飒','2122',0),(7,'撒撒','11111',0),(8,'打算大苏打','121212121',0);

#
# Structure for table "sys_role_menu"
#

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '删除,0正常，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

#
# Data for table "sys_role_menu"
#

INSERT INTO `sys_role_menu` VALUES (1,1,1,0),(2,1,2,0),(3,1,3,0),(4,1,4,0),(5,1,5,0),(6,1,6,0),(7,1,15,0),(8,1,16,0),(9,2,1,0),(10,2,3,0),(11,2,4,0),(12,2,6,0),(13,2,15,0),(14,2,16,0),(15,2,2,0),(16,2,5,0),(17,2,17,0),(18,2,18,0),(19,1,17,0),(20,1,18,0),(21,1,19,0),(22,1,20,0),(23,1,21,0),(24,1,22,0),(25,1,23,0),(26,1,24,0),(27,1,25,0),(28,1,26,0);

#
# Structure for table "sys_role_permission"
#

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '删除,0正常，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

#
# Data for table "sys_role_permission"
#

INSERT INTO `sys_role_permission` VALUES (1,1,1,0),(2,1,2,0),(5,3,1,0),(6,3,2,0),(7,4,1,0),(8,4,2,0),(9,5,1,0),(10,1,3,0),(14,3,3,0),(16,4,3,0),(18,1,4,0),(20,3,4,0),(21,4,4,0),(22,5,4,0),(23,1,5,0),(24,1,6,0),(25,1,7,0),(26,1,8,0),(27,1,9,0),(28,1,10,0),(29,1,11,0),(30,1,12,0),(31,1,17,0),(32,1,18,0),(33,1,19,0),(34,1,20,0),(35,1,21,0),(36,1,22,0),(98,2,1,0),(99,2,2,0),(100,2,3,0),(101,1,23,0),(102,1,24,0),(103,1,25,0),(104,2,4,0),(105,2,5,0),(106,2,6,0),(107,2,7,0),(108,2,8,0),(109,2,9,0),(110,2,10,0),(111,2,11,0),(112,2,12,0),(113,2,17,0),(114,2,18,0),(115,2,19,0),(116,2,20,0),(117,2,21,0),(118,2,22,0),(119,2,23,0),(120,2,24,0),(121,2,25,0),(122,1,26,0),(123,1,27,0),(124,1,28,0),(125,1,29,0),(126,1,30,0),(127,1,31,0),(137,1,40,1),(140,1,41,1),(141,1,42,1);

#
# Structure for table "sys_user_role"
#

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '删除,0正常，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "sys_user_role"
#

INSERT INTO `sys_user_role` VALUES (1,1,1,0);
