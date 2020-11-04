# Dependencies
## External
* OpenJDK 11.0.9
* PostgreSQL Server 10.10
* Elasticsearch 7.1.1

## Internal
See `pom.xml` for details.

Notable Dependencies:
* Spring Boot 2.3.5
* Hibernate 5.4.2.FINAL
* Hibernate Search 6.0.0.Beta11

# Setup
## PostgreSQL
1. Create user `hosc` (with login privilege) and password `password`
2. Create database `hosc` with owner `hosc`

## Elasticsearch
Elasticsearch cluster available at `localhost:9200`

# Steps
1. Start up application with `mvn spring-boot:run`. Application starts within a few seconds with startup complete log
   ```
   Started Application in 2.102 seconds (JVM running for 2.342)
   ```
2. In `application.yml`, comment out property `spring.jpa.properties.hibernate.search.enabled` to disable Hibernate Search
3. Restart application. Application hangs and never get startup complete log message.

# Other Behaviors
* The thing table gets created in PostgreSQL
* The indexes **DO NOT** get created in Elasticsearch

# Thread Dump
## Hibernate Search Disabled
```

2020-11-03 21:55:36
Full thread dump OpenJDK 64-Bit Server VM (11.0.9+11-Ubuntu-0ubuntu1.18.04.1 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x00007f425c004a20, length=51, elements={
0x00007f43a0249800, 0x00007f43a024b800, 0x00007f43a0251000, 0x00007f43a0253000,
0x00007f43a0255000, 0x00007f43a028e800, 0x00007f43a0293800, 0x00007f43a0f07800,
0x00007f4300002000, 0x00007f43a0f0a800, 0x00007f43a0f7b000, 0x00007f42ec226800,
0x00007f42ec21c000, 0x00007f43a10ff800, 0x00007f42e8001800, 0x00007f42e8036800,
0x00007f42e8038800, 0x00007f42e803a800, 0x00007f42e803c800, 0x00007f42e803e000,
0x00007f42e8040000, 0x00007f42e8042000, 0x00007f42e8044000, 0x00007f42e8046000,
0x00007f42e8048000, 0x00007f42e804a800, 0x00007f42e804c800, 0x00007f42e804e800,
0x00007f42e8050800, 0x00007f42e8052800, 0x00007f43a111d000, 0x00007f43a111e800,
0x00007f43a1120000, 0x00007f43a1121800, 0x00007f43a1123800, 0x00007f43a1125800,
0x00007f43a1127800, 0x00007f43a1129800, 0x00007f43a112b800, 0x00007f43a112d800,
0x00007f43a112f800, 0x00007f43a1136000, 0x00007f43a113a800, 0x00007f42ec4c1800,
0x00007f43a0019000, 0x00007f4348001000, 0x00007f42a0095000, 0x00007f425c001800,
0x00007f4260019800, 0x00007f4260022800, 0x00007f425c003000
}

"Reference Handler" #2 daemon prio=10 os_prio=0 cpu=2.11ms elapsed=11.21s tid=0x00007f43a0249800 nid=0x29f7 waiting on condition  [0x00007f43692f6000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.9/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@11.0.9/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.9/Reference.java:213)

   Locked ownable synchronizers:
        - None

"Finalizer" #3 daemon prio=8 os_prio=0 cpu=0.56ms elapsed=11.21s tid=0x00007f43a024b800 nid=0x29f8 in Object.wait()  [0x00007f43691f5000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x0000000621227360> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.9/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x0000000621227360> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.9/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.9/Finalizer.java:170)

   Locked ownable synchronizers:
        - None

"Signal Dispatcher" #4 daemon prio=9 os_prio=0 cpu=0.48ms elapsed=11.21s tid=0x00007f43a0251000 nid=0x29f9 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"C1 CompilerThread0" #5 daemon prio=9 os_prio=0 cpu=1343.55ms elapsed=11.21s tid=0x00007f43a0253000 nid=0x29fa waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"Sweeper thread" #17 daemon prio=9 os_prio=0 cpu=26.24ms elapsed=11.21s tid=0x00007f43a0255000 nid=0x29fb runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Service Thread" #18 daemon prio=9 os_prio=0 cpu=0.15ms elapsed=11.20s tid=0x00007f43a028e800 nid=0x29fc runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Common-Cleaner" #19 daemon prio=8 os_prio=0 cpu=2.19ms elapsed=11.19s tid=0x00007f43a0293800 nid=0x29fe in Object.wait()  [0x00007f4368910000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x0000000621213d60> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.9/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x0000000621213d60> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@11.0.9/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)
        at jdk.internal.misc.InnocuousThread.run(java.base@11.0.9/InnocuousThread.java:134)

   Locked ownable synchronizers:
        - None

"Catalina-utility-1" #24 prio=1 os_prio=0 cpu=3.32ms elapsed=9.87s tid=0x00007f43a0f07800 nid=0x2a0f waiting on condition  [0x00007f432f3e1000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000062123b118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1177)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"Catalina-utility-2" #25 prio=1 os_prio=0 cpu=2.04ms elapsed=9.87s tid=0x00007f4300002000 nid=0x2a10 waiting on condition  [0x00007f432ece0000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000062123b118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1182)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"container-0" #26 prio=5 os_prio=0 cpu=0.22ms elapsed=9.87s tid=0x00007f43a0f0a800 nid=0x2a11 waiting on condition  [0x00007f432e1db000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@11.0.9/Native Method)
        at org.apache.catalina.core.StandardServer.await(StandardServer.java:570)
        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer$1.run(TomcatWebServer.java:197)

   Locked ownable synchronizers:
        - None

"task-1" #27 prio=5 os_prio=0 cpu=643.85ms elapsed=9.80s tid=0x00007f43a0f7b000 nid=0x2a12 waiting on condition  [0x00007f432deda000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000620ff2ac0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.LinkedBlockingQueue.poll(java.base@11.0.9/LinkedBlockingQueue.java:458)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1053)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"PostgreSQL-JDBC-SharedTimer-1" #28 daemon prio=5 os_prio=0 cpu=0.30ms elapsed=9.50s tid=0x00007f42ec226800 nid=0x2a15 in Object.wait()  [0x00007f432d1a7000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x000000062a0459c0> (a java.util.TaskQueue)
        at java.lang.Object.wait(java.base@11.0.9/Object.java:328)
        at java.util.TimerThread.mainLoop(java.base@11.0.9/Timer.java:527)
        - waiting to re-lock in wait() <0x000000062a0459c0> (a java.util.TaskQueue)
        at java.util.TimerThread.run(java.base@11.0.9/Timer.java:506)

   Locked ownable synchronizers:
        - None

"HikariPool-1 housekeeper" #29 daemon prio=5 os_prio=0 cpu=0.81ms elapsed=9.49s tid=0x00007f42ec21c000 nid=0x2a16 waiting on condition  [0x00007f432d0a6000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000062108b900> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1182)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"pool-1-thread-1" #30 prio=5 os_prio=0 cpu=5.28ms elapsed=9.42s tid=0x00007f43a10ff800 nid=0x2a17 runnable  [0x00007f432cda5000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000629562dd0> (a sun.nio.ch.Util$2)
        - locked <0x0000000629562c00> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor.execute(AbstractMultiworkerIOReactor.java:343)
        at org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager.execute(PoolingNHttpClientConnectionManager.java:221)
        at org.apache.http.impl.nio.client.CloseableHttpAsyncClientBase$1.run(CloseableHttpAsyncClientBase.java:64)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 1" #31 prio=5 os_prio=0 cpu=1.25ms elapsed=9.41s tid=0x00007f42e8001800 nid=0x2a18 runnable  [0x00007f432cca4000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfb420> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfb2d0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 2" #32 prio=5 os_prio=0 cpu=1.23ms elapsed=9.41s tid=0x00007f42e8036800 nid=0x2a19 runnable  [0x00007f432cba3000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfb7c0> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfb670> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 3" #33 prio=5 os_prio=0 cpu=0.88ms elapsed=9.41s tid=0x00007f42e8038800 nid=0x2a1a runnable  [0x00007f432caa2000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfbb60> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfba10> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 4" #34 prio=5 os_prio=0 cpu=0.74ms elapsed=9.41s tid=0x00007f42e803a800 nid=0x2a1b runnable  [0x00007f432c9a1000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfbf00> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfbdb0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 5" #35 prio=5 os_prio=0 cpu=0.67ms elapsed=9.41s tid=0x00007f42e803c800 nid=0x2a1c runnable  [0x00007f432c8a0000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfc2a0> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfc150> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 6" #36 prio=5 os_prio=0 cpu=0.82ms elapsed=9.41s tid=0x00007f42e803e000 nid=0x2a1d runnable  [0x00007f432c79f000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfc640> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfc4f0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 7" #37 prio=5 os_prio=0 cpu=0.80ms elapsed=9.41s tid=0x00007f42e8040000 nid=0x2a1e runnable  [0x00007f432c69e000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfc9e0> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfc890> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 8" #38 prio=5 os_prio=0 cpu=0.86ms elapsed=9.41s tid=0x00007f42e8042000 nid=0x2a1f runnable  [0x00007f432c59d000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfcd80> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfcc30> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 9" #39 prio=5 os_prio=0 cpu=0.75ms elapsed=9.41s tid=0x00007f42e8044000 nid=0x2a20 runnable  [0x00007f432c49c000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfd120> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfcfd0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 10" #40 prio=5 os_prio=0 cpu=0.75ms elapsed=9.41s tid=0x00007f42e8046000 nid=0x2a21 runnable  [0x00007f432c39b000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfd4c0> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfd370> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 11" #41 prio=5 os_prio=0 cpu=0.78ms elapsed=9.41s tid=0x00007f42e8048000 nid=0x2a22 runnable  [0x00007f432c29a000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfd860> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfd710> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 12" #42 prio=5 os_prio=0 cpu=0.82ms elapsed=9.41s tid=0x00007f42e804a800 nid=0x2a23 runnable  [0x00007f432c199000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfdc00> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfdab0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 13" #43 prio=5 os_prio=0 cpu=0.86ms elapsed=9.41s tid=0x00007f42e804c800 nid=0x2a24 runnable  [0x00007f42b3ffe000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfdfa0> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfde50> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 14" #44 prio=5 os_prio=0 cpu=0.78ms elapsed=9.41s tid=0x00007f42e804e800 nid=0x2a25 runnable  [0x00007f42b3efd000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfe340> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfe1f0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 15" #45 prio=5 os_prio=0 cpu=0.73ms elapsed=9.41s tid=0x00007f42e8050800 nid=0x2a26 runnable  [0x00007f42b3dfc000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfe6e0> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfe590> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 16" #46 prio=5 os_prio=0 cpu=0.81ms elapsed=9.41s tid=0x00007f42e8052800 nid=0x2a27 runnable  [0x00007f42b3cfb000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000628cfea80> (a sun.nio.ch.Util$2)
        - locked <0x0000000628cfe930> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-BlockPoller" #48 daemon prio=5 os_prio=0 cpu=0.67ms elapsed=9.39s tid=0x00007f43a111d000 nid=0x2a2a runnable  [0x00007f42b3af9000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627cf5fc8> (a sun.nio.ch.Util$2)
        - locked <0x0000000627cf5e78> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:313)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-1" #49 daemon prio=5 os_prio=0 cpu=0.30ms elapsed=9.38s tid=0x00007f43a111e800 nid=0x2a2b waiting on condition  [0x00007f42b39f8000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-2" #50 daemon prio=5 os_prio=0 cpu=0.27ms elapsed=9.38s tid=0x00007f43a1120000 nid=0x2a2c waiting on condition  [0x00007f42b38f7000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-3" #51 daemon prio=5 os_prio=0 cpu=0.30ms elapsed=9.38s tid=0x00007f43a1121800 nid=0x2a2d waiting on condition  [0x00007f42b37f6000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-4" #52 daemon prio=5 os_prio=0 cpu=0.14ms elapsed=9.38s tid=0x00007f43a1123800 nid=0x2a2e waiting on condition  [0x00007f42b36f5000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-5" #53 daemon prio=5 os_prio=0 cpu=0.26ms elapsed=9.38s tid=0x00007f43a1125800 nid=0x2a2f waiting on condition  [0x00007f42b35f4000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-6" #54 daemon prio=5 os_prio=0 cpu=0.17ms elapsed=9.38s tid=0x00007f43a1127800 nid=0x2a30 waiting on condition  [0x00007f42b34f3000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-7" #55 daemon prio=5 os_prio=0 cpu=0.09ms elapsed=9.38s tid=0x00007f43a1129800 nid=0x2a31 waiting on condition  [0x00007f42b33f2000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-8" #56 daemon prio=5 os_prio=0 cpu=0.07ms elapsed=9.38s tid=0x00007f43a112b800 nid=0x2a32 waiting on condition  [0x00007f42b32f1000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-9" #57 daemon prio=5 os_prio=0 cpu=0.30ms elapsed=9.38s tid=0x00007f43a112d800 nid=0x2a33 waiting on condition  [0x00007f42b31f0000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-10" #58 daemon prio=5 os_prio=0 cpu=0.19ms elapsed=9.38s tid=0x00007f43a112f800 nid=0x2a34 waiting on condition  [0x00007f42b30ef000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006279e20c8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-ClientPoller" #59 daemon prio=5 os_prio=0 cpu=0.98ms elapsed=9.38s tid=0x00007f43a1136000 nid=0x2a35 runnable  [0x00007f42b2fee000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x00000006279fc430> (a sun.nio.ch.Util$2)
        - locked <0x00000006279fc2e0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:709)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-Acceptor" #60 daemon prio=5 os_prio=0 cpu=0.55ms elapsed=9.38s tid=0x00007f43a113a800 nid=0x2a36 runnable  [0x00007f42b2eed000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.ServerSocketChannelImpl.accept0(java.base@11.0.9/Native Method)
        at sun.nio.ch.ServerSocketChannelImpl.accept(java.base@11.0.9/ServerSocketChannelImpl.java:533)
        at sun.nio.ch.ServerSocketChannelImpl.accept(java.base@11.0.9/ServerSocketChannelImpl.java:285)
        at org.apache.tomcat.util.net.NioEndpoint.serverSocketAccept(NioEndpoint.java:469)
        at org.apache.tomcat.util.net.NioEndpoint.serverSocketAccept(NioEndpoint.java:71)
        at org.apache.tomcat.util.net.Acceptor.run(Acceptor.java:106)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - <0x0000000627cf4ba0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)

"task-2" #61 prio=5 os_prio=0 cpu=1.78ms elapsed=9.07s tid=0x00007f42ec4c1800 nid=0x2a3f waiting on condition  [0x00007f42b27ec000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000620ff2ac0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.LinkedBlockingQueue.poll(java.base@11.0.9/LinkedBlockingQueue.java:458)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1053)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"DestroyJavaVM" #62 prio=5 os_prio=0 cpu=1913.31ms elapsed=8.95s tid=0x00007f43a0019000 nid=0x29ef waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Attach Listener" #63 daemon prio=9 os_prio=0 cpu=83.84ms elapsed=3.27s tid=0x00007f4348001000 nid=0x2a50 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"RMI TCP Accept-0" #64 daemon prio=9 os_prio=0 cpu=1.40ms elapsed=3.00s tid=0x00007f42a0095000 nid=0x2a51 runnable  [0x00007f42b22eb000]
   java.lang.Thread.State: RUNNABLE
        at java.net.PlainSocketImpl.socketAccept(java.base@11.0.9/Native Method)
        at java.net.AbstractPlainSocketImpl.accept(java.base@11.0.9/AbstractPlainSocketImpl.java:458)
        at java.net.ServerSocket.implAccept(java.base@11.0.9/ServerSocket.java:565)
        at java.net.ServerSocket.accept(java.base@11.0.9/ServerSocket.java:533)
        at sun.management.jmxremote.LocalRMIServerSocketFactory$1.accept(jdk.management.agent@11.0.9/LocalRMIServerSocketFactory.java:52)
        at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(java.rmi@11.0.9/TCPTransport.java:394)
        at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(java.rmi@11.0.9/TCPTransport.java:366)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"RMI TCP Connection(1)-127.0.0.1" #65 daemon prio=9 os_prio=0 cpu=66.14ms elapsed=2.99s tid=0x00007f425c001800 nid=0x2a53 runnable  [0x00007f42b21e8000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.9/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.9/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:140)
        at java.io.BufferedInputStream.fill(java.base@11.0.9/BufferedInputStream.java:252)
        at java.io.BufferedInputStream.read(java.base@11.0.9/BufferedInputStream.java:271)
        - locked <0x0000000622f68a90> (a java.io.BufferedInputStream)
        at java.io.FilterInputStream.read(java.base@11.0.9/FilterInputStream.java:83)
        at sun.rmi.transport.tcp.TCPTransport.handleMessages(java.rmi@11.0.9/TCPTransport.java:544)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(java.rmi@11.0.9/TCPTransport.java:796)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(java.rmi@11.0.9/TCPTransport.java:677)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$939/0x0000000840732040.run(java.rmi@11.0.9/Unknown Source)
        at java.security.AccessController.doPrivileged(java.base@11.0.9/Native Method)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(java.rmi@11.0.9/TCPTransport.java:676)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1128)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - <0x0000000622e00490> (a java.util.concurrent.ThreadPoolExecutor$Worker)

"RMI Scheduler(0)" #66 daemon prio=9 os_prio=0 cpu=0.32ms elapsed=2.97s tid=0x00007f4260019800 nid=0x2a54 waiting on condition  [0x00007f42b20e9000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x00000006230fb130> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1182)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"JMX server connection timeout 67" #67 daemon prio=9 os_prio=0 cpu=8.36ms elapsed=2.96s tid=0x00007f4260022800 nid=0x2a55 in Object.wait()  [0x00007f42b1fe8000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x0000000622fd5508> (a [I)
        at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(java.management@11.0.9/ServerCommunicatorAdmin.java:171)
        - waiting to re-lock in wait() <0x0000000622fd5508> (a [I)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"RMI TCP Connection(2)-127.0.0.1" #68 daemon prio=9 os_prio=0 cpu=28.93ms elapsed=1.91s tid=0x00007f425c003000 nid=0x2a5d runnable  [0x00007f42b1ee5000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.9/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.9/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:140)
        at java.io.BufferedInputStream.fill(java.base@11.0.9/BufferedInputStream.java:252)
        at java.io.BufferedInputStream.read(java.base@11.0.9/BufferedInputStream.java:271)
        - locked <0x0000000622a00148> (a java.io.BufferedInputStream)
        at java.io.FilterInputStream.read(java.base@11.0.9/FilterInputStream.java:83)
        at sun.rmi.transport.tcp.TCPTransport.handleMessages(java.rmi@11.0.9/TCPTransport.java:544)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(java.rmi@11.0.9/TCPTransport.java:796)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(java.rmi@11.0.9/TCPTransport.java:677)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$939/0x0000000840732040.run(java.rmi@11.0.9/Unknown Source)
        at java.security.AccessController.doPrivileged(java.base@11.0.9/Native Method)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(java.rmi@11.0.9/TCPTransport.java:676)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1128)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - <0x0000000622e00cb0> (a java.util.concurrent.ThreadPoolExecutor$Worker)

"VM Thread" os_prio=0 cpu=103.83ms elapsed=11.22s tid=0x00007f43a0246800 nid=0x29f6 runnable  

"GC Thread#0" os_prio=0 cpu=29.57ms elapsed=11.23s tid=0x00007f43a0032000 nid=0x29f1 runnable  

"GC Thread#1" os_prio=0 cpu=28.97ms elapsed=10.91s tid=0x00007f435c001000 nid=0x29ff runnable  

"GC Thread#2" os_prio=0 cpu=28.81ms elapsed=10.91s tid=0x00007f435c002800 nid=0x2a00 runnable  

"GC Thread#3" os_prio=0 cpu=28.70ms elapsed=10.91s tid=0x00007f435c004800 nid=0x2a01 runnable  

"GC Thread#4" os_prio=0 cpu=47.98ms elapsed=10.91s tid=0x00007f435c006000 nid=0x2a02 runnable  

"GC Thread#5" os_prio=0 cpu=29.84ms elapsed=10.91s tid=0x00007f435c008000 nid=0x2a03 runnable  

"GC Thread#6" os_prio=0 cpu=29.25ms elapsed=10.91s tid=0x00007f435c009800 nid=0x2a04 runnable  

"GC Thread#7" os_prio=0 cpu=28.55ms elapsed=10.91s tid=0x00007f435c00b800 nid=0x2a05 runnable  

"GC Thread#8" os_prio=0 cpu=29.35ms elapsed=10.91s tid=0x00007f435c00d800 nid=0x2a06 runnable  

"GC Thread#9" os_prio=0 cpu=28.67ms elapsed=10.91s tid=0x00007f435c00f000 nid=0x2a07 runnable  

"GC Thread#10" os_prio=0 cpu=30.66ms elapsed=10.91s tid=0x00007f435c011000 nid=0x2a08 runnable  

"GC Thread#11" os_prio=0 cpu=37.22ms elapsed=10.91s tid=0x00007f435c012800 nid=0x2a09 runnable  

"G1 Main Marker" os_prio=0 cpu=1.38ms elapsed=11.23s tid=0x00007f43a006d000 nid=0x29f2 runnable  

"G1 Conc#0" os_prio=0 cpu=15.93ms elapsed=11.23s tid=0x00007f43a006e800 nid=0x29f3 runnable  

"G1 Conc#1" os_prio=0 cpu=13.58ms elapsed=10.13s tid=0x00007f436c001000 nid=0x2a0d runnable  

"G1 Conc#2" os_prio=0 cpu=16.23ms elapsed=10.13s tid=0x00007f436c002000 nid=0x2a0e runnable  

"G1 Refine#0" os_prio=0 cpu=0.38ms elapsed=11.22s tid=0x00007f43a0206800 nid=0x29f4 runnable  

"G1 Young RemSet Sampling" os_prio=0 cpu=3.18ms elapsed=11.22s tid=0x00007f43a0208800 nid=0x29f5 runnable  
"VM Periodic Task Thread" os_prio=0 cpu=5.74ms elapsed=11.20s tid=0x00007f43a0290800 nid=0x29fd waiting on condition  

JNI global refs: 26, weak refs: 0


```

