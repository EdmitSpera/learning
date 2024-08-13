package 开发实用.demo;

public class LambdaDemo {
    public static void main(String[] args) {
        senMessage((name) -> {
            System.out.println(name);
            return name;
        },"email");
    }

    static String senMessage(Message message, String messageName){
        return message.send(messageName);
    }
}
