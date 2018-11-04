package patterns;

public interface IObservable<T> {
	public void notifyObservers(T msg);
	public void addObserver(IObserver<T> o);
	public void removeObserver(IObserver<T> o);
}
