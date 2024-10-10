package Util;
import java.util.Arrays;


public class Category<T> implements ICategory<T>{

    @Override
    public String getCategory(T product) {
        return null;
    }

    @Override
    public <E extends Enum<E>> void viewCategory(Class<E> classType) {
        E[] enumConstants = classType.getEnumConstants();
        Arrays.stream(enumConstants).forEach(val -> System.out.print(val + " || "));
//        Arrays.stream(innerClasses)
//                .filter(innerClass -> innerClass.isEnum())
//                .flatMap(innerClass -> Arrays.stream(innerClass.getEnumConstants()))
//                .forEach(System.out :: println);
    }

}
