# Oracle

## 一、ORACLE 简介

### 1. 什么是 ORACLE

ORACLE 数据库系统是美国 ORACLE 公司（甲骨文）提供的以分布式数据 库为核心的一组软件产品，是目前最流行的客户/服务器(CLIENT/SERVER)或 B/S 体系结构的数据库之一。 

ORACLE 通常应用于大型系统的数据库产品。

ORACLE 数据库是目前世界上使用最为广泛的数据库管理系统，作为一个通 用的数据库系统，它具有完整的数据管理功能；作为一个关系数据库，它是一个 完备关系的产品；作为分布式数据库它实现了分布式处理功能。

ORACLE 数据库具有以下特点：

1. 支持多用户、大事务量的事务处理
2. 数据安全性和完整性控制
3. 支持分布式数据处理
4. 可移植性



### 2. ORACLE 体系结构

1. 数据库
   Oracle 数据库是数据的物理存储。这就包括（数据文件 ORA 或者 DBF、控 制文件、联机日志、参数文件）。其实 Oracle 数据库的概念和其它数据库不一 样，这里的数据库是一个操作系统只有一个库。可以看作是 Oracle 就只有一个大数据库。



2. 实例
   一个Oracle实例（Oracle Instance）有一系列的后台进程（Backguound Processes) 和内存结构（Memory Structures)组成。一个数据库可以有 n 个实例。



3. 数据文件（dbf）
   数据文件是数据库的物理存储单位。数据库的数据是存储在表空间中的， 真正是在某一个或者多个数据文件中。而一个表空间可以由一个或多个数据文件 组成，一个数据文件只能属于一个表空间。一旦数据文件被加入到某个表空间后， 就不能删除这个文件，如果要删除某个数据文件，只能删除其所属于的表空间才 行。



4. 表空间
   表空间是 Oracle 对物理数据库上相关数据文件（ORA 或者 DBF 文件）的逻 辑映射。一个数据库在逻辑上被划分成一到若干个表空间，每个表空间包含了在 逻辑上相关联的一组结构。每个数据库至少有一个表空间(称之为 system 表空 间)。

   每个表空间由同一磁盘上的一个或多个文件组成，这些文件叫数据文件 (datafile)。一个数据文件只能属于一个表空间。

![](Oracle.assets/19.png)

注：表的数据，是有用户放入某一个表空间的，而这个表空间会随机把这些表数据放到一个或者多个数据文件中。

由于 oracle 的数据库不是普通的概念，oracle 是有用户和表空间对数据进行 管理和存放的。但是表不是有表空间去查询的，而是由用户去查的。因为不同用 户可以在同一个表空间建立同一个名字的表！这里区分就是用户了！

![](Oracle.assets/20.png)



5. 用户
   用户是在表空间下建立的。用户登陆后只能看到和操作自己的表, ORACLE 的用户与 MYSQL 的数据库类似，每建立一个应用需要创建一个用户。

![](Oracle.assets/21.png)





## 二、ORACLE 安装与配置

### 1. VMware 挂载 windows server 2003

打开“资源”文件夹中的 windows2003 文件夹，双击扩展名为 vmx 的文件即可

将 windows2003 系统挂载到 VMware 中

![](Oracle.assets/1.png)



### 2. 网络配置

1. 创建虚拟网卡

VMware 中选择菜单“编辑”--“虚拟网络编辑器”

![](Oracle.assets/2.png)

弹出的窗口中，点击“添加网络”按钮，名称为 VMnet2 ，确定

![](Oracle.assets/3.png)

设置为仅主机方式，并设定子网 IP 为 192.168.80.0 

![](Oracle.assets/4.png)



2. 设定虚拟操作系统的网络网卡

![](Oracle.assets/5.png)

右键点击虚拟操作系统，选择“设置”菜单项，弹出以下窗口

![](Oracle.assets/6.png)

点击网络适配器，选择自定义，VMnet2 



3. 设定虚拟操作系统的 IP 地址

在虚拟机的操作系统中设定 IP 地址为 192.168.80.10 

![](Oracle.assets/7.png)

![](Oracle.assets/8.png)

![](Oracle.assets/9.png)

![](Oracle.assets/10.png)

![](Oracle.assets/11.png)



4. 设置本地的操作系统的虚拟网卡 VMnet 的 IP 为 192.168.80.6 (与虚拟机中的操作系统的 IP 地址处于同一 IP 网段)

![](Oracle.assets/12.png)

![](Oracle.assets/13.png)

![](Oracle.assets/14.png)

![](Oracle.assets/15.png)

![](Oracle.assets/16.png)

![](Oracle.assets/17.png)



在本地操作系统打开命令行，用 ping 命令测试网络是否连接成功

![](Oracle.assets/18.png)



### 3. 安装 ORACLE 数据库

将“资源”文件夹 ORACLE 安装包解压拷贝到虚拟机的系统中 并双击解压目录下的 setup.exe，出现安装界面，如下：

![](Oracle.assets/22.png)

输入口令和确认口令，如：isjhd，点击下一步，出现如下进度条，

注：此口令即是管理员密码。

然后一直安装就行



可以去命令行窗口下进行测试安装结果

输入:sqlplus system/isjhd

isjhd为你安装时输入的密码

![](Oracle.assets/23.png)



### 4. SQLPlus 远程连接 ORACLE 数据库

将“资源”文件夹中的 instantclient_12_1 拷贝到一个目录

进入命令提示符，进入该目录，输入如下命令连接远程的 ORACLE

sqlplus system/isjhd@192.168.80.10:1521/orcl

![](Oracle.assets/24.png)





### 5. PLSQL Developer 安装与配置

1. 安装 资源文件夹 PLSQL+Developer10.0.3.1701 

注意事项：安装目录不能有中文和空格，否则连接不上

![](Oracle.assets/25.png)

将上边的目录改为 D:\PLSQLDeveloper 



2. 配置客户端路径

![](Oracle.assets/26.png)

在弹出的登陆窗口中，点取消，Tools->>Preferences

![](Oracle.assets/27.png)



3. 编辑 ORA 文件

在 ORACLE 的以下安装目录中找到 tnsnames.ora 文件，拷贝到本地电脑的 D盘

![](Oracle.assets/28.png)

打开 tnsnames.ora 文件编辑，修改下图红色方框处

![](Oracle.assets/29.png)



4. 设置环境变量 TNS_ADMIN 为 tnsnames.ora 所在目录

![](Oracle.assets/30.png)



5. 进入系统

![](Oracle.assets/31.png)

输入用户名 system 密码 isjhd 即可进入到软件主界面。



6. 中文编码设置

查看服务器端编码 SQL: select userenv('language') from dual

我实际查到的结果为:AMERICAN_AMERICA.ZHS16GBK

计算机->属性->高级系统设置->环境变量->新建

![](Oracle.assets/32.png)



### 6. 启动数据库

命令行窗口执行如下语句启动数据库

sqlplus /nolog
connect / as sysdba
startup force

然后等待启动成功





## 三、项目案例：《自来水公司收费系统》

### 1. 项目介绍与需求分析

XXX 市自来水公司为更好地对自来水收费进行规范化管理，决定委托传智 播客.黑马程序员开发《自来水公司收费系统》。考虑到自来水业务数量庞大， 数据并发量高，决定数据库采用 ORACLE 数据库。主要功能包括：

1. 基础信息管理：
   （1）业主类型设置
   （2）价格设置
   （3）区域设置
   （4）收费员设置
   （5）地址设置

2. 业主信息管理：
   （1）业主信息维护
   （2）业主信息查询

3. 收费管理：
   （1）抄表登记
   （2）收费登记
   （3）收费记录查询
   （4）欠费用户清单
4. 统计分析：
   （1）收费日报单 
   （2）收费月报表



### 2. 表结构设计

1. 业主类型表（T_OWNERTYPE）

![](Oracle.assets/33.png)

2. 价格表（T_PRICETABLE）

![](Oracle.assets/34.png)

3. 区域表（T_AREA）

![](Oracle.assets/35.png)

4. 收费员表（T_OPERATOR）

![](Oracle.assets/36.png)

5. 地址表（T_ADDRESS）

![](Oracle.assets/37.png)

6. 业主表（T_OWNERS）

![](Oracle.assets/38.png)

7. 收费台账（T_ACCOUNT）

![](Oracle.assets/39.png)



上述 7 张表的物理模型如下：

![](Oracle.assets/40.png)



### 3. 创建表空间

```sql
create tablespace waterboss
datafile 'c:\waterboss.dbf'
size 100m
autoextend on
next 10m
```

解释：

- waterboss 为表空间名称
- datafile 用于设置物理文件名称
- size 用于设置表空间的初始大小
- autoextend on 用于设置自动增长，如果存储量超过初始大小，则开始自动扩容
- next 用于设置扩容的空间大小



### 4. 创建用户

注意：执行多条语句时，使用 `;` 隔开

```sql
create user wateruser
identified by isjhd
default tablespace waterboss
```

wateruser 为创建的用户名

identified by 用于设置用户的密码

default tablesapce 用于指定默认表空间名称



### 5. 用户赋权

```sql
--给wateruser赋予DBA权限
grant dba to wateruser
```

给用户 wateruser 赋予 DBA 权限后即可登陆





## 四、表的创建、修改与删除

### 1. 创建表

语法：

```sql
CREATE TABLE 表名称(
    字段名 类型(长度) primary key,
    字段名 类型(长度),
    .......
);
```

数据类型：

**字符型**

1. CHAR : 固定长度的字符类型，最多存储 2000 个字节
2. VARCHAR2 :可变长度的字符类型，最多存储 4000 个字节
3. LONG : 大文本类型。最大可以存储 2 个 G



**数值型**

NUMBER : 数值类型 

例如：
NUMBER(5) 最大可以存的数为 99999
NUMBER(5,2) 最大可以存的数为 999.99 



**日期型**

1. DATE：日期时间型，精确到秒
2. TIMESTAMP：精确到秒的小数点后 9 位



**二进制型（大数据类型）**

1. CLOB : 存储字符,最大可以存 4 个 G
2. BLOB：存储图像、声音、视频等二进制数据,最多可以存 4 个 G



