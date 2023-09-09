import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.split(" ");
        String temp = s[0];
        int n = Integer.parseInt(s[1]);
        Solution solution = new Solution();
        String res = solution.reverseLeftWords(temp, n);
        System.out.println(res);
    }
}

class Solution {
    public String reverseLeftWords(String s, int n) {
        char[] c = s.toCharArray();
        reverse(c, 0, c.length - 1);
        reverse(c, 0, c.length - n - 1);
        reverse(c, c.length - n, c.length - 1);
        return String.valueOf(c);
    }
    void reverse(char[] c, int begin, int end) {
        for (int i = begin, j = end; i < j; i++, j--) {
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
        }
    }
}