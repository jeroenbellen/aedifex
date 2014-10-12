Aedifex
===================

> Auto-generate builders for POJOs.

Example:


    import io.aedifex.annotation.Aedifex;  
    import io.aedifex.annotation.AedifexIgnore;

    @Aedifex
    public class Foo {
        private String bar;
        @AedifexIgnore
        private String doNotAdd;
    }


Will create a builder:

    final Foo foo = Foo_.with().bar("Bar!").build();

