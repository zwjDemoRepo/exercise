/**
 * Created by Administrator on 2017/11/11 0011.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {
    public static void main(String[] args) {
        System.out.println("Tip: please input the Reverse Polish Notation:");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if(line != null && !line.isEmpty()){
            //用Stack<Integer>处理
            int res = resolve01(line.trim());
            System.out.println(String.valueOf(res));
        }
    }
    //0表示空格“ ”，1表示^自增，2表示+，3表示*乘法。4表示数字
    public static int resolve01(String str){
        //先对Str做处理。
        Stack<Integer> sta = new Stack<Integer>();
        String[] value = str.split(" ");
        for(int i=0;i<value.length;i++){
            int result = isCharacterOrDigit(value[i]);
            if(result == 4){
                //栈上溢出
                if(sta.size()>15){
                    return -2;
                }else{
                    sta.push(Integer.parseInt(value[i]));
                }
            }else if(result == 3){
                //栈下溢出
                if(sta.size()<2){
                    return -1;
                }else{
                    sta.push(sta.pop()*sta.pop());
                }
            }else if(result == 2){
                //栈下溢出
                if(sta.size()<2){
                    return -1;
                }else{
                    sta.push(sta.pop()+sta.pop());
                }
            }else if(result ==1){
                //栈下溢出
                if(sta.size()<1){
                    return -1;
                }else{
                    int aim = sta.pop()+1;
                    sta.push(aim);
                }
            }
        }
        return sta.peek();
    }
    //0表示空格“ ”(空格的长度为0)，1表示^自增，2表示+，3，表示*乘法。4表示数字
    public static int isCharacterOrDigit(String str){
        if(str.length() == 0){
            return 0;
        }else if(str.equals("^")){
            return 1;
        }else if(str.equals("+")){
            return 2;
        }else if(str.equals("*")){
            return 3;
        }else{
            //在此判断非法字符，返回-1，不处理。
            try {
                Integer.parseInt(str);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return -1;
            }
            return 4;
        }
    }
}
