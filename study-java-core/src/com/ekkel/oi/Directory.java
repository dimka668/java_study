package com.ekkel.oi;

import com.ekkel.utils.PPrint;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Directory {

    public static File[] local(File dir, final String regex){
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
    }
    public static File[] local(String path, final String regex){
        return local(new File(path), regex);
    }
    public static class TreeInfo implements Iterable<File>{
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();
        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }
        void addAll(TreeInfo other){
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "dirs " + PPrint.pformat(dirs) +
                    "\n\nfiles: " + PPrint.pformat(files);
        }
    }
    public static TreeInfo walk(String start, String regex){
        return recourseDir(new File(start), regex);
    }
    public static TreeInfo walk(File start, String regex){
        return recourseDir(start, regex);
    }
    public static TreeInfo walk(String start){
        return recourseDir(new File(start), ".*");
    }
    public static TreeInfo walk(File start){
        return recourseDir(start, ".*");
    }
    static TreeInfo recourseDir(File file, String regex){
        TreeInfo result = new TreeInfo();
        for (File item : file.listFiles()) {
            if (item.isDirectory()){
                result.dirs.add(item);
                result.addAll(recourseDir(item, regex));
            }else{
                if (item.getName().matches(regex))
                    result.files.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(walk("."));
    }
}
