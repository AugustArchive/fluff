# 🐻‍❄️✨ fluff
> *Transforms a Javadoc Doclet into an AST that is allowed to be used for automation tools for Java documentation*

**fluff** (case-sensitive) is a tool to transform the current Javadoc Doclet into an abstract syntax tree that can emit "tokens" to be used in automation tools for generating Java documentation.

> **Note** -- `fluff`'s name case-sensitive and all lowercase when it is mentioned.

**fluff** was created to give an easy representation of what Javadoc generates and it's easy for tools like [Noeldoc](https://noelware.org/noeldoc) to analyze a Java library's documentation and generate a common schema for it.

## Usage
**fluff** is just a dependency that you can use to emit the tokens from. So, you would just want to add it as a normal dependency. Refer to the [Installation](#installation) for more information on how to get the **fluff** binary.

```java
public class MyDoclet {
  public void run(@NotNull Environment environment) {
    final FluffSyntaxTree ast = new Fluff(environemnt).generate();
    final PrintWriter writer = new PrintWriter(System.out);    

    System.out.println(ast.writeTo(writer));
  }
}
```

Now, you can pass your doclet with the **javadoc** binary:

```shell
$ javadoc -docletpath . -doclet MyDoclet
```

optionally, with Gradle:

```kotlin
// Kotlin DSL
tasks.withType<Javadoc> {

}
```

Now, you should see a pretty printed syntax tree in your console similarly to:

```sh
# TODO
```

## Installation
;;; coming soon ;;;

## Contributing
;;; coming soon ;;;

## License
**fluff** is released under the **MIT License** with love by Noel. <3 ~ Read the [LICENSE](/LICENSE) for more information. :3
