## OPlay!
Sample project, for searching and playing video using OCS API

| Programs search screen | Program details screen |Player|
|--|--|--|
| <img src="https://github.com/medhdj/oplay/blob/main/docs/Screenshot_search.jpg?raw=true" width="300" style="inline"> | <img src="https://github.com/medhdj/oplay/blob/main/docs/Screenshot_details.jpg?raw=true" width="300" style="inline"> |<img src="https://github.com/medhdj/oplay/blob/main/docs/Screenshot_player_portrait.jpg?raw=true" width="300" style="inline">|

| Player landscape |
|--|
| <img src="https://github.com/medhdj/oplay/blob/main/docs/Screenshot_player_fullscreen.jpg?raw=true" width="300" style="inline"> |
|<img src="https://github.com/medhdj/oplay/blob/main/docs/Screenshot_player_landscape.jpg?raw=true" width="300" style="inline">|


## Technologies:

- clean architecture + modularization 
- MVVM
- Kotlin
- Hilt
- coroutines
- Paging library 3
- Gson TypeAdapters
- .... see [BuildSrc](https://github.com/medhdj/oplay/blob/main/buildSrc/src/main/kotlin/Dependencies.kt) for more 


## Project architecture
The choice of the architecture implemented in this project is based on my previous experiences on different types of projects, I mixed concepts of clean architecture and modularization to make the exploration and understanding of the code as easy as possible, also giving the possibility to extend and evolve the project beyond the constraint of a specific platform.

With this architecture and the help of  Kotlin KMM, we can easily migrate and share all our business logic and  the data sources between iOS and Android.

<img src="https://github.com/medhdj/oplay/blob/main/docs/project_arch.png?raw=true">

**App layer:**
 Hosts all the android platform specifics like views, view models, livedata, this layer is also responsible for dependency injection using hilt

**Buisiness:**
In this layer we find the different use cases and models that contribute to fulfilling the business requirements, this layer also defines the contracts that the repositories must follow.

**Data:** This layer is responsible for fetching data from the sources in our case from the OCS API

**Core:** helper layer containing utilities and extensions