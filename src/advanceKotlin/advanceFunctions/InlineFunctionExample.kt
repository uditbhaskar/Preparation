package advanceKotlin.advanceFunctions

/**
 * # Inline Functions in Kotlin
 * 
 * This file demonstrates inline functions and their behavior with lambdas.
 * Shows performance optimizations and restrictions with inline functions.
 * 
 * ## Topics Covered:
 * - **Inline Functions**: Functions that are expanded at call site for performance
 * - **noinline**: Preventing specific lambda parameters from being inlined
 * - **crossinline**: Preventing non-local returns in lambda parameters
 * - **Non-local Returns**: Returning from outer function within lambda
 * - **Performance**: How inlining affects bytecode and execution
 * 
 * @author Udit
 * @since 1.0
 */

inline fun inlineExample(noinline param: (Int,String) -> Int,  paramA: (Int, String) -> Int) {
    param(23,"done")
    println("Before paramA")
    paramA(43,"")
    println("After paramA")
    val runnable = Runnable {
        println("Before run")
//        paramA(43,"")
        // If returned early will give error. It will try to exit from calling function. However,
        //  here it cant return from main because not in the same call stack because of the use of another thread,
        // if not used via another thread it would have been returned easily like above

        println("After run")
    }
    runnable.run()
}

fun main() {
    inlineExample(param = {it,it1->
        println("$it $it1")
        it
    }, paramA = { a, _ ->
        println("Inside paramA")
        return
        a
        // return doesn't non-local return cause param in crossInline
    })
}

/**

1. Why is non-local return allowed in inline functions?

When you write:

inline fun runBlock(block: () -> Unit) {
    block()
}

fun main() {
    runBlock {
        println("Inside block")
        return // This returns from main(), not just the lambda!
    }
    println("This will never print")
}

- Because `runBlock` is inline, the compiler replaces the call to `runBlock` with the body of the function, inserting your lambda directly into `main`.
- So, `return` inside the lambda is like writing `return` directly in `main`.
- This is called a **non-local return**.


2. Why is non-local return NOT allowed inside another thread or lambda?

When you do this:

inline fun runBlock(block: () -> Unit) {
    val runnable = Runnable {
        block() // block is now running inside Runnable run() method
    }
    runnable.run()
}

- Now, your lambda is being called **inside the `Runnable`'s `run()` method**, not directly in `main`.
- If you try to use `return` inside the lambda, what should it return from?
  - It can't return from `main`, because it's not running in `main` anymore.
  - It can't return from `runBlock`, because it's not running there either.
- The compiler **doesn't know where to return to**, so it gives an error.


Analogy

- Imagine you’re in your house (main function).
- You can walk out the front door (return from main).
- But if you get into a car (Runnable), and then try to walk out the front door, you can’t—you’re not in the house anymore!

---

3. How does `crossInline` help?

- `crossInline` tells the compiler:
  > "Don’t allow non-local returns from this lambda."
- This makes it safe to pass the lambda into another thread or lambda, because the compiler knows you won’t try to return from the wrong place.

Summary Table

| Where is lambda called?         | Can use `return` to exit outer function? |
|---------------------------------|------------------------------------------|
| Directly in inline function     | Yes                                      |
| Inside another lambda/thread    | No (compiler error)                      |

---

In short:
- Non-local return works only when the lambda is called directly in the same function.
- If you pass the lambda to another thread or lambda, non-local return is not possible, so Kotlin prevents it.

Let me know if you want a code demo or further clarification!
 */