**实例：**创建业主表

```sql
create table t_owners
(
    id number primary key,
    name varchar2(30),
    addressid number,
    housenumber varchar2(30),
    watermeter varchar2(30),
    adddate date,
    ownertypeid number
);
```

其它表的创建见资料“自来水收费系统建表语句.txt”



### 2. 修改表

1. 增加字段语法:

```sql
ALTER TABLE 表名称 ADD(列名 1 类型 [DEFAULT 默认值]，列名 1 类型
[DEFAULT 默认值]...)
```

为业主表增加两个字段，语句：

```sql
--追加字段
ALTER TABLE T_OWNERS ADD
(
 REMARK VARCHAR2(20),
 OUTDATE DATE
)
```



2. 修改字段语法：

```sql
ALTER TABLE 表名称 MODIFY(列名 1 类型 [DEFAULT 默认值]，列名 1 类型
[DEFAULT 默认值]...)
```

修改两个字段的类型，语句：

```sql
--修改字段
ALTER TABLE T_OWNERS MODIFY
(
    REMARK CHAR(20),
    OUTDATE TIMESTAMP
)
```



3. 修改字段名语法：

```sql
ALTER TABLE 表名称 RENAME COLUMN 原列名 TO 新列名
```

语句：

```sql
ALTER TABLE T_OWNERS RENAME COLUMN OUTDATE TO EXITDATE
```



4. 删除字段名

```sql
--删除一个字段
ALTER TABLE 表名称 DROP COLUMN 列名

--删除多个字段
ALTER TABLE 表名称 DROP (列名 1,列名 2...)
```

语句：

```sql
--删除字段
ALTER TABLE T_OWNERS DROP COLUMN REMARK
```



### 3. 删除表

语法：

```sql
DROP TABLE 表名称
```



## 五、数据增删改

### 1. 插入数据

语法：

```sql
INSERT INTO 表名[(列名 1，列名 2，...)]VALUES(值 1，值 2，...)
```

执行 INSERT 后一定要再执行 commit 提交事务

向业主表插入数据：

```sql
insert into T_OWNERS VALUES (1,'张三丰',1,'2-2','5678',sysdate,1);
```

语句中的 sysdate 是系统变量用于获取当前日期

我们再次录入一条数据，语句如下:

```sql
insert into T_OWNERS VALUES (2,'赵大侃',1,'2-3','9876',sysdate,1);
commit;
```





### 2. 修改数据

语法：

```sql
UPDATE 表名 SET 列名 1=值 1，列名 2=值 2，....WHERE 修改条件；
```

执行 UPDATE 后一定要再执行 commit 提交事务



需求：将 ID 为 1 的业主的登记日期更改为三天前的日期

```sql
update T_OWNERS set adddate=adddate-3 where id=1;
commit;
```





### 3. 删除数据

语法 1：

```sql
DELETE FROM 表名 WHERE 删除条件;
```

执行 DELETE 后一定要再执行 commit 提交事务



需求：删除业主 ID 为 2 的业主信息

```sql
delete from T_OWNERS where id=2;
commit;
```



语法 2：

```sql
TRUNCATE TABLE 表名称
```



比较 truncat 与 delete 实现数据删除？

1. delete 删除的数据可以 rollback
2. delete 删除可能产生碎片，并且不释放空间
3. truncate 是先摧毁表结构，再重构表结构



## 六、JDBC 连接 ORACLE

### 1. 创建工程，引入驱动包

在下图目录中可以找到驱动包 ORACLE 的 JDBC 驱动包，拷贝到工程即可使用

![](Oracle.assets/41.png)

创建 java 工程 waterboss，建立 lib 文件夹，将 ojdbc.jar 拷贝到此文件夹，然后 add build path

![](Oracle.assets/42.png)



### 2. BaseDao

我们通常编写 BaseDao 负责加载驱动，获取数据库连接，关闭资源，代码如下：

```java
import java.sql.*;

//基本数据访问类(获取数据库连接 关闭资源)
public class BaseDao {

    static {
        //加载驱动
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //获取数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.10:1521:orcl", "wateruser", "isjhd");
    }

    //关闭资源
    public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {

        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
```

JDBC 驱动为： oracle.jdbc.OracleDriver

连接字符串( 瘦连接 )： jdbc:oracle:thin:@虚拟机的 IP:1521:orcl



### 3. 业主增删改代码编写

1. 创建实体类

```java
//业主表实体类
public class Owners {

    private Long id;//编号
    private String name;//业主名称
    private Long address;//地址ID
    private String houseNumber;//门牌号
    private String waterMeter;//水表编号
    private Date addDate;//增加日期
    private Long ownerTypeId;//业主类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(String waterMeter) {
        this.waterMeter = waterMeter;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Long getOwnerTypeId() {
        return ownerTypeId;
    }

    public void setOwnerTypeId(Long ownerTypeId) {
        this.ownerTypeId = ownerTypeId;
    }
}
```



2. 创建 Dao 类实现增删改

```java
import cn.itcast.waterboss.entity.Owners;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//业主信息数据访问类
public class OwnersDao {

    //增加业主
    public static void add(Owners owners) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = BaseDao.getConnection();
            stmt = conn.prepareStatement("insert into t_owners values(?,?,?,?,?,?,?)");
            stmt.setLong(1, owners.getId());//业主编号
            stmt.setString(2, owners.getName());//业主名称
            stmt.setLong(3, owners.getAddress());//地址编号
            stmt.setString(4, owners.getHouseNumber());//门牌号
            stmt.setString(5, owners.getWaterMeter());//水表编号
            stmt.setDate(6, new Date(owners.getAddDate().getTime()));//增加日期
            stmt.setLong(7, owners.getOwnerTypeId());//业主类型
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭所有对象
            BaseDao.closeAll(null, stmt, conn);
        }

    }

    //修改业主
    public static void update(Owners owners) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = BaseDao.getConnection();
            stmt = conn.prepareStatement("update t_owners set name=?, addressid=?," +
                    "housenumber=?, watermeter=?, adddate=?, ownertypeid=? where id=?");
            stmt.setString(1, owners.getName());//业主名称
            stmt.setLong(2, owners.getAddress());//地址编号
            stmt.setString(3, owners.getHouseNumber());//门牌号
            stmt.setString(4, owners.getWaterMeter());//水表编号
            stmt.setDate(5, new Date(owners.getAddDate().getTime()));//增加日期
            stmt.setLong(6, owners.getOwnerTypeId());//业主类型
            stmt.setLong(7, owners.getId());//业主编号
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭所有对象
            BaseDao.closeAll(null, stmt, conn);
        }

    }

    //删除业主
    public static void delete(Long id) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = BaseDao.getConnection();
            stmt = conn.prepareStatement("delete from t_owners where id=?");

            stmt.setLong(1, id);//业主编号
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭所有对象
            BaseDao.closeAll(null, stmt, conn);
        }

    }

}

```



## 七、数据导出与导入

当我们使用一个数据库时，总希望数据库的内容是可靠的、正确的，但由于 计算机系统的故障（硬件故障、软件故障、网络故障、进程故障和系统故障）影 响数据库系统的操作，影响数据库中数据的正确性，甚至破坏数据库，使数据库 中全部或部分数据丢失。因此当发生上述故障后，希望能重构这个完整的数据库 该处理称为数据库恢复，而要进行数据库的恢复必须要有数据库的备份工作。



### 1. 整库导出与导入

**整库导出命令**

```
exp system/isjhd full=y
```

添加参数 full=y 就是整库导出

![](Oracle.assets/43.png)

执行命令后会在当前目录下生成一个叫 EXPDAT.DMP，此文件为备份文件。

如果想指定备份文件的名称，则添加 file 参数即可，命令如下

```
exp system/isjhd file=文件名 full=y
```



**整库导入命令**

```
imp system/isjhd full=y
```

此命令如果不指定 file 参数，则默认用备份文件 EXPDAT.DMP 进行导入

如果指定 file 参数，则按照 file 指定的备份文件进行恢复

```
imp system/isjhd full=y file=water.dmp
```





### 2. 按用户导入与导出

按用户导出

```
exp system/itcast owner=wateruser file=wateruser.dmp
```



按用户导入

```
imp system/itcast file=wateruser.dmp fromuser=wateruser
```





### 3. 按表导入与导出

按表导出

```
exp wateruser/itcast file=a.dmp tables=t_account,a_area
```

用 tables 参数指定需要导出的表，如果有多个表用逗号分割即可



按表导入

```
imp wateruser/itcast file=a.dmp tables=t_account,a_area
```





## 八、单表查询

### 1. 简单条件查询

1. 精确查询

需求：查询水表编号为 30408 的业主记录

查询语句：

```sql
select * from T_OWNERS where watermeter='30408'
```



2. 模糊查询

需求：查询业主名称包含“刘”的业主记录

查询语句：

```sql
select * from t_owners where name like '%刘%'
```



3. and 运算符

需求：查询业主名称包含“刘”的并且门牌号包含 5 的业主记录

```sql
select * from t_owners where name like '%刘%' and housenumberlike '%5%'
```



4. or 运算符

需求：查询业主名称包含“刘”的或者门牌号包含 5 的业主记录

查询语句：

```sql
select * from t_owners 
	where name like '%刘%' or housenumber like '%5%'
```



5. and 与 or 运算符混合使用

需求：查询业主名称包含“刘”的或者门牌号包含 5 的业主记录，并且地址编号为 3 的记录。

语句：

```sql
select * from t_owners where (name like '%刘%' or housenumberlike '%5%') and addressid=3
```



6. 范围查询

需求：查询台账记录中用水字数大于等于 10000，并且小于等于20000 的记录

我们可以用>= 和<=来实现。

语句：

```sql
select * from T_ACCOUNT where usenum>=10000 and usenum<=20000
```

我们也可以用 between .. and ..来实现

```sql
select * from T_ACCOUNT where usenum between 10000 and 20000
```



7. 空值查询

需求：查询 T_PRICETABLE 表中 MAXNUM 为空的记录

语句：

```sql
select * from T_PRICETABLE t where maxnum is null
```



