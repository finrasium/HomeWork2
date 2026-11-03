package task2;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class ArrayVsLinked {
    private static final int size = 100000;
    private static final int numberOfWarmUps = 2;
    private static final int numberOfMeasures = 5;

    public static void main(String[] args){
        System.out.println("                              ArrayList          LinkedList");
        measure("1. Добавление в конец", list -> addToEnd(list));
        measure("2. Добавление в начало", list -> addToStart(list));
        measure("3. Вставка в середину", list -> addToMid(list));
        measure("4. Доступ по индексу", list -> getByIndex(list));
        measure("5. Удаление из начала", list -> removeFromStart(list));
        measure("6. Удаление из конца", list -> removeFromEnd(list));

    }
    public static void measure(String operationName, Operation operation) {
        for (int i = 0; i < numberOfWarmUps; i+=1){
            operation.run(new ArrayList<>());
            operation.run(new LinkedList<>());
        }
        long[] arrayListTimes = new long[numberOfMeasures];
        long[] linkedListTimes = new long[numberOfMeasures];

        for (int i = 0; i < numberOfMeasures; i+=1) {
            arrayListTimes[i] = funnyTimer(() -> operation.run(new ArrayList<>()));
            linkedListTimes[i] = funnyTimer(() -> operation.run(new LinkedList<>()));
        }
        long medianArrayList = mediana(arrayListTimes);
        long medianLinkedList = mediana(linkedListTimes);

        System.out.printf("%-30s | %-15.3f | %-15.3f%n",
                operationName, medianArrayList / 1_000_000.0, medianLinkedList / 1_000_000.0);
    }
    private static long funnyTimer(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        return System.nanoTime() - start;
    }
    private static long mediana(long[] times){
        long[] sorted = times.clone();
        Arrays.sort(sorted);
        return sorted[2];
    }
    @FunctionalInterface
    private interface Operation{
        void run(List<Integer> list);
    }

    private static void addToEnd(List<Integer> list){
        for (int i=0; i<size; i+=1){
            list.add(i);
        }
    }
    private static void addToStart(List<Integer> list){
        for (int i=0; i<size; i+=1){
            list.add(0, i);
        }
    }
    private static void addToMid(List<Integer> list){
        for (int i=0; i<size; i+=1){
            list.add(list.size()/2, i);
        }
    }
    private static void getByIndex(List<Integer> list) {
        for (int i = 0; i < size; i+=1) {
            list.add(i);
        }
        long sum = 0;
        for (int i = 0; i < size; i+=1) {
            sum += list.get(i);
        }
    }
    private static void removeFromStart(List<Integer> list) {
        for (int i = 0; i < size; i+=1) {
            list.add(i);
        }
        while (!list.isEmpty()) {
            list.remove(0);
        }
    }
    private static void removeFromEnd(List<Integer> list) {
        for (int i = 0; i < size; i+=1) {
            list.add(i);
        }
        while (!list.isEmpty()) {
            list.remove(list.size()-1);
        }
    }
}
