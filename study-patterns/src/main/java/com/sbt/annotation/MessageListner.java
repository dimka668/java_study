package com.sbt.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessageListner
{
    private static final Map<String, Method> commands = new HashMap<>(); //Map который хранит как ключ команду ("привет"), а как значение функцию которая будет обрабатывать команду
    private static final CommandListener listener = new CommandListener(); //Объект класса с командами (по сути нужен нам для рефлекции)

    static
    {

        for (Method m : listener.getClass().getDeclaredMethods()) //Берем список всех методов в классе CommandListener
        {
            if (m.isAnnotationPresent(Command.class)) //Смотрим, есть ли у метода нужная нам Аннотация @Command
            {
                Command cmd = m.getAnnotation(Command.class); //Берем объект нашей Аннотации
                commands.put(cmd.name(), m); //Обращаемся к аргументу name (чтобы использовать его как ключ), m - переменная хранящая наш метод
                for (String s : cmd.aliases())  //Также заносим каждый элемент aliases как ключ указывающий на тот же самый метод.
                {
                    commands.put(s, m);
                }
            }
        }
    }

    public void onMessageReceived(MessageReceivedEvent event)
    {

        String message = event.getMessage().toLowerCase();
        System.out.println(message);
        if (message.startsWith("бот"))
        {
            try
            {
                String[] args = message.split(" ");
                String command = args[1].toLowerCase();
                String[] nArgs = Arrays.copyOfRange(args, 2, args.length);
                Method m = commands.get(command);
                if (m == null)
                {
                    //(вывод помощи)
                    return;
                }
                //Command com = m.getAnnotation(Command.class);
                //System.out.println(m.getName());
                m.invoke(listener, new Object[]{nArgs}); //Через рефлекцию вызываем нашу функцию-обработчик (именно потому что мы всегда передаем nArgs у функции должен быть параметр String[] args - иначе она просто не будет найдена);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                //Вывод списка команд или какого-либо сообщения, в случае если просто написать "Бот"
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
