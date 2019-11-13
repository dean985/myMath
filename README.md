# polynom
## Java library working with polynomial.

  the library contain the classes `Monom`,`Polynom`.

  the interface `Polynom_able`

  and the testers `Monomtest`, `Polynomtest`.

### Monom class:

#### Constructors:

1) `Polynom()` - Creates zearo monom which defined as "0x^0".

2) `Polynom(String s)` - Creates polynomial from String. F.E. s = "x^2+2x+3"

#### Methods:

### Polynom class:

#### Constructors:

1) `Polynom()` - Creates zearo monom which defined as "0x^0".

2) `Polynom(String s)` - Creates polynomial from String. F.E. s = "x^2+2x+3"

#### Methods:

1) `double f(double x)` - return the y valu of the polynom for given x value.

2) `void add(Polynom_able p1)` - add the given polynom to the current polynom object, the answer get it to the currnt polynom object.

3) `void add(Monom m1)` - add the given Monom to the current polynom.

4) `void substract(Polynom_able p1)` - substract the given polynom_able with the current polynom the answer it put in the current polynom object.

5) `void multiply(Polynom_able p1)` - multiply the given object polynom_able with the current polynom the answer get in to the current polynom object

6) `boolean equals(Polynom_able p1)` - return true when the current polynom object is equle to the the given polynom_able object else return false.

7) `boolean isZero()` - return true when the polynom is zero which mean it contain zero Monom.

8) `double root(double x0, double x1, double eps)` - 

9) `Polynom_able copy()` - 

10) `Polynom_able derivative()` -

11) `double area(double x0, double x1, double eps)` -

12) `Iterator<Monom> iteretor()` -

13) `void multiply(Monom m1)` -

14) `String toString()` - 


