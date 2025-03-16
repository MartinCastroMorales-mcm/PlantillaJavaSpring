package cl.ubiobio_gps.plantilla_gps.helper;

public class Result<T, E> {
    private T value = null;
    private E error = null;
    private String ErrorMessage = "";

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public E getError() {
        return error;
    }
    public void setError(E error) {
        this.error = error;
    }


}
