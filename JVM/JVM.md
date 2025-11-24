# JVM

# JVM上篇：内存与垃圾回收篇



## 一、JVM与Java体系结构



### 0、前言

大部分Java开发人员，除会在项目中使用到与Java平台相关的各种高精尖技术，对于Java技术的核心Java虚拟机了解甚少。

![](JVM.assets/1.png)

如果我们把核心类库的API比做数学公式的话，那么 Java 虚拟机的知识就好比公式的推导过程。

![](JVM.assets/2.png)

计算机系统体系对我们来说越来越远，在不了解底层实现方式的前提下，通过高级语言很容易编写程序代码。但事实上计算机并不认识高级语言



**我们为什么要学习JVM？**

- 面试的需要（BATJ、TMD、PKQ等面试都爱问）
- 中高级程序员必备技能
  - 项目管理、调优的需要
- 追求极客的精神
  - 比如：垃圾回收算法、JIT、底层原理





### 1. Java及JVM简介

tiobe编程语言热度排行榜：https://www.tiobe.com/tiobe-index/

![](JVM.assets/3.png)

![](JVM.assets/4.png)

![](JVM.assets/5.png)





### 3. 虚拟机与Java虚拟机

#### 3.1 什么是虚拟机？

- 所谓虚拟机(Virtual Machine)，就是一台虚拟的计算机。它是一款软件，用来执行一系列虚拟计算机指令。大体上，虚拟机可以分为**系统虚拟机**和**程序虚拟机**。
  - 大名鼎鼎的visual Box，VMware就属于系统虚拟机，它们**完全是对物理计算机的仿真**，提供了一个可运行完整操作系统的软件平台。
  - 程序虚拟机的典型代表就是Java虚拟机，它专门为执行单个计算机程序而设计，在Java虚拟机中执行的指令我们称为Java字节码指令。
- 无论是系统虚拟机还是程序虚拟机，在上面运行的软件都被限制于虚拟机提供的资源中。



#### 3.2 什么是Java虚拟机？

- Java虚拟机是一台执行Java字节码的虚拟计算机，它拥有独立的运行机制，其运行的Java字节码也未必由Java语言编译而成。
- JVM平台的各种语言可以共享Java虚拟机带来的跨平台性、优秀的垃圾回器，以及可靠的即时编译器。
- **Java技术的核心就是Java虚拟机**(JVM，Java Virtual Machine)，因为所有的Java程序都运行在Java虚拟机内部。



#### 3.3 Java虚拟机的作用？

**Java虚拟机就是二进制字节码的运行环境**，负责装载字节码到其内部，解释并编译为对应平台上的机器指令执行。每一条Java指令，Java虚拟机规范中都有详细定义，如怎么取操作数，怎么处理操作数，处理结果放在哪里。



#### 3.4 Java虚拟机的特点？

- 一次编译，到处运行
- 自动内存管理
- 自动垃圾回收功能





### 3. JVM的整体结构

JVM的位置

![](JVM.assets/6.png)



- HotSpot VM是目前市面上高性能虚拟机的代表作之一。
- 它采用解释器与即时编译器并存的架构。
- 在今天，Java程序的运行性能早已脱胎换骨，已经达到了可以和C/C++程序一较高下的地步。

![](JVM.assets/7.png)



### 4. Java代码执行流程

![](JVM.assets/8.png)





### 5. JVM的架构模型

Java编译器输入的指令流基本上是一种基于**栈的指令集架构**，另外一种指令集架构则是基于**寄存器的指令集架构**。

具体来说：这两种架构之间的区别：

- **基于栈式架构的特点**
  - 设计和实现更简单，适用于资源受限的系统；
  - 避开了寄存器的分配难题：使用零地址指令方式分配。
  - 指令流中的指令大部分是零地址指令，其执行过程依赖于操作栈。指令集更小，编译器容易实现。
  - 不需要硬件支持，可移植性更好，更好实现跨平台

- **基于寄存器架构的特点**
  - 典型的应用是x86的二进制指令集：比如传统的Pc以及Android的Davlik虚拟机。
  - 指令集架构则完全依赖硬件，可移植性差。
  - 性能优秀和执行更高效。
  - 花费更少的指令去完成一项操作。
  - 在大部分情况下，基于寄存器架构的指令集往往都以一地址指令、二地址指令和三地址指令为主，而基于栈式架构的指令集却是以零地址指令为主。

举例：

![](JVM.assets/9.png)



总结：

由于跨平台性的设计，Java的指令都是根据栈来设计的。不同平台CPU架构不同，所以不能设计为基于寄存器的。优点是跨平台，指令集小，编译器容易实现，缺点是性能下降，实现同样的功能需要更多的指令。





### 6. JVM的生命周期

**虚拟机的启动**

Java虚拟机的启动是通过引导类加载器(bootstrap class loader)创建一个初始类(initial class)来完成的，这个类是由虚拟机的具体实现指定的。



**虚拟机的执行**

- 一个运行中的Java虚拟机有着一个清晰的任务：执行Java程序。
- 程序开始执行时他才运行，程序结束时他就停止。
- 执行一个所谓的Java程序的时候，真真正正在执行的是一个叫做Java虚拟机的进程。



**虚拟机的退出**

有如下的几种情况：

- 程序正常执行结束
- 程序在执行过程中遇到了异常或错误而异常终止
- 由于操作系统出现错误而导致Java虚拟机进程终止
- 某线程调用Runtime类或System类的exit方法，或Runtime类的halt方法，并且Java安全管理器也允许这次exit或halt操作。
- 除此之外，JNI（Java Native Interface）规范描述了用JNI Invocation API来加载或卸载Java虚拟机时，Java虚拟机的退出情况。





### 7. JVM的发展历程

**Sun Classic VM**

- 早在1996年Java1.0版本的时候，Sun公司发布了一款名为Sun Classic VM的Java虚拟机，它同时也是世界上第一款商用Java虚拟机，JDK1.4时完全被淘汰。
- 这款虚拟机内部只提供解释器。
- 如果使用JIT编译器，就需要进行外挂。但是一旦使用了JIT编译器，JIT就会接管虚拟机的执行系统。解释器就不再工作。解释器和编译器不能配合工作。
- 现在hotspot内置了此虚拟机。



**Exact VM**

- 为了解决上一个虚拟机问题，jdk1.2时，sun提供了此虚拟机。 
- Exact Memory Management：准确式内存管理
  - 也可以叫Non-Conservative / Accurate Memory Management
  - 虚拟机可以知道内存中某个位置的数据具体是什么类型。
- 具备现代高性能虚拟机的维形
  - 热点探测
  - 编译器与解释器混合工作模式
- 只在solaris平台短暂使用，其他平台上还是classic vm
  - 英雄气短，终被Hotspot虚拟机替换



**HotSpot VM**

- HotSpot历史
  - 最初由一家名为“Longview Technologies"的小公司设计
  - 1997年，此公司被Sun收购；2009年，Sun公司被甲骨文收购。
  - JDK1.3时，HotSpot VM成为默认虚拟机
- 目前Hotspot占有绝对的市场地位，称霸武林。
  - 不管是现在仍在广泛使用的JDK6，还是使用比例较多的JDK8中，默认的虚拟机都是HotSpot
  - Sun / Oracle JDK 和 Open JDK 的默认虚拟机
  - 因此本课程中默认介绍的虚拟机都是HotSpot，相关机制也主要是指HotSpot的GC机制。（比如其他两个商用虚拟机都没有方法区的概念）
- 从服务器、桌面到移动端、嵌入式都有应用。
- 名称中的HotSpot指的就是它的热点代码探测技术。
  - 通过计数器找到最具编译价值代码，触发即时编译或栈上替换
  - 通过编译器与解释器协同工作，在最优化的程序响应时间与最佳执行性能中取得平衡



**JRockit**

- 专注于服务器端应用
  - 它可以不太关注程序启动速度，因此JRockit内部不包含解析器实现，全部代码都靠即时编译器编译后执行。
- 大量的行业基准测试显示，**JRockit JVM是世界上最快的JVM**。
  - 使用JRockit产品，客户已经体验到了显著的性能提高(一些超过了70%)和硬件成本的减少(达50%)。
- 优势：全面的Java运行时解决方案组合
  - JRockit面向延迟敏感型应用的解决方案JRockit Real Time提供以毫秒或微秒级的JVM响应时间，适合财务、军事指挥、电信网络的需要
  - MissionControl服务套件，它是一组以极低的开销来监控、管理和分析生产环境中的应用程序的工具。
- 2008年，BEA被Oracle收购。
- Oracle表达了整合两大优秀虚拟机的工作，大致在JDK8中完成。整合的方式是在 HotSpot的基础上，移植JRockit的优秀特性。
- 高斯林：目前就职于谷歌，研究人工智能和水下机器人



**J9**

- 全称：IBM Technology for Java Virtual Machine，简称IT4J，内部代号：J9
- 市场定位与HotSpot接近，服务器端、桌面应用、嵌入式等多用途VM 广泛用于IBM的各种Java产品。
- 目前，有影响力的三大商用虚拟机之一，也号称是世界上最快的Java虚拟机。
- 2017年左右，IBM发布了开源J9 VM，命名为OpenJ9，交给Eclipse基金会管理，也称为 Eclipse OpenJ9



**KVM和CDC/CLDC Hotspot**

- Oracle在JavaME产品线上的两款虚拟机为：CDC/CLDC HotSpot Implementation VM
- KVM（Kilobyte）是CLDC-HI早期产品
- 目前移动领域地位尴尬，智能手机被Android和iOS二分天下。
- KVM简单、轻量、高度可移植，面向更低端的设备上还维持自己的一片市场
  - 智能控制器、传感器
  - 老人手机、经济欠发达地区的功能手机
- 所有的虚拟机的原则：一次编译，到处运行。



**Azul VM**

- 前面三大“高性能Java虚拟机”使用在通用硬件平台上
- 这里 Azul VM 和 BEA Liquid VM 是与特定硬件平台绑定、软硬件配合的专有虚拟机
  - 高性能Java虚拟机中的战斗机。
- Azul VM 是 Azul Systems 公司在HotSpot基础上进行大量改进，运行于 Azul Systems 公司的专有硬件Vega系统上的Java虚拟机。
- 每个 Azul VM 实例都可以管理至少数十个CPU和数百GB内存的硬件资源，并提供在巨大内存范围内实现可控的GC时间的垃圾收集器、专有硬件优化的线程调度等优秀特性，
- 2010年，Azul Systems公司开始从硬件转向软件，发布了自己的zing JVM，可以在通用x86平台上提供接近于Vega系统的特性。



**Liquid VM**

- 高性能Java虚拟机中的战斗机。
- BEA公司开发的，直接运行在自家Hypervisor系统上
- Liquid VM即是现在的JRockit VE(VirtualEdition)，Liquid VM不需要操作系统的支持，或者说它自己本身实现了一个专用操作系统的必要功能，如线程调度、文件系统、网络支持等。
- 随着JRockit虚拟机终止开发，Liquid VM 项目也停止了。



**Apache Harmony**

- Apache也曾经推出过与JDK 1.5和JDK 1.6兼容的Java运行平台 Apache Harmony
- 它是IBM和Intel联合开发的开源JVM，受到同样开源的OpenJDK的压制， Sun坚决不让Harmony获得JCP认证，最终于2011年退役，IBM转而参与 OpenJDK
- 虽然目前并没有Apache Harmony被大规模商用的案例，但是它的Java 类库代码吸纳进了Android SDK。



**Microsoft JVM**

- 微软为了在IE3浏览器中支持Java Applets，开发了Microsoft JVM。
- 只能在window平台下运行。但确是当时windows下性能最好的Java VM。 
- 1997年，Sun以侵犯商标、不正当竞争罪名指控微软成功，赔了sun很多钱。微软在windowsXP SP3中抹掉了其VM。现在windows上安装的jdk 都是HotSpot。



**TaobaoJVM**

- 由Ali JVM团队发布。阿里，国内使用Java最强大的公司，覆盖云计算、金融、物流、电商等众多领域，需要解决高并发、高可用、分布式的复合问题。有大量的开源产品。
- 基于OpenJDK 开发了自己的定制版本Alibaba JDK，简称AJDK。是整个阿里Java体系的基石。
- 基于OpenJDK HotSpot VM 发布的国内第一个优化、深度定制且开源的高性能服务器版Java虚拟机。
  - 创新的GCIH(GCinvisibleheap)技术实现了off-heap，即将生命周期较长的Java对象从heap中移到heap之外，并且GC不能管理GCIH内部的Java对象，以此达到降低GC的回收频率和提升GC的回收效率的目的。
  - GCIH 中的对象还能够在多个Java虚拟机进程中实现共享
  - 使用crc32指令实现 JVM intrinsic 降低 JNI 的调用开销
  - PMU hardware 的 Java profilingtool 和诊断协助功能
  - 针对大数据场景的zenGC
- taobao vm应用在阿里产品上性能高，硬件严重依赖intel的cpu，损失了兼容性，但提高了性能
- 目前已经在淘宝、天猫上线，把Oracle官方JVM版本全部替换了。



**Dalvik VM**

- 谷歌开发的，应用于Android系统，并在Android2.2中提供了JIT，发展迅猛。
- Dalvik VM 只能称作虚拟机，而不能称作“Java虚拟机”，它没有遵循Java 虚拟机规范
- 不能直接执行Java的Class文件
- 基于寄存器架构，不是jvm的栈架构。
- 执行的是编译以后的dex(Dalvik Executable)文件。执行效率比较高。
  - 它执行的dex(Dalvik Executable)文件可以通过class文件转化而来，使用Java语法编写应用程序，可以直接使用大部分的JavaAPI等。
- Android5.0使用支持提前编译（Ahead Of Time Compilation，AOT）的 ART VM替换Dalvik VM。



**Graal VM**

- 2018年4月，Oracle Labs公开了Graal VM，号称"Run Programs Faster Anywhere"，勃勃野心。与1995年java的”write once，run anywhere"遥相呼应。
- Graal VM在HotSpot VM基础上增强而成的跨语言全栈虚拟机，可以作为 “任何语言” 的运行平台使用。语言包括：Java、Scala、Groovy、Kotlin；C、C++、 JavaScript、Ruby、Python、R等
- 支持不同语言中混用对方的接口和对象，支持这些语言使用已经编写好的本地库文件
- 工作原理是将这些语言的源代码或源代码编译后的中间格式，通过解释器转换为能被 Graal VM接受的中间表示。Graal VM提供Truffle工具集快速构建面向一种新语言的解释器。在运行时还能进行即时编译优化，获得比原生编译器更优秀的执行效率。
- 如果说HotSpot有一天真的被取代，GraalVM希望最大。但是Java的软件生态没有丝毫变化。



## 二、类加载子系统

### 1. 内存结构概述

![](JVM.assets/10.png)



### 2. 类加载器和类加载过程

![](JVM.assets/11.png)

- 类加载器子系统负责从文件系统或者网络中加载class文件，class文件在文件开头有特定的文件标识。
- ClassLoader只负责class文件的加载，至于它是否可以运行，则由Execution Engine决定。
- 加载的类信息存放于一块称为方法区的内存空间。除了类的信息外，方法区中还会存放运行时常量池信息，可能还包括字符串字面量和数字常量(这部分常量信息是 Class文件中常量池部分的内存映射)

![](JVM.assets/12.png)

1. class file存在于本地硬盘上，可以理解为设计师画在纸上的模板，而最终这个模板在执行的时候是要加载到JVM当中来根据这个文件实例化出n个一模一样的实例。
2. class file加载到JVM中，被称为 DNA元数据模板，放在方法区。
3. 在.class文件 -> JVM -> 最终成为元数据模板，此过程就要一个运输工具（类装载器Class Loader），扮演一个快递员的角色。

![](JVM.assets/13.png)

![](JVM.assets/14.png)



#### 2.1 类的加载过程一：Loading

加载：

1. 通过一个类的全限定名获取定义此类的二进制字节流
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3. **在内存中生成一个代表这个类的java.lang.Class对象**，作为方法区这个类
   的各种数据的访问入口



补充：加载 .class 文件的方式

- 从本地系统中直接加载
- 通过网络获取，典型场景：WebApplet
- 从zip压缩包中读取，成为日后jar、war格式的基础
- 运行时计算生成，使用最多的是：动态代理技术
- 由其他文件生成，典型场景：JSP应用
- 从专有数据库中提取.class文件，比较少见
- 从加密文件中获取，典型的防 Class 文件被反编译的保护措施





#### 2.2 类的加载过程二：Linking

验证(Verify)：

- 目的在于确保class文件的字节流中包含信息符合当前虚拟机要求，保证被加载类的正确性，不会危害虚拟机自身安全。
- 主要包括四种验证，文件格式验证，元数据验证，字节码验证，符号引用验证

准备(Prepare)：

- 为类变量分配内存并且设置该类变量的默认初始值，即零值。
- 这里不包含用final修饰的static，因为final在编译的时候就会分配了，准备阶段会显式初始化；
- 这里不会为实例变量分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中。

解析(Resolve)：

- 将常量池内的符号引用转换为直接引用的过程。
- 事实上，解析操作往往会伴随着JVM在执行完初始化之后再执行。
- 符号引用就是一组符号来描述所引用的目标。符号引用的字面量形式明确定义在《java虚拟机规范》的class文件格式中。直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。
- 解析动作主要针对类或接口、字段、类方法、接口方法、方法类型等。对应常量池中的CONSTANT_Class_info、CONSTANT_Fieldref_info、CONSTANT_Methodref_info等



#### 2.3 类的加载过程三：Initialization

**初始化**：

- 初始化阶段就是执行类构造器方法<clinit>()的过程。
- 此方法不需定义，是javac编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来。
- 构造器方法中指令按语句在源文件中出现的顺序执行。
- <clinit>()不同于类的构造器。(关联：构造器是虚拟机视角下的<init>())
- 若该类具有父类，JVM会保证子类的<clinit>()执行前，父类的<clinit>() 已经执行完毕。
- 虚拟机必须保证一个类的<clinit>()方法在多线程下被同步加锁



### 3. 类加载器分类

- JVM支持两种类型的类加载器，分别为**引导类加载器**(Bootstrap ClassLoader)和**自定义类加载器**(User-Defined ClassLoader)
- 从概念上来讲，自定义类加载器一般指的是程序中由开发人员自定义的一类类加载器，但是Java虚拟机规范却没有这么定义，而是**将所有派生于抽象类classLoader的类加载器都划分为自定义类加载器**。
- 无论类加载器的类型如何划分，在程序中我们最常见的类加载器始终只有3 个，如下所示：

![](JVM.assets/15.png)



#### 3.1 虚拟器自带的加载器

- 启动类加载器(引导类加载器，Bootstrap ClassLoader)
  - 这个类加载使用C/C++语言实现的，嵌套在JVM内部。
  - 它用来加载Java的核心库（JAVA_HOME/jre/lib/rt.jar、resources.jar或sun.boot.class.path路径下的内容），用于提供 JVM自身需要的类
  - 并不继承自java.lang.ClassLoader，没有父加载器。
  - 加载扩展类和应用程序类加载器，并指定为他们的父类加载器。
  - 出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、 sun等开头的类



- 扩展类加载器（Extension ClassLoader）
  - Java语言编写，由sun.misc.Launcher$ExtClassLoader实现。
  - 派生于classLoader类
  - 父类加载器为启动类加载器
  - 从java.ext.dirs系统属性所指定的目录中加载类库，或从JDK的安装目录的jre/lib/ext子目录（扩展目录）下加载类库。如果用户创建的JAR放在此目录下，也会自动由扩展类加载器加载。



- 应用程序类加载器（系统类加载器，AppclassLoader）
  - java语言编写，由sun.misc.Launcher$AppClassLoader实现
  - 派生于classLoader类
  - 父类加载器为扩展类加载器
  - 它负责加载环境变量classpath或系统属性java.class.path指定路径下的类库
  - 该类加载是程序中默认的类加载器，一般来说，Java应用的类都是由它来完成加载
  - 通过classLoader#getSystemClassLoader()方法可以获取到该类加载器

```java
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

public class Test {

    public static void main(String[] args) {
        System.out.println("**********启动类加载器**************");
        //获取BootstrapClassLoader能够加载的api的路径
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL element : urLs) {
            System.out.println(element.toExternalForm());
        }
        //从上面的路径中随意选择一个类,来看看他的类加载器是什么:引导类加载器
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println("***********扩展类加载器*************");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }

        //从上面的路径中随意选择一个类,来看看他的类加载器是什么:扩展类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@1540e19d

    }
}

```

结果

```
*********启动类加载器**************
file:/D:/note/java/jdk8/jre/lib/resources.jar
file:/D:/note/java/jdk8/jre/lib/rt.jar
file:/D:/note/java/jdk8/jre/lib/sunrsasign.jar
file:/D:/note/java/jdk8/jre/lib/jsse.jar
file:/D:/note/java/jdk8/jre/lib/jce.jar
file:/D:/note/java/jdk8/jre/lib/charsets.jar
file:/D:/note/java/jdk8/jre/lib/jfr.jar
file:/D:/note/java/jdk8/jre/classes
null

***********扩展类加载器*************
D:\note\java\jdk8\jre\lib\ext
C:\WINDOWS\Sun\Java\lib\ext
sun.misc.Launcher$ExtClassLoader@29453f44
```



#### 3.2 用户自定义类加载器

- 在Java的日常应用程序开发中，类的加载几乎是由上述3种类加载器相互配合执行的，在必要时，我们还可以自定义类加载器，来定制类的加载方式。
- 为什么要自定义类加载器？
  - 隔离加载类
  - 修改类加载的方式
  - 扩展加载源
  - 防止源码泄漏



用户自定义类加载器实现步骤：

- 开发人员可以通过继承抽象类java.lang.ClassLoader类的方式，实现自己的类加载器，以满足一些特殊的需求
- 在JDK1.2之前，在自定义类加载器时，总会去继承classLoader类并重写loadClass()方法，从而实现自定义的类加载类，但是在JDK1.2之后已不再建议用户去覆盖loadclass()方法，而是建议把自定义的类加载逻辑写在findClass()方法中
- 在编写自定义类加载器时，如果没有太过于复杂的需求，可以直接继承URLClassLoader类，这样就可以避免自已去编写findClass()方法及其获取字节码流的方式，使自定义类加载器编写更加简洁。



#### 3.3 关于ClassLoader

ClassLoader类，它是一个抽象类，其后所有的类加载器都继承自 ClassLoader(不包括启动类加载器)



##### 3.3.1 常用方法

| 方法名称                                             | 描述                                                         |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| getParent()                                          | 返回该类加载器的超类加载器                                   |
| loadClass(String name)                               | 加载名称为name的类，返回结果为java.lang.Class类的实例        |
| findClass(String name)                               | 查找名称为name的类，返回结果为java.lang.Class类的实例        |
| findLoadedClass(String name)                         | 查找名称为name的已经被加载过的类，返回结果为java.lang.Class类的实例 |
| defineClass(String name, byte[] b, int off, int len) | 把字节数组b中的内容转换为一个Java类，返回结果为java.lang.Class类的实例 |
| resolveClass(Class<?> c)                             | 连接指定的个Java类                                           |

![](JVM.assets/16.png)



##### 3.3.2 获取ClassLoader的途径

| 方式                                    | 方法                                           |
| --------------------------------------- | ---------------------------------------------- |
| 方式一：获取当前类的classLoader         | clazz.getClassLoader()                         |
| 方式二：获取当前线程上下文的classLoader | Thread.currentThread().getContextClassLoader() |
| 方式三：获取系统的classLoader           | ClassLoader.getSystemClassLoader()             |
| 方式四：获取调用者的classLoader         | DriverManager.getCallerClassLoader()           |



### 4. 双亲委派机制

Java虚拟机对class文件采用的是**按需加载**的方式，也就是说当需要使用该类时才会将它的class文件加载到内存生成class对象。而且加载某个类的 class文件时，Java虚拟机采用的是**双亲委派模式**，即把请求交由父类处理，它是一种任务委派模式。



- 工作原理


1. 如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行；
2. 如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器；
3. 如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自已去加载，这就是双亲委派模式。

![](JVM.assets/17.png)



举例：

![](JVM.assets/18.png)

优势：

- 避免类的重复加载
- 保护程序安全，防止核心API被随意篡改



### 5. 沙箱安全机制

自定义String类，但是在加载自定义String类的时候会率先使用引导类加载器加载，而引导类加载器在加载的过程中会先加载jdk自带的文件(rt.jar包中java\lang\String.class)，报错信息说没有main方法，就是因为加载的是rt.jar包中的string类。这样可以保证对java核心源代码的保护，这就是**沙箱安全机制**。



### 6. 其他

- 在JVM中表示两个class对象是否为同一个类存在两个必要条件：
  - 类的完整类名必须一致，包括包名。
  - 加载这个类的classLoader(指classLoader实例对象)必须相同。
- 换句话说，在JVM中，即使这两个类对象(class对象)来源同一个class文件，被同一个虚拟机所加载，但只要加载它们的classLoader实例对象不同，那么这两个类对象也是不相等的。



**对类加载器的引用**

JVM必须知道一个类型是由启动加载器加载的还是由用户类加载器加载的。如果一个类型是由用户类加载器加载的，那么JVM会**将这个类加载器的一个引用作为类型信息的一部分保存在方法区中**。当解析一个类型到另一个类型的引用的时候，JVM需要保证这两个类型的类加载器是相同的。



**类的主动使用和被动使用**

Java程序对类的使用方式分为：主动使用和被动使用。

- 主动使用，又分为七种情况：
  - 创建类的实例
  - 访问某个类或接口的静态变量，或者对该静态变量赋值
  - 调用类的静态方法
  - 反射（比如：Class.forName("com.atguigu.Test")）
  - 初始化一个类的子类
  - Java虚拟机启动时被标明为启动类的类
  - JDK7开始提供的动态语言支持：java.lang.invoke.MethodHandle实例的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic句柄对应的类没有初始化，则初始化
- 除了以上七种情况，其他使用Java类的方式都被看作是对类的被动使用，都不会导致类的初始化。





## 三、运行时数据区概述及线程

### 1. 运行时数据区

![](JVM.assets/19.png)

![](JVM.assets/20.png)



内存是非常重要的系统资源，是硬盘和CPU的中间仓库及桥梁，承载着操作系统和应用程序的实时运行。JVM内存布局规定了Java在运行过程中内存申请、分配、管理的策略，保证了JVM的高效稳定运行。**不同的JVM对于内存的划分方式和管理机制存在着部分差异**。结合JVM 虚拟机规范，来探讨一下经典的JVM内存布局。

![](JVM.assets/21.png)

![](JVM.assets/22.png)



Java虚拟机定义了若干种程序运行期间会使用到的运行时数据区，其中有一些会随着虚拟机启动而创建，随着虚拟机退出而销毁。另外一些则是与线程一一对应的，这些与线程对应的数据区域会随着线程开始和结束而创建和销毁。

灰色的为单独线程私有的，红色的为多个线程共享的。即：

- 每个线程：独立包括程序计数器、栈、本地栈。
- 线程间共享：堆、堆外内存（永久代或元空间、代码缓存）

![](JVM.assets/23.png)

每个JVM只有一个Runtime实例。即为运行时环境，相当于内存结构的中间的那个框框：运行时环境。



### 2. 线程

- 线程是一个程序里的运行单元。JVM允许一个应用有多个线程并行的执行。
- 在Hotspot JVM里，每个线程都与操作系统的本地线程直接映射。
  - 当一个Java线程准备好执行以后，此时一个操作系统的本地线程也同时创建。Java线程执行终止后，本地线程也会回收。
- 操作系统负责所有线程的安排调度到任何一个可用的CPU上。一旦本地线程初始化成功，它就会调用Java线程中的run()方法。



- 这些主要的后台系统线程在Hotspot JVM里主要是以下几个：
  - **虚拟机线程**：这种线程的操作是需要JVM达到安全点才会出现。这些操作必须在不同的线程中发生的原因是他们都需要JVM达到安全点，这样堆才不会变化。这种线程的执行类型包括"stop-the-world"的垃圾收集，线程栈收集，线程挂起以及偏向锁撤销。
  - **周期任务线程**：这种线程是时间周期事件的体现（比如中断），他们一般用于周期性操作的调度执行。
  - **GC线程**：这种线程对在JVM里不同种类的垃圾收集行为提供了支持。
  - **编译线程**：这种线程在运行时会将字节码编译成到本地代码。
  - **信号调度线程**：这种线程接收信号并发送给JVM，在它内部通过调用适当的方法进行处理。



## 四、程序计数器(PC寄存器)

### 1. 介绍

![](JVM.assets/24.png)

JVM中的程序计数寄存器（ProgramCounterRegister）中，Register的命名源于 CPU的寄存器，寄存器存储指令相关的现场信息。CPU只有把数据装载到寄存器才能够运行。

这里，并非是广义上所指的物理寄存器，或许将其翻译为PC计数器（或指令计数器）会更加贴切（也称为程序钩子），并且也不容易引起一些不必要的误会。JVM中的PC寄存器是对物理PC 寄存器的一种抽象模拟。

**作用**：PC寄存器用来存储指向下一条指令的地址，也即将要执行的指令代码。由执行引擎读取下一条指令。

![](JVM.assets/25.png)



- 它是一块很小的内存空间，几乎可以忽略不记。也是运行速度最快的存储区域。
- 在JVM规范中，每个线程都有它自己的程序计数器，是线程私有的，生命周期与线程的生命周期保持一致。
- 任何时间一个线程都只有一个方法在执行，也就是所谓的当前方法。程序计数器会存储当前线程正在执行的Java方法的JVM指令地址；或者，如果是在执行native方法，则是未指定值（undefined）。
- 它是程序控制流的指示器，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。
- 字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令。
- 它是唯一一个在Java虚拟机规范中没有规定任何OutOtMemoryError 情况的区域。



### 2. 使用

![](JVM.assets/26.png)

![](JVM.assets/27.png)



### 3. 两个常见问题

> 使用PC寄存器存储字节码指令地址有什么用呢？
>
> 为什么使用PC寄存器记录当前线程的执行地址呢？

因为CPU需要不停的切换各个线程，这时候切换回来以后，就得知道接着从哪开始继续执行。

JVM的字节码解释器就需要通过改变PC寄存器的值来明确下一条应该执行什么样的字节码指令。



> PC寄存器为什么会被设定为线程私有？

我们都知道所谓的多线程在一个特定的时间段内只会执行其中某一个线程的方法，CPU 会不停地做任务切换，这样必然导致经常中断或恢复，如何保证分毫无差呢？**为了能够准确地记录各个线程正在执行的当前字节码指令地址，最好的办法自然是为每一个线程都分配一个PC寄存器**，这样一来各个线程之间便可以进行独立计算，从而不会出现相互干扰的情况。

由于CPU时间片轮限制，众多线程在并发执行过程中，任何一个确定的时刻，一个处理器或者多核处理器中的一个内核，只会执行某个线程中的一条指令。

这样必然导致经常中断或恢复，如何保证分毫无差呢？每个线程在创建后，都会产生自已的程序计数器和栈帧，程序计数器在各个线程之间互不影响。



### 4. CPU时间片

CPU时间片即CPU分配给各个程序的时间，每个线程被分配一个时间段，称作它的时间片。

在宏观上：我们可以同时打开多个应用程序，每个程序能够并行，同时运行。

但在微观上：由于只有一个CPU，一次只能处理程序要求的一部分，如何处理公平，一种方法就是引入时间片，每个程序轮流执行。



## 五、虚拟机栈

### 1. 概述

由于跨平台性的设计，Java的指令都是根据栈来设计的。不同平台CPU架构不同，所以不能设计为基于寄存器的。

优点是跨平台，指令集小，编译器容易实现，缺点是性能下降，实现同样的功能需要更多的指令。



有不少Java开发人员一提到Java内存结构，就会非常粗粒度地将JVM 中的内存区理解为仅有Java堆(heap)和Java栈(stack)？为什么？

**栈是运行时的单位，而堆是存储的单位。**
即：栈解决程序的运行问题，即程序如何执行，或者说如何处理数据。堆解决的是数据存储的问题，即数据怎么放、放在哪儿。



- **Java虚拟机栈是什么？**
  Java虚拟机栈(Java Virtual Machine Stack)，早期也叫Java栈。每个线程在创建时都会创建一个虚拟机栈，其内部保存一个个的栈帧(Stack Frame)，对应着一次次的Java方法调用。是线程私有的。
- **生命周期**
  生命周期和线程一致。
- **作用**
  主管Java程序的运行，它保存方法的局部变量、部分结果，并参与方法的调用和返回。



### 2. 栈的特点(优点)

- 栈是一种快速有效的分配存储方式，访问速度仅次于程序计数器。
- JVM直接对Java栈的操作只有两个：
  - 每个方法执行，伴随着进栈（入栈、压栈）
  - 执行结束后的出栈工作
- 对于栈来说不存在垃圾回收问题



### 3. 可能出现的异常

- Java虚拟机规范允许**Java栈的大小是动态的或者是固定不变的**。
  - 如果采用固定大小的Java虚拟机栈，那每一个线程的Java虚拟机栈容量可以在线程创建的时候独立选定。如果线程请求分配的栈容量超过Java虚拟机栈允许的最大容量，Java虚拟机将会抛出一个 StackOverflowError 异常。
  - 如果Java虚拟机栈可以动态扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的虚拟机栈，那Java虚拟机将会抛出一个OutOfMemoryError 异常。



### 4. 设置栈内存大小

我们可以使用参数-Xss 选项来设置线程的最大栈空间，栈的大小直接决定了函数调用的最大可达深度。

```java
/**
 * 演示栈中的异常：StackOverflowError
 *
 * 默认情况下，count:11420。
 * 设置栈的大小：-Xss256k，count:2456。
 */
public class Test {
    private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
```



### 5. 栈的存储单位

**栈中存储了什么？**

- 每个线程都有自己的栈，栈中的数据都是以**栈帧(StackFrame)的格式存在**。
- 在这个线程上正在执行的每个方法都各自对应一个栈帧(StackFrame)。
- 栈帧是一个内存区块，是一个数据集，维系着方法执行过程中的各种数据信息。





### 6. 栈的运行原理

- JVM直接对Java栈的操作只有两个，就是对栈帧的**压栈**和**出栈**，遵循“先进后出” 和 “后进先出”原则。
- 在一条活动线程中，一个时间点上，只会有一个活动的栈帧。即只有当前正在执行的方法的栈帧(栈顶栈帧)是有效的，这个栈帧被称为当前栈帧(Current Frame)，与当前栈帧相对应的方法就是当前方法(Current Method)，定义这个方法的类就是当前类(Current Class)。
- 执行引擎运行的所有字节码指令只针对当前栈帧进行操作。
- 如果在该方法中调用了其他方法，对应的新的栈帧会被创建出来，放在栈的顶端，成为新的当前帧。

![](JVM.assets/28.png)



- 不同线程中所包含的栈帧是不允许存在相互引用的，即不可能在一个栈帧之中引用另外一个线程的栈帧。
- 如果当前方法调用了其他方法，方法返回之际，当前栈帧会传回此方法的执行结果给前一个栈帧，接着，虚拟机会丢弃当前栈帧，使得前一个栈帧重新成为当前栈帧。
- Java方法有两种返回函数的方式，一种是正常的函数返回，使用return指令；另外一种是抛出异常。不管使用哪种方式，都会导致栈帧被弹出。



### 7. 栈帧的内部结构

- 局部变量表（Local Variables）
- 操作数栈（operand Stack）（或表达式栈）
- 动态链接（Dynamic Linking）（或指向运行时常量池的方法引用）
- 方法返回地址（Return Address）（或方法正常退出或者异常退出的定义）
- 一些附加信息

![](JVM.assets/29.png)



#### 7.1 局部变量表

- 局部变量表也被称之为局部变量数组或本地变量表
- 定义为一个数字数组，主要用于存储方法参数和定义在方法体内的局部变量，这些数据类型包括各类基本数据类型、对象引用（reference），以及 returnAddress类型。
- 由于局部变量表是建立在线程的栈上，是线程的私有数据，因此不存在数据安全问题
- 局部变量表所需的容量大小是在**编译期**确定下来的，并保存在方法的Code 属性的maximum local variables数据项中。在方法运行期间是不会改变局部变量表的大小的。

- 方法嵌套调用的次数由栈的大小决定。一般来说，栈越大，方法嵌套调用次数越多。对一个函数而言，它的参数和局部变量越多，使得局部变量表膨胀，它的栈帧就越大，以满足方法调用所需传递的信息增大的需求。进而函数调用就会占用更多的栈空间，导致其嵌套调用次数就会减少。
- 局部变量表中的变量只在当前方法调用中有效。在方法执行时，虚拟机通过使用局部变量表完成参数值到参数变量列表的传递过程。当方法调用结束后，随着方法栈帧的销毁，局部变量表也会随之销毁。



##### 7.1.1 变量槽slot

- 参数值的存放总是在局部变量数组的index0开始，到数组长度-1的索引结束。
- 局部变量表，最基本的存储单元是slot（变量槽）
- 局部变量表中存放编译期可知的各种基本数据类型（8种），引用类型（reference），returnAddress类型的变量。
- 在局部变量表里，32位以内的类型只占用一个slot（包括returnAddress类型），64位的类型（long和double）占用两个slot。
  - byte、short、char 在存储前被转换为int，boolean也被转换为int，0表示false，非0表示true。
  - long和double则占据两个slot。



- JVM会为局部变量表中的每一个Slot都分配一个访问索引，通过这个索引即可成功访问到局部变量表中指定的局部变量值
- 当一个实例方法被调用的时候，它的方法参数和方法体内部定义的局部变量将会按照顺序被复制到局部变量表中的每一个slot上

- 如果需要访问局部变量表中一个64bit的局部变量值时，只需要使用前一个索引即可。（比如：访问long或double类型变量）
- 如果当前帧是由构造方法或者实例方法创建的，那么该对象引用this将会存放在index为0的slot处，其余的参数按照参数表顺序继续排列。

![](JVM.assets/30.png)



**Slot的重复利用**

栈帧中的局部变量表中的槽位是可以重用的，如果一个局部变量过了其作用域，那么在其作用域之后申明的新的局部变量就很有可能会复用过期局部变量的槽位，从而达到节省资源的目的。

```java
public void test4() {

    int a = 0;

    {
        int b = 0;
        b = a + 1;
    }

    //变量c使用之前已经销毁的变量b占据的slot的位置
    int c = a + 1;

} 
```



##### 7.1.2 静态变量与局部变量的对比

- 参数表分配完毕之后，再根据方法体内定义的变量的顺序和作用域分配。
- 我们知道类变量表有两次初始化的机会，第一次是在“准备阶段”，执行系统初始化，对类变量设置零值，另一次则是在“初始化”阶段，赋予程序员在代码中定义的初始值。
- 和类变量初始化不同的是，局部变量表不存在系统初始化的过程，这意味着一旦定义了局部变量则必须人为的初始化，否则无法使用。



- 变量的分类：
  - 按照数据类型分：
    - 1.基本数据类型，
    - 2.引用数据类型。
  - 按照在类中声明的位置分：
    - 1.成员变量(在使用前，都经历过默认初始化赋值)：
      ①类变量：linking的prepare阶段：给类变量默认赋值 ---> initial阶段：给类变量显示赋值(静态代码块赋值)
      ②实例变量：随着对象的创建，会在堆空间中分配实例变量空间，并进行默认赋值
    - 2.局部变量：在使用前，必须要进行显示赋值。否则，编译不通过。



**补充说明**

- 在栈帧中，与性能调优关系最为密切的部分就是前面提到的局部变量表。在方法执行时，虚拟机使用局部变量表完成方法的传递。
- 局部变量表中的变量也是重要的垃圾回收根节点，只要被局部变量表中直接或间接引用的对象都不会被回收。



#### 7.2 操作数栈

- 每一个独立的栈帧中除了包含局部变量表以外，还包含一个**后进先出**(Last-In-First-Out)的操作数栈，也可以称之为**表达式栈**(Expression Stack）。
- 操作数栈，在方法执行过程中，根据字节码指令，往栈中写入数据或提取数据，即入栈(push)/出栈(pop)。
  - 某些字节码指令将值压入操作数栈，其余的字节码指令将操作数取出栈。使用它们后再把结果压入栈。
  - 比如：执行复制、交换、求和等操作

![](JVM.assets/31.png)

![](JVM.assets/32.png)



- 操作数栈，主要用于保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间。
- 操作数栈就是JVM执行引擎的一个工作区，当一个方法刚开始执行的时候，一个新的栈帧也会随之被创建出来，这个方法的操作数栈是空的。
- 每一个操作数栈都会拥有一个明确的栈深度用于存储数值，其所需的最大深度在编译期就定义好了，保存在方法的code属性中，为max_stack的值。
- 栈中的任何一个元素都是可以任意的Java数据类型。
  - 32bit的类型占用一个栈单位深度
  - 64bit的类型占用两个栈单位深度
- 操作数栈并非采用访问索引的方式来进行数据访问的，而是只能通过标准的入栈(push)和出栈(pop)操作来完成一次数据访问。



- 如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中，并更新PC寄存器中下一条需要执行的字节码指令。
- 操作数栈中元素的数据类型必须与字节码指令的序列严格匹配，这由编译器在编译器期间进行验证，同时在类加载过程中的类检验阶段的数据流分析阶段要再次验证。
- 另外，我们说Java虚拟机的解释引擎是基于栈的执行引擎，其中的栈指的就是操作数栈。





##### 7.2.1 代码追踪

> 涉及操作数栈的字节码指令执行分析
>

![](JVM.assets/33.png)

![](JVM.assets/34.png)

![](JVM.assets/35.png)

![](JVM.assets/36.png)

![](JVM.assets/37.png)



#### 7.3 栈顶缓存技术

前面提过，基于栈式架构的虚拟机所使用的零地址指令更加紧凑，但完成一项操作的时候必然需要使用更多的入栈和出栈指令，这同时也就意味着将需要更多的指令分派(instructiondispatch)次数和内存读/写次数。

由于操作数是存储在内存中的，因此频繁地执行内存读/写操作必然会影响执行速度。为了解决这个问题，HotSpotJVM的设计者们提出了栈顶缓存(ToS，Top-of-Stack Cashing)技术，将栈顶元素全部缓存在物理CPU的寄存器中，以此降低对内存的读/写次数，提升执行引擎的执行效率。



#### 7.4 动态链接

- 每一个栈帧内部都包含一个指向**运行时常量池**中**该栈帧所属方法的引用**。包含这个引用的目的就是为了支持当前方法的代码能够实现动态链接(Dynamic Linking)。比如：invokedynamic指令
- 在Java源文件被编译到字节码文件中时，所有的变量和方法引用都作为符号引用(SymbolicReference)保存在class文件的常量池里。比如：描述一个方法调用了另外的其他方法时，就是通过常量池中指向方法的符号引用来表示的，那么**动态链接的作用就是为了将这些符号引用转换为调用方法的直接引用**。

![](JVM.assets/38.png)



#### 7.5 方法的调用

在JVM中，将符号引用转换为调用方法的直接引用与方法的绑定机制相关。

- **静态链接**：
  当一个字节码文件被装载进JVM内部时，如果被调用的目标方法在编译期可知，且运行期保持不变时。这种情况下将调用方法的符号引用转换为直接引用的过程称之为静态链接。 
- **动态链接**：
  如果被调用的方法在编译期无法被确定下来，也就是说，只能够在程序运行期将调用方法的符号引用转换为直接引用，由于这种引用转换过程具备动态性，因此也就被称之为动态链接。



对应的方法的绑定机制为：早期绑定(EarlyBinding)和晚期绑定(LateBinding)。绑定是一个字段、方法或者类在符号引用被替换为直接引用的过程，这仅仅发生一次。

- **早期绑定**：
  早期绑定就是指被调用的目标方法如果在编译期可知，且运行期保持不变时，即可将这个方法与所属的类型进行绑定，这样一来，由于明确了被调用的目标方法究竞是哪一个，因此也就可以使用静态链接的方式将符号引用转换为直接引用。

- **晚期绑定**：
  如果被调用的方法在编译期无法被确定下来，只能够在程序运行期根据实际的类型绑定相关的方法，这种绑定方式也就被称之为晚期绑定。

```java
public class Test {

    public void showAnimal(Animal animal) {
        animal.eat(); //表现为 晚期绑定
    }

    public void showHunt(HuntTable h) {
        h.hunt();//表现为 晚期绑定
    }

}

//说明早期绑定和晚期绑定的例子
class Animal {
    public void eat() {
        System.out.println("动物进食");
    }
}

interface HuntTable {
    void hunt();
}

class Dog extends Animal implements HuntTable {

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    @Override
    public void hunt() {
        System.out.println("捕食耗子，多管闲事");
    }

}

class Cat extends Animal implements HuntTable {

    public Cat() {
        super();//表现为 早期绑定
    }

    public Cat(String name) {
        this();//表现为 早期绑定
    }

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }

    @Override
    public void hunt() {
        System.out.println("捕食耗子，天经地义");
    }

}
```



随着高级语言的横空出世，类似于Java一样的基于面向对象的编程语言如今越来越多，尽管这类编程语言在语法风格上存在一定的差别，但是它们彼此之间始终保持着一个共性，那就是都支持封装、继承和多态等面向对象特性，既然**这一类的编程语言具备多态特性，那么自然也就具备早期绑定和晚期绑定两种绑定方式**。

Java中任何一个普通的方法其实都具备虚函数的特征，它们相当于c++语言中的虚函数（C++中则需要使用关键字virtual来显式定义）。如果在Java 程序中不希望某个方法拥有虚函数的特征时，则可以使用关键字final来标记这个方法。



##### 7.5.1 虚方法与非虚方法

非虚方法：

- 如果方法在编译期就确定了具体的调用版本，这个版本在运行时是不可变的。这样的方法称为**非虚方法**。
- 静态方法、私有方法、final方法、实例构造器、父类方法都是非虚方法。
- 其他方法称为虚方法。



虚拟机中提供了以下几条方法调用指令：

- 普通调用指令：
  1. invokestatic：调用静态方法，解析阶段确定唯一方法版本
  2. invokespecial：调用< init >方法、私有及父类方法，解析阶段确定唯一方法版本
  3. invokevirtual：调用所有虚方法
  4. invokeinterface：调用接口方法

- 动态调用指令：
  5. invokedynamic：动态解析出需要调用的方法，然后执行

前四条指令固化在虚拟机内部，方法的调用执行不可人为干预，而invokedynamic指令则支持由用户确定方法版本。其中**invokestatic**指令和**invokespecial**指令调用的方法称为非虚方法，其余的（final修饰的除外）称为虚方法。



##### 7.5.2 关于invokedynamic指令

- JVM字节码指令集一直比较稳定，一直到Java7中才增加了一个invokedynamic指令，这是Java为了实现『动态类型语言』支持而做的一种改进。
- 但是在Java7中并没有提供直接生成invokedynamic指令的方法，需要借助 ASM这种底层字节码工具来产生invokedynamic指令。直到Java8的Lambda 表达式的出现，invokedynamic指令的生成，在Java中才有了直接的生成方式。
- Java7中增加的动态语言类型支持的本质是对Java虚拟机规范的修改，而不是对Java语言规则的修改，这一块相对来讲比较复杂，增加了虚拟机中的方法调用，最直接的受益者就是运行在Java平台的动态语言的编译器。



**动态类型语言和静态类型语言**

动态类型语言和静态类型语言两者的区别就在于对类型的检查是在编译期还是在运行期，满足前者就是静态类型语言，反之是动态类型语言。

说的再直白一点就是，静态类型语言是判断变量自身的类型信息；动态类型语言是判断变量值的类型信息，变量没有类型信息，变量值才有类型信息，这是动态语言的一个重要特征。





##### 7.5.3 方法重写的本质

Java语言中方法重写的本质：

1. 找到操作数栈顶的第一个元素所执行的对象的实际类型，记作C。

2. 如果不同类型C中找到与常量中的描述符合简单名称都相符的方法，则进行访问权限校验，如果通过则返回这个方法的直接引用，查找不通过，则返回java.lang.IllegalAccessError 异常.

3. 否则，按照继承关系从下往上依次对C的各个父类进行第2步的搜素和验证过程。 

4. 如果始终没有找到合适的方法，则抛出java.lang.AbstractMethodError异常。

   

- IllegalAccessError介绍：
  程序试图访问或修改一个属性或调用一个方法，这个属性或方法，你没有权限访问。一般的，这个会引起编译器异常。这个错误如果发生在运行时，就说明一个类发生了不兼容的改变。





##### 7.5.4 虚方法表

- 在面向对象的编程中，会很频繁的使用到动态分派，如果在每次动态分派的过程中都要重新在类的方法元数据中搜索合适的目标的话就可能影响到执行效率。因此，为了提高性能，JVM采用在类的方法区建立一个虚方法表(virtualmethodtable) (非虚方法不会出现在表中)来实现。使用索引表来代替查找。
- 每个类中都有一个虚方法表，表中存放着各个方法的实际入口。
- 那么虚方法表什么时候被创建？
  虚方法表会在类加载的链接阶段被创建并开始初始化，类的变量初始值准备完成之后，JVM会把该类的方法表也初始化完毕。





#### 7.6 方法返回地址

- 存放调用该方法的pc寄存器的值。
- 一个方法的结束，有两种方式：
  - 正常执行完成
  - 出现未处理的异常，非正常退出
- 无论通过哪种方式退出，在方法退出后都返回到该方法被调用的位置。方法正常退出时，调用者的pc计数器的值作为返回地址，即调用该方法的指令的下一条指令的地址。而通过异常退出的，返回地址是要通过异常表来确定，栈帧中一般不会保存这部分信息。



当一个方法开始执行后，只有两种方式可以退出这个方法：

1. 执行引擎遇到任意一个方法返回的字节码指令(return)，会有返回值传递给上层的方法调用者，简称**正常完成出口**；

   一个方法在正常调用完成之后究竟需要使用哪一个返回指令还需要根据方法返回值的实际数据类型而定。

   在字节码指令中，返回指令包含ireturn（当返回值是boolean、byte、char、short和int类型时使用）、lreturn、freturn、dreturn以及areturn，另外还有一个return指令供声明为void的方法、实例初始化方法、类和接口的初始化方法使用。

2. 在方法执行的过程中遇到了异常（Exception），并且这个异常没有在方法内进行处理，也就是只要在本方法的异常表中没有搜索到匹配的异常处理器，就会导致方法退出。简称异常完成出口。

   方法执行过程中抛出异常时的异常处理，存储在一个异常处理表，方便在发生异常的时候找到处理异常的代码。



本质上，方法的退出就是当前栈帧出栈的过程。此时，需要恢复上层方法的局部变量表、操作数栈、将返回值压入调用者栈帧的操作数栈、设置PC 寄存器值等，让调用者方法继续执行下去。

正常完成出口和异常完成出口的区别在于：通过异常完成出口退出的不会给他的上层调用者产生任何的返回值。



#### 7.7 一些附加信息

栈帧中还允许携带与Java虚拟机实现相关的一些附加信息。例如，对程序调试提供支持的信息。





## 五、本地方法接口

![](JVM.assets/39.png)



### 1. 什么是本地方法

简单地讲，一个NativeMethod就是一个Java调用非Java代码的接口。一个NativeMethod是这样一个Java方法：该方法的实现由非Java语言实现，比如 C。这个特征并非Java所特有，很多其它的编程语言都有这一机制，比如在C++中，你可以用extern"c"告知c++编译器去调用一个c的函数。

"A native method is a Java method whose implementation is provided by non-java code."

在定义一个native method时，并不提供实现体（有些像定义一个Java interface），因为其实现体是由非java语言在外面实现的。

本地接口的作用是融合不同的编程语言为Java所用，它的初衷是融合C/C++程序。

```java
class IHaveNatives {

    public native void methodNative1(int x);

    public native static long methodNative2();

    public native synchronized float methodNative3(Object o);

    native void methodNative4(int[] ary) throws Exception;

}
```

> 标识符native可以与所有其它的java标识符连用，但是abstract除外。
>





### 2. 为什么要使用Native Method

Java使用起来非常方便，然而有些层次的任务用Java实现起来不容易，或者
我们对程序的效率很在意时，问题就来了。

- 与Java环境外交互：
  有时Java应用需要与Java外面的环境交互，这是本地方法存在的主要原因。你可以想想Java需要与一些底层系统，如操作系统或某些硬件交换信息时的情况。本地方法正是这样一种交流机制：它为我们提供了一个非常简洁的接口，而且我们无需去了解Java应用之外的繁琐的细节。

- 与操作系统交互：
  JVM支持着Java语言本身和运行时库，它是Java程序赖以生存的平台，它由一个解释器（解释字节码）和一些连接到本地代码的库组成。然而不管怎样，它毕竟不是一个完整的系统，它经常依赖于一些底层系统的支持。这些底层系统常常是强大的操作系统。通过使用本地方法，我们得以用Java实现了jre的与底层系统的交互，甚至JVM 的一些部分就是用c写的。还有，如果我们要使用一些Java语言本身没有提供封装的操作系统的特性时，我们也需要使用本地方法。
- Sun's Java
  Sun的解释器是用c实现的，这使得它能像一些普通的c一样与外部交互。jre大部分是用Java实现的，它也通过一些本地方法与外界交互。例如：类java.lang.Thread 的setPriority()方法是用Java实现的，但是它实现调用的是该类里的本地方法 setPriority0()。这个本地方法是用c实现的，并被植入JVM内部，在windows95 的平台上，这个本地方法最终将调用win32 SetPriority() API。这是一个本地方法的具体实现由JVM直接提供，更多的情况是本地方法由外部的动态链接库（external dynamic link library）提供，然后被JVM调用。



**现状**：

目前该方法使用的越来越少了，除非是与硬件有关的应用，比如通过
Java程序驱动打印机或者Java系统管理生产设备，在企业级应用中已经比较少见。因为现在的异构领域间的通信很发达，比如可以使用socket 通信，也可以使用webService等等，不多做介绍。





## 六、本地方法栈

- Java虚拟机栈用于管理Java方法的调用，而本地方法栈用于管理本地方法的调用。
- 本地方法栈，也是线程私有的。
- 允许被实现成固定或者是可动态扩展的内存大小。(在内存溢出方面是相同的)
  - 如果线程请求分配的栈容量超过本地方法栈允许的最大容量，Java虚拟机将会抛出一个StackOverflowError异常。
  - 如果本地方法栈可以动态扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的本地方法栈，那么Java虚拟机将会抛出一个OutOfMemoryError异常。
- 本地方法是使用c语言实现的。
- 它的具体做法是Native Method Stack中登记native方法，在 Execution Engine执行时加载本地方法库。

![](JVM.assets/40.png)



- 当某个线程调用一个本地方法时，它就进入了一个全新的并且不再受虚拟机限制的世界。它和虚拟机拥有同样的权限。
  - 本地方法可以通过本地方法接口来访问虚拟机内部的运行时数据区。
  - 它甚至可以直接使用本地处理器中的寄存器 
  - 直接从本地内存的堆中分配任意数量的内存。
- 并不是所有的JVM都支持本地方法。因为Java虚拟机规范并没有明确要求本地方法栈的使用语言、具体实现方式、数据结构等。如果JVM产品不打算支持native方法，也可以无需实现本地方法栈。
- 在HotspotJVM中，直接将本地方法栈和虚拟机栈合二为一。





## 七、堆

![](JVM.assets/41.png)



### 1. 堆的核心概述

- 一个JVM实例只存在一个堆内存，堆也是Java内存管理的核心区域。
- Java堆区在JVM启动的时候即被创建，其空间大小也就确定了。是JVM管理的最大一块内存空间。
  - 堆内存的大小是可以调节的。
- 《Java虚拟机规范》规定，堆可以处于物理上不连续的内存空间中，但在逻辑上它应该被视为连续的。
- 所有的线程共享Java堆，在这里还可以划分线程私有的缓冲区(Thread Local Allocation Buffer， TLAB)。

- 《Java虚拟机规范》中对Java堆的描述是：所有的对象实例以及数组都应当在运行时分配在堆上。（The heap is the run-time data area from which memory for all class instances and arrays is allocated）
  - 我要说的是：“几乎”所有的对象实例都在这里分配内存。从实际使用角度看的。
- 数组和对象可能永远不会存储在栈上，因为栈帧中保存引用，这个引用指向对象或者数组在堆中的位置。
- 在方法结束后，堆中的对象不会马上被移除，仅仅在垃圾收集的时候才会被移除。
- 堆，是GC（GarbageCollection，垃圾收集器）执行垃圾回收的重点区域。

![](JVM.assets/42.png)



### 2. 内存细分

现代垃圾收集器大部分都基于分代收集理论设计，堆空间细分为：

![](JVM.assets/43.png)

![](JVM.assets/44.png)

![](JVM.assets/45.png)





### 3. 设置堆内存大小和OOM

- Java堆区用于存储Java对象实例，那么堆的大小在JVM启动时就已经设定好了，大家可以通过选项"-Xmx"和"-Xms"来进行设置。
  - “-Xms"用于表示堆区的初始内存，等价于-XX：InitialHeapSize
  - “-Xmx"则用于表示堆区的最大内存，等价于-XX：MaxHeapSize
- 一旦堆区中的内存大小超过“-Xmx"所指定的最大内存时，将会抛出 OutOfMemoryError异常。
- 通常会将-Xms和-Xmx两个参数配置相同的值，其目的是为了能够在iava垃圾回收机制清理完堆区后不需要重新分隔计算堆区的大小，从而提高性能。
- 默认情况下，**初始内存大小**：物理电脑内存大小／64。**最大内存大小**：物理电脑内存大小／4

```java
/**
 * 1. 设置堆空间大小的参数
 * -Xms 用来设置堆空间(年轻代+老年代)的初始内存大小
 *      -X 是JVM的运行参数
 *      ms 是memory start
 * -Xmx 用来设置堆空间(年轻代+老年代)的最大内存大小
 *
 * 2. 默认堆空间的大小
 *  初始内存大小：物理电脑内存大小／64。
 *  最大内存大小：物理电脑内存大小／4。
 *
 *  3. 手动设置：-Xms600m -Xmx600m
 *      开发中建议将初始堆内存和最大的堆内存设置成相同的值。
 *
 *  4. 查看设置的参数：方式一：jps / jstat -gc 进程id
 *                   方式二：-XX:+PrintGCDetails
 */
public class Test {

    public static void main(String[] args) {

        //返回java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms: " + initialMemory + "M");
        System.out.println("-Xmx: " + maxMemory + "M");

        System.out.println("系统内存大小为: " + initialMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为: " + maxMemory * 4.0 / 1024 + "G");

    }

}
```



#### 3.1 OutOfMemory

![](JVM.assets/46.png)



### 4. 年轻代和老年代

- 存储在JVM中的Java对象可以被划分为两类：
  - 一类是生命周期较短的瞬时对象，这类对象的创建和消亡都非常迅速
  - 另外一类对象的生命周期却非常长，在某些极端的情况下还能够与JVM的生命周期保持一致。
- Java堆区进一步细分的话，可以划分为年轻代(YoungGen)和老年代(oldGen)
- 其中年轻代又可以划分为Eden空间、Survivor0空间和Survivorl空间(有时也叫做 from区、to区)。

![](JVM.assets/47.png)



下面这参数开发中一般不会调：

![](JVM.assets/48.png)



- 配置新生代与老年代在堆结构的占比。
  - 默认-XX:NewRatio=2，表示新生代占1，老年代占2，新生代占整个堆的1/3
  - 可以修改-XX:NewRatio=4，表示新生代占1，老年代占4，新生代占整个堆的1/5



- 在HotSpot中，Eden空间和另外两个Survivor空间缺省所占的比例是8：1：1
- 当然开发人员可以通过选项“-XX：SurvivorRatio”调整这个空间比例。比如-XX:SurvivorRatio=8
- 几乎所有的Java对象都是在Eden区被new出来的。
- 绝大部分的Java对象的销毁都在新生代进行了。
  - IBM公司的专门研究表明，新生代中80%的对象都是“朝生夕死”的。
- 可以使用选项"-Xmn"设置新生代最大内存大小
  - 这个参数一般使用默认值就可以了。

```java
/**
 * -Xms600m -Xmx600m
 *
 * -XX:NewRatio ： 设置新生代与老年代的比例。默认值是2.
 * -XX:SurvivorRatio ：设置新生代中Eden区与Survivor区的比例。默认值是8
 * -XX:-UseAdaptiveSizePolicy ：关闭自适应的内存分配策略（暂时用不到）
 * -Xmn:设置新生代的空间的大小。 （一般不设置）
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  17:23
 */
public class EdenSurvivorTest {
    public static void main(String[] args) {
        System.out.println("我只是来打个酱油~");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

![](JVM.assets/49.png)



### 5. 对象分配过程

为新对象分配内存是一件非常严谨和复杂的任务，JVM的设计者们不仅需要考虑内存如何分配、在哪里分配等问题，并且由于内存分配算法与内存回收算法密切相关，所以还需要考虑GC执行完内存回收后是否会在内存空间中产生内存碎片。

1. new的对象先放伊甸园区。此区有大小限制。
2. 当伊甸园的空间填满时，程序又需要创建对象，JVM的垃圾回收器将对伊甸园区进行垃圾回收（MinorGC），将伊甸园区中的不再被其他对象所引用的对象进行销毁。再加载新的对象放到伊甸园区
3. 然后将伊甸园中的剩余对象移动到幸存者0区。
4. 如果再次触发垃圾回收，此时上次幸存下来的放到幸存者0区的，如果没有回收，就会放到幸存者1区。
5. 如果再次经历垃圾回收，此时会重新放回幸存者0区，接着再去幸存者1区。啥时候能去养老区呢？可以设置次数。默认是15次。
   - 可以设置参数：-XX:MaxTenuringThreshold=<N>进行设置。
6. 在养老区，相对悠闲。当养老区内存不足时，再次触发GC：MajorGC，进行养老区的内存清理。
7. 若养老区执行了Major GC之后发现依然无法进行对象的保存，就会产生OOM异常



总结：

- 针对幸存者s0,s1区的总结：复制之后有交换，谁空谁是to.
- 关于垃圾回收：频繁在新生区收集，很少在养老区收集，几乎不在永久区/元空间收集。



### 6. 对象分配的特殊情况

![](JVM.assets/53.png)

```java
/**
 * -Xms600m -Xmx600m
 * @author shkstart  shkstart@126.com
 * @create 2020  17:51
 */
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceTest> list = new ArrayList<HeapInstanceTest>();
        while (true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```



### 7. 常用调优工具

- JDK命令行
- Eclipse:Memory Analyzer Tool
- Jconsole
- VisualVM
- Jprofiler
- Java Flight Recorder
- GCViewer
- GC Easy



### 8. Minor GC、Major GC与Full GC

JVM在进行GC时，并非每次都对上面三个内存区域(新生代、老年代、方法区)一起回收的，大部分时候回收的都是指新生代。

针对HotSpotVM的实现，它里面的GC按照回收区域又分为两大种类型：一种是部分收集(PartialGC)，一种是整堆收集(FullGC)

- 部分收集：不是完整收集整个Java堆的垃圾收集。其中又分为：
  - 新生代收集（MinorGC／YoungGC）：只是新生代的垃圾收集
  - 老年代收集（MajorGC／OldGC）：只是老年代的垃圾收集。
    - 目前，只有CMS GC会有单独收集老年代的行为。
    - 注意，很多时候MajorGC会和FullGC混淆使用，需要具体分辨是老年代回收还是整堆回收。
  - 混合收集（Mixed GC)：收集整个新生代以及部分老年代的垃圾收集。
    - 目前，只有G1 GC会有这种行为
- 整堆收集（FullGC)：收集整个java堆和方法区的垃圾收集。



- 年轻代GC（Minor GC）触发机制：
  - 当年轻代空间不足时，就会触发MinorGC，这里的年轻代满指的是Eden代满，Survivor满不会引发GC。（每次MinorGC会清理年轻代的内存）
  - 因为Java对象大多都具备朝生夕灭的特性，所以Minor GC非常频繁，一般回收速度也比较快。这一定义既清晰又易于理解。
  - Minor GC会引发STW，暂停其它用户的线程，等垃圾回收结束，用户线程才恢复运行。

![](JVM.assets/54.png)



- 老年代GC（Major GC / Full GC）触发机制：
  - 指发生在老年代的GC，对象从老年代消失时，我们说“MajorGC”或“Ful1GC”发生了。
  - 出现了MajorGC，经常会伴随至少一次的MinorGC（但非绝对的，在Parallel Scavenge收集器的收集策略里就有直接进行Major GC的策略选择过程）。
    - 也就是在老年代空间不足时，会先尝试触发MinorGC。如果之后空间还不足，则触发MajorGC
  - MajorGC的速度一般会比MinorGC慢10倍以上，STW的时间更长。
  - 如果MajorGC后，内存还不足，就报OOM了。



- FullGC触发机制：（后面细讲）

触发FullGC执行的情况有如下五种：

1. 调用System.gc()时，系统建议执行FullGC，但是不必然执行
2. 老年代空间不足
3. 方法区空间不足
4. 通过MinorGC后进入老年代的平均大小大于老年代的可用内存
5. 由Eden区、survivor space0（From Space）区向survivor space1（To Space）区复制时，对象大小大于ToSpace可用内存，则把该对象转存到老年代，且老年代的可用内存小于该对象大小

说明：fullgc是开发或调优中尽量要避免的。这样暂时时间会短一些。



#### 8.1 GC举例

```java
/**
 * 测试MinorGC 、 MajorGC、FullGC
 * -Xms9m -Xmx9m -XX:+PrintGCDetails
 * @author shkstart  shkstart@126.com
 * @create 2020  14:19
 */
public class GCTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "atguigu.com";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }

        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为：" + i);
        }
    }
}
```





### 9. 堆空间分代思想

为什么需要把Java堆分代？不分代就不能正常工作了吗？

- 经研究，不同对象的生命周期不同。70%-99%的对象是临时对象。
  - 新生代：有Eden、两块大小相同的Survivor(又称为from/to，s0/s1)构成，to总为空。
- 老年代：存放新生代中经历多次GC仍然存活的对象。



- 其实不分代完全可以，分代的唯一理由就是优化GC性能。如果没有分代，那所有的对象都在一块，就如同把一个学校的人都关在一个教室。GC的时候要找到哪些对象没用，这样就会对堆的所有区域进行扫描。而很多对象都是朝生夕死的，如果分代的话，把新创建的对象放到某一地方，当GC的时候先把这块存储“朝生夕死”对象的区域进行回收，这样就会腾出很大的空间出来。

![](JVM.assets/55.png)



### 10. 内存分配策略

如果对象在Eden出生并经过第一次MinorGC后仍然存活，并且能被Survivor 容纳的话，将被移动到survivor空间中，并将对象年龄设为1。对象在Survivor区中每熬过一次MinorGC，年龄就增加1岁，当它的年龄增加到一定程度（默认为15岁，其实每个JVM、每个GC都有所不同）时，就会被晋升到老年代中。

对象晋升老年代的年龄阈值，可以通过选项-XX:MaxTenuringThreshold来设置

针对不同年龄段的对象分配原则如下所示：

- 优先分配到Eden
- 大对象直接分配到老年代
  - 尽量避免程序中出现过多的大对象
- 长期存活的对象分配到老年代
- 动态对象年龄判断
  - 如果Survivor区中相同年龄的所有对象大小的总和大于Survivor空间的一半，年龄大于或等于该年龄的对象可以直接进入老年代，无须等到 MaxTenuringThreshold中要求的年龄。
- 空间分配担保
  - -XX:HandlePromotionFailure



### 11. 为对象分配内存：TLAB

为什么有TLAB（Thread Local Allocation Buffer）？

- 堆区是线程共享区域，任何线程都可以访问到堆区中的共享数据
- 由于对象实例的创建在JVM中非常频繁，因此在并发环境下从堆区中划分内存空间是线程不安全的
- 为避免多个线程操作同一地址，需要使用加锁等机制，进而影响分配速度。



什么是TLAB？

- 从内存模型而不是垃圾收集的角度，对Eden区域继续进行划分，JVM为每个线程分配了一个私有缓存区域，它包含在Eden空间内。
- 多线程同时分配内存时，使用TLAB可以避免一系列的非线程安全问题，
- 同时还能够提升内存分配的吞吐量，因此我们可以将这种内存分配方式称之为快速分配策略。
- 据我所知所有OpenJDK衍生出来的JVM都提供了TLAB的设计。

![](JVM.assets/56.png)



TLAB说明：

- 尽管不是所有的对象实例都能够在TLAB中成功分配内存，但JVM确实是将TLAB作为内存分配的首选。
- 在程序中，开发人员可以通过选项“-XX：UseTLAB”设置是否开启TLAB空间。
- 默认情况下，TLAB空间的内存非常小，仅占有整个Eden空间的1%，当然我们可以通过选项“-XX:TLABWasteTargetPercent”设置TLAB空间所占用Eden空间的百分比大小。
- 一旦对象在TLAB空间分配内存失败时，JVM就会尝试着通过使用加锁机制确保数据操作的原子性，从而直接在Eden空间中分配内存。

```java
/**
 * 测试-XX:UseTLAB参数是否开启的情况:默认情况是开启的
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  16:16
 */
public class TLABArgsTest {
    public static void main(String[] args) {
        System.out.println("我只是来打个酱油~");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

![](JVM.assets/57.png)



### 12. 堆空间的常用参数设置

- 官网说明：https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html
- -XX:+PrintFlagsInitial：查看所有的参数的默认初始值
- -XX:+PrintFlagsFinal：查看所有的参数的最终值（可能会存在修改，不再是初始值）
  - 具体查看某个参数的指令：jps：查看当前运行中的进程
    											  jinfo -flag SurvivorRatio 进程id
- -Xms：初始堆空间内存（默认为物理内存的1/64）
- -Xmx：最大堆空间内存（默认为物理内存的1/4）
- -Xmn：设置新生代的大小。(初始值及最大值)
- -XX:NewRatio：配置新生代与老年代在堆结构的占比



- -XX:SurvivorRatio：设置新生代中Eden和S0/S1空间的比例
- -XX:MaxTenuringThreshold：设置新生代垃圾的最大年龄
- -XX:+PrintGCDetails：输出详细的GC处理日志
  - 打印gc简要信息：
    1. -XX:+PrintGC
    2. -verbose:gc
- -XX:HandlePromotionFailure：是否设置空间分配担保

在发生MinorGC之前，虚拟机会检查老年代最大可用的连续空间是否大于新生代所有对象的总空间

- 如果大于，则此次MinorGC是安全的
- 如果小于，则虚拟机会查看-XX:HandlePromotionFailure设置值是否允许担保失败。
  - 如果HandlePromotionFailure=true，那么会继续检查老年代最大可用连续空间是否大于历次晋升到老年代的对象的平均大小。
    - 如果大于，则尝试进行一次MinorGC，但这次MinorGC依然是有风险的；
    - 如果小于，则改为进行一次FullGC。
  - 如果HandlePromotionFailure=false，则改为进行一次Full GC。

在JDK6Update24之后，HandlePromotionFailure参数不会再影响到虚拟机的空间分配担保策略，观察OpenJDK中的源码变化，虽然源码中还定义了HandlePromotionFailure参数，但是在代码中已经不会再使用它。JDK6Update 24之后的规则变为只要老年代的连续空间大于新生代对象总大小或者历次晋升的平均大小就会进行MinorGC，否则将进行FullGC。





### 13.通过逃逸分析看堆空间的对象分配策略

#### 13.1 堆是分配对象存储的唯一选择吗？

在《深入理解Java虚拟机》中关于Java堆内存有这样一段描述：随着JIT编译期的发展与逃逸分析技术逐渐成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化，所有的对象都分配到堆上也渐渐变得不那么“绝对”了。

在Java虚拟机中，对象是在Java堆中分配内存的，这是一个普遍的常识。但是，有一种特殊情况，那就是如果经过逃逸分析(EscapeAnalysis)后发现，一个对象并没有逃逸出方法的话，那么就可能被优化成栈上分配。这样就无需在堆上分配内存，也无须进行垃圾回收了。这也是最常见的堆外存储技术。

此外，前面提到的基于OpenJDK深度定制的TaoBaoVM，其中创新的GCIH（GC invisibleheap）技术实现off-heap，将生命周期较长的Java对象从heap中移至 heap外，并且GC不能管理GCIH内部的Java对象，以此达到降低GC的回收频率和提升 GC的回收效率的目的。



#### 13.2 逃逸分析概述

- 如何将堆上的对象分配到栈，需要使用逃逸分析手段。
- 这是一种可以有效减少Java程序中同步负载和内存堆分配压力的跨函数全局数据流分析算法。
- 通过逃逸分析，JavaHotspot编译器能够分析出一个新的对象的引用的使用范围从而决定是否要将这个对象分配到堆上。
- 逃逸分析的基本行为就是分析对象动态作用域：
  - 当一个对象在方法中被定义后，对象只在方法内部使用，则认为没有发生逃逸。
  - 当一个对象在方法中被定义后，它被外部方法所引用，则认为发生逃逸。例如作为调用参数传递到其他地方中。

```java
public void my_methid() {
    V v = new V();
    v = null;
}
```

没有发生逃逸的对象，则可以分配到栈上，随着方法执行的结束，栈空间就被移除。

```java
public static StringBuffer createStringBuffer(String s1, String s2) {
    StringBuffer sb = new StringBuffer();
    sb.append(s1);
    sb.append(s2);
    return sb;
}
```

上述代码如果想要StringBuffersb不逃出方法，可以这样写：

```java
public static String createStringBuffer(String s1, String s2) {
    StringBuffer sb = new StringBuffer();
    sb.append(s1);
    sb.append(s2);
    return sb.toString();
}
```



```java
/**
 * 逃逸分析
 *
 *  如何快速的判断是否发生了逃逸分析，大家就看new的对象实体是否有可能在方法外被调用。
 * @author shkstart
 * @create 2020 下午 4:00
 */
public class EscapeAnalysis {

    public EscapeAnalysis obj;

    /*
    方法返回EscapeAnalysis对象，发生逃逸
     */
    public EscapeAnalysis getInstance(){
        return obj == null? new EscapeAnalysis() : obj;
    }
    /*
    为成员属性赋值，发生逃逸
     */
    public void setObj(){
        this.obj = new EscapeAnalysis();
    }
    //思考：如果当前的obj引用声明为static的？仍然会发生逃逸。

    /*
    对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useEscapeAnalysis(){
        EscapeAnalysis e = new EscapeAnalysis();
    }
    /*
    引用成员变量的值，发生逃逸
     */
    public void useEscapeAnalysis1(){
        EscapeAnalysis e = getInstance();
        //getInstance().xxx()同样会发生逃逸
    }
}
```



参数设置：

- 在JDK6u23版本之后，HotSpot中默认就已经开启了逃逸分析。
- 如果使用的是较早的版本，开发人员则可以通过：
  - 选项“-XX：+DoEscapeAnalysis"显式开启逃逸分析
  - 通过选项“-XX：+PrintEscapeAnalysis"查看逃逸分析的筛选结果。

> 结论：开发中能使用局部变量的，就不要使用在方法外定义。



### 14. 逃逸分析：代码优化

使用逃逸分析，编译器可以对代码做如下优化：

1. **栈上分配**。将堆分配转化为栈分配。如果一个对象在子程序中被分配，要使指向该对象的指针永远不会逃逸，对象可能是栈分配的候选，而不是堆分配。
2. **同步省略**。如果一个对象被发现只能从一个线程被访问到，那么对于这个对象的操作可以不考虑同步。
3. **分离对象或标量替换**。有的对象可能不需要作为一个连续的内存结构存在也可以被访问到，那么对象的部分（或全部）可以不存储在内存，而是存储在CPU寄存器中。



#### 14.1 栈上分配

- JIT编译器在编译期间根据逃逸分析的结果，发现如果一个对象并没有逃逸出方法的话，就可能被优化成栈上分配。分配完成后，继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收。这样就无须进行垃圾回收了。
- 常见的栈上分配的场景
  - 在逃逸分析中，已经说明了。分别是给成员变量赋值、方法返回值、实例引用传递。

```java
/**
 * 栈上分配测试
 * -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * @author shkstart  shkstart@126.com
 * @create 2020  10:31
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();//未发生逃逸
    }

    static class User {

    }
}
```



#### 14.2 同步省略

- 线程同步的代价是相当高的，同步的后果是降低并发性和性能。
- 在动态编译同步块的时候，JIT编译器可以借助逃逸分析来判断同步块所使用的锁对象是否只能够被一个线程访问而没有被发布到其他线程。如果没有，那么JIT编译器在编译这个同步块的时候就会取消对这部分代码的同步。这样就能大大提高并发性和性能。这个取消同步的过程就叫同步省略，也叫锁消除。

如以下代码：

```java
public void f() {
    Object hollis = new Object();
    synchronized (hollis) {
        System.out.println(hollis);
    }
}
```

代码中对hollis这个对象进行加锁，但是hollis对象的生命周期只在f()方法中，并不会被其他线程所访问到，所以在JIT编译阶段就会被优化掉。优化成：

```java
public void f() {
    Object hollis = new Object();
    System.out.println(hollis);
}
```



#### 14.3 分离对象或标量替换

标量（Scalar）是指一个无法再分解成更小的数据的数据。Java中的原始数据类型就是标量。

相对的，那些还可以分解的数据叫做聚合量（Aggregate），Java中的对象就是聚合量，因为他可以分解成其他聚合量和标量。

在JIT阶段，如果经过逃逸分析，发现一个对象不会被外界访问的话，那么经过JIT优化，就会把这个对象拆解成若干个其中包含的若干个成员变量来代替。这个过程就是标量替换。

```java
public class test {

    public static void main(String[] args) {
        alloc();
    }

    private static void alloc() {
        Point point = new Point(1, 2);
        System.out.println("point.x= " + point.x + "; point.y= " + point.y);
    }
    
}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

以上代码，经过标量替换后，就会变成：

```java
private static void alloc() {
    int x = 1;
    int y = 2;
    System.out.println("point.x= " + x + "; point.y= " + y);
}
```

可以看到，Point这个聚合量经过逃逸分析后，发现他并没有逃逸，就被替换成两个聚合量了。那么标量替换有什么好处呢？就是可以大大减少堆内存的占用。因为一旦不需要创建对象了，那么就不再需要分配堆内存了。

标量替换为栈上分配提供了很好的基础。

**标量替换参数设置**：参数-XX:+EliminateAllocations：开启了标量替换（默认打开），允许将对象打散分配在栈上。





## 八、方法区

![](JVM.assets/58.png)



### 1. 栈、堆、方法区的交互关系

从线程共享与否的角度来看

![](JVM.assets/59.png)



![](JVM.assets/60.png)

![](JVM.assets/61.png)



### 2. 方法区的理解

**方法区在哪里？**

《Java虚拟机规范》中明确说明：“尽管所有的方法区在逻辑上是属于堆的一部分，但一些简单的实现可能不会选择去进行垃圾收集或者进行压缩。”但对于HotSpotJVM而言，方法区还有一个别名叫做Non-Heap（非堆），目的就是要和堆分开。

所以，方法区看作是一块独立于Java堆的内存空间。

![](JVM.assets/62.png)



- 方法区（MethodArea）与Java堆一样，是各个线程共享的内存区域。
- 方法区在JVM启动的时候被创建，并且它的实际的物理内存空间中和Java堆区一样都可以是不连续的。
- 方法区的大小，跟堆空间一样，可以选择固定大小或者可扩展。
- 方法区的大小决定了系统可以保存多少个类，如果系统定义了太多的类，导致方法区溢出，虚拟机同样会抛出内存溢出错误：java.lang.OutOfMemoryError：PermGenspace 或者java.lang.OutofMemoryError：Metaspace
  - 加载大量的第三方的jar包；Tomcat部署的工程过多（30-50个）；大量动态的生成反射类
- 关闭JVM就会释放这个区域的内存。



### 3. Hotspot方法区的演进

- 在jdk7及以前，习惯上把方法区，称为永久代。jdk8开始，使用元空间取代了永久代。
- 本质上，方法区和永久代并不等价。仅是对hotspot而言的。《Java虚拟机规范》对如何实现方法区，不做统一要求。例如：BEAJRockit／IBMJ9中不存在永久代的概念。
  - 现在来看，当年使用永久代，不是好的idea。导致Java程序更容易OOM（超过-XX:MaxPermSize上限）

![](JVM.assets/63.png)

![](JVM.assets/64.png)



- 而到了JDK8，终于完全废弃了永久代的概念，改用与JRockit、J9一样在本地内存中实现的元空间（Metaspace）来代替


![](JVM.assets/65.png)



- 元空间的本质和永久代类似，都是对JVM规范中方法区的实现。不过元空间与永久代最大的区别在于：元空间不在虚拟机设置的内存中，而是使用本地内存。
- 永久代、元空间二者并不只是名字变了，内部结构也调整了。
- 根据《Java虚拟机规范》的规定，如果方法区无法满足新的内存分配需求时，将抛出OOM异常。



### 4. 设置方法区大小与OOM

- 方法区的大小不必是固定的，jvm可以根据应用的需要动态调整。
- jdk7及以前：
  - 通过-XX:PermSize来设置永久代初始分配空间。默认值是20.75M
  - -XX:MaxPermSize来设定永久代最大可分配空间。32位机器默认是64M，64位机器模式是 82M
  - 当JVM加载的类信息容量超过了这个值，会报异常OutOfMemoryError:PermGen space

![](JVM.assets/66.png)



- jdk8及以后：
  - 元数据区大小可以使用参数-XX:MetaspaceSize和-XX:MaxMetaspaceSize指定，替代上述原有的两个参数。
  - 默认值依赖于平台。windows下，-XX:MetaspaceSize是21M，-XX:MaxMetaspaceSize的值是-1，即没有限制。
  - 与永久代不同，如果不指定大小，默认情况下，虚拟机会耗尽所有的可用系统内存。如果元数据区发生溢出，虚拟机一样会抛出异常OutOfMemoryError:Metaspace
  - -XX:MetaspaceSize：设置初始的元空间大小。对于一个64位的服务器端JVM来说，其默认的-XX:MetaspaceSize值为21MB。这就是初始的高水位线，一旦触及这个水位线，FullGC将会被触发并卸载没用的类（即这些类对应的类加载器不再存活），然后这个高水位线将会重置。新的高水位线的值取决于GC后释放了多少元空间。如果释放的空间不足，那么在不超过MaxMetaspaceSize时，适当提高该值。如果释放空间过多，则适当降低该值。
  - 如果初始化的高水位线设置过低，上述高水位线调整情况会发生很多次。通过垃圾回收器的日志可以观察到FullGC多次调用。为了避免频繁地GC，建议将-XX:MetaspaceSize设置为一个相对较高的值。

代码举例：

```java
/**
 * jdk6/7中：
 * -XX:PermSize=10m -XX:MaxPermSize=10m
 *
 * jdk8中：
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  22:24
 */
public class OOMTest extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            OOMTest test = new OOMTest();
            for (int i = 0; i < 10000; i++) {
                //创建ClassWriter对象，用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                //指明版本号，修饰符，类名，包名，父类，接口
                classWriter.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                //返回byte[]
                byte[] code = classWriter.toByteArray();
                //类的加载
                test.defineClass("Class" + i, code, 0, code.length);//Class对象
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }
}
```



### 5. 如何解决OOM？

1. 要解决OOM异常或heap space的异常，一般的手段是首先通过内存映像分析工具（如Eclipse Memory Analyzer）对dump 出来的堆转储快照进行分析，重点是确认内存中的对象是否是必要的，也就是要先分清楚到底是出现了内存泄漏（Memory Leak）还是内存溢出（MemoryOverflow）。
2. 如果是内存泄漏，可进一步通过工具查看泄漏对象到GCRoots的引用链。于是就能找到泄漏对象是通过怎样的路径与GCRoots相关联并导致垃圾收集器无法自动回收它们的。掌握了泄漏对象的类型信息，以及GCRoots引用链的信息，就可以比较准确地定位出泄漏代码的位置。
3. 如果不存在内存泄漏，换句话说就是内存中的对象确实都还必须存活着，那就应当检查虚拟机的堆参数（-Xmx与-Xms），与机器物理内存对比看是否还可以调大，从代码上检查是否存在某些对象生命周期过长、持有状态时间过长的情况，尝试减少程序运行期的内存消耗。



### 6. 内存结构

![](JVM.assets/67.png)



**方法区(Method Area)存储了什么？**

《深入理解Java虚拟机》书中对方法区（MethodArea）存储内容描述如下：它用于存储已被虚拟机加载的类型信息、常量、静态变量、即时编译器编译后的代码缓存等。

![](JVM.assets/68.png)



- 类型信息


对每个加载的类型（类class、接口interface、枚举enum、注解annotation），JVM必须在方法区中存储以下类型信息：

1. 这个类型的完整有效名称（全名=包名.类名）
2. 这个类型直接父类的完整有效名(对于interface或是java.lang.Object，都没有父类)
3. 这个类型的修饰符(public, abstract, final的某个子集)
4. 这个类型直接接口的一个有序列表



- 域(Field)信息
  - JVM必须在方法区中保存类型的所有域的相关信息以及域的声明顺序。
  - 域的相关信息包括：域名称、域类型、域修饰符(public, private, protected, static, final, volatile, transient的某个子集)



- 方法(Method)信息
  JVM必须保存所有方法的以下信息，同域信息一样包括声明顺序：

  - 方法名称

  - 方法的返回类型(或void)
  - 方法参数的数量和类型(按顺序)
  - 方法的修饰符(public，private，protected，static，final，synchronized，native，abstract的一个子集)
  - 方法的字节码(bytecodes)、操作数栈、局部变量表及大小(abstract和 native方法除外)
  - 异常表(abstract和native方法除外)
    - 每个异常处理的开始位置、结束位置、代码处理在程序计数器中的偏移地址、被捕获的异常类的常量池索引



### 7. non-final的类变量

- 静态变量和类关联在一起，随着类的加载而加载，它们成为类数据在逻辑上的一部分。
- 类变量被类的所有实例共享，即使没有类实例时你也可以访问它。

```java
/**
 * non-final的类变量
 * @author shkstart  shkstart@126.com
 * @create 2020  20:37
 */
public class MethodAreaTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
    }
}

class Order {
    public static int count = 1;
    public static final int number = 2;


    public static void hello() {
        System.out.println("hello!");
    }
}
```

补充说明：全局常量(static, final)

被声明为final的类变量的处理方法则不同，每个全局常量在编译的时候就会被分配了。



### 8. class文件中的常量池

#### 8.1 运行时常量池 vs 常量池

![](JVM.assets/69.png)

- 方法区，内部包含了运行时常量池。
- 字节码文件，内部包含了常量池。
- 要弄清楚方法区，需要理解清楚classFile，因为加载类的信息都在方法区。
- 要弄清楚方法区的运行时常量池，需要理解清楚classFile中的常量池。

![](JVM.assets/70.png)

一个有效的字节码文件中除了包含类的版本信息、字段、方法以及接口等描述信息外，还包含一项信息那就是常量池表（ConstantPoolTable），包括各种字面量和对类型、域和方法的符号引用。



#### 8.2 为什么需要常量池？

一个java源文件中的类、接口，编译后产生一个字节码文件。而Java中的字节码需要数据支持，通常这种数据会很大以至于不能直接存到字节码里，换另一种方式，可以存到常量池，这个字节码包含了指向常量池的引用。在动态链接的时候会用到运行时常量池，之前有介绍。

比如：如下的代码：

```java
public class SimpleClass {
    public void sayHello() {
        System.out.println("hello");
    }
}
```

虽然只有194字节，但是里面却使用了String、System、PrintStream及object等结构。这里代码量其实已经很小了。如果代码多，引用到的结构会更多！这里就需要常量池了！



#### 8.3 常量池有什么？

几种在常量池内存储的数据类型包括：

- 数量值
- 字符串值
- 类引用
- 字段引用
- 方法引用

例如：

```java
public class MethodAreaTest2 {
    public static void main(String[] args) {
        Object obj = new Object();
    }
}
```

Object obj = new Object();将会被编译成如下字节码：

```
0:  new #2 				// Class java/lang/object
1:	dup
2:	invokespecial #3	// Method java/lang/object "<init>"( ) V
```



小结：常量池，可以看做是一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等类型。



### 9. 运行时常量池的理解

- 运行时常量池（Runtime Constant Pool）是方法区的一部分。
- 常量池表（Constant Pool Table）是Class文件的一部分，用于存放编译期生成的各种字面量与符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中。
- 运行时常量池，在加载类和接口到虚拟机后，就会创建对应的运行时常量池。
- JVM为每个已加载的类型（类或接口）都维护一个常量池。池中的数据项像数组项一样，是通过索引访问的。
- 运行时常量池中包含多种不同的常量，包括编译期就已经明确的数值字面量，也包括到运行期解析后才能够获得的方法或者字段引用。此时不再是常量池中的符号地址了，这里换为真实地址。
  - 运行时常量池，相对于class文件常量池的另一重要特征是：具备动态性。
- 运行时常量池类似于传统编程语言中的符号表（symbol table），但是它所包含的数据却比符号表要更加丰富一些。
- 当创建类或接口的运行时常量池时，如果构造运行时常量池所需的内存空间超过了方法区所能提供的最大值，则JVM会抛OutOfMemoryError异常。



### 10. 方法区的使用举例

```java
public class MethodAreaDemo {
    public static void main(String[] args) {
        int x = 500;
        int y = 100;
        int a = x / y;
        int b = 50;
        System.out.println(a + b);
    }
}
```

![](JVM.assets/71.png)

![](JVM.assets/72.png)

![](JVM.assets/73.png)

![](JVM.assets/74.png)

![](JVM.assets/75.png)

![](JVM.assets/76.png)

![](JVM.assets/77.png)

![](JVM.assets/78.png)

![](JVM.assets/79.png)

![](JVM.assets/80.png)

![](JVM.assets/81.png)

![](JVM.assets/82.png)

![](JVM.assets/83.png)

![](JVM.assets/84.png)

![](JVM.assets/85.png)

![](JVM.assets/86.png)



### 11. 方法区的演进细节

1. 首先明确：只有HotSpot才有永久代。BEA JRockit、IBM J9等来说，是不存在永久代的概念的。原则上如何实现方法区属于虚拟机实现细节，不受《Java虚拟机规范》管束，并不要求统一。 
2. Hotspot中方法区的变化：

| 版本         | 变化                                                         |
| ------------ | ------------------------------------------------------------ |
| jdk1.6及之前 | 有永久代(permanent generation)，静态变量存放在永久代上       |
| jdk1.7       | 有永久代，但已经逐步“去永久代”，字符串常量池、静态变量移除，保存在堆中 |
| jdk1.8及之后 | 无永久代，类型信息、字段、方法、常量保存在本地内存的元空间，但字符串常量池、静态变量仍在堆 |

![](JVM.assets/88.png)

![](JVM.assets/88.png)

![](JVM.assets/89.png)



#### 11.1 永久代为什么要被元空间替换？

- 随着Java8的到来，HotSpotVM中再也见不到永久代了。但是这并不意味着类的元数据信息也消失了。这些数据被移到了一个与堆不相连的本地内存区域，这个区域叫做元空间（Metaspace）
- 由于类的元数据分配在本地内存中，元空间的最大可分配空间就是系统可用内存空间。
- 这项改动是很有必要的，原因有：

1. 为永久代设置空间大小是很难确定的。

   在某些场景下，如果动态加载类过多，容易产生Perm区的OOM。比如某个实际Web工程中，因为功能点比较多，在运行过程中，要不断动态加载很多类，经常出现致命错误。

   而元空间和永久代之间最大的区别在于：元空间并不在虚拟机中，而是使用本地内存。因此，默认情况下，元空间的大小仅受本地内存限制。

2. 对永久代进行调优是很困难的。



#### 11.2 StringTable为什么要调整？

jdk7中将StringTable放到了堆空间中。因为永久代的回收效率很低，在fullgc 的时候才会触发。而fullgc是老年代的空间不足、永久代不足时才会触发。
这就导致StringTable回收效率不高。而我们开发中会有大量的字符串被创建，回收效率低，导致永久代内存不足。放到堆里，能及时回收内存。



#### 11.3 静态变量放在哪里？

```java
/**
 * 结论：
 * 静态引用对应的对象实体始终都存在堆空间
 *
 * jdk7：
 * -Xms200m -Xmx200m -XX:PermSize=300m -XX:MaxPermSize=300m -XX:+PrintGCDetails
 * jdk 8：
 * -Xms200m -Xmx200m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 * @author shkstart  shkstart@126.com
 * @create 2020  21:20
 */
public class StaticFieldTest {
    private static byte[] arr = new byte[1024 * 1024 * 100];//100MB

    public static void main(String[] args) {
        System.out.println(StaticFieldTest.arr);

//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}

```

```java
/**
 * 《深入理解Java虚拟机》中的案例：
 * staticObj、instanceObj、localObj存放在哪里？
 * @author shkstart  shkstart@126.com
 * @create 2020  11:39
 */
public class StaticObjTest {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new StaticObjTest.Test();
        test.foo();
    }
}

```

staticobj随着Test的类型信息存放在方法区，instanceobj随着Test的对象实例存放在Java堆，localobject则是存放在foo()方法栈帧的局部变量表中。

测试发现：三个对象的数据在内存中的地址都落在Eden区范围内，所以结论：只要是对象实例必然会在Java堆中分配。

接着，找到了一个引用该staticobj对象的地方，是在一个java.lang.Class的实例里，并且给出了这个实例的地址，通过Inspector查看该对象实例，可以清楚看到这确实是一个 java.lang.Class类型的对象实例，里面有一个名为staticobj的实例字段：

从《Java虚拟机规范》所定义的概念模型来看，所有class相关的信息都应该存放在方法区之中，但方法区该如何实现，《Java虚拟机规范》并未做出规定，这就成了一件允许不同虚拟机自己灵活把握的事情。JDK7及其以后版本的HotSpot虚拟机选择把静态变量与类型在Java语言一端的映射class对象存放在一起，存储于Java堆之中，从我们的实验中也明确验证了这一点



### 12. 方法区的垃圾回收

有些人认为方法区（如HotSpot虚拟机中的元空间或者永久代）是没有垃圾收集行为的，其实不然。《Java虚拟机规范》对方法区的约束是非常宽松的，提到过可以不要求虚拟机在方法区中实现垃圾收集。事实上也确实有未实现或未能完整实现方法区类型卸载的收集器存在（如JDK11时期的ZGC收集器就不支持类卸载）。

一般来说这个区域的回收效果比较难令人满意，尤其是类型的卸载，条件相当苛刻。但是这部分区域的回收有时又确实是必要的。以前Sun公司的Bug列表中，曾出现过的若干个严重的Bug就是由于低版本的HotSpot虚拟机对此区域未完全回收而导致内存泄漏。

方法区的垃圾收集主要回收两部分内容：常量池中废弃的常量和不再使用的类型。

**常量**

- 先来说说方法区内常量池之中主要存放的两大类常量：字面量和符号引用。字面量比较接近Java语言层次的常量概念，如文本字符串、被声明为final 的常量值等。而符号引用则属于编译原理方面的概念，包括下面三类常量：
  1. 类和接口的全限定名
  2. 字段的名称和描述符
  3. 方法的名称和描述符
- HotSpot虚拟机对常量池的回收策略是很明确的，只要常量池中的常量没有被任何地方引用，就可以被回收。
- 回收废弃常量与回收Java堆中的对象非常类似。



**类型**

- 判定一个常量是否“废弃”还是相对简单，而要判定一个类型是否属于“不再被使用的类”的条件就比较苛刻了。需要同时满足下面三个条件：
  - 该类所有的实例都已经被回收，也就是Java堆中不存在该类及其任何派生子类的实例。
  - 加载该类的类加载器已经被回收，这个条件除非是经过精心设计的可替换类加载器的场景，如OSGi、JSP的重加载等，否则通常是很难达成的。
  - 该类对应的java.lang.class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。
- Java虚拟机被允许对满足上述三个条件的无用类进行回收，这里说的仅仅是“被允许”，而并不是和对象一样，没有引用了就必然会回收。关于是否要对类型进行回收， HotSpot虚拟机提供了-Xnoclassgc参数进行控制，还可以使用-verbose:class以及-XX:+TraceClass-Loading、-XX:+TraceClassUnLoading查看类加载和卸载信息
- 在大量使用反射、动态代理、CGLib等字节码框架，动态生成JSP以及OSGi这类频繁自定义类加载器的场景中，通常都需要Java虚拟机具备类型卸载的能力，以保证不会对方法区造成过大的内存压力。





## 九、对象的实例化内存布局与访问定位

![](JVM.assets/91.png)





### 1. 对象的实例化

#### 1.1 创建对象的方式

1. new
   - 最常见的方式new Object();
   - 变形1：Xxx的静态方法
   - 变形2：XxxBuilder/XxxFactory的静态方法
2. Class的newInstance()：反射的方式，只能调用空参的构造器，权限必须是public
3. Constructor的newInstance(Xxx)：反射的方式，可以调用空参，带参的构造器，权限没有要求
4. 使用clone()：不调用任何构造器，当前类需要实现Cloneable接口，实现clone()
5. 使用反序列化：从文件或网络中获取一个对象的二进制流
6. 第三方库Objenesis





#### 1.2 创建对象的步骤

1. 判断对象对应的类是否加载、链接、初始化
2. 为对象分配内存
   - 如果内存规整 - 指针碰撞
   - 如果内存不规整 - 虚拟机需要维护一个列表，来进行空闲列表分配
3. 处理并发安全问题
   - 采用CAS失败重试、区域加锁保证更新的原子性
   - 每个线程预先分配一个TLAB - 通过 -XX:+/-UseTLAB参数来设置
4. 初始化分配到的空间
   - 所有属性设置默认值，保证对象实例字段在不赋值的情况下也能使用
5. 设置对象的对象头
   - 将对象的所属类(即类的元数据信息)、对象的HashCode和对象的GC信息、锁信息等数据存储在对象的对象头中。
6. 执行init方法进行初始化
   - 初始化成员变量，执行实例化代码块，调用类的构造方法，并把堆内对象的首地址赋值给引用变量。



### 2. 对象的内存布局

![](JVM.assets/90.png)



#### 2.1 对象头(Header)

包含两部分：

1. 运行时元数据(Mark Word)
   - 哈希值(HashCode)
   - GC分代年龄
   - 锁状态标志
   - 线程持有的锁
   - 偏向线程ID
   - 偏向时间戳
2. 类型指针
   - 指向类元数据InstanceKlasss，确定该对象所属的类型

说明：如果是数组，还需要记录数组的长度



#### 2.2 实例数据(Instance Data)

说明：它是对象真正存储的有效信息，包括程序代码中定义的各种类型的字段(包括从父类继承下来的和本身拥有的字段)

规则

- 相同宽度的字段总是被分配在一起
- 父类定义的变量会出现在子类之前
- 如果CompactFields参数为true(默认为true)：子类的窄变量可能插入到父类变量的空隙



#### 2.3 对齐填充(Padding)

不是必须的，也没有特别的含义，仅仅起到占位符的作用。





### 3. 对象的访问定位

JVM是如何通过栈帧中的对象引用访问到其内部的对象实例的呢？

![](JVM.assets/92.png)

- JVM是如何通过栈帧中的对象引用访问到其内部的对象实例的呢？

  定位，通过栈上reference访问



对象访问方式主要有两种

1. 句柄访问
   ![](JVM.assets/93.png)

2. 直接访问(Hotspot采用)

   ![](JVM.assets/94.png)





## 十、直接内存

- 不是虚拟机运行时数据区的一部分，也不是《Java虚拟机规范》中定义的内存区域。
- 直接内存是在Java堆外的、直接向系统申请的内存区间。
- 来源于NIO，通过存在堆中的DirectByteBuffer操作Native内存
- 通常，访问直接内存的速度会优于Java堆。即读写性能高。
  - 因此出于性能考虑，读写频繁的场合可能会考虑使用直接内存。
  - Java的NIO库允许Java程序使用直接内存，用于数据缓冲区

![](JVM.assets/95.png)

读写文件，需要与磁盘交互，需要由用户态切换到内核态。在内核态时，需要内存如上图的操作。使用IO，见右图。这里需要两份内存存储重复数据，效率低。



![](JVM.assets/96.png)

使用NIO时，如上图。操作系统划出的直接缓存区可以被java代码直接访问，只有一份。NIO适合对大文件的读写操作。



- 也可能导致OutOfMemoryError异常
- 由于直接内存在Java堆外，因此它的大小不会直接受限于-Xmx指定的最大堆大小，但是系统内存是有限的，Java堆和直接内存的总和依然受限于操作系统能给出的最大内存。
- 缺点：
  - 分配回收成本较高
  - 不受JVM内存回收管理
- 直接内存大小可以通过MaxDirectMemorySize设置如果不指定，默认与堆的最大值-Xmx参数值一致

![](JVM.assets/97.png)

简单理解：java process memory = java heap + native memory





## 十一、执行引擎

![](JVM.assets/98.png)

![](JVM.assets/99.png)



### 1. 概述

- 执行引擎是Java虚拟机核心的组成部分之一。
- “虚拟机”是一个相对于“物理机”的概念，这两种机器都有代码执行能力，其区别是物理机的执行引擎是直接建立在处理器、缓存、指令集和操作系统层面上的，而虚拟机的执行引擎则是由软件自行实现的，因此可以不受物理条件制约地定制指令集与执行引擎的结构体系，能够执行那些不被硬件直接支持的指令集格式。



JVM的主要任务是负责装载字节码到其内部，但字节码并不能够直接运行在操作系统之上，因为字节码指令并非等价于本地机器指令，它内部包含的仅仅只是一些能够被JVM所识别的字节码指令、符号表，以及其他辅助信息。

那么，如果想要让一个Java程序运行起来，执行引擎（ExecutionEngine）的任务就是将字节码指令解释/编译为对应平台上的本地机器指令才可以。简单来说，JVM中的执行引擎充当了将高级语言翻译为机器语言的译者。



### 2. 工作过程

![](JVM.assets/100.png)

1. 执行引擎在执行的过程中究竟需要执行什么样的字节码指令完全依赖于PC寄存器。
2. 每当执行完一项指令操作后， PC寄存器就会更新下一条需要被执行的指令地址。
3. 当然方法在执行的过程中，执行引擎有可能会通过存储在局部变量表中的对象引用准确定位到存储在Java堆区中的对象实例信息，以及通过对象头中的元数据指针定位到目标对象的类型信息。



从外观上来看，所有的Java虚拟机的执行引擎输入、输出都是一致的：输入的是字节码二进制流，处理过程是字节码解析执行的等效过程，输出的是执行结果。



### 3. Java代码编译和执行的过程

![](JVM.assets/101.png)

大部分的程序代码转换成物理机的目标代码或虚拟机能执行的指令集之前，都需要经过上图中的各个步骤。



Java代码编译是由Java源码编译器来完成，流程图如下所示：

![](JVM.assets/102.png)



JaVa字节码的执行是由JVM执行引擎来完成，流程图如下所示：

![](JVM.assets/103.png)



**问题：什么是解释器（Interpreter），什么是JIT编译器？**

解释器：当Java虚拟机启动时会根据预定义的规范对字节码采用逐行解释的方式执行，将每条字节码文件中的内容“翻译”为对应平台的本地机器指令执行。

JIT（JustInTimeCompiler）编译器：就是虚拟机将源代码直接编译成和本地机器平台相关的机器语言。



**问题：为什么说Java是半编译半解释型语言？**

JDK1.0时代，将Java语言定位为“解释执行”还是比较准确的。再后来， Java也发展出可以直接生成本地代码的编译器。

现在JVM在执行Java代码的时候，通常都会将解释执行与编译执行二者结合起来进行。



![](JVM.assets/104.png)





### 4. 机器码、指令、汇编语言

1. 机器码

- 各种用二进制编码方式表示的指令，叫做机器指令码。开始，人们就用它采编写程序，这就是机器语言。
- 机器语言虽然能够被计算机理解和接受，但和人们的语言差别太大，不易被人们理解和记忆，并且用它编程容易出差错。
- 用它编写的程序一经输入计算机，CPU直接读取运行，因此和其他语言编的程序相比，执行速度最快。
- 机器指令与CPU紧密相关，所以不同种类的CPU所对应的机器指令也就不同。



2. 指令

- 由于机器码是有0和1组成的二进制序列，可读性实在太差，于是人们发明了指令。
- 指令就是把机器码中特定的0和1序列，简化成对应的指令（一般为英文简写，如mov， inc等），可读性稍好
- 由于不同的硬件平台，执行同一个操作，对应的机器码可能不同，所以不同的硬件平台的同一种指令（比如mov），对应的机器码也可能不同。



3. 指令集

- 不同的硬件平台，各自支持的指令，是有差别的。因此每个平台所支持的指令，称之为对应平台的指令集。
- 如常见的
  - x86指令集，对应的是x86架构的平台
  - ARM指令集，对应的是ARM架构的平台



4. 汇编语言

- 由于指令的可读性还是太差，于是人们又发明了汇编语言。
- 在汇编语言中，用助记符（Mnemonics）代替机器指令的操作码，用地址符号（Symbol）或标号（Label）代替指令或操作数的地址。
- 在不同的硬件平台，汇编语言对应着不同的机器语言指令集，通过汇编过程转换成机器指令。
  - 由于计算机只认识指令码，所以用汇编语言编写的程序还必须翻译成机器指令码，计算机才能识别和执行。



5. 高级语言

- 为了使计算机用户编程序更容易些，后来就出现了各种高级计算机语言。高级语言比机器语言、汇编语言更接近人的语言
- 当计算机执行高级语言编写的程序时，仍然需要把程序解释和编译成机器的指令码。完成这个过程的程序就叫做解释程序或编译程序。

![](JVM.assets/105.png)



6. 字节码

- 字节码是一种中间状态（中间码）的二进制代码（文件），它比机器码更抽象，需要直译器转译后才能成为机器码
- 字节码主要为了实现特定软件运行和软件环境、与硬件环境无关。
- 字节码的实现方式是通过编译器和虚拟机器。编译器将源码编译成字节码，特定平台上的虚拟机器将字节码转译为可以直接执行的指令。
- 字节码的典型应用为Javabytecode。

![](JVM.assets/106.png)



7. C、C++源程序执行过程
   编译过程又可以分成两个阶段：编译和汇编。

- 编译过程：是读取源程序（字符流），只进行词法和语法的分析，将高级语言指令转换为功能等效的汇编代码
- 汇编过程：实际上指把汇编语言代码翻译成目标机器指令的过程。



### 5. 解释器

JVM设计者们的初衷仅仅只是单纯地为了满足Java程序实现跨平台特性，因此避免采用静态编译的方式直接生成本地机器指令，从而诞生了实现解释器在运行时采用逐行解释字节码执行程序的想法。

![](JVM.assets/107.png)



**工作机制**

- 解释器真正意义上所承担的角色就是一个运行时“翻译者”，将字节码文件中的内容“翻译”为对应平台的本地机器指令执行。
- 当一条字节码指令被解释执行完成后，接着再根据PC寄存器中记录的下一条需要被执行的字节码指令执行解释操作。



**解释器分类**

在Java的发展历史里，一共有两套解释执行器，即古老的**字节码解释器**、现在普遍使用的**模板解释器**。

- 字节码解释器在执行时通过**纯软件代码**模拟字节码的执行，效率非常低下。
- 而模板解释器将每一条字节码和一个模板函数相关联，模板函数中直接产生这条字节码执行时的机器码，从而很大程度上提高了解释器的性能。
  - 在HotSpotVM中，解释器主要由Interpreter模块和Code模块构成。
    - Interpreter模块：实现了解释器的核心功能
    - Code模块：用于管理HotSpotVM在运行时生成的本地机器指令



**现状**

- 由于解释器在设计和实现上非常简单，因此除了Java语言之外，还有许多高级语言同样也是基于解释器执行的，比如Python、Perl、Ruby等。但是在今天，基于解释器执行已经沦落为低效的代名词，并且时常被一些 C/C++程序员所调侃。
- 为了解决这个问题，JVM平台支持一种叫作即时编译的技术。即时编译的目的是避免函数被解释执行，而是将整个函数体编译成为机器码，每次函数执行时，只执行编译后的机器码即可，这种方式可以使执行效率大幅度提升。
- 不过无论如何，基于解释器的执行模式仍然为中间语言的发展做出了不可磨灭的贡献。





### 6. JIT编译器

> HotSpotVM是目前市面上高性能虚拟机的代表作之一。它采用解释器与即时编译器并存的架构。在Java虚拟机运行时，解释器和即时编译器能够相互协作，各自取长补短，尽力去选择最合适的方式来权衡编译本地代码的时间和直接解释执行代码的时间。

**java代码的执行分类**

- 第一种是将源代码编译成字节码文件，然后在运行时通过解释器将字节码文件转为机器码执行
- 第二种是编译执行（直接编译成机器码）。现代虚拟机为了提高执行效率，会使用即时编译技术（JIT，Just In Time）将方法编译成机器码后再执行



**问题来了**

既然HotSpotVM中已经内置JIT编译器了，那么为什么还需要再使用解释器来“拖累”程序的执行性能呢？

- 当程序启动后，解释器可以马上发挥作用，省去编译的时间，立即执行。
- 编译器要想发挥作用，把代码编译成本地代码，需要一定的执行时间。但编译为本地代码后，执行效率高。

所以

尽管JRockitVM中程序的执行性能会非常高效，但程序在启动时必然需要花费更长的时间来进行编译。对于服务端应用来说，启动时间并非是关注重点，但对于那些看中启动时间的应用场景而言，或许就需要采用解释器与即时编译器并存的架构来换取一个平衡点。在此模式下，当Java虚拟器启动时，解释器可以首先发挥作用，而不必等待即时编译器全部编译完成后再执行，这样可以省去许多不必要的编译时间。随着时间的推移，编译器发挥作用，把越来越多的代码编译成本地代码，获得更高的执行效率。

同时，解释执行在编译器进行激进优化不成立的时候，作为编译器的“逃生门”。



**HotSpot JVM的执行方式**

当虚拟机启动的时候，解释器可以首先发挥作用，而不必等待即时编译器全部编译完成再执行，这样可以省去许多不必要的编译时间。并且随着程序运行时间的推移，即时编译器逐渐发挥作用，根据热点探测功能，将有价值的字节码编译为本地机器指令，以换取更高的程序执行效率。



**案例**

注意解释执行与编译执行在线上环境微妙的辩证关系。机器在热机状态可以承受的负载要大于冷机状态。如果以热机状态时的流量进行切流，可能使处于冷机状态的服务器因无法承载流量而假死。

在生产环境发布过程中，以分批的方式进行发布，根据机器数量划分成多个批次，每个批次的机器数至多占到整个集群的1/8。曾经有这样的故障案例：某程序员在发布平台进行分批发布，在输入发布总批数时，误填写成分为两批发布。如果是热机状态，在正常情况下一半的机器可以勉强承载流量，但由于刚启动的JVM均是解释执行，还没有进行热点代码统计和JIT动态编译，导致机器启动之后，当前1/2发布成功的服务器马上全部岩机，此故障说明了JIT的存在。一阿里团队



#### 6.1 概念解释

- Java语言的“编译期”其实是一段“不确定”的操作过程，因为它可能是指一个前端编译器（其实叫“编译器的前端”更准确一些）把.java文件转变成.class文件的过程；
- 也可能是指虚拟机的后端运行期编译器（JIT编译器，JustInTimeCompiler）把字节码转变成机器码的过程。
- 还可能是指使用静态提前编译器（AOT编译器，AheadOfTimeCompiler）直接把.java文件编译成本地机器代码的过程。



- 前端编译器：Sun的Javac、EclipseJDT中的增量式编译器（ECJ） 
- JIT 编译器：HotSpot VM 的Cl、C2 编译器。
- AOT编译器：GNU Compiler for the Java（GCJ）、Excelsior JET。



#### 6.2 热点代码及探测方式

当然是否需要启动JIT编译器将字节码直接编译为对应平台的本地机器指令，则需要根据代码被调用执行的频率而定。关于那些需要被编译为本地代码的字节码，也被称之为“热点代码”，JIT编译器在运行时会针对那些频繁被调用的“热点代码”做出深度优化，将其直接编译为对应平台的本地机器指令，以此提升Java程序的执行性能。

- 一个被多次调用的方法，或者是一个方法体内部循环次数较多的循环体都可以被称之为“热点代码”，因此都可以通过JIT编译器编译为本地机器指令。由于这种编译方式发生在方法的执行过程中，因此也被称之为栈上替换，或简称为OSR（OnStack Replacement）编译。
- 一个方法究竟要被调用多少次，或者一个循环体究竟需要执行多少次循环才可以达到这个标准？必然需要一个明确的阈值，JIT编译器才会将这些“热点代码”编译为本地机器指令执行。这里主要依靠热点探测功能
- 目前HotSpotVM所采用的热点探测方式是基于计数器的热点探测。
- 采用基于计数器的热点探测，HotSpotVM将会为每一个方法都建立2个不同类型的计数器，分别为方法调用计数器（InvocationCounter）和回边计数器（Back Edge Counter）。
  - 方法调用计数器用于统计方法的调用次数
  - 回边计数器则用于统计循环体执行的循环次数





#### 6.3 方法调用计数器

- 这个计数器就用于统计方法被调用的次数，它的默认阈值在client模式下是1500次，在Server模式下是10000次。超过这个阈值，就会触发JIT编译。
- 这个阈值可以通过虚拟机参数-XX:CompileThreshold来人为设定。
- 当一个方法被调用时，会先检查该方法是否存在被JIT编译过的版本，如果存在，则优先使用编译后的本地代码来执行。如果不存在已被编译过的版本，则将此方法的调用计数器值加1，然后判断方法调用计数器与回边计数器值之和是否超过方法调用计数器的值。如果已超过值，那么将会向即时编译器提交一个该方法的代码编译请求。

![](JVM.assets/108.png)



**热度衰减**

- 如果不做任何设置，方法调用计数器统计的并不是方法被调用的绝对次数，而是一个相对的执行频率，即一段时间之内方法被调用的次数。当超过一定的时间限度，如果方法的调用次数仍然不足以让它提交给即时编译器编译，那这个方法的调用计数器就会被减少一半，这个过程称为方法调用计数器热度的衰减（CounterDecay），而这段时间就称为此方法统计的半衰周期（CounterHalfLifeTime）。
- 进行热度衰减的动作是在虚拟机进行垃圾收集时顺便进行的，可以使用虚拟机参数-XX:-UseCounterDecay来关闭热度衰减，让方法计数器统计方法调用的绝对次数，这样，只要系统运行时间足够长，绝大部分方法都会被编译成本地代码。
- 另外，可以使用-XX:CounterHalfLifeTime参数设置半衰周期的时间，单位是秒。





#### 6.4 回边计数器

它的作用是统计一个方法中循环体代码执行的次数，在字节码中遇到控制流向后跳转的指令称为“回边”（BackEdge）。显然，建立回边计数器统计的目的就是为了触发OSR编译。

![](JVM.assets/109.png)



#### 6.5 设置程序执行方式

缺省情况下HotSpotVM是采用解释器与即时编译器并存的架构，当然开发人员可以根据具体的应用场景，通过命令显式地为Java虚拟机指定在运行时到底是完全采用解释器执行，还是完全采用即时编译器执行。如下所示：

- -Xint：完全采用解释器模式执行程序；
- -Xcomp：完全采用即时编译器模式执行程序。如果即时编译出现问题，解释器会介入执行。
- -Xmixed：采用解释器+即时编译器的混合模式共同执行程序。



#### 6.6 JIT分类

在HotSpotVM中内嵌有两个JIT编译器，分别为Client Compiler和Server Compiler，但大多数情况下我们简称为C1编译器和C2编译器。开发人员可以通过如下命令显式指定Java虚拟机在运行时到底使用哪一种即时编译器，如下所示：

- -client：指定Java虚拟机运行在client模式下，并使用C1编译器；
  C1编译器会对字节码进行简单和可靠的优化，耗时短。以达到更快的编译速度。
- -server：指定Java虚拟机运行在Server模式下，并使用C2编译器。
  C2进行耗时较长的优化，以及激进优化。但优化的代码执行效率更高。



分层编译（TieredCompilation）策略：程序解释执行（不开启性能监控）可以触发C1编译，将字节码编译成机器码，可以进行简单优化，也可以加上性能监控，C2编译会根据性能监控信息进行激进优化。

不过在Java7版本之后，一旦开发人员在程序中显式指定命令“-server" 时，默认将会开启分层编译策略，由C1编译器和C2编译器相互协作共同来执行编译任务。



**C1和C2编译器不同的优化策略：**

- 在不同的编译器上有不同的优化策略，C1编译器上主要有方法内联，去虚拟化、冗余消除。
  - 方法内联：将引用的函数代码编译到引用点处，这样可以减少栈帧的生成，减少参数传递以及跳转过程
  - 去虚拟化：对唯一的实现类进行内联
  - 冗余消除：在运行期间把一些不会执行的代码折叠掉
- C2的优化主要是在全局层面，逃逸分析是优化的基础。基于逃逸分析在C2上有如下几种优化：
  - 标量替换：用标量值代替聚合对象的属性值
  - 栈上分配：对于未逃逸的对象分配对象在栈而不是堆
  - 同步消除：清除同步操作，通常指synchronized



总结：

- 一般来讲，JIT编译出来的机器码性能比解释器高。
- C2编译器启动时长比C1编译器慢，系统稳定执行以后，C2编译器执行速度远远快于C1编译器。





## 十二、StringTable

### 1. String的基本特性

- String：字符串，使用一对""引起来表示。
  - String s1 = "atguigu"; //字面量的定义方式
  - String s2 = new String("hello");
- String声明为final的，不可被继承
- String实现了Serializable接口：表示字符串是支持序列化的。实现了Comparable接口：表示String可以比较大小
- String在jdk8及以前内部定义了final char[］value用于存储字符串数据。jdk9时改为byte[]



- String：代表不可变的字符序列。简称：不可变性。
  - 当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值。
  - 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
  - 当调用string的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
- 通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。



字符串常量池中是不会存储相同内容的字符串的。

- String的StringPool是一个固定大小的Hashtable，默认值大小长度是1009。如果放进StringPool的String非常多，就会造成Hash冲突严重，从而导致链表会很长，而链表长了后直接会造成的影响就是当调用 String.intern时性能会大幅下降。
- 使用-XX:StringTableSize可设置StringTable的长度
- 在jdk6中StringTable是固定的，就是1009的长度，所以如果常量池中的字符串过多就会导致效率下降很快。StringTableSize设置没有要求
- 在jdk7中，StringTable的长度默认值是60013，StringTableSize设置没有要求。
- jdk8开始，设置StringTable的长度的话，1009是可设置的最小值。



### 2. String的内存分配

- 在Java语言中有8种基本数据类型和一种比较特殊的类型string。这些类型为了使它们在运行过程中速度更快、更节省内存，都提供了一种常量池的概念。
- 常量池就类似一个Java系统级别提供的缓存。8种基本数据类型的常量池都是系统协调的，String类型的常量池比较特殊。它的主要使用方法有两种。
  - 直接使用双引号声明出来的String对象会直接存储在常量池中。比如:String info = "atguigu.com";
  - 如果不是用双引号声明的String对象，可以使用string提供的 intern()方法。这个后面重点谈



- Java6及以前，字符串常量池存放在永久代。
- Java7中Oracle的工程师对字符串池的逻辑做了很大的改变，即将字符串常量池的位置调整到Java堆内。
  - 所有的字符串都保存在堆（Heap）中，和其他普通对象一样，这样可以让你在进行调优应用时仅需要调整堆大小就可以了。
  - 字符串常量池概念原本使用得比较多，但是这个改动使得我们有足够的理由让我们重新考虑在Java7中使用String.intern()。
-  Java8元空间，字符串常量在堆

![](JVM.assets/110.png)

![111](JVM.assets/111.png)



### 3. 熟悉String的基本操作

Java语言规范里要求完全相同的字符串字面量，应该包含同样的Unicode 字符序列（包含同一份码点序列的常量)，并且必须是指向同一个String 类实例。

```java
public class StringTest4 {
    public static void main(String[] args) {
        System.out.println();//2293
        System.out.println("1");//2294
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");//2303
        //如下的字符串"1" 到 "10"不会再次加载
        System.out.println("1");//2304
        System.out.println("2");//2304
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");//2304
    }
}
```



```java
class Memory {
    public static void main(String[] args) {//line 1
        int i = 1;//line 2
        Object obj = new Object();//line 3
        Memory mem = new Memory();//line 4
        mem.foo(obj);//line 5
    }//line 9

    private void foo(Object param) {//line 6
        String str = param.toString();//line 7
        System.out.println(str);
    }//line 8
}
```

![](JVM.assets/112.png)





### 4. 字符串拼接操作

- 常量与常量的拼接结果在常量池，原理是编译期优化
- 常量池中不会存在相同内容的常量。
- 只要其中有一个是变量，结果就在堆中。变量拼接的原理是stringBuilder
- 如果拼接的结果调用intern()方法，则主动将常量池中还没有的字符串对象放入池中，并返回此对象地址。

```java
import org.junit.Test;

/**
 * 字符串拼接操作
 * @author shkstart  shkstart@126.com
 * @create 2020  0:59
 */
public class StringTest5 {
    @Test
    public void test1(){
        String s1 = "a" + "b" + "c";//编译期优化：等同于"abc"
        String s2 = "abc"; //"abc"一定是放在字符串常量池中，将此地址赋给s2
        /*
         * 最终.java编译成.class,再执行.class
         * String s1 = "abc";
         * String s2 = "abc"
         */
        System.out.println(s1 == s2); //true
        System.out.println(s1.equals(s2)); //true
    }

    @Test
    public void test2(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";//编译期优化
        //如果拼接符号的前后出现了变量，则相当于在堆空间中new String()，具体的内容为拼接的结果：javaEEhadoop
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
        //intern():判断字符串常量池中是否存在javaEEhadoop值，如果存在，则返回常量池中javaEEhadoop的地址；
        //如果字符串常量池中不存在javaEEhadoop，则在常量池中加载一份javaEEhadoop，并返回次对象的地址。
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }

    @Test
    public void test3(){
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        /*
        如下的s1 + s2 的执行细节：(变量s是我临时定义的）
        ① StringBuilder s = new StringBuilder();
        ② s.append("a")
        ③ s.append("b")
        ④ s.toString()  --> 约等于 new String("ab")

        补充：在jdk5.0之后使用的是StringBuilder,在jdk5.0之前使用的是StringBuffer
         */
        String s4 = s1 + s2;//
        System.out.println(s3 == s4);//false
    }
    /*
    1. 字符串拼接操作不一定使用的是StringBuilder!
       如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译期优化，即非StringBuilder的方式。
    2. 针对于final修饰类、方法、基本数据类型、引用数据类型的量的结构时，能使用上final的时候建议使用上。
     */
    @Test
    public void test4(){
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//true
    }
    //练习：
    @Test
    public void test5(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//s4:常量
        String s5 = s4 + "hadoop";
        System.out.println(s1 == s5);//true

    }

    /*
    体会执行效率：通过StringBuilder的append()的方式添加字符串的效率要远高于使用String的字符串拼接方式！
    详情：① StringBuilder的append()的方式：自始至终中只创建过一个StringBuilder的对象
          使用String的字符串拼接方式：创建过多个StringBuilder和String的对象
         ② 使用String的字符串拼接方式：内存中由于创建了较多的StringBuilder和String的对象，内存占用更大；如果进行GC，需要花费额外的时间。

     改进的空间：在实际开发中，如果基本确定要前前后后添加的字符串长度不高于某个限定值highLevel的情况下,建议使用构造器实例化：
               StringBuilder s = new StringBuilder(highLevel);//new char[highLevel]
     */
    @Test
    public void test6(){

        long start = System.currentTimeMillis();

//        method1(100000);//4014
        method2(100000);//7

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));
    }

    public void method1(int highLevel){
        String src = "";
        for(int i = 0;i < highLevel;i++){
            src = src + "a";//每次循环都会创建一个StringBuilder、String
        }
//        System.out.println(src);

    }

    public void method2(int highLevel){
        //只需要创建一个StringBuilder
        StringBuilder src = new StringBuilder();
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
//        System.out.println(src);
    }
}

```



### 5. intern()的使用

- 如果不是用双引号声明的string对象，可以使用string提供的intern方法：intern 方法会从字符串常量池中查询当前字符串是否存在，若不存在就会将当前字符串放入常量池中。比如:String myInfo = new String("I love atguigu").intern();
- 也就是说，如果在任意字符串上调用string.intern方法，那么其返回结果所指向的那个类实例，必须和直接以常量形式出现的字符串实例完全相同。因此，下列表达式的值必定是true：("a" + "b" + "c").intern() == "abc"
- 通俗点讲，InternedString就是确保字符串在内存里只有一份拷贝，这样可以节约内存空间，加快字符串操作任务的执行速度。注意，这个值会被存放在字符串内部池(String Intern Pool）。

```java
/**
 * 如何保证变量s指向的是字符串常量池中的数据呢？
 * 有两种方式：
 * 方式一： String s = "shkstart";//字面量定义的方式
 * 方式二： 调用intern()
 *         String s = new String("shkstart").intern();
 *         String s = new StringBuilder("shkstart").toString().intern();
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  18:49
 */
public class StringIntern {
    public static void main(String[] args) {

        String s = new String("1");
        s.intern();//调用此方法之前，字符串常量池中已经存在了"1"
        String s2 = "1";
        System.out.println(s == s2);//jdk6：false   jdk7/8：false


        String s3 = new String("1") + new String("1");//s3变量记录的地址为：new String("11")
        //执行完上一行代码以后，字符串常量池中，是否存在"11"呢？答案：不存在！！
        s3.intern();//在字符串常量池中生成"11"。如何理解：jdk6:创建了一个新的对象"11",也就有新的地址。
                                            //         jdk7:此时常量中并没有创建"11",而是创建一个指向堆空间中new String("11")的地址
        String s4 = "11";//s4变量记录的地址：使用的是上一行代码代码执行时，在常量池中生成的"11"的地址
        System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
    }


}

```



#### 5.1 new String()创建了几个对象

new String("ab")会创建几个对象？看字节码：两个

- 一个对象是：new关键字在堆空间创建的
- 另一个对象是：字符串常量池中的对象"ab"



思考：new String("a") + new String("b")呢？

- 对象1：new StringBuilder()
- 对象2：new String("a")
- 对象3：常量池中的"a"
- 对象4：new String("b")
- 对象5：常量池中的"b"

深度剖析：StringBuilder的toString();

- 对象6：new String("ab")。强调一下，toString()的调用，在字符串常量池中，没有生成"ab"



#### 5.2 intern()面试难题

```java
import org.junit.Test;

/**
 * 如何保证变量s指向的是字符串常量池中的数据呢？
 * 有两种方式：
 * 方式一： String s = "shkstart";//字面量定义的方式
 * 方式二： 调用intern()
 *         String s = new String("shkstart").intern();
 *         String s = new StringBuilder("shkstart").toString().intern();
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  18:49
 */
public class StringIntern {
    public static void main(String[] args) {

        String s = new String("1");
        s.intern();//调用此方法之前，字符串常量池中已经存在了"1"
        String s2 = "1";
        System.out.println(s == s2);//jdk6：false   jdk7/8：false


        String s3 = new String("1") + new String("1");//s3变量记录的地址为：new String("11")
        //执行完上一行代码以后，字符串常量池中，是否存在"11"呢？答案：不存在！！
        s3.intern();//在字符串常量池中生成"11"。如何理解：jdk6:创建了一个新的对象"11",也就有新的地址。
                                            //         jdk7:此时常量中并没有创建"11",而是创建一个指向堆空间中new String("11")的地址
        String s4 = "11";//s4变量记录的地址：使用的是上一行代码代码执行时，在常量池中生成的"11"的地址
        System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
    }


}

```

![](JVM.assets/113.png)



##### 拓展

```java
public class StringIntern1 {
    public static void main(String[] args) {
        //StringIntern.java中练习的拓展：
        String s3 = new String("1") + new String("1");//new String("11")
        //执行完上一行代码以后，字符串常量池中，是否存在"11"呢？答案：不存在！！
        String s4 = "11";//在字符串常量池中生成对象"11"
        String s5 = s3.intern();
        System.out.println(s3 == s4);//false
        System.out.println(s5 == s4);//true
    }
}
```



总结string的intern（）的使用：

- jdk1.6中，将这个字符串对象尝试放入串池。
  - 如果串池中有，则并不会放入。返回已有的串池中的对象的地址
  - 如果没有，会把此对象复制一份，放入串池，并返回串池中的对象地址
- jdk1.7起，将这个字符串对象尝试放入串池。
  - 如果串池中有，则并不会放入。返回已有的串池中的对象的地址
  - 如果没有，则会把对象的引用地址复制一份，放入串池，并返回串池中的引用地址



#### 5.3 练习

```java
public class StringExer1 {
    public static void main(String[] args) {
        String x = "ab";
        String s = new String("a") + new String("b");//new String("ab")
        //在上一行代码执行完以后，字符串常量池中并没有"ab"

        String s2 = s.intern();//jdk6中：在串池中创建一个字符串"ab"
                               //jdk8中：串池中没有创建字符串"ab",而是创建一个引用，指向new String("ab")，将此引用返回

        System.out.println(s2 == "ab");//jdk6:true  jdk8:true
        System.out.println(s == "ab");//jdk6:false  jdk8:true
    }
}
```

![](JVM.assets/114.png)



```java
public class StringExer2 {
    public static void main(String[] args) {
        String s1 = new String("ab");//执行完以后，会在字符串常量池中会生成"ab"
//        String s1 = new String("a") + new String("b");////执行完以后，不会在字符串常量池中会生成"ab"
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);
    }
}
```



#### 5.4 空间效率测试

```java
import java.util.Random;

/**
 * 使用intern()测试执行效率：空间使用上
 *
 * 结论：对于程序中大量存在存在的字符串，尤其其中存在很多重复字符串时，使用intern()可以节省内存空间。
 *
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  21:17
 */
public class StringIntern2 {
    static final int MAX_COUNT = 1000 * 10000;
    static final String[] arr = new String[MAX_COUNT];

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i++) {
//            arr[i] = new String(String.valueOf(data[i % data.length]));
            arr[i] = new String(String.valueOf(data[i % data.length])).intern();

        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}

```

大的网站平台，需要内存中存储大量的字符串。比如社交网站，很多人都存储：北京市、海淀区等信息。这时候如果字符串都调用 intern（)方法，就会明显降低内存的大小。



### 6. StringTable的垃圾回收

```java
/**
 * String的垃圾回收:
 * -Xms15m -Xmx15m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  21:27
 */
public class StringGCTest {
    public static void main(String[] args) {
        for (int j = 0; j < 100000; j++) {
            String.valueOf(j).intern();
        }
    }
}
```



### 7. G1的String去重操作

- 背景：对许多Java应用（有大的也有小的）做的测试得出以下结果：
  - 堆存活数据集合里面String对象占了25%
  - 堆存活数据集合里面重复的String对象有13.5%
  - String对象的平均长度是45
- 许多大规模的Java应用的瓶颈在于内存，测试表明，在这些类型的应用里面，Java堆中存活的数据集合差不多25%是String对象。更进一步，这里面差不多一半String对象是重复的，重复的意思是说：stringl.equals(string2)=true。堆上存在重复的String对象必然是一种内存的浪费。这个项目将在G1垃圾收集器中实现自动持续对重复的String对象进行去重，这样就能避免浪费内存。



实现：

- 当垃圾收集器工作的时候，会访问堆上存活的对象。对每一个访问的对象都会检查是否是候选的要去重的String对象。
- 如果是，把这个对象的一个引用插入到队列中等待后续的处理。一个去重的线程在后台运行，处理这个队列。处理队列的一个元素意味着从队列删除这个元素，然后尝试去重它引用的String对象。
- 使用一个hashtable来记录所有的被string对象使用的不重复的char数组。当去重的时候，会查这个hashtable，来看堆上是否已经存在一个一模一样的 char数组。
- 如果存在，String对象会被调整引用那个数组，释放对原来的数组的引用，最终会被垃圾收集器回收掉。
- 如果查找失败，char数组会被插入到hashtable，这样以后的时候就可以共享这个数组了。



命令行选项：

- UseStringDeduplication(bool): 开启String去重，默认是不开启的，需要手动开启。
- PrintStringDeduplicationStatistics(bool): 打印详细的去重统计信息
- StringDeduplicationAgeThreshold(uintx): 达到这个年龄的string对象被认为是去重的候选对象





## 十三、垃圾回收

### 1. 概述

![](JVM.assets/115.png)



#### 1.1 什么是GC？

- 垃圾收集，不是Java语言的伴生产物。早在1960年，第一门开始使用内存动态分配和垃圾收集技术的Lisp语言诞生。
- 关于垃圾收集有三个经典问题：
  - 哪些内存需要回收？
  - 什么时候回收？
  - 如何回收？
- 垃圾收集机制是Java的招牌能力，极大地提高了开发效率。如今，垃圾收集几乎成为现代语言的标配，即使经过如此长时间的发展，Java的垃圾收集机制仍然在不断的演进中，不同大小的设备、不同特征的应用场景，对垃圾收集提出了新的挑战，这当然也是面试的热点。



- 什么是垃圾（Garbage）呢？
  - 垃圾是指在运行程序中没有任何指针指向的对象，这个对象就是需要被回收的垃圾。
  - 外文:An object is considered garbage when it can no Ilonger be reached from any pointer in the running program.
- 如果不及时对内存中的垃圾进行清理，那么，这些垃圾对象所占的内存空间会一直保留到应用程序结束，被保留的空间无法被其他对象使用。甚至可能导致内存溢出。





#### 1.2 为什么需要GC？

- 对于高级语言来说，一个基本认知是如果不进行垃圾回收，内存迟早都会被消耗完，因为不断地分配内存空间而不进行回收，就好像不停地生产生活垃圾而从来不打扫一样。
- 除了释放没用的对象，垃圾回收也可以清除内存里的记录碎片。碎片整理将所占用的堆内存移到堆的一端，以便JVM将整理出的内存分配给新的对象。
- 随着应用程序所应付的业务越来越庞大、复杂，用户越来越多，没有GC就不能保证应用程序的正常进行。而经常造成STW的GC又跟不上实际的需求，所以才会不断地尝试对GC进行优化。





#### 1.3 Java垃圾回收机制

- 自动内存管理，无需开发人员手动参与内存的分配与回收，这样降低内存泄漏和内存溢出的风险
  - 没有垃圾回收器，java也会和cpp一样，各种悬垂指针，野指针，泄露问题让你头疼不已。
- 自动内存管理机制，将程序员从繁重的内存管理中释放出来，可以更专心地专注于业务开发



- 对于Java开发人员而言，自动内存管理就像是一个黑匣子，如果过度依赖于“自动”，那么这将会是一场灾难，最严重的就会弱化Java开发人员在程序出现内存溢出时定位问题和解决问题的能力。
- 此时，了解JVM的自动内存分配和内存回收原理就显得非常重要，只有在真正了解JVM是如何管理内存后，我们才能够在遇见OutOfMemoryError时，快速地根据错误异常日志定位问题和解决问题。
- 当需要排查各种内存溢出、内存泄漏问题时，当垃圾收集成为系统达到更高并发量的瓶颈时，我们就必须对这些“自动化”的技术实施必要的监控和调节。

![](JVM.assets/116.png)



- 垃圾回收器可以对年轻代回收，也可以对老年代回收，甚至是全堆和方法区的回收。
  - 其中，Java堆是垃圾收集器的工作重点。
- 从次数上讲：
  - 频繁收集Young区
  - 较少收集Old区
  - 基本不动Perm区



### 2. 垃圾回收算法

#### 2.1 垃圾标记阶段

**垃圾标记阶段：对象存活判断**

- 在堆里存放着几乎所有的Java对象实例，在GC执行垃圾回收之前，首先需要区分出内存中哪些是存活对象，哪些是已经死亡的对象。只有被标记为己经死亡的对象，GC才会在执行垃圾回收时，释放掉其所占用的内存空间，因此这个过程我们可以称为垃圾标记阶段。
- 那么在JVM中究竟是如何标记一个死亡对象呢？简单来说，当一个对象已经不再被任何的存活对象继续引用时，就可以宣判为已经死亡。
- 判断对象存活一般有两种方式：引用计数算法和可达性分析算法。



##### 2.1.1引用计数算法

- 引用计数算法（ReferenceCounting）比较简单，对每个对象保存一个整型的引用计数器属性。用于记录对象被引用的情况。
- 对于一个对象A，只要有任何一个对象引用了A，则A的引用计数器就加1；当引用失效时，引用计数器就减1。只要对象A的引用计数器的值为0，即表示对象A不可能再被使用，可进行回收。
- 优点：实现简单，垃圾对象便于辨识；判定效率高，回收没有延迟性。
- 缺点：
  - 它需要单独的字段存储计数器，这样的做法增加了存储空间的开销。
  - 每次赋值都需要更新计数器，伴随着加法和减法操作，这增加了时间开销。
  - 引用计数器有一个严重的问题，即无法处理循环引用的情况。这是一条致命缺陷，导致在Java的垃圾回收器中没有使用这类算法。



**循环引用**

![](JVM.assets/117.png)



**举例：**

```java
/**
 * -XX:+PrintGCDetails
 * 证明：java使用的不是引用计数算法
 * @author shkstart
 * @create 2020 下午 2:38
 */
public class RefCountGC {
    //这个成员属性唯一的作用就是占用一点内存
    private byte[] bigSize = new byte[5 * 1024 * 1024];//5MB

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;
        //显式的执行垃圾回收行为
        //这里发生GC，obj1和obj2能否被回收？
        System.gc();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

![](JVM.assets/118.png)



小结：

- 引用计数算法，是很多语言的资源回收选择，例如因人工智能而更加火热的Python，它更是同时支持引用计数和垃圾收集机制。
- 具体哪种最优是要看场景的，业界有大规模实践中仅保留引用计数机制，以提高吞吐量的尝试。
- Java并没有选择引用计数，是因为其存在一个基本的难题，也就是很难处理循环引用关系。
- Python如何解决循环引用？
  - 手动解除：很好理解，就是在合适的时机，解除引用关系。
  - 使用弱引用weakref，weakref是Python提供的标准库，旨在解决循环引用。



##### 2.1.2 可达性分析算法

> 或叫根搜索算法、追踪性垃圾收集
>

- 相对于引用计数算法而言，可达性分析算法不仅同样具备实现简单和执行高效等特点，更重要的是该算法可以有效地解决在引用计数算法中循环引用的问题，防止内存泄漏的发生。
- 相较于引用计数算法，这里的可达性分析就是Java、C#选择的。这种类型的垃圾收集通常也叫作追踪性垃圾收集（Tracing Garbage Collection）.



- 所谓"GC Roots"根集合就是一组必须活跃的引用。

基本思路：

- 可达性分析算法是以根对象集合（GCRoots）为起始点，按照从上至下的方式搜索被根对象集合所连接的目标对象是否可达。
- 使用可达性分析算法后，内存中的存活对象都会被根对象集合直接或间接连接着，搜索所走过的路径称为引用链（ReferenceChain)
- 如果目标对象没有任何引用链相连，则是不可达的，就意味着该对象己经死亡，可以标记为垃圾对象。
- 在可达性分析算法中，只有能够被根对象集合直接或者间接连接的对象才是存活对象。

![](JVM.assets/119.png)



在Java语言中，GC Roots包括以下几类元素：

- 虚拟机栈中引用的对象
  - 比如：各个线程被调用的方法中使用到的参数、局部变量等。
- 本地方法栈内JNI(通常说的本地方法)引用的对象
- 方法区中类静态属性引用的对象
  - 比如：Java类的引用类型静态变量
- 方法区中常量引用的对象
  - 比如：字符串常量池（StringTable）里的引用
- 所有被同步锁synchronized持有的对象
- Java虚拟机内部的引用。
  - 基本数据类型对应的class对象，一些常驻的异常对象（如：NullPointerException、OutOfMemoryError），系统类加载器。
- 反映java虚拟机内部情况的JMXBean、JVMTI中注册的回调、本地代码缓存等。

![](JVM.assets/120.png)



- 除了这些固定的GCRoots集合以外，根据用户所选用的垃圾收集器以及当前回收的内存区域不同，还可以有其他对象“临时性”地加入，共同构成完整GCRoots集合。比如：分代收集和局部回收（PartialGC）。
  - 如果只针对Java堆中的某一块区域进行垃圾回收（比如：典型的只针对新生代），必须考虑到内存区域是虚拟机自己的实现细节，更不是孤立封闭的，这个区域的对象完全有可能被其他区域的对象所引用，这时候就需要一并将关联的区域对象也加入GCRoots集合中去考虑，才能保证可达性分析的准确性。
- 小技巧：由于Root采用栈方式存放变量和指针，所以如果一个指针，它保存了堆内存里面的对象，但是自己又不存放在堆内存里面，那它就是一个Root。



注意：

- 如果要使用可达性分析算法来判断内存是否可回收，那么分析工作必须在一个能保障一致性的快照中进行。这点不满足的话分析结果的准确性就无法保证。
- 这点也是导致GC进行时必须"Stop The World"的一个重要原因。
  - 即使是号称（几乎）不会发生停顿的CMS收集器中，枚举根节点时也是必须要停顿的。





#### 2.2 对象的finalization机制

- Java语言提供了对象终止（finalization）机制来允许开发人员提供对象被销毁之前的自定义处理逻辑。
- 当垃圾回收器发现没有引用指向一个对象，即：垃圾回收此对象之前，总会先调用这个对象的finalize()方法。
- finalize()方法允许在子类中被重写，用于在对象被回收时进行资源释放。通常在这个方法中进行一些资源释放和清理的工作，比如关闭文件、套接字和数据库连接等。



- 永远不要主动调用某个对象的finalize()方法，应该交给垃圾回收机制调用。理由包括下面三点：
  - 在finalize()时可能会导致对象复活。
  - finalize()方法的执行时间是没有保障的，它完全由GC线程决定，极端情况下，若不发生GC，则finalize()方法将没有执行机会。
  - 一个糟糕的finalize()会严重影响GC的性能。
- 从功能上来说，finalize()方法与c++中的析构函数比较相似，但是Java采用的是基于垃圾回收器的自动内存管理机制，所以finalize()方法在本质上不同于c++中的析构函数。
- 由于finalize()方法的存在，虚拟机中的对象一般处于三种可能的状态。



对象的三种状态：

- 如果从所有的根节点都无法访问到某个对象，说明对象已经不再使用了。一般来说，此对象需要被回收。但事实上，也并非是“非死不可”的，这时候它们暂时处于“缓刑”阶段。一个无法触及的对象有可能在某一个条件下“复活”自己，如果这样，那么对它的回收就是不合理的，为此，定义虚拟机中的对象可能的三种状态。如下：
  - 可触及的：从根节点开始，可以到达这个对象。
  - 可复活的：对象的所有引用都被释放，但是对象有可能在finalize()中复活。
  - 不可触及的：对象的finalize()被调用，并且没有复活，那么就会进入不可触及状态。不可触及的对象不可能被复活，因为finalize()只会被调用一次。
- 以上3种状态中，是由于finalize()方法的存在，进行的区分。只有在对象不可触及时才可以被回收。



具体过程：

判定一个对象objA是否可回收，至少要经历两次标记过程：

1. 如果对象objA到GCRoots没有引用链，则进行第一次标记。
2. 进行筛选，判断此对象是否有必要执行finalize()方法
   1. 如果对象objA没有重写finalize()方法，或者finalize()方法已经被虚拟机调用过，则虚拟机视为“没有必要执行”，objA被判定为不可触及的。
   2. 如果对象objA重写了finalize()方法，且还未执行过，那么objA会被插入到F-Queue 队列中，由一个虚拟机自动创建的、低优先级的Finalizer线程触发其finalize()方法执行。
   3. finalize()方法是对象逃脱死亡的最后机会，稍后GC会对F-Queue队列中的对象进行第二次标记。如果objA在finalize()方法中与引用链上的任何一个对象建立了联系，那么在第二次标记时，objA会被移出“即将回收”集合。之后，对象会再次出现没有引用存在的情况。在这个情况下，finalize方法不会被再次调用，对象会直接变成不可触及的状态，也就是说，一个对象的finalize方法只会被调用一次。

```java
/**
 * 测试Object类中finalize()方法，即对象的finalization机制。
 *
 * @author shkstart
 * @create 2020 下午 2:57
 */
public class CanReliveObj {
    public static CanReliveObj obj;//类变量，属于 GC Root


    //此方法只能被调用一次
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this;//当前待回收的对象在finalize()方法中与引用链上的一个对象obj建立了联系
    }


    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            // 对象第一次成功拯救自己
            obj = null;
            System.gc();//调用垃圾回收器
            System.out.println("第1次 gc");
            // 因为Finalizer线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
            System.out.println("第2次 gc");
            // 下面这段代码与上面的完全相同，但是这次自救却失败了
            obj = null;
            System.gc();
            // 因为Finalizer线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```



#### 2.3 垃圾清除阶段

当成功区分出内存中存活对象和死亡对象后，GC接下来的任务就是执行垃圾回收，释放掉无用对象所占用的内存空间，以便有足够的可用内存空间为新对象分配内存。

目前在JVM中比较常见的三种垃圾收集算法是标记-清除算法(Mark-Sweep)、复制算法(Copying)、标记－压缩算法(Mark-Compact)



##### 2.3.1 标记-清除算法

**背景：**
标记－清除算法（Mark-Sweep）是一种非常基础和常见的垃圾收集算法，该算法被J.McCarthy等人在1960年提出并并应用于Lisp语言。

**执行过程：**
当堆中的有效内存空间（availablememory）被耗尽的时候，就会停止整个程序（也被称为stoptheworld），然后进行两项工作，第一项则是标记，第二项则是清除。

- 标记：Collector从引用根节点开始遍历，标记所有被引用的对象。一般是在对象的Header中记录为可达对象。
- 清除：Collector对堆内存从头到尾进行线性的遍历，如果发现某个对象在其Header中没有标记为可达对象，则将其回收。

![](JVM.assets/121.png)



- 缺点
  - 效率不算高
  - 在进行GC的时候，需要停止整个应用程序，导致用户体验差
  - 这种方式清理出来的空闲内存是不连续的，产生内存碎片。需要维护一个空闲列表



- 注意：何为清除？
  - 这里所谓的清除并不是真的置空，而是把需要清除的对象地址保存在空闲的地址列表里。下次有新对象需要加载时，判断垃圾的位置空间是否够，如果够，就存放。





##### 2.3.2 复制算法

**背景：**
为了解决标记-清除算法在垃圾收集效率方面的缺陷，M.L.Minsky于 1963年发表了著名的论文，“使用双存储区的Lisp语言垃圾收集器CA LISP Garbage Collector Algorithm Using Serial SecondaryStorage）”。M.L.Minsky 在该论文中描述的算法被人们称为复制（Copying）算法，它也被M.L.Minsky本人成功地引入到了Lisp语言的一个实现版本中。

**核心思想：**
将活着的内存空间分为两块，每次只使用其中一块，在垃圾回收时将正在使用的内存中的存活对象复制到未被使用的内存块中，之后清除正在使用的内存块中的所有对象，交换两个内存的角色，最后完成垃圾回收。

![](JVM.assets/122.png)



优点：

- 没有标记和清除过程，实现简单，运行高效
- 复制过去以后保证空间的连续性，不会出现“碎片”问题。

缺点：

- 此算法的缺点也是很明显的，就是需要两倍的内存空间。
- 对于G1这种分拆成为大量region的GC，复制而不是移动，意味着GC需要维护region之间对象引用关系，不管是内存占用或者时间开销也不小。

特别的：

- 如果系统中的垃圾对象很多，复制算法不会很理想。因为复制算法需要复制的存活对象数量并不会太大，或者说非常低才行。



应用场景：

- 在新生代，对常规应用的垃圾回收，一次通常可以回收70%-99%的内存空间。回收性价比很高。所以现在的商业虚拟机都是用这种收集算法回收新生代。

![](JVM.assets/123.png)



##### 2.3.3 标记-压缩算法

**背景：**
复制算法的高效性是建立在存活对象少、垃圾对象多的前提下的。这种情况在新生代经常发生，但是在老年代，更常见的情况是大部分对象都是存活对象。如果依然使用复制算法，由于存活对象较多，复制的成本也将很高。因此，基于老年代垃圾回收的特性，需要使用其他的算法。

标记-清除算法的确可以应用在老年代中，但是该算法不仅执行效率低下，而且在执行完内存回收后还会产生内存碎片，所以JVM的设计者需要在此基础之上进行改进。标记-压缩(Mark－Compact)算法由此诞生。

1970年前后，G．L．Steele、C．J．Chene和D.S．Wise等研究者发布标记-压缩算法。在许多现代的垃圾收集器中，人们都使用了标记-压缩算法或其改进版本。



执行过程：

- 第一阶段和标记-清除算法一样，从根节点开始标记所有被引用对象
- 第二阶段将所有的存活对象压缩到内存的一端，按顺序排放。
- 之后，清理边界外所有的空间。

![](JVM.assets/124.png)



标记-压缩算法的最终效果等同于标记-清除算法执行完成后，再进行一次内存碎片整理，因此，也可以把它称为标记-清除-压缩(Mark-Sweep-Compact)算法。

二者的本质差异在于标记-清除算法是一种非移动式的回收算法，标记-压缩是移动式的。是否移动回收后的存活对象是一项优缺点并存的风险决策。

可以看到，标记的存活对象将会被整理，按照内存地址依次排列，而未被标记的内存会被清理掉。如此一来，当我们需要给新对象分配内存时，JVM只需要持有一个内存的起始地址即可，这比维护一个空闲列表显然少了许多开销。



优点：

- 消除了标记-清除算法当中，内存区域分散的缺点，我们需要给新对象分配内存时，JVM只需要持有一个内存的起始地址即可。
- 消除了复制算法当中，内存减半的高额代价。

缺点：

- 从效率上来说，标记-整理算法要低于复制算法。
- 移动对象的同时，如果对象被其他对象引用，则还需要调整引用的地址。
- 移动过程中，需要全程暂停用户应用程序。即：STW



##### 2.3.4 对比三种算法

![](JVM.assets/125.png)

效率上来说，复制算法是当之无愧的老大，但是却浪费了太多内存。

而为了尽量兼顾上面提到的三个指标，标记-整理算法相对来说更平滑一些，但是效率上不尽如人意，它比复制算法多了一个标记的阶段，比标记-清除多了一个整理内存的阶段。





#### 2.4 分代收集算法

前面所有这些算法中，并没有一种算法可以完全替代其他算法，它们都具有自已独特的优势和特点。分代收集算法应运而生。

分代收集算法，是基于这样一个事实：不同的对象的生命周期是不一样的。因此，不同生命周期的对象可以采取不同的收集方式，以便提高回收效率。一般是把Java堆分为新生代和老年代，这样就可以根据各个年代的特点使用不同的回收算法，以提高垃圾回收的效率。

在Java程序运行的过程中，会产生大量的对象，其中有些对象是与业务信息相关，比如Http请求中的Session对象、线程、Socket连接，这类对象跟业务直接挂钩，因此生命周期比较长。但是还有一些对象，主要是程序运行过程中生成的临时变量，这些对象生命周期会比较短，比如：String对象，由于其不变类的特性，系统会产生大量的这些对象，有些对象甚至只用一次即可回收。



目前几乎所有的GC都是采用分代收集（GenerationalCollecting）算法执行垃圾回收的。

在HotSpot中，基于分代的概念，GC所使用的内存回收算法必须结合年轻代和老年代各自的特点。

- 年轻代(Young Gen)
  年轻代特点：区域相对老年代较小，对象生命周期短、存活率低，回收频繁。

  这种情况复制算法的回收整理，速度是最快的。复制算法的效率只和当前存活对象大小有关，因此很适用于年轻代的回收。而复制算法内存利用率不高的问题，通过hotspot中的两个survivor的设计得到缓解。

- 老年代(Tenured Gen)
  老年代特点：区域较大，对象生命周期长、存活率高，回收不及年轻代频繁。

  这种情况存在大量存活率高的对象，复制算法明显变得不合适。一般是由标记-清除或者是标记-清除与标记-整理的混合实现。

  - Mark阶段的开销与存活对象的数量成正比。
  - Sweep阶段的开销与所管理区域的大小成正相关。
  - Compact阶段的开销与存活对象的数据成正比。



以HotSpot中的CMS回收器为例，CMS是基于Mark-Sweep实现的，对于对象的回收效率很高。而对于碎片问题，CMS采用基于Mark-Compact算法的SerialOld 回收器作为补偿措施：当内存回收不佳（碎片导致的Concurrent Mode Failure时），将采用Serial Old执行Full GC以达到对老年代内存的整理。

分代的思想被现有的虚拟机广泛使用。几乎所有的垃圾回收器都区分新生代和老年代。



#### 2.5 增量收集算法

上述现有的算法，在垃圾回收过程中，应用软件将处于一种stop the World 的状态。在stop the World状态下，应用程序所有的线程都会挂起，暂停一切正常的工作，等待垃圾回收的完成。如果垃圾回收时间过长，应用程序会被挂起很久，将严重影响用户体验或者系统的稳定性。为了解决这个问题，即对实时垃圾收集算法的研究直接导致了增量收集（IncrementalCollecting）算法的诞生。

**基本思想**

如果一次性将所有的垃圾进行处理，需要造成系统长时间的停顿，那么就可以让垃圾收集线程和应用程序线程交替执行。每次，垃圾收集线程只收集一小片区域的内存空间，接着切换到应用程序线程。依次反复，直到垃圾收集完成。

总的来说，增量收集算法的基础仍是传统的标记-清除和复制算法。增量收集算法通过对线程间冲突的妥善处理，允许垃圾收集线程以分阶段的方式完成标记、清理或复制工作。

**缺点：**使用这种方式，由于在垃圾回收过程中，间断性地还执行了应用程序代码，所以能减少系统的停顿时间。但是，因为线程切换和上下文转换的消耗，会使得垃圾回收的总体成本上升，**造成系统吞吐量的下降**。



#### 2.6 分区算法

一般来说，在相同条件下，堆空间越大，一次GC时所需要的时间就越长，有关GC产生的停顿也越长。为了更好地控制GC产生的停顿时间，将一块大的内存区域分割成多个小块，根据目标的停顿时间，每次合理地回收若干个小区间，而不是整个堆空间，从而减少一次GC所产生的停顿。

分代算法将按照对象的生命周期长短划分成两个部分，分区算法将整个堆空间划分成连续的不同小区间region。

每一个小区间都独立使用，独立回收。这种算法的好处是可以控制一次回收多少个小区间。

![](JVM.assets/126.png)





### 3. 垃圾回收相关概念

#### 3.1 System.gc()的理解

- 在默认情况下，通过System.gc()或者Runtime.getRuntime().gc()的调用，会显式触发FullGC，同时对老年代和新生代进行回收，尝试释放被丢弃对象占用的内存。
- 然而System.gc()调用附带一个免责声明，无法保证对垃圾收集器的调用。 
- JVM实现者可以通过System.gc()调用来决定JVM的GC行为。而一般情况下，垃圾回收应该是自动进行的，无须手动触发，否则就太过于麻烦了。在一些特殊情况下，如我们正在编写一个性能基准，我们可以在运行之间调用 System.gc()。

```java
/**
 * @author shkstart  shkstart@126.com
 * @create 2020  14:49
 */
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
        System.gc();//提醒jvm的垃圾回收器执行gc,但是不确定是否马上执行gc
        //与Runtime.getRuntime().gc();的作用一样。

        System.runFinalization();//强制调用使用引用的对象的finalize()方法
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("SystemGCTest 重写了finalize()");
    }
}
```

```java
public class LocalVarGC {
    public void localvarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];//10MB
        System.gc();
    }

    public void localvarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    public void localvarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    public void localvarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    public void localvarGC5() {
        localvarGC1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC local = new LocalVarGC();
        local.localvarGC5();
    }
}

```



#### 3.2 内存溢出

- 内存溢出相对于内存泄漏来说，尽管更容易被理解，但是同样的，内存溢出也是引发程序崩溃的罪魁祸首之一。
- 由于GC一直在发展，所有一般情况下，除非应用程序占用的内存增长速度非常快，造成垃圾回收已经跟不上内存消耗的速度，否则不太容易出现OOM 的情况。
- 大多数情况下，GC会进行各种年龄段的垃圾回收，实在不行了就放大招，来一次独占式的FullGC操作，这时候会回收大量的内存，供应用程序继续使用。
- javadoc中对OutOfMemoryError的解释是，没有空闲内存，并且垃圾收集器也无法提供更多内存。



- 首先说没有空闲内存的情况：说明Java虚拟机的堆内存不够。原因有二：

1. Java虚拟机的堆内存设置不够。
   比如：可能存在内存泄漏问题：也很有可能就是堆的大小不合理，比如我们要处理比较可观的数据量，但是没有显式指定JVM堆大小或者指定数值偏小。我们可以通过参数-Xms、-Xmx来调整。

2. 代码中创建了大量的大对象，并且长时间不能被垃圾收集器收集（存在被引用）

   对于老版本的OracleJDK，因为永久代的大小是有限的，并且JVM对永久代垃圾回收（如，常量池回收、卸载不再需要的类型）非常不积极，所以当我们不断添加新类型的时候，永久代出现OutOfMemoryError也非常多见，尤其是在运行时存在大量动态类型生成的场合；类似intern字符串缓存占用太多空间，也会导致OOM问题。对应的异常信息，会标记出来和永久代相关：“java.lang.OutOfMemoryError：PermGenspace"。

   随着元数据区的引入，方法区内存已经不再那么窘迫，所以相应的OOM有所改观，出现 OOM，异常信息则变成了：“java.lang.OutOfMemoryError：Metaspace"。直接内存不足，也会导致OOM。



- 这里面隐含着一层意思是，在抛出OutOfMemoryError之前，通常垃圾收集器会被触发，尽其所能去清理出空间。
  - 例如：在引用机制分析中，涉及到JVM会去尝试回收软引用指向的对象等
  - 在java.nio.BIts.reserveMemory()方法中，我们能清楚的看到，System.gc()会被调用，以清理空间。
- 当然，也不是在任何情况下垃圾收集器都会被触发的
  - 比如，我们去分配一个超大对象，类似一个超大数组超过堆的最大值，JVM可以判断出垃圾收集并不能解决这个问题，所以直接抛出OutOfMemoryError。



#### 3.3 内存泄漏

- 也称作“存储渗漏”。严格来说，只有对象不会再被程序用到了，但是GC又不能回收他们的情况，才叫内存泄漏。
- 但实际情况很多时候一些不太好的实践（或疏忽）会导致对象的生命周期变得很长甚至导致OOM，也可以叫做宽泛意义上的“内存泄漏”
- 尽管内存泄漏并不会立刻引起程序崩溃，但是一旦发生内存泄漏，程序中的可用内存就会被逐步蚕食，直至耗尽所有内存，最终出现OutOfMemory异常，导致程序崩溃。
- 注意，这里的存储空间并不是指物理内存，而是指虚拟内存大小，这个虚拟内存大小取决于磁盘交换区设定的大小。

![](JVM.assets/127.png)



举例：

1. 单例模式
   单例的生命周期和应用程序是一样长的，所以单例程序中，如果持有对外部对象的引用的话，那么这个外部对象是不能被回收的，则会导致内存泄漏的产生。
2. 一些提供close的资源未关闭导致内存泄漏
   数据库连接（dataSourse.getConnection()），网络连接（socket)和 io连接必须手动close，否则是不能被回收的。



#### 3.4 StopTheWorld

- Stop-the-World，简称sTW，指的是GC事件发生过程中，会产生应用程序的停顿。停顿产生时整个应用程序线程都会被暂停，没有任何响应，有点像卡死的感觉，这个停顿称为STW。
  - 可达性分析算法中枚举根节点（GCRoots）会导致所有Java执行线程停顿。
    - 分析工作必须在一个能确保一致性的快照中进行
    - 一致性指整个分析期间整个执行系统看起来像被冻结在某个时间点上
    - 如果出现分析过程中对象引用关系还在不断变化，则分析结果的准确性无法保证
- 被STW中断的应用程序线程会在完成GC之后恢复，频繁中断会让用户感觉像是网速不快造成电影卡带一样，所以我们需要减少STW的发生。



- STW事件和采用哪款GC无关，所有的GC都有这个事件。
- 哪怕是G1也不能完全避免Stop-the-world情况发生，只能说垃圾回收器越来越优秀，回收效率越来越高，尽可能地缩短了暂停时间。
- STW是JVM在后台自动发起和自动完成的。在用户不可见的情况下，把用户正常的工作线程全部停掉。
- 开发中不要用system.gc()；会导致stop-the-world的发生。

```java
public class StopTheWorldDemo {
    public static class WorkThread extends Thread {
        List<byte[]> list = new ArrayList<byte[]>();

        public void run() {
            try {
                while (true) {
                    for(int i = 0;i < 1000;i++){
                        byte[] buffer = new byte[1024];
                        list.add(buffer);
                    }

                    if(list.size() > 10000){
                        list.clear();
                        System.gc();//会触发full gc，进而会出现STW事件
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class PrintThread extends Thread {
        public final long startTime = System.currentTimeMillis();

        public void run() {
            try {
                while (true) {
                    // 每秒打印时间信息
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WorkThread w = new WorkThread();
        PrintThread p = new PrintThread();
        w.start();
        p.start();
    }
}
```



#### 3.5 垃圾回收的并行与并发

并发和并行，在谈论垃圾收集器的上下文语境中，它们可以解释如下：

- 并行(Parallel)：指多条垃圾收集线程并行工作，但此时用户线程仍处于等待状态。如ParNew、Parallel Scavenge、Parallel Old; 
- 串行(Serial)
  - 相较于并行的概念，单线程执行。
  - 如果内存不够，则程序暂停，启动JVM垃圾回收器进行垃圾回收。回收完，再启动程序的线程。

![](JVM.assets/128.png)



- 并发(Concurrent)：指用户线程与垃圾收集线程同时执行(但不一定是并行的，可能会交替执行)，垃圾回收线程在执行时不会停顿用户程序的运行。
  - 用户程序在继续运行，而垃圾收集程序线程运行于另一个CPU上；
  - 如：CMS、G1

![](JVM.assets/129.png)



#### 3.6 安全点

程序执行时并非在所有地方都能停顿下来开始GC，只有在特定的位置才能停顿下来开始GC，这些位置称为“安全点（Safepoint）”

SafePoint的选择很重要，如果太少可能导致GC等待的时间太长，如果太频繁可能导致运行时的性能问题。大部分指令的执行时间都非常短暂，通常会根据“是否具有让程序长时间执行的特征”为标准。比如：选择一些执行时间较长的指令作SafePoint，如方法调用、循环跳转和异常跳转等。



如何在GC发生时，检查所有线程都跑到最近的安全点停顿下来呢？

- **抢先式中断**：（目前没有虚拟机采用了）
  首先中断所有线程。如果还有线程不在安全点，就恢复线程，让线程跑到安全点。
- **主动式中断**：
  设置一个中断标志，各个线程运行到SafePoint的时候主动轮询这个标志，如果中断标志为真，则将自己进行中断挂起。





#### 3.7 安全区域

Safepoint机制保证了程序执行时，在不太长的时间内就会遇到可进入GC 的Safepoint。但是，程序“不执行”的时候呢？例如线程处于Sleep状态或Blocked状态，这时候线程无法响应JVM的中断请求，“走”到安全点去中断挂起，JVM也不太可能等待线程被唤醒。对于这种情况，就需要安全区域（SafeRegion）来解决。

安全区域是指在一段代码片段中，对象的引用关系不会发生变化，在这个区域中的任何位置开始GC都是安全的。我们也可以把SafeRegion看做是被扩展了的Safepoint。



实际执行时：

1. 当线程运行到SafeRegion的代码时，首先标识已经进入了SafeRegion，如果这段时间内发生GC，JVM会忽略标识为SafeRegion状态的线程；
2. 当线程即将离开SafeRegion时，会检查JVM是否已经完成GC，如果完成了，则继续运行，否则线程必须等待直到收到可以安全离开safeRegion的信号为止；





#### 3.8 引用

我们希望能描述这样一类对象：当内存空间还足够时，则能保留在内存中；如果内存空间在进行垃圾收集后还是很紧张，则可以抛弃这些对象。

【既偏门又非常高频的面试题】强引用、软引用、弱引用、虚引用有什么区别？具体使用场景是什么？

在JDK1.2版之后，Java对引用的概念进行了扩充，将引用分为强引用（Strong Reference）、软引用（Soft Reference）、弱引用（Weak Reference）和虚引用（Phantom Reference）4种，这4种引用强度依次逐渐减弱。

除强引用外，其他3种引用均可以在java.lang.ref包中找到它们的身影。如下图，显示了这3种引用类型对应的类，开发人员可以在应用程序中直接使用它们。

![](JVM.assets/130.png)



Reference子类中只有终结器引用是包内可见的，其他3种引用类型均为public，可以在应用程序中直接使用

- 强引用（StrongReference）：最传统的“引用”的定义，是指在程序代码之中普遍存在的引用赋值，即类似“Object obj = new Object()"这种引用关系。无论任何情况下，只要强引用关系还存在，垃圾收集器就永远不会回收掉被引用的对象。
- 软引用（SoftReference）：在系统将要发生内存溢出之前，将会把这些对象列入回收范围之中进行第二次回收。如果这次回收后还没有足够的内存，才会抛出内存溢出异常。
- 弱引用（WeakReference）：被弱引用关联的对象只能生存到下一次垃圾收集之前。当垃圾收集器工作时，无论内存空间是否足够，都会回收掉被弱引用关联的对象。
- 虚引用（PhantomReference）：一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来获得一个对象的实例。为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个系统通知。



##### 3.8.1 强引用

> 强引用（Strong Reference）- 不回收

在Java程序中，最常见的引用类型是强引用（普通系统99%以上都是强引用），也就是我们最常见的普通对象引用，也是默认的引用类型。

当在Java语言中使用new操作符创建一个新的对象，并将其赋值给一个变量的时候，这个变量就成为指向该对象的一个强引用。

强引用的对象是可触及的，垃圾收集器就永远不会回收掉被引用的对象。

对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显式地将相应（强）引用赋值为null，就是可以当做垃圾被收集了，当然具体回收时机还是要看垃圾收集策略。

相对的，软引用、弱引用和虚引用的对象是软可触及、弱可触及和虚可触及的，在一定条件下，都是可以被回收的。所以，强引用是造成Java内存泄漏的主要原因之一。



强引用例子：

```java
StringBuffer str = new StringBuffer("Hello, World");
```

局部变量str指向StringBuffer实例所在堆空间，通过str可以操作该实例，那么str就是StringBuffer实例的强引用



特点：

- 强引用可以直接访问目标对象。
- 强引用所指向的对象在任何时候都不会被系统回收，虚拟机宁愿抛出OOM异常，也不会回收强引用所指向对象。
- 强引用可能导致内存泄漏。



##### 3.8.2 软引用

> 软引用（Soft Reference）- 内存不足即回收

软引用是用来描述一些还有用，但非必需的对象。只被软引用关联着的对象，在系统将要发生内存溢出异常前，会把这些对象列进回收范围之中进行第二次回收，如果这次回收还没有足够的内存，才会抛出内存溢出异常。

软引用通常用来实现内存敏感的缓存。比如：高速缓存就有用到软引用。如果还有空闲内存，就可以暂时保留缓存，当内存不足时清理掉，这样就保证了使用缓存的同时，不会耗尽内存。

垃圾回收器在某个时刻决定回收软可达的对象的时候，会清理软引用，并可选地把引用存放到一个引用队列（ReferenceQueue）。

类似弱引用，只不过Java虚拟机会尽量让软引用的存活时间长一些，迫不得已才清理。



在JDK1.2版之后提供了java.lang.ref.SoftReference类来实现软引用。

```java
Object obj = new Object();//声明强引用

SoftReference<Object> sf = new SoftReference<Object>(obj);

obj = null;//销毁强引用
```

```java
import java.lang.ref.SoftReference;

/**
 * 软引用的测试：内存不足即回收
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  16:06
 */
public class SoftReferenceTest {
    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;

        @Override
        public String toString() {
            return "[id=" + id + ", name=" + name + "] ";
        }
    }

    public static void main(String[] args) {
        //创建对象，建立软引用
//        SoftReference<User> userSoftRef = new SoftReference<User>(new User(1, "songhk"));
        //上面的一行代码，等价于如下的三行代码
        User u1 = new User(1,"songhk");
        SoftReference<User> userSoftRef = new SoftReference<User>(u1);
        u1 = null;//取消强引用


        //从软引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.gc();
        System.out.println("After GC:");
//        //垃圾回收之后获得软引用中的对象
        System.out.println(userSoftRef.get());//由于堆空间内存足够，所有不会回收软引用的可达对象。
//
        try {
            //让系统认为内存资源紧张、不够
//            byte[] b = new byte[1024 * 1024 * 7];
            byte[] b = new byte[1024 * 7168 - 635 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //再次从软引用中获取数据
            System.out.println(userSoftRef.get());//在报OOM之前，垃圾回收器会回收软引用的可达对象。
        }
    }
}
```



##### 3.8.3 弱引用

> 弱引用（Weak Reference）- 发现即回收

弱引用也是用来描述那些非必需对象，被弱引用关联的对象只能生存到下一次垃圾收集发生为止。在系统GC时，只要发现弱引用，不管系统堆空间使用是否充足，都会回收掉只被弱引用关联的对象。

但是，由于垃圾回收器的线程通常优先级很低，因此，并不一定能很快地发现持有弱引用的对象。在这种情况下，弱引用对象可以存在较长的时间。

弱引用和软引用一样，在构造弱引用时，也可以指定一个引用队列，当弱引用对象被回收时，就会加入指定的引用队列，通过这个队列可以跟踪对象的回收情况。

软引用、弱引用都非常适合来保存那些可有可无的缓存数据。如果这么做，当系统内存不足时，这些缓存数据会被回收，不会导致内存溢出。而当内存资源充足时，这些缓存数据又可以存在相当长的时间，从而起到加速系统的作用。



在JDK1.2版之后提供了java.lang.ref.WeakReference类来实现弱引用。

```java
Object obj = new Object();//声明强引用

WeakReference<Object> wr = new WeakReference<Object>(obj);

obj = null;//销毁强引用
```

弱引用对象与软引用对象的最大不同就在于，当GC在进行回收时，需要通过算法检查是否回收软引用对象，而对于弱引用对象，GC总是进行回收。弱引用对象更容易、更快被GC回收。

```java
import java.lang.ref.WeakReference;

/**
 * 弱引用的测试
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  16:06
 */
public class WeakReferenceTest {
    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;

        @Override
        public String toString() {
            return "[id=" + id + ", name=" + name + "] ";
        }
    }

    public static void main(String[] args) {
        //构造了弱引用
        WeakReference<User> userWeakRef = new WeakReference<User>(new User(1, "songhk"));
        //从弱引用中重新获取对象
        System.out.println(userWeakRef.get());

        System.gc();
        // 不管当前内存空间足够与否，都会回收它的内存
        System.out.println("After GC:");
        //重新尝试从弱引用中获取对象
        System.out.println(userWeakRef.get());
    }
}

```



##### 3.8.4 虚引用

> 虚引用（Phantom Reference）- 对象回收跟踪
>

也称为“幽灵引用”或者“幻影引用”，是所有引用类型中最弱的一个。

一个对象是否有虚引用的存在，完全不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它和没有引用几乎是一样的，随时都可能被垃圾回收器回收。

它不能单独使用，也无法通过虚引用来获取被引用的对象。当试图通过虚引用的get()方法取得对象时，总是null。

为一个对象设置虚引用关联的唯一目的在于跟踪垃圾回收过程。比如：能在这个对象被收集器回收时收到一个系统通知。



虚引用必须和引用队列一起使用。虚引用在创建时必须提供一个引用队列作为参数。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象后，将这个虚引用加入引用队列，以通知应用程序对象的回收情况。

由于虚引用可以跟踪对象的回收时间，因此，也可以将一些资源释放操作放置在虚引用中执行和记录。

在JDK1.2版之后提供了PhantomReference类来实现虚引用。

```java
Object obj = new Object();
ReferenceQueue phantomQueue = new ReferenceQueue();
PhantomReference<Object> pf = new PhantomReference<Object>(obj, phantomQueue);
obj = null;
```



```java
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用的测试
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  16:07
 */
public class PhantomReferenceTest {
    public static PhantomReferenceTest obj;//当前类对象的声明
    static ReferenceQueue<PhantomReferenceTest> phantomQueue = null;//引用队列

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    PhantomReference<PhantomReferenceTest> objt = null;
                    try {
                        objt = (PhantomReference<PhantomReferenceTest>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (objt != null) {
                        System.out.println("追踪垃圾回收过程：PhantomReferenceTest实例被GC了");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable { //finalize()方法只能被调用一次！
        super.finalize();
        System.out.println("调用当前类的finalize()方法");
        obj = this;
    }

    public static void main(String[] args) {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);//设置为守护线程：当程序中没有非守护线程时，守护线程也就执行结束。
        t.start();

        phantomQueue = new ReferenceQueue<PhantomReferenceTest>();
        obj = new PhantomReferenceTest();
        //构造了 PhantomReferenceTest 对象的虚引用，并指定了引用队列
        PhantomReference<PhantomReferenceTest> phantomRef = new PhantomReference<PhantomReferenceTest>(obj, phantomQueue);

        try {
            //不可获取虚引用中的对象
            System.out.println(phantomRef.get());

            //将强引用去除
            obj = null;
            //第一次进行GC,由于对象可复活，GC无法回收该对象
            System.gc();
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null");
            } else {
                System.out.println("obj 可用");
            }
            System.out.println("第 2 次 gc");
            obj = null;
            System.gc(); //一旦将obj对象回收，就会将此虚引用存放到引用队列中。
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null");
            } else {
                System.out.println("obj 可用");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```



##### 3.8.5 终结器引用

- 它用以实现对象的finalize()方法，也可以称为终结器引用。
- 无需手动编码，其内部配合引用队列使用。
- 在GC时，终结器引用入队。由Finalizer线程通过终结器引用找到被引用对象并调用它的finalize()方法，第二次GC时才能回收被引用对象。





### 4. 垃圾回收器

- 垃圾收集器没有在规范中进行过多的规定，可以由不同的厂商、不同版本的JVM来实现。
- 由于JDK的版本处于高速迭代过程中，因此Java发展至今已经衍生了众多的GC版本。
- 从不同角度分析垃圾收集器，可以将GC分为不同的类型。



Java不同版本的新特性：

1. 语法层面：Lambda表达式、switch、自动装箱、自动拆箱、enum、<>、。。。
2. API层面：StreamAPI、新的日期时间、Optional、String、集合框架
3. 底层优化：JVM的优化，GC的变化、元空间、静态域、字符串常量池等



#### 4.1 GC分类

1. 按线程数分，可以分为串行垃圾回收器和并行垃圾回收器。
   ![](JVM.assets/131.png)



- 串行回收指的是在同一时间段内只允许有一个CPU用于执行垃圾回收操作，此时工作线程被暂停，直至垃圾收集工作结束。
  - 在诸如单CPU处理器或者较小的应用内存等硬件平台不是特别优越的场合，串行回收器的性能表现可以超过并行回收器和并发回收器。所以，串行回收默认被应用在客户端的client模式下的JVM中
  - 在并发能力比较强的CPU上，并行回收器产生的停顿时间要短于串行回收器。
- 和串行回收相反，并行收集可以运用多个CPU同时执行垃圾回收，因此提升了应用的吞吐量，不过并行回收仍然与串行回收一样，采用独占式，使用了“stop-the-world”机制。



2. 按照工作模式分，可以分为并发式垃圾回收器和独占式垃圾回收器。

- 并发式垃圾回收器与应用程序线程交替工作，以尽可能减少应用程序的停顿时间。
- 独占式垃圾回收器(Stop the world)一旦运行，就停止应用程序中的所有用户线程，直到垃圾回收过程完全结束。

![](JVM.assets/132.png)



3. 按碎片处理方式分，可分为压缩式垃圾回收器和非压缩式垃圾回收器。

- 压缩式垃圾回收器会在回收完成后，对存活对象进行压缩整理，消除回收后的碎片。再分配对象空间使用：指针碰撞
- 非压缩式的垃圾回收器不进行这步操作。再分配对象空间使用：空闲列表



4. 按工作的内存区间分，又可分为年轻代垃圾回收器和老年代垃圾回收器。





#### 4.2 性能指标

- **吞吐量：运行用户代码的时间占总运行时间的比例**（总运行时间：程序的运行时间十内存回收的时间）
- 垃圾收集开销：吞吐量的补数，垃圾收集所用时间与总运行时间的比例。
- **暂停时间：执行垃圾收集时，程序的工作线程被暂停的时间。**
- 收集频率：相对于应用程序的执行，收集操作发生的频率。
- **内存占用：Java堆区所占的内存大小。**
- 快速：一个对象从诞生到被回收所经历的时间。



吞吐量、暂停时间、内存占用

- 这三者共同构成一个“不可能三角”。三者总体的表现会随着技术进步而越来越好。一款优秀的收集器通常最多同时满足其中的两项。
- 这三项里，暂停时间的重要性日益凸显。因为随着硬件发展，内存占用多些越来越能容忍，硬件性能的提升也有助于降低收集器运行时对应用程序的影响，即提高了吞吐量。而内存的扩大，对延迟反而带来负面效果。
- 简单来说，主要抓住两点：
  - 吞吐量
  - 暂停时间



##### 4.2.1 吞吐量

- 吞吐量就是CPU用于运行用户代码的时间与CPU总消耗时间的比值，即吞吐量 = 运行用户代码时间 ／ (运行用户代码时间+垃圾收集时间)。
  - 比如：虚拟机总共运行了100分钟，其中垃圾收集花掉1分钟，那吞吐量就是99%。
- 这种情况下，应用程序能容忍较高的暂停时间，因此，高吞吐量的应用程序有更长的时间基准，快速响应是不必考虑的。
- 吞吐量优先，意味着在单位时间内，STW的时间最短：0.2+0.2=0.4

![](JVM.assets/133.png)



##### 4.2.2 暂停时间

- “暂停时间”是指一个时间段内应用程序线程暂停，让GC线程执行的状态
  - 例如，GC期间100毫秒的暂停时间意味着在这100毫秒期间内没有应用程序线程是活动的。
- 暂停时间优先，意味着尽可能让单次STW的时间最短：0.1+0.1+0.1+ 0.1 + 0.1 = 0.5

![](JVM.assets/133.png)





##### 4.2.3 吞吐量vs暂停时间

- 高吞吐量较好因为这会让应用程序的最终用户感觉只有应用程序线程在做“生产性”工作。直觉上，吞吐量越高程序运行越快。
- 低暂停时间（低延迟）较好因为从最终用户的角度来看不管是GC还是其他原因导致一个应用被挂起始终是不好的。这取决于应用程序的类型，有时候甚至短暂的200毫秒暂停都可能打断终端用户体验。因此，具有低的较大暂停时间是非常重要的，特别是对于一个交互式应用程序。
- 不幸的是”高吞吐量”和”低暂停时间”是一对相互竞争的目标（矛盾）。
  - 因为如果选择以吞吐量优先，那么必然需要降低内存回收的执行频率，但是这样会导致GC需要更长的暂停时间来执行内存回收。
  - 相反的，如果选择以低延迟优先为原则，那么为了降低每次执行内存回收时的暂停时间，也只能频繁地执行内存回收，但这又引起了年轻代内存的缩减和导致程序吞吐量的下降。



在设计（或使用）GC算法时，我们必须确定我们的目标：一个GC算法只可能针对两个目标之一（即只专注于较大吞吐量或最小暂停时间），或尝试找到一个二者的折衷。

现在标准：在最大吞吐量优先的情况下，降低停顿时间。



#### 4.3 发展迭代史

有了虚拟机，就一定需要收集垃圾的机制，这就是GarbageCollection，对应的产品我们称为GarbageCollector。

- 1999年随JDK1.3.1一起来的是串行方式的SerialGC，它是第一款GC。ParNew垃圾收集器是Serial收集器的多线程版本
- 2002年2月26日，ParallelGC 和ConcurrentMark Sweep GC跟随JDK1.4.2一起发布
- ParallelGC在JDK6之后成为HotSpot默认GC。
- 2012年，在JDK1.7u4版本中，G1可用。
- 2017年，JDK9中G1变成默认的垃圾收集器，以替代CMS。
- 2018年3月，JDK10中G1垃圾回收器的并行完整垃圾回收，实现并行性来改善最坏情况下的延迟。
- 2018年9月，JDK11发布。引入Epsilon垃圾回收器，又被称为"No-Op（无操作）" 回收器。同时，引入zGC：可伸缩的低延迟垃圾回收器（Experimental）。
- 2019年3月，JDK12发布。增强G1，自动返回未用堆内存给操作系统。同时，引入 ShenandoahGC：低停顿时间的GC（Experimental）。
- 2019年9月，JDK13发布。增强ZGC，自动返回未用堆内存给操作系统。
- 2020年3月，JDK14发布。删除CMS垃圾回收器。扩展zGC在macOS和Windows上的应用



七款经典的垃圾收集器

- 串行回收器：Serial、SerialOld
- 并行回收器：ParNew、ParallelScavenge、ParallelOld
- 并发回收器：CMS、G1



#### 4.4 7款经典收集器与垃圾分代之间的关系

![](JVM.assets/134.png)

- 新生代收集器：Serial、ParNew、Parallel Scavenge;
- 老年代收集器：Serial Old、Parallel Old、CMS;
- 整堆收集器：G1;



垃圾收集器的组合关系

![](JVM.assets/135.png)

1. 两个收集器间有连线，表明它们可以搭配使用：
   Serial / Serial Old、Serial / CMS、ParNew / Serial Old、ParNew / CMS、Parallel Scavenge / Serial Old、Parallel Scavenge/Parallel Old、G1;
2. 其中Serial Old作为CMS出现"Concurrent Mode Failure"失败的后备预案。
3. (红色虚线)由于维护和兼容性测试的成本，在JDK8时将Serial + CMS、ParNew + Serial Old这两个组合声明为废弃(JEP173)，并在JDK9中完全取消了这些组合的支持(JEP214)，即：移除。
4. (绿色虚线)JDK14中：弃用Parallel Scavenge和Serialold GC组合(JEP366)
5. (青色虚线)JDK14中：删除CMS垃圾回收器(JEP 363)
   

- 为什么要有很多收集器，一个不够吗？因为Java的使用场景很多，移动端，服务器等。所以就需要针对不同的场景，提供不同的垃圾收集器，提高垃圾收集的性能。
- 虽然我们会对各个收集器进行比较，但并非为了挑选一个最好的收集器出来。没有一种放之四海皆准、任何场景下都适用的完美收集器存在，更加没有万能的收集器。所以我们选择的只是对具体应用最合适的收集器。



#### 4.5 如何查看默认的垃圾回收器

- -XX:+PrintCommandLineFlags：查看命令行相关参数（包含使用的垃圾收集器）
- 使用命令行指令：jinfo -flag 相关垃圾回收器参数 进程ID



#### 4.6 Serial回收器：串行回收

- Serial收集器是最基本、历史最悠久的垃圾收集器了。JDK1.3之前回收新生代唯一的选择。
- Serial收集器作为HotSpot中Client模式下的默认新生代垃圾收集器。
- Serial收集器采用复制算法、串行回收和"Stop-the-World"机制的方式执行内存回收。
- 除了年轻代之外，Serial收集器还提供用于执行老年代垃圾收集的 Serial Old收集器。Serial Old收集器同样也采用了串行回收和"Stop the World"机制，只不过内存回收算法使用的是标记-压缩算法。
  - Serial Old是运行在Client模式下默认的老年代的垃圾回收器
  - Serial Old在Server模式下主要有两个用途：①与新生代的Parallel Scavenge配合使用②作为老年代CMS收集器的后备垃圾收集方案

![](JVM.assets/136.png)

这个收集器是一个单线程的收集器，但它的“单线程”的意义并不仅仅说明它只会使用一个CPU或一条收集线程去完成垃圾收集工作，更重要的是在它进行垃圾收集时，必须暂停其他所有的工作线程，直到它收集结束（StopTheWorld）。



- 优势：简单而高效（与其他收集器的单线程比），对于限定单个CPU的环境来说，Serial收集器由于没有线程交互的开销，专心做垃圾收集自然可以获得最高的单线程收集效率。
  - 运行在Client模式下的虚拟机是个不错的选择。
- 在用户的桌面应用场景中，可用内存一般不大(几十MB至一两百MB)：可以在较短时间内完成垃圾收集(几十ms至一百多ms)，只要不频繁发生，使用串行回收器是可以接受的。
- 在HotSpot虚拟机中，使用-XX:+UseSerialGC参数可以指定年轻代和老年代都使用串行收集器。
  - 等价于新生代用Serial GC，且老年代用Serial Old GC



总结：这种垃圾收集器大家了解，现在已经不用串行的了。而且在限定单核cpu才可以用。现在都不是单核的了。

对于交互较强的应用而言，这种垃圾收集器是不能接受的。一般在Java web应用程序中是不会采用串行垃圾收集器的。



#### 4.7 ParNew回收器：并行回收

- 如果说Serial GC是年轻代中的单线程垃圾收集器，那么ParNew收集器则是serial收集器的多线程版本。
  - Par是Parallel的缩写，New：只能处理的是新生代
- ParNew收集器除了采用并行回收的方式执行内存回收外，两款垃圾收集器之间几乎没有任何区别。ParNew收集器在年轻代中同样也是采用复制算法、"Stop-the-World"机制。
- ParNew是很多JVM运行在Server模式下新生代的默认垃圾收集器。

![](JVM.assets/137.png)

- 对于新生代，回收次数频繁，使用并行方式高效。
- 对于老年代，回收次数少，使用串行方式节省资源。（CPU并行需要切换线程，串行可以省去切换线程的资源）



- 由于ParNew收集器是基于并行回收，那么是否可以断定ParNew收集器的回收效率在任何场景下都会比Serial收集器更高效？
  - ParNew收集器运行在多CPU的环境下，由于可以充分利用多CPU、多核心等物理硬件资源优势，可以更快速地完成垃圾收集，提升程序的吞吐量。
  - 但是在单个CPU的环境下，ParNew收集器不比Serial收集器更高效。虽然Serial收集器是基于串行回收，但是由于CPU不需要频繁地做任务切换，因此可以有效避免多线程交互过程中产生的一些额外开销。
- 因为除Serial外，目前只有ParNew GC能与CMS收集器配合工作



设置ParNew垃圾回收器

- 在程序中，开发人员可以通过选项"-XX:+UseParNewGC"手动指定使用 ParNew收集器执行内存回收任务。它表示年轻代使用并行收集器，不影响老年代。
- -XX:ParallelGCThreads限制线程数量，默认开启和CPU数据相同的线程数。



#### 4.8 Parallel回收器：吞吐量优先

- HotSpot的年轻代中除了拥有ParNew收集器是基于并行回收的以外，Parallel Scavenge收集器同样也采用了复制算法、并行回收和"Stop the World"机制。
- 那么Parallel收集器的出现是否多此一举？
  - 和ParNew收集器不同，Parallel Scavenge收集器的目标则是达到一个可控制的吞吐量（Throughput），它也被称为吞吐量优先的垃圾收集器。
  - 自适应调节策略也是ParallelScavenge与ParNew一个重要区别。



- 高吞吐量则可以高效率地利用CPU时间，尽快完成程序的运算任务，主要适合在后台运算而不需要太多交互的任务。因此，常见在服务器环境中使用。例如，那些执行批量处理、订单处理、工资支付、科学计算的应用程序。
- Parallel收集器在JDK1.6时提供了用于执行老年代垃圾收集的Parallel Old收集器，用来代替老年代的Serial Old收集器。
- ParallelOld收集器采用了标记-压缩算法，但同样也是基于并行回收和"Stop-the-World"机制。

![](JVM.assets/138.png)

- 在程序吞吐量优先的应用场景中，Parallel收集器和Parallel Old收集器的组合，在server模式下的内存回收性能很不错。
- 在Java8中，默认是此垃圾收集器。



##### 4.8.1 参数设置

- **-XX:+UseParallelGC**：手动指定年轻代使用Parallel并行收集器执行内存回收任务。
- **-XX:+UseParallelOldGC**：手动指定老年代都是使用并行回收收集器。
  - 分别适用于新生代和老年代。默认jdk8是开启的。
  - 上面两个参数，默认开启一个，另一个也会被开启。（互相激活）
- **-XX:ParallelGCThreads**：设置年轻代并行收集器的线程数。一般地，最好与CPU数量相等，以避免过多的线程数影响垃圾收集性能。
  - 在默认情况下，当CPU数量小于8个，ParallelGCThreads的值等于CPU数量。
  - 当CPU数量大于8个，ParallelGCThreads的值等于3+[5*CPUCount]/8]。



- **-XX:MaxGCPauseMillis**：设置垃圾收集器最大停顿时间（即STW的时间）。单位是毫秒。
  - 为了尽可能地把停顿时间控制在MaxGCPauseMills以内，收集器在工作时会调整Java堆大小或者其他一些参数。
  - 对于用户来讲，停顿时间越短体验越好。但是在服务器端，我们注重高并发，整体的吞吐量。所以服务器端适合Parallel，进行控制。
  - 该参数使用需谨慎
- **-XX:GCTimeRatio**：垃圾收集时间占总时间的比例（ = 1／(N+1) ）。用于衡量吞吐量的大小。
  - 取值范围（0，100）。默认值99，也就是垃圾回收时间不超过1%。
  - 与前一个-XX:MaxGCPauseMillis参数有一定矛盾性。暂停时间越长，Radio参数就容易超过设定的比例。



- **-XX:+UseAdaptiveSizePolicy**：设置Parallel Scavenge收集器具有自适应调节策略
  - 在这种模式下，年轻代的大小、Eden和Survivor的比例、晋升老年代的对象年龄等参数会被自动调整，已达到在堆大小、吞吐量和停顿时间之间的平衡点。
  - 在手动调优比较困难的场合，可以直接使用这种自适应的方式，仅指定虚拟机的最大堆、目标的吞吐量（GCTimeRatio）和停顿时间（MaxGCPauseMills），让虚拟机自已完成调优工作。



#### 4.9 CMS回收器：低延迟

- 在JDK1.5时期，HotSpot推出了一款在强交互应用中几乎可认为有划时代意义的垃圾收集器：CMS(Concurrent-Mark-Sweep)收集器，这款收集器是HotSpot虚拟机中第一款真正意义上的并发收集器，它第一次实现了让垃圾收集线程与用户线程同时工作。
- CMS收集器的关注点是尽可能缩短垃圾收集时用户线程的停顿时间。停顿时间越短（低延迟）就越适合与用户交互的程序，良好的响应速度能提升用户体验。
  - 目前很大一部分的Java应用集中在互联网站或者B/S系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间最短，以给用户带来较好的体验。 CMS收集器就非常符合这类应用的需求。
- CMS的垃圾收集算法采用标记-清除算法，并且也会"Stop-the-world"



不幸的是，CMS作为老年代的收集器，却无法与JDK1.4.0中已经存在的新生代收集器ParallelScaVenge配合工作，所以在JDK1.5中使用CMS来收集老年代的时候，新生代只能选择ParNew或者Serial收集器中的一个。

在G1出现之前，CMS使用还是非常广泛的。一直到今天，仍然有很多系统使用CMS GC。

![](JVM.assets/139.png)

CMS整个过程比之前的收集器要复杂，整个过程分为4个主要阶段，即初始标记阶段、并发标记阶段、重新标记阶段和并发清除阶段。

- 初始标记（Initial-Mark）阶段：在这个阶段中，程序中所有的工作线程都将会因为“stop-the-World”机制而出现短暂的暂停，这个阶段的主要任务仅仅只是标记出GCRoots能直接关联到的对象。一旦标记完成之后就会恢复之前被暂停的所有应用线程。由于直接关联对象比较小，所以这里的速度非常快。
- 并发标记（Concurrent-Mark）阶段：从GCRoots的直接关联对象开始遍历整个对象图的过程，这个过程耗时较长但是不需要停顿用户线程，可以与垃圾收集线程一起并发运行。

- 重新标记（Remark）阶段：由于在并发标记阶段中，程序的工作线程会和垃圾收集线程同时运行或者交叉运行，因此为了修正并发标记期间，因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间通常会比初始标记阶段稍长一些，但也远比并发标记阶段的时间短。
- 并发清除（concurrent-Sweep）阶段：此阶段清理删除掉标记阶段判断的已经死亡的对象，释放内存空间。由于不需要移动存活对象，所以这个阶段也是可以与用户线程同时并发的



- 尽管CMS收集器采用的是并发回收（非独占式），但是在其初始化标记和再次标记这两个阶段中仍然需要执行“stop-the-World”机制暂停程序中的工作线程，不过暂停时间并不会太长，因此可以说明目前所有的垃圾收集器都做不到完全不需要“stop-the-World”，只是尽可能地缩短暂停时间。
- 由于最耗费时间的并发标记与并发清除阶段都不需要暂停工作，所以整体的回收是低停顿的。
- 另外，由于在垃圾收集阶段用户线程没有中断，所以在CMS回收过程中，还应该确保应用程序用户线程有足够的内存可用。因此，CMS收集器不能像其他收集器那样等到老年代几乎完全被填满了再进行收集，而是当堆内存使用率达到某一國值时，便开始进行回收，以确保应用程序在CMS工作过程中依然有足够的空间支持应用程序运行。要是CMS运行期间预留的内存无法满足程序需要，就会出现一次“Concurrent Mode Failure” 失败，这时虚拟机将启动后备预案：临时启用Serial Old收集器来重新进行老年代的垃圾收集，这样停顿时间就很长了。



CMS收集器的垃圾收集算法采用的是标记一清除算法，这意味着每次执行完内存回收后，由于被执行内存回收的无用对象所占用的内存空间极有可能是不连续的一些内存块，不可避免地将会产生一些内存碎片。那么CMS在为新对象分配内存空间时，将无法使用指针碰撞（BumpthePointer）技术，而只能够选择空闲列表（FreeList）执行内存分配。

![](JVM.assets/140.png)



- 有人会觉得既然MarkSweep会造成内存碎片，那么为什么不把算法换成MarkCompact呢?

  答案其实很简答，因为当并发清除的时候，用compact整理内存的话，原来的用户线程使用的内存还怎么用呢？要保证用户线程能继续执行，前提的它运行的资源不受影响嘛。MarkCompact更适合“stop the World” 这种场景下使用



CMS的优点：

- 并发收集
- 低延迟

CMS的弊端：

1. **会产生内存碎片**，导致并发清除后，用户线程可用的空间不足。在无法分配大对象的情况下，不得不提前触发FullGC。
2. **CMS收集器对CPU资源非常敏感**。在并发阶段，它虽然不会导致用户停顿，但是会因为占用了一部分线程而导致应用程序变慢，总吞吐量会降低。
3. **CMS收集器无法处理浮动垃圾**。可能出现“concurrent Mode Failure"失败而导致另一次FullGC的产生。在并发标记阶段由于程序的工作线程和垃圾收集线程是同时运行或者交叉运行的，那么在并发标记阶段如果产生新的垃圾对象，CMS将无法对这些垃圾对象进行标记，最终会导致这些新产生的垃圾对象没有被及时回收，从而只能在下一次执行GC时释放这些之前未被回收的内存空间。



##### 4.9.1 参数设置

- **-XX:+UseConcMarkSweepGC**：手动指定使用CMS收集器执行内存回收任务。
  - 开启该参数后会自动将-XX:+UseParNewGC打开。即：ParNew（Young区用）+CMS（Old区用）+Serial Old的组合。
- **-XX:CMslnitiatingOccupanyFraction**：设置堆内存使用率的阈值，一旦达到该阈值，便开始进行回收。
  - JDK5及以前版本的默认值为68，即当老年代的空间使用率达到68%时，会执行一次CMS回收。JDK6及以上版本默认值为92%
  - 如果内存增长缓慢，则可以设置一个稍大的值，大的阈值可以有效降低CMS的触发频率，减少老年代回收的次数可以较为明显地改善应用程序性能。反之，如果应用程序内存使用率增长很快，则应该降低这个阈值，以避免频繁触发老年代串行收集器。因此通过该选项便可以有效降低FullGC的执行次数。



- **-XX:+UseCMSCompactAtFullCollection**：用于指定在执行完FullGC后对内存空间进行压缩整理，以此避免内存碎片的产生。不过由于内存压缩整理过程无法并发执行，所带来的问题就是停顿时间变得更长了。
- **-XX:CMSFullGCsBeforeCompaction**：设置在执行多少次FullGC后对内存空间进行压缩整理。
- **-XX:ParallelcMSThreads**：设置CMS的线程数量。
  - CMS默认启动的线程数是（ParallelGCThreads+3）/ 4，ParallelGCThreads是年轻代并行收集器的线程数。当CPU资源比较紧张时，受到CMS收集器线程的影响，应用程序的性能在垃圾回收阶段可能会非常糟糕。



小结：HotSpot有这么多的垃圾回收器，那么如果有人问，SerialGC、ParallelGC、Concurrent Mark Sweep GC这三个GC有什么不同呢? 

请记住以下口令：

- 如果你想要最小化地使用内存和并行开销，请选SerialGC;
- 如果你想要最大化应用程序的吞吐量，请选ParallelGC;
- 如果你想要最小化GC的中断或停顿时间，请选CMSGC。





#### 4.10 G1回收器：区域化分代式

**既然我们已经有了前面几个强大的GC，为什么还要发布Garbage First（G1） GC?**

原因就在于应用程序所应对的**业务越来越庞大、复杂，用户越来越多**，没有GC就不能保证应用程序正常进行，而经常造成STW的GC又跟不上实际的需求，所以才会不断地尝试对GC进行优化。G1（Garbage-First）垃圾回收器是在 Java7 update 4之后引入的一个新的垃圾回收器，是当今收集器技术发展的最前沿成果之一。

与此同时，为了适应现在不断扩大的内存和不断增加的处理器数量，进一步降低暂停时间（pausetime），同时兼顾良好的吞吐量。

官方给G1设定的目标是在延迟可控的情况下获得尽可能高的吞吐量，所以才担当起“全功能收集器”的重任与期望。



为什么名字叫做GarbageFirst（G1）呢？

- 因为G1是一个并行回收器，它把堆内存分割为很多不相关的区域（Region）（物理上不连续的）。使用不同的Region来表示Eden、幸存者0区，幸存者1区，老年代等。
- G1 GC有计划地避免在整个Java堆中进行全区域的垃圾收集。G1跟踪各个 Region 里面的垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的Region。
- 由于这种方式的侧重点在于回收垃圾最大量的区间（Region），所以我们给G1一个名字：垃圾优先（GarbageFirst）。



G1（Garbage-First）是一款面向服务端应用的垃圾收集器，主要针对配备多核CPU及大容量内存的机器，以极高概率满足GC停顿时间的同时，还兼具高吞吐量的性能特征。

在JDK1.7版本正式启用。移除了Experimental的标识，是JDK9以后的默认垃圾回收器，取代了CMS回收器以及Parallel+Parallelold组合。被Oracle官方称为“全功能的垃圾收集器”。

与此同时，CMS已经在JDK9中被标记为废弃（deprecated）。在jdk8中还不是默认的垃圾回收器，需要使用-XX:+UseG1GC来启用。



**优势**

与其他GC收集器相比，G1使用了全新的分区算法，其特点如下所示：

1. 并行与并发
   - 并行性：G1在回收期间，可以有多个GC线程同时工作，有效利用多核计算能力。此时用户线程STW
   - 并发性：G1拥有与应用程序交替执行的能力，部分工作可以和应用程序同时执行，因此，一般来说，不会在整个回收阶段发生完全阻塞应用程序的情况
2. 分代收集
   - 从分代上看，G1依然属于分代型垃圾回收器，它会区分年轻代和老年代，年轻代依然有Eden区和Survivor区。但从堆的结构上看，它不要求整个Eden区、年轻代或者老年代都是连续的，也不再坚持固定大小和固定数量。
   - 将堆空间分为若干个区域（Region），这些区域中包含了逻辑上的年轻代和老年代。
   - 和之前的各类回收器不同，它同时兼顾年轻代和老年代。对比其他回收器，或者工作在年轻代，或者工作在老年代；

![](JVM.assets/141.png)

![](JVM.assets/142.png)



3. 空间整合
   - CMS：“标记-清除”算法、内存碎片、若干次GC后进行一次碎片整理
   - G1将内存划分为一个个的region。内存的回收是以region作为基本单位的。Region之间是复制算法，但整体上实际可看作是标记-压缩（Mark-Compact）算法，两种算法都可以避免内存碎片。这种特性有利于程序长时间运行，分配大对象时不会因为无法找到连续内存空间而提前触发下一次GC。尤其是当 Java堆非常大的时候，G1的优势更加明显。



4. 可预测的停顿时间模型（即：软实时soft real-time）
   这是G1相对于CMS的另一大优势，G1除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为M毫秒的时间片段内，消耗在垃圾收集上的时间不得超过N毫秒。
   - 由于分区的原因，G1可以只选取部分区域进行内存回收，这样缩小了回收的范围，因此对于全局停顿情况的发生也能得到较好的控制。
   - G1跟踪各个Region里面的垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的Region。保证了G1收集器在有限的时间内可以获取尽可能高的收集效率。
   - 相比于CMS GC，G1未必能做到CMS在最好情况下的延时停顿，但是最差情况要好很多。



**缺点**

相较于CMS，G1还不具备全方位、压倒性优势。比如在用户程序运行过程中， G1无论是为了垃圾收集产生的内存占用（Footprint）还是程序运行时的额外执行负载（overload）都要比CMS要高。

从经验上来说，在小内存应用上CMS的表现大概率会优于G1，而G1在大内存应用上则发挥其优势。平衡点在6-8GB之间。



##### 4.10.1 参数设置

- -XX:+UseG1GC：手动指定使用G1收集器执行内存回收任务。
- -XX:G1HeapRegionSize：设置每个Region的大小。值是2的幂，范围是1MB到32MB之间，目标是根据最小的Java堆大小划分出约2048个区域。默认是堆内存的 1/2000。
- -XX:MaxGCPauseMillis：设置期望达到的最大GC停顿时间指标（JVM会尽力实现，但不保证达到）。默认值是200ms
- -XX:ParallelGCThread：设置STW时，GC线程数的值。最多设置为8
- -XX:ConcGCThreads：设置并发标记的线程数。将n设置为并行垃圾回收线程数（ParallelGCThreads）的1/4左右。
- -XX:InitiatingHeapOccupancyPercent：设置触发并发GC周期的Java堆占用率阈值。超过此值，就触发GC。默认值是45%。



**常见操作步骤**

G1的设计原则就是简化JVM性能调优，开发人员只需要简单的三步即可完成调优：

- 第一步：开启G1垃圾收集器
- 第二步：设置堆的最大内存
- 第三步：设置最大的停顿时间

G1中提供了三种垃圾回收模式：YoungGC、MixedGC和FullGC，在不同的条件下被触发。



##### 4.10.2 适用场景

- 面向服务端应用，针对具有大内存、多处理器的机器。（在普通大小的堆里表现并不惊喜)
- 最主要的应用是需要低GC延迟，并具有大堆的应用程序提供解决方案；
- 如：在堆大小约6GB或更大时，可预测的暂停时间可以低于0.5秒；（G1通过每次只清理一部分而不是全部的Region的增量式清理来保证每次GC停顿时间不会过长）。
- 用来替换掉JDK1.5中的CMS收集器；
  在下面的情况时，使用G1可能比CMS好： 
  1. 超过50%的Java堆被活动数据占用；
  2. 对象分配频率或年代提升频率变化很大； 
  3. GC停顿时间过长（长于0.5至1秒）。
- HotSpot垃圾收集器里，除了G1以外，其他的垃圾收集器使用内置的JVM线程执行 GC的多线程操作，而G1GC可以采用应用线程承担后台运行的GC工作，即当JVM的GC 线程处理速度慢时，系统会调用应用程序线程帮助加速垃圾回收过程。



##### 4.10.3 分区Region

使用G1收集器时，它将整个Java堆划分成约2048个大小相同的独立Region 块，每个Region块大小根据堆空间的实际大小而定，整体被控制在1MB到32MB 之间，且为2的N次幂，即1MB，2MB，4MB，8MB，16MB，32MB。可以通过-XX:G1HeapRegionSize设定。所有的Region大小相同，且在JVM生命周期内不会被改变。

虽然还保留有新生代和老年代的概念，但新生代和老年代不再是物理隔离的了，它们都是一部分Region（不需要连续）的集合。通过Region的动态分配方式实现逻辑上的连续。

![](JVM.assets/143.png)

- 一个 region 有可能属于 Eden，Survivor 或者old/Tenured 内存区域。但是一个region只可能属于一个角色。图中的E表示该region属于Eden内存区域，S表示属于survivor内存区域，o表示属于old内存区域。图中空白的表示未使用的内存空间。
- G1垃圾收集器还增加了一种新的内存区域，叫做Humongous内存区域，如图中的 H 块。主要用于存储大对象，如果超过1.5个region，就放到H。

**设置H的原因**：对于堆中的大对象，默认直接会被分配到老年代，但是如果它是一个短期存在的大对象，就会对垃圾收集器造成负面影响。为了解决这个问题，G1划分了一个Humongous区，它用来专门存放大对象。如果一个H区装不下一个大对象，那么G1会寻找连续的H区来存储。为了能找到连续的H区，有时候不得不启动FullGC。G1的大多数行为都把H区作为老年代的一部分来看待。

![](JVM.assets/144.png)



##### 4.10.4 回收过程

G1GC的垃圾回收过程主要包括如下三个环节：

- 年轻代GC（Young GC）
- 老年代并发标记过程（Concurrent Marking）
- 混合回收（Mixed GC）
- 如果需要，单线程、独占式、高强度的FullGC还是继续存在的。它针对GC的评估失败提供了一种失败保护机制，即强力回收。

![](JVM.assets/145.png)

顺时针，young gc -> young gc + concurrent mark -> Mixed GC顺序, 进行垃圾回收。

- 应用程序分配内存，当年轻代的Eden区用尽时开始年轻代回收过程；G1的年轻代收集阶段是一个并行的独占式收集器。在年轻代回收期，G1GC暂停所有应用程序线程，启动多线程执行年轻代回收。然后从年轻代区间移动存活对象到survivor区间或者老年区间，也有可能是两个区间都会涉及。
- 当堆内存使用达到一定值（默认45%）时，开始老年代并发标记过程。
- 标记完成马上开始混合回收过程。对于一个混合回收期，G1GC从老年区间移动存活对象到空闲区间，这些空闲区间也就成为了老年代的一部分。和年轻代不同，老年代的G1回收器和其他GC不同，G1的老年代回收器不需要整个老年代被回收，一次只需要扫描/回收一小部分老年代的Region就可以了。同时，这个老年代Region是和年轻代一起被回收的。
- 举个例子：一个Web服务器，Java进程最大堆内存为4G，每分钟响应1500个请求，每45 秒钟会新分配大约2G的内存。G1会每45秒钟进行一次年轻代回收，每31个小时整个堆的使用率会达到45%，会开始老年代并发标记过程，标记完成后开始四到五次的混合回收。



###### (1) 记忆集 Remembered Set

一个对象被不同区域引用的问题。一个Region不可能是孤立的，一个Region中的对象可能被其他任意Region中对象引用，判断对象存活时，是否需要扫描整个Java堆才能保证准确？

在其他的分代收集器，也存在这样的问题（而G1更突出）

回收新生代也不得不同时扫描老年代？这样的话会降低MinorGC的效率；



解决方法：

- 无论G1还是其他分代收集器，JVM都是使用RememberedSet来避免全局扫描
- 每个Region都有一个对应的Remembered Set
- 每次Reference类型数据写操作时，都会产生一个WriteBarrier暂时中断操作
- 然后检查将要写入的引用指向的对象是否和该Reference类型数据在不同的Region（其他收集器：检查老年代对象是否引用了新生代对象）
- 如果不同，通过cardTable把相关引用信息记录到引用指向对象的所在Region对应的 Remembered Set中;
- 当进行垃圾收集时，在GC根节点的枚举范围加入RememberedSet；就可以保证不进行全局扫描，也不会有遗漏。

![](JVM.assets/146.png)



###### (2) 年轻代GC

JVM启动时，G1先准备好Eden区，程序在运行过程中不断创建对象到Eden区，当Eden空间耗尽时，G1会启动一次年轻代垃圾回收过程。

年轻代垃圾回收只会回收Eden区和survivor区。

YGC时，首先G1停止应用程序的执行（Stop-The-World），G1创建回收集(Collection Set)，回收集是指需要被回收的内存分段的集合，年轻代回收过程的回收集包含年轻代Eden区和survivor区所有的内存分段。

![](JVM.assets/147.png)

然后开始如下回收过程：

1. 第一阶段，扫描根。
   根是指static变量指向的对象，正在执行的方法调用链条上的局部变量等。根引用连同RSet记录的外部引用作为扫描存活对象的入口。
2. 第二阶段，更新RSet。
   处理dirty card queue中的card，更新RSet。此阶段完成后，RSet可以准确的反映老年代对所在的内存分段中对象的引用。
3. 第三阶段，处理RSet。
   识别被老年代对象指向的Eden中的对象，这些被指向的Eden中的对象被认为是存活的对象。
4. 第四阶段，复制对象
   此阶段，对象树被遍历，Eden区内存段中存活的对象会被复制到Survivor区中空的内存分段， Survivor区内存段中存活的对象如果年龄未达阔值，年龄会加1，达到阀值会被会被复制到 Old区中空的内存分段。如果Survivor空间不够，Eden空间的部分数据会直接晋升到老年代空间。
5. 第五阶段，处理引用。
   处理Soft，Weak，Phantom，Final，JNIWeak等引用。最终Eden空间的数据为空，GC停止工作，而目标内存中的对象都是连续存储的，没有碎片，所以复制过程可以达到内存整理的效果，减少碎片。

**dirty card queue**

对于应用程序的引用赋值语句 object.field=object，JVM会在之前和之后执行特殊的操作以在dirty card queue中入队一个保存了对象引用信息的card。在年轻代回收的时候，G1 会对Dirty Card Queue中所有的card进行处理，以更新RSet，保证RSet实时准确的反映引用关系。

那为什么不在引用赋值语句处直接更新RSet呢？这是为了性能的需要，RSet的处理需要线程同步，开销会很大，使用队列性能会好很多。



###### (3) 并发标记过程

1. **初始标记阶段**：标记从根节点直接可达的对象。这个阶段是STW的，并且会触发一次年轻代GC。
2. **根区域扫描(RootRegionScanning)**：G1GC扫描Survivor区直接可达的老年代区域对象，并标记被引用的对象。这一过程必须在young GC之前完成。
3. **并发标记(ConcurrentMarking)**：在整个堆中进行并发标记（和应用程序并发执行），此过程可能被youngGC中断。在并发标记阶段，若发现区域对象中的所有对象都是垃圾，那这个区域会被立即回收。同时，并发标记过程中，会计算每个区域的对象活性（区域中存活对象的比例)。
4. **再次标记(Remark)**：由于应用程序持续进行，需要修正上一次的标记结果。是STW 的。G1中采用了比CMS更快的初始快照算法:snapshot-at-the-beginning(SATB)。
5. **独占清理(cleanup,STW)**：计算各个区域的存活对象和GC回收比例，并进行排序，识别可以混合回收的区域。为下阶段做铺垫。是STW的。
   - 这个阶段并不会实际上去做垃圾的收集
6. **并发清理阶段**：识别并清理完全空闲的区域。



###### (4) 混合回收

当越来越多的对象晋升到老年代old region时，为了避免堆内存被耗尽，虚拟机 会触发一个混合的垃圾收集器，即MixedGC，该算法并不是一个Old GC，除了回收整个YoungRegion，还会回收一部分的oldRegion。这里需要注意：是一部分老年代，而不是全部老年代。可以选择哪些Old Region进行收集，从而可以对垃圾回收的耗时时间进行控制。也要注意的是MixedGC并不是FullGC。

![](JVM.assets/148.png)

- 并发标记结束以后，老年代中百分百为垃圾的内存分段被回收了，部分为垃圾的内存分段被计算了出来。默认情况下，这些老年代的内存分段会分8次（可以通过-XX:G1MixedGCCountTarget设置）被回收。
- 混合回收的回收集（collectionSet）包括八分之一的老年代内存分段，Eden区内存分段，Survivor区内存分段。混合回收的算法和年轻代回收的算法完全一样，只是回收集多了老年代的内存分段。具体过程请参考上面的年轻代回收过程。
- 由于老年代中的内存分段默认分8次回收，G1会优先回收垃圾多的内存分段。**垃圾占内存分段比例越高的，越会被先回收**。并且有一个值会决定内存分段是否被回收，-XX:G1MixedGCLiveThresholdPercent，默认为65%，意思是垃圾占内存分段比例要达到65%才会被回收。如果垃圾占比太低，意味着存活的对象占比高，在复制的时候会花费更多的时间。
- 混合回收并不一定要进行8次。有一个阈值-XX:G1HeapWastePercent，默认值为10%，意思是充许整个堆内存中有10%的空间被浪费，意味着如果发现可以回收的垃圾占堆内存的比例低于10%，则不再进行混合回收。因为GC会花费很多的时间但是回收到的内存却很少。



###### (5) Full GC

G1的初衷就是要避免FullGC的出现。但是如果上述方式不能正常工作，G1 会停止应用程序的执行（Stop-The-World），使用单线程的内存回收算法进行垃圾回收，性能会非常差，应用程序停顿时间会很长。

要避免FullGC的发生，一旦发生需要进行调整。什么时候会发生FullGC呢？比如堆内存太小，当G1在复制存活对象的时候没有空的内存分段可用，则会回退到full gc，这种情况可以通过增大内存解决。

导致G1FullGC的原因可能有两个：

1. Evacuation的时候没有足够的to-space来存放晋升的对象；
2. 并发处理过程完成之前空间耗尽。



###### (6) 补充

从Oracle官方透露出来的信息可获知，回收阶段（Evacuation）其实本也有想过设计成与用户程序一起并发执行，但这件事情做起来比较复杂，考虑到G1只是回收一部分Region，停顿时间是用户可控制的，所以并不迫切去实现，而选择把这个特性放到了G1之后出现的低延迟垃圾收集器（即ZGC）中。另外，还考虑到G1不是仅仅面向低延迟，停顿用户线程能够最大幅度提高垃圾收集效率，为了保证吞吐量所以才选择了完全暂停用户线程的实现方案。



###### (7) 优化建议

- 年轻代大小
  - 避免使用-Xmn或-XX:NewRatio等相关选项显式设置年轻代大小
  - 固定年轻代的大小会覆盖暂停时间目标
- 暂停时间目标不要太过严苛
  - G1 GC的吞吐量目标是90%的应用程序时间和10%的垃圾回收时间
  - 评估G1 GC的吞吐量时，暂停时间目标不要太严苛。目标太过严苛表示你愿意承受更多的垃圾回收开销，而这些会直接影响到吞吐量。





#### 4.11 总结

截止JDK1.8，一共有7款不同的垃圾收集器。每一款不同的垃圾收集器都有不同的特点，在具体使用的时候，需要根据具体的情况选用不同的垃圾收集器。

![](JVM.assets/149.png)

GC发展阶段：Serial => Parallel(并行) => CMS(并发) => G1 => ZGC



**怎么选择垃圾回收器？**

Java垃圾收集器的配置对于JVM优化来说是一个很重要的选择，选择合适的垃圾收集器可以让JVM的性能有一个很大的提升。

1. 优先调整堆的大小让JVM自适应完成。如果内存小于100M，使用串行收集器。
2. 如果是单核、单机程序，并且没有停顿时间的要求，串行收集器。
3. 如果是多CPU、需要高吞吐量、允许停顿时间超过1秒，选择并行或者JVM自已选择。
4. 如果是多CPU、追求低停顿时间，需快速响应(比如延迟不能超过1秒，如互联网应用)，使用并发收集器。
5. 官方推荐G1，性能高。现在互联网的项目，基本都是使用G1。



#### 4.12 GC日志分析

通过阅读GC日志，我们可以了解Java虚拟机内存分配与回收策略。

内存分配与垃圾回收的参数列表

- -XX:+PrintGC：输出GC日志。类似：-verbose:gc
- -XX:+PrintGCDetails：输出GC的详细日志
- -XX:+PrintGCTimeStamps：输出GC的时间戳（以基准时间的形式）
- -XX:+PrintGCDateStamps：输出GC的时间戳（以日期的形式，如2013-05-04T21:53:59.234+0800）
- -XX:+PrintHeapAtGC：在进行GC的前后打印出堆的信息
- -xloggc:../logs/gc.log：日志文件的输出路径

![](JVM.assets/150.png)

![](JVM.assets/151.png)

![](JVM.assets/152.png)



- 如果想把GC日志存到文件的话，是下面这个参数：


```
-Xloggc:/path/to/gc.log
```



日志补充说明：

- "[GC"和"[Full GC"说明了这次垃圾收集的停顿类型，如果有"Full"则说明GC发生了"Stop The World"
- 使用Serial收集器在新生代的名字是Default New Generation，因此显示的是"[DefNew"
- 使用ParNew收集器在新生代的名字会变成"[ParNew"，意思是"ParallelNewGeneration"
- 使用ParallelScavenge收集器在新生代的名字是"[PSYoungGen"
- 老年代的收集和新生代道理一样，名字也是收集器决定的
- 使用G1收集器的话，会显示为"garbage-first heap"



- Allocation Failure：表明本次引起GC的原因是因为在年轻代中没有足够的空间能够存储新的数据了。
- [PSYoungGen：5986K->696K(8704K)] 5986K->704K(9216K)
  中括号内：GC回收前年轻代大小，回收后大小，（年轻代总大小）
  括号外：GC回收前年轻代和老年代大小，回收后大小，（年轻代和老年代总大小）
- user代表用户态回收耗时，sys内核态回收耗时，rea实际耗时。由于多核的原因，时间总和可能会超过real时间

![](JVM.assets/153.png)



- Minor GC 日志

![](JVM.assets/154.png)



- Full GC 日志

![](JVM.assets/155.png)



```java
/**
 * 在jdk7 和 jdk8中分别执行
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * @author shkstart  shkstart@126.com
 * @create 2020  0:12
 */
public class GCLogTest1 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] agrs) {
        testAllocation();
    }
}
```

![](JVM.assets/156.png)

![](JVM.assets/157.png)



#### 4.13 GC日志分析工具

可以用一些工具去分析这些gc日志。

常用的日志分析工具有：GCViewer、GCEasy、GCHisto、GCLogViewer、Hpjmeter、garbagecat等。



#### 4.14 垃圾回收器新发展

GC仍然处于飞速发展之中，目前的默认选项G1GC在不断的进行改进，很多我们原来认为的缺点，例如串行的FullGC、CardTable扫描的低效等，都已经被大幅改进，例如，JDK10以后，Fu11GC已经是并行运行，在很多场景下，其表现还略优于ParallelGC的并行FullGC实现。

即使是SerialGC，虽然比较古老，但是简单的设计和实现未必就是过时的，它本身的开销，不管是GC相关数据结构的开销，还是线程的开销，都是非常小的，所以随着云计算的兴起，在Serverless等新的应用场景下，SerialGC找到了新的舞台。

比较不幸的是CMSGC，因为其算法的理论缺陷等原因，虽然现在还有非常大的用户群体，但在JDK9中已经被标记为废弃，并在JDK14版本中移除。



JDK11新特性

![](JVM.assets/158.png)



Open JDK12的Shenandoah GC

- 现在G1回收器已成为默认回收器好几年了。
- 我们还看到了引入了两个新的收集器： ZGC（JDK11出现）和shenandoah（Open JDK12）。
  - 主打特点：低停顿时间

**OpenJDK12的shenandoahGC：低停顿时间的GC（实验性）**

Shenandoah，无疑是众多Gc中最孤独的一个。是第一款不由oracle公司团队领导开发的HotSpot垃圾收集器。不可避免的受到官方的排挤。比如号称OpenJDK和 OracleJDK没有区别的oracle公司仍拒绝在OracleJDK12中支持Shenandoah。

Shenandoah垃圾回收器最初由RedHat进行的一项垃圾收集器研究项目PauselessGC的实现，旨在针对JVM上的内存回收实现低停顿的需求。在2014年贡献给 OpenJDK。

RedHat研发shenandoah团队对外宣称，Shenandoah垃圾回收器的暂停时间与堆大小无关，这意味着无论将堆设置为200MB还是200GB，99.9%的目标都可以把垃圾收集的停顿时间限制在十毫秒以内。不过实际使用性能将取决于实际工作堆的大小和工作负载。

![](JVM.assets/159.png)

- 这是RedHat在2016年发表的论文数据，测试内容是使用ES对200GB的维基百科数据进行索引。从结果看：
  - 停顿时间比其他几款收集器确实有了质的飞跃，但也未实现最大停顿时间控制在十毫秒以内的目标。
  - 而吞吐量方面出现了明显的下降，总运行时间是所有测试收集器里最长的。

总结：

- Shenandoah GC的弱项：高运行负担下的吞吐量下降。 
- ShenandoahGC的强项：低延迟时间。





#### 4.15 ZGC介绍

ZGC与Shenandoah目标高度相似，在尽可能对吞吐量影响不大的前提下，实现在任意堆内存大小下都可以把垃圾收集的停顿时间限制在十毫秒以内的低延迟。

《深入理解Java虚拟机》一书中这样定义ZGC：ZGC收集器是一款基于Region内存布局的，（暂时）不设分代的，使用了读屏障、染色指针和内存多重映射等技术来实现可并发的标记-压缩算法的，以低延迟为首要目标的一款垃圾收集器。

ZGC的工作过程可以分为4个阶段：并发标记-并发预备重分配-并发重分配-并发重映射等。

ZGC几乎在所有地方并发执行的，除了初始标记的是STW的。所以停顿时间几乎就耗费在初始标记上，这部分的实际时间是非常少的。

![](JVM.assets/160.png)

![](JVM.assets/161.png)

在zGc的强项停顿时间测试上，它毫不留情的将Parallel、G1拉开了两个数量级的差距。无论平均停顿、95%停顿、99%停顿、99.9%停顿，还是最大停顿时间，ZGC都能毫不费劲控制在10毫秒以内。







# JVM中篇：字节码与类的加载篇

## 一、概述

### 1. 字节码文件的跨平台性

1. Java语言：跨平台的语言（writeonce，runanywhere）
   - 当Java源代码成功编译成字节码后，如果想在不同的平台上面运行，则无须再次编译
   - 这个优势不再那么吸引人了。Python、PHP、Perl、Ruby、Lisp等有强大的解释器。
   - 跨平台似乎已经快成为一门语言必选的特性。



2. Java虚拟机：跨语言的平台
   Java虚拟机不和包括Java在内的任何语言绑定，它只与“class文件”这种特定的二进制文件格式所关联。无论使用何种语言进行软件开发，只要能将源文件编译为正确的class文件，那么这种语言就可以在Java虚拟机上执行。可以说，统一而强大的 Class文件结构，就是Java虚拟机的基石、桥梁。

![](JVM.assets/162.png)

所有的JVM全部遵守Java虚拟机规范，也就是说所有的JVM环境都是一样的，这样一来字节码文件可以在各种JVM上运行。



3. 想要让一个Java程序正确地运行在JVM中，Java源码就必须要被编译为符合JVM规范的字节码。
   - 前端编译器的主要任务就是负责将符合Java语法规范的Java代码转换为符合JVM规范的字节码文件。
   - javac是一种能够将Java源码编译为字节码的前端编译器。
   - javac编译器在将Java源码编译为一个有效的字节码文件过程中经历了4个步骤，分别是词法解析、语法解析、语义解析以及生成字节码。

![](JVM.assets/163.png)

Oracle的JDK软件包括两部分内容：

- 一部分是将Java源代码编译成Java虚拟机的指令集的编译器
- 另一部分是用于实现Java虚拟机的运行时环境。



### 2. Java的前端编译器

![](JVM.assets/164.png)

Java源代码的编译结果是字节码，那么肯定需要有一种编译器能够将Java源码编译为字节码，承担这个重要责任的就是配置在 path环境变量中的javac编译器。javac是一种能够将Java源码编译为字节码的前端编译器。

HotSpot VM并没有强制要求前端编译器只能使用javac来编译字节码，其实只要编译结果符合JVM规范都可以被JVM所识别即可。在Java的前端编译器领域，除了javac之外，还有一种被大家经常用到的前端编译器，那就是内置在Eclipse中的ECJ(Eclipse Compiler for Java)编译器。和Javac的全量式编译不同，ECJ是一种增量式编译器。

- 在Eclipse中，当开发人员编写完代码后，使用“Ctrl+S”快捷键时，ECJ编译器所采取的编译方案是把未编译部分的源码逐行进行编译，而非每次都全量编译。因此ECJ的编译效率会比javac更加迅速和高效，当然编译质量和javac相比大致还是一样的。
- ECJ不仅是Eclipse的默认内置前端编译器，在Tomcat中同样也是使用ECJ编译器来编译jsp文件。由于ECJ编译器是采用GPLv2的开源协议进行源代码公开，所以，大家可以登录eclipse官网下载ECJ编译器的源码进行二次开发。
- 默认情况下，IntelliJIDEA使用javac编译器。（还可以自己设置为AspectJ编译器ajc）

前端编译器并不会直接涉及编译优化等方面的技术，而是将这些具体优化细节移交给HotSpot的JIT编译器负责。





### 3. 透过字节码指令看代码细节

```java
public class IntegerTest {
    public static void main(String[] args) {

        Integer x = 5;
        int y = 5;
        System.out.println(x == y);

        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);//true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);//false

    }
}

```

```java
public class StringTest {
    public static void main(String[] args) {
        String str = new String("hello") + new String("world");
        String str1 = "helloworld";
        System.out.println(str == str1);
        String str2 = new String("helloworld");
        System.out.println(str == str2);
    }
}
```

```java
/*
成员变量（非静态的）的赋值过程： ① 默认初始化 - ② 显式初始化 /代码块中初始化 - ③ 构造器中初始化 - ④ 有了对象之后，可以“对象.属性”或"对象.方法"
 的方式对成员变量进行赋值。
 */
class Father {
    int x = 10;

    public Father() {
        this.print();
        x = 20;
    }
    public void print() {
        System.out.println("Father.x = " + x);
    }
}

class Son extends Father {
    int x = 30;
//    float x = 30.1F;
    public Son() {
        this.print();
        x = 40;
    }
    public void print() {
        System.out.println("Son.x = " + x);
    }
}

public class SonTest {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.x);
        //结果：
        // Son.x = 0
        // Son.x = 30
        // 
    }
}
```



## 二、Class文件

### 1. 解读Class文件的三种方式

- 字节码文件里是什么？
  源代码经过编译器编译之后便会生成一个字节码文件，字节码是一种二进制的类文件，它的内容是JVM的指令，而不像C、C++经由编译器直接生成机器码。

- 什么是字节码指令（bytecode)？
  Java虚拟机的指令由一个字节长度的、代表着某种特定操作含义的操作码（opcode）以及跟随其后的零至多个代表此操作所需参数的操作数（operand）所构成。虚拟机中许多指令并不包含操作数，只有一个操作码。比如：操作码 (操作数)
  ![](JVM.assets/165.png)



- 如何解读供虚拟机解释执行的二进制字节码？

1. 一个一个二进制的看。这里用到的是Notepad++，需要安装一个HEX-Editor插件，或者使用BinaryViewer

   ![](JVM.assets/166.png)



2. 使用javap指令：jdk自带的反解析工具



3. 使用IDEA插件：jclasslib 或jclasslib bytecode viewer客户端工具。（可视化更好）
   ![](JVM.assets/167.png)



### 2. Class文件结构

**Class类的本质**：任何一个class文件都对应着唯一一个类或接口的定义信息，但反过来说，Class文件实际上它并不一定以磁盘文件的形式存在。Class文件是一组以8位字节为基础单位的二进制流。

**Class文件格式**：Class的结构不像XML等描述语言，由于它没有任何分隔符号。所以在其中的数据项，无论是字节顺序还是数量，都是被严格限定的，哪个字节代表什么含义，长度是多少，先后顺序如何，都不允许改变。

Class文件格式采用一种类似于c语言结构体的方式进行数据存储，这种结构中只有两种数据类型：无符号数和表。

- 无符号数属于基本的数据类型，以u1、u2、u4、u8来分别代表1个字节、2个字节、4个字节和8个字节的无符号数，无符号数可以用来描述数字、索引引用、数量值或者按照UTF-8编码构成字符串值。
- 表是由多个无符号数或者其他表作为数据项构成的复合数据类型，所有表都习惯性地以“_info”结尾。表用于描述有层次关系的复合结构的数据，整个Class文件本质上就是一张表。由于表没有固定长度，所以通常会在其前面加上个数说明



**class文件结构概述**：Class文件的结构并不是一成不变的，随着Java虚拟机的不断发展，总是不可避免地会对Class文件结构做出一些调整，但是其基本结构和框架是非常稳定的。

class文件的总体结构如下：

- 魔数
- Class文件版本
- 常量池访问标志
- 类索引，父类索引，接口索引集合
- 字段表集合
- 方法表集合
- 属性表集合

![](JVM.assets/168.png)

![169](JVM.assets/169.png)



#### 2.1 魔数

- 每个Class文件开头的4个字节的无符号整数称为魔数（MagicNumber）
- 它的唯一作用是确定这个文件是否为一个能被虚拟机接受的有效合法的Class文件。即：魔数是Class文件的标识符。
- 魔数值固定为OxCAFEBABE。不会改变。
- 如果一个Class文件不以OxCAFEBABE开头，虚拟机在进行文件校验的时候就会直接抛出以下错误： Error: A JNI error has occurred, please check your installation and try again Exception in thread "main" java.lang.ClassFormatError: Incompatible magic value 1885430635 in class file StringTest
- 使用魔数而不是扩展名来进行识别主要是基于安全方面的考虑，因为文件扩展名可以随意地改动。



#### 2.2 Class文件版本号

- 紧接着魔数的4个字节存储的是Class文件的版本号。同样也是4个字节。第5个和第6个字节所代表的含义就是编译的副版本号minor_version，而第7个和第8个字节就是编译的主版本号major_version。
- 它们共同构成了class文件的格式版本号。譬如某个Class文件的主版本号为M，副版本号为m，那么这个class文件的格式版本号就确定为M.m。
- 版本号和Java编译器的对应关系如下表：

![](JVM.assets/170.png)

- Java的版本号是从45开始的，JDK1.1之后的每个JDK大版本发布主版本号向上加1。
- 不同版本的Java编译器编译的Class文件对应的版本是不一样的。目前，高版本的Java虚拟机可以执行由低版本编译器生成的Class文件，但是低版本的Java虚拟机不能执行由高版本编译器生成的Class文件。否则JVM会抛出java.lang.UnsupportedClassVersionError异常。
- 在实际应用中，由于开发环境和生产环境的不同，可能会导致该问题的发生。因此，需要我们在开发时，特别注意开发编译的 JDK版本和生产环境中的JDK版本是否一致。
  - 虚拟机JDK版本为1.k（k>=2）时，对应的class文件格式版本号的范围为45.0－44+k.0（含两端）。



#### 2.3 常量池

- 常量池是class文件中内容最为丰富的区域之一。常量池对于class文件中的字段和方法解析也有着至关重要的作用。
- 随着Java虚拟机的不断发展，常量池的内容也日渐丰富。可以说，常量池是整个class文件的基石。



- 在版本号之后，紧跟着的是常量池的数量，以及若干个常量池表项。
- 常量池中常量的数量是不固定的，所以在常量池的入口需要放置一项u2类型的无符号数，代表常量池容量计数值（ constant_pool_count）。与Java中语言习惯不一样的是，这个容量计数是从1而不是0开始的。

![](JVM.assets/171.png)

由上表可见，Class文件使用了一个前置的容量计数器（constant_pool_count）加若干个连续的数据项（constant_pool）的形式来描述常量池内容。我们把这一系列连续常量池数据称为常量池集合。

- 常量池表项中，用于存放编译时期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放



##### 2.3.1 常量池计数器

- 由于常量池的数量不固定，时长时短，所以需要放置两个字节来表示常量池容量计数值。
- 常量池容量计数值（u2类型）：从1开始，表示常量池中有多少项常量。即constant_pool_count=1表示常量池中有0个常量项
- Demo的值为：
  ![](JVM.assets/172.png)

其值为0x0016，掐指一算，也就是22。

需要注意的是，这实际上只有21项常量。索引为范围是1-21。为什么呢？

通常我们写代码时都是从0开始的，但是这里的常量池却是从1开始，因为它把第0项常量空出来了。这是为了满足后面某些指向常量池的索引值的数据在特定情况下需要表达“不引用任何一个常量池项目"的含义，这种情况可用索引值0来表示。





##### 2.3.2 常量池表

- constant_pool是一种表结构，以1～constant_pool_count－1为索引。表明了后面有多少个常量项。
- 常量池主要存放两大类常量：**字面量（Literal）**和**符号引用（SymbolicReferences）**
- 它包含了class文件结构及其子结构中引用的所有字符串常量、类或接口名、字段名和其他常量。常量池中的每一项都具备相同的特征。第1个字节作为类型标记，用于确定该项的格式，这个字节称为tagbyte（标记字节、标签字节）。

![](JVM.assets/173.png)



###### (1) 字面量和符号引用

在对这些常量解读前，我们需要搞清楚几个概念。
常量池主要存放两大类常量：字面量（Literal）和符号引用（SymbolicReferences）。如下表：

![](JVM.assets/174.png)

1. **全限定名**：com/atguigu/test/Demo这个就是类的全限定名，仅仅是把包名的"."替换成"/"，为了使连续的多个全限定名之间不产生混淆，在使用时最后一般会加入一个“；”表示全限定名结束。
2. **简单名称**：简单名称是指没有类型和参数修饰的方法或者字段名称，上面例子中的类的add()方法和num字段的简单名称分别是add和num。
3. **描述符**：描述符的作用是用来描述字段的数据类型、方法的参数列表（包括数量、类型以及顺序）和返回值。根据描述符规则，基本数据类型（byte、char、double、float、int、long、short、boolean）以及代表无返回值的void类型都用一个大写字符来表示，而对象类型则用字符L加对象的全限定名来表示，详见下表：

| 标志符 | 含义                |
| ------ | ------------------- |
| B      | 基本数据类型byte    |
| C      | 基本数据类型char    |
| D      | 基本数据类型double  |
| F      | 基本数据类型float   |
| I      | 基本数据类型int     |
| J      | 基本数据类型long    |
| S      | 基本数据类型short   |
| Z      | 基本数据类型boolean |
| V      | 代表void类型        |

用描述符来描述方法时，按照先参数列表，后返回值的顺序描述，参数列表按照参数的严格顺序放在一组小括号“（）”之内。如方法 java.lang.String toString()的描述符为() Ljava/lang/String;，方法int abc(int[]x，inty)的描述符为([II)I。



**补充说明**：虚拟机在加载class文件时才会进行动态链接，也就是说，Class文件中不会保存各个方法和字段的最终内存布局信息，因此，这些字段和方法的符号引用不经过转换是无法直接被虚拟机使用的。当虚拟机运行时，需要从常量池中获得对应的符号引用，再在类加载过程中的解析阶段将其替换为直接引用，并翻译到具体的内存地址中。

这里说明下符号引用和直接引用的区别与关联：

- **符号引用**：符号引用以一组符号来描述所引用的目标，符号可以是任何形式的字面量，只要使用时能无歧义地定位到目标即可。符号引用与虚拟机实现的内存布局无关，引用的目标并不一定已经加载到了内存中。
- **直接引用**：直接引用可以是直接指向目标的指针、相对偏移量或是一个能间接定位到目标的句柄。直接引用是与虚拟机实现的内存布局相关的，同一个符号引用在不同虚拟机实例上翻译出来的直接引用一般不会相同。如果有了直接引用，那说明引用的目标必定已经存在于内存之中了。



###### (2) 常量类型和结构

![](JVM.assets/175.png)

![176](JVM.assets/176.png)

总结1：

- 这14种表（或者常量项结构）的共同点是：表开始的第一位是一个u1类型的标志位（tag），代表当前这个常量项使用的是哪种表结构，即哪种常量类型。
- 在常量池列表中，CONSTANT_Utf8_info常量项是一种使用改进过的UTF-8编码格式来存储诸如文字字符串、类或者接口的全限定名、字段或者方法的简单名称以及描述符等常量字符串信息。
- 这14种常量项结构还有一个特点是，其中13个常量项占用的字节固定，只有CONSTANT_Utf8_info占用字节不固定，其大小由length决定。为什么呢？因为从常量池存放的内容可知，其存放的是字面量和符号引用，最终这些内容都会是一个字符串，这些字符串的大小是在编写程序时才确定，比如你定义一个类，类名可以取长取短，所以在没编译前，大小不固定，编译后，通过utf-8编码，就可以知道其长度。



总结2：

- **常量池**：可以理解为class文件之中的资源仓库，它是class文件结构中与其他项目关联最多的数据类型（后面的很多数据类型都会指向此处），也是占用class文件空间最大的数据项目之一。
- **常量池中为什么要包含这些内容**：Java代码在进行Javac编译的时候，并不像c和c++那样有“连接”这一步骤，而是在虚拟机加载class文件的时候进行动态链接。也就是说，在Class文件中不会保存各个方法、字段的最终内存布局信息，因此这些字段、方法的符号引用不经过运行期转换的话无法得到真正的内存入口地址，也就无法直接被虚拟机使用。当虚拟机运行时，需要从常量池获得对应的符号引用，再在类创建时或运行时解析、翻译到具体的内存地址之中。关于类的创建和动态链接的内容，在虚拟机类加载过程时再进行详细讲解



#### 2.4 访问标识

在常量池后，紧跟着访问标记。该标记使用两个字节表示，用于识别一些类或者接口层次的访问信息，包括：这个Class 是类还是接口；是否定义为public类型；是否定义为abstract类型；如果是类的话，是否被声明为final等。各种访问标记如下所示：

![](JVM.assets/177.png)

- 类的访问权限通常为ACC_开头的常量。
- 每一种类型的表示都是通过设置访问标记的32位中的特定位来实现的。比如，若是publicfinal的类，则该标记为 ACC_PUBLIC | ACC_FINAL。
- 使用ACC_SUPER可以让类更准确地定位到父类的方法super.method()，现代编译器都会设置并且使用这个标记。



补充说明：

1. 带有ACC_INTERFACE标志的class文件表示的是接口而不是类，反之则表示的是类而不是接口。
   - 如果一个class文件被设置了ACC_INTERFACE标志，那么同时也得设置ACC_ABSTRACT标志。同时它不能再设置ACC_FINAL、 ACC_SUPER或ACC_ENUM标志。
   - 如果没有设置ACC_INTERFACE标志，那么这个class文件可以具有上表中除ACC_ANNOTATION外的其他所有标志。当然，ACC_FINAL和 ACC_ABSTRACT这类互斥的标志除外。这两个标志不得同时设置。
2. ACC_SUPER标志用于确定类或接口里面的invokespeciaL指令使用的是哪一种执行语义。针对Java虚拟机指令集的编译器都应当设置这个标志。对于JavaSE8及后续版本来说，无论class文件中这个标志的实际值是什么，也不管class文件的版本号是多少，Java虚拟机都认为每个class文件均设置了ACC_SUPER标志。
   - ACC_SUPER标志是为了向后兼容由旧Java编译器所编译的代码而设计的。目前的ACC_SUPER标志在由JDK1.0.2之前的编译器所生成的access_flags中是没有确定含义的，如果设置了该标志，那么Oracle的Java虚拟机实现会将其忽略。
3. ACC_SYNTHETIC标志意味着该类或接口是由编译器生成的，而不是由源代码生成的。
4. 注解类型必须设置ACC_ANNOTATION标志。如果设置了ACC_ANNOTATION标志，那么也必须设置ACC_INTERFACE标志。
5. ACC_ENUM标志表明该类或其父类为枚举类型。



#### 2.5 类索引、父类索引、接口索引集合

在访问标记后，会指定该类的类别、父类类别以及实现的接口，格式如下：

| 长度 | 含义                         |
| ---- | ---------------------------- |
| u2   | this_class                   |
| u2   | super_class                  |
| u2   | interfaces_count             |
| u2   | interfaces[interfaces_count] |

- 这三项数据来确定这个类的继承关系。
  - 类索引用于确定这个类的全限定名
  - 父类索引用于确定这个类的父类的全限定名。由于Java语言不允许多重继承，所以父类索引只有一个，除了java.lang.Object之外，所有的Java类都有父类，因此除了java.lang.Object外，所有Java类的父类索引都不为0。
  - 接口索引集合就用来描述这个类实现了哪些接口，这些被实现的接口将按implements语句（如果这个类本身是一个接口，则应当是extends语句）后的接口顺序从左到右排列在接口索引集合中。



1. this_class（类索引）
   2字节无符号整数，指向常量池的索引。它提供了类的全限定名，如com/atguigu/javal/Demo。this_class的值必须是对常量池表中某项的一个有效索引值。常量池在这个索引处的成员必须为CONSTANT_Class_info类型结构体，该结构体表示这个 class文件所定义的类或接口。

2. super_class（父类索引）

   - 2字节无符号整数，指向常量池的索引。它提供了当前类的父类的全限定名。如果我们没有继承任何类，其默认继承的是java/lang/Object类。同时，由于Java不支持多继承，所以其父类只有一个。 
   - superclass指向的父类不能是final。

3. interfaces

   - 指向常量池索引集合，它提供了一个符号引用到所有已实现的接口
   - 由于一个类可以实现多个接口，因此需要以数组形式保存多个接口的索引，表示接口的每个索引也是一个指向常量池的 CONSTANT_Class（当然这里就必须是接口，而不是类）。

   1. interfaces_count（接口计数器）
      interfaces_count项的值表示当前类或接口的直接超接口数量。
   2. interfaces[](接口索引集合）
      interfaces[]中每个成员的值必须是对常量池表中某项的有效索引值，它的长度为 interfaces_count。每个成员interfaces[i]必须为CONSTANT_Class_info结构，其中 0 <= i < interfaces_count。在interfaces[]中，各成员所表示的接口顺序和对应的源代码中给定的接口顺序（从左至右）一样，即interfaces[0]对应的是源代码中最左边的接口。



#### 2.6 字段表集合

fields

- 用于描述接口或类中声明的变量。字段（field）包括类级变量以及实例级变量，但是不包括方法内部、代码块内部声明的局部变量（local variables）。
- 字段叫什么名字、字段被定义为什么数据类型，这些都是无法固定的，只能引用常量池中的常量来描述。
- 它指向常量池索引集合，它描述了每个字段的完整信息。比如字段的标识符、访问修饰符（public、private或protected）、是类变量还是实例变量（static修饰符）、是否是常量（final修饰符）等。



注意事项：

- 字段表集合中不会列出从父类或者实现的接口中继承而来的字段，但有可能列出原本Java代码之中不存在的字段。譬如在内部类中为了保持对外部类的访问性，会自动添加指向外部类实例的字段。
- 在Java语言中字段是无法重载的，两个字段的数据类型、修饰符不管是否相同，都必须使用不一样的名称，但是对于字节码来讲，如果两个字段的描述符不一致，那字段重名就是合法的。



##### 2.6.1 字段计数器

fields_count的值表示当前class文件fields表的成员个数。使用两个字节来表示。

fields表中每个成员都是一个field_info结构，用于表示该类或接口所声明的所有类字段或者实例字段，不包括方法内部声明的变量，也不包括从父类或父接口继承的那些字段。



##### 2.6.2 字段表

- fields表中的每个成员都必须是一个fields_info结构的数据项，用于表示当前类或接口中某个字段的完整描述。
- 一个字段的信息包括如下这些信息。这些信息中，各个修饰符都是布尔值，要么有，要么没有。
  - 作用域（public、private、protected修饰符）
  - 是实例变量还是类变量（static修饰符） 
  - 可变性（final）
  - 并发可见性（volatile修饰符，是否强制从主内存读写） 
  - 可否序列化（transient修饰符）
  - 字段数据类型（基本数据类型、对象、数组）
  - 字段名称

- 字段表结构

字段表作为一个表，同样有他自己的结构：

![](JVM.assets/178.png)



1. 字段表访问标志

我们知道，一个字段可以被各种关键字去修饰，比如：作用域修饰符（public、private、protected）、static修饰符、 final修饰符、volatile修饰符等等。因此，其可像类的访问标志那样，使用一些标志来标记字段。字段的访问标志有如下这些：

![](JVM.assets/179.png)



2. **字段名索引**：根据字段名索引的值，查询常量池中的指定索引项即可。



3. 描述符索引

描述符的作用是用来描述字段的数据类型、方法的参数列表（包括数量、类型以及顺序）和返回值。根据描述符规则，基本数据类型（byte，char，double，float，int，long，short，boolean）及代表无返回值的void类型都用一个大写字符来表示，而对象则用字符L加对象的全限定名来表示，如下所示：

![](JVM.assets/180.png)



4. 属性表集合

一个字段还可能拥有一些属性，用于存储更多的额外信息。比如初始化值、一些注释信息等。属性个数存放在attribute_count中，属性具体内容存放在attributes数组中。

以常量属性为例，结构为：

```java
ConstantValue_attribute{
	u2attribute_name_index;
	u4 attribute_length;
	u2 constantvalue_index;
}
```

说明：对于常量属性而言，attribute_length值恒为2。



#### 2.7 方法表集合

methods：指向常量池索引集合，它完整描述了每个方法的签名。

- 在字节码文件中，每一个method_info项都对应着一个类或者接口中的方法信息。比如方法的访问修饰符（public、 private或protected），方法的返回值类型以及方法的参数信息等。
- 如果这个方法不是抽象的或者不是native的，那么字节码中会体现出来。
- 一方面，methods表只描述当前类或接口中声明的方法，不包括从父类或父接口继承的方法。另一方面，methods表有可能会出现由编译器自动添加的方法，最典型的便是编译器产生的方法信息（比如：类（接口）初始化方法<clinit>()和实例初始化方法<init>()）。

**使用注意事项**：在Java语言中，要重载（Overload）一个方法，除了要与原方法具有相同的简单名称之外，还要求必须拥有一个与原方法不同的特征签名，特征签名就是一个方法中各个参数在常量池中的字段符号引用的集合，也就是因为返回值不会包含在特征签名之中，因此Java语言里无法仅仅依靠返回值的不同来对一个已有方法进行重载。但在class文件格式中，特征签名的范围更大一些，只要描述符不是完全一致的两个方法就可以共存。也就是说，如果两个方法有相同的名称和特征签名，但返回值不同，那么也是可以合法共存于同一个class文件中。

也就是说，尽管Java语法规范并不允许在一个类或者接口中声明多个方法签名相同的方法，但是和Java语法规范相反，字节码文件中却恰恰允许存放多个方法签名相同的方法，唯一的条件就是这些方法之间的返回值不能相同。



##### 2.7.1 方法计数器

methods_count的值表示当前class文件methods表的成员个数。使用两个字节来表示。 

methods表中每个成员都是一个method_info结构。



##### 2.7.2 方法表

- methods表中的每个成员都必须是一个method_info结构，用于表示当前类或接口中某个方法的完整描述。如果某个method_info结构的access_flags项既没有设置ACC_NATIVE标志也没有设置ACC_ABSTRACT标志，那么该结构中也应包含实现这个方法所用的Java虚拟机指令。
- method_info结构可以表示类和接口中定义的所有方法，包括实例方法、类方法、实例初始化方法和类或接口初始化方法
- 方法表的结构实际跟字段表是一样的，方法表结构如下：

![](JVM.assets/181.png)



1. 方法表访问标志：

跟字段表一样，方法表也有访问标志，而且他们的标志有部分相同，部分则不同，方法表的具体访问标志如下：

![](JVM.assets/182.png)



#### 2.8 属性表集合

方法表集合之后的属性表集合，指的是class文件所携带的辅助信息，比如该class文件的源文件的名称。以及任何带有 RetentionPolicy.CLASS或者RetentionPolicy.RUNTIME的注解。这类信息通常被用于Java虚拟机的验证和运行，以及 Java程序的调试，一般无须深入了解。

此外，字段表、方法表都可以有自己的属性表。用于描述某些场景专有的信息。

属性表集合的限制没有那么严格，不再要求各个属性表具有严格的顺序，并且只要不与已有的属性名重复，任何人实现的编译器都可以向属性表中写入自己定义的属性信息，但Java虚拟机运行时会忽略掉它不认识的属性。



##### 2.8.1 属性表

属性表的每个项的值必须是attribute_info结构。属性表的结构比较灵活，各种不同的属性只要满足以下结构即可。



1. 属性的通用格式

![](JVM.assets/183.png)

即只需说明属性的名称以及占用位数的长度即可，属性表具体的结构可以去自定义。



2. 属性类型

属性表实际上可以有很多类型，上面看到的code属性只是其中一种，Java8里面定义了23种属性。下面这些是虚拟机中预定义的属性：

![](JVM.assets/184.png)

![](JVM.assets/185.png)



##### 2.8.2 Code属性

在class文件中，属性表、方法表中都可以包含自己的属性表集合，用于描述某些场景的专有信息。

Java程序方法体中的代码经过javac编译器处理后，最终变为字节码指令存储在Code属性中。当然不是所有的方法都必须有这个属性（接口中的方法或抽象方法就不存在Code属性）

![](JVM.assets/187.png)



###### (1) LineNumberTable属性

用于描述Java源码的行号与字节码行号之间的对应关系，非运行时必需属性，会默认生成至 class文件中，可以使用javac的-g:none或-g:lines命令关闭或要求生成该项属性信息。

![](JVM.assets/188.png)

line_number_table是一组line_number_info类型数据的集合，其所包含的line_number_info 类型数据的数量为line_number_table_length。

line_number_info结构如下所示：

![](JVM.assets/189.png)



###### (2) LocalVariableTable属性

栈帧中局部变量表中的变量与Java源码中定义的变量之间的关系，用于描述方法中 **局部变量的作用域、存储位置和类型信息**。非运行时必需属性，默认不会生成至class文件中，可以使用javac的-g:none或-g:vars命令关闭或要求生成该项属性信息。

![](JVM.assets/190.png)

local_variable_table是一组local_variable_info类型数据的集合，其所包含的local_variable info类型数据的数量为local_variable_table_length。local_variable_info结构如下所示。

![](JVM.assets/191.png)



##### 2.8.3 SourceFile属性

 在字节码的最后，为SourceFile属性；

当程序抛出异常时，堆栈跟踪信息中显示了准确的源文件名和行号，让你能够快速定位问题。

它承载着源代码到字节码的映射关系，是调试和诊断的基石。

![](JVM.assets/192.png)

1. 属性名称索引 (attribute_name_index)：这是一个指向常量池的索引值，对应的常量必须是一个CONSTANT_Utf8_info类型的常量，其值为字符串"SourceFile"。
2. 属性长度 (attribute_length)：由于SourceFile属性只有一个sourcefile_index字段，所以属性长度固定为2字节
3. 源文件索引 (sourcefile_index)：这是SourceFile属性的核心字段，指向常量池中的一个CONSTANT_Utf8_info常量，该常量存储了源文件的名称（不包括路径信息）。









### 3. 使用javap指令解析Class文件

#### 3.1 解析字节码的作用

通过反编译生成的字节码文件，我们可以深入的了解java代码的工作机制。但是，自己分析类文件结构太麻烦了！除了使用第三方的jclasslib工具之外，oracle官方也提供了工具：javap。

javap是jdk自带的反解析工具。它的作用就是根据class字节码文件，反解析出当前类对应的code区（字节码指令）、局部变量表、异常表和代码行偏移量映射表、常量池等信息。

通过局部变量表，我们可以查看局部变量的作用域范围、所在槽位等信息，甚至可以看到槽位复用等信息。



#### 3.2 javac -g操作

解析字节码文件得到的信息中，有些信息（如局部变量表、指令和代码行偏移量映射表、常量池中方法的参数名称等等）需要在使用javac编译成class文件时，指定参数才能输出。

比如，你直接javac xx.java，就不会在生成对应的局部变量表等信息，如果你使用javac -g xx.java就可以生成所有相关信息了。如果你使用的eclipse或IDEA，则默认情况下，eclipse、IDEA在编译时会帮你生成局部变量表、指令和代码行偏移量映射表等信息的。



#### 3.3 javap的用法

> javap <options> <classes>
>

其中，classes就是你要反编译的class文件。

在命令行中直接输入 javap 或 javap -help 可以看到 javap 的 options 有如下选项：

![](JVM.assets/186.png)

- -help：输出此用法消息
- -version：版本信息，其实是当前javap所在jdk的版本信息，不是class在哪个jdk下生成的。



- -public：仅显示公共类和成员

- -protected：显示受保护的 / 公共类和成员

- -p    -private：显示所有类和成员

- -package：显示程序包/受保护的/公共类和成员（默认）

- -sysinfo：显示正在处理的类的系统信息（路径，大小，日期，MD5散列，源文件名）

- -constants：显示静态最终常量



- -s：输出内部类型签名
- -l：输出行号和本地变量表
- -c：对代码进行反汇编
- -v   -verbose：输出附加信息（包括行号、本地变量表，反汇编等详细信息）



- -classpath <path>：指定查找用户类文件的位置
- -cp <path>：指定查找用户类文件的位置
- -bootclasspath <path>：覆盖引导类文件的位置



##### 3.3.1 解析后的文件结构解读

![](JVM.assets/193.png)

![](JVM.assets/194.png)

![](JVM.assets/195.png)

![](JVM.assets/196.png)



## 三、字节码指令集与解析

### 1. 概述

- Java字节码对于虚拟机，就好像汇编语言对于计算机，属于基本执行指令。
- Java虚拟机的指令由一个字节长度的、代表着某种特定操作含义的数字（称为操作码，Opcode）以及跟随其后的零至多个代表此操作所需参数（称为操作数，Operands）而构成。由于Java虚拟机采用面向操作数栈而不是寄存器的结构，所以大多数的指令都不包含操作数，只有一个操作码。
- 由于限制了Java虚拟机操作码的长度为一个字节（即0～255），这意味着指令集的操作码总数不可能超过 256条。
- 官方文档：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html
- 熟悉虚拟机的指令对于动态字节码生成、反编译class文件、Class文件修补都有着非常重要的价值。因此，阅读字节码作为了解Java虚拟机的基础技能，需要熟练掌握常见指令。



#### 1.1 执行模型

如果不考虑异常处理的话，那么JaVa虚拟机的解释器可以使用下面这个伪代码当做最基本的执行模型来理解 

```java
do {
	自动计算PC寄存器的值加1;
	根据PC寄存器的指示位置，从字节码流中取出操作码;
	if(字节码存在操作数) 从字节码流中取出操作数;
	执行操作码所定义的操作;
}while(字节码长度>0);
```



#### 1.2 字节码与数据类型

在Java虚拟机的指令集中，大多数的指令都包含了其操作所对应的数据类型信息。例如，iload指令用于从局部变量表中加载int型的数据到操作数栈中，而fload指令加载的则是float类型的数据。

1. 对于大部分与数据类型相关的字节码指令，它们的操作码助记符中都有特殊的字符来表明专门为哪种数据类型服务：

- i代表对int类型的数据操作，
- l代表long
- s代表short
- b代表byte
- c代表char
- f代表float
- d代表double



2. 也有一些指令的助记符中没有明确地指明操作类型的字母，如arraylength指令，它没有代表数据类型的特殊字符，但操作数永远只能是一个数组类型的对象。



3. 还有另外一些指令，如无条件跳转指令goto则是与数据类型无关的。



大部分的指令都没有支持整数类型byte、char和short，甚至没有任何指令支持boolean类型。编译器会在编译期或运行期将byte和short类型的数据带符号扩展（Sign-Extend）为相应的int类型数据，将boolean和char类型数据零位扩展（Zero-Extend）为相应的int类型数据。与之类似，在处理boolean、byte、short和char类型的数组时，也会转换为使用对应的int类型的字节码指令来处理。因此，大多数对于boolean、byte、short和char类型数据的操作，实际上都是使用相应的int类型作为运算类型。

```java
byte b1 = 12;
short s1 = 10;
int i = b1 + s1;
```



#### 1.3 指令分类

由于完全介绍和学习这些指令需要花费大量时间。为了让大家能够更快地熟悉和了解这些基本指令，这里将JVM中的字节码指令集按用途大致分成9类。

- 加载与存储
- 指令算术指令
- 类型转换指令
- 对象的创建与访问指令
- 方法调用与返回指令
- 操作数栈管理指令
- 比较控制指令
- 异常处理指令
- 同步控制指令



在做值相关操作时：

- 一个指令，可以从局部变量表、常量池、堆中对象、方法调用、系统调用中等取得数据，这些数据（可能是值，可能是对象的引用）被压入操作数栈。
- 一个指令，也可以从操作数栈中取出一到多个值（pop多次），完成赋值、加减乘除、方法传参、系统调用等等操作。



### 2. 加载与存储指令

**作用**：加载和存储指令用于将数据从栈帧的局部变量表和操作数栈之间来回传递。

**常用指令**：

1. **局部变量压栈指令**：将一个局部变量加载到操作数栈，xload、xload_<n>（其中x为i、l、f、d、 a，n为0到3）
2. **常量入栈指令**：将一个常量加载到操作数栈：bipush、sipush、ldc、ldc_W、ldc2_w、 aconst_null、iconst_ml、iconst_ <i>、lconst_ <l>、fconst_ <f>、dconst_<d>
3. **出栈装入局部变量表指令**：将一个数值从操作数栈存储到局部变量表：xstore、xstore_<n>（其中x为i、1、f、d、a，n为0到3）；xastore（其中x为i、l、f、d、a、b、c、s）
4. 扩充局部变量表的访问索引的指令：wide。

上面所列举的指令助记符中，有一部分是以尖括号结尾的（例如iload_ <n>）。这些指令助记符实际上代表了一组指令（例如iload_<n>代表了iload_0、iload_1、iload_2和iload_3这几个指令）。这几组指令都是某个带有一个操作数的通用指令（例如iload）的特殊形式，**对于这若干组特殊指令来说，它们表面上没有操作数，不需要进行取操作数的动作，但操作数都隐含在指令中**。

比如：

- iload_0：将局部变量表中索引为0位置上的数据压入操作数栈中。(原因：节省空间，只占一个字节)
- iload0：将局部变量表中索引为0位置上的数据压入操作数栈中。(占据3个字节)
- iload4：将局部变量表中索引为4位置上的数据压入操作数栈中。(原因：不能无限制的制造各种各样的下划线)

除此之外，它们的语义与原生的通用指令完全一致（例如iload_0的语义与操作数为0时的iload指令语义完全一致）。在尖括号之间的字母指定了指令隐含操作数的数据类型，<n>代表非负的整数，<i>代表是int类型数据，<l>代表long类型，<f>代表float类型，<d>代表double类型。

操作byte、char、short和boolean类型数据时，经常用int类型的指令来表示。



#### 2.1 局部变量压栈指令

局部变量压栈指令将给定的局部变量表中的数据压入操作数栈。

这类指令大体可以分为：

- xload_<n>（x为i、l、f、d、a，n为0到3) 
- xload (x为i、l、f、d、a)

说明：在这里，x的取值表示数据类型。

指令xload_n表示将第n个局部变量压入操作数栈，比如iload_1、fload_0、aload_0等指令。其中aload_n表示将个对象引用压栈。

指令xload通过指定参数的形式，把局部变量压入操作数栈，当使用这个命令时，表示局部变量的数量可能超过了4个，比如指令iload、fload等。

```java
//1.局部变量压栈指令
public void load(int num, Object obj,long count,boolean flag,short[] arr) {
    System.out.println(num);
    System.out.println(obj);
    System.out.println(count);
    System.out.println(flag);
    System.out.println(arr);
}
```

![](JVM.assets/197.png)



#### 2.2 常量入栈指令

常量入栈指令的功能是将常数压入操作数栈，根据数据类型和入栈内容的不同，又可以分为const系列、push系列和ldc指令。



**指令const系列**：用于对特定的常量入栈，入栈的常量隐含在指令本身里。指令有：iconst_ <i>(i从-1到5)、lconst_ <l>(l从0到1)、fconst_ <f>(f从0到2)、dconst_ <d>(d从0到1)、aconst_null。

比如：

- iconst_m1将-1压入操作数栈；
- iconst_x（x为0到5）将x压入栈：
- lconst_0、lconst_1分别将长整数0和1压入栈；
- fconst_0、fconst_1、fconst_2分别将浮点数0、1、2压入栈；
- dconst_0和dconst_1分别将double型0和1压入栈。
- aconst_null将null压入操作数栈；

从指令的命名上不难找出规律，指令助记符的第一个字符总是喜欢表示数据类型，i表示整数，l表示长整数，f表示浮点数，d表示双精度浮点，习惯上用a表示对象引用。如果指令隐含操作的参数，会以下划线形式给出。



**指令push系列**：主要包括bipush和sipush。它们的区别在于接收数据类型的不同，bipush接收8位整数作为参数，sipush接收16位整数，它们都将参数压入栈。



**指令ldc系列**：如果以上指令都不能满足需求，那么可以使用万能的ldc指令，它可以接收一个8位的参数，该参数指向常量池中的int、float或者String的索引，将指定的内容压入堆栈。

类似的还有ldc_w，它接收两个8位参数，能支持的索引范围大于ldc。

如果要压入的元素是long或者double类型的，则使用ldc2_w指令，使用方式都是类似的。

![](JVM.assets/198.png)



#### 2.3 出栈装入局部变量表指令

出栈装入局部变量表指令用于将操作数栈中栈顶元素弹出后，装入局部变量表的指定位置，用于给局部变量赋值。

这类指令主要以store的形式存在，比如xstore（x为i、l、f、d、a）、xstore_n（x为i、l、f、d、a，n为 0至3）。

- 其中，指令istore_n将从操作数栈中弹出一个整数，并把它赋值给局部变量索引n位置。
- 指令xstore由于没有隐含参数信息，故需要提供一个byte类型的参数类指定目标局部变量表的位置。

**说明**：一般说来，类似像store这样的命令需要带一个参数，用来指明将弹出的元素放在局部变量表的第几个位置。但是，为了尽可能压缩指令大小，使用专门的istore1指令表示将弹出的元素放置在局部变量表第1个位置。类似的还有
istore_e、istore_2、istore3，它们分别表示从操作数栈顶弹出一个元素，存放在局部变量表第0、2、3个位置。

由于局部变量表前几个位置总是非常常用，因此这种做法虽然增加了指令数量，但是可以大大压缩生成的字节码的体积。如果局部变量表很大，需要存储的槽位大于3，那么可以使用istore指令，外加一个参数，用来表示需要存放的槽位位置。

![](JVM.assets/199.png)





### 3. 算术指令

1. **作用**：算术指令用于对两个操作数栈上的值进行某种特定运算，并把结果重新压入操作数栈。



2. **分类**：大体上算术指令可以分为两种：对整型数据进行运算的指令与对浮点类型数据进行运算的指令。



3. **byte、short、char和boolean类型说明**:
   在每一大类中，都有针对Java虚拟机具体数据类型的专用算术指令。但没有直接支持byte、short、char和boolean类型的算术指令，对于这些数据的运算，都使用int类型的指令来处理。此外，在处理boolean、byte、short和char类型的数组时，也会转换为使用对应的int类型的字节码指令来处理。

![](JVM.assets/200.png)



4. **运算时的溢出**
   数据运算可能会导致溢出，例如两个很大的正整数相加，结果可能是一个负数。其实Java虚拟机规范并无明确规定过整型数据溢出的具体结果，仅规定了在处理整型数据时，只有除法指令以及求余指令中当出现除数为0时会导致虚拟机抛出异常ArithmeticException。



5. 运算模式

- **向最接近数舍入模式(四舍五入)**：JVM要求在进行浮点数计算时，所有的运算结果都必须舍入到适当的精度，非精确结果必须舍入为可被表示的最接近的精确值，如果有两种可表示的形式与该值一样接近，将优先选择最低有效位为零的；
- **向零舍入模式(取整)**：将浮点数转换为整数时，采用该模式，该模式将在目标数值类型中选择一个最接近但是不大于原值的数字作为最精确的舍入结果；





6. NaN值使用
   当一个操作产生溢出时，将会使用有符号的无穷大表示，如果某个操作结果没有明确的数学定义的话，将会使用NaN值来表示。而且所有使用NaN值作为操作数的算术操作，结果都会返回NaN；



#### 3.1 所有算术指令

所有的算术指令包括：

加法指令：iadd、ladd、fadd、dadd

减法指令：isub、1sub、fsub、dsub

乘法指令：imul、lmul、fmul、dmul

除法指令：idiv、1div、fdiv、ddiv

求余指令：irem、1rem、frem、drem //remainder:余数

取反指令：ineg、lneg、fneg、dneg //negation：取反

自增指令：iinc

位运算指令，又可分为：

- 位移指令：ishl、ishr、iushr、lshl、lshr、lushr
- 按位或指令：ior、lor
- 按位与指令：iand、land
- 按位异或指令：ixor、lxor

比较指令：dcmpg、dcmpl、fcmpg、fcmpl、lcmp





#### 3.2 比较指令说明

- 比较指令的作用是比较栈顶两个元素的大小，并将比较结果入栈。
- 比较指令有：dcmpg、dcmpl、fcmpg、fcmpl、lcmp。
  - 与前面讲解的指令类似，首字符d表示double类型，f表示float，l表示long。
- 对于double和float类型的数字，由于NaN的存在，各有两个版本的比较指令。以float为例，有fcmpg和fcmpl两个指令，它们的区别在于在数字比较时，若遇到NaN值，处理结果不同。
- 指令dcmpl和dcmpg也是类似的，根据其命名可以推测其含义，在此不再赘述。
- 指令lcmp针对long型整数，由于long型整数没有NaN值，故无需准备两套指令。

**举例：**
指令fcmpg和fcmpl都从栈中弹出两个操作数，并将它们做比较，设栈顶的元素为v2，栈顶顺位第2位的元素为v1，若 v1=v2，则压入0；若v1>v2则压入1；若v1<v2则压入-1。

两个指令的不同之处在于，如果遇到NaN值，fcmpg会压入1,而fcmpl会压入-1。





### 4. 类型转换指令

**类型转换指令说明**

1. 类型转换指令可以将两种不同的数值类型进行相互转换。
2. 这些转换操作一般用于实现用户代码中的显式类型转换操作，或者用来处理字节码指令集中数据类型相关指令无法与数据类型一一对应的问题。



#### 4.1 宽化类型转换

**转换规则**

Java虚拟机直接支持以下数值的宽化类型转换（widening numeric conversion，小范围类型向大范围类型的安全转换）。也就是说，并不需要指令执行，包括：

- 从int类型到long、float或者double类型。对应的指令为：i2l、i2f、i2d
- 从long类型到float、double类型。对应的指令为：12f、12d
- 从float类型到double类型。对应的指令为：f2d

简化为：int-->long-->float-->double



**精度损失问题**

1. 宽化类型转换是不会因为超过目标类型最大值而丢失信息的，例如，从int转换到long，或者从int转换到 double，都不会丢失任何信息，转换前后的值是精确相等的。
2. 从int、long类型数值转换到float，或者long类型数值转换到double时，将可能发生精度丢失一一可能丢失掉几个最低有效位上的值，转换后的浮点数值是根据IEEE754最接近舍入模式所得到的正确整数值。

尽管宽化类型转换实际上是可能发生精度丢失的，但是这种转换永远不会导致Java虚拟机抛出运行时异常。

```java
//举例：精度损失的问题
@Test
public void upCast2(){
    int i = 123123123;
    float f = i;
    System.out.println(f);//123123120

    long l = 123123123123L;
    l = 123123123123123123L;
    double d = l;
    System.out.println(d);//123123123123123120

}
```



**补充说明**

从byte、char和short类型到int类型的宽化类型转换实际上是不存在的。对于byte类型转为int，虚拟机并没有做实质性的转化处理，只是简单地通过操作数栈交换了两个数据。而将byte转为long时，使用的是i2l，可以看到在内部 byte在这里已经等同于int类型处理，类似的还有short类型，这种处理方式有两个特点：

1. 一方面可以减少实际的数据类型，如果为short和byte都准备一套指令，那么指令的数量就会大增，而虚拟机目前的设计上，只愿意使用一个字节表示指令，因此指令总数不能超过256个，为了节省指令资源，将short和byte当做 int处理也在情理之中。
2. 另一方面，由于局部变量表中的槽位固定为32位，无论是byte或者short存入局部变量表，都会占用32位空间。从这个角度说，也没有必要特意区分这几种数据类型。





#### 4.2 窄化类型转换

转换规则

Java虚拟机也直接支持以下窄化类型转换：

- 从int类型至byte、short或者char类型。对应的指令有：i2b、i2s、i2c
- 从long类型到int类型。对应的指令有：l2i
- 从float类型到int或者long类型。对应的指令有：f2i、f2l
- 从double类型到int、long或者float类型。对应的指令有：d2i、d2l、d2f



**精度损失问题**

窄化类型转换可能会导致转换结果具备不同的正负号、不同的数量级，因此，转换过程很可能会导致数值丢失精度。

尽管数据类型窄化转换可能会发生上限溢出、下限溢出和精度丢失等情况，但是Java虚拟机规范中明确规定数值类型的窄化转换指令永远不可能导致虚拟机抛出运行时异常



**补充说明**

1. 当将一个浮点值窄化转换为整数类型T（T限于int或long类型之一）的时候，将遵循以下转换规则：

- 如果浮点值是NaN那转换结果就是int或long类型的0。
- 如果浮点值不是无穷大的话，浮点值使用IEEE754的向零舍入模式取整，获得整数值v，如果v在目标类型T（ int或long）的表示范围之内，那转换结果就是v。否则，将根据v的符号，转换为T所能表示的最大或者最小正数



2. 当将一个double类型窄化转换为float类型时，将遵循以下转换规则：通过向最接近数舍入模式舍入一个可以使用float类型表示的数字。最后结果根据下面这3条规则判断：

- 如果转换结果的绝对值太小而无法使用float来表示，将返回float类型的正负零。
- 如果转换结果的绝对值太大而无法使用float来表示，将返回float类型的正负无穷大。
- 对于double类型的NaN值将按规定转换为float类型的NaN值。

```java
//测试NaN,无穷大的情况
@Test
public void downCast5(){
    double d1 = Double.NaN; //0.0 / 0.0
    int i = (int)d1;
    System.out.println(d1);//NaN
    System.out.println(i);//0

    double d2 = Double.POSITIVE_INFINITY;
    long l = (long)d2;
    int j = (int)d2;
    System.out.println(l);// 9223372036854775807
    System.out.println(Long.MAX_VALUE);// 9223372036854775807
    System.out.println(j);// 2147483647
    System.out.println(Integer.MAX_VALUE);// 2147483647

    float f = (float)d2;
    System.out.println(f);//Infinity

    float f1 = (float)d1;
    System.out.println(f1);//NaN
}
```



### 5. 对象的创建与访问指令

Java是面向对象的程序设计语言，虚拟机平台从字节码层面就对面向对象做了深层次的支持。有一系列指令专门用于对象操作，可进一步细分为创建指令、字段访问指令、数组操作指令、类型检查指令。



#### 5.1 创建指令

虽然类实例和数组都是对象，但Java虚拟机对类实例和数组的创建与操作使用了不同的字节码指令：

1. 创建类实例的指令：

- 创建类实例的指令：new
  - 它接收一个操作数，为指向常量池的索引，表示要创建的类型，执行完成后，将对象的引用压入栈。



2. 创建数组的指令：

- 创建数组的指令：newarray、anewarray、multianewarray
  - newarray：创建基本类型数组
  - anewarray：创建引用类型数组
  - multianewarray：创建多维数组

上述创建指令可以用于创建对象或者数组，由于对象和数组在Java中的广泛使用，这些指令的使用频率也非常高。





#### 5.2 字段访问指令

对象创建后，就可以通过对象访问指令获取对象实例或数组实例中的字段或者数组元素。

- 访问类字段（static字段，或者称为类变量）的指令：getstatic、putstatic
- 访问类实例字段（非static字段，或者称为实例变量）的指令：getfield、putfield



举例：以getstatic指令为例，它含有一个操作数，为指向常量池的Fieldref索引，它的作用就是获取Fieldref指定的对象或者值，并将其压入操作数栈。 

```java
public void sayHello() {
	System.out.println("hello");
}
```

对应的字节码指令：

```
0 getstatic #8 <java/lang/System.out>
3 ldc #9 <hello>
5 invokevirtual #10 <java/io/PrintStream.println>
8 return 
```

图示：

![](JVM.assets/201.png)

![](JVM.assets/202.png)

![](JVM.assets/203.png)





#### 5.3 数组操作指令

数组操作指令主要有：xastore和xaload指令。具体为：

- 把一个数组元素加载到操作数栈的指令：baload、caload、saload、iaload、laload、faload、 daload、aaload
- 将一个操作数栈的值存储到数组元素中的指令：bastore、castore、sastore、iastore、lastore、 fastore、dastore、aastore

即：

![](JVM.assets/204.png)

- 取数组长度的指令：arraylength
  - 该指令弹出栈顶的数组元素，获取数组的长度，将长度压入栈。



说明

- 指令xaload表示将数组的元素压栈，比如saload、caload分别表示压入short数组和char数组。指令xaload在执行时，要求操作数中栈顶元素为数组索引i，栈顶顺位第2个元素为数组引用a，该指令会弹出栈顶这两个元素，并将a[i]重新压入堆栈。
- xastore则专门针对数组操作，以iastore为例，它用于给一个int数组的给定索引赋值。在iastore执行前，操作数栈顶需要以此准备3个元素：值、索引、数组引用，iastore会弹出这3个值，并将值赋给数组中指定索引的位置。

![](JVM.assets/205.png)



#### 5.4 类型检查指令

检查类实例或数组类型的指令：instanceof、checkcast。

- 指令checkcast用于检查类型强制转换是否可以进行。如果可以进行，那么checkcast指令不会改变操作数栈，否则它会抛出classCastException异常。
- 指令instanceof用来判断给定对象是否是某一个类的实例，它会将判断结果压入操作数栈。



### 6. 方法调用与返回指令

#### 6.1 方法调用指令

1. **invokevirtual**指令用于调用对象的实例方法，根据对象的实际类型进行分派（虚方法分派），支持多态。这也是Java语言中最常见的方法分派方式。
2. **invokeinterface**指令用于调用接口方法，它会在运行时搜索由特定对象所实现的这个接口方法，并找出适合的方法进行调用。
3. **invokespecial**指令用于调用一些需要特殊处理的实例方法，包括实例初始化方法（构造器）、私有方法和父类方法。这些方法都是静态类型绑定的，不会在调用时进行动态派发。
4. **invokestatic**指令用于调用命名类中的类方法（static方法）。这是静态绑定的。
5. **invokedynamic**：调用动态绑定的方法，这个是JDK1.7后新加入的指令。用于在运行时动态解析出调用点限定符所引用的方法，并执行该方法。前面4条调用指令的分派逻辑都固化在java虚拟机内部，而 invokedynamic指令的分派逻辑是由用户所设定的引导方法决定的。

```java
/**
 * @author shkstart
 * @create 2020-09-08 9:35
 *
 * 指令5：方法调用与返回指令
 */
public class MethodInvokeReturnTest {

    //方法调用指令:invokespecial:静态分派
    public void invoke1(){
        //情况1：类实例构造器方法：<init>()
        Date date = new Date();

        Thread t1 = new Thread();
        //情况2：父类的方法
        super.toString();
        //情况3：私有方法
        methodPrivate();
    }

    private void methodPrivate(){

    }
    //方法调用指令:invokestatic:静态分派
    public void invoke2(){
        methodStatic();
    }
    public static void methodStatic(){

    }

    //方法调用指令:invokeinterface
    public void invoke3(){
        Thread t1 = new Thread();
        ((Runnable)t1).run();

        Comparable<Integer> com = null;
        com.compareTo(123);
    }

    //方法调用指令:invokeVirtual:动态分派
    public void invoke4(){
        System.out.println("hello");

        Thread t1 = null;
        t1.run();
    }
    //方法的返回指令
    public int returnInt(){
        int i = 500;
        return i;
    }

    public double returnDouble(){
        return 0.0;
    }

    public String returnString(){
        return "hello,world";
    }

    public int[] returnArr(){
        return null;
    }
    public float returnFloat(){
        int i = 10;
        return i;
    }

    public byte returnByte(){
        return 0;
    }

    public void methodReturn(){
        int i = returnByte();
    }

}
```



#### 6.2 方法返回指令

方法调用结束前，需要进行返回。方法返回指令是根据返回值的类型区分的。

- 包括ireturn（当返回值是boolean、byte、char、short和int类型时使用）、lreturn、freturn、 dreturn和areturn
- 另外还有一条return指令供声明为void的方法、实例初始化方法以及类和接口的类初始化方法使用。

![](JVM.assets/206.png)



**举例**：

通过ireturn指令，将当前函数操作数栈的顶层元素弹出，并将这个元素压入调用者函数的操作数栈中（因为调用者非常关心函数的返回值），所有在当前函数操作数栈中的其他元素都会被丢弃。

如果当前返回的是synchronized方法，那么还会执行一个隐含的monitorexit指令，退出临界区。

最后，会丢弃当前方法的整个帧，恢复调用者的帧，并将控制权转交给调用者。

![](JVM.assets/207.png)

```java
public int methodReturn() {
    int i = 500;
    int j = 200;
    int k = 50;
    
    return (i + j) / k;
}
```





### 7. 操作数栈管理指令

如同操作一个普通数据结构中的堆栈那样，JVM提供的操作数栈管理指令，可以用于直接操作操作数栈的指令。

这类指令包括如下内容：

- 将一个或两个元素从栈顶弹出，并且直接废弃：pop，pop2；
- 复制栈顶一个或两个数值并将复制值或双份的复制值重新压入栈顶：dup，dup2，dup_x1，dup2_x1，dup_x2，dup2_x2；
- 将栈最顶端的两个Slot数值位置交换：swap。Java虚拟机没有提供交换两个64位数据类型（ long、double）数值的指令。
- 指令nop，是一个非常特殊的指令，它的字节码为0x00。和汇编语言中的nop一样，它表示什么都不做。这条指令一般可用于调试、占位等。

这些指令属于通用型，对栈的压入或者弹出无需指明数据类型。



**说明**：

- 不带_x的指令是复制栈顶数据并压入栈顶。包括两个指令，dup和dup2。dup的系数代表要复制的Slot个数。
  - dup开头的指令用于复制1个slot的数据。例如1个int或1个reference类型数据
  - dup2开头的指令用于复制2个slot的数据。例如1个long，或2个int，或1个int+1个 float类型数据
- 带 _x 的指令是复制栈顶数据并插入栈顶以下的某个位置。共有4个指令，dup_x1，dup2_x1，dup_x2，dup2_x2。对于带 _x 的复制插入指令，只要将指令的dup和x的系数相加，结果即为需要插入的位置。因此
  - dup_x1插入位置：1+1=2，即栈顶2个Slot下面
  - dup_x2插入位置：1+2=3，即栈顶3个Slot下面
  - dup2_x1插入位置：2+1=3，即栈顶3个Slot下面
  - dup2_x2插入位置：2+2=4，即栈顶4个Slot下面
- pop：将栈顶的1个Slot数值出栈。例如1个short类型数值
- pop2：将栈顶的2个Slot数值出栈。例如1个double类型数值，或者2个int类型数值



### 8. 控制转移指令

程序流程离不开条件控制，为了支持条件跳转，虚拟机提供了大量字节码指令。



#### 8.1 条件跳转指令

条件跳转指令通常和比较指令结合使用。在条件跳转指令执行前，一般可以先用比较指令进行栈顶元素的准备，然后进行条件跳转。

条件跳转指令有：ifeq，iflt，ifle，ifne，ifgt，ifge，ifnull，ifnonnull。这些指令都接收两个字节的操作数，用于计算跳转的位置（16位符号整数作为当前位置的offset）。

它们的统一含义为：弹出栈顶元素，测试它是否满足某一条件，如果满足条件，则跳转到给定位置。



具体说明：

![](JVM.assets/208.png)



注意：

1. 与前面运算规则一致：

- 对于boolean、byte、char、short类型的条件分支比较操作，都是使用int类型的比较指令完成。
- 对于long、float、double类型的条件分支比较操作，则会先执行相应类型的比较运算指令，运算指令会返回一个整型值到操作数栈中，随后再执行int类型的条件分支比较操作来完成整个分支跳转。

2. 由于各类型的比较最终都会转为int类型的比较操作，所以Java虚拟机提供的int类型的条件分支指令是最为丰富和强大的。





#### 8.2 比较条件跳转指令

比较条件跳转指令类似于比较指令和条件跳转指令的结合体，它将比较和跳转两个步骤合二为一。

这类指令有：if_icmpeq、if_icmpne、if_icmplt、if_icmpgt、if_icmple、if_icmpge、if_acmpeq和if_acmpne。其中指令助记符加上“if_”后，以字符“i”开头的指令针对int型整数操作（也包括short和byte类型），以字符“a”开头的指令表示对象引用的比较。



具体说明：

![](JVM.assets/209.png)

这些指令都接收两个字节的操作数作为参数，用于计算跳转的位置。同时在执行指令时，栈顶需要准备两个元素进行比较。指令执行完成后，栈顶的这两个元素被清空，且没有任何数据入栈。如果预设条件成立，则执行跳转，否则，继续执行下一条语句。



#### 8.3 多条件分支跳转指令

多条件分支跳转指令是专为switch-case语句设计的，主要有tableswitch和lookupswitch。

![](JVM.assets/210.png)

从助记符上看，两者都是switch语句的实现，它们的区别：

- tableswitch要求多个条件分支值是连续的，它内部只存放起始值和终止值，以及若干个跳转偏移量，通过给定的操作数index，可以立即定位到跳转偏移量位置，因此效率比较高。
- 指令lookupswitch内部存放着各个离散的case-offset对，每次执行都要搜索全部的case-offset对，找到匹配的 case值，并根据对应的offset计算跳转地址，因此效率较低。



指令tableswitch的示意图如下图所示。由于tableswitch的case值是连续的，因此只需要记录最低值和最高值，以及每-项对应的offset偏移量，根据给定的index值通过简单的计算即可直接定位到offset。

![](JVM.assets/211.png)

指令lookupswitch处理的是离散的case值，但是出于效率考虑，将case-offset对按照case值大小排序，给定index时，需要查找与index相等的case，获得其offset，如果找不到则跳转到default。指令lookupswitch如下图所示。

![](JVM.assets/212.png)





#### 8.4 无条件跳转指令

目前主要的无条件跳转指令为goto。指令goto接收两个字节的操作数，共同组成一个带符号的整数，用于指定指令的偏移量指令执行的目的就是跳转到偏移量给定的位置处。

如果指令偏移量太大，超过双字节的带符号整数的范围，则可以使用指令goto_w，它和goto有相同的作用，但是它接收4个字节的操作数，可以表示更大的地址范围。

指令jsr、jsr_w、ret虽然也是无条件跳转的，但主要用于try-finally语句，且已经被虚拟机逐渐废弃，故不在这里介绍这两个指令。

![](JVM.assets/213.png)



### 9. 异常处理指令

#### 9.1 抛出异常指令

1. athrow指令

   在Java程序中显示抛出异常的操作（throw语句）都是由athrow指令来实现。

   除了使用throw语句显示抛出异常情况之外，**JVM规范还规定了许多运行时异常会在其他Java虚拟机指令检测到异常状况时自动抛出**。例如，在之前介绍的整数运算时，当除数为零时，虚拟机会在idiv或ldiv指令中抛出ArithmeticException异常。

**注意**：正常情况下，操作数栈的压入弹出都是一条条指令完成的。唯一的例外情况是在抛异常时，Java虚拟机会清除操作数栈上的所有内容，而后将异常实例压入调用者操作数栈上。



#### 9.2 异常处理与异常表

1. **处理异常**：在Java虚拟机中，处理异常（catch语句）不是由字节码指令来实现的（早期使用jsr、ret指令），而是采用异常表来完成的。

2. **异常表**：如果一个方法定义了一个try-catch或者try-finally的异常处理，就会创建一个异常表。它包含了每个异常处理或者 finally块的信息。异常表保存了每个异常处理信息。比如：

- 起始位置
- 结束位置
- 程序计数器记录的代码处理的偏移地址
- 被捕获的异常类在常量池中的索引

当一个异常被抛出时，JVM会在当前的方法里寻找一个匹配的处理，如果没有找到，这个方法会强制结束并弹出当前栈帧，并且异常会重新抛给上层调用的方法（在调用方法栈帧）。如果在所有栈帧弹出前仍然没有找到合适的异常处理，这个线程将终止。如果这个异常在最后一个非守护线程里抛出，将会导致JVM自己终止，比如这个线程是个main线程。

不管什么时候抛出异常，如果异常处理最终匹配了所有异常类型，代码就会继续执行。在这种情况下，如果方法结束后没有抛出异常，仍然执行finally块，在return前，它直接跳到finally块来完成目标



### 10. 同步控制指令

java虚拟机支持两种同步结构：方法级的同步和方法内部一段指令序列的同步，这两种同步都是使用monitor来支持的。



#### 10.1 方法级的同步

方法级的同步：是隐式的，即无须通过字节码指令来控制，它实现在方法调用和返回操作之中。虚拟机可以从方法常量池的方法表结构中的ACC_SYNCHRONIZED访问标志得知一个方法是否声明为同步方法；

当调用方法时，调用指令将会检查方法的ACCSYNCHRONIZED访问标志是否设置。

- 如果设置了，执行线程将先持有同步锁，然后执行方法。最后在方法完成（无论是正常完成还是非正常完成）时释放同步锁。
- 在方法执行期间，执行线程持有了同步锁，其他任何线程都无法再获得同一个锁。
- 如果一个同步方法执行期间抛出了异常，并且在方法内部无法处理此异常，那这个同步方法所持有的锁将在异常抛到同步方法之外时自动释放。



举例：

```java
private int i = 0;
public synchronized void add(){
    i++;
}
```

对应的字节码：

```
00 aload_0
01 dup
02 getfield #2 <com/atguigu/java1/SynchronizedTest.i>
05 iconst_1 
06 iadd
07 putfield #2 <com/atguigu/java1/SynchronizedTest.i>
10 return 
```

**说明**：这段代码和普通的无同步操作的代码没有什么不同，没有使用monitorenter和monitorexit进行同步区控制。这是因为，对于同步方法而言，当虚拟机通过方法的访问标示符判断是一个同步方法时，会自动在方法调用前进行加锁，当同步方法执行完毕后，不管方法是正常结束还是有异常抛出，均会由虚拟机释放这个锁。因此，对于同步方法而言，monitorenter和 monitorexit指令是隐式存在的，并未直接出现在字节码中。



#### 10.2 方法内指定序列的同步

同步一段指令集序列：通常是由java中的synchronized语句块来表示的。jvm的指令集有monitorenter和 monitorexit两条指令来支持synchronized关键字的语义。

当一个线程进入同步代码块时，它使用monitorenter指令请求进入。如果当前对象的监视器计数器为0，则它会被准许进入，若为1，则判断持有当前监视器的线程是否为自己，如果是，则进入，否则进行等待，直到对象的监视器计数器为0，才会被允许进入同步块。



当线程退出同步块时，需要使用monitorexit声明退出。在Java虚拟机中，任何对象都有一个监视器与之相关联，用来判断对象是否被锁定，当监视器被持有后，对象处于锁定状态。

指令monitorenter和monitorexit在执行时，都需要在操作数栈顶压入对象，之后monitorenter和monitorexit的锁定和释放都是针对这个对象的监视器进行的。

下图展示了监视器如何保护临界区代码不同时被多个线程访问，只有当线程4离开临界区后，线程1、2、3才有可能进入。

![](JVM.assets/214.png)



编译器必须确保无论方法通过何种方式完成，方法中调用过的每条monitorenter指令都必须执行其对应的monitorexit指令，而无论这个方法是正常结束还是异常结束。

为了保证在方法异常完成时monitorenter和monitorexit指令依然可以正确配对执行，编译器会自动产生一个异常处理器，这个异常处理器声明可处理所有的异常，它的目的就是用来执行monitorexit指令





## 四、类的加载过程详解

### 1. 概述

在Java中数据类型分为基本数据类型和引用数据类型。基本数据类型由虚拟机预先定义，引用数据类型则需要进行类的加载。

按照Java虚拟机规范，从class文件到加载到内存中的类，到类卸载出内存为止，它的整个生命周期包括如下7个阶段：

![](JVM.assets/215.png)

其中，验证、准备、解析3个部分统称为链接（Linking）



从程序中类的使用过程看：

![](JVM.assets/216.png)



### 2. 过程一：Loading(加载)阶段

#### 2.1 加载完成的操作

所谓加载，简而言之就是将Java类的字节码文件加载到机器内存中，并在内存中构建出Java类的原型一一类模板对象。所谓类模板对象，其实就是Java类在JVM内存中的一个快照，JVM将从字节码文件中解析出的常量池、类字段、类方法等信息存储到类模板中，这样JVM在运行期便能通过类模板而获取Java类中的任意信息，能够对Java类的成员变量进行遍历，也能进行Java方法的调用。

反射的机制即基于这一基础。如果JVM没有将Java类的声明信息存储起来，则JVM在运行期也无法反射。



**加载完成的操作**

加载阶段，简言之，查找并加载类的二进制数据，生成class的实例。

在加载类时，Java虚拟机必须完成以下3件事情：

- 通过类的全名，获取类的二进制数据流。
- 解析类的二进制数据流为方法区内的数据结构（Java类模型）
- 创建java.lang.Class类的实例，表示该类型。作为方法区这个类的各种数据的访问入口



#### 2.2 二进制流的获取方式

对于类的二进制数据流，虚拟机可以通过多种途径产生或获得。（只要所读取的字节码符合JVM规范即可）

- 虚拟机可能通过文件系统读入一个class后缀的文件（最常见）
- 读入jar、zip等归档数据包，提取类文件。
- 事先存放在数据库中的类的二进制数据
- 使用类似于HTTP之类的协议通过网络进行加载
- 在运行时生成一段class的二进制信息等

在获取到类的二进制信息后，Java虚拟机就会处理这些数据，并最终转为一个java.lang.Class的实例。

如果输入数据不是classFile的结构，则会抛出classFormatError。



#### 2.3 类模型与Class实例的位置

**类模型的位置**：加载的类在JVM中创建相应的类结构，类结构会存储在方法区(JDK1.8之前：永久代：JDK1.8及之后：元空间)。 

**Class实例的位置**：类将.class文件加载至元空间后，会在堆中创建一个Java.lang.Class对象，用来封装类位于方法区内的数据结构，该 class对象是在加载类的过程中创建的，每个类都对应有一个Class类型的对象。

图示：

![](JVM.assets/217.png)

外部可以通过访问代表Order类的Class对象来获取Order的类数据结构。

**再说明**：Class类的构造方法是私有的，只有JVM能够创建。

java.lang.Class实例是访问类型元数据的接口，也是实现反射的关键数据、入口。通过Class类提供的接口，可以获得目标类所关联的.class文件中具体的数据结构：方法、字段等信息。



#### 2.4 数组类的加载

创建数组类的情况稍微有些特殊，因为数组类本身并不是由类加载器负责创建，而是由JVM在运行时根据需要而直接创建的但数组的元素类型仍然需要依靠类加载器去创建。创建数组类（下述简称A）的过程：

1. 如果数组的元素类型是引用类型，那么就遵循定义的加载过程递归加载和创建数组A的元素类型; 
2. JVM使用指定的元素类型和数组维度来创建新的数组类。

如果数组的元素类型是引用类型，数组类的可访问性就由元素类型的可访问性决定。否则数组类的可访问性将被缺省定义为 public.





### 3. 过程二：Linking(链接)阶段

#### 3.1 环节1：链接阶段之Werification(验证)

当类加载到系统后，就开始链接操作，验证是链接操作的第一步。

它的目的是保证加载的字节码是合法、合理并符合规范的。

验证的步骤比较复杂，实际要验证的项目也很繁多，大体上Java虚拟机需要做以下检查，如图所示。

![](JVM.assets/218.png)

**整体说明**：验证的内容则涵盖了类数据信息的格式验证、语义检查、字节码验证，以及符号引用验证等。

1. 其中格式验证会和加载阶段一起执行。验证通过之后，类加载器才会成功将类的二进制数据信息加载到方法区中。
2. 格式验证之外的验证操作将会在方法区中进行。

链接阶段的验证虽然拖慢了加载速度，但是它避免了在字节码运行时还需要进行各种检查。（磨刀不误砍柴工）



具体说明：

1. 格式验证：是否以魔数0xCAFEBABE开头，主版本和副版本号是否在当前Java虚拟机的支持范围内，数据中每一个项是否都拥有正确的长度等。



2. Java虚拟机会进行字节码的语义检查，但凡在语义上不符合规范的，虚拟机也不会给予验证通过。比如：

- 是否所有的类都有父类的存在（在Java里，除了object外，其他类都应该有父类）
- 是否一些被定义为final的方法或者类被重写或继承了
- 非抽象类是否实现了所有抽象方法或者接口方法
- 是否存在不兼容的方法（比如方法的签名除了返回值不同，其他都一样，这种方法会让虚拟机无从下手调度；abstract情况下的方法，就不能是final的了）



3. Java虚拟机还会进行字节码验证，字节码验证也是验证过程中最为复杂的一个过程。它试图通过对字节码流的分析，判断字节码是否可以被正确地执行。比如：

- 在字节码的执行过程中，是否会跳转到一条不存在的指令
- 函数的调用是否传递了正确类型的参数
- 变量的赋值是不是给了正确的数据类型等

栈映射帧（StackMapTable)就是在这个阶段，用于检测在特定的字节码处，其局部变量表和操作数栈是否有着正确的数据类型。但遗憾的是，100%准确地判断一段字节码是否可以被安全执行是无法实现的，因此，该过程只是尽可能地检查出可以预知的明显的问题。如果在这个阶段无法通过检查，虚拟机也不会正确装载这个类。但是，如果通过了这个阶段的检查，也不能说明这个类是完全没有问题的。

在前面3次检查中，已经排除了文件格式错误、语义错误以及字节码的不正确性。但是依然不能确保类是没有问题的。



4. 校验器还将进行符号引用的验证。Class文件在其常量池会通过字符串记录自己将要使用的其他类或者方法。因此，在验证阶段，虚拟机就会检查这些类或者方法确实是存在的，并且当前类有权限访问这些数据，如果一个需要使用类无法在系统中找到，则会抛出NoClassDefFoundError，如果一个方法无法被找到，则会抛出NoSuchMethodError。此阶段在解析环节才会执行。



#### 3.2 环节2：链接阶段之Preparation(准备)

准备阶段（Preparation），简言之，为类的静态变量分配内存，并将其初始化为默认值。

当一个类验证通过时，虚拟机就会进入准备阶段。在这个阶段，虚拟机就会为这个类分配相应的内存空间，并设置默认初始值。 Java虚拟机为各类型变量默认的初始值如表所示。

![](JVM.assets/219.png)

注意：Java并不支持boolean类型，对于boolean类型，内部实现是int，由于int的默认值是0,故对应的，boolean的默认值就是false。



注意：

1. 这里不包含基本数据类型的字段用static final修饰的情况，因为final在编译的时候就会分配了，准备阶段会显式赋值。
2. 注意这里不会为实例变量分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中。 
3. 在这个阶段并不会像初始化阶段中那样会有初始化或者代码被执行。





#### 3.3 环节3：链接阶段之Resolution(解析)

在准备阶段完成后，就进入了解析阶段。

解析阶段（Resolution)，简言之，将类、接口、字段和方法的符号引用转为直接引用。

**具体描述**：符号引用就是一些字面量的引用，和虚拟机的内部数据结构和和内存布局无关。比较容易理解的就是在class类文件中，通过常量池进行了大量的符号引用。但是在程序实际运行时，只有符号引用是不够的，比如当如下println()方法被调用时，系统需要明确知道该方法的位置。

**举例**：输出操作System.out.println()对应的字节码：
invokevirtual #24 <java/io/PrintStream.println>

![](JVM.assets/220.png)

以方法为例，Java虚拟机为每个类都准备了一张方法表，将其所有的方法都列在表中，当需要调用一个类的方法的时候，只要知道这个方法在方法表中的偏移量就可以直接调用该方法。通过解析操作，符号引用就可以转变为目标方法在类中方法表中的位置，从而使得方法被成功调用。 



**小结**：所谓解析就是将符号引用转为直接引用，也就是得到类、字段、方法在内存中的指针或者偏移量。因此，可以说，如果直接引用存在，那么可以肯定系统中存在该类、方法或者字段。但只存在符号引用，不能确定系统中一定存在该结构。

不过Java虚拟机规范并没有明确要求解析阶段一定要按照顺序执行。在HotSpotVM中，加载、验证、准备和初始化会按照顺序有条不紊地执行，但链接阶段中的解析操作往往会伴随着JVM在执行完初始化之后再执行。



**字符串的复习**：最后，再来看一下CONSTANT_String的解析。由于字符串在程序开发中有着重要的作用，因此，读者有必要了解一下 String在Java虚拟机中的处理。当在Java代码中直接使用字符串常量时，就会在类中出现CONSTANT_String，它表示字符串常量，并且会引用一个CONSTANT_UTF8的常量项。在Java虚拟机内部运行中的常量池中，会维护一张字符串拘留表（intern），它会保存所有出现过的字符串常量，并且没有重复项。只要以CONSTANT_String形式出现的字符串也都会在这张表中。使用String.intern()方法可以得到一个字符串在拘留表中的引用，因为该表中没有重复项，所以任何字面相同的字符串的string.intern()方法返回总是相等的。





### 4. 过程三：Initialization(初始化)阶段

初始化阶段，简言之，为类的静态变量赋予正确的初始值。 



**具体描述**

类的初始化是类装载的最后一个阶段。如果前面的步骤都没有问题，那么表示类可以顺利装载到系统中。此时，类才会开始执行Java字节码。（即：到了初始化阶段，才真正开始执行类中定义的Java程序代码）

初始化阶段的重要工作是执行类的初始化方法：<clinit>()方法。

- 该方法仅能由Java编译器生成并由JVM调用，程序开发者无法自定义一个同名的方法，更无法直接在Java程序中调用该方法，虽然该方法也是由字节码指令所组成。
- 它是由类静态成员的赋值语句以及static语句块合并产生的。 



**说明**

1. 在加载一个类之前，虚拟机总是会试图加载该类的父类，因此父类的<clinit>总是在子类<clinit>之前被调用。也就是说，父类的static块优先级高于子类。
2. Java编译器并不会为所有的类都产生<clinit>()初始化方法。哪些类在编译为字节码后，字节码文件中将不会包含<clinit>()方法？
   - 一个类中并没有声明任何的类变量，也没有静态代码块时
   - 一个类中声明类变量，但是没有明确使用类变量的初始化语句以及静态代码块来执行初始化操作时
   - 一个类中包含static final修饰的基本数据类型的字段，这些类字段初始化语句采用编译时常量表达式

```java
/**
 * @author shkstart
 * @create 2020-09-14 18:49
 * 哪些场景下，java编译器就不会生成<clinit>()方法
 */
public class InitializationTest1 {
    //场景1：对应非静态的字段，不管是否进行了显式赋值，都不会生成<clinit>()方法
    public int num = 1;
    //场景2：静态的字段，没有显式的赋值，不会生成<clinit>()方法
    public static int num1;
    //场景3：比如对于声明为static final的基本数据类型的字段，不管是否进行了显式赋值，都不会生成<clinit>()方法
    public static final int num2 = 1;
}
```



#### 4.1 static与final的搭配问题

```java
/**
 * @author shkstart
 * @create 2020-09-14 18:55
 *
 * 说明：使用static + final修饰的字段的显式赋值的操作，到底是在哪个阶段进行的赋值？
 * 情况1：在链接阶段的准备环节赋值
 * 情况2：在初始化阶段<clinit>()中赋值
 *
 * 结论：
 * 在链接阶段的准备环节赋值的情况：
 * 1. 对于基本数据类型的字段来说，如果使用static final修饰，则显式赋值(直接赋值常量，而非调用方法）通常是在链接阶段的准备环节进行
 * 2. 对于String来说，如果使用字面量的方式赋值，使用static final修饰的话，则显式赋值通常是在链接阶段的准备环节进行
 *
 * 在初始化阶段<clinit>()中赋值的情况：
 * 排除上述的在准备环节赋值的情况之外的情况。
 *
 * 最终结论：使用static + final修饰，且显示赋值中不涉及到方法或构造器调用的基本数据类型或String类型的显式赋值，是在链接阶段的准备环节进行。
 */
public class InitializationTest2 {
    public static int a = 1;//在初始化阶段<clinit>()中赋值
    public static final int INT_CONSTANT = 10;//在链接阶段的准备环节赋值

    public static final Integer INTEGER_CONSTANT1 = Integer.valueOf(100);//在初始化阶段<clinit>()中赋值
    public static Integer INTEGER_CONSTANT2 = Integer.valueOf(1000);//在初始化阶段<clinit>()中赋值

    public static final String s0 = "helloworld0";//在链接阶段的准备环节赋值
    public static final String s1 = new String("helloworld1");//在初始化阶段<clinit>()中赋值

    public static String s2 = "helloworld2";

    public static final int NUM1 = new Random().nextInt(10);//在初始化阶段<clinit>()中赋值
}

```



#### 4.2 < clinit >()的线程安全性

对于<clinit>()方法的调用，也就是类的初始化，虚拟机会在内部确保其多线程环境中的安全性。

虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的<clinit>()方法，其他线程都需要阻塞等待，直到活动线程执行<clinit>()方法完毕。

正是因为函数<clinit>()带锁线程安全的，因此，如果在一个类的<clinit>()方法中有耗时很长的操作，就可能造成多个线程阻塞，引发死锁。并且这种死锁是很难发现的，因为看起来它们并没有可用的锁信息。

如果之前的线程成功加载了类，则等在队列中的线程就没有机会再执行<clinit>()方法了。那么，当需要使用这个类时，虚拟机会直接返回给它已经准备好的信息。





#### 4.3 类的初始化情况：主动使用vs被动使用

Java程序对类的使用分为两种：主动使用和被动使用。

如果针对代码，设置参数-XX:+TraceClassLoading，可以追踪类的加载信息并打印出来。



**主动使用**：class只有在必须要首次使用的时候才会被装载，Java虚拟机不会无条件地装载class类型。Java虚拟机规定，一个类或接口在初次使用前，必须要进行初始化。这里指的“使用”，是指主动使用，主动使用只有下列几种情况：（即：如果出现如下的情况，则会对类进行初始化操作。而初始化操作之前的加载、验证、准备已经完成。

1. 当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。 
2. 当调用类的静态方法时，即当使用了字节码invokestatic指令。
3. 当使用类、接口的静态字段时（final修饰特殊考虑），比如，使用getstatic或者putstatic指令。（对应访问变量、赋值变量操作）
4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：Class.forName("com.atguigu.java.Test")
5. 当初始化子类时，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
6. 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化。 
7. 当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。
8. 当初次调用MethodHandle实例时，初始化该MethodHandle指向的方法所在的类。（涉及解析 REF_getStatic、REF_putStatic、REF_invokeStatic方法句柄对应的类）



**针对5，补充说明：**

当Java虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口。

- 在初始化一个类时，并不会先初始化它所实现的接口
- 在初始化一个接口时，并不会先初始化它的父接口

因此，一个父接口并不会因为它的子接口或者实现类的初始化而初始化。只有当程序首次使用特定接口的静态字段时，才会导致该接口的初始化。



**针对7，说明：**

JVM启动的时候通过引导类加载器加载一个初始类。这个类在调用public static void main(String[])方法之前被链接和初始化。这个方法的执行将依次导致所需的类的加载，链接和初始化。





```java
/**
 * @author shkstart
 * @create 2020-09-14 16:37
 *
 * 测试类的主动使用：意味着会调用类的<clinit>()，即执行了类的初始化阶段
 *
 * 1. 当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。
 * 2. 当调用类的静态方法时，即当使用了字节码invokestatic指令。
 */
public class ActiveUse1 {
    public static void main(String[] args) {
        Order order = new Order();
    }

    //序列化的过程：
    @Test
    public void test1() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("order.dat"));

            oos.writeObject(new Order());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //反序列化的过程：（验证）
    @Test
    public void test2() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("order.dat"));

            Order order = (Order) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test3(){
        Order.method();
    }

}

class Order implements Serializable{
    static {
        System.out.println("Order类的初始化过程");
    }

    public static void method(){
        System.out.println("Order method()....");
    }
}

```

```java
/**
 * @author shkstart
 * @create 2020-09-14 16:49
 *
 * 3. 当使用类、接口的静态字段时(final修饰特殊考虑)，比如，使用getstatic或者putstatic指令。（对应访问变量、赋值变量操作）
 *
 */
public class ActiveUse2 {
    @Test
    public void test1(){
//        System.out.println(User.num);
//        System.out.println(User.num1);
        System.out.println(User.num2);
    }

    @Test
    public void test2(){
//        System.out.println(CompareA.NUM1);
        System.out.println(CompareA.NUM2);
    }
}

class User{
    static{
        System.out.println("User类的初始化过程");
    }

    public static int num = 1;
    public static final int num1 = 1;
    public static final int num2 = new Random().nextInt(10);

}

interface CompareA{
    public static final Thread t = new Thread(){
        {
            System.out.println("CompareA的初始化");
        }
    };

    public static final int NUM1 = 1;
    public static final int NUM2 = new Random().nextInt(10);

}
```

```java
/**
 * @author shkstart
 * @create 2020-09-14 17:00
 * <p>
 * 4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：Class.forName("com.atguigu.java.Test")
 * 5. 当初始化子类时，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
 * 6. 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化。
 * 7. 当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。
 * 8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。
 * （涉及解析REF_getStatic、REF_putStatic、REF_invokeStatic方法句柄对应的类）
 * <p>
 * <p>
 * 针对5，补充说明：
 * 当Java虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口。
 * >在初始化一个类时，并不会先初始化它所实现的接口
 * >在初始化一个接口时，并不会先初始化它的父接口
 * 因此，一个父接口并不会因为它的子接口或者实现类的初始化而初始化。只有当程序首次使用特定接口的静态字段时，
 * 才会导致该接口的初始化。
 */
public class ActiveUse3 {
    static{
        System.out.println("ActiveUse3的初始化过程");
    }
    @Test
    public void test1() {
        try {
            Class clazz = Class.forName("com.atguigu.java1.Order");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        System.out.println(Son.num);
    }

    @Test
    public void test3(){
        System.out.println(CompareC.NUM1);
    }

    @Test
    public void test4() {
        System.out.println(Son.num);
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}


class Father {
    static {
        System.out.println("Father类的初始化过程");
    }
}

class Son extends Father implements CompareB{
    static {
        System.out.println("Son类的初始化过程");
    }

    public static int num = 1;
}

interface CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareB的初始化");
        }
    };
    public default void method1(){
        System.out.println("你好！");
    }

}

interface CompareC extends CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareC的初始化");
        }
    };

    public static final int NUM1 = new Random().nextInt();
}

```



**被动使用**

除了以上的情况属于主动使用，其他的情况均属于被动使用。被动使用不会引起类的初始化。

也就是说：并不是在代码中出现的类，就一定会被加载或者初始化。如果不符合主动使用的条件，类就不会初始化。 

1. 当访问一个静态字段时，只有真正声明这个字段的类才会被初始化。
   - 当通过子类引用父类的静态变量，不会导致子类初始化
2. 通过数组定义类引用，不会触发此类的初始化
3. 引用常量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了。
4. 调用classLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。

```java
/**
 * @author shkstart
 * @create 2020-09-14 17:24
 *
 * 关于类的被动使用，即不会进行类的初始化操作，即不会调用<clinit>()
 *
 * 1. 当访问一个静态字段时，只有真正声明这个字段的类才会被初始化。
 *     > 当通过子类引用父类的静态变量，不会导致子类初始化
 * 2. 通过数组定义类引用，不会触发此类的初始化
 *
 * 说明：没有初始化的类，不意味着没有加载！
 */
public class PassiveUse1 {
    @Test
    public void test1(){
        System.out.println(Child.num);
    }

    @Test
    public void test2(){
        Parent[] parents = new Parent[10];
        System.out.println(parents.getClass());
        System.out.println(parents.getClass().getSuperclass());

        parents[0] = new Parent();
        parents[1] = new Parent();
    }
}

class Parent{
    static{
        System.out.println("Parent的初始化过程");
    }

    public static int num = 1;
}

class Child extends Parent{
    static{
        System.out.println("Child的初始化过程");
    }
}
```

```java
/**
 * @author shkstart
 * @create 2020-09-14 17:30
 *
 *  * 3. 引用常量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了。
 *  * 4. 调用ClassLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 */
public class PassiveUse2 {
    @Test
    public void test1(){
//        System.out.println(Person.NUM);
        System.out.println(Person.NUM1);
    }

    @Test
    public void test2(){
//        System.out.println(SerialA.ID);
        System.out.println(SerialA.ID1);
    }

    @Test
    public void test3(){
        try {
            Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.atguigu.java1.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class Person{
    static{
        System.out.println("Person类的初始化");
    }
    public static final int NUM = 1;//在链接过程的准备环节就被赋值为1了。
    public static final int NUM1 = new Random().nextInt(10);//此时的赋值操作需要在<clinit>()中执行
}

interface SerialA{
    public static final Thread t = new Thread() {
        {
            System.out.println("SerialA的初始化");
        }
    };

    int ID = 1;
    int ID1 = new Random().nextInt(10);//此时的赋值操作需要在<clinit>()中执行
}
```





### 5、过程四：类的Using(使用)

任何一个类型在使用之前都必须经历过完整的加载、链接和初始化3个类加载步骤。一旦一个类型成功经历过这3个步骤之后，便“万事俱备，只欠东风”，就等着开发者使用了。

开发人员可以在程序中访问和调用它的静态类成员信息（比如：静态字段、静态方法），或者使用new关键字为其创建对象实例。



### 6、过程五：类的Unloading(卸载)

1. 类、类的加载器、类的实例之间的引用关系

在类加载器的内部实现中，用一个Java集合来存放所加载类的引用。另一方面，一个class对象总是会引用它的类加载器，调用class对象的getClassLoader()方法，就能获得它的类加载器。由此可见，代表某个类的class实例与其类的加载器之间为双向关联关系。

一个类的实例总是引用代表这个类的class对象。在object类中定义了getClass()方法，这个方法返回代表对象所属类的Class对象的引用。此外，所有的Java类都有一个静态属性class，它引用代表这个类的class对象。



2. 类的生命周期

当Sample类被加载、链接和初始化后，它的生命周期就开始了。当代表Sample类的class对象不再被引用，即不可触及时，Class对象就会结束生命周期，Sample类在方法区内的数据也会被卸载，从而结束Sample类的生命周期。

一个类何时结束生命周期，取决于代表它的class对象何时结束生命周期。



3. 具体例子

![](JVM.assets/221.png)

loader1变量和obj变量间接应用代表Sample类的class对象，而objClass变量则直接引用它。

如果程序运行过程中，将上图左侧三个引用变量都置为null，此时Sample对象结束生命周期，MyClassLoader对象结束生命周期，代表Sample类的class对象也结束生命周期，Sample类在方法区内的二进制数据被卸载。

当再次有需要时，会检查Sample类的class对象是否存在，如果存在会直接使用，不再重新加载；如果不存在Sample类会被重新加载，在Java虚拟机的堆区会生成一个新的代表Sample类的class实例（可以通过哈希码查看是否是同一个实例)。



4. 类的卸载
   1. 启动类加载器加载的类型在整个运行期间是不可能被卸载的（jvm和jls规范）
   2. 被系统类加载器和扩展类加载器加载的类型在运行期间不太可能被卸载，因为系统类加载器实例或者扩展类的实例基本上在整个运行期间总能直接或者间接的访问的到，其达到unreachable的可能性极小。
   3. 被开发者自定义的类加载器实例加载的类型只有在很简单的上下文环境中才能被卸载，而且一般还要借助于强制调用虚拟机的垃圾收集功能才可以做到。可以预想，稍微复杂点的应用场景中（比如：很多时候用户在开发自定义类加载器实例的时候采用缓存的策略以提高系统性能），被加载的类型在运行期间也是几乎不太可能被卸载的（至少卸载的时间是不确定的）。

综合以上三点，一个已经加载的类型被卸载的几率很小至少被卸载的时间是不确定的。同时我们可以看的出来，开发者在开发代码时候，不应该对虚拟机的类型卸载做任何假设的前提下，来实现系统中的特定功能。





#### 6.1 方法区的垃圾回收

方法区的垃圾收集主要回收两部分内容：**常量池中废弃的常量**和**不再使用的类型**

HotSpot虚拟机对常量池的回收策略是很明确的，只要常量池中的常量没有被任何地方引用，就可以被回收。

判定一个常量是否“废弃”还是相对简单，而要判定一个类型是否属于“不再被使用的类”的条件就比较苛刻了。需要同时满足下面三个条件：

- 该类所有的实例都已经被回收。也就是Java堆中不存在该类及其任何派生子类的实例。
- 加载该类的类加载器已经被回收。这个条件除非是经过精心设计的可替换类加载器的场景，如OSGi、JSP的重加载等，否则通常是很难达成的。
- 该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。

Java虚拟机被允许对满足上述三个条件的无用类进行回收，这里说的仅仅是“被允许”，而并不是和对象一样，没有引用了就必然会回收。





## 五、再谈类的加载器

### 1. 概述

类加载器是JVM执行类加载机制的前提。

**ClassLoader的作用**：ClassLoader是Java的核心组件，所有的class都是由classLoader进行加载的，ClassLoader负责通过各种方式将 Class信息的二进制数据流读入JVM内部，转换为一个与目标类对应的java.lang.Class对象实例。然后交给Java虚拟机进行链接、初始化等操作。因此，ClassLoader在整个装载阶段，只能影响到类的加载，而无法通过classLoader去改变类的链接和初始化行为。至于它是否可以运行，则由Execution Engine决定。

![](JVM.assets/222.png)

类加载器最早出现在Java1.0版本中，那个时候只是单纯地为了满足JavaApplet应用而被研发出来。但如今类加载器却在OSGi、字节码加解密领域大放异彩。这主要归功于Java虚拟机的设计者们当初在设计类加载器的时候，并没有考虑将它绑定在JVM内部，这样做的好处就是能够更加灵活和动态地执行类加载操作。



#### 1.1 类加载的分类

类的加载分类：显式加载vs隐式加载

class文件的显式加载与隐式加载的方式是指JVM加载class文件到内存的方式。

- 显式加载指的是在代码中通过调用ClassLoader加载class对象，如直接使用Class.forName(name)或 this.getClass().getClassLoader().loadclass()加载class对象。
- 隐式加载则是不直接在代码中调用ClassLoader的方法加载class对象，而是通过虚拟机自动加载到内存中，如在加载某个类的class文件时，该类的class文件中引用了另外一个类的对象，此时额外引用的类将通过JVM自动加载到内存中。

在日常开发以上两种方式一般会混合使用。

```java
public class UserTest {
    public static void main(String[] args) {
        User user = new User(); //隐式加载

        try {
            Class clazz = Class.forName("com.atguigu.java.User"); //显式加载
            ClassLoader.getSystemClassLoader().loadClass("com.atguigu.java.User");//显式加载
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
```





#### 1.2 类加载器的必要性

一般情况下，Java开发人员并不需要在程序中显式地使用类加载器，但是了解类加载器的加载机制却显得至关重要。从以下几个方面说：

- 避免在开发中遇到java.lang.ClassNotFoundException异常或java.lang.NoClassDefFoundError异常时，手足无措。只有了解类加载器的加载机制才能够在出现异常的时候快速地根据错误异常日志定位问题和解决问题
- 需要支持类的动态加载或需要对编译后的字节码文件进行加解密操作时，就需要与类加载器打交道了。
- 开发人员可以在程序中编写自定义类加载器来重新定义类的加载规则，以便实现一些自定义的处理逻辑。



#### 1.3 命名空间

1. 何为类的唯一性？

对于任意一个类，都需要由加载它的类加载器和这个类本身一同确认其在Java虚拟机中的唯一性。每一个类加载器，都拥有一个独立的类名称空间：**比较两个类是否相等，只有在这两个类是由同一个类加载器加载的前提下才有意义**。否则，即使这两个类源自同一个class文件，被同一个虚拟机加载，只要加载他们的类加载器不同，那这两个类就必定不相等。



2. 命名空间

- 每个类加载器都有自己的命名空间，命名空间由该加载器及所有的父加载器所加载的类组成
- 在同一命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类
- 在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类

在大型应用中，我们往往借助这一特性，来运行同一个类的不同版本。





#### 1.4 类加载机制的基本特征

通常类加载机制有三个基本特征：

- 双亲委派模型。但不是所有类加载都遵守这个模型，有的时候，启动类加载器所加载的类型，是可能要加载用户代码的，比如JDK内部的ServiceProvider/ServiceLoader机制，用户可以在标准API框架上，提供自己的实现，JDK也需要提供些默认的参考实现。例如，Java中JNDI、JDBC、文件系统、Cipher等很多方面，都是利用的这种机制，这种情况就不会用双亲委派模型去加载，而是利用所谓的上下文加载器。
- 可见性，子类加载器可以访问父加载器加载的类型，但是反过来是不允许的。不然，因为缺少必要的隔离，我们就没有办法利用类加载器去实现容器的逻辑。
- 单一性，由于父加载器的类型对于子加载器是可见的，所以父加载器中加载过的类型，就不会在子加载器中重复加载。但是注意，类加载器“邻居”间，同一类型仍然可以被加载多次，因为互相并不可见。





### 2. 类的加载器分类

JVM支持两种类型的类加载器，分别为引导类加载器（BootstrapClassLoader）和自定义类加载器（ User-Defined ClassLoader）

从概念上来讲，自定义类加载器一般指的是程序中由开发人员自定义的一类类加载器，但是Java虚拟机规范却没有这么定义，而是将所有派生于抽象类ClassLoader的类加载器都划分为自定义类加载器。无论类加载器的类型如何划分，在程序中我们最常见的类加载器结构主要是如下情况：

![](JVM.assets/223.png)

- 除了顶层的启动类加载器外，其余的类加载器都应当有自己的“父类”加载器。
- 不同类加载器看似是继承（Inheritance）关系，实际上是包含关系。在下层加载器中，包含着上层加载器的引用。

```java
class ClassLoader {
    
    ClassLoader parent;//父类加载器
    
    public ClassLoader(ClassLoader parent) {
        this.parent = parent;
    }
    
}

class ParentClassLoader extends ClassLoader {
    public ParentClassLoader(ClassLoader parent) {
        super(parent);
    }
}

class ChildClassLoader extends ClassLoader {
	public ChildClassLoader(ClassLoader parent) {
        super(parent);
    }
}
```



#### 2.1 引导类加载器

启动类加载器（引导类加载器，Bootstrap ClassLoader）

> 使用-XX:+TraceClassLoading参数得到。

- 这个类加载使用C/C++语言实现的，嵌套在JVM内部。
- 它用来加载Java的核心库（JAVA_HOME/jre/lib/rt.jar或sun.boot.class.path路径下的内容）。用于提供 JVM自身需要的类。
- 并不继承自java.lang.ClassLoader，没有父加载器。
- 出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类
- 加载扩展类和应用程序类加载器，并指定为他们的父类加载器。



启动类加载器使用c++编写的？Yes！

- C/C++：指针函数&函数指针、C++支持多继承、更加高效
- Java：由C++演变而来，(C++)--版，单继承



#### 2.2 扩展类加载器

扩展类加载器（ExtensionClassLoader）

- Java语言编写，由sun.misc.Launcher$ExtClassLoader实现。
- 继承于ClassLoader类
- 父类加载器为启动类加载器
- 从java.ext.dirs系统属性所指定的目录中加载类库，或从JDK的安装目录的jre/lib/ext子目录下加载类库。如果用户创建的JAR放在此目录下，也会自动由扩展类加载器加载。

![](JVM.assets/224.png)



#### 2.3 系统类加载器

应用程序类加载器（系统类加载器，AppClassLoader）

- java语言编写，由sun.misc.Launcher$AppClassLoader实现
- 继承于ClassLoader类
- 父类加载器为扩展类加载器
- 它负责加载环境变量classpath或系统属性java.class.path指定路径下的类库
- 应用程序中的类加载器默认是系统类加载器。
- 它是用户自定义类加载器的默认父加载器
- 通过classLoader的getSystemClassLoader()方法可以获取到该类加载器





#### 2.4 用户自定义类加载器

- 在Java的日常应用程序开发中，类的加载几乎是由上述3种类加载器相互配合执行的。在必要时，我们还可以自定义类加载器，来定制类的加载方式。
- 体现Java语言强大生命力和巨大魅力的关键因素之一便是，Java开发者可以自定义类加载器来实现类库的动态加载，加载源可以是本地的JAR包，也可以是网络上的远程资源。
- 通过类加载器可以实现非常绝妙的插件机制，这方面的实际应用案例举不胜举。例如，著名的OSGI组件框架，再如 Eclipse的插件机制。类加载器为应用程序提供了一种动态增加新功能的机制，这种机制无须重新打包发布应用程序就能实现。
- 同时，自定义加载器能够实现应用隔离，例如Tomcat，Spring等中间件和组件框架都在内部实现了自定义的加载器，并通过自定义加载器隔离不同的组件模块。这种机制比C/C++程序要好太多，想不修改C/C++程序就能为其新增功能，几乎是不可能的，仅仅一个兼容性便能阻挡住所有美好的设想。
- 自定义类加载器通常需要继承于classLoader。



### 3. 测试不同的类加载器

每个Class对象都会包含一个定义它的ClassLoader的一个引用。

获取ClassLoader的途径

- 获得当前类的classLoader：clazz.getClassLoader()
- 获得当前线程上下文的classLoader：Thread.currentThread().getContextClassLoader()
- 获得系统的classLoader：ClassLoader.getSystemClassLoader()

**说明**：站在程序的角度看，引导类加载器与另外两种类加载器（系统类加载器和扩展类加载器）并不是同一个层次意义上的加载器，引导类加载器是使用c++语言编写而成的，而另外两种类加载器则是使用Java语言编写而成的。由于引导类加载器压根儿就不是一个Java类，因此在Java程序中只能打印出空值。

数组类的Class对象，不是由类加载器去创建的，而是在Java运行期JVM根据需要自动创建的。对于数组类的类加载器来说，是通过Class.getClassLoader()返回的，与数组当中元素类型的类加载器是一样的；如果数组当中的元素类型是基本数据类型，数组类是没有类加载器的。

```java
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        //获取系统该类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        //获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@1540e19d
        //试图获取引导类加载器：失败
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null

        //###########################
        try {
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);
            //自定义的类默认使用系统类加载器
            ClassLoader classLoader1 = Class.forName("com.atguigu.java.ClassLoaderTest1").getClassLoader();
            System.out.println(classLoader1);

            //关于数组类型的加载:使用的类的加载器与数组元素的类的加载器相同
            String[] arrStr = new String[10];
            System.out.println(arrStr.getClass().getClassLoader());//null:表示使用的是引导类加载器

            ClassLoaderTest1[] arr1 = new ClassLoaderTest1[10];
            System.out.println(arr1.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2

            int[] arr2 = new int[10];
            System.out.println(arr2.getClass().getClassLoader());//null:不需要类的加载器


            System.out.println(Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

```



### 4. ClassLoader源码解析

ClassLoader与现有类加载器的关系：

![](JVM.assets/225.png)

除了以上虚拟机自带的加载器外，用户还可以定制自己的类加载器。Java提供了抽象类java.lang.ClassLoader，所有用户自定义的类加载器都应该继承classLoader类。





#### 4.1 ClassLoader的主要方法

抽象类classLoader的主要方法：（内部没有抽象方法）

- public final ClassLoader getParent()

返回该类加载器的超类加载器



- public Class<?> loadclass(String name) throws ClassNotFoundException

加载名称为name的类，返回结果为java.lang.Class类的实例。如果找不到类，则返回ClassNotFoundException 异常。该方法中的逻辑就是双亲委派模式的实现。



- protected Class<?> findclass(String name) throws ClassNotFoundException

查找二进制名称为name的类，返回结果为java.lang.Class类的实例。这是一个受保护的方法，JVM鼓励我们重写此方法，需要自定义加载器遵循双亲委托机制，该方法会在检查完父类加载器之后被loadClass()方法调用。

在JDK1.2之前，在自定义类加载时，总会去继承ClassLoader类并重写loadClass方法，从而实现自定义的类加载类。但是在JDK1.2之后已不再建议用户去覆盖loadClass()方法，而是建议把自定义的类加载逻辑写在 findClass()方法中，从前面的分析可知，findClass()方法是在loadClass()方法中被调用的，当loadClass()方法中父加载器加载失败后，则会调用自已的findClass()方法来完成类加载，这样就可以保证自定义的类加载器也符合双亲委托模式。

需要注意的是ClassLoader类中并没有实现findClass()方法的具体代码逻辑，取而代之的是抛出ClassNotFoundException异常，同时应该知道的是findClass方法通常是和defineClass方法一起使用的。一般情况下，在自定义类加载器时，会直接覆盖ClassLoader的findClass()方法并编写加载规则，取得要加载类的字节码后转换成流，然后调用defineClass()方法生成类的class对象。



- protected final Class<?> defineClass(String name,byte[] b,int off,int len)

根据给定的字节数组b转换为class的实例，off和len参数表示实际class信息在byte数组中的位置和长度，其中 byte数组b是classLoader从外部获取的。这是受保护的方法，只有在自定义classLoader子类中可以使用。

defineClass()方法是用来将byte字节流解析成JVM能够识别的class对象（ClassLoader中已实现该方法逻辑），通过这个方法不仅能够通过class文件实例化class对象，也可以通过其他方式实例化class对象，如通过网络接收一个类的字节码，然后转换为byte字节流创建对应的class对象。

defineClass()方法通常与findClass()方法一起使用，一般情况下，在自定义类加载器时，会直接覆盖ClassLoader的findClass()方法并编写加载规则，取得要加载类的字节码后转换成流，然后调用defineClass()方法生成类的class对象



- protected final void resolveClass(Class<?> c)

链接指定的一个Java类。使用该方法可以使用类的class对象创建完成的同时也被解析。前面我们说链接阶段主要是对字节码进行验证，为类变量分配内存并设置初始值同时将字节码文件中的符号引用转换为直接引用。 



- protected final Class<?> findLoadedclass(String name）

查找名称为name的已经被加载过的类，返回结果为java.lang.Class类的实例。这个方法是final方法，无法被修改。



- private final ClassLoader parent;

它也是一个ClassLoader的实例，这个字段所表示的ClassLoader也称为这个ClassLoader的双亲。在类加载的过程中,ClassLoader可能会将某些请求交予自已的双亲处理。



#### 4.2 SecureClassLoader与URLClassLoader

接着SecureClassLoader扩展了ClassLoader，新增了几个与使用相关的代码源（对代码源的位置及其证书的验证）和权限定义类验证（主要指对class源码的访问权限）的方法，一般我们不会直接跟这个类打交道，更多是与它的子类 URLClassLoader有所关联。

前面说过，ClassLoader是一个抽象类，很多方法是空的没有实现，比如findClass()、findResource()等。而 URLClassLoader这个实现类为这些方法提供了具体的实现。并新增了URLClassPath类协助取得class字节码流等功能。在编写自定义类加载器时，如果没有太过于复杂的需求，可以直接继承URLClassLoader类，这样就可以避免自已去编写findClass()方法及其获取字节码流的方式，使自定义类加载器编写更加简洁。



#### 4.3 ExtClassLoader与AppClassLoader

了解完URLClassLoader后接着看看剩余的两个类加载器，即拓展类加载器ExtClassLoader和系统类加载器 AppClassLoader，这两个类都继承自URLClassLoader，是sun.misc.Launcher的静态内部类。sun.misc.Launcher主要被系统用于启动主应用程序，ExtClassLoader和AppClassLoader都是由sun.misc.Launcher创建的。

我们发现ExtClassLoader并没有重写loadClass()方法，这足矣说明其遵循双亲委派模式，而AppClassLoader重载了loadClass()方法，但最终调用的还是父类loadClass()方法，因此依然遵守双亲委派模式。



#### 4.4 Class.forName() 与 ClassLoader.loadClass()

- Class.forName()：是一个静态方法，最常用的是Class.forName(String className)；根据传入的类的全限定名返回一个class对象。**该方法在将Class文件加载到内存的同时，会执行类的初始化**。如： Class.forName("com.atguigu.java.HelloWorld");
- ClassLoader.loadClass()：这是一个实例方法，需要一个ClassLoader对象来调用该方法。**该方法将Class文件加载到内存时，并不会执行类的初始化，直到这个类第一次使用时才进行初始化**。该方法因为需要得到一个ClassLoader对象，所以可以根据需要指定使用哪个类加载器.如：ClassLoader cl=.....；cl.loadClass("com.atguigu.java.HelloWorld");





### 5. 双亲委派模型

#### 5.1 定义和本质

类加载器用来把类加载到Java虚拟机中。从JDK1.2版本开始，类的加载过程采用双亲委派机制，这种机制能更好地保证Java平台的安全。 

**定义**：如果一个类加载器在接到加载类的请求时，它首先不会自已尝试去加载这个类，而是把这个请求任务委托给父类加载器去完成，依次递归，如果父类加载器可以完成类加载任务，就成功返回。只有父类加载器无法完成此加载任务时，才自己去加载。 

**本质**：规定了类加载的顺序是：引导类加载器先加载，若加载不到，由扩展类加载器加载，若还加载不到，才会由系统类加载器或自定义的类加载器进行加载。

![](JVM.assets/226.png)

![](JVM.assets/227.png)





#### 5.2 优势与劣势

1. 双亲委派机制优势

- 避免类的重复加载，确保一个类的全局唯一性。
  Java类随着它的类加载器一起具备了一种带有优先级的层次关系，通过这种层级关可以避免类的重复加载，当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次。
- 保护程序安全，防止核心API被随意篡改。



2. 代码支持

双亲委派机制在java.lang.ClassLoader.loadClass(String，boolean)接口中体现。该接口的逻辑如下：

- (1)先在当前加载器的缓存中查找有无目标类，如果有，直接返回。
- (2)判断当前加载器的父加载器是否为空，如果不为空，则调用parent.loadclass(name, false)接口进行加载。
- (3)反之，如果当前加载器的父类加载器为空，则调用findBootstrapClassOrNull(name)接口，让引导类加载器进行加载。
- (4)如果通过以上3条路径都没能成功加载，则调用findClass(name)接口进行加载。该接口最终会调用java.lang.ClassLoader接口的defineClass系列的native接口加载目标Java类。

双亲委派的模型就隐藏在这第2和第3步中。



3. 举例

假设当前加载的是java.lang.Object这个类，很显然，该类属于JDK中核心得不能再核心的一个类，因此一定只能由引导类加载器进行加载。当JVM准备加载java.ang.Object时，JVM默认会使用系统类加载器去加载，按照上面4步加载的逻辑，在第1步从系统类的缓存中肯定查找不到该类，于是进入第2步。由于从系统类加载器的父加载器是扩展类加载器，于是扩展类加载器继续从第1步开始重复。由于扩展类加载器的缓存中也一定查找不到该类，因此进入第2步。扩展类的父加载器是null,因此系统调用findclass(String)，最终通过引导类加载器进行加载。



4. 思考

如果在自定义的类加载器中重写java.lang.ClassLoader.loadClass(String)或
java.lang.ClassLoader.loadClass(String, boolean)方法，抹去其中的双亲委派机制，仅保留上面这4步中的第 1步与第4步，那么是不是就能够加载核心类库了呢？

这也不行！因为JDK还为核心类库提供了一层保护机制。不管是自定义的类加载器，还是系统类加载器或扩展类加载器，最终都必须调用java.lang.ClassLoader.defineClass(String, byte[], int, int, ProtectionDomain)方法，而该方法会执行preDefineClass()接口，该接口中提供了对JDK核心类库的保护。



5. 双亲委托模式的弊端

检查类是否加载的委托过程是单向的，这个方式虽然从结构上说比较清晰，使各个ClassLoader的职责非常明确，但是同时会带来一个问题，即顶层的ClassLoader无法访问底层的ClassLoader所加载的类。

通常情况下，启动类加载器中的类为系统核心类，包括一些重要的系统接口，而在应用类加载器中，为应用类。按照这种模式，应用类访问系统类自然是没有问题，但是系统类访问应用类就会出现问题。比如在系统类中提供了一个接口，该接口需要在应用类中得以实现，该接口还绑定一个工厂方法，用于创建该接口的实例，而接口和工厂方法都在启动类加载器中。这时，就会出现该工厂方法无法创建由应用类加载器加载的应用实例的问题。



6. 结论：

由于Java虚拟机规范并没有明确要求类加载器的加载机制一定要使用双亲委派模型，只是建议采用这种方式而已。

比如在Tomcat中，类加载器所采用的加载机制就和传统的双亲委派模型有一定区别，当缺省的类加载器接收到一个类的加载任务时，首先会由它自行加载，当它加载失败时，才会将类的加载任务委派给它的超类加载器去执行，这同时也是 Servlet规范推荐的一种做法。





#### 5.3 破坏双亲委派机制

##### 5.3.1 破坏双亲委派机制1

双亲委派模型并不是一个具有强制性约束的模型，而是Java设计者推荐给开发者们的类加载器实现方式。

在Java的世界中大部分的类加载器都遵循这个模型，但也有例外的情况，直到Java模块化出现为止，双亲委派模型主要出现过3次较大规模“被破坏”的情况。



第一次破坏双亲委派机制：

双亲委派模型的第一次“被破坏”其实发生在双亲委派模型出现之前一一即JDK1.2面世以前的“远古”时代。

由于双亲委派模型在JDK1.2之后才被引入，但是类加载器的概念和抽象类java.lang.ClassLoader则在Java的第一个版本中就已经存在，面对已经存在的用户自定义类加载器的代码，Java设计者们引入双亲委派模型时不得不做出一些妥协，**为了兼容这些已有代码，无法再以技术手段避免loadClass()被子类覆盖的可能性**，只能在JDk1.2之后的java.lang.ClassLoader中添加一个新的protected方法findclass()，并引导用户编写的类加载逻辑时尽可能去重写这个方法，而不是在loadClass()中编写代码。上节我们已经分析过loadclass()方法，双亲委派的具体逻辑就实现在这里面，按照loadClass()方法的逻辑，如果父类加载失败，会自动调用自己的findClass()方法来完成加载，这样既不影响用户按照自已的意愿去加载类，又可以保证新写出来的类加载器是符合双亲委派规则的。



##### 5.3.2 破坏双亲委派机制2

第二次破坏双亲委派机制：线程上下文类加载器

双亲委派模型的第二次“被破坏”是由这个模型自身的缺陷导致的，双亲委派很好地解决了各个类加载器协作时基础类型的一致性问题（越基础的类由越上层的加载器进行加载），基础类型之所以被称为“基础”，是因为它们总是作为被用户代码继承、调用的API存在，但程序设计往往没有绝对不变的完美规则，如果有基础类型又要调用回用户的代码，那该怎么办呢？

这并非是不可能出现的事情，一个典型的例子便是JNDI服务，JNDI现在已经是Java的标准服务，它的代码由启动类加载器来完成加载（在JDK1.3时加入到rt.jar的），肯定属于Java中很基础的类型了。但JNDI存在的目的就是对资源进行查找和集中管理，它需要调用由其他厂商实现并部署在应用程序的ClassPath下的JNDI服务提供者接口（ServiceProviderInterface，SPI）的代码，现在问题来了，启动类加载器是绝不可能认识、加载这些代码的，那该怎么办？（SPI：在Java平台中，通常把核心类rt.jar中提供外部服务、可由应用层自行实现的接口称为SPI）

为了解决这个困境，Java的设计团队只好引入了一个不太优雅的设计：线程上下文类加载器（ThreadContextClassLoader）。这个类加载器可以通过java.lang.Thread类的setContextClassLoader()方法进行设置，如果创建线程时还未设置，它将会从父线程中继承一个，如果在应用程序的全局范围内都没有设置过的话，那这个类加载器默认就是应用程序类加载器。

有了线程上下文类加载器，程序就可以做一些“舞弊”的事情了。JNDI服务使用这个线程上下文类加载器去加载所需的 SPI服务代码，**这是一种父类加载器去请求子类加载器完成类加载的行为，这种行为实际上是打通了双亲委派模型的层次结构来逆向使用类加载器，已经违背了双亲委派模型的一般性原则**，但也是无可奈何的事情。Java中涉及SPI的加载基本上都采用这种方式来完成，例如JNDI、JDBC、JCE、JAXB和JBI等。不过，当SPI的服务提供者多于一个的时候，代码就只能根据具体提供者的类型来硬编码判断，为了消除这种极不优雅的实现方式，在JDK6时，JDK提供了java.util.ServiceLoader类，以META-INF/services中的配置信息，辅以责任链模式，这才算是给SPI的加载提供了一种相对合理的解决方案。

![](JVM.assets/228.png)

默认上下文加载器就是应用类加载器，这样以上下文加载器为中介，使得启动类加载器中的代码也可以访问应用类加载器中的类。



##### 5.3.3 破坏双亲委派机制3

第三次破坏双亲委派机制：

双亲委派模型的第三次“被破坏”是由于用户对程序动态性的追求而导致的。如：代码热替换（HotSwap）、模块热部署（HotDeployment）等

IBM公司主导的JSR-291（即OSGiR4.2）实现模块化热部署的关键是它自定义的类加载器机制的实现，每一个程序模块（OSGi中称为Bundle）都有一个自己的类加载器，当需要更换一个Bundle时，就把Bundle连同类加载器一起换掉以实现代码的热替换。在OSGi环境下，类加载器不再双亲委派模型推荐的树状结构，而是进一步发展为更加复杂的网状结构。



当收到类加载请求时，OSGi将按照下面的顺序进行类搜索：

1. 将以java.*开头的类，委派给父类加载器加载。
2. 否则，将委派列表名单内的类，委派给父类加载器加载。
3. 否则，将Import列表中的类，委派给Export这个类的Bundle的类加载器加载。 
4. 否则，查找当前Bundle的classPath，使用自己的类加载器加载。
5. 否则，查找类是否在自己的FragmentBundle中，如果在，则委派给FragmentBundle的类加载器加载。
6. 否则，查找DynamicImport列表的Bundle，委派给对应Bundle的类加载器加载。 
7. 否则，类查找失败。

**说明**：只有开头两点仍然符合双亲委派模型的原则，其余的类查找都是在平级的类加载器中进行的

**小结**：这里，我们使用了“被破坏”这个词来形容上述不符合双亲委派模型原则的行为，但这里“被破坏”并不一定是带有贬义的。只要有明确的目的和充分的理由，突破旧有原则无疑是一种创新。

**正如**：OSGi中的类加载器的设计不符合传统的双亲委派的类加载器架构，且业界对其为了实现热部署而带来的额外的高复杂度还存在不少争议，但对这方面有了解的技术人员基本还是能达成一个共识，认为OSGi中对类加载器的运用是值得学习的，完全弄懂了OSGi的实现，就算是掌握了类加载器的精粹。





#### 5.4 热替换的实现

热替换是指在程序的运行过程中，不停止服务，只通过替换程序文件来修改程序的行为。**热替换的关键需求在于服务不能中断，修改必须立即表现正在运行的系统之中**。基本上大部分脚本语言都是天生支持热替换的，比如：PHP，只要替换了PHP源文件，这种改动就会立即生效，而无需重启Web服务器。

但对Java来说，热替换并非天生就支持，如果一个类已经加载到系统中，通过修改类文件，并无法让系统再来加载并重定义这个类。因此，在Java中实现这一功能的一个可行的方法就是灵活运用ClassLoader。

注意：由不同ClassLoader加载的同名类属于不同的类型，不能相互转换和兼容。即两个不同的ClassLoader加载同一个类，在虚拟机内部，会认为这2个类是完全不同的。

根据这个特点，可以用来模拟热替换的实现，基本思路如下图所示：

![](JVM.assets/229.png)

```java
public class LoopRun {
    public static void main(String args[]) {
        while (true) {
            try {
                //1. 创建自定义类加载器的实例
                MyClassLoader loader = new MyClassLoader("D:\\code\\workspace_idea5\\JVMDemo1\\chapter04\\src\\");
                //2. 加载指定的类
                Class clazz = loader.findClass("com.atguigu.java1.Demo1");
                //3. 创建运行时类的实例
                Object demo = clazz.newInstance();
                //4. 获取运行时类中指定的方法
                Method m = clazz.getMethod("hot");
                //5. 调用指定的方法
                m.invoke(demo);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("not find");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

}
```





### 6. 沙箱安全机制

- 保证程序安全
- 保护Java原生的JDK代码

Java安全模型的核心就是Java沙箱（sandbox）。什么是沙箱？沙箱是一个限制程序运行的环境。

沙箱机制就是将Java代码**限定在虚拟机（JVM）特定的运行范围中，并且严格限制代码对本地系统资源访问**。通过这样的措施来保证对代码的有限隔离，防止对本地系统造成破坏。

沙箱主要限制系统资源访问，那系统资源包括什么？CPU、内存、文件系统、网络。不同级别的沙箱对这些资源访问的限制也可以不一样。

所有的Java程序运行都可以指定沙箱，可以定制安全策略。



#### 6.1 JDK1.0时期

在Java中将执行程序分成本地代码和远程代码两种，本地代码默认视为可信任的，而远程代码则被看作是不受信的。对于授信的本地代码，可以访问一切本地资源。而对于非授信的远程代码在早期的Java实现中，安全依赖于沙箱（ Sandbox）机制。如下图所示JDK1.0安全模型

![](JVM.assets/230.png)





#### 6.2 JDK1.1时期

JDK1.0中如此严格的安全机制也给程序的功能扩展带来障碍，比如当用户希望远程代码访问本地系统的文件时候，就无法实现。

因此在后续的Java1.1版本中，针对安全机制做了改进，增加了安全策略。允许用户指定代码对本地资源的访问权限。如下图所示JDK1.1安全模型

![](JVM.assets/231.png)



#### 6.3 JDK1.2时期

在Java1.2版本中，再次改进了安全机制，增加了代码签名。不论本地代码或是远程代码，都会按照用户的安全策略设定，由类加载器加载到虚拟机中权限不同的运行空间，来实现差异化的代码执行权限控制。如下图所示JDK1.2安全模型

![](JVM.assets/232.png)



#### 6.4 JDK1.6时期

当前最新的安全机制实现，则引入了**域（Domain）**的概念。

虚拟机会把所有代码加载到不同的系统域和应用域。**系统域部分专门负责与关键资源进行交互**，而各个应用域部分则通过系统域的部分代理来对各种需要的资源进行访问。虚拟机中不同的受保护域（ProtectedDomain），对应不一样的权限（Permission）。存在于不同域中的类文件就具有了当前域的全部权限，如下图所示，最新的安全模型（ jdk1.6）

![](JVM.assets/233.png)





### 7. 自定义类的加载器

1. 为什么要自定义类加载器？

- **隔离加载类**：在某些框架内进行中间件与应用的模块隔离，把类加载到不同的环境。比如：阿里内某容器框架通过自定义类加载器确保应用中依赖的jar包不会影响到中间件运行时使用的jar包。再比如：Tomcat这类Web应用服务器，内部自定义了好几种类加载器，用于隔离同一个Web应用服务器上的不同应用程序。（类的仲裁-->类中突）
- **修改类加载的方式**：类的加载模型并非强制，除Bootstrap外，其他的加载并非一定要引入，或者根据实际情况在某个时间点进行按需进行动态加载
- **扩展加载源**：比如从数据库、网络、甚至是电视机机顶盒进行加载
- **防止源码泄漏**：Java代码容易被编译和篡改，可以进行编译加密。那么类加载也需要自定义，还原加密的字节码。



2. 常见的场景

- 实现类似进程内隔离，类加载器实际上用作不同的命名空间，以提供类似容器、模块化的效果。例如，两个模块依赖于某个类库的不同版本，如果分别被不同的容器加载，就可以互不干扰。这个方面的集大成者是JavaEE和 OSGI、JPMS等框架。
- 应用需要从不同的数据源获取类定义信息，例如网络数据源，而不是本地文件系统。或者是需要自已操纵字节码，动态修改或者生成类型。



3. **注意**：在一般情况下，使用不同的类加载器去加载不同的功能模块，会提高应用程序的安全性。但是，如果涉及Java类型转换，则加载器反而容易产生不美好的事情。在做Java类型转换时，只有两个类型都是由同一个加载器所加载，才能进行类型转换，否则转换时会发生异常。



#### 7.1 实现方式

用户通过定制自己的类加载器，这样可以重新定义类的加载规则，以便实现一些自定义的处理逻辑。



1. 实现方式

- Java提供了抽象类java.lang.ClassLoader，所有用户自定义的类加载器都应该继承ClassLoader类。
- 在自定义ClassLoader的子类时候，我们常见的会有两种做法：
  - 方式一：重写loadClass()方法
  - 方式二：重写findclass()方法-->推荐



2. 对比

这两种方法本质上差不多，毕竞loadClass()也会调用findClass()，但是从逻辑上讲我们最好不要直接修改loadClass()的内部逻辑。建议的做法是只在findClass()里重写自定义类的加载方法，根据参数指定类的名字，返回对应的class对象的引用。

- loadClass()这个方法是实现双亲委派模型逻辑的地方，擅自修改这个方法会导致模型被破坏，容易造成问题。**因此我们最好是在双亲委派模型框架内进行小范围的改动，不破坏原有的稳定结构**。同时，也避免了自己重写loadClass()方法的过程中必须写双亲委托的重复代码，从代码的复用性来看，不直接修改这个方法始终是比较好的选择。
- 当编写好自定义类加载器后，便可以在程序中调用loadClass()方法来实现类加载操作。 



3. 说明

- 其父类加载器是系统类加载器
- JVM中的所有类加载都会使用java.lang.ClassLoader.loadClass(String)接口（自定义类加载器重写 java.lang.ClassLoader.loadClass(String)接口的除外），连JDK的核心类库也不能例外。

```java
/**
 * @author shkstart
 * @create 15:20
 * 自定义ClassLoader
 */
public class MyClassLoader extends ClassLoader {
    private String byteCodePath;

    public MyClassLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    public MyClassLoader(ClassLoader parent, String byteCodePath) {
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            //获取字节码文件的完整路径
            String fileName = byteCodePath + className + ".class";
            //获取一个输入流
            bis = new BufferedInputStream(new FileInputStream(fileName));
            //获取一个输出流
            baos = new ByteArrayOutputStream();
            //具体读入数据并写出的过程
            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            //获取内存中的完整的字节数组的数据
            byte[] byteCodes = baos.toByteArray();
            //调用defineClass()，将字节数组的数据转换为Class的实例。
            Class clazz = defineClass(null, byteCodes, 0, byteCodes.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;


    }
}

```

```java
/**
 * @author shkstart
 * @create 15:20
 */
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader("d:/");

        try {
            Class clazz = loader.loadClass("Demo1");
            System.out.println("加载此类的类的加载器为：" + clazz.getClassLoader().getClass().getName());

            System.out.println("加载当前Demo1类的类的加载器的父类加载器为：" + clazz.getClassLoader().getParent().getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

```







### 8. Java9新特性

为了保证兼容性，JDK9没有从根本上改变三层类加载器架构和双亲委派模型，但为了模块化系统的顺利运行，仍然发生了一些值得被注意的变动。



1. 扩展机制被移除，扩展类加载器由于向后兼容性的原因被保留，不过被重命名为平台类加载器（platformclass loader）。可以通过classLoader的新方法getPlatformClassLoader()来获取。

JDK9时基于模块化进行构建（原来的rt.jar和tools.jar被拆分成数十个JMOD文件），其中的Java类库就已天然地满足了可扩展的需求，那自然无须再保留<JAVA_HOME>\lib\ext目录，此前使用这个目录或者java.ext.dirs系统变量来扩展JDK功能的机制已经没有继续存在的价值了。 



2. 平台类加载器和应用程序类加载器都不再继承自java.net.URLclassLoader。

现在启动类加载器、平台类加载器、应用程序类加载器全都继承于jdk.internal.loader.BuiltinClassLoader。

![](JVM.assets/234.png)

如果有程序直接依赖了这种继承关系，或者依赖了URLClassLoader类的特定方法，那代码很可能会在JDK9及更高版本的JDK中崩溃。



3. 在Java9中，类加载器有了名称。该名称在构造方法中指定，可以通过getName()方法来获取。平台类加载器的名称是platform，应用类加载器的名称是app。类加载器的名称在调试与类加载器相关的问题时会非常有用。



4. 启动类加载器现在是在jvm内部和java类库共同协作实现的类加载器（以前是C++实现），但为了与之前代码兼容，在获取启动类加载器的场景中仍然会返回null，而不会得到BootClassLoader实例。



5. 类加载的委派关系也发生了变动。
   当平台及应用程序类加载器收到类加载请求，在委派给父加载器加载前，要先判断该类是否能够归属到某一个系统模块中，如果可以找到这样的归属关系，就要优先委派给负责那个模块的加载器完成加载。

![](JVM.assets/235.png)

![](JVM.assets/236.png)







# JVM下篇：性能监控与调优篇

## 一、概述

### 1. 背景说明

#### 1.1 生产环境中的问题

- 生产环境发生了内存溢出该如何处理？
- 生产环境应该给服务器分配多少内存合适？
- 如何对垃圾回收器的性能进行调优？
- 生产环境CPU负载高该如何处理？
- 生产环境应该给应用分配多少线程合适？
- 不加log，如何确定请求是否执行了某一行代码？
- 不加log，如何实时查看某个方法的入参与返回值？





#### 1.2 为什么要调优？

- 防止出现OOM
- 解决OOM
- 减少Full GC出现的频率





#### 1.3 不同阶段的考虑

- 上线前
- 项目运行阶段
- 线上出现OOM







### 2. 调优概述

#### 2.1 监控的依据

- 运行日志
- 异常堆栈
- GC日志
- 线程快照
- 堆转储快照





#### 2.2 调优的大方向

- 合理地编写代码
- 充分并合理的使用硬件资源
- 合理地进行JVM调优





### 3. 性能优化的步骤

#### 3.1 第一步(发现问题)：性能监控

以一种非强行或者非入侵方式收集或查看应用运营性能数据的活动。

监控通常是指一种在生产、质量评估或者开发环境下实施的带有**预防或主动性**的活动。

当应用相关干系人提出性能问题却没有提供足够多的线索时，首先我们需要进行性能监控，随后是性能分析。



- GC频繁
- cpu load过高
- 0OM
- 内存泄漏
- 死锁
- 程序响应时间较长



#### 3.2 第二步(排查问题)：性能分析

一种以**侵入方式**收集运行性能数据的活动，它会影响应用的吞吐量或响应性。

性能分析是针对性能问题的答复结果，关注的范围通常比性能监控更加集中。

性能分析很少在生产环境下进行，通常是在质量评估、系统测试或者开发环境下进行，是性能监控之后的步骤。



- 打印GC日志，通过GCviewer或者 http://gceasy.io来分析日志信息
- 灵活运用命令行工具，jstack，jmap，jinfo等
- dump出堆文件，使用内存分析工具分析文件
- 使用阿里Arthas，或jconsole，JVisuaJVM来实时查看JVM状态
- jstack查看堆栈信息



#### 3.3 第三步(解决问题)：性能调优

一种为改善应用响应性或吞吐量而更改参数、源代码、属性配置的活动，性能调优是在性能监控、性能分析之后的活动。



- 适当增加内存，根据业务背景选择垃圾回收器
- 优化代码，控制内存使用
- 增加机器，分散节点压力
- 合理设置线程池线程数量
- 使用中间件提高程序效率，比如缓存，消息队列等
- 其他....





### 4. 性能评价/测试指标

1. **停顿时间（或响应时间）**

提交请求和返回该请求的响应之间使用的时间，一般比较关注平均响应时间常用操作的响应时间列表：

![](JVM.assets/237.png)

在垃圾回收环节中，暂停时间是执行垃圾收集时，程序的工作线程被暂停的时间。

-XX:MaxGCPauseMillis



2. **吞吐量** 

单位时间内完成的工作量（请求）的量度

在GC中：运行用户代码的时间占总运行时间的比例（总运行时间：程序的运行时间+内存回收的时间）**吞吐量为1 - 1 / (1 + n)**。-XX:GCTimeRatio=n





3. **并发数**：同一时刻，对服务器有实际交互的请求数

1000个人同时在线，估计并发数在5%-15%之间，也就是同时并发量：50－150之间。



4. **内存占用**：Java堆区所占的内存大小





5. **相互间的关系**：以高速公路通行状况为例

吞吐量：每天通过高速公路收费站的车辆的数据（也可以理解为收费站收取的高速费）

并发数：高速公路上正在行驶的车辆的数目

响应时间：车速





## 二、JVM监控及诊断工具-命令行篇

### 1. 概述

性能诊断是软件工程师在日常工作中需要经常面对和解决的问题，在用户体验至上的今天，解决好应用的性能问题能带来非常大的收益。

Java作为最流行的编程语言之一，其应用性能诊断一直受到业界广泛关注。可能造成 Java应用出现性能问题的因素非常多，例如线程控制、磁盘读写、数据库访问、网络I/0、垃圾收集等。想要定位这些问题，一款优秀的性能诊断工具必不可少。

体会1：使用数据说明问题，使用知识分析问题，使用工具处理问题。

体会2：无监控、不调优！





#### 1.1 简单命令行工具

在我们刚接触java学习的时候，大家肯定最先了解的两个命令就是javac，java，那么除此之外，还有没有其他的命令可以供我们使用呢？我们**进入到安装jdk的bin目录**，发现还有一系列辅助工具。这些辅助工具用来获取目标JVM不同方面、不同层次的信息，帮助开发人员很好地解决Java应用程序的一些疑难杂症。







### 2. jps：查看正在运行的Java

jps(Java Process Status)：显示指定系统内所有的HotSpot虚拟机进程（查看虚拟机进程信息），可用于查询正在运行的虚拟机进程。

说明：对于本地虚拟机进程来说，进程的本地虚拟机ID与操作系统的进程ID是一致的，是唯一的。



#### 2.1 基本语法

> jps [options] [hostid]
>



##### 2.1.1 options参数

- -q:仅仅显示LVMID(local virtual machine id)，即本地虚拟机唯一id。不显示主类的名称等
- -l：输出应用程序主类的全类名或如果进程执行的是jar包，则输出jar完整路径
- -m：输出虚拟机进程启动时传递给主类main()的参数
- -v：列出虚拟机进程启动时的JVM参数。比如：-Xms20m -Xmx50m是启动程序指定的jvm参数。

说明：以上参数可以综合使用，除了-q。

补充：如果某Java进程关闭了默认开启的UsePerfData参数(即使用参数-XX:-UsePerfData)，那么jps命令（以及下面介绍的jstat）将无法探知该Java 进程。



##### 2.1.2 hostid参数

RMI注册表中注册的主机名。

如果想要远程监控主机上的java程序，需要安装jstatd。

对于具有更严格的安全实践的网络场所而言，可能使用一个自定义的策略文件来显示对特定的可信主机或网络的访问，尽管这种技术容易受到IP地址欺诈攻击。

如果安全问题无法使用一个定制的策略文件来处理，那么最安全的操作是不运行 jstatd服务器，而是在本地使用jstat和jps工具。





### 3. jstat：查看JVM统计信息

jstat（JVM Statistics Monitoring Tool）：用于监视虚拟机各种运行状态信息的命令行工具。它可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、 JIT编译等运行数据。

在没有GUI图形界面，只提供了纯文本控制台环境的服务器上，它将是运行期定位虚拟机性能问题的首选工具。常用于检测垃圾回收问题以及内存泄漏问题。





#### 3.1 基本语法

> jstat -<option> [-t] [-h<lines>] <vmid> [<interval>] [<count>]]
>

<vmid>：程序的进程号

##### 3.1.1 option参数

- 类装载相关的：

  - -class：显示ClassLoader的相关信息：类的装载、卸载数量、总空间、类装载所消耗的时间等

    ![](JVM.assets/244.png)



- JIT相关的：
  - -compiler：显示JIT编译器编译过的方法、耗时等信息
    ![](JVM.assets/242.png)
  - -printcompilation：输出已经被JIT编译的方法
    ![](JVM.assets/243.png)



- 垃圾回收相关的：
  - -gc：显示与GC相关的堆信息。包括Eden区、两个Survivor区、老年代、永久代等的容量、已用空间、GC时间合计等信息。
    ![](JVM.assets/245.png)
  - -gccapacity：显示内容与-gc基本相同，但输出主要关注Java堆各个区域使用到的最大、最小空间。
  - -gcutil：显示内容与-gc基本相同，但输出主要关注已使用空间占总空间的百分比。
  - -gccause：与-gcutil功能一样，但是会额外输出导致最后一次或当前正在发生的GC产生的原因。
  - -gcnew：显示新生代GC状况
  - -gcnewcapacity：显示内容与-gcnew基本相同，输出主要关注使用到的最大、最小空间
  - -geold：显示老年代GC状况
  - -gcoldcapacity：显示内容与-gcold基本相同，输出主要关注使用到的最大、最小空间
  - -gcpermcapacity：显示永久代使用到的最大、最小空间。







##### 3.1.2 interval参数

用于指定输出统计数据的周期，单位为毫秒。即：查询间隔

每隔一秒输出一次示例：

![](JVM.assets/238.png)



##### 3.1.3 count参数

用于指定查询的总次数

每隔一秒输出一次，并且只输出十次示例：

![](JVM.assets/239.png)



##### 3.1.4 -t参数

可以在输出信息前加上一个Timestamp列，显示程序的运行时间。单位：秒

![](JVM.assets/240.png)





##### 3.1.5 -h参数

可以在周期性数据输出时，输出多少行数据后输出一个表头信息

每隔三行数据就打印一个表头示例：

![](JVM.assets/241.png)





### 4. jinfo：实时查看和修改JVM配置参数

jinfo(Configuration Info for Java)：查看虚拟机配置参数信息，也可用于调整虚拟机的配置参数。

在很多情况下，Java应用程序不会指定所有的Java虚拟机参数。而此时，开发人员可能不知道某一个具体的Java虚拟机参数的默认值。在这种情况下，可能需要通过查找文档获取某个参数的默认值。这个查找过程可能是非常艰难的。但有了jinfo工具，开发人员可以很方便地找到 Java虚拟机参数的当前值。



#### 4.1 基本语法

> jinfo [options} pid
>



##### 4.1.1 options参数：

**查看**

- jinfo -sysprops PID：输出系统属性
- jinfo -flags PID：输出全部的参数
- jinfo -flag 具体参数PID：查看某个java进程的具体参数的值



**修改**(并非所有参数都支持动态修改。参数只有被标记为manageable的flag可以被实时修改。其实，这个修改能力是极其有限的。)

- jinfo -flag [ + / - ] 具体参数 PID
- jinfo -flag 具体参数=具体参数值 PID





#### 4.2 拓展

- java -XX:+PrintFlagslnitial：查看所有JIVM参数启动的初始值
- java -XX:+PrintFlagsFinal：查看所有JVM参数的最终值
- java -XX:+PrintCommandLineFlags：查看那些已经被用户或者JVM设置过的详细的XX参数的名称和值





### 5. jmap：导出内存映像文件&内存使用情况

jmap(JVM Memory Map)：作用一方面是获取dump文件（堆转储快照文件，二进制文件），它还可以获取目标Java进程的内存相关信息，包括Java堆各区域的使用情况、堆中对象的统计信息、类加载信息等。

开发人员可以在控制台中输入命令“jmap-help”查阅jmap工具的具体使用方式和一些标准选项配置。



#### 5.1 基本语法

它的基本使用语法为：

- jmap [option] <pid>：当前进程
- jmap [option] <executable <core>：可执行性代码
- jmap [option] [server_id@]<remote server IP or hostname>：远程连接



##### 5.1.1 options参数

- -dump：生成Java堆转储快照：dump文件。特别的：-dump:live只保存堆中的存活对象
- -heap：输出整个堆空间的详细信息，包括GC的使用、堆配置信息，以及内存的使用信息等
- -histo：输出堆中对象的统计信息，包括类、实例数量和合计容量。特别的：-histo:live只统计堆中的存活对象。
- -permstat：以ClassLoader为统计口径输出永久代的内存状态信息，仅linux/solaris平台有效。
- -finalizerinfo：显示在F-Queue中等待Finalizer线程执行finalize方法的对象，仅linux/solaris平台有效。
- -F：当虚拟机进程对-dump选项没有任何响应时，可使用此选项强制执行生成dump文件仅linux/solaris平台有效
- -h | -help：jmap工具使用的帮助命令
- -J <flag>：传递参数给jmap启动的jvm





#### 5.2 使用一：导出内存映像文件

一般来说，使用jmap指令生成dump文件的操作算得上是最常用的jmap命令之一，将堆中所有存活对象导出至一个文件之中。

Heap Dump又叫做堆存储文件，指一个Java进程在某个时间点的内存快照。Heap Dump在触发内存快照的时候会保存此刻的信息如下：

- All 0bjects：Class,fields,primitive values and references
- All Classes：ClassLoader,name,super class,static fields
- Garbage Collection Roots：Objects defined to be reachable by the JVM
- Thread Stacks and Local Variables：The call-stacks of threads at the moment of the snapshot,and per-frame information about local objects

说明：

1. 通常在写HeapDump文件前会触发一次FullGC，所以heapdump文件里保存的都是 FullGC后留下的对象信息。
2. 由于生成dump文件比较耗时，因此大家需要耐心等待，尤其是大内存镜像生成dump文件则需要耗费更长的时间来完成。





##### 5.2.1 手动的方式

- jmap -dump:format=b,file=<filename.hprof> <pid>

- jmap -dump:live,format=b,file=<filename.hprof> <pid>
  format：使得jmap生成的，能和hprof的文件格式能够匹配上

  ![](JVM.assets/246.png)
  



##### 5.2.2 自动的方式

当程序发生OOM退出系统时，一些瞬时信息都随着程序的终止而消失，而重现OOM问题往往比较困难或者耗时。此时若能在OOM时，自动导出dump文件就显得非常迫切。

这里介绍一种比较常用的取得堆快照文件的方法，即使用：

- -XX：+HeapDumpOnOutOfMemoryError：在程序发生OOM时，导出应用程序的当前堆快照。
- -XX：HeapDumpPath：可以指定堆快照的保存位置。

比如：-Xmx100m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\m.hprof





#### 5.3 使用2：显示堆内存相关信息

- jmap -heap pid：输出整个堆空间的详细信息
- jmap -histo pid：输出堆中对象的统计信息





#### 5.4 使用3：其他作用

- jmap -permstat pid：查看系统的ClassLoader信息
- jmap -finalizerinfo：查看堆积在finalizer队列中的对象





**小结**：由于jmap将访问堆中的所有对象，为了保证在此过程中不被应用线程干扰，jmap需要借助安全点机制，让所有线程停留在不改变堆中数据的状态。也就是说，由jmap导出的堆快照必定是安全点位置的。这可能导致基于该堆快照的分析结果存在偏差。

举个例子，假设在编译生成的机器码中，某些对象的生命周期在两个安全点之间，那么：live选项将无法探知到这些对象。

另外，如果某个线程长时间无法跑到安全点，jmap将一直等下去。与前面讲的jstat则不同，垃圾回收器会主动将jstat所需要的摘要数据保存至固定位置之中，而jstat只需直接读取即可。





### 6. jhat：JDK自带堆分析工具

jhat(JVM Heap Analysis Tool)：

SunJDK提供的jhat命令与jmap命令搭配使用，用于分析jmap生成的heap dump文件（堆转储快照）。jhat内置了一个微型的HTTP/HTML服务器，生成dump文件的分析结果后，用户可以在浏览器中查看分析结果(分析虚拟机转储快照信息)。

使用了jhat命令，就启动了一个http服务，端口是7000，即http://localhost:7000/，就可以在浏览器里分析。

说明：jhat命令在JDK9、JDK10中已经被删除，官方建议用Visual VM代替。



#### 6.1 基本语法

> jhat [option] [dumpfile]，比如说：jhat d:\1.hprof



##### 6.1.1 options参数

- option参数：-stack false l true：关闭 | 打开对象分配调用栈跟踪
- option参数：-refs false l true：关闭 | 打开对象引用跟踪
- option参数：-port port-number：设置jhat HTTP(Server的端口号，默认7000)
- option参数：-exclude exclude-file：执行对象查询时需要排除的数据成员
- option参数：-baseline exclude-file：指定一个基准堆转储
- option参数：-debugint：设置debug级别
- option参数：-version：启动后显示版本信息就退出
- option参数：-J<flag>：传入启动参数，比如-J-Xmx512m





### 7. jstack：打印JVM中线程快照

jstack(JVM Stack Trace)：用于生成虚拟机指定进程当前时刻的线程快照（虚拟机堆栈跟踪）。线程快照就是当前虚拟机内指定进程的每一条线程正在执行的方法堆栈的集合。

生成线程快照的作用：可用于定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待等问题。这些都是导致线程长时间停顿的常见原因。当线程出现停顿时，就可以用jstack显示各个线程调用的堆栈情况。

在thread dump中，要留意下面几种状态

- 死锁，Deadlock（重点关注）
- 等待资源，Waiting on condition（重点关注）
- 等待获取监视器，Waiting on monitor entry（重点关注）
- 阻塞，Blocked（重点关注）
- 执行中，Runnable
- 暂停，Suspended
- 对象等待中，Object.wait()或TIMED_WAITING
- 停止，Parked



#### 7.1 基本语法

> jstack option pid
>



##### 7.1.1 option参数

- option参数：-F：当正常输出的请求不被响应时，强制输出线程堆栈
- option参数：-l：除堆栈外，显示关于锁的附加信息
- option参数：-m：如果调用到本地方法的话，可以显示C/C++的堆栈
- option参数：-h：帮助操作





### 8. jcmd：多功能命令行

在JDK1.7以后，新增了一个命令行工具jcmd。

它是一个多功能的工具，可以用来实现前面除了jstat之外所有命令的功能。比如：用它来导出堆、内存使用、查看Java进程、导出线程信息、执行GC、JVM运行时间等。

官方帮助文档：https://docs.oracle.com/en/java/javase/11/tools/jcmd.html

jcmd拥有jmap的大部分功能，并且在Oracle的官方网站上也推荐使用jcmd命令代jmap命令



#### 8.1 基本语法

- jcmd -l：列出所有的JVM进程
- jcmd pid help：针对指定的进程，列出支持的所有命令
- jcmd pid 具体命令：显示指定进程的指令命令的数据





### 9. jstatd：远程主机信息收集

之前的指令只涉及到监控本机的Java应用程序，而在这些工具中，一些监控工具也支持对远程计算机的监控（如jps、jstat）。为了启用远程监控，则需要配合使用jstatd工具。

命令jstatd是一个RMI服务端程序，它的作用相当于代理服务器，建立本地计算机与远程监控工具的通信。jstatd服务器将本机的Java应用程序信息传递到远程计算机。

![](JVM.assets/247.png)





## 三、JVM监控及诊断工具-GUI篇

使用上一章命令行工具或组合能帮您获取目标Java应用性能相关的基础信息，但它们存在下列局限：

1. 无法获取方法级别的分析数据，如方法间的调用关系、各方法的调用次数和调用时间等（这对定位应用性能瓶颈至关重要）。
2. 要求用户登录到目标Java应用所在的宿主机上，使用起来不是很方便。
3. 分析数据通过终端输出，结果展示不够直观。

为此，JDK提供了一些内存泄漏的分析工具，如jconsole，jvisualvm等，用于辅助开发人员定位问题，但是这些工具很多时候并不足以满足快速定位的需求。所以这里我们介绍的工具相对多一些、丰富一些。



**图形化综合诊断工具**

- JDK自带的工具
  - jconsole
  - Visual VM
  - JMC
- 第三方工具
  - MAT
  - JProfiler
  - Arthas
  - Btrace





### 1. jConsole

- 从Java5开始，在JDK中自带的java监控和管理控制台。
- 用于对JVM中内存、线程和类等的监控，是一个基于JMX(java management extensions)的GUI性能监控工具。



**启动**

![](JVM.assets/248.png)



**三种连接方式**

- **Local**：使用JConsole连接一个正在本地系统运行的JVM，并且执行程序的和运行JConsole的需要是同一个用户。JConsole使用文件系统的授权通过RMI连接器连接到平台的MBean服务器上。这种从本地连接的监控能力只有Sun的JDK具有。
- **Remote**：使用下面的URL通过RMl连接器连接到一个JMX代理，service:jmx:rmi:/jndi/rmi:/hostName:portNum/jmxrmi。 JConsole为建立连接，需要在环境变量中设置mx.remote.credentials来指定用户名和密码，从而进行授权。
- **Advanced**：使用一个特殊的URL连接JMX代理。一般情况使用自己定制的连接器而不是RMI提供的连接器来连接JMX代理，或者是一个使用JDK1.4的实现了JMX和JMXRmote的应用。





### 2. Visual VM

- Visual VM是一个功能强大的多合一故障诊断和性能监控的可视化工具。
- 它集成了多个JDK命令行工具，使用Visual VM可用于显示虚拟机进程及进程的配置和环境信息(jps, jinfo)，监视应用程序的CPU、GC、堆、方法区及线程的信息(jstat、jstack)等，甚至代替JConsole。
- 在JDK6 Update 7以后，Visual VM便作为JDK的一部分发布（Visual VM在JDK/bin目录下），即：它完全免费。
- 此外，VisualVM也可以作为独立的软件安装



**启动**

![](JVM.assets/249.png)





**插件的安装**

- VisualVM的一大特点是支持插件扩展，并且插件安装非常方便。我们既可以通过离线下载插件文件*.nbm，然后在Plugin对话框的己下载页面下，添加己下载的插件。也可以在可用插件页面下，在线安装插件。（这里建议安装上：VisualGC）

  插件地址：https://visualvm.github.io/pluginscenters.html



- IDEA安装VisualVM Launcher插件

  Preferences --> Plugins --> 搜索VisualVM Launcher，安装重启即可。



**连接方式**

- **本地连接**：监控本地Java进程的CPU、类、线程等



- 远程连接

1. 确定远程服务器的ip地址
2. 添加JMX（通过JMX技术具体监控远端服务器哪个Java进程
3. 修改bin/catalina.sh文件，连接远程的tomcat
4. 在../conf中添加jmxremote.access和jmxremote.password文件
5. 将服务器地址改为公网ip地址
6. 设置阿里云安全策略和防火墙策略
7. 启动tomcat，查看tomcat启动日志和端口监听
8. JMX中输入端口号、用户名、密码登录





#### 2.1 主要功能

1. 生成/读取堆内存快照
2. 查看JVM参数和系统属性
3. 查看运行中的虚拟机进程
4. 生成/读取线程快照
5. 程序资源的实时监控
6. 其他功能
   - JMX代理连接
   - 远程环境监控
   - CPU分析和内存分析





### 3. MAT

MAT(Memory Analyzer Tool)工具是一款功能强大的**Java堆内存分析器**。可以用于查找内存泄漏以及查看内存消耗情况。

MAT是基于Eclipse开发的，不仅可以单独使用，还可以作为插件的形式嵌入在Eclipse中使用。是一款免费的性能分析工具，使用起来非常方便。大家可以在
https://www.eclipse.org/mat/downloads.php下载并使用MAT。



MAT可以分析heapdump文件。在进行内存分析时，只要获得了反映当前设备内存映像的hprof文件，通过MAT打开就可以直观地看到当前的内存信息。

一般说来，这些内存信息包含：

- 所有的对象信息，包括对象实例、成员变量、存储于栈中的基本类型值和存储于堆中的其他对象的引用值。
- 所有的类信息，包括classloader、类名称、父类、静态变量等
- GCRoot到所有的这些对象的引用路径
- 线程信息，包括线程的调用栈及此线程的线程局部变量（TLS）



- **缺点**：MAT不是一个万能工具，它并不能处理所有类型的堆存储文件。但是比较主流的厂家和格式，例如Sun，HP，SAP 所采用的 HPROF 二进制堆存储文件，以及IBM 的 PHD 堆存储文件等都能被很好的
- **优点**：最吸引人的还是能够快速为开发人员生成**内存泄漏报表**，方便定位问题和分析问题。虽然MAT有如此强大的功能，但是内存分析也没有简单到一键完成的程度，很多内存问题还是需要我们从MAT展现给我们的信息当中通过经验和直觉来判断才能发现。





#### 3.1 浅堆和深堆

- 浅堆

浅堆(Shallow Heap)是指一个对象所消耗的内存。在32位系统中，一个对象引用会占据4个字节，一个 int类型会占据4个字节，long型变量会占据8个字节，每个对象头需要占用8个字节。根据堆快照格式不同，对象的大小可能会向8字节进行对齐。

以String为例：2个int值共占8字节，对象引用占用4字节，对象头8字节，合计20字节，向8字节对齐，故占24字节。（jdk7中）

![](JVM.assets/250.png)

这24字节为String对象的浅堆大小。它与String的value实际取值无关，无论字符串长度如何，浅堆大小始终是24字节。



- 深堆


**保留集(Retained Set)**：对象A的保留集指当对象A被垃圾回收后，可以被释放的所有的对象集合(包括对象A本身)，即对象A的保留集可以被认为是只能通过对象A被直接或间接访问到的所有对象的集合。通俗地说，就是指仅被对象A所持有的对象的集合。

**深堆(RetainedHeap)**：深堆是指对象的保留集中所有的对象的浅堆大小之和。

**注意**：浅堆指对象本身占用的内存，不包括其内部引用对象的大小。一个对象的深堆指只能通过该对象访问到的(直接或间接)所有对象的浅堆之和，即对象被回收后，可以释放的真实空间。



**补充：对象实际大小**

另外一个常用的概念是对象的实际大小。这里，对象的实际大小定义为一个对象**所能触及**的所有对象的浅堆大小之和，也就是通常意义上我们说的对象大小。与深堆相比，似乎这个在日常开发中更为直观和被人接受，但实际上，这个概念和垃圾回收无关。

下图显示了一个简单的对象引用关系图，对象A引用了C和D，对象B引用了C和E。那么对象A的浅堆大小只是A本身，不含C和D，而A的实际大小为A、C、D三者之和。而A的深堆大小为A与D之和，由于对象C还可以通过对象B访问到，因此不在对象A的深堆范围内。

![](JVM.assets/251.png)







#### 3.2 支配树

支配树（Dominator Tree）支配树的概念源自图论。

MAT提供了一个称为支配树（DominatorTree）的对象图。支配树体现了对象实例间的支配关系。在对象引用图中，所有指向对象B的路径都经过对象A，则认为对象A支配对象B。如果对象A是离对象B最近的一个支配对象，则认为对象A为对象B的直接支配者。支配树是基于对象间的引用图所建立的，它有以下基本性质：

- 对象A的子树（所有被对象A支配的对象集合）表示对象A的保留集（retainedset），即深堆。
- 如果对象A支配对象B，那么对象A的直接支配者也支配对象B。
- 支配树的边与对象引用图的边不直接对应。

如下图所示：左图表示对象引用图，右图表示左图所对应的支配树。对象A和B由根对象直接支配，由于在到对象C的路径中，可以经过A，也可以经过B，因此对象C的直接支配者也是根对象。对象F与对象 D相互引用，因为到对象F的所有路径必然经过对象D，因此，对象D是对象F的直接支配者。而到对象 D的所有路径中，必然经过对象C，即使是从对象F到对象D的引用，从根节点出发，也是经过对象C的，所以，对象D的直接支配者为对象C。

![](JVM.assets/252.png)

同理，对象E支配对象G。到达对象H的可以通过对象D，也可以通过对象E，因此对象D和E都不能支配对象H，而经过对象C既可以到达D也可以到达E，因此对象C为对象H的直接支配者。

在MAT中，单击工具栏上的对象支配树按钮，可以打开对象支配树视图。









### 4. 内存泄漏

**何为内存泄漏（memory leak）**

![](JVM.assets/253.png)

可达性分析算法来判断对象是否是不再使用的对象，本质都是判断一个对象是否还被引用。那么对于这种情况下，由于代码的实现不同就会出现很多种内存泄漏问题（让JVM误以为此对象还在引用中，无法回收，造成内存泄漏）。

**严格来说，只有对象不会再被程序用到了，但是GC又不能回收他们的情况，才叫内存泄漏。**

但实际情况很多时候一些不太好的实践（或疏忽）会导致对象的生命周期变得很长甚至导致OOM，也可以叫做宽泛意义上的“内存泄漏”。

![](JVM.assets/254.png)

对象X引用对象Y，X的生命周期比Y的生命周期长；

那么当Y生命周期结束的时候，X依然引用着Y，这时候，垃圾回收期是不会回收对象Y的；

如果对象X还引用着生命周期比较短的A、B、C，对象A又引用着对象a、b、c，这样就可能造成大量无用的对象不能被回收，进而占据了内存资源，造成内存泄漏，直到内存溢出。



**内存泄漏与内存溢出的关系**：

1. 内存泄漏（memory leak）

   申请了内存用完了不释放，比如一共有1024M的内存，分配了512M的内存一直不回收，那么可以用的内存只有512M了，仿佛泄露掉了一部分；

   通俗一点讲的话，内存泄漏就是【占着茅坑不拉shi】。



2. 内存溢出（out of memory）

   申请内存时，没有足够的内存可以使用；

   通俗一点儿讲，一个厕所就三个坑，有两个站着茅坑不走的（内存泄漏），剩下最后一个坑，厕所表示接待压力很大，这时候一下子来了两个人，坑位（内存）就不够了，内存泄漏变成内存溢出了。

   可见，内存泄漏和内存溢出的关系：内存泄漏的增多，最终会导致内存溢出。



**泄漏的分类**

- **经常发生**：发生内存泄露的代码会被多次执行，每次执行，泄露一块内存；
- **偶然发生**：在某些特定情况下才会发生；
- **一次性**：发生内存泄露的方法只会执行一次；
- **隐式泄漏**：一直占着内存不释放，直到执行结束；严格的说这个不算内存泄漏，因为最终释放掉了，但是如果执行时间特别长，也可能会导致内存耗尽。





#### 4.1 内存泄漏的八种情况

1. 静态集合类

静态集合类，如HashMap、LinkedList等等。如果这些容器为静态的，那么它们的生命周期与 JVM程序一致，则容器中的对象在程序结束之前将不能被释放，从而造成内存泄漏。简单而言，长生命周期的对象持有短生命周期对象的引用，尽管短生命周期的对象不再使用，但是因为长生命周期对象持有它的引用而导致不能被回收。

```java
public class MemoryLeak {
    
    static List list = new ArrayList();
    
    public void oomTests() {
        Object obj = new Object();//局部变量
        list.add(obj);
    }
    
}
```



2. 单例模式

单例模式，和静态集合导致内存泄露的原因类似，因为单例的静态特性，它的生命周期和JVM的生命周期一样长，所以如果单例对象如果持有外部对象的引用，那么这个外部对象也不会被回收，那么就会造成内存泄漏。



3. 内部类持有外部类

内部类持有外部类，如果一个外部类的实例对象的方法返回了一个内部类的实例对象。

这个内部类对象被长期引用了，即使那个外部类实例对象不再被使用，但由于内部类持有外部类的实例对象，这个外部类对象将不会被垃圾回收，这也会造成内存泄漏。



4. 各种连接，如数据库连接、网络连接和IO连接等

各种连接，如数据库连接、网络连接和I0连接等。

在对数据库进行操作的过程中，首先需要建立与数据库的连接，当不再使用时，需要调用close方法来释放与数据库的连接。只有连接被关闭后，垃圾回收器才会回收对应的对象。

否则，如果在访问数据库的过程中，对Connection、Statement或ResultSet不显性地关闭，将会造成大量的对象无法被回收，从而引起内存泄漏。

```java
public static void main(String[] args) {
    try {
        Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver"); 
        conn = DriverManager.getConnection("url", "", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("...")
    } catch (Excetion e) {//异常日志
        
    } finally {
        //1.关闭结果集Statement
		//2.关闭声明的对象ResuLtSet
        //3.关闭连接Connection
    }
}
```



5. 变量不合理的作用域

变量不合理的作用域。一般而言，一个变量的定义的作用范围大于其使用范围，很有可能会造成内存泄漏。另一方面，如果没有及时地把对象设置为null，很有可能导致内存泄漏的发生。

```java
public class UsingRandom {
    private String msg;
    public void receiveMsg(){
        //private String msg;
    	readFromNet();//从网络中接受数据保存到msg中 
        saveDB();//把msg保存到数据库中
        //msg = null;
    }
}
```

如上面这个伪代码，通过readFromNet方法把接受的消息保存在变量msg中，然后调用saveDB方法把 msg的内容保存到数据库中，此时msg已经就没用了，由于msg的生命周期与对象的生命周期相同，此时msg还不能回收，因此造成了内存泄漏。

实际上这个msg变量可以放在receiveMsg方法内部，当方法使用完，那么msg的生命周期也就结束，此时就可以回收了。还有一种方法，在使用完msg后，把msg设置为null，这样垃圾回收器也会回收



6. 改变哈希值

改变哈希值，当一个对象被存储进HashSet集合中以后，就不能修改这个对象中的那些参与计算哈希值的字段了。

否则，对象修改后的哈希值与最初存储进HashSet集合中时的哈希值就不同了，在这种情况下，即使在 contains方法使用该对象的当前引用作为的参数去HashSet集合中检索对象，也将返回找不到对象的结果，这也会导致无法从HashSet集合中单独删除当前对象，造成内存泄漏。 

这也是String为什么被设置成了不可变类型，我们可以放心地把String存入HashSet，或者把 String当做HashMap的key值；

当我们想把自已定义的类保存到散列表的时候，需要保证对象的hashcode不可变。

```java
/**
 * 演示内存泄漏
 * @author shkstart
 * @create 14:47
 */
public class ChangeHashCode1 {
    public static void main(String[] args) {
        HashSet<Point> hs = new HashSet<Point>();
        Point cc = new Point();
        cc.setX(10);//hashCode = 41
        hs.add(cc);

        cc.setX(20);//hashCode = 51  此行为导致了内存的泄漏

        System.out.println("hs.remove = " + hs.remove(cc));//false
        hs.add(cc);
        System.out.println("hs.size = " + hs.size());//size = 2

        System.out.println(hs);
    }

}

class Point {
    int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        if (x != other.x) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                '}';
    }
}

```





7. 缓存泄漏

内存泄漏的另一个常见来源是缓存，一旦你把对象引用放入到缓存中，他就很容易遗忘。比如：之前项目在一次上线的时候，应用启动奇慢直到夯死，就是因为代码中会加载一个表中的数据到缓存（内存）中，测试环境只有几百条数据，但是生产环境有几百万的数据。

对于这个问题，可以使用WeakHashMap代表缓存，此种Map的特点是，当除了自身有对key的引用外，此key没有其他引用那么此map会自动丢弃此值。

```java
/**
 * 演示内存泄漏
 *
 * @author shkstart
 * @create 14:53
 */
public class MapTest {
    static Map wMap = new WeakHashMap();
    static Map map = new HashMap();

    public static void main(String[] args) {
        init();
        testWeakHashMap();
        testHashMap();
    }

    public static void init() {
        String ref1 = new String("obejct1");
        String ref2 = new String("obejct2");
        String ref3 = new String("obejct3");
        String ref4 = new String("obejct4");
        wMap.put(ref1, "cacheObject1");
        wMap.put(ref2, "cacheObject2");
        map.put(ref3, "cacheObject3");
        map.put(ref4, "cacheObject4");
        System.out.println("String引用ref1，ref2，ref3，ref4 消失");

    }

    public static void testWeakHashMap() {

        System.out.println("WeakHashMap GC之前");
        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }
        try {
            System.gc();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WeakHashMap GC之后");
        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }
    }

    public static void testHashMap() {
        System.out.println("HashMap GC之前");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }
        try {
            System.gc();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HashMap GC之后");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }
    }

}
/**
 * 结果
 * String引用ref1，ref2，ref3，ref4 消失
 * WeakHashMap GC之前
 * obejct2=cacheObject2
 * obejct1=cacheObject1
 * WeakHashMap GC之后
 * HashMap GC之前
 * obejct4=cacheObject4
 * obejct3=cacheObject3
 * Disconnected from the target VM, address: '127.0.0.1:51628', transport: 'socket'
 * HashMap GC之后
 * obejct4=cacheObject4
 * obejct3=cacheObject3
 **/

```



![](JVM.assets/255.png)

上面代码和图示主演演示WeakHashMap如何自动释放缓存对象，当init函数执行完成后，局部变量字符串引用weakd1,weakd2,d1,d2都会消失，此时只有静态map中保存中对字符串对象的引用，可以看到，调用gc之后，HashMap的没有被回收，而WeakHashMap里面的缓存被回收了。



8. 监听器和回调

内存泄漏第三个常见来源是监听器和其他回调，如果客户端在你实现的API中注册回调，却没有显示的取消，那么就会积聚。

需要确保回调立即被当作垃圾回收的最佳方法是只保存它的弱引用，例如将他们保存成为WeakHashMap中的键。





### 5. 支持使用OQL语言查询对象信息

MAT支持一种类似于SQL的查询语言OQL（Object Query Language）。OQL使用类 SQL语法，可以在堆中进行对象的查找和筛选。



#### 5.1 SELECT子句

在MAT中，Select子句的格式与SQL基本一致，用于指定要显示的列。Select子句中可以使
用“*”，查看结果对象的引用实例（相当于outgoing references）。 

SELECT * FROM java.util.Vector V

使用“OBJECTS”关键字，可以将返回结果集中的项以对象的形式显示。
SELECT objects v.elementData FROM java.util.Vector v 
SELECT OBJECTS s.value FROM java.lang.String s

在Select子句中，使用“AS RETAINED SET”关键字可以得到所得对象的保留集。
SELECT AS RETAINED SET * FROM com.atguigu.mat.Student

“DISTINCT”关键字用于在结果集中去除重复对象。
SELECT DISTINCT OBJECTS classof(s) FROM java.lang.String s





#### 5.2 FROM子句

From子句用于指定查询范围，它可以指定类名、正则表达式或者对象地址。
SELECT * FROM java.lang.String s

下例使用正则表达式，限定搜索范围，输出所有com.atguigu包下所有类的实例
SELECT * FROM "com\ .atguigu\ ..*"

也可以直接使用类的地址进行搜索。使用类的地址的好处是可以区分被不同ClassLoader加载的同一种类型。
select * from 0x37a0b4d



#### 5.3 WHERE子句

Where子句用于指定OQL的查询条件。OQL查询将只返回满足Where子句指定条件的对象。
Where子句的格式与传统SQL极为相似。

下例返回长度大于10的char数组。
SELECT * FROM char[] s WHERE s.@length>10

下例返回包含“java”子字符串的所有字符串，使用“LIKE”操作符，“LIKE”操作符的操作参数为正则表达式。
SELECT * FROM java.lang.String s WHERE toString(s) LIKE ".*java. *" 

下例返回所有value域不为null的字符串，使用“=”操作符。
SELECT * FROM java.lang.String S where s.value!=nul1

Where子句支持多个条件的AND、OR运算。下例返回数组长度大于15，并且深堆大于1000字节的所有Vector对象。
SELECT * FROM java.util.Vector v WHERE v.elementData.@length>15 AND v.@retainedHeapSize>1000



#### 5.4 内置对象和方法

OQL中可以访问堆内对象的属性，也可以访问堆内代理对象的属性。访问堆内对象的属性时，格式如下：
[<alias>.］<field>.<field>.<field> 
其中alias为对象名称。

访问java.io.File对象的path属性，并进一步访问path的value属性：
SELECT toString(f.path.value) FROM java.io.File f

下例显示了String对象的内容、objectid和objectAddress。
SELECT s.toString(), s.@objectId, s.@objectAddress FROM java.lang.String s

下例显示java.util.Vector内部数组的长度。
SELECT v.elementData.@length FROM java.util.Vector 

下例显示了所有的java.util.Vector对象及其子类型
select * from INSTANCEOF java.util.Vector







### 6. JProfiler

在运行Java的时候有时候想测试运行时占用内存情况，这时候就需要使用测试工具查看了。在eclipse里面有Eclipse Memory Analyzertool(MAT)插件可以测试，而在IDEA中也有这么一个插件，就是JProfiler。

JProfiler 是由 ej-technologies 公司开发的一款 Java 应用性能诊断工具。功能强大，但是收费。

官网下载地址：https://www.ej-technologies.com/products/jprofiler/overview.html



特点：

- 使用方便、界面操作友好（简单且强大）
- 对被分析的应用影响小(提供模板）
- CPU, Thread, Memory分析功能尤其强大
- 支持对jdbc, noSql, jsp, servlet, socket等进行分析
- 支持多种模式(离线，在线)的分析
- 支持监控本地、远程的JVM
- 跨平台，拥有多种操作系统的安装版本



主要功能

1. 方法调用：对方法调用的分析可以帮助您了解应用程序正在做什么，并找到提高其性能的方法
2. 内存分配：通过分析堆上对象、引用链和垃圾收集能帮您修复内存泄漏问题，优化内存使用
3. 线程和锁：JProfiler提供多种针对线程和锁的分析视图助您发现多线程问题
4. 高级子系统：许多性能问题都发生在更高的语义级别上。例如，对于JDBC调用，您可能希望找出执行最慢的SQL语句。JProfiler支持对这些子系统进行集成分析





### 7. Arthas

前面，我们介绍了jdk自带的jvisualvm等免费工具，以及商业化工具Jprofiler。

这两款工具在业界知名度也比较高，他们的优点是可以图形界面上看到各维度的性能数据，使用者根据这些数据进行综合分析，然后判断哪里出现了性能问题。

但是这两款工具也有个缺点，都必须在服务端项目进程中配置相关的监控参数。然后工具通过远程连接到项目进程，获取相关的数据。这样就会带来一些不便，比如线上环境的网络是隔离的，本地的监控工具根本连不上线上环境。并且类似于Jprofiler这样的商业工具，是需要付费的。

那么有没有一款工具不需要远程连接，也不需要配置监控参数，同时也提供了丰富的性能监控数据呢?

今天跟大家介绍一款阿里巴巴开源的性能分析神器Arthas（阿尔萨斯）



**概述**

Arthas（阿尔萨斯）是Alibaba开源的Java诊断工具，深受开发者喜爱。在线排查问题，无需重启；动态跟踪Java代码；实时监控JVM状态。

Arthas支持JDK6+，支持Linux/Mac/Windows，采用命令行交互模式，同时提供丰富的Tab自动补全功能，进一步方便进行问题的定位和诊断。

当你遇到以下类似问题而束手无策时，Arthas可以帮助你解决：

- 这个类从哪个jar包加载的？为什么会报各种类相关的Exception？
- 我改的代码为什么没有执行到？难道是我没commit？分支搞错了？
- 遇到问题无法在线上debug，难道只能通过加日志再重新发布吗？
- 线上遇到某个用户的数据处理有问题，但线上同样无法debug，线下无法重现！
- 是否有一个全局视角来查看系统的运行状况？
- 有什么办法可以监控到JVM的实时运行状态？





### 8. Java Mission Control

在Oracle收购Sun之前，Oracle的JRockit虚拟机提供了一款叫做JRockit Mission Control 的虚拟机诊断工具。

在Oracle收购Sun之后，Oracle公司同时拥有了Sun Hotspot和JRockit两款虚拟机。根据 Oracle对于Java的战略，在今后的发展中，会将JRockit的优秀特性移植到Hotspot上。其中一个重要的改进就是在Sun的JDK中加入了JRockit的支持。

在OracleJDK7u40之后，MissionControl这款工具已经绑定在0racleJDk中发布。

自Java11开始，本节介绍的JFR已经开源。但在之前的Java版本，JFR属于
Commercial Feature，需要通过Java虚拟机参数-XX:+UnlockCommercialFeatures开启。

如果你有兴趣请可以查看OpenJDk的MissionControl项目。 
https://github.com/JDKMissionControl/jmc



Java Mission Control位于%JAVA_HoME%/bin/jmc.exe，打开这款软件。

![](JVM.assets/256.png)



**概述**

Java Mission Control（简称JMC），Java官方提供的性能强劲的工具。是一个用于对 Java应用程序进行管理、监视、概要分析和故障排除的工具套件。

它包含一个GUI客户端，以及众多用来收集Java虚拟机性能数据的插件，如JMX Console（能够访问用来存放虚拟机各个子系统运行数据的MXBeans），以及虚拟机内置的高效 profiling 工具 Java Flight Recorder（JFR）

JMC的另一个优点就是：采用取样，而不是传统的代码植入技术，对应用性能的影响非常非常小，完全可以开着JMC来做压测（唯一影响可能是full gc多了）。





### 9. 其他工具

#### 9.1 Flame Graphs(火焰图)

在追求极致性能的场景下，了解你的程序运行过程中cpu在干什么很重要，火焰图就是一种非常直观的展示cpu在程序整个生命周期过程中时间分配的工具。

火焰图对于现代的程序员不应该陌生，这个工具可以非常直观的显示出调用栈中的CPU消耗瓶颈。

网上的关于java火焰图的讲解大部分来自于BrendanGregg的博客： http://www.brendangregg.com/flamegraphs.html

![](JVM.assets/257.png)

火焰图，简单通过x轴横条宽度来度量时间指标，y轴代表线程栈的层次。





#### 9.2 Tprofiler

**案例**

使用JDK自身提供的工具进行JVM调优可以将TPS由2.5提升到20(提升了7倍)，并准确定位系统瓶颈。

系统瓶颈有：应用里静态对象不是太多、有大量的业务线程在频繁创建一些生命周期很长的临时对象，代码里有问题。

那么，如何在海量业务代码里边准确定位这些性能代码？这里使用阿里开源工具TProfiler来定位这些性能代码，成功解决掉了GC过于频繁的性能瓶颈，并最终在上次优化的基础上将TPS再提升了4倍，即提升到100。

- TProfiler配置部署、远程操作、日志阅读都不太复杂，操作还是很简单的。但是其却是能够起到一针见血、立竿见影的效果，帮我们解决了GC过于频繁的性能瓶颈。
- TProfiler最重要的特性就是能够统计出你指定时间段内JVM的top method，这些top method极有可能就是造成你JVM性能瓶颈的元凶。这是其他大多数JVM调优工具所不具备的，包括JRockit Mission Control。JRokit首席开发者Marcus Hirt在其私人博客《Low Overhead Method Profiling with Java Mission Control》下的评论中曾明确指出 JRMC并不支持TOP方法的统计。



#### 9.3 Btrace

**Java运行时追踪工具**

常见的动态追踪工具有BTrace、HouseMD（该项目已经停止开发）、Greys-Anatomy（国人开发，个人开发者）、Byteman（JBoss出品），注意Java运行时追踪工具并不限于这几种，但是这几个是相对比较常用的。

BTrace是SUN Kenai云计算开发平台下的一个开源项目，旨在为java提供安全可靠的动态跟踪分析工具。

BTrace是一个Java平台的安全的动态追踪工具。可以用来动态地追踪一个运行的Java程序。 BTrace动态调整目标应用程序的类以注入跟踪代码（“字节码跟踪”）





## 四、JVM运行时参数

### 1. JVM参数选项类型

#### 1.1 类型一：标准参数选项

> 特点：比较稳定，后续版本基本不会变化(以-开头)。

运行java或者java -help可以看到所有的标准选项



补充内容：-server与-client

Hotspot JVM有两种模式，分别是server和client，分别通过-server和-client模式设置

1. 在32位Windows系统上，默认使用client类型的JVM。要想使用Server模式，则机器配置至少有2个以上的CPU和2G以上的物理内存。client模式适用于对内存要求较小的桌面应用程序，默认使用Serial串行垃圾收集器。
2. 64位机器上只支持server模式的JVM，适用于需要大内存的应用程序，默认使用并行垃圾收集器。



#### 1.2 类型二：-X参数选项

特点：

- 非标准化参数
- 功能还是比较稳定的。但官方说后续版本可能会变更
- 以-X开头

运行java -X命令可以看到所有的X选项



JVM的JIT编译模式相关的选项

- -Xint：禁用JIT，所有字节码都被解释执行，这个模式的速度最慢的
- -Xcomp：所有字节码第一次使用就都被编译成本地代码，然后再执行
- -Xmixed：混合模式，默认模式，让JIT根据程序运行的情况，采用解释器+即时编译器的混合模式共同执行程序。



-Xmx-Xms-Xss属于XX参数？

- -Xms<size>：设置初始Java堆大小，等价于-XX:lnitialHeapSize
- -Xmx<size>：设置最大Java堆大小，等价于-XX:MaxHeapSize
- -Xss<size>：设置Java线程堆栈大小，-XX:ThreadStackSize





#### 1.3 类型三：-XX参数选项

特点：

- 非标准化参数
- 使用的最多的参数类型
- 这类选项属于实验性，不稳定
- 以-XX开头

作用：用于开发和调试JVM



分类：

- Boolean类型格式
  - -XX:+<option>表示启用option属性
  - -XX:-<option>表示禁用option属性
- 非Boolean类型格式（key-value类型）
  - 子类型1：数值型格式-XX:<option>=<number>
  - 子类型2：非数值型格式-XX:<name>=<string>



特别：-XX:+PrintFlagsFinal

- 输出所有参数的名称和默认值
- 默认不包括Diagnostic和Experimental的参数
- 可以配合-XX:+UnlockDiagnosticVMOptions和-XX:UnlockExperimentalVMOptions使用





### 2. 添加JVM参数选项

**IDEA**

![](JVM.assets/258.png)



**运行jar包**

java -Xms50m -Xmx50m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -jar demo.jar



**通过Tomcat运行war包**

- Linux系统下可以在tomcat/bin/catalina.sh中添加类似如下配置： JAVA_OPTS="-Xms512M -Xmx1024M"
- Windows系统下在catalina.bat中添加类似如下配置： set "JAVA_OPTS=-Xms512M -Xmx1024M"



**程序运行过程中**

- 使用jinfo -flag <name>=<value> <pid> 设置非Boolean类型参数
- 使用jinfo -flag [+I-]<name> <pid> 设置Boolean类型参数





### 3. 常见的JVM参数选项

#### 3.1 打印设置的XX选项及值

- -XX:+PrintCommandLineFlags：可以让在程序运行前打印出用户手动设置或者VM自动设置的XX选项
- -XX:+PrintFlagslnitial：表示打印出所有XX选项的默认值
- -XX:+PrintFlagsFinal：表示打印出XX选项在运行程序时生效的值
- -XX:+PrintVMOptions：打印JVM的参数



#### 3.2 堆、栈、方法区等内存大小设置

- 栈

  - -Xss128k：设置每个线程的栈大小为128k

- 堆

  - -Xms3550m：等价于-XX:lnitialHeapSize，设置JVM初始堆内存为3550M
  - -Xmx3550m：等价于-XX:MaxHeapSize，设置JVM最大堆内存为3550M
  - -Xmn2g：设置年轻代大小为2G（官方推荐配置为整个堆大小的3/8）
  - -XX:NewSize=1024m：设置年轻代初始值为1024M
  - -XX:MaxNewSize=1024m：设置年轻代最大值为1024M
  - -XX:SurvivorRatio=8：设置年轻代中Eden区与一个Survivor区的比值，默认为8
  - -XX:+UseAdaptiveSizePolicy：自动选择各区大小比例
  - -XX:NewRatio=4：设置老年代与年轻代（包括1个Eden和2个Survivor区）的比值
  - -XX:PretenureSizeThreadshold=1024：设置让大于此阔值的对象直接分配在老年代，单位为字节（只对Serial、ParNew收集器有效）
  - -XX:MaxTenuringThreshold=15：默认值为15，新生代每次MinorGC后，还存活的对象年龄+1，当对象的年龄大于设置的这个值时就进入老年代
  - -XX:+PrintTenuringDistribution：让JVM在每次MinorGC后打印出当前使用的Survivor中对象的年龄分布
  - -XX:TargetSurvivorRatio：表示MinorGC结束后Survivor区域中占用空间的期望比例

- 方法区

  - 永久代
    - -XX:PermSize=256m：设置永久代初始值为256M
    - -XX:MaxPermSize=256m：设置永久代最大值为256M
  - 元空间
    - -XX:MetaspaceSize：初始空间大小
    - -XX:MaxMetaspaceSize：最大空间，默认没有限制
    - -XX:+UseCompressedOops：压缩对象指针
    - -XX:+UseCompressedClassPointers：压缩类指针
    - -XX:CompressedClassSpaceSize：设置Klass Metaspace的大小，默认1G

- 直接内存

  - -XX:MaxDirectMemorySize：指定DirectMemory容量，若未指定，则默认与Java堆最大值一样

  

#### 3.3 OutofMemory相关的选项

- -XX:+HeapDumpOnOutOfMemoryError：表示在内存出现OOM的时候，把Heap转存（Dump）到文件以便后续分析
- -XX:+HeapDumpBeforeFullGC：表示在出现FulIGC之前，生成Heap转储文件
- -XX:HeapDumpPath=<path>：指定heap转存文件的存储路径
- -XX:OnOutOfMemoryError：指定一个可行性程序或者脚本的路径，当发生OOM的时候，去执行这个脚本



#### 3.4 垃圾收集器相关选项

7款经典收集器与垃圾分代之间的关系

![](JVM.assets/259.png)



##### 3.4.1 查看默认垃圾收集器

- -XX:+PrintCommandLineFlags：查看命令行相关参数（包含使用的垃圾收集器）

- 使用命令行指令：jinfo -flag 相关垃圾回收器参数 进程ID



##### 3.4.2 Serial回收器

Serial收集器作为HotSpot中Client模式下的默认新生代垃圾收集器。Serial Old是运行在Client模式下默认的老年代的垃圾回收器。

-XX:+UseSerialGC：指定年轻代和老年代都使用串行收集器。等价于新生代用Serial GC，且老年代用 Serial Old GC。可以获得最高的单线程收集效率。



##### 3.4.3 ParNew回收器

-XX:+UseParNewGC：手动指定使用ParNew收集器执行内存回收任务。它表示年轻代使用并行收集器，不影响老年代。

-XX:ParallelGCThreads=N：限制线程数量，默认开启和CPU数据相同的线程数。



##### 3.4.4 Parallel回收器

- -XX:+UseParallelGC：手动指定年轻代使用Parallel并行收集器执行内存回收任务。
- -XX:+UseParallelOldGC：手动指定老年代都是使用并行回收收集器。
  - 分别适用于新生代和老年代。默认jdk8是开启的。
  - 上面两个参数，默认开启一个，另一个也会被开启。（互相激活）
- -XX:ParallelGCThreads：设置年轻代并行收集器的线程数。一般地，最好与CPU数量相等，以避免过多的线程数影响垃圾收集性能。
  - 在默认情况下，当CPU数量小于8个，ParallelGCThreads的值等于CPU数量。
  - 当CPU数量大于8个，ParallelGCThreads的值等于3+[5*CPU_Count]/8]。
- -XX:MaxGCPauseMillis：设置垃圾收集器最大停顿时间（即STW的时间）。单位是毫秒。
  - 为了尽可能地把停顿时间控制在MaxGCPauseMills以内，收集器在工作时会调整Java堆大小或者其他一些参数。
  - 对于用户来讲，停顿时间越短体验越好。但是在服务器端，我们注重高并发，整体的吞吐量。所以服务器端适合Parallel，进行控制。
  - 该参数使用需谨慎。
- -XX:GCTimeRatio：垃圾收集时间占总时间的比例（=1 /（N+1））。用于衡量吞吐量的大小。
  - 取值范围（0，100）。默认值99，也就是垃圾回收时间不超过1%。
  - 与前一个-XX：MaxGCPauseMillis参数有一定矛盾性。暂停时间越长，Radio参数就容易超过设定的比例。
- -XX:+UseAdaptiveSizePolicy：设置ParallelScavenge收集器具有自适应调节策略
  - 在这种模式下，年轻代的大小、Eden和Survivor的比例、晋升老年代的对象年龄等参数会被自动调整，已达到在堆大小、吞吐量和停顿时间之间的平衡点。
  - 在手动调优比较困难的场合，可以直接使用这种自适应的方式，仅指定虚拟机的最大堆、目标的吞吐量（GCTimeRatio）和停顿时间（MaxGCPauseMills），让虚拟机自己完成调优工作。





##### 3.4.5 CMS回收器

- -XX:+UseConcMarkSweepGC：手动指定使用CMS收集器执行内存回收任务。
  - 开启该参数后会自动将-XX:+UseParNewGC打开。即：ParNew(Young区用)+CMS(Old区用)+Serial Old的组合。
- -XX:CMSInitiatingOccupanyFraction：设置堆内存使用率的阈值，一且达到该阈值，便开始进行回收。
  - JDK5及以前版本的默认值为68，即当老年代的空间使用率达到68%时，会执行一次CMS回收。 JDK6及以上版本默认值为92%
  - 如果内存增长缓慢，则可以设置一个稍大的值，大的阅值可以有效降低CMS的触发频率，减少老年代回收的次数可以较为明显地改善应用程序性能。反之，如果应用程序内存使用率增长很快，则应该降低这个阅值，以避免频繁触发老年代串行收集器。因此通过该选项便可以有效降低Full GC的执行次数。
- -XX:+UseCMSCompactAtFullcollection：用于指定在执行完Full GC后对内存空间进行压缩整理：以此避免内存碎片的产生。不过由于内存压缩整理过程无法并发执行，所带来的问题就是停顿时间变得更长了。
- -XX:CMSFullGCsBeforeCompaction：设置在执行多少次FullGC后对内存空间进行压缩整理。
- -XX:ParallelcMSThreads设置CMS的线程数量。
  - CMS默认启动的线程数是（ParallelGCThreads+3）/ 4，ParallelGCThreads是年轻代并行收集器的线程数。当CPU资源比较紧张时，受到CMS收集器线程的影响，应用程序的性能在



**补充参数。另外，CMS收集器还有如下常用参数：**

- -XX:ConcGCThreads：设置并发垃圾收集的线程数，默认该值是基于ParallelGCThreads计算出来的；
- -XX:+UseCMSInitiatingOccupancyOnly：是否动态可调，用这个参数可以使cMS一直按 CMSInitiatingOccupancyFraction设定的值启动
- -XX:+CMSScavengeBeforeRemark：强制hotspot虚拟机在cmsremark阶段之前做一次minor gc，用于提高remark阶段的速度；
- -XX：+CMSClassUnloadingEnable：如果有的话，启用回收Perm区（JDK8之前）
- -XX：+CMSParallelInitialEnabled：用于开启cMSinitial-mark阶段采用多线程的方式进行标记，用于提高标记速度，在Java8开始已经默认开启；
- -XX：+CMSParallelRemarkEnabled：用户开启cMSremark阶段采用多线程的方式进行重新标记，默认开启；
- -XX:+ExplicitGCInvokesConcurrent、-XX:+ExplicitGCInvokesConcurrentAndunloadsClasses：这两个参数用户指定hotspot虚拟在执行System.gc()时使用CMS周期；
- -XX:+CMSPrecleaningEnabled：指定CMS是否需要进行Precleaning这个阶段



**特别说明**

- JDK9新特性：CMS被标记为Deprecate了（JEP291）
  - 如果对JDK9及以上版本的HotSpot虚拟机使用参数-XX:+UseConcMarkSweepGC来开启CMS收集器的话，用户会收到一个警告信息，提示 CMS未来将会被废弃。
- JDK14新特性：删除CMS垃圾回收器（JEP363）
  - 移除了CMS垃圾收集器，如果在JDK14中使用-XX:+UseConcMarkSweepGC的话，JVM不会报错，只是给出一个warning信息，但是不会exit。JVM会自动回退以默认GC方式启动JVM



##### 3.4.6 G1回收器

- -XX:+UseG1GC：手动指定使用G1收集器执行内存回收任务。
- -XX:G1HeapRegionSize：设置每个Region的大小。值是2的幂，范围是1MB到32MB之间，目标是根据最小的Java堆大小划分出约2048个区域。默认是堆内存的1/2000。
- -XX:MaxGCPauseMillis：设置期望达到的最大GC停顿时间指标（JVM会尽力实现，但不保证达到）。默认值是200ms
- -XX:ParallelGCThread：设置STW时GC线程数的值。最多设置为8
- -XX:ConcGCThreads：设置并发标记的线程数。将n设置为并行垃圾回收线程数（ParallelGCThreads）的1/4左右。
- -XX:InitiatingHeapOccupancyPercent：设置触发并发GC周期的Java堆占用率阙值。超过此值，就触发GC。默认值是45。
- -XX:G1NewSizePercent、-XX:G1MaxNewSizePercent：新生代占用整个堆内存的最小百分比（默认5%）、最大百分比（默认60%）
- -XX:G1ReservePercent=10：保留内存区域，防止tospace（Survivor中的to区）溢出



**MixedGC调优参数**

注意：G1收集器主要涉及到Mixed GC，MixedGC会回收young区和部分old区。

G1关于MixedGC调优常用参数：

- -XX:InitiatingHeapOccupancyPercent：设置堆占用率的百分比（0到100）达到这个数值的时候触发global concurrent marking（全局并发标记），默认为 45%。值为0表示间断进行全局并发标记。
- -XX:G1MixedGCLiveThresholdPercent：设置old区的region被回收时候的对象占比，默认占用率为85%。只有old区的region中存活的对象占用达到了这个百分比，才会在MixedGC中被回收。
- -XX:G1HeapWastePercent：在global concurrent marking（全局并发标记）结束之后，可以知道所有的区有多少空间要被回收，在每次youngGC之后和再次发生MixedGC之前，会检查垃圾占比是否达到此参数，只有达到了，下次才会发生 Mixed GC。
- -XX:G1MixedGCCountTarget：一次global concurrent marking（全局并发标记）之后，最多执行MixedGC的次数，默认是8。
- -XX:G1OldCSetRegionThresholdPercent：设置MixedGC收集周期中要收集的 0ldregion数的上限。默认值是Java堆的10%





##### 3.4.7 怎么选择垃圾回收器

- 优先调整堆的大小让JVM自适应完成。
- 如果内存小于100M，使用串行收集器
- 如果是单核、单机程序，并且没有停顿时间的要求，串行收集器
- 如果是多CPU、需要高吞吐量、允许停顿时间超过1秒，选择并行或者JVM自己选择
- 如果是多CPU、追求低停顿时间，需快速响应（比如延迟不能超过1秒，如互联网应用），使用并发收集器。官方推荐G1，性能高。现在互联网的项目，基本都是使用G1。

特别说明：

1. 没有最好的收集器，更没有万能的收集；
2. 调优永远是针对特定场景、特定需求，不存在一劳永逸的收集器





#### 3.5 GC日志相关选项

##### 3.5.1 常用参数

- -verbose:gc：输出gc日志信息，默认输出到标准输出
- -XX:+PrintGC：等同于-verbose:gc，表示打开简化的GC日志
- -XX:+PrintGCDetails：在发生垃圾回收时打印内存回收详细的日志，并在进程退出时输出当前内存各区域分配情况
- -XX:+PrintGCTimeStamps：输出GC发生时的时间戳
- -XX:+PrintGCDateStamps：输出GC发生时的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800)
- -XX:+PrintHeapAtGC：每一次GC前和GC后，都打印堆信息
- -Xloggc:<file> ：把GC日志写入到一个文件中去，而不是打印到标准输出中





##### 3.5.2 其他参数

- -XX:+TraceClassLoading：监控类的加载
- -XX:+PrintGCApplicationStoppedTime：打印GC时线程的停顿时间
- -XX:+PrintGCApplicationConcurrentTime：垃圾收集之前打印出应用未中断的执行时间
- -XX:+PrintReferenceGC：记录回收了多少种不同引用类型的引用
- -XX:+PrintTenuringDistribution：让JVM在每次MinorGC后打印出当前使用的Survivor中对象的年龄分布
- -XX:+UseGCLogFileRotation：启用GC日志文件的自动转储
- -XX:NumberOfGClogFiles=1：GC日志文件的循环数目控制
- -XX:GCLogFileSize=1M：GC日志文件的大小





#### 3.6 其他参数

- -XX:+DisableExplicitGC：禁止hotspot执行System.gc()，默认禁用
- -XX:ReservedCodeCacheSize=<n>[glm|k]、-XX:InitialCodeCacheSize=<n>[g|m|k]：指定代码缓存的大小
- -XX:+UseCodeCacheFlushing：使用该参数让jvm放弃一些被编译的代码，避免代码缓存被占满时JVM切换到interpreted-only的情况
- -XX:+DoEscapeAnalysis：开启逃逸分析
- -XX:+UseBiasedLocking：开启偏向锁
- -XX:+UseLargePages：开启使用大页面
- -XX:+UseTLAB：使用TLAB，默认打开
- -XX:+PrintTLAB：打印TLAB的使用情况
- -XX:TLABSize：设置TLAB大小





### 4. 通过Java代码获取JVM参数

Java提供了java.lang.management包用于监视和管理Java虚拟机和Java运行时中的其他组件，它允许本地和远程监控和管理运行的Java虚拟机。其中ManagementFactory这个类还是挺常用的。另外还有Runtime类也可以获取一些内存、CPU核数等相关的数据。

通过这些api可以监控我们的应用服务器的堆内存使用情况，设置一些阀值进行报警等处理。

```java
package com.atguigu.java;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 *
 * 监控我们的应用服务器的堆内存使用情况，设置一些阈值进行报警等处理
 *
 * @author shkstart
 * @create 15:23
 */
public class MemoryMonitor {
    public static void main(String[] args) {
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit() / 1024 / 1024 + "m");
        System.out.println("MAX HEAP: " + usage.getMax() / 1024 / 1024 + "m");
        System.out.println("USE HEAP: " + usage.getUsed() / 1024 / 1024 + "m");
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: " + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: " + memorymbean.getNonHeapMemoryUsage());

        System.out.println("=======================通过java来获取相关系统状态============================ ");
        System.out.println("当前堆内存大小totalMemory " + (int) Runtime.getRuntime().totalMemory() / 1024 / 1024 + "m");// 当前堆内存大小
        System.out.println("空闲堆内存大小freeMemory " + (int) Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");// 空闲堆内存大小
        System.out.println("最大可用总堆内存maxMemory " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m");// 最大可用总堆内存大小

    }
}
```





## 五、分析GC日志

### 1. GC日志参数

- -verbose:gc：输出gc日志信息，默认输出到标准输出
- -XX:+PrintGC：输出GC日志。类似：-verbose:gc
- -XX:+PrintGCDetails：在发生垃圾回收时打印内存回收详细的日志，并在进程退出时输出当前内存各区域分配情况
- -XX:+PrintGCTimeStamps：输出GC发生时的时间戳
- -XX:+PrintGCDateStamps：输出GC发生时的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800)
- -XX:+PrintHeapAtGC：每一次GC前和GC后，都打印堆信息
- -Xloqqc:<file>：表示把GC日志写入到一个文件中去，而不是打印到标准输出中





### 2. GC日志格式

#### 2.1 GC分类

针对HotSpotVM的实现，它里面的GC按照回收区域又分为两大种类型：一种是部分收集（ Partial GC），一种是整堆收集（Full GC）

- 部分收集：不是完整收集整个Java堆的垃圾收集。其中又分为：
  - 新生代收集（MinorGC／YoungGC）：只是新生代（Eden\S0,S1）的垃圾收集
  - 老年代收集（MajorGC／OldGC）：只是老年代的垃圾收集。
    - 目前，只有CMSGC会有单独收集老年代的行为。
    - 注意，很多时候MajorGC会和FullGC混淆使用，需要具体分辨是老年代回收还是整堆回收。
  - 混合收集（MixedGC）：收集整个新生代以及部分老年代的垃圾收集。
    - 目前，只有G1GC会有这种行为
- 整堆收集（FullGC)：收集整个java堆和方法区的垃圾收集。



哪些情况会触发FullGC？

- 老年代空间不足
- 方法区空间不足
- 显式调用System.gc()
- MinorGC进入老年代的数据的平均大小大于老年代的可用内存
- 大对象直接进入老年代，而老年代的可用空间不足





#### 2.2 GC日志分类

**MinorGC**

![](JVM.assets/260.png)

![](JVM.assets/262.png)



**FullGC**

![](JVM.assets/261.png)

![](JVM.assets/263.png)





#### 2.3 GC日志结构剖析

##### 2.3.1 垃圾收集器

- 使用Serial收集器在新生代的名字是Default New Generation，因此显示的是"[DefNew"
- 使用ParNew收集器在新生代的名字会变成"[ParNew"，意思是"Parallel New Generation"
- 使用Parallel Scavenge收集器在新生代的名字是"[PSYoungGen"，这里的JDK1.7使用的就是PSYoungGen
- 使用Parallel Old Generation收集器在老年代的名字是"[ParoldGen"
- 使用G1收集器的话，会显示为"garbage-first heap" 

AllocationFailure
表明本次引起GC的原因是因为在年轻代中没有足够的空间能够存储新的数据了。



##### 2.3.2 GC前后情况

通过图示，我们可以发现GC日志格式的规律一般都是：GC前内存占用一>GC后内存占用（该区域内存总大小）

[PSYoungGen:5986K->696K(8704K)] 5986K->704K(9216K)

中括号内：GC回收前年轻代堆大小，回收后大小，（年轻代堆总大小）
括号外：GC回收前年轻代和老年代大小，回收后大小，（年轻代和老年代总大小）



##### 2.3.3 GC时间

GC日志中有三个时间：user，sys和real

- user－进程执行用户态代码（核心之外）所使用的时间。这是执行此进程所使用的实际 CPU时间，其他进程和此进程阻塞的时间并不包括在内。在垃圾收集的情况下，表示GC线程执行所使用的CPU总时间。
- sys－进程在内核态消耗的CPU时间，即在内核执行系统调用或等待系统事件所使用的 CPU时间
- real－程序从开始到结束所用的时钟时间。这个时间包括其他进程使用的时间片和进程阻塞的时间（比如等待I/O完成）。对于并行gc，这个数字应该接近（用户时间+系统时间）除以垃圾收集器使用的线程数。

由于多核的原因，一般的GC事件中，real time是小于sys + user time的，因为一般是多个线程并发的去做GC，所以real time是要小于sys + user time的。如果real > sys + user的话，则你的应用可能存在下列问题：IO负载非常重或者是CPU不够用。





#### 2.4 Minor GC 日志解析

2020-11-20T17:19:43.265-0800:0.822:[GC (ALLOCATION FAILURE)[PSYOUNGGEN:76800K->8433K(89600K)] 76800K->8449K(294400K), 0.0088371 SECS] [TIMES: USER=0.02 SYS=0.01, REAL=0.01 SECS]

- 2020-11-20T17:19:43.265-0800：日志打印时间 日期格式，如2013-05-04T21:53:59.234+0800
- 0.822：gc发生时，Java虚拟机启动以来经过的秒数
- [GC (Allocation Failure)：发生了一次垃圾回收，这是一次Minor GC。它不区分新生代GC还是老年代 GC，括号里的内容是gc发生的原因，这里的Allocation Failure的原因是新生代中没有足够区域能够存放需要分配的数据而失败。
- [PSYoungGen:76800K->8433K(89600K)]
  - PSYoungGen：表示GC发生的区域，区域名称与使用的GC 收集器是密切相关的
    - Serial收集器：Default New Generation显示DefNew
    - ParNew收集器：ParNew
    - Parallel Scanvenge收集器：PSYoung
    - 老年代和新生代同理，也是和收集器名称相关
  - 76800K->8433K(89600K):GC 前该内存区域已使用容量 ->GC 后该区域容量（该区域总容量）
    - 如果是新生代，总容量则会显示整个新生代内存的9/10，即 eden+from/to区
    - 如果是老年代，总容量则是全部内存大小，无变化
- 76800K->8449K(294400K)：在显示完区域容量GC的情况之后，会接着显示整个堆内存区域的GC情况：GC前堆内存已使用容量->GC堆内存容量（堆内存总容量）堆内存总容量=9/10新生代+老年代<初始化的内存大小
- 0.0088371 secs]：整个GC所花费的时间，单位是秒
- [Times: user=0.02 sys=0.01, real=0.01 secs]
  - user：指的是CPU工作在用户态所花费的时间
  - sys：指的是CPU工作在内核态所花费的时间
  - real：指的是在此次GC事件中所花费的总时间





#### 2.5 Full GC 日志解析

2020-11-20T17:19:43.794-0800: 1.351: [FULL GC (METADATA GC THRESHOLD)[PSY0UNGGEN:10082K->0K(89600K)] [PAR0LDGEN:32K->9638K(204800K)]10114K->9638K（294400K), [METASPACE:20158K->20156K(1067008K)],0.0285388 SECS] [TIMES:USER=0.11 SYS=0.00,REAL=0.03SECS]

- 2020-11-20T17:19:43.794-0800：日志打印时间日期格式如2013-05-04T21:53:59.234+0800
- 1.351：gc发生时，Java虚拟机启动以来经过的秒数
- Full GC (Metadata GC Threshold)
  - 发生了一次垃圾回收，这是一次FULLGC 它不区分新生代GC还是老年代GC
  - 括号里的内容是gc发生的原因，这里的Metadata GCThreshold的原因是Metaspace区不够用了。
    - Full GC (Ergonomics) : JVM 自适应调整导致的GC
    - FullGC(System)：调用了System.gc()方法
- [PSYoungGen: 10082K->0K(89600K)]
  - PSYoungGen：表示GC发生的区域，区域名称与使用的GC 收集器是密切相关的
    - Serial收集器：DefaultNewGeneration显示DefNew
    - ParNew收集器：ParNew
    - Parallel Scanvenge收集器：PSYoung
    - 老年代和新生代同理，也是和收集器名称相关
  - 10082K->0K(89600K):GC前该内存区域已使用容量->GC后该区域容量(该区域总容量）
    - 如果是新生代，总容量则会显示整个新生代内存的9/10，即 eden+from/to区
    - 如果是老年代，总容量则是全部内存大小，无变化
- [ParOldGen:32K->9638K(204800K)]：老年代区域没有发生GC，因为本次GC是meta space引引起的
- 10114K->9638K(294400K)：在显示完区域容量GC的情况之后，会接着显示整个堆内存区域的GC情况：GC前堆内存已使用容量->GC堆内存容量（堆内存总容量）堆内存总容量=9/10新生代+老年代<初始化的内存大小
- [Metaspace:20158K->20156K(1067008K)]：metaspaceGC回收2K空间
- 0.0285388 secs：整个GC所花费的时间，单位是秒
- [Times: user=0.11 sys=0.00, real=0.03 secs]
  - user：指的是CPU工作在用户态所花费的时间
  - sys：指的是CPU工作在内核态所花费的时间
  - real：指的是在此次GC事件中所花费的总时间





### 3. GC日志分析工具

上节介绍了GC日志的打印及含义，但是GC日志看起来比较麻烦，本节将会介绍一下GC日志可视化分析工具GCeasy和GCviewer等。通过GC日志可视化分析工具，我们可以很方便的看到JVM各个分代的内存使用情况、垃圾回收次数、垃圾回收的原因、垃圾回收占用的时间、吞吐量等，这些指标在我们进行JVM调优的时候是很有用的。

如果想把GC日志存到文件的话，是下面这个参数：-xloggc:/path/to/gc.log
然后就可以用一些工具去分析这些gc日志。



#### 3.1 GCeasy

GCeasy，一款超好用的在线分析GC日志的网站

官网地址：https://gceasy.io/

GCeasy是一款在线的GC日志分析器，可以通过GC日志分析进行内存泄漏检测、GC暂停原因分析、JVM配置建议优化等功能，而且是可以免费使用的（有一些服务是收费的）。





#### 3.2 GCViewer

上面介绍了一款在线的Gc日志分析器，下面介绍一个离线版的GCViewer。

GCViewer是一个免费的、开源的分析小工具，用于可视化查看由SUN/Oracle，IBM，HP和BEA Java虚拟机产生的垃圾收集器的日志。

GCViewer用于可视化JavaVM选项-verbose:gc和.NET生成的数据-Xloggc:<file>。它还计算与垃圾回收相关的性能指标（吞吐量，累积的暂停，最长的暂停等）。当通过更改世代大小或设置初始堆大小来调整特定应用程序的垃圾回收时，此功能非常有用。



















































