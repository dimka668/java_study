package com.ekkel.oi;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;
import com.ekkel.utils.*;

/**
 * Created by 16688641 on 21.01.2019.
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String,Charset> charSets = Charset.availableCharsets();
        Iterator it = charSets.keySet().iterator();
        while (it.hasNext()){
            String csName = (String) it.next();
            System.out.print(csName);
            Iterator aliaces = charSets.get(csName).aliases().iterator();
            if (aliaces.hasNext())
                System.out.print(": ");
            while (aliaces.hasNext()){
                System.out.print(aliaces.next());
                if (aliaces.hasNext())
                    System.out.print(", ");
            }
            System.out.println();
        }
    }
}
