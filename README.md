# Storm Comics

Test App using the [Marvel Developer API](http://developer.marvel.com/docs) showing a list of comics of Marvel's heroine Storm.

## Basics

### Architecture: 
 - `app` module: Views connected to their ViewModels using LiveData.
 - `data` module: Domain and data layers (UseCases, Repositories) separated in a (pseudo) clean architecture.
 - Ideally `data` should have no Android dependencies, but we do inject the `Application` for local sources. Separated, it's easier to add to other Android projects of the app (Watch, TV, etc).
 

### Libraries
 - *dagger* for dependency injection.
 - *retrofit* (& co) for API calls
 - *picasso* for showing images
 - *rxjava2* for threading and data combination (A possible TODO would be migrating to coroutines)
