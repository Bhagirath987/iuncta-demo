package com.demo.mobile.app.util.misc;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {
    public static int DEFAULT_WAIT = 500;

    public static Observable<String> from(@NonNull EditText editText) {

        final PublishSubject<String> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subject.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return subject;
    }


}
