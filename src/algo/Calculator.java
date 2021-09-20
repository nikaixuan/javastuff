package algo;

import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        int a = new Calculator().calculate("(1+(4+5+2)-3)+(6+8)");
        System.out.println(a);
    }

    // 224
    public int calculate(String s) {
        int n = 0;
        Stack<Integer> stack = new Stack<>();
        // last sign
        char sign = '+';
        int i = 0;
        // count bracket
        int cnt = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            // get number
            if (c>='0'&&c<='9') {
                n = n * 10 + (c - '0');
            }
            // if having a bracket, take the equation inside it as a new sting and calculate
            // also need to skip all the index inside it.
            if (c == '(') {
                int j = i;
                // count bracket number in case there are multiple
                while(j < s.length()) {
                    if (s.charAt(j)=='(') cnt++;
                    if (s.charAt(j)==')') cnt--;
                    j++;
                    if (cnt==0) break;
                }
                // the string should be the equation inside the bracket
                n = calculate(s.substring(i+1, j-1));
                // move the pointer to the end bracket
                i=j-1;
            }
            if ((!(c>='0'&&c<='9') && c!=' ') || i == s.length()-1) {
                switch (sign) {
                    case '+':
                        stack.push(n);
                        break;
                    case '-':
                        stack.push(-n);
                        break;
                    case '*':
                        stack.push(stack.pop()*n);
                        break;
                    case '/':
                        stack.push(stack.pop()/n);
                        break;
                }
                sign = c;
                n = 0;
            }
            i++;
        }
        int sum = 0;
        while (!stack.empty()) {
            sum+=stack.pop();
        }
        return sum;
    }
}
