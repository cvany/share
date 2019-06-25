/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.List;
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




}