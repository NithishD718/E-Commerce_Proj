package Util;

public interface ICategory<T> {

String getCategory(T product);

    default <E extends Enum<E>> void viewCategory(Class<E> category) {
    }

}
