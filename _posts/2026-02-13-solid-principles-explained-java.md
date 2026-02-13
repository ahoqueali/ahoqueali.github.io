---
layout: post
title: "SOLID Principles Explained (with Java Examples)"
description: "A clear, developer-friendly explanation of the SOLID principles with Java examples—what they mean, why they matter, and how they look in practice."
date: 2026-02-13 11:43:00 +0000
last_modified_at: 2026-02-13 11:43:00 +0000
author: "Ali Hoque"
categories: [software-engineering, design-principles]
tags: [SOLID, Java, OOP, Clean Code, Design Patterns]
toc: true
toc_sticky: true
permalink: /blog/solid-principles-java-examples/
excerpt_separator: <!--more-->
---

Here’s a clear, developer-friendly explanation of the **SOLID principles** with **Java examples**. Each principle includes what it means, why it matters, and how it looks in practice.  
<!--more-->

---

# 🧱 **SOLID Principles Explained (with Java Examples)**

SOLID is a set of five design principles that make software **maintainable**, **scalable**, and **easy to extend**. It’s widely used in object‑oriented programming (OOP).

---

## 1. **S — Single Responsibility Principle (SRP)**

**A class should have only one reason to change.**

### ✅ What it means

Each class should do **one thing**, not multiple things. This reduces bugs and makes the code easier to maintain.

### ❌ Bad Example

The class does too much: formatting + sending email.

```java
public class EmailService {
    public String formatEmail(String message) {
        return "HTML: " + message;
    }

    public void sendEmail(String to, String message) {
        // logic to send email
    }
}
```

### ✅ Good Example (SRP applied)

```java
public class EmailFormatter {
    public String format(String message) {
        return "HTML: " + message;
    }
}

public class EmailSender {
    public void send(String to, String formattedMessage) {
        // send logic
    }
}
```

Each class has **one clear responsibility**.

---

## 2. **O — Open/Closed Principle (OCP)**

**Classes should be open for extension, but closed for modification.**

### ✅ What it means

You should be able to **add new behaviour** without changing existing code.

### ❌ Bad Example

Adding new payment types requires modifying the existing class:

```java
public class PaymentProcessor {
    public void pay(String type) {
        if(type.equals("card")) { }
        else if(type.equals("paypal")) { }
    }
}
```

### ✅ Good Example using **polymorphism**

```java
public interface PaymentMethod {
    void pay();
}

public class CardPayment implements PaymentMethod {
    public void pay() { }
}

public class PayPalPayment implements PaymentMethod {
    public void pay() { }
}

public class PaymentProcessor {
    public void process(PaymentMethod method) {
        method.pay();
    }
}
```

Now you can add **new payment types** without touching `PaymentProcessor`.

---

## 3. **L — Liskov Substitution Principle (LSP)**

**Subclasses should be usable in place of their base class without breaking behaviour.**

### ❌ Bad Example

Square breaks the expectations of Rectangle:

```java
public class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int w) { this.width = this.height = w; }
    @Override
    public void setHeight(int h) { this.width = this.height = h; }
}
```

A "square" **violates expected rectangle behaviour**, so substituting it causes bugs.

### ✅ Good Example

Use separate hierarchies:

```java
interface Shape {
    int area();
}

class Rectangle implements Shape {
    int width, height;
    public Rectangle(int w, int h) { width = w; height = h; }
    public int area() { return width * height; }
}

class Square implements Shape {
    int side;
    public Square(int s) { side = s; }
    public int area() { return side * side; }
}
```

Both objects now behave correctly when treated as `Shape`.

---

## 4. **I — Interface Segregation Principle (ISP)**

**Do not force a class to implement methods it doesn’t need.**

### ❌ Bad Example

One large interface forces unused methods:

```java
public interface Worker {
    void work();
    void eat();
}
```

A robot worker shouldn’t implement `eat()`.

### ✅ Good Example

```java
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public class Human implements Workable, Eatable {
    public void work() {}
    public void eat() {}
}

public class Robot implements Workable {
    public void work() {}
}
```

Interfaces are small, focused, and adaptable.

---

## 5. **D — Dependency Inversion Principle (DIP)**

**Depend on abstractions, not concrete implementations.**

### ❌ Bad Example

High-level class depends on a low-level concrete class:

```java
public class WiredKeyboard { }

public class Computer {
    private WiredKeyboard keyboard = new WiredKeyboard();
}
```

You cannot swap the keyboard easily.

### ✅ Good Example

```java
public interface Keyboard {}

public class WiredKeyboard implements Keyboard {}
public class BluetoothKeyboard implements Keyboard {}

public class Computer {
    private Keyboard keyboard;

    public Computer(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
```

Now the computer supports **any** type of keyboard.

---

## 🎉 Summary Table

| Principle | Meaning                                           | Benefit                         |
| --------- | ------------------------------------------------- | ------------------------------- |
| **S**     | One responsibility per class                      | Cleaner, maintainable code      |
| **O**     | Add functionality without modifying existing code | Safe extension                  |
| **L**     | Subclasses must behave like their parents         | Prevents unexpected bugs        |
| **I**     | Small, focused interfaces                         | Less coupling, more flexibility |
| **D**     | Depend on abstractions                            | Easy to swap implementations    |

---

If you'd like, I can also create:

- ✅ A UML diagram  
- ✅ A cheat sheet PDF  
- ✅ A more advanced real‑world example (e.g., SOLID in a banking system)
