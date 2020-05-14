package com.klyshov;

import com.sun.xml.internal.fastinfoset.stax.events.ReadIterator;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Main {

    static Logger LOGGER = Logger.getLogger(Main.class.getName());;
    static final String baseUrl = "https://www.booksite.ru/";
    private static final int CONNECTION_TIMEOUT = 30000;

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        HashMap<String, ArrayList<HashMap<String, String>>> data = new HashMap<>();

        ArrayList<String> urls = getChildURLs("https://www.booksite.ru/alph.htm", "href=[\"'](\\w{1,4}.html?)[\"']");
        ExecutorService service = Executors.newFixedThreadPool(50);
        urls.forEach((url) -> {
            data.put(url, new ArrayList<>());
            service.submit(new Runnable() {
                public void run() {
                    try {
                        LOGGER.info("Get content: " + baseUrl + url);
                        String letterPageContent = getContent(baseUrl + url);
                        Iterator bookIterator = new RegexpHelper(letterPageContent, "<p\\s+class\\s*=\\s*[\"'][^\"']*bk_open[^\"']*[\"']\\s*>(.+?)<\\s*/\\s*p\\s*>").iterator();
                        while (bookIterator.hasNext()) {
                            String book = (String) bookIterator.next();
                            Iterator urlIterator = new RegexpHelper(book, "href=[\"'](.+?)[\"']").iterator();
                            String bookUrl = (String) urlIterator.next();
                            synchronized (data) {
                                HashMap<String,String> bookHashMap = new HashMap<>();
                                bookHashMap.put("text", book.replaceAll("<.+?>", "").replaceAll("\\s+", " "));
                                bookHashMap.put("url", bookUrl);
                                data.get(url).add(bookHashMap);
                                LOGGER.info("bookData: " + "text: " + bookHashMap.get("text") + "; url: " + bookHashMap.get("url"));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        });
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(data);
        long timeSpent = System.currentTimeMillis() - startTime;
        //data.values().forEach(x -> System.out.println(x.size()));
        System.out.println(data.values().stream().mapToInt(i -> i.size()).sum());
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
/*
        String pageContent = getContent("https://www.booksite.ru/library/14.htm");
        Iterator bookIterator = new RegexpHelper(pageContent, "<ul>(.+?)</ul>").iterator();
        while (bookIterator.hasNext()){
            String bookItem = (String) bookIterator.next();
            //System.out.println(bookItem.replaceAll(">\\s+", ">\n"));
            System.out.println(
                    bookItem.replaceAll(">\\s+", ">")
                            .replaceAll("\\s+<", "<")
                            .replaceAll("\\s+", " ")
                            .replaceAll("<.+?>", ""));
            Iterator urlIterator = new RegexpHelper(bookItem, "href=\"(.+?)\"").iterator();
            System.out.println(urlIterator.next());
        }
        */
    }

    private static ArrayList<String> getChildURLs(String url, String pattern) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        String pageContent = getContent(url);
        Iterator urlIterator = new RegexpHelper(pageContent, pattern).iterator();
        while (urlIterator.hasNext()) {
            String childUrl = (String) urlIterator.next();
            result.add(childUrl);
            LOGGER.info("add childUrl: " + childUrl);
        }
        return result;
    }

    private static String getContent(String urlString) throws IOException {
        final URL url = new URL(urlString);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "text/html");
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(CONNECTION_TIMEOUT);

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "windows-1251"))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
