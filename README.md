Source code for the Pragmatic Unit Testing
=====================

This is the source code for the [Building a Pragmatic Unit Test Suite Pluralsight course][L0].

There are two versions of the code base: the [old version][L5] that doesn't comply with the unit testing guidelines described in the course and the [new one][L6] which adheres to those guidelines.

How to Get Started
--------------

Both versions are fully functional and covered with auto-tests. In order to run integration tests for the new version, you need to [create a database][L2] and change the [connection string][L3].

Licence
--------------
[Apache 2 License][L1]

[L1]: http://www.apache.org/licenses/LICENSE-2.0
[L5]: old
[L6]: new
[L2]: new/src/DBCreationScript.sql
[L3]: new/src/Tests/Integration/Tests.cs
[L0]: https://www.pluralsight.com/courses/pragmatic-unit-testing
