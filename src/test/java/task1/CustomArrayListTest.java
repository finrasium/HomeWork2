package task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayListTest {
    private CustomArrayList<String> list;
    @BeforeEach
    void setUp(){
        list = new CustomArrayList<>();
    }
    @Test //пункт 1
    void isNewListEmpty(){
        assertTrue(list.isEmpty());
        assertEquals(0,list.size());
    }
    @Test //пункт 2
    void addingAndOrder(){
        list.add("Элвин");
        list.add("Саймон");
        list.add("Теодор");

        assertEquals("Элвин", list.get(0));
        assertEquals("Саймон", list.get(1));
        assertEquals("Теодор", list.get(2));
    }
    @Test //пункт 3
    void cartoonAddTen(){
        for (int i =0;i<20;i+=1){
            list.add("Elemento numero " + i);
        }
        assertEquals(20,list.size());
        for (int i = 0; i < 15; i++) {
            assertEquals("Elemento numero " + i, list.get(i));
        }
    }
    @Test //пункт 4
    void getusElementus(){
        list.add("Китайский");
        list.add("АААААнгем");
        list.add("Матанализ");
        list.add("Тулзы");
        list.add("ДискрААА");

        assertEquals("Китайский", list.get(0));
        assertEquals("Матанализ", list.get(2));
        assertEquals("ДискрААА", list.get(4));
    }
    @ParameterizedTest //пункт 5
    @ValueSource(ints = {-1, -100, 1})
    void noNoNoMrGotRemove(int index){
        list.add("AAA");
        assertThrows(IndexOutOfBoundsException.class,() -> list.get(index));
        assertThrows(IndexOutOfBoundsException.class,() -> list.remove(index));
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4})
    void removePlease(int index){
        list.add("Китайский");
        list.add("АААААнгем");
        list.add("Матанализ");
        list.add("Тулзы");
        list.add("ДискрААА");

        String expectRemovable = list.get(index);
        String forReal = list.remove(index);

        assertEquals(expectRemovable, forReal);
        assertEquals(4, list.size());
    }
    @Test //пункт 7
    void doNotAddNullus(){
        assertThrows(NullPointerException.class, () -> list.add(null));
    }
    @Test //пункт 8
    void iteration(){
        list.add("AAA");
        list.add("B");

        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());

        assertEquals("AAA",iterator.next());
        assertEquals("B",iterator.next());
        assertFalse(iterator.hasNext());
    }
    @Test //пункт 9
    void iteratiorIsNoMore(){
        list.add("One and only");
        Iterator<String> iterator = list.iterator();
        assertEquals("One and only", iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
