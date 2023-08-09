package springbook.learningtest.jdk;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReflectTest {

    @Test
    public void invokeMethod() throws Exception{
        String name = "Spring";

        assertThat(name.length(), is(6));

        Method lengthMethod = String.class.getMethod("length");
        assertThat((Integer) lengthMethod.invoke(name), is(6));

        assertThat(name.charAt(0), is('S'));

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character)charAtMethod.invoke(name, 0), is('S'));
    }

//    @Test
//    public void simpleProxy(){
//        Hello hello = new HelloUppercase(new HelloTarget());
//        assertThat(hello.sayHello("Toby"), is("HELLO TOBY"));
//        assertThat(hello.sayHi("Toby"), is("HI TOBY"));
//        assertThat(hello.sayThankYou("Toby"), is("THANK YOU TOBY"));
//
//        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
//                getClass().getClassLoader(),
//                new Class[] {Hello.class},
//                new UppercaseHandler(new HelloTarget())
//        );
//    }

}
