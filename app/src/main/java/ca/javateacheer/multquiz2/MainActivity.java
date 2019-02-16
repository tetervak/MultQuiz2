/* Alex Tetervak, Sheridan College, Ontario */
package ca.javateacheer.multquiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  public static final String A = "A";
  public static final String B = "B";

  TextView mMessageView;
  TextView mProblemView;
  EditText mAnswerEdit;

  MultiplicationProblem mProblem = new MultiplicationProblem();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mMessageView = findViewById(R.id.message);
    mProblemView = findViewById(R.id.problem);
    mAnswerEdit = findViewById(R.id.answer);

    mProblemView.setText(mProblem.toString());
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(A,mProblem.getA());
    outState.putInt(B,mProblem.getB());
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    int a = savedInstanceState.getInt(A);
    int b = savedInstanceState.getInt(B);
    mProblem = new MultiplicationProblem(a, b);
    mProblemView.setText(mProblem.toString());
    check(null);
  }

  public void check(View view) {
    try{
      int userAnswer  = Integer.parseInt(mAnswerEdit.getText().toString());
      if(userAnswer == mProblem.getAnswer()){
        Toast.makeText(this,R.string.right_answer, Toast.LENGTH_LONG).show();
        mMessageView.setText(R.string.right_answer);
        mMessageView.setTextColor(getResources().getColor(R.color.Green500));
      }else{
        Toast.makeText(this,R.string.wrong_answer, Toast.LENGTH_LONG).show();
        mMessageView.setText(R.string.wrong_answer);
        mMessageView.setTextColor(getResources().getColor(R.color.Red500));
      }
    }catch(NumberFormatException e) {
      Toast.makeText(this, R.string.illegal_input, Toast.LENGTH_LONG).show();
    }
  }

  public void next(View view) {
    mMessageView.setText(R.string.enter_answer);
    mMessageView.setTextColor(getResources().getColor(R.color.Indigo700));
    mAnswerEdit.setText("");
    mProblem.reset();
    mProblemView.setText(mProblem.toString());
  }
}
