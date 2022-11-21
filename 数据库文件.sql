prompt PL/SQL Developer import file
prompt Created on 2022年10月27日 by 陈锦房
set feedback off
set define off
prompt Creating G_D_ORDER...
create table G_D_ORDER
(
  OID         VARCHAR2(20) not null,
  USERNAME    VARCHAR2(30),
  TOTALSUM    NUMBER(10,2),
  EXINCOME    NUMBER(10,2),
  TIME        DATE,
  PAYWAY      VARCHAR2(10),
  STATE       VARCHAR2(10),
  EXECUTETIME DATE,
  AUTHOR      VARCHAR2(20),
  DETAIL_ID   VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_ORDER
  add primary key (OID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_O_DETAIL...
create table G_D_O_DETAIL
(
  ID          VARCHAR2(20),
  PNAME       VARCHAR2(50),
  PIMG        VARCHAR2(20),
  PRICE       VARCHAR2(20),
  AVERAGERATE VARCHAR2(20),
  USERNAME    VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_O_PAY...
create table G_D_O_PAY
(
  PID   VARCHAR2(10),
  PNAME VARCHAR2(20),
  PDESC VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_O_STATE...
create table G_D_O_STATE
(
  SID   VARCHAR2(10),
  SNAME VARCHAR2(20),
  PDESC VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_PRODUCT...
create table G_D_PRODUCT
(
  ID           VARCHAR2(20) not null,
  PNAME        VARCHAR2(50),
  PIMG         VARCHAR2(30),
  PTYPE        VARCHAR2(10),
  PRICE        VARCHAR2(30),
  PSTATE       NUMBER(10),
  TOTALNUM     VARCHAR2(30),
  AVERAGERATE  VARCHAR2(10),
  RECENTLYRATE VARCHAR2(10),
  PSIZE        VARCHAR2(20),
  PTIME        DATE,
  PMANAGER     VARCHAR2(20),
  PDESC        VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_PRODUCT
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_P_MANAGER...
create table G_D_P_MANAGER
(
  MID   VARCHAR2(20) not null,
  MNAME VARCHAR2(20),
  MDESC VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_P_MANAGER
  add primary key (MID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_P_RECENTLYRATE...
create table G_D_P_RECENTLYRATE
(
  RID   VARCHAR2(10) not null,
  JAN   VARCHAR2(10),
  MAR   VARCHAR2(10),
  MAY   VARCHAR2(10),
  JULY  VARCHAR2(10),
  SEP   VARCHAR2(10),
  NOV   VARCHAR2(10),
  RNAME VARCHAR2(30)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_P_RECENTLYRATE
  add primary key (RID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_P_STATE...
create table G_D_P_STATE
(
  SID   NUMBER(10) not null,
  SNAME VARCHAR2(20),
  SDESC VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_P_STATE
  add primary key (SID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_P_TYPE...
create table G_D_P_TYPE
(
  TID   VARCHAR2(10) not null,
  TNAME VARCHAR2(30),
  TDESC VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_P_TYPE
  add primary key (TID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_USER...
create table G_D_USER
(
  ID         VARCHAR2(20) not null,
  USERNAME   VARCHAR2(50),
  PASSWORD   VARCHAR2(50),
  IMG        VARCHAR2(20),
  NICKNAME   VARCHAR2(50),
  SEX        VARCHAR2(5),
  CREATETIME DATE,
  ROLE_ID    VARCHAR2(10),
  STATE      NUMBER(10),
  MOBILE     VARCHAR2(50),
  EMAIL      VARCHAR2(50),
  MONEYID    VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_USER
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create unique index USERNAME on G_D_USER (USERNAME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_U_CART...
create table G_D_U_CART
(
  CID         VARCHAR2(20) not null,
  USERNAME    VARCHAR2(50),
  PIMG        VARCHAR2(30),
  PNAME       VARCHAR2(50),
  PRICE       VARCHAR2(30),
  AVERAGERATE VARCHAR2(10),
  PNUM        NUMBER(20),
  SUM         NUMBER(10,2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_U_CART
  add primary key (CID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_U_MONEY...
create table G_D_U_MONEY
(
  MID        VARCHAR2(20) not null,
  TOTALMONEY NUMBER(20,2),
  INCOME     NUMBER(20,2),
  PAY        NUMBER(20,2),
  BALANCE    NUMBER(20,2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_U_MONEY
  add primary key (MID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_U_ROLE...
create table G_D_U_ROLE
(
  RID     VARCHAR2(20) not null,
  RNAME   VARCHAR2(50),
  RDESC   VARCHAR2(50),
  RDATE   DATE,
  RAUTHOR VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_U_ROLE
  add primary key (RID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating G_D_U_STATE...
create table G_D_U_STATE
(
  SID   NUMBER(10) not null,
  SNAME VARCHAR2(30),
  SDESC VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table G_D_U_STATE
  add primary key (SID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for G_D_ORDER...
alter table G_D_ORDER disable all triggers;
prompt Disabling triggers for G_D_O_DETAIL...
alter table G_D_O_DETAIL disable all triggers;
prompt Disabling triggers for G_D_O_PAY...
alter table G_D_O_PAY disable all triggers;
prompt Disabling triggers for G_D_O_STATE...
alter table G_D_O_STATE disable all triggers;
prompt Disabling triggers for G_D_PRODUCT...
alter table G_D_PRODUCT disable all triggers;
prompt Disabling triggers for G_D_P_MANAGER...
alter table G_D_P_MANAGER disable all triggers;
prompt Disabling triggers for G_D_P_RECENTLYRATE...
alter table G_D_P_RECENTLYRATE disable all triggers;
prompt Disabling triggers for G_D_P_STATE...
alter table G_D_P_STATE disable all triggers;
prompt Disabling triggers for G_D_P_TYPE...
alter table G_D_P_TYPE disable all triggers;
prompt Disabling triggers for G_D_USER...
alter table G_D_USER disable all triggers;
prompt Disabling triggers for G_D_U_CART...
alter table G_D_U_CART disable all triggers;
prompt Disabling triggers for G_D_U_MONEY...
alter table G_D_U_MONEY disable all triggers;
prompt Disabling triggers for G_D_U_ROLE...
alter table G_D_U_ROLE disable all triggers;
prompt Disabling triggers for G_D_U_STATE...
alter table G_D_U_STATE disable all triggers;
prompt Deleting G_D_U_STATE...
delete from G_D_U_STATE;
commit;
prompt Deleting G_D_U_ROLE...
delete from G_D_U_ROLE;
commit;
prompt Deleting G_D_U_MONEY...
delete from G_D_U_MONEY;
commit;
prompt Deleting G_D_U_CART...
delete from G_D_U_CART;
commit;
prompt Deleting G_D_USER...
delete from G_D_USER;
commit;
prompt Deleting G_D_P_TYPE...
delete from G_D_P_TYPE;
commit;
prompt Deleting G_D_P_STATE...
delete from G_D_P_STATE;
commit;
prompt Deleting G_D_P_RECENTLYRATE...
delete from G_D_P_RECENTLYRATE;
commit;
prompt Deleting G_D_P_MANAGER...
delete from G_D_P_MANAGER;
commit;
prompt Deleting G_D_PRODUCT...
delete from G_D_PRODUCT;
commit;
prompt Deleting G_D_O_STATE...
delete from G_D_O_STATE;
commit;
prompt Deleting G_D_O_PAY...
delete from G_D_O_PAY;
commit;
prompt Deleting G_D_O_DETAIL...
delete from G_D_O_DETAIL;
commit;
prompt Deleting G_D_ORDER...
delete from G_D_ORDER;
commit;
prompt Loading G_D_ORDER...
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('161', '内马尔', 16578.12, 2693.74, to_date('24-06-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:17', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '578');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('162', '内马尔', 16585.4, 2694.92, to_date('04-09-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:15', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '355');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('165', '德布劳内', 249.6, 30.99, to_date('22-12-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 21:57:51', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '529');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('164', '德布劳内', 1888.6, 234.45, to_date('31-12-2020', 'dd-mm-yyyy'), '2', '3', to_date('03-10-2022 21:54:07', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '30');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('120', '内马尔', 176.4, 28.66, to_date('03-10-2022 11:47:51', 'dd-mm-yyyy hh24:mi:ss'), '1', '0', null, null, null);
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('166', '德布劳内', 249.6, -11.06, to_date('07-03-2022', 'dd-mm-yyyy'), '1', '3', to_date('03-10-2022 22:35:56', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '140');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('118', '内马尔', 215.08, 34.95, to_date('03-10-2022 11:47:32', 'dd-mm-yyyy hh24:mi:ss'), '1', '1', null, null, null);
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('181', '阿扎尔', 4740.51, 788.25, to_date('21-03-2021', 'dd-mm-yyyy'), '0', '2', null, null, '985');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('163', '德布劳内', 1314, 163.12, to_date('25-07-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 21:30:01', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '23');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('146', '内马尔', 5790, 940.8, to_date('23-02-2022', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:11', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '185');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('147', '内马尔', 6027.72, 979.43, to_date('27-03-2020', 'dd-mm-yyyy'), '2', '3', null, null, '733');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('148', '阿扎尔', 840, 139.68, to_date('23-01-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:08', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '33');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('145', '内马尔', 1045, 169.8, to_date('25-12-2020', 'dd-mm-yyyy'), '1', '3', null, null, '913');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('149', '阿扎尔', 203.76, 33.88, to_date('21-11-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:04', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '384');
commit;
prompt 14 records loaded
prompt Loading G_D_O_DETAIL...
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '铂金531', 'gold4.png', '901.8', '-2.02', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '白银T D', 'gold2.png', '43.2', '42.1', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '鸿运周期60天型1号', 'bank2.jpg', '15.3', '1.05', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '金地集团600383', 'shares6.png', '17.5', '11.76', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '铂金531', 'gold4.png', '901.8', '-2.02', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '白银T D', 'gold2.png', '43.2', '42.1', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '鸿运周期60天型1号', 'bank2.jpg', '15.3', '1.05', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '恒生电子（600570）', 'shares5.png', '11.32', '35.80', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('30', '实益达（002137）', 'shares2.png', '33.7', '10.09', '德布劳内');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('30', '20国债07', 'bond2.jpg', '52.3', '8', '德布劳内');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('30', '招商沪港商300', 'fund2.jpg', '66.0', '15', '德布劳内');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('529', '招商中证白酒指数', 'fund6.jpg', '15.6', '-4.43', '德布劳内');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '平安中老年人医疗保险', 'insurance3.jpg', '74.5', '1.32', '阿扎尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '金鹰元祺信用债券', 'bond1.jpg', '20.0', '10', '阿扎尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '中海环保新能源混合aa', 'fund1.jpg', '30.0', '31', '阿扎尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '奥瑞金（002701）', 'shares3.png', '82.31', '5.02', '阿扎尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('23', '招商沪港商300', 'fund2.jpg', '66.0', '15', '德布劳内');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('23', '中海环保新能源混合aa', 'fund1.jpg', '30.0', '31', '德布劳内');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('140', '招商中证白酒指数', 'fund6.jpg', '15.6', '-4.43', '德布劳内');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '恒生电子（600570）', 'shares5.png', '11.32', '35.80', '阿扎尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('185', '13国债18', 'bond3.jpg', '33', '10', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('185', '平安儿童医疗保险', 'insurance2.jpg', '30.0', '30', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('185', '平安E家平安-百万医疗险', 'insurance4.jpg', '214', '1.3', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', '平安儿童医疗保险', 'insurance2.jpg', '30.0', '30', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('913', '工银产业升级101', 'fund3.jpg', '55', '-30', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', '13国债18', 'bond3.jpg', '33', '10', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', '平安E家平安-百万医疗险', 'insurance4.jpg', '214', '1.3', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', '恒生电子（600570）', 'shares5.png', '11.32', '35.80', '内马尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('33', '金鹰元祺信用债券', 'bond1.jpg', '20.0', '10', '阿扎尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('33', '中海环保新能源混合aa', 'fund1.jpg', '30.0', '31', '阿扎尔');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('384', '恒生电子（600570）', 'shares5.png', '11.32', '35.80', '阿扎尔');
commit;
prompt 31 records loaded
prompt Loading G_D_O_PAY...
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('0', '支付宝', '最常用，使用方便');
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('1', '微信', '方便支付快');
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('2', '银行卡', '建议大额支付使用');
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('3', '蚂蚁花呗', '提前消费方式');
commit;
prompt 4 records loaded
prompt Loading G_D_O_STATE...
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('0', '等待中', '插入订单表中的默认状态');
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('1', '已取消', '用户点击支付旁边的取消订单按钮，也会向订单表中添加数据但是下单时间为null,类型为0');
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('2', '已支付', '用户点击支付，然后成功支付，但是后台管理员还未核实计算收益的状态');
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('3', '已完成', '用户支付完，且管理员处理该条订单，成功计算完收益返回给用户余额和收益');
commit;
prompt 4 records loaded
prompt Loading G_D_PRODUCT...
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('47', '奥瑞金（002701）', 'shares3.png', '2', '82.31', 1, '35000', '5.02', '59', '3千万', to_date('16-09-2022 16:21:39', 'dd-mm-yyyy hh24:mi:ss'), '6', '小单中单资金流入');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('49', '恒生电子（600570）', 'shares5.png', '2', '11.32', 1, '15000', '35.80', '63', '645.8亿', to_date('16-09-2022 16:25:16', 'dd-mm-yyyy hh24:mi:ss'), '1', '中国市场Top50');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('64', '平安中老年人医疗保险', 'insurance3.jpg', '5', '74.5', 1, '56321', '1.32', '93', '1千万', to_date('17-09-2022 11:01:33', 'dd-mm-yyyy hh24:mi:ss'), '4', '老年人意外高发场景覆盖广保障实用，性价比高');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('68', '纸黄金006', 'gold3.png', '6', '385.6', 1, '12353', '36.7', '101', '13253', to_date('17-09-2022 11:13:34', 'dd-mm-yyyy hh24:mi:ss'), '6', '行情相对平稳');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('69', '铂金531', 'gold4.png', '6', '901.8', 1, '2362', '-2.02', '103', '123万', to_date('17-09-2022 11:15:01', 'dd-mm-yyyy hh24:mi:ss'), '3', '开始回撤下降，可能未来有上升趋势');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('1', '中海环保新能源混合aa', 'fund1.jpg', '1', '30.0', 1, '200', '31', '1', '30亿', to_date('19-04-1987', 'dd-mm-yyyy'), '1', '这是一只新能源基金');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('2', '金鹰元祺信用债券', 'bond1.jpg', '3', '20.0', 1, '200', '10', '2', '50亿', to_date('14-04-2001', 'dd-mm-yyyy'), '2', '是一只债券');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('4', '邮银财富-军人专享60', 'bank1.jpg', '4', '30', 1, '500', '30', '4', '10亿', to_date('19-05-2010', 'dd-mm-yyyy'), '4', '这是银行储蓄啊，收益相对稳定');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('5', '平安儿童医疗保险', 'insurance2.jpg', '5', '30.0', 1, '200', '30', '5', '500亿', to_date('14-07-2021', 'dd-mm-yyyy'), '5', '是一只保险');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('45', '盛德鑫泰（300881）', 'shares1.png', '2', '66.7', 1, '50000', '10.69', '55', '10亿', to_date('16-09-2022 15:44:50', 'dd-mm-yyyy hh24:mi:ss'), '6', '强力股');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('51', '金地集团600383', 'shares6.png', '2', '17.5', 1, '35666', '11.76', '67', '102.22万元', to_date('16-09-2022 22:44:40', 'dd-mm-yyyy hh24:mi:ss'), '4', '集团股票');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('52', '交银趋势混合A', 'fund5.jpg', '1', '15.6', 1, '335156', '6.83', '69', '1000万', to_date('16-09-2022 22:49:22', 'dd-mm-yyyy hh24:mi:ss'), '3', '混合基金');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('54', '国泰大宗商品', 'fund7.jpg', '1', '15.6', 1, '32153', '42.09', '73', '30亿', to_date('16-09-2022 22:55:16', 'dd-mm-yyyy hh24:mi:ss'), '4', '商品基金');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('55', '长盛安逸纯债基金', 'fund8.jpg', '1', '56.3', 1, '55321', '6.79', '75', '62亿', to_date('16-09-2022 22:57:24', 'dd-mm-yyyy hh24:mi:ss'), '5', '纯债基金');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('56', '长盛安逸纯债基金C', 'fund9.jpg', '1', '56.3', 1, '12345', '12.6', '77', '62万元', to_date('16-09-2022 22:59:18', 'dd-mm-yyyy hh24:mi:ss'), '4', '纯债基金');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('21', '招商沪港商300', 'fund2.jpg', '1', '66.0', 1, '600', '15', '27', '600亿', to_date('15-09-2022 16:40:16', 'dd-mm-yyyy hh24:mi:ss'), '1', '消费性指数增长基金');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('22', '工银产业升级101', 'fund3.jpg', '1', '55', 0, '600', '-30', '29', '10亿', to_date('15-09-2022 16:43:07', 'dd-mm-yyyy hh24:mi:ss'), '5', '医药类型基金');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('41', '13国债18', 'bond3.jpg', '3', '33', 1, '3000', '10', '47', '30亿', to_date('16-09-2022 10:41:59', 'dd-mm-yyyy hh24:mi:ss'), '6', '稳健型偏债债券');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('42', '20复星03', 'bond4.jpg', '3', '52.0', 1, '15500', '11', '49', '10亿', to_date('16-09-2022 10:43:21', 'dd-mm-yyyy hh24:mi:ss'), '4', '短债型稳健债券');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('43', '20国债07', 'bond2.jpg', '3', '52.3', 1, '8888', '8', '51', '5千万', to_date('16-09-2022 10:45:53', 'dd-mm-yyyy hh24:mi:ss'), '3', '规模较小的债券');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('44', '黄金T+D', 'gold1.png', '6', '12.6', 1, '6226', '30', '53', '5亿', to_date('16-09-2022 10:47:44', 'dd-mm-yyyy hh24:mi:ss'), '2', '南方泰安混合旗下一只黄金理财');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('50', '国泰国证房地产行业指数C', 'fund4.jpg', '1', '65.1', 0, '56333', '15.79', '65', '2.92亿', to_date('16-09-2022 16:56:31', 'dd-mm-yyyy hh24:mi:ss'), '4', '指数型股票基金重仓房地产行业');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('61', '鸿运周期60天型1号', 'bank2.jpg', '4', '15.3', 1, '50000', '1.05', '87', '5百万', to_date('17-09-2022 10:48:18', 'dd-mm-yyyy hh24:mi:ss'), '3', '小型银行储蓄');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('62', '鸿景半年定开4号', 'bank3.jpg', '4', '15.3', 1, '51320', '1.03', '89', '6百万', to_date('17-09-2022 10:50:09', 'dd-mm-yyyy hh24:mi:ss'), '3', '小型定期银行储蓄');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('63', '鸿景周期30天型3号财富', 'bank4.jpg', '4', '15.3', 1, '23156', '1.01', '91', '4百万', to_date('17-09-2022 10:52:02', 'dd-mm-yyyy hh24:mi:ss'), '3', '定期储存银行理财');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('48', '中产地交（000736）', 'shares4.png', '2', '103.53', 1, '33333', '17.11', '61', '17.69', to_date('16-09-2022 16:23:12', 'dd-mm-yyyy hh24:mi:ss'), '5', '超大单资金流入较多');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('46', '实益达（002137）', 'shares2.png', '2', '33.7', 1, '50000', '10.09', '57', '50亿', to_date('16-09-2022 16:19:49', 'dd-mm-yyyy hh24:mi:ss'), '2', '主力资金流入该股票');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('53', '招商中证白酒指数', 'fund6.jpg', '1', '15.6', 0, '50000', '-4.43', '71', '100亿', to_date('16-09-2022 22:52:20', 'dd-mm-yyyy hh24:mi:ss'), '1', '年度Top10');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('65', '平安E家平安-百万医疗险', 'insurance4.jpg', '5', '214', 1, '123523', '1.3', '95', '1百万', to_date('17-09-2022 11:04:46', 'dd-mm-yyyy hh24:mi:ss'), '6', '全家共享免赔额，重疾0免赔抗癌特药覆盖，新药持续更新');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('66', '平安e生平安-孝心保', 'insurance5.jpg', '5', '1513', 1, '3600', '2.0', '97', '3千万', to_date('17-09-2022 11:07:27', 'dd-mm-yyyy hh24:mi:ss'), '6', '老人专享，升级可享百万医疗保障，最高600万额度，报销不限社保内外');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('67', '白银T+D', 'gold2.png', '6', '43.2', 1, '15623', '42.1', '99', '1亿', to_date('17-09-2022 11:11:04', 'dd-mm-yyyy hh24:mi:ss'), '4', '经历低谷回撤，如今开始有明显上升趋势');
commit;
prompt 31 records loaded
prompt Loading G_D_P_MANAGER...
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('1', '张坤', '很牛逼');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('2', '李四', '还行吧');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('3', '王五', '欧克欧克');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('4', '刘六', '就这？');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('5', '嗨七', '巴拉巴拉');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('6', '巴巴', '呃呃呃');
commit;
prompt 6 records loaded
prompt Loading G_D_P_RECENTLYRATE...
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('59', '16.2', '32.1', '36.1', '28.6', '33.4', '38.2', '奥瑞金（002701）');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('63', '15.6', '36.4', '62.3', '92.3', '52.1', '14.6', '恒生电子（600570）');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('93', '0.3', '0.6', '0.8', '0.9', '1.0', '1.6', '平安中老年人医疗保险');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('101', '12.3', '10.6', '15.9', '14.3', '10.8', '9.8', '纸黄金006');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('103', '-1.0', '-1.3', '-2.3', '1.0', '1.3', '-0.9', '铂金531');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('1', '10', '30', '40', '20', '60', '50', '中海环保新能源混合aa');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('2', '10', '20', '30', '15', '25', '20', '金鹰元祺信用债券');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('3', '30', '20', '30', '15', '25', '20', '金鹰元祺信用股票1');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('4', '10', '20', '30', '15', '55', '60', '广发中证银行存蓄');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('5', '20', '20', '30', '15', '45', '20', '新开源保险A');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('29', '15', '30', '21', '44', '50', '20', '汇添富创新医药主题混合');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('55', '14.6', '36.1', '36.5', '32.3', '34.3', '35.9', '盛德鑫泰（300881）');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('67', '17.3', '16.2', '19.6', '18.6', '19.6', '21.3', '金地集团600383');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('69', '15.6', '15.7', '32.6', '15.6', '41.6', '11.6', '交银趋势混合A');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('73', '15.6', '33.2', '54.6', '45.3', '21.6', '66.2', '国泰大宗商品');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('75', '15.6', '16.6', '41.6', '2.3', '16.6', '3.2', '长盛安逸纯债基金');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('77', '15.6', '32.6', '12.6', '15.9', '14.3', '13.5', '长盛安逸纯债基金C');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('47', '15', '30', '11', '50', '41', '36', '天弘弘丰增强回报债券');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('27', '10', '20', '30', '40', '60', '70', '易方达蓝筹');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('49', '36', '11', '52', '49', '33', '10', '财付通资管鸿富短债债券C');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('51', '14', '23', '16', '15.5', '36.1', '14.6', '中融盈泽中端债券A');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('53', '11.2', '33.6', '21.2', '45.2', '33.6', '35.1', '南方泰安混合A');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('65', '17.6', '23.6', '15.9', '14.6', '32.4', '15.6', '国泰国证房地产行业指数C');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('87', '0.2', '0.6', '1.3', '2.3', '-0.3', '1.5', '鸿运周期60天型1号');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('89', '1.3', '0.5', '0.8', '0.3', '0.7', '1.0', '鸿景半年定开4号');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('91', '1.3', '1.2', '0.8', '1.3', '1.3', '1.2', '鸿景周期30天型3号财富');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('71', '15.6', '-10.6', '-5.6', '-4.5', '-6.5', '-1.3', '招商中证白酒指数');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('57', '14.3', '36.5', '23.1', '56.4', '23.5', '56.9', '实益达（002137）');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('61', '14.6', '36.5', '35.9', '31.2', '36.8', '74.3', '中产地交（000736）');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('95', '1.0', '1.3', '1.2', '0.6', '0.4', '0.5', '平安E家平安-百万医疗险');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('97', '1.0', '0.8', '0.7', '0.6', '0.2', '1.0', '平安e生平安-孝心保');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('99', '41.0', '45.6', '35.6', '38.9', '46.3', '50.7', '白银T+D');
commit;
prompt 32 records loaded
prompt Loading G_D_P_STATE...
insert into G_D_P_STATE (SID, SNAME, SDESC)
values (1, '正常', '当用户购买份额超过产品总份额时，产品状态为停用');
insert into G_D_P_STATE (SID, SNAME, SDESC)
values (0, '停用', '当用户购买份额超过产品总份额时，产品状态为停用');
commit;
prompt 2 records loaded
prompt Loading G_D_P_TYPE...
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('1', '基金', '基金，英文是fund，广义是指为了某种目的而设立的具有一定数量的资金。主要包括信托投资基金、公积金、保险基金、退休基金，各种基金会的基金。' || chr(10) || '从会计角度透析，基金是一个狭义的概念，意指具有特定目的和用途的资金。我们提到的基金主要是指证券投资基金。');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('2', '股票', '股票（stock）是股份公司所有权的一部分，也是发行的所有权凭证，是股份公司为筹集资金而发行给各个股东作为持股凭证并借以取得股息和红利的一种有价证券。股票是资本市场的长期信用工具，可以转让，买卖，股东凭借它可以分享公司的利润。');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('3', '债券', '债券是一种金融契约，是政府、金融机构、工商企业等直接向社会借债筹借资金时，向投资者发行同时承诺按一定利率支付利息并按约定条件偿还本金的债权债务凭证。债券的本质是债的证明书，具有法律效力债券购买者或投资者与发行者之间是一种债权债务关系 。');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('4', '银行储蓄', '银行储蓄是指每个人或家庭，把节约的钱存到银行的经济活动。城乡居民将暂时不用或结余的货币收入存入银行或其他金融机构的一种存款活动。又称储蓄存款。储蓄存款是信用机构的一项重要资金来源。');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('5', '保险', '保险，是指投保人根据合同约定，向保险人支付保险费保险人对于合同约定的可能发生的事故因其发生所造成的财产损失承担赔偿保险金责任或者被保险人死亡伤残疾病或者达到合同约定的年龄承担给付保险金责任的商业保险行为。从经济角度看保险是分摊意外事故损失的一种财务安排；');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('6', '黄金', '黄金理财是指通过对黄金价格走势的预判，通过低价买入高价卖出赚取中间差价的一种理财方式，黄金理财包括纸黄金和现货黄金、实物黄金投资者可以通过各大正规黄金交易平台进行投资，' || chr(10) || '投资人在黄金理财时，一定要根据自己的实际情况选择产品，毕竟是一种投资，存在风险。');
commit;
prompt 6 records loaded
prompt Loading G_D_USER...
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('34', '内马尔', '123', '2.jpg', '内少', '男', to_date('08-09-2022 21:24:08', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '12345678123', '123@qq.com', '34');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('35', '德布劳内', '123', '7.jpg', '丁丁', '男', to_date('08-09-2022 21:48:46', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '19816280861', '123@qq.com', '35');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('36', '阿扎尔', '123', '0.jpg', '阿扎尔球王', '男', to_date('08-09-2022 21:53:50', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '12345678232', '123456@qq.com', '42');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('74', 'niubi', '123', '0.jpg', '牛逼啊', '男', to_date('13-09-2022 20:41:50', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '19816280864', '5320@qq.com', '75');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('1', 'cjf', '123', '1.jpg', '我房帅呆呆', '男', to_date('06-09-2022 21:22:13', 'dd-mm-yyyy hh24:mi:ss'), '3', 0, '19816280861', '53206@qq.com', '1');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('2', 'admin', '123', '0.jpg', 'admin', '男', to_date('06-09-2022 21:25:42', 'dd-mm-yyyy hh24:mi:ss'), '2', 1, '12345678123', '532055876@qq.com', '2');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('72', 'boss', '123', '0.jpg', '目前最大boss', '男', to_date('13-09-2022 10:36:57', 'dd-mm-yyyy hh24:mi:ss'), '4', 1, '15689412654', '5320@qq.com', '72');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('76', 'agai', '123', '9.jpg', '阿盖', '男', to_date('13-09-2022 21:36:15', 'dd-mm-yyyy hh24:mi:ss'), '0', 0, '19524682163', '532055876@qq.com', '78');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('77', 'aba', '123', '1.jpg', null, null, to_date('13-09-2022 21:38:53', 'dd-mm-yyyy hh24:mi:ss'), '0', 0, null, null, '80');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('7', '琪琪', '1234', '3.jpg', '小七AA', '男', to_date('06-09-2022 21:27:00', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '18257788920', '123@qq.com', '7');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('10', '啦啦', '1234', '3.jpg', '小七DD', '男', to_date('06-09-2022 21:27:00', 'dd-mm-yyyy hh24:mi:ss'), '0', 0, '18257788920', '2986045492@qq.com', '10');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('11', 'pc', '12345', '5.jpg', '小潘', '男', to_date('06-09-2022 21:27:24', 'dd-mm-yyyy hh24:mi:ss'), '2', 1, '19816280861', '532055876@qq.com', '11');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('13', 'wsc', '1234', null, null, '男', to_date('07-09-2022 17:00:32', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, null, null, '12');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('37', '贝尔', '123', '1.jpg', '大圣a', '男', to_date('08-09-2022 22:46:52', 'dd-mm-yyyy hh24:mi:ss'), '1', 0, '12345689122', '123@qq.com', '44');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('75', '阿gai', '123', '1.jpg', null, null, to_date('13-09-2022 21:28:01', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, null, null, '76');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('101', '内马尔1', '123', '0.jpg', null, '男', to_date('22-10-2022 13:57:54', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, null, '532055876@qq.com', '110');
commit;
prompt 16 records loaded
prompt Loading G_D_U_CART...
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('109', '内马尔', 'insurance2.jpg', '平安儿童医疗保险', '30.0', '30', 22, 660);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('111', '内马尔', 'bond3.jpg', '13国债18', '33', '10', 25, 825);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('38', '阿扎尔', 'insurance3.jpg', '平安中老年人医疗保险', '74.5', '1.32', 22, 1639);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('143', '德布劳内', 'shares2.png', '实益达（002137）', '33.7', '10.09', 10, 337);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('112', '内马尔', 'shares5.png', '恒生电子（600570）', '11.32', '35.80', 16, 181.12);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('146', '德布劳内', 'bond2.jpg', '20国债07', '52.3', '8', 12, 627.6);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('161', '内马尔', 'shares3.png', '奥瑞金（002701）', '82.31', '5.02', 18, 1481.58);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('113', '内马尔', 'insurance4.jpg', '平安E家平安-百万医疗险', '214', '1.3', 21, 4494);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('114', '内马尔', 'gold4.png', '铂金531', '901.8', '-2.02', 15, 13527);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('115', '内马尔', 'gold2.png', '白银T+D', '43.2', '42.1', 17, 734.4);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('116', '内马尔', 'bank2.jpg', '鸿运周期60天型1号', '15.3', '1.05', 18, 275.4);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('117', '内马尔', 'shares6.png', '金地集团600383', '17.5', '11.76', 14, 245);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('118', '阿扎尔', 'bond1.jpg', '金鹰元祺信用债券', '20.0', '10', 18, 360);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('119', '阿扎尔', 'fund1.jpg', '中海环保新能源混合aa', '30.0', '31', 16, 480);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('120', '阿扎尔', 'shares3.png', '奥瑞金（002701）', '82.31', '5.02', 25, 2057.75);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('121', '阿扎尔', 'shares5.png', '恒生电子（600570）', '11.32', '35.80', 18, 203.76);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('144', '德布劳内', 'fund2.jpg', '招商沪港商300', '66.0', '15', 14, 924);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('145', '德布劳内', 'fund1.jpg', '中海环保新能源混合aa', '30.0', '31', 13, 390);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('147', '德布劳内', 'gold4.png', '铂金531', '901.8', '-2.02', 12, 10821.6);
commit;
prompt 19 records loaded
prompt Loading G_D_U_MONEY...
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('35', 21709.82, 1709.82, 14633.37, 10919.69);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('42', 10455.98, 455.98, 2498.26, 8413.7);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('94', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('78', 10000, 336.4, 1563.6, 8234.3);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('80', 10000, 456.1, 633.5, 9364.8);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('96', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('92', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('34', 120089.49, 9089.49, 66111.42, 63067.56);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('98', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('112', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('114', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('7', 13000, 3000, 2000, 14000);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('1', 10000, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('2', 10000, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('100', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('5', 10000, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('102', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('8', 10000, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('104', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('10', 10000, 655.3, 1366.5, 8624.6);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('11', 10000, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('44', 10000, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('106', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('108', null, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('76', 10000, 5648.3, 8935.6, 6523.6);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('12', 10000, 1236.6, 3654.3, 7895.3);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('72', 10000, null, null, null);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('75', 10000, 1235.6, 3654.3, 7896.3);
insert into G_D_U_MONEY (MID, TOTALMONEY, INCOME, PAY, BALANCE)
values ('110', null, null, null, null);
commit;
prompt 29 records loaded
prompt Loading G_D_U_ROLE...
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('0', '普通用户', '权限最低', to_date('11-09-2022 00:29:20', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('2', '管理员', '权限也就那样吧', to_date('13-09-2022 16:01:45', 'dd-mm-yyyy hh24:mi:ss'), 'boss');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('3', '经理', '权限能力不错了好嘛！', to_date('10-09-2022 22:52:22', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('4', 'boss', '权限最高', to_date('11-09-2022 00:25:28', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('1', '清洁阿姨', '权限也就那样吧比用户高一点', to_date('10-09-2022 22:50:13', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('7', '大大大Boss', '拥有超级权限', to_date('11-09-2022 00:41:42', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
commit;
prompt 6 records loaded
prompt Loading G_D_U_STATE...
insert into G_D_U_STATE (SID, SNAME, SDESC)
values (1, '正常', '1代表正常，0代表停用');
insert into G_D_U_STATE (SID, SNAME, SDESC)
values (0, '停用', '1代表正常，0代表停用');
commit;
prompt 2 records loaded
prompt Enabling triggers for G_D_ORDER...
alter table G_D_ORDER enable all triggers;
prompt Enabling triggers for G_D_O_DETAIL...
alter table G_D_O_DETAIL enable all triggers;
prompt Enabling triggers for G_D_O_PAY...
alter table G_D_O_PAY enable all triggers;
prompt Enabling triggers for G_D_O_STATE...
alter table G_D_O_STATE enable all triggers;
prompt Enabling triggers for G_D_PRODUCT...
alter table G_D_PRODUCT enable all triggers;
prompt Enabling triggers for G_D_P_MANAGER...
alter table G_D_P_MANAGER enable all triggers;
prompt Enabling triggers for G_D_P_RECENTLYRATE...
alter table G_D_P_RECENTLYRATE enable all triggers;
prompt Enabling triggers for G_D_P_STATE...
alter table G_D_P_STATE enable all triggers;
prompt Enabling triggers for G_D_P_TYPE...
alter table G_D_P_TYPE enable all triggers;
prompt Enabling triggers for G_D_USER...
alter table G_D_USER enable all triggers;
prompt Enabling triggers for G_D_U_CART...
alter table G_D_U_CART enable all triggers;
prompt Enabling triggers for G_D_U_MONEY...
alter table G_D_U_MONEY enable all triggers;
prompt Enabling triggers for G_D_U_ROLE...
alter table G_D_U_ROLE enable all triggers;
prompt Enabling triggers for G_D_U_STATE...
alter table G_D_U_STATE enable all triggers;
set feedback on
set define on
prompt Done.
