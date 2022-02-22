package fr.delcey.chipexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.main_button);
        TextInputEditText textInputEditText = findViewById(R.id.main_text_input_edit_text);

        button.setOnClickListener(view -> chipify(textInputEditText));
        textInputEditText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                chipify(textInputEditText);
                return true;
            }
            return false;
        });
    }

    private void chipify(TextInputEditText textInputEditText) {
        Editable text = textInputEditText.getText();

        ChipDrawable chipDrawable = ChipDrawable.createFromResource(MainActivity.this, R.xml.standalone_chip);
        // or ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(MainActivity.this, null, 0, 0);

        chipDrawable.setText(text);
        chipDrawable.setBounds(0, 0, chipDrawable.getIntrinsicWidth(), chipDrawable.getIntrinsicHeight());

        text.setSpan(new ImageSpan(chipDrawable), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}