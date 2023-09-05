# jgiven-kotlin

Extensions that make working with the JVM BDD testing tool [jgiven](https://jgiven.org) and Kotlin even more fun.

[![stable](https://img.shields.io/badge/lifecycle-STABLE-green.svg)](https://github.com/toolisticon#stable)
[![Build Status](https://github.com/toolisticon/jgiven-kotlin/workflows/Development%20branches/badge.svg)](https://github.com/toolisticon/jgiven-kotlin/actions)
[![sponsored](https://img.shields.io/badge/sponsoredBy-Holisticon-red.svg)](https://holisticon.de/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.testing/jgiven-kotlin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.testing/jgiven-kotlin)

## Usage 

### Maven
```
<dependency>
  <groupId>io.toolisticon.testing</groupId>
  <artifactId>jgiven-kotlin</artifactId>
  <version>1.2.5.0</version>
  <scope>test</scope>  
</dependency>

```

## Features

### GIVEN,WHEN,THEN

when using kotlin, instead of

```
given()
  .some_facts()

`when`()  // when is a keyword in kotlin
  .something_happens()

then()
  .expect_a_result()

```

you can simply use the dynamic extension properties:

```kotlin
GIVEN
  .some_facts();

WHEN
  .something_happens()

THEN
  .expect_a_result()
```

(written in CAPS to avoid keyword conflicts).

### Implementing Stages

In jgiven-java, you have to do:

```java
class MyStage extends Stage<MyStage> {

  MyStage my_step() {
    // what the step does
    return self();
  }
}
```

jgiven-kotlin introduces the inline extension function `step()`, so this can be simplified to:

```kotlin
@JGivenKotlinStage
class MyStage : Stage<MyStage>() {

  fun `my step`() = step {
    // what the step does
  }
}
```

### JGivenKotlinStage annotation

Since all classes and functions are final by default in kotlin, you have to explicitly mark everything you write in a Stage to be `open`.

Using the `JGivenKotlinStage` annotation and kotlin's "all-open" compiler plugin, this can be avoided.

#### Gradle

```kotlin
plugins {
  id("org.jetbrains.kotlin.plugin.allopen") version "${kotlin_version}"
}

//...

allOpen {
  annotation("io.toolisticon.testing.jgiven.JGivenKotlinStage")
}
```

#### Maven

```xml

<plugin>
  <!-- Kotlin compiler -->
  <artifactId>kotlin-maven-plugin</artifactId>
  <groupId>org.jetbrains.kotlin</groupId>
  <configuration>
    <compilerPlugins>
      <plugin>all-open</plugin>
    </compilerPlugins>
    <pluginOptions>
      <option>all-open:annotation=io.toolisticon.testing.jgiven.JGivenKotlinStage</option>
    </pluginOptions>
  </configuration>
  <!-- ... -->
  <dependencies>
    <dependency>
      <groupId>org.jetbrains.kotlin</groupId>
      <artifactId>kotlin-maven-allopen</artifactId>
      <version>${kotlin.version}</version>
    </dependency>
  </dependencies>
</plugin>
```

## Notes

* These helpers where moved from [jgiven-addons](https://github.com/toolisticon/jgiven-addons) for better separation of concerns and
  simplified publishing.
* This extension might become obsolete once jgiven [supports this officially](https://github.com/TNG/JGiven/pull/407).