需求：查询 T_PRICETABLE 表中 MAXNUM 不为空的记录

语句：

```sql
select * from T_PRICETABLE t where maxnum is not null
```



### 2. 去掉重复记录

需求：查询业主表中的地址 ID,不重复显示

语句：

```sql
select distinct addressid from T_OWNERS
```



### 3. 排序查询

1. 升序排序

需求：对 T_ACCOUNT 表按使用量进行升序排序

语句：

```sql
select * from T_ACCOUNT order by usenum
```



2. 降序排序

需求：对 T_ACCOUNT 表按使用量进行降序排序

语句：

```sql
select * from T_ACCOUNT order by usenum desc
```



### 4. 基于伪列的查询

在 Oracle 的表的使用过程中，实际表中还有一些附加的列，称为伪列。

伪列就像表中的列一样，但是在表中并不存储。

伪列只能查询，不能进行增删改操作。接下来学习两个伪列：ROWID 和 ROWNUM。



1. ROWID

表中的每一行在数据文件中都有一个物理地址，ROWID 伪列返回的就是该行的物理地址。使用 ROWID 可以快速的定位表中的某一行。ROWID 值可以唯一的标识表中的一行。由于 ROWID 返回的是该行的物理地址，因此使用ROWID可以显示行是如何存储的。

查询语句：

```sql
select rowID,t.* from T_AREA t
```

查询结果如下：

![](Oracle.assets/44.png)

我们可以通过指定 ROWID 来查询记录

```sql
select rowID,t.*
    from T_AREA t
    where ROWID='AAAM1uAAGAAAAD8AAC';
```



2. ROWNUM

在查询的结果集中，ROWNUM 为结果集中每一行标识一个行号，第一行返回1，第二行返回 2，以此类推。通过 ROWNUM 伪列可以限制查询结果集中返回的行数。

查询语句：

```sql
select rownum,t.* from T_OWNERTYPE t
```

查询结果如下：

![](Oracle.assets/45.png)

我们的分页查询需要用到此伪列，在本章第四小节详细讲解。



### 5. 聚合统计

ORACLE 的聚合统计是通过分组函数来实现的，与 MYSQL 一致。



#### 5.1 聚合函数

1. 求和 sum

需求：统计 2012 年所有用户的用水量总和

```sql
select sum(usenum) from t_account where year='2012'
```



2. 求平均 avg

需求：统计 2012 年所有用水量（字数）的平均值

```sql
select avg(usenum) from T_ACCOUNT where year='2012'
```



3. 求最大值 max

需求：统计 2012 年最高用水量（字数）

```sql
select max(usenum) from T_ACCOUNT where year='2012'
```



4. 求最小值 min

需求：统计 2012 年最低用水量（字数）

```sql
select min(usenum) from T_ACCOUNT where year='2012'
```



5. 统计记录个数 count

需求：统计业主类型 ID 为 1 的业主数量

```sql
select count(*) from T_OWNERS t where ownertypeid=1
```



#### 5.2 分组聚合 Group by

需求：按区域分组统计水费合计数

语句：

```sql
select areaid,sum(money) from t_account group by areaid
```



#### 5.3 分组后条件查询 having

需求：查询水费合计大于 16900 的区域及水费合计

语句：

```sql
select areaid,sum(money) from t_account group by areaidhaving sum(money)>169000
```



## 九、连接查询

### 1. 多表内连接查询

1. 需求：查询显示业主编号，业主名称，业主类型名称，如下图：

![](Oracle.assets/46.png)

查询语句：

```sql
select o.id 业主编号,o.name 业主名称,ot.name 业主类型
	from T_OWNERS o,T_OWNERTYPE ot
		where o.ownertypeid=ot.id
```



2. 需求：查询显示业主编号，业主名称、地址和业主类型，如下图

![](Oracle.assets/47.png)

分析：此查询需要三表关联查询。分别是业主表，业主分类表和地址表

语句：

```sql
select o.id 业主编号,o.name 业主名称,ad.name 地址, ot.name 业主类型
from T_OWNERS o,T_OWNERTYPE ot,T_ADDRESS ad
where o.ownertypeid=ot.id and o.addressid=ad.id
```



### 2. 左外连接查询

需求：查询业主的账务记录，显示业主编号、名称、年、月、金额。如果此业主没有账务记录也要列出姓名。

![](Oracle.assets/48.png)

分析：我们要查询这个结果，需要用到 T_OWNERS（业主表），T_ACCOUNT（台账表） 按照查询结果，业主表为左表、账务表为右表。

按照 SQL1999 标准的语法，查询语句如下：

```sql
SELECT ow.id,ow.name,ac.year ,ac.month,ac.money
FROM T_OWNERS ow left join T_ACCOUNT ac
on ow.id=ac.owneruuid
```

按照 ORACLE 提供的语法，就很简单了：

```sql
SELECT ow.id,ow.name,ac.year ,ac.month,ac.money 
FROM T_OWNERS ow,T_ACCOUNT ac
WHERE ow.id=ac.owneruuid(+)
```

如果是左外连接，就在右表所在的条件一端填上（+）



### 3. 右外连接查询

需求：查询业主的账务记录，显示业主编号、名称、年、月、金额。如果账务记录没有对应的业主信息，也要列出记录。如下图：

![](Oracle.assets/49.png)

SQL1999 标准的语句

```sql
select ow.id,ow.name,ac.year,ac.month,ac.money 
from T_OWNERS ow right join T_ACCOUNT ac
on ow.id=ac.owneruuid
```

ORACLE 的语法

```sql
select ow.id,ow.name,ac.year,ac.month,ac.money 
from T_OWNERS ow , T_ACCOUNT ac
where ow.id(+) = ac.owneruuid
```



## 十、子查询

### 1. where 子句中的子查询

#### 1.1 单行子查询

- 只返回一条记录
- 单行操作符

![](Oracle.assets/50.png)

需求：查询 2012 年 1 月用水量大于平均值的台账记录

语句：

```sql
select * from T_ACCOUNT
where year='2012' and month='01' and usenum >
( select avg(usenum) from T_ACCOUNT where year='2012'and month='01' )
```



#### 1.2 多行子查询

- 返回了多条记录
- 多行操作符

![](Oracle.assets/51.png)





### 2. from 子句中的子查询

from 子句的子查询为多行子查询

需求：查询显示业主编号，业主名称，业主类型名称，条件为业主类型为”居民”，使用子查询实现。

语句：

```sql
select * from
(select o.id 业主编号,o.name 业主名称,ot.name 业主类型from T_OWNERS o,T_OWNERTYPE ot
where o.ownertypeid=ot.id)
where 业主类型='居民'
```



### 3. select 子句中的子查询

select 子句的子查询必须为单行子查询

需求：列出业主信息，包括 ID，名称，所属地址。

语句：

```sql
select id,name,
(select name from t_address where id=addressid) addressnamefrom t_owners
```





## 十一、分页查询

### 1. 简单分页

需求：分页查询台账表 T_ACCOUNT，每页 10 条记录

分析：我们在 ORACLE 进行分页查询，需要用到伪列ROWNUM和嵌套查询

我们首先显示前 10 条记录，语句如下：

```sql
select rownum, t.* from T_ACCOUNT t where rownum<=10
```

那么我们显示第 11 条到第 20 条的记录呢？编写语句：

```sql
select rownum, t.* from T_ACCOUNT t
where rownum>10 and rownum<=20
```

嗯？怎么没有结果？

这是因为 rownum 是在查询语句扫描每条记录时产生的，所以不能使用“大于”符号，只能使用“小于”或“小于等于” ，只用“等于”也不行。

那怎么办呢？我们可以使用子查询来实现

```sql
select * from
(select rownum r,t.* from T_ACCOUNT t where rownum<=20)
where r>10
```



### 2. 基于排序的分页

需求：分页查询台账表 T_ACCOUNT，每页 10 条记录，按使用字数降序排序。

我们查询第 2 页数据，如果基于上边的语句添加排序，语句如下：

```sql
select * from
(select rownum r,t.* from T_ACCOUNT t where rownum<=20 order by usenum desc)
where r>10
```

查询结果如下：

![](Oracle.assets/52.png)

经过验证，我们看到第 2 页的结果应该是下列记录

![](Oracle.assets/53.png)

所以推断刚才的语句是错误的！那为什么是错误的呢？

我们可以先单独执行嵌套查询里面的那句话

```sql
select rownum r,t.* from T_ACCOUNT t
where rownum<=20 order by usenum desc
```

你会看到查询结果如下：

![](Oracle.assets/54.png)

你会发现排序后的 R 是乱的。这是因为 ROWNUM 伪列的产生是在表记录扫描是产生的，而排序是后进行的，排序时 R 已经产生了，所以排序后R是乱的。

那该如何写呢？

很简单，我们只要再嵌套一层循环（一共三层），让结果先排序，然后对排序后的结果再产生 R，这样就不会乱了。

语句如下：

```sql
select * from
	(select rownum r,t.* from
		(select * from T_ACCOUNT order by usenum desc) t
			where rownum<=20 )
where r>10
```

结果如下：

![](Oracle.assets/55.png)





## 十二、单行函数

### 1. 字符函数

| 函数      | 说明                           |
| --------- | ------------------------------ |
| ASCII     | 返回对应字符的十进制值         |
| CHR       | 给出十进制返回字符             |
| CONCAT    | 拼接两个字符串，与\|\|相同     |
| INITCAT   | 将字符串的第一个字母变为大写   |
| INSTR     | 找出某个字符串的位置           |
| INSTRB    | 找出某个字符串的位置和字节数   |
| LENGTH    | 以字符给出字符串的长度         |
| LENGTHB   | 以字节给出字符串的长度         |
| LOWER     | 将字符串转换成小写             |
| LPAD      | 使用指定的字符在字符的左边填充 |
| LTRIM     | 在左边裁剪掉指定的字符         |
| RPAD      | 使用指定的字符在字符的右边填充 |
| RTRIM     | 在右边裁剪掉指定的字符         |
| REPLACE   | 执行字符串搜索和替换           |
| SUBSTR    | 取字符串的子串                 |
| SUBSTRB   | 取字符串的子串(以字节)         |
| SOUNDEX   | 返回一个同音字符串             |
| TRANSLATE | 执行字符串搜索和替换           |
| TRIM      | 裁剪掉前面或后面的字符串       |
| UPPER     | 将字符串变为大写               |

