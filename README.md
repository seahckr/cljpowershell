# cljpowershell

A simple library to work with Powershell in idiomatic clojure

Builds on the [jPowershell](https://github.com/profesorfalken/jPowerShell) library

## Usage

To execute a series of command on a remote node

```
(cljpowershell.core/on "dev-deepne" "hostname" "hostname" "hostname")
=> ("dev-deepne\r\n" "dev-deepne\r\n" "dev-deepne\r\n")


```

Assumes that Powershell remoting between localhost and remote node is working.

Intended to be a drop-in replacement for 
https://github.com/jepsen-io/jepsen/blob/master/jepsen/src/jepsen/control.clj

## TODO

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
