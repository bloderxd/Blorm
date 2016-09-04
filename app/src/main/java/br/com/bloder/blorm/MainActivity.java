package br.com.bloder.blorm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.bloder.blormlib.Blorm;
import br.com.bloder.blormlib.validation.Action;

import static br.com.bloder.blormlib.validation.Validations.*;

public class MainActivity extends AppCompatActivity {

  private EditText editTextFilled;
  private EditText editTextFilled2;
  private EditText editTextFilled3;
  private Button submit;
  private CheckBox checkBox;
  private CheckBox checkBox2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editTextFilled = (EditText) findViewById(R.id.edit_text_filled);
    editTextFilled2 = (EditText) findViewById(R.id.edit_text_filled_2);
    editTextFilled3 = (EditText) findViewById(R.id.edit_text_filled_3);
    checkBox = (CheckBox) findViewById(R.id.check_box);
    checkBox2 = (CheckBox) findViewById(R.id.check_box2);
    submit = (Button) findViewById(R.id.submit);

    new Blorm.Builder().field(editTextFilled).is(email).whenChangeFocusOn(editTextFilled);

    new Blorm.Builder()
            .field(editTextFilled).is(email)
            .andField(editTextFilled2).is(number.withMaxSize(3))
            .andField(editTextFilled3).is(filled)
            .andField(checkBox).is(checked)
            .andField(checkBox2).is("test3", checked)
            .onSuccess(new Action() {
                  @Override
                  public void call() {
                      onSuccess();
                  }
              })
            .onError(new Action() {
                @Override
                public void call() {
                  onError();
                }
              })
            .onSubmit(submit);
  }

  private void onSuccess() {
    editTextFilled.setError(null);
    checkBox.setError(null);
    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
  }

  private void onError() {
    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
  }
}
