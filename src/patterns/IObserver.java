package patterns;

public interface IObserver<T> {
	public void update(T msg);
}
