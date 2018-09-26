package ws.base;

public class WsPair<K,V> {
	private final K k;
	private final V v;
	
	public WsPair(final K k, final V v){
		this.k = k;
		this.v = v;
	}
	
	public K getKey() {
		return this.k;
	}
	
	public V getValue() {
		return this.v;
	}
}
