### SpringBoot学习笔记

###### 问题1：关于注解@Valid/@ModelAttribute，以及Model类对象

关于@ModelAttribute以及@SessionAttributes的详细解释：https://www.jianshu.com/p/0ec4e7afb7ed

场景：Model层：design.html提交了一个表单，其中使用属性"design",th:object="${design}"，后端校验失败就返回并显示错误信息。

```java
@PostMapping
public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors,Model model) {
        if(errors.hasErrors()){
            return "design";
        }
        log.info("处理订单： "+design);
        //重定向到order/current
        return "redirect:/orders/current";
    }
```

解答：

1. 注解@Valid是对形参design进行校验，在Taco类的每个成员字段都有注解定义了校验标准。校验是在调用方法之前完成，调用方法的时候把错误信息保存在errors中。
2. Model的实例model总是存在的，保存了Controller与View之间传递的信息
3. 作用于参数的ModelAttribute注解的有两个作用。第一个是从前端传来的数据中读出指定属性名(当缺省的时候，就默认为形参的类名小写)的数据，然后写入后面的形参中，这里是design。第二步是：添加到model中，相当于model.addAttribute("design",design)。**但还有更多的作用**。
4. 当没有这个注解时，Taco design这个对象也会添加到model中，只是属性名会使用默认值，即类名(小写)"taco"。但是，design.html中使用了design属性，这时由于model中没有这条字段，就会渲染失败。
5. 但若在方法体内部使用model.addAttribute("design",design)显式添加也是可以的，渲染可以成功，但是仍然存在问题。
6. 因为model中不止"design"字段，还有另外一条保存了org.framework.validation.BindingResult.taco字段，这个字段保存了很多额外的信息，其某一个属性errors就会保存检查的结果，但这与"taco"绑定了，并没有与我们期望的"design"绑定，所以在design.html会找不到error信息。@Valid注解就无意义了，虽然进行了检查，但前端接收不到结果。

**注意：**当@ModelAttribute(name="att")作用于方法体时，每次进入这个类，都会首先执行这个方法。但是@ModelAttribute("att")作用于方法体且属性名与@SessionAttributes("att")的属性名相同时，该方法体只会相对于会话执行一次。

---



###### 问题二：服务器渲染和浏览器渲染的区别

浏览器渲染路线：1. 请求一个html -> 2. 服务端返回一个html -> 3. 浏览器下载html里面的js/css文件 -> 4. 等待js文件下载完成 -> 5. 等待js加载并初始化完成 -> 6. js代码终于可以运行，由js代码向后端请求数据( ajax/fetch ) -> 7. 等待后端数据返回 -> 8. 客户端从无到完整地，把数据渲染为响应页面

服务端渲染路线：2. 请求一个html -> 2. 服务端请求数据( 内网请求快 ) -> 3. 服务器初始渲染（服务端性能好，较快） -> 4. 服务端返回已经有正确内容的页面 -> 5. 客户端请求js/css文件 -> 6. 等待js文件下载完成 -> 7. 等待js加载并初始化完成 -> 8. 客户端把剩下一部分渲染完成( 内容小，渲染快 )

---

###### 问题三：使用内嵌的H2数据库

首先在pom.xml中添加依赖

```XML
<dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
```

然后，SpringBoot会在应用启动时加载resources下的schema.sql和data.sql，可以分别在里面建表和添加一些表项。

最后，程序运行时可以在浏览内查看数据库，通过localhost:8080/h2-console。为了成功登录，需要在application.properties中设置一下，然后访问登录时按照设置的登录即可。

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
```

