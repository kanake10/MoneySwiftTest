I&M Bank Android Take Home Assignment
==============

Writing MoneySwiftTest App using [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/), in 100% Kotlin, using Android Jetpack Components, and in Compose :rocket:

## Prerequisites
To run the project in your local environment, you need
* Go to the Android studio and create a new project using the 'Get from version control' option.
* Paste this link `https://github.com/kanake10/MoneySwiftTest.git` 
* Build the project and run it
* Ready app APK can be downloaded from [here](https://drive.google.com/file/d/1-6rg0G4aPFUNEBkwkxmL9d8_f2mCY2wf/view?usp=drivesdk)

## Features
On its launch MoneySwift will:
  * First show a splash screen
  * Then the MoneySwift Products that are available for sale
  * onClick of one of the item's cards, we shall be navigated to the respective item's details screen
  * onClick on the Buy Now button, our app shall launch a dialog that expects us to key in our payment Details
  * After we successfully enter our correct payment details, another dialogs launches with a successful payment message
  * N/B, for our payment to be successful, the payment details dialog expects a 16 digit CardNumber, a 3 digits CVC and in our MM/YY textfield, the Month has to be between 1-12 and all these payment details have to be filled otherwise the app shall show an error.


## Tech-stack
* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a modern, cross-platform, statically typed, general-purpose programming language with type inference.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - lightweight threads to perform asynchronous tasks.
    * [Room Database](https://kotlinlang.org/docs/reference/coroutines-overview.html) - lightweight library used to locally store our data.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - a stream of data that emits multiple values sequentially.
    * [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#:~:text=StateFlow%20is%20a%20state%2Dholder,property%20of%20the%20MutableStateFlow%20class.) - Flow APIs that enable flows to emit updated state and emit values to multiple consumers optimally.
    * [Dagger Hilt](https://dagger.dev/hilt/) - a dependency injection library for Android built on top of [Dagger](https://dagger.dev/) that reduces the boilerplate of doing manual injection.
    * [Gson](https://github.com/google/gson) A Java and Kotlin serialization/deserialization library to convert Kotlin/Java Objects into JSON and back
    * [Jetpack](https://developer.android.com/jetpack)
        * [Jetpack Compose](https://developer.android.com/jetpack/compose) - A modern toolkit for building native Android UI
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle state.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data lifecycle in a conscious manner and survive configuration change.
* Tests
    * [JUnit4](https://junit.org/junit4/)
    * [MockK](https://github.com/mockk/mockk)
    * [Mockito](https://github.com/mockito/mockito)
    * [Google Truth](https://github.com/google/truth)

* Architecture
    * MVVM—Model View ViewModel pattern with layer-based modularization. 
    * N/B This is a small project, hence Modularization is overkill, but it is implemented to demonstrate how layer-based modularization can be implemented in a large-scale complex project
  
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - An alternative syntax for writing Gradle build scripts using Koltin.
* CI/CD
    * [GitHub Actions](https://github.com/features/actions)

 ## App Preview
 
Let's take a look at our MoneySwift app:

Splash | Products | Product Details |Payment Details
--- | --- | --- |---
<img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/splash.jpeg" width="280"/> | <img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/products.jpeg" width="280"/> | <img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/details_screen.jpeg" width="280"/> | <img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/payment_details.jpeg" width="280"/>

Error Handling UI with Custom Error Displays :rocket::rocket:

Error on blank TextField | CVC Error | Card Number Error | Correct Details Example | Payment Success
--- | --- | --- | --- | ---
<img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/error_on-empty_field.jpeg" width="280"/> | <img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/cvc_error.jpeg" width="280"/> | <img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/card_number_error.jpeg" width="280"/> | <img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/correct_filled-details.jpeg" width="280"/> | <img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/payment_successful.jpeg" width="280"/>

## App Recording 🎥

https://github.com/kanake10/MoneySwiftTest/assets/77957614/ad55c9f1-24a1-4289-b27b-7a6da90b4e52

## Testing
The screenshots below show test results for tests done on this repo

#### Unit Tests
<img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/products-usecase.png"/>
<img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/product-usecase.png"/>
<img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/product-dao.png"/>
<img src="https://github.com/kanake10/MoneySwiftTest/blob/main/screenshots/products-dao.png"/>



