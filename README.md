# spark-2.4-ml-tutorial
A sample project to demonstrate how to use spark ml workflow

- maven-shade-plugin 3.1 以上的会遇到MuiltiMap找不到的问题
- Logging问题需要依赖log4j以及配scope和exclusion
- spark-submit时可能会遇到缺少jersey类问题
- 如果遇到java.lang.NoSuchMethodError: scala.Predef$.doubleArrayOps
那很可能就是scala编译时版本的问题，建议用2.11与2.12替换一下尝试