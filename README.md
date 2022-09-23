# -Graduate-Designing-



# --吾爱理财平台毕业设计

【项目简介】

​		随着社会的发展，社会的方方面面都在利用信息化时代的优势。互联网理财是一种新事物，就让我们在应用中学习，逐步提高自己的理财能力，理财贯穿于我们生活中的各个细节中，随着网络与网民生活的日益融合，互联网理财应该会成为网民生活中的一个热点。在理财产品的选择上一般是综合权衡各种资产存在方式的流动性、安全性、风险性，根据自己的需要和风险偏好进行选择。优秀理财观念不断深入人心，本项目借助互联网的优势开发一个关于市面上常见理财产品的交互平台。该平台分为门户界面和管理员后台页面促使理财平台变的更加系统化、有序化。系统界面较友好，易于操作。

【技术架构】

​		主要采用前后端分离的方式，通过接口请求访问后端数据。前端主要采用Node.js基于Vue2.0+element ui 进行架构设计 使用vuex进行全局的状态管理 以及vue-router 进行路由的跳转，并采用echarts 进行数据图表显示；后端主要采用oracle数据库与java语言，基于SpringBoot对用户、理财产品、订单等模块进行设计，并搭建swagger进行前后端分离接口设计，以及搭载Interceptor拦截器进行权限拦截，项目构建于maven3.6.0的依赖仓库中。

【功能模块】

​		1.门户展示模块：作为普通用户的购入前的门户页面，主要分为首页模块，股票，基金，债券，银行储蓄理财模块，信息反馈模块，个人中心模块的设计，其中每个理财产品模块包含了对每个理财产品的入行指南，产品介绍，大V推荐等区域设计。其中在首页搭载轮播图，最新理财咨询。在理财产品模块可进行理财产品购买，添加到预购中心，若想查看理财产品详情可以点击进入查看。在信息反馈模块中，用户可编辑信息进行用户间评论交流，以及推送给后台管理员平台建议等。在个人中心模块可显示已购入的理财产品以及预购的理财产品并对预购的产品进行勾选和修改。

​		2.用户模块：通过前台首页登陆按钮进入登陆页面，唯有用户登陆后才可以进行理财产品的购买和个人信息的修改和查看，登陆模块采用了输入用户名，密码，以及滑动验证的方式，提高了用户登陆的安全性，如果是管理员用户，则跳转到后台管理员登陆页面进行登陆，普通用户若无账号信息，可在登陆页面直接跳转到用户注册。

​		3.后台管理模块：总体分为用户管理，理财管理，订单管理，公告管理，意见反馈和系统管理等小模块。其中用户管理中包括用户列表，角色管理，用户新增等子模块，能够实现对用户角色权限的管理，以及新用户的添加。在理财管理中可以通过数据库中对理财类型的字段遍历查询得出股票，基金，债券，银行储蓄等理财产品的分组管理订单管理中首先显示所有订单列表，其次可根据普通用户对该订单的支付情况分为已支付，已完成，已取消订单，并且管理员可以对所有订单的部分信息进行修改与删除在公告管理中可对前台门户页面首页的公告进行更新以及轮播图内容的管理。在意见反馈管理中，可以根据前台用户的反馈信息对平台内容，数据进行更新，给予管理者更好的改善体验。

​		4.预购模块：类似于商城网站的购物车模块设计，可以将用户想要的某项理财产品添加到预购中，然后可以继续选购，继续向预购中添加理财产品，用户可以在预购中清楚的看到自己所选的理财产品和数量以及购买所需的金额并进行总价计算，并且可以批量删除预购中不想要的理财产品，当用户选购结束后，可提交订单进行结算。

​		5.订单模块：分为订单简介和订单详情子模块设计，可在首页点击购买理财产品按钮后进入订单简介，填写购入数量以及支付信息的确认。在订单模块中如果点击取消订单，该订单记录将会发往后台已取消订单中。当成功支付订单后，该订单则将发往后台已支付订单中。当后台管理员审核后该订单状态改为已完成订单。若对订单信息不够了解，或者根据产品的图片进入相对应的详情页面确认。

​		6.支付模块：根据订单模块传递来的订单信息，跳转至支付页面根据订单总价以及支付的方式进行支付，支付成功后可在首页个人中心查看已完成订单。

