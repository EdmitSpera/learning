# Optional类
### 思考
- Optional类是用来解决什么问题的？
- Optional类有什么特点？优点和缺点？
- Optional类的实际使用场景有哪些？

```java
public class OptionalDemo {
    public static void main(String[] args) {
        // 创建Optional对象的三种方式
        Optional<Object> empty = Optional.empty();          // 空对象
        Optional<String> object = Optional.of("Object");    // 不空的对象，为空抛出异常
        Optional<String> nullAble = Optional.ofNullable("Object");   // 可空的对象

        // 获取对象值 -> 底层通过泛型value存储
//        System.out.println(empty.get());    // 异常
//        System.out.println(object.get());
//        System.out.println(nullAble.get());

        // 判断 -> isPresent() ifPresent(Consumer<? super T> action)
        System.out.println(empty.isPresent());
        nullAble.ifPresent((data) -> System.out.println(data));

        // 条件控制 orElse()    orElseGet()

        // 转换值map
        //@Test
        //public void testOptionalMap() {
        //    String courseName = Optional.ofNullable(student)
        //            .map(Student::getSchoolClass)
        //            .map(SchoolClass::getCourse)
        //            .map(Course::getCourseName)
        //            .orElse("-");
        //}

        // 过滤值filter
        //String studentName = Optional.ofNullable(student)
        //        .map(Student::getName)
        //        .filter(data -> Objects.equals(data, "张三"))
        //        .orElse("-");
    }
}
```