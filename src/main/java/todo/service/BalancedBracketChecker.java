package todo.service;

import java.util.Stack;

public class BalancedBracketChecker {

    public static boolean isBalanced(String text) {
        final Stack<Character> stack = new Stack<Character>();
        boolean result = true;
        char[] chars = text.toCharArray();

        for (int i = 0; result && i < chars.length; i++)  {
            switch (chars[i]) {
                case '(' :
                    stack.push(chars[i]);
                    break;
                case ')':
                    if (!checkExpected(stack, '('))
                        result = false;
                    break;
                case '[' :
                    stack.push(chars[i]);
                    break;
                case ']':
                    if (!checkExpected(stack, '['))
                        result = false;
                    break;
                case '{' :
                    stack.push(chars[i]);
                    break;
                case '}':
                    if (!checkExpected(stack, '{'))
                        result = false;
                    break;
                default:
                    break;
            }
        }
        if (stack.size() > 0) {
            result = false;
        }

        return result;
    }

    public static boolean checkExpected(final Stack<Character> stack, final char ch) {
        boolean result = false;
        if (!stack.empty()) {
            Character schar = stack.peek();
            if (schar.charValue() == ch) {
                stack.pop();
                result = true;
            }
        }
        return result;
    }
}
