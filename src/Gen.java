public class Gen {
    public static void main(String[] args) {

        Storage<Double> storage = Storage.getStorageNotNull(1.0);
        Storage<Double> storage1 = Storage.getStorageNull(2.0);

        storage.setT(1.1111);
        System.out.println(storage.getT());

        storage1.setT(null);
        System.out.println(storage1.getT());

    }
}

class Storage<T> {
    T t;
    private boolean isNull;
    private T def;

    public Storage() {
    }

    private Storage(boolean isNull, T def) {
        this.isNull = isNull;
        this.def = def;
    }

    private Storage(T t) {
        this.t = t;
    }

    public T getT() {
        if (t == null) return def;
        T newT = t;
        t = null;
        return newT;
    }

    public void setT(T t) {
        if (t == null && !isNull) throw new NullPointerException();
        this.t = t;
    }

    public static <Q> Storage<Q> getStorageNotNull(Q q) {
        return new Storage<>(false, q);
    }

    public static <Q> Storage<Q> getStorageNull(Q q) {
        return new Storage<>(true, q);
    }

}