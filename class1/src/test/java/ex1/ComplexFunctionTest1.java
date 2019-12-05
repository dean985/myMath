package ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexFunctionTest1 {
    @Test
    void constructor(){
        Monom m1 = new Monom(2,2);
        Monom m2 = new Monom(3,3);
        ComplexFunction cf = new ComplexFunction("plus", m1,m2);
        m1.add(m2);
        Monom res = new Monom(m1.toString());
        assertEquals(m1.toString(),cf.toString() );
    }
    @Test
    void f() {
    }

    @Test
    void initFromString() {
    }

    @Test
    void copy() {
    }

    @Test
    void plus() {
    }

    @Test
    void mul() {
    }

    @Test
    void div() {
    }

    @Test
    void max() {
    }

    @Test
    void min() {
    }

    @Test
    void comp() {
    }

    @Test
    void left() {
    }

    @Test
    void right() {
    }

    @Test
    void getOp() {
    }
}