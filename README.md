# Mate

## A embeddable programming language for every day usage

### Unified Error handling try catch + error values

Combining try+catch mechanisms with response values. 

```

// Handling errors
var {response, error} = try httpClient.sendRequest("GET", "https://example.com")

if(error) {
# Handle error
}

## Or let the program panic
var response = httpClient.sendRequest("GET", "https://example.com")
```

The `try` keyword is a replacement for the following code:

```
try {
  var response = httpClient.sendRequest("GET", "https://example.com")
  return Optional.of(response)
} catch (HttpException e) {
  return Optional.empty();
}
```

### Concurrency should be easy and sequential program flow is default.

```
# Functions

setup
teardown
displayLoadingAnimation
longRunning
longRunning2
longRunning3

setup()

# Schedule functions
go (displayLoadingAnimation, calculate, longRunning, longRunning2, longRunning3)

teardown()
```

The `await` keyword is a replacement for the following code:


```
ExecutorService pool = Executors.newFixedThreadPool(2);
Future<HttpResponse> future = pool.submit(() -> new HttpResponse());
future.wait();
return future.get();
```

### Getter Setter

```

# Getter setter
class Human {
  Date @birthday { get, set } # used with get and set keywords

  def init() {
    const bla = get @birthday()
    set @birthday(new Date())
  }

  set {
   public birthday: [](birthday) => @birthday = birthday
  }

  get {
    public birthday: []() => @birthday
  }

}

```

### Mixins

```
# Can define the inner "interface" like variables and state
# Where interfaces define the methods of a class
module Formats {

  def format(Str birthday) {
    guard birthday # Throws an error if birthday is null or undefined

    return birthday.replace(".", "-")
  }

}

# class wide include
class Human includes Formats {
  private Date @birthday { get, set } # used with get and set keywords

  # constructor
  def init() {
    format("08.06.1995")
  }
  
}

```

### Ideas

- Safety concept like deno
  - Only allow explicit claims to ressources like "file", "net", "os", "env"
- Are there differences between primitives and not?
  - I dont think so. (String, Number, Boolean) ~> (Str, Num, Bool)?
- No inheritance, only modules/mixins with limited scope (no access to local/instance/class variables)
- Easy and safe Concurrency and Paralellism mechanisms
    - await/go semantics
    - Auto scheduled
- Good embeddability
  - Give the Java program a context about memory usage, statistics, and so on
  - Something loke rubys GC.stats and so on.
- There are no SUM Types.
  - Interfaces over sum types
- Good stdlib by default
  - Avoid "everything as a package"
- Metaprogramming and Queryability
  - Queryable types like c#
  - Hooks extensibility like ruby
