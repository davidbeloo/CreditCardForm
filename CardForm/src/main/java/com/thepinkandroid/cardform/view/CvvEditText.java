package com.thepinkandroid.cardform.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.thepinkandroid.cardform.R;
import com.thepinkandroid.cardform.utils.CardType;

/**
 * An {@link android.widget.EditText} that displays a CVV hint for a given Card type when focused.
 */
public class CvvEditText extends FloatingLabelEditText implements TextWatcher {

    private static final int DEFAULT_MAX_LENGTH = 3;

    private CardType mCardType;
    private boolean mAlwaysDisplayHint = false;

    public CvvEditText(Context context) {
        super(context);
        init();
    }

    public CvvEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CvvEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setFilters(new InputFilter[]{new LengthFilter(DEFAULT_MAX_LENGTH)});
        addTextChangedListener(this);
    }

    /**
     * Sets the card type associated with the security code type. {@link CardType#AMEX} has a
     * different icon and length than other card types. Typically handled through
     * {@link com.thepinkandroid.cardform.view.CardEditText.OnCardTypeChangedListener#onCardTypeChanged(com.thepinkandroid.cardform.utils.CardType)}.
     *
     * @param cardType Type of card represented by the current value of card number input.
     */
    public void setCardType(CardType cardType) {
        mCardType = cardType;
        InputFilter[] filters = {new LengthFilter(cardType.getSecurityCodeLength())};
        setFilters(filters);
        invalidate();
    }

    /**
     * Force the CVV hint image to always show.
     * <p/>
     * By default, {@link com.thepinkandroid.cardform.view.CvvEditText} will only show the hint
     * when the view is focused.
     *
     * @param alwaysDisplayHint Whether or not to show display hint when not focused.
     */
    public void setAlwaysDisplayHint(boolean alwaysDisplayHint) {
        mAlwaysDisplayHint = alwaysDisplayHint;
        invalidate();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mCardType == null) { return; }

        if (mCardType.getSecurityCodeLength() == editable.length() &&
                getSelectionStart() == editable.length()) {
            validate();

            if (isValid()) {
                focusNextView();
            }
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        int cvvResource = 0;
        if (focused || mAlwaysDisplayHint) {
            if (mCardType == null) {
                cvvResource = R.drawable.bt_cvv_highlighted;
            } else {
                cvvResource = mCardType.getSecurityCodeResource();
            }
        }

        if(mRightToLeftLanguage) {
            setCompoundDrawablesWithIntrinsicBounds(cvvResource, 0, 0, 0);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, cvvResource, 0);
        }
    }

    @Override
    public boolean isValid() {
        return getText().toString().length() == getSecurityCodeLength();
    }

    private int getSecurityCodeLength() {
        if (mCardType == null) {
            return DEFAULT_MAX_LENGTH;
        } else {
            return mCardType.getSecurityCodeLength();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
}
