# hollywoodi

Very small project which contains my version of a dependency injection controller.
It is kept very simple and at the moment not very well documented.

## Usage

Check out the testcalsses for an example usage. Most of the time, a simple 
    @Injectable("name")
in combination with
    @RequestInjection("name")
is enough.

You do not have to specify the name, it defaults to the lowercased classname, in case of the @Injectable annotation
and to the property's name, in case of the @RequestInjection.

## Todos

* Make the configuration better(at the moment only annotations are supported)
* Comment the code =)
* Maybe add more advanced stuff, who knows

## License

See LICENSE