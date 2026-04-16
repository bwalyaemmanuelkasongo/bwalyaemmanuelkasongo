import java.util.Stack;

public class infix {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert infix to postfix
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // If '(', push to stack
            else if (c == '(') {
                stack.push(c);
            }
            // If ')', pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }
            // If operator
            else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Function to reverse a string
    static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    // Function to convert infix to prefix
    static String infixToPrefix(String exp) {
        // Reverse infix
        String reversed = reverse(exp);
        // Swap brackets
        reversed = reversed.replace('(', '#').replace(')', '(').replace('#', ')');
        // Convert to postfix
        String postfix = infixToPostfix(reversed);
        // Reverse postfix → prefix
        return reverse(postfix);
    }

    // Main method
    public static void main(String[] args) {
        String infix = "(A-B/C)*(A/K-L)";
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
        System.out.println("Prefix: " + infixToPrefix(infix));
    }
}
