package br.com.bloder.blorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.bloder.blormlib.Blorm;
import br.com.bloder.blormlib.validation.Action;

import static br.com.bloder.blormlib.validation.Validations.*;

public class MainActivity extends AppCompatActivity {

  private EditText editTextFilled;
  private Button submit;
  private CheckBox checkBox;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editTextFilled = (EditText) findViewById(R.id.edit_text_filled);
    submit = (Button) findViewById(R.id.submit);
    checkBox = (CheckBox) findViewById(R.id.check_box);

    editTextFilled.setText("Hello");

      new Blorm.Builder()
              .field(editTextFilled).is(filled)
              .field(checkBox).is(checked)
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
              .submitOn(submit);
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
