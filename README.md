# spring3demo
Tp build with native compile make sure graal vm is installed

```
openjdk version "17.0.5" 2022-10-18 LTS
OpenJDK Runtime Environment GraalVM 22.3.0 (build 17.0.5+8-LTS)
OpenJDK 64-Bit Server VM GraalVM 22.3.0 (build 17.0.5+8-LTS, mixed mode, sharing)
```

```
./mvnw -Pnative native:compile
```

you should end up with a binary in the target dir which you can run 

```
target/blog-app
```
