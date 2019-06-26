# Project Title

An Android App To View Images from Multiple Source(Currently from UnSplash). Written Entirely in Kotlin.

### Table of Contents

- [Architecture](#architecture)
- [Modules](#modules)
- [Getting Started](#getting-started)
- [Running the tests](#running-the-tests)
- [References](#reference)
- [Author Info](#author-info)

## Architecture

This Project follows multi module MVVM Architecture with clear separation of concerns. Clean Architecture Principles were followed with the use of [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
This project makes use of

* [Data Binding](https://developer.android.com/topic/libraries/data-binding) - For View Binding
* [Co Routines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For Asynchronous Programming
* [Kotlix-Serilaization](https://github.com/Kotlin/kotlinx.serialization) - For Serialization

## Modules

### HTTPClient
A simple Http client which uses Java's In-built URLConnection for Making Http Calls

### Image Loader
A simple Image Loader Module which user [HTTPClient](#HTTPClient) for fetching Images with basic Disk as well as Memory Caching Features.

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

```
Android Studio
```

### Running the tests

To Run Tests Run the Gradle Tasks with the following command

```
./gradlew test
```

## References

* [UnSplash](https://unsplash.com/developers) - The Source for Images
* [CoRoutines](https://github.com/Kotlin/kotlinx.coroutines)
* [Kotlix-Serilaization](https://github.com/Kotlin/kotlinx.serialization)

## Author Info

* **Purna** - [Github](https://github.com/purnaPrasanth)
