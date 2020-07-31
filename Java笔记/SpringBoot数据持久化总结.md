## 关于Spring-Boot数据持久化

### 1. JdbcTemplate

关于使用：

>1. 引入依赖。
>
>2. 定义接口
>
>3. 实现接口，并使用@Repository注解，Spring-boot的组件扫描会发现这个注解，然后把这个类作为bean添加到应用上下文中
>
>4. 定义私有成员：JdbcTemplate对象jdbc，并且使用构造函数初始化，使用@AutoWired注入。
>
>5. 使用jdbc进行数据库操作，但是JdbcTemplate对于插入操作有点繁琐，所以SimpleJdbcInsert对JdbcTemplate进行了封装，简化了插入操作。
>
>6. SimpleJdbcInsert使用JdbcTemplate的对象进行初始化
>
>   ```Java
>   this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("order").usingGeneratedKeyColumns("id"); //绑定表并且id使用由数据库生成的。
>   ```
>
>7. SimpleJdbcInsert使用execute(value)和executeAndReturnKey(value)执行插入操作，传入的参数value是一个map，key是数据库表中列名，value是要插入的值。类对象转map有个方法可以简单实现，内部实现方法也很简单，就是利用反射，找到类对象的成员字段，插入到Map中。
>
>   ```Java
>   objectMapper.convertValue(order, Map.class)
>   ```

### 2. Spring   Data JPA

使用JPA进行数据库操作十分方法，如果仅是简单的数据库操作那么基本不需要额外的写SQL语句，甚至不需要实现具体的类进行数据库操作。

关于基本使用

>1. 首先引入依赖
>
>2. 使用@Entity将Model对象标记为JPA实体，标记为@Entity的类将会与数据库中的表对应，成员字段与对应表项对应。实体对象需要无参的构造函数。
>
>3. 使用@Id将成员字段标记为主键。
>
>4. 有的字段是执行插入时生成的，比如常有的“日期”，对于类似的成员字段，可以通过@Prepersist标记方法，然后在执行插入时就会先执行这个方法，进行一些初始化操作。
>
>   ```Java
>   @PrePersist
>       void createdAt(){
>           this.createdAt = new Date();
>       }
>   ```
>
>5. 声明JPA Repository，自定义一个接口继承CrudRepository接口即可。基本的数据库操作方法都定义在CrudRepository里了，所以我们的Repository也不需要额外的定义，更方便的是不需要像JdbcTemplate一样需要自己去实现这个类，类的实现是由Spring框架在运行时通过反射实现的。范型参数一表示实体类型，参数二表示数据库表主键类型
>
>   ```Java
>   public interface IngredientRepository extends CrudRepository<Ingredient,String> {
>   }
>   ```
>
>6. 如果CrudRepository定义的基本方法不符合需要，那么在自己的接口里再定义方法即可，自定义的方法体也是不需要具体实现的，Spring会根据方法名/参数/返回值来推测这个方法体的执行操作。但这种方法是比较危险的，为了更保险一点，可以在方法前添加注解比如：@Query("")来指明具体的数据库操作。

注意事项

>1. 默认情况下，Spring会在启动时，根据@Entity标记的实体类检查数据库中的表，若数据库中没有对应的表，就会自动建表。spring.jpa.hibernate.ddl-auto=none禁止自动建表。Spring建立数据库时把驼锋形式的类名/成员字段以xxx_xxx_xxx的创建。
>
>2. 默认情况下，Spring将实体类名对应数据库表名，成员字段名对应表项(驼锋转为xx_xx形式)。但也是可以指定的.
>
>   ```Java
>   @Table(name = "User") //指定表名
>   @Column(name = "id") //指定列名
>   ```
>
>3. 上面说到，JPA实体类需要有无参的构造函数，但是当类有未初始化的final类型字段时，需要有带参构造函数，这时默认的无参构造函数就没有了，可以自定义一个，也可以使用Lombok的注解
>
>   ```Java
>   @RequiredArgsConstructor
>   @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
>   ```
>
>4. 常见的Java数据类型都可以与数据库中的数据类型对应，Spring可以自动转换，但是类对象/或者枚举类型，不可以。对于枚举对象，可以与数据中Int类型对应，Spring自动建表的话，就会建立为数值类型。所以当Spring无法进行自动转换的类型，需要自行写转换方法。
>
>   首先，实现AttributeConverter接口，范型参数一代表实体字段类型，参数二对应数据库中表项类型
>
>   ```Java
>   public class IngredientTypeConverter implements AttributeConverter<Ingredient.Type, String>{
>       
>   }
>   ```
>
>   其次，在实体的成员字段里用@Convert标记，这样Spring就会调用执行转换。
>
>   ```Java
>   @Convert(converter = IngredientTypeConverter.class)
>   private final Type type;
>   ```
>
>5. 对于主键字段，若Id需要自动生成，可以使用注解，JPA提供了四种策略
>
>   ```Java
>   @Id
>   @GeneratedValue(strategy = GenerationType.IDENTITY) 
>   @Column(name = "id")
>   private Long id;
>   
>   TABLE：使用一个特定的数据库表格来保存主键。 
>   SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。 
>   IDENTITY：主键由数据库自动生成（主要是自动增长型） 
>   AUTO：主键由程序控制。
>   ```
>
>6. 对于多表联合，举例说明: 
>
>   假设Taco对应的数据库表为Taco，Ingredient对应的数据库表为Ingredient，joinColumns代表主表Taco，inverseJoinColumns代表副表Ingredient。联合表是Taco_Ingredients，列"taco"引用Taco表的列"id"，列"ingredient"引用Ingredient表的列"id"。
>
>   ```Java
>   @Data
>   @Entity
>   public class Taco {
>       @ManyToMany(targetEntity = Ingredient.class) //表明一个Taco可以有多个Ingredient
>       @JoinTable(name = "Taco_Ingredients",
>               joinColumns = {@JoinColumn(name = "taco",referencedColumnName = "id")},
>               inverseJoinColumns = {@JoinColumn(name = "ingredient",referencedColumnName = "id")})
>       private List<Ingredient> ingredients;
>   }
>   ```
>
>   默认情况下，若只有@ManyToMany，相当于下面：
>
>   ```Java
>   @JoinTable(name = "Taco_Ingredients",
>               joinColumns = {@JoinColumn(name = "taco_id",referencedColumnName = "id")},
>               inverseJoinColumns = {@JoinColumn(name = "ingredient_id",referencedColumnName = "id")})
>   ```

