/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2017-03-09 12:23:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_province` varchar(255) NOT NULL COMMENT '地址 省',
  `address_city` varchar(255) NOT NULL COMMENT '地址 市',
  `address_district` varchar(255) NOT NULL COMMENT '地址 区',
  `address_street` varchar(255) NOT NULL COMMENT '地址 街道或路',
  `address` varchar(255) NOT NULL COMMENT '地址 详细',
  `coord_long` double NOT NULL COMMENT '经度',
  `coord_lat` double NOT NULL COMMENT '纬度',
  `service_type` varchar(255) NOT NULL,
  `service_object` varchar(255) NOT NULL COMMENT '服务对象（儿童、老人……）',
  `recruit_time` varchar(255) NOT NULL,
  `recruit_person_number` int(11) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '活动名称',
  `description` text NOT NULL,
  `time` varchar(255) NOT NULL,
  `principal_name` varchar(255) NOT NULL,
  `principal_contact` varchar(255) NOT NULL,
  `activity_status_id` int(11) NOT NULL COMMENT '活动状态',
  `working_hours` double(11,0) NOT NULL COMMENT '记工时数',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `picture` varchar(255) NOT NULL DEFAULT '',
  `code` int(11) NOT NULL COMMENT '随机的六位数',
  `organization_id` int(11) NOT NULL,
  `tribe_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `activity_activity_status_id` (`activity_status_id`),
  CONSTRAINT `activity_activity_status_id` FOREIGN KEY (`activity_status_id`) REFERENCES `activity_status` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '浙江省', '杭州市', '西湖区', '紫荆花路', '西湖区紫荆花路9号紫庭南弄紫金庭园芦荻苑9幢3号商铺', '120.10045467807049', '30.276733980070816', '敬老助残', '老人', '3月3号', '20', '为老人开展志愿服务', '为老人开展志愿服务，要求会理发，义诊，缝补，磨剪刀，心理咨询，清洗眼镜，量血压', '3月5号', '卜丽萍', '13805771623', '3', '8', '2017-02-21 00:00:00', '/public/img/9629ec51-4968-4173-b109-4ac89616350a.jpeg', '120932', '3', '2084525335');
