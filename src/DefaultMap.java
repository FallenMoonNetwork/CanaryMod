import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A {@link Map} wrapper that implements default values.
 * @author Willem Mulder (14mRh4X0r)
 */
public abstract class DefaultMap<K, V> implements Map<K, V> {
    private Map<K, V> map;

    /**
     * Creates a new instance with <tt>map</tt> as the underlying storage.
     * @param map the {@link Map} to use as storage
     */
    public DefaultMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        if (!map.containsKey((K) key)) {
            map.put((K) key, this.getDefaultValue());
        }
        return map.get((K) key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof DefaultMap && map.equals(((DefaultMap) o).map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("DefaultMap[map=%s]", map);
    }

    /**
     * Returns the default value for this {@link Map}. This is called by the
     * {@link #get(java.lang.Object)} method to pre-initialize the map with a
     * default value.
     * @return the default value for this {@link Map}
     */
    protected abstract V getDefaultValue();
    
}
