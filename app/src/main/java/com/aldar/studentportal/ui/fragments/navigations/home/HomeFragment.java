package com.aldar.studentportal.ui.fragments.navigations.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.common.faq.FaqActivity;
import com.aldar.studentportal.ui.activities.common.FeeActivity;
import com.aldar.studentportal.ui.studentPortal.activities.LoginSignUpActivity;
import com.aldar.studentportal.ui.activities.common.WebActivity;
import com.aldar.studentportal.utilities.FileUtils;
import com.aldar.studentportal.utilities.PermissionUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.layout_blog)
    LinearLayout layoutBlog;
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
    @BindView(R.id.btn_share_feedback)
    Button btnShareFeedback;
    @BindView(R.id.btn_inquire_us)
    Button btnInquireUs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        layoutBlog.setOnClickListener(this);
        layoutPortal.setOnClickListener(this);
        layoutFee.setOnClickListener(this);
        btnBoucher.setOnClickListener(this);
        layoutFAQ.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);
        ivWhatsapp.setOnClickListener(this);
        ivCall.setOnClickListener(this);
        ivEmail.setOnClickListener(this);
        btnShareFeedback.setOnClickListener(this);
        btnInquireUs.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_blog:
                new LeakyClass(getActivity()).redirectToWebview("https://www.aldar.ac.ae/category/english-content-blog/");
                break;
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
                String myPdfUrl = "https://www.aldar.ac.ae/wp-content/uploads/2019/06/Academic-Calendar.pdf";
                String url = "https://docs.google.com/gview?embedded=true&url="+myPdfUrl;
                new LeakyClass(getActivity()).redirectToWebview(url);
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
            case R.id.btn_share_feedback:
                new LeakyClass(getActivity()).redirectToFeedback();
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

        private void redirectToWebview(String message) {
            Activity activity = weakReference.get();
            if(activity != null) {
                Bundle bundle = new Bundle();
                bundle.putString("link",message);
                activity.startActivity(new Intent(activity, WebActivity.class).putExtras(bundle));
            }
        }


        private void redirectToInqureUS() {
            Activity activity = weakReference.get();
            if(activity != null) {
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                navController.navigate(R.id.nav_contact);
            }
        }

        private void redirectToFeedback() {
            Activity activity = weakReference.get();
            if(activity != null) {
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                navController.navigate(R.id.nav_feedback);
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

    }
}