常用字符函数讲解：

1. 求字符串长度 LENGTH

语句：

```sql
select length('ABCD') from dual; # 结果：4
```



2. 求字符串的子串 SUBSTR

语句：

```sql
select substr('ABCD',2,2) from dual; # 结果：BC
```



3. 字符串拼接 CONCAT

语句：

```sql
select concat('ABC','D') from dual; # 结果：ABCD
```

注：我们也可以用 || 对字符串进行拼接，查询结果同上。





### 2. 数值函数

![](Oracle.assets/56.png)

![57](Oracle.assets/57.png)

常用数值函数讲解：

- 四舍五入函数 ROUND


语句：

```sql
select round(100.567) from dual # 结果 101
```

语句：

```sql
select round(100.567,2) from dual # 结果 100.57
```



- 截取函数 TRUNC


语句：

```sql
select trunc(100.567) from dual # 100
```

语句：

```sql
select trunc(100.567,2) from dual # 100.56
```



- 取模 MOD


语句：

```sql
select mod(10,3) from dual # 结果 1
```



### 3. 日期函数

![](Oracle.assets/58.png)

![59](Oracle.assets/59.png)



- 我们用 sysdate 这个系统变量来获取当前日期和时间


语句：

```sql
select sysdate from dual # 2016/10/11 星期二 下午 10:43:46
```



- 加月函数 ADD_MONTHS ：在当前日期基础上加指定的月


语句：

```sql
select add_months(sysdate,2) from dual # 2016/12/11 星期日 下午 10:43:46
```



- 求所在月最后一天 LAST_DAY


语句：

```sql
select last_day(sysdate) from dual # 2016/10/31 星期一 下午 10:43:46
```



- 日期截取 TRUNC


语句：

```sql
select TRUNC(sysdate) from dual -- 按日截取(把时间截掉) 2016/10/11 星期二
select TRUNC(sysdate,'mm') from dual -- 按月截取(把日截掉) 2016/10/1 星期六
select TRUNC(sysdate,'yyyy') from dual -- 按年截取(把月截掉) 2016/1/1 星期五
```



### 4. 转换函数

![](Oracle.assets/60.png)

![61](Oracle.assets/61.png)



- 数字转字符串 TO_CHAR

语句：

```sql
select TO_CHAR(1024) from dual
```



- 日期转字符串 TO_CHAR

语句：

```sql
select TO_CHAR(sysdate,'yyyy-mm-dd') from dual
```

语句：

```sql
select TO_CHAR(sysdate,'yyyy-mm-dd hh:mi:ss') from dual
```



- 字符串转日期 TO_DATE

语句：

```sql
select TO_DATE('2017-01-01','yyyy-mm-dd') from dual
```



- 字符串转数字 TO_NUMBER

语句：

```sql
select to_number('100') from dual
```



### 5. 其它函数

- 空值处理函数 NVL

用法：NVL（检测的值，如果为 null 的值）；

语句：

```sql
select NVL(NULL,0) from dual
```



- 空值处理函数 NVL2

用法： NVL2（检测的值，如果不为 null 的值，如果为 null 的值）；

语句：

```sql
select NVL2(NULL,0, 1) from dual
```



- 条件取值 decode

语法：

```sql
decode(条件,值 1,翻译值 1,值 2,翻译值 2,...值 n,翻译值n,缺省值)
【功能】根据条件返回相应值
```

需求：显示下列信息（不要关联查询业主类型表，直接判断1 2 3 的值）

![](Oracle.assets/62.png)

语句：

```sql
select name, decode( ownertypeid,1,' 居民',2,' 行政事业单位',3,'商业') as 类型 from T_OWNERS
```

上边的语句也可以用 case when then 语句来实现

```sql
select name ,(case ownertypeid
                when 1 then '居民'
                when 2 then '行政事业单位'
                when 3 then '商业'
                else '其它'
                end
                ) from T_OWNERS
```

还有另外一种写法：

```sql
select name,(case
             when ownertypeid= 1 then '居民'
             when ownertypeid= 2 then '行政事业'
             when ownertypeid= 3 then '商业'
             end )
             from T_OWNERS
```



## 十三、行列转换

需求：按月份统计 2012 年各个地区的水费，如下图

![](Oracle.assets/63.png)

```sql
select (select name from T_AREA where id= areaid )区域,
	sum( case when month='01' then money else 0 end) 一月,
	sum( case when month='02' then money else 0 end) 二月,
	sum( case when month='03' then money else 0 end) 三月,
	sum( case when month='04' then money else 0 end) 四月,
	sum( case when month='05' then money else 0 end) 五月,
	sum( case when month='06' then money else 0 end) 六月,
	sum( case when month='07' then money else 0 end) 七月,
	sum( case when month='08' then money else 0 end) 八月,
	sum( case when month='09' then money else 0 end) 九月,
	sum( case when month='10' then money else 0 end) 十月,
	sum( case when month='11' then money else 0 end) 十一月,
	sum( case when month='12' then money else 0 end) 十二月
	from T_ACCOUNT where year='2012' group by areaid
```



需求：按季度统计 2012 年各个地区的水费，如下图

![](Oracle.assets/64.png)

语句如下：

```sql
select (select name from T_AREA where id= areaid )区域,
	sum( case when month>='01' and month<='03' then moneyelse0 end) 第一季度,
	sum( case when month>='04' and month<='06' then moneyelse0 end) 第二季度,
	sum( case when month>='07' and month<='09' then moneyelse0 end) 第三季度,
	sum( case when month>='10' and month<='12' then moneyelse0 end) 第四季度
	from T_ACCOUNT where year='2012' group by areaid
```





## 十四、分析函数

以下三个分析函数可以用于排名使用。

下图为三种排名方式的举例

![](Oracle.assets/65.png)



1. RANK 相同的值排名相同，排名跳跃

需求：对 T_ACCOUNT 表的 usenum 字段进行排序，相同的值排名相同，排名跳跃。

语句：

```sql
select rank() over(order by usenum desc ), usenum from T_ACCOUNT
```



2. DENSE_RANK 相同的值排名相同，排名连续

需求：对 T_ACCOUNT 表的 usenum 字段进行排序，相同的值排名相同，排名连续

语句：

```sql
select dense_rank() over(order by usenum desc ),usenum from T_ACCOUNT
```



3. ROW_NUMBER 返回连续的排名，无论值是否相等

需求：对 T_ACCOUNT 表的 usenum 字段进行排序，返回连续的排名，无论值是否相等

语句：

```sql
select row_number() over(order by usenum desc ),usenum from T_ACCOUNT
```





## 十五、集合运算

### 1. 什么是集合运算

集合运算，集合运算就是将两个或者多个结果集组合成为一个结果集。

集合运算包括：

- UNION ALL(并集)，返回各个查询的所有记录，包括重复记录。
- UNION(并集)，返回各个查询的所有记录，不包括重复记录。
- INTERSECT(交集)，返回两个查询共有的记录。
- MINUS(差集)，去除第一个查询检索出的记录和第二个查询检索出的记录，之后剩余的记录。

![](Oracle.assets/66.png)



- UNION ALL(并集)，返回各个查询的所有记录，包括重复记录。

```sql
select * from t_owners where id<=7
union all
select * from t_owners where id>=5
```



- UNION(并集)，返回各个查询的所有记录，不包括重复记录。


```sql
select * from t_owners where id<=7
union
select * from t_owners where id>=5
```



- INTERSECT(交集)，返回两个查询共有的记录。


```sql
select * from t_owners where id<=7
intersect
select * from t_owners where id>=5
```

![](Oracle.assets/67.png)





- MINUS(差集)，去除第一个查询检索出的记录和第二个查询检索出的记录，之后剩余的记录。


```sql
select * from t_owners where id<=7
minus
select * from t_owners where id>=5
```

![](Oracle.assets/68.png)





## 十六、视图

### 1. 什么是视图

视图是一种数据库对象，是从一个或者多个数据表或视图中导出的虚表，视 图所对应的数据并不真正地存储在视图中，而是存储在所引用的数据表中，视图 的结构和数据是对数据表进行查询的结果。

根据创建视图时给定的条件，视图可以是一个数据表的一部分，也可以是多 个基表的联合，它存储了要执行检索的查询语句的定义，以便在引用该视图时使 用。

使用视图的优点：

1. 简化数据操作：视图可以简化用户处理数据的方式。
2. 着重于特定数据：不必要的数据或敏感数据可以不出现在视图中。
3. 视图提供了一个简单而有效的安全机制，可以定制不同用户对数据的访问
   权限。
4. 提供向后兼容性：视图使用户能够在表的架构更改时为表创建向后兼容接
   口。



### 2. 创建或修改视图语法

```sql
CREATE [OR REPLACE] [FORCE] VIEW view_name
AS subquery
[WITH CHECK OPTION ]
[WITH READ ONLY]
```

选项解释：

- OR REPLACE ：若所创建的试图已经存在，ORACLE 自动重建该视图；
- FORCE ：不管基表是否存在 ORACLE 都会自动创建该视图；
- subquery ：一条完整的 SELECT 语句，可以在该语句中定义别名；
- WITH CHECK OPTION ：插入或修改的数据行必须满足视图定义的约束；
- WITH READ ONLY ：该视图上不能进行任何 DML 操作。



### 3. 删除视图语法

```sql
DROP VIEW view_name
```



### 4. 案例

1. 简单视图的创建与使用

需求：创建视图 ：业主类型为 1 的业主信息

语句：

```sql
create or replace view view_owners1 as select * from T_OWNERS where ownertypeid=1
```

利用该视图进行查询

```sql
select * from view_owners1 where addressid=1;
```

就像使用表一样去使用视图就可以了。 

对于简单视图，我们不仅可以用查询，还可以增删改记录。

视图其实是一个虚拟的表，它 的数据其实来自于表。如果更改了视图的数据，表的数据也自然会变化，更改了 表的数据，视图也自然会变化。一个视图所存储的并不是数据，而是一条 SQL 语句。



