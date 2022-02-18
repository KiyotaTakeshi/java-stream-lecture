package com.kiyotakeshi;

import com.kiyotakeshi.beans.Group;
import com.kiyotakeshi.beans.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Playground {

    private static List<Student> students = new ArrayList<>();
    private static List<Group> groups = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        Student popcorn = new Student("popcorn", 50);
        Student yamada = new Student("yamada", 35);
        Student mike = new Student("mike", 70);
        Student taro = new Student("taro", 66);
        students = Arrays.asList(popcorn, yamada, mike, taro);

        Group group = new Group();
        group.add(popcorn);
        group.add(yamada);

        Group group2 = new Group();
        group2.add(mike);
        group2.add(taro);

        groups.add(group);
        groups.add(group2);
    }

    // @see https://speakerdeck.com/shintanimoto/stream-api-ru-men-number-jjug-number-javajo
    // Stream API は How(個別の処理)ではなく What(処理の目的)を列挙するコード
    // stream() で stream を作り
    // filter,map,limit,distinct,sorted などで変換や絞り込みなどの「中間操作」を行い
    // collect,forEach,max,min などの「終端操作」で結果として何かしらの形に集約して取り出す(出力する、変数に代入するなど)
    @Test
    void filter() {
        students.stream() // stream を作成
                .filter(s -> s.getScore() >= 50) // 中間操作(intermediate operation)
                .filter(s -> s.getName().contains("m")) // 中間操作
                .forEach(System.out::println); // 終端操作(terminal operation)

        // 終端操作で stream からコレクションや配列に変換したり、要素ごとに処理したり、要素を集計する
        // 終端で Stream に対して繰り返し処理を行いたい場合は forEach
    }

    @Test
    void sorted() {
//        students.sort((s1, s2) -> Integer.compare(s1.getScore(), s2.getScore()));
//        students.sort(Comparator.comparingInt(Student::getScore));
//        students.forEach(System.out::println);
        students.stream()
                // .sorted((s1, s2) -> Integer.compare(s1.getScore(), s2.getScore()))
                .sorted(Comparator.comparingInt(Student::getScore).reversed())
                .forEach(System.out::println); // java/util/stream/Stream.java
    }

    @Test
    void max() {
        Optional<Student> maxScoreStudent = students.stream()
                .max(Comparator.comparingInt(Student::getScore));

        maxScoreStudent.ifPresent(System.out::println);
    }

    @Test
    void map() {
        // map メソッドは要素を別の値に書き換える中間処理
        // Student オブジェクトから score の値を取り出すことで、 Stream の型が Integer に変わる
        // (以下は確認のためで、実際は Stream インスタンスを変数に代入することはない)
        // Stream<Student> stream = students2.stream();
        // Stream<Integer> integerStream = students2.stream().map(s -> s.getScore());
        // integerStream.forEach(System.out::println);
        students.stream()
                // .map(s -> s.getScore())
                // メソッド呼び出し1つだけならメソッド参照が使える
                .map(Student::getScore)
                .collect(Collectors.toList())
                .forEach(System.out::println); // java/lang/Iterable.java
    }

    @Test
    void flatMap() {
        Stream<List<Student>> studentListStream = groups.stream()
                .map(Group::getStudents);
        studentListStream.forEach(System.out::println);
        // [Student(name=popcorn, score=50), Student(name=yamada, score=35)]
        // [Student(name=mike, score=70), Student(name=taro, score=66)]

        // flatMap は結果を新しい配列内にフラット化する
        // この後は Student に対して処理できる
        Stream<Student> studentStream = groups.stream()
                .flatMap(g -> g.getStudents().stream());
        studentStream.forEach(System.out::println);
        // Student(name=popcorn, score=50)
        // Student(name=yamada, score=35)
        // Student(name=mike, score=70)
        // Student(name=taro, score=66)

        // Student の list に変換
        List<Student> allStudents = groups.stream()
                .flatMap(g -> g.getStudents().stream())
                .collect(Collectors.toList());

        allStudents.stream()
                // sorted は2つの引数を受け付けて int を返す関数オブジェクトを指定する
                // Comparator は戻り値の int によって挙動が変わる
                // 0より小さい場合は第1引数、大きい場合は第2引数のオブジェクトが前方になる
                .sorted((s1, s2) -> s2.getScore() - s1.getScore())
                // .sorted(Comparator.comparingInt(Student::getScore).reversed())
                .forEach(s -> System.out.println(s.getName() + " " + s.getScore()));
    }

    @Test
    void groupingBy() {
        List<Student> students3 = Arrays.asList(
                new Student("popcorn", 78),
                new Student("taro", 60),
                new Student("mike", 100),
                new Student("john", 100)
        );
        Map<Integer, List<Student>> studentMap = students3.stream()
                .collect(Collectors.groupingBy(Student::getScore));

        List<Student> perfectScoreStudents = studentMap.get(100);
        perfectScoreStudents.forEach(s -> System.out.println(s.getName()));
    }

    @Test
    void mapAndFilter() {
        List<String> list = Arrays.asList("Taro", "Ichiro", "Saburo");
        list.stream()
                .filter(e -> e.length() > 5)
                .map(e -> "[" + e + "]")
                .forEach(System.out::println);
    }
}
