Aedifex
===================
[![Build Status](https://travis-ci.org/jeroenbellen/aedifex.svg)](https://travis-ci.org/jeroenbellen/aedifex)
[![Coverage Status](https://img.shields.io/coveralls/jeroenbellen/aedifex.svg)](https://coveralls.io/r/jeroenbellen/aedifex)


> Auto-generate builders for POJOs.

Example:


    import com.github.jeroenbellen.aedifex.annotation.Aedifex;
    import com.github.jeroenbellen.aedifex.annotation.AedifexIgnore;

    @Aedifex
    public class Foo {
        private String bar;
        @AedifexIgnore
        private String doNotAdd;
    }


Will create a builder:

    final Foo foo = $Foo.with().bar("Bar!").build();

