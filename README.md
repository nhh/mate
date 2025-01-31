# Mate

## Syntax ideas

### module information (header/interface)

This whole language is influenced by the use of the following languages in order: ruby -> java -> ts -> golang -> clang

31.12.2025. This is inspired by Clang header files, Golang modules (folders) the chapter 4 "Modules should be deep" in "A philosophy of software design" by John Ousterhout

I think I dont want to have classes anymore. The more I work with go and C where no classes exist, I think having a module structure coupled to folders is enough. 


- Variables are private by default (file scope)
- Variables can be marked as module available (module scope)
- Variables cannot be included in module definition, which makes it public

- Public API surface must be implemented somewhere in src/humans (the whole folder is the module)
- Module definition dont have an implementation
- Module implementations cannot define public module api, needs to be done via `.mod` file

// ts: type -> attributes and functions
// c: struct -> attribute and functions
// java: interface -> only functions
// golang: struct[type|interface] a struct can either be a type or a interface
`type`, `interface`, `struct`

I often use these words in ways that are not strictly related to the meaning of it in java/ts/c

`src/humans/.mod`
```ts

  // types are PascalCase
  // methods are lowerCamelCase
  // attributes are lowerCamelCase

  struct CreateParams {
    fullname: String
  }

  struct Human {
    speak(): Void
    shout(): Void
    eat(): Void
  }

  newHuman(params: CreateParams): Human
  veryPublic(): Void
```

`src/humans/human.m`
```ts
  // std -> standard lib
  // src -> user code
  // pkg -> dependencies
  include "std/io/console"
  include "std/array/list"
  include "pkg/github.com/nhh/m8-yaml/yaml"

  include "src/human"
  
  // Can be accessed by all files in src/humans (whole module)
  mod interalVariable: string

  // Struct are implementations and only have attributes
  // (thoughts) Need to think more about type def and struct initialization. And how it is going to be used
  var admin: Human = { 
    fullname = ""
  }

  var admin: Human[] = []

  Human::speak(): void {
    io::console::writeLine(this.fullname)
  }

  Human::shout(): void {
    io::console::writeLine(this.fullname.toUpperCase())
  }

  Human::eat(): void {}

  someInternalMethod(): void {
    io::console::writeLine(admin.toString())
  }

  newHuman(): Human {
   return admin
  }

  triggerError(): String, Error {}
```

`main.m`
```ts
include "src/humans"

humans::veryPublic()

var human = humans::newHuman()
```


Combining try+catch mechanisms with response values.

EDIT: 31.01.2025

every `try` keyword captures exceptions being thrown and return it as seperate parameter like in go. But this is opt in. (Some scripts panic top level anyway)


```
var response = myModule.triggerError() // by default panic
var response, error = try myModule.triggerError() // captures paniced module func and returns the error as value
```

EDIT: 31.01.2025 // After talking to Augusto

Errors are always checked and ignoring it is opt in.

```
// default, errors need to be handled
var response, err = myModule.triggerError()

// Different syntax possible
var response = myModule.triggerError()!
var response = panic myModule.triggerError()

// Maybe panic is a function thats generic and takes a function that returns smth+error and wraps error handling
var response = errors.panic(myModule.triggerError())

// not part of std, part of the language
var response = panic myModule.triggerError()
```

### parameter/return value mapping operator

```
// I need generics
map(T[], (T number):T): T[]
filter(T[], (T number): T): T[]


var numbers: int[] = [1,2,3,4,5,6]

var myList = list.filter(numbers, (num: int) => num >= 2)) >> list.map((num: int) => num * 2))

```

### 

### conditional returns
syntactic sugar for early exit/ guard clauses
```

def doSomething(name)
return (!name) "This is a default message"
return (name.length <= 3) "This is a default message"

# Or

return "This is a default message" if (name.length <= 3)
return "This is a default message" if (!name)

end

```

### Unified Error handling try catch + error values

Combining try+catch mechanisms with response values. 

```
// Idea  15.01.2025
// Basically objects are always Response|Error and can be checked with the 'is' operator
// Why returning two thing when one might be enough? (just a thought)
response := httpClient.sendRequest("GET", "https://example.com")

if (response is Error) {
  panic(response.message())
}

resJson := response.json() // type Json|Error

if (resJson is Error) {
  panic(resJson.message())
}

// Idea  15.01.2025
// Method invocation chaining. The first error that happens will be returned. Kind of a safe navigation operator
// Basically invocations are always safe and can be chained
json := httpClient < sendRequest("GET", "https://example.com") < json()

if (response is Error) {
  panic(response.message())
}

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
- ~No inheritance, only modules/mixins with limited scope (no access to local/instance/class variables)~
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
