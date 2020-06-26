DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `did` int(11) NOT NULL COMMENT '部门编号',
  `parent_did` int(11) NOT NULL COMMENT '上级部门编号',
  `cn_name` varchar(250) DEFAULT NULL COMMENT '部门中文名称',
  `full_name` varchar(1000) DEFAULT NULL COMMENT '部门完整层级名称',
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modified` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_did`(`did`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '项目编号',
  `en_name` varchar(500) NOT NULL,
  `cn_name` varchar(250) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `owner_id` int(20) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_pid`(`pid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;