package com.sbt.annotation;

import java.lang.reflect.Method;

/**
 * Created by SBT-Klyshov-DA on 26.06.2018.
 */
public class CommandListener
{
    @Command(name = "привет",
            args = "",
            desc = "Будь культурным, поздоровайся",
            showInHelp = false,
            aliases = {"здаров"})
    public void hello(String[] args)
    {
        System.out.println("Hello");
    }

    @Command(name = "пока",
            args = "",
            desc = "",
            aliases = {"удачи"})
    public void bie(String[] args)
    {
        System.out.println("Bye");
    }

    @Command(name = "помощь",
            args = "",
            desc = "Выводит список команд",
            aliases = {"help", "команды"})
    public void help(String[] args)
    {
        StringBuilder sb = new StringBuilder("Список команд: \n");
        for (Method m : this.getClass().getDeclaredMethods())
        {
            if (m.isAnnotationPresent(Command.class))
            {
                Command com = m.getAnnotation(Command.class);
                if (com.showInHelp()) //Если нужно показывать команду в списке.
                {
                    sb.append("Бот, ").append(com.name()).append(" ").append(com.args()).append(" - ").append(com.desc()).append("\n");
                }
            }
        }
        System.out.println(sb.toString());

    }
}