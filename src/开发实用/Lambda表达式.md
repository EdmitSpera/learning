# Lambda表达式
### 思考
- Lambda表达式的出现是为了解决什么问题的？

Lambda表达式的出现主要是解决函数式编程的问题，能够有效地简化对于接口中的抽象方法的实现

传统的接口实现的代码较为冗长
```java
/**
 * 消息接口
 */
public interface Message {
    String send(String messageName);
}

/**
 * 消息接口实现类
 */
public class MessageImpl implements Message{

    @Override
    public String send(String messageName) {
        System.out.println(messageName + " message");
        return "true";
    }
}

/**
 * 客户端
 */
public class Application {
    public static void main(String[] args) {
        MessageImpl message = new MessageImpl();
        senMessage(message, "email");
    }

    static String senMessage(Message message, String messageName){
        return message.send(messageName);
    }
}
```
- Lambda表达式的特征是什么
  - 省略方法名、方法的参数类型（编译器中可以推断出参数类型，具体参数的类型已经在接口的抽象方法里定义了）
  - 只需要声明参数名和实现的函数体即可
```java
Object lambdaExpression = (Object param) -> {};
```
上面的代码可以简化为
```java
public class Application {
    public static void main(String[] args) {
        senMessage((name) -> {
            System.out.println(name);
            return name;
        }, "email");
    }

    static String senMessage(Message message, String messageName) {
        return message.send(messageName);
    }
}

```

- 在哪些场景下Lambda表达式能够发挥其作用
函数式的接口才可以使用Lambda表达式，函数时接口只允许一个抽象方法

其他常见的场景比如说是Stream类和Optional类都要Lambda表达式的影子