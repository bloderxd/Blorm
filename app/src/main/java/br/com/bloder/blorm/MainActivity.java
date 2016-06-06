package br.com.bloder.blorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.bloder.blormlib.Blorm;
import br.com.bloder.blormlib.validation.Validate;

import static br.com.bloder.blormlib.validation.Validations.*;

public class MainActivity extends AppCompatActivity {

  private EditText editTextFilled;
  private Button submit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editTextFilled = (EditText) findViewById(R.id.edit_text_filled);
    submit = (Button) findViewById(R.id.submit);

    new Blorm.Builder().field(editTextFilled).is(filled).onSubmit(submit);

    new Blorm.Builder().field(editTextFilled).is(new Validate() {
      @Override
      public boolean validate() {
        return false;
      }

      @Override
      public void onError() {

      }
    }).onSubmit(submit);
  }
}
