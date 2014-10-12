Aedifex
===================

> Auto-generate builders for POJOs.

Example:

    package example;
    import io.aedifex.annotation.Aedifex;

    @Aedifex
    public class Foo {
	    private String bar;
    }

Will create a builder:

    final Foo foo = Foo_.with().bar("Bar!").build();

