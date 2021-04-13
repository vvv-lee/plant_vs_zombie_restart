//package game.spirits.util;
//
//import java.lang.reflect.Constructor;
//
//public class ReflectUtil {
//    private static final SimpleCache<Class<?>, Constructor<?>[]> CONSTRUCTORS_CACHE = new SimpleCache<>();
//
//    public static <T> T newInstance(Class<T> clazz, Object... params)  {
//        if (params == null || params.length == 0) {
//            final Constructor<T> constructor = getConstructor(clazz);
//            try {
//                return constructor.newInstance();
//            } catch (Exception e) {
//                throw new UtilException(e, "Instance class [{}] error!", clazz);
//            }
//        }
//
//        final Class<?>[] paramTypes = ClassUtil.getClasses(params);
//        final Constructor<T> constructor = getConstructor(clazz, paramTypes);
//        if (null == constructor) {
//            throw new UtilException("No Constructor matched for parameter types: [{}]", new Object[]{paramTypes});
//        }
//        try {
//            return constructor.newInstance(params);
//        } catch (Exception e) {
//            throw new UtilException(e, "Instance class [{}] error!", clazz);
//        }
//    }
//    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
//        if (null == clazz) {
//            return null;
//        }
//
//        final Constructor<?>[] constructors = getConstructors(clazz);
//        Class<?>[] pts;
//        for (Constructor<?> constructor : constructors) {
//            pts = constructor.getParameterTypes();
//            if (ClassUtil.isAllAssignableFrom(pts, parameterTypes)) {
//                // 构造可访问
//                setAccessible(constructor);
//                return (Constructor<T>) constructor;
//            }
//        }
//        return null;
//    }
//    public static <T> Constructor<T>[] getConstructors(Class<T> beanClass) throws SecurityException {
//        Assert.notNull(beanClass);
//        Constructor<?>[] constructors = CONSTRUCTORS_CACHE.get(beanClass);
//        if (null != constructors) {
//            return (Constructor<T>[]) constructors;
//        }
//
//        constructors = getConstructorsDirectly(beanClass);
//        return (Constructor<T>[]) CONSTRUCTORS_CACHE.put(beanClass, constructors);
//    }
//
//
//}
