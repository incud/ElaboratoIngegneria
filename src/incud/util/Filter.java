package incud.util;

public interface Filter<T> {

    public boolean canPass(T data);

}