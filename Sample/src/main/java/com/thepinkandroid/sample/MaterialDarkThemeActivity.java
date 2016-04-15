package com.thepinkandroid.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.thepinkandroid.cardform.OnCardFormSubmitListener;
import com.thepinkandroid.cardform.view.CardForm;

public class MaterialDarkThemeActivity extends Activity implements OnCardFormSubmitListener {

    private CardForm mCardForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_form);

        mCardForm = (CardForm) findViewById(R.id.card_form);
        mCardForm.setRequiredFields(this, true, true, true, false, getString(R.string.add));
        mCardForm.setOnCardFormSubmitListener(this);
    }

    @Override
    public void onCardFormSubmit() {
        if (mCardForm.isValid()) {
            Toast.makeText(this, R.string.valid, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.invalid, Toast.LENGTH_SHORT).show();
        }
    }
}
