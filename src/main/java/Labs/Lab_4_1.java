package Labs;

import MinSearch.MinStack;
import Classes.MinStackImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lab_4_1 {
    public static void start(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Для читки с консоли
            StringBuilder sb = new StringBuilder(); // Для конечного ответа
            MinStack<Integer> stack = new MinStackImpl();
            String line;

            System.out.println("Введите команды (push число, pop, top, min, exit):");

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                int sep = line.indexOf(' '); // Парсим строку до сюда (сепаратор)
                String command;
                if (line.equals("exit")){ // Выход
                    break;
                }
                if (sep == -1) { // Если сепаратора нет, то всю строку
                    command = line;
                }
                else {
                    command = line.substring(0, sep); // Парсим до сепаратора
                }

                switch (command) {
                    case "push" -> {
                        int data = Integer.parseInt(line.substring(sep+1).trim()); // Считываем число, удаляем пробелы, преобразуем в int
                        stack.push(data);
                    }
                    case "pop" -> stack.pop();
                    case "top" -> sb.append(stack.top()).append('\n');
                    case "min" -> sb.append(stack.min()).append('\n');
                }
            }
            System.out.println("Вывод: ");
            System.out.println(sb);
        } catch (IOException err) {
            throw new RuntimeException(err);
        }

    }
}
