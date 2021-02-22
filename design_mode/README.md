- 23个设计模式详解
----
- 代理模式的产生
> 某些情况下，不能或不想访问某些对象，如:远程对象较大（视频）、或者内部数据库不允许直接访问
- 代理模式定义
> 由于某些原因需要给某对象提供一个代理以控制对该对象的访问。这时，访问对象不适合或者不能直接引用目标对象，代理对象作为访问对象和目标对象之间的中介。
- 模式结构
> 1. 抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
> 2. 真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
> 3. 代理（Proxy）类：提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
>
- 代理模式优点
1. 中介和保护目标对象
2. 扩展目标对象功能
3. 降低系统耦合度，增加程序扩展性
4. 真实主题只关注自身业务方法，不用关系公共业务，公共业务由代理关注

- 代理模式缺点
1. 造成类的增加
2. 造成请求速度变慢
3. 增加系统复杂度
- 如何尽量避免上述缺点？
```使用动态代理```

- 补充 
> 在代码中，一般代理会被理解为代码增强，实际上就是在原代码逻辑前后增加一些代码逻辑，而使调用者无感知。
  
>根据代理的创建时期，代理模式分为静态代理和动态代理。
  静态：由程序员创建代理类或特定工具自动生成源代码再对其编译，在程序运行前代理类的 .class 文件就已经存在了。
  动态：在程序运行时，运用反射机制动态创建而成

> 这也是SpringAop的实现方式
----