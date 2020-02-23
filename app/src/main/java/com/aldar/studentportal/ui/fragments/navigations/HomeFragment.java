package com.aldar.studentportal.ui.fragments.navigations;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.FaqActivity;
import com.aldar.studentportal.ui.activities.FeeActivity;
import com.aldar.studentportal.ui.activities.SplashActivity;
import com.aldar.studentportal.ui.activities.containerActivities.LoginSignUpActivity;
import com.aldar.studentportal.ui.activities.inquireUs.InquireUsActivity;
import com.aldar.studentportal.ui.activities.StudentPortalMainActivity;
import com.aldar.studentportal.ui.activities.WebActivity;
import com.aldar.studentportal.utilities.FileUtils;
import com.aldar.studentportal.utilities.PermissionUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.layout_portal)
    LinearLayout layoutPortal;
    @BindView(R.id.layout_fee)
    LinearLayout layoutFee;
    @BindView(R.id.btn_download_boucher)
    Button btnBoucher;
    @BindView(R.id.layout_faq)
    LinearLayout layoutFAQ;
    @BindView(R.id.btn_calendar)
    Button btnCalendar;
    @BindView(R.id.iv_whatsapp)
    ImageView ivWhatsapp;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.iv_email)
    ImageView ivEmail;
    @BindView(R.id.btn_inquire_us)
    Button btnInquireUs;

    private LeakyClass leakyClass;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        layoutPortal.setOnClickListener(this);
        layoutFee.setOnClickListener(this);
        btnBoucher.setOnClickListener(this);
        layoutFAQ.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);
        ivWhatsapp.setOnClickListener(this);
        ivCall.setOnClickListener(this);
        ivEmail.setOnClickListener(this);
        btnInquireUs.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_portal:
                startActivity(new Intent(getActivity(), LoginSignUpActivity.class));
                break;
            case R.id.layout_fee:
                startActivity(new Intent(getActivity(), FeeActivity.class));
                break;
            case R.id.btn_download_boucher:
                PermissionUtils.checkPermision(getActivity());
                try {
                    new FileUtils(getActivity(), "https://www.aldar.ac.ae/wp-content/uploads/2019/06/Academic-Calendar.pdf");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_faq:
                new LeakyClass(getActivity()).redirectToFAQ();
                break;
            case R.id.btn_calendar:
                new LeakyClass(getActivity()).redirectToWebview();
                break;
            case R.id.iv_whatsapp:
                showWhatsApp();
                break;
            case R.id.iv_call:
                showDialer();
                break;
            case R.id.iv_email:
                loadEmail();
                break;
            case R.id.btn_inquire_us:
                new LeakyClass(getActivity()).redirectToInqureUS();
                break;
        }
    }

    private static class LeakyClass {

        private final WeakReference<Activity> weakReference;

        private LeakyClass(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        private void redirectToFAQ() {
            Activity activity = weakReference.get();
            if(activity != null) {
                activity.startActivity(new Intent(activity, FaqActivity.class));
            }
        }

        private void redirectToWebview() {
            Activity activity = weakReference.get();
            if(activity != null) {
                activity.startActivity(new Intent(activity, WebActivity.class));
            }
        }


        private void redirectToInqureUS() {
            Activity activity = weakReference.get();
            if(activity != null) {
                activity.startActivity(new Intent(activity, InquireUsActivity.class));
            }
        }

    }

    private void showWhatsApp(){
        String number = "+971 5 0963 1770";
        String url = "https://api.whatsapp.com/send?phone="+number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void showDialer(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+971 4 282 6880"));
        startActivity(intent);
    }

    private void loadEmail(){
        Intent emailSelectorIntent = new Intent(Intent.ACTION_SENDTO);
        emailSelectorIntent.setData(Uri.parse("mailto:"));

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@aldar.ac.ae"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        emailIntent.setSelector( emailSelectorIntent );

        try {
            startActivity(emailIntent);
        }
        catch (Exception e){
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        leakyClass = null;
    }
}