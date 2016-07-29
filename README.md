# Storm Comics

Test App using the [Marvel Developer API](http://developer.marvel.com/docs) showing a list of comics of Marvel's heroine Storm.

## Basics

- Architecture: 
 - `data` package: Separates the repositories and the data models.
 - `domain` package: `UseCase`s go there, that is, strict business logic of the app. Complex computations should go there (There are none right now, ofc) and any Android dependency should be avoided. ~~(Maybe extract this to a pure Java module someday)~~
 - `ui` package: Following an MVP pattern, views have contracts that present the data, treated to be shown.

- Libraries
 - *retrofit* (& co) for API calls
 - *picasso* for showing images
 - *recycler view* (& co) for pretty card views
 - *rxjava* (&co) for threading (and readability)
