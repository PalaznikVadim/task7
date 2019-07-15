import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<String> mass=validate("{asd");
        for(String str:mass){
            System.out.println(str);
        }
    }

    // функция, возвращающая правильные скобковые выражения,
    // полученые путем удаления минимального количества лишних скобок
    static public Set<String> validate(String input){
        Set<String> corrStr=new HashSet<>();

        ArrayDeque<String> queue = new ArrayDeque<String>();

        String temp=new String();

        queue.push(input);

        while(!queue.isEmpty()) {
            input = queue.pop();
            if (isRight(input)) {
                    corrStr.add(input);
                continue;
            }
            for (int i = 0; i < input.length(); i++) {
                if (!isBracket(input.charAt(i)))
                    continue;
                temp = input.substring(0, i) + input.substring(i + 1);
                if (!queue.contains(temp)) {
                    queue.push(temp);
                }
            }
        }

        int max_lenght=0;
        for (String str:corrStr){
            if(max_lenght<str.length()){
                max_lenght=str.length();
            }
        }
        Set<String> answer=new HashSet<>();
        for(String str:corrStr){
            if(max_lenght==str.length()){
                answer.add(str);
            }
        }

        return answer;
    }

    //функция, определяющая является ли переданная строка правильным скобочным выражением или нет
    static boolean isRight(String input){

        String lock = "{";
        String unlock="}";

        int count=0;
        for(int i=0;i<input.length();i++){
            if (input.charAt(i)== lock.charAt(0)) {
                count++;
            }
            else if(input.charAt(i)== unlock.charAt(0)){
                count--;
            }
            if(count<0){
                return false;
            }
        }

        if(count!=0){
            return false;
        }
        else{
            return true;
        }
    }

    //функция,определяющая является ли символ скобкой( lock или unlock)
    static boolean isBracket(char ch){
        return ((ch=='{')||(ch=='}'));
    }
}