## Hibernate Search Enabled
```
2020-11-03 21:51:22
Full thread dump OpenJDK 64-Bit Server VM (11.0.9+11-Ubuntu-0ubuntu1.18.04.1 mixed mode, sharing):

Threads class SMR info:
_java_thread_list=0x00007fe268004ca0, length=50, elements={
0x00007fe3a8019000, 0x00007fe3a8249800, 0x00007fe3a824b800, 0x00007fe3a8251000,
0x00007fe3a8253000, 0x00007fe3a8255000, 0x00007fe3a828e800, 0x00007fe3a8293800,
0x00007fe3a8f07800, 0x00007fe300002000, 0x00007fe3a8f0a800, 0x00007fe3a8f7b000,
0x00007fe2fc222000, 0x00007fe2fc217000, 0x00007fe3a90fe800, 0x00007fe2ec001800,
0x00007fe2ec034800, 0x00007fe2ec036000, 0x00007fe2ec038800, 0x00007fe2ec03a800,
0x00007fe2ec03c800, 0x00007fe2ec03e800, 0x00007fe2ec040800, 0x00007fe2ec042800,
0x00007fe2ec044800, 0x00007fe2ec046800, 0x00007fe2ec048800, 0x00007fe2ec04a800,
0x00007fe2ec04c800, 0x00007fe2ec04e800, 0x00007fe2ec050000, 0x00007fe3a9120800,
0x00007fe3a9123800, 0x00007fe3a9124800, 0x00007fe3a9126800, 0x00007fe3a9128800,
0x00007fe3a912a800, 0x00007fe3a912c800, 0x00007fe3a912e800, 0x00007fe3a9130800,
0x00007fe3a9132800, 0x00007fe3a9134000, 0x00007fe3a913a800, 0x00007fe3a913d000,
0x00007fe350001000, 0x00007fe2e808d000, 0x00007fe268001800, 0x00007fe26c01f800,
0x00007fe26c028800, 0x00007fe268003800
}

"main" #1 prio=5 os_prio=0 cpu=1888.38ms elapsed=28.84s tid=0x00007fe3a8019000 nid=0x2605 waiting on condition  [0x00007fe3b009d000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000629ac9800> (a java.util.concurrent.FutureTask)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.FutureTask.awaitDone(java.base@11.0.9/FutureTask.java:447)
        at java.util.concurrent.FutureTask.get(java.base@11.0.9/FutureTask.java:190)
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.getNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:540)
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.invokeProxyMethod(AbstractEntityManagerFactoryBean.java:497)
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean$ManagedEntityManagerFactoryInvocationHandler.invoke(AbstractEntityManagerFactoryBean.java:680)
        at com.sun.proxy.$Proxy64.getMetamodel(Unknown Source)
        at org.springframework.data.jpa.repository.config.JpaMetamodelMappingContextFactoryBean$$Lambda$730/0x00000008405c8840.apply(Unknown Source)
        at java.util.stream.ReferencePipeline$3$1.accept(java.base@11.0.9/ReferencePipeline.java:195)
        at java.util.Iterator.forEachRemaining(java.base@11.0.9/Iterator.java:133)
        at java.util.Spliterators$IteratorSpliterator.forEachRemaining(java.base@11.0.9/Spliterators.java:1801)
        at java.util.stream.AbstractPipeline.copyInto(java.base@11.0.9/AbstractPipeline.java:484)
        at java.util.stream.AbstractPipeline.wrapAndCopyInto(java.base@11.0.9/AbstractPipeline.java:474)
        at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(java.base@11.0.9/ReduceOps.java:913)
        at java.util.stream.AbstractPipeline.evaluate(java.base@11.0.9/AbstractPipeline.java:234)
        at java.util.stream.ReferencePipeline.collect(java.base@11.0.9/ReferencePipeline.java:578)
        at org.springframework.data.jpa.repository.config.JpaMetamodelMappingContextFactoryBean.getMetamodels(JpaMetamodelMappingContextFactoryBean.java:106)
        at org.springframework.data.jpa.repository.config.JpaMetamodelMappingContextFactoryBean.createInstance(JpaMetamodelMappingContextFactoryBean.java:80)
        at org.springframework.data.jpa.repository.config.JpaMetamodelMappingContextFactoryBean.createInstance(JpaMetamodelMappingContextFactoryBean.java:44)
        at org.springframework.beans.factory.config.AbstractFactoryBean.afterPropertiesSet(AbstractFactoryBean.java:142)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1853)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1790)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:594)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:516)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:324)
        at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$188/0x0000000840186840.getObject(Unknown Source)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        - locked <0x0000000629c51518> (a java.util.concurrent.ConcurrentHashMap)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:322)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:330)
        at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:113)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1697)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1442)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:593)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:516)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:324)
        at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$188/0x0000000840186840.getObject(Unknown Source)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        - locked <0x0000000629c51518> (a java.util.concurrent.ConcurrentHashMap)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:322)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeansOfType(DefaultListableBeanFactory.java:624)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeansOfType(DefaultListableBeanFactory.java:612)
        at org.springframework.data.repository.config.DeferredRepositoryInitializationListener.onApplicationEvent(DeferredRepositoryInitializationListener.java:51)
        at org.springframework.data.repository.config.DeferredRepositoryInitializationListener.onApplicationEvent(DeferredRepositoryInitializationListener.java:36)
        at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172)
        at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165)
        at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139)
        at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:404)
        at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:361)
        at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:898)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:554)
        - locked <0x000000062989cb50> (a java.lang.Object)
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:143)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:758)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:750)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:405)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:315)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1237)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226)
        at com.example.hiberormsearchclean.Application.main(Application.java:10)

   Locked ownable synchronizers:
        - None

"Reference Handler" #2 daemon prio=10 os_prio=0 cpu=1.80ms elapsed=28.83s tid=0x00007fe3a8249800 nid=0x260d waiting on condition  [0x00007fe3713f7000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.9/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@11.0.9/Reference.java:241)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.9/Reference.java:213)

   Locked ownable synchronizers:
        - None

"Finalizer" #3 daemon prio=8 os_prio=0 cpu=0.52ms elapsed=28.83s tid=0x00007fe3a824b800 nid=0x260e in Object.wait()  [0x00007fe3712f6000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x000000060addcef8> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.9/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000060addcef8> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.9/ReferenceQueue.java:176)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.9/Finalizer.java:170)

   Locked ownable synchronizers:
        - None

"Signal Dispatcher" #4 daemon prio=9 os_prio=0 cpu=0.43ms elapsed=28.83s tid=0x00007fe3a8251000 nid=0x260f runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"C1 CompilerThread0" #5 daemon prio=9 os_prio=0 cpu=1323.52ms elapsed=28.83s tid=0x00007fe3a8253000 nid=0x2610 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

   Locked ownable synchronizers:
        - None

"Sweeper thread" #17 daemon prio=9 os_prio=0 cpu=21.22ms elapsed=28.83s tid=0x00007fe3a8255000 nid=0x2611 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Service Thread" #18 daemon prio=9 os_prio=0 cpu=0.07ms elapsed=28.81s tid=0x00007fe3a828e800 nid=0x2612 runnable  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Common-Cleaner" #19 daemon prio=8 os_prio=0 cpu=2.64ms elapsed=28.81s tid=0x00007fe3a8293800 nid=0x2614 in Object.wait()  [0x00007fe370a11000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x000000060addeb98> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@11.0.9/ReferenceQueue.java:155)
        - waiting to re-lock in wait() <0x000000060addeb98> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@11.0.9/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)
        at jdk.internal.misc.InnocuousThread.run(java.base@11.0.9/InnocuousThread.java:134)

   Locked ownable synchronizers:
        - None

"Catalina-utility-1" #24 prio=1 os_prio=0 cpu=5.25ms elapsed=27.39s tid=0x00007fe3a8f07800 nid=0x262f waiting on condition  [0x00007fe32f4e3000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000629e5a440> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1177)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"Catalina-utility-2" #25 prio=1 os_prio=0 cpu=6.94ms elapsed=27.39s tid=0x00007fe300002000 nid=0x2630 waiting on condition  [0x00007fe32efe2000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000629e5a440> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1182)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"container-0" #26 prio=5 os_prio=0 cpu=0.32ms elapsed=27.39s tid=0x00007fe3a8f0a800 nid=0x2631 waiting on condition  [0x00007fe32e2dd000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@11.0.9/Native Method)
        at org.apache.catalina.core.StandardServer.await(StandardServer.java:570)
        at org.springframework.boot.web.embedded.tomcat.TomcatWebServer$1.run(TomcatWebServer.java:197)

   Locked ownable synchronizers:
        - None

"task-1" #27 prio=5 os_prio=0 cpu=717.01ms elapsed=27.31s tid=0x00007fe3a8f7b000 nid=0x2634 waiting for monitor entry  [0x00007fe32dfdb000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getSingletonFactoryBeanForTypeCheck(AbstractAutowireCapableBeanFactory.java:987)
        - waiting to lock <0x0000000629c51518> (a java.util.concurrent.ConcurrentHashMap)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getTypeForFactoryBean(AbstractAutowireCapableBeanFactory.java:884)
        at org.springframework.beans.factory.support.AbstractBeanFactory.isTypeMatch(AbstractBeanFactory.java:619)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doGetBeanNamesForType(DefaultListableBeanFactory.java:536)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(DefaultListableBeanFactory.java:503)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(DefaultListableBeanFactory.java:480)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(DefaultListableBeanFactory.java:473)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveNamedBean(DefaultListableBeanFactory.java:1159)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveBean(DefaultListableBeanFactory.java:420)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:349)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:342)
        at org.springframework.orm.hibernate5.SpringBeanContainer.createBean(SpringBeanContainer.java:151)
        at org.springframework.orm.hibernate5.SpringBeanContainer.getBean(SpringBeanContainer.java:110)
        at org.hibernate.search.mapper.orm.bootstrap.impl.HibernateOrmBeanContainerBeanProvider.forType(HibernateOrmBeanContainerBeanProvider.java:72)
        at org.hibernate.search.engine.environment.bean.impl.ConfiguredBeanResolver.resolve(ConfiguredBeanResolver.java:65)
        at org.hibernate.search.engine.environment.bean.TypeBeanReference.resolve(TypeBeanReference.java:27)
        at org.hibernate.search.engine.environment.bean.BeanResolver.resolve(BeanResolver.java:73)
        at org.hibernate.search.engine.common.impl.SearchIntegrationBuilderImpl$$Lambda$808/0x00000008406c6040.apply(Unknown Source)
        at org.hibernate.search.engine.cfg.impl.DefaultedConfigurationProperty.convert(DefaultedConfigurationProperty.java:27)
        at org.hibernate.search.engine.cfg.impl.AbstractConfigurationProperty.doGet(AbstractConfigurationProperty.java:44)
        at org.hibernate.search.engine.cfg.impl.AbstractConfigurationProperty.getAndTransform(AbstractConfigurationProperty.java:36)
        at org.hibernate.search.engine.common.impl.SearchIntegrationBuilderImpl.prepareBuild(SearchIntegrationBuilderImpl.java:200)
        at org.hibernate.search.mapper.orm.bootstrap.impl.HibernateOrmIntegrationBooterImpl.doBootFirstPhase(HibernateOrmIntegrationBooterImpl.java:282)
        at org.hibernate.search.mapper.orm.bootstrap.impl.HibernateOrmIntegrationBooterImpl.bootNow(HibernateOrmIntegrationBooterImpl.java:226)
        at org.hibernate.search.mapper.orm.bootstrap.impl.HibernateOrmIntegrationBooterImpl$$Lambda$751/0x00000008405cb440.apply(Unknown Source)
        at java.util.concurrent.CompletableFuture$UniApply.tryFire(java.base@11.0.9/CompletableFuture.java:642)
        at java.util.concurrent.CompletableFuture.postComplete(java.base@11.0.9/CompletableFuture.java:506)
        at java.util.concurrent.CompletableFuture.complete(java.base@11.0.9/CompletableFuture.java:2073)
        at org.hibernate.search.mapper.orm.bootstrap.impl.HibernateSearchSessionFactoryObserver.sessionFactoryCreated(HibernateSearchSessionFactoryObserver.java:41)
        - locked <0x00000006255f08a0> (a org.hibernate.search.mapper.orm.bootstrap.impl.HibernateSearchSessionFactoryObserver)
        at org.hibernate.internal.SessionFactoryObserverChain.sessionFactoryCreated(SessionFactoryObserverChain.java:35)
        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:382)
        at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:469)
        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1259)
        at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:58)
        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365)
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:391)
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean$$Lambda$565/0x00000008403bac40.call(Unknown Source)
        at java.util.concurrent.FutureTask.run(java.base@11.0.9/FutureTask.java:264)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1128)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - <0x0000000629ac9868> (a java.util.concurrent.ThreadPoolExecutor$Worker)

"PostgreSQL-JDBC-SharedTimer-1" #28 daemon prio=5 os_prio=0 cpu=0.29ms elapsed=27.05s tid=0x00007fe2fc222000 nid=0x2637 in Object.wait()  [0x00007fe32d4a9000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x0000000629ee1860> (a java.util.TaskQueue)
        at java.lang.Object.wait(java.base@11.0.9/Object.java:328)
        at java.util.TimerThread.mainLoop(java.base@11.0.9/Timer.java:527)
        - waiting to re-lock in wait() <0x0000000629ee1860> (a java.util.TaskQueue)
        at java.util.TimerThread.run(java.base@11.0.9/Timer.java:506)

   Locked ownable synchronizers:
        - None

"HikariPool-1 housekeeper" #29 daemon prio=5 os_prio=0 cpu=0.70ms elapsed=27.04s tid=0x00007fe2fc217000 nid=0x263b waiting on condition  [0x00007fe32d3a8000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000629f0e9b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1182)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"pool-1-thread-1" #31 prio=5 os_prio=0 cpu=6.48ms elapsed=26.92s tid=0x00007fe3a90fe800 nid=0x2641 runnable  [0x00007fe32cda6000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x000000062898b5e8> (a sun.nio.ch.Util$2)
        - locked <0x000000062898b418> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor.execute(AbstractMultiworkerIOReactor.java:343)
        at org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager.execute(PoolingNHttpClientConnectionManager.java:221)
        at org.apache.http.impl.nio.client.CloseableHttpAsyncClientBase$1.run(CloseableHttpAsyncClientBase.java:64)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 1" #32 prio=5 os_prio=0 cpu=1.87ms elapsed=26.92s tid=0x00007fe2ec001800 nid=0x2642 runnable  [0x00007fe32cca5000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627eef6c0> (a sun.nio.ch.Util$2)
        - locked <0x0000000627eef570> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 2" #33 prio=5 os_prio=0 cpu=1.92ms elapsed=26.92s tid=0x00007fe2ec034800 nid=0x2643 runnable  [0x00007fe32cba4000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627eefa60> (a sun.nio.ch.Util$2)
        - locked <0x0000000627eef910> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 3" #34 prio=5 os_prio=0 cpu=1.47ms elapsed=26.92s tid=0x00007fe2ec036000 nid=0x2644 runnable  [0x00007fe32caa3000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627eefe00> (a sun.nio.ch.Util$2)
        - locked <0x0000000627eefcb0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 4" #35 prio=5 os_prio=0 cpu=1.43ms elapsed=26.92s tid=0x00007fe2ec038800 nid=0x2645 runnable  [0x00007fe32c9a2000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef01a0> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef0050> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 5" #36 prio=5 os_prio=0 cpu=1.47ms elapsed=26.92s tid=0x00007fe2ec03a800 nid=0x2646 runnable  [0x00007fe32c8a1000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef0540> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef03f0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 6" #37 prio=5 os_prio=0 cpu=1.43ms elapsed=26.91s tid=0x00007fe2ec03c800 nid=0x2647 runnable  [0x00007fe32c7a0000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef08e0> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef0790> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 7" #38 prio=5 os_prio=0 cpu=1.57ms elapsed=26.91s tid=0x00007fe2ec03e800 nid=0x2648 runnable  [0x00007fe32c69f000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef0c80> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef0b30> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 8" #39 prio=5 os_prio=0 cpu=1.54ms elapsed=26.91s tid=0x00007fe2ec040800 nid=0x2649 runnable  [0x00007fe32c59e000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef1020> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef0ed0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 9" #40 prio=5 os_prio=0 cpu=1.40ms elapsed=26.91s tid=0x00007fe2ec042800 nid=0x264a runnable  [0x00007fe32c49d000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef13c0> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef1270> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 10" #41 prio=5 os_prio=0 cpu=1.37ms elapsed=26.91s tid=0x00007fe2ec044800 nid=0x264b runnable  [0x00007fe32c39c000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef1760> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef1610> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 11" #42 prio=5 os_prio=0 cpu=1.31ms elapsed=26.91s tid=0x00007fe2ec046800 nid=0x264d runnable  [0x00007fe32c29b000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef1b00> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef19b0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 12" #43 prio=5 os_prio=0 cpu=1.42ms elapsed=26.91s tid=0x00007fe2ec048800 nid=0x264e runnable  [0x00007fe32c19a000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef1ea0> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef1d50> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 13" #44 prio=5 os_prio=0 cpu=1.31ms elapsed=26.91s tid=0x00007fe2ec04a800 nid=0x264f runnable  [0x00007fe2b7ffe000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef2240> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef20f0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 14" #45 prio=5 os_prio=0 cpu=1.61ms elapsed=26.91s tid=0x00007fe2ec04c800 nid=0x2650 runnable  [0x00007fe2b7efd000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef25e0> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef2490> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 15" #46 prio=5 os_prio=0 cpu=1.44ms elapsed=26.91s tid=0x00007fe2ec04e800 nid=0x2651 runnable  [0x00007fe2b7dfc000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef2980> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef2830> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"I/O dispatcher 16" #47 prio=5 os_prio=0 cpu=1.31ms elapsed=26.91s tid=0x00007fe2ec050000 nid=0x2652 runnable  [0x00007fe2b7cfb000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000627ef2d20> (a sun.nio.ch.Util$2)
        - locked <0x0000000627ef2bd0> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:255)
        at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
        at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:591)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-BlockPoller" #48 daemon prio=5 os_prio=0 cpu=1.23ms elapsed=26.89s tid=0x00007fe3a9120800 nid=0x265a runnable  [0x00007fe2b7bfa000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000626ed04e0> (a sun.nio.ch.Util$2)
        - locked <0x0000000626ed0390> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:313)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-1" #49 daemon prio=5 os_prio=0 cpu=0.15ms elapsed=26.89s tid=0x00007fe3a9123800 nid=0x265c waiting on condition  [0x00007fe2b7af9000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-2" #50 daemon prio=5 os_prio=0 cpu=0.27ms elapsed=26.89s tid=0x00007fe3a9124800 nid=0x265d waiting on condition  [0x00007fe2b79f8000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-3" #51 daemon prio=5 os_prio=0 cpu=0.10ms elapsed=26.89s tid=0x00007fe3a9126800 nid=0x265e waiting on condition  [0x00007fe2b78f7000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-4" #52 daemon prio=5 os_prio=0 cpu=0.07ms elapsed=26.89s tid=0x00007fe3a9128800 nid=0x265f waiting on condition  [0x00007fe2b77f6000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-5" #53 daemon prio=5 os_prio=0 cpu=0.17ms elapsed=26.89s tid=0x00007fe3a912a800 nid=0x2660 waiting on condition  [0x00007fe2b76f5000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-6" #54 daemon prio=5 os_prio=0 cpu=0.20ms elapsed=26.89s tid=0x00007fe3a912c800 nid=0x2661 waiting on condition  [0x00007fe2b75f4000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-7" #55 daemon prio=5 os_prio=0 cpu=0.18ms elapsed=26.89s tid=0x00007fe3a912e800 nid=0x2664 waiting on condition  [0x00007fe2b74f3000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-8" #56 daemon prio=5 os_prio=0 cpu=0.15ms elapsed=26.89s tid=0x00007fe3a9130800 nid=0x2665 waiting on condition  [0x00007fe2b73f2000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-9" #57 daemon prio=5 os_prio=0 cpu=0.20ms elapsed=26.89s tid=0x00007fe3a9132800 nid=0x2666 waiting on condition  [0x00007fe2b72f1000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-exec-10" #58 daemon prio=5 os_prio=0 cpu=0.11ms elapsed=26.89s tid=0x00007fe3a9134000 nid=0x2667 waiting on condition  [0x00007fe2b71f0000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000626efdc40> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at java.util.concurrent.LinkedBlockingQueue.take(java.base@11.0.9/LinkedBlockingQueue.java:433)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:108)
        at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:33)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-ClientPoller" #59 daemon prio=5 os_prio=0 cpu=2.04ms elapsed=26.89s tid=0x00007fe3a913a800 nid=0x2668 runnable  [0x00007fe2b70ef000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9/SelectorImpl.java:124)
        - locked <0x0000000626f17fa8> (a sun.nio.ch.Util$2)
        - locked <0x0000000626f17e58> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9/SelectorImpl.java:136)
        at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:709)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"http-nio-65529-Acceptor" #60 daemon prio=5 os_prio=0 cpu=0.30ms elapsed=26.89s tid=0x00007fe3a913d000 nid=0x2669 runnable  [0x00007fe2b6fee000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.ServerSocketChannelImpl.accept0(java.base@11.0.9/Native Method)
        at sun.nio.ch.ServerSocketChannelImpl.accept(java.base@11.0.9/ServerSocketChannelImpl.java:533)
        at sun.nio.ch.ServerSocketChannelImpl.accept(java.base@11.0.9/ServerSocketChannelImpl.java:285)
        at org.apache.tomcat.util.net.NioEndpoint.serverSocketAccept(NioEndpoint.java:469)
        at org.apache.tomcat.util.net.NioEndpoint.serverSocketAccept(NioEndpoint.java:71)
        at org.apache.tomcat.util.net.Acceptor.run(Acceptor.java:106)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - <0x00000006271ff580> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)

"Attach Listener" #61 daemon prio=9 os_prio=0 cpu=81.33ms elapsed=5.98s tid=0x00007fe350001000 nid=0x2773 waiting on condition  [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"RMI TCP Accept-0" #62 daemon prio=9 os_prio=0 cpu=1.30ms elapsed=4.13s tid=0x00007fe2e808d000 nid=0x2778 runnable  [0x00007fe2b66ed000]
   java.lang.Thread.State: RUNNABLE
        at java.net.PlainSocketImpl.socketAccept(java.base@11.0.9/Native Method)
        at java.net.AbstractPlainSocketImpl.accept(java.base@11.0.9/AbstractPlainSocketImpl.java:458)
        at java.net.ServerSocket.implAccept(java.base@11.0.9/ServerSocket.java:565)
        at java.net.ServerSocket.accept(java.base@11.0.9/ServerSocket.java:533)
        at sun.management.jmxremote.LocalRMIServerSocketFactory$1.accept(jdk.management.agent@11.0.9/LocalRMIServerSocketFactory.java:52)
        at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(java.rmi@11.0.9/TCPTransport.java:394)
        at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(java.rmi@11.0.9/TCPTransport.java:366)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"RMI TCP Connection(1)-127.0.0.1" #63 daemon prio=9 os_prio=0 cpu=98.19ms elapsed=4.10s tid=0x00007fe268001800 nid=0x277b runnable  [0x00007fe2b65ea000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.9/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.9/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:140)
        at java.io.BufferedInputStream.fill(java.base@11.0.9/BufferedInputStream.java:252)
        at java.io.BufferedInputStream.read(java.base@11.0.9/BufferedInputStream.java:271)
        - locked <0x00000006228684d0> (a java.io.BufferedInputStream)
        at java.io.FilterInputStream.read(java.base@11.0.9/FilterInputStream.java:83)
        at sun.rmi.transport.tcp.TCPTransport.handleMessages(java.rmi@11.0.9/TCPTransport.java:544)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(java.rmi@11.0.9/TCPTransport.java:796)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(java.rmi@11.0.9/TCPTransport.java:677)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$856/0x0000000840715040.run(java.rmi@11.0.9/Unknown Source)
        at java.security.AccessController.doPrivileged(java.base@11.0.9/Native Method)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(java.rmi@11.0.9/TCPTransport.java:676)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1128)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - <0x0000000622be5820> (a java.util.concurrent.ThreadPoolExecutor$Worker)

"RMI Scheduler(0)" #64 daemon prio=9 os_prio=0 cpu=0.31ms elapsed=4.07s tid=0x00007fe26c01f800 nid=0x277c waiting on condition  [0x00007fe2b64eb000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000622b027c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(java.base@11.0.9/LockSupport.java:234)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(java.base@11.0.9/AbstractQueuedSynchronizer.java:2123)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:1182)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(java.base@11.0.9/ScheduledThreadPoolExecutor.java:899)
        at java.util.concurrent.ThreadPoolExecutor.getTask(java.base@11.0.9/ThreadPoolExecutor.java:1054)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1114)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"JMX server connection timeout 65" #65 daemon prio=9 os_prio=0 cpu=7.91ms elapsed=4.06s tid=0x00007fe26c028800 nid=0x277e in Object.wait()  [0x00007fe2b63ea000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x00000006228d5108> (a [I)
        at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(java.management@11.0.9/ServerCommunicatorAdmin.java:171)
        - waiting to re-lock in wait() <0x00000006228d5108> (a [I)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - None

"RMI TCP Connection(2)-127.0.0.1" #66 daemon prio=9 os_prio=0 cpu=23.11ms elapsed=1.93s tid=0x00007fe268003800 nid=0x278f runnable  [0x00007fe2b62e7000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.9/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.9/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.9/SocketInputStream.java:140)
        at java.io.BufferedInputStream.fill(java.base@11.0.9/BufferedInputStream.java:252)
        at java.io.BufferedInputStream.read(java.base@11.0.9/BufferedInputStream.java:271)
        - locked <0x00000006224f2fe0> (a java.io.BufferedInputStream)
        at java.io.FilterInputStream.read(java.base@11.0.9/FilterInputStream.java:83)
        at sun.rmi.transport.tcp.TCPTransport.handleMessages(java.rmi@11.0.9/TCPTransport.java:544)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(java.rmi@11.0.9/TCPTransport.java:796)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(java.rmi@11.0.9/TCPTransport.java:677)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$856/0x0000000840715040.run(java.rmi@11.0.9/Unknown Source)
        at java.security.AccessController.doPrivileged(java.base@11.0.9/Native Method)
        at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(java.rmi@11.0.9/TCPTransport.java:676)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@11.0.9/ThreadPoolExecutor.java:1128)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@11.0.9/ThreadPoolExecutor.java:628)
        at java.lang.Thread.run(java.base@11.0.9/Thread.java:834)

   Locked ownable synchronizers:
        - <0x0000000622be5d60> (a java.util.concurrent.ThreadPoolExecutor$Worker)

"VM Thread" os_prio=0 cpu=96.64ms elapsed=28.83s tid=0x00007fe3a8246800 nid=0x260c runnable  

"GC Thread#0" os_prio=0 cpu=56.51ms elapsed=28.84s tid=0x00007fe3a8032000 nid=0x2606 runnable  

"GC Thread#1" os_prio=0 cpu=41.04ms elapsed=28.52s tid=0x00007fe364001000 nid=0x2615 runnable  

"GC Thread#2" os_prio=0 cpu=40.63ms elapsed=28.52s tid=0x00007fe364002800 nid=0x2616 runnable  

"GC Thread#3" os_prio=0 cpu=41.71ms elapsed=28.52s tid=0x00007fe364004800 nid=0x2617 runnable  

"GC Thread#4" os_prio=0 cpu=40.05ms elapsed=28.52s tid=0x00007fe364006000 nid=0x2618 runnable  

"GC Thread#5" os_prio=0 cpu=40.65ms elapsed=28.52s tid=0x00007fe364008000 nid=0x2619 runnable  

"GC Thread#6" os_prio=0 cpu=40.38ms elapsed=28.52s tid=0x00007fe364009800 nid=0x261a runnable  

"GC Thread#7" os_prio=0 cpu=40.34ms elapsed=28.52s tid=0x00007fe36400b800 nid=0x261b runnable  

"GC Thread#8" os_prio=0 cpu=41.72ms elapsed=28.52s tid=0x00007fe36400d800 nid=0x261c runnable  

"GC Thread#9" os_prio=0 cpu=40.56ms elapsed=28.52s tid=0x00007fe36400f000 nid=0x261d runnable  

"GC Thread#10" os_prio=0 cpu=40.80ms elapsed=28.52s tid=0x00007fe364011000 nid=0x2621 runnable  

"GC Thread#11" os_prio=0 cpu=40.08ms elapsed=28.52s tid=0x00007fe364012800 nid=0x2624 runnable  

"G1 Main Marker" os_prio=0 cpu=1.28ms elapsed=28.84s tid=0x00007fe3a806d000 nid=0x2608 runnable  

"G1 Conc#0" os_prio=0 cpu=17.21ms elapsed=28.84s tid=0x00007fe3a806e800 nid=0x2609 runnable  

"G1 Conc#1" os_prio=0 cpu=17.27ms elapsed=27.69s tid=0x00007fe374001000 nid=0x262d runnable  

"G1 Conc#2" os_prio=0 cpu=14.15ms elapsed=27.69s tid=0x00007fe374002000 nid=0x262e runnable  

"G1 Refine#0" os_prio=0 cpu=0.14ms elapsed=28.84s tid=0x00007fe3a8206800 nid=0x260a runnable  

"G1 Young RemSet Sampling" os_prio=0 cpu=7.20ms elapsed=28.84s tid=0x00007fe3a8208800 nid=0x260b runnable  
"VM Periodic Task Thread" os_prio=0 cpu=15.07ms elapsed=28.81s tid=0x00007fe3a8290800 nid=0x2613 waiting on condition  

JNI global refs: 26, weak refs: 0


```