# Mate

## A embeddable programming language for every day usage


## Unified Error handling try catch + error values

Combining try+catch mechanisms with response values. 

```
// the try keyword takes the response type from the caller and wraps it into an Optional.
var response = try httpClient.sendRequest("GET", "https://example.com")
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

## Everything is awaitable

By default every function is synchronous. You can await a function with the keyword `await`. There is no need to mark a function as async. The keyword will wrap the function in a CompletableFuture in schedule it in a thread pool executor. 

Its basically just a instruction to the vm that this function should be run on a separat thread/core.

```
var response = await httpClient.sendRequest("GET", "https://example.com")
```

The `await` keyword is a replacement for the following code:


```
ExecutorService pool = Executors.newFixedThreadPool(2);
Future<HttpResponse> future = pool.submit(() -> new HttpResponse());
future.wait();
return future.get();
```


### Ideas

- Safety concept like deno
  - Only allow explicit claims to ressources like "file", "net", "os", "env"
- Good embeddability
  - Give the Java program a context about memory usage, statistics, and so on
  - Something loke rubys GC.stats and so on.
- Metaprogramming and Queryability
  - Queryable types like c#
  - Hooks extensibility like ruby
