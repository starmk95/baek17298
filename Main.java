import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int len = Integer.parseInt(br.readLine());
        String cmd = br.readLine();
        Stack<Integer> stack = new Stack<>();
        String[] stringArray = cmd.split(" ");
        int[] NGE = new int[len];
        /*
        stack에는 오큰수가 정해지지 않은 수들의 위치가 들어간다.
        새로운 숫자의 입력이 들어오면 stack의 top부터 대소비교를 진행한다.
        만약 새로운 숫자의 입력이 크다면, stack에 담긴 숫자(왼쪽의 숫자)의 오큰수가 정해진 것이므로
        이를 stack에서 pop한다.
        새로운 숫자의 입력이 더 작은 경우가 나올때까지 stack의 pop을 계속한다.
        새로운 숫자의 입력이 더 작다면, stack의 top과 새로운 입력 모두 오큰수가 없는 것이다.
        고로 새로운 숫자를 stack에 push한다.
        입력의 끝에 다다를 때까지 해당과정을 반복한다.
        위 과정에서 stack에 push되는 숫자들은 top으로 갈수록 수가 작아지는 내림차순으로 구성된다.
        (새로운 입력이 들어올 때마다 stack의 top과 대소비교를 하고 입력이 작은 경우에만 push하기 때문)
        (이렇게 stack이 구성되면 입력된 수열의 수가 top의 오큰수가 되지 못하면 top 밑의 수들에 대해서도 오큰수가 되지 않는다는 것을 알 수 있음)
         */
        for (int i = 0; i < len; i++) {
            if (stack.empty()) { // stack이 비어있으면 해당 수에서 판별할 오큰수 없는 것이므로 입력수를 stack에 push해주고 다음 반복으로 넘어감
                stack.push(i);
                continue;
            }
            if (Integer.parseInt(stringArray[stack.peek()]) < Integer.parseInt(stringArray[i])) { // 입력으로 들어온 수열의 수가 top보다 크다면 -> stack의 top(오큰수가 정해지지 않은 수)에 대한 오큰수가 등장한 것이다.
                NGE[stack.pop()] = Integer.parseInt(stringArray[i]);
                while (!stack.empty()) { // top 밑의 수들(top보다 큰 수들)에 대해서도 오큰수가 될 수도 있기 때문에 입력수보다 큰 값이 나올때까지 pop하며 대소비교
                    if (Integer.parseInt(stringArray[stack.peek()]) < Integer.parseInt(stringArray[i])) { // 오큰수 성립
                        NGE[stack.pop()] = Integer.parseInt(stringArray[i]); // pop하고 그 아래 수들에 대해서도 대소비교
                    } else { // 오큰수 성립 안함 -> 그 밑의 수들에 대해서도 오큰수가 아니기 때문에 대소비교 중단
                        stack.push(i); // 입력으로 들어온 수도 오큰수가 정해진 수가 아니기 때문에 stack에 넣어주어야 함, 입력수보다 큰 수들은 while문을 통해 pop되었으므로 stack에 있는 수들 중 가장 작은 수가 되어 top이 될 수 있는 조건을 갖추므로 push한다.
                        break; // while문 탈출
                    }
                }
            }
            // 입력으로 들어온 수가 stack의 top보다 작음 -> 오큰수 성립 안함
            stack.push(i); // 입력된 수도 오큰수가 정해지지 않은 수이기 때문에 push (그리고 top보다 작은 수이기 때문에 push, 새로운 top이 됨)
        }
        while (!stack.empty()) { // 모든 입력이 처리된 뒤에도 오큰수가 정해지지 않은 수들에 대한 -1 할당 작업 필요
            NGE[stack.pop()] = -1;
        }
        for (int i = 0; i < len; i++) {
            bw.write(NGE[i] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
