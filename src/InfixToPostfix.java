import java.util.Stack;

public class InfixToPostfix {
    // 方法：将中缀表达式转换为后缀表达式
    public static String infixToPostfix(String exp) {
        StringBuilder output = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        for (char c : exp.toCharArray()) {
            // 如果是操作数（这里简化处理，只考虑单字符变量），直接输出
            if (Character.isLetterOrDigit(c)) {
                output.append(c);
            }
            // 左括号，直接压栈
            else if (c == '(') {
                operators.push(c);
            }
            // 右括号，弹出操作符直到遇到左括号
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.append(operators.pop());
                }
                operators.pop(); // 弹出左括号
            }
            // 操作符
            else {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    output.append(operators.pop());
                }
                operators.push(c);
            }
        }

        // 弹出所有剩余的操作符
        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                return "Invalid Expression"; // 错误的输入表达式
            }
            output.append(operators.pop());
        }

        return output.toString();
    }

    // 方法：比较操作符优先级
    public static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1; // 操作符不合法
    }

    public static void main(String[] args) {
        String infixExp = "(a+b)*c-(d/(e+f))-g";
        String postfixExp = infixToPostfix(infixExp);
        System.out.println("Postfix expression: " + postfixExp);
    }
}
