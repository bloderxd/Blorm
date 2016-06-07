# What's Blorm?

![alt tag](http://www.clipartbest.com/cliparts/dc7/ed7/dc7ed74Gi.png)

 Blorm is a field validation lib for android.
 
# What Blorm can validate?
 
 Blorm can validate any android field you want, like EditText's, TextViews's etc.

# How does it work?

With blorm you can do validations in a most beautiful way. Example:
 ```java
new Blorm.Builder().field(editText).is(Validations.filled).submitOn(button); 
 ```
And you can make it more beautiful! using static import in Validations class you can turn your validation in a phrase,
like:
```java
 import static br.com.bloder.blormlib.validation.Validations.*;
 
new Blorm.Builder().field(editText).is(filled).submitOn(button);
```
"Field is filled on submit" Beautiful! :heart::heart::heart::heart:

You can also make your own validations.
```java
new Blorm.Builder().field(editText).is(new Validate() {
  @Override
  public boolean validate() {
    return false;
  }
 }).submitOn(button);
```

# Error Messages

Blorm also supports custom error messages, EditText error message example:

```java
new Blorm.Builder().field(editTextFilled).is("Your Error Message", filled).submitOn(submit);
```
Or you can also make your error case custom.

```java
new Blorm.Builder().field(editTextFilled).is(new Validate() {
      @Override
      public boolean validate() {
        return false;
      }

      @Override
      public void onError() {

      }
    }).submitOn(submit);
```

# Success case

You can also define what program will do if all validations has passed:

```java
 new Blorm.Builder().field(editTextFilled).is(filled)
                    .field(editTextFilled).is(cpf)
                    .onSuccess(new Action() {
                             @Override
                             public void call() {
                                  Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                             }}
        ).submitOn(submit);
```

In this example Blorm verify if an editText is filled and is a cpf and show a toast if all those validations pass.

# Error case

With Blorm you can set individually error cases in each validation and you can make a general error case:

```java
new Blorm.Builder()
              .field(editTextFilled)
              .is(filled)
              .is(cpf)
              .onError(new Action() {
                @Override
                public void call() {
                  Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
              })
              .submitOn(submit);
```

In this example, if one of these validations failed, it will appears that error toast. 