2. 带检查约束的视图

需求：根据地址表（T_ADDRESS）创建视图 VIEW_ADDRESS2 ,内容为区域 ID 为 2 的记录。

语句：

```sql
create or replace view view_address2 as
select * from T_ADDRESS where areaid=2
with check option
```

执行下列更新语句：

```sql
-- 无法修改成功的语句，因为该视图的条件是areaid=2
update view_address2 set areaid=1 where id=4
```

系统提示如下错误信息：

![](Oracle.assets/69.png)



3. 只读视图的创建与使用

如果我们创建一个视图，并不希望用户能对视图进行修改，那我们就需要创建视 图时指定 WITH READ ONLY 选项，这样创建的视图就是一个只读视图。

需求：将上边的视图修改为只读视图

语句：

```sql
create or replace view view_owners1 as 
select * from T_OWNERS where ownertypeid=1 
with read only
```

修改后，再次执行 update 语句，会出现如下错误提示

![](Oracle.assets/70.png)





4. 创建带错误的视图

我们创建一个视图，如果视图的 SQL 语句所设计的表并不存在，如下

```sql
create or replace view view_TEMP as
select * from T_TEMP
```

T_TEMP 表并不存在，此时系统会给出错误提示

![](Oracle.assets/71.png)

有的时候，我们创建视图时的表可能并不存在，但是以后可能会存在，我们如果 此时需要创建这样的视图，需要添加 FORCE 选项，SQL 语句如下：

```sql
create or replace FORCE view view_TEMP as
select * from T_TEMP
```

此时视图创建成功。



5. 复杂视图的创建与使用

所谓复杂视图，就是视图的 SQL 语句中，有聚合函数或多表关联查询。

1. 多表关联查询的例子

需求： 创建视图，查询显示业主编号，业主名称，业主类型名称

语句：

```sql
create or replace view view_owners as
select o.id 业主编号,o.name 业主名称,ot.name 业主类型
from T_OWNERS o,T_OWNERTYPE ot
where o.ownertypeid=ot.id
```

使用该视图进行查询

```sql
select * from view_owners
```

那这个视图能不能去修改数据呢？

我们试一下下面的语句：

```sql
update view_owners set 业主名称='范小冰' where 业主编号=1;
```

可以修改成功。

我们再试一下下面的语句：

```sql
update view_owners set 业主类型='普通居民' where 业主编号=1;
```

这次我们会发现，系统弹出错误提示：

![](Oracle.assets/72.png)

这个是什么意思？是说我们所需改的列不属于键保留表的列。

什么叫键保留表呢？

键保留表是理解连接视图修改限制的一个基本概念。该表的主键列全部显示在视 图中,并且它们的值在视图中都是唯一且非空的。也就是说，表的键值在一个连 接视图中也是键值，那么就称这个表为键保留表。

在我们这个例子中，视图中存在两个表，业主表（T_OWNERS）和业主类型表 （T_OWNERTYPE）, 其中 T_OWNERS 表就是键保留表，因为 T_OWNERS 的 主键也是作为视图的主键。键保留表的字段是可以更新的，而非键保留表是不能 更新的。





2. 分组聚合统计查询的例子

需求：创建视图，按年月统计水费金额，效果如下

![](Oracle.assets/73.png)

语句：

```sql
create view view_accountsum as
select year,month,sum(money) moneysum
from T_ACCOUNT
group by year,month
order by year,month
```

此例用到聚合函数，没有键保留表，所以无法执行 update 。

![](Oracle.assets/74.png)





## 十七、物化视图

### 1. 什么是物化视图

视图是一个虚拟表（也可以认为是一条语句），基于它创建时指定的查询语 句返回的结果集。每次访问它都会导致这个查询语句被执行一次。为了避免每次访问都执行这个查询，可以将这个查询结果集存储到一个物化视图（也叫实体化 视图）。

物化视图与普通的视图相比的区别是物化视图是建立的副本，它类似于一张 表，需要占用存储空间。而对一个物化视图查询的执行效率与查询一个表是一样 的。



### 2. 创建物化视图语法

```sql
CREATE METERIALIZED VIEW view_name
[BUILD IMMEDIATE | BUILD DEFERRED ]
REFRESH [FAST|COMPLETE|FORCE]
[
ON [COMMIT |DEMAND ] | START WITH (start_time) NEXT
(next_time)
]
AS
subquery
```

BUILD IMMEDIATE 是在创建物化视图的时候就生成数据

BUILD DEFERRED 则在创建时不生成数据，以后根据需要再生成数据。 默认为 BUILD IMMEDIATE。

刷新（REFRESH）：指当基表发生了 DML 操作后，物化视图何时采用哪种 方式和基表进行同步。

REFRESH 后跟着指定的刷新方法有三种：FAST、COMPLETE、FORCE。FAST 刷新采用增量刷新，只刷新自上次刷新以后进行的修改。COMPLETE 刷新对整 个物化视图进行完全的刷新。如果选择 FORCE 方式，则 Oracle 在刷新时会去判 断是否可以进行快速刷新，如果可以则采用 FAST 方式，否则采用 COMPLETE的方式。FORCE 是默认的方式。

刷新的模式有两种：ON DEMAND 和 ON COMMIT。ON DEMAND 指需要 手动刷新物化视图（默认）。ON COMMIT 指在基表发生 COMMIT 操作时自动 刷新。



### 3. 案例

1. 创建手动刷新的物化视图

需求：查询地址 ID,地址名称和所属区域名称, 结果如下：

![](Oracle.assets/75.png)

语句：

```sql
create materialized view mv_address
as
select ad.id,ad.name adname,ar.name ar_name
from t_address ad,t_area ar
where ad.areaid=ar.id
```

执行上边的语句后查询

```sql
select * from mv_address;
```

查询结果如下：

![](Oracle.assets/75.png)

这时，我们向地址表（T_ADDRESS）中插入一条新记录，

```sql
insert into t_address values(8,'宏福苑小区',1,1);
```

再次执行上边的语句进行查询，会发现新插入的语句并没有出现在物化视图中。

我们需要通过下面的语句（PL/SQL），手动刷新物化视图：

```sql
begin
DBMS_MVIEW.refresh('MV_ADDRESS','C');
end;
```

或者通过下面的命令手动刷新物化视图：

```sql
EXEC DBMS_MVIEW.refresh('MV_ADDRESS','C');
# 注意：此语句需要在命令窗口中执行。
```

执行此命令后再次查询物化视图，就可以查询到最新的数据了。

DBMS_MVIEW.refresh 实际上是系统内置的存储过程，关于存储过程我们在第 4 章会详细讲解。



2. 创建自动刷新的物化视图，和上例一样的结果集

语句如下：

```sql
create materialized view mv_address2
refresh
on commit
as
select ad.id,ad.name adname,ar.name ar_name
from t_address ad,t_area ar
where ad.areaid=ar.id
```

创建此物化视图后，当 T_ADDRESS 表发生变化时，MV_ADDRESS2 自动跟着 改变。



3. 创建时不生成数据的物化视图

```sql
create materialized view mv_address3
build deferred
refresh
on commit
as
select ad.id,ad.name adname,ar.name ar_name
from t_address ad,t_area ar
where ad.areaid=ar.id;
```

创建后执行下列语句查询物化视图

```sql
select * from mv_address3
```

查询结果：

![](Oracle.assets/76.png)

执行下列语句生成数据

```sql
begin
DBMS_MVIEW.refresh('MV_ADDRESS3','C');
end;
```

再次查询，得到结果：

![](Oracle.assets/77.png)

由于我们创建时指定的 on commit ,所以在修改数据后能立刻看到最新数据，无须 再次执行 refresh 



4. 创建增量刷新的物化视图

如果创建增量刷新的物化视图，必须首先创建物化视图日志

```sql
create materialized view log on t_address with rowid；
create materialized view log on t_area with rowid
```

创建的物化视图日志名称为 MLOG$_表名称



创建物化视图

```sql
create materialized view mv_address4
refresh fast
as
select ad.rowid adrowid ,ar.rowid arrowid, ad.id,ad.name
adname,ar.name ar_name
from t_address ad,t_area ar
where ad.areaid=ar.id;
```

注意：创建增量刷新的物化视图，必须：

1. 创建物化视图中涉及表的物化视图日志。
2. 在查询语句中，必须包含所有表的 rowid(以 rowid 方式建立物化视图日志)



当我们向地址表插入数据后，物化视图日志的内容：

![](Oracle.assets/78.png)

SNAPTIME$$：用于表示刷新时间。

DMLTYPE$$：用于表示 DML 操作类型，I 表示 INSERT，D 表示 DELETE，U 表示 UPDATE。

OLD_NEW$$：用于表示这个值是新值还是旧值。N（EW）表示新值，O（LD） 表示旧值，U 表示 UPDATE 操作。

CHANGE_VECTOR$$：表示修改矢量，用来表示被修改的是哪个或哪几个字段。

此列是 RAW 类型，其实 Oracle 采用的方式就是用每个 BIT 位去映射一个列。

插入操作显示为：FE, 删除显示为：OO 更新操作则根据更新字段的位置而显示 不同的值。

当我们手动刷新物化视图后，物化视图日志被清空，物化视图更新。

```sql
begin
DBMS_MVIEW.refresh('MV_ADDRESS4','C');
end;
```



## 十八、序列

> 序列是 ORACLE 提供的用于产生一系列唯一数字的数据库对象。
>



### 1. 创建与使用简单序列

创建序列语法：

```sql
create sequence 序列名称
```

通过序列的伪列来访问序列的值

- NEXTVAL 返回序列的下一个值
- CURRVAL 返回序列的当前值



注意：我们在刚建立序列后，无法提取当前值，只有先提取下一个值时才能再次提取当前值。

- 提取下一个值


```sql
select 序列名称.nextval from dual
```

- 提取当前值


```sql
select 序列名称.currval from dual
```



### 2. 创建复杂序列

语法：

