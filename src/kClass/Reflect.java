package kClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflect {
    private String name;
    private int age;

    public Reflect() { }

    public Reflect(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void greet() {
        System.out.println("Hello, my name is " + name);
    }
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("kClass.Reflect");

        Field field = clazz.getDeclaredField("name");
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        Object reflectInstance = constructor.newInstance("Udit", 30);
        Method method = clazz.getDeclaredMethod("greet");
        method.setAccessible(true);
        method.invoke(reflectInstance);
    }
}


