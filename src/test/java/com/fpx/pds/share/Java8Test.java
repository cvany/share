
package com.fpx.pds.share;

import com.alibaba.fastjson.JSON;
import com.fpx.pds.utils.TimeUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 岁月
 *
 * @Author: cuiwy
 * @Date: 2019/1/11 13:47
 * @version: v1.0.0
 */
public class Java8Test {
    @Test
    public void test1() {
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param));
        s.convert(4);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    @Test
    public void test2() {
        Car c1 = Car.create(Car::new);
        Car c2 = Car.create(Car::new);
        Car c3 = Car.create(Car::new);
        final Car c4 = Car.create(Car::new);
        List<Car> cars = Arrays.asList(c1, c2, c3);
        cars.forEach(Car::repair);
    }

    static class Car {
        @FunctionalInterface
        public interface Supplier<T> {
            T get();
        }

        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

    interface vehicle {
        static void print() {
            System.out.println("我是汽车");
        }

        default void blowHorn() {
            System.out.println("按喇叭");
        }
    }

    @Test
    public void test3() {
//        List<Integer> list = Arrays.asList(1, 2, 3, 65, 78, 5, 78, 0, -2);
//        list.stream().limit(2).map(a -> a * a).sorted().forEach(System.out::println);
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        int count = (int) strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }


    @Test
    public void test4() {
        long s = System.currentTimeMillis();
        int a = 1;
        for (int i = 0; i < 10000000; i++) {
            a++;
        }
        long e = System.currentTimeMillis();
        System.out.println(a);
        System.out.println("耗时：" + (e - s) + "ms");
    }

    @Test
    public void test5() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

//        inChannel.position();
//        inChannel.size();
        inChannel.force(true);
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
//            buf.compact();
//            buf.mark();
//            buf.reset();
//            buf.equals()
//            buf.compareTo()
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    @Test
    public void test6() throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        selector.select();


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.write(byteBuffer);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.accept();
    }

    @Test
    public void test7() throws InterruptedException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
    }


    @Test
    public void test8() {
        double i = 8;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(i / 13));

        System.out.println(TimeUtil.comsumeTime(43158));
    }

    @Test
    public void test9() {
        Date bdate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            System.out.println("ok");
        } else
            System.out.println("no");
    }

    /**
     * 获取一年中 周日 具体日期
     */
    @Test
    public void test10() {
        int year = 2019;
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar(year, 0, 1);
        int i = 1;
        while (calendar.get(Calendar.YEAR) < year + 1) {
            calendar.set(Calendar.WEEK_OF_YEAR, i++);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            if (calendar.get(Calendar.YEAR) == year) {
                System.out.println("周日：" + simdf.format(calendar.getTime()));
                dateList.add(simdf.format(calendar.getTime()));
            }
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
           /* if (calendar.get(Calendar.YEAR) == year) {
                System.out.println("周六：" + simdf.format(calendar.getTime()));
                dateList.add(simdf.format(calendar.getTime()));
            }*/
        }

        System.out.println(dateList.size());
    }

    /**
     * 获取今天 的前后几天
     */
    @Test
    public void test11() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/E");
        System.out.println("今天是:" + df.format(c.getTime()));
//        c.add(Calendar.DATE, 3);
//        System.out.println("三天后:"+df.format(c.getTime()));
        c.add(Calendar.DATE, -3);
        System.out.println("三天前:" + df.format(c.getTime()));
    }

    @Test
    public void test12() {
        Date date = new Date(1567740067005L);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(df.format(date));
    }

    @Test
    public void test13() {
        StringBuilder builder = new StringBuilder();
        builder.reverse();
        String temp = "a00b";
        char[] cha = temp.toCharArray();
        cha[0] = 0;
        cha[1] = 31;
        cha[2] = 34;
        String str = new String(cha);
        System.out.println(str);
    }

    @Test
    public void test14() {
        long maxValue = Long.MAX_VALUE;
        long minValue = Long.MIN_VALUE;
        System.out.println(maxValue);
        System.out.println(minValue);
        int i = 1 << 31;
        System.out.println(i);
        System.out.println((int) ('0'));
        System.out.println("9223372036854775808".length());
    }

    @Test
    public void test15() {
        System.out.println("4PX-上水石湖墟 HK005點".trim().equals("4PX-上水石湖墟 HK005點"));
        System.out.println("RRKM190820GGGU000001512345678901".length());
    }

    @Test
    public void test16() {
        try {
            long s = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(1);
            long currentTimeMillis = System.currentTimeMillis();
            System.out.println((currentTimeMillis - s) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test17() {
        String s = "234234234234,  5786  ,     ";
        String[] array = s.replaceAll(" +", "").split(",");
        for (String ins : array) {
            System.out.println(ins);
        }
        System.out.println(array.length);
    }

    @Test
    public void test18() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println(JSON.toJSONString(map));
        map.remove("A");
    }

    @Test
    public void test19() {
        // 10的相反数
        System.out.println(10 & (~10 + 1));
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(~10+1));
    }
}