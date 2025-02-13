package com.aldar.studentportal.ui.commonScreens.navigations.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.NewsAdapter;
import com.aldar.studentportal.databinding.FragmentHomeBinding;
import com.aldar.studentportal.models.contactsModel.ContactDataModel;
import com.aldar.studentportal.models.newDataModels.NewsDataModel;
import com.aldar.studentportal.ui.activities.common.LearnMoreActivity;
import com.aldar.studentportal.ui.activities.common.fee.OnlinePaymentActivity;
import com.aldar.studentportal.ui.activities.activtiesForFragments.LoginSignUpActivity;
import com.aldar.studentportal.utilities.PermissionUtil;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private ArrayList<ContactDataModel> contactList = new ArrayList<>();
    private HomeViewModel viewModel;
    private String android_id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        PermissionUtil.isContactPermissionGranted(getActivity());

        binding.layoutBlog.setOnClickListener(this);
        binding.layoutPortal.setOnClickListener(this);
        binding.layoutFee.setOnClickListener(this);
        binding.ivWhatsapp.setOnClickListener(this);
        binding.ivCall.setOnClickListener(this);
        binding.ivFacebook.setOnClickListener(this);
        binding.ivEmail.setOnClickListener(this);
        binding.btnShareFeedback.setOnClickListener(this);
        binding.btnInquireUs.setOnClickListener(this);

        return binding.getRoot();
    }

    @SuppressLint("HardwareIds")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        android_id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        int checkContact = SharedPreferencesManager.getInstance(getActivity()).getIntValue("contactStore");
        if (checkContact == 0) {
            //sendContact();
        }


        viewModel.getNews().observe(getViewLifecycleOwner(), newsResponseModel -> {
            if (newsResponseModel.getData() != null) {
                binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                NewsAdapter newsAdapter = new NewsAdapter(newsResponseModel.getData());
                binding.rvNews.setAdapter(newsAdapter);
            }
        });

        viewModel.getContact().observe(getViewLifecycleOwner(), commonApiResponse -> {
            if (commonApiResponse != null) {
                Toast.makeText(getActivity(), "" + commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                SharedPreferencesManager.getInstance(getActivity()).setIntValueInEditor("contactStore", 1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_blog:
                new InnerClass(getActivity()).redirectToMore();
                break;
            case R.id.layout_portal:
                new InnerClass(getActivity()).redirectToLogin();
                break;
            case R.id.layout_fee:
                new InnerClass(getActivity()).redirectToPayment();
                break;
            case R.id.iv_whatsapp:
                showWhatsApp();
                break;
            case R.id.iv_call:
                showDialer();
                break;
            case R.id.iv_facebook:
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL();
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
                break;
            case R.id.iv_email:
                loadEmail();
                break;
            case R.id.btn_share_feedback:
                new InnerClass(getActivity()).redirectToFeedback();
                break;
            case R.id.btn_inquire_us:
                new InnerClass(getActivity()).redirectToInqureUS();
                break;
        }
    }

    private static class InnerClass {

        private final WeakReference<Activity> weakReference;

        private InnerClass(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        private void redirectToMore() {
            Activity activity = weakReference.get();
            if (activity != null) {
                activity.startActivity(new Intent(activity, LearnMoreActivity.class));
            }
        }

        private void redirectToLogin() {
            Activity activity = weakReference.get();
            if (activity != null) {
                activity.startActivity(new Intent(activity, LoginSignUpActivity.class));
            }
        }

        private void redirectToPayment() {
            Activity activity = weakReference.get();
            if (activity != null) {
                activity.startActivity(new Intent(activity, OnlinePaymentActivity.class));
            }
        }

        private void redirectToInqureUS() {
            Activity activity = weakReference.get();
            if (activity != null) {
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                navController.navigate(R.id.nav_contact);
            }
        }

        private void redirectToFeedback() {
            Activity activity = weakReference.get();
            if (activity != null) {
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                navController.navigate(R.id.nav_feedback);
            }
        }

    }

    private void showWhatsApp() {
        String number = "+971 5 0963 1770";
        String url = "https://api.whatsapp.com/send?phone=" + number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void showDialer() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+971 4 282 6880"));
        startActivity(intent);
    }

    private void loadEmail() {
        Intent emailSelectorIntent = new Intent(Intent.ACTION_SENDTO);
        emailSelectorIntent.setData(Uri.parse("mailto:"));

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@aldar.ac.ae"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        emailIntent.setSelector(emailSelectorIntent);

        try {
            startActivity(emailIntent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getFacebookPageURL() {
        String FACEBOOK_URL = "https://www.facebook.com/ALDARUniversityCollegedubai";
        String FACEBOOK_PAGE_ID = "ALDARUniversityCollegedubai";
        PackageManager packageManager = getActivity().getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    private void sendContact() {
        disposables.add(Observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<ContactDataModel>>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ArrayList<ContactDataModel> contactList) {
                        String strName = null, strNumber = null;
                        if (contactList.size() > 0) {
                            for (int k = 0; k < contactList.size(); k++) {
                                if (k == 0) {
                                    strName += contactList.get(k).getNameContact();
                                    strNumber += contactList.get(k).getNumContact();
                                } else {
                                    strName += "," + contactList.get(k).getNameContact();
                                    strNumber += "," + contactList.get(k).getNumContact();
                                }
                            }
                            viewModel.sendContactToServer(android_id, strName, strNumber);
                        }
                    }
                }));
    }

    Observable<ArrayList<ContactDataModel>> Observable() {
        return Observable.defer((Supplier<Observable<ArrayList<ContactDataModel>>>) () -> {
            // Do some long running operation
            return Observable.just(getContactsIntoArrayList());
        });
    }

    private ArrayList<ContactDataModel> getContactsIntoArrayList() {
        contactList = new ArrayList<>();
        String name, phoneNumber;

        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ContactDataModel contactModel = new ContactDataModel();
            contactModel.setNameContact(name);
            contactModel.setNumContact(phoneNumber);

            contactList.add(contactModel);

        }
        cursor.close();
        return contactList;
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();

    }
}