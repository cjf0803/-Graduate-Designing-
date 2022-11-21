prompt PL/SQL Developer import file
prompt Created on 2022��10��27�� by �½���
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
values ('161', '�����', 16578.12, 2693.74, to_date('24-06-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:17', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '578');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('162', '�����', 16585.4, 2694.92, to_date('04-09-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:15', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '355');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('165', '�²�����', 249.6, 30.99, to_date('22-12-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 21:57:51', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '529');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('164', '�²�����', 1888.6, 234.45, to_date('31-12-2020', 'dd-mm-yyyy'), '2', '3', to_date('03-10-2022 21:54:07', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '30');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('120', '�����', 176.4, 28.66, to_date('03-10-2022 11:47:51', 'dd-mm-yyyy hh24:mi:ss'), '1', '0', null, null, null);
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('166', '�²�����', 249.6, -11.06, to_date('07-03-2022', 'dd-mm-yyyy'), '1', '3', to_date('03-10-2022 22:35:56', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '140');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('118', '�����', 215.08, 34.95, to_date('03-10-2022 11:47:32', 'dd-mm-yyyy hh24:mi:ss'), '1', '1', null, null, null);
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('181', '������', 4740.51, 788.25, to_date('21-03-2021', 'dd-mm-yyyy'), '0', '2', null, null, '985');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('163', '�²�����', 1314, 163.12, to_date('25-07-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 21:30:01', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '23');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('146', '�����', 5790, 940.8, to_date('23-02-2022', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:11', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '185');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('147', '�����', 6027.72, 979.43, to_date('27-03-2020', 'dd-mm-yyyy'), '2', '3', null, null, '733');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('148', '������', 840, 139.68, to_date('23-01-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:08', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '33');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('145', '�����', 1045, 169.8, to_date('25-12-2020', 'dd-mm-yyyy'), '1', '3', null, null, '913');
insert into G_D_ORDER (OID, USERNAME, TOTALSUM, EXINCOME, TIME, PAYWAY, STATE, EXECUTETIME, AUTHOR, DETAIL_ID)
values ('149', '������', 203.76, 33.88, to_date('21-11-2021', 'dd-mm-yyyy'), '0', '3', to_date('03-10-2022 20:47:04', 'dd-mm-yyyy hh24:mi:ss'), 'boss', '384');
commit;
prompt 14 records loaded
prompt Loading G_D_O_DETAIL...
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '����531', 'gold4.png', '901.8', '-2.02', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '����T D', 'gold2.png', '43.2', '42.1', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '��������60����1��', 'bank2.jpg', '15.3', '1.05', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('355', '��ؼ���600383', 'shares6.png', '17.5', '11.76', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '����531', 'gold4.png', '901.8', '-2.02', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '����T D', 'gold2.png', '43.2', '42.1', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '��������60����1��', 'bank2.jpg', '15.3', '1.05', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('578', '�������ӣ�600570��', 'shares5.png', '11.32', '35.80', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('30', 'ʵ��002137��', 'shares2.png', '33.7', '10.09', '�²�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('30', '20��ծ07', 'bond2.jpg', '52.3', '8', '�²�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('30', '���̻�����300', 'fund2.jpg', '66.0', '15', '�²�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('529', '������֤�׾�ָ��', 'fund6.jpg', '15.6', '-4.43', '�²�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', 'ƽ����������ҽ�Ʊ���', 'insurance3.jpg', '74.5', '1.32', '������');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '��ӥԪ������ծȯ', 'bond1.jpg', '20.0', '10', '������');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '�к���������Դ���aa', 'fund1.jpg', '30.0', '31', '������');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '�����002701��', 'shares3.png', '82.31', '5.02', '������');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('23', '���̻�����300', 'fund2.jpg', '66.0', '15', '�²�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('23', '�к���������Դ���aa', 'fund1.jpg', '30.0', '31', '�²�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('140', '������֤�׾�ָ��', 'fund6.jpg', '15.6', '-4.43', '�²�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('985', '�������ӣ�600570��', 'shares5.png', '11.32', '35.80', '������');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('185', '13��ծ18', 'bond3.jpg', '33', '10', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('185', 'ƽ����ͯҽ�Ʊ���', 'insurance2.jpg', '30.0', '30', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('185', 'ƽ��E��ƽ��-����ҽ����', 'insurance4.jpg', '214', '1.3', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', 'ƽ����ͯҽ�Ʊ���', 'insurance2.jpg', '30.0', '30', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('913', '������ҵ����101', 'fund3.jpg', '55', '-30', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', '13��ծ18', 'bond3.jpg', '33', '10', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', 'ƽ��E��ƽ��-����ҽ����', 'insurance4.jpg', '214', '1.3', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('733', '�������ӣ�600570��', 'shares5.png', '11.32', '35.80', '�����');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('33', '��ӥԪ������ծȯ', 'bond1.jpg', '20.0', '10', '������');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('33', '�к���������Դ���aa', 'fund1.jpg', '30.0', '31', '������');
insert into G_D_O_DETAIL (ID, PNAME, PIMG, PRICE, AVERAGERATE, USERNAME)
values ('384', '�������ӣ�600570��', 'shares5.png', '11.32', '35.80', '������');
commit;
prompt 31 records loaded
prompt Loading G_D_O_PAY...
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('0', '֧����', '��ã�ʹ�÷���');
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('1', '΢��', '����֧����');
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('2', '���п�', '������֧��ʹ��');
insert into G_D_O_PAY (PID, PNAME, PDESC)
values ('3', '���ϻ���', '��ǰ���ѷ�ʽ');
commit;
prompt 4 records loaded
prompt Loading G_D_O_STATE...
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('0', '�ȴ���', '���붩�����е�Ĭ��״̬');
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('1', '��ȡ��', '�û����֧���Աߵ�ȡ��������ť��Ҳ���򶩵�����������ݵ����µ�ʱ��Ϊnull,����Ϊ0');
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('2', '��֧��', '�û����֧����Ȼ��ɹ�֧�������Ǻ�̨����Ա��δ��ʵ���������״̬');
insert into G_D_O_STATE (SID, SNAME, PDESC)
values ('3', '�����', '�û�֧���꣬�ҹ���Ա��������������ɹ����������淵�ظ��û���������');
commit;
prompt 4 records loaded
prompt Loading G_D_PRODUCT...
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('47', '�����002701��', 'shares3.png', '2', '82.31', 1, '35000', '5.02', '59', '3ǧ��', to_date('16-09-2022 16:21:39', 'dd-mm-yyyy hh24:mi:ss'), '6', 'С���е��ʽ�����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('49', '�������ӣ�600570��', 'shares5.png', '2', '11.32', 1, '15000', '35.80', '63', '645.8��', to_date('16-09-2022 16:25:16', 'dd-mm-yyyy hh24:mi:ss'), '1', '�й��г�Top50');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('64', 'ƽ����������ҽ�Ʊ���', 'insurance3.jpg', '5', '74.5', 1, '56321', '1.32', '93', '1ǧ��', to_date('17-09-2022 11:01:33', 'dd-mm-yyyy hh24:mi:ss'), '4', '����������߷��������ǹ㱣��ʵ�ã��Լ۱ȸ�');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('68', 'ֽ�ƽ�006', 'gold3.png', '6', '385.6', 1, '12353', '36.7', '101', '13253', to_date('17-09-2022 11:13:34', 'dd-mm-yyyy hh24:mi:ss'), '6', '�������ƽ��');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('69', '����531', 'gold4.png', '6', '901.8', 1, '2362', '-2.02', '103', '123��', to_date('17-09-2022 11:15:01', 'dd-mm-yyyy hh24:mi:ss'), '3', '��ʼ�س��½�������δ������������');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('1', '�к���������Դ���aa', 'fund1.jpg', '1', '30.0', 1, '200', '31', '1', '30��', to_date('19-04-1987', 'dd-mm-yyyy'), '1', '����һֻ����Դ����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('2', '��ӥԪ������ծȯ', 'bond1.jpg', '3', '20.0', 1, '200', '10', '2', '50��', to_date('14-04-2001', 'dd-mm-yyyy'), '2', '��һֻծȯ');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('4', '�����Ƹ�-����ר��60', 'bank1.jpg', '4', '30', 1, '500', '30', '4', '10��', to_date('19-05-2010', 'dd-mm-yyyy'), '4', '�������д������������ȶ�');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('5', 'ƽ����ͯҽ�Ʊ���', 'insurance2.jpg', '5', '30.0', 1, '200', '30', '5', '500��', to_date('14-07-2021', 'dd-mm-yyyy'), '5', '��һֻ����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('45', 'ʢ����̩��300881��', 'shares1.png', '2', '66.7', 1, '50000', '10.69', '55', '10��', to_date('16-09-2022 15:44:50', 'dd-mm-yyyy hh24:mi:ss'), '6', 'ǿ����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('51', '��ؼ���600383', 'shares6.png', '2', '17.5', 1, '35666', '11.76', '67', '102.22��Ԫ', to_date('16-09-2022 22:44:40', 'dd-mm-yyyy hh24:mi:ss'), '4', '���Ź�Ʊ');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('52', '�������ƻ��A', 'fund5.jpg', '1', '15.6', 1, '335156', '6.83', '69', '1000��', to_date('16-09-2022 22:49:22', 'dd-mm-yyyy hh24:mi:ss'), '3', '��ϻ���');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('54', '��̩������Ʒ', 'fund7.jpg', '1', '15.6', 1, '32153', '42.09', '73', '30��', to_date('16-09-2022 22:55:16', 'dd-mm-yyyy hh24:mi:ss'), '4', '��Ʒ����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('55', '��ʢ���ݴ�ծ����', 'fund8.jpg', '1', '56.3', 1, '55321', '6.79', '75', '62��', to_date('16-09-2022 22:57:24', 'dd-mm-yyyy hh24:mi:ss'), '5', '��ծ����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('56', '��ʢ���ݴ�ծ����C', 'fund9.jpg', '1', '56.3', 1, '12345', '12.6', '77', '62��Ԫ', to_date('16-09-2022 22:59:18', 'dd-mm-yyyy hh24:mi:ss'), '4', '��ծ����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('21', '���̻�����300', 'fund2.jpg', '1', '66.0', 1, '600', '15', '27', '600��', to_date('15-09-2022 16:40:16', 'dd-mm-yyyy hh24:mi:ss'), '1', '������ָ����������');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('22', '������ҵ����101', 'fund3.jpg', '1', '55', 0, '600', '-30', '29', '10��', to_date('15-09-2022 16:43:07', 'dd-mm-yyyy hh24:mi:ss'), '5', 'ҽҩ���ͻ���');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('41', '13��ծ18', 'bond3.jpg', '3', '33', 1, '3000', '10', '47', '30��', to_date('16-09-2022 10:41:59', 'dd-mm-yyyy hh24:mi:ss'), '6', '�Ƚ���ƫծծȯ');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('42', '20����03', 'bond4.jpg', '3', '52.0', 1, '15500', '11', '49', '10��', to_date('16-09-2022 10:43:21', 'dd-mm-yyyy hh24:mi:ss'), '4', '��ծ���Ƚ�ծȯ');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('43', '20��ծ07', 'bond2.jpg', '3', '52.3', 1, '8888', '8', '51', '5ǧ��', to_date('16-09-2022 10:45:53', 'dd-mm-yyyy hh24:mi:ss'), '3', '��ģ��С��ծȯ');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('44', '�ƽ�T+D', 'gold1.png', '6', '12.6', 1, '6226', '30', '53', '5��', to_date('16-09-2022 10:47:44', 'dd-mm-yyyy hh24:mi:ss'), '2', '�Ϸ�̩���������һֻ�ƽ����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('50', '��̩��֤���ز���ҵָ��C', 'fund4.jpg', '1', '65.1', 0, '56333', '15.79', '65', '2.92��', to_date('16-09-2022 16:56:31', 'dd-mm-yyyy hh24:mi:ss'), '4', 'ָ���͹�Ʊ�����زַ��ز���ҵ');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('61', '��������60����1��', 'bank2.jpg', '4', '15.3', 1, '50000', '1.05', '87', '5����', to_date('17-09-2022 10:48:18', 'dd-mm-yyyy hh24:mi:ss'), '3', 'С�����д���');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('62', '�辰���궨��4��', 'bank3.jpg', '4', '15.3', 1, '51320', '1.03', '89', '6����', to_date('17-09-2022 10:50:09', 'dd-mm-yyyy hh24:mi:ss'), '3', 'С�Ͷ������д���');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('63', '�辰����30����3�ŲƸ�', 'bank4.jpg', '4', '15.3', 1, '23156', '1.01', '91', '4����', to_date('17-09-2022 10:52:02', 'dd-mm-yyyy hh24:mi:ss'), '3', '���ڴ����������');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('48', '�в��ؽ���000736��', 'shares4.png', '2', '103.53', 1, '33333', '17.11', '61', '17.69', to_date('16-09-2022 16:23:12', 'dd-mm-yyyy hh24:mi:ss'), '5', '�����ʽ�����϶�');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('46', 'ʵ��002137��', 'shares2.png', '2', '33.7', 1, '50000', '10.09', '57', '50��', to_date('16-09-2022 16:19:49', 'dd-mm-yyyy hh24:mi:ss'), '2', '�����ʽ�����ù�Ʊ');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('53', '������֤�׾�ָ��', 'fund6.jpg', '1', '15.6', 0, '50000', '-4.43', '71', '100��', to_date('16-09-2022 22:52:20', 'dd-mm-yyyy hh24:mi:ss'), '1', '���Top10');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('65', 'ƽ��E��ƽ��-����ҽ����', 'insurance4.jpg', '5', '214', 1, '123523', '1.3', '95', '1����', to_date('17-09-2022 11:04:46', 'dd-mm-yyyy hh24:mi:ss'), '6', 'ȫ�ҹ��������ؼ�0���⿹����ҩ���ǣ���ҩ��������');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('66', 'ƽ��e��ƽ��-Т�ı�', 'insurance5.jpg', '5', '1513', 1, '3600', '2.0', '97', '3ǧ��', to_date('17-09-2022 11:07:27', 'dd-mm-yyyy hh24:mi:ss'), '6', '����ר�������������ҽ�Ʊ��ϣ����600���ȣ����������籣����');
insert into G_D_PRODUCT (ID, PNAME, PIMG, PTYPE, PRICE, PSTATE, TOTALNUM, AVERAGERATE, RECENTLYRATE, PSIZE, PTIME, PMANAGER, PDESC)
values ('67', '����T+D', 'gold2.png', '6', '43.2', 1, '15623', '42.1', '99', '1��', to_date('17-09-2022 11:11:04', 'dd-mm-yyyy hh24:mi:ss'), '4', '�����͹Ȼس������ʼ��������������');
commit;
prompt 31 records loaded
prompt Loading G_D_P_MANAGER...
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('1', '����', '��ţ��');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('2', '����', '���а�');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('3', '����', 'ŷ��ŷ��');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('4', '����', '���⣿');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('5', '����', '��������');
insert into G_D_P_MANAGER (MID, MNAME, MDESC)
values ('6', '�Ͱ�', '������');
commit;
prompt 6 records loaded
prompt Loading G_D_P_RECENTLYRATE...
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('59', '16.2', '32.1', '36.1', '28.6', '33.4', '38.2', '�����002701��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('63', '15.6', '36.4', '62.3', '92.3', '52.1', '14.6', '�������ӣ�600570��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('93', '0.3', '0.6', '0.8', '0.9', '1.0', '1.6', 'ƽ����������ҽ�Ʊ���');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('101', '12.3', '10.6', '15.9', '14.3', '10.8', '9.8', 'ֽ�ƽ�006');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('103', '-1.0', '-1.3', '-2.3', '1.0', '1.3', '-0.9', '����531');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('1', '10', '30', '40', '20', '60', '50', '�к���������Դ���aa');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('2', '10', '20', '30', '15', '25', '20', '��ӥԪ������ծȯ');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('3', '30', '20', '30', '15', '25', '20', '��ӥԪ�����ù�Ʊ1');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('4', '10', '20', '30', '15', '55', '60', '�㷢��֤���д���');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('5', '20', '20', '30', '15', '45', '20', '�¿�Դ����A');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('29', '15', '30', '21', '44', '50', '20', '��������ҽҩ������');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('55', '14.6', '36.1', '36.5', '32.3', '34.3', '35.9', 'ʢ����̩��300881��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('67', '17.3', '16.2', '19.6', '18.6', '19.6', '21.3', '��ؼ���600383');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('69', '15.6', '15.7', '32.6', '15.6', '41.6', '11.6', '�������ƻ��A');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('73', '15.6', '33.2', '54.6', '45.3', '21.6', '66.2', '��̩������Ʒ');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('75', '15.6', '16.6', '41.6', '2.3', '16.6', '3.2', '��ʢ���ݴ�ծ����');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('77', '15.6', '32.6', '12.6', '15.9', '14.3', '13.5', '��ʢ���ݴ�ծ����C');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('47', '15', '30', '11', '50', '41', '36', '�������ǿ�ر�ծȯ');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('27', '10', '20', '30', '40', '60', '70', '�׷�������');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('49', '36', '11', '52', '49', '33', '10', '�Ƹ�ͨ�ʹܺ踻��ծծȯC');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('51', '14', '23', '16', '15.5', '36.1', '14.6', '����ӯ���ж�ծȯA');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('53', '11.2', '33.6', '21.2', '45.2', '33.6', '35.1', '�Ϸ�̩�����A');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('65', '17.6', '23.6', '15.9', '14.6', '32.4', '15.6', '��̩��֤���ز���ҵָ��C');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('87', '0.2', '0.6', '1.3', '2.3', '-0.3', '1.5', '��������60����1��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('89', '1.3', '0.5', '0.8', '0.3', '0.7', '1.0', '�辰���궨��4��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('91', '1.3', '1.2', '0.8', '1.3', '1.3', '1.2', '�辰����30����3�ŲƸ�');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('71', '15.6', '-10.6', '-5.6', '-4.5', '-6.5', '-1.3', '������֤�׾�ָ��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('57', '14.3', '36.5', '23.1', '56.4', '23.5', '56.9', 'ʵ��002137��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('61', '14.6', '36.5', '35.9', '31.2', '36.8', '74.3', '�в��ؽ���000736��');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('95', '1.0', '1.3', '1.2', '0.6', '0.4', '0.5', 'ƽ��E��ƽ��-����ҽ����');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('97', '1.0', '0.8', '0.7', '0.6', '0.2', '1.0', 'ƽ��e��ƽ��-Т�ı�');
insert into G_D_P_RECENTLYRATE (RID, JAN, MAR, MAY, JULY, SEP, NOV, RNAME)
values ('99', '41.0', '45.6', '35.6', '38.9', '46.3', '50.7', '����T+D');
commit;
prompt 32 records loaded
prompt Loading G_D_P_STATE...
insert into G_D_P_STATE (SID, SNAME, SDESC)
values (1, '����', '���û�����ݶ����Ʒ�ܷݶ�ʱ����Ʒ״̬Ϊͣ��');
insert into G_D_P_STATE (SID, SNAME, SDESC)
values (0, 'ͣ��', '���û�����ݶ����Ʒ�ܷݶ�ʱ����Ʒ״̬Ϊͣ��');
commit;
prompt 2 records loaded
prompt Loading G_D_P_TYPE...
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('1', '����', '����Ӣ����fund��������ָΪ��ĳ��Ŀ�Ķ������ľ���һ���������ʽ���Ҫ��������Ͷ�ʻ��𡢹����𡢱��ջ������ݻ��𣬸��ֻ����Ļ���' || chr(10) || '�ӻ�ƽǶ�͸����������һ������ĸ����ָ�����ض�Ŀ�ĺ���;���ʽ������ᵽ�Ļ�����Ҫ��ָ֤ȯͶ�ʻ���');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('2', '��Ʊ', '��Ʊ��stock���ǹɷݹ�˾����Ȩ��һ���֣�Ҳ�Ƿ��е�����Ȩƾ֤���ǹɷݹ�˾Ϊ�Ｏ�ʽ�����и������ɶ���Ϊ�ֹ�ƾ֤������ȡ�ù�Ϣ�ͺ�����һ���м�֤ȯ����Ʊ���ʱ��г��ĳ������ù��ߣ�����ת�ã��������ɶ�ƾ�������Է���˾������');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('3', 'ծȯ', 'ծȯ��һ�ֽ�����Լ�������������ڻ�����������ҵ��ֱ��������ծ����ʽ�ʱ����Ͷ���߷���ͬʱ��ŵ��һ������֧����Ϣ����Լ���������������ծȨծ��ƾ֤��ծȯ�ı�����ծ��֤���飬���з���Ч��ծȯ�����߻�Ͷ�����뷢����֮����һ��ծȨծ���ϵ ��');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('4', '���д���', '���д�����ָÿ���˻��ͥ���ѽ�Լ��Ǯ�浽���еľ��û�����������ʱ���û����Ļ�������������л��������ڻ�����һ�ִ�����ֳƴ���������������û�����һ����Ҫ�ʽ���Դ��');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('5', '����', '���գ���ָͶ���˸��ݺ�ͬԼ����������֧�����շѱ����˶��ں�ͬԼ���Ŀ��ܷ������¹����䷢������ɵĲƲ���ʧ�е��⳥���ս����λ��߱������������˲м������ߴﵽ��ͬԼ��������е��������ս����ε���ҵ������Ϊ���Ӿ��ýǶȿ������Ƿ�̯�����¹���ʧ��һ�ֲ����ţ�');
insert into G_D_P_TYPE (TID, TNAME, TDESC)
values ('6', '�ƽ�', '�ƽ������ָͨ���Իƽ�۸����Ƶ�Ԥ�У�ͨ���ͼ�����߼�����׬ȡ�м��۵�һ����Ʒ�ʽ���ƽ���ư���ֽ�ƽ���ֻ��ƽ�ʵ��ƽ�Ͷ���߿���ͨ����������ƽ���ƽ̨����Ͷ�ʣ�' || chr(10) || 'Ͷ�����ڻƽ����ʱ��һ��Ҫ�����Լ���ʵ�����ѡ���Ʒ���Ͼ���һ��Ͷ�ʣ����ڷ��ա�');
commit;
prompt 6 records loaded
prompt Loading G_D_USER...
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('34', '�����', '123', '2.jpg', '����', '��', to_date('08-09-2022 21:24:08', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '12345678123', '123@qq.com', '34');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('35', '�²�����', '123', '7.jpg', '����', '��', to_date('08-09-2022 21:48:46', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '19816280861', '123@qq.com', '35');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('36', '������', '123', '0.jpg', '����������', '��', to_date('08-09-2022 21:53:50', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '12345678232', '123456@qq.com', '42');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('74', 'niubi', '123', '0.jpg', 'ţ�ư�', '��', to_date('13-09-2022 20:41:50', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '19816280864', '5320@qq.com', '75');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('1', 'cjf', '123', '1.jpg', '�ҷ�˧����', '��', to_date('06-09-2022 21:22:13', 'dd-mm-yyyy hh24:mi:ss'), '3', 0, '19816280861', '53206@qq.com', '1');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('2', 'admin', '123', '0.jpg', 'admin', '��', to_date('06-09-2022 21:25:42', 'dd-mm-yyyy hh24:mi:ss'), '2', 1, '12345678123', '532055876@qq.com', '2');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('72', 'boss', '123', '0.jpg', 'Ŀǰ���boss', '��', to_date('13-09-2022 10:36:57', 'dd-mm-yyyy hh24:mi:ss'), '4', 1, '15689412654', '5320@qq.com', '72');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('76', 'agai', '123', '9.jpg', '����', '��', to_date('13-09-2022 21:36:15', 'dd-mm-yyyy hh24:mi:ss'), '0', 0, '19524682163', '532055876@qq.com', '78');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('77', 'aba', '123', '1.jpg', null, null, to_date('13-09-2022 21:38:53', 'dd-mm-yyyy hh24:mi:ss'), '0', 0, null, null, '80');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('7', '����', '1234', '3.jpg', 'С��AA', '��', to_date('06-09-2022 21:27:00', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, '18257788920', '123@qq.com', '7');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('10', '����', '1234', '3.jpg', 'С��DD', '��', to_date('06-09-2022 21:27:00', 'dd-mm-yyyy hh24:mi:ss'), '0', 0, '18257788920', '2986045492@qq.com', '10');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('11', 'pc', '12345', '5.jpg', 'С��', '��', to_date('06-09-2022 21:27:24', 'dd-mm-yyyy hh24:mi:ss'), '2', 1, '19816280861', '532055876@qq.com', '11');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('13', 'wsc', '1234', null, null, '��', to_date('07-09-2022 17:00:32', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, null, null, '12');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('37', '����', '123', '1.jpg', '��ʥa', '��', to_date('08-09-2022 22:46:52', 'dd-mm-yyyy hh24:mi:ss'), '1', 0, '12345689122', '123@qq.com', '44');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('75', '��gai', '123', '1.jpg', null, null, to_date('13-09-2022 21:28:01', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, null, null, '76');
insert into G_D_USER (ID, USERNAME, PASSWORD, IMG, NICKNAME, SEX, CREATETIME, ROLE_ID, STATE, MOBILE, EMAIL, MONEYID)
values ('101', '�����1', '123', '0.jpg', null, '��', to_date('22-10-2022 13:57:54', 'dd-mm-yyyy hh24:mi:ss'), '0', 1, null, '532055876@qq.com', '110');
commit;
prompt 16 records loaded
prompt Loading G_D_U_CART...
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('109', '�����', 'insurance2.jpg', 'ƽ����ͯҽ�Ʊ���', '30.0', '30', 22, 660);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('111', '�����', 'bond3.jpg', '13��ծ18', '33', '10', 25, 825);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('38', '������', 'insurance3.jpg', 'ƽ����������ҽ�Ʊ���', '74.5', '1.32', 22, 1639);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('143', '�²�����', 'shares2.png', 'ʵ��002137��', '33.7', '10.09', 10, 337);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('112', '�����', 'shares5.png', '�������ӣ�600570��', '11.32', '35.80', 16, 181.12);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('146', '�²�����', 'bond2.jpg', '20��ծ07', '52.3', '8', 12, 627.6);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('161', '�����', 'shares3.png', '�����002701��', '82.31', '5.02', 18, 1481.58);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('113', '�����', 'insurance4.jpg', 'ƽ��E��ƽ��-����ҽ����', '214', '1.3', 21, 4494);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('114', '�����', 'gold4.png', '����531', '901.8', '-2.02', 15, 13527);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('115', '�����', 'gold2.png', '����T+D', '43.2', '42.1', 17, 734.4);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('116', '�����', 'bank2.jpg', '��������60����1��', '15.3', '1.05', 18, 275.4);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('117', '�����', 'shares6.png', '��ؼ���600383', '17.5', '11.76', 14, 245);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('118', '������', 'bond1.jpg', '��ӥԪ������ծȯ', '20.0', '10', 18, 360);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('119', '������', 'fund1.jpg', '�к���������Դ���aa', '30.0', '31', 16, 480);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('120', '������', 'shares3.png', '�����002701��', '82.31', '5.02', 25, 2057.75);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('121', '������', 'shares5.png', '�������ӣ�600570��', '11.32', '35.80', 18, 203.76);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('144', '�²�����', 'fund2.jpg', '���̻�����300', '66.0', '15', 14, 924);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('145', '�²�����', 'fund1.jpg', '�к���������Դ���aa', '30.0', '31', 13, 390);
insert into G_D_U_CART (CID, USERNAME, PIMG, PNAME, PRICE, AVERAGERATE, PNUM, SUM)
values ('147', '�²�����', 'gold4.png', '����531', '901.8', '-2.02', 12, 10821.6);
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
values ('0', '��ͨ�û�', 'Ȩ�����', to_date('11-09-2022 00:29:20', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('2', '����Ա', 'Ȩ��Ҳ��������', to_date('13-09-2022 16:01:45', 'dd-mm-yyyy hh24:mi:ss'), 'boss');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('3', '����', 'Ȩ�����������˺��', to_date('10-09-2022 22:52:22', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('4', 'boss', 'Ȩ�����', to_date('11-09-2022 00:25:28', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('1', '��ఢ��', 'Ȩ��Ҳ�������ɱ��û���һ��', to_date('10-09-2022 22:50:13', 'dd-mm-yyyy hh24:mi:ss'), 'admin');
insert into G_D_U_ROLE (RID, RNAME, RDESC, RDATE, RAUTHOR)
values ('7', '����Boss', 'ӵ�г���Ȩ��', to_date('11-09-2022 00:41:42', 'dd-mm-yyyy hh24:mi:ss'), 'cjf');
commit;
prompt 6 records loaded
prompt Loading G_D_U_STATE...
insert into G_D_U_STATE (SID, SNAME, SDESC)
values (1, '����', '1����������0����ͣ��');
insert into G_D_U_STATE (SID, SNAME, SDESC)
values (0, 'ͣ��', '1����������0����ͣ��');
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
