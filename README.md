# Cache providers example

This project is a demo for the cache providers listed in https://blog.frankel.ch/choose-cache/2/.

These are:

| Name                        | Provider              | Source                                             | License                      | Inception                | Local / Distributed   | Non Blocking | JCache | Spring Cache |
|-----------------------------|-----------------------|----------------------------------------------------|------------------------------|--------------------------|-----------------------|--------------|--------|--------------|
| Java Caching System         | The Apache Foundation | [GitHub](https://github.com/apache/commons-jcs)    | Apache 2.0                   | 2002                     | Local and Distributed | No           | Yes    | No           |
| Guava                       | Google                | [GitHub](https://github.com/google/guava)          | Apache 2.0                   | 2010                     | Local                 | No           | No     | No           |
| Caffeine                    | Ben Manes             | [GitHub](https://github.com/ben-manes/caffeine)    | Apache 2.0                   | 2014                     | Local                 | Yes          | Yes    | Yes          |
| Ehcache                     | Software AG           | [GitHub](https://github.com/ehcache/ehcache3)      | Apache 2.0                   | 2009                     | Local                 | No           | Yes    | Yes          |
| Infinispan                  | RedHat                | [GitHub](https://github.com/infinispan/infinispan) | Apache 2.0                   | 2009                     | Local and Distributed | Yes          | Yes    | Yes          |
| Coherence Community Edition | Oracle                | [GitHub](https://github.com/oracle/coherence)      | Universal Permissive License | 2001                     | Local and Distributed | Yes          | Yes    | No           |
| Ignite                      | GridGrain             | [GitHub](https://github.com/apache/ignite)         | Apache 2.0                   | ? (open-sourced 2014)    | Distributed           | Yes          | Yes    | Yes          |
| Geode                       | Pivotal               | [GitHub](https://github.com/apache/geode)          | Apache 2.0                   | 2015 (open-sourced 2019) | Local and Distributed | No           | No     | Yes          |
| Hazelcast                   | Hazelcast             | [GitHub](https://github.com/hazelcast/hazelcast)   | Apache 2.0                   | 2008                     | Local and Distributed | Yes          | Yes    | Yes          |
| Redis                       | Redis                 | [GitHub](https://github.com/redis/redis/)          | BSD 3                        | 2009                     | Distributed           | ?            | Yes    | Yes          |

This is using Java 17.

## Note about Ignite
see [running Ignite with Java 11 or later](https://ignite.apache.org/docs/latest/quick-start/java#running-ignite-with-java-11-or-later).

Ignite uses proprietary SDK APIs that are not available by default. You need to pass specific flags to JVM to make these APIs available. If you use the start-up script ignite.sh (or ignite.bat for Windows), you do not need to do anything because these flags are already set up in the script. Otherwise, provide the following parameters to the JVM of your application:
```text
--add-opens=java.base/jdk.internal.access=ALL-UNNAMED
--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED
--add-opens=java.base/sun.nio.ch=ALL-UNNAMED
--add-opens=java.base/sun.util.calendar=ALL-UNNAMED
--add-opens=java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED
--add-opens=jdk.internal.jvmstat/sun.jvmstat.monitor=ALL-UNNAMED
--add-opens=java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED
--add-opens=jdk.management/com.sun.management.internal=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.base/java.nio=ALL-UNNAMED
--add-opens=java.base/java.net=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent.locks=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.invoke=ALL-UNNAMED
--add-opens=java.base/java.math=ALL-UNNAMED
--add-opens=java.sql/java.sql=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.time=ALL-UNNAMED
--add-opens=java.base/java.text=ALL-UNNAMED
--add-opens=java.management/sun.management=ALL-UNNAMED
--add-opens java.desktop/java.awt.font=ALL-UNNAMED
```
In this repository, these settings are added to the SureFire plugin configuration.
