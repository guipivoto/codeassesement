# CodeAssessment

This is a simple app that display a list of images to the user and the user can select one of them to display larger.

## Stack and Libraries

* Kotlin v7.4.2
* [Retrofit](https://square.github.io/retrofit/)
* [Picasso](https://square.github.io/picasso/)
* [Hilt](https://dagger.dev/hilt/)

## Modules description

* `:app`: App's main modules which uses the Android application plugin
* `:buildSrc`: Common definition for whole project such as library and plugins versions
* `:code`: Base contracts for whole app. This module is a dependency for all other modules.
* `:features:home`: UI module. Home screen fragment
* `:features:details`: UI module. Photo details fragment
* `:features:navigation`: Defines common contracts that are used as dependency for all feature modules (contracts that 
allow a fragment to navigate from and to another fragment)
* `:models`: Definition of some model classes that are available across the whole app.
* `:repository`: Module responsible to retrieve photos from the backend and serve the app.
* `:test`: Definition of base classes to be used on unit tests. It should be used only for unit or instrumentation tests.