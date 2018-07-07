# downloader
Resource downloader.

A image-data downloading project for android. Uses a simple in-memory cache to donload and cache images. 
Also capable of downloading, parsing json data.
Uses MVP-CLEAN architecture.

The Model-View-Presenter(MVP) Pattern
Model — the data layer. Responsible for handling the business logic and communication with the network and database layers.
View — the UI layer. Displays the data and notifies the Presenter about user actions.
Presenter — retrieves the data from the Model, applies the UI logic and manages the state of the View, decides 
what to display and reacts to user input notifications from the View.

As a part of the Clean architecure implementation, using Usecase-Repository implementation.
UseCase is responsible for fetching and converting data from a request to the desired result format. For this, the UseCase
will interact with the Repository.
A Repository mediates between the domain and data mapping layers, acting like an in-memory domain object collection. 
Client objects construct query specifications declaratively and submit them to Repository for satisfaction. 
Objects can be added to and removed from the Repository, as they can from a simple collection of objects, and the 
mapping code encapsulated by the Repository will carry out the appropriate operations behind the scenes.

Possible improvements:
- Better error handling.
- A file cache for the images rather than a memory cache as it is currently.
- Avoid sequences of callbacks by using RX.
- Dependancy injection will make life way easier, with Dagger.
