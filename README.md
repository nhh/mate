# Mate
## A safe, typed, and embeddable programming language for every day usage

## A unique memory concept for different use cases

Write mate in two different flavors, either fluent or strict.

### Fluent

Fluent mate focuses on joy and developer flow. Its purely dynamic typed. No type and memory checks at "compile" time.
Everything is defered to runtime like ruby, python and javascript. Having the option to choose between two "flavors" of mate, 
should enable the developer to use mate in scripting environments aswell as micro controllers

```m8

// hello is infered to be a String of dynamic size
var hello = "World"

puts hello

```

### Strict
Strict type definition enforces provided memory information available at compile time.

```m8

// static type declaration in combination with manual memory information
// There are only 255 characters at maximum allowed
strict var hello = "Hello World" (255)

puts hello

```

```m8

// static type declaration in combination with manual memory information
// Only 15 strings with the size of 255 characters are allowed.
strict getUsers(): Array<String(255)>(15) {
    return this.users
}

```

```m8

// static type declaration in combination with manual memory information
// Only 15 strings with the size of 255 characters are allowed.
strict class User {
    const name: String(255)
    
    User() {
      this.name = "Niklas Hanft"
    }
}

```

### Ideas

- Makes async/paralell programming blue
  - I dont know how yet, but I will figure out
  - Maybe something like `var result = willReturnAFuture().await()`
  - Future.all(fut1, fut2, fut3).await()
- Safety concept like deno
  - Only allow explicit claims to ressources like "file", "net", "os", "env"
- Extreme good embeddability
  - Give the Java program a context about memory usage, statistics, and so on
  - Something loke rubies GC.stats and so on.
- Metaprogramming and Queryability
  - Queryable types like c#
  - Hooks extensibility like ruby