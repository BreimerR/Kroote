# I am **KROOTE**

Ktor Router Provides for routing code generator using [KSP](https://github.com/google/ksp)

## Usage

### Adding Dependencies

1. Add KSP plugin

```kotlin

plugins {
    // ...
    id("com.google.devtools.ksp") version "[kotlinVersion]()-[symbolProcessingVersion]()"
    // ...
}
```

#### Adding NonMultiplatform Dependencies

> ### Disclaimer
> Refer to the [Test Project](./test) For Build Scripts and usage.

1. Plugin

```kotlin
plugins {
    kotlin("multiplatform")
    // ...
}
```

2. Dependencies

```kotlin
kotlin {
    // ...
}
// Only use depending on your requirements either jvm/kotlin/multiplatform 
dependencies {
    // for kotlin common I think 😅 Couldn't find specifications & using alone might cause descriptor exceptions.
    add("kspMetadata", project(":kroote"))

    add("kspJvm", project(":kroote")) // jvmMainCodeGeneration
    add("kspJvmTest", project(":kroote")) // jvmTestCodeGeneration
    add("kspJs", project(":kroote")) // jsMainCode generation
    add("kspJsTest", project(":kroote")) // ...  
    add("kspAndroidNativeArm64", project(":kroote")) // ...
    add("kspAndroidNativeArm64Test", project(":kroote")) // ...
    add("kspLinuxX64", project(":kroote")) // ...
    add("kspLinuxX64Test", project(":kroote")) // ...
    add("kspMingwX64", project(":kroote")) // ...
    add("kspMingwX64Test", project(":kroote")) // ...
}
```

#### Adding Multiplatform Dependencies

1. Plugin

```kotlin
plugins {
    kotlin("jvm")
    // ...
}
```

## Dependencies

[BuildSrc](https://github.com/BreimerR/BuildSrc.git) // Easy to use gradle scripts

> Run
1. ```git clone https://github.com/BreimerR/BuildSrc.git```
2. ```cd BuildSrc```
3. ```build publishMavenLocal```
>
> Add to your project
> ```
> repositories{  
>     //...  
>     mavenLocal()  
> }
> ```

## Design

## Issues

### Versioning conventions

[libraryMajorVersion][libraryMinorUpdatesVersion][kotlinVersion][untestedIdentifier]
1.0.1531-SNAPSHOT

## Publishing *Unpublished*

Preferred mavenPublication

No support for maven build scripts yet

I'll update the documentation on writing your own KSP application soon. Also Checkout [Rebo]()-> Link Coming Soon.

<a href="https://www.paypal.com/donate/?hosted_button_id=CUHRL6CUYWRTA" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>




