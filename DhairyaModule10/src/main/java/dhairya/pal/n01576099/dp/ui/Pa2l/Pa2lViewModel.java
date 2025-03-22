package dhairya.pal.n01576099.dp.ui.Pa2l;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Pa2lViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Pa2lViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Pa2l fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}