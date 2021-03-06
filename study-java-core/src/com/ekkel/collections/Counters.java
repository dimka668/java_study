package com.ekkel.collections;

import java.util.*;

public class Counters {

    public  static final  String[][] DATA = {
            {"ALGERIA", "Alqiers"}, {"ANGOLA", "Luanda"}
    };

    private static class FlyweightMap extends AbstractMap<String, String> {

        private static class Entry implements Map.Entry<String, String> {
            int index;
            Entry(int index) { this.index = index; }
            public boolean equals(Object o) { return DATA[index][0].equals(o); }

            @Override
            public String getKey() { return DATA[index][0]; }

            @Override
            public String getValue() { return DATA[index][1]; }

            @Override
            public String setValue(String value) { throw new UnsupportedOperationException(); }

            public int hashCode(){ return DATA[index][0].hashCode(); }

        }

        static class EntrySet extends AbstractSet<Map.Entry<String,String>>{

            private int size;
            EntrySet(int size){
                if (size < 0)
                    this.size=0;
                else if (size>DATA.length)
                    this.size=DATA.length;
                else
                    this.size = size;
            }

            @Override
            public int size() { return size; }

            private class Iter implements Iterator<Map.Entry<String,String>>{

                private Entry entry = new Entry(-1);

                @Override
                public boolean hasNext() { return entry.index < size - 1; }

                @Override
                public Map.Entry<String, String> next() {
                    entry.index++;
                    return entry;
                }
            }

            @Override
            public Iterator<Map.Entry<String, String>> iterator() {
                return new Iter();
            }

        }

        private static Set<Map.Entry<String,String>> entries = new EntrySet(DATA.length);

        @Override
        public Set<Map.Entry<String, String>> entrySet() {
            return entries;
        }


    }
}