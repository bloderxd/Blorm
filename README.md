[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.bloder/blormlib/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.bloder/blormlib) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Blorm-green.svg?style=true)](https://android-arsenal.com/details/1/3874) [![Codewake](https://www.codewake.com/badges/ask_question.svg)](https://www.codewake.com/p/blorm) [![Codewake](https://www.codewake.com/badges/codewake.svg)](https://www.codewake.com/p/blorm)

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

Or like this:

```java
import static br.com.bloder.blormlib.validation.Validations.*;
 
new Blorm.Builder().field(editText).is(filled).and(email)
                   .andField(checkBox).is(checked)
                   .submitOn(button);
```
"field editText is filled and cpf and field checkBox is checked" Yeah... I love it :heart:

You can also make your own validations.
```java
new Blorm.Builder().validate(new Validate() {
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
new Blorm.Builder().validate(new Validate() {
      @Override
      public boolean validate() {
        return false;
      }

      @Override
      public void onError() {

      }
      
      @Override
      public void onSuccess() {
          
      }
    }).submitOn(submit);
```

# Success case

You can also define what program will do if all validations has passed:

```java
 new Blorm.Builder().field(editTextFilled).is(filled)
                    .andField(editTextFilled).is(email)
                    .onSuccess(new Action() {
                             @Override
                             public void call() {
                                  Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                             }}
        ).submitOn(submit);
```

In this example Blorm verify if an editText is filled and is a cpf and show a toast if all those validations pass.
<br><br>
Or you can implement a specific success case in each validation like this:

```java
new Blorm.Builder().validate(new Validate() {
        @Override
        public void onSuccess() {
          Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
        }
        @Override public boolean validate() { return false; }
        @Override public void onError() {}
      });
```

# Error case

With Blorm you can set individually error cases in each validation and you can make a general error case:

```java
new Blorm.Builder()
              .field(editTextFilled)
              .is(filled)
              .and(cpf)
              .onError(new Action() {
                @Override
                public void call() {
                  Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
              })
              .submitOn(submit);
```

In this example, if one of these validations failed, it will appears that error toast. 

# Import Blorm

## Gradle

```groovy
dependencies {
    compile 'com.github.bloder:blormlib:2.2'
}
```

## Maven
```xml
<dependency>
  <groupId>com.github.bloder</groupId>
  <artifactId>blormlib</artifactId>
  <version>2.2</version>
  <type>pom</type>
</dependency>
```
##Ivy

```xml
<dependency org='com.github.bloder' name='blormlib' rev='2.2'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>
```

# License

```
The MIT License (MIT)

Copyright (c) 2016 Bloder

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
