package dhairya.pal.n01576099.dp.ui.N013576099;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class N013576099ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public N013576099ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is N013576099 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}