```sql
CREATE SEQUENCE sequence # 创建序列名称
	[INCREMENT BY n] # 递增的序列值是 n 如果 n 是正数就递增,如果是负数就递减,默认是 1
	[START WITH n] # 开始的值,递增默认是 minvalue 递减是 maxvalue
	[{MAXVALUE n | NOMAXVALUE}] # 最大值
	[{MINVALUE n | NOMINVALUE}] # 最小值
	[{CYCLE | NOCYCLE}] # 循环/不循环
	[{CACHE n | NOCACHE}]; # 分配并存入到内存中
```



### 3. 案例

#### 3.1 有最大值的非循环序列

创建序列的语句：

```sql
create sequence seq_test1
increment by 10
start with 10
maxvalue 300
minvalue 20
```

![](Oracle.assets/79.png)

以上的错误，是由于我们的开始值小于最小值 。开始值不能小于最小值，修改以上语句：

```sql
create sequence seq_test1
increment by 10
start with 10
maxvalue 300
minvalue 5
```

我们执行下列语句提取序列值，当序列值为 300（最大值）的时候再次提取值，系统会报异常信息。

![](Oracle.assets/80.png)



#### 3.2 有最大值的循环序列

```sql
create sequence seq_test2
increment by 10
start with 10
maxvalue 300
minvalue 5
cycle;
```

当序列当前值为 300（最大值），再次提取序列的值

```sql
select seq_test2.nextval from dual
```

提取的值为：5

由此我们得出结论，循环的序列，第一次循环是从开始值开始循环，而第二次循环是从最小值开始循环。



思考问题： 下列语句是否会报错？为什么？

```sql
create sequence seq_test3
increment by 10
start with 10
minvalue 5
cycle ;
```

答：此为错误的语句。因为你创建的是一个循环的序列，所以必须指定最大值，否则会报错。



#### 3.3 带缓存的序列

我们执行下列语句：

```sql
create sequence seq_test3
increment by 10
start with 10
maxvalue 300
minvalue 5
cycle
cache 50;
```

我们执行上边语句的意思是每次取出 50 个缓存值，但是执行会提示错误

![](Oracle.assets/81.png)

上边错误提示的意思是：缓存设置的数必须小于每次循环的数。

我们缓存设定的值是 50，而最大值是 300，那么为什么还会提示这样的信息呢？

其实我们的 cache 虽然是 50，但是我们每次增长值是 10。这样 50 次缓存提取出 的数是 500 （50*10）



我们更改为下列的语句：

```sql
create sequence seq_test4
increment by 10
start with 10
maxvalue 500
minvalue 10
cycle
cache 50;
```

下列语句依然会提示上边的错误，这是因为还存在一个 minvalue ，minvalue 和 maxvalue 之间是 490 个数，也就是一次循环可以提取 490，但是我们的缓存是 500。

我们再次修改语句：

```sql
create sequence seq_test5
increment by 10
start with 10
maxvalue 500
minvalue 9
cycle
cache 50;
```

把最小值减 1，或把最大值加 1，都可以通过。



### 4. 修改和删除序列

修改序列：使用 ALTER SEQUENCE 语句修改序列，不能更改序列的 START WITH 参数

```sql
ALTER SEQUENCE 序列名称 MAXVALUE 5000 CYCLE;
```

删除序列：

```sql
DROP SEQUENCE 序列名称;
```





## 十九、同义词

### 1. 什么是同义词

同义词实质上是指定方案对象的一个别名。通过屏蔽对象的名称和所有者以 及对分布式数据库的远程对象提供位置透明性，同义词可以提供一定程度的安全 性。同时，同义词的易用性较好，降低了数据库用户的 SQL 语句复杂度。 同义词允许基对象重命名或者移动，这时，只需对同义词进行重定义，基于同义 词的应用程序可以继续运行而无需修改。

你可以创建公共同义词和私有同义词。其中，公共同义词属于 PUBLIC 特殊 用户组，数据库的所有用户都能访问；而私有同义词包含在特定用户的方案中， 只允许特定用户或者有基对象访问权限的用户进行访问。

同义词本身不涉及安全，当你赋予一个同义词对象权限时，你实质上是在给 同义词的基对象赋予权限，同义词只是基对象的一个别名。



### 2. 创建与使用同义词

创建同义词的具体语法是：

```sql
create [public] SYNONYM synooym for object;
```

其中 synonym 表示要创建的同义词的名称，object 表示表，视图，序列等我们要 创建同义词的对象的名称。



### 3. 案例

#### 3.1 私有同义词

需求：为表 T_OWNERS 创建( 私有 )同义词 名称为 OWNERS 

语句：

```sql
create synonym OWNERS for T_OWNERS;
```

使用同义词：

```sql
select * from OWNERS;
```

查询结果如下：

![](Oracle.assets/82.png)





#### 3.2 公有同义词

需求：为表 T_OWNERS 创建( 公有 )同义词 名称为 OWNERS2：

```sql
create public synonym OWNERS2 for T_OWNERS;
```

以另外的用户登陆，也可以使用公有同义词：

```sql
select * from OWNERS2;
```





## 二十、索引

### 1. 什么是索引

索引是用于加速数据存取的数据对象。合理的使用索引可以大大降低 i/o 次 数,从而提高数据访问性能。

索引是需要占据存储空间的，也可以理解为是一种特殊的数据。形式类似于 下图的一棵“树”，而树的节点存储的就是每条记录的物理地址，也就是我们提 到的伪列（ROWID）

![](Oracle.assets/83.png)



### 2. 普通索引

语法：

```sql
create index 索引名称 on 表名(列名);
```

需求：我们经常要根据业主名称搜索业主信息，所以我们基于业主表的 name 字 段来建立索引。语句如下：

```sql
create index index_owners_name on T_OWNERS(name)
```



索引性能测试：

创建一个两个字段的表

```sql
create table T_INDEXTEST (
    ID NUMBER,
    NAME VARCHAR2(30)
);
```

编写 PL/SQL 插入 100 万条记录（关于 PL/SQL 我们在第四章会学到）

```sql
BEGIN
 FOR i in 1..1000000
 loop
 	INSERT INTO T_INDEXTEST VALUES(i,'AA'||i);
 end loop;
 commit;
END;
```

创建完数据后，根据 name 列创建索引

```sql
CREATE INDEX INDEX_TESTINDEX on T_INDEXTEST(name)
```

执行下面两句 SQL 执行

```sq;
SELECT * from T_INDEXTEST where ID=765432;
SELECT * from T_INDEXTEST where NAME='AA765432';
```

我们会发现根据 name 查询所用的时间会比根据 id 查询所用的时间要短



### 3. 唯一索引

如果我们需要在某个表某个列创建索引，而这列的值是不会重复的。这是我们可 以创建唯一索引。

语法：

```sql
create unique index 索引名称 on 表名(列名);
```

需求：在业主表的水表编号一列创建唯一索引

语句：

```sql
create unique index index_owners_watermeter on T_OWNERS(watermeter);
```



### 4. 复合索引

我们经常要对某几列进行查询，比如，我们经常要根据学历和性别对学员进行搜 索，如果我们对这两列建立两个索引，因为要查两棵树，查询性能不一定高。那 如何建立索引呢？我们可以建立复合索引，也就是基于两个以上的列建立一个索 引 。

语法：

```sql
create index 索引名称 on 表名(列名,列名.....);
```

根据地址和门牌号对学员表创建索引，语句如下：

```sql
create index owners_index_ah on T_OWNERS(addressid,housenumber);
```



### 5. 反向键索引

应用场景：当某个字段的值为连续增长的值，如果构建标准索引，会形成歪脖子 树。这样会增加查询的层数，性能会下降。建立反向键索引，可以使索引的值变 得不规则，从而使索引树能够均匀分布。

![](Oracle.assets/84.png)

语法：

```sql
create index 索引名称 on 表名(列名) reverse;
```



### 6. 位图索引

使用场景：位图索引适合创建在低基数列上 

位图索引不直接存储 ROWID，而是存储字节位到 ROWID 的映射

优点：减少响应时间，节省空间占用

语法：

```sql
create bitmap index 索引名称 on 表名(列名);
```

需求：我们在 T_owners 表的 ownertypeid 列上建立位图索引，语句：

```sql
create bitmap index index_owners_typeid on T_OWNERS(ownertypeid)
```

注意：

- 适合重复数多的列。
- 只能用等号，不能做范围查询(能查，但是不走索引)。

![](Oracle.assets/85.png)







## 二十一、PL/SQL

### 1. 什么是 PL/SQL

PL/SQL（Procedure Language/SQL）是 Oracle 对 sql 语言的过程化扩展，指 在 SQL 命令语言中增加了过程处理语句（如分支、循环等），使 SQL 语言具有 过程处理能力。把 SQL 语言的数据操纵能力与过程语言的数据处理能力结合起 来，使得 PLSQL 面向过程但比过程语言简单、高效、灵活和实用。

基本语法结构

```sql
[declare
 --声明变量
 ]
begin
 --代码逻辑
[exception
 --异常处理
 ]
end;
```



### 2. 变量

声明变量的语法：

```sql
变量名 类型（长度）;
```

变量赋值的语法：

```sql
变量名:=变量值
```



变量的声明：

需求：

声明变量水费单价、水费字数、吨数、金额。

对水费单价、字数、进行赋值 。吨数根据水费字数换算，规则为水费字数除以 1000，并且四舍五入，保留两位小数。计算金额，金额=单价*吨数。

输出单价 、数量和金额。

```sql
--变量的用法--
declare
 v_price number(10,2);--水费单价
 v_usenum number; --水费字数
 v_usenum2 number(10,2);--吨数
 v_money number(10,2);--金额
begin
 v_price:=2.45;--水费单价
 v_usenum:=8012;--字数
 --字数换算为吨数
 v_usenum2:= round( v_usenum/1000,2);
 --计算金额
 v_money:=round(v_price*v_usenum2,2);
 dbms_output.put_line('单价：'||v_price||'吨
数:'||v_usenum2||'金额：'||v_money);
end;
```

Select into 方式 赋值

语法结构：

```sql
select 列名 into 变量名 from 表名 where 条件
```

注意：结果必须是一条记录 ，有多条记录和没有记录都会报错

