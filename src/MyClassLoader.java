import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * Class loader used so we can dynamically load classes. Normal class loader
 * doesn't close the .jar so you can't reload. This fixes that.
 *
 * @author James
 */
public class MyClassLoader extends URLClassLoader {
    private static Set<MyClassLoader> instances = new HashSet<MyClassLoader>();

    /**
     * Creates loader
     *
     * @param urls
     * @param loader
     */
    public MyClassLoader(URL[] urls, ClassLoader loader) {
        super(urls, loader);
        instances.add(this);
    }

    /**
     * Fix here.
     */
    public void close() {
        // If we run on Java 7, just call URLClassLoader.close()
        if (System.getProperty("java.version").startsWith("1.7")) {
            try {
                URLClassLoader.class.getDeclaredMethod("close").invoke(this);
                return;
            } catch (Exception ex) {
                // Probably IOException, ignore.
            }
        }
        try {
            Class<?> clazz = java.net.URLClassLoader.class;
            java.lang.reflect.Field ucp = clazz.getDeclaredField("ucp");

            ucp.setAccessible(true);
            Object sun_misc_URLClassPath = ucp.get(this);
            java.lang.reflect.Field loaders = sun_misc_URLClassPath.getClass().getDeclaredField("loaders");

            loaders.setAccessible(true);
            Object java_util_Collection = loaders.get(sun_misc_URLClassPath);

            for (Object sun_misc_URLClassPath_JarLoader : ((java.util.Collection) java_util_Collection).toArray()) {
                try {
                    java.lang.reflect.Field loader = sun_misc_URLClassPath_JarLoader.getClass().getDeclaredField("jar");

                    loader.setAccessible(true);
                    Object java_util_jar_JarFile = loader.get(sun_misc_URLClassPath_JarLoader);

                    ((java.util.jar.JarFile) java_util_jar_JarFile).close();
                    if(instances.contains(this)){
                        instances.remove(this);
                    }
                } catch (Throwable t) {
                    // if we got this far, this is probably not a JAR loader so
                    // skip it
                }
            }
        } catch (Throwable t) {
            // probably not a SUN VM
        }
    }

    /**
     * {@inheritDoc}
     *
     * This method is overridden to check other plugin class loaders as well.
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = this.tryLoadClass(name);
        if (clazz != null)
            return clazz;

        for (MyClassLoader cl : instances) {
            clazz = cl.tryLoadClass(name);
            if (clazz != null)
                return clazz;
        }

        throw new ClassNotFoundException(name);
    }

    private Class<?> tryLoadClass(String name) {
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public void addURL(URL url){
        super.addURL(url);
    }
}