​		

​		

## 后台模块设计

- 首页：显示图表，用户数据总览，天气等

- ​	用户管理
  - 用户列表：增删改查
  
  - 角色管理

  - 添加用户
  
    
  
- 理财管理
  - 总览（添加分类类型）
  - 股票
    - 增删改查
  - 基金
    - 增删改查
  - 债券
    - 国债	
    - 企业债券
  - 银行储蓄
  - 保险
  - 黄金

- 订单管理
  - 订单总览
  - 已完成订单	
  - 已取消订单
  - 已支付订单
  
- 公告管理
  - 公告

- 流水信息管理

- 目标计划管理

- 意见反馈管理

- 系统管理

  - 轮播图管理

  
  
  ## 数据库表的设计
  
  User表：
  
  一个User总表：其中可以包含普通用户和管理员，后台也可以通过点击普通用户详情查看普通用户具体信息
  
  有三个多表连接的字段：
  
  1.角色id :role_id比较重要 可根据值的大小决定层级变化，当角色的值较大他可以修改或删除下一层级的信息，反之则不能修改或删除上一层级的信息；
  
  2.state状态管理：也用到了多表连接 表示该用户状态是否已开启，如果未开启则该用户不能登录！
  
  3.资金账号：通过多表连接表示该资金账号下用户购买理财产品的收支情况
  
  具体字段设计如下
  
  ​	------这是用户表(11个字段)
  
  ​	create table g_d_user(
  
  ​			id varchar2(20) primary key,
  
  ​			username  varchar2(50),
  
  ​			password  varchar2(50),
  
  ​			img  varchar2(20),
  
  ​			nickname varchar2(50),
  
  ​			sex varchar2(5),
  
  ​			createtime date,
  
  ​			role_id  varchar2(10),
  
  ​			state number(10),
  
  ​			mobile varchar2(50),
  
  ​			email varchar2(50),
  
  ​			moneyid varchar2(20) (资金账号) 			
  
  ​	)
  
  
  
  -----这是角色管理表(多表连接1)
  
  ​	create table g_d_u_role(
  
  ​			rid VARCHAR2(20)  primary key,
  
  ​			rname varchar2(50),
  
  ​			rdesc varchar2(50),
  
  ​			rdate DATE,
  
  ​			rauthor varchar2(10)
  
  ​	)
  
  ----这是状态管理表（多表连接2）
  
  ​		create table g_d_u_state(
  ​       		sid number(10) primary key,
  ​       		sname varchar2(30),
  ​       		sdesc varchar2(50)
  
  )
  
  ---这是资金账号管理表（多表连接3）
  
  ​		create table G_D_U_MONEY(
    MID        VARCHAR2(20)  primary key,
    TOTALMONEY NUMBER(20,2),
    INCOME     NUMBER(20,2),
    PAY        NUMBER(20,2),
    BALANCE    NUMBER(20,2)
  )
  
  
  
  1.权限功能的实现：
  
  ​		在当前用户登陆的角色下 ，可以修改和删除比他低级别的角色信息，不能删除或者修改大于或等于他当前角色的信息（删除和修改严格权限约束）
  
  ​		模糊查询只要是能登陆到后台页面的角色都能查询（无权限约束）
  
  ​		添加的话只能是大于等于经理的角色可以添加（经理角色大概是所有角色的中间权限）
  
  2.后台管理首页（先简单构建中。。）
  
  ​	head显示当前时间，当前城市和温度（调用高德天气接口）
  
  ​	下一个框暂时没有想法先copy一个固定的框
  
  ​	后续需要加上图片和图表显示数据！
  
  3.将门户模板copy到当前项目中并实现普通用户跳转的时候
  
  能够将大部分门户功能实现！
  
  4.普通用户登陆注册实现 若是管理员跳转到管理员登录（good5）
  
  5.构思和设计理财产品表
  
  
  
  理财产品表（本项目关键）
  
  1.当普通用户开通账户后可以购买理财产品，购买时输入该产品购买份额，当购买份额超出该产品总份额时，购买失败。
  
  2.普通用户根据产品描述，基金经理描述和平均的收益率决定是否要购买该理财产品
  
  Product 表（一个主表，四个从表）
  
  初级表构思：产品Id，产品名称，产品图片，产品类型（重要）(外键)，产品单价，产品状态（外键），产品总份额，产品平均收益率（%），产品近期收益率（外键），产品资金规模，产品上市时间，产品经理（外键）， 产品描述
  
  
  
  主表：
  
  create table g_d_product(
         id varchar2(20) primary key,
         pname varchar2(50),
         pimg varchar2(10),
         ptype varchar2(10),
         price varchar2(30),
         pstate number(10),
         totalnum varchar2(30),
         averagerate varchar2(10),
         recentlyrate varchar2(10),
         psize varchar2(20),
         ptime date,
         pmanager varchar2(20),
         pdesc varchar2(50)
         
  
  )
  
  产品类型从表（外键1）
  
  create table g_d_p_type(
         tid varchar2(10) primary key,
         tname varchar2(30),
         tdesc varchar2(50)
       
  )
  
  产品状态从表（外键2）
  
  create table g_d_p_state(
         sid number(10) primary key,
         sname varchar2(20),
         sdesc varchar2(20)
               
  
  )
  
  产品近期收益率从表（外键3）
  
  create table g_d_p_recentlyrate(
        rid varchar2(10) primary key,
        jan varchar2(10),
        mar varchar2(10),
        may varchar2(10),
        july varchar2(10),
        sep  varchar2(10),
        nov varchar2(10) 
         
  
  )
  
  产品经理从表（外键4）
  
  create table g_d_p_manager(
         mid varchar2(20) primary key,
         mname varchar2(20),
         mdesc varchar2(50)
  
  )
  
  
  
  后端接口都已测试成功，完善前端功能（1.显示所有产品2.添加，修改，删除，模糊查询，详情功能实现）
  
  其中
  
  - [x] 1.找产品图片	
  
  - [x] 2.这些功能都在一个组件中实现！！
  
  - [x] 3.编辑产品近期收益率
  
    - [x] ​	1.当添加一个产品后同时将产品名字也添加到recently表中
  
    - [x] ​	2.近期收益率添加删除修改功能
  
  - [x] 4.加上权限！
  
  
  
  明日（2022.9.16）：
  
  - [x] 1.根据type查询产品（只需要显示数据即可）
  
  ​	最上面显示产品类型的描述，或者随便一些产品指数图片，代表的基金经理
  
  ​	表格中显示基本跟总览中一样
  
  2.前台门户
  
  基础构思：类似商城，首先显示图片，或视频，点击图片进入详情页（产品表中字段数据），详情中有添加预购（输入购买份额），或者直接去结算，最后在订单页面去支付
  
  - [x] ​		1.参考别的理财网
  
  - [x] ​		2.找图片
  
  - [x] ​		3.普通用户的信息修改（个人主页中），头像 ，名字
  
  ​		
  
  明天任务（2022.9.16）：
  
  - [x] ​		1.根据类型将产品显示在不同门户主页上
  
  ​				（带图片，带产品简介，一个可查看详情的按钮）
  
  ​					排版方式：（横排或竖排）
  
  ​									1.股票（人物介绍卡片）
  
  ​								    2.基金（内容上浮卡片+横排）
  
  ​									3.债券（高光卡片）
  
  ​									4.保险（Expanding Cards-2）
  
  ​									5.银行储蓄
  
  ​								    6.黄金（横排卡片）
  
  - [x] ​	 	   2.开通账户按钮
  
  ​					登陆成功后提示请点击开通账户按钮 来开通账户！
  
  - [x] ​			3.找产品简介视频（mp3/mp4）
  
  ​			
  
  明日任务（2022.9.18）
  
  - [x] ​	1.点击详情进入详情页面（详情页面参考first Project）
  
  基础构思：分产品类型设计不同的产品细节模块组件，传入参数为产品id,根据id查询详细信息
  
  - 可以展示一个产品介绍Mp4,以及产品的各种详细信息（Mp4为每个类型产品定死的东西）
  
  - 一个加入预购按钮（输入购入份额）
  
  - 一个结算按钮（输入购入份额）
  
    - [x]   2.构思预购表和订单表（重要）
    - [x]  3.设计一个在门户任意位置可查看的预购组件
  
    
  
    ​								
  
  预购表（可以批量选择删除,根据用户名筛选）
  
  ​		1.还未进行支付，所以不需要对用户表中用户资产和余额等修改
  
  ​		2.根据选择框是否勾选来决定是否形成订单
  
  ​		3.要进行分页
  
  ​		4.有一个可以更改数量的按钮
  
  具体表设计如下：
  
  ​	create table g_d_u_cart(
  ​       cid varchar2(20) primary key,
  ​       username varchar2(50),
  ​       pimg varchar2(10),
  ​       pname varchar2(50),
  ​       price varchar2(30),
  ​       averagerate varchar2(10),
  ​       recentlyrate varchar2(10),
  ​       pnum number(20),
  ​       sum varchar2(20)
  ​    
  
  )
  
  
  
  明日任务（2022.9.19）
  
  - [x] ​	1.个人预购中心批量删除和点击复选框计算总价
  - [x] ​    2.设计订单表结构（后端基础增删改查代码完成）
  - [x] ​    3.构思用户资产，余额，支出，收益的实现
  
  
  
  
  进入订单有两个渠道
  
  1.通过我的预购进行结算
  
  ​	当点击结算后弹出一个框确认1.用户名 2.总价 3.支付的方式
  
  ​	然后直接进入支付界面输入账号密码支付
  
  2.点击产品详情进入订单页（需要输入购入份数）
  
  ​		显示该产品的信息，需要支付的金额，确定用户，选择支付方式
  
  ​		点击支付后将数据添加到订单表中
  
  下面是订单表设计
  
  ​		字段：订单id,订单用户，订单总额，预期收益（用户界面不显示），下单时间，支付方式（外键），订单状态（多表外键）
  
  create table g_d_order(
         oid varchar2(20) primary key,
         username varchar2(30),
         totalsum Number(10,2),
         Exincome number(10,2),
         time date,
         payWay varchar2(10),
         state varchar2(10)，
  
  ​	    executetime date,
  
  ​		author varchar2(20)
  
  )
  
  支付方式表（外键1）
  
  ​	create table g_d_o_pay(
  ​       pid varchar2(10),
  ​       pname varchar2(20),
  ​       pdesc varchar2(50)
  
  )
  
  订单状态表（外键2）：0等待中，1已取消 ，2 已支付，3已完成
  
  等待订单：插入订单表中的默认状态
  
  取消订单：用户点击支付旁边的取消订单按钮，也会向订单表中添加数据但是下单时间为null,类型为0
  
  支付订单：用户点击支付，然后成功支付，但是后台管理员还未核实计算收益的状态
  
  完成订单：用户支付完，且管理员处理该条订单，成功计算完收益返回给用户余额和收益
  
  create table g_d_o_state(
         sid varchar2(10),
         sname varchar2(20),
         pdesc varchar2(50)
  )
  
  ---
  
  明日任务（2022.9.20）
  
  - [ ] 1.购物车多选计算总价并结算（改Bug有难度）
  
  - [x] 2.后台管理显示订单详情（各种基本功能，上午先做这个 easy）
  
  - [x] 3.构思支付模块：优先找模板
  
  ​		初级构思:要支付登陆 登陆成功后显示支付成功 再去修改表中字段数据



​	当点击确定以后进行结算

- [x] ​		1.首先根据id修改执行时间和执行者，然后根据用户名去找到用户表中的moneyid,再根据moneyid找到g_d__u_money表中的记录去修改。



- [x] ​        2.点击首页的开通账户通过取出的moneyid ，通过moneyid 去查找用户资产表中的数据，并将余额存入vuex中并进行购买时的判断
- [x] ​         3.沙盒支付



echarts:

- [x] ​    首页显示用户资产图表和理财产品月平均收益率

- [x] ​	保证每个模块都有一个图标

​	1.用户余额等等

​	2.理财产品的平均收益率和各月份收益率

​	3.理财产品的上市时间

- [x] 数据统计模块中存放所有图表



个人详情：

- [x] ​		显示图表（个人资产）

- [x] ​		个人已完成订单，已取消订单，已支付订单

- [x] ​		充值个人资金按钮

​	



- [x]  点击购物车多选计算不同的总价

- [ ] 在订单表中设计一个字段供外键表，外键表中id不唯一（不设置主键） 表中字段有：产品id,产品的名称，产品图片，产品小计等等
- [ ] 订单表中查询时可根据多表查询