```sql
declare
 v_price number(10,2);--单价
 v_usenum number;--水费字数
 v_num0 number;--上月字数
 v_num1 number;--本月字数
 v_usenum2 number(10,2);--使用吨数
 v_money number(10,2);--水费金额
begin
 --对单价进行赋值
 v_price:=3.45;
 --变量赋值
 select usenum,num0,num1 into v_usenum,V_num0,V_num1 from
T_ACCOUNT
 where year='2012' and month='01' and owneruuid=1;
 v_usenum2:= round(v_usenum/1000,2);
 v_money:=v_price*v_usenum2;
 
 DBMS_OUTPUT.put_line('单价：'||v_price||'吨数：' ||v_usenum2||'金额：'||v_money||'上月字数：'||v_num0||'本月字数'||v_num1);
end;
```



### 3. 属性类型

%TYPE 引用型 

作用：引用某表某列的字段类型

```sql
declare
 v_price number(10,2);--单价
 v_usenum T_ACCOUNT.USENUM%TYPE;--水费字数
 v_num0 T_ACCOUNT.NUM0%TYPE;--上月字数
 v_num1 T_ACCOUNT.NUM1%TYPE;--本月字数
 v_usenum2 number(10,2);--使用吨数
 v_money number(10,2);--水费金额
begin
 --对单价进行赋值
 v_price:=3.45;
 --v_usenum:=8090;
 select usenum,num0,num1 into v_usenum,V_num0,V_num1 from T_ACCOUNT
 where year='2012' and month='01' and owneruuid=1;
 --使用吨数
 v_usenum2:= round(v_usenum/1000,2);
 --计算金额
 v_money:=v_price*v_usenum2;
 DBMS_OUTPUT.put_line('单价：'||v_price||'吨数：' ||v_usenum2||'金额：'||v_money||'上月字数：'||v_num0||'本月字数'||v_num1);
end;
```



%ROWTYPE 记录型 ，上例中的例子可以用下面的代码代替

作用: 标识某个表的行记录类型

```sql
--变量的用法--
declare
 v_price number(10,2);--单价
 v_account T_ACCOUNT%ROWTYPE;--记录型
 v_usenum2 number(10,2);--使用吨数
 v_money number(10,2);--水费金额
begin
 --对单价进行赋值
 v_price:=3.45;
 --赋值
 select * into v_account from T_ACCOUNT
 where year='2012' and month='01' and owneruuid=1;
 --使用吨数
 v_usenum2:= round(v_account.usenum/1000,2);
 --计算金额
 v_money:=v_price*v_usenum2;
 DBMS_OUTPUT.put_line('单价：'||v_price||'吨数：'
 ||v_usenum2||'金额：'||v_money||'上月字数：
'||v_account.num0||'本月字数'||v_account.num1);
end;
```



### 4. 异常

在运行程序时出现的错误叫做异常

发生异常后，语句将停止执行，控制权转移到 PL/SQL 块的异常处理部分

异常有两种类型：

- 预定义异常 - 当 PL/SQL 程序违反 Oracle 规则或超越系统限制时隐式引发
- 用户定义异常 - 用户可以在 PL/SQL 块的声明部分定义异常，自定义的 异常通过 RAISE 语句显式引发



#### 4.1 预定义异常

Oracle 预定义异常 21 个

| 命名的系统异常          | 产生原因                                                     |
| ----------------------- | ------------------------------------------------------------ |
| ACCESS_INTO_NULL        | 未定义对象                                                   |
| CASE_NOT_FOUND          | CASE 中若未包含相应的 WHEN ，并且没有设置 ELSE 时            |
| COLLECTION_IS_NULL      | 集合元素未初始化                                             |
| CURSER_ALREADY_OPEN     | 游标已经打开                                                 |
| DUP_VAL_ON_INDEX        | 唯一索引对应的列上有重复的值                                 |
| INVALID_CURSOR          | 在不合法的游标上进行操作                                     |
| INVALID_NUMBER          | 内嵌的 SQL 语句不能将字符转换为数字                          |
| NO_DATA_FOUND           | 使用 select into 未返回行                                    |
| TOO_MANY_ROWS           | 执行 select into 时，结果集超过一行                          |
| ZERO_DIVIDE             | 除数为 0                                                     |
| SUBSCRIPT_BEYOND_COUNT  | 元素下标超过嵌套表或 VARRAY 的最大值                         |
| SUBSCRIPT_OUTSIDE_LIMIT | 使用嵌套表或 VARRAY 时，将下标指定为负数                     |
| VALUE_ERROR             | 赋值时，变量长度不足以容纳实际数据                           |
| LOGIN_DENIED            | PL/SQL 应用程序连接到 oracle 数据库时，提供了不正确的用户名或密码 |
| NOT_LOGGED_ON           | PL/SQL 应用程序在没有连接 oralce 数据库的情况下访问数据      |
| PROGRAM_ERROR           | PL/SQL 内部问题，可能需要重装数据字典＆ pl./SQL 系统包       |
| ROWTYPE_MISMATCH        | 宿主游标变量与 PL/SQL 游标变量的返回类型不兼容               |
| SELF_IS_NULL            | 使用对象类型时，在 null 对象上调用对象方法                   |
| STORAGE_ERROR           | 运行 PL/SQL 时，超出内存空间                                 |
| SYS_INVALID_ID          | 无效的 ROWID 字符串                                          |
| TIMEOUT_ON_RESOURCE     | Oracle 在等待资源时超时                                      |

语法结构：

```sql
exception
 when 异常类型 then
 异常处理逻辑
```

根据上例中的代码，添加异常处理部分

```sql
--变量的用法--
declare
 v_price number(10,2);--水费单价
 v_usenum T_ACCOUNT.USENUM%type; --水费字数
 v_usenum2 number(10,3);--吨数
 v_money number(10,2);--金额
begin
 v_price:=2.45;--水费单价
 select usenum into v_usenum from T_ACCOUNT where
owneruuid=1 and year='2012' and month='01';
 --字数换算为吨数
 v_usenum2:= round( v_usenum/1000,3);
 --计算金额
 v_money:=round(v_price*v_usenum2,2);
 dbms_output.put_line('单价：'||v_price||'吨
数:'||v_usenum2||'金额：'||v_money);
exception
 when NO_DATA_FOUND then
 dbms_output.put_line('未找到数据，请核实');
 when TOO_MANY_ROWS then
 dbms_output.put_line('查询条件有误，返回多条信息，请核实');
end;
```



### 5.条件判断

基本语法 1

```sql
if 条件 then
 业务逻辑
end if;
```

基本语法 2

```sql
if 条件 then
 业务逻辑
else
 业务逻辑
end if;
```

基本语法 3

```sql
if 条件 then
 业务逻辑
elsif 条件 then
 业务逻辑
else
 业务逻辑
end if;
```

需求：设置三个等级的水费 5 吨以下 2.45 元/吨 5 吨到 10 吨部分 3.45 元/吨 ， 超过 10 吨部分 4.45 ，根据使用水费的量来计算阶梯水费。

```sql
declare
 v_price1 number(10,2);--不足 5 吨的单价
 v_price2 number(10,2);--超过 5 吨不足 10 吨单价
 v_price3 number(10,2);--超过 10 吨
 v_account T_ACCOUNT%ROWTYPE;--记录型
 v_usenum2 number(10,2);--使用吨数
 v_money number(10,2);--水费金额
begin

 --对单价进行赋值
 v_price1:=2.45;
 v_price2:=3.45;
 v_price3:=4.45;
 
 --赋值
 select * into v_account from T_ACCOUNT
 where year='2012' and month='01' and owneruuid=1;
 
 --使用吨数
 v_usenum2:= round(v_account.usenum/1000,2);

 --计算金额(阶梯水费)
 if v_usenum2<=5 then--第一个阶梯
 	v_money:=v_price1*v_usenum2;
 elsif v_usenum2>5 and v_usenum2<=10 then --第二个阶梯
 	v_money:=v_price1*5 + v_price2*( v_usenum2-5);
 else --第三个阶梯
 	v_money:=v_price1*5 +v_price2*5 + v_price3*( v_usenum2-10 );
 end if;
 
 DBMS_OUTPUT.put_line('吨数：'
 ||v_usenum2||'金额：'||v_money||'上月字数：
'||v_account.num0||'本月字数'||v_account.num1);

exception
 when NO_DATA_FOUND then
 DBMS_OUTPUT.put_line('没有找到数据');
 when TOO_MANY_ROWS then
 DBMS_OUTPUT.put_line('返回的数据有多行');
end;
```



### 6. 循环

1. 无条件循环

语法结构

```sql
loop
	--循环语句
end loop;
```

范例：输出从1开始的100个数

```sql
declare
v_num number:=1;
begin
    loop
        dbms_output.put_line(v_num);
        v_num:=v_num+1;
        exit when v_num>100;
    end loop;
end ;
```



2. 条件循环

语法结构

```sql
while 条件
loop
end loop;
```

范例：输出从1开始的100个数

```sql
declare
v_num number:=1;
begin
     while v_num<=100
     loop
         dbms_output.put_line(v_num);
         v_num:=v_num+1;
     end loop;
end;
```



3. for循环

基本语法

```sql
for 变量 in 起始值..终止值
loop

end loop;
```

范例：输出从1开始的100个数

```sql
begin
     for v_num in 1..100
     loop
     	dbms_output.put_line(v_num);
     end loop;
end;
```



### 7. 游标

#### 7.1 什么是游标

游标是系统为用户开设的一个数据缓冲区,存放 SQL 语句的执行结果。我们可以把游标理解为 PL/SQL 中的结果集。

![](Oracle.assets/86.png)



#### 7.2 语法结构及示例

在声明区声明游标，语法如下：

```sql
cursor 游标名称 is SQL 语句;
```

使用游标语法

```sql
open 游标名称
loop
     fetch 游标名称 into 变量
     exit when 游标名称%notfound
end loop;
close 游标名称
```

需求：打印业主类型为 1 的价格表

代码：

