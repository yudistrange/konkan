# konkan
[![Build Status](https://travis-ci.com/yudistrange/konkan.svg?branch=master)](https://travis-ci.com/yudistrange/konkan)

Learn you some konkani for good

The project is a glorified dictionary.

## Development Setup
```
make dev-setup
```
This should bring up local dependencies required by the application. The configurations are present in the `resources/config.edn` file.

## Running tests
```
profile=test lein test
```
Please note that `profile` environment var needs to be set for the tests to run against the test database. By default `profile` is set to dev, thus `lein test` in isolation will run the tests against the development database

## Development Setup for the frontend
The project uses shadow-cljs for building/releasing the frontend application. The repl can be started as:
```
shadow-cljs cljs-repl konkan-ui
```

## License

Copyright © 2019 udit

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
