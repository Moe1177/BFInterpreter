import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String code = "1 +++++ +++               Set Cell #0 to 8\n" +
                " 2 [\n" +
                " 3     >++++               Add 4 to Cell #1; this will always set Cell #1 to 4\n" +
                " 4     [                   as the cell will be cleared by the loop\n" +
                " 5         >++             Add 4*2 to Cell #2\n" +
                " 6         >+++            Add 4*3 to Cell #3\n" +
                " 7         >+++            Add 4*3 to Cell #4\n" +
                " 8         >+              Add 4 to Cell #5\n" +
                " 9         <<<<-           Decrement the loop counter in Cell #1\n" +
                "10     ]                   Loop till Cell #1 is zero\n" +
                "11     >+                  Add 1 to Cell #2\n" +
                "12     >+                  Add 1 to Cell #3\n" +
                "13     >-                  Subtract 1 from Cell #4\n" +
                "14     >>+                 Add 1 to Cell #6\n" +
                "15     [<]                 Move back to the first zero cell you find; this will\n" +
                "16                         be Cell #1 which was cleared by the previous loop\n" +
                "17     <-                  Decrement the loop Counter in Cell #0\n" +
                "18 ]                       Loop till Cell #0 is zero\n" +
                "19 \n" +
                "20 The result of this is:\n" +
                "21 Cell No :   0   1   2   3   4   5   6\n" +
                "22 Contents:   0   0  72 104  88  32   8\n" +
                "23 Pointer :   ^\n" +
                "24 \n" +
                "25 >>.                     Cell #2 has value 72 which is 'H'\n" +
                "26 >---.                   Subtract 3 from Cell #3 to get 101 which is 'e'\n" +
                "27 +++++ ++..+++.          Likewise for 'llo' from Cell #3\n" +
                "28 >>.                     Cell #5 is 32 for the space\n" +
                "29 <-.                     Subtract 1 from Cell #4 for 87 to give a 'W'\n" +
                "30 <.                      Cell #3 was set to 'o' from the end of 'Hello'\n" +
                "31 +++.----- -.----- ---.  Cell #3 for 'rl' and 'd'\n" +
                "32 >>+.                    Add 1 to Cell #5 gives us an exclamation point\n" +
                "33 >++.                    And finally a newline from Cell #6";
        int[] memory = new int[30000];
        int memPointer = 0;
        int counterClosed = 0;
        int counterOpen = 0;

        for (int i = 0; i < code.length(); i++) {
            char codeChar = code.charAt(i);

            if (codeChar == '+') { // Increments the number in the index
                memory[memPointer]++;
            } else if (codeChar == '-') { // Decrements the number in the index
                memory[memPointer]--;
            } else if (codeChar == '>') { // Increments the index
                if (memPointer == 29999) {
                    System.out.println(">Error: " + memPointer);
                } else if (memPointer < 29999) {
                    memPointer++;
                }
            } else if (codeChar == '<') { // Decrements the index
                if (memPointer == 0) {
                    System.out.println("<Error: " + memPointer);
                } else if (memPointer > 0) {
                    memPointer--;
                }
            } else if (codeChar == '.') { // prints the number in the index (acsii) to the console
                System.out.print((char) memory[memPointer]);
            } else if (codeChar == ',') { // Stores the inputted value into the index
                int storeNum = scanner.nextInt();
                memory[memPointer] = storeNum;
            } else if(codeChar == '[') { // If the number in index is 0 it finds the matching bracket
                if (memory[memPointer] == 0) {
                    for (int bracketOpenIndex = i; bracketOpenIndex < code.length(); bracketOpenIndex++) {
                        char closed = code.charAt(bracketOpenIndex);
                        if (closed != '[' && closed != ']') {
                            i++;
                        }  else if (closed == ']') {
                            counterClosed++;
                            i++;
                        } else if (closed == '[') {
                            counterOpen++;
                            i++;
                        }
                        if (counterOpen == counterClosed) {
                            i--;
                            break;
                        }
                    }
                }

            } else if (codeChar == ']') {
                if (memory[memPointer] != 0) {
                    for (int bracketClosedIndex = i; bracketClosedIndex >= 0; bracketClosedIndex--) {
                        char opened = code.charAt(bracketClosedIndex);
                        if (opened != '[' && opened != ']') {
                            i--;
                        } else if (opened == '[') {
                            counterOpen++;
                            i--;
                        } else if (opened == ']') {
                            counterClosed++;
                            i--;
                        } if (counterClosed == counterOpen) {
                            i++;
                            break;
                        }
                    }
                }
            }
        }
        System.out.print(Arrays.toString(memory));
    }
}