INSERT INTO `activity` VALUES ('2', '浙江省', '杭州市', '拱墅区', '丰潭路', '西湖区丰潭路255号（近政苑小区）', '120.11228674951892', '30.30248685539544', '便民服务', '老人', '即日起至2月18日止', '40', '理发捶背等服务老', '理发捶背等服务老人的志愿者，司机，拍摄人员', '2月18号', '阿惠', '13968164404', '3', '2', '2017-02-21 20:32:22', '/public/img/5640d09f-f89b-44ae-97f0-5aa71dd71587.png', '101746', '3', '2084525335');
INSERT INTO `activity` VALUES ('3', '浙江省', '杭州市', '江干区', '金沙大道', '江干区金沙大道龙湖时代天街6层-001', '120.33427116078644', '30.315655589097755', '便民服务', '“好家风”党员志愿者服务队队员和其他爱心人士', '即日起至2月18日止', '30', '弯腰一秒，捡起垃圾，为城市增光', '弯腰一秒，捡起垃圾，为城市增光', '2月19号', '方卫生', '13868030016', '3', '20', '2017-02-22 00:00:00', '/public/img/574faf4c-a919-4fb6-9bf6-d861640ca40d.jpeg', '145933', '3', '2084525335');
INSERT INTO `activity` VALUES ('4', '浙江省', '杭州市', '上城区', '湖滨路', '上城区学士路58号（东坡路至长生路左拐，即可到达学士路九井店门口）', '120.16888851527868', '30.261106821572888', '便民服务', '杭州环境', '即日起至2月19日止', '20', '清蓝行动，倡导垃圾你不落地', '清蓝行动，倡导垃圾你不落地，志愿杭州，相识杭州创业杭州', '2月19号', '黄元淑', '18905812538', '1', '15', '2017-02-21 20:32:24', '/public/img/4784a926-3bbd-4da3-a3f7-495b7ef833f7.jpeg', '124431', '3', '2084525335');
INSERT INTO `activity` VALUES ('5', '浙江省', '杭州市', '上城区', '湖滨路', '上城区学士路58号（东坡路至长生路左拐，即可到达学士路九井店门口）', '120.16888851527868', '30.261106821572888', '大型活动', '送花车队', '3月4号下午1点止', '60', '为“百姓身边的活雷锋”送去鲜花与祝福', '同当地道德模范、社会精英名人、奥运或亚运冠军、最美志愿者和广电名主持一同发出为“百姓身边的活雷锋”送去鲜花与祝福', '3月4号', '应丰蔚', '0571-85174221', '1', '5', '2017-02-21 00:00:00', '/public/img/27cf115d-5161-4cd8-909d-a49c7d107eef.jpeg', '184353', '3', '2084525335');
INSERT INTO `activity` VALUES ('6', '浙江省', '杭州市', '萧山区', '萧绍路', '萧山区萧绍路1131号（315车站往东150米）', '120.28156726929555', '30.170919633318324', '青少年服务', '志愿者档案', '即日起至2月6号', '3', '协助进行大屋顶志愿者管理工作', '协助进行大屋顶志愿者管理工作', '2月6号', '马晨阳', '15088626056', '2', '7', '2017-02-21 00:00:00', '/public/img/b072d18f-2f6e-449c-be81-68da7d33d4e3.png', '148477', '3', '2084525335');
INSERT INTO `activity` VALUES ('7', '浙江省', '杭州市', '萧山区', '城河街', '萧山区城河街33-34号（新华书店旁）', '120.27334012750275', '30.16913332703096', '社会管理', '献血', '即日起至2月8号', '100', '引导献血人员填写信息、化验以及献血过程中的协助', '引导献血人员填写信息、化验以及献血过程中的协助。在献血车中为献血者提供热水及饮食服务等献血后的恢复工作。户外志愿献血宣传', '2月8号', '周洪华', '15990085180', '1', '6', '2017-02-21 20:32:27', '/public/img/ef0afd6e-b4eb-429d-bed1-9498450a2ebe.jpeg', '189324', '3', '2084525335');
INSERT INTO `activity` VALUES ('8', '浙江省', '杭州市', '江干区', '金沙大道', '江干区金沙大道龙湖时代天街6层-001', '120.33427116078644', '30.315655589097755', '文化体育', '梅花', '即日起至2月28号', '50', '进化梅花节期间志愿服务工作', '进化梅花节期间志愿服务工作', '1月26号', '陈清峰', '13968038789', '1', '6', '2017-02-21 20:32:28', '/public/img/0a23ca1b-2d33-48c8-8a48-661a6af05caf.jpeg', '101192', '3', '2084525335');
INSERT INTO `activity` VALUES ('9', '浙江省', '杭州市', '上城区', '翰林街', '下城区翰林街88号（庆春路）', '120.19134245380603', '30.26173419140316', '环境保护', '韩美林艺术馆', '即日起到12月31号', '30', '志愿者提供志愿服务，包括活动前期准备', '志愿者提供志愿服务，包括活动前期准备，活动帮助，结束后的场所整理', '12月31号', '韩旖旎', '13588150826', '1', '8', '2017-02-21 20:32:29', '/public/img/f129776e-1b44-4f67-a526-79796c051a38.jpeg', '137988', '3', '2084525335');
INSERT INTO `activity` VALUES ('10', '浙江省', '杭州市', '余杭区', '南大街', '余杭区南大街139号', '120.31353300015603', '30.41707196018103', '青少年服务', '特殊青少年群体', '即日起至4月7号', '30', '帮助特殊群体', '帮助特殊群体', '4月7号', '张风旗', '15355465906', '1', '7', '2017-02-21 20:32:30', '/public/img/8ffa23e8-b49d-447a-807c-3390e87df515.png', '186366', '3', '2084525335');
INSERT INTO `activity` VALUES ('11', '浙江省', '杭州市', '上城区', '庆春路', '上城区长生路太平里3号（衣之家广场屈臣氏对面）', '120.16773569466515', '30.26375483370823', '环境保护', '医院', '即日起至12月31号', '30', '在社区为患者提供导医', '在社区为患者提供导医、咨询以及帮助患者使用新设备等志愿服务', '12月31号', '李东华', '15858218132', '1', '8', '2017-02-21 20:32:31', '/public/img/3f21f0f4-38a4-4a34-a03f-fed31399be53.jpeg', '117867', '3', '2084525335');
INSERT INTO `activity` VALUES ('12', '浙江省', '杭州市', '滨江区', '江陵路', '滨江区江陵路2030号（滨盛路与江陵路交叉口）', '120.2207886978211', '30.218845388295104', '便民服务', '骨伤医院', '即日起至10月25号', '100', '引导、帮助、陪同就医患者挂号、候诊、检查', '热情接待每位患者，引导、帮助、陪同就医患者挂号、候诊、检查、缴费、缴费、治疗等', '10月27号', '刘建立', '13588391208', '1', '8', '2017-02-21 20:32:32', '/public/img/86fc3686-2ab6-4e96-a7d1-121fad176368.jpeg', '130238', '3', '2084525335');
INSERT INTO `activity` VALUES ('13', '浙江省', '杭州市', '萧山区', '市心北路', '萧山区市心北路227号中誉万豪广场（建设三路地铁站B出口）', '120.27367787869653', '30.2118478608464', '便民服务', '浙一医院', '即日起11月1号', '30', '期望长期在我院行志愿服务', '期望长期在我院行志愿服务', '11月1号', '王颖', '0571-87235000', '1', '8', '2017-02-21 20:32:33', '/public/img/c2a36ba3-91dc-442f-a864-06c6162c6ded.jpeg', '197588', '3', '2084525335');
INSERT INTO `activity` VALUES ('14', '浙江省', '杭州市', '西湖区', '西溪路', '西湖区西溪路399号', '120.13418585413956', '30.274306022913304', '平安守护', '公粮应急救援服务中心', '即日起至9月8号', '100', '有爱心，责任心处事认真谨慎', '有爱心，责任心处事认真谨慎，有户外经验者优先考虑', '9月8号', '韩强', '13148305415', '1', '8', '2017-02-21 20:32:33', '/public/img/ddf21b9c-aebf-4c0d-9d4e-64240c257d27.jpeg', '197228', '3', '2084525335');
INSERT INTO `activity` VALUES ('15', '浙江省', '杭州市', '上城区', '开元路', '上城区惠兴路9-2号（解放路与惠兴路交叉口，吴山夜市解放路入口）', '120.1701772926345', '30.2538463715128', '环境保护', '超山景区', '即日起至2月19日止', '20', '户外活动请注意安全', '户外活动请注意安全，请携带小学以上的家长务必照顾负责好孩子的安全。请自带手套以及垃圾袋等工具', '2月19号', '王海英', '15158182190', '1', '3', '2017-02-21 20:32:34', '/public/img/14b91879-b85b-4f97-8901-0a27d060d452.jpeg', '193380', '3', '2084525335');
INSERT INTO `activity` VALUES ('16', '浙江省', '杭州市', '西湖区', '天目山路', '西湖区西溪路550号西溪新座7幢101A', '120.11678488410583', '30.27480017782277', '文化体育', '杭州图书馆环保分馆', '即日起至3月31号', '30', '图书馆管理、图书整理', '图书馆管理、图书整理、借还书处理、活动协助', '3月31号', '胡云华', '13735456540', '1', '7', '2017-02-21 20:32:35', '/public/img/3f2c2e9e-4080-4ad8-b837-0b52d9fa2f24.jpeg', '175219', '3', '2084525335');
INSERT INTO `activity` VALUES ('17', '浙江省', '杭州市', '萧山区', '万达北路', '萧山区万达北路278号阿莲大锅台（郁金香岸对面）', '120.16798603624129', '30.14290101927445', '环境保护', '黑马公益服务中心', '即日起至2月18日止', '200', '对市民不文明现象进行劝导', '对市民不文明现象进行劝导以及清理地面小垃圾', '2月18号', '黑马公益', '13735587115', '1', '7', '2017-02-21 20:32:36', '/public/img/1be30877-9706-4e9f-bb95-4342eb4e9350.jpeg', '195952', '3', '2084525335');
INSERT INTO `activity` VALUES ('18', '浙江省', '杭州市', '临安市', '锦江路', '临安市锦江路87号（中国体育彩票隔壁）', '119.71829300258979', '30.234053770555963', '青少年服务', '儿童', '即日起至9月30号', '100', '对多动症的小朋友进行感觉统合训练', '志愿者对多动症的小朋友进行感觉统合训练，其中有篮球训练，平衡力训练等等', '9月30号', '龙森', '13967171102', '1', '8', '2017-02-21 20:32:37', '/public/img/c5b48bd6-ae6c-4539-aa3e-a3b90d07dae1.jpeg', '154108', '3', '2084525335');
INSERT INTO `activity` VALUES ('19', '浙江省', '杭州市', '萧山区', '建设三路', '萧山区市心北路217号德意中兴广场3楼（近建设三路）', '120.27319289882288', '30.210691907071695', '便民服务', '手语翻译志愿者', '即日起至12月5号', '100', '消除沟通障碍，促进参与共享', '消除沟通障碍，促进参与共享，用爱心点燃希望的火种，用行动传递公益的力量', '12月5号', '茹万龙', '13588033183', '1', '8', '2017-02-21 00:00:00', '/public/img/c0f074bf-201a-4e45-a391-b646f430cdf4.jpg', '151090', '3', '2084525335');
INSERT INTO `activity` VALUES ('20', '浙江省', '杭州市', '滨江区', '商业街', '滨江区高教园商业街4号楼(近华润超市)', '120.16044789028312', '30.1762190302109', '环境保护', '江南红巷AAA级景区', '即日起至5月25号', '100', '我们正在寻找加入称为亲民君的伙伴', '我们正在寻找有兴趣、愿意参与、加入称为亲民君的伙伴', '5月25号', '王主任', '86906731', '1', '8', '2017-02-21 00:00:00', '/public/img/8ba5d5db-5ca1-4ba2-b684-45a96e2b6439.jpg', '107405', '3', '2084525335');
INSERT INTO `activity` VALUES ('25', '浙江省', '杭州市', '江干区', '二号大街', '杭州电子科技大学', '120.10000000000049', '30.27673398', '敬老助残', '老人', '即日起到2月25号', '200', '为老人开展志愿服务', '为老人洗脚', '2月24号', '吴晓明', '13588002512', '1', '8', '2017-02-22 00:00:00', '/public/img/9629ec51-4968-4173-b109-4ac89616350a.jpeg', '785621', '3', '2084525335');
INSERT INTO `activity` VALUES ('26', '浙江省', '杭州市', '江干区', '二号大街', '杭州电子科技大学', '120.10000000000049', '30.27673398', '敬老助残', '老人', '即日起到2月25号', '200', '为老人开展志愿服务', '为老人洗脚', '2月24号', '吴晓明', '13588002512', '1', '8', '2017-02-22 00:00:00', '/public/img/9629ec51-4968-4173-b109-4ac89616350a.jpeg', '785622', '3', '2084525335');
INSERT INTO `activity` VALUES ('27', '浙江省', '杭州市', '江干区', '二号大街', '杭州电子科技大学', '120.10000000000049', '30.27673398', '敬老助残', '老人', '即日起到2月25号', '200', '为老人开展志愿服务', '为老人洗脚', '2月24号', '吴晓明', '13588002512', '1', '8', '2017-02-22 00:00:00', '/public/img/9629ec51-4968-4173-b109-4ac89616350a.jpeg', '660088', '3', '2084525335');
INSERT INTO `activity` VALUES ('28', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-25 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '453419', '3', '2084525335');
INSERT INTO `activity` VALUES ('29', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '525027', '3', '2084525335');
INSERT INTO `activity` VALUES ('30', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '803929', '3', '2084525335');
INSERT INTO `activity` VALUES ('31', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '346480', '3', '2084525335');
INSERT INTO `activity` VALUES ('32', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '339539', '3', '2084525335');
INSERT INTO `activity` VALUES ('33', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '595510', '3', '2084525335');
INSERT INTO `activity` VALUES ('34', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '577113', '3', '2084525335');
INSERT INTO `activity` VALUES ('35', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '117388', '3', '2084525335');
INSERT INTO `activity` VALUES ('36', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '282837', '3', '2084525335');
INSERT INTO `activity` VALUES ('37', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '870487', '3', '2084525335');
INSERT INTO `activity` VALUES ('38', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '50338', '3', '2084525335');
INSERT INTO `activity` VALUES ('39', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '897682', '3', '2084525335');
INSERT INTO `activity` VALUES ('41', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '642521', '3', '2084525335');
INSERT INTO `activity` VALUES ('42', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '110037', '3', '0');
INSERT INTO `activity` VALUES ('43', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-26 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '335588', '3', '2084547479');
INSERT INTO `activity` VALUES ('45', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-27 10:46:28', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '161043', '3', '2084521020');
INSERT INTO `activity` VALUES ('46', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '环保助力中国梦', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-27 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '813957', '3', '2082387294');
INSERT INTO `activity` VALUES ('47', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '测试活动', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-27 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '7646', '3', '2082391566');
INSERT INTO `activity` VALUES ('48', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '测试活动2222', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-27 00:00:00', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '22968', '3', '2085272719');
INSERT INTO `activity` VALUES ('49', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '测试活动3333', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-27 14:42:50', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '328077', '3', '2084777001');
INSERT INTO `activity` VALUES ('51', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '环境保护', '地球', '即日起至3月3日', '20', '测试活动5555', '搞卫生', '3月4日', '吴大明', '13155555555', '1', '5', '2017-02-27 19:26:37', '/public/img/3db4436f-976b-4876-b1bd-af31c80d9e79.jpg', '558262', '3', '2085180457');
INSERT INTO `activity` VALUES ('52', '浙江省', '杭州市', '江干区', '金沙东路', '杭州电子科技大学', '120.35043127267346', '30.319567170311355', '平安守护', '学生', '即日起至3月1日', '1', '消防应急体验活动', '参与消防应急体验活动', '3月1日', '吴大明', '13555555555', '3', '10', '2017-02-28 00:00:00', '/public/img/e5feff96-6d52-46ba-8bce-815e1718a566.jpg', '855529', '4', '2086393047');
INSERT INTO `activity` VALUES ('53', '浙江省', '杭州市', '江干区', '学林街', '杭州电子科技大学五餐门口', '120.35053996764016', '30.322873226171176', '便民服务', '青少年', '即日起至3月4日', '20', '爱心献血', '在3月4号星期六早上8点到12点，杭电五餐门口，会举行无偿献血活动，欢迎同学们的爱心献血', '3月4日', '吴大涛', '13569956369', '1', '4', '2017-02-28 17:38:21', '/public/img/ffb8c086-5b02-4db9-b548-5c610934777a.jpg', '607135', '4', '2086599564');
INSERT INTO `activity` VALUES ('54', '浙江省', '杭州市', '下城区', '武林广场西通道', '杭州大厦', '120.16793018768944', '30.277692909332732', '青少年服务', '少年儿童', '即日起至3月16日', '5', '爱心教学活动', '教孩子们学习音乐', '3月16日', '徐海涛', '13258468512', '1', '5', '2017-03-02 15:52:41', '/public/img/91a8ef46-18e7-43cc-bbc2-c37f3401cc2b.jpg', '282325', '8', '2089605692');

-- ----------------------------
-- Table structure for activity_status
-- ----------------------------
DROP TABLE IF EXISTS `activity_status`;
CREATE TABLE `activity_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_status
-- ----------------------------
INSERT INTO `activity_status` VALUES ('1', '正在招募');
INSERT INTO `activity_status` VALUES ('2', '活动进行');
INSERT INTO `activity_status` VALUES ('3', '活动结束');

-- ----------------------------
-- Table structure for activity_user
-- ----------------------------
DROP TABLE IF EXISTS `activity_user`;
CREATE TABLE `activity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `activity_user_status_id` int(11) NOT NULL COMMENT '面试是否通过',
  `star` double(11,0) NOT NULL,
  `sign_in_status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_user_activity_id` (`activity_id`),
  KEY `activity_user_user_id` (`user_id`),
  KEY `activity_user_activity_user_status_id` (`activity_user_status_id`),
  CONSTRAINT `activity_user_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE,
  CONSTRAINT `activity_user_activity_user_status_id` FOREIGN KEY (`activity_user_status_id`) REFERENCES `activity_user_status` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `activity_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_user
-- ----------------------------
INSERT INTO `activity_user` VALUES ('1', '1', '1', '2', '0', '1');
INSERT INTO `activity_user` VALUES ('2', '1', '2', '2', '0', '1');
INSERT INTO `activity_user` VALUES ('3', '1', '5', '2', '0', '0');
INSERT INTO `activity_user` VALUES ('4', '2', '1', '2', '0', '0');
INSERT INTO `activity_user` VALUES ('5', '2', '2', '2', '0', '1');
INSERT INTO `activity_user` VALUES ('47', '4', '2', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('48', '11', '2', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('52', '43', '2', '2', '0', '0');
INSERT INTO `activity_user` VALUES ('53', '47', '2', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('55', '51', '2', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('56', '49', '2', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('57', '19', '2', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('58', '19', '2', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('59', '52', '6', '2', '0', '1');
INSERT INTO `activity_user` VALUES ('61', '20', '6', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('62', '53', '6', '2', '0', '1');
INSERT INTO `activity_user` VALUES ('64', '54', '7', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('65', '19', '7', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('66', '5', '7', '1', '0', '0');
INSERT INTO `activity_user` VALUES ('67', '4', '7', '1', '0', '0');

-- ----------------------------
-- Table structure for activity_user_status
-- ----------------------------
DROP TABLE IF EXISTS `activity_user_status`;
CREATE TABLE `activity_user_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_user_status
-- ----------------------------
INSERT INTO `activity_user_status` VALUES ('1', '等待面试');
INSERT INTO `activity_user_status` VALUES ('2', '面试通过');
INSERT INTO `activity_user_status` VALUES ('3', '面试失败');

-- ----------------------------
-- Table structure for certificate
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `certificate_status_id` int(11) NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(255) NOT NULL,
  `receiver` varchar(255) NOT NULL,
  `receiver_phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `certificate_certificate_status_id` (`certificate_status_id`),
  CONSTRAINT `certificate_certificate_status_id` FOREIGN KEY (`certificate_status_id`) REFERENCES `certificate_status` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of certificate
-- ----------------------------
INSERT INTO `certificate` VALUES ('4', '1', '1', '2017-02-23 17:42:17', '浙江省杭州市江干区杭州电子科技大学生活区21号楼', '吴涛', '13175011234');
INSERT INTO `certificate` VALUES ('7', '2', '1', '2017-02-25 19:49:10', '杭州电子科技大学', '王小二', '132555555555');
INSERT INTO `certificate` VALUES ('8', '6', '1', '2017-02-28 10:08:39', '杭州电子科技大学', '徐晓明', '13855551111');

-- ----------------------------
-- Table structure for certificate_status
-- ----------------------------
DROP TABLE IF EXISTS `certificate_status`;
CREATE TABLE `certificate_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of certificate_status
-- ----------------------------
INSERT INTO `certificate_status` VALUES ('1', '正在审核');
INSERT INTO `certificate_status` VALUES ('2', '等待邮寄');

-- ----------------------------
-- Table structure for organization_info
-- ----------------------------
DROP TABLE IF EXISTS `organization_info`;
CREATE TABLE `organization_info` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '组织名称',
  `address_province` varchar(255) NOT NULL COMMENT '地址 省',
  `address_city` varchar(255) NOT NULL COMMENT '地址 市',
  `address_district` varchar(255) NOT NULL COMMENT '地址 区',
  `address_street` varchar(255) NOT NULL COMMENT '地址 街道或路',
  `address` varchar(255) NOT NULL COMMENT '地址 详细',
  `zip_code` varchar(6) NOT NULL DEFAULT '000000' COMMENT '邮编',
  `founding_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '成立日期',
  `volunteer_number` int(11) NOT NULL,
  `principal_name` varchar(255) NOT NULL,
  `principal_telephone` varchar(255) NOT NULL,
  `principal_telephone_public` int(255) NOT NULL COMMENT '联系电话是否公开',
  `avatar` varchar(255) NOT NULL DEFAULT '/public/img/default_avatar.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization_info
-- ----------------------------
INSERT INTO `organization_info` VALUES ('3', '富春街道', '浙江省', '杭州市', '富阳区', '富春街道', '浙江省杭州市富春街道', '000000', '2017-02-25 10:59:24', '2', '唐松松', '15988838059', '1', '/public/img/default_avatar.jpg');
INSERT INTO `organization_info` VALUES ('4', '李家坞', '浙江省', '杭州市', '千岛湖街道', '李家坞社区', '浙江省淳安市千岛湖街道', '000000', '2017-02-25 11:00:05', '2', '应淑燕', '64830499', '1', '/public/img/default_avatar.jpg');
INSERT INTO `organization_info` VALUES ('8', '德胜志愿者总队', '浙江省', '杭州市', '下城区', '德胜街道', '浙江省杭州市德胜街道', '000000', '2017-03-02 15:54:46', '2', '徐海涛', '13208293357', '1', '/public/img/default_avatar.jpg');

-- ----------------------------
-- Table structure for organization_volunteer
-- ----------------------------
DROP TABLE IF EXISTS `organization_volunteer`;
CREATE TABLE `organization_volunteer` (
  `id` int(11) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `volunteer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization_volunteer
-- ----------------------------
INSERT INTO `organization_volunteer` VALUES ('1', '3', '1');
INSERT INTO `organization_volunteer` VALUES ('2', '3', '2');
INSERT INTO `organization_volunteer` VALUES ('3', '3', '5');
INSERT INTO `organization_volunteer` VALUES ('4', '4', '6');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'volunteer', 'ROLE_VOL');
INSERT INTO `role` VALUES ('2', 'organization', 'ROLE_ORG');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hello', 'world', 'hello');
INSERT INTO `user` VALUES ('2', 'wutao', 'wutaoshabi', 'wutao');
INSERT INTO `user` VALUES ('3', 'xushaohui', 'xshsb', 'xsh@126.com');
INSERT INTO `user` VALUES ('4', 'wuxiaoming', 'wxmdsb', 'wxmdsb@163.com');
INSERT INTO `user` VALUES ('5', 'wanglixian', 'wlx', 'wlx');
INSERT INTO `user` VALUES ('6', 'tangzezhou', 'tzz', 'tzz');
INSERT INTO `user` VALUES ('7', 'voltest', 'voltest', 'test1@email.com');
INSERT INTO `user` VALUES ('8', 'orgtest', 'orgtest', 'test2@eamil.com');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('0', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '1');
INSERT INTO `user_role` VALUES ('3', '3', '2');
INSERT INTO `user_role` VALUES ('4', '4', '2');
INSERT INTO `user_role` VALUES ('5', '5', '1');
INSERT INTO `user_role` VALUES ('6', '6', '1');
INSERT INTO `user_role` VALUES ('7', '7', '1');
INSERT INTO `user_role` VALUES ('8', '8', '2');

-- ----------------------------
-- Table structure for volunteer_info
-- ----------------------------
DROP TABLE IF EXISTS `volunteer_info`;
CREATE TABLE `volunteer_info` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `id_card` varchar(255) NOT NULL,
  `sex` int(255) NOT NULL DEFAULT '0',
  `age` int(255) NOT NULL COMMENT '年龄',
  `birthday` date NOT NULL COMMENT '出生年月',
  `political_status` varchar(255) NOT NULL DEFAULT '' COMMENT '政治面貌',
  `address` varchar(255) NOT NULL COMMENT '详细地址',
  `address_province` varchar(255) NOT NULL COMMENT '省',
  `address_city` varchar(255) NOT NULL COMMENT '市',
  `working_hours` double NOT NULL COMMENT '志愿者工时',
  `avatar` varchar(255) NOT NULL DEFAULT '/public/img/default_avatar.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of volunteer_info
-- ----------------------------
INSERT INTO `volunteer_info` VALUES ('1', '谢鸿涛', '350581198503129431', '0', '32', '1985-03-12', '群众', '福建省泉州市石狮市', '福建省', '泉州市', '93', '/public/img/default_avatar.jpg');
INSERT INTO `volunteer_info` VALUES ('2', '昌绍辉 ', '51190119730115255X', '0', '44', '1973-01-15', '群众', '四川省巴中市市辖区', '四川省', '巴中市', '97', '/public/img/e3e8f15a-56fd-426f-aaa7-bb9698a6f9c9.jpg');
INSERT INTO `volunteer_info` VALUES ('5', '黄德慧', '421122198407145703', '1', '33', '1984-07-14', '群众', '湖北省黄冈市红安县', '湖北省', '黄冈市', '0', '/public/img/default_avatar.jpg');
INSERT INTO `volunteer_info` VALUES ('6', '于熊博', '140424198203212397', '0', '35', '1982-03-21', '群众', '山西省长治市屯留县', '山西省', '长治市', '65', '/public/img/default_avatar.jpg');
INSERT INTO `volunteer_info` VALUES ('7', '汪薛聪', '360425199607188491', '0', '21', '1996-07-18', '群众', '江西省九江市永修县', '江西省', '九江市', '2', '/public/img/default_avatar.jpg');
SET FOREIGN_KEY_CHECKS=1;