```sql
declare
     v_pricetable T_PRICETABLE%rowtype;--价格行对象
     cursor cur_pricetable is select * from T_PRICETABLE where ownertypeid=1;--定义游标
begin
    open cur_pricetable;--打开游标
    loop
        fetch cur_pricetable into v_pricetable;--提取游标到变量
        exit when cur_pricetable%notfound;--当游标到最后一行下面退出循环

        dbms_output.put_line( '价格:' ||v_pricetable.price ||'吨位：'||v_pricetable.minnum||'-'||v_pricetable.maxnum );
    end loop;
    
    close cur_pricetable;--关闭游标
end ;
```





#### 7.3 带参数的游标

我们的查询语句的条件值有可能是在运行时才能决定的，比如性业主类型，可能是运行时才可以决定，那如何实现呢？我们接下来学习带参数的游标，修改上述案例

```sql
declare
    v_pricetable T_PRICETABLE%rowtype;--价格行对象
    
    cursor cur_pricetable(v_ownertypeid number) is select * from T_PRICETABLE where ownertypeid=v_ownertypeid;--定义游标
begin
    open cur_pricetable(2);--打开游标
    loop
    
    fetch cur_pricetable into v_pricetable;--提取游标到变量
    
    exit when cur_pricetable%notfound;--当游标到最后一行下面退出循环
    
    dbms_output.put_line('价格:'||v_pricetable.price ||'吨位：'||v_pricetable.minnum||'-'||v_pricetable.maxnum );
    
    end loop;
    close cur_pricetable;--关闭游标
end ;
```



#### 7.4 for 循环提取游标值

我们每次提取游标，需要打开游标 关闭游标 循环游标 提取游标 控制循环的退出等等，好麻烦！有没有更简单的写法呢？有！用 for 循环一切都那么简单，上例的代码可以改造为下列形式

```sql
declare

    cursor cur_pricetable(v_ownertypeid number) is select * from T_PRICETABLE where ownertypeid=v_ownertypeid;--定义游标

begin

    for v_pricetable in cur_pricetable(3)
    loop
        dbms_output.put_line('价格:'||v_pricetable.price ||'吨位：'||v_pricetable.minnum||'-'||v_pricetable.maxnum );
    end loop;

end ;
```



## 二十二、存储函数

### 1. 什么是存储函数

存储函数又称为自定义函数。可以接收一个或多个参数，返回一个结果。 在函数中我们可以使用 P/SQL 进行逻辑的处理。



### 2. 存储函数语法结构

创建或修改存储过程的语法如下:

```sql
CREATE [ OR REPLACE ] FUNCTION 函数名称
	（参数名称 参数类型, 参数名称 参数类型, ...）
RETURN 结果变量数据类型
is
	变量声明部分;
BEGIN
	逻辑部分;
	RETURN 结果变量;
[EXCEPTION
 	异常处理部分]
END;
```



### 3. 案例

需求： 创建存储函数，根据地址 ID 查询地址名称。

语句:

```sql
create function fn_getaddress(v_id number) 
return varchar2
is
	v_name varchar2(30);
begin
	select name into v_name from t_address where id=v_id;
	return v_name;
end;
```

测试此函数：

```sql
select fn_getaddress(3) from dual
```



需求：查询业主 ID，业主名称，业主地址，业主地址使用刚才我们创建的函数来实现。

```sql
select id 编号,name 业主名称,fn_getaddress(addressid) 地址
from t_owners 
```





## 二十三、存储过程

### 1. 什么是存储过程

存储过程是被命名的 PL/SQL 块，存储于数据库中，是数据库对象的一种。应用程序可以调用存储过程，执行相应的逻辑。

存储过程与存储函数都可以封装一定的业务逻辑并返回结果，存在区别如下：

1. 存储函数中有返回值，且必须返回；而存储过程没有返回值，可以通过 传出参数返回多个值。
2. 存储函数可以在 select 语句中直接使用，而存储过程不能。过程多数是 被应用程序所调用。
3. 存储函数一般都是封装一个查询结果，而存储过程一般都封装一段事务 代码。



### 2. 存储过程语法结构

创建或修改存储过程的语法如下：

```sql
CREATE [ OR REPLACE ] PROCEDURE 存储过程名称
	（参数名 类型, 参数名 类型, 参数名 类型）
IS|AS
	变量声明部分;
BEGIN
	逻辑部分
[EXCEPTION 
	异常处理部分]
END;
```

参数只指定类型，不指定长度

过程参数的三种模式：

- IN 传入参数（默认）
- OUT 传出参数 ，主要用于返回程序运行结果
- IN OUT 传入传出参数



### 3. 案例

1. 创建不带传出参数的存储过程：添加业主信息

```sql
--增加业主信息序列
create sequence seq_owners start with 11;

--增加业主信息存储过程
create or replace procedure pro_owners_add
(
 v_name varchar2,
 v_addressid number,
 v_housenumber varchar2,
 v_watermeter varchar2,
 v_type number
)
is

begin
	 insert into T_OWNERS values( seq_owners.nextval,v_name,v_addressid,v_housenumber,v_watermeter,sysdate,v_type );
	commit;
end;
```

PL/SQL 中调用存储过程

```sql
call pro_owners_add('赵伟',1,'999-3','132-7',1);
```

JDBC 调用存储过程

```java
/**
* 增加
* @param owners
*/
public static void add(Owners owners){
    
    java.sql.Connection conn=null;
    java.sql.CallableStatement stmt=null;
    
    try {
        conn=BaseDao.getConnection();
        stmt=conn.prepareCall("{call pro_owners_add(?,?,?,?,?)}");
        
        stmt.setString(1, owners.getName());
        stmt.setLong(2, owners.getAddressid());
        stmt.setString(3, owners.getHousenumber());
        stmt.setString(4, owners.getWatermeter());
        stmt.setLong(5, owners.getOwnertypeid());
        
        stmt.execute();
    } catch (SQLException e) {
    	e.printStackTrace();
    }finally {
    	BaseDao.closeAll(null, stmt, conn);
    }
}
```



2. 创建带传出参数的存储过程

需求：添加业主信息，传出参数为新增业主的 ID

```sql
create or replace procedure pro_owners_add
(
     v_name varchar2,
     v_addressid number,
     v_housenumber varchar2,
     v_watermeter varchar2,
     v_type number,
     v_id out number
)
is
begin
     select seq_owners.nextval into v_id from dual;
     insert into T_OWNERS
	 values( v_id,v_name,v_addressid,v_housenumber,v_watermeter,sysdate,v_type );
 	 commit;
end;
```

PL/SQL 调用该存储过程

```sql
declare
	v_id number;--定义传出参数的变量
begin
	pro_owners_add('王旺旺',1,'922-3','133-7',1,v_id);
	DBMS_OUTPUT.put_line('增加成功,ID:'||v_id);
end;
```

JDBC 调用存储过程

```sql
/**
* 增加
* @param owners
*/
public static long add(Owners owners){

    long id=0;
    java.sql.Connection conn=null;
    java.sql.CallableStatement stmt=null;
    
    try {
        conn=BaseDao.getConnection();
        stmt=conn.prepareCall("{call pro_owners_add(?,?,?,?,?,?)}");
        
        stmt.setString(1, owners.getName());
        stmt.setLong(2, owners.getAddressid());
        stmt.setString(3, owners.getHousenumber());
        stmt.setString(4, owners.getWatermeter());
        stmt.setLong(5, owners.getOwnertypeid());
        stmt.registerOutParameter(6, OracleTypes.NUMBER);//注册传出参数类型
        stmt.execute();
    	id=stmt.getLong(6);//提取传出参数
    } catch (SQLException e) {
    	e.printStackTrace();
    }finally {
    	BaseDao.closeAll(null, stmt, conn);
    }
    return id;
}
```



## 二十四、触发器

### 1. 什么是触发器

数据库触发器是一个与表相关联的、存储的 PL/SQL 程序。每当一个特定的 数据操作语句(Insert,update,delete)在指定的表上发出时，Oracle 自动地执行触发 器中定义的语句序列。

触发器可用于

- 数据确认
- 实施复杂的安全性检查
- 做审计，跟踪表上所做的数据操作等
- 数据的备份和同步

触发器分类

- 前置触发器（BEFORE）
- 后置触发器（AFTER）



### 2. 创建触发器的语法

语法：

```sql
CREATE [or REPLACE] TRIGGER 触发器名
	BEFORE | AFTER
	[DELETE ][[or] INSERT] [[or]UPDATE [OF 列名]]
	ON 表名
	[FOR EACH ROW ][WHEN(条件) ]
declare
	……
begin
	PLSQL 块
End ；
```

FOR EACH ROW 作用是标注此触发器是行级触发器 语句级触发器

- 行级触发器：对每一行数据的变更都会触发一次触发器
- 语句级触发器：执行完整个SQL语句后，只触发一次的触发器

在触发器中触发语句与伪记录变量的值

| 触发语句 | :old                       | :new                       |
| -------- | -------------------------- | -------------------------- |
| Insert   | 没有，所有字段都是空(null) | 将要插入的数据             |
| Update   | 更新以前该行的值           | 更新后的值                 |
| delete   | 删除以前该行的值           | 没有，所有字段都是空(null) |



### 3. 案例

#### 3.1 前置触发器

需求：当用户输入本月累计表数后，自动计算出本月使用数 。

代码：

```sql
create or replace trigger tri_account_updatenum1
before
update of num1
on t_account
for each row
declare
begin
	:new.usenum:=:new.num1-:new.num0;
end; 
```



#### 3.2 后置触发器

需求：当用户修改了业主信息表的数据时记录修改前与修改后的值

```sql
--创建业主名称修改日志表:用于记录业主更改前后的名称
create table t_owners_log
(
updatetime date,
ownerid number,
oldname varchar2(30),
newname varchar2(30)
);

--创建后置触发器，自动记录业主更改前后日志
create trigger tri_owners_log
after
update of name
on t_owners
for each row
declare

begin
	insert into t_owners_log values(sysdate,:old.id,:old.name,:new.name);
	-- zi'do
end;
```



测试：

```sql
--更新数据
update t_owners set name='杨小花' where id=3;
commit;

--查询日志表
select * from t_owners_log;
```























