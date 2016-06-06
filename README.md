# What's Blorm?

![alt tag](http://www.clipartbest.com/cliparts/dc7/ed7/dc7ed74Gi.png)

 Blorm is a field validation lib for android.
 
# What Blorm can validate?
 
 Blorm can validate any android field you want, like EditText's, TextViews's etc.

# How does it work?

With blorm you can do validations in a most beautiful way. Example:

    Blorm.Builder().field(editText).is(Validations.filled).onSubmit(button); 

And you can make it more beautiful! using static import in Validations class you can turn your validation in a phrase,
like:

    import static br.com.bloder.blormlib.validation.Validations.*;
    
    Blorm.Builder().field(editText).is(filled).onSubmit(button);

"Field is filled on submit" Beautiful! :heart::heart::heart::heart:

You can also make your own validations.

    new Blorm.Builder().field(editText).is(new Validate() {
      @Override
      public boolean validate() {
        return false;
      }
    }).onSubmit(button);
  

