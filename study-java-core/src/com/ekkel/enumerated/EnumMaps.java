package com.ekkel.enumerated;

import java.util.EnumMap;
import java.util.EnumSet;
import static com.ekkel.enumerated.AlarmPoints.*;

/**
 * Created by 16688641 on 23.01.2019.
 */
interface Command{
    void action();
}

public class EnumMaps {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> pointsSet = EnumSet.allOf(AlarmPoints.class);
        EnumMap<AlarmPoints, Command> points = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        points.put(BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("bathroom");
            }
        });
        points.entrySet().forEach(x -> {
            System.out.println(x.getKey() + ": ");
            x.getValue().action();
        });
    }
}
