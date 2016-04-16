# Credit Card Form
CreditCardForm is a ready made card form layout that can be included in your app making it easy to
accept credit and debit cards. 
This git forks Card Form by braintree and added support for ISRACARD Israeli credit card company (8-9 digits)

![alt tag](https://cloud.githubusercontent.com/assets/4125349/14583828/ea1f6be0-0436-11e6-9464-e3f0c164e20c.gif)


## Adding It To Your Project

In your Project `build.gradle`:

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

In your module `build.gradle`:

```groovy
dependencies {
    compile 'com.github.davidbeloo:creditcardform:1.0.0'
}
```

## Usage

Card Form is a LinearLayout that you can add to your layout:

```xml
<com.thepinkandroid.cardform.view.CardForm
    android:id="@+id/bt_card_form"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

To initialize the view and change which fields are required for the user to enter, use
`CardForm#setRequiredFields(Activity activity, boolean cardNumberRequired, boolean expirationRequired, boolean cvvRequired, boolean postalCodeRequired, String imeActionLabel)`.

```java
CardForm cardForm = (CardForm) findViewById(R.id.bt_card_form);
cardForm.setRequiredFields(Activity.this, true, true, false, false, "Purchase");
```

To access the values in the form, there are getters for each field:

```java
cardForm.getCardNumber();
cardForm.getExpirationMonth();
cardForm.getExpirationYear();
cardForm.getCvv();
cardForm.getPostalCode();
```

To check if `CardForm` is valid call `CardForm#isValid()`. To validate each required field
and show the user which fields are incorrect, call `CardForm#validate()`.

Additionally `CardForm` has 3 available listeners:

* `CardForm#setOnCardFormValidListener` called when the form changes state from valid to invalid or invalid to valid.
* `CardForm#setOnCardFormSubmitListener` called when the form should be submitted.
* `CardForm#setOnFormFieldFocusedListener` called when a field in the form is focused.

## Styling

All card form inputs use the `colorAccent` theme attribute, when present, to set their focused color.
For more information on the `colorAccent` attribute, see [Using the Material Theme](https://developer.android.com/training/material/theme.html).

The Git included Sample app for examples of a Holo theme, Material light theme and Material dark theme.

## [Releases](https://github.com/davidbeloo/CreditCardForm/releases)

## License

Card Form is open source and available under the MIT license. See the [LICENSE](LICENSE) file for more info.

