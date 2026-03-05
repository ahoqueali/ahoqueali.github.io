---
layout: post
title: "SOLID Principles: The Shapes Edition"
date: 2026-03-05 14:00:00 +0000
categories: [Java, Design Patterns]
tags: [java, solid, oop]
---

Understanding the **SOLID** principles is the difference between a codebase that is a house of cards and one that is a fortress. Let's break these down using a simple Shape-based ecosystem in Java.

---

## 1. Single Responsibility Principle (SRP)
**"A class should have one, and only one, reason to change."**

### The Problem
In this version, the `Circle` is trying to be both a mathematical model and a UI renderer. If your rendering engine changes, you have to modify your logic class.

```java
public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    // Responsibility 1: Mathematical Logic
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Responsibility 2: UI/Rendering
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }
}

```

### The Solution

Separate the concerns. The `Circle` holds data, and the `Canvas` handles the drawing logic.

```java
public class Circle {
    private final double radius;
    public Circle(double radius) { this.radius = radius; }
    public double getRadius() { return radius; }
    public double getArea() { return Math.PI * radius * radius; }
}

public class Canvas {
    public void drawCircle(Circle circle) {
        System.out.println("Drawing circle at Position: 0,0 | Radius: " + circle.getRadius());
    }
}

```

---

## 2. Open/Closed Principle (OCP)

**"Software entities should be open for extension, but closed for modification."**

### The Problem

If we want to add a `Square`, we have to modify the `Canvas` class by adding a `drawSquare` method. This violates OCP.

```java
public class Canvas {
    public void drawCircle(Circle circle) { /* ... */ }
    public void drawSquare(Square square) { /* ... */ } 
    // What happens when we add Triangle? Or Pentagon? We keep editing Canvas.
}

```

### The Solution

Use an interface. Now, `Canvas` can render any `Shape` without ever needing to be modified again.

```java
public interface Shape {
    void draw(); 
}

public class Circle implements Shape {
    @Override
    public void draw() { System.out.println("Drawing a Circle."); }
}

public class Canvas {
    public void render(Shape shape) {
        // This code NEVER needs to change.
        shape.draw();
    }
}

```

---

## 3. Liskov Substitution Principle (LSP)

**"Subtypes must be substitutable for their base types."**

### The Problem

A `Square` is technically a `Rectangle`, but in code, forcing that relationship often breaks logic. Setting the width of a Square shouldn't unexpectedly change its height if the user expects a standard Rectangle behavior.

```java
public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width; // Unexpected side effect for a Rectangle user!
    }
}

```

### The Solution

Avoid the "Is-A" trap if it breaks constraints. Make both implement a common interface instead.

```java
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape {
    private int width, height;
    public Rectangle(int w, int h) { this.width = w; this.height = h; }
    @Override
    public int getArea() { return width * height; }
}

public class Square implements Shape {
    private int side;
    public Square(int side) { this.side = side; }
    @Override
    public int getArea() { return side * side; }
}

```

---

## 4. Interface Segregation Principle (ISP)

**"Clients should not be forced to depend upon interfaces that they do not use."**

### The Problem

A 2D `Square` shouldn't be forced to implement `calculateVolume()`.

```java
public interface Shape {
    double calculateArea();
    double calculateVolume(); // 2D shapes hate this
}

```

### The Solution

Split large interfaces into smaller, specific ones.

```java
public interface AreaCalculatable { double calculateArea(); }
public interface VolumeCalculatable { double calculateVolume(); }

public class Square implements AreaCalculatable {
    public double calculateArea() { return 10 * 10; }
}

public class Cube implements AreaCalculatable, VolumeCalculatable {
    public double calculateArea() { return 600; }
    public double calculateVolume() { return 1000; }
}

```

---

## 5. Dependency Inversion Principle (DIP)

**"Depend upon abstractions, not concretions."**

### The Problem

The `Canvas` is hard-coded to a `Circle`. It can't draw anything else without code changes.

```java
class Canvas {
    private Circle circle = new Circle(); // Hard dependency (Bad!)

    public void render() {
        circle.draw();
    }
}

```

### The Solution

Inject the abstraction (`Shape`) into the `Canvas`.

```java
public class Canvas {
    private Shape shape;

    // Dependency is injected, not hard-coded
    public Canvas(Shape shape) {
        this.shape = shape;
    }

    public void render() {
        shape.draw();
    }
}

```

---

## Summary (The Shapes Edition)

* **SRP**: Circle does math; Canvas does drawing.
* **OCP**: Use interfaces so you can add a `Triangle` without changing the `Canvas`.
* **LSP**: Don't force a `Square` to be a `Rectangle` if it breaks the logic.
* **ISP**: Don't force a `Square` to calculate `Volume`.
* **DIP**: Make the `Canvas` depend on a `Shape` interface, not a specific `Circle`.

```

Would you like me to add a section on how to implement these using a specific Framework like Spring Boot?

```
